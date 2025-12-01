package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Key;
import main.KeyManager;

public class KeyManagerTest {
    private KeyManager keyManager;

    @BeforeEach
    void initialization() {
        keyManager = new KeyManager();
    }

    // @Test //ça fonctionne pas yeepee
    // void test() {
    //     try {
    //         System.out.println("UP");
    //         Key key = KeyManager.getKey();
    //         if (key.equals(Key.UP)) {
    //             System.out.println("DOWN");
    //             key = KeyManager.getKey();
    //             if (key.equals(Key.DOWN)) {
    //                 System.out.println("LEFT");
    //                 key = KeyManager.getKey();
    //                 if (key.equals(Key.LEFT)) {
    //                     System.out.println("RIGHT");
    //                     key = KeyManager.getKey();
    //                     if (key.equals(Key.RIGHT)) {
    //                         System.out.println("Z");
    //                         key = KeyManager.getKey();
    //                         if (key.equals(Key.Z)) {
    //                             System.out.println("Q");
    //                             key = KeyManager.getKey();
    //                             if (key.equals(Key.Q)) {
    //                                 System.out.println("S");
    //                                 key = KeyManager.getKey();
    //                                 if (key.equals(Key.S)) {
    //                                     System.out.println("D");
    //                                     key = KeyManager.getKey();
    //                                     if (key.equals(Key.D)) {
    //                                         System.out.println("E");
    //                                         key = KeyManager.getKey();
    //                                         if (key.equals(Key.E)) {
    //                                             System.out.println("X");
    //                                             key = KeyManager.getKey();
    //                                             if (key.equals(Key.X)) {
    //                                                 System.out.println("SPACE");
    //                                                 key = KeyManager.getKey();
    //                                                 if (key.equals(Key.SPACE)) {
    //                                                     System.out.println("ENTER");
    //                                                     key = KeyManager.getKey();
    //                                                     if (key.equals(Key.ENTER)) {
    //                                                         assertTrue(true);
    //                                                         System.out.println("test réussi");
    //                                                     }
    //                                                 }
    //                                             }
    //                                         }
    //                                     }
    //                                 }
    //                             }
    //                         }
    //                     }
    //                 }
    //             }

    //         } else {
    //             assertEquals("Non", "T'as fais un miss clique");
    //         }
    //     } catch (Exception e) {
    //         assertEquals("Non", "Fonctionne pas");
    //         e.printStackTrace();
    //     }
    // }
}
