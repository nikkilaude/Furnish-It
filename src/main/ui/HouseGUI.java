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
import java.io.IOException;

// Menu GUI layout was implemented from NGO Management System
// Link: https://github.com/Sid10501/NGO_Management_System

// Graphical User Interface for House Menu
public class HouseGUI extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/house.json";
    private JsonReader jsonReader;
    private House house;
    private JFrame frame;

    // EFFECTS: constructs a house menu
    public HouseGUI() {
        jsonReader = new JsonReader(JSON_STORE);
        runHouseApp();
    }

    // MODIFIES: this
    // EFFECTS: sets up house menu
    private void runHouseApp() {
        frame = new JFrame("FunishIt: Virtual Staging Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);
        frame.setContentPane(createContentPane());
        frame.setVisible(true);
    }

    // EFFECTS: adds all buttons and image to frame
    private JPanel createContentPane() {
        JPanel contentPane = new JPanel();
        JLabel message = new JLabel();
        JButton createHouseButton = new JButton();
        JButton loadHouseButton = new JButton();

        contentPane.setLayout(new GridLayout(4,0));
        contentPane.add(displayImage());

        message.setText("Welcome to FurnishIt!");
        message.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(message);

        createHouseButton.setText("Create a New House");
        createHouseButton.setActionCommand("create house");
        createHouseButton.setSize(500, 10);
        createHouseButton.addActionListener(this);
        contentPane.add(createHouseButton);

        loadHouseButton.setText("Load a Saved House");
        loadHouseButton.setActionCommand("load house");
        loadHouseButton.setSize(500, 30);
        loadHouseButton.addActionListener(this);
        contentPane.add(loadHouseButton);
        return contentPane;
    }

    // EFFECTS: adds image to frame
    private JLabel displayImage() {
        JLabel image = new JLabel();
        image.setHorizontalAlignment(SwingConstants.CENTER);
        image.setIcon(new ImageIcon((new ImageIcon("data/home.png").getImage()
                .getScaledInstance(800, 400, Image.SCALE_SMOOTH))));
        return image;
    }

    // EFFECTS: on activation, functionality depends on name of button
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("create house".equals(e.getActionCommand())) {
            new HouseCreationMenu(this.frame);
        } else if ("load house".equals(e.getActionCommand())) {
            loadHouse();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads house from file
    private void loadHouse() {
        try {
            house = jsonReader.read();
            System.out.println("Loaded " + house.getHouseName() + " from " + JSON_STORE);
            new RoomGUI(house, this.frame);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}