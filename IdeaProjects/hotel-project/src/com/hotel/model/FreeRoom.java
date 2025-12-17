package com.hotel.model;

public class FreeRoom extends Room {

    // FreeRoom constructor with enhanced features
    public FreeRoom(String roomNumber, RoomType roomType,
                    int floorNumber, boolean hasBalcony, boolean hasSeaView) {
        super(roomNumber, 0.0, roomType, floorNumber, hasBalcony, hasSeaView);
    }

    // Simplified constructor for backward compatibility
    public FreeRoom(String roomNumber, RoomType roomType) {
        this(roomNumber, roomType, 1, false, false);
    }

    @Override
    public String toString() {
        return "🎁 COMPLIMENTARY ROOM 🎁\n" + super.toString();
    }

    @Override
    public String getRoomCategory() {
        return "Complimentary";
    }

    @Override
    public String toSummaryString() {
        return String.format("🎁 FREE Room %s [%s, Floor %d] - All amenities included! 🌟🌟🌟🌟🌟",
                getRoomNumber(), getRoomType(), getFloorNumber());
    }
}