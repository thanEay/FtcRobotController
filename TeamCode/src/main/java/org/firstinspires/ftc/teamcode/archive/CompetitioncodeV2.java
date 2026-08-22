package org.firstinspires.ftc.teamcode.archive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//Since we are dealing with Java files you must made sure to close each complete code statement withe Semi Colon or it will not work you must make sure all of the brackets and braces match up in the end otherwise code WILL BE LOST TRUST I LEARNED THE HARD WAY!

/* You MUST make sure that bot the class and the name= the name of the file on the save so if the save file is called happydays.java you must make sure it says  
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="happydays"Linear Opmode")
public class happydaysLinearOpMode {

You do not and Should not put the .java at the end otherwise you will get an error
this is because the .java tells the computer that it is a java file for python it is .py and so on
*/

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="CompetitioncodeV2", group="Linear Opmode")
public class CompetitioncodeV2 extends LinearOpMode {

    private DcMotor rightfront;
    private DcMotor leftfront;
    private DcMotor flywheel;
    private Servo swivelarm;
    private DcMotor secondflywheel;
    private DcMotor conveyorbelt;
    private DcMotor intake;
// This is where you name your motors private starts it ALWAYS then you tell tyhe code what it is either DcMotor Servo of if its a custom import library you name those here
// MAKE SURE THEY MATCH WHAT IS IN THE DRIVER HUB CONFIG OTHERWISE THE CODE WILL TWEAK TF OUT!!!!!!!!!!!!!!!!!
    @Override
    public void runOpMode() {
        // Initialize the hardware variables
        rightfront = hardwareMap.get(DcMotor.class, "rightfront");
        leftfront = hardwareMap.get(DcMotor.class, "leftfront");
        flywheel = hardwareMap.get(DcMotor.class, "flywheel");
        swivelarm = hardwareMap.get(Servo.class, "swivelarm");
        secondflywheel = hardwareMap.get(DcMotor.class, "secondflywheel");
        conveyorbelt = hardwareMap.get(DcMotor.class, "conveyorbelt");
        intake = hardwareMap.get(DcMotor.class, "intake");
        // Wait for the game to start (driver presses PLAY)
        //This is where the name of it is made for the code to follow it needs to be the same as the name of it in the private this clasifies it for the code

        double ServoPosition1;
        double ServoSpeed1;
//
        double ServoPosition2;
        double ServoSpeed2;

// Doubling a variablew is basically saying I know this is wrong and I want you to ignore that its wrogn so that its right
//I have no idea ho w this works if were being Totally honest ALl I knowis that it does We really will never have to mess with these statements for the most part

// Set servo to mid position
        ServoPosition1 = 0.484;
        ServoSpeed1 = 0.009;
//
        ServoPosition2 = 0;
        ServoSpeed2 = 0.1;
        waitForStart();

        // Run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // Tank drive control
            // The Y axis of a joystick ranges from -1 in its topmost position to +1 in its bottommost position.
            // We negate this value so that the topmost position corresponds to maximum forward power.

            rightfront.setDirection(DcMotor.Direction.FORWARD);
            leftfront.setDirection(DcMotor.Direction.FORWARD);
            flywheel.setDirection(DcMotor.Direction.REVERSE);
            secondflywheel.setDirection(DcMotor.Direction.FORWARD);
            conveyorbelt.setDirection(DcMotor.Direction.REVERSE);

            // The Y axis of a joystick ranges from -1 in its topmost position to +1 in its bottommost position.
            // We negate this value so that the topmost position corresponds to maximum forward power.
            // Tank drive: each joystick controls its own side
            double leftPower  = gamepad1.left_stick_y;   // push forward = positive
            double rightPower = -gamepad1.right_stick_y;

            leftfront.setPower(leftPower * 0.75);
            rightfront.setPower(rightPower * 0.75);

			/* We can multiply the power by and amout to set it that much
equal by percentage to 0.75 is 75% of the power that you would typiically give to it without it
it is the default amount which it 1 but in realuity it is 126 sdince motos operate on as 0 -126 power range for sonme reason idk why dont ask
The point vallues extend indefinelt so it could be 0.00000000000000000000000000000000001 if you need it to be it allows for VERY fine tuning on the motors
*/
            // Close range firing

            flywheel.setPower(gamepad2.left_trigger * 0.62);
            secondflywheel.setPower(gamepad2.left_trigger * 0.62);

// Far range firing

            flywheel.setPower(gamepad2.left_stick_y * 0.75);
            secondflywheel.setPower(gamepad2.left_stick_y * 0.75);

// If X is pressed, run the Motor forward
            if (gamepad2.y) {
                conveyorbelt.setPower(1.0);   // full speed forward
            } else {
                conveyorbelt.setPower(0);     // stop when x is not pressed
            }

            if (gamepad2.x) {
                conveyorbelt.setPower(-1.0);// full speed forward
            } else {
                conveyorbelt.setPower(0);     // stop when x is not pressed
            }

            intake.setPower(gamepad2.right_trigger * 1);

            telemetry.addData("Intake", intake.getPower());
            telemetry.addData("Flywheel Power", flywheel.getPower());
            telemetry.addData("Left Power", leftfront.getPower());
            telemetry.addData("Right Power", rightfront.getPower());
            telemetry.addData("Servo", ServoPosition1);
            telemetry.addData("Servo", ServoPosition2);
        }
    }
}

// For the Love of God DO NOT MESS WITH THIS CODE UNLESS YOU ARE AUTHORIZED TO BY ME!
// OR COPY AND PASTE IT I MADE IT SPECIFICALLY TO NOT BE REPLICATED IT WILL NOT WORK IF YOU TRY AND REPLICATE MY CODE
// IF YOU DO DO NOT COME CRYING TO ME WHEN NOTHING ELSE WORKS BECAUSE NOTHING ELSE WILL WORK IF YOU ATTEMPT TO REPLICATE THIS CODE!!!!!!