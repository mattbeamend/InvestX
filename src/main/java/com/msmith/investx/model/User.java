package com.msmith.investx.model;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private static User instance;

    private String username;
    private LocalDate targetDate;
    private LocalDate startDate;
    private LocalDate currentDate;
    private LocalDate lastDepositDate;

    private double interestRate;
    private double target;
    private double shares;
    private double deposit;
    private double interest;
    private double current;
    private double[] monthlyAdditions;

    public static User getInstance() {
        if(instance == null)
            instance = new User();
        return instance;
    }

    public void updateInterest() {
        //this.currentDate = LocalDate.of(2032, 1,11);
        this.currentDate = LocalDate.now();
        this.current = shares * FundTracker.getInstance().getPrice();
        this.interest = current - deposit;
    }

    public void updateInvestmentFigures(int btn, int monthChange) {
        this.deposit += monthlyAdditions[btn];
        this.shares += (monthlyAdditions[btn] / FundTracker.getInstance().getPrice());
        this.targetDate = targetDate.plusMonths(monthChange);
        this.lastDepositDate = LocalDate.now();
        updateInterest();
        updateMonthlyAdditions();
    }

    public void updateMonthlyAdditions() {
        this.monthlyAdditions = new double[3];
        this.monthlyAdditions[0] = calculateMonthlyAddition(targetDate);
        this.monthlyAdditions[1] = calculateMonthlyAddition(targetDate.minusMonths(12));
        this.monthlyAdditions[2] = calculateMonthlyAddition(targetDate.plusMonths(12));
    }

    private double calculateMonthlyAddition(LocalDate endDate) {
        double time = ChronoUnit.MONTHS.between(currentDate, endDate);
        double r = (interestRate/100)/12;
        return ((target * r) - (r * current * Math.pow(1+r, time)))/(Math.pow(1+r, time)-1);
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

    public void setMonthlyAdditions(double[] monthlyAdditions) {
        this.monthlyAdditions = monthlyAdditions;
    }

    public double getPercentInterest() {
        return (interest/deposit) * 100;
    }

    public void setShares(double shares) {
        this.shares = shares;
    }

    public double getShares() {
        return shares;
    }

    public LocalDate getLastDepositDate() {
        return lastDepositDate;
    }

    public void setLastDepositDate(LocalDate lastDepositDate) {
        this.lastDepositDate = lastDepositDate;
    }
}
