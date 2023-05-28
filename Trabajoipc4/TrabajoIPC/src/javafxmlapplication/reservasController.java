/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapplication;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Court;
import model.Member;
import model.*;


/**
 *
 * @author DSIC_jsoler
 */
public class reservasController implements Initializable {

    @FXML
    private DatePicker day;
    @FXML
    private GridPane grid;
    @FXML
    private Label slotSelected;
    @FXML
    private Label labelCol;

    private final LocalTime firstSlotStart = LocalTime.of(9, 0);
    private final Duration slotLength = Duration.ofMinutes(60);
    private final LocalTime lastSlotStart = LocalTime.of(21, 0);

    // se puede cambiar por codigo la pseudoclase activa de un nodo    
    private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

    private List<TimeSlot> timeSlots = new ArrayList<>(); //Para varias columnas List<List<TimeSolt>>

    private ObjectProperty<TimeSlot> timeSlotSelected;
    
    private LocalDate daySelected;
    @FXML
    private Label a1;
    
    private Club club;
    public Court pist;
    public Member member;
    @FXML
    private Button salir;
    public String Nick ;
    public boolean credito;
    public String Pass;
    public LocalDateTime a;
    public List<Booking> pistasDia;
    @FXML
    private Label A2;
    @FXML
    private Label A3;
    @FXML
    private Label A4;
    @FXML
    private Label A5;
    public LocalTime z;
    @FXML
    private Label A6;
    @FXML
    private Label A7;
    @FXML
    private Label A8;
    @FXML
    private Label A9;
    @FXML
    private Label A10;
    @FXML
    private Label A11;
    @FXML
    private Label A12;
    @FXML
    private Label A13;
    public Member myself;
    public boolean estaReservado1= true;
    public boolean estaReservado2= true;
    public boolean estaReservado3= true;
    public boolean estaReservado4= true;
    public boolean estaReservado5= true;
    public boolean estaReservado6= true;
    public boolean estaReservado7= true;
    public boolean estaReservado8= true;
    public boolean estaReservado9= true;
    public boolean estaReservado10= true;
    public boolean estaReservado11= true;
    public boolean estaReservado12= true;
    public boolean estaReservado13= true;
    boolean ar[] = {estaReservado1,estaReservado2,estaReservado3,estaReservado4,estaReservado5,estaReservado6,estaReservado7,estaReservado8,estaReservado9,estaReservado10,estaReservado11,estaReservado12,estaReservado13};
    
    
    
   
 

  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            club = club.getInstance();
        } catch (ClubDAOException ex) {
            Logger.getLogger(reservasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(reservasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
        timeSlotSelected = new SimpleObjectProperty<>();
        pistasDia = club.getForDayBookings(LocalDate.now());
        
        Label arr[] = {a1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13};
        //---------------------------------------------------------------------
        //cambia los SlotTime al cambiar de dia
        day.valueProperty().addListener((a, b, c) -> {
            setTimeSlotsGrid(c);
            labelCol.setText(c.getDayOfWeek().getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault()));
            grid.setVisible(true);
            pistasDia = club.getForDayBookings(c);
            
                for(int i= 9; i<= 21; i++){
                    z=LocalTime.of(i, 0);
                    ar[i-9]= true;
                    arr[i-9].setText("Disponible");
                    for(int j=0; j<pistasDia.size(); j++){
                         
                        if(pistasDia.get(j).getFromTime()==z){
                          ar[i-9]= false;  
                        arr[i-9].setText("RESERVADO POR :" + pistasDia.get(j).getMember().getNickName());
                             break;
                            }
                        
                    }
            }  
                
        
        });
        
        //---------------------------------------------------------------------
        //inicializa el DatePicker al dia actual
        //day.setValue(LocalDate.now());        

        //---------------------------------------------------------------------
        // pinta los SlotTime en el grid
        //setTimeSlotsGrid(day.getValue());

        
        
       
        
        
        //---------------------------------------------------------------------
        // enlazamos timeSlotSelected con el label para mostrar la seleccion
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("E MMM d");
        timeSlotSelected.addListener((a, b, c) -> {
            if (c == null) {
                slotSelected.setText("");
            } else {
                slotSelected.setText(c.getDate().format(dayFormatter)
                        + "-"
                        + c.getStart().format(timeFormatter));
            }
            
        }
                
        );
    }
    public void a (Court b, Member c){
        pist=b;
        myself = c;
    }
    
    public void b(Member c){
     try {
            club = club.getInstance();
        } catch (ClubDAOException ex) {
            Logger.getLogger(reservasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(reservasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Nick = c.getNickName();
        Pass = c.getPassword();
        credito = club.hasCreditCard(Nick);
        member = club.getMemberByCredentials(Nick, Pass);
}

    private void setTimeSlotsGrid(LocalDate date) {
        //actualizamos la seleccion
        timeSlotSelected.setValue(null);

        //--------------------------------------------        
        //borramos los SlotTime del grid
        ObservableList<Node> children = grid.getChildren();
        for (TimeSlot timeSlot : timeSlots) {
            children.remove(timeSlot.getView());
        }
        timeSlots = new ArrayList<>();

        //----------------------------------------------------------------------------------
        // desde la hora de inicio y hasta la hora de fin creamos slotTime segun la duracion
        int slotIndex = 1;
        for (LocalDateTime startTime = date.atTime(firstSlotStart);
                !startTime.isAfter(date.atTime(lastSlotStart));
                startTime = startTime.plus(slotLength)) {

            //---------------------------------------------------------------------------------------
            // creamos el SlotTime, lo anyadimos a la lista de la columna y asignamos sus manejadores
            TimeSlot timeSlot = new TimeSlot(startTime, slotLength);
            timeSlots.add(timeSlot);
            registerHandlers(timeSlot);
            //-----------------------------------------------------------
            // lo anyadimos al grid en la posicion x= 1, y= slotIndex
            grid.add(timeSlot.getView(), 1, slotIndex);
            slotIndex++;
            
        }
    }
    
    public void registerHandlers(TimeSlot timeSlot)  {
            Label arr[] = {a1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13};
        timeSlot.getView().setOnMousePressed((MouseEvent event) -> {
            //---------------------------------------------slot----------------------------
            //solamente puede estar seleccionado un slot dentro de la lista de slot
            timeSlots.forEach(slot -> {
                slot.setSelected(slot == timeSlot);
            });

            //----------------------------------------------------------------
            //actualizamos el label Dia-Hora, esto es ad hoc,  para mi diseño
            timeSlotSelected.setValue(timeSlot);
            //----------------------------------------------------------------
            // si es un doubleClik  vamos a mostrar una alerta y cambiar el estilo de la celda
            if (event.getClickCount() > 1) {
                if(ar[timeSlot.getTime().getHour()-9]==true){
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("SlotTime");
                alerta.setHeaderText("Confirma la selecció");
                alerta.setContentText("Has seleccionat: "
                        + timeSlot.getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) + ", "
                        + timeSlot.getTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
                Optional<ButtonType> result = alerta.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK ) {
                    try {
                        ar[timeSlot.getTime().getHour()-9]=false;
                        arr [timeSlot.getTime().getHour()-9].setText("RESERVADO POR :" + Nick);
                        club.registerBooking (timeSlot.start, timeSlot.getDate(), timeSlot.getTime(), credito, pist,member);
                    } catch (ClubDAOException ex) {
                        Logger.getLogger(reservasController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ObservableList<String> styles = timeSlot.getView().getStyleClass();
                    if (styles.contains("time-slot")) {
                        styles.remove("time-slot");
                        styles.add("time-slot-libre");
                    } else {
                        styles.remove("time-slot-libre");
                        styles.add("time-slot");
                    }
                }
               
                }
                 else{
                    Alert alert = new Alert(AlertType.WARNING);
// ó AlertType.WARNING ó AlertType.ERROR ó AlertType.CONFIRMATIONalert.setTitle("Diálogo de información");
                    alert.setHeaderText("PISTA YA RESERVADA");
// ó null si no queremos cabecera
                alert.setContentText("Esta pista ya ha sido reservada por otro usuario" );
                alert.showAndWait();
                }
            }
        });
    }

    @FXML
    private void salirACTION(ActionEvent event) {
        salir.getScene().getWindow().hide();
    }

    public class TimeSlot {

        private final LocalDateTime start;
        private final Duration duration;
        protected final Pane view;

        private final BooleanProperty selected = new SimpleBooleanProperty();

        public final BooleanProperty selectedProperty() {
            return selected;
        }

        public final boolean isSelected() {
            return selectedProperty().get();
        }

        public final void setSelected(boolean selected) {
            selectedProperty().set(selected);
        }

        public TimeSlot(LocalDateTime start, Duration duration) {
            this.start = start;
            this.duration = duration;
            view = new Pane();
            view.getStyleClass().add("time-slot");
            // ---------------------------------------------------------------
            // de esta manera cambiamos la apariencia del TimeSlot cuando los seleccionamos
            selectedProperty().addListener((obs, wasSelected, isSelected)
                    -> view.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, isSelected));

        }

        public LocalDateTime getStart() {
            return start;
        }

        public LocalTime getTime() {
            return start.toLocalTime();
        }

        public LocalDate getDate() {
            return start.toLocalDate();
        }

        public DayOfWeek getDayOfWeek() {
            return start.getDayOfWeek();
        }

        public Duration getDuration() {
            return duration;
        }

        public Node getView() {
            return view;
        }
        public  Booking reserva() throws ClubDAOException{
        return  club.registerBooking (start, getDate(), getTime(), credito, pist,member);
        //return Booking reserva = (start, getDate(), getTime(), true, pist,member );
        }
    }

}
