package com.example.discovery_country.service.specification;

import com.example.discovery_country.dao.entity.RoomReservationEntity;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.model.dto.criteria.RoomReservationCriteriaRequest;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class RoomReservationSpecification {

    public static Specification<RoomReservationEntity> getRoomReservationByCriteria(RoomReservationCriteriaRequest criteria) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getName() != null && !criteria.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.function("jsonb_extract_path_text", String.class, root.get("name"),
                                criteriaBuilder.literal(criteria.getLangType().name())),
                        "%" + criteria.getName() + "%"
                ));
            }

            if (criteria.getEntryDate() != null && criteria.getExitDate() != null) {
                predicates.add(criteriaBuilder.between(root.get("entryDate"), criteria.getEntryDate(), criteria.getExitDate()));
            }

            predicates.add(criteriaBuilder.equal(root.get("status"), criteria.isStatus()));

            predicates.add(criteriaBuilder.equal(root.get("deleted"), criteria.isDeleted()));



            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
