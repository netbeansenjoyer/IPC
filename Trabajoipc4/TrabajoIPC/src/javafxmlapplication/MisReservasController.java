/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Sblasco
 */
public class MisReservasController implements Initializable {

    @FXML
    private Button info;
    @FXML
    private Button eliminar;
    @FXML
    private Button close;
    @FXML
    private GridPane grid;
    Member myself;
    
    @FXML
    private ListView<Booking> list;
    private ObservableList<Booking> datos;
    Club club;
    private List<Booking> aux; 
    private int i = 0;
    List<Booking> res;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void inita(Member m) {
        
        try {
            club = Club.getInstance();
            myself = m;
            aux =  club.getUserBookings(myself.getNickName());
        
        res = new ArrayList();
        for(;i < 10 && i < aux.size(); i++){
            res.add(aux.get(i));
        }
        
        datos = FXCollections.observableList(res);
        list.setItems(datos);
        
        
        list.setCellFactory(c -> new ListaCell());
        close.setOnAction((event) -> close(event));
        } catch (IOException ex) {
            Logger.getLogger(MisReservasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClubDAOException ex) {
            Logger.getLogger(MisReservasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void getInfo(ActionEvent event) {
        Booking bok = list.getSelectionModel().getSelectedItem();
        Alert al = new Alert(AlertType.INFORMATION);
        al.setTitle("Informacion de la reserva");
        al.setHeaderText("Reserva del usuario: " + myself.getNickName());
        String pago = "No Pagado";
        if(bok.getPaid()){pago = "Pagado";}
        al.setContentText("Reserva: Dia " + bok.getMadeForDay().toString() + " a las " + bok.getFromTime().toString() + "\n" + 
                "Estado: " + pago);
        al.showAndWait();
    }

    @FXML
    private void eliminar(ActionEvent event) throws ClubDAOException {   
        Booking del = list.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmacion");
        alert.setHeaderText("Eliminacion de reserva");
        alert.setContentText("¿Seguro que quiere eliminar la reserva del Dia " + del.getMadeForDay().toString() + " a las " + 
                del.getFromTime().toString());
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            club.removeBooking(del);
            aux =  club.getUserBookings(myself.getNickName());
            datos.remove(del);
            if(aux.size() > 10){
            res.add(aux.get(i));
        } else {
            
        }
        
        
        }
    }

    @FXML
    private void close(ActionEvent event) {
        this.close.getScene().getWindow().hide();
    }
    class ListaCell extends ListCell<Booking> {

        @Override
        protected void updateItem(Booking t, boolean bln) {
            super.updateItem(t, bln); //To change body of generated methods, choose Tools | Templates.
            if (bln) // esta vacia
            {
                setText("");
            } else {
                if(t.getPaid()){
                    setText(t.getMadeForDay().toString() + " " + t.getFromTime().toString() + "              Pagada" + "             " + t.getCourt().getName());

                }else{
                    setText("No pagada");
                }
            }

        }
    }
}