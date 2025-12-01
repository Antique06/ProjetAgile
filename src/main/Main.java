package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.junit.platform.console.shadow.picocli.CommandLine.Help.Ansi.Text;

import main.menu.Menu;
import main.role.Gambleur;
import main.role.Magicoin;
import main.role.Monk;
import main.role.Palacoin;

public class Main {
    private static boolean stop = false;
    private static Scanner scanner;
    private static KeyManager keyManager;
    private static TextBox textBox;
    private static Random random;
    public static Tower tower;

    public static Tower getTower() {
        return tower;
    }   

    public static void main(String[] args) {

        // === INITIALISATION ===
        ScreenManager.clear();
        scanner = new Scanner(System.in);
        keyManager = new KeyManager();
        textBox = new TextBox();
        random = new Random();

        // === UPDATE ===
        do {
            tower=null;
            Menu.navigate();
        } while (!stop);

        // === BEFORESTOP ===

        scanner.close();
    }

    public static Random getRandom() {
        return random;
    }

    public static void setStop(boolean stop) {
        Main.stop = stop;
    }

    public static void launch() {

        Joueur j1 = profile();

        // TODO à finir, si nom existe dans save.json alors reprendre la save, sinon on
        // en créer une nouvelle et on fais l'intro de l'histoire
        // Oui j'ai arrêter je suis crever, J'ai beaucoup changer de chose mais pas tant
        // que ça non plus donc ça va.
        // Parcontre l'équipe je vais maintenant vous demander d'utiliser uniquement
        // KeyManager, donc le scanner devra être supprimer à la fin du projet !
        // Pourquoi? Car malgré qu'il répond directement, oui je l'ai réparer, il
        // emmerde le scanner, ce qui apporte certain problème
        // De plus, pour un utilisateur, il est plus compréhensible de naviguer dans un
        // jeu avec des flèches que des touches attribuer aléatoirement
        // Bref, venez bagarre si pas d'accord

        // Joueur j1 = new Joueur("j1", 100, 50, new Gambleur());

        if (tower == null || tower.exit == true) {
            ScreenManager.clear();
            TextBox.afficherTextBox("res/Histoire.txt");
            tower = new Tower(j1, Biome.PLAINE);
            tower.rush(j1, Biome.PLAINE);
            if (tower.getBiome().equals(Biome.PLAINE)) {
                if (!tower.exit && j1.estVivant) {
                    tower = new Tower(j1, Biome.GLACIER);
                    tower.numberOfFight = 0;
                    tower.rush(j1, Biome.GLACIER);
                }
            }
            if (tower.getBiome().equals(Biome.GLACIER)) {
                if (!tower.exit && j1.estVivant) {
                    tower = new Tower(j1, Biome.ENFER);
                    tower.numberOfFight = 0;
                    tower.rush(j1, Biome.ENFER);
                }
            }
        } else {

            tower.rush(j1, tower.getBiome());
            if (tower.getBiome().equals(Biome.PLAINE)) {
                if (!tower.exit && j1.estVivant) {
                    tower = new Tower(j1, Biome.GLACIER);
                    tower.numberOfFight = 0;
                    tower.rush(j1, Biome.GLACIER);
                }
            }
            if (tower.getBiome().equals(Biome.GLACIER)) {
                if (!tower.exit && j1.estVivant) {
                    tower = new Tower(j1, Biome.ENFER);
                    tower.numberOfFight = 0;
                    tower.rush(j1, Biome.ENFER);
                }
            }

        }

    }

    public static void printLogo() {
        try {
            BufferedReader logo = new BufferedReader(new FileReader("res/logo.txt")); // TODO a revoir pour le .jar
            String line = logo.readLine();
            while (line != null) {
                System.out.println(line.toString());
                line = logo.readLine();
            }
            logo.close();

        } catch (FileNotFoundException e) {
            System.out.println("non trouvé");
        } catch (IOException e) {
            System.out.println("probleme de fichier");
        }
    }

    public static Scanner getScanner() {
        return scanner;
    }

    private static RoleState roleState = RoleState.PALADIN;

    private static Role getRole() {
        boolean select = false;
        Key key = null;
        try {
            do {
                do {
                    ScreenManager.clear();
                    TextBox.afficherTextBox("Cher canard", "Viens à moi, choisir ton rôle dans ce monde",
                            "Appuyez pour continuer");
                    List<Role> roles = new ArrayList<>();
                    roles.add(new Palacoin());
                    roles.add(new Monk());
                    roles.add(new Magicoin());
                    roles.add(new Gambleur());

                    System.out.println(showCompetence(roles));
                    key = KeyManager.getKey();
                } while (key == null || (!key.equals(Key.LEFT) && !key.equals(Key.RIGHT) && !key.equals(Key.ENTER)));

                if (key != null && key.equals(Key.RIGHT)) {
                    if (((roleState.ordinal() + 1) % RoleState.values().length) >= 0) {
                        roleState = RoleState.values()[((roleState.ordinal() + 1) % RoleState.values().length)];
                    } else {
                        roleState = RoleState.values()[((roleState.ordinal() + 1) % RoleState.values().length)
                                + RoleState.values().length];
                    }
                } else if (key != null && key.equals(Key.LEFT)) {
                    if (((roleState.ordinal() - 1) % RoleState.values().length) >= 0) {
                        roleState = RoleState.values()[((roleState.ordinal() - 1) % RoleState.values().length)];
                    } else {
                        roleState = RoleState.values()[((roleState.ordinal() - 1) % RoleState.values().length)
                                + RoleState.values().length];
                    }
                } else {
                    select = true;
                }

            } while (!select);
            switch (roleState) {
                case PALADIN:
                    return new Palacoin();

                case MONK:
                    return new Monk();

                case MAGE:
                    return new Magicoin();

                case GAMBLER:
                    return new Gambleur();

                default:
                    return null;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Joueur profile() {

        String name = getName();

        if (locateIfExist(name)) {

            ScreenManager.clear();

            JsonManager<Save> JSM = new JsonManager<Save>(Save.class);
            List<Save> saves = JSM.loadFromFile("res/save.json");
            Save save = getSave(saves, name);

            TextBox.afficherTextBox(
                    "Oh, tu es revenues " + save.joueur.getName()
                            + ". Prend ton temps un coinfé peut être ? Je te prépare ça...",
                    "Bon alors, arrivée à la tour " + save.tour.getBiome() + " tu t'en va affronter ton "
                            + save.tour.numberOfFight + "ème monstre.",
                    "T'as pas chaumé mon grand ! T'es sûre de reprendre ? (ENTER = OUI, RETOUR = NON)");
            Key key = null;
            try {
                do {
                    key = KeyManager.getKey();
                } while (!key.equals(Key.ENTER) && !key.equals(Key.BACKSPACE));
            } catch (Exception e) {
                key = Key.BACKSPACE;
                e.printStackTrace();
            }
            if (key.equals(Key.ENTER)) {
                Main.tower = save.tour;
                return save.joueur;
            } else if(key.equals(Key.BACKSPACE)) {
                Role role = getRole();
                return new Joueur(name, 100, 10+random.nextInt(20), role);
            }
            return null;
        } else {
            Role role = getRole();
            return new Joueur(name, 100, 10+random.nextInt(20), role);
        }

    }

    private static Save getSave(List<Save> saves, String name) {
        for (Save save : saves) {
            if (save.getJoueur().getName().equals(name)) {
                return save;
            }
        }
        return null;
    }

    private static boolean locateIfExist(String name) {
        JsonManager<Save> JSM = new JsonManager<Save>(Save.class);
        List<Save> saves = JSM.loadFromFile("res/save.json");
        if (saves.parallelStream().map(e -> e.joueur.getName()).collect(Collectors.toList()).contains(name)) {
            return true;
        }
        return false;
    }

    private static String getName() {

        boolean select = false;
        String string = "";
        try {

            Key key = null;
            do {
                ScreenManager.clear();
                TextBox.afficherTextBox("Bienvenue dans cette aventure.", "",
                        "J'apprécierais que tu me donnes ton nom.");
                String space = "";
                String showingString = "";
                int taille = string.length();
                for (int i = 0; i < (15 - taille) / 2; i++)
                    space += " ";
                if ((15 - string.length()) % 2 == 0) {
                    showingString = space + string + space;
                } else {
                    showingString = space + string + space + " ";
                }

                StringBuilder a = new StringBuilder();
                a.append("\n");
                a.append(
                        "                                                    =================                                               \n");
                a.append("                                                    =" + showingString
                        + "=                                               \n");
                a.append(
                        "                                                    =================                                               \n");
                a.append(
                        "                                                                                                                    \n");
                System.out.println(a);
                do {
                    key = KeyManager.getKey();
                } while (key == null || (key.ordinal() < 7 && !key.equals(Key.BACKSPACE) && !key.equals(Key.ENTER)));
                if (string.length() < 15 && key.ordinal() >= 7) {
                    string = string + key.name();
                }
                if (!string.equals("") && key.equals(Key.BACKSPACE)) {

                    string = string.substring(0, string.length() - 1);
                }
                if (key.equals(Key.ENTER) && !string.equals(""))
                    select = true;
            } while (!select);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public static String showCompetence(List<Role> roles) {
        StringBuilder res = new StringBuilder();
        List<String> list = new ArrayList<>();

        for (Role role : roles) {
            list.add(role.getNom());
        }

        List<String> listStringCompetence = StringManager.viewChoiceBox(list, 22);

        res.append(
                "                                                                                                                                      \n");
        res.append(
                "                                                                             ==========================     ==========================     ==========================     ==========================        \n");
        if (roleState.equals(RoleState.PALADIN)) {
            res.append(
                    "                                                                             ==■■■■■■■■■■■■■■■■■■■■■■==     ==                      ==     ==                      ==     ==                      ==        \n");
        } else if (roleState.equals(RoleState.MONK)) {
            res.append(
                    "                                                                             ==                      ==     ==■■■■■■■■■■■■■■■■■■■■■■==     ==                      ==     ==                      ==        \n");
        } else if (roleState.equals(RoleState.MAGE)) {
            res.append(
                    "                                                                             ==                      ==     ==                      ==     ==■■■■■■■■■■■■■■■■■■■■■■==     ==                      ==        \n");
        } else if (roleState.equals(RoleState.GAMBLER)) {
            res.append(
                    "                                                                             ==                      ==     ==                      ==     ==                      ==     ==■■■■■■■■■■■■■■■■■■■■■■==        \n");
        }

        res.append("                                                                             =="
                + (listStringCompetence.size() >= 1 ? listStringCompetence.get(0) : "                      ")
                + "==     =="
                + (listStringCompetence.size() >= 2 ? listStringCompetence.get(1) : "                      ")
                + "==     =="
                + (listStringCompetence.size() >= 3 ? listStringCompetence.get(2) : "                      ")
                + "==     =="
                + (listStringCompetence.size() >= 4 ? listStringCompetence.get(3) : "                      ")
                + "==        \n");

        if (roleState.equals(RoleState.PALADIN)) {
            res.append(
                    "                                                                             ==■■■■■■■■■■■■■■■■■■■■■■==     ==                      ==     ==                      ==     ==                      ==        \n");
        } else if (roleState.equals(RoleState.MONK)) {
            res.append(
                    "                                                                             ==                      ==     ==■■■■■■■■■■■■■■■■■■■■■■==     ==                      ==     ==                      ==        \n");
        } else if (roleState.equals(RoleState.MAGE)) {
            res.append(
                    "                                                                             ==                      ==     ==                      ==     ==■■■■■■■■■■■■■■■■■■■■■■==     ==                      ==        \n");
        } else if (roleState.equals(RoleState.GAMBLER)) {
            res.append(
                    "                                                                             ==                      ==     ==                      ==     ==                      ==     ==■■■■■■■■■■■■■■■■■■■■■■==        \n");
        }
        res.append(
                "                                                                             ==========================     ==========================     ==========================     ==========================        \n");
        res.append(
                "                                                                                                                                                                                                            \n");
        res.append(
                "                                                                                                                                                                                                            \n");

        return res + "";
    }

    public static KeyManager getKeyManager() { // Je vais casser la gueule à celui qui l'a retiré xDD
        return keyManager;
    }

    // public Scanner getScanner()
    public int setRandom(){
        return random.nextInt(50);
    }
}
