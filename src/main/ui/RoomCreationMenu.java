package ui;

import model.House;
import model.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// GUI was implemented from Alex Lee's "Java GUI Tutorial - Make a Login GUI"
// Link: https://www.youtube.com/watch?v=iE8tZ0hn2Ws&t=823s

// Graphical User Interface for Room Creation Menu
public class RoomCreationMenu extends JFrame implements ActionListener {

    private JButton button;
    private JTextField roomNameText;
    private JTextField roomLengthText;
    private JTextField roomWidthText;
    private House house;
    private JFrame parentFrame;
    private JPanel panel;

    // EFFECTS: creates a room creation menu
    public RoomCreationMenu(House house, JFrame frame) {
        this.setSize(350,180);
        panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);
        this.house = house;
        this.parentFrame = frame;

        createRoomNameField();
        createRoomLengthField();
        createRoomWidthField();
        createRoomSubmitButton();

        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates a room name field and button
    private void createRoomNameField() {
        JLabel roomNameLabel = new JLabel("Room Name");
        roomNameLabel.setBounds(10,20,150,25);
        panel.add(roomNameLabel);

        roomNameText = new JTextField();
        roomNameText.setBounds(160,20,165,25);
        panel.add(roomNameText);
    }

    // MODIFIES: this
    // EFFECTS: creates a room length field and button
    private void createRoomLengthField() {
        JLabel roomLengthLabel = new JLabel("Room Length");
        roomLengthLabel.setBounds(10,50,150,25);
        panel.add(roomLengthLabel);

        roomLengthText = new JTextField();
        roomLengthText.setBounds(160,50,165,25);
        panel.add(roomLengthText);
    }

    // MODIFIES: this
    // EFFECTS: creates a room width field and button
    private void createRoomWidthField() {
        JLabel roomWidthLabel = new JLabel("Room Width");
        roomWidthLabel.setBounds(10,80,150,25);
        panel.add(roomWidthLabel);

        roomWidthText = new JTextField();
        roomWidthText.setBounds(160,80,165,25);
        panel.add(roomWidthText);
    }

    // MODIFIES: this
    // EFFECTS: creates a room submit button
    private void createRoomSubmitButton() {
        button = new JButton("Submit");
        button.setBounds(250,110,80,25);
        button.addActionListener(this);
        panel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: on activation, saves room and moves to adding furnitures for that room
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String roomName = roomNameText.getText();
            int roomWidth = Integer.parseInt(roomLengthText.getText());
            int roomLength = Integer.parseInt(roomWidthText.getText());
            Room room = new Room(roomName, roomLength, roomWidth);
            house.addRoom(room);
            this.setVisible(false);
            parentFrame.setVisible(true);
        }
    }

}