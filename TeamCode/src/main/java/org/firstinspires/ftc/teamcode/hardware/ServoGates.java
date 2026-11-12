package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoGates {
    private Servo pollenServo;
    private Servo nectarServo;
    private double pollenServoOpenPosition = 0;
    private  double pollenServoClosedPosition = 1;
    private double nectarServoOpenPosition = 1;
    private double nectarServoClosedPosition = 0;

    public void init(HardwareMap hwMap) {
        pollenServo = hwMap.get(Servo.class, "pollenServo");
        nectarServo = hwMap.get(Servo.class, "nectarServo");
    }

    public void openPollenServo() {
        pollenServo.setPosition(pollenServoOpenPosition);
    }

    public void closePollenServo() {
        pollenServo.setPosition(pollenServoClosedPosition);
    }

    public void openNectarServo() {
        nectarServo.setPosition(nectarServoOpenPosition);
    }

    public void closeNectarServo() {
        nectarServo.setPosition(nectarServoClosedPosition);
    }

}
