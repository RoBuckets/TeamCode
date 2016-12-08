package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp (name = "robuckets_teleop")
public class robuckets_teleop extends OpMode {

    public DcMotor rightFront;
    public DcMotor leftFront;

    public DcMotor Rotate;
    public DcMotor Launch1;
    public DcMotor Launch2;
    public DcMotor Lift;


    @Override
    public void init() {

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

        float throttle = gamepad1.right_stick_y;
        float direction = gamepad1.right_stick_x;

        throttle = (float) Range.clip(throttle, -.5, .5);
        direction = (float) Range.clip(direction, -.5, .5);

        rightFront.setPower(direction - throttle);
        leftFront.setPower(direction + throttle);

        if (gamepad2.a) {
                Lift.setPower(0.25);
                Launch1.setPower(1.0);
                Launch2.setPower(2.0);
        }

        if (gamepad2.b) {
            Lift.setPower(0);
            Launch1.setPower(0);
            Launch2.setPower(0);
        }

        if (gamepad2.x) {

            Rotate.setPower(0.75);
        }

        if (gamepad2.y) {

            Rotate.setPower(0.75);
        }

    }

    @Override
    public void stop () {
        super.stop();
    }
}


