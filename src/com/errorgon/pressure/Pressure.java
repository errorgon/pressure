package com.errorgon.pressure;

import com.errorgon.pressure.coefficients.*;
import com.errorgon.pressure.enums.AtmosphericScalingBasis;
import com.errorgon.pressure.enums.MunitionType;
import com.errorgon.pressure.enums.PES;
import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.equations.StandardEquation;
import com.errorgon.pressure.explosives.Explosive;
import com.errorgon.pressure.explosives.TNT;

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


    void printOutputSection() {
        System.out.println("Tnt Eq - Pressure: " + explosive.getTNTPressureEquivalent(pes, netExplosiveWeight));
        System.out.println("Tnt Eq - Impulse: " + explosive.getTNTImpulseEquivalent(pes, netExplosiveWeight));
        System.out.println("Hemi Weight Pressure: " + getHemiWeightPressure());
        System.out.println("Hemi Weight Impulse: " + getHemiWeightImpulse());

        System.out.println("atmoPressure: " + atmoPressure);
        System.out.println("atmoPressureFactor: " + atmoPressureFactor);
        System.out.println("atmoDistanceFactor: " + atmoDistanceFactor);
        System.out.println("atmoTimeFactor: " + atmoTimeFactor);
        System.out.println("atmoImpulseFactor: " + atmoImpulseFactor);
    }


    /***** Coefficient Methods *****/
    public double getIncidentPressure(boolean atSeaLevel) {
        double pressure = StandardEquation.solve(IncidentPressure.getCoefficients(units, scaledPressureDistanceAtSeaLevel), scaledPressureDistanceAtSeaLevel);
        if (atSeaLevel) return pressure;
        else return pressure * atmoPressureFactor;
    }

    public double getReflectedPressure(boolean atSeaLevel) {
        double pressure = StandardEquation.solve(ReflectedPressure.getCoefficients(units, scaledPressureDistanceAtSeaLevel), scaledPressureDistanceAtSeaLevel);
        if (atSeaLevel) return pressure;
        return pressure * atmoPressureFactor;
    }

    public double getPositivePhaseDuration() {
        return Math.pow(explosive.getTNTImpulseEquivalent(pes, netExplosiveWeight ), (1.0 / 3.0)) * StandardEquation.solve(PositivePhaseDuration.getCoefficients(units, scaledDistance), scaledDistance);
    }

    public double getPositivePhaseImpulse() {
        return  Math.pow(explosive.getTNTImpulseEquivalent(pes, netExplosiveWeight ), (1.0 / 3.0)) * StandardEquation.solve(IncidentImpulse.getCoefficients(units, scaledDistance), scaledDistance);
    }

    public double getReflectedImpulse() {
        return  Math.pow(explosive.getTNTImpulseEquivalent(pes, netExplosiveWeight), (1.0 / 3.0)) * StandardEquation.solve(ReflectedImpulse.getCoefficients(units, scaledDistance), scaledDistance);
    }

    public double getDynamicOverpressure() {
        return StandardEquation.solve(DynamicOverpressure.getCoefficients(units, scaledPressureDistanceAtSeaLevel), scaledPressureDistanceAtSeaLevel);
    }

    public double getDynamicImpulse() {
        return Math.pow(explosive.getTNTImpulseEquivalent(pes, netExplosiveWeight), (1.0 / 3.0)) * StandardEquation.solve(DynamicImpulse.getCoefficients(units, scaledImpulseDistanceAtSeaLevel), scaledImpulseDistanceAtSeaLevel);
    }

}
