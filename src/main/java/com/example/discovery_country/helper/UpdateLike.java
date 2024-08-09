package com.example.discovery_country.helper;

import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.entity.HomeHotelEntity;
import com.example.discovery_country.dao.entity.RestaurantEntity;
import com.example.discovery_country.dao.entity.ScenicSpotEntity;
import com.example.discovery_country.dao.repository.ActivityRepository;
import com.example.discovery_country.dao.repository.HomeHotelRepository;
import com.example.discovery_country.dao.repository.RestaurantRepository;
import com.example.discovery_country.dao.repository.ScenicSpotRepository;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateLike {

    private final RestaurantRepository restaurantRepository;
    private final ScenicSpotRepository scenicSpotRepository;
    private final ActivityRepository activityRepository;
    private final HomeHotelRepository homeHotelRepository;
    public <T> void updateLikeCount(T entity, boolean increment) {
        if (entity instanceof ActivityEntity) {
            ActivityEntity activity = (ActivityEntity) entity;
            activity.setLikeCount(increment ? activity.getLikeCount() + 1 : activity.getLikeCount() - 1);
            activityRepository.save(activity);

        } else if (entity instanceof RestaurantEntity) {
            RestaurantEntity restaurant = (RestaurantEntity) entity;
            restaurant.setLikeCount(increment ? restaurant.getLikeCount() + 1 : restaurant.getLikeCount() - 1);
            restaurantRepository.save(restaurant);

        } else if (entity instanceof HomeHotelEntity) {
            HomeHotelEntity homeHotel = (HomeHotelEntity) entity;
            homeHotel.setLikeCount(increment ? homeHotel.getLikeCount() + 1 : homeHotel.getLikeCount() - 1);
            homeHotelRepository.save(homeHotel);

        } else if (entity instanceof ScenicSpotEntity) {
            ScenicSpotEntity scenicSpot = (ScenicSpotEntity) entity;
            scenicSpot.setLikeCount(increment ? scenicSpot.getLikeCount() + 1 : scenicSpot.getLikeCount() - 1);
            scenicSpotRepository.save(scenicSpot);
        }
    }
}
