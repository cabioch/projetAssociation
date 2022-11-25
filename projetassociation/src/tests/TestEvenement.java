package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import association.Evenement;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de {@link Evenement}.
 */
public class TestEvenement {

  private Evenement evenement1, evenement2, evenement3, evenement4, evenement5;

  @BeforeEach
  void setUp() {
    evenement1 = new Evenement("ev1", "nulpars", LocalDateTime.of(
            1, 1, 1, 1, 0), 10, 10);
    evenement2 = new Evenement("ev2", "nulpars2", LocalDateTime.of(
            1, 1, 1, 1, 0), 5, 10);
    evenement3 = new Evenement("ev2", "nulpars2", LocalDateTime.of(
            1, 1, 1, 1, 0), 15, 10);
    evenement4 = new Evenement("ev1", "nulpars", LocalDateTime.of(
            1, 1, 1, 1, 5), 10, 10);
    evenement5 = new Evenement("ev1", "nulpars", LocalDateTime.of(
            1, 1, 1, 1, 2), 2, 10);
  }


  /**
   * Tests de chevauchement.
   */
  @Test
  void testChevauchement() {
    // TODO Tester tout les cas de chevauchements en temps & tout les cas en lieu
    assertFalse(evenement1.pasDeChevauchementTemps(evenement1));
    assertFalse(evenement1.pasDeChevauchementTemps(evenement2));
    assertFalse(evenement1.pasDeChevauchementTemps(evenement3));
    assertFalse(evenement3.pasDeChevauchementTemps(evenement4));
    assertFalse(evenement4.pasDeChevauchementTemps(evenement3));
    assertFalse(evenement1.pasDeChevauchementTemps(evenement5));
    assertFalse(evenement5.pasDeChevauchementTemps(evenement1));

    assertTrue(evenement2.pasDeChevauchementTemps(evenement3));
    assertFalse(evenement1.pasDeChevauchementTemps(evenement1));
    assertTrue(evenement1.pasDeChevauchementTemps(evenement2));
    assertTrue(evenement1.pasDeChevauchementTemps(evenement3));
  }

  @Test
  void testAjoutParticipants() {

  }
}
