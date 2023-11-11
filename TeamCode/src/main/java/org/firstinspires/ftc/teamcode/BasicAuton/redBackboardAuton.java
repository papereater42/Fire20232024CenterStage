package org.firstinspires.ftc.teamcode.BasicAuton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.FireHardwareMap;

@Autonomous(name="redBackboardAuton", group="Auton")
public class redBackboardAuton extends LinearOpMode {

    FireHardwareMap robot = null;

    @Override
    public void runOpMode() {
        robot = new FireHardwareMap(this.hardwareMap);
        BasicAutoDriving autoDriving = new BasicAutoDriving(robot.frontLeftMotor, robot.frontRightMotor, robot.backLeftMotor, robot.backRightMotor);


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        telemetry.addData("Status", "Initialized");

        if (opModeIsActive()){


            scorePreLoaded(2, autoDriving);


        }

    }

    public void scorePreLoaded(int tickID, BasicAutoDriving bad) {
        if (tickID == 0) { // middle tick
            bad.drive(-65);
            sleep(2000);
            bad.drive(64);
            sleep(2000);
            bad.turn(-90);
            sleep(1500);
            bad.drive(-46);
            sleep(9000);
        } else if (tickID == 1) {
            scorePreLoaded(0, bad);
        } else if (tickID == 2) {
            bad.turn(-12);
            sleep(500);
            bad.drive(-40);
            sleep(2000);
            bad.turn(12);
            sleep(500);
            bad.drive(32);
            sleep(2000);
            bad.turn(-90);
            sleep(1500);
            bad.drive(-40);
            sleep(9000);
        }
    }
}
