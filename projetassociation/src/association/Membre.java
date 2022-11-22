package association;

import java.util.List;

/**
 * Gestion du membre ainsi que les �v�nements du membres et ses infos persos.
 * 
 * @author Jeant
 *
 */
public class Membre implements InterMembre {
  
  private InterGestionEvenements evenements;
  
  private InformationPersonnelle infos;
  
  private InterGestionMembres membres;
  
  
  /**
   * Creer un membre avec ses informations corr�l�es � ses
   * InformationPersonnelle.
   *
   * @param info l'objet informationPersonnelle du membre
   */
  public Membre(InformationPersonnelle info) {
    this.infos = info;
  }
  
  
  
  /**
   * La liste des �v�nements auquel le membre est inscrit ou a particip�.
   *
   * @return la liste des �v�nements du membre
   */
  @Override
  public List<Evenement> ensembleEvenements() {
    return evenements.ensembleEvenements();
  }
  
  /**
   * La liste des �v�nements auquel le membre est inscrit et qui n'ont pas
   * encore eu lieu.
   *
   * @return la liste des �v�nements � venir du memmbre
   */
  @Override
  public List<Evenement> ensembleEvenementsAvenir() {
    return evenements.ensembleEvenementAvenir();
  }
  
  /**
   * D�finit les informations personnelles du membre.
   *
   * @param info les informations personnelles du membre
   */
  @Override
  public void definirInformationPersonnnelle(InformationPersonnelle info) {
    if (membres.ensembleMembres().contains(info)) {
      // pas de v�rification car elles sont d�j� dans informations personnelles
      info.setAdresse(infos.getAdresse());
      info.setAge(infos.getAge());
    }
    
  }
  
  /**
   * Renvoie les informations personnelles du membre.
   *
   * @return l'objet contenant les informations personnelles du membre ou
   *         <code>null</code> si elles n'ont pas �t� d�finies
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
