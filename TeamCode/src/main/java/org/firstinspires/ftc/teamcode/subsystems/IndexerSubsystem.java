package org.firstinspires.ftc.teamcode.subsystems;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;
import static org.firstinspires.ftc.teamcode.Constants.IndexerConstants.LEFT_INDEXER_SERVO_NAME;
import static org.firstinspires.ftc.teamcode.Constants.IndexerConstants.RIGHT_INDEXER_SERVO_NAME;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IndexerSubsystem extends SubsystemBase {

    private final CRServo leftIndexer;
    private final CRServo rightIndexer;

    private final Telemetry telemetry;

    public IndexerSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        this.leftIndexer = hardwareMap.get(CRServo.class, LEFT_INDEXER_SERVO_NAME);
        this.rightIndexer = hardwareMap.get(CRServo.class, RIGHT_INDEXER_SERVO_NAME);

        leftIndexer.setDirection(REVERSE);
        rightIndexer.setDirection(FORWARD);
    }

    /**
     * Runs the indexer
     */
    public void run() {
        leftIndexer.setPower(1);
        rightIndexer.setPower(1);
    }

    /**
     * Stops the indexer
     */
    public void stop() {
        leftIndexer.setPower(0);
        rightIndexer.setPower(0);
    }

}
