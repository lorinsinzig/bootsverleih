package models;

import play.data.validation.Constraints;

public class UserData {
    @Constraints.Required(message = "Username is required")
    public String username;

    @Constraints.Required(message = "Password is required")
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
