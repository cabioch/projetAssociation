package association;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


/**
 * Gère la sauvegarde des données de l'association.
 */
public class GestionAssociation implements InterGestionAssociation {

  /**
   * Le gestionnaire d'evenements.
   */
  private static InterGestionEvenements gestionEvenements = null;
  private static InterGestionMembres gestionMembres = null;

  /**
   * Renvoie le gestionnaire d'�v�nements de l'association. L'objet retourn� est
   * unique. Au premier appel de la m�thode, il est cr�� et aux appels suivants,
   * c'est la r�f�rence sur cet objet qui est retourn�e.
   *
   * @return le gestionnaire d'�v�nements de l'association
   */
  @Override
  public InterGestionEvenements gestionnaireEvenements() {
    if (gestionEvenements == null) {
      // L'initialiser a partir de la classe
      gestionEvenements = null;
    }
    return gestionEvenements;
  }

  /**
   * Renvoie le gestionnaire de membres de l'association. L'objet retourn� est
   * unique. Au premier appel de la m�thode, il est cr�� et aux appels suivants,
   * c'est la r�f�rence sur cet objet qui est retourn�e.
   *
   * @return le gestionnaire de membres de l'association
   */
  @Override
  public InterGestionMembres gestionnaireMembre() {
    if (gestionMembres == null) {
      // L'initialiser a partir de la classe
      gestionMembres = null;
    }
    return gestionMembres;
  }

  /**
   * Enregistre dans un fichier toutes les donn�es de l'association,
   * c'est-�-dire l'ensemble des membres et des �v�n�ments.
   *
   * @param nomFichier le fichier dans lequel enregistrer les donn�es
   * @throws IOException en cas de probl�me d'�criture dans le fichier
   */
  @Override
  public void sauvegarderDonnees(String nomFichier) throws IOException {
    OutputStream output = new BufferedOutputStream(new FileOutputStream(nomFichier));
    ObjectOutputStream outObjStream = new ObjectOutputStream(output);
    outObjStream.writeObject(gestionMembres);
    outObjStream.writeObject(gestionEvenements);
    outObjStream.close();
    output.close();
  }

  /**
   * Charge � partir d'un fichier toutes les donn�es de l'association,
   * c'est-�-dire un ensemble de membres et d'�v�nements. Si des membres et des
   * �v�n�ments avaient d�j� �t� d�finis, ils sont �cras�s par le contenu trouv�
   * dans le fichier.
   *
   * @param nomFichier le fichier � partir duquel charger les donn�es
   * @throws IOException en cas de probl�me de lecture dans le fichier
   */
  @Override
  public void chargerDonnees(String nomFichier) throws IOException {
    InputStream input = new BufferedInputStream(new FileInputStream(nomFichier));
    ObjectInputStream inObjStream = new ObjectInputStream(input);
    try {
      gestionMembres = (InterGestionMembres) inObjStream.readObject();
    } catch (ClassNotFoundException e) {
      gestionMembres = null;
    }
    try {
      gestionEvenements = (InterGestionEvenements) inObjStream.readObject();
    } catch (ClassNotFoundException e) {
      gestionEvenements = null;
    }

    input.close();
    inObjStream.close();
  }
}
