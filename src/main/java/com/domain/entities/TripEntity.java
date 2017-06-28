/**
 * Created by zeruch on 30/03/17.
 */
package com.domain.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

import javax.persistence.*;

@Data
@Entity
@Table(name = "trip")
public class TripEntity {
    @Id
    @GeneratedValue(generator = "trips_id_gen")
    @GenericGenerator(
            name = "trips_id_gen",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "trips_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    private RouteEntity route;

    @OneToOne
    private BusEntity bus;

    @OneToOne
    private DriverEntity driver;

    private DateTime timeStart;
    private DateTime timeEnd;

    @Column(nullable = false)
    private Float priceCoefficient;

    private String description;
}
