package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import association.GestionMembres;
import association.InterMembre;

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
  private GestionMembres gt;
  
  /**
   * Un premier membre pour les tests.
   */
  private InterMembre membre_test;
  /**
   * Un second membre pour les tests.
   */
  private InterMembre membre_test2;
  
  /**
   * Un ensemble de membres pour les tests. L'ensemble est vide par défaut.
   */
  private Set<InterMembre> ensemble_membres = null;
  
  
  /**
   * Initialisation des tests par ajout de membres dans l'ensemble.
   */
  @BeforeEach
  void setUp() {
    ensemble_membres.add(membre_test);
    ensemble_membres.add(membre_test2);
    
  }
  
  /**
   * Test d'ajout d'un membre déja présent dans l'ensemble.
   *
   * @param membre_test un membre
   */
  @Test
  void TestAjouterUnMembreDejaPresent(InterMembre membre_test) {
    assertFalse(membre_test, gt.ajouterMembre(membre_test));
  }
  
  /**
   * Test d'ajout d'un membre non présent dans l'ensemble.
   *
   * @param membre_test un membre
   */
  @Test
  void TestAjouterUnMembreNonPresent(InterMembre membre_test) {
    ensemble_membres.clear();
    assertTrue(membre_test, gt.ajouterMembre(membre_test));
  }
  
  /**
   * Test de suppression d'un membre déja présent dans l'ensemble.
   *
   * @param membre_test un membre
   */
  @Test
  void TestSupprimerUnMembrePresent(InterMembre membre_test) {
    assertTrue(membre_test, gt.supprimerMembre(membre_test));
  }
  
  /**
   * Test de suppression d'un membre non présent dans l'ensemble.
   *
   * @param membre_test un membre
   */
  @Test
  void TestsupprimerUnMembreNonPresent(InterMembre membre_test) {
    gt.supprimerMembre(membre_test);
    assertFalse(membre_test, gt.supprimerMembre(membre_test));
  }
  
  /**
   * Test d'ajout de désignation d'un président. Le rôle de président par défaut
   * est <code>null</code>. Le membre est inclus dans l'ensemble de membres.
   *
   * @param membre_test un membre
   */
  @Test
  void TestDesignerUnPresident(InterMembre membre_test) {
    assertTrue(membre_test, gt.designerPresident(membre_test));
    assertTrue(membre_test, gt.president());
  }
  
  /**
   * Test d'ajout de désignation d'un président. Le rôle de président par défaut
   * est <code>null</code>. Le membre n'appartient pas à l'ensemble de membres.
   *
   * @param membre_test un membre
   */
  @Test
  void TestDesignerUnPresidentNonMembre(InterMembre membre_test) {
    gt.supprimerMembre(membre_test);
    assertFalse(membre_test, gt.designerPresident(membre_test));
    assertFalse(membre_test, gt.president());
    assertTrue(null, gt.president());
  }
  
  /**
   * Test d'ajout de désignation d'un président. Le rôle de président est
   * occupé. Le membre appartient à l'ensemble de membres.
   *
   * @param membre_test un membre
   */
  @Test
  void TestRemplacerUnPresidentAvecUnMembre(InterMembre membre_test) {
    gt.designerPresident(membre_test2);
    assertTrue(membre_test2, gt.president());
    assertTrue(membre_test, gt.designerPresident(membre_test));
    assertTrue(membre_test, gt.president());
  }
  
  /**
   * Test d'ajout de désignation d'un président. Le rôle de président est
   * occupé. Le membre n'appartient pas à l'ensemble de membres.
   *
   * @param membre_test un membre
   */
  @Test
  void TestRemplacerUnPresidentAvecUnNonMembre(InterMembre membre_test) {
    gt.designerPresident(membre_test2);
    gt.supprimerMembre(membre_test);
    assertTrue(membre_test2, gt.president());
    assertFalse(membre_test, gt.designerPresident(membre_test));
    assertTrue(membre_test2, gt.president());
  }
}
