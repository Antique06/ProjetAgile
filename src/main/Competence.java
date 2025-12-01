package main;

public class Competence {
    private String name;
    private String description;
    private int value;
    private boolean damage;
    private int cooldown;
    

    public Competence(String name, String description, int value, boolean damage, int cooldown){
        this.name = name;
        this.description = description;
        this.value = value;
        this.damage = damage;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isDamage() {
        return damage;
    }

    public void setDamage(boolean damage) {
        this.damage = damage;
    }
}