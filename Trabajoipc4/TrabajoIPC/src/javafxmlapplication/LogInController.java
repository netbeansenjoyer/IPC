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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;


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
    Member log;
    Boolean ok;
    
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
        
        
        cancelar.setOnAction((event) ->{
        cancelar.getScene().getWindow().hide();
        });
      }
                


    @FXML
    private void pulsarAceptar(ActionEvent event) throws ClubDAOException, IOException{
        Club club = Club.getInstance();
      log = club.getMemberByCredentials(NickName.textProperty().getValue(), Password.textProperty().getValue());
        if(log == null) {
            NickName.textProperty().setValue("");
            Password.textProperty().setValue("");
            mensajeErr.setVisible(true);
        } else{
            aceptar.getScene().getWindow().hide();
            ok = true;
        }
        }
    public Member getMember(){
        return log;
    }
    public boolean isOk(){
        return ok;
    }
    }
        
    
 

