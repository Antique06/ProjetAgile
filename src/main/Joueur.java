package main;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private String name;
    private Vie vie;
    private int degat;
    public Boolean estVivant;
    private int nbMonsterKill = 0;
    private Role role;
    private List<Competence> startingPowers = new ArrayList<Competence>();
    private List<Competence> unlockedPowers = new ArrayList<Competence>();

    public Joueur(String name, int vie, int degat, Role role) {
        this.name = name;
        this.vie = new Vie(vie);
        this.degat = degat;
        this.estVivant = true;
        this.role = role;
        this.startingPowers = this.role.getStartingPowers();
        this.unlockedPowers = this.role.getUnlockablePowers();
        System.out.println("passe dans joueur " + this.startingPowers.size());
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Competence> getStartingPowers() {
        return startingPowers;
    }

    public void setStartingPowers(List<Competence> startingPowers) {
        this.startingPowers = startingPowers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vie getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie.setPv(vie);
    }

    public int getDegat() {
        return degat;
    }

    public void setNbMonsterKill(int nbMonsterKill) {
        this.nbMonsterKill = nbMonsterKill;
    }

    public int getNbMonsterKill() {
        return nbMonsterKill;
    }

    public void setEstVivant(Boolean estVivant) {
        this.estVivant = estVivant;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    };

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        return true;
    }

    public int getPV() {
        return this.getVie().getPv();
    }

    public int getPVmax() {
        return this.getVie().getPvMax();
    }

    public Boolean getEstVivant() {
        return estVivant;
    }
    public List<Competence> getComp(){
        System.out.println(this.startingPowers.get(0));
        return this.startingPowers;
    }

    public String compToString() {
        String str = "voici vos compétences :\n";
        int i = 1;
        for (Competence c : this.startingPowers) {
            str = str + "[" + i + "] : " + c.getName() + " " + c.getValue() + "\n";
            i++;
        }
        return str;
    }

    public void addPv(int i) {
        this.vie.addPv(i);
    }

    public void DebloComp(){
        if (this.nbMonsterKill == 2) {
            this.startingPowers.add(this.unlockedPowers.get(0));
            ScreenManager.clear();
            TextBox.afficherTextBox("Vous venez de débloquer une nouvelle compétence", this.unlockedPowers.get(0).getName()+" : "+this.unlockedPowers.get(0).getDescription(), "");
            Key key = null;
            try{
            do {
                key = KeyManager.getKey();
            } while (key==null || !key.equals(Key.ENTER));
            
        } catch (Exception e){e.printStackTrace();}
        }else if (this.nbMonsterKill == 7 && this.unlockedPowers.size()>=2) {
            this.startingPowers.add(this.unlockedPowers.get(1));
            ScreenManager.clear();
            TextBox.afficherTextBox("Vous venez de débloquer une nouvelle compétence", this.unlockedPowers.get(1).getName()+" : "+this.unlockedPowers.get(1).getDescription(), "");
            Key key = null;
            try{
            do {
                key = KeyManager.getKey();
            } while (key==null || !key.equals(Key.ENTER));
            
        } catch (Exception e){e.printStackTrace();}
            
            
        }else if (this.nbMonsterKill == 15 && this.unlockedPowers.size()>=3) {
            this.startingPowers.add(this.unlockedPowers.get(2));
            ScreenManager.clear();
            TextBox.afficherTextBox("Vous venez de débloquer une nouvelle compétence", this.unlockedPowers.get(2).getName()+" : "+this.unlockedPowers.get(2).getDescription(), "");
            Key key = null;
            try{
            do {
                key = KeyManager.getKey();
            } while (key==null || !key.equals(Key.ENTER));
            
        } catch (Exception e){e.printStackTrace();}
        }
    }

}
