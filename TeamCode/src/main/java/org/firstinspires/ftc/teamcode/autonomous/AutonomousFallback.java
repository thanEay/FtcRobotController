package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Flywheel;
import org.firstinspires.ftc.teamcode.hardware.MecanumDrivetrain;

@Autonomous(name = "Autonomous Fallback", group = "Autonomous")
public class AutonomousFallback extends LinearOpMode {

    MecanumDrivetrain drivetrain = new MecanumDrivetrain();
    Flywheel flywheel = new Flywheel();
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        drivetrain.init(hardwareMap); // Initializes drivetrain hardware
        flywheel.init(hardwareMap); // Initializes flywheel hardware

        telemetry.addData("Status", "Initialized"); // Displays status on Driver Station
        telemetry.update(); // Updates telemetry display

        waitForStart(); // Waits for driver to press Play

        runtime.reset(); // Resets timer


        // Drive forward a little
        if (opModeIsActive()) {
            drivetrain.drive(0.5, 0.0, 0.0); // Drives forward at 50% power
            sleep(100); // Drives forward for 0.1 seconds
            drivetrain.drive(0.0, 0.0, 0.0); // Stops drivetrain
        }

        // Strafe left
        if (opModeIsActive()) {
            drivetrain.drive(0.0, -0.5, 0.0); // Strafes right at 50% power
            sleep(2000);
            drivetrain.drive(0.0, 0.0, 0.0); // Stops drivetrain
        }

        // Stop all systems
        flywheel.stopFlywheel(); // Stops flywheel motor
        drivetrain.drive(0.0, 0.0, 0.0); // Ensures drivetrain is stopped

        telemetry.addData("Status", "Autonomous Finished"); // Signals autonomous is complete
        telemetry.update(); // Updates telemetry display
    }
}
