package main.role;

import java.util.ArrayList;
import java.util.List;

import main.Competence;
import main.Role;
import main.competence.Becvrille;
import main.competence.Benediction;
import main.competence.Coupdebec;
import main.competence.Hadoken;

public class Monk extends Role {

   
    

    public Monk() {
        super("Monk", "Un canard sage","res/personnages/canardmonk.txt",  new ArrayList<Competence>(), new ArrayList<Competence>());
        Competence f = new Coupdebec();
        this.startingPowers.add(f);
        Competence s = new Benediction();
        this.unlockablePowers.add(s);
        Competence fi = new Becvrille();
        this.unlockablePowers.add(fi);
        Competence me = new Hadoken();
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