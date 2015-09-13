package com.mcgoodtime.gtgames;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by suhao on 2015.9.10.0010.
 *
 * @author suhao
 */
public class MathUtilities {

    /**
     *
     * @param scale accuracy
     * @return percentage
     */
    public static double getPercentage(double num, double total, int scale) {
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        df.setMaximumFractionDigits(scale);
        df.setRoundingMode(RoundingMode.HALF_UP);
        return num / total * 100;
    }

    /**
     *
     * @param scale accuracy
     * @return percentage
     */
    public static double getPercentageFormLong(long num, long total, int scale) {
        return getPercentage(num, total, scale);
    }
}
