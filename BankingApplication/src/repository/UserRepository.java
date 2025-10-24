package repository;
import entity.Transaction;
import entity.User;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class UserRepository {
    protected static Set<User> users = new HashSet<>();

    static {
        User user1 = new User("admin", "admin", "123456789", "admin", 0.0);
        User user2 = new User("user2", "user2", "123456789", "user", 10000.0);
        User user3 = new User("user3", "user3", "123456789", "user", 1000.0);
        User user4 = new User("user4", "user4", "123456789", "user", 40000.0);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
    }

    public void printUsers(){
        for(User user : users){
            System.out.println(user);
        }
    }

    public Double checkAccBalance(String userId){
        List<User> result = users.stream().filter(user -> user.getUserName().equals(userId)).toList();

        if(!result.isEmpty()){
            return result.getFirst().getCurrentBalance();
        } else {
            return null;
        }
    }

    public boolean addUser(String userName, String password, String contactNo){
        User user = new User(userName, password, contactNo, "user", 500.0);
        return users.add(user);
    }

    public User login(String userName, String password){
         List<User> finalList = users.stream()
                                            .filter(user ->
                                                    user.getUserName().equals(userName) &&
                                                    user.getPassword().equals(password)).toList();

         if(!finalList.isEmpty()){
             return finalList.getFirst();
         } else {
             return null;
         }
    }
}
