package com.example.discovery_country.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.discovery_country.dao.entity.ActivityCategoryEntity;
import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.dao.entity.ZoneEntity;
import com.example.discovery_country.dao.repository.ActivityCategoryRepository;
import com.example.discovery_country.dao.repository.RegionRepository;
import com.example.discovery_country.model.dto.request.ActivityCategoryRequest;
import com.example.discovery_country.model.dto.response.ActivityCategoryResponse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ActivityCategoryService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ActivityCategoryServiceDiffblueTest {
    @MockBean
    private ActivityCategoryRepository activityCategoryRepository;

    @Autowired
    private ActivityCategoryService activityCategoryService;

    @MockBean
    private RegionRepository regionRepository;

    /**
     * Method under test:
     * {@link ActivityCategoryService#create(ActivityCategoryRequest)}
     */
    @Test
    void testCreate() {
        // Arrange, Act and Assert
        assertThrows(IllegalArgumentException.class, () -> activityCategoryService.create(new ActivityCategoryRequest()));
    }

    /**
     * Method under test:
     * {@link ActivityCategoryService#create(ActivityCategoryRequest)}
     */
    @Test
    void testCreate2() {
        // Arrange
        ActivityCategoryRequest.ActivityCategoryRequestBuilder nameResult = ActivityCategoryRequest.builder().name("Name");
        ActivityCategoryRequest request = nameResult.regionIds(new ArrayList<>()).build();

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> activityCategoryService.create(request));
    }

    /**
     * Method under test:
     * {@link ActivityCategoryService#create(ActivityCategoryRequest)}
     */
    @Test
    void testCreate3() {
        // Arrange
        when(regionRepository.findAllById(Mockito.<Iterable<Long>>any())).thenReturn(new ArrayList<>());

        ArrayList<Long> resultLongList = new ArrayList<>();
        resultLongList.add(1L);
        ActivityCategoryRequest request = mock(ActivityCategoryRequest.class);
        when(request.getRegionIds()).thenReturn(resultLongList);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> activityCategoryService.create(request));
        verify(request, atLeast(1)).getRegionIds();
        verify(regionRepository).findAllById(isA(Iterable.class));
    }

    /**
     * Method under test:
     * {@link ActivityCategoryService#create(ActivityCategoryRequest)}
     */
    @Test
    void testCreate4() {
        // Arrange
        ActivityCategoryEntity activityCategoryEntity = new ActivityCategoryEntity();
        ArrayList<ActivityEntity> activities = new ArrayList<>();
        activityCategoryEntity.setActivities(activities);
        activityCategoryEntity.setDeleted(true);
        activityCategoryEntity.setId(1L);
        activityCategoryEntity.setName("Name");
        activityCategoryEntity.setRegions(new ArrayList<>());
        when(activityCategoryRepository.save(Mockito.<ActivityCategoryEntity>any())).thenReturn(activityCategoryEntity);

        ZoneEntity zone = new ZoneEntity();
        zone.setId(1L);
        zone.setName("ActionLog.createActivityCategory start");
        zone.setRegions(new ArrayList<>());

        RegionEntity regionEntity = new RegionEntity();
        regionEntity.setActivityCategories(new ArrayList<>());
        regionEntity.setHomeHotels(new ArrayList<>());
        regionEntity.setId(1L);
        regionEntity.setLatitude(10.0d);
        regionEntity.setLongitude(10.0d);
        regionEntity.setMapUrl("https://example.org/example");
        regionEntity.setName("ActionLog.createActivityCategory start");
        regionEntity.setRestaurants(new ArrayList<>());
        regionEntity.setScenicSpots(new ArrayList<>());
        regionEntity.setZone(zone);

        ArrayList<RegionEntity> regionEntityList = new ArrayList<>();
        regionEntityList.add(regionEntity);
        when(regionRepository.findAllById(Mockito.<Iterable<Long>>any())).thenReturn(regionEntityList);

        ArrayList<Long> resultLongList = new ArrayList<>();
        resultLongList.add(1L);
        ActivityCategoryRequest request = mock(ActivityCategoryRequest.class);
        when(request.getName()).thenReturn("Name");
        when(request.getRegionIds()).thenReturn(resultLongList);

        // Act
        ActivityCategoryResponse actualCreateResult = activityCategoryService.create(request);

        // Assert
        verify(request).getName();
        verify(request, atLeast(1)).getRegionIds();
        verify(activityCategoryRepository).save(isA(ActivityCategoryEntity.class));
        verify(regionRepository).findAllById(isA(Iterable.class));
        assertEquals("Name", actualCreateResult.getName());
        assertEquals(activities, actualCreateResult.getRegions());
    }
}
