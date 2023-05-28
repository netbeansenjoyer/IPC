/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxmlapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;
import model.Club;
import model.Member;
import model.ClubDAOException;
import java.io.IOException;
import java.lang.NullPointerException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;


/**
 *
 * @author vmariaaa
 */
public class LogInController implements Initializable{

    @FXML
    private TextField NickName;
    @FXML
    private PasswordField Password;
    @FXML
    private Button aceptar;
    @FXML
    private Button cancelar;
    @FXML
    private Text mensajeErr;
    
    //properties to control valid fieds values. 
   // private BooleanProperty validPassword;
   // private BooleanProperty validNick;
    
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
        aceptar.disableProperty().bind(NickName.textProperty().isEmpty().or(Password.textProperty().isEmpty()));
     try{
         Club club = Club.getInstance();
         String nick = NickName.getText();
         String pass = Password.getText();
         
        NickName.focusedProperty().addListener((observable, oldValue, newValue) ->{
             if(!newValue) {//focus lost.
               club.existsLogin(nick);
             }
         } );
        
        Password.focusedProperty().addListener((observable, oldValue, newValue) ->{
             if(!newValue) {//focus lost.
               club.existsLogin(pass);
             }
         } );
        
       }catch(ClubDAOException ex) {
           Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
           Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
      } 
        
     //Cuando pulsemos la tecla enter avanzará al siguiente campo
     NickName.setOnKeyPressed(event ->{
         if(event.getCode() == KeyCode.ENTER){
             Password.requestFocus();
         }
     });
     //Cuando pulsemos enter no llevará al botón de aceptar.
     Password.setOnKeyPressed(event ->{
         if(event.getCode() == KeyCode.ENTER){
             aceptar.requestFocus();
            //Creamos una alerta para asegurarnos de que el usuario desea realizar el inicio de sesión
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Diálogo de confirmación");
            alert.setHeaderText("Aceptar");
            alert.setContentText("¿Desea aceptar e iniciar sesión?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("OK");
                 aceptar.getScene().getWindow().hide();
            } else { 
                System.out.println("CANCEL");
            }
      
         }
     });
        //Creamos una alerta para confirmar que el usuario desea salir del inicio de sesión 
        cancelar.setOnAction((event) ->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Diálogo de confirmación");
            alert.setHeaderText("¿Cancelar?");
            alert.setContentText("¿Seguro que desea cancelar?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("OK");
                 cancelar.getScene().getWindow().hide();
            } else { 
                System.out.println("CANCEL");
            }
        });
      }
                


    @FXML
    private void pulsarAceptar(ActionEvent event) throws ClubDAOException, IOException{
        //try{
        Club club = Club.getInstance();
        //Si los datos introducidos no coinciden con ningún member registrado entonces aparece un mensaje
        //de error y hay que realizar el registro de nuevo si se desea.
        Member log = club.getMemberByCredentials(NickName.textProperty().getValue(), Password.textProperty().getValue());
        if(log == null) {
            NickName.textProperty().setValue("");
            Password.textProperty().setValue("");
            mensajeErr.setVisible(true);
           
        } else{
            aceptar.getScene().getWindow().hide();
        }
       // }catch (NullPointerException e){
        
          //  NickName.textProperty().setValue("");
         //   Password.textProperty().setValue("");
         //   mensajeErr.setVisible(true);
//}
    }
}
        
     
    
 

