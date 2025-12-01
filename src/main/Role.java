package main;

import java.util.ArrayList;
import java.util.List;
//import java.util.Map;

public class Role {
    private String nom;
    private String description;
    protected String sprite;
    protected List<Competence> startingPowers;
    protected List<Competence> unlockablePowers;

    // private Map<Integer, Competence> unlockablePowers;
    public Role(String nom, String description, String sprite, List<Competence> startingPowers, List<Competence> unlockablePowers) {
        this.nom = nom;
        this.description = description;
        this.sprite = sprite;
        this.startingPowers = startingPowers;
        this.unlockablePowers = unlockablePowers;
    }

    public List<Competence> getUnlockablePowers() {
        return unlockablePowers;
    }

    public void setUnlockablePowers(List<Competence> unlockablePowers) {
        this.unlockablePowers = unlockablePowers;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Competence> getStartingPowers() {
        return startingPowers;
    }

    public void setStartingPowers(List<Competence> startingPowers) {
        this.startingPowers = startingPowers;
    }

    /*
     * public Map<Integer, Competence> getUnlockablePowers() {
     * return unlockablePowers;
     * }
     * 
     * public void setUnlockablePowers(Map<Integer, Competence> unlockablePowers) {
     * this.unlockablePowers = unlockablePowers;
     * }
     */

    public void addCompetence(Competence comp) {
        if (this.startingPowers.contains(comp)) {
            System.out.println("La compétence est déjà présente");
        } else {
            this.startingPowers.add(comp);
            System.out.println("La compétence est déjà présente");
        }
    }

    public String getSprite() {
        return sprite;

    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public void changeCompetence(Competence cRemove, Competence cAdd) {
        if (startingPowers.contains(cRemove)) {
            startingPowers.remove(cRemove);
        }
        addCompetence(cAdd);
    }

    public String listComp() {
        return this.startingPowers.toString();
    }

    public String toString;



    /*
     * public static void main(String[] args) {
     * JsonManager<Role> JSN = new JsonManager<Role>(Role.class);
     * JSN.saveToFile("res/Role.json", new Role("Paladin",
     * "Le paladin est un héros assez équilibrer. Il est capable de manier autant une épée, que de se défendre et utilisé la magie."
     * ));
     * JSN.saveToFile("res/Role.json", new Role("Moine",
     * "Le Moine est un érudit des religions les plus mystérieuse. Malgré son faible physique, ses plus grande prière feront des explosions de lumières."
     * ));
     * JSN.saveToFile("res/Role.json", new Role("Mage",
     * "Le Mage possède des capacités magiques hors de la norme. Sa magie peut provoquer les plus grand miracles."
     * ));
     * JSN.saveToFile("res/Role.json", new Role("Gambler",
     * "Malgré ses faibles capacité à se battre, ses cartes sont d'une magie extrêmement puissante... mais aléatoire."
     * ));
     * 
     * }
     */

}