package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ShootDistance;
import org.firstinspires.ftc.teamcode.subsystems.FlywheelSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IndexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ScoopSubsystem;

public class ShootCommand extends CommandBase {

    private final FlywheelSubsystem flywheelSubsystem;
    private final IndexerSubsystem indexerSubsystem;
    private final ScoopSubsystem scoopSubsystem;

    private final ShootDistance shootDistance;

    private final Telemetry telemetry;

    public ShootCommand(
            FlywheelSubsystem flywheelSubsystem,
            IndexerSubsystem indexerSubsystem,
            ScoopSubsystem scoopSubsystem,
            ShootDistance shootDistance,
            Telemetry telemetry
    ) {
        this.flywheelSubsystem = flywheelSubsystem;
        this.indexerSubsystem = indexerSubsystem;
        this.scoopSubsystem = scoopSubsystem;
        this.shootDistance = shootDistance;
        this.telemetry = telemetry;

        addRequirements(flywheelSubsystem, indexerSubsystem, scoopSubsystem);
    }

    @Override
    public void execute() {
        scoopSubsystem.moveToShoot();
        flywheelSubsystem.setTargetAngularVelocity(shootDistance.getFlywheelRPM());

        if (flywheelSubsystem.isReadyToShoot()) {
            indexerSubsystem.run();
        } else {
            indexerSubsystem.stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        indexerSubsystem.stop();
        flywheelSubsystem.stop();
    }
}
