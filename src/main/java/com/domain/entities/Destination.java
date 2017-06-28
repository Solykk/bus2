package com.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by burbulet on 4/30/17.
 */

@Entity
@Getter
@NoArgsConstructor
public class Destination {
    @Id
    @GeneratedValue(generator = "destinations_id_gen")
    @GenericGenerator(
            name = "destinations_id_gen",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "destinations_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "startCityId")
    private CityEntity startCity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "endCityId")
    private CityEntity endCity;

    private BigDecimal priceOneWay;

    private BigDecimal priceReturn;

    public Destination(CityEntity startCity, CityEntity endCity, BigDecimal priceOneWay, BigDecimal priceReturn) {
        this.startCity = startCity;
        this.endCity = endCity;
        this.priceOneWay = priceOneWay;
        this.priceReturn = priceReturn;
    }
}
