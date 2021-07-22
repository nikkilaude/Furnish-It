package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FurnitureTest {
    private Furniture furn1;

    @BeforeEach
    public void setup() {
        furn1 = new Furniture("Table", "West Elm", 100, "Brown", 350, 350);
    }

    @Test
    public void testSetType() {
        furn1.setType("Couch");
        assertEquals(furn1.getType(), "Couch");
    }

    @Test
    public void testSetBrand() {
        furn1.setBrand("Ikea");
        assertEquals(furn1.getBrand(), "Ikea");
    }

    @Test
    public void testSetColor() {
        furn1.setColor("White");
        assertEquals(furn1.getColor(), "White");
    }


    @Test
    public void testSetCost() {
        furn1.setCost(1000);
        assertEquals(furn1.getCost(), 1000);
    }

    @Test
    public void testSetFurnitureLength() {
        furn1.setFurnitureLength(100);
        assertEquals(furn1.getFurnitureLength(), 100);
    }

    @Test
    public void testSetFurnitureWidth() {
        furn1.setFurnitureWidth(80);
        assertEquals(furn1.getFurnitureWidth(), 80);
    }


}
