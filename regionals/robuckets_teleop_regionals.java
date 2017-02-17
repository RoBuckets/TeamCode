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

    public DcMotor flywheel;
    public DcMotor collector;
    public Servo launchServo;

    public int manipStage = 0;
    public int capBallStage = 0;

    public Servo capServo;
    public DcMotor capMotor;

    public robuckets_teleop() {


    }

    @Override
    public void init() {
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        leftBack = hardwareMap.dcMotor.get("leftBack");

        flywheel = hardwareMap.dcMotor.get("flywheel");
        collector = hardwareMap.dcMotor.get("collector");
        launchServo = hardwareMap.servo.get("launchServo");

        capServo = hardwareMap.servo.get("capServo");
        capMotor = hardwareMap.dcMotor.get("capMotor");

        flywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        collector.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

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
        double time = getRuntime();

        double collectorSpeed = 0.75;
        double flywheelSpeed = 0.9;

        double launchServoIn = 0.0;
        double launchServoOut = 100.0;

        double manipTimeDelay1 = 2.5;
        double manipTimeDelay2 = 1.5;

        boolean endGameStarted = false;

        double capServoIn = 0;
        double capServoOut = 100;
        double capMotorSpeed = 0.25;

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

        if (endGameStarted == false) {
            if (gamepad1.right_bumper) {
                endGameStarted = true;
                capServo.setPosition(capServoOut);
            }

            rightFront.setPower(x - y + turn);
            leftFront.setPower(x + y + turn);
            rightBack.setPower(-y - x + turn);
            leftBack.setPower(y - x + turn);

        }
        else {
            if (gamepad1.right_bumper) {
                endGameStarted = false;
                capServo.setPosition(capServoIn);
            }

            rightBack.setPower(x - y + turn);
            rightFront.setPower(x + y + turn);
            leftBack.setPower(-y - x + turn);
            leftFront.setPower(y - x + turn);

            //capBall Code
            if (gamepad1.right_trigger > 0.75) {
                capMotor.setPower(capMotorSpeed);
            }
            else if (gamepad1.left_trigger > 0.75) {
                capMotor.setPower(-capMotorSpeed);
            }
            else {
                capMotor.setPower(0.0);
            }

        }



        //collector code
        if(gamepad2.a) {
            collector.setPower(collectorSpeed);
        }
        else {
            collector.setPower(0.0);
        }



        //launcher code
        if(manipStage == 0) {
            if (gamepad2.b) {
                flywheel.setPower(flywheelSpeed);
                manipStage++;
            }
        }
        else if(manipStage == manipTimeDelay1 * 500){
            launchServo.setPosition(launchServoOut);
            manipStage++;
        }
        else if(manipStage < manipTimeDelay1 * 1000) {
            delay(0.001);
            manipStage++;
        }
        else if(manipStage == manipTimeDelay1 * 1000) {
            collector.setPower(collectorSpeed);
            manipStage++;
        }
        else {
            if(gamepad2.b) {
                flywheel.setPower(0.0);
                collector.setPower(0.0);
                manipStage = 0;
            }
        }


    }



    @Override
    public void stop () {
        super.stop();
    }
}
