package user;

import java.util.Objects;

public class User {
    String userName;
    String password;
    String emailId;

    public User(String userName, String password, String emailId) {
        this.userName = userName;
        this.password = password;
        this.emailId = emailId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(userName, user.userName) && Objects.equals(password, user.password) && Objects.equals(emailId, user.emailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, emailId);
    }
}
