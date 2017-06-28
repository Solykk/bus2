package com.domain.dtos;

import com.domain.entities.CoordinateEntity;

import lombok.Data;

/**
 * @author Dmitriy Lyashenko
 */

@Data
public class CoordinateDto {

    private Long id;
    private Float longitude;
    private Float latitude;

    public CoordinateDto(CoordinateEntity coordinateEntity) {
        this(new CoordinateDto.CoordinateDtoBuilder(coordinateEntity.getId())
                .longitude(coordinateEntity.getLongitude())
                .latitude(coordinateEntity.getLatitude()));
    }

    private CoordinateDto(CoordinateDto.CoordinateDtoBuilder builder) {
        this.id = builder.id;
        this.longitude = builder.longitude;
        this.latitude = builder.latitude;
    }

    public static class CoordinateDtoBuilder {

        private Long id;
        private Float longitude;
        private Float latitude;

        public CoordinateDtoBuilder(Long id) {
            this.id = id;
        }

        public CoordinateDto.CoordinateDtoBuilder longitude(Float longitude) {
            this.longitude = longitude;
            return this;
        }

        public CoordinateDto.CoordinateDtoBuilder latitude(Float latitude) {
            this.latitude = latitude;
            return this;
        }

        public CoordinateDto build() {
            return new CoordinateDto(this);
        }
    }
}
