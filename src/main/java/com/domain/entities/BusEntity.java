/**
 * Created by zeruch on 30/03/17.
 */
package com.domain.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "bus")
public class BusEntity {
    @Id
    @GeneratedValue(generator = "buses_id_gen")
    @GenericGenerator(
            name = "buses_id_gen",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "buses_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ElementCollection
    private List<String> photos;

    private String description;
}
