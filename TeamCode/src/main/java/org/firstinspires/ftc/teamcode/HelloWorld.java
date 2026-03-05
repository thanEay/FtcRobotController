package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp
public class HelloWorld extends OpMode {

//  This runs on initialization
    @Override
    public void init() {
        telemetry.addData("Hello", "Ethan");
    }

//  This runs as a loop
    @Override
    public void loop() {

    }
}
