package model;

import java.util.ArrayList;

// Represents a room having a list of Furniture
public class Room {
    private ArrayList<Furniture> furnitures;
    private String roomName;
    private int roomLength;
    private int roomWidth;
    private int roomEmptyArea;

    // EFFECTS: creates a Room with a name, length, and width
    public Room(String name, int length, int width) {
        this.furnitures = new ArrayList<>();
        this.roomName = name;
        this.roomLength = length;
        this.roomWidth = width;
        this.roomEmptyArea = length * width;
    }

    // EFFECTS: returns Room name
    public String getRoomName() {
        return this.roomName;
    }

    // EFFECTS: returns Room length
    public int getRoomLength() {
        return this.roomLength;
    }

    // EFFECTS: returns Room width
    public int getRoomWidth() {
        return this.roomWidth;
    }

    // EFFECTS: returns list of Furniture inside the Room
    public ArrayList<Furniture> getFurnitures() {
        return this.furnitures;
    }

    // MODIFIES: this
    // EFFECTS: sets Room name to parameter
    public void setRoomName(String name) {
        this.roomName = name;
    }

    // MODIFIES: this
    // EFFECTS: sets Room length to parameter
    public void setRoomLength(int length) {
        this.roomLength = length;
    }

    // MODIFIES: this
    // EFFECTS: sets Room width to parameter
    public void setRoomWidth(int width) {
        this.roomWidth = width;
    }

    // MODIFIES: this
    // EFFECTS: adds Furniture to Room if there is enough remaining empty area in Room, returns true if successful,
    // false otherwise
    public boolean addFurniture(Furniture furn) {
        int length = furn.getFurnitureLength();
        int width = furn.getFurnitureWidth();
        int furnArea = length * width;

        if (furnArea <= roomEmptyArea) {
            this.furnitures.add(furn);
            roomEmptyArea -= furnArea;
            return true;
        }
        return false;
    }

    // EFFECTS: removed Furniture from Room
    public void removeFurniture(Furniture f) {
        if (this.furnitures.contains(f)) {
            this.furnitures.remove(f);
        }
    }

    // EFFECTS: returns total cost of Furniture in Room
    public int getRoomCost() {
        int roomCost = 0;
        for (Furniture f : furnitures) {
            roomCost += f.getCost();
        }
        return roomCost;
    }
}
