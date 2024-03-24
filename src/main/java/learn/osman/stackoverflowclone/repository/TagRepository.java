package learn.osman.stackoverflowclone.repository;

import learn.osman.stackoverflowclone.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
