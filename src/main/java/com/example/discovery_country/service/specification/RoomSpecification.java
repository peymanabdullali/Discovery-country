package com.example.discovery_country.service.specification;

import com.example.discovery_country.dao.entity.RoomEntity;
import com.example.discovery_country.model.dto.criteria.RoomCriteriaRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

import jakarta.persistence.criteria.Predicate;

import java.util.List;

public class RoomSpecification implements Specification<RoomEntity> {

    public static Specification<RoomEntity> getRoomByCriteria(RoomCriteriaRequest roomCriteriaRequest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (roomCriteriaRequest.getName() != null && !roomCriteriaRequest.getName().isEmpty()) {
                Predicate namePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(
                                criteriaBuilder.function(
                                        "jsonb_extract_path_text",
                                        String.class,
                                        root.get("name"),
                                        criteriaBuilder.literal(roomCriteriaRequest.getKey().name())
                                )
                        ),
                        "%" + roomCriteriaRequest.getName().toLowerCase() + "%"
                );

                predicates.add(namePredicate);
            }

            if (roomCriteriaRequest.getRoomCount() != 0) {
                predicates.add(criteriaBuilder.equal(root.get("roomCount"), roomCriteriaRequest.getRoomCount()));
            }
            if (roomCriteriaRequest.getRoomType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("roomType"), roomCriteriaRequest.getRoomType()));
            }

            if (roomCriteriaRequest.getMinPrice() != null && roomCriteriaRequest.getMaxPrice() != null) {
                predicates.add(criteriaBuilder.between(root.get("price"),
                        roomCriteriaRequest.getMinPrice(), roomCriteriaRequest.getMaxPrice()));
            }
            predicates.add(criteriaBuilder.isTrue(root.get("available")));
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public jakarta.persistence.criteria.Predicate toPredicate(Root<RoomEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
