/**
 * Created by zeruch on 30/03/17.
 */
package com.domain.entities;

import lombok.Data;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "route")
public class RouteEntity {
    @Id
    @GeneratedValue(generator = "routes_id_gen")
    @GenericGenerator(
            name = "routes_id_gen",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "routes_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column (name = "number", nullable = false)
    private Integer number;

    @ManyToMany
    private List<StopEntity> stops;

    public RouteEntity(Integer number, List<StopEntity> stops) {
        this.number = number;
        this.stops = stops;
    }
}
