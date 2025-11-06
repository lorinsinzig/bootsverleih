package models;

import io.ebean.Model;
import models.finders.UserFinder;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "username")
    public String username;

    @Column(name = "password")
    public String password;

    @Column(name = "admin")
    public String admin;

    public static final UserFinder FINDER = new UserFinder();

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

    public String getAdmin() {
        return this.admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
