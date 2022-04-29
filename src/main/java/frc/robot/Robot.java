// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  // Runs on startup
  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
  }

  // This function runs 100% of the time
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  // Called when the robot is disabled
  @Override
  public void disabledInit() {}

  // Called while disabled
  @Override
  public void disabledPeriodic() {}

  // Called at the start of autonomous
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
  }

  // Called during autonomous
  @Override
  public void autonomousPeriodic() {}

  // Called at the start of teleop
  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  // Called during teleop
  @Override
  public void teleopPeriodic() {}

  // Called at the start of test
  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  // Called during test
  @Override
  public void testPeriodic() {}
}
