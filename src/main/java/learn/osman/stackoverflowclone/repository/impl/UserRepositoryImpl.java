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

import java.util.List;

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
}
