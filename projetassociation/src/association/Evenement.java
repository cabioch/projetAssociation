package association;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Représente un Evenement nommé a un lieu donné, avec un debut, une fin et des participants
 */
public class Evenement implements java.io.Serializable {

  /**
   * Le nom de l'evenement
   */
  private String nom;

  /**
   * Le lieu de l'evenement
   */
  private String lieu;

  /**
   * La date de l'evenement
   */
  private LocalDateTime date;

  /**
   * La duree en minutes de l'evenement
   */
  private int duree;

  /**
   * le nombre de participants maximums a l'evenement
   */
  private int nbParticipantsMax;

  /**
   * Les participants
   */
  private Set<InterMembre> participants;

  /**
   *
   * @param evt Un evenement
   * @return vrai si l'evenement en parametre ne se chevauche pas en temps et en lieu avec celui-ci
   */
  public boolean pasDeChevauchementLieu(Evenement evt) {
    // J'ai un doute sur le fonctionnement de la méthode -> Les 2 conditions doivent etre vérifiées ou juste une ?
    // Pris le fonctionnement a une condition vérifiée
    return (!Objects.equals(evt.lieu, this.lieu) || pasDeChevauchementTemps(evt));

  }

  /**
   *
   * @param evt Un evenement
   * @return vrai si l'evenement en parametre ne se chevauche pas en temps avec celui-ci
   */
  public boolean pasDeChevauchementTemps(Evenement evt) {
    LocalDateTime finThis = date.plusMinutes(duree);
    LocalDateTime finEvt = date.plusMinutes(evt.duree);

    // Pas supperposé en temps
    return (finThis.compareTo(evt.date) <= 0 || date.compareTo(finEvt) >= 0);
  }

  //<editor-fold desc="Getter-Setter">
  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getLieu() {
    return lieu;
  }

  public void setLieu(String lieu) {
    this.lieu = lieu;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public int getDuree() {
    return duree;
  }

  public void setDuree(int duree) {
    this.duree = duree;
  }

  public int getNbParticipantsMax() {
    return nbParticipantsMax;
  }

  public void setNbParticipantsMax(int nbParticipantsMax) {
    this.nbParticipantsMax = nbParticipantsMax;
  }

  public Set<InterMembre> getParticipants() {
    return participants;
  }

  // Juste enlevé le setter de participants
  //</editor-fold>


  //<editor-fold desc="Overrides">
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Evenement evenement = (Evenement) o;

    if (duree != evenement.duree) return false;
    if (!Objects.equals(lieu, evenement.lieu)) return false;
    return Objects.equals(date, evenement.date);
  }

  @Override
  public int hashCode() {
    int result = lieu != null ? lieu.hashCode() : 0;
    result = 31 * result + (date != null ? date.hashCode() : 0);
    result = 31 * result + duree;
    return result;
  }

  @Override
  public String toString() {
    return "Evenement \"" + nom + "\" a " + lieu + " , de " + date + " a " + date.plusMinutes(duree) + ". Il y a un maximum de " + nbParticipantsMax + " participants.";
  }
  //</editor-fold>

  /**
   *
   * @param participant Un participant
   * @return Vrai si le participant a bien pu être ajouté
   */
  public boolean ajouterParticipant(InterMembre participant) {
    return this.participants.add(participant);
  }

  /**
   *
   * @param participant Un participant
   * @return Vrai si le participant a bien été enlevé
   */
  public boolean enleverParticipant(InterMembre participant) {
    return this.participants.remove(participant);
  }

  /**
   *
   * @param nom Le nom de l'evenement
   * @param lieu Le lieu de l'evenement
   * @param date La date de l'evenement
   * @param duree La duree de l'evenement en minutes
   * @param nbParticipantsMax Le nombre maximum de participants
   */
  public Evenement(String nom, String lieu, LocalDateTime date, int duree, int nbParticipantsMax) {
    this.nom = nom;
    this.lieu = lieu;
    this.date = date;
    this.duree = duree;
    this.nbParticipantsMax = nbParticipantsMax;
    this.participants = new HashSet<>();
  }

  /**
   *
   * @param nom Le nom de l'evenement
   * @param lieu Le lieu de l'evenement
   * @param annee L'année de l'evenement
   * @param mois Le mois auquel l'evenement debute
   * @param jour Le jour auquel l'evenement debute
   * @param heure L'heure à laquelle l'evenement debute
   * @param minute La minute à laquelle l'evenement debute
   * @param duree La duree de l'evenement en minutes
   * @param nbParticipantsMax Le nombre maximum de participants
   */
  public Evenement(String nom, String lieu, int annee, int mois, int jour, int heure, int minute, int duree, int nbParticipantsMax) {
    this.nom = nom;
    this.lieu = lieu;
    this.date = LocalDateTime.of(annee, mois, jour, heure, minute);
    this.duree = duree;
    this.nbParticipantsMax = nbParticipantsMax;
    this.participants = new HashSet<>();
  }
}
