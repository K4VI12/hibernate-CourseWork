package lk.ijse.HostalMangement.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.ijse.HostalMangement.bo.BoFactory;
import lk.ijse.HostalMangement.bo.custom.RoomBo;
import lk.ijse.HostalMangement.dto.RoomDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManageroomformController implements Initializable {
    public Button tableopenbtn;
    public Button homepagebtn;
    public JFXComboBox <String> roomtypeidtxt;
    public JFXTextField keymoneytxt;
    public JFXTextField qtytxt;
    public Button savebtn;
    public Button updatebtn;
    public Button deletebtn;
    public JFXComboBox <String> Type;
    public JFXTextField roomtypeidSave;
    public Button savefieldbtn;
    public Button updatefieldbtn;
    public JFXTextField roomtypeSave;
    public Button typefieldbtn;
    public Button updatetypefieldbtn;

    private List<String> dataList = new ArrayList<>();

    private RoomBo roomBo = BoFactory.getBoFactory().getBo(BoFactory.BoType.ROOM);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDataForComboBox(SelectType.ROOM_TYPE_ID);
        setDataForComboBox(SelectType.TYPE);
    }

    @FXML
    private void tableopenbtnonAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/manageroomdetailtableform.fxml"));
        Parent root1 = null;
        try {
            root1 = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1));
        Stage currentStage = (Stage) tableopenbtn.getScene().getWindow();
        stage.setY(currentStage.getY()+80);
        stage.setX(currentStage.getX()+80);

        stage.show();


        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1.20), root1);
        translateTransition.setToX(10);

        translateTransition.play();
    }

    @FXML
    private void homepagebtnonAction(ActionEvent actionEvent) {
        Stage currentStage = (Stage) homepagebtn.getScene().getWindow();
        double yaxics = currentStage.getY()+50;
        double xaxics = currentStage.getX()+50;

        double middleY = currentStage.getY() + currentStage.getHeight() / 2;
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1.2)
                , currentStage.getScene().getRoot());
        translateTransition.setToY(middleY);
        translateTransition.setOnFinished(event -> currentStage.close());
        translateTransition.play();

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(1150);
                    Platform.runLater(() -> {
                        try {
                            PageLauncher.OpenPageWithLocation("/view/homepageform.fxml",yaxics,xaxics);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                }
            }
        }.start();
    }

    @FXML
    private void roomtypeidtxtonAction(ActionEvent actionEvent) {
        if(roomtypeidtxt.getValue()!=null&roomtypeidtxt.getValue()!="") {
            try {
                RoomDTO roomDTO = roomBo.getRoom(roomtypeidtxt.getValue());

                roomtypeidtxt.setDisable(true);

                Type.setValue(roomDTO.getType());
                keymoneytxt.setText(roomDTO.getKeyMoney());
                qtytxt.setText(String.valueOf(roomDTO.getQty()));
                setDataForComboBox(SelectType.ROOM_TYPE_ID);
                setDataForComboBox(SelectType.TYPE);
            } catch (Exception e) {
                setDefault();
                showAlert("Room Management"
                        , "Something Wrong !" + "\n" + "Please Check & Enter Valid ID"
                        , SelectType.WARNING);
            }
        }
    }

    @FXML
    private void savebtnonAction(ActionEvent actionEvent) {
        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setRoomTypeId(roomtypeidSave.getText());
        if(roomtypeSave.isVisible()){
            roomDTO.setType(roomtypeSave.getText());
        }else{
            roomDTO.setType(Type.getValue());
        }
        roomDTO.setKeyMoney(keymoneytxt.getText());
        roomDTO.setQty(Integer.parseInt(qtytxt.getText()));

        String save = roomBo.SaveRoom(roomDTO);
        if(save.equals("-1")){
            showAlert("Room Management"
                    ,"Something Wrong !"+"\n"+"Duplicate ID Entry"
                    , SelectType.ERROR);
            setDefault();
        }else{
            showAlert("Room Management"
                    ,"Successfully Room Details Saved !"
                    , SelectType.INFORMATION);
            setDefault();
            setDataForComboBox(SelectType.ROOM_TYPE_ID);
            setDataForComboBox(SelectType.TYPE);
        }
    }

    @FXML
    private void updatebtnonAction(ActionEvent actionEvent) {
        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setRoomTypeId(roomtypeidtxt.getValue());
        if(roomtypeSave.isVisible()){
            roomDTO.setType(roomtypeSave.getText());
        }else{
            roomDTO.setType(Type.getValue());
        }
        roomDTO.setKeyMoney(keymoneytxt.getText());
        roomDTO.setQty(Integer.parseInt(qtytxt.getText()));

        boolean update = roomBo.UpdateRoom(roomDTO);
        if(update==true){
            showAlert("Room Management"
                    ,"Successfully Room Details Updated !"
                    , SelectType.INFORMATION);
            setDefault();
            setDataForComboBox(SelectType.ROOM_TYPE_ID);
            setDataForComboBox(SelectType.TYPE);
        }else{
            showAlert("Room Management"
                    ,"Something Wrong !"
                    , SelectType.ERROR);
            setDefault();
        }
        roomtypeidtxt.setDisable(false);
    }

    @FXML
    private void deletebtnonAction(ActionEvent actionEvent) {
        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setRoomTypeId(roomtypeidtxt.getValue());
        roomDTO.setType(Type.getValue());
        roomDTO.setKeyMoney(keymoneytxt.getText());
        roomDTO.setQty(Integer.parseInt(qtytxt.getText()));

        try {
            roomBo.DeleteRoom(roomDTO);

            showAlert("Room Management"
                    ,"Successfully Room Details Deleted !"
                    , SelectType.INFORMATION);
            setDefault();
            setDataForComboBox(SelectType.ROOM_TYPE_ID);
            setDataForComboBox(SelectType.TYPE);
        }catch (Exception e){
            showAlert("Room Management"
                    ,"Something Wrong !"
                    , SelectType.ERROR);
            setDefault();
        }
        roomtypeidtxt.setDisable(false);
    }

    @FXML
    private void savefieldbtnonAction(ActionEvent actionEvent) {
        roomtypeidtxt.setVisible(false);
        roomtypeidSave.setVisible(true);
        savefieldbtn.setVisible(false);
        updatefieldbtn.setVisible(true);
    }

    @FXML
    private void updatefieldbtnonAction(ActionEvent actionEvent) {
        roomtypeidSave.setVisible(false);
        roomtypeidtxt.setVisible(true);
        updatefieldbtn.setVisible(false);
        savefieldbtn.setVisible(true);
    }

    @FXML
    private void savetypefieldbtnonAction(ActionEvent actionEvent) {
        Type.setVisible(false);
        roomtypeSave.setVisible(true);
        typefieldbtn.setVisible(false);
        updatetypefieldbtn.setVisible(true);
    }

    @FXML
    private void updatetypefieldbtnonAction(ActionEvent actionEvent) {
        roomtypeSave.setVisible(false);
        Type.setVisible(true);
        updatetypefieldbtn.setVisible(false);
        typefieldbtn.setVisible(true);
    }

    private void setDataForComboBox(SelectType type){

        if(type==SelectType.TYPE) {
            dataList = roomBo.getAllRoomType();

            List<String> checkdataList = new ArrayList<>();
            checkdataList.add(dataList.get(0));

            for(int i=0; i<dataList.size(); i++){
                inner:for (int j=0; j<checkdataList.size(); j++){
                if(dataList.get(i).equals(checkdataList.get(j))){
                    break inner;
                }
                    if(j==checkdataList.size()-1&dataList.get(i)!=checkdataList.get(checkdataList.size()-1)){
                        checkdataList.add(dataList.get(i));
                    }
                }
            }
            dataList.clear();
            dataList.addAll(checkdataList);
            checkdataList.clear();

            ObservableList<String>observableList = FXCollections.observableArrayList(dataList);
            Type.setItems(observableList);
            dataList.clear();
        }else{
            dataList = roomBo.getAllRoomTypeID();

            ObservableList<String>observableList = FXCollections.observableArrayList(dataList);
            roomtypeidtxt.setItems(observableList);
            dataList.clear();
        }


    }

    private void setDefault(){
        roomtypeidtxt.setValue(null);
        roomtypeSave.clear();
        Type.setValue(null);
        roomtypeidSave.clear();
        keymoneytxt.clear();
        qtytxt.clear();
    }

    private void showAlert(String title, String content,SelectType type) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if(type== SelectType.INFORMATION){
            alert = new Alert(Alert.AlertType.INFORMATION);
        }else if(type== SelectType.WARNING){
            alert = new Alert(Alert.AlertType.WARNING);
        }else if(type== SelectType.ERROR){
            alert = new Alert(Alert.AlertType.ERROR);
        }

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }

    private enum SelectType{
        ROOM_TYPE_ID,TYPE,INFORMATION,WARNING,ERROR
    }

}
