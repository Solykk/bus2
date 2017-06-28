/**
 * Created by zeruch on 07/04/17.
 */
package com.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.domain.entities.CityEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CityDto {

    private String name;
    private Long id;
    private String imageUrl;
    @JsonIgnore
    private String code;

    public CityDto(CityEntity cityEntity) {
        this(new CityDto.CityDtoBuilder(cityEntity.getId())
                .name(cityEntity.getCode())
                .image(cityEntity.getImageUrl()));
    }

    private CityDto(CityDtoBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.code = builder.code;
        this.imageUrl = builder.imageUrl;
    }

    public static class CityDtoBuilder {
        private String name;
        private Long id;
        private String code;
        private String imageUrl;

        public CityDtoBuilder(Long id) {
            this.id = id;
        }

        public CityDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CityDtoBuilder code(String code) {
            this.code = code;
            return this;
        }

        public CityDtoBuilder image(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public CityDto build() {
            return new CityDto(this);
        }


    }
}
