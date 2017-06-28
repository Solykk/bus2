/**
 * Created by zeruch on 01/04/17.
 */
package com.domain.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "driver")
public class DriverEntity {
    @Id
    @GeneratedValue(generator = "drivers_id_gen")
    @GenericGenerator(
            name = "drivers_id_gen",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "drivers_id_seq"),
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
}
