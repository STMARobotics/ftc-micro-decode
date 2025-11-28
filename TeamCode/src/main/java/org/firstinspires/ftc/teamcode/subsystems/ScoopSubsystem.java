package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.Constants.ScoopConstants.INTAKE_POSITION;
import static org.firstinspires.ftc.teamcode.Constants.ScoopConstants.SHOOT_POSITION;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ScoopSubsystem extends SubsystemBase {

    private final Servo scoop;

    private final Telemetry telemetry;

    public ScoopSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        this.scoop = hardwareMap.get(Servo.class, "scoop");
    }

    /**
     * Moves the scoop to the intake position
     */
    public void moveToIntake() {
        scoop.setPosition(INTAKE_POSITION);
    }

    /**
     * Moves the scoop to the shoot position
     */
    public void moveToShoot() {
        scoop.setPosition(SHOOT_POSITION);
    }

}
