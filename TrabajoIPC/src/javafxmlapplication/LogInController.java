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
    private Text mensajePass;
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
      boolean aux3 = checkNick(NickName.getText());
        if(aux3 == false){
           mensajeErr.setVisible(true);
          NickName.textProperty().setValue("");
          
        }else{
            mensajeErr.setVisible(false);
            //NickName.textProperty().setValue("");
            Password.textProperty().setValue("");
        }
        //Member member = authenticate(NickName.getText(), Password.getText());
        boolean aux = checkPasw(Password.getText());
        if (aux == true && club.existsLogin(Password.getText())) {
            mensajePass.setVisible(false);
            //Password.textProperty().setValue("");
        }else{
            mensajePass.setVisible(true);
            Password.textProperty().setValue("");
        }
        boolean aux8 = aux && aux3;
        if(aux8 && club.existsLogin(Password.getText())){
            aceptar.getScene().getWindow().hide();}
        }
        
       
     private Member authenticate(String nick, String pass) throws ClubDAOException, IOException{
        Club club = Club.getInstance();
        Member member = club.getMemberByCredentials(NickName.getText(), Password.getText());
        return member;
    }
    
    @FXML
    private boolean checkPasw(String pass){
        return pass.length() >6;
    }
    
    private boolean checkNick(String nick) throws ClubDAOException, IOException{
        Club club = Club.getInstance();
        return nick.length() > 1 && !(club.existsLogin(nick));
        
    }
    
 }

