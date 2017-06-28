/**
 * Created by zeruch on 07/04/17.
 */
package com.domain.request;

import com.domain.enums.PassengerGroup;
import lombok.Data;

@Data
public class AddPassengerRequest {

    private String phone;
    private String name;
    private String surname;
    private String details;
    private PassengerGroup passengerGroup;
}
