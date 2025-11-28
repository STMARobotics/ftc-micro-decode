package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSubsystem;

import java.util.function.DoubleSupplier;

public class TeleopDriveCommand extends CommandBase {

    private final DrivetrainSubsystem drivetrainSubsystem;

    private final DoubleSupplier translationXSupplier;
    private final DoubleSupplier translationYSupplier;
    private final DoubleSupplier rotationSupplier;

    private final Telemetry telemetry;

    public TeleopDriveCommand(
            DrivetrainSubsystem drivetrainSubsystem,
            DoubleSupplier translationXSupplier,
            DoubleSupplier translationYSupplier,
            DoubleSupplier rotationSupplier,
            Telemetry telemetry
    ) {
        this.drivetrainSubsystem = drivetrainSubsystem;

        this.translationXSupplier = translationXSupplier;
        this.translationYSupplier = translationYSupplier;
        this.rotationSupplier = rotationSupplier;

        this.telemetry = telemetry;

        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void execute() {
        drivetrainSubsystem.drive(
                translationYSupplier.getAsDouble(),
                translationXSupplier.getAsDouble(),
                rotationSupplier.getAsDouble()
        );
    }

    @Override
    public void end(boolean interrupted) {
        drivetrainSubsystem.stop();
    }

}
