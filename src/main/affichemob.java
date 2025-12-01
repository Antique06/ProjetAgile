package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class affichemob {
    

    public static void afficheTruc(String pathfile){
        try {
            BufferedReader img = new BufferedReader(new FileReader(pathfile)); //TODO a revoir pour le .jar
            String line = img.readLine();
            while (line != null) {
                System.out.println(line.toString());
                line = img.readLine();
            }
            img.close();

        }catch(FileNotFoundException e){
            System.out.println("non trouvé");
        }catch(IOException e){
            System.out.println("probleme de fichier");
        }
    }
    
}
