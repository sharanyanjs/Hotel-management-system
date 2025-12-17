package com.hotel.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Room implements IRoom {
    private final String roomNumber;
    private final Double price;
    private final RoomType roomType;

    // New enhanced features
    private final int floorNumber;
    private final boolean hasBalcony;
    private final boolean hasSeaView;
    private final List<String> amenities;
    private RoomStatus status;
    private LocalDateTime lastCleaned;

    // Room status enum (inner class)
    public enum RoomStatus {
        AVAILABLE("Available for booking"),
        OCCUPIED("Currently occupied"),
        CLEANING("Being cleaned"),
        MAINTENANCE("Under maintenance");

        private final String description;

        RoomStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    // Constructor with all features
    public Room(String roomNumber, Double price, RoomType roomType,
                int floorNumber, boolean hasBalcony, boolean hasSeaView) {
        validateRoomNumber(roomNumber);
        validatePrice(price);

        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.floorNumber = floorNumber;
        this.hasBalcony = hasBalcony;
        this.hasSeaView = hasSeaView;
        this.amenities = new ArrayList<>();
        this.status = RoomStatus.AVAILABLE;
        this.lastCleaned = LocalDateTime.now();

        // Add default amenities based on room type
        addDefaultAmenities();
    }

    // Constructor with minimal parameters (for backward compatibility)
    public Room(String roomNumber, Double price, RoomType roomType) {
        this(roomNumber, price, roomType,
                1, // default floor
                false, // no balcony by default
                false); // no sea view by default
    }

    // Validation methods
    private void validateRoomNumber(String roomNumber) {
        if (roomNumber == null || roomNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Room number cannot be empty");
        }
        if (!roomNumber.matches("^[0-9]{3}[A-Z]?$")) {
            throw new IllegalArgumentException("Room number format: 3 digits + optional letter (e.g., 101A)");
        }
    }

    private void validatePrice(Double price) {
        if (price == null || price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }

    private void addDefaultAmenities() {
        // All rooms get basic amenities
        amenities.add("Free WiFi");
        amenities.add("Flat-screen TV");
        amenities.add("Air Conditioning");
        amenities.add("Private Bathroom");

        // Add amenities based on room type
        if (roomType == RoomType.DOUBLE) {
            amenities.add("King Size Bed");
            amenities.add("Mini Refrigerator");
            amenities.add("Work Desk");
        } else {
            amenities.add("Queen Size Bed");
        }

        // Add premium amenities for higher floors
        if (floorNumber >= 3) {
            amenities.add("Premium Toiletries");
            amenities.add("Coffee/Tea Maker");
            amenities.add("Bathrobe & Slippers");
        }

        // Add balcony amenities if available
        if (hasBalcony) {
            amenities.add("Private Balcony");
            amenities.add("Outdoor Seating");
        }

        // Add view amenities if available
        if (hasSeaView) {
            amenities.add("Ocean View");
            amenities.add("Binoculars");
        }
    }

    // Interface implementation
    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return price == 0.0;
    }

    // New getters for enhanced features
    public int getFloorNumber() {
        return floorNumber;
    }

    public boolean hasBalcony() {
        return hasBalcony;
    }

    public boolean hasSeaView() {
        return hasSeaView;
    }

    public List<String> getAmenities() {
        return new ArrayList<>(amenities); // Return copy for immutability
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
        if (status == RoomStatus.AVAILABLE) {
            this.lastCleaned = LocalDateTime.now();
        }
    }

    public LocalDateTime getLastCleaned() {
        return lastCleaned;
    }

    public void markAsCleaned() {
        this.lastCleaned = LocalDateTime.now();
        this.status = RoomStatus.AVAILABLE;
    }

    // Add custom amenity
    public void addAmenity(String amenity) {
        if (amenity != null && !amenity.trim().isEmpty() && !amenities.contains(amenity)) {
            amenities.add(amenity);
        }
    }

    // Check if room needs cleaning (if not cleaned for 24 hours)
    public boolean needsCleaning() {
        return lastCleaned.isBefore(LocalDateTime.now().minusHours(24));
    }

    // Calculate cleaning priority based on room type and floor
    public int getCleaningPriority() {
        int priority = 0;

        // Higher floor = higher priority
        priority += floorNumber;

        // Room type priority
        if (roomType == RoomType.DOUBLE) {
            priority += 2;
        }

        // Premium features add priority
        if (hasSeaView) priority += 1;
        if (hasBalcony) priority += 1;

        return priority;
    }

    // Get room rating (1-5 stars based on features)
    public int getRoomRating() {
        int rating = 3; // Base rating

        if (floorNumber >= 3) rating += 1;
        if (hasSeaView) rating += 1;
        if (hasBalcony) rating += 1;
        if (roomType == RoomType.DOUBLE) rating += 1;

        return Math.min(rating, 5); // Max 5 stars
    }

    // Get room category based on features
    public String getRoomCategory() {
        if (isFree()) return "Economy";

        int score = 0;
        if (hasSeaView) score += 2;
        if (hasBalcony) score += 1;
        if (floorNumber >= 3) score += 1;
        if (roomType == RoomType.DOUBLE) score += 1;

        if (score >= 4) return "Premium";
        if (score >= 2) return "Standard";
        return "Basic";
    }

    // Override equals() and hashCode() based on room number
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber.equals(room.roomNumber);
    }

    @Override
    public int hashCode() {
        return roomNumber.hashCode();
    }

    // Enhanced toString() method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ROOM ").append(roomNumber).append(" ===\n")
                .append("Type: ").append(roomType.getDescription())
                .append(" (").append(roomType.getMaxOccupancy()).append(" persons max)\n")
                .append("Floor: ").append(floorNumber)
                .append(" | Price: $").append(String.format("%.2f", price)).append("/night")
                .append("\nStatus: ").append(status.getDescription())
                .append("\nCategory: ").append(getRoomCategory())
                .append(" | Rating: ").append("*".repeat(getRoomRating()))
                .append("\nFeatures: ");

        List<String> features = new ArrayList<>();
        if (hasBalcony) features.add("Private Balcony");
        if (hasSeaView) features.add("Ocean View");

        if (!features.isEmpty()) {
            sb.append(String.join(", ", features));
        } else {
            sb.append("Standard");
        }

        sb.append("\nTop Amenities: ").append(String.join(", ",
                amenities.subList(0, Math.min(5, amenities.size()))));
        if (amenities.size() > 5) {
            sb.append("\n(+").append(amenities.size() - 5).append(" more amenities)");
        }

        if (needsCleaning()) {
            sb.append("\n⚠️  Needs cleaning (last cleaned: ")
                    .append(lastCleaned.toLocalDate()).append(")");
        }

        return sb.toString();
    }

    // Additional helper method for display in lists
    public String toSummaryString() {
        String freeMarker = isFree() ? " (FREE!)" : "";
        String viewMarker = hasSeaView ? " 🌊" : "";
        String balconyMarker = hasBalcony ? " 🪟" : "";
        String stars = "⭐".repeat(getRoomRating());

        return String.format("%s Room %s - $%.2f/night [Floor %d]%s%s%s %s",
                getRoomCategory(), roomNumber, price, floorNumber,
                freeMarker, viewMarker, balconyMarker, stars);
    }

    // Quick info for reservation confirmations
    public String getQuickInfo() {
        return String.format("Room %s: %s bed, Floor %d, $%.2f/night",
                roomNumber, roomType, floorNumber, price);
    }
}