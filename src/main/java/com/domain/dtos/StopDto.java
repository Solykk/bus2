package com.domain.dtos;

import com.domain.entities.StopEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Lyashenko
 */

@Data
@NoArgsConstructor
public class StopDto {

    private Long id;
    private CityDto city;
    private StationDto station;

    public List<StopDto> getListDTO (List<StopEntity> listStopEntity) {
        List<StopDto> dtoList = new ArrayList<>();
        for (StopEntity entity: listStopEntity){
            dtoList.add(new StopDto(entity));
        }

        return dtoList;
    }

    public StopDto(StopEntity stopEntity) {
        this(new StopDto.StopDtoBuilder(stopEntity.getId())
                .city(new CityDto(stopEntity.getCity()))
                .station(new StationDto(stopEntity.getStation())));
    }

    private StopDto(StopDto.StopDtoBuilder builder) {
        this.id = builder.id;
        this.city = builder.city;
        this.station = builder.station;
    }

    public static class StopDtoBuilder {

        private Long id;
        private CityDto city;
        private StationDto station;

        public StopDtoBuilder(Long id) {
            this.id = id;
        }

        public StopDto.StopDtoBuilder city(CityDto city) {
            this.city = city;
            return this;
        }

        public StopDto.StopDtoBuilder station(StationDto station) {
            this.station = station;
            return this;
        }

        public StopDto build() {
            return new StopDto(this);
        }
    }
}
