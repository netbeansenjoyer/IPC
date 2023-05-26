/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
    @FXML
    private ImageView photo;
    Image img;
    @FXML
    private ComboBox<String> comboImg;
    @FXML
    private Button delete;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        botA.disableProperty().bind(TextNomb.textProperty().isEmpty().or(TextApell.textProperty().isEmpty()));
        
      img = new Image("/avatars/default.png");
      photo.setImage(img);
      TextTar.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          if (!newValue.matches("\\d*")) {
              TextTar.setText(newValue.replaceAll("[^\\d]", ""));
          }
          if(newValue.length() >16){TextTar.setText(oldValue);}
      });
      TextTel.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          if (!newValue.matches("\\d*")) {
              TextTel.setText(newValue.replaceAll("[^\\d]", ""));
          }
          
      });
      TextSVC.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          
          if (!newValue.matches("\\d*")) {
              TextSVC.setText(newValue.replaceAll("[^\\d]", ""));
          }
          if(newValue.length() >3){TextSVC.setText(oldValue);}
      });
        TextNomb.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          if (!newValue.matches("([a-zA-Z]*)")) {
              TextNomb.setText(newValue.replaceAll("[^([a-zA-Z]|[ \\t\\n\\x0B\\f\\r])]", ""));
          }
        });
        TextApell.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          if (!newValue.matches("([a-zA-Z]*)")) {
              TextApell.setText(newValue.replaceAll("[^([a-zA-Z]|[ \\t\\n\\x0B\\f\\r])]", ""));
          }
        });
        TextNick.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
          if (!newValue.matches("(([a-zA-Z]*)+\\d*)")) {
              TextNick.setText(newValue.replaceAll("[^([a-zA-Z]|\\d)]", ""));
          }
        });
        comboImg.getItems().add("/avatars/default.png");
        comboImg.getItems().addAll("/avatars/men.PNG", "/avatars/men2.PNG", "/avatars/men3.PNG");
        comboImg.getItems().addAll("/avatars/men4.PNG", "/avatars/men5.PNG", "/avatars/woman.PNG");
        comboImg.getItems().addAll("/avatars/woman2.PNG", "/avatars/woman3.PNG", "/avatars/woman4.PNG");
        comboImg.getItems().addAll("/avatars/woman5.PNG", "/avatars/woman6.PNG");
        comboImg.setCellFactory(c-> new ImagenTabCell());
        comboImg.setButtonCell(new ImagenTabCell());
        comboImg.getSelectionModel().selectedItemProperty().addListener((ob, oldV,newV)->{
            if(!newV.equals(photo.getImage().getUrl())){
                photo.setImage(new Image(newV));
            }
        });
}

   @FXML
    private void aceptar(ActionEvent event) throws ClubDAOException, IOException {
        
            
        
       
        boolean aux = checkName(TextNomb.getText());
        if(aux == false){
           errName.setVisible(true);
        }else{
            errName.setVisible(false);
        }
        
        boolean aux2 = checkApellidos(TextApell.getText());
        if(aux2 == false){
           errApell.setVisible(true);
        }else{
            errApell.setVisible(false);
        }
        
        boolean aux3 = checkNick(TextNick.getText());
        if(aux3 == false){
           errNick.setVisible(true);
        }else{
            errNick.setVisible(false);
        }
        
        boolean aux4 = checkPasw(TextPasw.getText());
        if(aux4 == false){
           errpasw.setVisible(true);
        }else{
            errpasw.setVisible(false);
        }
        boolean aux5 = checkTel(TextTel.getText());
        if(aux5 == false){
           errTel.setVisible(true);
        }else{
            errTel.setVisible(false);
        }
        
        boolean aux6 = checkTar(TextTar.getText());
        if(TextTar.getText().length() > 1){
        if(aux6 == false){
           errTar.setVisible(true);
        }else{
            errTar.setVisible(false);
        }
        }
        boolean aux7 = checkSVC(TextSVC.getText());
        int svc = 0;
        if(TextSVC.getText().length() > 0){
           svc = Integer.parseInt(TextSVC.getText());
        if(aux7 == false){
           errsvc.setVisible(true);
        }else{
            errsvc.setVisible(false);
        }
                        
        }
       
        boolean aux8 = aux && aux2 && aux3 && aux4 && aux5;
        if((aux8 && !aux6 && !aux7) || (aux8 && aux6 && aux7)){
            Club club = Club.getInstance();
            club.registerMember(TextNomb.getText(), TextApell.getText(), TextTel.getText(), TextNick.getText(), TextPasw.getText(), TextTar.getText(),svc,img);
            botA.getScene().getWindow().hide();
       
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
      
        botC.getScene().getWindow().hide();

    }

    @FXML
    private void selFoto(ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir fichero");
        fileChooser.getExtensionFilters().addAll(
        
        new ExtensionFilter("Imágenes", "*.png", "*.jpg"));
        
        File selectedFile = fileChooser.showOpenDialog(
        ((Node)event.getSource()).getScene().getWindow());
        if (selectedFile != null) {
        img = new Image(new FileInputStream(selectedFile.getAbsolutePath()));
        photo.setImage(img);
        }
    }
    
    private boolean checkName(String nombre){
        return nombre.length() > 1;
    }
    
    private boolean checkPasw(String nombre){
        return nombre.length() >6;
    }
    private boolean checkNick(String nombre) throws ClubDAOException, IOException{
        Club club = Club.getInstance();
        return nombre.length() > 1 && !(club.existsLogin(nombre));
        
    }
    private boolean checkTar(String nombre){
        return nombre.length() > 15;
    }
    private boolean checkTel(String nombre) throws ClubDAOException, IOException{
        return nombre.length() > 1;
    }
     private boolean checkSVC(String nombre){
       return nombre.length() > 2;
    }
     private boolean checkApellidos(String nombre){
        return nombre.length() > 1;
    }

    @FXML
    private void deletePhoto(ActionEvent event) {
        photo.setImage(new Image("/avatars/default.png"));
        comboImg.getSelectionModel().select(0);
    }
     class ImagenTabCell extends ComboBoxListCell<String> {
        private ImageView view = new ImageView();
        private Image imagen;

        @Override
        public void updateItem(String t, boolean bln) {
            super.updateItem(t, bln); 
            if (t == null || bln) {
                setText(null);
                setGraphic(null);
            } else {
                imagen = new Image(t,25,25,true,true);
                view.setImage(imagen);
                setGraphic(view);
                setText(null);
            }
        }
    }

}
