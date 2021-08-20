package com.errorgon.pressure;

import com.errorgon.pressure.enums.PES;
import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.explosives.Explosive;
import com.errorgon.pressure.explosives.TNT;
import com.errorgon.pressure.explosives.Tritonal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PressureTest {

    @Test
    public void incidentOverpressurePSITest() {
        Pressure pressure = new Pressure(100, 100);
        double result = pressure.getIncidenPressure();
        Assertions.assertEquals(2.6912489, result, 1e-6);
    }

    @Test
    public void reflectedOverpressurePSITest() {
        Pressure pressure = new Pressure(200, 100);
        double result = pressure.getReflectedPressure();
        Assertions.assertEquals(8.35734, result, 1e-2);
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

}