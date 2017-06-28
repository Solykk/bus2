/**
 * Created by zeruch on 30/03/17.
 */
package com.domain.entities;

import com.domain.request.AddPassengerRequest;
import com.domain.enums.PassengerGroup;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "passenger")
public class PassengerEntity {
    @Id
    @GeneratedValue(generator = "passengers_id_gen")
    @GenericGenerator(
            name = "passengers_id_gen",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "passengers_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String phone;

    private String name;

    private String surname;

    private String details;

    private PassengerGroup passengerGroup;

    public PassengerEntity() {
    }

    public PassengerEntity(final AddPassengerRequest addPassengerRequest) {
        this.phone = addPassengerRequest.getPhone();
        this.name = addPassengerRequest.getName();
        this.surname = addPassengerRequest.getSurname();
        this.details = addPassengerRequest.getDetails();
        this.passengerGroup = addPassengerRequest.getPassengerGroup();
    }
}