package org.firstinspires.ftc.teamcode.subsystems;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_USING_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.FLOAT;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static org.firstinspires.ftc.teamcode.Constants.FlywheelConstants.FLYWHEEL_MOTOR_NAME;
import static org.firstinspires.ftc.teamcode.Constants.FlywheelConstants.FLYWHEEL_PIDF_COEFFICIENTS;
import static org.firstinspires.ftc.teamcode.Constants.FlywheelConstants.RPM_TO_TICKS_PER_SECOND_CONVERSION_FACTOR;
import static org.firstinspires.ftc.teamcode.Constants.FlywheelConstants.SHOOT_RPM_TOLERANCE;
import static org.firstinspires.ftc.teamcode.Constants.FlywheelConstants.TICKS_PER_SECOND_TO_RPM_CONVERSION_FACTOR;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class FlywheelSubsystem extends SubsystemBase {

    private final DcMotorEx flywheel;

    private double targetRPM;

    private final Telemetry telemetry;

    public FlywheelSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        this.flywheel = hardwareMap.get(DcMotorEx.class, FLYWHEEL_MOTOR_NAME);

        flywheel.setDirection(FORWARD);
        flywheel.setZeroPowerBehavior(FLOAT);
        flywheel.setMode(RUN_USING_ENCODER);

        flywheel.setPIDFCoefficients(RUN_USING_ENCODER, FLYWHEEL_PIDF_COEFFICIENTS);
    }

    public boolean isReadyToShoot() {
        return targetRPM > 0 && Math.abs(getAngularVelocity() - targetRPM) <= SHOOT_RPM_TOLERANCE;
    }

    /**
     * Returns the current angular velocity of the flywheel in rotations per minute
     * @return angular velocity of the flywheel
     */
    public double getAngularVelocity() {
        return flywheel.getVelocity() * TICKS_PER_SECOND_TO_RPM_CONVERSION_FACTOR;
    }

    /**
     * Sets the target angular velocity of the flywheel in rotations per minute
     * @param rpm Target angular velocity
     */
    public void setTargetAngularVelocity(double rpm) {
        targetRPM = rpm;
        flywheel.setVelocity(rpm * RPM_TO_TICKS_PER_SECOND_CONVERSION_FACTOR);
    }

    public void stop() {
        setTargetAngularVelocity(0);
    }

}
