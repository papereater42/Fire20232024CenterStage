package org.firstinspires.ftc.teamcode.BasicAuton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FireHardwareMap;

@Autonomous(name="blueFarAuton", group="Auton")
public class blueFarAuton extends LinearOpMode {

    FireHardwareMap robot = null;

    @Override
    public void runOpMode() {
        robot = new FireHardwareMap(this.hardwareMap);
        BasicAutoDriving autoDriving = new BasicAutoDriving(robot.frontLeftMotor, robot.frontRightMotor, robot.backLeftMotor, robot.backRightMotor);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        telemetry.addData("Status", "Initialized");

        if (opModeIsActive()){


            scorePreLoaded(0, autoDriving);
            sleep(2000);


        }

    }

    public void scorePreLoaded(int tickID, BasicAutoDriving bad) {
        if (tickID == 0) { // middle tick
            bad.drive(-65);
            sleep(2000);
        } else if (tickID == 1) {
            bad.turn(-10);
            sleep(500);
            bad.drive(-38);
            sleep(2000);
        } else if (tickID == 2) {
//            bad.drive(-52);
//            sleep(2000);
//            bad.turn(50);
//            sleep(1000);
//            bad.drive(-15);
//            sleep(1000);
            scorePreLoaded(0, bad);
        }
    }
}
