package persistence;

import model.Furniture;
import model.House;
import model.Room;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkHouse(House h, String name, List<Room> rooms) {
        assertEquals(name, h.getHouseName());
        checkRooms(h, rooms);
    }

    private void checkRooms(House h, List<Room> rooms) {
        List<Room> houseRooms = h.getRooms();
        assertEquals(houseRooms.size(), rooms.size());
        for (int i = 0; i < houseRooms.size(); i++) {
            checkRoom(houseRooms.get(i), rooms.get(i));
        }
    }

    private void checkRoom(Room room1, Room room2) {
        assertEquals(room1.getRoomCost(), room2.getRoomCost());
        assertEquals(room1.getRoomName(), room2.getRoomName());
        assertEquals(room1.getRoomLength(), room2.getRoomLength());
        assertEquals(room1.getRoomWidth(), room2.getRoomWidth());

        List<Furniture> furnitures1 = room1.getFurnitures();
        List<Furniture> furnitures2 = room2.getFurnitures();
        assertEquals(furnitures1.size(), furnitures2.size());
        for (int i = 0; i < furnitures1.size(); i++) {
            checkFurniture(furnitures1.get(i), furnitures2.get(i));
        }
    }

    private void checkFurniture(Furniture furniture1, Furniture furniture2) {
        assertEquals(furniture1.getType(), furniture2.getType());
        assertEquals(furniture1.getBrand(), furniture2.getBrand());
        assertEquals(furniture1.getCost(), furniture2.getCost());
        assertEquals(furniture1.getColor(), furniture2.getColor());
        assertEquals(furniture1.getFurnitureWidth(), furniture2.getFurnitureWidth());
        assertEquals(furniture1.getFurnitureLength(), furniture2.getFurnitureLength());
    }

}