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
   * Des informations personnelles pour un membre.
   */
  
  private InformationPersonnelle infoC;
  /**
   * Des informations personnelles pour un deuxième membre.
   */
  
  private InformationPersonnelle infoC2;
  
  /**
   * Des informations personnelles qui auront une chaine vide dans le nom et le
   * prénom.
   */
  private InformationPersonnelle infoVide;
  
  /**
   * Des informations personnelles qui aura une chaine vide dans le nom.
   */
  private InformationPersonnelle infoVideNom;
  
  /**
   * Des informations personnelles qui auront une chaine vide dans le prénom.
   */
  private InformationPersonnelle infoVidePrenom;
  
  /**
   * Des membres pour les tests.
   */
  private Membre membreT1;
  private Membre membreT2;
  private Membre membreT3;
  private Membre membreT4;
  private Membre membreT5;
  private Membre membreT6;
  
  /**
   * Un ensemble de membres représentant une association pour les tests.
   * L'ensemble est vide par défaut.
   */
  private Set<InterMembre> ensembleM = gt.ensembleMembres();
  
  /**
   * Initialisation des tests par ajout de membres dans l'ensemble et des
   * informations les concernant.
   */
  @BeforeEach
  void setUp() throws Exception {
    
    infoC2 = new InformationPersonnelle("Tilia", "EMILE", "Belgique", 35);
    infoC = new InformationPersonnelle("Alan", "PARKER", "France", 20);
    infoVide = new InformationPersonnelle("", "", "Luxembourg", 50);
    infoVideNom = new InformationPersonnelle("Eliot", "", "Allemagne", 20);
    infoVidePrenom = new InformationPersonnelle("", "PATINSON", "USA", 20);
    membreT1 = new Membre(infoC);
    membreT2 = new Membre(infoC2);
    membreT3 = new Membre(infoC2);
    membreT4 = new Membre(infoVide);
    membreT5 = new Membre(infoVideNom);
    membreT6 = new Membre(infoVidePrenom);
    ensembleM.add(membreT1);
    ensembleM.add(membreT2);
  }
  
  /**
   * Test d'ajout d'un membre déja présent dans l'ensemble.
   */
  
  @Test
  void testAjouterUnMembreDejaPresent() {
    assertFalse(gt.ajouterMembre(membreT1));
  }
  
  /**
   * Test d'ajout d'un membre non présent dans l'ensemble.
   *
   */
  @Test
  void testAjouterUnMembreNonPresent() {
    ensembleM.clear();
    assertTrue(gt.ajouterMembre(membreT1));
  }
  
  /**
   * Test d'ajout d'un membre ayant les même noms et prénoms qu'un autre.
   */
  @Test
  void testAjouterUnMembreIdentique() {
    assertFalse(gt.ajouterMembre(membreT3));
  }
  
  /**
   * Test d'ajout d'un membre ayant aucun prénom ou nom.
   */
  @Test
  void testAjouterUnMembreAvecAucunPrenomEtNom() {
    assertFalse(gt.ajouterMembre(membreT4));
  }
  
  /**
   * Test d'ajout d'un membre une chaine vide pour nom.
   */
  @Test
  void testAjouterUnMembreAvecAucunPrenom() {
    assertFalse(gt.ajouterMembre(membreT6));
  }
  
  /**
   * Test d'ajout d'un membre avec une chaine vide pour prénom.
   */
  @Test
  void testAjouterUnMembreAvecAucunNom() {
    assertFalse(gt.ajouterMembre(membreT5));
  }
  
  
  @Test
  void testSupprimerUnMembrePresent() {
    assertTrue(gt.supprimerMembre(membreT1));
  }
  
  /**
   * Test de suppression d'un membre non présent dans l'ensemble.
   */
  @Test
  void testsupprimerUnMembreNonPresent() {
    gt.supprimerMembre(membreT1);
    assertFalse(gt.supprimerMembre(membreT1));
  }
  
  /**
   * Test de désignation du président. Le rôle de président par défaut est
   * <code>null</code>. Le membre appartient à l'association. Le président est
   * le membre ajouté.
   */
  @Test
  void testDesignerUnPresident() {
    assertTrue(gt.designerPresident(membreT1));
    assertTrue(gt.president().equals(membreT1));
  }
  
  /**
   * Test de désignation d'un président. Le rôle de président par défaut est
   * <code>null</code>. Le membre n'appartient pas à l'association. Le président
   * reste a la valeur <code>null</code>.
   */
  @Test
  void testDesignerUnPresidentNonMembre() {
    gt.supprimerMembre(membreT1);
    assertFalse(gt.designerPresident(membreT1));
    assertTrue(gt.president() == null);
  }
  
  /**
   * Test de remplacement du président. Le rôle de président est occupé par un
   * membre de l'association. Le membre qui remplace l'ancien président
   * appartient à l'ensemble de membres. Un nouveau président est désigné.
   *
   */
  @Test
  void testRemplacerUnPresidentAvecUnMembre() {
    gt.designerPresident(membreT2);
    assertTrue(gt.president().equals(membreT2));
    assertTrue(gt.designerPresident(membreT1));
    assertTrue(gt.president().equals(membreT1));
  }
  
  /**
   * Test de remplacement du président. Le rôle de président est occupé par un
   * membre de l'association. Le membre qui remplace l'ancien président
   * n'appartient pas à l'ensemble de membres. Le président reste inchangé.
   *
   */
  @Test
  void testRemplacerUnPresidentAvecUnNonMembre() {
    gt.designerPresident(membreT2);
    gt.supprimerMembre(membreT1);
    assertTrue(gt.president().equals(membreT2));
    assertFalse(gt.designerPresident(membreT1));
    assertTrue(gt.president().equals(membreT2));
  }
}
