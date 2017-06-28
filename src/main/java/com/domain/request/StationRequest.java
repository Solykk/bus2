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
public class StationRequest {

    @NotEmpty
    private String address;

    @NotEmpty
    private CoordinateRequest coordinate;

    public StationRequest(@JsonProperty("address") String address,
                          @JsonProperty("coordinate") CoordinateRequest coordinate) {
        this.address = address;
        this.coordinate = coordinate;
    }
}
