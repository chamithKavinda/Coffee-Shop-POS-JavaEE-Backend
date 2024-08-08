package org.example.coffeeshopposjavaeebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private String order_id;
    private LocalDateTime dateAndTime;
    private String contact;

    public Orders(String orderId, String dateAndTime, String contact) {
    }
}
