package learn.osman.stackoverflowclone.repository;

import learn.osman.stackoverflowclone.entity.User;

import java.util.List;
import java.util.Map;

public interface UserRepositoryCustom {

    List<User> searchUsersByNameOrEmail(String keyword);

    List<User> filterUsersByTag(Long tagId);

    Map<Long, Long> countUsersByTag();
}
