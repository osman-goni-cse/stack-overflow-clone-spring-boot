package learn.osman.stackoverflowclone.entity;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    private Long tagId;
    private String tagName;
    private String tagDetails;
    private List<Question> questions;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDetails() {
        return tagDetails;
    }

    public void setTagDetails(String tagDetails) {
        this.tagDetails = tagDetails;
    }
    public List<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Tag(Long tagId, String tagName, String tagDetails) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.tagDetails = tagDetails;
        this.questions = new ArrayList<>();
    }

}
