package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@Autonomous (name = "robuckets_autonomous_regionals")
public class robuckets_autonomous_regionals extends LinearOpMode {

    public DcMotor rightFront;
    public DcMotor leftFront;
    public DcMotor rightBack;
    public DcMotor leftBack;

    public DcMotor Launch1;
    public DcMotor Launch2
    public DcMotor collector;
    public Servo launchServo;
    
    public int active = 0;

    ColorSensor color_sensor;

    @Override
    public void runOpMode() throws InterruptedException {

        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        leftBack = hardwareMap.dcMotor.get("leftBack");

        Launch1 = hardwareMap.dcMotor.get("Launch1")
        Launch2 = hardwareMap.dcMotor.get("Launch2")        
        collector = hardwareMap.dcMotor.get("collector")
        launchServo = hardwareMap.servo.get("launchServo");

        rightFront.setMode(DcMotor.RunMode.RUN_WITOUT_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Launch1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        Launch2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        collector.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        color_sensor = hardwafeareMap.colorSensor.get("color");

        waitForStart();
           
        while (opModeIsActive() && counter != 1):
            color_sensor.enableLed(false);
            strafeDistance(4 ft);
            driveForwardDistance(1.7 ft);
            While (color_sensor.blue > 5 && color_sensor.blue < 10):
                strafe(0.5);
            stopDriving();
            delay(1.0);
            if (color_sensor.blue > 5 && color_sensor.blue < 10):
                driveForwardDistance(0.25 ft);
                stopDriving();
                driveForwardDistance(-0.25 ft);
                strafeDistance(0.5 ft);
            While (color_sensor.blue > 5 && color_sensor.blue < 10):
                strafe(0.5);
            stopDriving();
            delay(1.0);
            if (color_sensor.blue > 5 && color_sensor.blue < 10):
                driveForwardDistance(0.25 ft);
                stopDriving();
                driveForwardDistance(-0.75 ft);
            turnDistance(0.75 ft);
            autoLaunch(0.5);
                

    }

    public void delay(double secs) { /* creation of a wait method*/
        try {
            Thread.sleep((long) secs * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void drive(double power) {
        rightFront.setPower(-power);
        leftFront.setPower(power);
        rightBack.setPower(-power);
        leftBack.setPower(power)
    }

    public void strafe(double power) {
        rightFront.setPower(power);
        leftFront.setPower(power);
        rightBack.setPower(-power);
        leftBack.setPower(-power)
    }

    public void stopDriving() {
        driveForward(0);
        strafe(0);
    }

    public void launch(double launchPower) {
        Launch1.setPower(launchPower);
        Launch2.setPower(launchPower);
    }

    public void stopLaunch() {
        launch(0);
    }

    public void driveForwardDistance(double distance) {

        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        int rightCurrentPos = rightFront.getCurrentPosition();
        int leftCurrentPos = leftFront.getCurrentPosition();

        rightFront.setTargetPosition((int) -distance);
        leftFront.setTargetPosition((int) distance);

        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        driveForward(0.75);

        while (rightFront.isBusy() && leftFront.isBusy()) {

        }

        driveForward(0);

        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
        while (color_sensor.alpha() != color_sensor.red()) {
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
