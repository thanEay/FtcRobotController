package org.firstinspires.ftc.teamcode.biobuzz;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Flywheel;
import org.firstinspires.ftc.teamcode.hardware.Intake;
import org.firstinspires.ftc.teamcode.hardware.ServoGates;

@TeleOp
@Disabled
public class CalibrateFlywheel extends OpMode {

    Flywheel flywheel = new Flywheel();
    Intake intake = new Intake();
    private double flyWheelPower = 0.7;
    private boolean dpadWasPressed = false;
    private static final double intakeMaxPower = 1.0;
    ServoGates servoGates = new ServoGates();


    public void init() {
    flywheel.init(hardwareMap);
    intake.init(hardwareMap);
}

public void loop() {
    if (!dpadWasPressed) {
        if (gamepad1.dpad_up) {
            flyWheelPower += 0.05;
            dpadWasPressed = true;
        }
        if(gamepad1.dpad_down) {
            flyWheelPower -= 0.05;
            dpadWasPressed = true;
        }
    }
    if (!gamepad1.dpad_up && !gamepad1.dpad_down) {
        dpadWasPressed = false;
    }

    if (flyWheelPower > 1) {
        flyWheelPower = 1;
    }
    if (flyWheelPower < 0) {
        flyWheelPower = 0;
    }

    telemetry.addData("Flywheel power", (flyWheelPower * 100));
    telemetry.update();

    if (gamepad1.left_trigger_pressed) {
        flywheel.setFlywheelSpeed(flyWheelPower); // Set flywheel speed
    }
    else {
        flywheel.stopFlywheel();
    }

    if (gamepad1.right_trigger_pressed) {
        intake.setIntakeMotor(intakeMaxPower); // Set intake power
    }
    else if (gamepad1.right_bumper) {
        intake.setIntakeMotor(-0.7 * intakeMaxPower); // Allow intake to push ball back out
    }
    else {
            /* Uncomment for the intake to be continuously on
            if (continuousIntake) {
                intake.setIntakeMotor(0.5);
            }
            else {
                intake.setIntakeMotor(0);
            }
            */
        intake.setIntakeMotor(0);
    }
    if (gamepad1.touchpadWasPressed()) { // Code for disabling continuousIntake, currently practically unused
    }

    // Servo Gates
    if (gamepad1.cross) {
        servoGates.openPollenServo();
    }
    else {
        servoGates.closePollenServo();
    }

    if (gamepad1.circle) {
        servoGates.openNectarServo();
    }
    else {
        servoGates.closeNectarServo();
    }
    }
}
