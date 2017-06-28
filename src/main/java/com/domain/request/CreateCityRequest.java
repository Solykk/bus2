package com.domain.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by burbulet on 4/27/17.
 */
@Getter
@NoArgsConstructor
public class CreateCityRequest implements Serializable {
    List<CreateCityLocaleRequest> locales;
    String imageUrl;
    String code;

    public CreateCityRequest(String code, String imageUrl, List<CreateCityLocaleRequest> locales) {
        this.locales = locales;
        this.imageUrl = imageUrl;
        this.code = code;
    }
}
