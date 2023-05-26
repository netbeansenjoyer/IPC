/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Scene;
import model.Club;
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
    
    //=========================================================
    // event handler, fired when button is clicked or 
    //                      when the button has the focus and enter is pressed
    private void handleButtonAction(ActionEvent event) {
        labelMessage.setText("Hello, this is your first JavaFX project - IPC");
    }
    
    //=========================================================
    // you must initialize here all related with the object 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }    

    @FXML
    private void hora1_action(ActionEvent event) {
        
    }

    @FXML
    private void Register(ActionEvent event) throws Exception{
        FXMLLoader micargador = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        Parent root = micargador.load();
        Stage stage = new Stage();
        
        SignUpController control = micargador.getController();
        Scene scene= new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Registro de usuario");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void LogIn(ActionEvent event) throws Exception {
        FXMLLoader loader = new  FXMLLoader(getClass().getResource("LogIn.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
       
        LogInController control = loader.getController();
        Scene scene= new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Inicio de Sesión");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    
}