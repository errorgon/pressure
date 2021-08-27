package com.errorgon.pressure.coefficients;

import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.exceptions.OutOfRangeException;


// Table 2-5
// Used to calculate Reflected Pressure
public class ReflectedPressure {

    private static double A = 0;
    private static double B = 0;
    private static double C = 0;
    private static double D = 0;
    private static double E = 0;
    private static double F = 0;
    private static double G = 0;
    private static double H = 0;

    public static double []  getCoefficients(Units units, double scaledDistance) {
        if (units.equals(Units.ENGLISH)) {
            setEnglishCoefficients(scaledDistance);
        } else if (units.equals(Units.METRIC)) {
            setMetricCoefficients(scaledDistance);
        }

        return new double[]{A, B, C, D, E, F, G, H};
    }

    private ReflectedPressure() {

    }


    private static void setMetricCoefficients(double scaledDistance) {
        if (scaledDistance < 0.06) {
            throw new OutOfRangeException();
        } else if (scaledDistance < 2.0) {
            A = 9.00178;
            B = -2.68104;
            C = -0.54254;
            D = 0.14121;
            E = 0.15765;
            F = 0.00414;
            G = -0.01180;
            H = 0.00000;
        } else if (scaledDistance < 40) {
            A = 8.94402;
            B = -2.14524;
            C = -2.05822;
            D = 1.89885;
            E = -0.68439;
            F = 0.11795;
            G = -0.01180;
            H = 0.00000;
        } else {
            throw new OutOfRangeException();
        }
    }

    private static void setEnglishCoefficients(double scaledDistance) {
        if (scaledDistance < 0.3) {
            throw new OutOfRangeException();
        } else if (scaledDistance < 4.0) {
            A = 9.07950;
            B = -1.75110;
            C = -0.28770;
            D = -0.21990;
            E = -0.01280;
            F = 0.06960;
            G = -0.01180;
            H = 0.00000;
        } else if (scaledDistance < 100) {
            A = 5.15150;
            B = 9.15826;
            C = -11.85735;
            D = 5.56754;
            E = -1.33455;
            F = 0.16333;
            G = -0.008181;
            H = 0.00000;
        } else {
            throw new OutOfRangeException();
        }
    }


}

