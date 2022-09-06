package main.java.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CharacterDemo {
    private String description;
    private List<String> inventory;
    private int health;
    private int damage;

    private HashMap<String, String> charCreation;



    //Constructors
    public CharacterDemo() {
        description = "";
        inventory = List.of();
        health = 0;
        charCreation = new HashMap<>();

    }

    public CharacterDemo(String description, String inventory, int health, int damage, HashMap<String, String> charList) {
        this.description = description;
        this.inventory = Collections.singletonList(inventory);
        this.health = health;
        this.damage = damage;
        this.charCreation = charList;
    }


    //Getters and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "CharactersDemo{" +
                "description='" + description + '\'' +
                ", inventory='" + inventory + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", charCreation=" + charCreation +
                '}';
    }

    public static void main(String[] args) throws IOException {

        HashMap<String, String> movements = new HashMap<>();
        movements.put("North", "kitchen");
        movements.put("South", "Great Room");
        movements.put("East", "Pantry");
        movements.put("West", "");
        CharacterDemo soldier1 = new CharacterDemo("Soldier1", "{Bow, arrow}", 3, 5, movements);
        System.out.println(soldier1);

        HashMap<String, String> movements2 = new HashMap<>();
        movements2.put("North", "kitchen");
        movements2.put("South", "Great Room");
        movements2.put("East", "Pantry");
        movements2.put("West", "");
        CharacterDemo solder2 = new CharacterDemo("Solder2", "{Bow, Health}",3, 7, movements);

        HashMap<String, CharacterDemo> map = new HashMap<>();
        map.put("Soldier1", soldier1);
        map.put("Soldier2", solder2);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(
                new FileOutputStream("characterDemo.json"), map);

        TypeReference<HashMap<String, CharacterDemo>> typeRef  = new TypeReference<HashMap<String, CharacterDemo>>() {};
        HashMap<String, CharacterDemo> charactersMap = new ObjectMapper().readValue(new File("characterDemo.json"), typeRef);

        System.out.println(charactersMap);
        System.out.println(charactersMap.get("Soldier1"));
        System.out.println(charactersMap.get("Soldier1").getInventory());


    }
}
