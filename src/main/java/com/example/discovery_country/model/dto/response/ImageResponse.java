package com.example.discovery_country.model.dto.response;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {
    List<Integer> ids;
    String url;
    String name;

}