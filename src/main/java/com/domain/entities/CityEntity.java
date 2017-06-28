/**
 * Created by zeruch on 30/03/17.
 */
package com.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "city")
public class CityEntity {

    @Id
    @GeneratedValue(generator = "cities_id_gen")
    @GenericGenerator(
            name = "cities_id_gen",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "cities_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
    private Set<CityLocale> locales;

    @ManyToOne
    private CountryEntity country;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = StationEntity.class)
    private List<StationEntity> stations = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "startCity")
    private List<Destination> destinations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "endCity")
    private List<Destination> origins;

    private String imageUrl;

    public CityEntity(String code, String imageUrl) {
        this.code = code;
        this.imageUrl = imageUrl;
    }
}
