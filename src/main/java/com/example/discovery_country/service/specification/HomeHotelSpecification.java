package com.example.discovery_country.service.specification;

import com.example.discovery_country.dao.entity.*;
import com.example.discovery_country.model.dto.criteria.CriteriaRequestForName;
import com.example.discovery_country.model.dto.criteria.HomeHotelCriteriaRequest;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class HomeHotelSpecification implements Specification<HomeHotelEntity> {

    public static Specification<HomeHotelEntity> homeByCriteria(HomeHotelCriteriaRequest criteriaRequest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteriaRequest.getName() != null && !criteriaRequest.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + criteriaRequest.getName().toLowerCase() + "%"
                ));
            }
            if (criteriaRequest.getType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("type"), criteriaRequest.getType()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
    public static Specification<HomeHotelEntity> findByIdWithFilters(Long id) {
        return (root, query, criteriaBuilder) -> {
            root.fetch("rooms", JoinType.LEFT);
            root.fetch("images", JoinType.LEFT);
            root.fetch("reviews", JoinType.LEFT);

            // Join for rooms
            Join<HomeHotelEntity, RoomEntity> roomJoin = root.join("rooms", JoinType.LEFT);
            roomJoin.on(
                    criteriaBuilder.equal(roomJoin.get("available"), true),
                    criteriaBuilder.equal(roomJoin.get("deleted"), false)
            );

            // Join for images
            Join<HomeHotelEntity, ImageEntity> imageJoin = root.join("images", JoinType.LEFT);
            imageJoin.on(
                    criteriaBuilder.equal(imageJoin.get("deleted"), false)
            );

            // Join for reviews
            Join<HomeHotelEntity, ReviewEntity> reviewJoin = root.join("reviews", JoinType.LEFT);
            reviewJoin.on(
                    criteriaBuilder.equal(reviewJoin.get("status"), false)
            );

            // Where clause for HomeHotelEntity
            return criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("id"), id),
                    criteriaBuilder.equal(root.get("deleted"), false)
            );
        };
    }
    @Override
    public Predicate toPredicate(Root<HomeHotelEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
