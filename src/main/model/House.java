package model;

import java.util.ArrayList;

// Represents a house having a list of Rooms
public class House {
    private ArrayList<Room> rooms;
    private String houseName;

    public House(String name) {
        this.rooms = new ArrayList<>();
        this.houseName = name;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public void removeRoom(Room room) {
        this.rooms.remove(room);
    }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    public String getHouseName() {
        return this.houseName;
    }

    public void setHouseName(String name) {
        this.houseName = name;
    }

    public Room getRoom(int index) {
        return this.rooms.get(index);
    }

    public int getHouseCost() {
        int houseCost = 0;
        for (Room r : rooms) {
            houseCost += r.getRoomCost();
        }
        return houseCost;
    }
}
