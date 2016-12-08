package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@Autonomous (name = "R_cornerPark")
public class R_cornerPark extends LinearOpMode {

    DcMotor rightFront = null;
    DcMotor leftFront = null;
    DcMotor Launch1 = null;
    DcMotor Launch2 = null;

    Servo beaconPush = null;

    ColorSensor color_sensor;
    int blue = 3;
    int red = 10;

    @Override
    public void runOpMode() throws InterruptedException {

        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        Launch1 = hardwareMap.dcMotor.get("Launch1");
        Launch2 = hardwareMap.dcMotor.get("Launch2");

        beaconPush = hardwareMap.servo.get("beaconPusher");

        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        color_sensor = hardwareMap.colorSensor.get("color");

        waitForStart();

        color_sensor.enableLed(false);
        color_sensor.red(); // Red channel value
        color_sensor.green(); // Green channel value
        color_sensor.blue(); // Blue channel value
        color_sensor.alpha(); // Total luminosity
        color_sensor.argb(); // Hue measurement

        beacon();
        postBeacon();
        autoLaunch(0.25);

    }

    public void delay(double secs) { /* creation of a wait method*/
        try {
            Thread.sleep((long) secs * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void driveForward(double power) {
        rightFront.setPower(power);
        leftFront.setPower(power);
    }

    public void driveLeft(double power) {
        rightFront.setPower(-power);
        leftFront.setPower(power);
    }

    public void driveRight(double power) {
        rightFront.setPower(power);
        leftFront.setPower(-power);
    }

    public void stopDriving() {
        driveForward(0);
        driveLeft(0);
        driveRight(0);
    }

    public void launch(double launchPower) {
        Launch1.setPower(launchPower);
        Launch2.setPower(launchPower);
    }

    public void stopLaunch() {
        launch(0);
    }


    public void autoLaunch(double launchPower) {

        launch(launchPower);

        delay(7.0);

        stopLaunch();

    }

    public void beaconPress() {
        if(beaconPush.getPosition() < 1.0) {
            beaconPush.setPosition(Range.clip(beaconPush.getPosition() + .01, 0, 1));     //Change added value for different speed
        }
        delay(3.0);
        if(beaconPush.getPosition() > 0) {
            beaconPush.setPosition(Range.clip(beaconPush.getPosition() - .01, 0, 1));
        }
    }

    public void beacon() {
        while (color_sensor.alpha() != red) {
            driveForward(0.75);
            delay(2.0);
            stopDriving();

            driveLeft(0.5);
            stopDriving();

            driveForward(0.75);
            delay(2.0);
            stopDriving();

            driveLeft(0.75);
            delay(5.0);
            stopDriving();
        }
        beaconPress();
    }

    public void postBeacon() {
        driveRight(0.5);
        delay(0.25);
        stopDriving();

        driveForward(0.5);
        delay(1.0);
        stopDriving();

        driveRight(0.5);
        delay(0.75);
        stopDriving();
    }
}
