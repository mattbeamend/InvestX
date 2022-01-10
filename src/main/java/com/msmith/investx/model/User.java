package com.msmith.investx.model;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class User implements Serializable {

    private static User instance;
    private String username;
    private LocalDate targetDate;
    private LocalDate startDate;

    private double interestRate;
    private double target;
    private double deposit;
    private double interest;
    private double current;

    private double[] monthlyAdditions;

    private User() {}

    public static User getInstance() {
        if(instance == null)
            instance = new User();
        return instance;
    }

    public void initialize() {
        calculateInterestEarned();
        calculateSuggestedInvestmentValues();
    }

    private void calculateInterestEarned() {
        LocalDate currentDate = LocalDate.now();
        double daysBetween = ChronoUnit.DAYS.between(startDate, currentDate);
        current = deposit *  Math.pow((1 + ((interestRate/100)/365)), daysBetween);
        interest = current - deposit;
    }

    public void calculateSuggestedInvestmentValues() {
        double t;
        double r = (interestRate/100)/12;
        monthlyAdditions = new double[3];

        t = ChronoUnit.MONTHS.between(LocalDate.now(), targetDate);
        this.monthlyAdditions[0] = ((target * r) - (r * deposit * Math.pow(1+r, t)))/(Math.pow(1+r, t)-1);
        t = ChronoUnit.MONTHS.between(LocalDate.now(), targetDate.minusMonths(12));
        this.monthlyAdditions[1] = ((target * r) - (r * deposit * Math.pow(1+r, t)))/(Math.pow(1+r, t)-1);
        t = ChronoUnit.MONTHS.between(LocalDate.now(), targetDate.plusMonths(12));
        this.monthlyAdditions[2] = ((target * r) - (r * deposit * Math.pow(1+r, t)))/(Math.pow(1+r, t)-1);
    }

    public double getTarget() {
        return target;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public double[] getMonthlyAdditions() {
        return monthlyAdditions;
    }
}
