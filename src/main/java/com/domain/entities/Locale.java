package com.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by burbulet on 4/26/17.
 */
@Getter
@NoArgsConstructor
@Entity
public class Locale {

    @Id
    @GeneratedValue(generator = "locales_id_gen")
    @GenericGenerator(
            name = "locales_id_gen",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "locales_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "locale")
    Set<CityLocale> cities;

    public Locale(String code) {
        this.code = code;
    }
}
