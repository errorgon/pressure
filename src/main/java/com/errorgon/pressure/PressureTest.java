package com.errorgon.pressure;

import com.errorgon.pressure.enums.AtmosphericScalingBasis;
import com.errorgon.pressure.enums.PES;
import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.explosives.Explosive;
import com.errorgon.pressure.explosives.Tritonal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PressureTest {

    @Test
    public void atmosphereParameterTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
//        pressure.printOutputSection();
    }

    @Test
    public void tntEquivalentTest() {
        Pressure pressure = new Pressure(10, 100);
        Explosive explosive = new Tritonal();
        double res = explosive.getTNTImpulseEquivalent(PES.OPEN_STORAGE_STANDARD, 1);
        Assertions.assertEquals(0.96, res);
        res = explosive.getTNTImpulseEquivalent(PES.SHIP, 1);
        Assertions.assertEquals(1.0, res);
        res = explosive.getTNTPressureEquivalent(PES.OPEN_STORAGE_STANDARD, 1);
        Assertions.assertEquals(1.07, res);
        res = explosive.getTNTPressureEquivalent(PES.SHIP, 1);
        Assertions.assertEquals(1.0, res);
    }

    @Test
    public void incidentPressurePSITest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        double result = pressure.getIncidentPressure(true);
        Assertions.assertEquals(4.38984059, result, 1e-6);
        result = pressure.getIncidentPressure(false);
        Assertions.assertEquals(4.227475106, result, 1e-6);
    }

    @Test
    public void reflectedPressurePSITest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        double result = pressure.getReflectedPressure(true);
        Assertions.assertEquals(9.801187, result, 1e-6);
        result = pressure.getReflectedPressure(false);
        Assertions.assertEquals(9.43867367, result, 1e-6);
    }

    @Test
    public void getPositivePhaseDurationTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        double result = pressure.getPositivePhaseDuration(true);
        Assertions.assertEquals(14.5929066, result, 1e-6);
        result = pressure.getPositivePhaseDuration(false);
        Assertions.assertEquals(14.7773888, result, 1e-6);
    }

    @Test
    public void getPositivePhaseImpulseTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        double result = pressure.getPositivePhaseImpulse(true);
        Assertions.assertEquals(24.061020, result, 1e-6);
        result = pressure.getPositivePhaseImpulse(false);
        Assertions.assertEquals(23.46401058, result, 1e-6);
    }

    @Test
    public void getReflectedImpulseTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        double result = pressure.getReflectedImpulse(true);
        Assertions.assertEquals(48.738633, result, 1e-6);
        result = pressure.getReflectedImpulse(false);
        Assertions.assertEquals(47.529314, result, 1e-6);
    }

    @Test
    public void getDynamicOverpressureTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        double result = pressure.getDynamicOverpressure(true);
        Assertions.assertEquals(0.4502734, result, 1e-6);
        result = pressure.getDynamicOverpressure(false);
        Assertions.assertEquals(0.4336193, result, 1e-6);
    }

    @Test
    public void getDynamicImpulseTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        double result = pressure.getDynamicImpulse(true);
        Assertions.assertEquals(1.814153265, result, 1e-6);
        result = pressure.getDynamicImpulse(false);
        Assertions.assertEquals(1.7691399, result, 1e-6);
    }

    @Test
    public void getTimeOfArrivalTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        double result = pressure.getTimeOfArrival(true);
        Assertions.assertEquals(41.2457491  , result, 1e-6);
        result = pressure.getTimeOfArrival(false);
        Assertions.assertEquals(41.7671740, result, 1e-6);
    }

    @Test
    public void distanceAtIncidentPressureTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        double result = pressure.distanceAtIncidentPressure(10.0, true);
        Assertions.assertEquals(46.96487758308649, result, 1e-6);
        result = pressure.distanceAtIncidentPressure(10.0, false);
        Assertions.assertEquals(46.05746313929558, result, 1e-6);
    }

    @Test
    public void distanceAtReflectedPressureTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        double result = pressure.distanceAtReflectedPressure(10.0, true);
        Assertions.assertEquals(74.16050434112549, result, 1e-6);
        result = pressure.distanceAtReflectedPressure(10.0, false);
        Assertions.assertEquals(72.62337639927864, result, 1e-6);
    }

    @Test
    public void distanceAtPositivePhaseDurationTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        double result = pressure.distanceAtPositivePhaseDuration(20, true);
        Assertions.assertEquals(199.90147829055786, result, 1e-6);
        result = pressure.distanceAtPositivePhaseDuration(20, false);
        Assertions.assertEquals(191.74107670783997, result, 1e-6);
    }

    @Test
    public void distanceAtPositivePhaseImpulseTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        double result = pressure.distanceAtPositivePhaseImpulse(10.0, true);
        Assertions.assertEquals(188.4093788266182, result, 1e-6);
        result = pressure.distanceAtPositivePhaseImpulse(10.0, false);
        Assertions.assertEquals(183.5912275314331, result, 1e-6);
    }

    @Test
    public void distanceAtReflectedImpulseTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        double result = pressure.distanceAtReflectedImpulse(10.0, true);
        Assertions.assertEquals(336.61971539258957, result, 1e-6);
        result = pressure.distanceAtReflectedImpulse(10.0, false);
        Assertions.assertEquals(328.64198863506317, result, 1e-6);
    }

    @Test
    public void distanceAtDynamicOverpressureTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        double result = pressure.distanceAtDynamicOverpressure(10.0, true);
        Assertions.assertEquals(32.13211052119732, result, 1e-6);
        result = pressure.distanceAtDynamicOverpressure(10.0, false);
        Assertions.assertEquals(31.81903620250523, result, 1e-6);
    }

    @Test
    public void distanceAtTimeOfArrivalTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        double result = pressure.distanceAtTimeOfArrival(10.0, true);
        Assertions.assertEquals(31.4429859, result, 1e-6);
        result = pressure.distanceAtTimeOfArrival(10.0, false);
        Assertions.assertEquals(31.22566442, result, 1e-6);
    }

    @Test
    public void distanceAtDynamicImpulseTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        double result = pressure.distanceAtDynamicImpulse(20.0, true);
        Assertions.assertEquals(29.17469597, result, 1e-6);
        result = pressure.distanceAtDynamicImpulse(20.0, false);
        Assertions.assertEquals(28.901116028, result, 1e-6);
    }

    @Test
    public void getChanceOfEardrumRuptureTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        double result[] = pressure.getChanceOfEardrumRupture();
        Assertions.assertEquals(0.150788210, result[0], 1e-6);  // minor
        Assertions.assertEquals(0.022825407, result[1], 1e-6);  // moderate
        Assertions.assertEquals(0.000037349, result[2], 1e-6);  // major
    }

    @Test
    public void getChanceOfLungRuptureTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        double result = pressure.getChanceOfLungRupture();
    }


}