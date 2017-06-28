package com.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Dmitriy Lyashenko
 */

@Data
@NoArgsConstructor
@Entity
@Table(name = "station")
public class StationEntity {

    @Id
    @GeneratedValue(generator = "stations_id_gen")
    @GenericGenerator(
            name = "stations_id_gen",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "stations_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToOne
    @JoinColumn(name = "coordinate_id")
    private CoordinateEntity coordinate;

    public StationEntity(String address, CoordinateEntity coordinate) {
        this.address = address;
        this.coordinate = coordinate;
    }
}
