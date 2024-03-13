package learn.osman.stackoverflowclone.entity;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class User {
    private Long userId;
    private String displayName;
    private String emailAddress;
    private String password;
    private MultipartFile userProfilePicture;
    public User() {

    }
    public User(Long userId, String displayName, String emailAddress, String password, MultipartFile userProfilePicture) {
        this.userId = userId;
        this.displayName = displayName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.userProfilePicture = userProfilePicture;
    }

    public User(Long userId, String displayName, String emailAddress, String password) {
        this.userId = userId;
        this.displayName = displayName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MultipartFile getUserProfilePicture()  {
        return userProfilePicture;
    }
    public void setUserProfilePicture(MultipartFile userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
    }

}
