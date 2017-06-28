package com.domain.dtos;

import com.domain.entities.StationEntity;

import lombok.Data;

/**
 * @author Dmitriy Lyashenko
 */

@Data
public class StationDto {

    private Long id;
    private String address;
    private CoordinateDto coordinate;

    public StationDto(StationEntity stationEntity) {
        this(new StationDto.StationDtoBuilder(stationEntity.getId())
                .address(stationEntity.getAddress())
                .coordinate(new CoordinateDto(stationEntity.getCoordinate())));
    }

    private StationDto(StationDto.StationDtoBuilder builder) {
        this.id = builder.id;
        this.address = builder.address;
        this.coordinate = builder.coordinate;
    }

    public static class StationDtoBuilder {

        private Long id;
        private String address;
        private CoordinateDto coordinate;

        public StationDtoBuilder(Long id) {
            this.id = id;
        }

        public StationDto.StationDtoBuilder address(String address) {
            this.address = address;
            return this;
        }

        public StationDto.StationDtoBuilder coordinate(CoordinateDto coordinate) {
            this.coordinate = coordinate;
            return this;
        }

        public StationDto build() {
            return new StationDto(this);
        }
    }
}
