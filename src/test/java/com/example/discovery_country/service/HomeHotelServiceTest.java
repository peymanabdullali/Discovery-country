package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.HomeHotelEntity;
import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.dao.entity.RoomEntity;
import com.example.discovery_country.dao.repository.HomeHotelRepository;
import com.example.discovery_country.dao.repository.ImageRepository;
import com.example.discovery_country.enums.HomeHotelType;
import com.example.discovery_country.enums.LangType;
import com.example.discovery_country.exception.HomeHotelNotFoundException;
import com.example.discovery_country.helper.IncreaseViewCount;
import com.example.discovery_country.helper.RatingHelper;
import com.example.discovery_country.helper.UpdateLike;
import com.example.discovery_country.mapper.HomeHotelMapper;
import com.example.discovery_country.model.dto.criteria.HomeHotelCriteriaRequest;
import com.example.discovery_country.model.dto.request.HomeHotelRequest;
import com.example.discovery_country.model.dto.response.HomeHotelResponse;
import com.example.discovery_country.model.dto.response.HomeHotelResponseFindById;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HomeHotelServiceTest {

    @Mock
    private HomeHotelMapper mockHomeHotelMapper;
    @Mock
    private HomeHotelRepository mockHomeHotelRepository;
    @Mock
    private ImageRepository mockImageRepository;
    @Mock
    private IncreaseViewCount mockViewCount;
    @Mock
    private RatingHelper mockRatingHelper;
    @Mock
    private UpdateLike mockUpdateLike;

    private HomeHotelService homeHotelServiceUnderTest;

    @BeforeEach
    void setUp() {
        homeHotelServiceUnderTest = new HomeHotelService(mockHomeHotelMapper, mockHomeHotelRepository,
                mockImageRepository, mockViewCount, mockRatingHelper, mockUpdateLike);
    }

    @Test
    void testCreate() {
        final HomeHotelRequest homeHotelRequest = HomeHotelRequest.builder()
                .imageIds(List.of(0L))
                .build();
        final HomeHotelResponse expectedResult = new HomeHotelResponse();
        expectedResult.setId(0L);
        expectedResult.setName("name");
        expectedResult.setAddress("address");
        expectedResult.setPrice(0.0);
        expectedResult.setViewed(0L);

        final ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId(0L);
        imageEntity.setName("name");
        imageEntity.setUrl("url");
        imageEntity.setDeleted(false);

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setAvailable(false);
        roomEntity.setDeleted(false);
        imageEntity.setRoom(roomEntity);

        final List<ImageEntity> imageEntities = List.of(imageEntity);
        when(mockImageRepository.findAllById(List.of(0L))).thenReturn(imageEntities);

        final HomeHotelEntity homeHotelEntity = new HomeHotelEntity();
        homeHotelEntity.setId(0L);
        homeHotelEntity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));

        RoomEntity roomEntity1 = new RoomEntity();
        roomEntity1.setAvailable(false);
        roomEntity1.setDeleted(false);
        homeHotelEntity.setRooms(Set.of(roomEntity1));

        final ImageEntity imageEntity1 = new ImageEntity();
        imageEntity1.setId(0L);
        imageEntity1.setName("name");
        imageEntity1.setUrl("url");
        imageEntity1.setDeleted(false);

        RoomEntity roomEntity2 = new RoomEntity();
        roomEntity2.setAvailable(false);
        roomEntity2.setDeleted(false);
        imageEntity1.setRoom(roomEntity2);

        final List<ImageEntity> images = List.of(imageEntity1);
        when(mockHomeHotelMapper.mapToEntity(any(HomeHotelRequest.class), eq(images))).thenReturn(homeHotelEntity);

        final HomeHotelEntity homeHotelEntity1 = new HomeHotelEntity();
        homeHotelEntity1.setId(0L);
        homeHotelEntity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity1.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity1.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));

        RoomEntity roomEntity3 = new RoomEntity();
        roomEntity3.setAvailable(false);
        roomEntity3.setDeleted(false);
        homeHotelEntity1.setRooms(Set.of(roomEntity3));

        final HomeHotelEntity entity = new HomeHotelEntity();
        entity.setId(0L);
        entity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));

        RoomEntity roomEntity4 = new RoomEntity();
        roomEntity4.setAvailable(false);
        roomEntity4.setDeleted(false);
        entity.setRooms(Set.of(roomEntity4));

        when(mockHomeHotelRepository.save(entity)).thenReturn(homeHotelEntity1);

        final HomeHotelResponse homeHotelResponse = new HomeHotelResponse();
        homeHotelResponse.setId(0L);
        homeHotelResponse.setName("name");
        homeHotelResponse.setAddress("address");
        homeHotelResponse.setPrice(0.0);
        homeHotelResponse.setViewed(0L);

        final HomeHotelEntity entity1 = new HomeHotelEntity();
        entity1.setId(0L);
        entity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity1.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity1.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));

        RoomEntity roomEntity5 = new RoomEntity();
        roomEntity5.setAvailable(false);
        roomEntity5.setDeleted(false);
        entity1.setRooms(Set.of(roomEntity5));

        when(mockHomeHotelMapper.mapToResponse(entity1, LangType.AZ)).thenReturn(homeHotelResponse);

        final HomeHotelResponse result = homeHotelServiceUnderTest.create(homeHotelRequest);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testCreate_ImageRepositoryReturnsNoItems() {
        final HomeHotelRequest homeHotelRequest = HomeHotelRequest.builder()
                .imageIds(List.of(0L))
                .build();
        final HomeHotelResponse expectedResult = new HomeHotelResponse();
        expectedResult.setId(0L);
        expectedResult.setName("name");
        expectedResult.setAddress("address");
        expectedResult.setPrice(0.0);
        expectedResult.setViewed(0L);

        when(mockImageRepository.findAllById(List.of(0L))).thenReturn(Collections.emptyList());

        final HomeHotelEntity homeHotelEntity = new HomeHotelEntity();
        homeHotelEntity.setId(0L);
        homeHotelEntity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));

        RoomEntity roomEntity1 = new RoomEntity();
        roomEntity1.setAvailable(false);
        roomEntity1.setDeleted(false);
        homeHotelEntity.setRooms(Set.of(roomEntity1));

        when(mockHomeHotelMapper.mapToEntity(any(HomeHotelRequest.class), eq(Collections.emptyList()))).thenReturn(homeHotelEntity);

        final HomeHotelEntity homeHotelEntity1 = new HomeHotelEntity();
        homeHotelEntity1.setId(0L);
        homeHotelEntity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity1.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity1.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));

        RoomEntity roomEntity2 = new RoomEntity();
        roomEntity2.setAvailable(false);
        roomEntity2.setDeleted(false);
        homeHotelEntity1.setRooms(Set.of(roomEntity2));

        when(mockHomeHotelRepository.save(any(HomeHotelEntity.class))).thenReturn(homeHotelEntity1);

        final HomeHotelResponse homeHotelResponse = new HomeHotelResponse();
        homeHotelResponse.setId(0L);
        homeHotelResponse.setName("name");
        homeHotelResponse.setAddress("address");
        homeHotelResponse.setPrice(0.0);
        homeHotelResponse.setViewed(0L);

        when(mockHomeHotelMapper.mapToResponse(any(HomeHotelEntity.class), eq(LangType.AZ))).thenReturn(homeHotelResponse);

        final HomeHotelResponse result = homeHotelServiceUnderTest.create(homeHotelRequest);

        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    void testCreate_HomeHotelRepositoryThrowsOptimisticLockingFailureException() {
        final HomeHotelRequest homeHotelRequest = HomeHotelRequest.builder()
                .imageIds(List.of(0L))
                .build();

        final ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId(0L);
        imageEntity.setName("name");
        imageEntity.setUrl("url");
        imageEntity.setDeleted(false);

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setAvailable(false);
        roomEntity.setDeleted(false);
        imageEntity.setRoom(roomEntity);

        final List<ImageEntity> imageEntities = List.of(imageEntity);
        when(mockImageRepository.findAllById(List.of(0L))).thenReturn(imageEntities);

        final HomeHotelEntity homeHotelEntity = new HomeHotelEntity();
        homeHotelEntity.setId(0L);
        homeHotelEntity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));

        RoomEntity roomEntity1 = new RoomEntity();
        roomEntity1.setAvailable(false);
        roomEntity1.setDeleted(false);
        homeHotelEntity.setRooms(Set.of(roomEntity1));

        final ImageEntity imageEntity1 = new ImageEntity();
        imageEntity1.setId(0L);
        imageEntity1.setName("name");
        imageEntity1.setUrl("url");
        imageEntity1.setDeleted(false);

        RoomEntity roomEntity2 = new RoomEntity();
        roomEntity2.setAvailable(false);
        roomEntity2.setDeleted(false);
        imageEntity1.setRoom(roomEntity2);

        final List<ImageEntity> images = List.of(imageEntity1);
        when(mockHomeHotelMapper.mapToEntity(any(HomeHotelRequest.class), eq(images))).thenReturn(homeHotelEntity);

        final HomeHotelEntity entity = new HomeHotelEntity();
        entity.setId(0L);
        entity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));

        RoomEntity roomEntity3 = new RoomEntity();
        roomEntity3.setAvailable(false);
        roomEntity3.setDeleted(false);
        entity.setRooms(Set.of(roomEntity3));

        when(mockHomeHotelRepository.save(entity)).thenThrow(OptimisticLockingFailureException.class);

        assertThatThrownBy(() -> homeHotelServiceUnderTest.create(homeHotelRequest))
                .isInstanceOf(OptimisticLockingFailureException.class);
    }


    @Test
    void testHomeHotelResponseFindById() {
        final HomeHotelResponseFindById expectedResult = new HomeHotelResponseFindById();
        expectedResult.setId(0L);
        expectedResult.setName("name");
        expectedResult.setDescription("description");
        expectedResult.setAddress("address");
        expectedResult.setContact("contact");

        final HomeHotelEntity homeHotelEntity1 = new HomeHotelEntity();
        homeHotelEntity1.setId(0L);
        homeHotelEntity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity1.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity1.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setAvailable(false);
        roomEntity.setDeleted(false);
        homeHotelEntity1.setRooms(Set.of(roomEntity));

        final Optional<HomeHotelEntity> homeHotelEntity = Optional.of(homeHotelEntity1);
        when(mockHomeHotelRepository.findOne(any(Specification.class))).thenReturn(homeHotelEntity);

        final HomeHotelResponseFindById homeHotelResponseFindById = new HomeHotelResponseFindById();
        homeHotelResponseFindById.setId(0L);
        homeHotelResponseFindById.setName("name");
        homeHotelResponseFindById.setDescription("description");
        homeHotelResponseFindById.setAddress("address");
        homeHotelResponseFindById.setContact("contact");

        final HomeHotelEntity entity = new HomeHotelEntity();
        entity.setId(0L);
        entity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));

        RoomEntity roomEntity2 = new RoomEntity();
        roomEntity2.setAvailable(false);
        roomEntity2.setDeleted(false);
        entity.setRooms(Set.of(roomEntity2));

        when(mockHomeHotelMapper.mapToHomeHotelResponseFindById(entity, LangType.AZ))
                .thenReturn(homeHotelResponseFindById);

        final HomeHotelResponseFindById result = homeHotelServiceUnderTest.homeHotelResponseFindById(0L, LangType.AZ);

        assertThat(result).isEqualTo(expectedResult);

        final HomeHotelEntity entity1 = new HomeHotelEntity();
        entity1.setId(0L);
        entity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity1.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity1.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));

        RoomEntity roomEntity3 = new RoomEntity();
        roomEntity3.setAvailable(false);
        roomEntity3.setDeleted(false);
        entity1.setRooms(Set.of(roomEntity3));

        verify(mockViewCount).updateViewCount(entity1);
    }

    @Test
    void testHomeHotelResponseFindById_HomeHotelRepositoryReturnsAbsent() {
        // Setup
        when(mockHomeHotelRepository.findOne(any(Specification.class))).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> homeHotelServiceUnderTest.homeHotelResponseFindById(0L, LangType.AZ))
                .isInstanceOf(HomeHotelNotFoundException.class);
    }

    @Test
    void testHomeHotelResponseFindById_HomeHotelRepositoryThrowsIncorrectResultSizeDataAccessException() {
        // Setup
        when(mockHomeHotelRepository.findOne(any(Specification.class)))
                .thenThrow(IncorrectResultSizeDataAccessException.class);

        // Run the test
        assertThatThrownBy(() -> homeHotelServiceUnderTest.homeHotelResponseFindById(0L, LangType.AZ))
                .isInstanceOf(IncorrectResultSizeDataAccessException.class);
    }

    @Test
    void testGetHomeHotels() {
        final HomeHotelCriteriaRequest criteria = new HomeHotelCriteriaRequest();
        criteria.setName("name");
        criteria.setType(HomeHotelType.HOME);
        criteria.setKey(LangType.AZ);

        final HomeHotelEntity homeHotelEntity = new HomeHotelEntity();
        homeHotelEntity.setId(0L);
        homeHotelEntity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setAvailable(false);
        roomEntity.setDeleted(false);
        homeHotelEntity.setRooms(Set.of(roomEntity));

        final Page<HomeHotelEntity> homeHotelEntities = new PageImpl<>(List.of(homeHotelEntity));
        when(mockHomeHotelRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(homeHotelEntities);

        final HomeHotelResponse homeHotelResponse = new HomeHotelResponse();
        homeHotelResponse.setId(0L);
        homeHotelResponse.setName("name");
        homeHotelResponse.setAddress("address");
        homeHotelResponse.setPrice(0.0);
        homeHotelResponse.setViewed(0L);

        final HomeHotelEntity entity = new HomeHotelEntity();
        entity.setId(0L);
        entity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));

        RoomEntity roomEntity2 = new RoomEntity();
        roomEntity2.setAvailable(false);
        roomEntity2.setDeleted(false);
        entity.setRooms(Set.of(roomEntity2));

        when(mockHomeHotelMapper.mapToResponse(entity, LangType.AZ)).thenReturn(homeHotelResponse);

        final Page<HomeHotelResponse> result = homeHotelServiceUnderTest.getHomeHotels(PageRequest.of(0, 1), criteria);
    }


    @Test
    void testGetHomeHotels_HomeHotelRepositoryReturnsNoItems() {
        // Setup
        final HomeHotelCriteriaRequest criteria = new HomeHotelCriteriaRequest();
        criteria.setName("name");
        criteria.setType(HomeHotelType.HOME);
        criteria.setKey(LangType.AZ);

        when(mockHomeHotelRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final Page<HomeHotelResponse> result = homeHotelServiceUnderTest.getHomeHotels(PageRequest.of(0, 1), criteria);

        // Verify the results
    }

    @Test
    void testUpdateHomeHotel() {
        final HomeHotelRequest homeHotelRequest = HomeHotelRequest.builder()
                .imageIds(List.of(0L))
                .build();
        final HomeHotelResponse expectedResult = new HomeHotelResponse();
        expectedResult.setId(0L);
        expectedResult.setName("name");
        expectedResult.setAddress("address");
        expectedResult.setPrice(0.0);
        expectedResult.setViewed(0L);

        final HomeHotelEntity homeHotelEntity1 = new HomeHotelEntity();
        homeHotelEntity1.setId(0L);
        homeHotelEntity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity1.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity1.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));

        RoomEntity roomEntity1 = new RoomEntity();
        roomEntity1.setAvailable(false);
        roomEntity1.setDeleted(false);
        homeHotelEntity1.setRooms(Set.of(roomEntity1));

        final Optional<HomeHotelEntity> homeHotelEntity = Optional.of(homeHotelEntity1);
        when(mockHomeHotelRepository.findById(0L)).thenReturn(homeHotelEntity);

        final HomeHotelEntity homeHotelEntity2 = new HomeHotelEntity();
        homeHotelEntity2.setId(0L);
        homeHotelEntity2.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity2.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity2.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));

        RoomEntity roomEntity2 = new RoomEntity();
        roomEntity2.setAvailable(false);
        roomEntity2.setDeleted(false);
        homeHotelEntity2.setRooms(Set.of(roomEntity2));

        final HomeHotelEntity entity = new HomeHotelEntity();
        entity.setId(0L);
        entity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));

        RoomEntity roomEntity3 = new RoomEntity();
        roomEntity3.setAvailable(false);
        roomEntity3.setDeleted(false);
        entity.setRooms(Set.of(roomEntity3));

        when(mockHomeHotelRepository.save(entity)).thenReturn(homeHotelEntity2);

        final HomeHotelResponse homeHotelResponse = new HomeHotelResponse();
        homeHotelResponse.setId(0L);
        homeHotelResponse.setName("name");
        homeHotelResponse.setAddress("address");
        homeHotelResponse.setPrice(0.0);
        homeHotelResponse.setViewed(0L);

        final HomeHotelEntity entity1 = new HomeHotelEntity();
        entity1.setId(0L);
        entity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity1.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity1.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));

        RoomEntity roomEntity4 = new RoomEntity();
        roomEntity4.setAvailable(false);
        roomEntity4.setDeleted(false);
        entity1.setRooms(Set.of(roomEntity4));

        when(mockHomeHotelMapper.mapToResponse(entity1, LangType.AZ)).thenReturn(homeHotelResponse);

        final HomeHotelResponse result = homeHotelServiceUnderTest.updateHomeHotel(0L, homeHotelRequest);

        assertThat(result).isEqualTo(expectedResult);

        final HomeHotelEntity homeHotelEntity3 = new HomeHotelEntity();
        homeHotelEntity3.setId(0L);
        homeHotelEntity3.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity3.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity3.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));

        RoomEntity roomEntity5 = new RoomEntity();
        roomEntity5.setAvailable(false);
        roomEntity5.setDeleted(false);
        homeHotelEntity3.setRooms(Set.of(roomEntity5));

        verify(mockHomeHotelMapper).mapForUpdate(eq(homeHotelEntity3), any(HomeHotelRequest.class));
    }


    @Test
    void testUpdateHomeHotel_HomeHotelRepositoryFindByIdReturnsAbsent() {
        // Setup
        final HomeHotelRequest homeHotelRequest = HomeHotelRequest.builder()
                .imageIds(List.of(0L))
                .build();
        when(mockHomeHotelRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> homeHotelServiceUnderTest.updateHomeHotel(0L, homeHotelRequest))
                .isInstanceOf(HomeHotelNotFoundException.class);
    }

    @Test
    void testUpdateHomeHotel_HomeHotelRepositorySaveThrowsOptimisticLockingFailureException() {
        final HomeHotelRequest homeHotelRequest = new HomeHotelRequest();
        homeHotelRequest.setImageIds(List.of(0L));

        final HomeHotelEntity homeHotelEntity1 = new HomeHotelEntity();
        homeHotelEntity1.setId(0L);
        homeHotelEntity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity1.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity1.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity1.setRooms(Set.of(new RoomEntity() {{
            setAvailable(false);
            setDeleted(false);
        }}));
        final Optional<HomeHotelEntity> homeHotelEntity = Optional.of(homeHotelEntity1);
        when(mockHomeHotelRepository.findById(0L)).thenReturn(homeHotelEntity);

        final HomeHotelEntity entity = new HomeHotelEntity();
        entity.setId(0L);
        entity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setRooms(Set.of(new RoomEntity() {{
            setAvailable(false);
            setDeleted(false);
        }}));
        when(mockHomeHotelRepository.save(entity)).thenThrow(OptimisticLockingFailureException.class);

        assertThatThrownBy(() -> homeHotelServiceUnderTest.updateHomeHotel(0L, homeHotelRequest))
                .isInstanceOf(OptimisticLockingFailureException.class);

        final HomeHotelEntity homeHotelEntity2 = new HomeHotelEntity();
        homeHotelEntity2.setId(0L);
        homeHotelEntity2.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity2.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity2.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity2.setRooms(Set.of(new RoomEntity() {{
            setAvailable(false);
            setDeleted(false);
        }}));
        verify(mockHomeHotelMapper).mapForUpdate(eq(homeHotelEntity2), any(HomeHotelRequest.class));
    }



    @Test
    void testSoftDelete() {
        homeHotelServiceUnderTest.softDelete(0L);

        verify(mockHomeHotelRepository).softDelete(0L);
    }

    @Test
    void testRateHomeHotel() {
        final HomeHotelEntity homeHotelEntity1 = new HomeHotelEntity();
        homeHotelEntity1.setId(0L);
        homeHotelEntity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity1.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity1.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity1.setRooms(Set.of(new RoomEntity() {{
            setAvailable(false);
            setDeleted(false);
        }}));
        final Optional<HomeHotelEntity> homeHotelEntity = Optional.of(homeHotelEntity1);
        when(mockHomeHotelRepository.findById(0L)).thenReturn(homeHotelEntity);

        homeHotelServiceUnderTest.rateHomeHotel(0L, 0);

        final HomeHotelEntity entity = new HomeHotelEntity();
        entity.setId(0L);
        entity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setRooms(Set.of(new RoomEntity() {{
            setAvailable(false);
            setDeleted(false);
        }}));
        verify(mockRatingHelper).addRating(entity, 0);
    }


    @Test
    void testRateHomeHotel_HomeHotelRepositoryReturnsAbsent() {
        when(mockHomeHotelRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> homeHotelServiceUnderTest.rateHomeHotel(0L, 0))
                .isInstanceOf(HomeHotelNotFoundException.class);
    }

    @Test
    void testUpdateLikeCount() {
        final HomeHotelEntity homeHotelEntity1 = new HomeHotelEntity();
        homeHotelEntity1.setId(0L);
        homeHotelEntity1.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity1.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity1.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        homeHotelEntity1.setRooms(Set.of(new RoomEntity() {{
            setAvailable(false);
            setDeleted(false);
        }}));
        final Optional<HomeHotelEntity> homeHotelEntity = Optional.of(homeHotelEntity1);
        when(mockHomeHotelRepository.findById(0L)).thenReturn(homeHotelEntity);

        homeHotelServiceUnderTest.updateLikeCount(0L, false);

        final HomeHotelEntity entity = new HomeHotelEntity();
        entity.setId(0L);
        entity.setName(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setDescription(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setAddress(Map.ofEntries(Map.entry(LangType.AZ, "value")));
        entity.setRooms(Set.of(new RoomEntity() {{
            setAvailable(false);
            setDeleted(false);
        }}));
        verify(mockUpdateLike).updateLikeCount(entity, false);
    }

    @Test
    void testUpdateLikeCount_HomeHotelRepositoryReturnsAbsent() {
        when(mockHomeHotelRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> homeHotelServiceUnderTest.updateLikeCount(0L, false))
                .isInstanceOf(HomeHotelNotFoundException.class);
    }
}
