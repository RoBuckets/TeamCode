package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "R_noBeacon")
public class R_noBeacon extends LinearOpMode {

    DcMotor rightFront = null;
    DcMotor leftFront = null;
    DcMotor Launch1 = null;
    DcMotor Launch2 = null;


    @Override
    public void runOpMode() throws InterruptedException {

        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        Launch1 = hardwareMap.dcMotor.get("Launch1");
        Launch2 = hardwareMap.dcMotor.get("Launch2");


        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        autoLaunch(0.25);
        driveForwardDistance(13420 * 0.41175);

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
}
