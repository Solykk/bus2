/**
 * Created by zeruch on 07/04/17.
 */
package com.domain.dtos;

import com.domain.entities.RouteEntity;
import lombok.Data;

import java.util.List;

@Data
public class RouteDto {

    private Long id;
    private Integer number;
    private List<StopDto> stops;

    public RouteDto(RouteEntity routeEntity) {
        this(new RouteDto.RouteDtoBuilder(routeEntity.getId())
                .number(routeEntity.getNumber())
                .stops(new StopDto().getListDTO(routeEntity.getStops())));
    }

    private RouteDto(RouteDto.RouteDtoBuilder builder) {
        this.id = builder.id;
        this.number = builder.number;
        this.stops = builder.stops;
    }

    public static class RouteDtoBuilder {

        private Long id;
        private Integer number;
        private List<StopDto> stops;

        public RouteDtoBuilder(Long id) {
            this.id = id;
        }

        public RouteDto.RouteDtoBuilder number(Integer number) {
            this.number = number;
            return this;
        }

        public RouteDto.RouteDtoBuilder stops(List<StopDto> stops) {
            this.stops = stops;
            return this;
        }

        public RouteDto build() {
            return new RouteDto(this);
        }
    }
}
