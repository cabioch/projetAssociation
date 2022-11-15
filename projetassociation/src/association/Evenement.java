package association;

import java.time.LocalDateTime;
import java.util.Set;

public class Evenement implements java.io.Serializable {
  
  private String nom;

  private String lieu;

  private LocalDateTime date;

  private int duree;

  private int nbParticipantsMax;

  private Set<InterMembre> participants;

  // A implémenter : retourne vrai si deux événements ne se chevauchent pas
  // dans le même lieu en même temps
  public boolean pasDeChevauchementLieu(Evenement evt) {
    throw new UnsupportedOperationException("Méthode non implémentée !");
  }

  // A implémenter : retourne vrai si deux événements ne se chevauchent pas
  // dans le temps (indépendamment du lieu)
  public boolean pasDeChevauchementTemps(Evenement evt) {
    throw new UnsupportedOperationException("Méthode non implémentée !");
  }

  // A compléter :
  //
  // - génération automatique des getters, setters, constructeurs,
  // des méthodes hashCode, toString et equals.
  // Modifiez manuellement le code généré au besoin. Rajoutez notamment
  // les méthodes de gestion des participants à l'événement.
  //
  // - Rajoutez un/des constructeurs permettant de construire plus facilement
  // un événement sans avoir besoin de passer un paramètre de type LocalDateTime
  //
  // - Ecrivez la JavaDoc complète de la classe
}
