package com.example.discovery_country.service.specification;

import com.example.discovery_country.dao.entity.ScenicSpotEntity;
import com.example.discovery_country.model.dto.request.ActivityCategoryCriteriaRequest;
import com.example.discovery_country.model.dto.criteria.CriteriaRequestForName;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ScenicSpotSpecification implements Specification<ScenicSpotEntity> {

    public static Specification<ScenicSpotEntity>getScenicSpotByCriteria(CriteriaRequestForName criteriaRequest){
        return (root, query, criteriaBuilder) ->{
            List<Predicate> predicates=new ArrayList<>();

            if(criteriaRequest.getName()!=null && criteriaRequest.getName().isEmpty()){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%" + criteriaRequest));
            }
            predicates.add(criteriaBuilder.isFalse(root.get("status")));
            predicates.add(criteriaBuilder.isTrue(root.get("checkStatus")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public Predicate toPredicate(Root<ScenicSpotEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
