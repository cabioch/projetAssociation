package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
   * Des informations personnelles qui aura une chaine vide dans le nom uniquement.
   */
  private InformationPersonnelle infoVideNom;
  
  /**
   * Des informations personnelles qui auront une chaine vide dans le prénom uniquement.
   */
  private InformationPersonnelle infoVidePrenom;
  
  /**
   * Des informations personnelles qui auront des valeurs <code>null</code> en prénom et nom.
   */
  private InformationPersonnelle infoNull;
  
  /**
   * Des informations personnelles qui aura une valeur <code>null</code> pour le prénom uniquement.
   */
  private InformationPersonnelle infoNullPrenom;
  
  /**
   * Des informations personnelles qui aura une valeur <code>null</code> pour le nom uniquement.
   */
  private InformationPersonnelle infoNullNom;
  
  
  /**
   * Des membres pour les tests.
   */
  private Membre membreT1;
  private Membre membreT2;
  private Membre membreT3;
  
  /**
   * Un ensemble de membres représentant une association pour les tests.
   * L'ensemble est vide par défaut.
   */
  private Set<InterMembre> ensembleM = gt.ensembleMembres();
  
  /**
   * Un ensemble de membres représentant une association pour les tests.
   * L'ensemble est <code>null</code> par défaut.
   */
  private Set<InterMembre> ensembleM2;
  
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
    infoNull = new InformationPersonnelle(null, null, "Luxembourg", 60);
    infoNullNom = new InformationPersonnelle("Marissa", null, "Pays-Bas", 32);
    infoNullPrenom = new InformationPersonnelle(null, "JANIG", "Espagne", 70);
    membreT1 = new Membre(infoC);
    membreT2 = new Membre(infoC2);
    membreT3 = new Membre(infoC2);
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
   * Test d'ajout d'un membre ayant une chaine vide en nom et prénom.
   */
  @Test
  void testAjouterUnMembreChainevideNomEtPrenom() {
	membreT3 = new Membre(infoVide);
    assertFalse(gt.ajouterMembre(membreT3));
  }
  
  /**
   * Test d'ajout d'un membre ayant des valeurs <code>null</code> en nom et prénom.
   */
  @Test
  void testAjouterUnMembreAvecAucunPrenomEtNom() {
	  membreT3 = new Membre(infoNull);
	  assertFalse(gt.ajouterMembre(membreT3));
  }
  
  /**
   * Test d'ajout d'un membre ayant des valeurs <code>null</code> en prénom.
   */
  
  @Test
  void testAjouterUnMembreAvecAucunPrenom() {
	  membreT3 = new Membre(infoNullPrenom);
	    assertFalse(gt.ajouterMembre(membreT3));
  }
  
  /**
   * Test d'ajout d'un membre ayant des valeurs <code>null</code> en nom.
   */
  
  @Test
  void testAjouterUnMembreAvecAucunNom() {
	membreT3 = new Membre(infoNullNom);
	assertFalse(gt.ajouterMembre(membreT3));
  }
  
  
  /**
   * Test d'ajout d'un membre une chaine vide pour nom.
   */
  @Test
  void testAjouterUnMembreAvecChaineVidePrenom() {
	  membreT3 = new Membre(infoVidePrenom);
	  assertFalse(gt.ajouterMembre(membreT3));
  }
  
  /**
   * Test d'ajout d'un membre avec une chaine vide pour prénom.
   */
  @Test
  void testAjouterUnMembreAvecChaineVideNom() {
	 membreT3 = new Membre(infoVideNom);
	 assertFalse(gt.ajouterMembre(membreT3));
  }
  
  /**
   * Test de suppression d'un membre présent dans l'association.
   */
  @Test
  void testSupprimerUnMembrePresent() {
    assertTrue(gt.supprimerMembre(membreT1));
  }
  
  /**
   * Test de suppression d'un membre non présent dans l'association.
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
   * reste à la valeur <code>null</code>.
   */
  @Test
  void testDesignerUnPresidentNonMembre() {
    gt.supprimerMembre(membreT1);
    assertFalse(gt.designerPresident(membreT1));
    assertTrue(gt.president() ==null);
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
  
  /**
   * Test du getter de construction de l'ensemble des membres.
   * Si l'ensemble est <code>null</code>, la méthode le renvoie vide. Sinon
   * elle renvoie la liste des membres.
   */
  @Test
  void testGetterEnsembleMembre() {
	  assertTrue(ensembleM2 == null);
	  ensembleM2 = gt.ensembleMembres();
	  assertTrue(ensembleM2 != null);
	  assertFalse(ensembleM.isEmpty());
  }
}
