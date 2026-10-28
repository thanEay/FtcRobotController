package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

// TODO Add dead weight motors in case there are motors in front. Actually TODO may already be done.
// // This code uses the config file "Tank Drive Drivetrain"
public class TankDriveDrivetrain {

    private DcMotor backLeftDcMotor;
    private DcMotor backRightDcMotor;
    private DcMotor frontLeftDcMotor;
    private DcMotor frontRightDcMotor;
    private double ticksPerRevolution = 28;

    public void init(HardwareMap hwMap) {
        backLeftDcMotor = hwMap.get(DcMotor.class, "backLeft");
        backRightDcMotor = hwMap.get(DcMotor.class, "backRight");
        frontLeftDcMotor = hwMap.get(DcMotor.class, "frontLeft");
        frontRightDcMotor = hwMap.get(DcMotor.class, "frontRight");
        backLeftDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


    }

    public double getBackLeftMotorRevolutions() {
        return backLeftDcMotor.getCurrentPosition() / ticksPerRevolution * 20; // Normalizing ticks to revolutions. For a gear ratio, multiply by X:1 ratio. For example, ...PerRevolution * 4; for a 4:1 ratio.
    }
    public double getBackRightMotorRevolutions() {
        return -(backRightDcMotor.getCurrentPosition() / ticksPerRevolution * 20); // Normalizing ticks to revolutions. For a gear ratio, multiply by X:1 ratio. For example, ...PerRevolution * 4; for a 4:1 ratio.
    }
    public void resetMotorRevolutions(){
        backRightDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setBackLeftDcMotor(double speed) {
        backLeftDcMotor.setPower(speed); // Accepts values from -1.0 to 1.0
    }
    public void setBackRightDcMotor(double speed) {
        backRightDcMotor.setPower(speed); // Accepts values from -1.0 to 1.0
    }
}
