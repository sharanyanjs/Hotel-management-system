package com.hotel.model;

public interface IRoom {
    // Get room number
    String getRoomNumber();

    // Get price per night
    Double getRoomPrice();

    // Get room type (SINGLE or DOUBLE)
    RoomType getRoomType();

    // Check if room is free
    boolean isFree();
}