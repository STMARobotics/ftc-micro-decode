package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IndexerSubsystem {

    private final CRServo leftIndexer;
    private final CRServo rightIndexer;

    public IndexerSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        leftIndexer = hardwareMap.get(CRServo.class, "leftIndexer");
        rightIndexer = hardwareMap.get(CRServo.class, "rightIndexer");

        leftIndexer.setDirection(CRServo.Direction.REVERSE);
        rightIndexer.setDirection(CRServo.Direction.FORWARD);
    }

    public void run(double power) {
        leftIndexer.setPower(power);
        rightIndexer.setPower(power);
    }

    public void stop() {
        run(0);
    }

}
