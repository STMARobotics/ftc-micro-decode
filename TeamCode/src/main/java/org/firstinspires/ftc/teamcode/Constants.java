package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

public class Constants {

    public static class DrivetrainConstants {

        public static String FRONT_LEFT_MOTOR_NAME = "frontLeft";
        public static String FRONT_RIGHT_MOTOR_NAME = "frontRight";
        public static String BACK_LEFT_MOTOR_NAME = "backLeft";
        public static String BACK_RIGHT_MOTOR_NAME = "backRight";

    }

    public static class IMUConstants {

        public static String IMU_NAME = "imu";

        public static RevHubOrientationOnRobot IMU_ORIENTATION = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
        );

    }

    public static class FlywheelConstants {

        public static String FLYWHEEL_MOTOR_NAME = "flywheel";

        public static PIDFCoefficients FLYWHEEL_PIDF_COEFFICIENTS =
                new PIDFCoefficients(500.0, 0.0, 0.0, 13.0);

        public static double RPM_TO_TICKS_PER_SECOND_CONVERSION_FACTOR = 28.0 / 60.0;
        public static double TICKS_PER_SECOND_TO_RPM_CONVERSION_FACTOR = 60.0 / 28.0;

        public static double CLOSE_FLYWHEEL_RPM = 2500;
        public static double MIDDLE_FLYWHEEL_RPM = 3000;
        public static double FAR_FLYWHEEL_RPM = 4000;

        public static double SHOOT_RPM_TOLERANCE = 20;

    }

    public static class IndexerConstants {

        public static String LEFT_INDEXER_SERVO_NAME = "leftIndexer";
        public static String RIGHT_INDEXER_SERVO_NAME = "rightIndexer";

    }

    public static class IntakeConstants {

        public static String INTAKE_MOTOR_NAME = "intake";

    }

    public static class ScoopConstants {

        public static String SCOOP_SERVO_NAME = "scoop";

        public static double INTAKE_POSITION = 0.5;
        public static double SHOOT_POSITION = Servo.MAX_POSITION;

    }

}
