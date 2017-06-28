/**
 * Created by zeruch on 07/04/17.
 */
package com.domain.dtos;

import com.domain.entities.PassengerEntity;
import com.domain.enums.PassengerGroup;
import lombok.Data;

@Data
public class PassengerDto {

    private Long id;
    private String phone;
    private String name;
    private String surname;
    private String details;
    private PassengerGroup passengerGroup;

    public PassengerDto() {
    }

    public PassengerDto(PassengerEntity passengerEntity) {
        this(new PassengerDtoBuilder(passengerEntity.getId())
                .name(passengerEntity.getName())
                .surname(passengerEntity.getSurname())
                .phone(passengerEntity.getPhone())
                .details(passengerEntity.getDetails())
                .passengerGroup(passengerEntity.getPassengerGroup()));
    }

    private PassengerDto(PassengerDtoBuilder builder) {
        this.id = builder.id;
        this.phone = builder.phone;
        this.name = builder.name;
        this.surname = builder.surname;
        this.details = builder.details;
        this.passengerGroup = builder.passengerGroup;
    }

    public static class PassengerDtoBuilder {

        private Long id;
        private String phone;
        private String name;
        private String surname;
        private String details;
        private PassengerGroup passengerGroup;

        public PassengerDtoBuilder(Long id) {
            this.id = id;
        }

        public PassengerDtoBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public PassengerDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PassengerDtoBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public PassengerDtoBuilder details(String details) {
            this.details = details;
            return this;
        }

        public PassengerDtoBuilder passengerGroup(PassengerGroup passengerGroup) {
            this.passengerGroup = passengerGroup;
            return this;
        }

        public PassengerDto build() {
            return new PassengerDto(this);
        }
    }
}
