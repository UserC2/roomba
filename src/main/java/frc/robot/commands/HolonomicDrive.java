// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class HolonomicDrive extends CommandBase {
  private final XboxController m_stick;

  /** Creates a new Drive. */
  public HolonomicDrive(XboxController stick) {
    addRequirements(RobotContainer.m_driveSubsystem);
    m_stick = stick;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double axisX = m_stick.getRawAxis(Constants.Xbox.LEFT_STICK_X_AXIS);
    double axisY = m_stick.getRawAxis(Constants.Xbox.LEFT_STICK_Y_AXIS);
    double rotationRate = m_stick.getRawAxis(Constants.Xbox.RIGHT_STICK_X_AXIS);
    double vb = Math.sqrt(Math.pow(axisX, 2) + Math.pow(axisY, 2));
    double angleRadians = -(Math.atan2(axisX, axisY));
    double gyroReading = 130.0;

    RobotContainer.m_driveSubsystem.holonomicDrive(vb, angleRadians, rotationRate, gyroReading);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
