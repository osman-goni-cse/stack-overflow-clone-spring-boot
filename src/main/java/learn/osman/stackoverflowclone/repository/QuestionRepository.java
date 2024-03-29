package learn.osman.stackoverflowclone.repository;

import learn.osman.stackoverflowclone.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q WHERE q.userEntity.userId = :userId")
    List<Question> questionAskedByUser(@Param("userId") Long userId);

    @Query("SELECT COUNT(q) FROM Question q JOIN q.tagList t WHERE t.tagId = :tagId")
    Integer countQuestionBasedOnTag(@Param("tagId") Long tagId);

    @Query("SELECT q FROM Question q JOIN q.tagList t WHERE t.tagId = :tagId")
    List<Question> questionWithSpecificTag(@Param("tagId") Long tagId);
}
