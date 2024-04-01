package learn.osman.stackoverflowclone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UserEntity") // user is a reserved keyword in PostgreSQL
@NamedNativeQuery(
        name = "User.filterUsersByTag",
        query = "SELECT DISTINCT u.* "+
                "FROM user_entity u " +
                "INNER JOIN question q ON u.user_id = q.user_entity_user_id " +
                "INNER JOIN questions_tags qt ON q.question_id = qt.question_id " +
                "INNER JOIN tag t ON qt.tag_id = t.tag_id " +
                "WHERE t.tag_id =:tagId",
        resultClass = User.class
)
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
    private List<Question> questions = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", displayName='" + displayName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
