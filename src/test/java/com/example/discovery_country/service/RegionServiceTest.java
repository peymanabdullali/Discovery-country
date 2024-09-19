package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.dao.repository.RegionRepository;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.mapper.RegionMapper;
import com.example.discovery_country.model.dto.criteria.CriteriaRequestForName;
import com.example.discovery_country.model.dto.request.RegionRequest;
import com.example.discovery_country.model.dto.response.RegionResponse;
import com.example.discovery_country.model.dto.response.RegionResponseForFindById;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegionServiceTest {

    @Mock
    private RegionRepository mockRegionRepository;
    @Mock
    private RegionMapper mockRegionMapper;

    private RegionService regionServiceUnderTest;

    @BeforeEach
    void setUp() {
        regionServiceUnderTest = new RegionService(mockRegionRepository, mockRegionMapper);
    }

    @Test
    void testCreateRegion() {
        // Setup
        final RegionRequest request = new RegionRequest(Map.ofEntries(Map.entry(LangType.AZ, "value")), 0.0, 0.0,
                "mapUrl", 0L);
        final RegionResponse expectedResult = RegionResponse.builder().build();

        // Configure RegionMapper.mapToEntity(...).
        final RegionEntity regionEntity = new RegionEntity();
        regionEntity.setId(0L);
        regionEntity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        regionEntity.setMapUrl("mapUrl");
        regionEntity.setLatitude(0.0);
        regionEntity.setLongitude(0.0);
        when(mockRegionMapper.mapToEntity(
                new RegionRequest(Map.ofEntries(Map.entry(LangType.AZ, "value")), 0.0, 0.0, "mapUrl", 0L)))
                .thenReturn(regionEntity);

        // Configure RegionRepository.save(...).
        final RegionEntity regionEntity1 = new RegionEntity();
        regionEntity1.setId(0L);
        regionEntity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        regionEntity1.setMapUrl("mapUrl");
        regionEntity1.setLatitude(0.0);
        regionEntity1.setLongitude(0.0);
        final RegionEntity entity = new RegionEntity();
        entity.setId(0L);
        entity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setMapUrl("mapUrl");
        entity.setLatitude(0.0);
        entity.setLongitude(0.0);
        when(mockRegionRepository.save(entity)).thenReturn(regionEntity1);

        // Configure RegionMapper.mapToResponse(...).
        final RegionEntity entity1 = new RegionEntity();
        entity1.setId(0L);
        entity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity1.setMapUrl("mapUrl");
        entity1.setLatitude(0.0);
        entity1.setLongitude(0.0);
        when(mockRegionMapper.mapToResponse(entity1, LangType.AZ)).thenReturn(RegionResponse.builder().build());

        // Run the test
        final RegionResponse result = regionServiceUnderTest.createRegion(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetRegions() {
        // Setup
        final CriteriaRequestForName criteriaRequest = CriteriaRequestForName.builder()
                .name("name")
                .key(LangType.AZ)
                .build();

        // Configure RegionRepository.findAll(...).
        final RegionEntity regionEntity = new RegionEntity();
        regionEntity.setId(0L);
        regionEntity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        regionEntity.setMapUrl("mapUrl");
        regionEntity.setLatitude(0.0);
        regionEntity.setLongitude(0.0);
        final Page<RegionEntity> regionEntities = new PageImpl<>(List.of(regionEntity));
        when(mockRegionRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(regionEntities);

        // Configure RegionMapper.mapToResponse(...).
        final RegionEntity entity = new RegionEntity();
        entity.setId(0L);
        entity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setMapUrl("mapUrl");
        entity.setLatitude(0.0);
        entity.setLongitude(0.0);
        when(mockRegionMapper.mapToResponse(entity, LangType.AZ)).thenReturn(RegionResponse.builder().build());

        // Run the test
        final Page<RegionResponse> result = regionServiceUnderTest.getRegions(criteriaRequest, PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    void testGetRegions_RegionRepositoryReturnsNoItems() {
        // Setup
        final CriteriaRequestForName criteriaRequest = CriteriaRequestForName.builder()
                .name("name")
                .key(LangType.AZ)
                .build();
        when(mockRegionRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final Page<RegionResponse> result = regionServiceUnderTest.getRegions(criteriaRequest, PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    void testDeleteRegion() {
        // Setup
        // Run the test
        regionServiceUnderTest.deleteRegion(0L);

        // Verify the results
        verify(mockRegionRepository).deleteById(0L);
    }

    @Test
    void testFindRegionById() {
        // Setup
        final RegionResponseForFindById expectedResult = RegionResponseForFindById.builder().build();

        // Configure RegionRepository.findById(...).
        final RegionEntity regionEntity1 = new RegionEntity();
        regionEntity1.setId(0L);
        regionEntity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        regionEntity1.setMapUrl("mapUrl");
        regionEntity1.setLatitude(0.0);
        regionEntity1.setLongitude(0.0);
        final Optional<RegionEntity> regionEntity = Optional.of(regionEntity1);
        when(mockRegionRepository.findById(0L)).thenReturn(regionEntity);

        // Configure RegionMapper.mapToResponseForFindById(...).
        final RegionResponseForFindById regionResponseForFindById = RegionResponseForFindById.builder().build();
        final RegionEntity entity = new RegionEntity();
        entity.setId(0L);
        entity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setMapUrl("mapUrl");
        entity.setLatitude(0.0);
        entity.setLongitude(0.0);
        when(mockRegionMapper.mapToResponseForFindById(entity, LangType.AZ)).thenReturn(regionResponseForFindById);

        // Run the test
        final RegionResponseForFindById result = regionServiceUnderTest.findRegionById(0L, LangType.AZ);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindRegionById_RegionRepositoryReturnsAbsent() {
        // Setup
        when(mockRegionRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> regionServiceUnderTest.findRegionById(0L, LangType.AZ))
                .isInstanceOf(RuntimeException.class);
    }
}
