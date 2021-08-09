package ui;

import model.House;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Form GUI Layout was implemented from Alex Lee's "Java GUI Tutorial - Make a Login GUI"
// Link: https://www.youtube.com/watch?v=iE8tZ0hn2Ws&t=823s

// Graphical User Interface for House Creation Menu
public class HouseCreationMenu extends JFrame implements ActionListener {

    private JButton button;
    private JTextField houseNameText;
    private House house;
    private JFrame parentFrame;
    private JPanel panel;

    // EFFECTS: constructs a new house creation menu
    public HouseCreationMenu(JFrame frame) {
        this.setSize(345,100);
        panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);
        this.parentFrame = frame;
        createHouseNameField();
        createHouseSubmitButton();
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates house name field and button
    private void createHouseNameField() {
        JLabel houseNameLabel = new JLabel("House Name");
        houseNameLabel.setBounds(10,20,150,25);
        panel.add(houseNameLabel);

        houseNameText = new JTextField();
        houseNameText.setBounds(160,20,165,25);
        panel.add(houseNameText);
    }

    // MODIFIES: this
    // EFFECTS: creates house submit button
    private void createHouseSubmitButton() {
        button = new JButton("Submit");
        button.setBounds(250,50,80,25);
        button.addActionListener(this);
        panel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: on activation, stores house and moves to room menu
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String houseName = houseNameText.getText();
            house = new House(houseName);
            this.setVisible(false);
            new RoomGUI(house, parentFrame);
        }
    }
}
