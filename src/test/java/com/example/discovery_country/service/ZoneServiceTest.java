package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.dao.entity.ZoneEntity;
import com.example.discovery_country.dao.repository.ZoneRepository;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.mapper.ZoneMapper;
import com.example.discovery_country.model.dto.criteria.CriteriaRequestForName;
import com.example.discovery_country.model.dto.request.ZoneRequest;
import com.example.discovery_country.model.dto.response.RegionResponseForRelations;
import com.example.discovery_country.model.dto.response.ZoneResponse;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ZoneServiceTest {

    @Mock
    private ZoneRepository mockZoneRepository;
    @Mock
    private ZoneMapper mockZoneMapper;

    private ZoneService zoneServiceUnderTest;

    @BeforeEach
    void setUp() {
        zoneServiceUnderTest = new ZoneService(mockZoneRepository, mockZoneMapper);
    }

    @Test
    void testCreate() {
        // Setup
        final ZoneRequest request = new ZoneRequest(Map.ofEntries(Map.entry("value", "value")));
        final ZoneResponse expectedResult = new ZoneResponse(0L, "name",
                List.of(new RegionResponseForRelations(0L, "name")));

        // Configure ZoneMapper.mapToEntity(...).
        final ZoneEntity zoneEntity = new ZoneEntity();
        zoneEntity.setId(0L);
        zoneEntity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        final RegionEntity regionEntity = new RegionEntity();
        regionEntity.setId(0L);
        regionEntity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        zoneEntity.setRegions(List.of(regionEntity));
        when(mockZoneMapper.mapToEntity(any(ZoneRequest.class)))
                .thenReturn(zoneEntity);

        // Configure ZoneRepository.save(...).
        final ZoneEntity zoneEntity1 = new ZoneEntity();
        zoneEntity1.setId(0L);
        zoneEntity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        final RegionEntity regionEntity1 = new RegionEntity();
        regionEntity1.setId(0L);
        regionEntity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        zoneEntity1.setRegions(List.of(regionEntity1));
        when(mockZoneRepository.save(any(ZoneEntity.class))).thenReturn(zoneEntity1);

        // Adjusted stubbing to account for `null` LangType
        final ZoneResponse zoneResponse = new ZoneResponse(0L, "name",
                List.of(new RegionResponseForRelations(0L, "name")));
        when(mockZoneMapper.mapToResponse(any(ZoneEntity.class), eq(null))).thenReturn(zoneResponse);

        // Run the test
        final ZoneResponse result = zoneServiceUnderTest.create(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    void testCreate_ZoneRepositoryThrowsOptimisticLockingFailureException() {
        // Setup
        final ZoneRequest request = new ZoneRequest(Map.ofEntries(Map.entry("value", "value")));

        // Configure ZoneMapper.mapToEntity(...).
        final ZoneEntity zoneEntity = new ZoneEntity();
        zoneEntity.setId(0L);
        zoneEntity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        final RegionEntity regionEntity = new RegionEntity();
        regionEntity.setId(0L);
        regionEntity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        zoneEntity.setRegions(List.of(regionEntity));
        when(mockZoneMapper.mapToEntity(new ZoneRequest(Map.ofEntries(Map.entry("value", "value")))))
                .thenReturn(zoneEntity);

        // Configure ZoneRepository.save(...).
        final ZoneEntity entity = new ZoneEntity();
        entity.setId(0L);
        entity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        final RegionEntity regionEntity1 = new RegionEntity();
        regionEntity1.setId(0L);
        regionEntity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setRegions(List.of(regionEntity1));
        when(mockZoneRepository.save(entity)).thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        assertThatThrownBy(() -> zoneServiceUnderTest.create(request))
                .isInstanceOf(OptimisticLockingFailureException.class);
    }

    @Test
    void testGetZones() {
        // Setup
        final CriteriaRequestForName criteriaRequest = CriteriaRequestForName.builder()
                .name("name")
                .key(LangType.AZ)
                .build();

        // Configure ZoneRepository.findAll(...).
        final ZoneEntity zoneEntity = new ZoneEntity();
        zoneEntity.setId(0L);
        zoneEntity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        final RegionEntity regionEntity = new RegionEntity();
        regionEntity.setId(0L);
        regionEntity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        zoneEntity.setRegions(List.of(regionEntity));
        final Page<ZoneEntity> zoneEntities = new PageImpl<>(List.of(zoneEntity));
        when(mockZoneRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(zoneEntities);

        // Configure ZoneMapper.mapToResponse(...).
        final ZoneResponse zoneResponse = new ZoneResponse(0L, "name",
                List.of(new RegionResponseForRelations(0L, "name")));
        final ZoneEntity entity = new ZoneEntity();
        entity.setId(0L);
        entity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        final RegionEntity regionEntity1 = new RegionEntity();
        regionEntity1.setId(0L);
        regionEntity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setRegions(List.of(regionEntity1));
        when(mockZoneMapper.mapToResponse(entity, LangType.AZ)).thenReturn(zoneResponse);

        // Run the test
        final Page<ZoneResponse> result = zoneServiceUnderTest.getZones(criteriaRequest, PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    void testGetZones_ZoneRepositoryReturnsNoItems() {
        // Setup
        final CriteriaRequestForName criteriaRequest = CriteriaRequestForName.builder()
                .name("name")
                .key(LangType.AZ)
                .build();
        when(mockZoneRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final Page<ZoneResponse> result = zoneServiceUnderTest.getZones(criteriaRequest, PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    void testUpdate() {
        // Setup
        final ZoneRequest zoneRequest = new ZoneRequest(Map.ofEntries(Map.entry("value", "value")));
        final ZoneResponse expectedResult = new ZoneResponse(0L, "name",
                List.of(new RegionResponseForRelations(0L, "name")));

        // Configure ZoneRepository.findById(...).
        final ZoneEntity zoneEntity1 = new ZoneEntity();
        zoneEntity1.setId(0L);
        zoneEntity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        final RegionEntity regionEntity = new RegionEntity();
        regionEntity.setId(0L);
        regionEntity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        zoneEntity1.setRegions(List.of(regionEntity));
        final Optional<ZoneEntity> zoneEntity = Optional.of(zoneEntity1);
        when(mockZoneRepository.findById(0L)).thenReturn(zoneEntity);

        // Configure ZoneRepository.save(...).
        final ZoneEntity zoneEntity2 = new ZoneEntity();
        zoneEntity2.setId(0L);
        zoneEntity2.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        final RegionEntity regionEntity1 = new RegionEntity();
        regionEntity1.setId(0L);
        regionEntity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        zoneEntity2.setRegions(List.of(regionEntity1));
        when(mockZoneRepository.save(any(ZoneEntity.class))).thenReturn(zoneEntity2);

        // Configure ZoneMapper.mapToResponse(...).
        final ZoneResponse zoneResponse = new ZoneResponse(0L, "name",
                List.of(new RegionResponseForRelations(0L, "name")));
        final ZoneEntity entity1 = new ZoneEntity();
        entity1.setId(0L);
        entity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        final RegionEntity regionEntity3 = new RegionEntity();
        regionEntity3.setId(0L);
        regionEntity3.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity1.setRegions(List.of(regionEntity3));

        // Stubbing with null to match the actual call
        when(mockZoneMapper.mapToResponse(entity1, null)).thenReturn(zoneResponse);

        // Run the test
        final ZoneResponse result = zoneServiceUnderTest.update(0L, zoneRequest);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);

        // Confirm ZoneMapper.mapForUpdate(...).
        final ZoneEntity entity2 = new ZoneEntity();
        entity2.setId(0L);
        entity2.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        final RegionEntity regionEntity4 = new RegionEntity();
        regionEntity4.setId(0L);
        regionEntity4.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity2.setRegions(List.of(regionEntity4));
        verify(mockZoneMapper).mapForUpdate(entity2, new ZoneRequest(Map.ofEntries(Map.entry("value", "value"))));
    }


    @Test
    void testUpdate_ZoneRepositoryFindByIdReturnsAbsent() {
        // Setup
        final ZoneRequest zoneRequest = new ZoneRequest(Map.ofEntries(Map.entry("value", "value")));
        when(mockZoneRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> zoneServiceUnderTest.update(0L, zoneRequest)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testUpdate_ZoneRepositorySaveThrowsOptimisticLockingFailureException() {
        // Setup
        final ZoneRequest zoneRequest = new ZoneRequest(Map.ofEntries(Map.entry("value", "value")));

        // Configure ZoneRepository.findById(...).
        final ZoneEntity zoneEntity1 = new ZoneEntity();
        zoneEntity1.setId(0L);
        zoneEntity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        final RegionEntity regionEntity = new RegionEntity();
        regionEntity.setId(0L);
        regionEntity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        zoneEntity1.setRegions(List.of(regionEntity));
        final Optional<ZoneEntity> zoneEntity = Optional.of(zoneEntity1);
        when(mockZoneRepository.findById(0L)).thenReturn(zoneEntity);

        // Configure ZoneRepository.save(...).
        final ZoneEntity entity = new ZoneEntity();
        entity.setId(0L);
        entity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        final RegionEntity regionEntity1 = new RegionEntity();
        regionEntity1.setId(0L);
        regionEntity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setRegions(List.of(regionEntity1));
        when(mockZoneRepository.save(entity)).thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        assertThatThrownBy(() -> zoneServiceUnderTest.update(0L, zoneRequest))
                .isInstanceOf(OptimisticLockingFailureException.class);

        // Confirm ZoneMapper.mapForUpdate(...).
        final ZoneEntity entity1 = new ZoneEntity();
        entity1.setId(0L);
        entity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        final RegionEntity regionEntity2 = new RegionEntity();
        regionEntity2.setId(0L);
        regionEntity2.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity1.setRegions(List.of(regionEntity2));
        verify(mockZoneMapper).mapForUpdate(entity1, new ZoneRequest(Map.ofEntries(Map.entry("value", "value"))));
    }

    @Test
    void testDelete() {
        // Setup
        // Run the test
        zoneServiceUnderTest.delete(0L);

        // Verify the results
        verify(mockZoneRepository).deleteById(0L);
    }
}
