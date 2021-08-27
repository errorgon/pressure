package com.errorgon.pressure.coefficients;

import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.exceptions.OutOfRangeException;

public class EardrumRupture {

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

    private EardrumRupture() {
    }


    private static void setMetricCoefficients(double scaledDistance) {
        if (scaledDistance < 0.0) {
            throw new OutOfRangeException();
        } else if (scaledDistance < 10000) {
            A = 4.2258;
            B = -0.44829;
            C = -0.23285;
            D = 0.067411;
            E = -0.007007;
            F = 0.00024553;
            G = 0.00000;
            H = 0.00000;
        } else {
            throw new OutOfRangeException();
        }

    }

    private static void setEnglishCoefficients(double scaledDistance) {
        if (scaledDistance < 0.0) {
            throw new OutOfRangeException();
        } else if (scaledDistance < 10000) {
            A = 3.8112;
            B = -0.77824;
            C = 0.018551;
            D = 0.022446;
            E = -0.0046371;
            F = 0.00024553;
            G = 0.00000;
            H = 0.00000;
        } else {
            throw new OutOfRangeException();
        }
    }


}



