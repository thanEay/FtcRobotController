package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class MecanumDrivetrain {

    private DcMotor backLeftDcMotor, backRightDcMotor, frontLeftDcMotor, frontRightDcMotor;
    private IMU imu;

    public void init(HardwareMap hwMap) {
        backLeftDcMotor = hwMap.get(DcMotor.class, "backLeft");
        backRightDcMotor = hwMap.get(DcMotor.class, "backRight");
        frontLeftDcMotor = hwMap.get(DcMotor.class, "frontLeft");
        frontRightDcMotor = hwMap.get(DcMotor.class, "frontRight");

        frontLeftDcMotor.setDirection(DcMotorSimple.Direction.REVERSE); // Set in reverse for code simplicity
        backLeftDcMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        backLeftDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // Run using encoder so that the motors try to run at a set speed regardless of resistance
        backRightDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backLeftDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRightDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontLeftDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        imu = hwMap.get(IMU.class, "imu"); // "imu" is the default name in the Control Hub

        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
                RevHubOrientationOnRobot.UsbFacingDirection.RIGHT);

        imu.initialize(new IMU.Parameters(RevOrientation)); // Initializing the IMU. Tbh I don't know how this line works
    }

    public void drive(double forward, double strafe, double rotate) {
        double backLeftPower = forward - strafe + rotate;
        double backRightPower = forward + strafe - rotate;
        double frontLeftPower = forward + strafe + rotate;
        double frontRightPower = forward - strafe - rotate;

        double maxPower = 1.0;
        double maxSpeed = 1.0;  // make this slower for outreaches

        maxPower = Math.max(maxPower, Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));

        backLeftDcMotor.setPower(maxSpeed * (frontLeftPower / maxPower));
        backRightDcMotor.setPower(maxSpeed * (frontRightPower / maxPower));
        frontLeftDcMotor.setPower(maxSpeed * (backLeftPower / maxPower));
        frontRightDcMotor.setPower(maxSpeed * (backRightPower / maxPower));
    }
}
