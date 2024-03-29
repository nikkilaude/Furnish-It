//package ui;
//
//import exceptions.FurnitureNotThere;
//import model.Furniture;
//import model.House;
//import model.Room;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//// UI Functionality and methods are implemented from Teller App. Link below:
//// https://github.students.cs.ubc.ca/CPSC210/TellerApp
//
//// Furniture manger application
//public class HouseApp {
//    private static final String JSON_STORE = "./data/house.json";
//    private Scanner input;
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//    private House house;
//    private Room room;
//
//
//    // EFFECTS: runs the furniture manager application
//    public HouseApp() throws FileNotFoundException {
//        input = new Scanner(System.in);
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//        runHouseApp();
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: processes user input for house menu
//    private void runHouseApp() {
//        boolean keepGoing = true;
//        String command = null;
//
//        input = new Scanner(System.in);
//        house = null;
//        room = null;
//
//        while (keepGoing) {
//            displayHouseMenu();
//            command = input.nextLine();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processHouseMenuCommand(command);
//            }
//        }
//
//        System.out.println("Goodbye!");
//    }
//
//
//    // EFFECTS: displays menu of house options to user
//    private void displayHouseMenu() {
//        System.out.println("\nWelcome to FurnishIt!");
//        System.out.println("Select from:");
//        System.out.println("\tn -> Create a new house");
//        System.out.println("\tl -> Load house file");
//        System.out.println("\tq -> Quit application");
//    }
//
//    // REQUIRES: string is from listed commands
//    // MODIFIES: this
//    // EFFECTS: processes user command for houses menu
//    private void processHouseMenuCommand(String command) {
//        if (command.equals("n")) {
//            displayHouseCreationMenu();
//        } else if (command.equals("l")) {
//            loadHouse();
//        } else {
//            System.out.println("Selection invalid, please try again");
//        }
//    }
//
//    // REQUIRES: string is not empty
//    // MODIFIES: this
//    // EFFECTS: adds a house
//    private void displayHouseCreationMenu() {
//        System.out.println("Create a name for your new house");
//        String houseName = input.nextLine();
//        house = new House(houseName);
//        System.out.println("House successfully created");
//        runRoomApp();
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: processes user input for rooms menu
//    private void runRoomApp() {
//        boolean keepGoing = true;
//        String command = null;
//
//        while (keepGoing) {
//            displayRoomMenu();
//            command = input.nextLine();
//            command = command.toLowerCase();
//
//            if (command.equals("b")) {
//                keepGoing = false;
//            } else {
//                processRoomMenuCommand(command);
//            }
//        }
//
//    }
//
//    // EFFECTS: displays menu of room options to user
//    private void displayRoomMenu() {
//        System.out.println("Select from:");
//        System.out.println("\tn -> Create a new room");
//        System.out.println("\te -> Choose an existing room");
//        System.out.println("\tc -> Calculate total house cost");
//        System.out.println("\ts -> Save house");
//        System.out.println("\tb -> Go back to houses menu");
//    }
//
//    // REQUIRES: string is from listed commands
//    // MODIFIES: this
//    // EFFECTS: processes user command for rooms menu
//    private void processRoomMenuCommand(String command) {
//        if (command.equals("n")) {
//            displayRoomCreationMenu();
//        } else if (command.equals("e")) {
//            displayExistingRoomsMenu();
//        } else if (command.equals("c")) {
//            displayHouseCalculation();
//        } else if (command.equals("s")) {
//            saveHouse();
//        } else {
//            System.out.println("Selection invalid, please try again");
//        }
//    }
//
//    // REQUIRES: string is not empty
//    // MODIFIES: this
//    // EFFECTS: adds a room
//    private void displayRoomCreationMenu() {
//        System.out.println("Create a name for your new room");
//        String roomName = input.nextLine();
//        System.out.println("Enter the length of the room");
//        int roomLength = input.nextInt();
//        input.nextLine();
//        System.out.println("Enter the width of the room");
//        int roomWidth = input.nextInt();
//        input.nextLine();
//        house.addRoom(new Room(roomName, roomLength, roomWidth));
//        System.out.println("Room successfully added");
//    }
//
//    // REQUIRES: number is from the listed numbers
//    // EFFECTS: selects a room
//    private void displayExistingRoomsMenu() {
//        if (house.getRooms().size() == 0) {
//            System.out.println("You have no existing rooms in this house");
//        } else {
//            ArrayList<Room> rooms = house.getRooms();
//            int count = 1;
//            for (Room r : rooms) {
//                System.out.println("\t" + count + ") " + r.getRoomName());
//                count++;
//            }
//            int roomSelection = input.nextInt();
//            input.nextLine();
//            room = house.getRoom(roomSelection - 1);
//            runFurnitureApp();
//        }
//    }
//
//    // EFFECTS: calculates total cost of house
//    private void displayHouseCalculation() {
//        System.out.println(house.getHouseName() + " total house cost = " + house.getHouseCost());
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user input for furniture menu
//    private void runFurnitureApp() {
//        boolean keepGoing = true;
//        String command = null;
//
//        while (keepGoing) {
//            displayFurnitureMenu();
//            command = input.nextLine();
//            command = command.toLowerCase();
//
//            if (command.equals("b")) {
//                keepGoing = false;
//            } else {
//                processFurnitureMenuCommand(command);
//            }
//        }
//
//    }
//
//    // EFFECTS: displays menu of furniture options to user
//    private void displayFurnitureMenu() {
//        System.out.println("\nHouse selected: " + house.getHouseName());
//        displayListOfFurniture();
//
//        int count = 1;
//        for (Furniture f : room.getFurnitures()) {
//            System.out.println("\t" + count + ") " + f.getType());
//            count++;
//        }
//
//        System.out.println("Select from:");
//        System.out.println("\ta -> Add new furniture");
//        System.out.println("\tr -> Remove existing furniture");
//        System.out.println("\tv -> View furniture details");
//        System.out.println("\tc -> Calculate total room cost");
//        System.out.println("\ts -> Save house");
//        System.out.println("\tb -> Go back to rooms menu");
//    }
//
//    // EFFECTS: displays list of furniture in a room
//    private void displayListOfFurniture() {
//        if (room.getFurnitures().size() >= 1) {
//            System.out.println("Furniture currently inside " + room.getRoomName());
//        } else {
//            System.out.println("There is no furniture currently inside " + room.getRoomName());
//        }
//    }
//
//    // REQUIRES: string is from listed commands
//    // MODIFIES: this
//    // EFFECTS: processes user command for furniture menu
//    private void processFurnitureMenuCommand(String command) {
//        if (command.equals("a")) {
//            displayFurnitureCreationMenu();
//        } else if (command.equals("r")) {
//            displayFurnitureRemovalMenu();
//        } else if (command.equals("v")) {
//            displayFurnitureDetails();
//        } else if (command.equals("c")) {
//            displayRoomCalculation();
//        } else if (command.equals("s")) {
//            saveHouse();
//        } else {
//            System.out.println("Selection invalid, please try again");
//        }
//    }
//
//    // REQUIRES: strings and integers are not null
//    // MODIFIES: this
//    // EFFECTS: adds a piece of furniture to room
//    private void displayFurnitureCreationMenu() {
//        System.out.println("Please input the following information:");
//        System.out.println("Furniture type:");
//        String type = input.nextLine();
//        System.out.println("Furniture brand:");
//        String brand = input.nextLine();
//        System.out.println("Furniture price:");
//        int cost = input.nextInt();
//        input.nextLine();
//        System.out.println("Furniture color:");
//        String color = input.nextLine();
//        System.out.println("Furniture length:");
//        int length = input.nextInt();
//        input.nextLine();
//        System.out.println("Furniture width:");
//        int width = input.nextInt();
//        input.nextLine();
//        if (room.addFurniture(new Furniture(type, brand, cost, color, length, width))) {
//            System.out.println("Furniture successfully added");
//        } else {
//            System.out.println("There isn't enough space in " + room.getRoomName() + " to add this furniture");
//        }
//    }
//
//    // REQUIRES: number is from listed numbers
//    // MODIFIES: this
//    // EFFECTS: removes a piece of furniture in room
//    private void displayFurnitureRemovalMenu() {
//        if (room.getFurnitures().size() >= 1) {
//            System.out.println("Please select a furniture to remove by inputting its number: ");
//            int furnitureSelection = input.nextInt();
//            input.nextLine();
//            try {
//                room.removeFurniture(room.getFurnitures().get(furnitureSelection - 1));
//            } catch (FurnitureNotThere e) {
//                System.err.println("The chosen furniture to remove was not previously added.");
//            }
//        } else {
//            System.out.println("There is currently no furniture to remove");
//        }
//    }
//
//    // REQUIRES: number is from listed numbers
//    // EFFECTS: displays furniture details of selected piece
//    private void displayFurnitureDetails() {
//        if (room.getFurnitures().size() >= 1) {
//            System.out.println("Please select a furniture to view by inputting its number: ");
//            int furnitureSelection = input.nextInt();
//            input.nextLine();
//            Furniture f = room.getFurnitures().get(furnitureSelection - 1);
//            System.out.println("Type: " + f.getType());
//            System.out.println("Brand: " + f.getBrand());
//            System.out.println("Price: " + f.getCost());
//            System.out.println("Color: " + f.getColor());
//            System.out.println("Length: " + f.getFurnitureLength());
//            System.out.println("Width: " + f.getFurnitureWidth());
//        } else {
//            System.out.println("There is currently no furniture to remove");
//        }
//    }
//
//    // EFFECTS: calculates total room cost
//    private void displayRoomCalculation() {
//        System.out.println(room.getRoomName() + " total room cost = " + room.getRoomCost());
//    }
//
//    // EFFECTS: saves the house to file
//    private void saveHouse() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(house);
//            jsonWriter.close();
//            System.out.println("Saved " + house.getHouseName() + " to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads house from file
//    private void loadHouse() {
//        try {
//            house = jsonReader.read();
//            System.out.println("Loaded " + house.getHouseName() + " from " + JSON_STORE);
//            runRoomApp();
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//
//}
