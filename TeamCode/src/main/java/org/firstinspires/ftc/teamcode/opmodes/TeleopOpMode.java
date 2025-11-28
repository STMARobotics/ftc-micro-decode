package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.ShootDistance;
import org.firstinspires.ftc.teamcode.commands.ShootCommand;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.FlywheelSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IndexerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ScoopSubsystem;
import org.firstinspires.ftc.teamcode.commands.TeleopDriveCommand;

@TeleOp
public class TeleopOpMode extends CommandOpMode {

    private GamepadEx driverController;
    private GamepadEx operatorController;

    private DrivetrainSubsystem drivetrainSubsystem;
    private FlywheelSubsystem flywheelSubsystem;
    private IndexerSubsystem indexerSubsystem;
    private IntakeSubsystem intakeSubsystem;
    private ScoopSubsystem scoopSubsystem;

    @Override
    public void initialize() {
        super.reset();

        driverController = new GamepadEx(gamepad1);
        operatorController = new GamepadEx(gamepad2);

        drivetrainSubsystem = new DrivetrainSubsystem(hardwareMap, telemetry);
        flywheelSubsystem = new FlywheelSubsystem(hardwareMap, telemetry);
        indexerSubsystem = new IndexerSubsystem(hardwareMap, telemetry);
        intakeSubsystem = new IntakeSubsystem(hardwareMap, telemetry);
        scoopSubsystem = new ScoopSubsystem(hardwareMap, telemetry);

        register(
                drivetrainSubsystem,
                flywheelSubsystem,
                indexerSubsystem,
                intakeSubsystem,
                scoopSubsystem
        );

        addDefaultCommands();
        addButtonBindings();
    }

    /**
     * Set the default commands for subsystems
     */
    private void addDefaultCommands() {
        drivetrainSubsystem.setDefaultCommand(new TeleopDriveCommand(
                drivetrainSubsystem,
                () -> -driverController.getLeftY(),
                () -> driverController.getLeftX(),
                () -> driverController.getRightX(),
                telemetry
        ));

        flywheelSubsystem.setDefaultCommand(new RunCommand(flywheelSubsystem::stop, flywheelSubsystem));
        indexerSubsystem.setDefaultCommand(new RunCommand(indexerSubsystem::stop, indexerSubsystem));
        intakeSubsystem.setDefaultCommand(new RunCommand(intakeSubsystem::stop, intakeSubsystem));
    }

    /**
     * Add the button bindings to the controllers
     */
    private void addButtonBindings() {
        driverController.getGamepadButton(GamepadKeys.Button.BACK)
                        .whenPressed(drivetrainSubsystem::resetYaw);

        operatorController.getGamepadButton(GamepadKeys.Button.X)
                .whileHeld(new ShootCommand(flywheelSubsystem, indexerSubsystem, scoopSubsystem, ShootDistance.CLOSE, telemetry));
        operatorController.getGamepadButton(GamepadKeys.Button.A)
                .whileHeld(new ShootCommand(flywheelSubsystem, indexerSubsystem, scoopSubsystem, ShootDistance.MIDDLE, telemetry));
        operatorController.getGamepadButton(GamepadKeys.Button.B)
                .whileHeld(new ShootCommand(flywheelSubsystem, indexerSubsystem, scoopSubsystem, ShootDistance.FAR, telemetry));

        new Trigger(() -> operatorController.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.8)
                .whileActiveContinuous(new RunCommand(() -> {
                    scoopSubsystem.moveToIntake();
                    intakeSubsystem.intake();
                }, intakeSubsystem, scoopSubsystem));

        new Trigger(() -> operatorController.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.8)
                .whileActiveContinuous(new RunCommand(intakeSubsystem::outtake, intakeSubsystem));
    }

    @Override
    public void run() {
        super.run();

        telemetry.update();
    }

}
