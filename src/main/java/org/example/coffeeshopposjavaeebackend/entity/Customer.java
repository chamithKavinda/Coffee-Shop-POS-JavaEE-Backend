package org.example.coffeeshopposjavaeebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String custId;
    private String custName;
    private String custAddress;
    private String custContact;
}
