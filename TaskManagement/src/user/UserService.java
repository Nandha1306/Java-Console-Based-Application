package user;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signIn(String userName, String email, String password){
        return userRepository.signIn(userName, email, password);
    }

    public User login(String emailId, String password){
        return userRepository.login(emailId, password);
    }
}
