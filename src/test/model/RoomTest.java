package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
    private Room livRoom;

    @BeforeEach
    public void setup() {
        livRoom = new Room("Living Room", 100, 100);
    }

    @Test
    public void setRoomName() {
        livRoom.setRoomName("Master Bedroom");
        assertEquals(livRoom.getRoomName(), "Master Bedroom");
    }

    @Test
    public void setRoomLength() {
        livRoom.setRoomLength(200);
        assertEquals(livRoom.getRoomLength(), 200);
    }

    @Test
    public void setRoomWidth() {
        livRoom.setRoomWidth(200);
        assertEquals(livRoom.getRoomWidth(), 200);
    }

    @Test
    public void addFurnitureOne() {
        Furniture couch = new Furniture("Couch", "Ikea", 3000, "Brown", 10, 10);
        livRoom.addFurniture(couch);
        assertEquals(livRoom.getFurnitures().size(),1);
        assertTrue(livRoom.getFurnitures().contains(couch));
        Furniture rug = new Furniture("Rug", "Ikea", 1000, "Black", 200, 200);
        livRoom.addFurniture(rug);
        assertEquals(livRoom.getFurnitures().size(),1);
        assertFalse(livRoom.getFurnitures().contains(rug));
    }

    @Test
    public void addFurnitureMany() {
        Furniture couch = new Furniture("Couch", "Ikea", 3000, "Brown", 10, 10);
        assertTrue(livRoom.addFurniture(couch));
        assertEquals(livRoom.getFurnitures().size(),1);
        assertTrue(livRoom.getFurnitures().contains(couch));
        Furniture table = new Furniture("Table", "Ikea", 2000, "White", 90, 50);
        assertTrue(livRoom.addFurniture(table));
        assertEquals(livRoom.getFurnitures().size(),2);
        assertTrue(livRoom.getFurnitures().contains(table));
        Furniture rug = new Furniture("Rug", "Ikea", 1000, "Black", 30, 180);
        assertTrue(livRoom.addFurniture(rug));
        assertEquals(livRoom.getFurnitures().size(),3);
        assertTrue(livRoom.getFurnitures().contains(rug));
        Furniture chair = new Furniture("Chair", "Ikea", 1000, "Black", 1, 1);
        assertFalse(livRoom.addFurniture(chair));
        assertEquals(livRoom.getFurnitures().size(),3);
        assertFalse(livRoom.getFurnitures().contains(chair));
    }

    @Test
    public void testRemoveFurniture() {
        Furniture couch = new Furniture("Couch", "Ikea", 3000, "Brown", 10, 10);
        livRoom.addFurniture(couch);
        Furniture table = new Furniture("Table", "Ikea", 2000, "White", 90, 50);
        livRoom.addFurniture(table);
        livRoom.removeFurniture(couch);
        assertEquals(livRoom.getFurnitures().size(),1);
        assertFalse(livRoom.getFurnitures().contains(couch));
        Furniture chair = new Furniture("Chair", "Ikea", 1000, "Black", 1, 1);
        livRoom.removeFurniture(chair);
        assertEquals(livRoom.getFurnitures().size(),1);
    }

    @Test
    public void testGetRoomCost() {
        Furniture bed = new Furniture("Bed", "Ikea", 1000, "Brown", 10, 10);
        Furniture dresser = new Furniture("Dresser", "Ikea", 500, "Brown", 10, 10);
        livRoom.addFurniture(bed);
        livRoom.addFurniture(dresser);
        assertEquals(livRoom.getRoomCost(),1500);

    }

}