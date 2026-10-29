package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

// This code uses the config file "Flywheel". TODO Change the variables in the config to "leftFlywheelmotor" and "rightFlywheelMotor"
@TeleOp
public class Flywheel{
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private double ticksPerRevolution = 28; // Found on the internet

    public void init(HardwareMap hwMap) {
        leftMotor = hwMap.get(DcMotor.class, "leftFlywheelMotor");
        rightMotor = hwMap.get(DcMotor.class, "rightFlywheelMotor");

        // Ensures that the motors start at zero revolutions at initialization
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); // When off, will automatically stop
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void setFlywheelSpeed(double speed) { // Sets flywheel speed by setting the speed of two motors on the same shaft
        leftMotor.setPower(-speed); // Accepts values from -1.0 to 1.0
        rightMotor.setPower(speed);
//        rightMotor.setPower(0); // rightMotor is dead weight (unpowered/coasting) for testing

    }
    public double getFlywheelRevolutions() { // Get the number of flywheel revolutions. Only checks one motor because they are connected on one shaft and expected to have identical absolute values.
        return leftMotor.getCurrentPosition() / ticksPerRevolution; // Normalizing ticks to revolutions. For a gear ratio, multiply by X:1 ratio. For example, ...PerRevolution * 4; for a 4:1 ratio.
    }

    public void resetFlywheelRevolutions(){ // Reset encoders
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

}
