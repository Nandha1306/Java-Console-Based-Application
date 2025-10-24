package service;

import entity.User;
import repository.UserRepository;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public void printUsers(){
        userRepository.printUsers();
    }


    public User login(String userName, String password){
        return userRepository.login(userName, password);
    }

    public boolean addUser(String userName, String password, String contactNo){
        return userRepository.addUser(userName, password, contactNo);
    }

    public Double checkAccBalance(String userId){
        return userRepository.checkAccBalance(userId);
    }
}
