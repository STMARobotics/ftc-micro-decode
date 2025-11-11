package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeleopOpMode extends OpMode {

    private DrivetrainSubsystem drivetrainSubsystem;
    private FlywheelSubsystem flywheelSubsystem;
    private IndexerSubsystem indexerSubsystem;
    private ScoopSubsystem scoopSubsystem;
    private IntakeSubsystem intakeSubsystem;

    @Override
    public void init() {
        drivetrainSubsystem = new DrivetrainSubsystem(hardwareMap, telemetry);
        flywheelSubsystem = new FlywheelSubsystem(hardwareMap, telemetry);
        indexerSubsystem = new IndexerSubsystem(hardwareMap, telemetry);
        scoopSubsystem = new ScoopSubsystem(hardwareMap, telemetry);
        intakeSubsystem = new IntakeSubsystem(hardwareMap, telemetry);
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
        double translationX = Math.copySign(Math.pow(gamepad1.left_stick_x, 2), gamepad1.left_stick_x);
        double rotation = Math.copySign(Math.pow(gamepad1.right_stick_x, 2), gamepad1.right_stick_x);

        drivetrainSubsystem.drive(translationY, translationX, rotation);

        if (gamepad1.back) {
            drivetrainSubsystem.resetYaw();
        }

        if (gamepad2.a) {
            // middle shot
            flywheelSubsystem.setTargetVelocity(3000);
            scoopSubsystem.moveToShoot();
        } else if (gamepad2.x) {
            // close shot
            flywheelSubsystem.setTargetVelocity(2500);
            scoopSubsystem.moveToShoot();
        } else if (gamepad2.b) {
            // far shot
            flywheelSubsystem.setTargetVelocity(4000);
            scoopSubsystem.moveToShoot();
        } else {
            flywheelSubsystem.stop();
        }

        if (flywheelSubsystem.isAtSpeed()) {
            indexerSubsystem.run(1);
        } else {
            indexerSubsystem.stop();
        }

        if (gamepad2.right_trigger > 0.8) {
            intakeSubsystem.intake();
            scoopSubsystem.moveToIntake();
        } else if (gamepad2.left_trigger > 0.8) {
            intakeSubsystem.outtake();
        } else {
            intakeSubsystem.stop();
        }

        telemetry.update();
    }

    @Override
    public void stop() {
        drivetrainSubsystem.stop();
    }

}
