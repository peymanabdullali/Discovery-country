package com.example.discovery_country.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegionResponseForRelations {
    String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegionResponseForRelations that = (RegionResponseForRelations) o;
        return
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash( name);
    }

}
