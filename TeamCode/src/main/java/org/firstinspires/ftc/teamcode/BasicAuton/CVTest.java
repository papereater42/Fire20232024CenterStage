package org.firstinspires.ftc.teamcode.BasicAuton;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FireHardwareMap;
import org.firstinspires.ftc.teamcode.vision.PositionDetector;

@Autonomous(name="CV Test", group="Auton")
@Config
public class CVTest extends LinearOpMode {

    FireHardwareMap robot = null;

    @Override
    public void runOpMode() {
        robot = new FireHardwareMap(this.hardwareMap);
        BasicAutoDriving autoDriving = new BasicAutoDriving(robot.frontLeftMotor, robot.frontRightMotor, robot.backLeftMotor, robot.backRightMotor);
        PositionDetector pd = new PositionDetector(hardwareMap, telemetry);
        pd.startStreaming();
        int count = 0;

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        while (opModeIsActive()){
            robot.led.setPattern(RevBlinkinLedDriver.BlinkinPattern.WHITE);
            pd.getPosition();
            telemetry.addData("Here", pd.getValue());
            telemetry.addData("count", count);
            count++;
            sleep(100);
            telemetry.update();

//CVVVVVVV
        }
    }
}
