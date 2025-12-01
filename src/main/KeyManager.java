package main;

import java.io.IOException;

import org.jline.terminal.*;
import org.jline.terminal.Terminal.Signal;

//TODO SI CELA VOUS MET UNE ERREUR, IL FAUT METTRE LA LIB DANS LE CLASSPATH DES LIBRAIRIES. CELA NOUS PERMET DE DÉTECTER UNE TOUCHE SANS PASSER PAR ENTRÉE
//Ce qui est grave chiant pour l'utilisateur
//How: En bas à droite, JAVA PROJECTS, ..., configure Classpath, librairie, mettez tous ce qui a dans lib,APPLY SETTINGS
//à noter, on ne peut utiliser la touche echap avec cette méthode... Il faudra donc utilisé X (à la undertale)

public class KeyManager {
    private static Terminal terminal;

    public KeyManager() {
        try {
            terminal = TerminalBuilder.terminal();
        } catch (Exception e) {
            System.err.println("Le terminal n'as pas était trouver par CoinCoinQuest");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {

        // veuillez utilisé ce main si vous souhaitez comprendre le fonctionnement de
        // cette librairie.
        Terminal terminal = TerminalBuilder.terminal();
        Attributes originalAttrs = terminal.getAttributes();
        System.out.println("Appuyez sur une touche (q pour quitter)");
        terminal.enterRawMode();
        // terminal.echo(false);

        while (true) {
            int first = terminal.reader().read(); // Lis le premier caractère (désignant une touche)
            System.out.println(first);
            if (first == 27) { // ça c'est soit echappe, soit une flèche
                terminal.reader().read(); // On s'en blc vu que les flèches envoie 3 signaux
                int third = terminal.reader().read();

                switch (third) {
                    case 'A':
                        System.out.println("↑ Flèche HAUT");
                        break;
                    case 'B':
                        System.out.println("↓ Flèche BAS");
                        break;
                    case 'C':
                        System.out.println("→ Flèche DROITE");
                        break;
                    case 'D':
                        System.out.println("← Flèche GAUCHE");
                        break;
                    default:
                        System.out.println("Séquence inconnue : " + third); // Si le mec tente échap + touche + touche
                                                                            // ça viens là
                        break;
                }
            } else if (first == 'q') {
                System.out.println("Fermeture...");
                break;
            } else {
                System.out.println("Touche normale : " + (char) first);
            }

        }
        terminal.setAttributes(originalAttrs);
    }

    public static Terminal getTerminal() {
        return terminal;
    }

    public static Key getKey() throws IOException { // OUI C'EST VACHEMENT SAUVAGE DE MAQUILLER UN WHILE TRUE DE CETTE
                                                    // MANIÈRE, ET??? :D
        // N'oubliez pas de faire un message personnaliser pour chaque catch, afin de
        // repérer plus rapidement les erreurs ^^
        getTerminal().flush();
        terminal.enterRawMode();
        terminal.echo(false);
        boolean wait = true;
        while (wait) {
            int first = getTerminal().reader().read();

            if (first == 27) {
                getTerminal().reader().read();
                int third = getTerminal().reader().read();
                switch (third) {
                    case 'A':
                        return Key.UP;
                    case 'B':
                        return Key.DOWN;
                    case 'C':
                        return Key.RIGHT;
                    case 'D':
                        return Key.LEFT;
                    default:
                        break;
                }

            } else if (first == 'a' || first == 'A') {
                return Key.A;
            } else if (first == 'b' || first == 'B') {
                return Key.B;
            } else if (first == 'c' || first == 'C') {
                return Key.C;
            } else if (first == 'd' || first == 'D') {
                return Key.D;
            } else if (first == 'e' || first == 'E') {
                return Key.E;
            } else if (first == 'f' || first == 'F') {
                return Key.F;
            } else if (first == 'g' || first == 'G') {
                return Key.G;
            } else if (first == 'h' || first == 'H') {
                return Key.H;
            } else if (first == 'i' || first == 'I') {
                return Key.I;
            } else if (first == 'j' || first == 'J') {
                return Key.J;
            } else if (first == 'k' || first == 'K') {
                return Key.K;
            } else if (first == 'l' || first == 'L') {
                return Key.L;
            } else if (first == 'm' || first == 'M') {
                return Key.M;
            } else if (first == 'n' || first == 'N') {
                return Key.N;
            } else if (first == 'o' || first == 'O') {
                return Key.O;
            } else if (first == 'p' || first == 'P') {
                return Key.P;
            } else if (first == 'q' || first == 'Q') {
                return Key.Q;
            } else if (first == 'r' || first == 'R') {
                return Key.R;
            } else if (first == 's' || first == 'S') {
                return Key.S;
            } else if (first == 't' || first == 'T') {
                return Key.T;
            } else if (first == 'u' || first == 'U') {
                return Key.U;
            } else if (first == 'v' || first == 'V') {
                return Key.V;
            } else if (first == 'w' || first == 'W') {
                return Key.W;
            } else if (first == 'x' || first == 'X') {
                return Key.X;
            } else if (first == 'y' || first == 'Y') {
                return Key.Y;
            } else if (first == 'z' || first == 'Z') {
                return Key.Z;
            } else if (first == ' ') {
                return Key.SPACE;
            } else if ((int) first == 10 || (int) first == 13) {
                return Key.ENTER;
            } else if ((int) first == 127 || (int) first == 8) {
                return Key.BACKSPACE;
            }
        }
        return null;
    }

}
