package com.example.discovery_country.service.specification;

import com.example.discovery_country.dao.entity.RestaurantEntity;
import com.example.discovery_country.dao.entity.ScenicSpotEntity;
import com.example.discovery_country.enums.LangType;
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

            if(criteria.getName() != null && !criteria.getName().isEmpty()) {
                LangType langType = criteria.getKey();
                Predicate namePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(
                                criteriaBuilder.function(
                                        "jsonb_extract_path_text",
                                        String.class,
                                        root.get("name"),
                                        criteriaBuilder.literal(langType.name())
                                )
                        ),
                        "%" + criteria.getName().toLowerCase() + "%"
                );

                predicate = criteriaBuilder.and(predicate, namePredicate);
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
