// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final double gyroAngleOffset = 130;
    
    public final class PWM {
        public static final int LEFT_SPARK_CHANNEL = 1;
        public static final int RIGHT_SPARK_CHANNEL = 4;
        public static final int FRONT_LEFT_SPARK_CHANNEL = 0;
        public static final int FRONT_RIGHT_SPARK_CHANNEL = 3;
        public static final int REAR_LEFT_SPARK_CHANNEL = 2;
        public static final int REAR_RIGHT_SPARK_CHANNEL = 5;
    }

    public final class Xbox {
        public static final double JOYSTICK_DRIFT_TOLERANCE = 0.1;
        public static final int DRIVER_PORT = 0;
        public static final int OPERATOR_PORT = 1;
        public static final int LEFT_STICK_X_AXIS = 0;
        public static final int LEFT_STICK_Y_AXIS = 1;
        public static final int RIGHT_STICK_X_AXIS = 4;
        public static final int RIGHT_STICK_Y_AXIS = 5;
        public static final int LEFT_TRIGGER_AXIS = 2;
        public static final int RIGHT_TRIGGER_AXIS = 3;
        public static final int A_BUTTON = 1;
        public static final int B_BUTTON = 2;
        public static final int X_BUTTON = 3;
        public static final int Y_BUTTON = 4;
        public static final int START_BUTTON = 8;
        public static final int BACK_BUTTON = 7;
        public static final int LEFT_BUMPER = 5;
        public static final int RIGHT_BUMPER = 6;
        public static final int LEFT_STICK_BUTTON = 9;
        public static final int RIGHT_STICK_BUTTON = 10;
        public static final int POV_UP_BUTTON = 0;
        public static final int POV_DOWN_BUTTON = 180;
        public static final int POV_RIGHT_BUTTON = 90;
        public static final int POV_LEFT_BUTTON = 270;
    }
}
