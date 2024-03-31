package learn.osman.stackoverflowclone.repository;

import learn.osman.stackoverflowclone.entity.User;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> searchUsersByNameOrEmail(String keyword);
}
