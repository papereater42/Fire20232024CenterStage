package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**

 *
 * 1) Axial:    Driving forward and backward               Left-joystick Forward/Backward
 * 2) Lateral:  Strafing right and left                     Left-joystick Right and Left
 * 3) Yaw:      Rotating Clockwise and counter clockwise    Right-joystick Right and Left
 *

 */

@TeleOp(name="Field Oriented TeleOp", group="Linear Opmode")
public class FieldOriented extends LinearOpMode {

    /*
    Controls for gamepad2
    Dpad down - lower slides
    Dpad up - raise slides to high level
    Dpad right - raise slides to mid level
    Y - open door
    B - close door
    A - tilt box to scoring position
    X - return box to 0
    Left bumper - toggle separator
    Right bumper - toggle hook servo up or down
    Left joystick y - spin intake wheels
    Right joystick x - actuator motor
     */


    //ServoImplEx servo;
    //PwmControl.PwmRange range = new PwmControl.PwmRange(533,2425);
    // Declare OpMode members for each of the 4 motors.
    private ElapsedTime runtime = new ElapsedTime();
    private FireHardwareMap HW = null;
    private ActiveLocation activeLocation = null;
    @Override

    public void runOpMode() {
        HW = new FireHardwareMap(this.hardwareMap);
        activeLocation = new ActiveLocation(HW);
        //servo = hardwareMap.get(ServoImplEx.class, "left_hand");
        //servo.setPwmRange(range);


        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        boolean separated = false;
        boolean hookUp = false;
        double currentAngle;
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double max;
            double i =0.0;
            currentAngle = activeLocation.getTrimmedAngleInRadians();


            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double axial   = -gamepad1.left_stick_y * Math.cos(currentAngle) +
                    gamepad1.left_stick_x * Math.sin(currentAngle); // Note: pushing stick forward gives negative value
            double lateral =  gamepad1.left_stick_x * Math.cos(currentAngle) -
                    -gamepad1.left_stick_y * Math.sin(currentAngle);
            double yaw     =  gamepad1.right_stick_x;


            double axial2 =  -gamepad2.left_stick_y;
//            double lateral2 =  gamepad2.left_stick_x * 1.1;
            double yaw2     =  gamepad2.right_stick_x;


            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double leftFrontPower  = axial + lateral + yaw;
            double rightFrontPower = axial - lateral - yaw;
            double leftBackPower   = axial - lateral + yaw;
            double rightBackPower  = axial + lateral - yaw;

            double intakeWheelPower = (gamepad2.right_trigger-gamepad2.left_trigger);


            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));
            i = gamepad1.right_trigger;
            if (max > 1) {
                leftFrontPower  /= max;
                rightFrontPower /= max;
                leftBackPower   /= max;
                rightBackPower  /= max;
//                axial2 /=max;
                i /= max;
//                (lateral2)/=max;
                yaw2 /=max;
            }
            if(gamepad1.right_bumper){
                i=i;
            }
            else{
                leftFrontPower  /= 2;
                rightFrontPower /= 2;
                leftBackPower   /= 2;
                rightBackPower  /= 2;
            }



            double doorServoPower = yaw2;



            double leftRightServoPower = -gamepad2.right_stick_y;


            double separatorServoPower;

            double hookServoPower;

            if (gamepad2.left_bumper) {
                separatorServoPower = 0.9;
            }  else if (gamepad2.right_bumper) {
                separatorServoPower = -0.9;
            } else {
                separatorServoPower = 0.0;
            }
            if (1<2) {
                HW.led.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
            }
            else {
                HW.led.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
            }
            if (gamepad2.dpad_left) {
                hookServoPower = 1;
            }  else if (gamepad2.dpad_right) {
                hookServoPower = -1;
            } else {
                hookServoPower = 0.0;
            }
            double actuatorPower;
            if (gamepad2.dpad_left) {
                actuatorPower = 1;
            }  else if (gamepad2.dpad_right) {
                actuatorPower = -1;
            } else {
                actuatorPower = 0.0;
            }


            axial2 = axial2;
//
//            lateral2 = lateral2/1.5;
//
            yaw2 = yaw2/1.5;

            // Send calculated power to wheels
            HW.frontLeftMotor.setPower(leftFrontPower);
            HW.frontRightMotor.setPower(rightFrontPower);
            HW.backLeftMotor.setPower(leftBackPower);
            HW.backRightMotor.setPower(rightBackPower);
            HW.intakeMotor.setPower(intakeWheelPower);
            HW.actuatorMotor.setPower(actuatorPower);

            HW.doorServo.setPower(doorServoPower);
            HW.boxRightServo.setPower(-leftRightServoPower*0.5);
            HW.boxLeftServo.setPower(leftRightServoPower*0.5);
            HW.separatorServo.setPower(separatorServoPower);
            HW.slideRightMotor.setPower(axial2);
            HW.slideLeftMotor.setPower(axial2);
            HW.hookServo.setPower(hookServoPower);



            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Tim: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
//            telemetry.addData("Servo  left/Right", "%4.2f, %4.2f", axial2, axial2);
//            telemetry.addData("Intake Operational: ", HW.intakeMotor.isBusy());
            telemetry.addData("Intake Number: ", i);
//            telemetry.addData("Current frontLeftMotor Encoder Position: ", HW.frontLeftMotor.getCurrentPosition());
//            telemetry.addData("frontLeftMotor Operational: ", HW.frontLeftMotor.isBusy());
//            telemetry.addData("Current frontRightMotor Encoder Position: ", HW.frontRightMotor.getCurrentPosition());
//            telemetry.addData("frontRightMotor Operational: ", HW.frontRightMotor.isBusy());
//            telemetry.addData("Current backLeftMotor Encoder Position: ", HW.backLeftMotor.getCurrentPosition());
//            telemetry.addData("backLeftMotor Operational: ", HW.backLeftMotor.isBusy());
//            telemetry.addData("Current backRightMotor Encoder Position: ", HW.backRightMotor.getCurrentPosition());
//            telemetry.addData("backRightMotor Operational: ", HW.backRightMotor.isBusy());
            telemetry.addData("slideLeftMotorTicks target ", HW.slideLeftMotor.getTargetPosition());
            telemetry.addData("boxLeftServo pow: ", HW.boxLeftServo.getPower());
            telemetry.addData("doorServo power: ", HW.doorServo.getPower());
            telemetry.addData("doorServo exists: ", HW.doorServo.getDeviceName());
            telemetry.addData("Color red: ", HW.color.red());
            telemetry.addData("Color greed: ", HW.color.green());
            telemetry.addData("Color blue:", HW.color.blue());
            telemetry.update();

        }
    }
}