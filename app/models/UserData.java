package models;

import io.ebean.Finder;
import io.ebean.Model;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserData extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "username")
    public String username;

    @Column(name = "password")
    public String password;

    public static final Finder<Long, UserData> find = new Finder<>(UserData.class);

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
