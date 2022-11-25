package association;

import java.util.List;
/**
 * Gestion du membre ainsi que les �v�nements du membres et ses infos persos.
 * 
 * @author Jeant
 *
 */
public class Membre implements InterMembre{
	
	/**
	   * Le nom de la personne (ne peut pas �tre modifi�).
	   */
	  private final String nom;
	  
	  /**
	   * Le pr�nom de la personne (ne peut pas �tre modifi�).
	   */
	  private final String prenom;
	  
	  /**
	   * L'�ge de la personne (la valeur 0 correspond � un �ge non d�fini).
	   */
	  private int age;
	  
	  /**
	   * L'adresse de la personne (une chaine vide "" correspond � une adresse non
	   * d�finie).
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
	   *         <code>null</code> si elles n'ont pas �t� d�finies
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
