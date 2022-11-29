package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import association.GestionMembres;
import association.InformationPersonnelle;
import association.InterMembre;
import association.Membre;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link association.GestionMembres GestionMembres}.
 *
 * @author Nicolas Renard
 * @see association.GestionMembres
 */

class TestGestionMembres {
  
  /**
   * Un appel à la méthode GestionMembres.
   */
  private GestionMembres gt = new GestionMembres();
  
  /**
   * Des informations personnels pour un membre.
   */
  
  private InformationPersonnelle infoC;
  /**
   * Des informations personnels pour un deuxième membre.
   */
  
  private InformationPersonnelle infoC2;
  
  /**
   * Un premier membre pour les tests.
   */
  private Membre membreT1;
  /**
   * Un second membre pour les tests.
   */
  private Membre membreT2;
  
  /**
   * Un ensemble de membres pour les tests. L'ensemble est vide par défaut.
   */
  private Set<InterMembre> ensembleM = gt.ensembleMembres();
  
  /**
   * Initialisation des tests par ajout de membres dans l'ensemble.
   */
  @BeforeEach
  void setUp() throws Exception {
    
    infoC2 = new InformationPersonnelle("Tilia", "EMILE", "Belgique", 35);
    ensembleM.add(membreT1);
    infoC = new InformationPersonnelle("Alan", "PARKER", "France", 20);
    membreT1 = new Membre(infoC);
    membreT2 = new Membre(infoC2);
    ensembleM.add(membreT1);
    ensembleM.add(membreT2);
  }
  
  /**
   * Test d'ajout d'un membre déja présent dans l'ensemble.
   *
   * @param membre_test un membre
   */
  
  @Test
  
  void testAjouterUnMembreDejaPresent() {
    assertFalse(gt.ajouterMembre(membreT1));
  }
  
  /**
   * Test d'ajout d'un membre non présent dans l'ensemble.
   *
   * @param membre_test un membre
   */
  @Test
  void testAjouterUnMembreNonPresent() {
    ensembleM.clear();
    assertTrue(gt.ajouterMembre(membreT1));
  }
  
  /**
   * Test de suppression d'un membre déja présent dans l'ensemble.
   *
   * @param membre_test un membre
   */
  @Test
  void testSupprimerUnMembrePresent() {
    assertTrue(gt.supprimerMembre(membreT1));
  }
  
  /**
   * Test de suppression d'un membre non présent dans l'ensemble.
   *
   * @param membre_test un membre
   */
  @Test
  void testsupprimerUnMembreNonPresent() {
    gt.supprimerMembre(membreT1);
    assertFalse(gt.supprimerMembre(membreT1));
  }
  
  /**
   * Test d'ajout de désignation d'un président. Le rôle de président par défaut
   * est <code>null</code>. Le membre est inclus dans l'ensemble de membres.
   *
   * @param membre_test un membre
   */
  @Test
  void testDesignerUnPresident() {
    assertTrue(gt.designerPresident(membreT1));
    assertTrue(gt.president() == membreT1);
  }
  
  /**
   * Test d'ajout de désignation d'un président. Le rôle de président par défaut
   * est <code>null</code>. Le membre n'appartient pas à l'ensemble de membres.
   *
   * @param membre_test un membre
   */
  @Test
  void testDesignerUnPresidentNonMembre() {
    gt.supprimerMembre(membreT1);
    assertFalse(gt.designerPresident(membreT1));
    assertFalse(gt.president() == membreT1);
    assertTrue(gt.president() == null);
  }
  
  /**
   * Test d'ajout de désignation d'un président. Le rôle de président est
   * occupé. Le membre appartient à l'ensemble de membres.
   *
   * @param membre_test un membre
   */
  @Test
  void testRemplacerUnPresidentAvecUnMembre() {
    gt.designerPresident(membreT2);
    assertTrue(gt.president() == membreT2);
    assertTrue(gt.designerPresident(membreT1));
    assertTrue(gt.president() == membreT1);
  }
  
  /**
   * Test d'ajout de désignation d'un président. Le rôle de président est
   * occupé. Le membre n'appartient pas à l'ensemble de membres.
   *
   * @param membre_test un membre
   */
  @Test
  void testRemplacerUnPresidentAvecUnNonMembre() {
    gt.designerPresident(membreT2);
    gt.supprimerMembre(membreT1);
    assertTrue(gt.president() == membreT2);
    assertFalse(gt.designerPresident(membreT1));
    assertTrue(gt.president() == membreT2);
  }
}
