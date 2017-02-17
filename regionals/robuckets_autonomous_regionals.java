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
            auto();
            counter = 1;     

    }
    
    public void auto() {
        strafeDistance(4 ft);
        driveDistance(1.7 ft);
        While (color_sensor.blue > 5 && color_sensor.blue < 10):
            strafe(0.5);
        stopDriving();
        delay(1.0);
        if (color_sensor.blue > 5 && color_sensor.blue < 10):
            driveDistance(0.25 ft);
            stopDriving();
            driveDistance(-0.25 ft);
            strafeDistance(0.5 ft);
        While (color_sensor.blue > 5 && color_sensor.blue < 10):
            strafe(0.5);
        stopDriving();
        delay(1.0);
        if (color_sensor.blue > 5 && color_sensor.blue < 10):
            driveDistance(0.25 ft);
            stopDriving();
            driveDistance(-0.75 ft);
        turnDistance(0.75 ft);
        autoLaunch(0.5);

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
    
    public void turn(double power) {
        rightFront.setPower(power);
        leftFront.setPower(power);
        rightBack.setPower(power);
        leftBack.setPower(power)
    }

    public void launch(double launchPower) {
        launchServo.setPosition(1.0);
        Launch1.setPower(launchPower);
        Launch2.setPower(launchPower);
    }
        
    public void stopDriving() {
        driveForward(0);
        strafe(0);
        turn(0);
    }

    public void stopLaunch() {
        launch(0);
        collector.setPower(0);
        launchServo.setPower(0);
    }

    public void driveDistance(double distance) {

        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition((int) distance);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        drive(0.75);

        while (leftFront.isBusy()) {

        }

        drive(0);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    
    public void strafeDistance(double distance) {

        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition((int) distance);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        strafe(0.75);

        while (leftFront.isBusy()) {

        }

        strafe(0);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void turnDistance(double distance) {

        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition((int) distance);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        turn(0.75);

        while (leftFront.isBusy()) {

        }

        turn(0);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
        
    public void autoLaunch(double launchPower, double collectPower) {

        launch(launchPower);
        delay(1.0);
        collector.setPower(collectPower);
        delay(4.0);

        stopLaunch();

    }
}
