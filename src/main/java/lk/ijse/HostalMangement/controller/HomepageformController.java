package lk.ijse.HostalMangement.controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class HomepageformController {
    public Pane roompane;
    public Pane studentpane;
    public Pane reservationpane;
    public Button addstudentbtn;
    public Button addroombtn;
    public Button managereservationbtn;
    public Button paneopenbtn;
    public Pane optionpane;
    public Button backbtn;
    public Button notificationbtn;
    public Button availablebtn;
    public Button seettingbtn;

    /*All Action On Here.*/

    public void addstudentbtnonAction(ActionEvent actionEvent) {
        PageLauncher.OpenPageWithAnimation(addstudentbtn,"/view/managestudentform.fxml",0, 0);
    }

    public void addroombtnonaction(ActionEvent actionEvent) {
        PageLauncher.OpenPageWithAnimation(addroombtn,"/view/manageroomform.fxml",0, 0);
    }

    public void managereservationbtnonAction(ActionEvent actionEvent) {
        PageLauncher.OpenPageWithAnimation(managereservationbtn,"/view/managereservationform.fxml",0, 0);
    }

    public void paneopenbtnAction(ActionEvent actionEvent) {
        optionpane.setVisible(true);
    }

    public void backbtnonAction(ActionEvent actionEvent) {
        optionpane.setVisible(false);
    }

    public void notificationbtnonAction(ActionEvent actionEvent) {

    }

    public void availablebtnonAction(ActionEvent actionEvent) {

    }

    public void seettingbtnonAction(ActionEvent actionEvent) {

    }

    /*Mouse Entetrd Action In Here.*/

    public void addstudentonMouseenterd(MouseEvent mouseEvent) {
        studentpane.setVisible(true);
        VisiblePane(studentpane);
        BtnAnimatioStart(addstudentbtn);
    }

    public void addroomonMouseenterd(MouseEvent mouseEvent) {
        roompane.setVisible(true);
        VisiblePane(roompane);
        BtnAnimatioStart(addroombtn);
    }

    public void managereservationonMouseenterd(MouseEvent mouseEvent) {
        reservationpane.setVisible(true);
        VisiblePane(reservationpane);
        BtnAnimatioStart(managereservationbtn);
    }

    public void notificationbtnonMouseenterd(MouseEvent mouseEvent) {
        BtnAnimatioStart(notificationbtn);
    }

    public void availablebtnonMouseenterd(MouseEvent mouseEvent) {
        BtnAnimatioStart(availablebtn);
    }

    public void settingMouseenterd(MouseEvent mouseEvent) {
        BtnAnimatioStart(seettingbtn);
    }

    /*Mouse Exited Action On Here.*/

    public void addstudentMouseexited(MouseEvent mouseEvent) {
        BtnAnimationStop(addstudentbtn);
    }

    public void addroomMouseexited(MouseEvent mouseEvent) {
        BtnAnimationStop(addroombtn);
    }

    public void managereservationMouseexited(MouseEvent mouseEvent) {
        BtnAnimationStop(managereservationbtn);
    }

    public void notificationbtnonMouseexited(MouseEvent mouseEvent) {
        BtnAnimationStop(notificationbtn);
    }

    public void availablebtnonMouseexited(MouseEvent mouseEvent) {
        BtnAnimationStop(availablebtn);
    }

    public void settingbtnonMouse(MouseEvent mouseEvent) {
        BtnAnimationStop(seettingbtn);
    }


    /*Animation Start For Button*/
    private void BtnAnimatioStart(Button btn){
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), btn);
        scaleTransition.setToX(1.2);
        scaleTransition.setToY(1.2);
        scaleTransition.playFromStart();
    }

    /*Animation Stop For Button*/
    private void BtnAnimationStop(Button btn){
        ScaleTransition reverseScaleTransition = new ScaleTransition(Duration.millis(200), btn);
        reverseScaleTransition.setToX(1.0);
        reverseScaleTransition.setToY(1.0);
        reverseScaleTransition.playFromStart();
    }

    /*Information Pane Visible When Mouse Enterd*/
    private void VisiblePane(Pane pane) {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                    pane.setVisible(false);
                } catch (Exception e) {
                }
            }
        }.start();
    }

}
