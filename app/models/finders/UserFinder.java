package models.finders;

import io.ebean.Finder;
import models.User;

import java.util.List;

public class UserFinder extends Finder<Integer, User> {

    public UserFinder() {
        super(User.class);
    }

    public User getUserByCredentials(String username, String password) {
        return query()
                .where()
                .eq("username", username)
                .eq("password", password)
                .findOne();
    }
}