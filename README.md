
# MASTestApp for Android

## Overview

**MASTestApp** is an Android application written in Kotlin. Contributors can easily create and test new MASTG demos, ensuring that the static and dynamic analysis processes are properly documented and reproducible.

The app is intentionally simple, offering three essential files:
1. `MainActivity.kt` - Contains the default UI, which should not be modified.
2. `AndroidManifest.xml` - Contains placeholders for additional things that may be needed.
3. `MastgTest.kt` - Contains one function. This file is intended to be modified by users to create new MASTG demos but should not be modified in the original repository.

Contributors must copy the final modified `MastgTest.kt` file to their demo folder in the OWASP MASTG repository under the corresponding `risk > test > demo`.

## Instructions

### Create a New Demo in the MASTG

Create a new folder in the MASTG repository under the corresponding `risk > test > demo` following the [guidelines](https://docs.google.com/document/d/1EMsVdfrDBAu0gmjWAUEs60q-fWaOmDB5oecY9d9pOlg/edit#heading=h.y294y561hx14)

### Clone the MASTestApp Repository

Clone the app repository and open it in Android Studio.

```sh
git clone https://github.com/cpholguera/MASTestApp-Android.git
```

### Add Your Demo Code

- Edit `MastgTest.kt` to implement your demo.
- If applicable, modify the `AndroidManifest.xml` to add necessary permissions or components.
- Build the app and **test it** on the Android emulator or a physical device

### Run the Extraction Script 

Run the provided script:

```sh
tools/extract-code-for-mastg-demo.sh
```

The output will be:

```sh
output/
├── MastgTest.kt
├── MastgTest_reversed.java
├── AndroidManifest.xml
└── AndroidManifest_reversed.xml
```

### If Your Demo Requires Static Analysis (Reverse Engineering)

Run your SAST rules on the `_reversed` files and ensure they work as expected.

### If Your Demo Requires Dynamic Analysis

Use the Android emulator or a physical device and run your dynamic scripts.

## Finalize Your Demo

Once everything works fine, copy the relevant files from the output folder to the demo folder in the MASTG repository. It should look like this:

```sh
owasp-mastg/risks/.../demo-1/
├── MastgTest.kt
├── MastgTest_reversed.java
├── AndroidManifest.xml
├── AndroidManifest_reversed.xml
├── demo.md
├── output.txt
└── run.sh
```

Finalize your demo by adding a `demo.md` file, tweaking the `run.sh` script, and adding the relevant output files.
