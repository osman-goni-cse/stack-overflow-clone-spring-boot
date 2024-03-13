package learn.osman.stackoverflowclone.service;

import learn.osman.stackoverflowclone.entity.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
//    private List<User> userList = new ArrayList<>(Arrays.asList(
//            new User(1L, "Osman", "osman@gmail.com", "123456"),
//            new User(2L, "Rafi", "rafi@gmail.com", "221133"),
//            new User(3L, "Toufique", "tfq@gmail.com", "tourfida"),
//            new User(4L, "Sazzad", "sazzad@gmail.com", "sazzad")
//        ));

    private Map<Long, User> users;

    public UserService() {
        users = new HashMap<>();
        addDummyData();
    }

    private void addDummyData() {
        User user1 = new User(1L, "Osman", "osman@gmail.com", "123456");
//        User user2 = new User(2L, "Farman", "farman@gmail.com", "123456");
        users.put(user1.getUserId(), user1);
//        users.put(user2.getUserId(), user2);
    }

    public Map<Long, User> getAllUser() {
        return users;
    }

    public User getUser(Long userId) {
        return users.get(userId);
    }

    public void registerUser(User user) {
        users.put(user.getUserId(), user);
    }

    public boolean isUserAuthenticate(User user) {
        User user1 = users.get(user.getUserId());
        return user1 != null && user1.getPassword().equals(user.getPassword());
    }
}
