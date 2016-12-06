package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_TO_POSITION;

@Autonomous (name = "robuckets_autonomous")
public class robuckets_autonomous extends LinearOpMode {
    public DcMotor rightFront;
    public DcMotor leftFront;



    public robuckets_autonomous() {

    }

    public void runOpMode() throws InterruptedException {

        waitForStart();

        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftFront = hardwareMap.dcMotor.get("leftFront");

        /* rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); */
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftFront.setDirection(DcMotor.Direction.FORWARD);

       /* int rightCurrentPos = rightFront.getCurrentPosition();
        int leftCurrentPos = leftFront.getCurrentPosition();

        rightFront.setTargetPosition(13420 + rightCurrentPos);
        leftFront.setTargetPosition(13420 + leftCurrentPos);

        rightFront.setMode(RUN_TO_POSITION);
        leftFront.setMode(RUN_TO_POSITION); */
       /* rightFront.setTargetPosition(40000);
        leftFront.setTargetPosition(40000); */

        rightFront.setPower(-0.5);
        leftFront.setPower(0.5);


       /* rightFront.setMode(RUN_TO_POSITION);
        leftFront.setMode(RUN_TO_POSITION); */
        sleep(2500);
        rightFront.setPower(0);
        leftFront.setPower(0);
    }

}

    /*
     @Override
    public void runOpMode() throws InterruptedException {


        int rightCurrentPos = rightFront.getCurrentPosition();
        int leftCurrentPos = leftFront.getCurrentPosition();

        rightFront.setTargetPosition(13420 + rightCurrentPos);
        leftFront.setTargetPosition(13420 + leftCurrentPos);

        rightFront.setMode(RUN_TO_POSITION);
        leftFront.setMode(RUN_TO_POSITION);
    } */


