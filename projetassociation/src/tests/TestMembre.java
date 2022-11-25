package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import association.InformationPersonnelle;
import association.Membre;



/**
 * Tests JUnit de la classe {@link association.Membre.Membre}.
 *
 * @author Jeant
 * @see association.Membre
 */
public class TestMembre {
  
  private Membre membre;
  
  /**
   * Instancie un membre pour les tests.
   *
   * @throws Exception ne peut pas �tre lev�e ici
   */
  @BeforeEach
  void setUp() throws Exception {
    membre = new Membre(25, "2 rue Nulle part");
    
  }
  
  /**
   * Ne fait rien apr�s les tests.
   *
   * @throws Exception ne peut pas �tre lev�e ici
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  /**
   * V�rifie que l'on peut r�cuperer les informations d'un membre.
   */
  @Test
  void getInformationPersonnelle() {
    assertTrue(membre.getInformationPersonnelle() != null);
  }
  
  /**
   * V�rifie que les informations du constructeur sont bien instanci�es.
   */
  @Test
  void testConstructeur() {
    InformationPersonnelle infos = new InformationPersonnelle("jean-andre", "thomas");
    Membre membreTest = new Membre(infos);
    assertTrue(membreTest.getInformationPersonnelle().getPrenom() != null);
    assertEquals(membreTest.getInformationPersonnelle().getNom(), "jean-andre");
    assertEquals(membreTest.getInformationPersonnelle().getNom(), "thomas");
    
  }
}
