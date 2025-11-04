package repository;
import entity.Admin;
import entity.Customer;
import entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRepository {
    protected Set<User> users = new HashSet<>();

    public UserRepository() {
        User user1 = new Customer("customer1", "cus1", "nandha");
        User user2 = new Customer("customer2", "cus2", "kumar");
        User user3 = new Admin("admin1", "admin1", "pranesh");
        User user4 = new Admin("admin2", "admin2", "prasanna");
//        User user5 = new Customer("customer1", "cus1", "Nandha Kumar", 0);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
//        users.add(user5);
    }

    public User login(String userName, String password){
        List<User> finalList = users.stream().filter((user) ->
                                                                user.getUserName().equals(userName) &&
                                                                user.getPassword().equals(password)).toList();

        if(finalList.isEmpty()){
            return null;
        }
        return finalList.getFirst();
    }


}
