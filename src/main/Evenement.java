package main;

public class Evenement {
    String phrase;
    String[] reponsePossible;
    String[] resultatPossible;

    public Evenement(String phrase, String[] reponsePossible, String[] resultatPossible) {
        this.phrase = phrase;
        this.reponsePossible = reponsePossible;
        this.resultatPossible = resultatPossible;
    }

    public boolean reponseEstValide(String reponse) {
        boolean ok = false;
        int i=0;
        while(!ok && i<this.reponsePossible.length) {
            if(this.reponsePossible[i].equals(reponse)) {
                ok = true;
            }
            i++;
        }
        return ok;
    }

    public String getResultat(int i) { //TODO à modifier
        return resultatPossible[i];
    }

    public String getPhrase() {
        return this.phrase;
    }

    public String getReponsePossible() {
        String retour = this.reponsePossible[0];
        for(int i=1; i<this.reponsePossible.length; i++) {
            retour += " / " + this.reponsePossible[i];
        }
        return retour;
    }

    public static void main(String[] args) {
        JsonManager<Evenement> JSM = new JsonManager<Evenement>(Evenement.class);
        JSM.saveToFile("res/EvenementPlaine.json", new Evenement("Un rugissement résonne dans la tour, rappelant la menace des créatures sauvages dehors, ce qui fait bouger certains monstres. Que souhaitez-vous faire ?",
                                                                new String[] {"partir", "rester"},
                                                                new String[] {"rien", "degats"}));
        JSM.saveToFile("res/EvenementPlaine.json", new Evenement("Tu trouves un nid avec des œufs, ce qui pourrait attirer des monstres ou réveiller une créature mère. Que souhaitez-vous faire ?",
                                                                new String[] {"prendre", "laisser"},
                                                                new String[] {"heal", "degats"}));
        JSM.saveToFile("res/EvenementPlaine.json", new Evenement("Tu entends des pas lourds résonner dans la grande salle, ce qui te laisse deviner la taille et le nombre des monstres à venir. Que souhaitez-vous faire ?",
                                                                new String[] {"partir", "aller"},
                                                                new String[] {"rien", "degats"}));
        JSM.saveToFile("res/EvenementPlaine.json", new Evenement("Dans une forêt, vous pouvez soit continuer votre chemin, soit tourner. Que souhaitez-vous faire ?",
                                                                new String[] {"continuer", "tourner"},
                                                                new String[] {"heal", "degats"}));
        JSM.saveToFile("res/EvenementPlaine.json", new Evenement("Pendant que vous vous reposez, un monstre sauvage vous saute dessus. Comment réagisez-vous ?",
                                                                new String[] {"esquiver", "contrer"},
                                                                new String[] {"rien", "degats"}));
        JSM.saveToFile("res/EvenementPlaine.json", new Evenement("En vous baladant, vous remarquer un champignon qui a l'air différend des autre, Que souhaitez-vous faire ?",
                                                                new String[] {"manger", "laisser"},
                                                                new String[] {"heal", "rien"}));
        JSM.saveToFile("res/EvenementPlaine.json", new Evenement("Lors d'une balade nocturne, vous entendez un bruit de caillou tomber par terre. Que souhaitez-vous faire ?",
                                                                new String[] {"ignorer", "aller"},
                                                                new String[] {"rien", "degats"}));
        JSM.saveToFile("res/EvenementPlaine.json", new Evenement("Une torche au sol semble encore chaude, signe d'une présence récente. La prudence est de mise. Que fais-tu ?",
                                                                new String[] {"examiner", "partir"},
                                                                new String[] {"degats", "rien"}));
        JSM.saveToFile("res/EvenementPlaine.json", new Evenement("Un vieux puits attire ton attention. Tu entends de l’eau couler au fond. Que fais-tu ?",
                                                                new String[] {"descendre", "ignorer"},
                                                                new String[] {"heal", "rien"}));
        JSM.saveToFile("res/EvenementPlaine.json", new Evenement("Un papillon lumineux vole près de toi, dégageant une douce chaleur. Tu sens qu’il pourrait t’aider... ou te piéger. Que fais-tu ?",
                                                                new String[] {"le suivre", "l’ignorer"},
                                                                new String[] {"heal", "rien"}));
        JSM.saveToFile("res/EvenementPlaine.json", new Evenement("Un grincement métallique se fait entendre derrière une porte rouillée. Que choisis-tu de faire ?",
                                                                new String[] {"ouvrir", "reculer"},
                                                                new String[] {"degats", "rien"}));
        JSM.saveToFile("res/EvenementPlaine.json", new Evenement("Tu découvres une étagère poussiéreuse avec des fioles colorées. L'une semble intacte. Que fais-tu ?",
                                                                new String[] {"boire", "laisser"},
                                                                new String[] {"heal", "rien"}));
        JSM.saveToFile("res/EvenementPlaine.json", new Evenement("Une ombre furtive traverse le couloir derrière toi. Tu n’es pas seul. Que fais-tu ?",
                                                                new String[] {"fuir", "observer"},
                                                                new String[] {"rien", "degats"}));
        JSM.saveToFile("res/EvenementPlaine.json", new Evenement("Une salle remplie de miroirs déformants te déstabilise. Ton reflet t’imite avec un léger retard. Que fais-tu ?",
                                                                new String[] {"toucher le miroir", "reculer"},
                                                                new String[] {"degats", "rien"}));


        JSM.saveToFile("res/EvenementGlace.json", new Evenement("Un hurlement glacé résonne dans la vallée gelée, rappelant la menace des créatures sauvages des neiges, ce qui fait bouger certains monstres. Que souhaitez-vous faire ?",
                                                        new String[] {"partir", "rester"},
                                                        new String[] {"rien", "degats"}));
        JSM.saveToFile("res/EvenementGlace.json", new Evenement("Tu trouves un nid gelé contenant des œufs recouverts de givre, ce qui pourrait attirer une créature polaire en colère. Que souhaites-tu faire ?",
                                                                new String[] {"prendre", "laisser"},
                                                                new String[] {"heal", "degats"}));
        JSM.saveToFile("res/EvenementGlace.json", new Evenement("Tu entends des pas lourds résonner dans la neige compacte, trahissant la taille et le nombre des créatures glacées qui approchent. Que souhaites-tu faire ?",
                                                                new String[] {"partir", "aller"},
                                                                new String[] {"rien", "degats"}));
        JSM.saveToFile("res/EvenementGlace.json", new Evenement("Dans une forêt enneigée, vous pouvez soit continuer votre chemin, soit bifurquer dans un passage couvert de glace. Que souhaitez-vous faire ?",
                                                                new String[] {"continuer", "tourner"},
                                                                new String[] {"heal", "degats"}));
        JSM.saveToFile("res/EvenementGlace.json", new Evenement("Alors que vous vous reposez, une bête des neiges surgit et vous attaque par surprise. Comment réagissez-vous ?",
                                                                new String[] {"esquiver", "contrer"},
                                                                new String[] {"rien", "degats"}));
        JSM.saveToFile("res/EvenementGlace.json", new Evenement("En explorant, vous remarquez un champignon gelé aux reflets bleutés, différent des autres. Que souhaitez-vous faire ?",
                                                                new String[] {"manger", "laisser"},
                                                                new String[] {"heal", "rien"}));
        JSM.saveToFile("res/EvenementGlace.json", new Evenement("Lors d’une marche nocturne dans la neige, un bloc de glace se détache et tombe lourdement. Que souhaitez-vous faire ?",
                                                                new String[] {"ignorer", "aller"},
                                                                new String[] {"rien", "degats"}));
        JSM.saveToFile("res/EvenementGlace.json", new Evenement("Un feu de camp à moitié éteint fume encore dans la neige, signe d’une présence récente. La prudence est de mise. Que fais-tu ?",
                                                                new String[] {"examiner", "partir"},
                                                                new String[] {"degats", "rien"}));
        JSM.saveToFile("res/EvenementGlace.json", new Evenement("Un puits gelé attire ton attention. Tu entends de l’eau encore active sous la glace. Que fais-tu ?",
                                                                new String[] {"descendre", "ignorer"},
                                                                new String[] {"heal", "rien"}));
        JSM.saveToFile("res/EvenementGlace.json", new Evenement("Un papillon cristallin vole près de toi, diffusant une douce lumière glacée. Il pourrait t’aider... ou te piéger. Que fais-tu ?",
                                                                new String[] {"le suivre", "l’ignorer"},
                                                                new String[] {"heal", "rien"}));
        JSM.saveToFile("res/EvenementGlace.json", new Evenement("Un grincement métallique se fait entendre derrière une lourde porte gelée. Que choisis-tu de faire ?",
                                                                new String[] {"ouvrir", "reculer"},
                                                                new String[] {"degats", "rien"}));
        JSM.saveToFile("res/EvenementGlace.json", new Evenement("Tu découvres une étagère enneigée avec des fioles givrées. L’une semble intacte. Que fais-tu ?",
                                                                new String[] {"boire", "laisser"},
                                                                new String[] {"heal", "rien"}));
        JSM.saveToFile("res/EvenementGlace.json", new Evenement("Une ombre glisse furtivement derrière un mur de glace. Tu n’es pas seul. Que fais-tu ?",
                                                                new String[] {"fuir", "observer"},
                                                                new String[] {"rien", "degats"}));
        JSM.saveToFile("res/EvenementGlace.json", new Evenement("Une caverne remplie de miroirs de glace t’entoure. Ton reflet semble bouger avec un léger retard. Que fais-tu ?",
                                                                new String[] {"toucher le miroir", "reculer"},
                                                                new String[] {"degats", "rien"}));


        JSM.saveToFile("res/EvenementEnfer.json", new Evenement("Un rugissement démoniaque résonne dans les profondeurs, rappelant la menace des créatures infernales, ce qui fait bouger certains monstres. Que souhaitez-vous faire ?",
                                                                new String[] {"partir", "rester"},
                                                                new String[] {"rien", "degats"}));
        JSM.saveToFile("res/EvenementEnfer.json", new Evenement("Tu trouves un nid calciné contenant des œufs embrasés, ce qui pourrait attirer une créature de feu. Que souhaites-tu faire ?",
                                                                new String[] {"prendre", "laisser"},
                                                                new String[] {"heal", "degats"}));
        JSM.saveToFile("res/EvenementEnfer.json", new Evenement("Tu entends des pas lourds résonner sur la roche volcanique, trahissant la puissance et le nombre des créatures infernales qui approchent. Que souhaites-tu faire ?",
                                                                new String[] {"partir", "aller"},
                                                                new String[] {"rien", "degats"}));
        JSM.saveToFile("res/EvenementEnfer.json", new Evenement("Dans un dédale de roches en fusion, vous pouvez soit continuer votre chemin, soit bifurquer vers un couloir embrasé. Que souhaitez-vous faire ?",
                                                                new String[] {"continuer", "tourner"},
                                                                new String[] {"heal", "degats"}));
        JSM.saveToFile("res/EvenementEnfer.json", new Evenement("Alors que vous vous reposez, une bête enflammée surgit et vous attaque par surprise. Comment réagissez-vous ?",
                                                                new String[] {"esquiver", "contrer"},
                                                                new String[] {"rien", "degats"}));
        JSM.saveToFile("res/EvenementEnfer.json", new Evenement("En explorant, vous remarquez un champignon incandescent aux reflets rougeoyants, différent des autres. Que souhaitez-vous faire ?",
                                                                new String[] {"manger", "laisser"},
                                                                new String[] {"heal", "rien"}));
        JSM.saveToFile("res/EvenementEnfer.json", new Evenement("Lors d’une marche dans les entrailles brûlantes, une pierre en fusion tombe du plafond. Que souhaitez-vous faire ?",
                                                                new String[] {"ignorer", "aller"},
                                                                new String[] {"rien", "degats"}));
        JSM.saveToFile("res/EvenementEnfer.json", new Evenement("Un brasier encore fumant crépite dans un recoin de la caverne, signe d’une présence récente. La prudence est de mise. Que fais-tu ?",
                                                                new String[] {"examiner", "partir"},
                                                                new String[] {"degats", "rien"}));
        JSM.saveToFile("res/EvenementEnfer.json", new Evenement("Un gouffre incandescent attire ton attention. Tu entends de la lave bouillonner au fond. Que fais-tu ?",
                                                                new String[] {"descendre", "ignorer"},
                                                                new String[] {"heal", "rien"}));
        JSM.saveToFile("res/EvenementEnfer.json", new Evenement("Une flamme spectrale danse près de toi, irradiant une énergie étrange. Elle pourrait t’aider... ou te consumer. Que fais-tu ?",
                                                                new String[] {"la suivre", "l’ignorer"},
                                                                new String[] {"heal", "rien"}));
        JSM.saveToFile("res/EvenementEnfer.json", new Evenement("Un grincement métallique résonne derrière une lourde porte de basalte brûlant. Que choisis-tu de faire ?",
                                                                new String[] {"ouvrir", "reculer"},
                                                                new String[] {"degats", "rien"}));
        JSM.saveToFile("res/EvenementEnfer.json", new Evenement("Tu découvres une étagère carbonisée avec des fioles encore brûlantes. L’une semble intacte. Que fais-tu ?",
                                                                new String[] {"boire", "laisser"},
                                                                new String[] {"heal", "rien"}));
        JSM.saveToFile("res/EvenementEnfer.json", new Evenement("Une ombre démoniaque traverse les flammes derrière toi. Tu n’es pas seul. Que fais-tu ?",
                                                                new String[] {"fuir", "observer"},
                                                                new String[] {"rien", "degats"}));
        JSM.saveToFile("res/EvenementEnfer.json", new Evenement("Une salle aux parois de lave solidifiée reflète ton image dans des cristaux ardents. Ton reflet bouge avec un léger retard. Que fais-tu ?",
                                                                new String[] {"toucher le cristal", "reculer"},
                                                                new String[] {"degats", "rien"}));
    }
}