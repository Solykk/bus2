package com.domain.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by burbulet on 4/24/17.
 */
@NoArgsConstructor
@Getter
public class BookTicketsRequest implements Serializable{
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp = "^[\\+][0-9]{2,3}[0-9]{2,3}[0-9]{7,8}$", message = "phone number must be in format +380671112233")
    private String phone;

    @NotEmpty
    @Length(min = 2, max = 30, message = "name must have between 2 and 30 symbols")
    private String name;

    @NotEmpty
    @Length(min = 2, max = 30, message = "surname must have between 2 and 30 symbols")
    private String surname;

    @NonNull
    @Min(1)
    @Max(4)
    private Integer passengers;

    @NonNull
    private Long startCityId;

    @NonNull
    private Long endCityId;

    @NotEmpty
    @Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])\\-(0?[1-9]|1[012])\\-(201[7-9]|202[0-9])$", message = "date must be in format 01-01-2017")
    private String startDate;

    @Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])\\-(0?[1-9]|1[012])\\-(201[7-9]|202[0-9])$", message = "date must be in format 01-01-2017")
    private String returnDate;

    @Length(max = 140, message = "message can not be more that 140 symbols")
    private String message;
}
