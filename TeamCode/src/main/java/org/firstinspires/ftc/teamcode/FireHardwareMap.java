package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

public class FireHardwareMap {
    //create motors
    public DcMotor frontRightMotor = null;
    public DcMotor frontLeftMotor = null;
    public DcMotor backRightMotor = null;
    public DcMotor backLeftMotor = null;

    public DcMotor slideLeftMotor = null;

    public DcMotor slideRightMotor = null;

    public DcMotor actuatorMotor = null;

    //create servos
    public DcMotor IntakeMotor = null;
    public Servo boxLeftServo = null;
    public Servo boxRightServo = null;
    public Servo boxOpenCloseServo = null;
    public Servo maintainIntakeServo = null;
    public Servo hookServo = null;

    public BNO055IMU imu = null;


    //Hardware Map object
    com.qualcomm.robotcore.hardware.HardwareMap HardwareMap = null;

    public ElapsedTime runtime = new ElapsedTime();

    public FireHardwareMap(com.qualcomm.robotcore.hardware.HardwareMap hwmap){

        initialize(hwmap);
    }

    private void initialize(com.qualcomm.robotcore.hardware.HardwareMap hwmap){
        HardwareMap = hwmap;
        //the name of device should change based on name
        frontRightMotor = HardwareMap.get(DcMotor.class, "frontRightMotor");
        frontLeftMotor = HardwareMap.get(DcMotor.class, "frontLeftMotor");
        backRightMotor = HardwareMap.get(DcMotor.class, "backRightMotor");
        backLeftMotor = HardwareMap.get(DcMotor.class, "backLeftMotor");
        IntakeMotor = HardwareMap.get(DcMotor.class, "intakeMotor");
        actuatorMotor = HardwareMap.get(DcMotor.class, "actuatorMotor");
        slideLeftMotor = HardwareMap.get(DcMotor.class, "slideLeftMotor");
        slideRightMotor = HardwareMap.get(DcMotor.class, "slideRightMotor");



        //Making servo
        //leftServo = HardwareMap.get(Servo.class, "leftServo");

        //Set up motor direction

        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        IntakeMotor.setDirection(DcMotor.Direction.FORWARD);

        slideLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        slideRightMotor.setDirection((DcMotor.Direction.FORWARD));

        actuatorMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        //Set motor mode
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        IntakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);






        //Set zero power behavior

        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        IntakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        actuatorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //Set 0 power
        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        IntakeMotor.setPower(0);
        slideLeftMotor.setPower(0);
        slideRightMotor.setPower(0);
        actuatorMotor.setPower(0);



        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        //return value of radians
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        //gets imu from rev hardware map and connects it to code
        imu = hwmap.get(BNO055IMU.class, "imuex");
        //sets the settings we declared above.
        imu.initialize(parameters);
        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);


    }








}