package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class TeleopOpMode extends OpMode {

    private DrivetrainSubsystem drivetrainSubsystem;
    private FlywheelSubsystem flywheelSubsystem;
    private IndexerSubsystem indexerSubsystem;

    private boolean spinFlywheel = false;

    @Override
    public void init() {
        drivetrainSubsystem = new DrivetrainSubsystem(hardwareMap, telemetry);
        flywheelSubsystem = new FlywheelSubsystem(hardwareMap, telemetry);
        indexerSubsystem = new IndexerSubsystem(hardwareMap, telemetry);
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {
        drivetrainSubsystem.resetYaw();
    }

    @Override
    public void loop() {
        double translationY = Math.copySign(Math.pow(-gamepad1.left_stick_y, 2), -gamepad1.left_stick_y);
        double translationX = Math.copySign(Math.pow(gamepad1.left_stick_x, 2), -gamepad1.left_stick_x);
        double rotation = Math.copySign(Math.pow(gamepad1.right_stick_x, 2), gamepad1.right_stick_x);

        drivetrainSubsystem.drive(translationY, translationX, rotation);

        if (gamepad1.backWasPressed()) {
            drivetrainSubsystem.resetYaw();
        }

        if (gamepad2.aWasPressed()) {
            spinFlywheel = true;
        }
        if (gamepad2.bWasPressed()) {
            spinFlywheel = false;
        }
        if (spinFlywheel) {
            flywheelSubsystem.run(0.6);
        } else {
            flywheelSubsystem.stop();
        }

        if (gamepad2.x) {
            indexerSubsystem.run(1);
        } else {
            indexerSubsystem.run(0);
        }
    }

    @Override
    public void stop() {
        drivetrainSubsystem.stop();
    }

}
