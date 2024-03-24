package learn.osman.stackoverflowclone.repository;

import learn.osman.stackoverflowclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
