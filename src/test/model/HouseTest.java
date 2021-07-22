package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HouseTest {
    private House house1;

    @BeforeEach
    public void setup() {
        house1 = new House("Nikki's House");
    }

    @Test
    public void addRoom() {
        Room livingRoom = new Room("Living Room",100, 100);
        house1.addRoom(livingRoom);
        assertEquals(house1.getRooms().size(),1);
        assertTrue(house1.getRooms().contains(livingRoom));
    }

    @Test
    public void removeRoom() {
        Room livingRoom = new Room("Living Room",100, 100);
        Room masterBed = new Room("Master Bedroom",100, 100);
        house1.addRoom(livingRoom);
        house1.addRoom(masterBed);
        house1.removeRoom(livingRoom);
        assertEquals(house1.getRooms().size(),1);
        assertTrue(house1.getRooms().contains(masterBed));
    }

    @Test
    public void setHouseName() {
        house1.setHouseName("Nikki's House");
        assertEquals(house1.getHouseName(), "Nikki's House");
    }

    @Test
    public void testGetRoom() {
        Room livingRoom = new Room("Living Room",100, 100);
        Room masterBed = new Room("Master Bedroom",100, 100);
        house1.addRoom(livingRoom);
        house1.addRoom(masterBed);
        assertEquals(house1.getRoom(0), livingRoom);
        assertEquals(house1.getRoom(1), masterBed);
    }

    @Test
    public void testGetHouseCost() {
        Room masterBed = new Room("Master Bedroom",100, 100);
        house1.addRoom(masterBed);
        Furniture bed = new Furniture("Bed", "Ikea", 1000, "Brown", 10, 10);
        Furniture dresser = new Furniture("Dresser", "Ikea", 500, "Brown", 10, 10);
        masterBed.addFurniture(bed);
        masterBed.addFurniture(dresser);

        Room livingRoom = new Room("Living Room",100, 100);
        house1.addRoom(livingRoom);
        Furniture couch = new Furniture("Couch", "Ikea", 3000, "Brown", 10, 10);
        Furniture tv = new Furniture("TV", "Panasonic", 500, "Silver", 10, 10);
        livingRoom.addFurniture(couch);
        livingRoom.addFurniture(tv);

        assertEquals(house1.getHouseCost(),5000);
    }

}