package learn.osman.stackoverflowclone.controller;

import jakarta.servlet.http.HttpSession;
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
        boolean isUserBlank = false;
        if (user.getUserId() == null) {
            isUserBlank = true;
            model.addAttribute("userIdError", "User ID is required");
        }
        if (user.getDisplayName().trim().isEmpty()) {
            isUserBlank = true;
            model.addAttribute("displayNameError", "Display Name is required");
        }
        if (user.getEmailAddress().trim().isEmpty()) {
            isUserBlank = true;
            model.addAttribute("emailAddressError", "Email ID is required");
        }
        if (user.getPassword().trim().isEmpty()) {
            isUserBlank = true;
            model.addAttribute("passwordError", "Password is required");
        }

        if (isUserBlank) {
            return "register-user-form";
        }

        userService.registerUser(user);
        Map<Long, User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/login")
    public String showLoginForm(@ModelAttribute("userObj") User user) {
        return "login-form";
    }

    @PostMapping("/login")
    public String userLogin(@ModelAttribute("userObj") User user, Model model, HttpSession session) {
        boolean isUserBlank = false;
        if (user.getUserId() == null) {
            isUserBlank = true;
            model.addAttribute("userIdError", "User Id is required");
        }
        if (user.getPassword().trim().isEmpty()) {
            isUserBlank = true;
            model.addAttribute("passwordError", "Password is required");
        }

        if (isUserBlank) {
            return "login-form";
        }
        if (userService.isUserAuthenticate(user)) {
            User loggedInUser = userService.getUser(user.getUserId());
            model.addAttribute("loggedInUser", loggedInUser);
            session.setAttribute("loggedInUser", loggedInUser);
            return "redirect:/";
        }
        else {
            model.addAttribute("validationError", "Your ID/Password is incorrect");
            return "login-form";
        }
    }
}
