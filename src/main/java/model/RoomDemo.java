package main.java.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class RoomDemo {
    static ObjectMapper mapper = new ObjectMapper();
    private String name;
    private int numWereWolves;
    private HashMap<String, String> adjList;





    public RoomDemo() throws IOException {
        name = "";
        numWereWolves = 0;
        adjList = new HashMap<>();
    }
    public RoomDemo(String name, int numWereWolves, HashMap<String, String> adjList) throws IOException {
        this.name = name;
        this.numWereWolves = numWereWolves;
        this.adjList = adjList;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumWereWolves() {
        return numWereWolves;
    }

    public void setNumWereWolves(int numWereWolves) {
        this.numWereWolves = numWereWolves;
    }

    public HashMap<String, String> getAdjList() {
        return adjList;
    }

    public void setAdjList(HashMap<String, String> adjList) {
        this.adjList = adjList;
    }

    public String toString(){
        return "(Name: " + name + "  Num of werewolves:" + numWereWolves + " adjlist: " + adjList;

    }

    public static void main(String[] args) throws IOException {

//        mapper.readValue(new File("src/main/resources/rooms.json"), new TypeReference<HashMap<String, RoomDemo>>() {});
//        TypeReference<HashMap<String, RoomDemo>> abc = new TypeReference<HashMap<String, RoomDemo>>() {};
//        HashMap<String, RoomDemo> gameMap1 = new ObjectMapper().readValue(new File("src/main/resources/rooms.json"), abc);
//        System.out.println(gameMap1);











        HashMap<String, String> directions = new HashMap<>();
        directions.put("North", "kitchen");
        directions.put("South", "Great Room");
        directions.put("East", "Pantry");
        directions.put("West", "");


        RoomDemo room1 = new RoomDemo("Room1", 2, directions);
        System.out.println(room1);
        HashMap<String, String> directions2 = new HashMap<>();
        directions2.put("North", "Room1");
        directions2.put("South", "");
        directions2.put("East", "");
        directions2.put("West", "");
        RoomDemo greatRoom  = new RoomDemo("greatRoom", 0, directions2);

        HashMap<String, RoomDemo> map = new HashMap<>();
        map.put("Room1", room1);
        map.put("greatRoom", greatRoom);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(
                new FileOutputStream("map.json"), map);

        TypeReference<HashMap<String, RoomDemo>> typeRef  = new TypeReference<HashMap<String, RoomDemo>>() {};
        HashMap<String, RoomDemo> gameMap = new ObjectMapper().readValue(new File("map.json"), typeRef);

        System.out.println(gameMap);
        System.out.println(gameMap.get("Room1"));




    }







}
