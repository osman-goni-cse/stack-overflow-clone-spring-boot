package learn.osman.stackoverflowclone.specifications;

import learn.osman.stackoverflowclone.entity.User;
import learn.osman.stackoverflowclone.entity.User_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecification {
    public static Specification<User> hasDisplayName(String displayName) {
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(criteriaBuilder.upper(root.get(User_.DISPLAY_NAME)), displayName.toUpperCase());
        });
    }

    public static Specification<User> hasEmailAddress(String email) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.like(criteriaBuilder.upper(root.get(User_.EMAIL_ADDRESS)), "%" + email.toUpperCase()+ "%");
        };
    }
}
