package main;

public class Save {
    Joueur joueur;
    Combat combat;
    Tower tour;
    public Save(Joueur joueur, Tower tour) {
        this.joueur = joueur;
        this.tour = tour;
    }

    public void saving(){
        JsonManager<Save> JSN = new JsonManager<Save>(Save.class);
        JSN.save(this);
    }

    public Joueur getJoueur() {
        return joueur;
    }





}
