package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ScoopSubsystem {

    private final Servo scoop;
    private final Telemetry telemetry;

    public ScoopSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.scoop = hardwareMap.get(Servo.class, "scoop");
        this.telemetry = telemetry;
    }

    public void moveToIntake() {
        scoop.setPosition(0.5);
    }

    public void moveToShoot() {
        scoop.setPosition(Servo.MAX_POSITION);
    }

}
