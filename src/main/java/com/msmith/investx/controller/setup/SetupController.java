package com.msmith.investx.controller.setup;

import com.msmith.investx.Start;
import com.msmith.investx.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class SetupController {

    @FXML private TextField username;
    @FXML private TextField target;
    @FXML private DatePicker targetDate;
    @FXML private TextField interestRate;
    @FXML private TextField deposit;

    @FXML
    public void onCreateAccountClicked() throws Exception {
        addUserDetails();
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 500);
        Start.getContainer().setScene(scene);
        Start.getContainer().show();
    }

    private void addUserDetails() {
        User.getInstance().setUsername(username.getText());
        User.getInstance().setDeposit(Double.parseDouble(deposit.getText()));
        User.getInstance().setInterestRate(Double.parseDouble(interestRate.getText()));
        User.getInstance().setTarget(Double.parseDouble(target.getText()));
        User.getInstance().setTargetDate(targetDate.getValue());
        User.getInstance().setCurrent(Double.parseDouble(deposit.getText()));
    }

}