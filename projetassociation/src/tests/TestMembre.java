package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import association.Evenement;
import association.InformationPersonnelle;
import association.Membre;
import java.time.Month;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Tests JUnit de la classe {@link association.Membre.Membre}.
 *
 * @author Jeant
 * @see association.Membre
 */
public class TestMembre {
  
  /**
   * Création d'un membre non complet (sans adresse et age) pour les tests.
   */
  private Membre membre;
  
  /**
   * Création d'un membreListeVide non complet(sans adresse et age) et sans
   * liste.
   */
  private Membre membreListeVide;
  
  private Membre membreNonConforme;
  
  /**
   * Création d'un membre complet pour les tests.
   */
  private Membre membreComplet;
  
  /**
   * Une information basique: nom, prenom.
   */
  private InformationPersonnelle infos;
  
  /**
   * Une information basique: nom, prenom mais VIDE.
   */
  private InformationPersonnelle infosVide;
   
  /**
   * Une information complete: nom, prenom, adresse, age.
   */
  private InformationPersonnelle infosComplet;
  
  /**
   * Création d'information personnelle non complet (nom et prenom)pour une
   * liste vide.
   */
  private InformationPersonnelle infosListVide;
  
  /**
   * Création d'information personnelle nom et prenom, adresse nulle et age négatif.
   * liste vide.
   */
  private InformationPersonnelle infosNonConforme;
  
  /**
   *  Instance d'évènements.
   */
  private Evenement evenements;
  private Evenement evenements2;
  private Evenement evenements3;
  private Evenement evenementaVenir1;
  private Evenement evenementaVenir2;
  private Evenement evenementPasse;
  
  /**
   * Initialisation des tests en definissant les deux types d'infos et le
   * membre.
   * 
   */
  @BeforeEach
  void setUp() {
    infos = new InformationPersonnelle("thomas", "jean-andre");
    infosComplet = new InformationPersonnelle("thomas", "jean-andre",
        "14 rue Archives", 20);
    infosVide = new InformationPersonnelle(" ", " ");
    infosListVide = new InformationPersonnelle("test", "test");
    infosNonConforme = new InformationPersonnelle(null, null, null, -20);
    
    membre = new Membre(infos);
    membreComplet = new Membre(infosComplet);
    new Membre(infosVide);
    membreListeVide = new Membre(infosListVide);
    membreNonConforme = new Membre(infosNonConforme);
    new ArrayList<>();
    
    // Ajout d'un événement nommé Classe se trouvant à Lannion et ayant
    // la particularité de commencer le 17 Mars 2022 durant 60 minutes et
    // commençant à 18h30.
    // Cet événement sera ajouté à la liste des événements et on lui ajoutera
    // un
    // membre
    evenements = new Evenement("Classe", "Lannion", 2022, Month.MARCH, 15, 19,
        00, 60, 23);
    evenements2 =
        new Evenement("Fete", "Lannion", 2022, Month.MARCH, 20, 20, 00, 60, 5);
    evenements3 =
        new Evenement("Boum", "Lannion", 2022, Month.MARCH, 25, 21, 00, 30, 10);
    new Evenement("Boum", "Lannion", 2022, Month.MARCH, 25, 21, 00, 30, 10);
    evenementaVenir1 = new Evenement("Party Night", "Brest", 2023,
        Month.JANUARY, 25, 21, 00, 30, 10);
    evenementaVenir2 = new Evenement("Beach Party", "Monaco", 2023,
        Month.JANUARY, 25, 21, 00, 30, 10);
    evenementPasse = new Evenement("Party Sun", "Paris", 2021, Month.MARCH, 25,
        21, 00, 30, 10);
  }
  
  /**
   * Vérifie que l'on peut récuperer les informations d'un membre avec un age
   * négatif.
   */
  @Test
  void testgetInformationPersonnelleAgeNegatif() {
    assertEquals(0, membreNonConforme.getInformationPersonnelle().getAge());
  }
  
  /**
   * Vérifie que l'on peut récuperer les informations d'un membre avec une
   * adresse null.
   */
  @Test
  void testgetInformationPersonnelleAdresseNull() {
    assertEquals("",
        membreNonConforme.getInformationPersonnelle().getAdresse());
  }
  
  /**
   * Vérifie que l'on peut récuperer les informations d'un membre avec un prenom
   * null.
   */
  @Test
  void testgetInformationPersonnellePrenomNull() {
    assertEquals(null,
        membreNonConforme.getInformationPersonnelle().getPrenom());
  }
  
  /**
   * Vérifie que les informations du constructeur sont bien instanciées.
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
   * Vérifie que la definitionInformationPersonnelle ne modifie pas les valeurs
   * si l'age est négatif.
   * 
   */
  @Test
  void testdefinirInformationPersonnelleAgeNegatif() {
    InformationPersonnelle infoModif = new InformationPersonnelle("thomas",
        "jean-andre", "test_invalide", -30);
    membreComplet.definirInformationPersonnnelle(infoModif);
    assertEquals(20, membreComplet.getInformationPersonnelle().getAge());
  }
  
  /**
   * Appelle un membre déjà présent pour le modifier. Son âge et son adresse
   * reste inchangé car son age était valide et qu'aucun autre âge est passé en
   * paramètres.
   * 
   */
  @Test
  void testdefinirInformationPersonnelleModification() {
    InformationPersonnelle infoModif =
        new InformationPersonnelle("thomas", "jean-andre");
    membreComplet.definirInformationPersonnnelle(infoModif);
    assertEquals(20, membreComplet.getInformationPersonnelle().getAge());
  }
  
  
  /**
   * Vérifie que la definitionInformationPersonnelle ne fonctionne pas en
   * donnant un nom et un prenom qui ne correspond pas à l'instance de classe.
   */
  @Test
  void testdefinirInformationPersonnelleNomPrenomFaux() {
    InformationPersonnelle infoModif = new InformationPersonnelle("mauvais_nom",
        "mauvais_prenom", "changement_adresse", 40);
    membreComplet.definirInformationPersonnnelle(infoModif);
    assertFalse("changement_adresse" == membreComplet
        .getInformationPersonnelle().getAdresse());
    assertFalse(membreComplet.getInformationPersonnelle().getAge() == 40);
  }
  
  /**
   * Vérifie que la definitionInformationPersonnelle ne modifie pas l'adresse et
   * l'age si le nom ne correspond pas au nom de l'instance de membre.
   * 
   */
  @Test
  void testdefinirInformationPersonnelleNomInvalide() {
    InformationPersonnelle infoModif =
        new InformationPersonnelle("invalide", "jean-andre", "test_valide", 80);
    membreComplet.definirInformationPersonnnelle(infoModif);
    assertFalse(
        membreComplet.getInformationPersonnelle().getNom() == "invalide");
  }
  
  /**
   * Vérifie que la definitionInformationPersonnelle ne modifie pas l'adresse et
   * l'age si le prenom ne correspond pas au prenom de l'instance de membre.
   * 
   */
  @Test
  void testdefinirInformationPersonnellePrenomInvalide() {
    InformationPersonnelle infoModif =
        new InformationPersonnelle("thomas", "invalide", "test_valide", 80);
    membreComplet.definirInformationPersonnnelle(infoModif);
    assertFalse(
        membreComplet.getInformationPersonnelle().getNom() == "invalide");
    
  }
  
  /**
   * Ajoute 3 participations au membre et vérifie qu'il est bien inscrit dans 3
   * évènements différents.
   */
  @Test
  void testensembleEvenementsMembre3Evenement() {
    // listEvenements.add(evenements);
    // ajout d'un membre dans l'évènement créé dans le setup
    evenements.ajouterParticipant(membreComplet);
    evenements2.ajouterParticipant(membreComplet);
    evenements3.ajouterParticipant(membreComplet);
    membreComplet.ensembleEvenements().add(evenements);
    membreComplet.ensembleEvenements().add(evenements2);
    membreComplet.ensembleEvenements().add(evenements3);
    assertEquals(3, membreComplet.ensembleEvenements().size());
  }
  
  /**
   * Ajoute un évènement à un membre, vérifie que le membre a bien l'évènement
   * mais n'a pas d'évènement à venir.
   */
  @Test
  void testensembleEvenementsaVenirVide() {
    // listEvenements.add(evenements);
    // ajout d'un membre dans l'évènement créé dans le setup
    evenements2.ajouterParticipant(membreComplet);
    membreComplet.ensembleEvenements().add(evenements);
    assertEquals(1, membreComplet.ensembleEvenements().size());
    assertEquals(0, membreComplet.ensembleEvenementsAvenir().size());
  }
  
  /**
   * Ajoute un évènement à un membre mais n'ajoute pas dans l'évènement le
   * membre.
   */
  @Test
  void testensembleEvenementsEvenementVide() {
    // ajout d'un membre dans l'évènement créé dans le setup
    membreComplet.ensembleEvenements().add(evenements);
    assertEquals(0, evenements.getParticipants().size());
    
  }
  
  /**
   * Ajoute une participation d'un membre à un évènement mais n'ajoute pas le
   * membre l'évènement.
   */
  @Test
  void testensembleEvenementsSansAjoutMembre() {
    // ajout d'un membre dans l'évènement créé dans le setup
    evenements.ajouterParticipant(membreListeVide);
    assertEquals(0, membreListeVide.ensembleEvenements().size());
  }
  
  /**
   * Ajoute un évènement à venir au membre et evènement dépassé au membre. La
   * liste de ses évènement à venir ne doit donc comprendre qu'un évènement.
   */
  @Test
  void testensembleEvenementsaVenir2Evenements() {
    evenementaVenir1.ajouterParticipant(membreComplet);
    membreComplet.ensembleEvenements().add(evenementaVenir1);
    evenementPasse.ajouterParticipant(membreComplet);
    membreComplet.ensembleEvenements().add(evenementPasse);
    assertEquals(1, membreComplet.ensembleEvenementsAvenir().size());
  }
  
  /**
   * Ajoute un évènement à venir et évènement passé aà deux membres différents.
   */
  @Test
  void testensembleEvenementsaVenir2Membres() {
    evenementaVenir1.ajouterParticipant(membreComplet);
    membreComplet.ensembleEvenements().add(evenementaVenir1);
    evenementPasse.ajouterParticipant(membreComplet);
    membreComplet.ensembleEvenements().add(evenementPasse);
    
    evenementaVenir2.ajouterParticipant(membre);
    membre.ensembleEvenements().add(evenementaVenir2);
    evenementPasse.ajouterParticipant(membre);
    membre.ensembleEvenements().add(evenementPasse);
    
    assertEquals(1, membreComplet.ensembleEvenementsAvenir().size());
    assertEquals(1, membre.ensembleEvenementsAvenir().size());
  }
  
  /**
   * Appelle la fonction toString d'un membre non complet (sans adresse et sans
   * le renseignement d'âge).
   */
  @Test
  void testtoStringMembreNonComplet() {
    String res = membre.toString();
    assertEquals(
        "Prenom : " + infos.getPrenom() + ", Nom : " + infos.getNom() + " | "
            + " Adresse : " + infos.getAdresse() + ", Age : " + infos.getAge(),
        res);
  }
}
