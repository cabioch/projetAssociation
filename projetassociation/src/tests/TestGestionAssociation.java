package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import association.Evenement;
import association.GestionAssociation;
import association.GestionEvenements;
import association.GestionMembres;
import association.InformationPersonnelle;
import association.InterGestionEvenements;
import association.InterGestionMembres;
import association.Membre;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Month;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de la classe {@link GestionAssociation}.
 *
 *@author Enzo Cabioch
 *@see association.GestionAssociation
 */
public class TestGestionAssociation {
  
  private GestionAssociation association;
  
  /**
   * Des membres pour les tests avec des informations personnelles basiques.
   */
  private static final Membre membre1 =
      new Membre(new InformationPersonnelle("Jean", "Pierre"));
  private static final Membre membre2 =
      new Membre(new InformationPersonnelle("Michel", "Dupont"));
  private static final Membre membre3 =
      new Membre(new InformationPersonnelle("André", "Dupuis"));  

  /**
   * Crée une gestionAssociation et y ajoute des membres et des évenements, puis
   * le sérialise dans testChargementDonnees.txt.
   */
  @BeforeAll
  static void setUpAll() throws FileNotFoundException, IOException {
    GestionAssociation association1 = getAssociationTypique();
    
    // On sérialise les données dans un fichier pour testChargementDonnees
    ObjectOutputStream ois = new ObjectOutputStream(
        new FileOutputStream("testChargementDonnees.txt"));
    ois.writeObject(association1);
    ois.close();
  }
  
  /**
   * Crée une gestionAssociation et y ajoute des membres et des évenements.
   */
  @BeforeEach
  void setUp() throws FileNotFoundException, IOException {
    association = getAssociationTypique();
  }
  
  /**
   * Permet de créer une association typique avec des membres et des évenements,
   * et des membres inscrits à des évenements.
   *
   * @return Une association avec des membres et des évenements pour les tests.
   */
  private static GestionAssociation getAssociationTypique() {
    // Utilisée par les tests de sauvegarde et de chargement
    GestionAssociation asso = new GestionAssociation();
    
    // On récupère les gestionnaires
    InterGestionEvenements gestionEvenements = asso.gestionnaireEvenements();
    InterGestionMembres gestionMembres = asso.gestionnaireMembre();
    
    // On ajoute des membres
    gestionMembres.ajouterMembre(membre1);
    gestionMembres.ajouterMembre(membre2);
    gestionMembres.ajouterMembre(membre3);
    
    // On ajoute des événements
    Evenement evenement1 = gestionEvenements.creerEvenement("Evenement1",
        "Endroit1", 1, Month.DECEMBER, 2022, 0, 0, 60, 10);
    Evenement evenement2 = gestionEvenements.creerEvenement("Evenement2",
        "Endroit2", 1, Month.DECEMBER, 2022, 2, 0, 60, 10);
    Evenement evenement3 = gestionEvenements.creerEvenement("Evenement3",
        "Endroit3", 1, Month.DECEMBER, 2022, 4, 0, 60, 10);
    
    // On ajoute des participants aux evenements
    gestionEvenements.inscriptionEvenement(evenement1, membre1);
    gestionEvenements.inscriptionEvenement(evenement1, membre2);
    gestionEvenements.inscriptionEvenement(evenement2, membre1);
    gestionEvenements.inscriptionEvenement(evenement3, membre2);
    
    return asso;
  }
  
  
  /**
   * Supprime les fichiers utilisés pour les tests.
   */
  @AfterAll
  static void teardown() throws IOException {
    new File("testChargementDonnees.txt").delete();
    new File("testSauvegardeDonnees.txt").delete();
    new File("testSauvegardeNull.txt").delete();
  }
  
  /**
   * Teste que un GestionAssociation juste créé retourne bien un
   * GestionEvenements vide.
   */
  @Test
  void testGetterGestionEvenements() {
    GestionAssociation association2 = new GestionAssociation();
    InterGestionEvenements gestionEvenements =
        association2.gestionnaireEvenements();
    assertTrue(gestionEvenements.ensembleEvenements().isEmpty());
  }
  
  /**
   * Teste que un GestionAssociation juste créé retourne bien un GestionMembres
   * vide.
   */
  @Test
  void testGetterGestionMembres() {
    GestionAssociation association2 = new GestionAssociation();
    InterGestionMembres gestionMembres = association2.gestionnaireMembre();
    assertTrue(gestionMembres.ensembleMembres().isEmpty());
  }
  
  /**
   * Teste que les données écrites correspondent bien aux données du programme.
   */
  @Test
  void testSauvegardeDonnees() throws IOException, ClassNotFoundException {
    // On sauvegarde les données dans le fichier testSauvegardeDonnees.txt
    association.sauvegarderDonnees("testSauvegardeDonnees.txt");
    
    // On récupère les données dans association2
    GestionAssociation association2;
    ObjectInputStream ois =
        new ObjectInputStream(new FileInputStream("testSauvegardeDonnees.txt"));
    association2 = (GestionAssociation) ois.readObject();
    ois.close();
    
    // On vérifie que les données sont les mêmes
    assertEquals(association, association2);
  }
  
  /**
   * Teste que les données lues par chargerDonnées correspondent bien aux
   * données du fichier.
   */
  @Test
  void testChargementDonnees() throws IOException {
    // On charge les données du fichier testChargementDonnees.txt dans
    // association2
    GestionAssociation association2 = new GestionAssociation();
    association2.chargerDonnees("testChargementDonnees.txt");
    
    // On vérifie que les données sont les mêmes
    assertEquals(association, association2);
  }
  
  /**
   * Vérifie que le programme ne produit pas d'erreur si on essaye de sérialiser
   * alors que gestionMembres et gestionEvenements sont à null.
   */
  @Test
  void testSauvegardeNull() throws IOException {
    // On crée un objet vide
    GestionAssociation association2 = new GestionAssociation();
    
    // On sauvegarde puis charge les données
    association2.sauvegarderDonnees("testSauvegardeNull.txt");
    association2.chargerDonnees("testSauvegardeNull.txt");
    
    assertEquals(association2.gestionnaireEvenements(),
        new GestionEvenements());
    assertEquals(association2.gestionnaireMembre(), new GestionMembres());
  }
}
