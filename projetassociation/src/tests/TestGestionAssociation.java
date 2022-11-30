package tests;

import association.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGestionAssociation {

  private GestionAssociation association;
  private final GestionAssociation association2 = new GestionAssociation();

  private final Membre membre1 = new Membre(new InformationPersonnelle("Nom1", "Prenom1"));
  private final Membre membre2 = new Membre(new InformationPersonnelle("Nom2", "Prenom2"));
  private final Membre membre3 = new Membre(new InformationPersonnelle("Nom3", "Prenom3"));

  @BeforeEach
  void setUp() {
    // Utilisée par les tests de sauvegarde et de chargement
    association = new GestionAssociation();

    // On récupère les gestionnaires
    InterGestionEvenements gestionEvenements = association.gestionnaireEvenements();
    InterGestionMembres gestionMembres = association.gestionnaireMembre();

    // On ajoute des membres
    gestionMembres.ajouterMembre(membre1);
    gestionMembres.ajouterMembre(membre2);
    gestionMembres.ajouterMembre(membre3);

    // On ajoute des événements
    Evenement evenement1 = gestionEvenements.creerEvenement(
            "Evenement1", "Endroit1", 1, Month.DECEMBER, 2022, 0, 0, 60, 10);
    Evenement evenement2 = gestionEvenements.creerEvenement(
            "Evenement2", "Endroit2", 1, Month.DECEMBER, 2022, 2, 0, 60, 10);
    Evenement evenement3 = gestionEvenements.creerEvenement(
            "Evenement3", "Endroit3", 1, Month.DECEMBER, 2022, 4, 0, 60, 10);

    // On ajoute des participants aux evenements
    gestionEvenements.inscriptionEvenement(evenement1, membre1);
    gestionEvenements.inscriptionEvenement(evenement1, membre2);
    gestionEvenements.inscriptionEvenement(evenement2, membre1);
    gestionEvenements.inscriptionEvenement(evenement3, membre2);
  }

  /**
   * Teste que un GestionAssociation juste créé retourne bien un GestionEvenements vide.
   */
  @Test
  void testGetterGestionEvenements() {
    InterGestionEvenements gestionEvenements = association2.gestionnaireEvenements();
    assertTrue(gestionEvenements.ensembleEvenements().isEmpty());
  }

  /**
   * Teste que un GestionAssociation juste créé retourne bien un GestionMembres vide.
   */
  @Test
  void testGetterGestionMembres() {
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
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("testSauvegardeDonnees.txt"));
    association2 = (GestionAssociation) ois.readObject();
    ois.close();

    // On vérifie que les données sont les mêmes
    assertEquals(association, association2);
  }


  @Test
  /**
   * Teste que les données lues par chargerDonnées correspondent bien aux données du fichier.
   */
  void testChargementDonnees() throws IOException {
    // On charge les données du fichier testLectureDonnees.txt dans association2
    GestionAssociation association2 = new GestionAssociation();
    association2.chargerDonnees("testSauvegardeDonnees.txt");

    // On vérifie que les données sont les mêmes
    assertEquals(association, association2);
  }
}
