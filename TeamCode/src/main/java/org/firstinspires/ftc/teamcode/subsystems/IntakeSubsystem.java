package org.firstinspires.ftc.teamcode.subsystems;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;
import static org.firstinspires.ftc.teamcode.Constants.IntakeConstants.INTAKE_MOTOR_NAME;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSubsystem extends SubsystemBase {

    private final DcMotor intake;

    private final Telemetry telemetry;

    public IntakeSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        this.intake = hardwareMap.get(DcMotor.class, INTAKE_MOTOR_NAME);

        intake.setDirection(REVERSE);
    }

    /**
     * Runs the intake forwards
     */
    public void intake() {
        intake.setPower(1.0);
    }

    /**
     * Runs the intake in reverse
     */
    public void outtake() {
        intake.setPower(-1.0);
    }

    /**
     * Stops the intake
     */
    public void stop() {
        intake.setPower(0.0);
    }

}
