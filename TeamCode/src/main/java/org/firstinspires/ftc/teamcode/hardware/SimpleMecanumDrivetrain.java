package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SimpleMecanumDrivetrain {
    private DcMotor backLeftDcMotor;
    private DcMotor backRightDcMotor;
    private DcMotor frontLeftDcMotor;
    private DcMotor frontRightDcMotor;
    private double ticksPerRevolution = 28;

    public void init(HardwareMap hwMap) { // Motor initializations
        backLeftDcMotor = hwMap.get(DcMotor.class, "backLeft");
        backRightDcMotor = hwMap.get(DcMotor.class, "backRight");
        frontLeftDcMotor = hwMap.get(DcMotor.class, "frontLeft");
        frontRightDcMotor = hwMap.get(DcMotor.class, "frontRight");
        backLeftDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRightDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontLeftDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void moveLeft() {
        frontLeftDcMotor.setPower(-1.0);
        frontRightDcMotor.setPower(-1.0);
        backLeftDcMotor.setPower(1.0);
        backRightDcMotor.setPower(1.0);
    }

    public void moveRight() {
        frontLeftDcMotor.setPower(1.0);
        frontRightDcMotor.setPower(1.0);
        backLeftDcMotor.setPower(-1.0);
        backRightDcMotor.setPower(-1.0);
    }

    public void moveForward() {
        frontLeftDcMotor.setPower(1.0);
        frontRightDcMotor.setPower(-1.0);
        backLeftDcMotor.setPower(1.0);
        backRightDcMotor.setPower(-1.0);
    }

    public void moveBackward() {
        frontLeftDcMotor.setPower(-1.0);
        frontRightDcMotor.setPower(1.0);
        backLeftDcMotor.setPower(-1.0);
        backRightDcMotor.setPower(1.0);
    }
}
