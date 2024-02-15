package learn.osman.stackoverflowclone.controller;

import learn.osman.stackoverflowclone.entity.User;
import learn.osman.stackoverflowclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        List<User> users = userService.getAllUserList();
        model.addAttribute("users", users);
        return "user-list";
    }
    @GetMapping("/create-new-user")
    public String createNewUser(@ModelAttribute("userObj") User user) {
        return "register-user-form";
    }
}
