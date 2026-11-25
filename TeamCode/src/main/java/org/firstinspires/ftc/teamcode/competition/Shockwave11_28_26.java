package org.firstinspires.ftc.teamcode.competition;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Flywheel;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.hardware.ServoGates;
import org.firstinspires.ftc.teamcode.hardware.SimpleMecanumDrivetrain;

@TeleOp
public class Shockwave11_28_26 extends OpMode {

MecanumDrivetrain drivetrain = new MecanumDrivetrain();
Flywheel flywheel = new Flywheel();
Intake intake = new Intake();
ServoGates servoGates = new ServoGates();
private static final double intakeMaxPower = 1.0;
private static final double flywheelMaxPower = 0.5;
double forward, strafe, rotate;

    @Override
    public void init() {
        drivetrain.init(hardwareMap);
    }

    @Override
    public void loop() {

        // Mecanum drivetrain
        forward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;
        drivetrain.driveFieldRelative(forward, strafe, rotate); // Use .driveFieldRelative for field relative or .drive for robot relative

        // Intake
        if (gamepad2.right_trigger_pressed) {
            intake.setIntakeMotor(intakeMaxPower); // Set intake power
        }
        else if (gamepad2.right_bumper) {
            intake.setIntakeMotor(-0.7 * intakeMaxPower); // Allow intake to push ball back out
        }
        else {
            intake.setIntakeMotor(0);
        }

        // Flywheel
        if (gamepad2.left_trigger_pressed) {
            flywheel.setFlywheelSpeed(flywheelMaxPower); // Set flywheel speed
        }
        else {
            flywheel.stopFlywheel();
        }

        // Servo Gates
        if (gamepad2.cross) {
            servoGates.openPollenServo();
        }
        else {
            servoGates.closePollenServo();
        }
    }

}
