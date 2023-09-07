package lk.ijse.HostalMangement.controller;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginfirstpageformController implements Initializable {
    public Button btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Thread() {
            public void run() {
                for (int i = 0; i<20;i++){
                    try {
                        Thread.sleep(500);
                        if(i==19){
                            Platform.runLater(() -> {
                                try {
                                    PageLauncher.LauncherPage("/view/loginpageform.fxml",btn);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }.start();
    }

}
