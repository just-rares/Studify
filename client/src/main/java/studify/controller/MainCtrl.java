package studify.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainCtrl {
    @FXML
    Button plusButton;
    @FXML
    Button minusButton;
    @FXML
    Label myLabel;

    private int clicks = 0;

    public void plusButton(ActionEvent event) {
        myLabel.setText(String.valueOf(++clicks));
    }
    public void minusButton(ActionEvent event) {
        myLabel.setText(String.valueOf(--clicks));
    }
}