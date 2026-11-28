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

        // Step 1: Spin up flywheel
        if (opModeIsActive()) {
            flywheel.setFlywheelSpeed(0.6); // Sets flywheel speed to 60% power
            sleep(2000); // Waits 2 seconds for flywheel to spin up
        }

        // Step 2: Drive forward
        if (opModeIsActive()) {
            drivetrain.drive(0.5, 0.0, 0.0); // Drives forward at 50% power
            sleep(1000); // Drives forward for 1 second
            drivetrain.drive(0.0, 0.0, 0.0); // Stops drivetrain
        }

        // Step 3: Strafe right
        if (opModeIsActive()) {
            drivetrain.drive(0.0, 0.5, 0.0); // Strafes right at 50% power
            sleep(2000); // Strafes right for 2 seconds
            drivetrain.drive(0.0, 0.0, 0.0); // Stops drivetrain
        }

        // Step 4: Stop all systems
        flywheel.stopFlywheel(); // Stops flywheel motor
        drivetrain.drive(0.0, 0.0, 0.0); // Ensures drivetrain is stopped

        telemetry.addData("Status", "Autonomous Finished"); // Signals autonomous is complete
        telemetry.update(); // Updates telemetry display
    }
}
