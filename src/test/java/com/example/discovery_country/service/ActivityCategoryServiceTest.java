
package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.dao.repository.ActivityCategoryRepository;
import com.example.discovery_country.dao.repository.RegionRepository;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.exception.ActivityCategoryNotFoundException;
import com.example.discovery_country.model.dto.criteria.ActivityCategoryCriteriaRequest;
import com.example.discovery_country.model.dto.request.ActivityCategoryRequest;
import com.example.discovery_country.model.dto.response.ActivityCategoryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActivityCategoryServiceTest {

    @Mock
    private ActivityCategoryRepository mockActivityCategoryRepository;
    @Mock
    private RegionRepository mockRegionRepository;

    private ActivityCategoryService activityCategoryServiceUnderTest;

    @BeforeEach
    void setUp() {
        activityCategoryServiceUnderTest = new ActivityCategoryService(mockActivityCategoryRepository,
                mockRegionRepository);
    }

    @Test
    void testCreate() {
        final ActivityCategoryRequest request = ActivityCategoryRequest.builder()
                .regionIds(List.of(0L))
                .build();
        final Map<LangType, String> nameMap = Map.of(LangType.EN, "updatedName");
        final RegionEntity regionEntity = new RegionEntity();
        regionEntity.setId(0L);
        regionEntity.setName(nameMap);
        regionEntity.setMapUrl("mapUrl");
        regionEntity.setLatitude(0.0);
        regionEntity.setLongitude(0.0);
        final List<RegionEntity> regionEntities = List.of(regionEntity);
        when(mockRegionRepository.findAllById(List.of(0L))).thenReturn(regionEntities);

        final ActivityCategoryEntity activityCategoryEntity = new ActivityCategoryEntity();
        activityCategoryEntity.setId(0L);
        activityCategoryEntity.setName(nameMap);
        activityCategoryEntity.setDeleted(false);
        final ActivityEntity activity = new ActivityEntity();
        activityCategoryEntity.setActivities(List.of(activity));
        activityCategoryEntity.setRegions(List.of(regionEntity));

        when(mockActivityCategoryRepository.save(any(ActivityCategoryEntity.class)))
                .thenReturn(activityCategoryEntity);

        activityCategoryServiceUnderTest.create(request);


    }


    @Test
    void testCreate_RegionRepositoryReturnsNull() {
        final ActivityCategoryRequest request = ActivityCategoryRequest.builder()
                .regionIds(List.of(0L))
                .build();
        when(mockRegionRepository.findAllById(List.of(0L))).thenReturn(null);

        assertThatThrownBy(() -> activityCategoryServiceUnderTest.create(request)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testCreate_RegionRepositoryReturnsNoItems() {
        final ActivityCategoryRequest request = ActivityCategoryRequest.builder()
                .regionIds(List.of(0L))
                .build();
        when(mockRegionRepository.findAllById(List.of(0L))).thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> activityCategoryServiceUnderTest.create(request)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testCreate_ActivityCategoryRepositoryThrowsOptimisticLockingFailureException() {

        final ActivityCategoryRequest request = ActivityCategoryRequest.builder()
                .regionIds(List.of(0L))
                .build();
        final Map<LangType, String> nameMap = Map.of(LangType.EN, "updatedName");

        final RegionEntity regionEntity = new RegionEntity();
        regionEntity.setId(0L);
        regionEntity.setName(nameMap);
        regionEntity.setMapUrl("mapUrl");
        regionEntity.setLatitude(0.0);
        regionEntity.setLongitude(0.0);
        when(mockRegionRepository.findAllById(List.of(0L))).thenReturn(List.of(regionEntity));


        when(mockActivityCategoryRepository.save(any(ActivityCategoryEntity.class)))
                .thenThrow(OptimisticLockingFailureException.class);

        assertThatThrownBy(() -> activityCategoryServiceUnderTest.create(request))
                .isInstanceOf(OptimisticLockingFailureException.class);
    }

    @Test
    void testGetActivityCategories() {

        final ActivityCategoryCriteriaRequest criteriaRequest = ActivityCategoryCriteriaRequest.builder()
                .name("name")
                .build();
        final Map<LangType, String> nameMap = Map.of(LangType.EN, "updatedName");
        final ActivityCategoryEntity activityCategoryEntity = new ActivityCategoryEntity();
        activityCategoryEntity.setId(0L);
        activityCategoryEntity.setName(nameMap);
        activityCategoryEntity.setDeleted(false);
        final ActivityEntity activity = new ActivityEntity();
        activityCategoryEntity.setActivities(List.of(activity));
        final RegionEntity regionEntity = new RegionEntity();
        activityCategoryEntity.setRegions(List.of(regionEntity));
        final Page<ActivityCategoryEntity> activityCategoryEntities = new PageImpl<>(List.of(activityCategoryEntity));
        when(mockActivityCategoryRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(activityCategoryEntities);

        final Page<ActivityCategoryResponse> result = activityCategoryServiceUnderTest.getActivityCategories(
                criteriaRequest, PageRequest.of(0, 1));

    }

    @Test
    void testGetActivityCategories_ActivityCategoryRepositoryReturnsNoItems() {
        final ActivityCategoryCriteriaRequest criteriaRequest = ActivityCategoryCriteriaRequest.builder()
                .name("name")
                .build();
        when(mockActivityCategoryRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        final Page<ActivityCategoryResponse> result = activityCategoryServiceUnderTest.getActivityCategories(
                criteriaRequest, PageRequest.of(0, 1));

    }
    @Test
    void testUpdate() {

        final ActivityCategoryRequest categoryRequest = ActivityCategoryRequest.builder()
                .regionIds(List.of(0L))
                .build();
        final Map<LangType, String> nameMap = Map.of(LangType.EN, "updatedName");

        final ActivityCategoryEntity existingCategoryEntity = new ActivityCategoryEntity();
        existingCategoryEntity.setId(0L);
        existingCategoryEntity.setName(nameMap);
        existingCategoryEntity.setDeleted(false);
        final ActivityEntity existingActivity = new ActivityEntity();
        existingCategoryEntity.setActivities(List.of(existingActivity));
        final RegionEntity existingRegion = new RegionEntity();
        existingCategoryEntity.setRegions(List.of(existingRegion));
        when(mockActivityCategoryRepository.findById(0L)).thenReturn(Optional.of(existingCategoryEntity));


        final ActivityCategoryEntity updatedCategoryEntity = new ActivityCategoryEntity();
        updatedCategoryEntity.setId(0L);
        updatedCategoryEntity.setName(nameMap);
        updatedCategoryEntity.setDeleted(false);
        final ActivityEntity updatedActivity = new ActivityEntity();
        updatedCategoryEntity.setActivities(List.of(updatedActivity));
        final RegionEntity updatedRegion = new RegionEntity();
        updatedCategoryEntity.setRegions(List.of(updatedRegion));
        when(mockActivityCategoryRepository.save(any(ActivityCategoryEntity.class))).thenReturn(updatedCategoryEntity);


        final ActivityCategoryResponse result = activityCategoryServiceUnderTest.update(0L, categoryRequest);


        verify(mockActivityCategoryRepository).save(existingCategoryEntity);


        assertNotNull(result);
        assertEquals(updatedCategoryEntity.getName(), result.getName());

    }



    @Test
    void testUpdate_ActivityCategoryRepositoryFindByIdReturnsAbsent() {
        final ActivityCategoryRequest categoryRequest = ActivityCategoryRequest.builder()
                .regionIds(List.of(0L))
                .build();
        when(mockActivityCategoryRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> activityCategoryServiceUnderTest.update(0L, categoryRequest))
                .isInstanceOf(ActivityCategoryNotFoundException.class);
    }




    @Test
    void testSoftDelete() {

        activityCategoryServiceUnderTest.softDelete(0L);


        verify(mockActivityCategoryRepository).softDelete(0L);
    }
}