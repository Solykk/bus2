package com.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;

/**
 * @author Dmitriy Lyashenko
 */

@Getter
@ToString
public class RouteRequest {

    @NotEmpty
    private Integer number;

    @NotEmpty
    private ArrayList<StopRequest> stops;

    public RouteRequest(@JsonProperty("number") Integer number,
                        @JsonProperty("stops") ArrayList<StopRequest> stops) {
        this.number = number;
        this.stops = stops;
    }
}
