package persistence;

import model.Furniture;
import model.House;
import model.Room;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

// Methods taken from JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonWriterTest.java

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            House h = new House("Test House");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyHouse() {
        try {
            House h = new House("Test House");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyHouse.json");
            writer.open();
            writer.write(h);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyHouse.json");
            h = reader.read();
            checkHouse(h, "Test House", new ArrayList<>());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralHouse() {
        try {
            House h = new House("Test House");
            addRooms(h);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralHouse.json");
            writer.open();
            writer.write(h);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralHouse.json");
            h = reader.read();
            ArrayList<Room> rooms = new ArrayList<>();

            ArrayList<Furniture> furnitures1 = new ArrayList<>();
            Room room1 = new Room("Bathroom", 2, 3, furnitures1, 6);
            rooms.add(room1);

            ArrayList<Furniture> furnitures2 = new ArrayList<>();
            furnitures2.add(new Furniture("Chair", "IKEA", 200, "Blue", 1, 1));
            furnitures2.add(new Furniture("Table", "IKEA", 300, "White", 3, 2));
            Room room2 = new Room("Living Room", 5, 6, furnitures2, 23);
            rooms.add(room2);

            checkHouse(h, "Test House", rooms);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    void addRooms(House h) {
        ArrayList<Furniture> furnitures1 = new ArrayList<>();
        Room room1 = new Room("Bathroom", 2, 3, furnitures1, 6);

        ArrayList<Furniture> furnitures2 = new ArrayList<>();
        furnitures2.add(new Furniture("Chair", "IKEA", 200, "Blue", 1, 1));
        furnitures2.add(new Furniture("Table", "IKEA", 300, "White", 3, 2));
        Room room2 = new Room("Living Room", 5, 6, furnitures2, 23);

        h.addRoom(room1);
        h.addRoom(room2);
    }

}
