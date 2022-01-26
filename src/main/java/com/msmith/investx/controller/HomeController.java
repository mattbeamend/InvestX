package com.msmith.investx.controller;

import com.msmith.investx.Start;
import com.msmith.investx.controller.utilities.FileUtility;
import com.msmith.investx.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;


public class HomeController implements Initializable {

    @FXML private Label username;
    @FXML private Label target;
    @FXML private Label targetDate;
    @FXML private Label interestRate;
    @FXML private Label deposits;
    @FXML private Label interest;
    @FXML private Label total;
    @FXML private Label percentChange;
    // TODO: Implement line chart to follow user historical investment total
    //@FXML private LineChart<Number, Number> progressChart;

    @FXML private Label deposits1;
    @FXML private Label lastDepositDate;
    @FXML private Button onTrackButton;
    @FXML private Button aheadTrack;
    @FXML private Button behindTrack;
    @FXML private TextField customAmount;
    @FXML private Label customEstimatedDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User.getInstance().calculateInterest();
        User.getInstance().updateMonthlyAdditions();
        updateLabels();
    }

    // TODO: Use a timer to update current total and interest every 2 seconds
    private void updateLabels() {
        username.setText(User.getInstance().getUsername());
        target.setText("£" + String.format("%.2f", User.getInstance().getTarget()));
        targetDate.setText("" + User.getInstance().getTargetDate());
        interestRate.setText((User.getInstance().getInterestRate()) + "%");
        deposits.setText("£" + String.format("%.2f", User.getInstance().getDeposit()));
        interest.setText("£" + String.format("%.2f", User.getInstance().getInterest()));
        total.setText("£" + String.format("%.2f", User.getInstance().getCurrent()));
        percentChange.setText("(" + String.format("%.2f", User.getInstance().getPercentInterest()) + "%)");
        deposits1.setText("£" + String.format("%.2f", User.getInstance().getDeposit()));
        onTrackButton.setText("£" + String.format("%.2f", User.getInstance().getMonthlyAdditions()[0]));
        aheadTrack.setText("£" + String.format("%.2f", User.getInstance().getMonthlyAdditions()[1]));
        behindTrack.setText("£" + String.format("%.2f", User.getInstance().getMonthlyAdditions()[2]));
        lastDepositDate.setText("" + User.getInstance().getLastDepositDate());
        customEstimatedDate.setText(String.valueOf(User.getInstance().getTargetDate()));

        FileUtility.updateUserFile();
    }



    public void onSettingsClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("views/setting-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 500);
        Start.getContainer().setScene(scene);
        Start.getContainer().show();
    }

    public void onTrackClick() {
        User.getInstance().updateInvestmentFigures(1, 0);
        updateLabels();
    }

    public void onAheadTrackClick() {
        User.getInstance().updateInvestmentFigures(1, -12 );
        updateLabels();
    }

    public void onBehindTrackClick() {
        User.getInstance().updateInvestmentFigures(2, 12);
        updateLabels();
    }

    // TODO: Get custom invest button working, updating target date also
    public void onCustomInvestClick() {

    }

    // TODO: Finish the estimated date for custom investments
    public void onEnterCustomAmount() {
        if(Objects.equals(customAmount.getText(), "")) {
            customEstimatedDate.setText(String.valueOf(User.getInstance().getTargetDate()));
            return;
        }
        double addition = Double.parseDouble(customAmount.getText());
        double rates = (User.getInstance().getInterest()/100)/12;
        double target = User.getInstance().getTarget();
        double current = User.getInstance().getCurrent();
        double p1 = ((target * rates) + addition)/(addition + (rates * current));
        long months = (long) (Math.log(p1)/Math.log(1+rates));
        System.out.println(months);
        customEstimatedDate.setText(String.valueOf(User.getInstance().getTargetDate().plusMonths(months)));
    }
}
