package main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

//TODO SI CELA VOUS MET UNE ERREUR, IL FAUT METTRE LA LIB DANS LE CLASSPATH DES LIBRAIRIES. CELA NOUS PERMET DE STOCKER NOS DONNÉES TROP BIEN
//How: En bas à droite, JAVA PROJECTS, ..., configure Classpath, librairie, mettez tous ce qui a dans lib,APPLY SETTINGS
//à noter, NE STOCKER PAS 2 TYPES DIFFERENTS DANS LE MEME FICHIER, SINON VOUS FAITES DE LA MAGIE NOIR.

public class JsonManager<T> { // Classe générique digne de mon génie (et surtout celui d'internet...)

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create(); // on initialise notre json et le rend
                                                                              // plus lisible à l'oeil humain...

    private final Type listType; // On stock le type du générique sous forme de liste

    public JsonManager(Class<T> c) {
        this.listType = TypeToken.getParameterized(List.class, c).getType(); // permet de récupérer le type du générique
                                                                             // sous forme de liste, c'est incrr
    }

    public void saveToFile(String path, T data) { //TODO améliorer le chemin afin qu'il fonctionne relativement, hors du jar
        List<T> dataList = new ArrayList<>();
        File file = new File(path);

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                dataList = gson.fromJson(reader, listType); // voilà pourquoi on stocker l'item générique sous forme de
                                                            // liste
                if (dataList == null)
                    dataList = new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } // ça sa permet juste d'éviter d'erase au dessus du fichier, ça add par dessus

        dataList.add(data); // bon je vous apprend rien

        try (FileWriter writer = new FileWriter(file)) { // Puis on réecrie tout (ce qu'on a recup pour pas erase +
                                                         // nouvelle data)
            gson.toJson(dataList, writer);
        } catch (IOException e) {
            System.out.println("L'erreur s trouve ici");
            e.printStackTrace();
        }
    }

    public void save(Save data) { //TODO améliorer le chemin afin qu'il fonctionne relativement, hors du jar 
        String path = "res/save.json";
    //à utiliser seulement pour les save, mettre une protection
        List<Save> dataList = new ArrayList<>();
        File file = new File(path);

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                dataList = gson.fromJson(reader, listType); 
                if (dataList == null)
                    dataList = new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<Save> removingSaves = new ArrayList<>(); 
        for (Save save : dataList){
            if (save.getJoueur().getName().equals(data.getJoueur().getName())){
                removingSaves.add(save);
            }
        }
        dataList.removeAll(removingSaves);
        dataList.add(data);

        try (FileWriter writer = new FileWriter(file)) { 
            gson.toJson(dataList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeSave(Save data) { //TODO améliorer le chemin afin qu'il fonctionne relativement, hors du jar 
        String path = "res/save.json";
    //à utiliser seulement pour les save, mettre une protection
        List<Save> dataList = new ArrayList<>();
        File file = new File(path);

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                dataList = gson.fromJson(reader, listType); 
                if (dataList == null)
                    dataList = new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<Save> removingSaves = new ArrayList<>(); 
        for (Save save : dataList){
            if (save.getJoueur().getName().equals(data.getJoueur().getName())){
                removingSaves.add(save);
            }
        }
        dataList.removeAll(removingSaves);

        try (FileWriter writer = new FileWriter(file)) { 
            gson.toJson(dataList, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     

    public List<T> loadFromFile(String path) {
        File file = new File(path); //On va chercher simplement le fichier //TODO améliorer le chemin afin qu'il fonctionne relativement, hors du jar
        if (!file.exists())
            return new ArrayList<>();

        try (FileReader reader = new FileReader(file)) {
            List<T> list = gson.fromJson(reader, listType);
            return (list != null) ? list : new ArrayList<>(); //on évite de renvoyer null vu que c'est nul et que ça va créer des problèmes
        } catch (IOException e) {
            System.out.println("Désolée mon gars j'ai merder lors du chargement de "+path);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // public static void main(String[] args) {
        
    //     Joueur joueur = new Joueur("BASTE LE DIEU DE TOUS", 5, 2);
    //     Save save = new Save(joueur, new Combat(joueur, new Monstre(10, "Patrick et triste", 1)), new Tower(joueur, Biome.PLAINE));
    //     save.saving();
    
    // }

    
    //TODO à améliorer pour récupérer un monstre par son nom, des données de joueur par son nom, et puis autres je suis crever je vais me coucher.
}
