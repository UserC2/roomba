// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.HolonomicDrive;
import frc.robot.subsystems.Drive;

// Structure of the robot (subsystems, commands, and button) goes in here
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final XboxController m_stick = new XboxController(Constants.Xbox.DRIVER_PORT);
  public static final Drive m_driveSubsystem = new Drive(m_stick);
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  // Configure button bindings here
  private void configureButtonBindings() {}

  // Use this for auto chooser
  public Command getAutonomousCommand() {
    return null;
  }
}
