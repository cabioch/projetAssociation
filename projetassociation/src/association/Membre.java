package association;

import java.util.List;
/**
 * Gestion du membre ainsi que les évènements du membres et ses infos persos.
 * 
 * @author Jeant
 *
 */
public class Membre implements InterMembre{
	
	/**
	   * Le nom de la personne (ne peut pas être modifié).
	   */
	  private final String nom;
	  
	  /**
	   * Le prénom de la personne (ne peut pas être modifié).
	   */
	  private final String prenom;
	  
	  /**
	   * L'âge de la personne (la valeur 0 correspond à un âge non défini).
	   */
	  private int age;
	  
	  /**
	   * L'adresse de la personne (une chaine vide "" correspond à une adresse non
	   * définie).
	   */
	  private String adresse;
	  
	  private InterGestionMembres membres;
	
	  public Membre(String nom, String prenom, int age, String adresse) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.adresse = adresse;
	}

	  private InterGestionEvenements evenements;
	  
	private InformationPersonnelle infos;
	
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
		if(membres.ensembleMembres().contains(info)) {
			if(this.adresse != null)
				info.setAdresse(this.adresse);
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
		if(infos.getNom()== null) {
			return null;
		}
		else {
			return infos;
		}
		
	}

}
