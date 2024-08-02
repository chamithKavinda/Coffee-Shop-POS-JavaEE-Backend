package org.example.coffeeshopposjavaeebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO{
    private String custId;
    private String custName;
    private String custAddress;
    private String custContact;

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "custId='" + custId + '\'' +
                ", custName='" + custName + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custContact='" + custContact + '\'' +
                '}';
    }
}
