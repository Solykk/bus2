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
@Table(name = "coordinate")
public class CoordinateEntity {

    @Id
    @GeneratedValue(generator = "coordinates_id_gen")
    @GenericGenerator(
            name = "coordinates_id_gen",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "coordinates_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "longitude", nullable = false)
    private Float longitude;

    @Column(name = "latitude", nullable = false)
    private Float latitude;

    public CoordinateEntity(Float longitude, Float latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
