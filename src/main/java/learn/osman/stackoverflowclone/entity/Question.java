package learn.osman.stackoverflowclone.entity;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private Long questionId;
    private String questionTitle;
    private String questionDetails;
    private User user;
    private List<Tag> tagList;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

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


    public Question() {

    }
    public Question(Long questionId, String questionTitle, String questionDetails, User user, List<Tag> tagList) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.questionDetails = questionDetails;
        this.user = user;
        this.tagList = tagList;
//        this.tagList = new ArrayList<>();
    }
    public Question(Long questionId, String questionTitle, String questionDetails, User user) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.questionDetails = questionDetails;
        this.user = user;
        this.tagList = new ArrayList<>();
    }

    public void addTagToTheQuestions(Question question, List<Tag> tagList) {
        for (Tag tag: tagList) {
            question.tagList.add(tag);
        }
    }

    public void removeTag(Question question, Tag tag) {
        question.tagList.remove(tag);
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionDetails='" + questionDetails + '\'' +
                ", user=" + user +
                ", tagList=" + tagList +
                '}';
    }


}
