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
 * TODO Commenter chaque test
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
  private final String nom = "test";
  private final String lieu = "endroit";

  @BeforeEach
  void setUp() {
    evenement1 =
            new Evenement("ev1", "nulpars", LocalDateTime.of(2022, 1, 1, 1, 0), 10, 2);
    evenement2 = new Evenement("ev2", "nulpars2",
            LocalDateTime.of(2022, 1, 1, 1, 5), 5, 10);
    evenement3 = new Evenement("ev3", "nulpars2",
            LocalDateTime.of(2022, 1, 1, 1, 15), 5, 10);
    evenement4 = new Evenement("ev1", "nulpars",
            LocalDateTime.of(2022, 1, 1, 1, 9), 10, 10);
    evenement5 = new Evenement("ev1", "nulpars",
            LocalDateTime.of(2022, 1, 1, 1, 19), 10, 10);

    membre1 = new Membre(new InformationPersonnelle("Jean", "Pierre"));
    membre2 = new Membre(new InformationPersonnelle("Jean2", "Pierre2"));
    membre3 = new Membre(new InformationPersonnelle("Jean3", "Pierre3"));
  }


  /**
   * Tests de chevauchement. Utilise la fonction compareChevauchement et compare à un évenement qui va de 10 à 20
   * minutes.
   */
  @Test
  void testChevauchementTemps() {
    // compare a un evenement qui va de 10 minutes a 20
    assertFalse(compareChevauchement(0, 10));
    assertFalse(compareChevauchement(0, 15));
    assertFalse(compareChevauchement(0, 20));
    assertFalse(compareChevauchement(0, 30));
    assertFalse(compareChevauchement(10, 15));
    assertFalse(compareChevauchement(10, 20));
    assertFalse(compareChevauchement(10, 30));
    assertFalse(compareChevauchement(15, 18));
    assertFalse(compareChevauchement(15, 20));
    assertFalse(compareChevauchement(15, 30));
    assertFalse(compareChevauchement(20, 40));
    assertTrue(compareChevauchement(0, 5));
    assertTrue(compareChevauchement(25, 30));
  }

  /**
   * Fonction intermédiaire pour testChevauchementTemps.
   *
   * @param debut1 Debut evenement 1 minutes
   * @param fin1   Fin evenement 1 minutes
   * @return la valeur de pasDeChevauchementTemps
   */
  private boolean compareChevauchement(int debut1, int fin1) {
    Evenement ev1 = new Evenement("test", "test",
            LocalDateTime.of(2022, 10, 10, 0, debut1), (fin1 - debut1), 10);
    Evenement ev2 = new Evenement("test", "test",
            LocalDateTime.of(2022, 10, 10, 0, 10), (20 - 10), 10);
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
    Evenement ev = new Evenement("test", "endroit", 2022, Month.NOVEMBER, 29,
            15, 23, 60, 20);
    assertEquals(ev.getNom(), "test");
    assertEquals(ev.getLieu(), "endroit");
    assertEquals(ev.getDate(), date);
    assertEquals(ev.getDuree(), 60);
    assertEquals(ev.getNbParticipantsMax(), 20);
  }

  @Test
  void testEvenementAnneeNegative() {
    Evenement ev1 = new Evenement(nom, lieu, -200, Month.NOVEMBER, 10, 10, 10, 10, 10);
    Evenement ev2 = new Evenement(nom, lieu, LocalDateTime.of(-200, 1, 1, 1, 1), 10, 10);
    assertTrue(isDateNulle(ev1));
    assertTrue(isDateNulle(ev2));
  }

  @Test
  void testEvenementAnneeZero() {
    Evenement ev3 = new Evenement(nom, lieu, 0, Month.NOVEMBER, 10, 10, 10, 10, 10);
    Evenement ev4 = new Evenement(nom, lieu, LocalDateTime.of(0, 1, 1, 1, 1), 10, 10);
    assertTrue(isDateNulle(ev3));
    assertTrue(isDateNulle(ev4));
  }

  @Test
  void testEvenementMinutesNegatives() {
    Evenement ev1 = new Evenement(nom, lieu, 2022, Month.NOVEMBER, 10, 10, -10, 10, 10);
    assertTrue(isDateNulle(ev1));
  }

  @Test
  void testEvenementHeureNegative() {
    Evenement ev1 = new Evenement(nom, lieu, 2022, Month.NOVEMBER, 10, -10, 10, 10, 10);
    assertTrue(isDateNulle(ev1));
  }

  @Test
  void testEvenementZeroParticipants() {
    Evenement ev1 = new Evenement(nom, lieu, 2022, Month.NOVEMBER, 10, 10, 10, 10, 0);
    Evenement ev2 = new Evenement(nom, lieu, LocalDateTime.of(2022, 1, 1, 1, 1), 10, 0);
    assertEquals(-1, ev1.getNbParticipantsMax());
    assertEquals(-1, ev2.getNbParticipantsMax());
  }

  @Test
  void testParticipantsNegatifs() {
    Evenement ev1 = new Evenement(nom, lieu, 2022, Month.NOVEMBER, 10, 10, 10, 10, -10);
    Evenement ev2 = new Evenement(nom, lieu, LocalDateTime.of(2022, 1, 1, 1, 1), 10, -10);
    assertEquals(-1, ev1.getNbParticipantsMax());
    assertEquals(-1, ev2.getNbParticipantsMax());
  }

  @Test
  void testDureeZero() {
    Evenement ev1 = new Evenement(nom, lieu, 2022, Month.NOVEMBER, 10, 10, 10, 0, 10);
    Evenement ev2 = new Evenement(nom, lieu, LocalDateTime.of(2022, 1, 1, 1, 1), 0, 10);
    assertEquals(0, ev1.getDuree());
    assertEquals(0, ev2.getDuree());
  }

  @Test
  void testDureeNegative() {
    Evenement ev1 = new Evenement(nom, lieu, 2022, Month.NOVEMBER, 10, 10, 10, -100, 10);
    Evenement ev2 = new Evenement(nom, lieu, LocalDateTime.of(2022, 1, 1, 1, 1), -10, 10);
    assertEquals(0, ev1.getDuree());
    assertEquals(0, ev2.getDuree());
  }

  @Test
  void testLieuNull() {
    Evenement ev1 = new Evenement(nom, null, 2022, Month.NOVEMBER, 10, 10, 10, 100, 10);
    Evenement ev2 = new Evenement(nom, null, LocalDateTime.of(2022, 1, 1, 1, 1), 10, 10);
    assertEquals("", ev1.getLieu());
    assertEquals("", ev2.getLieu());
  }

  @Test
  void testNomNull() {
    Evenement ev1 = new Evenement(null, lieu, 2022, Month.NOVEMBER, 10, 10, 10, 100, 10);
    Evenement ev2 = new Evenement(null, lieu, LocalDateTime.of(2022, 1, 1, 1, 1), 10, 10);
    assertEquals("", ev1.getNom());
    assertEquals("", ev2.getNom());
  }

  /**
   * Fonction intermédiaire pour testConstructeurMauvaisesDates, qui permet de savoir si la date d'un évenement est bien
   * la date nulle. (Année 0, Mois 1, Jour 1, Heure 0, Minute 0)
   *
   * @param ev L'évenement qu'on souhaite tester
   * @return true si l'évenement est bien à la date nulle
   */
  private boolean isDateNulle(Evenement ev) {
    LocalDateTime dateNulle = LocalDateTime.of(0, 1, 1, 0, 0);
    return (ev.getDate().equals(dateNulle));
  }
}
