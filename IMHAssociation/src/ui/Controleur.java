package ui;

import association.InformationPersonnelle;
import association.Membre;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * Classe publique de contrôle de l'interface Utilisateur.
 *
 */

public class Controleur implements Initializable {
  
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
  private ListView<String> listeEvenements;
  
  @FXML
  private ListView<String> listeMembres;
  
  @FXML
  private TextArea message;
  
  @FXML
  private Font x1;
  
  @FXML
  private Color x2;
  
  @FXML
  void actionBoutonAfficherMembreSelectionneMembre(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonAfficherParticipantsEvt(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonAfficherTousMembresMembre() {

  }
  
  @FXML
  void actionBoutonEvenementSelectionneEvt(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonEvenementsFutursAssociation(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonEvenementsFutursMembre(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonEvenementsMembreMembre(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonDesinscrireMembreEvenement(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonInscrireMembreEvenement(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonNouveauEvt(ActionEvent event) {
    
  }
  /**
   * Bouton de création d'un nouveau membre.
   * Efface les données inscrite précédemment dans les zones de textes.
   *
   * @param event l'objet récupéré par un clique sur le bouton "Nouveau".
   */
  
  @FXML
  void actionBoutonNouveauMembre(ActionEvent event) {
    entreePrenomMembre.setText("");
    entreeNomMembre.setText("");
    entreAdresseMembre.setText("");
    entreAgeMembre.setText("");
  }
  
  @FXML
  void actionBoutonSupprimerEvt(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonSupprimerMembre(ActionEvent event) {

  }
  
  @FXML
  void actionBoutonTousEvenementsAssociationEvt(ActionEvent event) {
    
  }
  
  @FXML
  void actionBoutonValiderEvt(ActionEvent event) {
    
  }
  /**
   * Bouton d'action pour valider les informations d'un membre.
   * Les informations prennent en compte, un nom, un prénom, une adresse et un age.
   *
   * @param event l'objet récupéré par un clique sur le bouton "Valider Membre".
   */
  
  @FXML
  void actionBoutonValiderMembre(ActionEvent event) {
  }
  
  @FXML
  void actionMenuApropos(ActionEvent event) {
    
  }
  
  @FXML
  void actionMenuCharger(ActionEvent event) {
    
  }
  
  @FXML
  void actionMenuNouveau(ActionEvent event) {
    
  }
  
  @FXML
  void actionMenuQuitter(ActionEvent event) {
    Alert quitter = new Alert(AlertType.INFORMATION);
    quitter.setHeaderText("Quitter l'application ");
    quitter.setTitle("L'application va fermer.\n Merci.");
    
  }
  
  @FXML
  void actionMenuSauvegarder(ActionEvent event) {
    
  }
  
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    System.out.println("Initialisation de l'interface");
  }
  
}
