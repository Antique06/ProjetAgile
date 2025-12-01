package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.platform.console.shadow.picocli.CommandLine.Help.Ansi.Text;

public class Tower {
    Biome biome; // PRIMARY KEY
    int numberOfFight = 0;
    Monstre[] listeMonstre;
    List<Evenement> events;
    private final List<Monstre> MONSTERSLIST;
    private final Boss miniBoss;
    private final Boss boss;
    private Joueur joueur;
    public boolean exit;

    public Tower(Joueur joueur, Biome biome) {
        this.biome = biome;
        this.joueur = joueur;
        this.joueur = joueur;
        this.MONSTERSLIST = generateMonsterList();
        this.miniBoss = generateRandomMiniBoss();
        this.boss = generateBoss();
        final JsonManager<Evenement> JSM = new JsonManager<>(Evenement.class);
        if (biome.equals(Biome.PLAINE)) {
            this.events = JSM.loadFromFile("res/EvenementPlaine.json");
        } else if (biome.equals(Biome.ENFER)) {
            this.events = JSM.loadFromFile("res/EvenementEnfer.json");
        } else if (biome.equals(Biome.GLACIER)) {
            this.events = JSM.loadFromFile("res/EvenementGlace.json");
        }

    }

    public int getNumberOfFight() {
        return numberOfFight;
    }

    public Biome getBiome() {
        return biome;
    }

    private Boss generateBoss() {
        final JsonManager<Boss> JSM = new JsonManager<Boss>(Boss.class);
        return JSM.loadFromFile("res/Boss.json").get(new Random().nextInt(JSM.loadFromFile("res/Boss.json").size()));
    }

    private Boss generateRandomMiniBoss() {
        final JsonManager<Boss> JSM = new JsonManager<Boss>(Boss.class);
        return JSM.loadFromFile("res/MiniBoss.json")
                .get(new Random().nextInt(JSM.loadFromFile("res/MiniBoss.json").size()));
    }

    private List<Monstre> generateMonsterList() {
        final JsonManager<Monstre> JSM = new JsonManager<Monstre>(Monstre.class);
        return JSM.loadFromFile("res/Monster.json");
    }

    public Monstre randomMonster() {
        Monstre monstr = MONSTERSLIST.remove(new Random().nextInt(MONSTERSLIST.size()));

        monstr.generate(numberOfFight / 7 + 1);

        return monstr;
    }

    public void rush(Joueur joueur, Biome biome) {
        ScreenManager.clear();
        switch (biome) {
            case PLAINE:
                TextBox.afficherTextBox("", "Vous venez de rentrer dans la tour de la mousse",
                        "Appuyez sur entrée pour continuer");
                break;
            case GLACIER:
                TextBox.afficherTextBox("", "Vous venez de rentrer dans la tour du glacier",
                        "Appuyez sur entrée pour continuer");
                break;
            case ENFER:
                TextBox.afficherTextBox("", "Vous venez de rentrer dans la tour des enfer",
                        "Appuyez sur entrée pour continuer");
                break;

            default:
                TextBox.afficherTextBox("", "Vous venez de rentrer dans la tour du vide",
                        "Appuyez sur entrée pour continuer");
                break;
        }
        Key keya = null;
        try {
            do {
                keya = KeyManager.getKey();
            } while (keya == null || !keya.equals(Key.ENTER));
            ScreenManager.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (joueur.getEstVivant() && numberOfFight < 10 && !exit) {
            if (numberOfFight == 5) {
                if (biome.equals(Biome.PLAINE))
                    miniBoss.generate(3);
                else if (biome.equals(Biome.GLACIER))
                    miniBoss.generate(4);
                else
                    miniBoss.generate(5);
                ScreenManager.clear();
                TextBox.afficherTextBox("Un grand monstre semble s'approcher au loin.",
                        "D'un coup, vous comprenez que vous avez à faire au Mini Boss du coin",
                        "Appuyez sur entrée pour continuer");
                try {
                    Key wait = null;
                    do {
                        wait = KeyManager.getKey();
                    } while (!wait.equals(Key.ENTER));
                    Combat combat = new Combat(joueur, miniBoss);
                    combat.combat();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Monstre monster = randomMonster();

                Combat combat = new Combat(joueur, monster);
                combat.combat();

            }
            if (joueur.getEstVivant() && !exit) {

                numberOfFight++;
                joueur.setNbMonsterKill(joueur.getNbMonsterKill() + 1);
                joueur.addPv(5);
                Evenement event = generateEvent();

                // do {
                // System.out.println("\nRéponses possibles : " + event.getReponsePossible());
                // reponse = Main.getScanner().nextLine();
                // } while (!event.reponseEstValide(reponse));
                boolean select = false;
                Key key = null;
                try {
                    do {
                        ScreenManager.clear();
                        System.out.println(event.getPhrase());
                        showChoix(event);
                        do {
                            key = KeyManager.getKey();
                        } while (key == null
                                || (!key.equals(Key.LEFT) && !key.equals(Key.RIGHT) && !key.equals(Key.ENTER)));
                        if (key.equals(Key.LEFT))
                            eventState = EventState.REP1;
                        if (key.equals(Key.RIGHT))
                            eventState = EventState.REP2;
                        if (key.equals(Key.ENTER))
                            select = true;
                    } while (!select);
                    String resultat = "";
                    if (eventState == EventState.REP1) {
                        System.out.println("E1");
                        resultat = event.getResultat(0);
                    } else if (eventState == EventState.REP2) {
                        System.out.println("E1");
                        resultat = event.getResultat(1);
                    }
                    System.out.println(resultat);
                    if (resultat.equals("heal")) {
                        System.out.println("E2");
                        joueur.addPv(10);
                        ScreenManager.clear();
                        TextBox.afficherTextBox("Cette action semble vous avoir soigné", "Vous avez gagné 10 pv",
                                "Gagner contre ce monstre vous a fait gagné 5 pv supplémentaire");
                    } else if (resultat.equals("degats")) {
                        System.out.println("E2");
                        joueur.setVie(joueur.getPV() - 20);
                        if(joueur.getPV() <= 0) {
                            joueur.setEstVivant(false);
                        }
                        ScreenManager.clear();
                        TextBox.afficherTextBox("Cette action semble vous avoir blessé", "Vous avez perdu 20 pv",
                                "Gagner contre ce monstre vous a fait gagné 5 pv supplémentaire");
                    } else {
                        ScreenManager.clear();
                        TextBox.afficherTextBox("Cette action ne vous rapporte rien", "",
                                "Gagner contre ce monstre vous a fait gagné 5 pv supplémentaire");
                    }
                    Key wait = null;
                    do {
                        wait = KeyManager.getKey();
                    } while (!wait.equals(Key.ENTER));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (!exit){
                JsonManager<Save> JSM = new JsonManager<Save>(Save.class);
                JSM.save(new Save(joueur, this));
            }
            
        }
        if (joueur.getEstVivant() && !exit) {
            if (numberOfFight == 10) {
                if (biome.equals(Biome.PLAINE))
                    boss.generate(5);
                else if (biome.equals(Biome.GLACIER))
                    boss.generate(6);
                else
                    boss.generate(7);
                ScreenManager.clear();
                TextBox.afficherTextBox("Un énorme monstre se présente à proximité",
                        "Il semble hyper menaçant, comme-ci, il était le roi de cette tour.",
                        "Appuyez sur entrée pour continuer");
                try {
                    Key wait = null;
                    do {
                        wait = KeyManager.getKey();
                    } while (!wait.equals(Key.ENTER));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Combat combat = new Combat(joueur, boss);
                combat.combat();
            }
        }
        try {
            if (joueur.getEstVivant() && !exit) {
                ScreenManager.clear();
                JsonManager<Save> JSM = new JsonManager<Save>(Save.class);
                JSM.save(new Save(joueur, this));
                BufferedReader victoire = new BufferedReader(new FileReader("res/Victoire.txt"));
                ScreenManager.clear();
                while (victoire.ready()) {
                    System.out.println(victoire.readLine());
                }
                Key key = null;
                do {
                    key = KeyManager.getKey();
                } while (key == null || !key.equals(Key.ENTER));
                victoire.close();
            } else if (!exit){
                ScreenManager.clear();
                JsonManager<Save> JSM = new JsonManager<Save>(Save.class);
                JSM.removeSave(new Save(joueur, this));
                BufferedReader defaite = new BufferedReader(new FileReader("res/Defaite.txt"));
                while (defaite.ready()) {
                    System.out.println(defaite.readLine());
                }
                Key key = null;
                do {
                    key = KeyManager.getKey();

                } while (key == null || (!key.equals(Key.LEFT) && !key.equals(Key.RIGHT) && !key.equals(Key.ENTER)));
                defaite.close();
            }
            
        } catch (IOException e) {
            System.out.println("IOException " + e);
        }
    }

    EventState eventState = EventState.REP1;

    private void showChoix(Evenement evenement) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < evenement.reponsePossible.length; i++) {
            list.add(evenement.reponsePossible[i]);
        }
        List<String> listAnswer = StringManager.viewChoiceBox(list, 22);
        StringBuilder res = new StringBuilder();
        res.append(
                "                                                                                                                                 \n");
        res.append(
                "                       ==========================                            ==========================                     \n");

        if (eventState.equals(EventState.REP1)) {
            res.append(
                    "                       ==■■■■■■■■■■■■■■■■■■■■■■==                            ==                      ==                     \n");
        } else if (eventState.equals(EventState.REP2)) {
            res.append(
                    "                       ==                      ==                            ==■■■■■■■■■■■■■■■■■■■■■■==                     \n");
        }

        res.append("                       ==" + listAnswer.get(0) + "==                            =="
                + listAnswer.get(1) + "==                     \n");

        if (eventState.equals(EventState.REP1)) {
            res.append(
                    "                       ==■■■■■■■■■■■■■■■■■■■■■■==                            ==                      ==                     \n");
        } else if (eventState.equals(EventState.REP2)) {
            res.append(
                    "                       ==                      ==                            ==■■■■■■■■■■■■■■■■■■■■■■==                     \n");
        }

        res.append(
                "                       ==========================                            ==========================                     \n");
        res.append(
                "                                                                                                                           \n");

        System.out.println(res);
    }

    private Evenement generateEvent() {
        System.out.println(events.size());
        return events.remove(new Random().nextInt(events.size()));
    }
}
