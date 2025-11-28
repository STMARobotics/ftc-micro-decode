package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Constants.FlywheelConstants.CLOSE_FLYWHEEL_RPM;
import static org.firstinspires.ftc.teamcode.Constants.FlywheelConstants.FAR_FLYWHEEL_RPM;
import static org.firstinspires.ftc.teamcode.Constants.FlywheelConstants.MIDDLE_FLYWHEEL_RPM;

public enum ShootDistance {

    CLOSE(CLOSE_FLYWHEEL_RPM),
    MIDDLE(MIDDLE_FLYWHEEL_RPM),
    FAR(FAR_FLYWHEEL_RPM);

    private final double flywheelRPM;

    private ShootDistance(double flywheelRPM) {
        this.flywheelRPM = flywheelRPM;
    }

    public double getFlywheelRPM() {
        return flywheelRPM;
    }

}
