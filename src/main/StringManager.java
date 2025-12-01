package main;

import java.util.ArrayList;
import java.util.List;

public class StringManager {
    public static List<String> viewChoiceBox(List<String> list, int taille){
        List<String> listString = new ArrayList<>();
        for (String string : list){
            String nameres = "";
            int valueLack = taille - string.length();
            StringBuilder space = new StringBuilder();
            if (string.length()<=taille){
                for (int i = 0; i<valueLack/2; i++){
                        space.append(" ");
                    }
                if (valueLack%2==0){
                    
                    nameres = space + string + space;

                } else {
                    nameres = space + string + space + " ";
                }
                
            } else {
                nameres = string.substring(0, taille);
            }
            listString.add(nameres);
        }
        return listString;
    }
    
}
