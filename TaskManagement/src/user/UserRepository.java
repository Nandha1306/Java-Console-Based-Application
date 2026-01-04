package user;

import exception.UserNotFoundException;

import java.util.HashSet;

public class UserRepository {
    HashSet<User> users = new HashSet<>();

    public UserRepository() {
        User user1 = new User("Nandha", "1234", "nandha@gmail.com");
        User user2 = new User("Pranesh", "0987", "pranesh@gmail.com");

        users.add(user1);
        users.add(user2);
    }

    public User signIn(String userName, String email, String password){
        User user = new User(userName, email, password);
        users.add(user);
        System.out.println("Successfully singed in.");
        return user;
    }

    public User login(String emailId, String password) throws UserNotFoundException {
        return users.stream()
                .filter((user) -> user.emailId.equals(emailId)
                        && user.password.equals(password))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Invalid email or password"));
    }
}
