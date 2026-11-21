package org.firstinspires.ftc.teamcode.biobuzz;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Flywheel;
import org.firstinspires.ftc.teamcode.hardware.Intake;

@TeleOp
public class CalibrateFlywheel extends OpMode {

    Flywheel flywheel = new Flywheel();
    Intake intake = new Intake();
    private double flyWheelPower = 0.7;
    private boolean dpadWasPressed = false;

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
    }
}
