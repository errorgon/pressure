package com.errorgon.pressure;

import com.errorgon.pressure.enums.AtmosphericScalingBasis;
import com.errorgon.pressure.enums.PES;
import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.explosives.Explosive;
import com.errorgon.pressure.explosives.TNT;
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
        Assertions.assertEquals(14.59291, pressure.getPositivePhaseDuration(), 1e-2);
    }

    @Test
    public void getPositivePhaseImpulseTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        Assertions.assertEquals(24.0610, pressure.getPositivePhaseImpulse(), 1e-1);
    }

    @Test
    public void getReflectedImpulseTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        Assertions.assertEquals(48.738633, pressure.getReflectedImpulse(), 1e-1);
    }

    @Test
    public void getDynamicOverpressureTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        Assertions.assertEquals(0.45027, pressure.getDynamicOverpressure(), 1e-3);
    }

    @Test
    public void getDynamicImpulseTest() {
        Pressure pressure = new Pressure(Units.ENGLISH, new Tritonal(), 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
        Assertions.assertEquals(1.814153265, pressure.getDynamicImpulse(), 1e-8);
    }



}