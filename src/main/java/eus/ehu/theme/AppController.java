package eus.ehu.theme;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class AppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleGroup themes;
    private Application main;

    @FXML
    void onApplyBtn(ActionEvent event) {

        RadioButton selectedToggle = (RadioButton) themes.getSelectedToggle();

        main.ApplyTheme(selectedToggle.getText());
    }

    @FXML
    void initialize() {
        assert themes != null : "fx:id=\"themes\" was not injected: check your FXML file 'view.fxml'.";

    }

    public void setMain(Application application)
    {
        this.main = application;
    }

}
