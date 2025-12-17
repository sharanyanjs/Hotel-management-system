package com.hotel.model;

public enum RoomType {
    SINGLE("Single Bed", 1, 2),  // name, max adults, max children
    DOUBLE("Double Bed", 2, 2),
    SUITE("Suite", 4, 3),
    DELUXE("Deluxe", 3, 2);

    private final String description;
    private final int maxAdults;
    private final int maxChildren;

    RoomType(String description, int maxAdults, int maxChildren) {
        this.description = description;
        this.maxAdults = maxAdults;
        this.maxChildren = maxChildren;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxAdults() {
        return maxAdults;
    }

    public int getMaxChildren() {
        return maxChildren;
    }

    public int getMaxOccupancy() {
        return maxAdults + maxChildren;
    }

    @Override
    public String toString() {
        return description;
    }
}