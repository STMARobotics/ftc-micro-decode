package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class ShootThreeAutoOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem(hardwareMap, telemetry);
        FlywheelSubsystem flywheelSubsystem = new FlywheelSubsystem(hardwareMap, telemetry);
        IndexerSubsystem indexerSubsystem = new IndexerSubsystem(hardwareMap, telemetry);

        waitForStart();

        flywheelSubsystem.setTargetVelocity(3000);
        drivetrainSubsystem.driveRobotOriented(-.25, 0, 0);
        Thread.sleep(2600);
        drivetrainSubsystem.stop();
        Thread.sleep(500);

        long endTime = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < endTime) {
            if (flywheelSubsystem.isAtSpeed()) {
                indexerSubsystem.run(1);
            } else {
                indexerSubsystem.stop();
            }
        }

        drivetrainSubsystem.driveRobotOriented(.25, -.25, 0);
        Thread.sleep(2000);
        drivetrainSubsystem.stop();
    }

}
