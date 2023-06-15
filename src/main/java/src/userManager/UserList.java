package src.userManager;

import java.util.HashMap;
import java.util.Map;

public class UserList {
    private Map<String, User> users;

    public UserList() {
        users = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getName(), user);
    }

    public void removeUser(String username) {
        users.remove(username);
    }

    public User getUser(String username) {
        return users.get(username);
    }
}

