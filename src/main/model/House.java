package model;

import java.util.ArrayList;

// Represents a House having a list of Rooms inside it
public class House {
    private ArrayList<Room> rooms;
    private String houseName;

    // EFFECTS: creates a House with empty rooms list and name
    public House(String name) {
        this.rooms = new ArrayList<>();
        this.houseName = name;
    }

    // MODIFIES: this
    // EFFECTS: adds a room
    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    // REQUIRES: room to be removed should have been added previously
    // MODIFIES: this
    // EFFECTS: removes a room
    public void removeRoom(Room room) {
        this.rooms.remove(room);
    }

    // EFFECTS: return list of rooms in house
    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    // EFFECTS: returns house name
    public String getHouseName() {
        return this.houseName;
    }

    // MODIFIES: this
    // EFFECTS: sets house name
    public void setHouseName(String name) {
        this.houseName = name;
    }

    // REQUIRES: there must exist a room at index parameter
    // EFFECTS: returns a room located at index parameter
    public Room getRoom(int index) {
        return this.rooms.get(index);
    }

    // EFFECTS: returns total house cost, returns 0 if no furniture has been added so far
    public int getHouseCost() {
        int houseCost = 0;
        for (Room r : rooms) {
            houseCost += r.getRoomCost();
        }
        return houseCost;
    }
}
