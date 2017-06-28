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
public class StopRequest {

    @NotEmpty
    private String city;

    @NotEmpty
    private StationRequest station;

    public StopRequest(@JsonProperty("city") String city,
                       @JsonProperty("station") StationRequest station) {
        this.city = city;
        this.station = station;
    }
}
