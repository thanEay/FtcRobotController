package org.firstinspires.ftc.teamcode.biobuzz;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.Flywheel;

// This code uses the config file "Flywheel"
@TeleOp
public class UseFlywheel extends OpMode {
    public DcMotor leftMotor;
    public DcMotor rightMotor;
    private double ticksPerRevolution;
    Flywheel flywheel = new Flywheel();

    @Override
    public void init() {
        flywheel.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.left_trigger_pressed) {
            flywheel.setFlywheelSpeed(0.5); // 0.5 is the current max speed
        }
        telemetry.addData("Flywheel revolutions", flywheel.getFlywheelRevolutions()); // Uses leftMotor because it is the only one using the encoder
        if (gamepad1.a) {
            flywheel.resetFlywheelRevolutions();
        }
    }


}
