package tests;

import java.time.LocalDateTime;
import java.time.Month;
import org.junit.jupiter.api.BeforeEach;
import association.Evenement;
import association.GestionEvenements;
import association.InformationPersonnelle;

/**
 * Tests jUnit de la classe GestionEvenements
 *
 */
public class TestGestionEvenements {
  
  
  private GestionEvenements gEvent;
  
  private Evenement event1;
  private Evenement event2;
  private Evenement event3;
  
  /**
   * On instancie plusieurs événements afin de les ajouter à la liste
   * d'évenements pour notre jeu de tests
   * 
   */
  @BeforeEach
  void setUp() {
    
    
    //Ajout d'un premier événement nommé Bowling se trouvant à Lannion et ayant
    //la particularité de commencé le 14 Mars 2022 durant 120 minutes et
    //commençant à 14h00 
    
    event1 = new Evenement("Bowling", "Lannion", 2022, Month.MARCH, 14, 14, 0, 120, 10);
    
    
    // Ajout d'un deuxième événement nommé Sortie se trouvant à Lannion et ayant
    // la particularité de commencé le 15 Mars 2022 durant 45 minutes et
    // commençant à 18h30
    event2 = new Evenement("Sortie", "Lannion", 2022, Month.MARCH, 15, 18, 30, 45, 6);
    
    // Ajout d'un deuxième événement nommé Parc se trouvant à Brest et ayant
    // la particularité de commencé le 15 Juillet 2022 durant 45 minutes et
    // commençant à 18h30
    event3 = new Evenement("Parc", "Brest", 2024, Month.JULY, 6, 13, 0, 180, 14);
    
  }
  
}
