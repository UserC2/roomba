// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Jaguar;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class Drive extends SubsystemBase {
  /* Motor Orientation:
   fl fr
  l  ^  r
   rl rr
  */
  private final Jaguar leftJaguar = new Jaguar(Constants.PWM.LEFT_JAGUAR_CHANNEL);
  private final Jaguar rightJaguar = new Jaguar(Constants.PWM.RIGHT_JAGUAR_CHANNEL);
  private final Jaguar frontLeftJaguar = new Jaguar(Constants.PWM.FRONT_LEFT_JAGUAR_CHANNEL);
  private final Jaguar frontRightJaguar = new Jaguar(Constants.PWM.FRONT_RIGHT_JAGUAR_CHANNEL);
  private final Jaguar rearLeftJaguar = new Jaguar(Constants.PWM.REAR_LEFT_JAGUAR_CHANNEL);
  private final Jaguar rearRightJaguar = new Jaguar(Constants.PWM.REAR_RIGHT_JAGUAR_CHANNEL);
  private final XboxController m_stick;

  /** Creates a new Drive. */
  public Drive(XboxController stick) {
    m_stick = stick;
  }

  @Override
  public void periodic() {
    double axisX = m_stick.getRawAxis(Constants.Xbox.LEFT_STICK_X_AXIS);
    double axisY = m_stick.getRawAxis(Constants.Xbox.LEFT_STICK_Y_AXIS);
    double rotationRate = m_stick.getRawAxis(Constants.Xbox.RIGHT_STICK_X_AXIS);
    double vb = Math.sqrt(Math.pow(axisX, 2) + Math.pow(axisY, 2));
    double angleRadians = -(Math.atan2(axisX, axisY));
    double gyroReading = 130.0;
    holonomicDrive(vb, angleRadians, rotationRate, gyroReading);
  }

  public void holonomicDrive(double vb, double angleRadians, double rotationRate, double gyroReading) {
      final double movedeadband = 0.2; // deadband1
      final double rotatedeadband = 0.2; // deadband2
      //final double moveSensitivity = 1.0; // Never used?
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
      // TODO: find these pwm values
      // pwm 1:
      // pwm1.set(vb * (0.5 * Math.cos(angleRadians) + Math.sqrt(3) / 2 * Math.sin(angleRadians)) + rotationRate);
      // // pwm 2:
      // pwm2.set(vb * (-0.5 * Math.cos(angleRadians) + Math.sqrt(3) / 2 * Math.sin(angleRadians)) + rotationRate);
      // // pwm 4:
      // pwm4.set(vb * (-0.5 * Math.cos(angleRadians) - Math.sqrt(3) / 2 * Math.sin(angleRadians)) + rotationRate);
      // // pwm 5:
      // pwm5.set(vb * (0.5 * Math.cos(angleRadians) - Math.sqrt(3) / 2 * Math.sin(angleRadians)) + rotationRate);
  }

  public void setAll(double speed) { 
    leftJaguar.set(speed);
    rightJaguar.set(speed);
    frontLeftJaguar.set(speed);
    frontRightJaguar.set(speed);
    rearLeftJaguar.set(speed);
    rearRightJaguar.set(speed);
  }

  public void driveFWDREV(double speed) {
    leftJaguar.set(-speed);
    rightJaguar.set(speed);
    frontLeftJaguar.set(-speed * 0.5);
    frontRightJaguar.set(speed * 0.5);
    rearLeftJaguar.set(-speed * 0.5);
    rearRightJaguar.set(speed * 0.5);
  }
}
