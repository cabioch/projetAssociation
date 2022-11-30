package tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import association.Evenement;
import association.GestionEvenements;
import association.InformationPersonnelle;
import association.InterGestionMembres;
import association.InterMembre;
import association.Membre;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests jUnit de la classe GestionEvenements.
 *
 */
public class TestGestionEvenements {
  
  /**
   * gevent est l'instance de la classe GestionEvenements.
   */
  private GestionEvenements gevent;
  
  /**
   * listeEvenements est la liste des Evenements.
   */
  private List<Evenement> listeEvenements = new ArrayList<Evenement>();
  
  /**
   *  Création d'un premier événement appelé event1.
   */
  private Evenement event1;
  
  /**
   *  Création d'un deuxième événement appelé event2.
   */
  private Evenement event2;
  
  /**
   *  Création d'un troisème événement appelé event3.
   */
  private Evenement event3;
  
  /**
   *  Création d'un quatrième événement appelé event4.
   */
  private Evenement event4;
  
  /**
   *  Création d'un cinquième événement appelé event5.
   */
  private Evenement event5;
  
  /**
   *  Création d'un sixième événement appelé event5.
   */
  private Evenement event6;
  
  /**
   *  Création d'un septième événement appelé event5.
   */
  private Evenement event7;
  
  /**
   *  Création d'un huitième événement appelé event5.
   */
  private Evenement event8;
  
  /**
   *  Création d'un huitième événement appelé event5.
   */
  private Evenement event9;
  
  /**
   *  Création d'un premier membre appelé membre1.
   */
  private InterMembre membre1;
  
  /**
   *  Création d'un deuxième membre appelé membre2.
   */
  private InterMembre membre2;
  
  /**
   *  Création des informations personnelles pour le premier membre.
   */
  private InformationPersonnelle infoC;
  
  /**
   *  Création des informations personnelles pour le deuxième membre.
   */
  private InformationPersonnelle infoD;
  
 
  /**
   * On instancie plusieurs événements afin de les ajouter à la liste
   * d'évenements pour notre jeu de tests.
   * 
   */
  @BeforeEach
  void setUp() {
    
    //Instanciation de gevent implémentant l'interface GestionEvenements
    gevent = new GestionEvenements();
    
    //Ajout d'un premier événement nommé Bowling se trouvant à Lannion et ayant
    //la particularité de commencé le 14 Mars 2022 durant 120 minutes et
    //commençant à 14h00. 
    
    event1 = new Evenement("Bowling", "Lannion", 2022, Month.MARCH, 14, 14, 0, 120, 10);
    
    
    // Ajout d'un deuxième événement nommé Sortie se trouvant à Lannion et ayant
    // la particularité de commencé le 15 Mars 2022 durant 45 minutes et
    // commençant à 18h30.
    event2 = new Evenement("Sortie", "Lannion", 2022, Month.MARCH, 15, 18, 30, 45, 1);
    
    // Ajout d'un troisième événement nommé Parc se trouvant à Brest et ayant
    // la particularité de commencé le 15 Juillet 2024 durant 45 minutes et
    // commençant à 13h00.
    event3 = new Evenement("Parc", "Brest", 2024, Month.JULY, 6, 13, 0, 180, 14);
    
    // Ajout d'un quatrième événement nommé Piscine se trouvant à Brest et ayant
    // la particularité de commencé le 15 Juillet 2022 durant 45 minutes et
    // commençant à 13h00.
    //Cet événement n'est pas inscrit dans la liste des événements
    event4 = new Evenement("Piscine", "Brest", 2022, Month.JULY, 6, 13, 0, 180, 14);
    
    // Ajout d'un cinquième événement nommé Classe se trouvant à Tréguier et ayant
    // la particularité de commencé le 15 Mars 2022 durant 60 minutes et
    // commençant à 19h00.
    // Cet événement sera ajouté à la liste des événements lors du test
    // de la création d'un événement
    event5 = new Evenement("Classe", "Tréguier", 2022, Month.MARCH, 15, 19, 00, 60, 23);
    
    // Ajout d'un sixième événement nommé Classe se trouvant à Lannion et ayant
    // la particularité de commencé le 15 Mars 2022 durant 60 minutes et
    // commençant à 19h00.
    // Cet événement sera ajouté à la liste des événements lors du test
    // de la création d'un événement
    event6 = new Evenement("Classe", "Lannion", 2022, Month.MARCH, 15, 19, 00, 60, 23);
    
    // Ajout d'un septième événement nommé TV se trouvant à Brest et ayant
    // la particularité de commencé le 3 Janvier 2023 durant 60 minutes et
    // commençant à 21h00.
    // Cet événement sera ajouté à la liste des événements lors du test
    // de la création d'un événement
    event7 = new Evenement("TV", "Brest", 2023, Month.JANUARY, 3, 21, 00, 60, 23);
    
    
    event8 = new Evenement("Football", "Qatar", 2023, Month.MAY, 20, 16, 00, 130, 4);
    
    event9 = new Evenement("Mer", "Bretagne", 2022, Month.MARCH, 15, 18,
        45, 100, 23);
    
    // Ajout du prénom Titi et nom Beau comme nom pour le premier membre
    infoC = new InformationPersonnelle("Titi", "Beau");
    
    // Ajout du prénom Toche et nom Rapide comme nom pour le deuxième membre
    infoD = new InformationPersonnelle("Toche", "Rapide");
    
    // On créer le membre1 avec ses infos personnelles correspondantes
    membre1 = new Membre(infoC);
    
    // On créer le membre2 avec ses infos personnelles correspondantes
    membre2 = new Membre(infoD);
    
    listeEvenements = gevent.ensembleEvenements();
    
    // On ajoute l'événement 1.
    listeEvenements.add(event1);
    // On ajoute l'événement 2.
    listeEvenements.add(event2);
    // On ajoute l'événement 3.
    listeEvenements.add(event3);
    // On ajoute l'événement 7.
    listeEvenements.add(event7);
    
    // On inscrit le membre1 au deuxième événement 
    gevent.inscriptionEvenement(event2, membre1);
    
    // On ajoute le membre 2 à la liste des participants de l'événement 1
    event1.ajouterParticipant(membre2);
    membre2.ensembleEvenements().add(event1);
    
  }
  
  

  /**
   * Test d'inscription réussie d'un membre pour un événement dont
   * le nombre de participant n'est pas atteint et dont l'événement
   * auquel le membre participe ne chevauche pas en temps avec un autre 
   * événement auquel il participe aussi.
   */
  @Test
  void testInscriptionEvenementReusssie() {
    assertTrue(gevent.inscriptionEvenement(event1, membre1));
  }
  
  /**
   * Test d'inscription d'un membre à un événement dont celui ci comporte un
   * chevauche en temps avec un autre événement auquel ce membre participe.
   */
  @Test
  void testInscriptionEvenementChevauchementTemps() {
    assertFalse(gevent.inscriptionEvenement(event5, membre1));
  }
  
  /**
   * Test d'inscription d'un membre à un événement dont celui ci comporte un
   * chevauche en lieu avec un autre événement auquel ce membre participe.
   */
  @Test
  void testInscriptionEvenementChevauchementLieu() {
    assertFalse(gevent.inscriptionEvenement(event6, membre1));
  }
  
  
  /**
   * Test d'inscription à un événement d'un membre. C'est inscription
   * sera échouée car le nombre max de participants sera atteint.
   */
  @Test
  void testInscriptionEvtDepMaxParticipant() {
    assertFalse(gevent.inscriptionEvenement(event2, membre2));
  }
  
  /**
   * Test d'annulation d'un événement réussie auquel un membre participe et dont 
   * cet événement est réellement existant.
   */
  @Test
  void testAnnulerEvenementExistant() {
    assertTrue(gevent.annulerEvenement(event1, membre2));
  }
  
  /**
   * Test d'annulation d'un événement non réussie auquel cet événement
   * n'existe pas réellement.
   */
  @Test
  void testAnnulerEvenementNonExistant() {
    assertFalse(gevent.annulerEvenement(event4, membre1));
  }
  
  /**
   * Test d'un événement existant mais dont le membre n'est pas participant 
   * de celui ci.
   */
  @Test
  void testAnnulerEvenementMembreNonExistant() {
    assertFalse(gevent.annulerEvenement(event4, membre2));
  }
  
  /**
   * Test de retour de tous les événements de l'association
   * Le test doit nous renvoyer les 3 événements de l'association.
   */
  @Test
  void testEnsembleEvenements() {
    assertEquals(4, gevent.ensembleEvenements().size());
  }
  
  /**
   * Test de retour de tous les événements de l'association comportant
   * 0 événements.
   * Le test doit nous renvoyer Null car la liste ne contient 0 événements.
   */
  @Test
  void testEnsembleEvenementsNull() {
    gevent.ensembleEvenements().clear();
    assertEquals(0, gevent.ensembleEvenements().size());
  }
  
  /**
   * Test de retour de tous les événements à venir de l'association
   * Le test doit nous renvoyer les 2 événements de l'association
   * qui sont à venir.
   */
  @Test
  void testEnsembleEvenementsAvenir() {
    assertEquals(2, gevent.ensembleEvenementAvenir().size());
  }
  /**
   * Test de retour de tous les événements à venir de l'association
   * n'ayant pas d'événement à venir.
   * Le test doit rien nous renvoyer.
   */
  @Test
  void testEnsembleEvenementsAvenirNull() {
    listeEvenements.clear();
    assertEquals(0, gevent.ensembleEvenementAvenir().size());
  }
  
  /**
   * Test de la création d'un événement ne comportant ni chevauchement
   * en temps, ni chevauchement en lieu ni erreur de format de date ni erreur
   * de format de l'heure.
   */
  @Test
  void testCreationEvenementStandard() {
    assertEquals(event8, gevent.creerEvenement("Football", "Qatar", 20, Month.MAY, 2023, 16,
        00, 130, 4));
  }
  
  /**
   * Test de la création d'un événement comportant un chevauchement
   * en temps avec un autre événement de l'association. Cet événement
   * ne comporte ni de chevauchement en lieu, ni de problème de format d'heure et de 
   * date.
   * La méthode doit créer l'événement et la retourner car elle ne comporte pas de chevauchement 
   * en lieu.
   */
  @Test
  void testCreationEvenementChevauchementTemps() {
    assertEquals(event9, gevent.creerEvenement("Mer", "Bretagne", 15, Month.MARCH, 2022, 18,
        45, 100, 23));
  }
  
  /**
   * Test de la création d'un événement comportant un chevauchement
   * en temps avec un autre événement de l'association. Cet événement
   * ne comporte ni de chevauchement en lieu, ni de problème de format d'heure et de 
   * date.
   * La méthode doit créer l'événement et la retourner car elle ne comporte pas de chevauchement 
   * en lieu.
   */
  @Test
  void testCreationEvenementChevauchementLieu() {
    assertEquals(null, gevent.creerEvenement("Mer", "Lannion", 15, Month.MARCH, 2022, 18,
        45, 100, 23));
  }
  
  /**
   * Test de la suppresion d'un événement issue de l'association.
   * L'association comporte 4 événements. Après supression de l'événement1,
   * l'association comportera 3 événements.
   */
  @Test
  void testSuppressionEvenementStandard() {
    gevent.supprimerEvenement(event1);
    assertEquals(3, gevent.ensembleEvenements().size());
  }
  
  /**
   * Test de la suppresion d'un événement non issue de l'association.
   * L'association comporte 4 événements. Après supression de l'événement6
   * qui n'est pas dans l'association, doit toujours comporter 4 événements.
   */
  @Test
  void testSuppressionEvenementNonStandard() {
    gevent.supprimerEvenement(event6);
    assertEquals(4, gevent.ensembleEvenements().size());
  }
  
  
  
  
  

  
}
