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
@Table(name = "stop")
public class StopEntity {

    @Id
    @GeneratedValue(generator = "stops_id_gen")
    @GenericGenerator(
            name = "stops_id_gen",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "stops_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    private CityEntity city;

    @OneToOne
    @JoinColumn(name = "station_id")
    private StationEntity station;

    public StopEntity(CityEntity city, StationEntity station) {
        this.city = city;
        this.station = station;
    }

    @Override
    public String toString() {
        return "StopEntity{" +
                "id=" + id +
                ", city=" + (city != null ? city.getCode(): "null") +
                ", station=" + station +
                '}';
    }
}
