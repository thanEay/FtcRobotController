package org.firstinspires.ftc.teamcode.biobuzz;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class Flywheel extends OpMode {
    public DcMotor leftMotor;
    public DcMotor rightMotor;

    private double ticksPerRevolution;

    @Override
    public void init() {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        ticksPerRevolution = leftMotor.getMotorType().getTicksPerRev(); // Define how many ticks are in a revolution of the motor for determining revolutions when gearboxes are attached

        // Ensures that the motors start at zero revolutions at initialization
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        setFlywheelSpeed(gamepad1.left_stick_y * 0.5); // Directionality may need to be flipped
    }

    public void setFlywheelSpeed(double speed) { // Sets flywheel speed by setting the speed of two motors on the same shaft
        leftMotor.setPower(speed); // Accepts values from -1.0 to 1.0
        rightMotor.setPower(-speed);
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
