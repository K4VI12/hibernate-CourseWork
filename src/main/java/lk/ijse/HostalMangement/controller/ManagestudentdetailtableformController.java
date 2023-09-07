package lk.ijse.HostalMangement.controller;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.ijse.HostalMangement.bo.BoFactory;
import lk.ijse.HostalMangement.bo.custom.StudentBo;
import lk.ijse.HostalMangement.dto.StudentDTO;
import lk.ijse.HostalMangement.entity.StudentEntity;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManagestudentdetailtableformController implements Initializable {
    public Button tableclosebtn;
    public TableColumn studentidrow;
    public TableColumn firstnamerow;
    public TableColumn lastnamerow;
    public TableColumn Genderrow;
    public TableColumn addressrow;
    public TableColumn dobrow;
    public TableColumn contactnumberrow;
    public TableView tableview;

    private StudentBo studentBo = BoFactory.getBoFactory().getBo(BoFactory.BoType.STUDENT);

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
        List<StudentEntity>student = studentBo.getAllStudent();
        ObservableList<StudentDTO>studentDTO = FXCollections.observableArrayList();

        for(StudentEntity studentEntity : student){
            studentDTO.add(new StudentDTO(
                    studentEntity.getStudentId(),
                    studentEntity.getFullName().getFirstName(),
                    studentEntity.getFullName().getLastName(),
                    studentEntity.getFullName(),
                    studentEntity.getAddress(),
                    studentEntity.getContactNumber(),
                    studentEntity.getDateOfBirth(),
                    studentEntity.getGender()
            ));
        }
        tableview.setItems(studentDTO);
    }

    private void setCellValueFactory(){
        studentidrow.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        firstnamerow.setCellValueFactory(new PropertyValueFactory<>("Name"));
        lastnamerow.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        Genderrow.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        addressrow.setCellValueFactory(new PropertyValueFactory<>("Address"));
        dobrow.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        contactnumberrow.setCellValueFactory(new PropertyValueFactory<>("ContactNumber"));
    }

}
