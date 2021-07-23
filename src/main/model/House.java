package model;

import java.util.ArrayList;

// Represents a house having a list of Rooms
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

    // EFFECTS: returns a room located at index parameter
    public Room getRoom(int index) {
        return this.rooms.get(index);
    }

    // EFFECTS: returns total house cost
    public int getHouseCost() {
        int houseCost = 0;
        for (Room r : rooms) {
            houseCost += r.getRoomCost();
        }
        return houseCost;
    }
}
