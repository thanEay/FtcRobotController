package org.firstinspires.ftc.teamcode.competition;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardware.Flywheel;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.hardware.ServoGates;
import org.firstinspires.ftc.teamcode.hardware.SimpleMecanumDrivetrain;

@TeleOp(name="Comp Code TeleOp")
public class Shockwave11_28_26 extends OpMode {

MecanumDrivetrain drivetrain = new MecanumDrivetrain();
Flywheel flywheel = new Flywheel();
Intake intake = new Intake();
ServoGates servoGates = new ServoGates();
private static final double intakeMaxPower = 1.0;
double flywheelPower = 0.7;
double forward, strafe, rotate;
private boolean dpadWasPressed = false;


    @Override
    public void init() {
        drivetrain.init(hardwareMap);
        flywheel.init(hardwareMap);
        intake.init(hardwareMap);
        servoGates.init(hardwareMap);
    }

    @Override
    public void loop() {

        // Mecanum drivetrain
        if (Math.abs(gamepad1.left_stick_y) > 0.1) {
            forward = -gamepad1.left_stick_y;
        }
        else{
            forward = 0;
        }
        if (Math.abs(gamepad1.left_stick_x) > 0.1) {
            strafe = gamepad1.left_stick_x;
        }
        else{
            strafe = 0;
        }
        if (Math.abs(gamepad1.right_stick_x) > 0.1) {
            rotate = gamepad1.right_stick_x;
        }
        else{
            rotate = 0;
        }
        // Dpad controls for mecanum
        if (gamepad1.dpad_up) {
            forward = 1;
        }
        if (gamepad1.dpad_left) {
            strafe = -1;
        }
        if (gamepad1.dpad_right) {
            strafe = 1;
        }
        if (gamepad1.dpad_down) {
            forward = -1;
        }
        drivetrain.drive(forward, strafe, rotate); // Use .driveFieldRelative for field relative or .drive for robot relative
        if (gamepad1.touchpadWasPressed()) {
            drivetrain.resetIMU(); // Reset the IMU to ensure accurate field-relative driving
        }

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
            flywheel.setFlywheelSpeed(flywheelPower); // Set flywheel speed
        }
        else {
            flywheel.stopFlywheel();
        }
        // Adjust flywheel power on the fly
        if (!dpadWasPressed) {
            if (gamepad2.dpad_up) {
                flywheelPower += 0.05;
                dpadWasPressed = true;
            }
            else if(gamepad2.dpad_down) {
                flywheelPower -= 0.05;
                dpadWasPressed = true;
            }
        }
        if (!gamepad2.dpad_up && !gamepad2.dpad_down) {
            dpadWasPressed = false;
        }

        if (flywheelPower > 1) {
            flywheelPower = 1;
        }
        if (flywheelPower < 0) {
            flywheelPower = 0;
        }
        // Print flywheel power as a percentage
        double flywheelPowerPercent = Math.round(flywheelPower * 100.0);
        telemetry.addData("Flywheel Max Power %", flywheelPowerPercent + "%");

        // Servo Gates
        if (gamepad2.cross) {
            servoGates.openNectarServo();
        }
        else {
            servoGates.closeNectarServo();
        }
    }

}
