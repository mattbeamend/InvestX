package com.msmith.investx.model;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;

public class FundTracker {

    private static FundTracker instance;

    public static FundTracker getInstance() {
        if(instance == null) {
            instance = new FundTracker();
        }
        return instance;
    }

    public double getPrice() {
        try {
            return YahooFinance.get("VOO").getQuote().getPrice().doubleValue();
        } catch (IOException ignored) {}
        return 0;
    }

}
