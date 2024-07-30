package com.example.discovery_country.service.specification;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import com.example.discovery_country.dao.entity.ZoneEntity;
import com.example.discovery_country.model.dto.request.CriteriaRequestForName;
import com.example.discovery_country.model.dto.request.ZoneCriteriaRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ZoneSpecification implements Specification<ZoneEntity> {

    public static Specification<ZoneEntity>getZoneByCriteria(CriteriaRequestForName criteriaRequest){
        return (root, query, criteriaBuilder) ->{
            List<Predicate> predicates=new ArrayList<>();

            if(criteriaRequest.getName()!=null && criteriaRequest.getName().isEmpty()){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%" + criteriaRequest));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public Predicate toPredicate(Root<ZoneEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
