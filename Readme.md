# Calcu – Android CNC Glass Cutting Application for Bando Machines

## Overview
**Calcu** is an Android application specifically designed for **Bando-brand CNC machines** used in the automotive glass industry. These machines are utilized to cut side windows (left and right) as well as rear windows for various car models.

## Key Features

### 🔹 Cutting Line Calculation
The application calculates precise cutting lines for the required window geometries. The operator enters the current machine parameters from the **FANUC controller** into the app, which then accurately displays the correct cutting line angles.

### 🔹 Dynamic Resizing
Users can easily resize the length of the cutting lines directly within the app. Once resized, the application automatically recalculates and outputs the updated coordinate values, which can be manually typed back into the FANUC controller.

### 🔹 Calibration Support
The app includes a built-in calibration function. This ensures it continues to work correctly with the machine even after physical adjustments or changes are made to the machinery setup.

### 🔹 Grinding Disc Diameter Calculator
A separate, dedicated panel is available for calculating the diameter of the grinding disc used by the machine for glass dressing and sharpening. This panel is highly specific and requires the operator to input particular values into the FANUC—specifically, the parameter labeled **`A`**.

---

## Usage Workflow
1. Launch the Calcu app on your Android device.
2. Input current FANUC machine parameters into the app.
3. View the calculated cutting line angles and geometry.
4. Resize lines if needed and retrieve updated coordinate values.
5. Use the calibration feature when machine adjustments occur.
6. Utilize the dedicated grinding disc panel to compute the required diameter and provide the correct `A` parameter to the FANUC.