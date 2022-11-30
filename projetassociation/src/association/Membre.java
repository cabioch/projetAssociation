package association;

import java.util.List;
import java.util.Objects;

/**
 * Gestion du membre ainsi que les évènements du membres et ses infos persos.
 * (nom et prenom et vide)
 * 
 * @author Jean
 */
public class Membre implements InterMembre {
  
  
  /**
   * Les evenements aux quels participe ou participera le membre.
   */
  private InterGestionEvenements evenements;
  
  /**
   * Les informations personnelles du membre.
   */
  private InformationPersonnelle infos;
  
  
  
  /**
   * Creer un membre avec ses informations corrélées à ses
   * InformationPersonnelle.
   *
   * @param info l'objet informationPersonnelle du membre
   */
  public Membre(InformationPersonnelle info) {
    this.infos = info;
  }
  
  
  /**
   * La liste des événements auquel le membre est inscrit ou a participé.
   *
   * @return la liste des événements du membre
   */
  @Override
  public List<Evenement> ensembleEvenements() {
    return evenements.ensembleEvenements();
  }
  
  /**
   * La liste des événements auquel le membre est inscrit et qui n'ont pas
   * encore eu lieu.
   *
   * @return la liste des événements à venir du memmbre
   */
  @Override
  public List<Evenement> ensembleEvenementsAvenir() {
    return evenements.ensembleEvenementAvenir();
  }
  
  /**
   * Définit les informations personnelles (adresse et age) du membre en
   * vérifant si c'est bien lui.
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
   *         <code>null</code> si elles n'ont pas été définies
   */
  @Override
  public InformationPersonnelle getInformationPersonnelle() {
    if (infos.getNom() == null) {
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
