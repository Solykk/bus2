package com.domain.request;

import lombok.Getter;
import lombok.NonNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by burbulet on 4/24/17.
 */
@Getter
public class BookTicketsInternalRequest {
    private String email;
    private String phone;
    private String name;
    private String surname;
    private Integer passengers;
    private String startCity;
    private String endCity;
    private String startDate;
    private String returnDate;
    private String message;
    private String id;
    private String createDate;
    private BigDecimal rate;
    private BigDecimal price;

    public BookTicketsInternalRequest(String id,
                                      String email,
                                      String phone,
                                      String name,
                                      String surname,
                                      String startCity,
                                      String endCity,
                                      String startDate,
                                      String  returnDate,
                                      String message,
                                      Integer passengers,
                                      String createDate,
                                      BigDecimal rate,
                                      BigDecimal price)
    {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.startCity = startCity;
        this.endCity = endCity;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.passengers = passengers;
        this.message = message;
        this.createDate = createDate;
        this.rate = rate;
        this.price = price;
    }
}
