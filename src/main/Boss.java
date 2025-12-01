package main;

import java.util.List;


public class Boss extends Monstre {
    private List<Competence> comps;

    public Boss(int level, int vie, String name, int force, List<Competence> comps) {
        super(level, name);
        this.comps = comps;
    }

    public List<Competence> getComps() {
        return comps;
    }

    public void setComps(List<Competence> comps) {
        this.comps = comps;
    }

    //  new Boss(100, "Olaf", 10, new Competence[] {
    //             new Competence("coup de carotte", "A", 2),
    //             new Competence("rayon de glace", "A", 5) }))

    // public static void main(String[] args) {
    //     final JsonManager<Boss> JSM = new JsonManager<Boss>(Boss.class);
    //     JSM.saveToFile("res/Boss.json",new Boss(10,100, "Olaf", 10, List.of(new Competence("coup de carotte", "A", 2,true),
    //             new Competence("rayon de glace", "A", 5,true))));
    //     JSM.saveToFile("res/Boss.json",new Boss(10,100, "Red", 15, List.of(new Competence("Lance Flamme", "A", 2,true),
    //             new Competence("Déflagration", "A", 5,true))));
    //     JSM.saveToFile("res/Boss.json",new Boss(10,100, "Sephiroth", 20, List.of(new Competence("Supernova", "A", 2,true),
    //             new Competence("Octofrappe", "A", 5,true))));
    //     JSM.saveToFile("res/Boss.json",new Boss(10,100, "Steve", 15, List.of(new Competence("TNT", "A", 2,true),
    //             new Competence("Épée en diamant", "A", 5,true))));
    // }

    // public static void main(String[] args) {
    //             final JsonManager<Boss> JSM = new JsonManager<Boss>(Boss.class);
    //     JSM.saveToFile("res/MiniBoss.json",new Boss(10,50, "Ifrit", 12, List.of(
    //         new Competence("Flammes des Enfers", "A", 3,true))));
    //     JSM.saveToFile("res/MiniBoss.json",new Boss(10,50, "Ryu", 12, List.of(
    //         new Competence("Hadouken", " ", 3,true))));
    //     JSM.saveToFile("res/MiniBoss.json",new Boss(10,50, "Titan de Glace", 12, List.of(
    //         new Competence("Marteau de Glace","Une attaque puissante du titan de Glace",10,true))));
        
    // }

    // public static void main(String[] args) {
    //     final JsonManager<Monstre> JSM = new JsonManager<Monstre>(Monstre.class);
    //     JSM.saveToFile("res/Monster.json",new Monstre(10,100, "Berseker", 2));
    //     JSM.saveToFile("res/Monster.json",new Monstre(10,100, "Bombo", 2));
    //     JSM.saveToFile("res/Monster.json",new Monstre(10,100, "Dragon", 2));
    //     JSM.saveToFile("res/Monster.json",new Monstre(10,100, "Gobelin", 2));
    //     JSM.saveToFile("res/Monster.json",new Monstre(10,100, "Mammouth", 2));
    //     JSM.saveToFile("res/Monster.json",new Monstre(10,100, "PereNoel", 2));
    //     JSM.saveToFile("res/Monster.json",new Monstre(10,100, "Shrek", 2));
    //     JSM.saveToFile("res/Monster.json",new Monstre(10,100, "Slime", 2));
    //     JSM.saveToFile("res/Monster.json",new Monstre(10,100, "Wyverne", 2));
    //     JSM.saveToFile("res/Monster.json",new Monstre(10,100, "Yeti", 2));
    // }

}
