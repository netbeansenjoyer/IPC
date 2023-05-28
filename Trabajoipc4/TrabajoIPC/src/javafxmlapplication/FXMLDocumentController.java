/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Club;
import model.ClubDAOException;
import model.Court;
import model.Member;


/**
 *
 * @author jsoler
 */
public class FXMLDocumentController implements Initializable {
    private Label labelMessage;
    @FXML
    private AnchorPane Panel;
    @FXML
    private Button LogIn;
    @FXML
    private Button SignUp;
    @FXML
    private Button ReservarPista;
    @FXML
    private Button MisReservas;
    @FXML
    private Button PistasDisponibles;
    @FXML
    private Button Exit;
    @FXML
    private Button CalendarioReserva1;
    @FXML
    private Button hora1;
    @FXML
    private Button hora5;
    @FXML
    private Button hora6;
    @FXML
    private Button hora4;
    @FXML
    private Button hora3;
    @FXML
    private Button hora2;
    private List<Court> pistas;
    private Club club;
    public Court pist;
    @FXML
    private SplitMenuButton OP;
    @FXML
    private MenuItem opcion;
    @FXML
    private MenuItem cierreSesion;
    @FXML
    private ImageView avatar;
    @FXML
    private Pane reservas;
    public Member myself;
   
    
    
    
    
    //=========================================================
    // event handler, fired when button is clicked or 
    //                      when the button has the focus and enter is pressed
    private void handleButtonAction(ActionEvent event) {
        labelMessage.setText("Hello, this is your first JavaFX project - IPC");
    }
    
    //=========================================================
    // you must initialize here all related with the object 

    /**
     *
     * 
     */
    BooleanProperty login;
    //Member myself;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        login = new SimpleBooleanProperty();
        myself = null;
        try {
            club = club.getInstance();
            pistas =  club.getCourts();
        } catch (ClubDAOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SignUp.disableProperty().bind(login);
        LogIn.disableProperty().bind(login);
        SignUp.visibleProperty().bind(login.not());
        LogIn.visibleProperty().bind(login.not());
        MisReservas.disableProperty().bind(login.not());
        ReservarPista.disableProperty().bind(login.not());
        OP.disableProperty().bind(login.not());
        OP.visibleProperty().bind(login);
     
    }    

   
    @FXML
    private void hora1_action(ActionEvent event) throws IOException, ClubDAOException {
           pist = pistas.get(0);
         FXMLLoader micargador = new FXMLLoader(getClass().getResource("reservas.fxml"));
        Parent root = micargador.load();
        Stage stage = new Stage();
        reservasController asdadas = micargador.getController();
        
        Scene scene= new Scene(root, 700, 1000);
        stage.setScene(scene);
        stage.setTitle("Reserva Pista 1");
        stage.initModality(Modality.APPLICATION_MODAL);
        asdadas.a(pist, myself);
        asdadas.b( myself);
        stage.showAndWait();
        
    }

    @FXML
    private void Register(ActionEvent event) throws IOException {
         FXMLLoader micargador = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        Parent root = micargador.load();
         Stage stage = new Stage();
        
        SignUpController controlPersona = micargador.getController();
        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Añadir persona");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        
        if(controlPersona.isOk()){
            myself = controlPersona.getMember();
            this.login.setValue(true);
            avatar.setImage(myself.getImage());
        }
        
        
    }
    @FXML
    private void LogIn(ActionEvent event) throws Exception {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("LogIn.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
       
        LogInController control = loader.getController();
        Scene scene= new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Log In");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        if(control.isOk()){
            myself = control.getMember();
            login.set(true);
            avatar.setImage(myself.getImage());
        }
    }
    private void logueado(){
        if(login.getValue()){
            login.setValue(false);
        }else{
            login.setValue(true);
        }
    }

    @FXML
    private void Exit(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    private void PistasDisponiblesAction(ActionEvent event) throws IOException {
         pist = pistas.get(0);
         FXMLLoader micargador = new FXMLLoader(getClass().getResource("verReservasController.fxml"));
        Parent root = micargador.load();
        Stage stage = new Stage();
        verReservasController asdadas = micargador.getController();
        Scene scene= new Scene(root, 700, 1000);
        stage.setScene(scene);
        stage.setTitle("Ver reservas de la Pista 1");
        stage.initModality(Modality.APPLICATION_MODAL);
        asdadas.a(pist);
        stage.showAndWait();
    }

    
     @FXML
    private void opciones(ActionEvent event) throws IOException {
        FXMLLoader micargador = new FXMLLoader(getClass().getResource("Options.fxml"));
        Parent root = micargador.load();
         Stage stage = new Stage();
        
        OptionsController control = micargador.getController();
        Scene scene = new Scene(root, 1000, 600);
        control.inita(myself);
        stage.setScene(scene);
        stage.setTitle("Opciones");
        stage.initModality(Modality.APPLICATION_MODAL);
     
        stage.showAndWait();
        if(control.isOK()){
            avatar.setImage(myself.getImage());
        }
        
        
    }
    @FXML
    private void cerrarSesion(ActionEvent event) {
        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        al.setTitle("Confirmación");
        al.setHeaderText("Cierre de Sesión");
        al.setContentText("¿Está seguro de querer cerrar sesión?");
        Optional<ButtonType> result = al.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            myself = null;
            login.setValue(false);
            avatar.setImage(null);
        }
    }

    @FXML
    private void misReservas(ActionEvent event) throws IOException {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("MisReservas.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
       
        MisReservasController control = loader.getController();
        control.inita(myself);
        Scene scene= new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Mis Reservas");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
}