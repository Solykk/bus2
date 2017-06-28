/**
 * Created by zeruch on 07/04/17.
 */
package com.domain.dtos;

import com.domain.entities.DriverEntity;
import lombok.Data;

@Data
public class DriverDto {

    private Long id;
    private String phone;
    private String name;
    private String surname;

    public DriverDto() {
    }

    public DriverDto(DriverEntity driverEntity) {
        this(new DriverDtoBuilder(driverEntity.getId())
                .phone(driverEntity.getPhone())
                .name(driverEntity.getName())
                .surname(driverEntity.getSurname()));
    }

    private DriverDto(DriverDtoBuilder builder) {
        this.id = builder.id;
        this.phone = builder.phone;
        this.name = builder.name;
        this.surname = builder.surname;
    }

    public static class DriverDtoBuilder {
        private Long id;
        private String phone;
        private String name;
        private String surname;

        public DriverDtoBuilder(Long id) {
            this.id = id;
        }

        public DriverDtoBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public DriverDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DriverDtoBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public DriverDto build() {
            return new DriverDto(this);
        }
    }
}
