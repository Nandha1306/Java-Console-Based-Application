package entity;

import java.util.Objects;

public class User {
    protected String userId;
    protected String password;
    protected String userName;

    public User(String userId, String password, String userName) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof User)) return false;

        User other = (User) obj;
        return this.userName.equals(other.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userName);
    }
}