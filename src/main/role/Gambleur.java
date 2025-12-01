package main.role;

import java.util.ArrayList;
import java.util.List;

import main.Competence;
import main.Role;
import main.competence.piochecoeur;
import main.competence.piochepic;

public class Gambleur extends Role {


    

    public Gambleur() {
        super("Gambleur", "Un canard super chanceux","res/personnages/gambleur.txt",  new ArrayList<Competence>(), new ArrayList<Competence>());
        Competence f = new piochepic();
        this.startingPowers.add(f);
        Competence s = new piochecoeur();
        this.unlockablePowers.add(s);
    }

    @Override
    public String toString() {
        return this.getNom() + " " + this.getDescription() + "\n" + this.sprite + "\n" + this.startingPowers.toString();

    }

    /*public static void main(String[] args) {
        Magicoin m = new Magicoin();
        System.out.println(m.toString());

        m.addCompetence(m.unlockablePowers.get(0));
        System.out.println(m.toString());

    }*/

    @Override
    public void addCompetence(Competence comp) {
        if (this.startingPowers.contains(comp)) {
            System.out.println("La compétence est déjà présente");
        } else {
            this.startingPowers.add(comp);
            System.out.println("La compétence est déjà présente");
        }
    }
}
