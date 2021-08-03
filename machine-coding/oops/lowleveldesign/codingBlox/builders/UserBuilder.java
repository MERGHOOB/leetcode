package lowleveldesign.codingBlox.builders;

import lowleveldesign.codingBlox.entities.User;

public class UserBuilder {


    User user;

    public UserBuilder(User user) {
        this.user = user;
    }

    public UserBuilder userName(String username) {
        user.setUserName(username);
        return this;
    }

    public User build() {
        return user;
    }
}
