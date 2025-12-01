package main;

public class ColorWritter {
    public static void afficherLignePixel(String ligne) {
        for(int i=0; i<ligne.length(); i++) {
            afficherPixel(ligne.charAt(i));
        }
        System.out.println("");
    }

    public static void afficherTableauPixel(String[] tab) {
        for(String s : tab) {
            afficherLignePixel(s);
        }
    }

    public static void afficherPixel(char c) {
        if(c == 'N' || c == ' ') {
            System.out.print("\033[30m" + "■" + "\033[0m");
        }
        else if(c == 'B') {
            System.out.print("\033[32m" + "■" + "\033[0m");
        }
        else if(c == 'V') {
            System.out.print("\033[34m" + "■" + "\033[0m");
        }
        else if(c == 'R') {
            System.out.print("\033[31m" + "■" + "\033[0m");
        }
        else if(c == 'J') {
            System.out.print("\033[33m" + "■" + "\033[0m");
        }
        else if(c == 'M') {
            System.out.print("\033[35m" + "■" + "\033[0m");
        }
        else if(c == 'C') {
            System.out.print("\033[36m" + "■" + "\033[0m");
        }
        else if(c == 'W') {
            System.out.print("\033[37m" + "■" + "\033[0m");
        }
    }

    public static void main(String[] args) {
        String phrase1 = "NVB BV";
        String phrase2 = "NRVJBMCW ";
        String phrase3 = "NVB BV";
        afficherLignePixel(phrase1);
        afficherLignePixel(phrase2);
        afficherLignePixel(phrase3);
        String[] tab = new String[] {"NVB BV", "NRVJBMCW ", "NVB BV"};
        afficherTableauPixel(tab);
    }
}

/*
30  Noir
31  Rouge
32  Vert
33  Jaune
34  Bleu
35  Magenta
36  Cyan
37  Blanc
*/
