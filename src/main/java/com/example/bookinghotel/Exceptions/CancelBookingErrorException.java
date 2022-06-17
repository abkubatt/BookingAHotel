package com.example.bookinghotel.Exceptions;

public class CancelBookingErrorException extends RuntimeException{
    public CancelBookingErrorException(String message){
        super(message);
    }
}
