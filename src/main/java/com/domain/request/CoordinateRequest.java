package com.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Dmitriy Lyashenko
 */

@Getter
@ToString
public class CoordinateRequest {

    @NotEmpty
    private Float longitude;

    @NotEmpty
    private Float latitude;

    public CoordinateRequest(@JsonProperty("longitude") Float longitude,
                             @JsonProperty("latitude") Float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
