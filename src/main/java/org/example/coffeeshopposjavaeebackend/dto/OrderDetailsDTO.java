package org.example.coffeeshopposjavaeebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {
    private String order_id;
    private String pro_id;
    private String qty;
    private String unitPrice;
}
