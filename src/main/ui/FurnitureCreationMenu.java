package ui;

import model.Furniture;
import model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// GUI was implemented from Alex Lee's "Java GUI Tutorial - Make a Login GUI"
// Link: https://www.youtube.com/watch?v=iE8tZ0hn2Ws&t=823s

// Graphical User Interface for Furniture Creation Menu
public class FurnitureCreationMenu extends JFrame implements ActionListener {

    private JButton button;
    private JTextField furnitureNameText;
    private JTextField furniturePriceText;
    private JTextField furnitureColorText;
    private JTextField furnitureBrandText;
    private JTextField furnitureLengthText;
    private JTextField furnitureWidthText;

    private Room room;
    private JFrame parentFrame;
    private JPanel panel;

    // EFFECTS: constructs a furniture creation menu
    public FurnitureCreationMenu(Room room, JFrame frame) {
        this.setSize(350,250);
        panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);
        this.room = room;
        this.parentFrame = frame;
        createFurnitureTypeField();
        createFurnitureBrandField();
        createFurniturePriceField();
        createFurnitureColorField();
        createFurnitureLengthField();
        createFurnitureWidthField();
        createFurnitureSubmitButton();
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates a furniture type field and button
    private void createFurnitureTypeField() {
        JLabel furnitureTypeLabel = new JLabel("Furniture Type");
        furnitureTypeLabel.setBounds(10,20,150,25);
        panel.add(furnitureTypeLabel);

        furnitureNameText = new JTextField();
        furnitureNameText.setBounds(160,20,165,25);
        panel.add(furnitureNameText);
    }

    // MODIFIES: this
    // EFFECTS: creates a furniture brand field and button
    private void createFurnitureBrandField() {
        JLabel furnitureBrandLabel = new JLabel("Furniture Brand");
        furnitureBrandLabel.setBounds(10,50,150,25);
        panel.add(furnitureBrandLabel);

        furnitureBrandText = new JTextField();
        furnitureBrandText.setBounds(160,50,165,25);
        panel.add(furnitureBrandText);
    }

    // MODIFIES: this
    // EFFECTS: creates a furniture brand price and button
    private void createFurniturePriceField() {
        JLabel furniturePriceLabel = new JLabel("Furniture Price");
        furniturePriceLabel.setBounds(10,80,150,25);
        panel.add(furniturePriceLabel);

        furniturePriceText = new JTextField();
        furniturePriceText.setBounds(160,80,165,25);
        panel.add(furniturePriceText);
    }

    // MODIFIES: this
    // EFFECTS: creates a furniture color field and button
    private void createFurnitureColorField() {
        JLabel furnitureColorLabel = new JLabel("Furniture Color");
        furnitureColorLabel.setBounds(10,110,150,25);
        panel.add(furnitureColorLabel);

        furnitureColorText = new JTextField();
        furnitureColorText.setBounds(160,110,165,25);
        panel.add(furnitureColorText);
    }

    // MODIFIES: this
    // EFFECTS: creates a furniture length field and button
    private void createFurnitureLengthField() {
        JLabel furnitureLengthLabel = new JLabel("Furniture Length");
        furnitureLengthLabel.setBounds(10,140,150,25);
        panel.add(furnitureLengthLabel);

        furnitureLengthText = new JTextField();
        furnitureLengthText.setBounds(160,140,165,25);
        panel.add(furnitureLengthText);
    }

    // MODIFIES: this
    // EFFECTS: creates a furniture width field and button
    private void createFurnitureWidthField() {
        JLabel furnitureWidthLabel = new JLabel("Furniture Width");
        furnitureWidthLabel.setBounds(10,170,150,25);
        panel.add(furnitureWidthLabel);

        furnitureWidthText = new JTextField();
        furnitureWidthText.setBounds(160,170,165,25);
        panel.add(furnitureWidthText);
    }

    // MODIFIES: this
    // EFFECTS: creates a furniture submit button
    private void createFurnitureSubmitButton() {
        button = new JButton("Submit");
        button.setBounds(250,200,80,25);
        button.addActionListener(this);
        panel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: on activation, adds furniture to room
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String type = furnitureNameText.getText();
            String brand = furnitureBrandText.getText();
            int price = Integer.parseInt(furniturePriceText.getText());
            String color = furnitureColorText.getText();
            int length = Integer.parseInt(furnitureLengthText.getText());
            int width = Integer.parseInt(furnitureWidthText.getText());
            Furniture furniture = new Furniture(type, brand, price, color, length, width);
            if (room.addFurniture(furniture)) {
                JOptionPane.showMessageDialog(this, "Furniture successfully added", "",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Not enough space for furniture", "",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            this.setVisible(false);
            parentFrame.setVisible(true);
        }
    }
}