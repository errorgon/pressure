package com.errorgon.pressure.coefficients;

import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.exceptions.OutOfRangeException;

// Table 2-7
// Used for Positive Phase Impulse
public class IncidentImpulse {


    private static double A = 0;
    private static double B = 0;
    private static double C = 0;
    private static double D = 0;
    private static double E = 0;
    private static double F = 0;
    private static double G = 0;


    public static double [] getCoefficients(Units units, double scaledDistance) {
        if (units.equals(Units.ENGLISH)) {
            setEnglishCoefficients(scaledDistance);
        } else if (units.equals(Units.METRIC)) {
            setMetricCoefficients(scaledDistance);
        }

        return new double[]{A, B, C, D, E, F, G};
    }


    private IncidentImpulse() {

    }

    private static void setMetricCoefficients(double scaledDistance) {
        if (scaledDistance < 0.2) {
            throw new OutOfRangeException();
        } else if (scaledDistance < 0.96) {
            A = 5.52181;
            B = 1.11663;
            C = 0.59998;
            D = -0.29175;
            E = -0.08700;
            F = 0.00000;
            G = 0.00000;
        } else if (scaledDistance < 2.38) {
            A = 5.46527;
            B = -0.30760;
            C = -1.46449;
            D = 1.36232;
            E = -0.43200;
            F = 0.00000;
            G = 0.00000;
        } else if (scaledDistance < 33.7) {
            A = 5.27482;
            B = -0.46744;
            C = -0.25006;
            D = 0.05881;
            E = -0.00554;
            F = 0.00000;
            G = 0.00000;
        } else if (scaledDistance < 396.7) {
            A = 5.98260;
            B = -1.06200;
            C = 0.00000;
            D = 0.00000;
            E = 0.00000;
            F = 0.00000;
            G = 0.00000;
        } else {
            throw new OutOfRangeException();
        }

    }

    private static void setEnglishCoefficients(double scaledDistance) {
        if (scaledDistance < 0.5) {
            throw new OutOfRangeException();
        } else if (scaledDistance < 2.41) {
            A = -2.97500;
            B = -0.46600;
            C = 0.96300;
            D = 0.03000;
            E = -0.08700;
            F = 0.00000;
            G = 0.00000;
        } else if (scaledDistance < 6.0) {
            A = 0.91100;
            B = 7.26000;
            C = -7.45900;
            D = 2.96000;
            E = -0.43200;
            F = 0.00000;
            G = 0.00000;
        } else if (scaledDistance < 85) {
            A = 3.24840;
            B = 0.16330;
            C = -0.44160;
            D = 0.07930;
            E = -0.00554;
            F = 0.00000;
            G = 0.00000;
        } else if (scaledDistance < 1000) {
            A = 4.77020;
            B = -1.06200;
            C = 0.00000;
            D = 0.00000;
            E = 0.00000;
            F = 0.00000;
            G = 0.00000;
        } else {
            throw new OutOfRangeException();
        }
    }


}