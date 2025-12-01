package main.menu;

import java.io.IOException;

import main.Key;
import main.KeyManager;
import main.Main;
import main.ScreenManager;

public class Menu {
        private static MenuState selectedOption;
        private static boolean select;

        // public Menu(){
        // show();
        // }

        public static void show() {
                Main.printLogo();
                StringBuilder menu = new StringBuilder();

                menu.append(
                                "                                                                                                                                        \n");
                menu.append(
                                "                                                                                                                                        \n");
                if (!selectedOption.equals(MenuState.JOUER)) {
                        menu.append(
                                        "                                                                 =========================================                              \n");
                        menu.append(
                                        "                                                                 ==                                     ==                              \n");
                        menu.append(
                                        "                                                                 ==                JOUER                ==                              \n");
                        menu.append(
                                        "                                                                 ==                                     ==                              \n");
                        menu.append(
                                        "                                                                 =========================================                              \n");
                } else {
                        menu.append(
                                        "                                                                 =========================================                              \n");
                        menu.append(
                                        "                                                                 ==              ■■■■■■■■■              ==                              \n");
                        menu.append(
                                        "                                                                 ==              ■ JOUER ■              ==                              \n");
                        menu.append(
                                        "                                                                 ==              ■■■■■■■■■              ==                              \n");
                        menu.append(
                                        "                                                                 =========================================                              \n");
                }
                menu.append(
                                "                                                                                                                                        \n");

                if (!selectedOption.equals(MenuState.REGLE)) {
                        menu.append(
                                        "                                                                 =========================================                              \n");
                        menu.append(
                                        "                                                                 ==                                     ==                              \n");
                        menu.append(
                                        "                                                                 ==                REGLE                ==                              \n");
                        menu.append(
                                        "                                                                 ==                                     ==                              \n");
                        menu.append(
                                        "                                                                 =========================================                              \n");
                } else {
                        menu.append(
                                        "                                                                 =========================================                              \n");
                        menu.append(
                                        "                                                                 ==              ■■■■■■■■■              ==                              \n");
                        menu.append(
                                        "                                                                 ==              ■ REGLE ■              ==                              \n");
                        menu.append(
                                        "                                                                 ==              ■■■■■■■■■              ==                              \n");
                        menu.append(
                                        "                                                                 =========================================                              \n");
                }
                menu.append(
                                "                                                                                                                                        \n");
                if (!selectedOption.equals(MenuState.QUITTER)) {
                        menu.append(
                                        "                                                                 =========================================                              \n");
                        menu.append(
                                        "                                                                 ==                                     ==                              \n");
                        menu.append(
                                        "                                                                 ==               QUITTER               ==                              \n");
                        menu.append(
                                        "                                                                 ==                                     ==                              \n");
                        menu.append(
                                        "                                                                 =========================================                              \n");
                } else {
                        menu.append(
                                        "                                                                 =========================================                              \n");
                        menu.append(
                                        "                                                                 ==              ■■■■■■■■■              ==                              \n");
                        menu.append(
                                        "                                                                 ==              ■QUITTER■              ==                              \n");
                        menu.append(
                                        "                                                                 ==              ■■■■■■■■■              ==                              \n");
                        menu.append(
                                        "                                                                 =========================================                              \n");
                }
                menu.append(
                                "                                                                                                                                        \n");
                menu.append(
                                "                                                                                                                                        \n");
                System.out.println(menu);
        }

        public static void afficherRegle() {
                ScreenManager.clear();
                for (int i = 0; i < 10; i++) {
                        System.out.println();
                }
                System.out.println(
                                "                                                                    Le but de ce jeu est de battre les boss");
                System.out.println(
                                "                                                                 Vous disposez pour cela de plusieurs action :");
                System.out.println("");
                System.out.println("");
                System.out.println("");
                System.out.println(
                                "                                                                                    ATTAQUER                  ");
                System.out.println(
                                "                                                                      Vous réaliser une attaque classique     ");
                System.out.println(
                                "                                                                           infligeant peu de dégats           ");
                System.out.println("");
                System.out.println("");
                System.out.println(
                                "                                                                                   COMPÉTENCE                 ");
                System.out.println(
                                "                                                                   Vous réaliser une attaque spéciale liée    ");
                System.out.println(
                                "                                                                       à votre classe plus puissante que      ");
                System.out.println(
                                "                                                                               l'attaque de base.             ");
                System.out.println("");
                System.out.println("");
                System.out.println("");
                System.out.println(
                                "                                                                    Si vous avez des questions plus poussées,  ");
                System.out.println(
                                "                                                                   n'hésitez pas à contacter le service client ");
                System.out.println("");
                System.out.println("");
                System.out.println(
                                "                                                                      En espérant que le jeu vous plaise.      ");
                System.out.println("");
                System.out.println("");
                for (int i = 0; i < 10; i++) {
                        System.out.println();
                }
                try {
                        Key key = null;
                        System.out.println("Appuyer sur BACKSPACE pour revenir au MENU");
                        do {
                                key = KeyManager.getKey();
                        } while (key == null || !key.equals(Key.BACKSPACE));
                        ScreenManager.clear();
                        Menu.navigate();
                } catch (IOException e) {
                        System.out.println("IOException " + e);
                }
        }

        public static void navigate() {
                do {

                        select = false;
                        if (selectedOption == null)
                                selectedOption = MenuState.JOUER;
                        ScreenManager.clear();
                        show();
                        Key key = null;
                        do {
                                try {
                                        key = KeyManager.getKey();
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        } while (key == null
                                        || (!key.equals(Key.UP) && !key.equals(Key.DOWN) && !key.equals(Key.ENTER)));
                        if (key != null && key.equals(Key.DOWN)) {
                                if (((selectedOption.ordinal() + 1) % MenuState.values().length) >= 0) {
                                        selectedOption = MenuState.values()[((selectedOption.ordinal() + 1)
                                                        % MenuState.values().length)];
                                } else {
                                        selectedOption = MenuState.values()[((selectedOption.ordinal() + 1)
                                                        % MenuState.values().length) + MenuState.values().length];
                                }
                        } else if (key != null && key.equals(Key.UP)) {
                                if (((selectedOption.ordinal() - 1) % MenuState.values().length) >= 0) {
                                        selectedOption = MenuState.values()[((selectedOption.ordinal() - 1)
                                                        % MenuState.values().length)];
                                } else {
                                        selectedOption = MenuState.values()[((selectedOption.ordinal() - 1)
                                                        % MenuState.values().length) + MenuState.values().length];
                                }
                        } else {
                                select = true;
                        }
                } while (!select);
                ScreenManager.clear();

                if (selectedOption.equals(MenuState.JOUER)) {
                        Main.launch();
                } else if (selectedOption.equals(MenuState.REGLE)) {
                        Menu.afficherRegle();
                } else if (selectedOption.equals(MenuState.QUITTER)) {
                        Main.setStop(true);
                }

        }

        public static void main(String[] args) {
                KeyManager lol = new KeyManager();
                navigate();

        }

        public static void setSelectedOption(MenuState selectedOption) {
                Menu.selectedOption = selectedOption;
        }

        public static MenuState getSelectedOption() {
                return selectedOption;
        }
}
