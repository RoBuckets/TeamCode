package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp public class robuckets_teleop_1 extends OpMode { /* declaration of variable, motors, and servos */
    public DcMotor rightFront;
    public DcMotor leftFront;

    public DcMotor Rotate;
    public DcMotor Launch1;
    public DcMotor Launch2;
    public DcMotor Lift;

    //double launchTime = .15;
    int launcherVal = 0;
    //double launchPos = 1514/4.6;
    //double powerRatio = 5.0;

    public robuckets_teleop_1() {


    }

    @Override
    public void init() { /* setting motor and encoder configurations, servo configuration, sets initial servo position */
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftFront = hardwareMap.dcMotor.get("leftFront");

        Rotate = hardwareMap.dcMotor.get("Rotate");
        Launch1 = hardwareMap.dcMotor.get("Launch1");
        Launch2 = hardwareMap.dcMotor.get("Launch2");
        Lift = hardwareMap.dcMotor.get("Lift");

        Rotate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Launch1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Launch2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }


    @Override
    public void loop() {
        //sets direction to 2 separate controls
        /*
        float throttle = gamepad1.right_stick_y;
        float direction = gamepad1.left_stick_x;
        */

        //sets it to 1 control



        float throttle = gamepad1.right_stick_y; //sets forward/backward throttle to y-axis of right stick
        float direction = gamepad1.right_stick_x; //sets right/left throttle to x-axis of right stick

        throttle = (float) Range.clip(throttle, -.5, .5); //sets max for throttle at 1/2 power
        direction = (float) Range.clip(direction, -.5, .5); //sets max for direction at 1/2 power

        rightFront.setPower(direction - throttle); //sets power for turning and moving, enables both to be done at once
        leftFront.setPower(direction + throttle); //sets power for turning and moving, enables both to be done at once

        if (gamepad2.a) {
            if (launcherVal == 0) {
                Rotate.setPower(0.75);
                Launch1.setPower(1.0);
                Launch2.setPower(2.0);
            }
            if (gamepad2.a){
                launcherVal = 0;
            }
        }

        if (gamepad2.b) {
            Lift.setPower(0.25);
        }
        else {
            Lift.setPower(0);
        }
    }



    @Override
    public void stop () {
        super.stop();
    }
}


