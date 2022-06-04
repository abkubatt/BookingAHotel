package com.example.bookinghotel.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    final String message;

    public static Message of(String message){
        return new Message(message);
    }
}
