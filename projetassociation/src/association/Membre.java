package association;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.catalog.CatalogManager;

/**
 * Gestion du membre ainsi que les Ã©vÃ¨nements du membres et ses informations
 * personnelles.
 *
 * @author Jean
 */
public class Membre implements InterMembre {
  
  /**
   * Les evenements auxquels participe ou a participé le membre.
   */
  private List<Evenement> listEvenements;
  
  /**
   * Les informations personnelles du membre.
   */
  private InformationPersonnelle infos;
  
  
  /**
   * Creer un membre avec ses informations corrÃ©lÃ©es Ã  ses informations
   * personnelles.
   *
   * @param info les informations personnelles du membre
   * @see informationPersonnelle
   */
  public Membre(InformationPersonnelle info) {
    this.infos = info;
  }
  
  /**
   * Getters de la liste des événements.
   *
   * @return listeEvenements La liste des événements.
   * 
   */
  public List<Evenement> getListEvenements() {
    return listEvenements;
  }
  
  /**
   * Setters de la liste évènements.
   *
   * @param listeEvenements.
   */
  public void setListeEvenements(List<Evenement> listEvenements) {
    this.listEvenements = listEvenements;
  }
  
  /**
   * La liste des évènements auquel le membre est inscrit ou a participÃ©.
   *
   * @return la liste des évènements auxquels participe le membre
   */
  @Override
  public List<Evenement> ensembleEvenements() {
    if (listEvenements == null) {
      listEvenements = new ArrayList<>();
    }
    // regarde pour chaque evenement dans la liste d'évènement
    for (Evenement e : listEvenements) {
      // si la liste contient l'objet infos du membre
      if (e.getParticipants().contains(infos)) {
        
        listEvenements.add(e);
      }
    }
    return listEvenements;
  }
  
  /**
   * La liste des Ã©vÃ©nements auquel le membre est inscrit et qui n'ont pas
   * encore eu lieu.
   *
   * @return la liste des Ã©vÃ¨nements Ã© venir du membre
   */
  @Override
  public List<Evenement> ensembleEvenementsAvenir() {
    if (listEvenements == null) {
      listEvenements = new ArrayList<>();
    }
    // regarde pour chaque evenement dans la liste d'évènement
    for (Evenement e : listEvenements) {
      // si la liste contient l'objet infos du membre ET si l'évènement est
      // après la date locale, donc à venir.
      if (e.getParticipants().contains(infos)
          && e.getDate().isAfter(LocalDateTime.now())) {
        listEvenements.add(e);
      }
    }
    return listEvenements;
  }
  
  /**
   * Dï¿½finit les informations personnelles (adresse et age) du membre en
   * vï¿½rifant si c'est bien lui (nom et prenom).
   *
   * @param info les informations personnelles du membre
   */
  @Override
  public void definirInformationPersonnnelle(InformationPersonnelle info) {
    if (this.infos.equals(info)) {
      info.setAdresse(this.infos.getAdresse());
      info.setAge(this.infos.getAge());
    }
  }
  
  /**
   * Renvoie les informations personnelles du membre.
   *
   * @return l'objet contenant les informations personnelles du membre ou
   *         <code>null</code> si aucune information n'a Ã©tÃ© dÃ©finie.
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
   * Retourne en string les infos du membre.
   */
  @Override
  public String toString() {
    return infos.getNom() + " " + infos.getPrenom() + " " + infos.getAdresse()
        + " " + infos.getAge();
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

