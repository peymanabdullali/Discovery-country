package com.example.discovery_country.service;

import com.example.discovery_country.dao.entity.HomeHotelEntity;
import com.example.discovery_country.dao.entity.ImageEntity;
import com.example.discovery_country.dao.entity.RoomEntity;
import com.example.discovery_country.dao.repository.HomeHotelRepository;
import com.example.discovery_country.dao.repository.ImageRepository;
import com.example.discovery_country.enums.HomeHotelType;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
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
        imageEntity.setRoom(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build());
        final List<ImageEntity> imageEntities = List.of(imageEntity);
        when(mockImageRepository.findAllById(List.of(0L))).thenReturn(imageEntities);


        final HomeHotelEntity homeHotelEntity = new HomeHotelEntity();
        homeHotelEntity.setId(0L);
        homeHotelEntity.setName("name");
        homeHotelEntity.setDescription("description");
        homeHotelEntity.setAddress("address");
        homeHotelEntity.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        final ImageEntity imageEntity1 = new ImageEntity();
        imageEntity1.setId(0L);
        imageEntity1.setName("name");
        imageEntity1.setUrl("url");
        imageEntity1.setDeleted(false);
        imageEntity1.setRoom(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build());
        final List<ImageEntity> images = List.of(imageEntity1);
        when(mockHomeHotelMapper.mapToEntity(any(HomeHotelRequest.class), eq(images))).thenReturn(homeHotelEntity);

        // Configure HomeHotelRepository.save(...).
        final HomeHotelEntity homeHotelEntity1 = new HomeHotelEntity();
        homeHotelEntity1.setId(0L);
        homeHotelEntity1.setName("name");
        homeHotelEntity1.setDescription("description");
        homeHotelEntity1.setAddress("address");
        homeHotelEntity1.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        final HomeHotelEntity entity = new HomeHotelEntity();
        entity.setId(0L);
        entity.setName("name");
        entity.setDescription("description");
        entity.setAddress("address");
        entity.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        when(mockHomeHotelRepository.save(entity)).thenReturn(homeHotelEntity1);

        // Configure HomeHotelMapper.mapToResponse(...).
        final HomeHotelResponse homeHotelResponse = new HomeHotelResponse();
        homeHotelResponse.setId(0L);
        homeHotelResponse.setName("name");
        homeHotelResponse.setAddress("address");
        homeHotelResponse.setPrice(0.0);
        homeHotelResponse.setViewed(0L);
        final HomeHotelEntity entity1 = new HomeHotelEntity();
        entity1.setId(0L);
        entity1.setName("name");
        entity1.setDescription("description");
        entity1.setAddress("address");
        entity1.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        when(mockHomeHotelMapper.mapToResponse(entity1)).thenReturn(homeHotelResponse);

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
        imageEntity.setRoom(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build());
        final List<ImageEntity> imageEntities = List.of(imageEntity);
        when(mockImageRepository.findAllById(List.of(0L))).thenReturn(imageEntities);


        final HomeHotelEntity homeHotelEntity = new HomeHotelEntity();
        homeHotelEntity.setId(0L);
        homeHotelEntity.setName("name");
        homeHotelEntity.setDescription("description");
        homeHotelEntity.setAddress("address");
        homeHotelEntity.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        final ImageEntity imageEntity1 = new ImageEntity();
        imageEntity1.setId(0L);
        imageEntity1.setName("name");
        imageEntity1.setUrl("url");
        imageEntity1.setDeleted(false);
        imageEntity1.setRoom(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build());
        final List<ImageEntity> images = List.of(imageEntity1);
        when(mockHomeHotelMapper.mapToEntity(any(HomeHotelRequest.class), eq(images))).thenReturn(homeHotelEntity);

        // Configure HomeHotelRepository.save(...).
        final HomeHotelEntity entity = new HomeHotelEntity();
        entity.setId(0L);
        entity.setName("name");
        entity.setDescription("description");
        entity.setAddress("address");
        entity.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        when(mockHomeHotelRepository.save(entity)).thenThrow(OptimisticLockingFailureException.class);

        // Run the test
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
        homeHotelEntity1.setName("name");
        homeHotelEntity1.setDescription("description");
        homeHotelEntity1.setAddress("address");
        homeHotelEntity1.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        final Optional<HomeHotelEntity> homeHotelEntity = Optional.of(homeHotelEntity1);
        when(mockHomeHotelRepository.findOne(any(Specification.class))).thenReturn(homeHotelEntity);


        final HomeHotelResponseFindById homeHotelResponseFindById = new HomeHotelResponseFindById();
        homeHotelResponseFindById.setId(0L);
        homeHotelResponseFindById.setName("name");
        homeHotelResponseFindById.setDescription("description");
        homeHotelResponseFindById.setAddress("address");
        homeHotelResponseFindById.setContact("contact");
        final HomeHotelEntity homeHotelEntity2 = new HomeHotelEntity();
        homeHotelEntity2.setId(0L);
        homeHotelEntity2.setName("name");
        homeHotelEntity2.setDescription("description");
        homeHotelEntity2.setAddress("address");
        homeHotelEntity2.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        when(mockHomeHotelMapper.mapToHomeHotelResponseFindById(homeHotelEntity2))
                .thenReturn(homeHotelResponseFindById);


        final HomeHotelResponseFindById result = homeHotelServiceUnderTest.homeHotelResponseFindById(0L);

        assertThat(result).isEqualTo(expectedResult);


        final HomeHotelEntity entity = new HomeHotelEntity();
        entity.setId(0L);
        entity.setName("name");
        entity.setDescription("description");
        entity.setAddress("address");
        entity.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        verify(mockViewCount).updateViewCount(entity);
    }

    @Test
    void testHomeHotelResponseFindById_HomeHotelRepositoryReturnsAbsent() {

        when(mockHomeHotelRepository.findOne(any(Specification.class))).thenReturn(Optional.empty());


        assertThatThrownBy(() -> homeHotelServiceUnderTest.homeHotelResponseFindById(0L))
                .isInstanceOf(HomeHotelNotFoundException.class);
    }

    @Test
    void testHomeHotelResponseFindById_HomeHotelRepositoryThrowsIncorrectResultSizeDataAccessException() {

        when(mockHomeHotelRepository.findOne(any(Specification.class)))
                .thenThrow(IncorrectResultSizeDataAccessException.class);


        assertThatThrownBy(() -> homeHotelServiceUnderTest.homeHotelResponseFindById(0L))
                .isInstanceOf(IncorrectResultSizeDataAccessException.class);
    }

    @Test
    void testGetHomeHotels() {

        final HomeHotelCriteriaRequest criteria = new HomeHotelCriteriaRequest();
        criteria.setName("name");
        criteria.setType(HomeHotelType.HOME);


        final HomeHotelEntity homeHotelEntity = new HomeHotelEntity();
        homeHotelEntity.setId(0L);
        homeHotelEntity.setName("name");
        homeHotelEntity.setDescription("description");
        homeHotelEntity.setAddress("address");
        homeHotelEntity.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        final Page<HomeHotelEntity> homeHotelEntities = new PageImpl<>(List.of(homeHotelEntity));
        when(mockHomeHotelRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(homeHotelEntities);


        final HomeHotelResponse homeHotelResponse = new HomeHotelResponse();
        homeHotelResponse.setId(0L);
        homeHotelResponse.setName("name");
        homeHotelResponse.setAddress("address");
        homeHotelResponse.setPrice(0.0);
        homeHotelResponse.setViewed(0L);
        final List<HomeHotelResponse> homeHotelResponses = List.of(homeHotelResponse);
        final HomeHotelEntity homeHotelEntity1 = new HomeHotelEntity();
        homeHotelEntity1.setId(0L);
        homeHotelEntity1.setName("name");
        homeHotelEntity1.setDescription("description");
        homeHotelEntity1.setAddress("address");
        homeHotelEntity1.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        final List<HomeHotelEntity> entityList = List.of(homeHotelEntity1);
        when(mockHomeHotelMapper.mapToResponseList(entityList)).thenReturn(homeHotelResponses);

        final Page<HomeHotelResponse> result = homeHotelServiceUnderTest.getHomeHotels(PageRequest.of(0, 1), criteria);

    }

    @Test
    void testGetHomeHotels_HomeHotelRepositoryReturnsNoItems() {
        final HomeHotelCriteriaRequest criteria = new HomeHotelCriteriaRequest();
        criteria.setName("name");
        criteria.setType(HomeHotelType.HOME);

        when(mockHomeHotelRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        final HomeHotelResponse homeHotelResponse = new HomeHotelResponse();
        homeHotelResponse.setId(0L);
        homeHotelResponse.setName("name");
        homeHotelResponse.setAddress("address");
        homeHotelResponse.setPrice(0.0);
        homeHotelResponse.setViewed(0L);
        final List<HomeHotelResponse> homeHotelResponses = List.of(homeHotelResponse);

        when(mockHomeHotelMapper.mapToResponseList(anyList())).thenReturn(homeHotelResponses);

        final Page<HomeHotelResponse> result = homeHotelServiceUnderTest.getHomeHotels(PageRequest.of(0, 1), criteria);

        assertThat(result.getContent()).isEqualTo(homeHotelResponses);
    }

    @Test
    void testGetHomeHotels_HomeHotelMapperReturnsNoItems() {
        final HomeHotelCriteriaRequest criteria = new HomeHotelCriteriaRequest();
        criteria.setName("name");
        criteria.setType(HomeHotelType.HOME);

        final HomeHotelEntity homeHotelEntity = new HomeHotelEntity();
        homeHotelEntity.setId(0L);
        homeHotelEntity.setName("name");
        homeHotelEntity.setDescription("description");
        homeHotelEntity.setAddress("address");
        homeHotelEntity.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        final Page<HomeHotelEntity> homeHotelEntities = new PageImpl<>(List.of(homeHotelEntity));
        when(mockHomeHotelRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(homeHotelEntities);


        final HomeHotelEntity homeHotelEntity1 = new HomeHotelEntity();
        homeHotelEntity1.setId(0L);
        homeHotelEntity1.setName("name");
        homeHotelEntity1.setDescription("description");
        homeHotelEntity1.setAddress("address");
        homeHotelEntity1.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        final List<HomeHotelEntity> entityList = List.of(homeHotelEntity1);
        when(mockHomeHotelMapper.mapToResponseList(entityList)).thenReturn(Collections.emptyList());


        final Page<HomeHotelResponse> result = homeHotelServiceUnderTest.getHomeHotels(PageRequest.of(0, 1), criteria);


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
        homeHotelEntity1.setName("name");
        homeHotelEntity1.setDescription("description");
        homeHotelEntity1.setAddress("address");
        homeHotelEntity1.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        final Optional<HomeHotelEntity> homeHotelEntity = Optional.of(homeHotelEntity1);
        when(mockHomeHotelRepository.findById(0L)).thenReturn(homeHotelEntity);


        final HomeHotelEntity homeHotelEntity2 = new HomeHotelEntity();
        homeHotelEntity2.setId(0L);
        homeHotelEntity2.setName("name");
        homeHotelEntity2.setDescription("description");
        homeHotelEntity2.setAddress("address");
        homeHotelEntity2.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        final HomeHotelEntity entity = new HomeHotelEntity();
        entity.setId(0L);
        entity.setName("name");
        entity.setDescription("description");
        entity.setAddress("address");
        entity.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        when(mockHomeHotelRepository.save(entity)).thenReturn(homeHotelEntity2);


        final HomeHotelResponse homeHotelResponse = new HomeHotelResponse();
        homeHotelResponse.setId(0L);
        homeHotelResponse.setName("name");
        homeHotelResponse.setAddress("address");
        homeHotelResponse.setPrice(0.0);
        homeHotelResponse.setViewed(0L);
        final HomeHotelEntity entity1 = new HomeHotelEntity();
        entity1.setId(0L);
        entity1.setName("name");
        entity1.setDescription("description");
        entity1.setAddress("address");
        entity1.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        when(mockHomeHotelMapper.mapToResponse(entity1)).thenReturn(homeHotelResponse);


        final HomeHotelResponse result = homeHotelServiceUnderTest.updateHomeHotel(0L, homeHotelRequest);

        assertThat(result).isEqualTo(expectedResult);


        final HomeHotelEntity homeHotelEntity3 = new HomeHotelEntity();
        homeHotelEntity3.setId(0L);
        homeHotelEntity3.setName("name");
        homeHotelEntity3.setDescription("description");
        homeHotelEntity3.setAddress("address");
        homeHotelEntity3.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        verify(mockHomeHotelMapper).mapForUpdate(eq(homeHotelEntity3), any(HomeHotelRequest.class));
    }

    @Test
    void testUpdateHomeHotel_HomeHotelRepositoryFindByIdReturnsAbsent() {

        final HomeHotelRequest homeHotelRequest = HomeHotelRequest.builder()
                .imageIds(List.of(0L))
                .build();
        when(mockHomeHotelRepository.findById(0L)).thenReturn(Optional.empty());


        assertThatThrownBy(() -> homeHotelServiceUnderTest.updateHomeHotel(0L, homeHotelRequest))
                .isInstanceOf(HomeHotelNotFoundException.class);
    }

    @Test
    void testUpdateHomeHotel_HomeHotelRepositorySaveThrowsOptimisticLockingFailureException() {

        final HomeHotelRequest homeHotelRequest = HomeHotelRequest.builder()
                .imageIds(List.of(0L))
                .build();


        final HomeHotelEntity homeHotelEntity1 = new HomeHotelEntity();
        homeHotelEntity1.setId(0L);
        homeHotelEntity1.setName("name");
        homeHotelEntity1.setDescription("description");
        homeHotelEntity1.setAddress("address");
        homeHotelEntity1.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        final Optional<HomeHotelEntity> homeHotelEntity = Optional.of(homeHotelEntity1);
        when(mockHomeHotelRepository.findById(0L)).thenReturn(homeHotelEntity);


        final HomeHotelEntity entity = new HomeHotelEntity();
        entity.setId(0L);
        entity.setName("name");
        entity.setDescription("description");
        entity.setAddress("address");
        entity.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        when(mockHomeHotelRepository.save(entity)).thenThrow(OptimisticLockingFailureException.class);


        assertThatThrownBy(() -> homeHotelServiceUnderTest.updateHomeHotel(0L, homeHotelRequest))
                .isInstanceOf(OptimisticLockingFailureException.class);


        final HomeHotelEntity homeHotelEntity2 = new HomeHotelEntity();
        homeHotelEntity2.setId(0L);
        homeHotelEntity2.setName("name");
        homeHotelEntity2.setDescription("description");
        homeHotelEntity2.setAddress("address");
        homeHotelEntity2.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
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
        homeHotelEntity1.setName("name");
        homeHotelEntity1.setDescription("description");
        homeHotelEntity1.setAddress("address");
        homeHotelEntity1.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        final Optional<HomeHotelEntity> homeHotelEntity = Optional.of(homeHotelEntity1);
        when(mockHomeHotelRepository.findById(0L)).thenReturn(homeHotelEntity);


        homeHotelServiceUnderTest.rateHomeHotel(0L, 0);


        final HomeHotelEntity entity = new HomeHotelEntity();
        entity.setId(0L);
        entity.setName("name");
        entity.setDescription("description");
        entity.setAddress("address");
        entity.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        verify(mockRatingHelper).addRating(entity, 0);
    }

    @Test
    void testRateHomeHotel_HomeHotelRepositoryReturnsAbsent() {
        when(mockHomeHotelRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> homeHotelServiceUnderTest.rateHomeHotel(0L, 0))
                .isInstanceOf(HomeHotelNotFoundException.class);
    }

    @Test
    void testUpdateLikeCount() {

        final HomeHotelEntity homeHotelEntity1 = new HomeHotelEntity();
        homeHotelEntity1.setId(0L);
        homeHotelEntity1.setName("name");
        homeHotelEntity1.setDescription("description");
        homeHotelEntity1.setAddress("address");
        homeHotelEntity1.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        final Optional<HomeHotelEntity> homeHotelEntity = Optional.of(homeHotelEntity1);
        when(mockHomeHotelRepository.findById(0L)).thenReturn(homeHotelEntity);


        homeHotelServiceUnderTest.updateLikeCount(0L, false);


        final HomeHotelEntity entity = new HomeHotelEntity();
        entity.setId(0L);
        entity.setName("name");
        entity.setDescription("description");
        entity.setAddress("address");
        entity.setRooms(Set.of(RoomEntity.builder()
                .available(false)
                .deleted(false)
                .build()));
        verify(mockUpdateLike).updateLikeCount(entity, false);
    }

    @Test
    void testUpdateLikeCount_HomeHotelRepositoryReturnsAbsent() {

        when(mockHomeHotelRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> homeHotelServiceUnderTest.updateLikeCount(0L, false))
                .isInstanceOf(HomeHotelNotFoundException.class);
    }
}
