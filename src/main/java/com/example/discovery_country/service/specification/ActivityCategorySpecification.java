package com.example.discovery_country.service.specification;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import com.example.discovery_country.model.dto.criteria.ActivityCategoryCriteriaRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class ActivityCategorySpecification implements Specification<ActivityCategoryEntity> {

    public static Specification<ActivityCategoryEntity> getActivityCategoryByCriteria(ActivityCategoryCriteriaRequest criteriaRequest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteriaRequest.getName() != null && !criteriaRequest.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + criteriaRequest.getName().toLowerCase() + "%"));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));

            log.debug("Predicates: {}", predicates);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public Predicate toPredicate(Root<ActivityCategoryEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}

