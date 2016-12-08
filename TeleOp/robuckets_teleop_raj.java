package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp public class robuckets_teleop_raj extends OpMode {
    public DcMotor rightFront;
    public DcMotor leftFront;

    public DcMotor Rotate;
    public Servo Displace;
    public DcMotor Launch;

    double displaceMin = 0.5;
    double displaceMax = 1.0;
    double displaceTime = 1.7;

    //double launchTime = .15;
    double launchPower = 0.245;
    //double launchPos = 1514/4.6;
    //double powerRatio = 5.0;

    double rotatePos = 1514;
    double rotatePower = 0.1;

    public robuckets_teleop_raj() {


    }

    @Override
    public void init() {
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftFront = hardwareMap.dcMotor.get("leftFront");

        Rotate = hardwareMap.dcMotor.get("Rotate");
        Displace = hardwareMap.servo.get("Displace");
        Launch = hardwareMap.dcMotor.get("Launch");

        Rotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Launch.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Displace.setPosition(displaceMin);

    }

    public void delay(double secs) {
        try {
            Thread.sleep((long) secs * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loop() {
        //sets direction to 2 separate controls
        /*
        float throttle = gamepad1.right_stick_y;
        float direction = gamepad1.left_stick_x;
        */

        //sets it to 1 control



        float throttle = gamepad1.right_stick_y;
        float direction = gamepad1.left_stick_x;

        throttle = (float) Range.clip(throttle, -.5, .5);
        direction = (float) Range.clip(direction, -.5, .5);

        rightFront.setPower(direction + throttle);
        leftFront.setPower(direction - throttle);

        if (gamepad2.a) {
            //New code, based on encoders, needs testing
            Rotate.setTargetPosition((int) rotatePos);
            Rotate.setPower(rotatePower);
            Rotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        if(gamepad2.b){
            //old code for old design
            /*
            Launch.setTargetPosition((int) launchPos);
            Launch.setPower(launchPower);
            Launch.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            while(Launch.getCurrentPosition() < launchPos) {
                delay(0.001);
            }

            Launch.setPower(launchPower / powerRatio);
            Launch.setTargetPosition(0);
            Launch.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            while(Launch.getCurrentPosition() > 9){
                delay(0.01);
            }

            Launch.setPower(0);
            Launch.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            */


            //New code for new design, needs testing
            Launch.setPower(launchPower);

        }else{

            Launch.setPower(0.0);
        }

        if (gamepad2.x) {

            Displace.setPosition(displaceMax);
            delay(displaceTime);
            Displace.setPosition(displaceMin);

        }

        if(gamepad2.y) {
            Rotate.setPower(0);
            Rotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }



    @Override
    public void stop () {
        super.stop();
    }
}


