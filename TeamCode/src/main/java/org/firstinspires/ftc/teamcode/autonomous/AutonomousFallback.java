package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Flywheel;
import org.firstinspires.ftc.teamcode.hardware.MecanumDrivetrain;

@Autonomous
public class AutonomousFallback extends LinearOpMode {

    MecanumDrivetrain drivetrain = new MecanumDrivetrain();
    Flywheel flywheel = new Flywheel();
    ElapsedTime runtime = new ElapsedTime();

    enum AutoState {
        WAIT_AT_FIRST,
        DRIVE_FORWARD,
        DONE
    }

    AutoState state = AutoState.DRIVE_FORWARD;

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
                case WAIT_AT_FIRST:

            }
        }
    }

}
