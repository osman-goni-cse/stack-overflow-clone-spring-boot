package learn.osman.stackoverflowclone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity(name = "UserEntity") // user is a reserved keyword in PostgreSQL
public class User {
    @Id
    @GeneratedValue
    private Long userId;
    @NotBlank(message = "Display name is required")
    private String displayName;
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email address is required")
    private String emailAddress;
    @NotBlank(message = "Password is required")
    private String password;
    private String userProfilePictureName;

    @OneToMany(mappedBy = "userEntity")
    private List<Question> questions;
    public User() {

    }
    public User(String displayName, String emailAddress, String password, String userProfilePictureName) {
        this.displayName = displayName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.userProfilePictureName = userProfilePictureName;
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

    public String getUserProfilePictureName()  {
        return userProfilePictureName;
    }
    public void setUserProfilePictureName(String userProfilePictureName) {
        this.userProfilePictureName = userProfilePictureName;
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
