package com.errorgon.pressure;

import com.errorgon.pressure.coefficients.*;
import com.errorgon.pressure.enums.AtmosphericScalingBasis;
import com.errorgon.pressure.enums.MunitionType;
import com.errorgon.pressure.enums.PES;
import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.equations.StandardEquation;
import com.errorgon.pressure.exceptions.OutOfRangeException;
import com.errorgon.pressure.exceptions.OutOfRangeHighException;
import com.errorgon.pressure.exceptions.OutOfRangeLowException;
import com.errorgon.pressure.exceptions.PressureNotFoundException;
import com.errorgon.pressure.explosives.Explosive;
import com.errorgon.pressure.explosives.TNT;
import org.apache.commons.math3.distribution.NormalDistribution;

public class Pressure {

    // Inputs
    PES pes;
    MunitionType munitionType;
    Explosive explosive;
    Units units;
    double netExplosiveWeight = 0;
    double distance;
    double temperature;
    double atmosphere;
    AtmosphericScalingBasis asb;

    // Calculated Atmosphere Parameters
    double atmoPressure = 0; // psi or
    double atmoPressureFactor = 0;
    double atmoDistanceFactor = 0;
    double atmoTimeFactor = 0;
    double atmoImpulseFactor = 0;

    // Calculated Variables
    double temperatureKelvin;
    double scaledDistance;
    double distanceAtSeaLevel;
    double scaledPressureDistanceAtSeaLevel;
    double scaledImpulseDistanceAtSeaLevel;

    // For goal seeking
    double error = 0.00000001;
    int runLimit = 100;

    enum GoalSeek {
        INCIDENT, REFLECTED_PRESSURE, POSTIVE_DURATION, POSTIVE_IMPULSE, REFLECTED_IMPULSE, DYNAMIC_OVERPRESSURE, DYNAMIC_IMPULSE, TIME_OF_ARRIVAL;
    }


    public Pressure(Explosive explosive) {
        this(Units.ENGLISH, explosive);
    }

    public Pressure(Units units, Explosive explosive) {
        this(units, explosive, 1.0);
    }

    public Pressure(Units units, Explosive explosive, double netExplosiveWeight) {
        this(units, explosive, 1.0, 1.0);
    }

    public Pressure(double netExplosiveWeight, double distance) {
        this(Units.ENGLISH, new TNT(), netExplosiveWeight, distance);
    }

    public Pressure(Units units, Explosive explosive, double netExplosiveWeight, double distance) {
        this(units, explosive, netExplosiveWeight, distance, PES.OPEN_STORAGE_STANDARD, 59.0, AtmosphericScalingBasis.ALTITUDE, 0.0);
    }

    public Pressure(Units units, Explosive explosive, double netExplosiveWeight, double distance, PES pes, double temperature, AtmosphericScalingBasis asb, double atmosphere) {
        this.units = units;
        this.explosive = explosive;
        this.netExplosiveWeight = netExplosiveWeight;
        this.distance = distance;
        this.pes = pes;
        setScaledDistance(distance);
        this.temperature = temperature;
        this.asb = asb;
        this.atmosphere = atmosphere;
        setAltitudeParameters(asb, atmosphere);
        setExplosiveParameters();

    }

    /***** Setters and Getter *****/
    public void setExplosive(Explosive explosive) {
        this.explosive = explosive;
    }

    public Explosive getExplosive() {
        return explosive;
    }

    /***** Helper Methods *****/
    // Equation 2-3
    // Z = R/W^(1/3)
    private void setScaledDistance(double distance) {
        this.scaledDistance = (distance / Math.pow(netExplosiveWeight, (1.0 / 3.0)));
    }

    private double getHemiWeightPressure() {

        return 0.0;
    }

    private double getHemiWeightImpulse() {

        return 0.0;
    }

    private void setAltitudeParameters(AtmosphericScalingBasis asb, double value) {
        double ATMO = 14.696;

        if (asb.equals(AtmosphericScalingBasis.ALTITUDE)) {
            atmoPressure = 14.696 * Math.exp(-0.000037688 * value);
        } else if (asb.equals(AtmosphericScalingBasis.BAROMETRIC_PRESSURE)) {
            atmoPressure = value;
        }

        if (units.equals(Units.ENGLISH)) {
            temperature = (temperature - 32) * (5.0 / 9.0);
            temperatureKelvin = temperature + 273.16;
        } else if (units.equals(Units.METRIC)) {
            temperatureKelvin = temperature + 273.16;
        }

        atmoPressureFactor = atmoPressure / ATMO;
        atmoDistanceFactor = Math.pow((ATMO / atmoPressure), (1.0 / 3.0));
        atmoTimeFactor = atmoDistanceFactor * Math.pow((288.16 / temperatureKelvin), 0.5);
        atmoImpulseFactor = Math.pow((atmoPressure / ATMO),(2.0 / 3.0))  * Math.pow((288.16 / temperatureKelvin), 0.5);
    }

    private void setExplosiveParameters() {
        distanceAtSeaLevel = distance / atmoDistanceFactor;
        scaledPressureDistanceAtSeaLevel = distanceAtSeaLevel / Math.pow(explosive.getTNTPressureEquivalent(pes, netExplosiveWeight), (1.0 / 3.0));
        scaledImpulseDistanceAtSeaLevel = distanceAtSeaLevel / Math.pow(explosive.getTNTImpulseEquivalent(pes, netExplosiveWeight), (1.0 / 3.0));
    }

    /***** Coefficient Methods *****/
    public double getIncidentPressure(boolean atSeaLevel) throws OutOfRangeException {
        double pressure = StandardEquation.solve(IncidentPressure.getCoefficients(units, scaledPressureDistanceAtSeaLevel), scaledPressureDistanceAtSeaLevel);
        if (atSeaLevel) return pressure;
        return pressure * atmoPressureFactor;
    }

    public double getReflectedPressure(boolean atSeaLevel) {
        double pressure = StandardEquation.solve(ReflectedPressure.getCoefficients(units, scaledPressureDistanceAtSeaLevel), scaledPressureDistanceAtSeaLevel);
        if (atSeaLevel) return pressure;
        return pressure * atmoPressureFactor;
    }

    public double getPositivePhaseDuration(boolean atSeaLevel) {
        double pressure = Math.pow(explosive.getTNTImpulseEquivalent(pes, netExplosiveWeight ), (1.0 / 3.0)) * StandardEquation.solve(PositivePhaseDuration.getCoefficients(units, scaledImpulseDistanceAtSeaLevel), scaledImpulseDistanceAtSeaLevel);
        if (atSeaLevel) return pressure;
        return pressure * atmoTimeFactor;
    }

    public double getPositivePhaseImpulse(boolean atSeaLevel) {
        double pressure = Math.pow(explosive.getTNTImpulseEquivalent(pes, netExplosiveWeight ), (1.0 / 3.0)) * StandardEquation.solve(IncidentImpulse.getCoefficients(units, scaledImpulseDistanceAtSeaLevel), scaledImpulseDistanceAtSeaLevel);
        if (atSeaLevel) return pressure;
        return pressure * atmoImpulseFactor;
    }

    public double getReflectedImpulse(boolean atSeaLevel) {
        double pressure = Math.pow(explosive.getTNTImpulseEquivalent(pes, netExplosiveWeight), (1.0 / 3.0)) * StandardEquation.solve(ReflectedImpulse.getCoefficients(units, scaledImpulseDistanceAtSeaLevel), scaledImpulseDistanceAtSeaLevel);
        if (atSeaLevel) return pressure;
        return pressure * atmoImpulseFactor;
    }

    public double getDynamicOverpressure(boolean atSeaLevel) {
        double pressure = StandardEquation.solve(DynamicOverpressure.getCoefficients(units, scaledPressureDistanceAtSeaLevel), scaledPressureDistanceAtSeaLevel);
        if (atSeaLevel) return pressure;
        return pressure * atmoPressureFactor;
    }

    public double getTimeOfArrival(boolean atSeaLevel) {
        double pressure = Math.pow(explosive.getTNTPressureEquivalent(pes, netExplosiveWeight), (1.0 / 3.0)) * StandardEquation.solve(TimeOfArrival.getCoefficients(units, scaledPressureDistanceAtSeaLevel), scaledPressureDistanceAtSeaLevel);
        if (atSeaLevel) return pressure;
        return pressure * atmoTimeFactor;
    }

    public double getDynamicImpulse(boolean atSeaLevel) {
        double pressure = Math.pow(explosive.getTNTImpulseEquivalent(pes, netExplosiveWeight), (1.0 / 3.0)) * StandardEquation.solve(DynamicImpulse.getCoefficients(units, scaledImpulseDistanceAtSeaLevel), scaledImpulseDistanceAtSeaLevel);
        if (atSeaLevel) return pressure;
        return pressure * atmoImpulseFactor;
    }

    /***** Eardrum Rupture Methods *****/
    public double[] getChanceOfEardrumRupture() {
        NormalDistribution nd = new NormalDistribution();

        double pressure = StandardEquation.solve(EardrumRupture.getCoefficients(units, scaledPressureDistanceAtSeaLevel), getIncidentPressure(true));
        double minor = nd.cumulativeProbability(((13.051 - 3.3 * Math.log(pressure)) - 5));
        double moderate = nd.cumulativeProbability(((12.636 - 3.5 * Math.log(pressure)) - 5));
        double major =nd.cumulativeProbability((12.876 - 4.3 * Math.log(pressure)) - 5);


        return new double[]{minor, moderate, major};

    }




    /***** Goalseek Methods *****/
    public double distanceAtIncidentPressure(double target, boolean atSeaLevel) {
        return goalSeek(GoalSeek.INCIDENT, target, atSeaLevel);
    }

    public double distanceAtReflectedPressure(double target, boolean atSeaLevel) {
        return goalSeek(GoalSeek.REFLECTED_PRESSURE, target, atSeaLevel);
    }

    public double distanceAtPositivePhaseDuration(double target, boolean atSeaLevel) {
        return goalSeek(GoalSeek.POSTIVE_DURATION, target, atSeaLevel);
    }

    public double distanceAtPositivePhaseImpulse(double target, boolean atSeaLevel) {
        return goalSeek(GoalSeek.POSTIVE_IMPULSE, target, atSeaLevel);
    }

    public double distanceAtReflectedImpulse(double target, boolean atSeaLevel) {
        return goalSeek(GoalSeek.REFLECTED_IMPULSE, target, atSeaLevel);
    }

    public double distanceAtTimeOfArrival(double target, boolean atSeaLevel) {
        return goalSeek(GoalSeek.TIME_OF_ARRIVAL, target, atSeaLevel);
    }

    public double distanceAtDynamicOverpressure(double target, boolean atSeaLevel) {
        return goalSeek(GoalSeek.DYNAMIC_OVERPRESSURE, target, atSeaLevel);
    }

    public double distanceAtDynamicImpulse(double target, boolean atSeaLevel) {
        return goalSeek(GoalSeek.DYNAMIC_IMPULSE, target, atSeaLevel);
    }

    private double goalSeek(GoalSeek goalSeek, double target, boolean atSeaLevel) {
        int direction;
        int lastDirection = 0;
        double stepsize = 10.0;
        double estimate = 0.0;
        double keep = distance;
        distance = 100.0;
        int run = 0;

        while (Math.abs(target - estimate) > error) {
            distanceAtSeaLevel = distance / atmoDistanceFactor;
            scaledPressureDistanceAtSeaLevel = distanceAtSeaLevel / Math.pow(explosive.getTNTPressureEquivalent(pes, netExplosiveWeight), (1.0 / 3.0));
            scaledImpulseDistanceAtSeaLevel = distanceAtSeaLevel / Math.pow(explosive.getTNTImpulseEquivalent(pes, netExplosiveWeight), (1.0 / 3.0));

            switch (goalSeek) {
                case DYNAMIC_IMPULSE:
                    estimate = getDynamicImpulse(atSeaLevel);
                    break;
                case INCIDENT:
                    estimate = getIncidentPressure(atSeaLevel);
                    break;
                case REFLECTED_PRESSURE:
                    estimate = getReflectedPressure(atSeaLevel);
                    break;
                case POSTIVE_DURATION:
                    estimate = getPositivePhaseDuration(atSeaLevel);
                    break;
                case POSTIVE_IMPULSE:
                    estimate = getPositivePhaseImpulse(atSeaLevel);
                    break;
                case REFLECTED_IMPULSE:
                    estimate = getReflectedImpulse(atSeaLevel);
                    break;
                case DYNAMIC_OVERPRESSURE:
                    estimate = getDynamicOverpressure(atSeaLevel);
                    break;
                case TIME_OF_ARRIVAL:
                    estimate = getTimeOfArrival(atSeaLevel);
                    break;
            }

            if (goalSeek.equals(GoalSeek.TIME_OF_ARRIVAL) || goalSeek.equals(GoalSeek.POSTIVE_DURATION)) {
                if (estimate < target) {
                    distance += stepsize;
                    direction = 1;
                } else {
                    distance -= stepsize;
                    direction = -1;
                }
            } else {
                if (estimate > target) {
                    distance += stepsize;
                    direction = 1;
                } else {
                    distance -= stepsize;
                    direction = -1;
                }
            }

            if (direction != lastDirection) {
                stepsize /= 2.0;
            }
            lastDirection = direction;
            run++;
            if (run == runLimit) throw new PressureNotFoundException();
        }
        double solution = distance;

        // Reset the original distance parameter
        distance = keep;

        // Reset the explosive parameters to the original object fields.
        setExplosiveParameters();

        return solution;
    }



}
