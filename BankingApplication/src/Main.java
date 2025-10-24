import entity.User;
import service.TransactionService;
import service.UserService;

import java.util.*;

public class Main{

    private static Scanner sc = new Scanner(System.in);
    static UserService userService = new UserService();
    static TransactionService transactionService = new TransactionService();
    static Main main = new Main();

    public static void main(String[] args) {

        while(true){
            System.out.println("Enter your username");
            String username = sc.next();

            System.out.println("Enter you password");
            String password = sc.next();

            userService.printUsers();

            User user = userService.login(username, password);
            if(user != null && user.getRole().equals("admin")){
                main.initAdmin();
            } else if (user != null && user.getRole().equals("user")){
                main.initCustomer(user);
            }else {
                System.out.println("Login failed");
            }
        }
    }

    private void initAdmin(){
        boolean flag = true;

        while(flag){
            System.out.println("1. Exit/Logout");
            System.out.println("2. Create customer account");
            System.out.println("3. See all transactions");
            System.out.println("4. Check balance");
            System.out.println("5. Approve check book request");

            int selectedOption = sc.nextInt();
            String userId = "";

            switch (selectedOption){
                case 1:
                    flag = false;
                    System.out.println("logged out successfully...");
                    break;
                case 2:
                    main.addCustomer();
                    break;
                case 3:
                    System.out.println("Enter user id:");
                    userId = sc.next();
                    main.printTransaction(userId);
                    break;
                case 4:
                    System.out.println("Enter user id:");
                    userId = sc.next();
                    Double accountBalance = checkAccBalance(userId);
                    System.out.println(userId + "'s account balance: " + accountBalance);
                case 5:
                    List<String> userIds = getUserIdForCheckBookRequest();
                    System.out.println("Please select user id from the below");
                    System.out.println(userIds);

                    userId = sc.next();

                    approveCheckBookRequest(userId);

                    System.out.println("CheckBook request is approved...");
                    break;
                default:
                    System.out.println("wrong choice");
            }
        }
    }

    private List<String> getUserIdForCheckBookRequest(){
        return transactionService.getUserIdForCheckBookRequest();
    }

    private void approveCheckBookRequest(String userId){
        transactionService.approveCheckBookRequest(userId);
    }

    private Double checkAccBalance(String userId){
        return userService.checkAccBalance(userId);
    }

    private void addCustomer(){
        System.out.println("Enter Username");
        String userName = sc.next();

        System.out.println("Enter Password");
        String password = sc.next();

        System.out.println("Enter Contact Number");
        String contactNo = sc.next();

        boolean res = userService.addUser(userName, password, contactNo);

        if(res){
            System.out.println("Customer account is added");
        }else{
            System.out.println("Customer account creation failed");
        }
    }

    private void initCustomer(User user) {
        boolean flag = true;

        while(flag){
            System.out.println("1. Exit/Logout");
            System.out.println("2. Check bank balance");
            System.out.println("3. Fund transfer");
            System.out.println("4. See all transactions");
            System.out.println("5. Raise cheque book request");

            int selectedOption = sc.nextInt();

            switch (selectedOption){
                case 1:
                    flag = false;
//                    transactionService.printTransactions();
                    System.out.println("logged out successfully...");
                    break;
                case 2:
                    Double balance = main.checkAccBalance(user.getUserName());
                    if(balance != null){
                        System.out.println("Your bank balance is " + balance);
                    } else {
                        System.out.println("Check your username.");
                    }
                case 3:
                    main.fundTransfer(user);
                    break;
                case 4:
                    main.printTransaction(user.getUserName());
                case 5:
                    String userId = user.getUserName();
                    Map<String, Boolean> map = getAllCheckBookRequest();

                    if(map.containsKey(userId) && !map.get(userId)){
                        System.out.println("you have already raised and request and it is pending for approval");
                    } else if (map.containsKey(userId) && map.get(userId)) {
                        System.out.println("You have already raised a request and it is already approved");
                    } else {
                        raiseChequeBookRequest(userId);
                        System.out.println("Request raised successfully...");
                    }
                    break;
                default:
                    System.out.println("wrong choice");
            }
        }
    }

    private void raiseChequeBookRequest(String userId){
        transactionService.raiseChequeBookRequest(userId);
    }

    private Map<String, Boolean> getAllCheckBookRequest(){
        return transactionService.getAllCheckBookRequest();
    }

    private void printTransaction(String userId){
        transactionService.printTransaction(userId);
    }

    private void fundTransfer(User userDetail) {
        System.out.println("Enter payee account user id: ");
        String payeeAccId = sc.next();

        User user = getUser(payeeAccId);

        if(user != null){
            System.out.println("Enter amount to transfer");
            Double amount = sc.nextDouble();

            Double userAccountBalance = checkAccBalance(userDetail.getUserName());

            if(userAccountBalance >= amount){
                boolean result = transactionService.transferAmount(userDetail.getUserName(), payeeAccId, amount);

                if(result){
                    System.out.println("Amount transferred successfully...");
                }else{
                    System.out.println("Transfer failed...");
                }
            }else{
                System.out.println("You balance is insufficient: " + userAccountBalance);
            }
        }else{
            System.out.println("Please enter valid username");
        }
    }

    private User getUser(String userId) {
        return transactionService.getUser(userId);
    }
}