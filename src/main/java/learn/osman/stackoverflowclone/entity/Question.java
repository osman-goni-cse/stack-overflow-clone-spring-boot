package learn.osman.stackoverflowclone.entity;

import java.util.List;

public class Question {
    private String questionTitle;

    private String questionDetails;
    private User user;
    private List<Tag> tagList;

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionDetails() {
        return questionDetails;
    }

    public void setQuestionDetails(String questionDetails) {
        this.questionDetails = questionDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public Question(String questionTitle, String questionDetails, User user, List<Tag> tagList) {
        this.questionTitle = questionTitle;
        this.questionDetails = questionDetails;
        this.user = user;
        this.tagList = tagList;
    }

}
