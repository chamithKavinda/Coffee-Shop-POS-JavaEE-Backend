package org.example.coffeeshopposjavaeebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String pro_id;
    private String pro_name;
    private String price;
    private String category;
    private String quantity;
}
