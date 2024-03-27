package learn.osman.stackoverflowclone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
