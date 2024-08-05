package org.example.coffeeshopposjavaeebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private String order_id;
    private String pro_id;
    private String product_qty;
    private String unit_price;
}
