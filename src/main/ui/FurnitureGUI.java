package ui;

import exceptions.FurnitureNotThere;
import model.Furniture;
import model.House;
import model.Room;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Enumeration;


// Menu GUI layout was implemented from NGO Management System
// Link: https://github.com/Sid10501/NGO_Management_System

// Graphical User Interface for Furniture Menu
public class FurnitureGUI extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/house.json";

    private House house;
    private JFrame parentFrame;
    private Room room;
    private JsonWriter jsonWriter;
    private JFrame frame;
    private ButtonGroup bg1;
    private ButtonGroup bg2;
    private JFrame allFurnitureToView;
    private JFrame allFurnitureToRemove;

    // EFFECTS: constructs a furniture menu
    public FurnitureGUI(House house, Room room, JFrame parentFrame) {
        this.house = house;
        this.room = room;
        jsonWriter = new JsonWriter(JSON_STORE);
        frame = new JFrame();
        this.parentFrame = parentFrame;
        runFurnitureApp();
    }

    // MODIFIES: this
    // EFFECTS: sets up furniture menu
    private void runFurnitureApp() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);
        frame.setContentPane(createContentPane());
        frame.setVisible(true);
        parentFrame.setVisible(false);
    }

    // EFFECTS: creates buttons for user to press
    private JPanel createContentPane() {
        JPanel contentPane = new JPanel();
        JLabel roomLabel = new JLabel();
        JLabel message = new JLabel();

        contentPane.setLayout(new GridLayout(8,0));

        roomLabel.setText("You are currently in " + room.getRoomName());
        roomLabel.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(roomLabel);

        message.setText("Select From:");
        message.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(message);

        makeAddFurnitureButton(contentPane);
        makeRemoveFurnitureButton(contentPane);
        makeViewFurnitureDetailsButton(contentPane);
        makeRoomCostButton(contentPane);
        makeSaveHouseButton(contentPane);
        makeBackToRoomsButton(contentPane);

        return contentPane;
    }

    // EFFECTS: creates add furniture button
    private void makeAddFurnitureButton(JPanel panel) {
        JButton createRoomButton = new JButton();
        createRoomButton.setText("Add Furniture");
        createRoomButton.setActionCommand("add furniture");
        createRoomButton.setSize(500, 10);
        createRoomButton.addActionListener(this);
        panel.add(createRoomButton);
    }

    // EFFECTS: creates remove furniture button
    private void makeRemoveFurnitureButton(JPanel panel) {
        JButton loadRoomsButton = new JButton();
        loadRoomsButton.setText("Remove Furniture");
        loadRoomsButton.setActionCommand("remove furniture");
        loadRoomsButton.setSize(500, 10);
        loadRoomsButton.addActionListener(this);
        panel.add(loadRoomsButton);
    }

    // EFFECTS: creates view furnitures button
    private void makeViewFurnitureDetailsButton(JPanel panel) {
        JButton viewDetailsButton = new JButton();
        viewDetailsButton.setText("View Furniture Details");
        viewDetailsButton.setActionCommand("view details");
        viewDetailsButton.setSize(500, 10);
        viewDetailsButton.addActionListener(this);
        panel.add(viewDetailsButton);
    }

    // EFFECTS: creates room cost button
    private void makeRoomCostButton(JPanel panel) {
        JButton houseCostButton = new JButton();
        houseCostButton.setText("Calculate Total Room Cost");
        houseCostButton.setActionCommand("room cost");
        houseCostButton.setSize(500, 10);
        houseCostButton.addActionListener(this);
        panel.add(houseCostButton);
    }

    // EFFECTS: creates save house button
    private void makeSaveHouseButton(JPanel panel) {
        JButton saveHouseButton = new JButton();
        saveHouseButton.setText("Save House");
        saveHouseButton.setActionCommand("save house");
        saveHouseButton.setSize(500, 10);
        saveHouseButton.addActionListener(this);
        panel.add(saveHouseButton);
    }

    // EFFECTS: creates back to rooms button
    private void makeBackToRoomsButton(JPanel panel) {
        JButton backToHousesButton = new JButton();
        backToHousesButton.setText("Go Back to Room Menu");
        backToHousesButton.setActionCommand("back rooms");
        backToHousesButton.setSize(500, 10);
        backToHousesButton.addActionListener(this);
        panel.add(backToHousesButton);
    }

    // MODIFIES: this
    // EFFECTS: on activation, does functionality based on name
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("add furniture".equals(e.getActionCommand())) {
            new FurnitureCreationMenu(house, room, this);
        } else if ("remove furniture".equals(e.getActionCommand())) {
            removeFurnitureButtonPress();
        } else if ("view details".equals(e.getActionCommand())) {
            viewFurnitureButtonPress();
        } else if ("room cost".equals(e.getActionCommand())) {
            showRoomCost();
        } else if ("save house".equals(e.getActionCommand())) {
            saveHouse();
        } else if ("back rooms".equals(e.getActionCommand())) {
            parentFrame.setVisible(true);
            frame.setVisible(false);
        } else if ("remove".equals(e.getActionCommand())) {
            removeFurnitureSetUp();
        } else if ("select".equals(e.getActionCommand())) {
            selectFurnitureSetUp();
        }
    }

    // MODIFIES: this
    // EFFECTS: removes furniture, if there is furniture
    private void removeFurnitureButtonPress() {
        if (room.getFurnitures().size() != 0) {
            removeFurniture();
        } else {
            JOptionPane.showMessageDialog(this, "No Furniture to Remove", "",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: views furniture, if there is furniture
    private void viewFurnitureButtonPress() {
        if (room.getFurnitures().size() != 0) {
            viewDetails();
        } else {
            JOptionPane.showMessageDialog(this, "No Furniture to View", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes chosen furniture
    private void removeFurnitureSetUp() {
        Furniture chosenFurnitureToRemove = getChosenFurnitureToRemove(bg1);
        try {
            room.removeFurniture(chosenFurnitureToRemove);
        } catch (FurnitureNotThere e) {
            System.err.println("The chosen furniture to remove was not previously added.");
        }
        allFurnitureToRemove.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: views chosen furniture details
    private void selectFurnitureSetUp() {
        Furniture chosenFurnitureToRemove = getChosenFurnitureToRemove(bg2);
        allFurnitureToView.setVisible(false);
        new FurnitureDetailsMenu(chosenFurnitureToRemove, this);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons for viewing furniture
    private void viewDetails() {
        allFurnitureToView = new JFrame();
        allFurnitureToView.setLayout(new FlowLayout());
        allFurnitureToView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bg2 = new ButtonGroup();
        addRadioButtons(allFurnitureToView, bg2);
        addChooseButton();
        allFurnitureToView.pack();
        allFurnitureToView.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds choose button
    private void addChooseButton() {
        JButton button = new JButton("Select");
        button.addActionListener(this);
        button.setActionCommand("select");
        allFurnitureToView.add(button);
    }

    // EFFECTS: gets the chosen furniture that will be removed
    private Furniture getChosenFurnitureToRemove(ButtonGroup bg) {
        String chosenFurniture = null;
        for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                chosenFurniture = button.getText();
            }
        }
        for (Furniture furniture : room.getFurnitures()) {
            if (chosenFurniture.equals(furniture.getType())) {
                return furniture;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: creates buttons for removing furniture
    private void removeFurniture() {
        allFurnitureToRemove = new JFrame();
        allFurnitureToRemove.setLayout(new FlowLayout());
        allFurnitureToRemove.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bg1 = new ButtonGroup();
        addRadioButtons(allFurnitureToRemove, bg1);
        addRemoveButton();
        allFurnitureToRemove.pack();
        allFurnitureToRemove.setVisible(true);
    }

    // EFFECTS: adds radio buttons of the furniture type
    private void addRadioButtons(JFrame frame, ButtonGroup bg) {
        for (Furniture furniture : room.getFurnitures()) {
            JRadioButton radioButton = new JRadioButton(furniture.getType());
            bg.add(radioButton);
            frame.add(radioButton);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds remove button
    private void addRemoveButton() {
        JButton button = new JButton("Remove");
        button.addActionListener(this);
        button.setActionCommand("remove");
        allFurnitureToRemove.add(button);
    }

    // EFFECTS: shows the cost of the house
    private void showRoomCost() {
        JFrame roomCostFrame = new JFrame();
        roomCostFrame.setSize(345,100);
        roomCostFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel message = new JLabel();
        message.setText(room.getRoomName() + " total room cost = " + room.getRoomCost());
        message.setHorizontalAlignment(JLabel.CENTER);
        roomCostFrame.add(message);
        roomCostFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: saves the house to file
    private void saveHouse() {
        JFrame saveHouseFrame = new JFrame();
        saveHouseFrame.setSize(400,100);
        saveHouseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel message = new JLabel();

        try {
            jsonWriter.open();
            jsonWriter.write(house);
            jsonWriter.close();
            System.out.println("Saved " + house.getHouseName() + " to " + JSON_STORE);

            message.setText(house.getHouseName() + " successfully saved to: " + JSON_STORE);

        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);

            message.setText("House successfully saved to: " + JSON_STORE);
        }

        message.setHorizontalAlignment(JLabel.CENTER);
        saveHouseFrame.add(message);
        saveHouseFrame.setVisible(true);
    }


}