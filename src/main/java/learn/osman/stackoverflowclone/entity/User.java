package learn.osman.stackoverflowclone.entity;


import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

@Entity(name = "UserEntity") // user is a reserved keyword in PostgreSQL
public class User {
    @Id
    @GeneratedValue
    private Long userId;
    private String displayName;
    private String emailAddress;
    private String password;

//    private MultipartFile userProfilePicture;
    @Lob
    private byte[] userProfilePicture;

    @OneToMany(mappedBy = "userEntity")
    private List<Question> questions;
    public User() {

    }
    public User(Long userId, String displayName, String emailAddress, String password, byte[] userProfilePicture) {
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

    public byte[] getUserProfilePicture()  {
        return userProfilePicture;
    }
    public void setUserProfilePicture(byte[] userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", displayName='" + displayName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
