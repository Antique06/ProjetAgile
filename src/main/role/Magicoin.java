package main.role;

import java.util.ArrayList;
import java.util.List;

import main.Competence;
import main.Role;
import main.competence.Fireball;
import main.competence.Foudre;
import main.competence.Megaquack;
import main.competence.Soin;

public class Magicoin extends Role {


    public Magicoin() {
        super("Magicoin", "Un canard avec un baton et un beau chapeau","res/personnages/canardmage.txt",  new ArrayList<Competence>(), new ArrayList<Competence>());
        Competence f = new Foudre();
        this.startingPowers.add(f);
        Competence s = new Soin();
        this.unlockablePowers.add(s);
        Competence fi = new Fireball();
        this.unlockablePowers.add(fi);
        Competence me = new Megaquack();
        this.unlockablePowers.add(me);
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
