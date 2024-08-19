package com.example.discovery_country.helper;

import com.example.discovery_country.dao.entity.ActivityEntity;
import com.example.discovery_country.dao.entity.RestaurantEntity;
import com.example.discovery_country.dao.entity.HomeHotelEntity;
import com.example.discovery_country.dao.entity.ScenicSpotEntity;
import com.example.discovery_country.dao.repository.ActivityRepository;
import com.example.discovery_country.dao.repository.HomeHotelRepository;
import com.example.discovery_country.dao.repository.RestaurantRepository;
import com.example.discovery_country.dao.repository.ScenicSpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class RatingHelper {
    private final RestaurantRepository restaurantRepository;
    private final ScenicSpotRepository scenicSpotRepository;
    private final ActivityRepository activityRepository;
    private final HomeHotelRepository homeHotelRepository;
    public <T> void addRating(T entity, int stars) {
        if (entity instanceof ActivityEntity activity) {
            int newRatingCount = activity.getRatingCount() + 1;
            double newAverageRating = (activity.getAverageRating() * activity.getRatingCount() + stars) / newRatingCount;
            activity.setRatingCount(newRatingCount);
            activity.setAverageRating(newAverageRating);
             activityRepository.save(activity);


        } else if (entity instanceof RestaurantEntity restaurant) {
            int newRatingCount = restaurant.getRatingCount() + 1;
            double newAverageRating = (restaurant.getAverageRating() * restaurant.getRatingCount() + stars) / newRatingCount;
            restaurant.setRatingCount(newRatingCount);
            restaurant.setAverageRating(newAverageRating);
            restaurantRepository.save(restaurant);


        } else if (entity instanceof HomeHotelEntity homeHotel) {
            int newRatingCount = homeHotel.getRatingCount() + 1;
            double newAverageRating = (homeHotel.getAverageRating() * homeHotel.getRatingCount() + stars) / newRatingCount;
            homeHotel.setRatingCount(newRatingCount);
            homeHotel.setAverageRating(newAverageRating);
            homeHotelRepository.save(homeHotel);

        } else if (entity instanceof ScenicSpotEntity) {
            ScenicSpotEntity scenicSpot = (ScenicSpotEntity) entity;
            long newRatingCount = scenicSpot.getRatingCount() + 1;
            double newAverageRating = (scenicSpot.getAverageRating() * scenicSpot.getRatingCount() + stars) / newRatingCount;
            scenicSpot.setRatingCount(newRatingCount);
            scenicSpot.setAverageRating(newAverageRating);
            scenicSpotRepository.save(scenicSpot);
        }
    }
}
