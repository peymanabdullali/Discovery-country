package com.example.discovery_country.service.specification;

import com.example.discovery_country.dao.entity.*;
import com.example.discovery_country.model.dto.criteria.CriteriaRequestForName;
import com.example.discovery_country.model.dto.criteria.HomeHotelCriteriaRequest;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeHotelSpecification implements Specification<HomeHotelEntity> {


public static Specification<HomeHotelEntity> homeByCriteria(HomeHotelCriteriaRequest criteria) {
    return (root, query, criteriaBuilder) -> {
        Predicate predicate = criteriaBuilder.conjunction();

        if(criteria.getName() != null && !criteria.getName().isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + criteria.getName().toLowerCase() + "%"
            ));
        }

        if (criteria.getType() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("type"), criteria.getType()));
        }

        predicate = criteriaBuilder.and(predicate, criteriaBuilder.isFalse(root.get("deleted")));

        Subquery<Long> subquery = query.subquery(Long.class);
        Root<ImageEntity> imageRoot = subquery.from(ImageEntity.class);
        subquery.select(criteriaBuilder.min(imageRoot.get("id")))
                .where(
                        criteriaBuilder.equal(imageRoot.get("homeHotel"), root),
                        criteriaBuilder.isFalse(imageRoot.get("deleted"))
                );

        query.multiselect(
                root.get("id"),
                root.get("name"),
                root.get("address"),
                root.get("viewed"),
                root.get("rooms").get("price"),
                subquery.as(Long.class)
        );

        return predicate;
    };
}

    public static Specification<HomeHotelEntity> findByIdWithFilters(Long id) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);

            Join<HomeHotelEntity, ImageEntity> imageJoin = root.join("images", JoinType.LEFT);
            imageJoin.on(
                    criteriaBuilder.equal(imageJoin.get("deleted"), false)
            );

            Join<HomeHotelEntity, ReviewEntity> reviewJoin = root.join("reviews", JoinType.LEFT);
            reviewJoin.on(
                    criteriaBuilder.equal(reviewJoin.get("status"), false)
            );



            query.where(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(root.get("id"), id),
                            criteriaBuilder.equal(root.get("deleted"), false)
                    )
            );

            return query.getRestriction();
        };
    }


    @Override
    public Predicate toPredicate(Root<HomeHotelEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
