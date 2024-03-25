package learn.osman.stackoverflowclone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag {
    @Id
    @GeneratedValue
    private Long tagId;

    @Column(unique = true, nullable = false, length = 64)
    @NotBlank(message = "Tag name is required")
    private String tagName;

    @Column(nullable = false)
    @NotBlank(message = "Tag Details is required")
    private String tagDetails;

    @ManyToMany
    @JoinTable(
            name = "TAGS_QUESTIONS",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions;

    public Tag() {

    }
    public Tag(String tagName, String tagDetails) {
        this.tagName = tagName;
        this.tagDetails = tagDetails;
        this.questions = new ArrayList<>();
    }
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

    @Override
    public String toString() {
        return tagName;
    }
}
