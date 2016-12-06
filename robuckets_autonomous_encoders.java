package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;

@Autonomous (name = "robuckets_autonomous_encoders")
public class robuckets_autonomous_encoders extends LinearOpMode {
    DcMotor rightFront = null;
    DcMotor leftFront = null;
    DcMotor Launch = null;


    @Override
    public void runOpMode() throws InterruptedException {

        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        Launch = hardwareMap.dcMotor.get("Launch");

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

    public void stopDriving() {
        driveForward(0);
    }

    public void launch(double launchPower) {
        Launch.setPower(launchPower);
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

        rightFront.setPower(0.75);
        leftFront.setPower(0.75);

        while (rightFront.isBusy() && leftFront.isBusy()) {

        }

        rightFront.setPower(0);
        leftFront.setPower(0);

        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void autoLaunch(double launchPower) {

        launch(launchPower);

        delay(7.0);

        stopLaunch();

        delay(10.0);
    }
}
