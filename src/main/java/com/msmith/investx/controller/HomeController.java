package com.msmith.investx.controller;

import com.msmith.investx.Start;
import com.msmith.investx.controller.utilities.FileUtility;
import com.msmith.investx.model.FundTracker;
import com.msmith.investx.model.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


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
    @FXML private LineChart<String, Number> progressChart;

    @FXML private Label deposits1;
    @FXML private Label lastDepositDate;
    @FXML private Button onTrackButton;
    @FXML private Button aheadTrack;
    @FXML private Button behindTrack;
    @FXML private TextField customAmount;
    @FXML private Label customEstimatedDate;

    private LocalDate estimatedDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        update();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    update();
                    System.out.print("UPDATE");
                    System.out.print(" | Quantity: " +String.format("%.2f", User.getInstance().getShares()));
                    System.out.print(" | Price: " + FundTracker.getInstance().getPrice());
                    System.out.println(" | Total: " +
                            String.format("%.2f",User.getInstance().getShares() * FundTracker.getInstance().getPrice()));
                });
            }
        }, 0, 5000);
    }

    private void update() {
        User.getInstance().updateInterest();
        User.getInstance().updateMonthlyAdditions();
        updateProgressChart();
        updateLabels();
    }

    private void updateLabels() {
        username.setText(User.getInstance().getUsername());
        target.setText("??" + String.format("%.2f", User.getInstance().getTarget()));
        targetDate.setText("" + User.getInstance().getTargetDate());
        interestRate.setText((User.getInstance().getInterestRate()) + "%");
        deposits.setText("??" + String.format("%.2f", User.getInstance().getDeposit()));
        total.setText("??" + String.format("%.2f", User.getInstance().getCurrent()));
        deposits1.setText("??" + String.format("%.2f", User.getInstance().getDeposit()));
        onTrackButton.setText("??" + String.format("%.2f", User.getInstance().getMonthlyAdditions()[0]));
        aheadTrack.setText("??" + String.format("%.2f", User.getInstance().getMonthlyAdditions()[1]));
        behindTrack.setText("??" + String.format("%.2f", User.getInstance().getMonthlyAdditions()[2]));
        lastDepositDate.setText("" + User.getInstance().getLastDepositDate());
        percentChange.setText("(" + String.format("%.2f", User.getInstance().getPercentInterest()) + "%)");
        interest.setText("??" + String.format("%.2f", User.getInstance().getInterest()));

        onEnterCustomAmount();

        if(User.getInstance().getInterest() < 0) {
            interest.setTextFill(Color.RED);
            percentChange.setTextFill(Color.RED);
        } else if(User.getInstance().getInterest() > 0) {
            interest.setTextFill(Color.GREEN);
            percentChange.setTextFill(Color.GREEN);
        } else {
            interest.setTextFill(Color.BLACK);
            percentChange.setTextFill(Color.BLACK);
        }
        FileUtility.updateUserFile();
    }

    private void updateProgressChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Portfolio Value");
        series.getData().add(new XYChart.Data<>("1", 20));
        series.getData().add(new XYChart.Data<>("2", 30));
        series.getData().add(new XYChart.Data<>("3", 40));
        series.getData().add(new XYChart.Data<>("4", 50));
        series.getData().add(new XYChart.Data<>("5", 60));
        progressChart.getData().add(series);
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

    public void onCustomInvestClick() {
        User.getInstance().setDeposit(User.getInstance().getDeposit() + Double.parseDouble(customAmount.getText()));
        User.getInstance().setShares(User.getInstance().getShares() +
                (Double.parseDouble(customAmount.getText())/ FundTracker.getInstance().getPrice()));
        User.getInstance().setTargetDate(estimatedDate);
        update();
    }

    public void onEnterCustomAmount() {
        if(Objects.equals(customAmount.getText(), "") || Objects.equals(customAmount.getText(), "0")) {
            customEstimatedDate.setText(String.valueOf(User.getInstance().getTargetDate()));
            return;
        }
        double addition = User.getInstance().getMonthlyAdditions()[0];
        double rates = (User.getInstance().getInterestRate()/100)/12;
        double target = User.getInstance().getTarget();
        double current = User.getInstance().getCurrent() + Double.parseDouble(customAmount.getText());
        long months = (long) (Math.log(((target * rates) + addition)/(addition + (rates * current)))/Math.log(1+rates));
        this.estimatedDate = LocalDate.now().plusMonths(months);
        customEstimatedDate.setText(String.valueOf(estimatedDate));
    }
}
