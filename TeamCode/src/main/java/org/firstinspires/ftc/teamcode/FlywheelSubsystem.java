package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class FlywheelSubsystem {

    private double targetVelocity = 0.0;
    private final DcMotorEx flywheel;
    private final Telemetry telemetry;

    public FlywheelSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        flywheel = hardwareMap.get(DcMotorEx.class, "flywheel");
        this.telemetry = telemetry;

        flywheel.setDirection(DcMotorEx.Direction.FORWARD);
        flywheel.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        flywheel.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        flywheel.setPIDFCoefficients(
                DcMotorEx.RunMode.RUN_USING_ENCODER,
                new PIDFCoefficients(
                        500.0, 0.0, 0.0, 13.0
                )
        );
    }

    public boolean isAtSpeed() {
        telemetry.addData("flywheel speed", flywheel.getVelocity() * 60.0 / 28.0);
        double error = Math.abs((flywheel.getVelocity() * 60.0 / 28.0) - targetVelocity);
        telemetry.addData("error", error);
        return targetVelocity > 0 && error <= 20;
    }

    public void setTargetVelocity(double rpm) {
        targetVelocity = rpm;
        flywheel.setVelocity(rpm * 28.0 / 60.0);
    }

    public void stop() {
        setTargetVelocity(0);
    }

}
