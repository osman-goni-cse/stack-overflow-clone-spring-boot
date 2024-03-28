package learn.osman.stackoverflowclone.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import jakarta.validation.Valid;
import learn.osman.stackoverflowclone.entity.Question;
import learn.osman.stackoverflowclone.entity.User;
import learn.osman.stackoverflowclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-all-user")
    public String allUsers(Model model) {
        Map<Long, User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/create-new-user")
    public String showUserForm(@ModelAttribute("userObj") User user) {
        return "register-user-form";
    }

    @PostMapping("/create-new-user")
    public String createNewUser(@ModelAttribute("userObj") @Validated User userObj, BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("profile");

        if (bindingResult.hasErrors() || file == null || file.isEmpty()) {
            if (file == null || file.isEmpty()) {
                model.addAttribute("profilePictureError", "Profile Picture is required");
            }
            return "register-user-form";
        }

        userObj.setUserProfilePictureName(file.getOriginalFilename());

        File imagesFolder = new File(new ClassPathResource(".").getFile().getPath() + "/static/images");
        if (!imagesFolder.exists()) {
            imagesFolder.mkdirs();
        }
        Path path = Paths.get(imagesFolder.getAbsolutePath() + File.separator + file.getOriginalFilename());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        userService.registerUser(userObj);

        return "redirect:/users/get-all-user";
    }

    @GetMapping("/login")
    public String showLoginForm(@ModelAttribute("userObj") User user, Model model, RedirectAttributes redirectAttributes) {
        String userIsNotLoggedIn = (String) redirectAttributes.getFlashAttributes().get("userIsNotLoggedIn");
        model.addAttribute("userIsNotLoggedIn", userIsNotLoggedIn);
        return "login-form";
    }

    @PostMapping("/login")
    public String userLogin(@ModelAttribute("userObj") User user, Model model, HttpSession session) {
        boolean isUserBlank = false;
        if (user.getEmailAddress().trim().isEmpty()) {
            isUserBlank = true;
            model.addAttribute("emailAddressError", "Email is required");
        }
        if (user.getPassword().trim().isEmpty()) {
            isUserBlank = true;
            model.addAttribute("passwordError", "Password is required");
        }

        if (isUserBlank) {
            return "login-form";
        }
        if (userService.isUserAuthenticate(user)) {
            User loggedInUser = userService.getUserByEmail(user.getEmailAddress());
            model.addAttribute("loggedInUser", loggedInUser);
            session.setAttribute("loggedInUser", loggedInUser);
            return "redirect:/";
        } else {
            model.addAttribute("validationError", "Your Email/Password is incorrect");
            return "login-form";
        }
    }

    @GetMapping("/logout")
    public String userLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/users/login";
    }

    @GetMapping("/search")
    public String searchUsersByNameOrEmail(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
//        Map<Long, User> users = userService.getAllUser();
        Map<Long, User> users = new HashMap<>();
//
        if (keyword != null && !keyword.isEmpty()) {
//            users = users.entrySet().stream()
//                    .filter(entry -> entry.getValue().getDisplayName().contains(keyword) || entry.getValue().getEmailAddress().contains(keyword))
//                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            User foundUser = userService.getUserByEmail(keyword);

            if (foundUser != null) {
                users.put(foundUser.getUserId(), foundUser);
            }

        }
        model.addAttribute("users", users);
        return "user-list";
    }

}
