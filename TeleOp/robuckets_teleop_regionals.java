package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp public class robuckets_teleop extends OpMode {
    public DcMotor rightFront;
    public DcMotor leftFront;
    public DcMotor rightBack;
    public DcMotor leftBack;

    public robuckets_teleop() {


    }

    @Override
    public void init() {
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        leftBack = hardwareMap.dcMotor.get("leftBack");

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

        float y = gamepad1.right_stick_y;
        float x = gamepad1.right_stick_x;
        float turn = gamepad1.left_stick_x;

        y = (float) Range.clip(y, -.5, .5);
        x = (float) Range.clip(x, -.5, .5);
        turn = (float) Range.clip(turn, -.5, .5);

        //Code for 45 degree omniwheels

        //If wheels spin clockwise

        //rightFront.setPower(y - x - turn);
        //leftFront.setPower(-y - x - turn);
        //rightBack.setPower(x + y - turn);
        //leftBack.setPower(x - y - turn);



        //If wheels spin counter-clockwise

        //rightFront.setPower(x - y + turn);
        //leftFront.setPower(x + y + turn);
        //rightBack.setPower(-y - x + turn);
        //leftBack.setPower(y - x + turn);


    }



    @Override
        public void stop () {
            super.stop();
        }
    }


