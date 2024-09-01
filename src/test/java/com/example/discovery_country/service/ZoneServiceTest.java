package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.RegionEntity;
import com.example.discovery_country.dao.entity.ZoneEntity;
import com.example.discovery_country.dao.repository.ZoneRepository;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
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

        final ZoneRequest request = new ZoneRequest("name");


        final RegionResponseForRelations expectedRegionResponse = new RegionResponseForRelations("name");
        final ZoneResponse expectedResult = new ZoneResponse(0L, "name", List.of(expectedRegionResponse));


        final ZoneEntity zoneEntity = new ZoneEntity();
        zoneEntity.setName("name");
        final RegionEntity regionEntity = new RegionEntity();
        regionEntity.setName("name");
        zoneEntity.setRegions(List.of(regionEntity));
        when(mockZoneMapper.mapToEntity(request)).thenReturn(zoneEntity);

        final ZoneEntity savedZoneEntity = new ZoneEntity();
        savedZoneEntity.setId(0L);
        savedZoneEntity.setName("name");
        savedZoneEntity.setRegions(List.of(regionEntity));
        when(mockZoneRepository.save(zoneEntity)).thenReturn(savedZoneEntity);

        when(mockZoneMapper.mapToResponse(savedZoneEntity)).thenReturn(expectedResult);


        final ZoneResponse result = zoneServiceUnderTest.create(request);


        assertThat(result).isEqualTo(expectedResult);
    }



    @Test
    void testCreate_ZoneRepositoryThrowsOptimisticLockingFailureException() {

        final ZoneRequest request = new ZoneRequest("name");


        final ZoneEntity zoneEntity = new ZoneEntity();
        zoneEntity.setId(0L);
        zoneEntity.setName("name");
        final RegionEntity regionEntity = new RegionEntity();
        regionEntity.setId(0L);
        regionEntity.setName("name");
        zoneEntity.setRegions(List.of(regionEntity));
        when(mockZoneMapper.mapToEntity(new ZoneRequest("name"))).thenReturn(zoneEntity);


        final ZoneEntity entity = new ZoneEntity();
        entity.setId(0L);
        entity.setName("name");
        final RegionEntity regionEntity1 = new RegionEntity();
        regionEntity1.setId(0L);
        regionEntity1.setName("name");
        entity.setRegions(List.of(regionEntity1));
        when(mockZoneRepository.save(entity)).thenThrow(OptimisticLockingFailureException.class);


        assertThatThrownBy(() -> zoneServiceUnderTest.create(request))
                .isInstanceOf(OptimisticLockingFailureException.class);
    }

    @Test
    void testGetZones() {

        final CriteriaRequestForName criteriaRequest = CriteriaRequestForName.builder()
                .name("name")
                .build();


        final ZoneEntity zoneEntity = new ZoneEntity();
        zoneEntity.setId(0L);
        zoneEntity.setName("name");
        final RegionEntity regionEntity = new RegionEntity();
        regionEntity.setId(0L);
        regionEntity.setName("name");
        zoneEntity.setRegions(List.of(regionEntity));
        final Page<ZoneEntity> zoneEntities = new PageImpl<>(List.of(zoneEntity));
        when(mockZoneRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(zoneEntities);


        final List<ZoneResponse> zoneResponses = List.of(
                new ZoneResponse(0L, "name", List.of(new RegionResponseForRelations("name"))));
        final ZoneEntity zoneEntity1 = new ZoneEntity();
        zoneEntity1.setId(0L);
        zoneEntity1.setName("name");
        final RegionEntity regionEntity1 = new RegionEntity();
        regionEntity1.setId(0L);
        regionEntity1.setName("name");
        zoneEntity1.setRegions(List.of(regionEntity1));
        final List<ZoneEntity> entities = List.of(zoneEntity1);
        when(mockZoneMapper.mapToZoneResponseList(entities)).thenReturn(zoneResponses);


        final Page<ZoneResponse> result = zoneServiceUnderTest.getZones(criteriaRequest, PageRequest.of(0, 1));


    }


    @Test
    void testGetZones_ZoneMapperReturnsNoItems() {

        final CriteriaRequestForName criteriaRequest = CriteriaRequestForName.builder()
                .name("name")
                .build();


        final ZoneEntity zoneEntity = new ZoneEntity();
        zoneEntity.setId(0L);
        zoneEntity.setName("name");
        final RegionEntity regionEntity = new RegionEntity();
        regionEntity.setId(0L);
        regionEntity.setName("name");
        zoneEntity.setRegions(List.of(regionEntity));
        final Page<ZoneEntity> zoneEntities = new PageImpl<>(List.of(zoneEntity));
        when(mockZoneRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(zoneEntities);


        final ZoneEntity zoneEntity1 = new ZoneEntity();
        zoneEntity1.setId(0L);
        zoneEntity1.setName("name");
        final RegionEntity regionEntity1 = new RegionEntity();
        regionEntity1.setId(0L);
        regionEntity1.setName("name");
        zoneEntity1.setRegions(List.of(regionEntity1));
        final List<ZoneEntity> entities = List.of(zoneEntity1);
        when(mockZoneMapper.mapToZoneResponseList(entities)).thenReturn(Collections.emptyList());


        final Page<ZoneResponse> result = zoneServiceUnderTest.getZones(criteriaRequest, PageRequest.of(0, 1));


    }



    @Test
    void testUpdate_ZoneRepositoryFindByIdReturnsAbsent() {

        final ZoneRequest zoneRequest = new ZoneRequest("name");
        when(mockZoneRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> zoneServiceUnderTest.update(0L, zoneRequest)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testUpdate_ZoneRepositorySaveThrowsOptimisticLockingFailureException() {

        final ZoneRequest zoneRequest = new ZoneRequest("name");


        final ZoneEntity zoneEntity1 = new ZoneEntity();
        zoneEntity1.setId(0L);
        zoneEntity1.setName("name");
        final RegionEntity regionEntity = new RegionEntity();
        regionEntity.setId(0L);
        regionEntity.setName("name");
        zoneEntity1.setRegions(List.of(regionEntity));
        final Optional<ZoneEntity> zoneEntity = Optional.of(zoneEntity1);
        when(mockZoneRepository.findById(0L)).thenReturn(zoneEntity);


        final ZoneEntity entity = new ZoneEntity();
        entity.setId(0L);
        entity.setName("name");
        final RegionEntity regionEntity1 = new RegionEntity();
        regionEntity1.setId(0L);
        regionEntity1.setName("name");
        entity.setRegions(List.of(regionEntity1));
        when(mockZoneRepository.save(entity)).thenThrow(OptimisticLockingFailureException.class);

        assertThatThrownBy(() -> zoneServiceUnderTest.update(0L, zoneRequest))
                .isInstanceOf(OptimisticLockingFailureException.class);


        final ZoneEntity entity1 = new ZoneEntity();
        entity1.setId(0L);
        entity1.setName("name");
        final RegionEntity regionEntity2 = new RegionEntity();
        regionEntity2.setId(0L);
        regionEntity2.setName("name");
        entity1.setRegions(List.of(regionEntity2));
        verify(mockZoneMapper).mapForUpdate(entity1, new ZoneRequest("name"));
    }

    @Test
    void testDelete() {

        zoneServiceUnderTest.delete(0L);

        verify(mockZoneRepository).deleteById(0L);
    }
}
