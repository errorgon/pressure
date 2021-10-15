# Read me

[![](https://jitpack.io/v/errorgon/pressure.svg)](https://jitpack.io/#errorgon/pressure)

Library for Java implementation of the Blast Effects Computer -Open (BEC-O) by the DDESB


For more information, click [here](https://denix.osd.mil/ddes/ddes-technical-papers/ddes-technical-papers/tp-20-ddesb-blast-effects-computer-open-bec-o-version-1-users-manual-and-documentation-11-june-2018/) for TP-20 Blast Effects Computer - Open (BEC-O) User Manual.

Click [here](https://www.denix.osd.mil/ddes/ddes-technical-papers/ddes-technical-papers/ddesb-blast-effects-computer-open-bec-o-version-1-spread-sheet/BEC-O%20V1.xlsx) to download the original excel version of the BEC-O produced by the DDESB.

Other DDESB Technical Papers (TP) can be found [here](https://www.denix.osd.mil/ddes/ddes-technical-papers/index.html).

Includes goal seek methods.

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

```

