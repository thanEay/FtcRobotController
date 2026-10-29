package org.firstinspires.ftc.teamcode.biobuzz;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Flywheel;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.SimpleMecanumDrivetrain;

@TeleOp
public class FullRobotSimpleMecanum extends OpMode {

    Flywheel flywheel = new Flywheel();
    SimpleMecanumDrivetrain drivetrain = new SimpleMecanumDrivetrain();
    Intake intake = new Intake();
    private static final double intakeMaxPower = 1.0;
    private static final double flywheelMaxPower = 0.5;


    @Override
    public void init() {
        flywheel.init(hardwareMap);
        drivetrain.init(hardwareMap);
        intake.init(hardwareMap);
    }

    @Override
    public void loop() {
        // Drivetrain
        if (gamepad1.dpad_left) {
            drivetrain.moveLeft();
        }
        else if (gamepad1.dpad_right) {
            drivetrain.moveRight();
        }
        else if (gamepad1.dpad_up) {
            drivetrain.moveForward();
        }
        else if (gamepad1.dpad_down) {
            drivetrain.moveBackward();
        }

        // Intake
        double power = -gamepad1.left_stick_y * intakeMaxPower; // Define the power
        intake.setIntakeMotor(power); // Set intake power

        // Flywheel
        if (gamepad1.left_trigger_pressed) {
            flywheel.setFlywheelSpeed(flywheelMaxPower); // Set flywheel speed
        }
    }

}
