package com.domain.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by burbulet on 4/27/17.
 */

@Getter
@NoArgsConstructor
public class CreateCityLocaleRequest implements Serializable {
    private String localeCode;
    private String name;

    public CreateCityLocaleRequest(String localeCode, String name) {
        this.localeCode = localeCode;
        this.name = name;
    }
}
