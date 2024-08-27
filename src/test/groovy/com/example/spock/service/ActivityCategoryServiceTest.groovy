package com.example.spock.service

import com.example.discovery_country.dao.entity.ActivityCategoryEntity
import com.example.discovery_country.dao.entity.RegionEntity
import com.example.discovery_country.dao.repository.ActivityCategoryRepository
import com.example.discovery_country.dao.repository.RegionRepository
import com.example.discovery_country.mapper.ActivityCategoryMapper
import com.example.discovery_country.model.dto.request.ActivityCategoryRequest
import com.example.discovery_country.service.ActivityCategoryService
import com.example.discovery_country.exception.ActivityCategoryNotFoundException
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import com.example.discovery_country.model.dto.criteria.ActivityCategoryCriteriaRequest
import com.example.discovery_country.service.specification.ActivityCategorySpecification


import org.springframework.data.jpa.domain.Specification
import spock.lang.Specification
import spock.lang.Unroll

class ActivityCategoryServiceTest extends Specification {
    private ActivityCategoryRepository activityCategoryRepository
    private RegionRepository regionRepository
    private ActivityCategoryService activityCategoryService
    private EnhancedRandom random

    def setup() {
        activityCategoryRepository = Mock()
        regionRepository = Mock()
        activityCategoryService = new ActivityCategoryService(activityCategoryRepository, regionRepository)
        random = EnhancedRandomBuilder.aNewEnhancedRandom()
    }

    def "create ActivityCategory"() {
        given: "A valid ActivityCategoryRequest and corresponding entities"
        def request = random.nextObject(ActivityCategoryRequest)
        def regions = random.objects(RegionEntity, request.getRegionIds().size()).toList()
        def savedActivityCategoryEntity = random.nextObject(ActivityCategoryEntity)

        and:
        regionRepository.findAllById(request.getRegionIds()) >> regions
        activityCategoryRepository.save(_ as ActivityCategoryEntity) >> savedActivityCategoryEntity

        when:
        def result = activityCategoryService.create(request)

        then:
        1 * regionRepository.findAllById(request.getRegionIds())
        1 * activityCategoryRepository.save(_ as ActivityCategoryEntity)

        and:
        result == ActivityCategoryMapper.INSTANCE.mapToResponse(savedActivityCategoryEntity)
    }

    def "create should throw IllegalArgumentException when regionIds are null or empty"() {
        given:
        def request = random.nextObject(ActivityCategoryRequest)
        request.setRegionIds(null)

        when:
        activityCategoryService.create(request)

        then:
        thrown(IllegalArgumentException)
    }

    def "create should throw RuntimeException when no regions are found"() {
        given:
        def request = random.nextObject(ActivityCategoryRequest)
        regionRepository.findAllById(request.getRegionIds()) >> []

        when:
        activityCategoryService.create(request)

        then:
        thrown(RuntimeException)
    }

    def "should return mapped activity categories based on criteria"() {
        given:
        def criteriaRequest = new ActivityCategoryCriteriaRequest(/* initialize with necessary values */)
        def pageable = Pageable.unpaged() // or use any specific Pageable object

        def activityCategoryEntities = [/* list of mock ActivityCategoryEntity */]
        def activityCategoryResponses = [/* list of mock ActivityCategoryResponse */]

        def activityCategoryEntityPage = new PageImpl<>(activityCategoryEntities, pageable, activityCategoryEntities.size())

        // Mock the repository to return a non-null page
        activityCategoryRepository.findAll(_ as Specification, pageable) >> activityCategoryEntityPage

        // Mock the mapper to map entities to responses
        ActivityCategoryMapper.INSTANCE.mapToResponse(_) >> { entity ->
            // Mock the mapping logic
            activityCategoryResponses
        }

        when:
        def result = activityCategoryService.getActivityCategories(criteriaRequest, pageable)

        then:
        1 * activityCategoryRepository.findAll(_ as Specification, pageable) >> activityCategoryEntityPage
        1 * ActivityCategoryMapper.INSTANCE.mapToResponse(_ as ActivityCategoryEntity) >> { entity ->
            activityCategoryResponses
        }
        result.content == activityCategoryResponses
    }
    @Unroll
    def "update "() {
        given:
        def id = random.nextLong()
        def request = random.nextObject(ActivityCategoryRequest)
        def existingCategory = random.nextObject(ActivityCategoryEntity)
        def updatedCategory = random.nextObject(ActivityCategoryEntity)

        and:
        activityCategoryRepository.findById(id) >> Optional.of(existingCategory)
        ActivityCategoryMapper.INSTANCE.mapForUpdate(existingCategory, request) >> updatedCategory
        activityCategoryRepository.save(existingCategory) >> updatedCategory

        when:
        def result = activityCategoryService.update(id, request)

        then:
        1 * activityCategoryRepository.findById(id)
        1 * ActivityCategoryMapper.INSTANCE.mapForUpdate(existingCategory, request)
        1 * activityCategoryRepository.save(existingCategory)
        result == ActivityCategoryMapper.INSTANCE.mapToResponse(updatedCategory)
    }

    def "update should throw ActivityCategoryNotFoundException when category does not exist"() {
        given: "A non-existent category"
        def id = random.nextLong()
        def request = random.nextObject(ActivityCategoryRequest)

        and:
        activityCategoryRepository.findById(id) >> Optional.empty()

        when: "The update method is called"
        activityCategoryService.update(id, request)

        then: "An ActivityCategoryNotFoundException is thrown"
        thrown(ActivityCategoryNotFoundException)
    }

    def "softDelete "() {
        given:
        def id = random.nextLong()

        when:
        activityCategoryService.softDelete(id)

        then: 
        1 * activityCategoryRepository.softDelete(id)
    }
}
