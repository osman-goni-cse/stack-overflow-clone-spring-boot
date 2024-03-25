package learn.osman.stackoverflowclone.repository;

import learn.osman.stackoverflowclone.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
