package com.errorgon.pressure.coefficients;

import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.exceptions.OutOfRangeException;

// Table 2-4
public class TimeOfArrival {

    private static double A = 0;
    private static double B = 0;
    private static double C = 0;
    private static double D = 0;
    private static double E = 0;
    private static double F = 0;
    private static double G = 0;
    private static double H = 0;

    public static double [] getCoefficients(Units units, double scaledDistance) {
        if (units.equals(Units.ENGLISH)) {
            setEnglishCoefficients(scaledDistance);
        } else if (units.equals(Units.METRIC)) {
            setMetricCoefficients(scaledDistance);
        }

        return new double[]{A, B, C, D, E, F, G, H};
    }

    private TimeOfArrival() {

    }


    private static void setMetricCoefficients(double scaledDistance) {
        if (scaledDistance < 0.06) {
            throw new OutOfRangeException();
        } else if (scaledDistance < 1.5) {
            A = -0.76102;
            B = 1.80448;
            C = 0.13258;
            D = -0.04187;
            E = -0.03617;
            F = -0.00862;
            G = 0.0;
            H = 0.0;
        } else if (scaledDistance < 40) {
            A = -0.74020;
            B = 1.66382;
            C = 0.45234;
            D = -0.36834;
            E = 0.09271;
            F = -0.00816;
            G = 0.0;
            H = 0.0;
        } else {
            throw new OutOfRangeException();
        }
    }

    private static void setEnglishCoefficients(double scaledDistance) {
        if (scaledDistance < 0.2) {
            throw new OutOfRangeException();
        } else if (scaledDistance < 4.5) {
            A = -2.56710;
            B = 1.53480;
            C = 0.13130;
            D = 0.01825;
            E = 0.003656;
            F = -0.008615;
            G = 0.0;
            H = 0.0;
        } else if (scaledDistance < 100) {
            A = -1.79097;
            B = -0.44021;
            C = 2.01409;
            D = -0.78101;
            E = 0.13045;
            F = -0.0081529;
            G = 0.0;
            H = 0.0;
        }  else {
            throw new OutOfRangeException();
        }
    }


}
