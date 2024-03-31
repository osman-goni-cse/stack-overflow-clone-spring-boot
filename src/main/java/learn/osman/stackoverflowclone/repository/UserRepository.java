package learn.osman.stackoverflowclone.repository;

import learn.osman.stackoverflowclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//
//    @Query("SELECT u FROM UserEntity u where lower(u.emailAddress) = lower(:email)")
//    User findByEmailAddress(@Param("email") String emailAddress);
//
//}

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    @Query("SELECT u FROM UserEntity u where lower(u.emailAddress) = lower(:email)")
    User findByEmailAddress(@Param("email") String emailAddress);

}
