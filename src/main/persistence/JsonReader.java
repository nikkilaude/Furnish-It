package persistence;

import model.Furniture;
import model.House;
import model.Room;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Data persistence was implemented from JsonSerializationDemo. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads house from file and returns it;
    // throws IOException if an error occurs reading data from file
    public House read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHouse(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses house from JSON object and returns it
    private House parseHouse(JSONObject jsonObject) {
        String name = jsonObject.getString("house name");
        House h = new House(name);
        addRooms(h, jsonObject);
        return h;
    }

    // MODIFIES: h
    // EFFECTS: parses rooms from JSON object and adds it to house
    private void addRooms(House h, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("rooms");
        for (Object json : jsonArray) {
            JSONObject room = (JSONObject) json;
            addRoom(h, room);
        }
    }

    // MODIFIES: h
    // EFFECTS: parses room from JSON object and adds it to house
    private void addRoom(House h, JSONObject jsonObject) {
        String roomName = jsonObject.getString("room name");
        int roomLength = jsonObject.getInt("room length");
        int roomWidth = jsonObject.getInt("room width");
        int roomEmptyArea = jsonObject.getInt("room empty area");
        ArrayList<Furniture> furnitures = addFurnitures(jsonObject);
        h.addRoom(new Room(roomName, roomLength, roomWidth, furnitures, roomEmptyArea));
    }

    // EFFECTS: parses furnitures from JSON object and returns it
    private ArrayList<Furniture> addFurnitures(JSONObject jsonObject) {
        ArrayList<Furniture> furnitures = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("furnitures");
        for (Object json : jsonArray) {
            JSONObject furnitureJson = (JSONObject) json;
            Furniture furniture = addFurniture(furnitureJson);
            furnitures.add(furniture);
        }
        return furnitures;
    }

    // EFFECTS: parses furniture from JSON object and returns it
    private Furniture addFurniture(JSONObject jsonObject) {
        String type = jsonObject.getString("furniture type");
        String brand = jsonObject.getString("furniture brand");
        int cost = jsonObject.getInt("furniture cost");
        String color = jsonObject.getString("furniture color");
        int length = jsonObject.getInt("furniture length");
        int width = jsonObject.getInt("furniture width");
        return new Furniture(type, brand, cost, color, length, width);
    }

}