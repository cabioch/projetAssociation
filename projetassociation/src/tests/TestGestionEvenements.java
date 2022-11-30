package tests;


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
    
    // Ajout d'un deuxième événement nommé Classe se trouvant à Lannion et ayant
    // la particularité de commencé le 17 Mars 2022 durant 60 minutes et
    // commençant à 18h30.
    // Cet événement sera ajouté à la liste des événements lors du test
    // de la création d'un événement
    event5 = new Evenement("Classe", "Lannion", 2022, Month.MARCH, 15, 19, 00, 60, 23);
    
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
   * Test d'inscription d'un membre à un événement dont celui ci
   * chevauche en temps avec un autre événement auquel ce membre participe.
   */
  @Test
  void testInscriptionEvenementChevauchementTemps() {
    assertFalse(gevent.inscriptionEvenement(event5, membre1));
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
  

  
}
