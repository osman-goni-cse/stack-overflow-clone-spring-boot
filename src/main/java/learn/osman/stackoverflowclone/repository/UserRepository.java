package learn.osman.stackoverflowclone.repository;

import learn.osman.stackoverflowclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAddress(String emailAddress);
}
