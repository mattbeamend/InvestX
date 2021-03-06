package com.msmith.investx.controller.utilities;

import com.msmith.investx.model.User;

import java.io.*;

public class FileUtility {

    // TODO: Pull user account information from database, update user object values (used on Login)

    public static void updateUserObject() {
        try {
            FileInputStream file = new FileInputStream(
                    "src/main/resources/com/msmith/investx/files/user-information.ser");
            ObjectInputStream stream = new ObjectInputStream(file);
            Object fileData = stream.readObject();

            User.getInstance().setUsername(((User) fileData).getUsername());
            User.getInstance().setTargetDate(((User) fileData).getTargetDate());
            User.getInstance().setStartDate(((User) fileData).getStartDate());
            User.getInstance().setInterestRate(((User) fileData).getInterestRate());
            User.getInstance().setTarget(((User) fileData).getTarget());
            User.getInstance().setDeposit(((User) fileData).getDeposit());
            User.getInstance().setInterest(((User) fileData).getInterest());
            User.getInstance().setCurrent(((User) fileData).getCurrent());
            User.getInstance().setShares(((User) fileData).getShares());
            User.getInstance().setMonthlyAdditions(((User) fileData).getMonthlyAdditions());
            User.getInstance().setLastDepositDate(((User) fileData).getLastDepositDate());

            stream.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // TODO: Update the database with new account information (used on Sign Up
    public static void updateUserFile() {
        try {
            FileOutputStream file = new FileOutputStream(
                    "src/main/resources/com/msmith/investx/files/user-information.ser");
            ObjectOutputStream stream = new ObjectOutputStream(file);
            stream.writeObject(User.getInstance());
            stream.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
