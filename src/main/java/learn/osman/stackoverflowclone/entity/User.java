package learn.osman.stackoverflowclone.entity;

import java.time.LocalDateTime;

public class User {
    private Long userId;
    private String displayName;
    private String emailAddress;
    private String password;
    private String aboutMe;
    private String location;
    private LocalDateTime createdDate;
    public User(){

    }
    public User(Long userId, String displayName, String emailAddress, String password, String aboutMe, String location, LocalDateTime createdDate) {
        this.userId = userId;
        this.displayName = displayName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.aboutMe = aboutMe;
        this.location = location;
        this.createdDate = createdDate;
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

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
