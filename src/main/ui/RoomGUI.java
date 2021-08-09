package ui;

import model.House;
import model.Room;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Enumeration;

// Menu GUI layout was implemented from NGO Management System
// Link: https://github.com/Sid10501/NGO_Management_System

// Graphical User Interface for Room Menu
public class RoomGUI extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/house.json";
    private JsonWriter jsonWriter;
    private House house;
    private JFrame frame;
    private JFrame parentFrame;
    private ButtonGroup bg;
    private JFrame allRooms;

    // EFFECTS: constructs a new room menu
    public RoomGUI(House house, JFrame parentFrame) {
        this.house = house;
        jsonWriter = new JsonWriter(JSON_STORE);
        frame = new JFrame();
        this.parentFrame = parentFrame;
        runRoomApp();
    }

    // MODIFIES: this
    // EFFECTS: setups the frame of room menu
    private void runRoomApp() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);
        frame.setContentPane(createContentPane());
        frame.setVisible(true);
        parentFrame.setVisible(false);

    }

    // EFFECTS: creates the content pane for title and buttons
    private JPanel createContentPane() {
        JPanel contentPane = new JPanel();
        JLabel houseLabel = new JLabel();
        JLabel message = new JLabel();

        contentPane.setLayout(new GridLayout(7,0));

        houseLabel.setText("You are currently in " + house.getHouseName());
        houseLabel.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(houseLabel);

        message.setText("Select From:");
        message.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(message);

        makeCreateRoomButton(contentPane);
        makeLoadRoomsButton(contentPane);
        makeHouseCostButton(contentPane);
        makeSaveHouseButton(contentPane);
        makeBackToHousesButton(contentPane);

        return contentPane;
    }

    // EFFECTS: creates the create room button
    private void makeCreateRoomButton(JPanel panel) {
        JButton createRoomButton = new JButton();
        createRoomButton.setText("Create a New Room");
        createRoomButton.setActionCommand("create room");
        createRoomButton.setSize(500, 10);
        createRoomButton.addActionListener(this);
        panel.add(createRoomButton);
    }

    // EFFECTS: creates the load room button
    private void makeLoadRoomsButton(JPanel panel) {
        JButton loadRoomsButton = new JButton();
        loadRoomsButton.setText("Choose an Existing Room");
        loadRoomsButton.setActionCommand("choose room");
        loadRoomsButton.setSize(500, 10);
        loadRoomsButton.addActionListener(this);
        panel.add(loadRoomsButton);
    }

    // EFFECTS: creates house cost button
    private void makeHouseCostButton(JPanel panel) {
        JButton houseCostButton = new JButton();
        houseCostButton.setText("Calculate Total House Cost");
        houseCostButton.setActionCommand("house cost");
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

    // EFFECTS: creates back button
    private void makeBackToHousesButton(JPanel panel) {
        JButton backToHousesButton = new JButton();
        backToHousesButton.setText("Go Back to House Menu");
        backToHousesButton.setActionCommand("back houses");
        backToHousesButton.setSize(500, 10);
        backToHousesButton.addActionListener(this);
        panel.add(backToHousesButton);
    }

    // MODIFIES: this
    // EFFECTS: on activation, does functionality of button depending on name
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("create room".equals(e.getActionCommand())) {
            new RoomCreationMenu(house, this);
        } else if ("choose room".equals(e.getActionCommand())) {
            chooseRoom();
        } else if ("house cost".equals(e.getActionCommand())) {
            showHouseCost();
        } else if ("save house".equals(e.getActionCommand())) {
            saveHouse();
        } else if ("back houses".equals(e.getActionCommand())) {
            frame.setVisible(false);
            parentFrame.setVisible(true);
        } else if ("select".equals(e.getActionCommand())) {
            Room chosenRoom = getChosenRoom();
            allRooms.setVisible(false);
            new FurnitureGUI(house, chosenRoom, frame);
        }
    }

    // EFFECTS: returns the chosen room from radio buttons
    private Room getChosenRoom() {
        String chosenRoom = null;
        for (Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                chosenRoom = button.getText();
            }
        }
        for (Room room : house.getRooms()) {
            if (chosenRoom.equals(room.getRoomName())) {
                return room;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: sets up frame for choosing a room
    private void chooseRoom() {
        if (house.getRooms().size() > 0) {
            allRooms = new JFrame();
            allRooms.setLayout(new FlowLayout());
            allRooms.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            bg = new ButtonGroup();
            addRadioButtons();
            addChooseButton();
            allRooms.pack();
            allRooms.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "There are currently no rooms in this house.",
                    "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds buttons for users to choose of room names
    private void addRadioButtons() {
        for (Room room : house.getRooms()) {
            JRadioButton radioButton = new JRadioButton(room.getRoomName());
            bg.add(radioButton);
            allRooms.add(radioButton);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds choose button for user to choose the room
    private void addChooseButton() {
        JButton button = new JButton("Select");
        button.addActionListener(this);
        button.setActionCommand("select");
        allRooms.add(button);
    }

    // EFFECTS: shows the cost of the house
    private void showHouseCost() {
        JFrame houseCostFrame = new JFrame();
        houseCostFrame.setSize(345,100);
        houseCostFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel message = new JLabel();
        message.setText(house.getHouseName() + " total house cost = " + house.getHouseCost());
        message.setHorizontalAlignment(JLabel.CENTER);
        houseCostFrame.add(message);
        houseCostFrame.setVisible(true);
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