package repository;

import entity.Transaction;
import entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionRepository extends UserRepository{
    private static List<Transaction> transactions = new ArrayList<>();
    private static Map<String, Boolean> chequeBookRequest = new HashMap<>();

//    public void printTransactions(){
//        for(Transaction transaction : transactions){
//            System.out.println(transaction);
//        }
//    }

    public void printTransaction(String userId){
        List<Transaction> filteredTransactions = transactions.stream().filter(transaction -> transaction.getTransactionProcessedBy().equals(userId)).collect(Collectors.toList());

        System.out.println("Date \t UserId \t Type \t InitialBalance \t FinalBalance");
        System.out.println("----------------------------------------------------------");
        for(Transaction t : filteredTransactions){
            System.out.println(t.getTransactionDate()
                +"\t"+t.getTransactionUserId()
                +"\t"+t.getTransactionType()
                +"\t\t"+t.getInitialBalance()
                +"\t\t\t\t"+t.getFinalBalance()
            );
        }
        System.out.println("-------------------------------------------------------------");
    }

    public User getUser(String userId){
        List<User> result = users.stream().filter(user -> user.getUserName().equals(userId)).collect(Collectors.toList());
        if(!result.isEmpty()){
            return result.getFirst();
        }
        return null;
    }

    public boolean transferAmount(String userId, String payeeUserId, Double amount){
        boolean isDebit = debit(userId, amount, payeeUserId);
        boolean isCredit = credit(payeeUserId, amount, userId);

        return isDebit && isCredit;
    }

    public void raiseChequeBookRequest(String userId){
        chequeBookRequest.put(userId, false);
    }

    public Map<String, Boolean> getAllCheckBookRequest(){
        return chequeBookRequest;
    }

    public List<String> getUserIdForCheckBookRequest(){
        List<String> userIds = new ArrayList<>();

        for(Map.Entry<String, Boolean> entry : chequeBookRequest.entrySet()){
            if(!entry.getValue()){
                userIds.add(entry.getKey());
            }
        }

        return userIds;
    }

    public void approveCheckBookRequest(String userId){
        if(chequeBookRequest.containsKey(userId)){
            chequeBookRequest.put(userId, true);
        }
    }

    private boolean debit(String userId, Double amount, String payeeUserId){
        User user = getUser(userId);

        Double accountBalance = user.getCurrentBalance();
        users.remove(user);

        Double finalBalance = accountBalance - amount;
        user.setCurrentBalance(finalBalance);

        Transaction transaction = new Transaction(
                LocalDate.now(),
                payeeUserId,
                amount,
                "debit",
                accountBalance,
                finalBalance,
                userId
        );
        System.out.println(transaction);
        transactions.add(transaction);
        return users.add(user);
    }

    private boolean credit(String payeeUserId, Double amount, String userId){
        User user = getUser(payeeUserId);

        Double accountBalance = user.getCurrentBalance();
        users.remove(user);

        Double finalBalance = accountBalance + amount;
        user.setCurrentBalance(finalBalance);

        Transaction transaction = new Transaction(
                LocalDate.now(),
                userId,
                amount,
                "credit"    ,
                accountBalance,
                finalBalance,
                payeeUserId
        );
        System.out.println(transaction);
        transactions.add(transaction);
        return users.add(user);
    }

}
