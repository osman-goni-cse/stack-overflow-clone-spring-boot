package learn.osman.stackoverflowclone.controller;

import learn.osman.stackoverflowclone.entity.User;
import learn.osman.stackoverflowclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-all-user")
    public String allUsers(Model model){
        Map<Long, User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "user-list";
    }
    @GetMapping("/create-new-user")
    public String showUserForm(@ModelAttribute("userObj") User user) {
        return "register-user-form";
    }
    @PostMapping("/create-new-user")
    public String createNewUser(@ModelAttribute("userObj") User user, Model model) {
//        Check user is null or not
//        add user
        userService.registerUser(user);
        return "user-list";
    }
}
