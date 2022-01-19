package com.msmith.investx.model;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;

public class FundTracker {

    private static FundTracker instance;
    private Stock stock;

    public static FundTracker getInstance() {
        if(instance == null) {
            instance = new FundTracker();
        }
        return instance;
    }

    private FundTracker() {
        try {
            this.stock = YahooFinance.get("VOO");
        } catch (IOException ignored) {}
    }

    public double getPrice() {
        return stock.getQuote().getPrice().doubleValue();
    }


}
