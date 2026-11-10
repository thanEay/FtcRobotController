package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SimpleMecanumDrivetrain {
    private DcMotor backLeftDcMotor;
    private DcMotor backRightDcMotor;
    private DcMotor frontLeftDcMotor;
    private DcMotor frontRightDcMotor;
    private double ticksPerRevolution = 28;
    private double motorSpeed = 0.5;

    public void init(HardwareMap hwMap) { // Motor initializations
        backLeftDcMotor = hwMap.get(DcMotor.class, "backLeft");
        backRightDcMotor = hwMap.get(DcMotor.class, "backRight");
        frontLeftDcMotor = hwMap.get(DcMotor.class, "frontLeft");
        frontRightDcMotor = hwMap.get(DcMotor.class, "frontRight");
        backLeftDcMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightDcMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeftDcMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightDcMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRightDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontLeftDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void moveLeft() {
        frontLeftDcMotor.setPower(-motorSpeed); // Sets motor power
        frontRightDcMotor.setPower(-motorSpeed); // Sets motor power
        backLeftDcMotor.setPower(motorSpeed); // Sets motor power
        backRightDcMotor.setPower(motorSpeed); // Sets motor power
    }

    public void moveRight() {
        frontLeftDcMotor.setPower(motorSpeed); // Sets motor power
        frontRightDcMotor.setPower(motorSpeed); // Sets motor power
        backLeftDcMotor.setPower(-motorSpeed); // Sets motor power
        backRightDcMotor.setPower(-motorSpeed); // Sets motor power
    }

    public void moveForward() {
        frontLeftDcMotor.setPower(motorSpeed);
        frontRightDcMotor.setPower(-motorSpeed);
        backLeftDcMotor.setPower(motorSpeed);
        backRightDcMotor.setPower(-motorSpeed);
    }

    public void moveBackward() {
        frontLeftDcMotor.setPower(-motorSpeed);
        frontRightDcMotor.setPower(motorSpeed);
        backLeftDcMotor.setPower(-motorSpeed);
        backRightDcMotor.setPower(motorSpeed);
    }

    public void turnLeft() {
        frontLeftDcMotor.setPower(-motorSpeed);
        frontRightDcMotor.setPower(-motorSpeed);
        backLeftDcMotor.setPower(-motorSpeed);
        backRightDcMotor.setPower(-motorSpeed);
    }

    public void turnRight() {
        frontLeftDcMotor.setPower(motorSpeed);
        frontRightDcMotor.setPower(motorSpeed);
        backLeftDcMotor.setPower(motorSpeed);
        backRightDcMotor.setPower(motorSpeed);
    }

    public void turnLeftWithPower(double multiplier) {
        frontLeftDcMotor.setPower(-motorSpeed * multiplier);
        frontRightDcMotor.setPower(-motorSpeed * multiplier);
        backLeftDcMotor.setPower(-motorSpeed * multiplier);
        backRightDcMotor.setPower(-motorSpeed * multiplier);
    }

    public void turnRightWithPower(double multiplier) {
        frontLeftDcMotor.setPower(motorSpeed * multiplier);
        frontRightDcMotor.setPower(motorSpeed * multiplier);
        backLeftDcMotor.setPower(motorSpeed * multiplier);
        backRightDcMotor.setPower(motorSpeed * multiplier);
    }

    public void stop() {
        frontLeftDcMotor.setPower(0);
        frontRightDcMotor.setPower(0);
        backLeftDcMotor.setPower(0);
        backRightDcMotor.setPower(0);
    }
}
