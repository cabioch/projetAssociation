package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.ClientInfoStatus;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import association.InformationPersonnelle;
import association.InterGestionMembres;
import association.InterMembre;
import association.Membre;
import junit.framework.AssertionFailedError;



/**
 * Tests JUnit de la classe {@link association.Membre.Membre}.
 *
 * @author Jeant
 * @see association.Membre
 */
public class TestMembre {
  
  /**
   * Cr�ation d'un membre non complet pour les tests.
   */
  private Membre membre;
  
  /**
   * Cr�ation d'un membre complet pour les tests.
   */
  private Membre membreComplet;
  
  /**
   * Une information basique: nom, prenom.
   */
  private InformationPersonnelle infos;
  
  /**
   * Un appel � la m�thode gestion membre.
   */
  private InterGestionMembres gestionMembres;
  
  /**
   * Une information complete: nom, prenom, adresse, age.
   */
  private InformationPersonnelle infosComplet;
  
  /**
   * Initialisation des tests en definissant les deux types d'infos et le
   * membre.
   * 
   */
  @BeforeEach
  void setUp() throws Exception {
    infos = new InformationPersonnelle("thomas", "jean-andre");
    membre = new Membre(infos);
    infosComplet = new InformationPersonnelle("thomas", "jean-andre",
        "14 rue Archives", 20);
    membreComplet = new Membre(infosComplet);
    
  }
  
  /**
   * V�rifie que l'on peut r�cuperer les informations d'un membre.
   */
  @Test
  void testgetInformationPersonnelle() {
    assertEquals(membre.getInformationPersonnelle(), infos);
  }
  
  /**
   * V�rifie que les informations du constructeur sont bien instanci�es.
   */
  @Test
  void testConstructeur() {
    InformationPersonnelle infosConstructeur =
        new InformationPersonnelle("thomas", "jean-andre");
    Membre membreTest = new Membre(infosConstructeur);
    assertEquals(membreTest.getInformationPersonnelle().getPrenom(),
        "jean-andre");
    assertEquals(membreTest.getInformationPersonnelle().getNom(), "thomas");
    
  }
  
  /**
   * V�rifie que la definitionInformationPersonnelle rend vrai en ajoutant un
   * membre dans l'association puis en modifiant ses informations personnelles.
   * 
   */
  @Test
  void testdefinirInformationPersonnelleAvecAssociation() {
    InformationPersonnelle infoModif =
        new InformationPersonnelle("thomas", "jean-andre", "test_valide", 80);
    gestionMembres.ajouterMembre(membreComplet);
    membreComplet.definirInformationPersonnnelle(infoModif);
    assertEquals("test_valide",
        membreComplet.getInformationPersonnelle().getAdresse());
    assertEquals(membre.getInformationPersonnelle().getAge(), 80);
    
  }
  
  /**
   * V�rifie que la definitionInformationPersonnelle ne fonctionne pas en
   * donnant un nom et un prenom qui ne correspond pas � l'instance de classe.
   */
  @Test
  void testdefinirInformationPersonnelleSansAssociation() {
    InformationPersonnelle infoModif = new InformationPersonnelle("mauvais_nom",
        "mauvais_prenom", "changement_adresse", 40);
    membreComplet.definirInformationPersonnnelle(infoModif);
    assertFalse("changement_adresse" == membreComplet
        .getInformationPersonnelle().getAdresse());
    assertFalse(40 != membreComplet.getInformationPersonnelle().getAge());
    
  }
}
