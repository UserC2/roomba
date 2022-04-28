// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Jaguar;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Drive extends SubsystemBase {
  /* Motor Orientation:
   fl fr
  l  ^  r
   rl rr
  */
  // TODO: Test Driving Forward/Back (see Drive.md)
  private final Jaguar leftJaguar = new Jaguar(Constants.PWM.LEFT_JAGUAR_CHANNEL);
  private final Jaguar rightJaguar = new Jaguar(Constants.PWM.RIGHT_JAGUAR_CHANNEL);
  private final Jaguar frontLeftJaguar = new Jaguar(Constants.PWM.FRONT_LEFT_JAGUAR_CHANNEL);
  private final Jaguar frontRightJaguar = new Jaguar(Constants.PWM.FRONT_RIGHT_JAGUAR_CHANNEL);
  private final Jaguar rearLeftJaguar = new Jaguar(Constants.PWM.REAR_LEFT_JAGUAR_CHANNEL);
  private final Jaguar rearRightJaguar = new Jaguar(Constants.PWM.REAR_RIGHT_JAGUAR_CHANNEL);
  private final XboxController m_stick;
  private int motor = 0;

  /** Creates a new Drive. */
  public Drive(XboxController stick) {
    m_stick = stick;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double axisX = m_stick.getRawAxis(Constants.Xbox.LEFT_STICK_X_AXIS);
    double axisY = m_stick.getRawAxis(Constants.Xbox.LEFT_STICK_Y_AXIS);
    double rotationRate = m_stick.getRawAxis(Constants.Xbox.RIGHT_STICK_X_AXIS);
    double vb = Math.sqrt(Math.pow(axisX, 2) + Math.pow(axisY, 2));
    double angleRadians = -(Math.atan2(axisX, axisY));
    double gyroReading = 130.0;
    holonomicDrive(vb, angleRadians, rotationRate, gyroReading);
  
    //setAll(speed);
    //driveFWDREV(speed);
  }
  // TODO: Make these spin motors correct direction (test)
  private void setCCW(Jaguar motor, double speed) { motor.set(speed); }
  private void setCW(Jaguar motor, double speed) { motor.set(-speed); }
  private void set(Jaguar motor, double speed) {}
  private void setAll(double speed) { 
    leftJaguar.set(speed);
    rightJaguar.set(speed);
    frontLeftJaguar.set(speed);
    frontRightJaguar.set(speed);
    rearLeftJaguar.set(speed);
    rearRightJaguar.set(speed);
  }
  private void driveFWDREV(double speed) {
    leftJaguar.set(-speed);
    rightJaguar.set(speed);
    frontLeftJaguar.set(-speed * 0.5);
    frontRightJaguar.set(speed * 0.5);
    rearLeftJaguar.set(-speed * 0.5);
    rearRightJaguar.set(speed * 0.5);
  }
  // TODO: PARAMETER TYPEES
  private void holonomicDrive(double vb, double angleRadians, double rotationRate, double gyroReading) {
      // TODO: move to Constants
      final double movedeadband = 0.2; // deadband1
      final double rotatedeadband = 0.2; // deadband2
      final double moveSensitivity = 1.0;
      double multiplier = (Math.abs(rotationRate) / 2);
      if (Math.abs(rotationRate) < rotatedeadband) {
        rotationRate = 0.0;
      }
      else {
        rotationRate = rotationRate * multiplier;
      }
      if (Math.abs(vb) < movedeadband) {
        vb = 0.0;
      }
      else {
        vb = vb * (1 - multiplier);
      }
      angleRadians = angleRadians - (gyroReading * Math.PI / 180);
      if (angleRadians < 0) {
        angleRadians = (2 * Math.PI) + angleRadians;
      }

      leftJaguar.set(vb * Math.cos(angleRadians) + rotationRate);
      rightJaguar.set(vb * -1 * Math.cos(angleRadians) + rotationRate);
      // find pwm values used in python code
      // 0 is leftFront?
      // 3 is rightFront?
      // 1, 2, 4, 5 turn motors


  }
}
