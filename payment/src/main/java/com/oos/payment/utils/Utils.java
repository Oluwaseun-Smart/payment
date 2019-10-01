package com.oos.payment.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Utils {

    public static String getTransactionId() {

        String SALTCHARS = "abcdefghjklmnopqrstuvwxyz123456789";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 4) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }


    public static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate);
    }
}
