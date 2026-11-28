package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Flywheel;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.hardware.ServoGates;

@Autonomous(name = "Shoot and Strafe", group = "Autonomous")
public class ShootAndStrafe extends LinearOpMode {

    MecanumDrivetrain drivetrain = new MecanumDrivetrain();
    Flywheel flywheel = new Flywheel();
    Intake intake = new Intake();
    ServoGates servoGates = new ServoGates();
    private static final double intakeMaxPower = 1.0;
    double flywheelPower = 0.7;

    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        drivetrain.init(hardwareMap); // Initializes drivetrain hardware
        flywheel.init(hardwareMap); // Initializes flywheel hardware
        intake.init(hardwareMap);
        servoGates.init(hardwareMap);

        telemetry.addData("Status", "Initialized"); // Displays status on Driver Station
        telemetry.update(); // Updates telemetry display

        waitForStart(); // Waits for driver to press Play

        runtime.reset(); // Resets timer


        // Drive forward a little
        if (opModeIsActive()) {
            drivetrain.drive(0.5, 0.0, 0.0); // Drives forward at 50% power
            sleep(75); // Drives forward for 0.05 seconds
            drivetrain.drive(0.0, 0.0, 0.0); // Stops drivetrain
        }

        // Spin up flywheel
        if (opModeIsActive()) {
            flywheel.setFlywheelSpeed(0.45); // Sets flywheel speed to 60% power
            sleep(3000); // Waits 2 seconds for flywheel to spin up
        }

        // Prepare to shoot
        if (opModeIsActive()) {
            // Flywheel is still on
            servoGates.openNectarServo();
            sleep(500);
            intake.setIntakeMotor(intakeMaxPower);
            sleep(5000);
            flywheel.stopFlywheel();
            intake.setIntakeMotor(0);
            servoGates.closeNectarServo();
        }

        // Drive forward
        if (opModeIsActive()) {
            flywheel.stopFlywheel();
            drivetrain.drive(0.75, 0.0, 0.0); // Drives forward at 50% power
            sleep(1000); // Drives forward for 1 second
            drivetrain.drive(0.0, 0.0, 0.0); // Stops drivetrain
        }

        // Strafe right
        if (opModeIsActive()) {
            drivetrain.drive(0.0, 0.5, 0.0); // Strafes right at 50% power
            sleep(2750); // Strafes right for 2.75 seconds
            drivetrain.drive(0.0, 0.0, 0.0); // Stops drivetrain
        }

        // Stop all systems
        flywheel.stopFlywheel(); // Stops flywheel motor
        drivetrain.drive(0.0, 0.0, 0.0); // Ensures drivetrain is stopped

        telemetry.addData("Status", "Autonomous Finished"); // Signals autonomous is complete
        telemetry.update(); // Updates telemetry display
    }
}
