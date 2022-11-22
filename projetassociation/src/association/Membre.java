package association;

import java.util.List;

/**
 * Gestion du membre ainsi que les �v�nements du membres et ses infos persos.
 * 
 * @author Jeant
 *
 */
public class Membre implements InterMembre {
  
  
  /**
   * L'�ge de la personne.
   */
  private int age;
  
  /**
   * L'adresse de la personne.
   */
  private String adresse;
  
  private InterGestionEvenements evenements;
  
  private InformationPersonnelle infos;
  
  private InterGestionMembres membres;
  
  
  /**
   * Creer un membre avec ses informations corr�l�es � ses InformationPersonnelle.
   *
   * @param adresse l'adresse de la personne
   * @param age l'age de la personne
   */
  public Membre(int age, String adresse) {
    String prenom = infos.getPrenom();
    String nom = infos.getNom();
    age = infos.getAge();
    adresse = infos.getAdresse();
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
      if (this.adresse != null)
        info.setAdresse(this.adresse);
      if (this.age < 0)
        this.age = 0;
      info.setAge(this.age);
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
