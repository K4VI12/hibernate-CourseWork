package lk.ijse.HostalMangement.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class LoginpageformController {
    public Button passwordvisiblebtn;
    public Button passwordhidebtn;
    public JFXTextField usernametxt;
    public JFXPasswordField passwordhidetxt;
    public JFXTextField passwordshowtxt;
    public Button passwordcofirmbtn;

    public void passwordvisiblebtnonAction(ActionEvent actionEvent) {
        passwordvisiblebtn.setVisible(false);
        passwordhidebtn.setVisible(true);

        passwordhidetxt.setVisible(false);
        passwordshowtxt.setVisible(true);

        passwordshowtxt.setText(passwordhidetxt.getText());
    }

    public void passwordhidebtnonAction(ActionEvent actionEvent) {
        passwordhidebtn.setVisible(false);
        passwordvisiblebtn.setVisible(true);

        passwordshowtxt.setVisible(false);
        passwordhidetxt.setVisible(true);

        passwordhidetxt.setText(passwordshowtxt.getText());
    }

    public void passwordconfirmbtnonAction(ActionEvent actionEvent) {
        PageLauncher.LauncherPage("/view/homepageform.fxml",passwordcofirmbtn);
    }
}
