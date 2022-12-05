package association;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Gestion du membre ainsi que les évènements du membres et ses informations
 * personnelles.
 *
 * @author Jean-André THOMAS
 */
public class Membre implements InterMembre, Serializable {
  
  
  private static final long serialVersionUID = 2037L;
  /**
   * Les evenements auxquels participe ou a participé le membre.
   */
  private List<Evenement> listEvenements;
  
  /**
   * Les informations personnelles du membre.
   */
  private InformationPersonnelle infos;
  
  /**
   * Creer un membre avec ses informations corrélées à  ses informations
   * personnelles.
   *
   * @param info les informations personnelles du membre
   * @see informationPersonnelle
   */
  public Membre(InformationPersonnelle info) {
    this.infos = info;
    this.listEvenements = new ArrayList<>();
  }
  
  /**
   * Setters de la liste évènements.
   *
   */
  public void setListeEvenements(List<Evenement> listEvenements) {
    this.listEvenements = listEvenements;
  }
  
  /**
   * La liste des évènements auquel le membre est inscrit ou a participé.
   *
   * @return la liste des évènements auxquels participe le membre.
   */
  @Override
  public List<Evenement> ensembleEvenements() {
    return listEvenements;
  }
  
  /**
   * La liste des évènements auquel le membre est inscrit et qui n'ont pas
   * encore eu lieu.
   *
   * @return la liste des évènements à venir du membre.
   */
  @Override
  public List<Evenement> ensembleEvenementsAvenir() {
    
    /* on instancie une nouvelle liste des que la méthode est appelée pour
     prendre en compte la date*/
    List<Evenement> avenir = new ArrayList<>();
    
    // regarde pour chaque evenement dans la liste d'évènement
    for (Evenement e : listEvenements) {
      /* si la liste contient l'objet infos du membre ET si l'évènement est
      après la date locale, donc à venir.*/
      if (e.getDate().isAfter(LocalDateTime.now())) {
        avenir.add(e);
      }
    }
    return avenir;
  }
  
  /**
   * Définit les informations personnelles (adresse et age) du membre en
   * vérifiant si c'est bien lui (nom et prénom).
   *
   * @param info les informations personnelles du membre.
   */
  @Override
  public void definirInformationPersonnnelle(InformationPersonnelle info) {
    if (this.infos.getNom().equals(info.getNom())
        && this.infos.getPrenom().equals(info.getPrenom())) {
      this.infos.setAdresse(info.getAdresse());
      this.infos.setAge(info.getAge());
    }
  }
  
  /**
   * Renvoie les informations personnelles du membre.
   *
   * @return l'objet contenant les informations personnelles du membre ou
   *         <code>null</code> si aucune information n'a été définie.
   */
  @Override
  public InformationPersonnelle getInformationPersonnelle() {
    if (infos == null) {
      return null;
    } else {
      return infos;
    }
  }
  
  /**
   * Retourne un hashcode des infos du membre.
   */
  @Override
  public int hashCode() {
    return Objects.hash(infos);
  }
  
  /**
   * Redéfinition de la méthode toString.
   */
  @Override
  public String toString() {
    return infos.getNom() + " " + infos.getPrenom() + " :" + infos.getAdresse()
        + " " + infos.getAge() +" ans.";
  }
  
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Membre other = (Membre) obj;
    return Objects.equals(infos.getAdresse(), other.infos.getAdresse())
        && infos.getAge() == other.infos.getAge()
        && Objects.equals(infos.getNom(), other.infos.getNom())
        && Objects.equals(infos.getPrenom(), other.infos.getPrenom());
  }
}

