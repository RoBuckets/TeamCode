package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "test")
public class test extends OpMode {

    public DcMotor Launch1;
    public DcMotor Launch2;
    public DcMotor Lift;


    @Override
    public void init() {


        Launch1 = hardwareMap.dcMotor.get("Launch1");
        Launch2 = hardwareMap.dcMotor.get("Launch2");
        Lift = hardwareMap.dcMotor.get("Lift");

        Launch1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Launch2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }


    @Override
    public void loop() {

        if (gamepad2.a) {
            Lift.setPower(0.25);
            Launch1.setPower(1.0);
            Launch2.setPower(-1);

        }

        if (gamepad2.b) {
            Lift.setPower(0);
            Launch1.setPower(0);
            Launch2.setPower(0);
        }

    }

    @Override
    public void stop () {
        super.stop();
    }
}



