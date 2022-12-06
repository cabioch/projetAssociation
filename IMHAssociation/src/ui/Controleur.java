package ui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import association.Evenement;
import association.GestionAssociation;
import association.InformationPersonnelle;
import association.InterGestionAssociation;
import association.InterMembre;
import association.Membre;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Classe publique de contrôle de l'interface Utilisateur. TODO Il n'y a pas
 * encore la possibilité de mettre a jour des trucs je crois (par ex changer
 * l'age d'un membre...)
 */

public class Controleur implements Initializable {
  
  @FXML
  private InterGestionAssociation association;
  
  /**
   * L'input correspondant à l'adresse d'un Membre.
   */
  @FXML
  private TextField entreAdresseMembre;
  
  /**
   * L'input correspondant à l'age d'un Membre.
   */
  @FXML
  private TextField entreAgeMembre;
  
  /**
   * L'input correspondant à la date d'un Evènement.
   */
  @FXML
  private TextField entreeDateEvt;
  
  /**
   * L'input correspondant à la durée d'un Evènement.
   */
  @FXML
  private TextField entreeDureeEvt;
  
  /**
   * L'input correspondant à l'heure de début d'un Evènement.
   */
  @FXML
  private TextField entreeHeureEvt;
  
  /**
   * L'input correspondant au lieu où se déroule d'un Evènement.
   */
  @FXML
  private TextField entreeLieuEvt;
  
  /**
   * L'input correspondant au nombre maximum de participants à un Evènement.
   */
  @FXML
  private TextField entreeMaxParticipantsEvt;
  
  /**
   * L'input correspondant au nom d'un Evènement.
   */
  @FXML
  private TextField entreeNomEvt;
  
  /**
   * L'input correspondant au nom d'un Membre.
   */
  @FXML
  private TextField entreeNomMembre;
  
  /**
   * L'input correspondant au prénom d'un Membre.
   */
  @FXML
  private TextField entreePrenomMembre;
  
  /**
   * Le label d'affichage d'une liste d'Evenèment.
   */
  @FXML
  private Label labelListeAfficheeEvt;
  
  /**
   * Le label d'affichage de la liste des Membres.
   */
  @FXML
  private Label labelListeAfficheeMembre;
  
  /**
   * La liste des Evenèments.
   */
  @FXML
  private ListView<Evenement> listeEvenements;
  
  /**
   * La liste des Membres.
   */
  @FXML
  private ListView<Membre> listeMembres;
  
  /**
   * La boite de dialogue qui réceptionne les "messages" d'actions.
   */
  @FXML
  private TextArea message;
  
  @FXML
  private Font x1;
  
  @FXML
  private Color x2;
  
  
  /**
   * Afficher le membre sélectionné : si un membre est sélectionné dans la
   * liste, affiche ses informations personnelles dans les quatres champs en haut de
   * la fenêtre.
   * 
   * @param event
   */
  @FXML
  void actionBoutonAfficherMembreSelectionneMembre(ActionEvent event) {
    InterMembre m = listeMembres.getSelectionModel().getSelectedItem();
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
    Evenement e = getEvenementFromFields();
    for (InterMembre m : e.getParticipants()) {
      listeMembres.getItems().add((Membre) m);
    }
    labelListeAfficheeEvt.setText(" Tous les participants de l'événement " + e.getNom());
    message.setText("Affichage des participants de l'événement " + e.getNom() + " a été effectué.");
  }
  
  /**
   * Afficher tous les membres : affiche dans la liste tous les membres de
   * l’association.
   */
  @FXML
  void actionBoutonAfficherTousMembresMembre() {
    listeMembres.getItems().clear();
    for (InterMembre m : association.gestionnaireMembre().ensembleMembres()) {
      listeMembres.getItems().add((Membre) m);
    }
  }
  
  /**
   * Affiche l’événement sélectionné : si un événement est sélectionné dans la
   * liste, affiche ses informations dans les champs en haut de la fenêtre.
   *
   * @param event Est l'événement sélectionné quand on clic sur le bouton
   */
  @FXML
  void actionBoutonEvenementSelectionneEvt(ActionEvent event) {
    Evenement e = listeEvenements.getSelectionModel().getSelectedItem();
    if (e == null) {
      message.setText("Aucun événement sélectionné.");
      return;
    }
    
    entreeNomEvt.setText(e.getNom());
    entreeLieuEvt.setText(e.getLieu());
    // Récupère la date au format YYYY-MM-DD (comme l'entrée nécessaire)
    entreeDateEvt.setText(e.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
    // Récupère l'heure au format HH:MM (comme l'entrée nécessaire)
    entreeHeureEvt.setText(e.getDate().format(DateTimeFormatter.ISO_LOCAL_TIME));
    entreeDureeEvt.setText(Integer.toString(e.getDuree()));
    entreeMaxParticipantsEvt
        .setText(Integer.toString(e.getNbParticipantsMax()));
  }
  
  /**
   * Affiche tous les événements futurs de l’association. Les évènements sont
   * listés dans la zone de texte.
   *
   * @param event Est l'objet récupéré en cliquant sur le bouton
   */
  @FXML
  void actionBoutonEvenementsFutursAssociation(ActionEvent event) {
    listeEvenements.getItems().clear();
    for (Evenement e : association.gestionnaireEvenements()
        .ensembleEvenementAvenir()) {
      listeEvenements.getItems().add(e);
    }
    
    labelListeAfficheeEvt.setText("Tous les événements futurs de l'association. ");
    message.setText(
        "L'affichage de tous les événements futurs de l'association a bien été effectué.");
  }
  
  /**
   * Evénements futurs : idem mais en affichant uniquement les événements à
   * venir pour le membre.
   *
   * @param event
   */
  @FXML
  void actionBoutonEvenementsFutursMembre(ActionEvent event) {
    listeEvenements.getItems().clear();
    Membre m = getMembreFromFields();
    for (Evenement e : m.ensembleEvenementsAvenir()) {
      listeEvenements.getItems().add(e);
    }
    if(m.ensembleEvenementsAvenir().isEmpty()) {
      message.setText("Aucun évènements futurs à afficher.");
    } else {
      message.setText("Liste générée.");
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
    Membre m = getMembreFromFields();
    for (Evenement e : m.ensembleEvenements()) {
      listeEvenements.getItems().add(e);
    }
    if(m.ensembleEvenements().isEmpty()) {
      message.setText("Aucune participation à des évènements.");
    } else {
      message.setText("Liste des évènements générée.");
    }
  }
  
  /**
   * Désinscrire membre à événement : si un membre est sélectionné dans la liste
   * de gauche et un événement est sélectionné dans la liste de droite, le
   * membre est désinscrit à cet événement.
   */
  @FXML
  void actionBoutonDesinscrireMembreEvenement(ActionEvent event) {
    // TODO GESTION ERREUR GESTION ERREUR
    InterMembre m = listeMembres.getSelectionModel().getSelectedItem();
    Evenement e = listeEvenements.getSelectionModel().getSelectedItem();
    
    
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
    InterMembre m = listeMembres.getSelectionModel().getSelectedItem();
    Evenement e = listeEvenements.getSelectionModel().getSelectedItem();
    
    // TODO Encore de la gestion d'erreur
    association.gestionnaireEvenements().inscriptionEvenement(e, m);
  }
  
  /**
   * Bouton de réinitialisation d'un nouvel évènement.
   * Efface le contenu des champs d’un événement afin de rajouter un
   * nouvel événement.
   *
   * @param event l'objet récupéré par un clique sur le bouton "Nouveau".
   */
  @FXML
  void actionBoutonNouveauEvt(ActionEvent event) {
    entreeNomEvt.clear();
    entreeLieuEvt.clear();
    entreeDateEvt.clear();
    entreeHeureEvt.clear();
    entreeDureeEvt.clear();
    entreeMaxParticipantsEvt.clear();
    message.setText(
        "Le contenu des champs d'un événement a bien été réinitialisé !");
  }
  
  /**
   * Bouton de création d'un nouveau membre. Efface les données inscrites
   * précédemment dans les zones de textes.Un message indique qu'un nouveau membre
   * peut-être créé.
   *
   * @param event l'objet récupéré par un clique sur le bouton "Nouveau".
   */
  
  @FXML
  void actionBoutonNouveauMembre(ActionEvent event) {
    entreePrenomMembre.clear();
    entreeNomMembre.clear();
    entreAdresseMembre.clear();
    entreAgeMembre.clear();
    message.setText(
        "Le contenu des champs a été réinitialisé.\n"
        + "Veuillez entrer de nouvelles données créer un nouveau membre.");
  }
  
  /**
   * Supprimer : efface de la liste des événements l’événement dont les
   * informations sont affichées.
   * 
   * @param event Est l'événement sélectionné pour la suppression
   */
  @FXML
  void actionBoutonSupprimerEvt(ActionEvent event) {
	//On prend les valeurs des entrées et on les associe à un événement
    Evenement e = getEvenementFromFields();
    //On supprime cet événement dans le gestionnaire
    association.gestionnaireEvenements().supprimerEvenement(e);
    //On clear les liste des événements pour l'affichage de la liste mise à jour
    listeEvenements.getItems().clear();
    
    //on affiche la liste mise à jour
    for (Evenement ei : association.gestionnaireEvenements()
            .ensembleEvenements()) {
          listeEvenements.getItems().add(ei);
        }
    
    //On oublie pas de supprimer les entrées
    entreeNomEvt.clear();
    entreeLieuEvt.clear();
    entreeDateEvt.clear();
    entreeHeureEvt.clear();
    entreeDureeEvt.clear();
    entreeMaxParticipantsEvt.clear();
    
    //gérer la suppression en cas d'erreur. 
    //Si l'evenement existe et a bien été supprimer comme les membre sinon un aute message.
    message.setText("La suppression a bien été prise en compte.");
  }
  
  /**
   * Efface de la liste des membres, le membre dont les informations sont
   * affichées. si le membre était président, un message de "place vacante" est
   * affiché pour le rôle de président en plus.
   */
  @FXML
  void actionBoutonSupprimerMembre(ActionEvent event) {
    Membre m = new Membre(new InformationPersonnelle(entreeNomMembre.getText(),
        entreePrenomMembre.getText()));
    if (association.gestionnaireMembre().supprimerMembre(m)
        && association.gestionnaireMembre().president().equals(m)) {
      listeMembres.getItems().remove(m);
      message.setText(
          "La suppression du membre a été faite.\n La place de président est vacante.");
    } else {
      message.setText("La suppression du membre a été faite.");
    }
  }
  
  /**
   * Afficher tous les événements de l’association : afficher dans la liste tous
   * les événements de l’association.
   *
   * @param event l'objet récupéré par un clique sur le bouton "Afficher tous
   *        les évènements de l'association".
   */
  @FXML
  void actionBoutonTousEvenementsAssociationEvt(ActionEvent event) {
    listeEvenements.getItems().clear();
    
    for (Evenement e : association.gestionnaireEvenements()
        .ensembleEvenements()) {
      listeEvenements.getItems().add(e);
    }
    
    labelListeAfficheeEvt.setText(" Tous les événements de l'association.");
    message.setText("L'affichage de tous les événements de l'association a bien été effectué.");
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
    Evenement cree = association.gestionnaireEvenements().creerEvenement(
        e.getNom(), e.getLieu(), date.getDayOfMonth(), date.getMonth(),
        date.getYear(), date.getHour(), date.getMinute(), e.getDuree(),
        e.getNbParticipantsMax());
    //TODO Possibilité de mettre les informations de l'événement à jour si la 
    //personne sélectionne l'événement dans la liste des événements
  }

  /**
   * Méthode de création d'un membre via les inputs de l'interface.
   */
  private Membre getMembreFromFields() {
    // TODO Gerer erreurs    
    int age = Integer.parseInt(entreAgeMembre.getText());
    String adresse = this.entreAdresseMembre.getText();
    InformationPersonnelle info = new InformationPersonnelle(this.entreeNomMembre.getText(),
        this.entreePrenomMembre.getText(), adresse, age);
    if (info.getAge() < 0) {
      age = 0;
    }
    if(info.getAdresse() == null) {
      adresse = "";
    }
    Membre m = new Membre(info);
    return m;
  }
  
  /**
   * Bouton d'action pour valider les informations d'un membre. Les informations
   * prennent en compte un nom, un prénom, une adresse et un age.
   * Si le membre existe déjà, ses informations sont mises à jour.
   *
   * @param event l'objet récupéré par un clique sur le bouton "Valider Membre".
   */
  
  @FXML
  void actionBoutonValiderMembre(ActionEvent event) {
    Membre m = getMembreFromFields();
    if (association.gestionnaireMembre().ajouterMembre(m)) {
      message.setText("Membre "
          + this.entreePrenomMembre.getText().substring(0, 1).toUpperCase()
          + this.entreePrenomMembre.getText()
              .substring(1, entreePrenomMembre.getLength()).toLowerCase()
          + " " + this.entreeNomMembre.getText().toUpperCase()
          + " à bien été créé et ajouté à l'association.");
      // on efface les champs automatiquement.
      entreePrenomMembre.clear();
      entreeNomMembre.clear();
      entreAdresseMembre.clear();
      entreAgeMembre.clear();
    } else {
      for (InterMembre me : association.gestionnaireMembre()
          .ensembleMembres()) {
        if (m.getInformationPersonnelle().getNom()
            .equals(me.getInformationPersonnelle().getNom())
            && m.getInformationPersonnelle().getPrenom()
                .equals(me.getInformationPersonnelle().getPrenom())) {
          m.definirInformationPersonnnelle(m.getInformationPersonnelle());
          message.setText("Le membre "
              + this.entreePrenomMembre.getText().substring(0, 1).toUpperCase()
              + this.entreePrenomMembre.getText()
                  .substring(1, entreePrenomMembre.getLength()).toLowerCase()
              + " " + this.entreeNomMembre.getText().toUpperCase()
              + "n'est pas créé.\n Ses informations personnelles ont été mise à jour.");
        }
      }
      message.setText(
          "Erreur sur la création du membre.\n Veuillez vérifier les valeurs des champs.");
    }
  }
  
  /**
   * A propos : affiche quelques informations sur votre application.
   *
   * @param event l'objet récupéré par un clique sur le menu "à propos".
   */
  @FXML
  void actionMenuApropos(ActionEvent event) {
    Alert alerte = new Alert(AlertType.INFORMATION);
    alerte.setTitle("A Propos");
    String content =
        "Application  de Gestion d'une Association.\n\n"
            + "Réalisation par Jean-André, Enzo, Nicolas & Romain\n\n"
            + "Tutoriel : \n"
            + "Vous avez deux encarts.\n La fênetre de gauche permet la gestion des membres "
            + "et leur importation dans l'association.\n "
            + "La fenêtre de droite permet la gestion des évènements de l'association.\n";
    alerte.setContentText(content);
    alerte.showAndWait();
  }
  
  /**
   * Menu Charger : charge les membres et les événements de l’association à partir
   * d’un fichier (dont on pourra optionnellement choisir l’emplacement). Une
   * fois chargé, les deux listes affichent tous les membres et tous les
   * événements.
   */
  @FXML
  void actionMenuCharger(ActionEvent event) {
    // TODO Choisir le fichier
    try {
      association.chargerDonnees("sauvegarde");
      message.setText("Fichier des données de l'Association chargé avec succès");
    } catch (IOException e) {
      message.setText("Impossible de charger le fichier.");
    }
  }
  
  /**
   * Menu Nouveau : Réinitialise l’association (efface tous les événements et membres chargés
   * en mémoire).
   */
  @FXML
  void actionMenuNouveau(ActionEvent event) {
    // On recrée une nouvelle association
    association = new GestionAssociation();
    
    // On reset les champs
    actionBoutonNouveauEvt(event);
    actionBoutonNouveauMembre(event);
    
    // On vide les listes
    listeEvenements.getItems().clear();
    listeMembres.getItems().clear();
    
    message.setText("Nouvelle association créée.");
  }
  
  /**
   * Menu Quitter : Execute la fermeture de l'application.
   */
  @FXML
  void actionMenuQuitter(ActionEvent event) {
    Alert quitter = new Alert(AlertType.CONFIRMATION);
    quitter.setTitle("Quitter l'application ?");
       
    // Retourne le type de bouton séléctionné ou null si un bouton n'a
    // pas été séléctionné ( = il a séléctionné la croix)
    ButtonType result = quitter.showAndWait().orElse(null);
    
    // On quitte seulement si l'utilisateur valide
    if (result == ButtonType.OK) {
      Platform.exit();
    }
  }
  
  /**
   * Sauvegarder : sauvegarde les membres et les événements de l’association
   * dans un fichier (dont on pourra optionnellement choisir l’emplacement).
   */
  @FXML
  void actionMenuSauvegarder(ActionEvent event) {
    try {
      association.sauvegarderDonnees("sauvegarde");
      message.setText("Données de l'Association sauvegardées");
    } catch (IOException e) {
      message.setText(
          "Erreur lors de la sauvegarde du fichier dans \"sauvegarde\"");
    }
  }
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    System.out.println("Initialisation de l'interface");
    association = new GestionAssociation();
    
  }


  /**
   * 
   * @return
   */
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

