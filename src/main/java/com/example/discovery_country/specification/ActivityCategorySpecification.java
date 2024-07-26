package com.example.discovery_country.specification;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import com.example.discovery_country.model.dto.criteria.ActivityCategoryCriteriaRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ActivityCategorySpecification implements Specification<ActivityCategoryEntity> {

    public static Specification<ActivityCategoryEntity>getActivityCategoryByCriteria(ActivityCategoryCriteriaRequest criteriaRequest){
        return (root, query, criteriaBuilder) ->{
            List<Predicate> predicates=new ArrayList<>();

            if(criteriaRequest.getName()!=null && criteriaRequest.getName().isEmpty()){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%" + criteriaRequest));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("deleted")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public Predicate toPredicate(Root<ActivityCategoryEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
