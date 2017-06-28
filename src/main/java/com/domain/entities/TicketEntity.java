/**
 * Created by zeruch on 30/03/17.
 */
package com.domain.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ticket")
public class TicketEntity {
    @Id
    @GeneratedValue(generator = "tickets_id_gen")
    @GenericGenerator(
            name = "tickets_id_gen",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "tickets_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ElementCollection
    private List<String> seats;

    @OneToOne
    private CityEntity startCity;

    @OneToOne
    private CityEntity endCity;

    @ManyToOne
    private BusEntity bus;

    @OneToOne
    private PassengerEntity passenger;

    private Integer price;

    private DateTime departureTime;
}
