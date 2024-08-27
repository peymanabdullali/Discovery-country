package com.example.discovery_country.controller

import com.example.discovery_country.model.dto.criteria.ActivityCategoryCriteriaRequest
import com.example.discovery_country.model.dto.request.ActivityCategoryRequest
import com.example.discovery_country.model.dto.response.ActivityCategoryResponse
import com.example.discovery_country.service.ActivityCategoryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Title("Unit tests for ActivityCategoryController")
class ActivityCategoryControllerSpec extends Specification {

    ActivityCategoryService activityCategoryService = Mock()

    @Subject
    ActivityCategoryController activityCategoryController = new ActivityCategoryController(activityCategoryService)

    def "createActivityCategory"() {
        given:
        def request = new ActivityCategoryRequest()
        def response = new ActivityCategoryResponse()

        when: "The createActivityCategory method is called"
        ResponseEntity<ActivityCategoryResponse> result = activityCategoryController.createActivityCategory(request)

        then:
        1 * activityCategoryService.create(request) >> response
        result.statusCode == HttpStatus.CREATED
        result.body == response
    }

    def "getActivityCategories should return  paginated list "() {
        given:
        def criteriaRequest = new ActivityCategoryCriteriaRequest()
        Pageable pageable = Mock()
        Page<ActivityCategoryResponse> response = Mock()

        when:
        ResponseEntity<Page<ActivityCategoryResponse>> result = activityCategoryController.getActivityCategories(criteriaRequest, pageable)

        then:
        1 * activityCategoryService.getActivityCategories(criteriaRequest, pageable) >> response
        result.statusCode == HttpStatus.OK
        result.body == response
    }

    def "updateActivityCategory "() {
        given:
        def id = 1L
        def request = new ActivityCategoryRequest()
        def response = new ActivityCategoryResponse()

        when:
        ResponseEntity<ActivityCategoryResponse> result = activityCategoryController.updateActivityCategory(id, request)

        then:
        1 * activityCategoryService.update(id, request) >> response
        result.statusCode == HttpStatus.OK
        result.body == response
    }

    def "deleteActivityCategory "() {
        given:
        def id = 1L

        when: "The deleteActivityCategory method is called"
        ResponseEntity<Void> result = activityCategoryController.deleteActivityCategory(id)

        then:
        1 * activityCategoryService.softDelete(id)
        result.statusCode == HttpStatus.NO_CONTENT
    }
}
