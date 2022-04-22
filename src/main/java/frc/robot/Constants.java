// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public final class PWM {
        // TODO: Check actual wiring
        public static final int LEFT_JAGUAR_CHANNEL = 1;
        public static final int RIGHT_JAGUAR_CHANNEL = 2;
        public static final int FRONT_LEFT_JAGUAR_CHANNEL = 3;
        public static final int FRONT_RIGHT_JAGUAR_CHANNEL = 4;
        public static final int REAR_LEFT_JAGUAR_CHANNEL = 5;
        public static final int REAR_RIGHT_JAGUAR_CHANNEL = 6;
    }
}
