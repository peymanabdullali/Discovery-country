package com.example.discovery_country.controller;

import com.example.discovery_country.model.dto.criteria.ActivityCategoryCriteriaRequest;
import com.example.discovery_country.model.dto.request.ActivityCategoryRequest;
import com.example.discovery_country.model.dto.response.ActivityCategoryResponse;
import com.example.discovery_country.service.ActivityCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ActivityCategoryController.class)
class ActivityCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActivityCategoryService mockActivityCategoryService;

    @Test
    void testCreateActivityCategory() throws Exception {

        final ActivityCategoryResponse activityCategoryResponse = ActivityCategoryResponse.builder().build();
        when(mockActivityCategoryService.create(ActivityCategoryRequest.builder().build()))
                .thenReturn(activityCategoryResponse);


        mockMvc.perform(post("/activity-categories")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetActivityCategories() throws Exception {

        final Page<ActivityCategoryResponse> activityCategoryResponses = new PageImpl<>(
                List.of(ActivityCategoryResponse.builder().build()));
        when(mockActivityCategoryService.getActivityCategories(eq(ActivityCategoryCriteriaRequest.builder().build()),
                any(Pageable.class))).thenReturn(activityCategoryResponses);

        mockMvc.perform(get("/activity-categories")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testGetActivityCategories_ActivityCategoryServiceReturnsNoItems() throws Exception {

        when(mockActivityCategoryService.getActivityCategories(eq(ActivityCategoryCriteriaRequest.builder().build()),
                any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));


        mockMvc.perform(get("/activity-categories")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]", true));
    }

    @Test
    void testUpdateActivityCategory() throws Exception {

        final ActivityCategoryResponse activityCategoryResponse = ActivityCategoryResponse.builder().build();
        when(mockActivityCategoryService.update(0L, ActivityCategoryRequest.builder().build()))
                .thenReturn(activityCategoryResponse);


        mockMvc.perform(put("/activity-categories/{id}", 0)
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    void testDeleteActivityCategory() throws Exception {

        mockMvc.perform(delete("/activity-categories/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        verify(mockActivityCategoryService).softDelete(0L);
    }
}
