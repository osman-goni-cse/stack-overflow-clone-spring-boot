package learn.osman.stackoverflowclone.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long questionId;
    private String questionTitle;
    private String questionDetails;
    @ManyToOne
    private User userEntity;
    @ManyToMany(mappedBy = "questions")
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

//    public userEntity getuserEntity() {
//        return userEntity;
//    }
//
//    public void setuserEntity(userEntity userEntity) {
//        this.userEntity = userEntity;
//    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }


    public Question() {

    }
    public Question(Long questionId, String questionTitle, String questionDetails, User userEntity, List<Tag> tagList) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.questionDetails = questionDetails;
        this.userEntity = userEntity;
        this.tagList = tagList;
//        this.tagList = new ArrayList<>();
    }
    public Question(Long questionId, String questionTitle, String questionDetails, User userEntity) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.questionDetails = questionDetails;
        this.userEntity = userEntity;
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
                ", user=" + userEntity +
                ", tagList=" + tagList +
                '}';
    }


}
