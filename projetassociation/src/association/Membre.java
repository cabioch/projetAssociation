package association;

import java.util.List;

/**
 * Gestion du membre ainsi que les évènements du membres et ses infos persos.
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
    if (this.infos.getNom() == info.getNom()
        && this.infos.getPrenom() == infos.getPrenom()) {
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
  
}
