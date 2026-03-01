import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Expedition", group="Linear Opmode")
public class Expedition extends LinearOpMode {

    private DcMotor rightfront;
    private DcMotor leftfront;
    private DcMotor linearSlideMotor;
    private Servo Claw;
    private DcMotor ArmRight;
    //private DcMotor ArmLeft;
    private Servo ClawArm;
    private boolean ArmRunning = false;
    private ElapsedTime xTimer = new ElapsedTime();


    @Override
    public void runOpMode() {
        // Initialize the hardware variables
        rightfront = hardwareMap.get(DcMotor.class, "rightfront");
        leftfront = hardwareMap.get(DcMotor.class, "leftfront");
        linearSlideMotor = hardwareMap.get(DcMotor.class, "linearSlideMotor");
        Claw = hardwareMap.get(Servo.class, "Claw");
        //ArmLeft = hardwareMap.get(DcMotor.class, "ArmLeft");
        ArmRight = hardwareMap.get(DcMotor.class, "ArmRight");
        ClawArm = hardwareMap.get(Servo.class, "ClawArm");
        double ServoPosition1;
        double ServoSpeed1;

        double ServoPosition2;
        double ServoSpeed2;

        // Set the motor to use encoders for the linear slide

        // Reverse one of the drive motors
        ArmRight.setDirection(DcMotor.Direction.REVERSE);

        //no power behavior
        ArmRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //ArmLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        int linearSlidePosition;
        // Set servo to mid position
        ServoPosition1 = 0.484;
        ServoSpeed1 = 0.009;

        ServoPosition2 = 0;
        ServoSpeed2 = 0.1;

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // Tank drive control
            // The Y axis of a joystick ranges from -1 in its topmost position to +1 in its bottommost position.
            // We negate this value so that the topmost position corresponds to maximum forward power.
            rightfront.setPower(-gamepad1.right_stick_y);
            leftfront.setPower(gamepad1.right_stick_y);
            rightfront.setDirection(DcMotor.Direction.FORWARD);
            leftfront.setDirection(DcMotor.Direction.FORWARD);
            // The Y axis of a joystick ranges from -1 in its topmost position to +1 in its bottommost position.
            // We negate this value so that the topmost position corresponds to maximum forward power.
            rightfront.setPower(-gamepad1.left_stick_x);
            leftfront.setPower(-gamepad1.left_stick_x);
            telemetry.addData("Left Pow", leftfront.getPower());
            telemetry.addData("Right Pow", rightfront.getPower());


            linearSlidePosition = linearSlideMotor.getCurrentPosition();

            // Control the linear slide with the gamepad
            if (linearSlidePosition > 120){
                linearSlideMotor.setPower(0.0);
            } else {
                if (gamepad2.dpad_down) {
                    linearSlideMotor.setPower(0.7); // Move up
                } else if (gamepad2.dpad_up) {
                    linearSlideMotor.setPower(-0.7); // Move down
                } else {
                    linearSlideMotor.setPower(-0.05); // Stop
                }
            }


            telemetry.addData("linearSlideMotor", linearSlideMotor.getPower());

            // Control the plane servo with the gamepad
            Claw.setDirection(Servo.Direction.REVERSE);
            //Claw.setPosition(gamepad2.right_trigger);
            // Use gamepad X and B to open close servo
            if (gamepad2.x) {
                ServoPosition2 += ServoSpeed2;
            }
            if (gamepad2.b) {
                ServoPosition2 += -ServoSpeed2;
            }
            // Keep Servo position in valid range
            ServoPosition2 = Math.min(Math.max(ServoPosition2, 0), 1);
            Claw.setPosition(ServoPosition2);



            if (Math.abs(gamepad2.left_stick_y) > -0.1) {
                //ArmLeft.setPower(0.9*gamepad2.left_stick_y); // Move up
                ArmRight.setPower(0.9*gamepad2.left_stick_y); // Move up
            } else if (Math.abs(gamepad2.left_stick_y) > 0.1) {
                ArmRight.setPower(-0.05*gamepad2.left_stick_y);
                //ArmLeft.setPower(-0.05*gamepad2.left_stick_y);
            } else {
                //ArmLeft.setPower(-0.05); // Stop
                ArmRight.setPower(-0.05); // Stop
            }

            // changed to joystick due to re-Positioning
            //the multiplyed value slows the rotation
            ClawArm.setDirection(Servo.Direction.REVERSE);



            // Use gamepad X and B to open close servo
            if (gamepad2.right_bumper) {
                ServoPosition1 += ServoSpeed1;
            }
            if (gamepad2.left_bumper) {
                ServoPosition1 += -ServoSpeed1;
            }
            // Keep Servo position in valid range
            ServoPosition1 = Math.min(Math.max(ServoPosition1, 0), 1);
            ClawArm.setPosition(ServoPosition1);




            // Send telemetry data to the driver station
            telemetry.addData("Left Power", leftfront.getPower());
            telemetry.addData("Right Power", rightfront.getPower());
            telemetry.addData("Linear Slide Position", linearSlideMotor.getCurrentPosition());
            telemetry.addData("Servo", ServoPosition1);
            telemetry.addData("Servo", ServoPosition2);
            telemetry.update();

        }
    }
}