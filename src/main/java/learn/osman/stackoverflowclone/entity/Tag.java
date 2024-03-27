package learn.osman.stackoverflowclone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @ManyToMany(mappedBy = "tagList")
    private List<Question> questions;

    @Override
    public String toString() {
        return tagName;
    }
}
