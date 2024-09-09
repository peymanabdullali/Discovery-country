package com.example.discovery_country.service.specification;

import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.model.dto.criteria.ActivityCriteriaRequest;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ActivitySpecification implements Specification<ActivityEntity> {

    public static Specification<ActivityEntity> getActivityByCriteria(ActivityCriteriaRequest activityCriteriaRequest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (activityCriteriaRequest.getName() != null && !activityCriteriaRequest.getName().isEmpty()) {
                Predicate namePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(
                                criteriaBuilder.function(
                                        "jsonb_extract_path_text",
                                        String.class,
                                        root.get("name"),
                                        criteriaBuilder.literal(activityCriteriaRequest.getKey().name())
                                )
                        ),
                        "%" + activityCriteriaRequest.getName().toLowerCase() + "%"
                );

                predicates.add(namePredicate);
            }

            if (activityCriteriaRequest.getStartDate() != null && activityCriteriaRequest.getEndDate() != null) {
                predicates.add(criteriaBuilder.between(
                        root.get("startDate"),
                        activityCriteriaRequest.getStartDate().atStartOfDay(),
                        activityCriteriaRequest.getEndDate().atTime(23, 59, 59)
                ));
            } else {
                if (activityCriteriaRequest.getStartDate() != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                            root.get("startDate"),
                            activityCriteriaRequest.getStartDate().atStartOfDay()
                    ));
                }

                if (activityCriteriaRequest.getEndDate() != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(
                            root.get("endDate"),
                            activityCriteriaRequest.getEndDate().atTime(23, 59, 59)
                    ));
                }
            }

            // Fiyat aralığı filtreleme
            if (activityCriteriaRequest.getPriceGreaterThan() != 0 && activityCriteriaRequest.getPriceLessThan() != 0) {
                predicates.add(criteriaBuilder.between(root.get("price"),
                        activityCriteriaRequest.getPriceGreaterThan(), activityCriteriaRequest.getPriceLessThan()));
            }

            // Deleted false olanları filtreleme
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));

            // İmage join işlemi ve deleted=false olan ilk resmi almak
            Join<ActivityEntity, ImageEntity> imagesJoin = root.join("images", JoinType.LEFT);
            predicates.add(criteriaBuilder.isFalse(imagesJoin.get("deleted")));
            query.orderBy(criteriaBuilder.asc(imagesJoin.get("id")));
            query.groupBy(root.get("id"));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }



    @Override
    public Predicate toPredicate(Root<ActivityEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
//qeyd