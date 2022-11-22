package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	   * @throws Exception ne peut pas être levée ici
	   */
	  @BeforeEach
	  void setUp() throws Exception {
	    membre = new Membre(25 , "2 rue Nulle part");
	    
	  }
	  
	  /**
	   * Ne fait rien après les tests 
	   *
	   * @throws Exception ne peut pas être levée ici
	   */
	  @AfterEach
	  void tearDown() throws Exception {}
	  
	  /**
	   * Vérifie que l'on peut positionner un age de 50 ans.
	   */
	  @Test
	  void testAge25Basique() {
	    membre.setAge(25);
	    assertEquals(infoBasique.getAge(), 25);
	  }
}
