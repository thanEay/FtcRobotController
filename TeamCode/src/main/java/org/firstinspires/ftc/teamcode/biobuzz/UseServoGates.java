package org.firstinspires.ftc.teamcode.biobuzz;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.ServoGates;

@TeleOp
public class UseServoGates extends OpMode {
    ServoGates servoGates = new ServoGates();

    @Override
    public void init() {
        servoGates.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.right_trigger_pressed) {
            servoGates.openPollenServo();
        }
        else {
            servoGates.closePollenServo();
        }
        if (gamepad1.left_trigger_pressed) {
            servoGates.openNectarServo();
        }
        else {
            servoGates.closeNectarServo();
        }

    }
}
