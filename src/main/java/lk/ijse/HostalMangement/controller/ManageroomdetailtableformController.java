package lk.ijse.HostalMangement.controller;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.HostalMangement.bo.BoFactory;
import lk.ijse.HostalMangement.bo.custom.RoomBo;
import lk.ijse.HostalMangement.dto.RoomDTO;
import lk.ijse.HostalMangement.entity.RoomEntity;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageroomdetailtableformController implements Initializable {
    public Button tableclosebtn;
    public TableView tableview;
    public TableColumn roomtypeidrow;
    public TableColumn typerow;
    public TableColumn keymoneyrow;
    public TableColumn qtyrow;

    private RoomBo roomBo = BoFactory.getBoFactory().getBo(BoFactory.BoType.ROOM);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDataForTable();
        setCellValueFactory();
    }

    @FXML
    private void tableclosebtnonAction(ActionEvent actionEvent) {
        Stage currentStage = (Stage) tableclosebtn.getScene().getWindow();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1.20), currentStage.getScene().getRoot());
        translateTransition.setToX(-currentStage.getWidth());

        translateTransition.setOnFinished(event -> currentStage.close());

        translateTransition.play();
    }

    private void setDataForTable(){
        List<RoomEntity>roomEntity = roomBo.getRoomDetails();
        ObservableList<RoomDTO>room = FXCollections.observableArrayList();

        for(RoomEntity roomENTITY : roomEntity){
            room.add(new RoomDTO(
                    roomENTITY.getRoomTypeId(),
                    roomENTITY.getType(),
                    roomENTITY.getKeyMoney(),
                    roomENTITY.getQty()
            ));
        }
        tableview.setItems(room);
    }

    private void setCellValueFactory(){
        roomtypeidrow.setCellValueFactory(new PropertyValueFactory<>("RoomTypeId"));
        typerow.setCellValueFactory(new PropertyValueFactory<>("Type"));
        keymoneyrow.setCellValueFactory(new PropertyValueFactory<>("KeyMoney"));
        qtyrow.setCellValueFactory(new PropertyValueFactory<>("Qty"));
    }

}
