/**
 * Created by zeruch on 07/04/17.
 */
package com.domain.dtos;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class TripDto {

    private Long id;
    private RouteDto route;
    private BusDto bus;
    private DriverDto driver;
    private Long timeStart;
    private Long timeEnd;
    private Float priceCoefficient;
    private String description;

    public TripDto() {
    }

    private TripDto(TripDtoBuilder builder) {
        this.id = builder.id;
        this.route = builder.route;
        this.bus = builder.bus;
        this.driver = builder.driver;
        this.timeStart = builder.timeStart;
        this.timeEnd = builder.timeEnd;
        this.priceCoefficient = builder.priceCoefficient;
        this.description = builder.description;
    }

    public static class TripDtoBuilder {

        private Long id;
        private RouteDto route;
        private BusDto bus;
        private DriverDto driver;
        private Long timeStart;
        private Long timeEnd;
        private Float priceCoefficient;
        private String description;

        public TripDtoBuilder(Long id) {
            this.id = id;
        }

        public TripDtoBuilder route(RouteDto route) {
            this.route = route;
            return this;
        }

        public TripDtoBuilder bus(BusDto bus) {
            this.bus = bus;
            return this;
        }

        public TripDtoBuilder driver(DriverDto driver) {
            this.driver = driver;
            return this;
        }

        public TripDtoBuilder timeStart(DateTime time) {
            this.timeStart = time.getMillis() / 1000;
            return this;
        }

        public TripDtoBuilder timeEnd(DateTime time) {
            this.timeEnd = time.getMillis() / 1000;
            return this;
        }

        public TripDtoBuilder priceCoefficient(Float priceCoefficient) {
            this.priceCoefficient = priceCoefficient;
            return this;
        }

        public TripDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TripDto build() {
            return new TripDto(this);
        }
    }
}
