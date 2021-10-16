# Read me

[![](https://jitpack.io/v/errorgon/pressure.svg)](https://jitpack.io/#errorgon/pressure)

Library for Java implementation of the Blast Effects Computer - Open (BEC-O) by the DDESB


For more information, click [here](https://denix.osd.mil/ddes/ddes-technical-papers/ddes-technical-papers/tp-20-ddesb-blast-effects-computer-open-bec-o-version-1-users-manual-and-documentation-11-june-2018/) for TP-20 Blast Effects Computer - Open (BEC-O) User Manual.

Click [here](https://www.denix.osd.mil/ddes/ddes-technical-papers/ddes-technical-papers/ddesb-blast-effects-computer-open-bec-o-version-1-spread-sheet/BEC-O%20V1.xlsx) to download the original excel version of the BEC-O produced by the DDESB.

Other DDESB Technical Papers (TP) can be found [here](https://www.denix.osd.mil/ddes/ddes-technical-papers/index.html).

Includes standard methods for returning pressures, time of arrivals, durations, etc as well as goal seek methods for returning a distance given a certain pressure, time of arrival, duration, etc. 

# Install

Add the following to your project

build.gradle (Project)

```
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```
build.gradle (Module):

```
dependencies {
    implementation 'com.github.errorgon:pressure:v1.0.4'
}
```

# Usage

A no-arg constructor is provided that will declare default values for input parameters. 
```
Pressure pressure = new Pressure();
```
However, more useful constructors are provided values for the energetics, unit of measurements, distance, and energetic weight, altitude of test. 
```
Pressure pressure = new Pressure(new TNT(), Units.ENGLISH, 40.0, 100.0);
```

Fully defining the test involves inputs for PES, temperature, and defining the atmospheric scaling basis as either altitude- or pressure-based along with the altitude or barometric pressure. Shown here are defaults for all arguments. 
```
Pressure pressure = new Pressure(new Tritonal(), Units.ENGLISH, 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);
```

With this new object, a variety of outputs can be calculated with the option of adjusting for values at sealevel.
```
Pressure pressure = new Pressure(new TNT(), Units.ENGLISH, 40.0, 100.0);

boolean atSeaLevel = false;

pressure.getDynamicImpulse(atSeaLevel);
pressure.getDynamicOverpressure(atSeaLevel);
pressure.getReflectedImpulse(atSeaLevel);
pressure.getReflectedPressure(atSeaLevel);
pressure.getPositivePhaseImpulse(atSeaLevel);
pressure.getPositivePhaseDuration(atSeaLevel);
pressure.getIncidentPressure(atSeaLevel);
pressure.getIncidentPressure(atSeaLevel);
pressure.getTimeOfArrival(atSeaLevel);
```

Eardrum and lung damage estimates is also calculated
```
Pressure pressure = new Pressure(new TNT(), Units.ENGLISH, 40.0, 100.0);
double[] eardrumRupture = pressure.getChanceOfEardrumRupture();
double minorDamage = eardrumRupture[0];
double moderateDamage = eardrumRupture[1];
double majorDamage = eardrumRupture[2];

double chanceOfLungRupture = pressure.getChanceOfLungRupture();
```

Goal seek methods are provided to return a distance given pressure, impulse, etc...
```
Pressure pressure = new Pressure(new Tritonal(), Units.ENGLISH, 100, 75, PES.OPEN_STORAGE_STANDARD, 59, AtmosphericScalingBasis.ALTITUDE, 1000);

boolean atSeaLevel = false;

double dynamicImpulse = 10.0;
distance = pressure.distanceAtDynamicImpulse(dynamicImpulse, atSeaLevel);

double dynamicOverpressure = 10.0;
double distance = pressure.distanceAtDynamicOverpressure(dynamicOverpressure, atSeaLevel);

double reflectedImpulse = 10.0;
distance = pressure.distanceAtReflectedImpulse(reflectedImpulse, atSeaLevel);

double reflectedPressure = 10.0;
distance = pressure.distanceAtReflectedPressure(reflectedPressure, atSeaLevel);

double positivePhaseImpulse = 10.0;
distance = pressure.distanceAtPositivePhaseImpulse(positivePhaseImpulse, atSeaLevel);

double positivePhaseDuration = 10.0;
distance = pressure.distanceAtPositivePhaseDuration(positivePhaseDuration, atSeaLevel);

double incidentPressure = 10.0;
distance = pressure.distanceAtIncidentPressure(incidentPressure, atSeaLevel);

double timeOfArrival = 10.0;
distance = pressure.distanceAtTimeOfArrival(timeOfArrival, atSeaLevel);
```