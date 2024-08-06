package com.example.discovery_country.service.specification;

import com.example.discovery_country.dao.entity.RestaurantEntity;
import com.example.discovery_country.dao.entity.ScenicSpotEntity;
import com.example.discovery_country.model.dto.criteria.CriteriaRequestForName;
import com.example.discovery_country.model.dto.criteria.RestaurantCriteriaRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RestaurantSpecification implements Specification<RestaurantEntity> {

    public static Specification<RestaurantEntity> getRestaurants(RestaurantCriteriaRequest criteria) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (StringUtils.hasText(criteria.getName())) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(root.get("name"), "%" + criteria.getName() + "%"));
            }

            if (criteria.getRating() > 0) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), criteria.getRating()));
            }

            predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.isFalse(root.get("status")));

            return predicate;
        };
    }


    @Override
    public Predicate toPredicate(Root<RestaurantEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
