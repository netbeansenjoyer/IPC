/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Sblasco
 */
public class SignUpController implements Initializable {

    @FXML
    private Button botA;
    @FXML
    private Button botC;
    @FXML
    private Button fotografia;
    
    @FXML
    private TextField TextNomb;
    @FXML
    private TextField TextApell;
    @FXML
    private TextField TextNick;
    @FXML
    private PasswordField TextPasw;
    @FXML
    private TextField TextTel;
    @FXML
    private TextField TextTar;
    @FXML
    private TextField TextSVC;
    @FXML
    private Label errName;
    @FXML
    private Label errApell;
    @FXML
    private Label errNick;
    @FXML
    private Label errpasw;
    @FXML
    private Label errTel;
    @FXML
    private Label errTar;
    @FXML
    private Label errsvc;
    Club club;
    @FXML
    private ImageView photo;
    Image img;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            club = Club.getInstance();
        } catch (ClubDAOException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
      img = new Image("Librerias/tenisClub.jar/default.png");
      photo.setImage(img);
        
}

   @FXML
    private void aceptar(ActionEvent event) throws ClubDAOException, IOException {
        boolean aux = checkName(TextNomb.getText());
        if(aux == false){
           errName.setDisable(false);
        }else{
            errName.setDisable(true);
        }
        
        boolean aux2 = checkApellidos(TextApell.getText());
        if(aux == false){
           errApell.setDisable(false);
        }else{
            errApell.setDisable(true);
        }
        
        boolean aux3 = checkNick(TextNick.getText());
        if(aux == false){
           errNick.setDisable(false);
        }else{
            errNick.setDisable(true);
        }
        
        boolean aux4 = checkPasw(TextPasw.getText());
        if(aux == false){
           errpasw.setDisable(false);
        }else{
            errpasw.setDisable(true);
        }
        boolean aux5 = checkTel(TextTel.getText());
        if(aux == false){
           errTel.setDisable(false);
        }else{
            errTel.setDisable(true);
        }
        
        boolean aux6 = checkTar(TextTar.getText());
        if(TextTar.getText() != null){
        if(aux == false){
           errTar.setDisable(false);
        }else{
            errTar.setDisable(true);
        }
        }
        boolean aux7 = checkSVC(TextSVC.getText());
        int svc = 0;
        if(TextSVC.getText() != null){
           svc = Integer.parseInt(TextSVC.getText());
        if(aux == false){
           errsvc.setDisable(false);
        }else{
            errsvc.setDisable(true);
        }
                        
        }
       
        boolean aux8 = aux && aux2 && aux3 && aux4 && aux5;
        if((aux8 && !aux6 && !aux7) || (aux8 && aux6 && aux7)){
            
            club.registerMember(TextNomb.getText(), TextApell.getText(), TextTel.getText(), TextNick.getText(), TextPasw.getText(), TextTar.getText(),svc,img);
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void selFoto(ActionEvent event) {
    }
    
    private boolean checkName(String nombre){
        if(nombre != null){
            
         for(int i = 0; i < nombre.length(); i++){
             int aux = (int) nombre.charAt(i);
             if(!((aux >= 65 && aux <=90) || (aux >= 97 && aux <=122) )){
                 return false;
             }
         }
         return true;
        }
        return false;
    }
    
    private boolean checkPasw(String nombre){
        if(nombre != null){
            if(nombre.length() < 6){return false;}
         for(int i = 0; i < nombre.length(); i++){
             int aux = (int) nombre.charAt(i);
             if(!((aux >= 48 && aux <= 57) || (aux >= 65 && aux <=90) || 
                     (aux >= 97 && aux <=122) )){
                 return false;
             }
         }
         return true;
        }
        return false;
    }
    private boolean checkNick(String nombre) throws ClubDAOException, IOException{
        if(nombre != null){
         for(int i = 0; i < nombre.length() - 1; i++){
             int aux = (int) nombre.charAt(i);
             if(aux == 32){
                 return false;
             }
         }
         
         ArrayList<Member> lista =  (ArrayList<Member>) club.getMembers();
         
         for(int i = 0; i < lista.size() - 1; i++){
             if(lista.get(i).equals(nombre)){
                 return false;
             }
         }
         return true;
        }
        return false;
    }
    private boolean checkTar(String nombre){
        if(nombre != null){
            if(nombre.length() != 16){return false;}
         for(int i = 0; i < nombre.length(); i++){
             int aux = (int) nombre.charAt(i);
             if(!(aux >= 48 && aux <= 57)){
                 return false;
             }
         }
         return true;
        }
        return false;
    }
    private boolean checkTel(String nombre) throws ClubDAOException, IOException{
        if(nombre != null){
         for(int i = 0; i < nombre.length() - 1; i++){
             int aux = (int) nombre.charAt(i);
             if(!(aux >= 48 && aux <= 57)){
                 return false;
             }
         }
         return true;
        }
        return false;
    }
     private boolean checkSVC(String nombre){
        if(nombre != null){
            if(nombre.length() != 3){return false;}
         for(int i = 0; i < nombre.length(); i++){
             int aux = (int) nombre.charAt(i);
             if(!(aux >= 48 && aux <= 57) ){
                 return false;
             }
         }
         return true;
        }
        return false;
    }
     private boolean checkApellidos(String nombre){
        if(nombre != null){
            
         for(int i = 0; i < nombre.length(); i++){
             int aux = (int) nombre.charAt(i);
             if(!((aux >= 65 && aux <=90) || (aux >= 97 && aux <=122)  || (aux == 32))){
                 return false;
             }
         }
         return true;
        }
        return false;
    }
}
