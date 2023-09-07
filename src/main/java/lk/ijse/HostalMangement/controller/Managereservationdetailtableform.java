package lk.ijse.HostalMangement.controller;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.HostalMangement.bo.BoFactory;
import lk.ijse.HostalMangement.bo.custom.ReservationBo;
import lk.ijse.HostalMangement.dto.ReservationDTO;
import lk.ijse.HostalMangement.entity.ReservationEntity;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Managereservationdetailtableform implements Initializable {
    public Button tableclosebtn;
    public TableView tableview;
    public TableColumn reservationidrow;
    public TableColumn roomtypeidrow;
    public TableColumn keymoneyrow;
    public TableColumn studentidrow;
    public TableColumn studentnamerow;
    public TableColumn daterow;
    public TableColumn statusrow;

    private ReservationBo reservationBo = BoFactory.getBoFactory().getBo(BoFactory.BoType.RESERVATION);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDataForTable();
        setCellValueFactory();
    }

    public void tableclosebtnonAction(ActionEvent actionEvent) {
        Stage currentStage = (Stage) tableclosebtn.getScene().getWindow();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1.20), currentStage.getScene().getRoot());
        translateTransition.setToX(-currentStage.getWidth());

        translateTransition.setOnFinished(event -> currentStage.close());

        translateTransition.play();
    }

    private void setDataForTable(){
        List<ReservationEntity>reservationEntity = reservationBo.getReservationDetails();
        ObservableList<ReservationDTO>reservationDTO = FXCollections.observableArrayList();

        for(ReservationEntity reservation : reservationEntity){
            reservationDTO.add(new ReservationDTO(
                    reservation.getReservationId(),
                    reservation.getLastDate(),
                    reservation.getOrderDateTime(),
                    reservation.getStudent(),
                    reservation.getStudent().getStudentId(),
                    reservation.getRoom(),
                    reservation.getRoom().getRoomTypeId(),
                    reservation.getStatus(),
                    reservation.getStudentName(),
                    reservation.getKeyMoney()
            ));
        }
        tableview.setItems(reservationDTO);
    }

    private void setCellValueFactory(){
        reservationidrow.setCellValueFactory(new PropertyValueFactory<>("ReservationId"));
        roomtypeidrow.setCellValueFactory(new PropertyValueFactory<>("RoomTypeID"));
        keymoneyrow.setCellValueFactory(new PropertyValueFactory<>("KeyMoney"));
        studentidrow.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        studentnamerow.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        daterow.setCellValueFactory(new PropertyValueFactory<>("LastDate"));
        statusrow.setCellValueFactory(new PropertyValueFactory<>("Status"));
    }

}
