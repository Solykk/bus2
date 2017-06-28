/**
 * Created by zeruch on 07/04/17.
 */
package com.domain.dtos;

import com.domain.entities.BusEntity;
import lombok.Data;

import java.util.List;

@Data
public class BusDto {

    private Long id;
    private List<String> photos;
    private String description;

    public BusDto() {
    }

    public BusDto(BusEntity busEntity) {
        this(new BusDtoBuilder(busEntity.getId())
                .photos(busEntity.getPhotos())
                .description(busEntity.getDescription()));
    }

    private BusDto(BusDtoBuilder builder) {
        this.id = builder.id;
        this.photos = builder.photos;
        this.description = builder.description;
    }

    public static class BusDtoBuilder {

        private Long id;
        private List<String> photos;
        private String description;

        public BusDtoBuilder(Long id) {
            this.id = id;
        }

        public BusDtoBuilder photos(List<String> photos) {
            this.photos = photos;
            return this;
        }

        public BusDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public BusDto build() {
            return new BusDto(this);
        }
    }
}
