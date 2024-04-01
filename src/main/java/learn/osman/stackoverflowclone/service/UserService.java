package learn.osman.stackoverflowclone.service;

import learn.osman.stackoverflowclone.entity.User;
import learn.osman.stackoverflowclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static learn.osman.stackoverflowclone.specifications.UserSpecification.hasDisplayName;
import static learn.osman.stackoverflowclone.specifications.UserSpecification.hasEmailAddress;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService() {

    }

    public Map<Long, User> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().collect(Collectors.toMap(User::getUserId, User -> User));
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).get();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmailAddress(email);
    }

    public List<User> getUsersByNameOrEmail(String keyword) {
        return userRepository.searchUsersByNameOrEmail(keyword);
    }

    public List<User> filterUsersByNameOrEmail(String keyword) {
        return userRepository.findAll(Specification.where(hasDisplayName(keyword)).or(hasEmailAddress(keyword)));
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public boolean isUserAuthenticate(User user) {
        User user1 = userRepository.findByEmailAddress(user.getEmailAddress());
        return user1 != null && user1.getPassword().equals(user.getPassword());
    }

    public List<User> filterUsersByTag(Long tagId) {
        return userRepository.filterUsersByTag(tagId);
    }

    public Map<Long, Long> countUsersByTag() {
        return userRepository.countUsersByTag();
    }
}
