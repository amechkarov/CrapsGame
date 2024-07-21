package com.gangames.crapsgame.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Formatters {

    public static double roundToTwoDecimalPlaces(double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(value));
    }

    public static boolean hasMoreDecimalPlaces(double value, int expectedDecimalPlaces) {
        BigDecimal bd = BigDecimal.valueOf(value);

        int scale = bd.scale();

        return scale > expectedDecimalPlaces;
    }
}
