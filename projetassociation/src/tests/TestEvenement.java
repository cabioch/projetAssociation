package tests;

import association.Evenement;
import association.InformationPersonnelle;
import association.InterMembre;
import association.Membre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests JUnit de {@link Evenement}.
 */
public class TestEvenement {

  private Evenement evenement1;
  private Evenement evenement2;
  private Evenement evenement3;
  private Evenement evenement4;
  private Evenement evenement5;

  private InterMembre membre1;
  private InterMembre membre2;
  private InterMembre membre3;

  @BeforeEach
  void setUp() {
    evenement1 = new Evenement("ev1", "nulpars", LocalDateTime.of(
            1, 1, 1, 1, 0), 10, 2);
    evenement2 = new Evenement("ev2", "nulpars2", LocalDateTime.of(
            1, 1, 1, 1, 5), 5, 10);
    evenement3 = new Evenement("ev2", "nulpars2", LocalDateTime.of(
            1, 1, 1, 1, 15), 5, 10);
    evenement4 = new Evenement("ev1", "nulpars", LocalDateTime.of(
            1, 1, 1, 1, 9), 10, 10);
    evenement5 = new Evenement("ev1", "nulpars", LocalDateTime.of(
            1, 1, 1, 1, 19), 10, 10);

    membre1 = new Membre(new InformationPersonnelle("Jean", "Pierre"));
    membre2 = new Membre(new InformationPersonnelle("Jean2", "Pierre2"));
    membre3 = new Membre(new InformationPersonnelle("Jean3", "Pierre3"));
  }


  /**
   * Tests de chevauchement.
   */
  @Test
  void testChevauchementTemps() {
    assertFalse(compareChevauchement(0, 10, 10, 20));
    assertFalse(compareChevauchement(0, 15, 10, 20));
    assertFalse(compareChevauchement(0, 20, 10, 20));
    assertFalse(compareChevauchement(0, 30, 10, 20));
    assertFalse(compareChevauchement(10, 15, 10, 20));
    assertFalse(compareChevauchement(10, 20, 10, 20));
    assertFalse(compareChevauchement(10, 30, 10, 20));
    assertFalse(compareChevauchement(15, 18, 10, 20));
    assertFalse(compareChevauchement(15, 20, 10, 20));
    assertFalse(compareChevauchement(15, 30, 10, 20));
    assertFalse(compareChevauchement(20, 40, 10, 20));
    assertTrue(compareChevauchement(0, 5, 10, 20));
    assertTrue(compareChevauchement(25, 30, 10, 20));
  }

  /**
   * Fonction intermédiaire pour testChevauchementTemps.
   *
   * @param debut1 Debut evenement 1 minutes
   * @param fin1   Fin evenement 1 minutes
   * @param debut2 Debut evenement 2 minutes
   * @param fin2   Fin evenement 2 minutes
   * @return la valeur de pasDeChevauchementTemps
   */
  private boolean compareChevauchement(int debut1, int fin1, int debut2, int fin2) {
    Evenement ev1 = new Evenement("test", "test", LocalDateTime.of(2022, 10, 10, 0, debut1), (fin1 - debut1), 10);
    Evenement ev2 = new Evenement("test", "test", LocalDateTime.of(2022, 10, 10, 0, debut2), (fin2 - debut2), 10);
    return ev1.pasDeChevauchementTemps(ev2) || ev2.pasDeChevauchementTemps(ev1);
  }

  @Test
  void testChevauchementLieu() {
    // Se chevauchent en temps & en lieu
    assertFalse(evenement1.pasDeChevauchementLieu(evenement4));
    // Se chevauchent uniquement en temps
    assertTrue(evenement1.pasDeChevauchementLieu(evenement2));
    // Se chevauchent uniquement en lieu
    assertTrue(evenement2.pasDeChevauchementLieu(evenement3));
    // Ne se chevauchent pas du tout
    assertTrue(evenement2.pasDeChevauchementLieu(evenement5));
  }

  @Test
  void testAjoutParticipants() {

    // evenement1 a un max de 2 participants
    evenement1.ajouterParticipant(membre1);
    evenement1.ajouterParticipant(membre2);

    // Teste qu'on ne peut pas ajouter un troisième
    assertFalse(evenement1.ajouterParticipant(membre3));
    assertTrue(evenement1.getParticipants().contains(membre1));
    assertTrue(evenement1.getParticipants().contains(membre2));
    assertEquals(2, evenement1.getParticipants().size());
  }

  @Test
  void testDoubleAjout() {
    evenement1.ajouterParticipant(membre1);
    assertFalse(evenement1.ajouterParticipant(membre1));
  }

  @Test
  void testEnleverParticipants() {
    evenement1.ajouterParticipant(membre1);
    evenement1.ajouterParticipant(membre2);
    evenement1.enleverParticipant(membre1);
    evenement1.enleverParticipant(membre2);

    assertFalse(evenement1.getParticipants().contains(membre1));
    assertFalse(evenement1.getParticipants().contains(membre2));
    assertEquals(0, evenement1.getParticipants().size());
  }

  @Test
  void testEnleverNonParticipant() {
    assertFalse(evenement1.enleverParticipant(membre1));
    assertFalse(evenement1.getParticipants().contains(membre1));
    assertEquals(0, evenement1.getParticipants().size());
  }

  @Test
  void testConstructeur1() {
    LocalDateTime date = LocalDateTime.of(2022, 11, 29, 15, 23);
    Evenement ev = new Evenement("test", "endroit", date, 60, 20);
    assertEquals(ev.getNom(), "test");
    assertEquals(ev.getLieu(), "endroit");
    assertEquals(ev.getDate(), date);
    assertEquals(ev.getDuree(), 60);
    assertEquals(ev.getNbParticipantsMax(), 20);
  }

  @Test
  void testConstructeur2() {
    LocalDateTime date = LocalDateTime.of(2022, 11, 29, 15, 23);
    Evenement ev = new Evenement("test", "endroit", 2022, Month.NOVEMBER, 29, 15, 23, 60, 20);
    assertEquals(ev.getNom(), "test");
    assertEquals(ev.getLieu(), "endroit");
    assertEquals(ev.getDate(), date);
    assertEquals(ev.getDuree(), 60);
    assertEquals(ev.getNbParticipantsMax(), 20);
  }

  @Test
  void testConstructeurMauvaiseDates() {
    String nom = "test";
    String lieu = "endroit";
    int annee = 2022;
    Month mois = Month.NOVEMBER;
    int jour = 29;
    int heure = 15;
    int minutes = 23;
    int duree = 60;
    int participants = 60;

    // TODO Réécrire tout le test sur chaque paramètre
  }
}
