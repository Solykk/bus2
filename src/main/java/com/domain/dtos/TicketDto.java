/**
 * Created by zeruch on 07/04/17.
 */
package com.domain.dtos;

import lombok.Data;
import org.joda.time.DateTime;

import java.util.List;

@Data
public class TicketDto {

    private Long id;
    private List<String> seats;
    private BusDto bus;
    private Long departureTime;
    private CityDto startCity;
    private CityDto endCity;
    private Integer price;
    private PassengerDto passenger;


    public TicketDto() {
    }

    private TicketDto(TicketDtoBuilder builder) {
        this.id = builder.id;
        this.seats = builder.seats;
        this.passenger = builder.passenger;
        this.startCity = builder.startCity;
        this.endCity = builder.endCity;
        this.bus = builder.bus;
        this.price = builder.price;
        this.departureTime = builder.departureTime;
    }

    public static class TicketDtoBuilder {

        private Long id;
        private List<String> seats;
        private PassengerDto passenger;
        private CityDto startCity;
        private CityDto endCity;
        private BusDto bus;
        private Integer price;
        private Long departureTime;

        public TicketDtoBuilder(Long id) {
            this.id = id;
        }

        public TicketDtoBuilder seats(List<String> seats) {
            this.seats = seats;
            return this;
        }

        public TicketDtoBuilder startCity(CityDto city) {
            this.startCity = city;
            return this;
        }

        public TicketDtoBuilder endCity(CityDto city) {
            this.endCity = city;
            return this;
        }

        public TicketDtoBuilder passenger(PassengerDto passenger) {
            this.passenger = passenger;
            return this;
        }

        public TicketDtoBuilder bus(BusDto bus) {
            this.bus = bus;
            return this;
        }

        public TicketDtoBuilder price(Integer price) {
            this.price = price;
            return this;
        }

        public TicketDtoBuilder departureTime(DateTime departureTime) {
            this.departureTime = departureTime.getMillis() / 1000;
            return this;
        }

        public TicketDto build() {
            return new TicketDto(this);
        }
    }
}
