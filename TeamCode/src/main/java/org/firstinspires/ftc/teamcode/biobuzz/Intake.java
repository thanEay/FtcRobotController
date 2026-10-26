package org.firstinspires.ftc.teamcode.biobuzz;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Intake")
public class Intake extends OpMode {
    private static final double MAX_POWER = 1.0;

    private DcMotor intakeMotor;

    @Override
    public void init() {
        intakeMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    @Override
    public void loop() {
        double power = -gamepad1.left_stick_y * MAX_POWER;
        intakeMotor.setPower(power);
        telemetry.addData("Intake power", power);
    }

    @Override
    public void stop() {
        intakeMotor.setPower(0);
    }
}
