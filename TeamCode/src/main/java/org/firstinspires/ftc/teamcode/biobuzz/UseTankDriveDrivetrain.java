package org.firstinspires.ftc.teamcode.biobuzz;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.TankDriveDrivetrain;

@TeleOp
public class UseTankDriveDrivetrain extends OpMode {

    TankDriveDrivetrain drivetrain = new TankDriveDrivetrain();

    @Override
        public void init() {drivetrain.init(hardwareMap);
    }

    @Override
    public void loop() {
        double leftPower = -gamepad1.left_stick_y * 0.3;
        double rightPower = -gamepad1.right_stick_y * 0.3;
        drivetrain.setBackLeftDcMotor(leftPower);
        drivetrain.setBackRightDcMotor(rightPower);

        telemetry.addData("Left Power", leftPower);
        telemetry.addData("Right Power", rightPower);
        telemetry.addData("Left Motor Revolutions", drivetrain.getBackLeftMotorRevolutions());
        telemetry.addData("Right Motor Revolutions", drivetrain.getBackRightMotorRevolutions());
    }
}
