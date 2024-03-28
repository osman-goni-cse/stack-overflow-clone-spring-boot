package learn.osman.stackoverflowclone.repository;

import learn.osman.stackoverflowclone.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByTagId(Long tagId);

    @Query("SELECT DISTINCT t FROM Tag t JOIN t.questions q WHERE q.userEntity.userId = :userId")
    List<Tag> tagsUsedByUser(@Param("userId") Long userId);
}
