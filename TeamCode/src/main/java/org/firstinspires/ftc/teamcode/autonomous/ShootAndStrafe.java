package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Flywheel;
import org.firstinspires.ftc.teamcode.hardware.MecanumDrivetrain;

@Autonomous(name = "Shoot and Strafe", group = "Autonomous")
public class ShootAndStrafe extends LinearOpMode {

    MecanumDrivetrain drivetrain = new MecanumDrivetrain();
    Flywheel flywheel = new Flywheel();
    ElapsedTime runtime = new ElapsedTime();

    private enum AutoState {
        START_FLYWHEEL,
        DRIVE_FORWARD,
        STRAFE_RIGHT,
        STOP_ROBOT,
        DONE
    }

    private AutoState state = AutoState.START_FLYWHEEL;

    @Override
    public void runOpMode() {
        drivetrain.init(hardwareMap);
        flywheel.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        runtime.reset();

        while (opModeIsActive() && !isStopRequested()) {

            switch (state) {
                case START_FLYWHEEL:
                    flywheel.setFlywheelSpeed(0.6); // Turn on flywheel to 60% power
                    runtime.reset();
                    state = AutoState.DRIVE_FORWARD;
                    break;

                case DRIVE_FORWARD:
                    drivetrain.drive(0.5, 0.0, 0.0); // Drive forward at 50% power
                    if (runtime.seconds() >= 1.0) { // Drive forward for 1 second
                        drivetrain.drive(0.0, 0.0, 0.0); // Stop driving
                        runtime.reset();
                        state = AutoState.STRAFE_RIGHT;
                    }
                    break;

                case STRAFE_RIGHT:
                    drivetrain.drive(0.0, 0.5, 0.0); // Strafe right at 50% power
                    if (runtime.seconds() >= 4.0) { // Strafe right for 4 seconds
                        drivetrain.drive(0.0, 0.0, 0.0); // Stop driving
                        state = AutoState.STOP_ROBOT;
                    }
                    break;

                case STOP_ROBOT:
                    drivetrain.drive(0.0, 0.0, 0.0);
                    flywheel.stopFlywheel(); // Stop flywheel motor
                    state = AutoState.DONE;
                    break;

                case DONE:
                default:
                    telemetry.addLine("Autonomous Finished");
                    break;
            }

            telemetry.addData("Current State", state);
            telemetry.addData("State Time", "%.1f sec", runtime.seconds());
            telemetry.update();
        }
    }
}
