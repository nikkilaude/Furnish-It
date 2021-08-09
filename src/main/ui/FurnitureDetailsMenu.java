package ui;

import model.Furniture;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Graphical User Interface for Furniture Details Menu
public class FurnitureDetailsMenu extends JFrame implements ActionListener {

    private Furniture furniture;
    private JFrame parentFrame;
    private JPanel panel;
    private JButton button;

    // EFFECTS: constructs a new furniture detail gui
    public FurnitureDetailsMenu(Furniture furniture, JFrame parentFrame) {
        this.setSize(350,250);
        panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);
        this.furniture = furniture;
        this.parentFrame = parentFrame;
        createFurnitureTypeField();
        createFurnitureBrandField();
        createFurniturePriceField();
        createFurnitureColorField();
        createFurnitureLengthField();
        createFurnitureWidthField();
        createFurnitureBackButton();
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: create furniture type field
    private void createFurnitureTypeField() {
        JLabel furnitureTypeLabel = new JLabel("Furniture Type: " + furniture.getType());
        furnitureTypeLabel.setBounds(10,20,340,25);
        panel.add(furnitureTypeLabel);
    }

    // MODIFIES: this
    // EFFECTS: create furniture brand field
    private void createFurnitureBrandField() {
        JLabel furnitureBrandLabel = new JLabel("Furniture Brand: " + furniture.getBrand());
        furnitureBrandLabel.setBounds(10,50,340,25);
        panel.add(furnitureBrandLabel);
    }

    // MODIFIES: this
    // EFFECTS: create furniture price field
    private void createFurniturePriceField() {
        JLabel furniturePriceLabel = new JLabel("Furniture Price: " + furniture.getCost());
        furniturePriceLabel.setBounds(10,80,340,25);
        panel.add(furniturePriceLabel);
    }

    // MODIFIES: this
    // EFFECTS: create furniture color field
    private void createFurnitureColorField() {
        JLabel furnitureColorLabel = new JLabel("Furniture Color: " + furniture.getColor());
        furnitureColorLabel.setBounds(10,110,340,25);
        panel.add(furnitureColorLabel);
    }

    // MODIFIES: this
    // EFFECTS: create furniture length field
    private void createFurnitureLengthField() {
        JLabel furnitureLengthLabel = new JLabel("Furniture Length: " + furniture.getFurnitureLength());
        furnitureLengthLabel.setBounds(10,140,340,25);
        panel.add(furnitureLengthLabel);
    }

    // MODIFIES: this
    // EFFECTS: create furniture width field
    private void createFurnitureWidthField() {
        JLabel furnitureWidthLabel = new JLabel("Furniture Width: " + furniture.getFurnitureWidth());
        furnitureWidthLabel.setBounds(10,170,340,25);
        panel.add(furnitureWidthLabel);
    }

    // MODIFIES: this
    // EFFECTS: create furniture back button
    private void createFurnitureBackButton() {
        button = new JButton("Back");
        button.setBounds(250,200,80,25);
        button.addActionListener(this);
        panel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: on activation, returns to furniture menu
    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        parentFrame.setVisible(true);
    }
}