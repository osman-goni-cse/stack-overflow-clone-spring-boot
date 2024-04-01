package learn.osman.stackoverflowclone.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import learn.osman.stackoverflowclone.entity.User;
import learn.osman.stackoverflowclone.repository.UserRepositoryCustom;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> searchUsersByNameOrEmail(String keyword) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = criteriaQuery.from(User.class);

        Predicate displayNamePredicate = criteriaBuilder
                .equal(criteriaBuilder.upper(userRoot.get("displayName")), keyword.toUpperCase());

        Predicate emailAddressPredicate = criteriaBuilder.
                like(criteriaBuilder.upper(userRoot.get("emailAddress")), "%" + keyword.toUpperCase() + "%");

        Predicate nameOrEmailPredicate = criteriaBuilder.or(
                displayNamePredicate, emailAddressPredicate
        );

        criteriaQuery.where(nameOrEmailPredicate);

        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public List<User> filterUsersByTag(Long tagId) {
        return entityManager.createNamedQuery(
                "User.filterUsersByTag", User.class)
                .setParameter("tagId", tagId)
                .getResultList();
    }

    @Override
    public Map<Long, Long> countUsersByTag() {
        String nativeQuery = "SELECT t.tag_id, COUNT(DISTINCT u.user_id) "+
                "FROM user_entity u " +
                "INNER JOIN question q ON u.user_id = q.user_entity_user_id " +
                "INNER JOIN questions_tags qt ON q.question_id = qt.question_id " +
                "INNER JOIN tag t ON qt.tag_id = t.tag_id " +
                "GROUP BY t.tag_id";

        List<Object[]> results = entityManager.createNativeQuery(nativeQuery).getResultList();

        Map<Long, Long> userCountByTag = new HashMap<>();
        for (Object[] result: results) {
            Long tag_id = (Long) result[0];
            Long count = (Long) result[1];
            userCountByTag.put(tag_id, count);
        }

        return userCountByTag;
    }
}
