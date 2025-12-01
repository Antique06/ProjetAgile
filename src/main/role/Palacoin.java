package main.role;

import java.util.ArrayList;
import java.util.List;

import main.Competence;
import main.Role;
import main.competence.Acoinllon;
import main.competence.Cointetsuken;
import main.competence.Epeelumiere;
import main.competence.Priere;

public class Palacoin extends Role {

 
    public Palacoin() {
        super("Palacoin", "Un canard avec une grosse épée","res/personnages/palacoin.txt",  new ArrayList<Competence>(), new ArrayList<Competence>());
        Competence f = new Epeelumiere();
        this.startingPowers.add(f);
        Competence s = new Priere();
        this.unlockablePowers.add(s);
        Competence fi = new Acoinllon();
        this.unlockablePowers.add(fi);
        Competence me = new Cointetsuken();
        this.unlockablePowers.add(me);
    }

    @Override
    public String toString() {
        return this.getNom() + " " + this.getDescription() + "\n" + this.sprite + "\n" + this.startingPowers.toString();

    }

    public static void main(String[] args) {
        Palacoin m = new Palacoin();
        System.out.println(m.toString());

        m.addCompetence(m.unlockablePowers.get(0));
        System.out.println(m.toString());

    }

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
