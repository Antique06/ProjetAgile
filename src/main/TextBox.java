package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextBox {

    private static final String PATHTEXTBOX = "res/TextBox.txt";
    private static final String PATHBARREHAUTE = "res/BarreHaute.txt";
    


    private static BufferedReader textBox;
    private static BufferedReader barreHaute;

    public TextBox() {
        try {
            this.textBox = new BufferedReader(new FileReader(PATHTEXTBOX));
            this.barreHaute = new BufferedReader(new FileReader(PATHBARREHAUTE));
        } catch(IOException e) {
            System.err.println("IOExeption " + e);
        }
    }

    public static void afficherTextBox(String chemin) {
        try {
            BufferedReader textAafficher = new BufferedReader(new FileReader(chemin));
            ArrayList<String> ligne = new ArrayList<String>();
            while(textAafficher.ready()) {
                ligne.add(textAafficher.readLine());
            }
            for(int nbLigne = 0; nbLigne<ligne.size(); nbLigne = nbLigne+3) {
                afficherTextBox(ligne.get(nbLigne), ligne.get(nbLigne+1), ligne.get(nbLigne+2));
                Key key = null;
                System.out.println("Appuyer sur entrée pour continuer");
                do{
                    key = KeyManager.getKey();
                } while (key ==null || !key.equals(Key.ENTER));
                
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch(IOException e) {
            System.err.println("IOExeption " + e);
        }
    }

    public static void afficherTextBox(String texte1, String texte2, String texte3) {
        
        String[] listeText = new String[] {texte1, texte2, texte3};
        try {
            String text = "";
            int nbLigne = 0;
            textBox.mark(1);
            while(textBox.ready()) {
                text = textBox.readLine();
                for(int i=0; i<text.length(); i++) {
                    if(text.charAt(i) == 'µ') {
                        if(listeText[nbLigne].length()%2==1) {
                            System.out.print(" ");
                        }
                        int moitie = (180 - listeText[nbLigne].length()) / 2;
                        for(int j=0; j<moitie; j++) {
                            System.out.print(" ");
                        }
                        System.out.print(listeText[nbLigne]);
                        for(int j=0; j<moitie; j++) {
                            System.out.print(" ");
                        }
                        nbLigne++;
                    } else {
                        System.out.print(text.charAt(i));
                    }
                }
                System.out.println();
            }
            textBox.reset();
        } catch(IOException e) {
            System.err.println("IOExeption " + e);
        }
    }

    
    public static void afficherBarreHaute(Joueur j, Monstre m) {
        try {
            String text = "";
            int nbLigne = 0;
            String ligneAajouter = "";
            barreHaute.mark(1);
            String vieJoueur = "";
            String vieMonstre = "";
            while(barreHaute.ready()) {
                text = barreHaute.readLine();
                for(int i=0; i<text.length(); i++) {
                    if(text.charAt(i) == 'µ') {
                        if(nbLigne == 0) {
                            String ligneBiome = Main.getTower().getBiome().toString();
                            for(int b=0; b<18-ligneBiome.length(); b++) {
                                ligneBiome = ligneBiome + " ";
                            }
                            ligneAajouter = "                                                                                                        BIOME DE LA TOUR : " + ligneBiome + "                                                                                                  ";
                            System.out.print(ligneAajouter);
                        } else if(nbLigne == 1) {
                            vieJoueur = "" + j.getPV() + "/" + j.getPVmax();
                            for(int v=0; v<9-vieJoueur.length(); v++) {
                                vieJoueur = vieJoueur + " ";
                            }
                            vieMonstre = "" + m.getPV() + "/" + m.getPVmax();
                            for(int v=0; v<9-vieMonstre.length(); v++) {
                                vieMonstre = vieMonstre + " ";
                            }
                            ligneAajouter = "                PV JOUEUR = " + vieJoueur + "                                                                                                                                                                  PV MONSTRE = " + vieMonstre + "               ";
                            System.out.print(ligneAajouter);
                        } else if(nbLigne == 2) {
                            String ligneNBmonstreTuer = "" + Main.getTower().getNumberOfFight();
                            for(int b=0; b<5-ligneNBmonstreTuer.length(); b++) {
                                ligneNBmonstreTuer = ligneNBmonstreTuer + " ";
                            }
                            ligneAajouter = "                                                                                                      NOMBRE DE COMBAT RÉALISER : " + ligneNBmonstreTuer + "                                                                                                    ";
                            System.out.print(ligneAajouter);
                        }
                        nbLigne++;
                    } else {
                        System.out.print(text.charAt(i));
                    }
                }
                System.out.println();
            }
            barreHaute.reset();
        } catch(IOException e) {
            System.err.println("IOExeption " + e);
        }
    }

    public static void afficherJoueurMonstre(Joueur joueur, Monstre monstre) {
        try {
            String cheminSpriteJoueur = joueur.getRole().getSprite();
            String cheminSpriteMonstre = monstre.getSprite();
            BufferedReader spriteJoueur = new BufferedReader(new FileReader(cheminSpriteJoueur));
            BufferedReader spriteMonstre = new BufferedReader(new FileReader(cheminSpriteMonstre));
            spriteJoueur.mark(1000);
            spriteMonstre.mark(1000);
            int hauteurJoueur = 0, hauteurMonstre = 0;
            while(spriteJoueur.ready()) {
                spriteJoueur.readLine();
                hauteurJoueur++;
            }
            while(spriteMonstre.ready()) {
                spriteMonstre.readLine();
                hauteurMonstre++;
            }
            spriteJoueur.reset();
            spriteMonstre.reset();
            int hauteurJoueurManquante = 32 - hauteurJoueur;
            int hauteurMonstreManquante = 32 - hauteurMonstre;
            String ligne, ligneJoueur, ligneMonstre;
            for(int i=0; i<32; i++) {
                ligne = "                      ";
                if(hauteurJoueurManquante > 0) {
                    hauteurJoueurManquante--;
                    ligne = ligne + "                                            ";
                } else {
                    ligneJoueur = spriteJoueur.readLine();
                    ligne = ligne + ligneJoueur;
                    for(int x=0; x<44-ligneJoueur.length(); x++) {
                        ligne = ligne + " ";
                    }
                }
                ligne = ligne + "                                                                                                                                ";
                if(hauteurMonstreManquante > 0) {
                    hauteurMonstreManquante--;
                } else {
                    ligneMonstre = spriteMonstre.readLine();
                    ligne = ligne + ligneMonstre;
                }
                System.out.println(ligne);
            }
            spriteJoueur.reset();
            spriteMonstre.reset();
            spriteJoueur.close();
            spriteMonstre.close();
            // Monstre 
            // Hauteur max : 32
            // Largeur max : 82
            //
            //Joueur
            // Hauteur max : 27
            // Largeur max : 44
        } catch(FileNotFoundException e) {
            System.out.println("FileNotFoundException " + e);
        } catch(IOException e) {
            System.out.println("IOException " + e);
        }
    }

    public static void afficherHautCombat(Joueur j, Monstre m) {
        afficherBarreHaute(j, m);
        afficherJoueurMonstre(j, m);
        for(int i=0; i<3; i++) {
            System.out.println("");
        }
    }
}
