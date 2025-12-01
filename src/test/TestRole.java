package test;


import static org.junit.Assert.assertTrue;
import main.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Joueur;
import main.Role;
import main.role.Gambleur;
import main.role.Magicoin;
import main.role.Monk;
import main.role.Palacoin;



public class TestRole {
    @BeforeEach
    void initialization(){
        Joueur j1 = new Joueur("J1", 100, 10, new Magicoin());
        Joueur j2 = new Joueur("J2", 100, 10, new Palacoin());
        Joueur j3 = new Joueur("J3", 100, 10, new Monk());
        //Main.setRandom() = new Random();
        //Joueur j4 = new Joueur("J4", 100, 10, new Gambleur());
    }

    @Test
    void testRole(){
        Joueur j1 = new Joueur("J1", 100, 10, new Magicoin());
        Joueur j2 = new Joueur("J2", 100, 10, new Magicoin());
        System.out.println(j1.getRole().equals(j2.getRole()));
    }
}
