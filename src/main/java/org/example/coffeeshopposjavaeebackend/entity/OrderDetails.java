package org.example.coffeeshopposjavaeebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    private String order_id;
    private String pro_id;
    private String qty;
    private String unitPrice;
}
