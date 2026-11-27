package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.Flywheel;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.hardware.ServoGates;

public class AutonomousHelperMethods {

    MecanumDrivetrain drivetrain = new MecanumDrivetrain();
    Flywheel flywheel = new Flywheel();
    Intake intake = new Intake();
    ServoGates servoGates = new ServoGates();
    private static final double intakeMaxPower = 1.0;
    double flywheelPower = 0.7;
    double forward, strafe, rotate;

    public void init(HardwareMap hwMap) {
        drivetrain.init(hwMap);
        flywheel.init(hwMap);
        intake.init(hwMap);
        servoGates.init(hwMap);
    }

    public void moveForward() {
        drivetrain.driveFieldRelative(1, 0, 0);
    }
    public void moveBack() {
        drivetrain.driveFieldRelative(-1, 0, 0);
    }
    public void moveLeft() {
        drivetrain.driveFieldRelative(0, -1, 0);
    }
    public void moveRight() {
        drivetrain.driveFieldRelative(0, 1, 0);
    }
}
