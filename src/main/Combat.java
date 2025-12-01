package main;

import java.util.ArrayList;
import java.util.List;

public class Combat {

    Joueur joueur;
    Monstre monstre;
    public boolean exit = false;

    public Combat(Joueur joueur, Monstre monstre) {
        this.joueur = joueur;
        this.monstre = monstre;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public Monstre getMonstre() {
        return monstre;
    }

    public void Killed() {
        if (monstre.getVie().getPv() <= 0) {
            monstre.setEstVivant(false);
        }
        if (joueur.getVie().getPv() <= 0) {
            joueur.setEstVivant(false);
        }
    }

    public void monstreAttaque() {
        this.joueur.setVie(this.joueur.getVie().getPv() - this.monstre.getForce());
        ScreenManager.showStory(joueur, monstre, "au tour de "+this.monstre.getName()+"...", "le " + this.monstre.getName() + " attaque !", "il vous reste " + joueur.getVie().getPv() + " PV");
        Killed();
    }

    private CombatState combatState = CombatState.ATTAQUER;


    public String showMenu(){

        StringBuilder res = new StringBuilder();

        res.append("                                                                                                                                 \n");
        res.append("                            ==========================                            ==========================                     \n");
        if (combatState.equals(CombatState.ATTAQUER)){
        res.append("                            ==     ■■■■■■■■■■■■     ==                            ==                      ==                     \n");
        res.append("                            ==     ■ ATTAQUER ■     ==                            ==      COMPETENCE      ==                     \n");
        res.append("                            ==     ■■■■■■■■■■■■     ==                            ==                      ==                     \n");
        } else if (combatState.equals(CombatState.COMPETENCE)){
        res.append("                            ==                      ==                            ==     ■■■■■■■■■■■■     ==                     \n");
        res.append("                            ==       ATTAQUER       ==                            ==     ■COMPETENCE■     ==                     \n");
        res.append("                            ==                      ==                            ==     ■■■■■■■■■■■■     ==                     \n");
        }

        res.append("                            ==========================                            ==========================                     \n");
        res.append("                                                                                                                                 \n");

        return ""+res;
    }

    private CompetenceState competenceState = CompetenceState.COMPETENCE1;

    public String showCompetence(List<Competence> competences){

        

        StringBuilder res = new StringBuilder();
        List<String> list=new ArrayList<>();

        for (Competence competence : competences){
            list.add(competence.getName());
        }

        List<String> listStringCompetence = StringManager.viewChoiceBox(list, 22);

        // for (Competence competence : competences){
        //     String name = competence.getName();
        //     String nameres = "";
        //     int valueLack = 22 - name.length();
        //     StringBuilder space = new StringBuilder();
        //     if (name.length()<=22){
        //         for (int i = 0; i<valueLack/2; i++) {
        //                 space.append(" ");
        //             }
        //         if (valueLack%2==0){
                    
        //             nameres = space + name + space;

        //         } else {
        //             nameres = space + name + space + " ";
        //         }
                
        //     } else {
        //         nameres = name.substring(0, 23);
        //     }
        //     listStringCompetence.add(nameres);
        // }


        res.append("                                                                                                                                 \n");
        res.append("  ==========================     ==========================     ==========================     ==========================        \n");
        if (competenceState.equals(CompetenceState.COMPETENCE1)){
        res.append("  ==■■■■■■■■■■■■■■■■■■■■■■==     ==                      ==     ==                      ==     ==                      ==        \n");
        } else if (competenceState.equals(CompetenceState.COMPETENCE2)){
        res.append("  ==                      ==     ==■■■■■■■■■■■■■■■■■■■■■■==     ==                      ==     ==                      ==        \n");
        } else if (competenceState.equals(CompetenceState.COMPETENCE3)){
        res.append("  ==                      ==     ==                      ==     ==■■■■■■■■■■■■■■■■■■■■■■==     ==                      ==        \n");
        } else if (competenceState.equals(CompetenceState.COMPETENCE4)){
        res.append("  ==                      ==     ==                      ==     ==                      ==     ==■■■■■■■■■■■■■■■■■■■■■■==        \n");
        }
        
        res.append("  =="+(listStringCompetence.size()>=1 ? listStringCompetence.get(0) : "                      ")+"==     =="+(listStringCompetence.size()>=2 ? listStringCompetence.get(1) : "                      ")+"==     =="+(listStringCompetence.size()>=3 ? listStringCompetence.get(2) : "                      ")+"==     =="+(listStringCompetence.size()>=4 ? listStringCompetence.get(3) : "                      ")+"==        \n");
        


        if (competenceState.equals(CompetenceState.COMPETENCE1)){
        res.append("  ==■■■■■■■■■■■■■■■■■■■■■■==     ==                      ==     ==                      ==     ==                      ==        \n");
        } else if (competenceState.equals(CompetenceState.COMPETENCE2)){
        res.append("  ==                      ==     ==■■■■■■■■■■■■■■■■■■■■■■==     ==                      ==     ==                      ==        \n");
        } else if (competenceState.equals(CompetenceState.COMPETENCE3)){
        res.append("  ==                      ==     ==                      ==     ==■■■■■■■■■■■■■■■■■■■■■■==     ==                      ==        \n");
        } else if (competenceState.equals(CompetenceState.COMPETENCE4)){
        res.append("  ==                      ==     ==                      ==     ==                      ==     ==■■■■■■■■■■■■■■■■■■■■■■==        \n");
        }
        res.append("  ==========================     ==========================     ==========================     ==========================        \n");
        res.append("                                                                                                                                 \n");
        res.append("                                                                                                                                 \n");

        return res+"";
    }

    public void joueurAttaque() {
        
        boolean back = false;
        do {    
            if (back){
                back = false;
                ScreenManager.showChoice(joueur, monstre, showMenu());
            }
        
        
        boolean select = false;
        do {
            Key key = null;
        do {
            try {
                key = KeyManager.getKey();
            } catch (Exception e){
                System.out.println("get fuck");
                e.printStackTrace();
            }
            
        } while (key == null || ( !key.equals(Key.LEFT) && !key.equals(Key.RIGHT) && !key.equals(Key.ENTER) && !key.equals(Key.BACKSPACE)));
        if (key.equals(Key.LEFT)) combatState=CombatState.ATTAQUER;
        if (key.equals(Key.RIGHT)) combatState=CombatState.COMPETENCE;
        if (key.equals(Key.BACKSPACE)) {


ScreenManager.clear();
            TextBox.afficherTextBox("Êtes vous sûre de vouloir quitter la partie (elle sera enregistré depuis la dernière victoire)", "", "OUI = ENTRÉE, NON = RETOUR");
            Key stop = null;
            try{
            do {
                stop = KeyManager.getKey();
            } while (stop==null || (!stop.equals(Key.ENTER) && !stop.equals(Key.BACKSPACE)));
            if (stop.equals(Key.ENTER)){
                exit = true;
            }
            
        } catch (Exception e){e.printStackTrace();}



        }
        
        if (!key.equals(Key.ENTER) && !key.equals(Key.BACKSPACE)){
            ScreenManager.showChoice(joueur, monstre, showMenu());
            
            
            
        } 
        else select=true;
        }while (!select && !exit);
        
        if (!exit){
        if (combatState.equals(CombatState.ATTAQUER)) {
            monstre.getVie().removePv(this.joueur.getDegat());

            ScreenManager.showStory(joueur, monstre, "vous attaquez avec succès !", "Vous avez infligé "+ this.joueur.getDegat() +" dégâts", "Appuyez sur entrée pour continuer");

        } else if (combatState.equals(CombatState.COMPETENCE)) {
            Competence competence = choixComp(this.joueur.getComp());
            if (competence == null) back = true;
            else {
                            compAction(competence);

            ScreenManager.showStory(joueur, monstre, "Vous avez utilisé votre compétence : "+competence.getName(),competence.getDescription(), "Appuyez sur entrée pour continuer");
            }

        } 
        if (!back) Killed();
    }
        } while (back);
        
    }

    public void combat() {
        ScreenManager.showStory(joueur, monstre, monstre.getName()+" fait face à vous.", "c'est le tour de " + this.joueur.getName(),"Appuyez sur entrée pour continuer");
        
        // System.out.println(this.monstre.getName() + " apparaît.");
        // System.out.println("Le combat commence.");
        do {
            ScreenManager.showChoice(joueur, monstre, showMenu());
            joueurAttaque();
            
            if (!exit){
            
            if (!monstre.getEstVivant()) {
                ScreenManager.showStory(joueur, monstre, "il reste " + monstre.getVie().getPv() + " PV au monstre", this.monstre.getName()+" est mort !", "Appuyez sur entrée pour continuer");
            } else {
                ScreenManager.showStory(joueur, monstre, "il reste " + monstre.getVie().getPv() + " PV au monstre", "", "Appuyez pour continuer");
            }

            if (monstre.getEstVivant()) {
                monstreAttaque();
            }
        }
        } while (this.joueur.getEstVivant() && this.monstre.getEstVivant() && !exit);
        if (!exit){
        joueur.DebloComp();
        
        } else {
            Main.getTower().exit=true;
        }
        
    }

    public Competence choixComp(List<Competence> comp) {
        boolean select = false;

        try {
            Key key = null;
            do{
                ScreenManager.showChoice(joueur, monstre, showCompetence(comp));

            

            

        
        
        do {
            key = KeyManager.getKey();
        } while (key==null || (!key.equals(Key.LEFT) && !key.equals(Key.RIGHT) && !key.equals(Key.ENTER) && !key.equals(Key.BACKSPACE)));

            if (key!=null && key.equals(Key.RIGHT)) {
                if (((competenceState.ordinal() + 1) % CompetenceState.values().length) >= 0) { competenceState = CompetenceState.values()[((competenceState.ordinal() + 1) % CompetenceState.values().length)]; }else{competenceState = CompetenceState.values()[((competenceState.ordinal() + 1) % CompetenceState.values().length)+CompetenceState.values().length];}
            } else if (key!=null && key.equals(Key.LEFT)) {
                if (((competenceState.ordinal() - 1) % CompetenceState.values().length) >= 0) { competenceState = CompetenceState.values()[((competenceState.ordinal() - 1) % CompetenceState.values().length)]; }else{competenceState = CompetenceState.values()[((competenceState.ordinal() - 1) % CompetenceState.values().length)+CompetenceState.values().length];}
            } else if (key != null && key.equals(Key.BACKSPACE)){
                select=true;
                ScreenManager.showChoice(joueur, monstre, showMenu());
                return null;
            } else {
                if ((comp.size()>=1 && competenceState.equals(CompetenceState.COMPETENCE1)) || 
                (comp.size()>=2 && competenceState.equals(CompetenceState.COMPETENCE2)) || 
                (comp.size()>=3 && competenceState.equals(CompetenceState.COMPETENCE3)) || 
                (comp.size()>=4 && competenceState.equals(CompetenceState.COMPETENCE4)) 
                )
                select = true;
            }
    } while (!select);

    if (competenceState.equals(CompetenceState.COMPETENCE1)){
        return comp.get(0);
    } else if (competenceState.equals(CompetenceState.COMPETENCE2)){
        return comp.get(1);
    } else if (competenceState.equals(CompetenceState.COMPETENCE3)){
        return comp.get(2);
    } else if (competenceState.equals(CompetenceState.COMPETENCE4)){
        return comp.get(3);
    }
    System.out.println("problème");
    return null;
       
    } catch (Exception e){
        e.printStackTrace();
        return comp.get(0);
    }
    }

    public void compAction(Competence comp) {
        int val = comp.getValue();
        if (val == 0){
            val = Main.getRandom().nextInt(301);
        }
        boolean degat = comp.isDamage();
        if (degat) {
            this.monstre.setVie(this.monstre.getVie().getPv() - val);
        } else {
            this.joueur.setVie(this.joueur.getVie().getPv() + val);
            System.out.println("vie restaurée ! vous avez " + this.joueur.getVie().getPv() + " PV");
        }
    }
}
