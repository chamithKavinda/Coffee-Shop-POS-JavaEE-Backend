package org.example.coffeeshopposjavaeebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private String order_id;
    private LocalDateTime dateAndTime;
    private String custContact;
}
