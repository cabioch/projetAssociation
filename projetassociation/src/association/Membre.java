package association;

import java.util.List;

/**
 * Gestion du membre ainsi que les évènements du membres et ses infos persos.
 * 
 * @author Jeant
 *
 */
public class Membre implements InterMembre {
  
  
  /**
   * L'âge de la personne (la valeur 0 correspond à un âge non défini).
   */
  private int age;
  
  /**
   * L'adresse de la personne (une chaine vide "" correspond à une adresse non
   * définie).
   */
  private String adresse;
  
  // private Evenement evenements;
  private InterGestionEvenements evenements;
  
  private InformationPersonnelle infos;
  
  private InterGestionMembres membres;
  
  public Membre(int age, String adresse) {
    String prenom = infos.getPrenom();
    String nom = infos.getNom();
    age = infos.getAge();
    adresse = infos.getAdresse();
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
   * Définit les informations personnelles du membre.
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
