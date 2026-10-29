package org.firstinspires.ftc.teamcode.biobuzz;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.Intake;

// This code uses the config file "Flywheel"
@TeleOp(name = "Intake")
public class UseIntake extends OpMode {
    private static final double MAX_POWER = 1.0;
    Intake intake = new Intake();

    @Override
    public void init() {
        intake.init(hardwareMap);
    }

    @Override
    public void loop() {
        double power = -gamepad1.left_stick_y * MAX_POWER;
        intake.setIntakeMotor(power);
    }

    @Override
    public void stop() {
        intake.setIntakeMotor(0);
    }
}
