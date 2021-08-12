package model;

import exceptions.FurnitureNotThere;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a Room having a list of Furniture placed inside it
public class Room implements Writable {
    private ArrayList<Furniture> furnitures;
    private String roomName;
    private int roomLength;
    private int roomWidth;
    private int roomEmptyArea;

    // EFFECTS: creates a Room with empty furniture list, a name, length, and width
    public Room(String name, int length, int width) {
        this.furnitures = new ArrayList<>();
        this.roomName = name;
        this.roomLength = length;
        this.roomWidth = width;
        this.roomEmptyArea = length * width;
    }

    // EFFECTS: creates a Room with furnitures, name, length, width, empty area
    public Room(String name, int length, int width, ArrayList<Furniture> furnitures, int emptyArea) {
        this.roomName = name;
        this.roomLength = length;
        this.roomWidth = width;
        this.furnitures = furnitures;
        this.roomEmptyArea = emptyArea;
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

    // REQUIRES: furniture to be removed should have been added previously
    // MODIFIES: this
    // EFFECTS: removes Furniture from Room
    public void removeFurniture(Furniture f) throws FurnitureNotThere {
        if (!furnitures.contains(f)) {
            throw new FurnitureNotThere();
        }
        this.furnitures.remove(f);
        roomEmptyArea += (f.getFurnitureLength() * f.getFurnitureWidth());
    }

    // EFFECTS: returns total cost of Furniture in Room, returns 0 if no furniture has been added so far
    public int getRoomCost() {
        int roomCost = 0;
        for (Furniture f : furnitures) {
            roomCost += f.getCost();
        }
        return roomCost;
    }

    // EFFECTS: returns JSON object of fields of Room
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("furnitures", furnituresToJson());
        json.put("room name", roomName);
        json.put("room length", roomLength);
        json.put("room width", roomWidth);
        json.put("room empty area", roomEmptyArea);
        return json;
    }

    // EFFECTS: returns JSON array of furnitures
    private JSONArray furnituresToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Furniture f : furnitures) {
            jsonArray.put(f.toJson());
        }
        return jsonArray;
    }
}