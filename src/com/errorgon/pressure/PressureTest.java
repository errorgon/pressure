package com.errorgon.pressure;

import com.errorgon.pressure.enums.PES;
import com.errorgon.pressure.enums.Units;
import com.errorgon.pressure.explosives.Explosive;
import com.errorgon.pressure.explosives.TNT;
import com.errorgon.pressure.explosives.Tritonal;
import org.junit.jupiter.api.Test;

class PressureTest {

    @Test
    public void incidentOverpressureTest() {
        Pressure pressure = new Pressure(10, 100);
        Explosive explosive = new TNT();
        System.out.println(explosive.getTNTImpulseEquivalent(PES.OPEN_STORAGE_STANDARD, 1));
        System.out.println(explosive.getTNTImpulseEquivalent(PES.SHIP, 1));

    }

}