package com.errorgon.pressure.coefficients;

import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.exceptions.OutOfRangeException;

// Table 2-6
public class PositivatePhaseDuration {

    public double A = 0;
    public double B = 0;
    public double C = 0;
    public double D = 0;
    public double E = 0;
    public double F = 0;
    public double G = 0;

    public PositivatePhaseDuration(Units units, double scaledDistance) {
        if (units.equals(Units.ENGLISH)) {
            setEnglishCoefficients(scaledDistance);
        } else if (units.equals(Units.METRIC)) {
            setMetricCoefficients(scaledDistance);
        }
    }


    private void setMetricCoefficients(double scaledDistance) {
        if (scaledDistance < 0.2) {
            throw new OutOfRangeException();
        } else if (scaledDistance < 1.02) {
            A = 0.55208;
            B = 3.43680;
            C = -0.62621;
            D = -4.28609;
            E = -2.86846;
            F = -0.60800;
            G = 0.00000;
        } else if (scaledDistance < 2.80) {
            A = 0.55250;
            B = 2.32380;
            C = -7.36364;
            D = 8.77005;
            E = -4.24959;
            F = 0.88170;
            G = 0.00000;
        } else if (scaledDistance < 40) {
            A = -1.91410;
            B = 5.86454;
            C = -4.44451;
            D = 1.76090;
            E = -0.34340;
            F = 0.02624;
            G = 0.00000;
        } else if (scaledDistance < 198.5) {
            A = 0.66760;
            B = 0.46860;
            C = -0.02895;
            D = 0.00000;
            E = 0.00000;
            F = 0.00000;
            G = 0.00000;
        } else {
            throw new OutOfRangeException();
        }

    }

    private void setEnglishCoefficients(double scaledDistance) {
        if (scaledDistance < 0.5) {
            throw new OutOfRangeException();
        } else if (scaledDistance < 2.5) {
            A = -1.7221;
            B = 0.45;
            C = 1.3552;
            D = 1.1249;
            E = -0.05773;
            F = -0.608;
            G = 0.00000;
        } else if (scaledDistance < 7.0) {
            A = -18.7701;
            B = 55.0513;
            C = -60.4348;
            D = 32.0236;
            E = -8.3256;
            F = 0.8817;
            G = 0.00000;
        } else if (scaledDistance < 100) {
            A = -13.0597;
            B = 19.7806;
            C = -11.2975;
            D = 3.2552;
            E = -0.4647;
            F = 0.02624;
            G = 0.00000;
        } else if (scaledDistance < 500) {
            A = -0.044686;
            B = 0.51213;
            C = -0.02895;
            D = 0.00000;
            E = 0.00000;
            F = 0.00000;
            G = 0.00000;
        } else {
            throw new OutOfRangeException();
        }
    }


}


