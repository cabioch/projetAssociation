package association;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Repr�sente un Evenement nomm� a un lieu donn�, avec un debut, une fin et des participants.
 */
public class Evenement implements java.io.Serializable {

  @Serial
  private static final long serialVersionUID = 7414938932769654866L;

  /**
   * Le nom de l'evenement.
   */
  private String nom;

  /**
   * Le lieu de l'evenement.
   */
  private String lieu;

  /**
   * La date de l'evenement.
   */
  private LocalDateTime date;

  /**
   * La duree en minutes de l'evenement.
   */
  private int duree;

  /**
   * le nombre de participants maximums a l'evenement.
   */
  private int nbParticipantsMax;

  /**
   * Les participants.
   */
  private final Set<InterMembre> participants;

  /**
   * V�rifie que 2 Evenements ne se passent pas en m�me temps et au m�me endroit.
   *
   * @param evt Un evenement
   * @return vrai si l'evenement en parametre ne se chevauche pas en temps et en lieu avec celui-ci
   */
  public boolean pasDeChevauchementLieu(Evenement evt) {
    // J'ai un doute sur le fonctionnement de la m�thode
    // -> Les 2 conditions doivent etre v�rifi�es ou juste une ?
    // Pris le fonctionnement a une condition v�rifi�e
    return (!Objects.equals(evt.lieu, this.lieu) || pasDeChevauchementTemps(evt));

  }

  /**
   * V�rifie si 2 Evenements ne se passent pas en m�me temps.
   *
   * @param evt Un evenement
   * @return vrai si l'evenement en parametre ne se chevauche pas en temps avec celui-ci
   */
  public boolean pasDeChevauchementTemps(Evenement evt) {
    LocalDateTime finThis = date.plusMinutes(duree);
    LocalDateTime finEvt = date.plusMinutes(evt.duree);

    // Pas supperpos� en temps
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

  // Juste enlev� le setter de participants
  //</editor-fold>


  //<editor-fold desc="Overrides">
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Evenement evenement = (Evenement) o;

    if (duree != evenement.duree) {
      return false;
    }
    if (!Objects.equals(lieu, evenement.lieu)) {
      return false;
    }
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
    return "Evenement \""
            + nom + "\" a "
            + lieu + " , de "
            + date + " a "
            + date.plusMinutes(duree)
            + ". Il y a un maximum de "
            + nbParticipantsMax + " participants.";
  }
  //</editor-fold>

  /**
   * Ajoute un participant � la liste de participants.
   *
   * @param participant Un participant
   * @return Vrai si le participant a bien pu �tre ajout�
   */
  public boolean ajouterParticipant(InterMembre participant) {
    return this.participants.add(participant);
  }

  /**
   * Enleve un participant de la liste de participants.
   *
   * @param participant Un participant
   * @return Vrai si le participant a bien �t� enlev�
   */
  public boolean enleverParticipant(InterMembre participant) {
    return this.participants.remove(participant);
  }

  /**
   * Cr�e un �venement a partir d'un nom, d'un lieu, d'un objet LocalDateTime, d'une dur�e et
   * d'un nombre de participants maximum.
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
   * Crée un évenement a partir d'un nom, d'un lieu, d'une année, mois, jour, heure, minute,
   * d'une durée en minutes et d'un nombre maximum de participants.
   *
   * @param nom               Le nom de l'evenement
   * @param lieu              Le lieu de l'evenement
   * @param annee             L'ann�e de l'evenement
   * @param mois              Le mois auquel l'evenement debute
   * @param jour              Le jour auquel l'evenement debute
   * @param heure             L'heure � laquelle l'evenement debute
   * @param minute            La minute � laquelle l'evenement debute
   * @param duree             La duree de l'evenement en minutes
   * @param nbParticipantsMax Le nombre maximum de participants
   */
  public Evenement(
          String nom, String lieu, int annee, int mois, int jour, int heure, int minute,
          int duree, int nbParticipantsMax) {
    this.nom = nom;
    this.lieu = lieu;
    this.date = LocalDateTime.of(annee, mois, jour, heure, minute);
    this.duree = duree;
    this.nbParticipantsMax = nbParticipantsMax;
    this.participants = new HashSet<>();
  }
}
