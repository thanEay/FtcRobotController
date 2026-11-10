package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoGates {
    private Servo pollenServo;
    private Servo nectarServo;

    public void init(HardwareMap hwMap) {
        pollenServo = hwMap.get(Servo.class, "pollenServo");
        nectarServo = hwMap.get(Servo.class, "nectarServo");
    }
}
