package main.java.model;

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> ca55a0e (fixed)
import java.util.List;

public abstract class Character {
    private String name;
<<<<<<< HEAD

    private String location;
=======
>>>>>>> ca55a0e (fixed)
    private int health;
    private int attackPower;
    private List<String> inventory;

<<<<<<< HEAD
    public Character(){
        this("", "", 0, 0, new ArrayList<String>());
    }

    public Character(String name, String location, int health, int attackPower, List<String> inventory) {
        this.name = name;
        this.location = location;
        this.health = health;
        this.attackPower = attackPower;
        this.inventory = inventory;
    }
=======
>>>>>>> ca55a0e (fixed)



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
<<<<<<< HEAD
        if (this.health < 0){
            this.health = 0;
        }
=======
>>>>>>> ca55a0e (fixed)
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }

<<<<<<< HEAD

    public void attack(Character enemy) {
        enemy.gotAttacked(this);
    }

    public void gotAttacked(Character enemy){
        this.health -= enemy.attackPower;
    }

    @Override
    public String toString() {
        return  this.getClass().getSimpleName() +"{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", health=" + health +
                ", attackPower=" + attackPower +
                ", inventory=" + inventory +
                '}';
    }
=======
    public









>>>>>>> ca55a0e (fixed)
}
