package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeSubsystem {

    private final DcMotor intake;
    private final Telemetry telemetry;

    public IntakeSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.intake = hardwareMap.get(DcMotor.class, "intake");
        this.telemetry = telemetry;

        intake.setDirection(DcMotor.Direction.REVERSE);
    }

    public void intake() {
        intake.setPower(1.0);
    }

    public void outtake() {
        intake.setPower(-1.0);
    }

    public void stop() {
        intake.setPower(0.0);
    }


}
