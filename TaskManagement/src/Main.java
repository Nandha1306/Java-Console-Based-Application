import exception.InvalidInputException;
import exception.TaskNotFoundException;
import exception.UserNotFoundException;
import task.Task;
import task.TaskRepository;
import task.TaskService;
import user.User;
import user.UserRepository;
import user.UserService;

import javax.crypto.spec.PSource;
import java.util.*;

public class Main {
    private static int id = 1;
    private static Scanner sc = new Scanner(System.in);

    private static UserRepository userRepository = new UserRepository();
    private static TaskRepository taskRepository = new TaskRepository();

    private static UserService userService = new UserService(userRepository);
    private static TaskService taskService = new TaskService(taskRepository);

    public static void main(String[] args) {
        startApp();
    }

    private static void startApp(){
        boolean flag = true;
        try {
            while (flag) {
                System.out.println("0. SingIn (If you are a new user).");
                System.out.println("1. LogIn (If you already having account).");

                System.out.println("Enter 0 or 1: ");

                int opt = sc.nextInt();

                if (opt == 1) {
                    flag = false;
                    login();
                } else if (opt == 0){
                    flag = false;
                    signin();
                } else {
                    throw new InvalidInputException ("Enter valid option.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Enter Number only!");
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void signin() {
        boolean flag = true;
        try {
            String userName = "", email = "", password = "";
            while (flag) {
                System.out.println("Enter userName: ");
                sc.nextLine();
                userName = sc.nextLine();

                System.out.println("Enter EmailId");
                email = sc.next();

                System.out.println("Enter Password");
                password = sc.next();

                System.out.println("Confirm Password");
                String reEnterdPass = sc.next();

                if (!password.equals(reEnterdPass)) {
                    System.out.println("Both password must be same!!");
                } else {
                    flag = false;
                }
            }

            User user = userService.signIn(userName, email, password);
            HomeMenu(user);
        } catch (InputMismatchException e) {
            System.out.println("Give inputs properly!!");
        }
    }

    private static void login() {
        boolean flag = true;
        try {
           while(flag){
               System.out.println("Enter EmailId: ");
               String email = sc.next();
               System.out.println("Enter Password: ");
               String password = sc.next();

               User user = userService.login(email, password);

               if(user != null){
                   flag = false;
               }
               System.out.println("logged in successfully");
               HomeMenu(user);
           }
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e){
            System.out.println("Give inputs properly");
        }

    }

    private static void HomeMenu(User user) {
        boolean flag = true;

        while (flag) {
            try {
                System.out.println("MENU");

                System.out.println("1. Create Task");
                System.out.println("2. Update Task");
                System.out.println("3. Delete Task");
                System.out.println("4. Display Tasks");

                System.out.println("Enter Option");
                int opt = sc.nextInt();

                switch (opt) {
                    case 1 -> createTask(user);
                    case 2 -> updateTask(user);
                    case 3 -> deleteTask(user);
                    case 4 -> taskService.getAllTasks(user);
                    case 5 -> flag = false;
                    default -> throw new InvalidInputException("Invalid menu option");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter valid input");
                sc.nextLine();
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void createTask(User user) {
        try {
            System.out.println("Enter task");
            sc.nextLine();
            String task = sc.nextLine();

            taskService.createTask(id, user.getUserName(), task, "Pending");
            id++;
        } catch (InputMismatchException e) {
            System.out.println("Please enter valid input");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Exception occurs");
        }
    }

    private static void deleteTask(User user) {
        try {
            taskService.getAllTasks(user);

            System.out.println("Enter the id of the task which you want to delete.");
            int id = sc.nextInt();

            taskService.deleteTask(id);
        }  catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Enter number onlyy!");
        }
    }

    private static void updateTask(User user) {
        try {
            taskService.getAllTasks(user);

            System.out.println("Enter the id of the task which you want to update:");
            int id = sc.nextInt();

            System.out.println("0. Update Task Name");
            System.out.println("1. Update Status");

            int opt = sc.nextInt();

            if (opt == 0) {
                System.out.println("Enter new Task Name:");
                sc.nextLine(); // clear buffer
                String updatedTaskName = sc.nextLine();
                taskService.updateTaskName(id, updatedTaskName);

            } else if (opt == 1) {
                System.out.println("0. Pending");
                System.out.println("1. Completed");

                int statusOpt = sc.nextInt();
                String status = (statusOpt == 0) ? "Pending" : "Completed";

                taskService.updateStatus(id, status);

            } else {
                throw new InvalidInputException("Invalid update option");
            }

            System.out.println("Task updated successfully");

        } catch (InputMismatchException e) {
            System.out.println("Please enter valid input");
            sc.nextLine();
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }
}
