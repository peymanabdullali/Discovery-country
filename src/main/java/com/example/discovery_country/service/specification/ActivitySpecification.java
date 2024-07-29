package com.example.discovery_country.service.specification;

import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.model.dto.criteria.ActivityCriteriaRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ActivitySpecification implements Specification<ActivityEntity> {

    public static Specification<ActivityEntity> getActivityByCriteria(ActivityCriteriaRequest activityCriteriaRequest){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates=new ArrayList<>();

            if(activityCriteriaRequest.getName()!=null && activityCriteriaRequest.getName().isEmpty()){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%"+ activityCriteriaRequest));
            }

            if (activityCriteriaRequest.getStartDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("startDate"),
                        activityCriteriaRequest.getStartDate()
                ));
            }

            if (activityCriteriaRequest.getEndDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("endDate"),
                        activityCriteriaRequest.getEndDate()
                ));
            }
            if(activityCriteriaRequest.getPriceGreaterThan()!=0 && activityCriteriaRequest.getPriceLessThan()!=0){
                predicates.add(criteriaBuilder.between(root.get("price"),
                        activityCriteriaRequest.getPriceGreaterThan(), activityCriteriaRequest.getPriceLessThan()));
            }


            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public Predicate toPredicate(Root<ActivityEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
