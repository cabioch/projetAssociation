package ui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import association.Evenement;
import association.GestionAssociation;
import association.InformationPersonnelle;
import association.InterGestionAssociation;
import association.InterMembre;
import association.Membre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Classe publique de contrôle de l'interface Utilisateur.
 *TODO Il n'y a pas encore la possibilité de mettre a jour des trucs je crois
 * (par ex changer l'age d'un membre...)
 */

public class Controleur implements Initializable {
  
  @FXML
  private InterGestionAssociation association;
  
  @FXML
  private TextField entreAdresseMembre;
  
  @FXML
  private TextField entreAgeMembre;
  
  @FXML
  private TextField entreeDateEvt;
  
  @FXML
  private TextField entreeDureeEvt;
  
  @FXML
  private TextField entreeHeureEvt;
  
  @FXML
  private TextField entreeLieuEvt;
  
  @FXML
  private TextField entreeMaxParticipantsEvt;
  
  @FXML
  private TextField entreeNomEvt;
  
  @FXML
  private TextField entreeNomMembre;
  
  @FXML
  private TextField entreePrenomMembre;
  
  @FXML
  private Label labelListeAfficheeEvt;
  
  @FXML
  private Label labelListeAfficheeMembre;
  
  @FXML
  // Probablement mieux d'éviter d'utiliser une liste annexe mais dans ce cas il
  // faut tout mettre a jour si on enlève un membre par exemple ?
  private ListView<String> listeEvenements;
  private ArrayList<Evenement> annexeEvenements = new ArrayList<>();
  
  @FXML
  private ListView<String> listeMembres;
  private ArrayList<InterMembre> annexeMembres = new ArrayList<>();
  
  @FXML
  private TextArea message;
  
  @FXML
  private Font x1;
  
  @FXML
  private Color x2;
  
  
  /**
   * Afficher le membre sélectionné : si un membre est sélectionné dans la
   * liste, affiche ses informations personnelles dans les 4 champs en haut de
   * la fenêtre.
   * 
   * @param event 
   */
  @FXML
  void actionBoutonAfficherMembreSelectionneMembre(ActionEvent event) {
    int indexSelected = listeMembres.getSelectionModel().getSelectedIndex();
    InterMembre m = annexeMembres.get(indexSelected);
    InformationPersonnelle info = m.getInformationPersonnelle();
    // TODO Vérifier qu'il est toujours dans l'association
    entreeNomMembre.setText(info.getNom());
    entreePrenomMembre.setText(info.getPrenom());
    entreAgeMembre.setText(Integer.toString(info.getAge()));
    entreAdresseMembre.setText(info.getAdresse());
  }
  
  /**
   * Afficher les participants : affiche dans la liste de gauche, les
   * participants inscrits à l’événement dont les informations sont affichées.
   * 
   */
  @FXML
  void actionBoutonAfficherParticipantsEvt(ActionEvent event) {
    listeEvenements.getItems().clear();
    annexeEvenements.clear();
    Evenement e = getEvenementFromFields();
    for (InterMembre m : e.getParticipants()) {
      listeEvenements.getItems().add(m.toString());
      annexeEvenements.add(e);
    }
  }
  
  /**
   * Afficher tous les membres : affiche dans la liste tous les membres de
   * l’association.
   */
  @FXML
  void actionBoutonAfficherTousMembresMembre() {
    listeMembres.getItems().clear();
    annexeMembres.clear();
    
    for (InterMembre m : association.gestionnaireMembre().ensembleMembres()) {
      listeMembres.getItems().add(m.toString());
      annexeMembres.add(m);
    }
  }
  
  /**
   * Affiche l’événement sélectionné : si un événement est sélectionné dans la
   * liste, affiche ses informations dans les champs en haut de la fenêtre.
   * 
   * @param event
   */
  @FXML
  void actionBoutonEvenementSelectionneEvt(ActionEvent event) {
    // TODO Gestion d'erreur
    int idxEvenement = listeEvenements.getSelectionModel().getSelectedIndex();
    
    Evenement e = annexeEvenements.get(idxEvenement);
    entreeNomEvt.setText(e.getNom());
    entreeLieuEvt.setText(e.getLieu());
    // TODO Afficher correctement
    entreeDateEvt.setText(e.getDate().toString());
    // Un truc comme ça
    // entreeHeureEvt.setText(e.getDate().format(new ...));
    entreeHeureEvt.clear(); // A enlever
    entreeDureeEvt.setText(Integer.toString(e.getDuree()));
    entreeMaxParticipantsEvt
        .setText(Integer.toString(e.getNbParticipantsMax()));
  }
  
  /**
   * Afficher tous les événements futurs de l’association : idem mais avec les
   * événements à venir de l’association.
   * 
   * @param event
   */
  @FXML
  void actionBoutonEvenementsFutursAssociation(ActionEvent event) {
    listeEvenements.getItems().clear();
    annexeEvenements.clear();
    
    for (Evenement e : association.gestionnaireEvenements()
        .ensembleEvenementAvenir()) {
      listeEvenements.getItems().add(e.toString());
      annexeEvenements.add(e);
    }
  }
  
  /**
   * Evénements futurs : idem mais en affichant uniquement les événements à
   * venir pour le membre
   * 
   * @param event
   */
  @FXML
  void actionBoutonEvenementsFutursMembre(ActionEvent event) {
    listeEvenements.getItems().clear();
    annexeEvenements.clear();
    
    Membre m = getMembreFromFields();
    for (Evenement e : m.ensembleEvenementsAvenir()) {
      listeEvenements.getItems().add(e.toString());
      annexeEvenements.add(e);
    }
  }
  
  /**
   * Evénements membre : affiche dans la liste de droite tous les événements du
   * membre dont les informations sont dans les champs au-dessus.
   * 
   * @param event
   */
  @FXML
  void actionBoutonEvenementsMembreMembre(ActionEvent event) {
    listeEvenements.getItems().clear();
    annexeEvenements.clear();
    
    Membre m = getMembreFromFields();
    for (Evenement e : m.ensembleEvenements()) {
      listeEvenements.getItems().add(e.toString());
      annexeEvenements.add(e);
    }
  }
  
  /**
   * Désinscrire membre à événement : si un membre est sélectionné dans la liste
   * de gauche et un événement est sélectionné dans la liste de droite, le
   * membre est désinscrit à cet événement
   */
  @FXML
  void actionBoutonDesinscrireMembreEvenement(ActionEvent event) {
    // TODO GESTION ERREUR GESTION ERREUR
    int idxMembre = listeMembres.getSelectionModel().getSelectedIndex();
    int idxEvenement = listeEvenements.getSelectionModel().getSelectedIndex();
    
    InterMembre m = annexeMembres.get(idxMembre);
    Evenement e = annexeEvenements.get(idxEvenement);
    
    // TODO Encore de la gestion d'erreur
    m.ensembleEvenements().remove(e);
    e.enleverParticipant(m);
  }
  
  /**
   * Inscrire membre à événement : si un membre est sélectionné dans la liste de
   * gauche et un événement est sélectionné dans la liste de droite, le membre
   * est inscrit à cet événement (dans la limite des places disponibles).
   * 
   * @param event
   */
  @FXML
  void actionBoutonInscrireMembreEvenement(ActionEvent event) {
    // TODO GESTION ERREUR GESTION ERREUR
    int idxMembre = listeMembres.getSelectionModel().getSelectedIndex();
    int idxEvenement = listeEvenements.getSelectionModel().getSelectedIndex();
    
    InterMembre m = annexeMembres.get(idxMembre);
    Evenement e = annexeEvenements.get(idxEvenement);
    
    // TODO Encore de la gestion d'erreur
    boolean r= association.gestionnaireEvenements().inscriptionEvenement(e, m);
  }
  
  /**
   * Nouveau : efface le contenu des champs d’un événement afin de rajouter un
   * nouvel événement
   * 
   */
  @FXML
  void actionBoutonNouveauEvt(ActionEvent event) {
    entreeNomEvt.clear();
    entreeLieuEvt.clear();
    entreeDateEvt.clear();
    entreeHeureEvt.clear();
    entreeDureeEvt.clear();
    entreeMaxParticipantsEvt.clear();
  }
  
  /**
   * Bouton de création d'un nouveau membre. Efface les données inscrites
   * précédemment dans les zones de textes.
   *
   * @param event l'objet récupéré par un clique sur le bouton "Nouveau".
   */
  
  @FXML
  void actionBoutonNouveauMembre(ActionEvent event) {
    entreePrenomMembre.clear();
    entreeNomMembre.clear();
    entreAdresseMembre.clear();
    entreAgeMembre.clear();
  }
  
  /**
   * Bouton d'affichage du président de l'association.
   * @param event 
   */
  @FXML
  void actionBoutonMontrePresident(ActionEvent event) {
    //TODO : afficher le président dans une boite d'alerte.
  }
  
  /**
   * Supprimer : efface de la liste des événements l’événement dont les
   * informations sont affichées.
   */
  @FXML
  void actionBoutonSupprimerEvt(ActionEvent event) {
    Evenement e = getEvenementFromFields();
    association.gestionnaireEvenements().supprimerEvenement(e);
  }
  
  /**
   * Supprimer : efface de la liste des membres, le membre dont les informations
   * sont affichées.
   */
  @FXML
  void actionBoutonSupprimerMembre(ActionEvent event) {
    Membre m = new Membre(new InformationPersonnelle(entreeNomMembre.getText(),
        entreePrenomMembre.getText()));
    association.gestionnaireMembre().supprimerMembre(m);
    // TODO Affichage réussi ou pas
  }
  
  /**
   * Afficher tous les événements de l’association : afficher dans la liste tous
   * les événements de l’association.
   * 
   * @param event
   */
  @FXML
  void actionBoutonTousEvenementsAssociationEvt(ActionEvent event) {
    listeEvenements.getItems().clear();
    annexeEvenements.clear();
    
    for (Evenement e : association.gestionnaireEvenements()
        .ensembleEvenements()) {
      listeEvenements.getItems().add(e.toString());
      annexeEvenements.add(e);
    }
  }
  
  /**
   * Valider : lit les champs d’un événement. Si l’événement existait déjà, ses
   * informations sont mises à jour, sinon, un nouvel événement est créé.
   */
  @FXML
  void actionBoutonValiderEvt(ActionEvent event) {
    Evenement e = getEvenementFromFields();
    // Approche horrible a changer
    // Actuellement je crée un évenement a partir des champs, puis je recrée un
    // autre en appelant gestionEvenements
    // Au lieu de direct appeler gestionEvenement
    // J'écrirais probablement une deuxième méthode dans GestionEvenements pour
    // faire ça
    LocalDateTime date = e.getDate();
    Evenement cree = association.gestionnaireEvenements().creerEvenement(e.getNom(), e.getLieu(),
        date.getDayOfMonth(), date.getMonth(), date.getYear(), date.getHour(),
        date.getMinute(), e.getDuree(), e.getNbParticipantsMax());
  }
  
  /**
   * Bouton d'action pour valider les informations d'un membre. Les informations
   * prennent en compte, un nom, un prénom, une adresse et un age.
   *
   * @param event l'objet récupéré par un clique sur le bouton "Valider Membre".
   */
  
  @FXML
  void actionBoutonValiderMembre(ActionEvent event) {
    Membre m = getMembreFromFields();
    // gérer le cas ou la checkbox président est active. Attention un membre unique président.
    
    
    // Gérer
    association.gestionnaireMembre().ajouterMembre(m);
  }
  
  /**
   * A propos : affiche quelques informations sur votre application.
   * 
   * @param event
   */
  @FXML
  void actionMenuApropos(ActionEvent event) {
    Alert alerte = new Alert(AlertType.INFORMATION);
    alerte.setTitle("A Propos");
    alerte.setContentText(
        "Application réalisée par Jean-André, Enzo, Nicolas & Romain\n");
    alerte.setContentText(
            "Tutoriel : \n");
    alerte.setContentText(
            "Vous avez deux fenêtres. La fênetre de gauche permet la gestion des membres et leur importation dans l'association.\n ");
    alerte.setContentText(
            "La fênetre de droite permet la gestion des évènements.\n");
    alerte.showAndWait();
  }
  
  /**
   * Charger : charge les membres et les événements de l’association à partir
   * d’un fichier (dont on pourra optionnellement choisir l’emplacement). Une
   * fois chargé, les deux listes affichent tous les membres et tous les
   * événements.
   */
  @FXML
  void actionMenuCharger(ActionEvent event) {
    // TODO Choisir le fichier
    Alert alerte = new Alert(AlertType.ERROR);
    alerte.setContentText("Erreur lors du chargement du fichier.");
    try {
      association.chargerDonnees("sauvegarde");
    } catch (IOException e) {
      alerte.showAndWait();
    }
  }
  
  /**
   * Réinitialise l’association (efface tous les événements et membres chargés
   * en mémoire).
   */
  @FXML
  void actionMenuNouveau(ActionEvent event) {
    association = new GestionAssociation();
    // TODO Réinitialiser les champs ?
    // @ Enzo -> oui il faut tout effacer les champs.
  }
  
  /**
   * Quitter : ferme l’application.
   */
  @FXML
  void actionMenuQuitter(ActionEvent event) {
    Alert quitter = new Alert(AlertType.INFORMATION);
    quitter.setHeaderText("Quitter l'application ");
    quitter.setTitle("L'application va fermer.\n Merci.");
    quitter.showAndWait();
    // TODO Fermer l'app
    
  }
  
  /**
   * Sauvegarder : sauvegarde les membres et les événements de l’association
   * dans un fichier (dont on pourra optionnellement choisir l’emplacement).
   */
  @FXML
  void actionMenuSauvegarder(ActionEvent event) {
    // TODO Choisir le fichier
    Alert alerte = new Alert(AlertType.ERROR);
    alerte.setContentText("Erreur lors de la sauvegarde du fichier");
    try {
      association.sauvegarderDonnees("sauvegarde");
    } catch (IOException e) {
      alerte.showAndWait();
    }
  }
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    System.out.println("Initialisation de l'interface");
    association = new GestionAssociation();
    configureCheckBox(checkBoxPresident);
  }
  /**
   * configuration de la checkbox au démarrage de l'application.
   * @param checkBoxPresident2
   */
  private void configureCheckBox(CheckBox checkBoxPresident2) {
    checkBoxPresident.setAllowIndeterminate(true);
    // a voir si on ne fait pas une alerte box plutot qui a la validation du membre demande s'il veut etre président ou non
    // ou autre possibilité, faire un bouton qui affiche les membres et on fait une selection du membre
    //ou encore possibilité, on faire un scroll avec une choiceBox pour choisir s'il est président ou pas
    //il faudra gérer le cas du remplacement en appelant la méthode designerprésident() de gestionmembre
  }

  /**
   * Essaye de créer un object membre a partir des text field 
   * TODO A commenter et probablement faire de la gestion d'erreur
   */
  private Membre getMembreFromFields() {
    // TODO Gerer erreurs
    int age = Integer.parseInt(entreAgeMembre.getText());
    Membre m =
        new Membre(new InformationPersonnelle(this.entreeNomMembre.getText(),
            this.entreePrenomMembre.getText(),
            this.entreAdresseMembre.getText(), age));
    return m;
  }
  
  private Evenement getEvenementFromFields() {
    String nom = entreeNomEvt.getText();
    String lieu = entreeLieuEvt.getText();
    String dateSansHeures = entreeDateEvt.getText();
    String heures = entreeHeureEvt.getText();
    int duree = Integer.parseInt(entreeDureeEvt.getText());
    int participants = Integer.parseInt(entreeMaxParticipantsEvt.getText());
    
    // TODO Gestion d'erreur, encore
    // La fonction essaye de générer un objet LocalDateTime a partir d'un
    // string. On combine les deux et on les donne a la fonction
    LocalDateTime date = LocalDateTime.parse(dateSansHeures + "T" + heures);
    Evenement e = new Evenement(nom, lieu, date, duree, participants);
    return e;
  }
}
