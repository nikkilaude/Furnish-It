package persistence;

import model.Furniture;
import model.House;
import model.Room;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

// Methods taken from JsonSerializationDemo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/test/persistence/JsonReaderTest.java

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            House h = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyHouse() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyHouse.json");
        try {
            House h = reader.read();
            checkHouse(h, "Test House", new ArrayList<>());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralHouse() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralHouse.json");
        try {
            House h = reader.read();
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
            fail("Couldn't read from file");
        }
    }

}