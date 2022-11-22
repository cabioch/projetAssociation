package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import association.Evenement;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

/**
 * Tests JUnit de {@link Evenement}.
 */
public class TestEvenement {
  /**
   * Tests de chevauchement.
   */
  @Test
  void testChevaucheEnTemps() {
    Evenement evenement1 = new Evenement("ev1", "nulpars", LocalDateTime.of(
            1, 1, 1, 1, 0), 10, 10);
    Evenement evenement2 = new Evenement("ev2", "nulpars2", LocalDateTime.of(
            1, 1, 1, 1, 5), 5, 10);
    Evenement evenement3 = new Evenement("ev2", "nulpars2", LocalDateTime.of(
            1, 1, 1, 1, 15), 5, 10);
    Evenement evenement4 = new Evenement("ev1", "nulpars", LocalDateTime.of(
            1, 1, 1, 1, 9), 10, 10);
    assertFalse(evenement1.pasDeChevauchementTemps(evenement2));
    assertFalse(evenement1.pasDeChevauchementLieu(evenement4));
    assertTrue(evenement2.pasDeChevauchementLieu(evenement3));
    assertFalse(evenement1.pasDeChevauchementLieu(evenement1));
    assertTrue(evenement1.pasDeChevauchementLieu(evenement2));
    assertTrue(evenement1.pasDeChevauchementTemps(evenement3));
  }
}
