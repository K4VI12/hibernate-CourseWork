package lk.ijse.HostalMangement.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.ijse.HostalMangement.bo.BoFactory;
import lk.ijse.HostalMangement.bo.custom.StudentBo;
import lk.ijse.HostalMangement.dto.StudentDTO;
import lk.ijse.HostalMangement.embedded.Name;

import java.io.IOException;
import java.time.LocalDate;

public class ManagestudentformController {
    public Button tableopenbtn;
    public Button homepagebtn;
    public JFXTextField studentIdtxt;
    public JFXTextField studentFirstNametxt;
    public JFXTextField studentLastNametxt;
    public JFXTextField contactNumbertxt;
    public JFXCheckBox malecheck;
    public JFXCheckBox femelcheck;
    public DatePicker dobtxt;
    public Button savebtn;
    public Button updatebtn;
    public Button deletebtn;
    public JFXTextField adresstxt;

    private static String checkGender;

    @FXML
    private void tableopenbtnonAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/managestudentdetailtableform.fxml"));
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
        //System.out.println(currentStage.getY());
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
    private void malecheckBoxonAction(ActionEvent actionEvent) {
        if(malecheck.isSelected()){
            femelcheck.setSelected(false);
        }
    }

    @FXML
    private void femekcheckBoxonAction(ActionEvent actionEvent) {
        if(femelcheck.isSelected()){
            malecheck.setSelected(false);
        }
    }

    @FXML
    private void savebtnonaction(ActionEvent actionEvent) {
        StudentDTO student = new StudentDTO();
        Name fullname = new Name();

        fullname.setFirstName(studentFirstNametxt.getText());
        fullname.setLastName(studentLastNametxt.getText());

        java.time.LocalDate selectedDate = dobtxt.getValue();

        student.setStudentId(studentIdtxt.getText());
        student.setFullName(fullname);
        student.setAddress(adresstxt.getText());
        student.setContactNumber(contactNumbertxt.getText());
        student.setDateOfBirth(selectedDate.toString());

        if(malecheck.isSelected()){
            checkGender = "Male";
        }else{
            checkGender = "Female";
        }
        student.setGender(checkGender);

        StudentBo studentBo = BoFactory.getBoFactory().getBo(BoFactory.BoType.STUDENT);
        String saveId = studentBo.SaveStudent(student);
        if(saveId.equals("-1")){
            showAlert("Student Management"
                    ,"Something Wrong !"+"\n"+"Duplicate ID Entry"
                    ,SelectType.ERROR);
        }else{
            showAlert("Student Management"
                    ,"Successfully Student Saved !"
                    ,SelectType.INFORMATION);
        }
        setDefault();
    }

    @FXML
    private void studentidtxtonAction(ActionEvent actionEvent) {
        StudentBo studentBo = BoFactory.getBoFactory().getBo(BoFactory.BoType.STUDENT);
        try {
            StudentDTO studentDTO = studentBo.getStudent(studentIdtxt.getText());

            studentFirstNametxt.setText(studentDTO.getFullName().getFirstName());
            studentLastNametxt.setText(studentDTO.getFullName().getLastName());
            contactNumbertxt.setText(studentDTO.getContactNumber());
            adresstxt.setText(studentDTO.getAddress());
            dobtxt.setValue(LocalDate.parse(studentDTO.getDateOfBirth()));
            studentIdtxt.setDisable(true);
            if(studentDTO.getGender().equals("Male")){
                malecheck.setSelected(true);
            }else{
                femelcheck.setSelected(true);
            }
        }catch (Exception e) {
            setDefault();
            showAlert("Student Management"
                    ,"Something Wrong !"+"\n"+"Please Check & Enter Valid ID"
                    ,SelectType.WARNING);
        }

    }

    @FXML
    private void updatebtnonAction(ActionEvent actionEvent) {
        StudentDTO studentDTO = new StudentDTO();
        Name fullName = new Name();

        fullName.setFirstName(studentFirstNametxt.getText());
        fullName.setLastName(studentLastNametxt.getText());

        java.time.LocalDate selectedDate = dobtxt.getValue();

        studentDTO.setStudentId(studentIdtxt.getText());
        studentDTO.setFullName(fullName);
        studentDTO.setAddress(adresstxt.getText());
        studentDTO.setContactNumber(contactNumbertxt.getText());
        studentDTO.setDateOfBirth(selectedDate.toString());

        if(malecheck.isSelected()){
            checkGender = "Male";
        }else{
            checkGender = "Female";
        }
        studentDTO.setGender(checkGender);

        StudentBo studentBo = BoFactory.getBoFactory().getBo(BoFactory.BoType.STUDENT);
        try{
            studentBo.UpdateStudent(studentDTO);
            showAlert("Student Management"
                    ,"Successfully Student Details Updated !"
                    ,SelectType.INFORMATION);
        }catch(Exception e){
            showAlert("Student Management"
                    ,"Something Wrong !"+"\n"+studentIdtxt.getText()
                            +" Student Details Not Updated !"
                    ,SelectType.ERROR);
        }
        setDefault();
        studentIdtxt.setDisable(false);

    }

    @FXML
    private void deletebtnonAction(ActionEvent actionEvent) {
        StudentDTO studentDTO = new StudentDTO();
        Name fullName = new Name();

        fullName.setFirstName(studentFirstNametxt.getText());
        fullName.setLastName(studentLastNametxt.getText());

        java.time.LocalDate selectedDate = dobtxt.getValue();

        studentDTO.setStudentId(studentIdtxt.getText());
        studentDTO.setFullName(fullName);
        studentDTO.setAddress(adresstxt.getText());
        studentDTO.setContactNumber(contactNumbertxt.getText());
        studentDTO.setDateOfBirth(selectedDate.toString());

        if(malecheck.isSelected()){
            checkGender = "Male";
        }else{
            checkGender = "Female";
        }
        studentDTO.setGender(checkGender);

        StudentBo studentBo = BoFactory.getBoFactory().getBo(BoFactory.BoType.STUDENT);
        try{
            studentBo.DeleteStudent(studentDTO);
            showAlert("Student Management"
                    ,"Successfully Student Details Deleted !"
                    ,SelectType.INFORMATION);
        }catch (Exception e){
            showAlert("Student Management"
                    ,"Something Wrong !"
                    ,SelectType.ERROR);
        }
        setDefault();
        studentIdtxt.setDisable(false);
    }

    private void setDefault(){
        studentFirstNametxt.clear();
        studentLastNametxt.clear();
        dobtxt.setValue(null);
        studentIdtxt.clear();
        adresstxt.clear();
        contactNumbertxt.clear();
        malecheck.setSelected(false);
        femelcheck.setSelected(false);
    }

    private void showAlert(String title, String content,SelectType type) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if(type==SelectType.INFORMATION){
            alert = new Alert(Alert.AlertType.INFORMATION);
        }else if(type==SelectType.WARNING){
            alert = new Alert(Alert.AlertType.WARNING);
        }else if(type==SelectType.ERROR){
            alert = new Alert(Alert.AlertType.ERROR);
        }

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
    }

    private enum SelectType {
        INFORMATION,WARNING,ERROR
    }

}
