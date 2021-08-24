package com.errorgon.pressure.coefficients;

import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.exceptions.OutOfRangeException;


// Table 2-9
// Used to calculate Dynamic Overpressure
public class DynamicOverpressure {

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

    private DynamicOverpressure() {

    }


    private static void setMetricCoefficients(double scaledDistance) {
        if (scaledDistance < 0.2) {
            throw new OutOfRangeException();
        } else if (scaledDistance < 59.5) {
            A = 7.72690;
            B = -3.07930;
            C = -0.71010;
            D = 0.12830;
            E = 0.09270;
            F = -0.02099;
            G = 0.00000;
            H = 0.00000;
        } else {
            throw new OutOfRangeException();
        }
    }

    private static void setEnglishCoefficients(double scaledDistance) {
        if (scaledDistance < 0.5) {
            throw new OutOfRangeException();
        } else if (scaledDistance < 150) {
            A = 8.01662;
            B = -1.80686;
            C = -0.424622;
            D = -0.393993;
            E = 0.189752;
            F = -0.0209928;
            G = 0.00000;
            H = 0.00000;
        } else {
            throw new OutOfRangeException();
        }
    }


}

