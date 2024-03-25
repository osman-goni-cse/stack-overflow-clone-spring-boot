package learn.osman.stackoverflowclone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue
    private Long questionId;

    @NotBlank(message = "Question title is required")
    private String questionTitle;

    @NotBlank(message = "Question details is required")
    private String questionDetails;

    @ManyToOne
    private User userEntity;

    @ManyToMany
    @JoinTable(
            name = "QUESTIONS_TAGS",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tagList;

    public Question() {

    }
    public Question(String questionTitle, String questionDetails, User userEntity, List<Tag> tagList) {
        this.questionTitle = questionTitle;
        this.questionDetails = questionDetails;
        this.userEntity = userEntity;
        this.tagList = tagList;
    }

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
        return userEntity;
    }

    public void setUser(User userEntity) {
        this.userEntity = userEntity;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
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
                ", user=" + userEntity +
                ", tagList=" + tagList +
                '}';
    }


}
