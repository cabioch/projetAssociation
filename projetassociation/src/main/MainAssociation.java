package main;

import java.io.IOException;

/**
 * classe de lancement de l'application en mode console. *
 *
 */
public class MainAssociation {

  /**
 *Classe main.
 *
 * @param args argument passé au programme.
 */
  public static void main(String[] args) {
    System.out.println("Ca marche !");
    System.out.println("\nAppuyez sur Entrée pour terminer le programme ...");
    try {
      System.in.read();
    } catch (IOException e) {
      System.err.println("Vous avez réussi à casser le clavier : " + e);
    }
  }
  
}
