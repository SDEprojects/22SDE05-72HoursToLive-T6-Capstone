package main.java.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {

    private String name;
    private String description;
    private Map<String,String> connectedRooms;
    private List<String> items;

    public static String currentRoom;






    public Room() {
    connectedRooms = new HashMap<>();
    items = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", connectedRooms=" + connectedRooms +
                ", items=" + items +
                '}';
    }

    public Room(String name, String description, Map<String, String> connectedRooms, List<String> items) {
        this.name = name;
        this.description = description;
        this.connectedRooms = connectedRooms;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getConnectedRooms() {
        return connectedRooms;
    }

    public void setConnectedRooms(Map<String, String> connectedRooms) {
        this.connectedRooms = connectedRooms;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
