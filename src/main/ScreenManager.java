package main;

import java.io.Console;

import org.junit.platform.console.shadow.picocli.CommandLine.Help.Ansi.Text;

public class ScreenManager {


    private TextBox textBox;
    private String choix;
    // BattleView battleView; //La ligne ou se trouve le canard et le monstre
    // 
    public ScreenManager(TextBox textBox, String choix){}

    public static void clear(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static void showChoice(Joueur joueur, Monstre monstre, String choix){
        clear();
        TextBox.afficherHautCombat(joueur, monstre);
        System.out.println(choix);
    }

    
    public static void showStory(Joueur joueur, Monstre monstre, String string1, String string2, String string3){
        clear();
        TextBox.afficherHautCombat(joueur, monstre);
        TextBox.afficherTextBox(string1,string2,string3);
        Key key = null;
        do {
            try {
                key=KeyManager.getKey();
            } catch (Exception e){
                e.printStackTrace();
            }
            

        } while(key==null || !key.equals(Key.ENTER));

    }

    // public static void main(String[] args) {
    //     new ScreenManager().clear();
        
    // }


}
