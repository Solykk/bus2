package com.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by burbulet on 4/26/17.
 */
@Getter
@NoArgsConstructor
@Entity
public class CityLocale {
    @Id
    @GeneratedValue(generator = "city_locales_id_gen")
    @GenericGenerator(
            name = "city_locales_id_gen",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "city_locales_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "localeId")
    private Locale locale;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityId")
    private CityEntity city;

    public CityLocale(CityEntity city, String code, Locale locale, String name) {
        this.city = city;
        this.code = code;
        this.locale = locale;
        this.name = name;
    }
}
