package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class TankDriveDrivetrain {

    private DcMotor frontLeftDcMotor;
    private DcMotor frontRightDcMotor;
    private DcMotor backLeftDcMotor;
    private DcMotor backRightDcMotor;

    public void init(HardwareMap hwMap) {
        frontLeftDcMotor = hwMap.get(DcMotor.class, "frontLeft");
        frontRightDcMotor = hwMap.get(DcMotor.class, "frontRight");
        backLeftDcMotor = hwMap.get(DcMotor.class, "backLeft");

    }

}
