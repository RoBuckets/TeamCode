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
    public DcMotor walkwheel;
    public DcMotor collector;
    public Servo pusher;
    
    public boolean collecting = false;
    public boolean pusherIsOut = false;
    public int manipStage = 0;
    

    public robuckets_teleop() {


    }

    @Override
    public void init() {
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        
        flywheel = hardwareMap.dcMotor.get("flywheel");
        walkwheel = hardwareMap.dcMotor.get("walkwheel");
        collector = hardwareMap.dcMotor.get("collector");
        pusher = hardwareMap.servo.get("pusher");
        
        flywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

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
        double collectorSpeed = 0.5;
        double walkwheelSpeed1 = 0.2;
        double walkwheelSpeed2 = 0.9;
        double flywheelSpeed = 0.9;
        
        double pusherIn = 0.0;
        double pusherOut = 100.0; 
        
        double manipTimeDelay1 = 2.5;
        double manipTimeDelay2 = 1.5;
        
        
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

        
        //collector code
        if(gamepad2.a) {
            if(collecting == false){
                collector.setPower(collectorSpeed);
                collecting = true;
            }
            else {
                collector.setPower(0.0);
                collecting = false;
            }
        }
        
        
        
        //launcher code
        if(manipStage == 0) {
            if (gamepad2.b) {
                walkwheel.setPower(walkwheelSpeed1);
                manipStage++;
            }
        }
        else if(manipStage == manipTimeDelay1 * 500){
            flywheel.setPower(flywheelSpeed);
            manipStage++;
        }
        else if(manipStage < manipTimeDelay1 * 1000) {
            delay(0.001);
            manipStage++;
        }
        else if(manipStage == manipTimeDelay1 * 1000) {
            walkwheel.setPower(walkwheelSpeed2);
            manipStage++;
        }
        else if(manipStage < (manipTimeDelay1 + manipTimeDelay2) * 1000) {
            delay(0.001);
            manipStage++;
        }
        else {
            flywheel.setPower(0.0);
            walkwheel.setPower(0.0);
            manipStage = 0;
        }
        
        //beacon code
        if(gamepad2.x){
            if(pusherIsOut == false){
                pusher.setPosition(pusherOut);
            }
            else {
                pusher.setPosition(pusherIn);
            }
        }

    }



    @Override
        public void stop () {
            super.stop();
        }
    }


