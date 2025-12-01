
package main;

import java.util.Random;

public class Monstre implements IVie{
    private int level;
    private Vie vie;
    private String name;
    private Boolean estVivant;
    private int force;
    private String design;

    public Monstre(int level, String name){ //il faudrait mieux lui donner un niveau, qui sera générer en fonction du biome au lieu de vie
        this.level = level;
        this.vie = null;
        //this.vie = level*5 - (new Random().nextInt(level)-(level/2)) afin de donner un aspect plus vivant avec une vie changeant un peu pour chaque monstre
        this.name = name;
        this.force = 0;
        this.estVivant = true;
        //this.DESIGN = JsonManager.getMonsterByName(nom);
    }
    public int getPV() {
        return this.getVie().getPv();
    }
    public String getSprite() {
        return this.design;
    }
    public int getPVmax() {
        return this.getVie().getPvMax();
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public Vie getVie() {
        return this.vie;
    }
    public void setVie(int vie) {
        this.vie.setPv(vie);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Boolean getEstVivant() {
        return estVivant;
    }
    public void setEstVivant(Boolean estVivant) {
        this.estVivant = estVivant;
    }
    public int getForce() {
        return force;
    }
    public void setForce(int force) {
        this.force = force;
    }

    public void revive(){
        this.setEstVivant(true);
        this.vie.setPv(Integer.MAX_VALUE);
        // this.vie.setPv(this.vie.getPv_def());
    }

    public void generate(int level){
        this.level=level;
        this.vie = new Vie(( 30 * this.level - (new Random().nextInt(10)-5)));
        this.force = 10*level + (new Random().nextInt(10)-5);
    }

    public static void main(String[] args) {
        JsonManager<Monstre> JSM = new JsonManager<Monstre>(Monstre.class);
        JSM.saveToFile("res/MonstreV2.json", new Monstre(2, "INEXISTANT"));
    }
    @Override
    public int getPv() {
        return this.vie.getPv();
    }
    @Override
    public void setPv(int v) {
        this.vie.addPv(v);
    }
    @Override
    public void addPV(int value) {
        this.vie.addPv(value);
    }
    @Override
    public void removePv(int value) {
        this.vie.removePv(value);;
    }
    @Override
    public int getPvMax() {
        return this.vie.getPvMax();
    }
    @Override
    public String affichePv() {
        return this.vie.affiche_pv();
    }

}