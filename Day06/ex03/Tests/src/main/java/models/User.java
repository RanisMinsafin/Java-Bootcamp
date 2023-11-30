package models;

import java.util.Objects;

public class User {
    private final Long id;
    private final String login;
    private final String password;
    private boolean authenticationStatus;

    public User(Long id, String login, String password, boolean authenticationStatus) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authenticationStatus = authenticationStatus;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(boolean authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return authenticationStatus == user.authenticationStatus && Objects.equals(id, user.id) && Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, authenticationStatus);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", authenticationStatus=" + authenticationStatus +
                '}';
    }
}
