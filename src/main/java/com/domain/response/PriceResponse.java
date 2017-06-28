package com.domain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by burbulet on 5/1/17.
 */
@Getter
@NoArgsConstructor
public class PriceResponse implements Serializable {
    private BigDecimal price;
    private BigDecimal rate;

    public PriceResponse(BigDecimal rate, BigDecimal price) {
        this.rate = rate;
        this.price = price;
    }
}
