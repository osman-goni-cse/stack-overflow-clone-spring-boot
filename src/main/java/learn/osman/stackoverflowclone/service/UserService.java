package learn.osman.stackoverflowclone.service;

import learn.osman.stackoverflowclone.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private List<User> userList = new ArrayList<>(Arrays.asList(
            new User(1L, "Osman", "osman@gmail.com", "123456", "Software Engineer", "Dhaka"),
            new User(2L, "Rafi", "rafi@gmail.com", "221133", "Lecturer", "Dhaka"),
            new User(3L, "Toufique", "tfq@gmail.com", "tourfida", "Software Engineer", "Dhaka"),
            new User(4L, "Sazzad", "sazzad@gmail.com", "sazzad", "business", "Dubai")
        ));

    public List<User> getAllUserList() {
        return userList;
    }
}
