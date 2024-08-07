    package com.example.discovery_country.helper;

    import com.example.discovery_country.dao.entity.ActivityEntity;
    import com.example.discovery_country.dao.entity.RestaurantEntity;
    import com.example.discovery_country.dao.entity.ScenicSpotEntity;
    import com.example.discovery_country.dao.repository.ActivityRepository;
    import com.example.discovery_country.dao.repository.RestaurantRepository;
    import com.example.discovery_country.dao.repository.ScenicSpotRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Component;

    @Component
    @RequiredArgsConstructor
    public class IncreaseViewCount {
        private final RestaurantRepository restaurantRepository;
        private final ScenicSpotRepository scenicSpotRepository;
        private final ActivityRepository activityRepository;
    //    private final HomeHotelRepository homeHotelRepository;

        public <T> void updateViewCount(T entity) {
            if (entity instanceof ScenicSpotEntity) {
                ((ScenicSpotEntity) entity).setViewed(((ScenicSpotEntity) entity).getViewed() + 1);
                scenicSpotRepository.save((ScenicSpotEntity) entity);

            } else if (entity instanceof RestaurantRepository) {
                ((RestaurantEntity) entity).setViewed(((RestaurantEntity) entity).getViewed() + 1);
                restaurantRepository.save((RestaurantEntity) entity);
            } else if (entity instanceof ActivityEntity) {
                ((ActivityEntity) entity).setViewed(((ActivityEntity) entity).getViewed() + 1);
                activityRepository.save((ActivityEntity) entity);
            }


        }

    }
