package org.firstinspires.ftc.teamcode.subsystems;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_WITHOUT_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import static org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants.BACK_LEFT_MOTOR_NAME;
import static org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants.BACK_RIGHT_MOTOR_NAME;
import static org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants.FRONT_LEFT_MOTOR_NAME;
import static org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants.FRONT_RIGHT_MOTOR_NAME;
import static org.firstinspires.ftc.teamcode.Constants.IMUConstants.IMU_NAME;
import static org.firstinspires.ftc.teamcode.Constants.IMUConstants.IMU_ORIENTATION;

public class DrivetrainSubsystem extends SubsystemBase {

    private final DcMotor frontLeft;
    private final DcMotor frontRight;
    private final DcMotor backLeft;
    private final DcMotor backRight;

    private final IMU imu;

    private final Telemetry telemetry;

    public DrivetrainSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        this.frontLeft = hardwareMap.get(DcMotor.class, FRONT_LEFT_MOTOR_NAME);
        this.frontRight = hardwareMap.get(DcMotor.class, FRONT_RIGHT_MOTOR_NAME);
        this.backLeft = hardwareMap.get(DcMotor.class, BACK_LEFT_MOTOR_NAME);
        this.backRight = hardwareMap.get(DcMotor.class, BACK_RIGHT_MOTOR_NAME);
        this.imu = hardwareMap.get(IMU.class, IMU_NAME);

        frontLeft.setZeroPowerBehavior(BRAKE);
        frontRight.setZeroPowerBehavior(BRAKE);
        backLeft.setZeroPowerBehavior(BRAKE);
        backRight.setZeroPowerBehavior(BRAKE);

        frontLeft.setDirection(FORWARD);
        frontRight.setDirection(REVERSE);
        backLeft.setDirection(FORWARD);
        backRight.setDirection(REVERSE);

        frontLeft.setMode(RUN_WITHOUT_ENCODER);
        frontRight.setMode(RUN_WITHOUT_ENCODER);
        backLeft.setMode(RUN_WITHOUT_ENCODER);
        backRight.setMode(RUN_WITHOUT_ENCODER);

        imu.initialize(new IMU.Parameters(IMU_ORIENTATION));
    }

    /**
     * Returns the current yaw of the drivetrain in radians
     * @return current yaw of the drivetrain in radians
     */
    public double getYaw() {
        return getYaw(AngleUnit.RADIANS);
    }

    /**
     * Returns the current yaw of the drivetrain in the given angle unit
     * @param angleUnit unit to return in
     * @return current yaw of the drivetrain in the given angle unit
     */
    public double getYaw(AngleUnit angleUnit) {
        return imu.getRobotYawPitchRollAngles().getYaw(angleUnit);
    }

    /**
     * Drives the robot in field oriented mode
     * @param translationY speed to move downfield (positive is downfield)
     * @param translationX speed to move across the field (positive is right)
     * @param rotation speed to rotate at (positive is clockwise)
     */
    public void drive(double translationY, double translationX, double rotation) {
        double robotHeading = getYaw();

        double rotX = translationX * Math.cos(-robotHeading) - translationY * Math.sin(-robotHeading);
        double rotY = translationX * Math.sin(-robotHeading) - translationY * Math.cos(-robotHeading);

        rotX *= 1.1;

        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rotation), 1);

        double frontLeftPower = (rotY + rotX + rotation) / denominator;
        double frontRightPower = (rotY - rotX - rotation) / denominator;
        double backLeftPower = (rotY - rotX + rotation) / denominator;
        double backRightPower = (rotY + rotX - rotation) / denominator;

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }

    /**
     * Drives the robot in robot oriented mode
     * @param speed speed to move forward/backwards (positive is forward)
     * @param strafe speed to move right/left (positive is right)
     * @param rotation speed to rotate at (positive is clockwise)
     */
    public void driveRobotOriented(double speed, double strafe, double rotation) {
        double denominator = Math.max(Math.abs(speed) + Math.abs(strafe) + Math.abs(rotation), 1);

        double frontLeftPower = (speed + strafe + rotation) / denominator;
        double frontRightPower = (speed - strafe - rotation) / denominator;
        double backLeftPower = (speed - strafe + rotation) / denominator;
        double backRightPower = (speed + strafe - rotation) / denominator;

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }

    /**
     * Stops the drivetrain
     */
    public void stop() {
        drive(0, 0, 0);
    }

    /**
     * Resets the zero for the yaw to the direction the robot is facing
     */
    public void resetYaw() {
        imu.resetYaw();
    }
}
