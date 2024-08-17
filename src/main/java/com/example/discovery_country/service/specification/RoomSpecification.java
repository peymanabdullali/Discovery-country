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
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                        "%" + roomCriteriaRequest.getName().toLowerCase() + "%"));
            }

            if (roomCriteriaRequest.getRoomCount() != null) {
                predicates.add(criteriaBuilder.equal(root.get("roomCount"), roomCriteriaRequest.getRoomCount()));
            }
            if (roomCriteriaRequest.getRoomType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("type"), roomCriteriaRequest.getRoomType()));
            }

            if (roomCriteriaRequest.getMinPrice() != null && roomCriteriaRequest.getMaxPrice() != null) {
                predicates.add(criteriaBuilder.between(root.get("price"),
                        roomCriteriaRequest.getMinPrice(), roomCriteriaRequest.getMaxPrice()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public jakarta.persistence.criteria.Predicate toPredicate(Root<RoomEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
