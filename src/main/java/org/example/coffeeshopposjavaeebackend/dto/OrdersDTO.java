package org.example.coffeeshopposjavaeebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private String order_id;
    private String pro_id;
    private String product_qty;
    private String unit_price;
}
