package learn.osman.stackoverflowclone.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import learn.osman.stackoverflowclone.entity.User;
import learn.osman.stackoverflowclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    public String createNewUser(@ModelAttribute("userObj") User user, Model model, HttpServletRequest request) throws IOException {
//        Check user is null or not
//        add user
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        MultipartFile file = multipartRequest.getFile("userProfilePicture");
//
//        // Process the file as needed
//        if (file != null) {
//            // Handle the file
//            user.setUserProfilePicture(file);
//            user.setUserProfilePictureName(file.getOriginalFilename());
//            // Rest of your logic
//        }

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
        if(user.getUserProfilePicture() == null || user.getUserProfilePicture().isEmpty()) {
            isUserBlank = true;
            model.addAttribute("userProfilePictureError", "Profile Picture Required.");
        }else {

//            String resourcesDirectory = "src/main/resources/static/images/";
//            byte[] avatar = user.getUserProfilePicture().getBytes();
//            String profilePictureName = user.getUserProfilePicture().getOriginalFilename();
//            user.setUserProfilePictureName(profilePictureName);
//            Path path = Paths.get(resourcesDirectory + profilePictureName);
//
//            // Write the file to the specified path
//            Files.write(path, avatar);
//            String destinationFileName = "/home/osman/IdeaProjects/stack-overflow-clone/src/main/resources/static/images/" + user.getUserProfilePicture().getOriginalFilename();
//            MultipartFile uploadedImage = user.getUserProfilePicture();
////            try(InputStream inputStream = user.getUserProfilePicture().getInputStream()){
////                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
////            }
//            try{
//                uploadedImage.transferTo(new File(destinationFileName));
//            }catch (IOException ioe) {
//                ioe.printStackTrace();
//            }

             /* This is working perfectly */
            MultipartFile uploadedImage = user.getUserProfilePicture();
            File imagesFolder = new File(new ClassPathResource(".").getFile().getPath() + "/static/images");
            if(!imagesFolder.exists()) {
                imagesFolder.mkdirs();
            }
            Path path = Paths.get(imagesFolder.getAbsolutePath() + File.separator + uploadedImage.getOriginalFilename());
            Files.copy(uploadedImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);


        }

        if (isUserBlank) {
            return "register-user-form";
        }

        userService.registerUser(user);

        return "redirect:/users/get-all-user";
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

    @GetMapping("/logout")
    public String userLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/users/login";
    }
}
