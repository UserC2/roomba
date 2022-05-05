// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Drive extends SubsystemBase {
  /* Motor Orientation:
   fl fr
  l  ^  r
   rl rr
  */
  private final Spark leftSpark = new Spark(Constants.PWM.LEFT_SPARK_CHANNEL);
  private final Spark rightSpark = new Spark(Constants.PWM.RIGHT_SPARK_CHANNEL);
  private final Spark frontLeftSpark = new Spark(Constants.PWM.FRONT_LEFT_SPARK_CHANNEL);
  private final Spark frontRightSpark = new Spark(Constants.PWM.FRONT_RIGHT_SPARK_CHANNEL);
  private final Spark rearLeftSpark = new Spark(Constants.PWM.REAR_LEFT_SPARK_CHANNEL);
  private final Spark rearRightSpark = new Spark(Constants.PWM.REAR_RIGHT_SPARK_CHANNEL);
  private final XboxController m_stick;

  /** Creates a new Drive. */
  public Drive(XboxController stick) {
    m_stick = stick;
  }

  @Override
  public void periodic() {
    //teleopHolonomicDrive();
    setAll(0.15);
  }

  public void teleopHolonomicDrive() {
    double axisX = m_stick.getRawAxis(Constants.Xbox.LEFT_STICK_X_AXIS);
    double axisY = m_stick.getRawAxis(Constants.Xbox.LEFT_STICK_Y_AXIS);
    double rotationRate = m_stick.getRawAxis(Constants.Xbox.RIGHT_STICK_X_AXIS);
    double vb = Math.sqrt(Math.pow(axisX, 2) + Math.pow(axisY, 2));
    double angleRadians = -(Math.atan2(axisX, axisY));
    //double gyroReading = 180; // 130.0;
    double gyroReading = RobotContainer.gyro.getAngle();
    if (m_stick.getAButtonPressed()) RobotContainer.gyro.reset();
    SmartDashboard.putNumber("Gyro", gyroReading);
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

      leftSpark.set(vb * Math.cos(angleRadians) + rotationRate);
      rightSpark.set(vb * -1 * Math.cos(angleRadians) + rotationRate);
      // TODO: find these pwm values
      // pwm 1:
      frontLeftSpark.set(vb * (0.5 * Math.cos(angleRadians) + Math.sqrt(3) / 2 * Math.sin(angleRadians)) + rotationRate);
      // // // pwm 2:
      frontRightSpark.set(vb * (-0.5 * Math.cos(angleRadians) + Math.sqrt(3) / 2 * Math.sin(angleRadians)) + rotationRate);
      // // // pwm 4:
      rearRightSpark.set(vb * (-0.5 * Math.cos(angleRadians) - Math.sqrt(3) / 2 * Math.sin(angleRadians)) + rotationRate);
      // // // pwm 5:
      rearLeftSpark.set(vb * (0.5 * Math.cos(angleRadians) - Math.sqrt(3) / 2 * Math.sin(angleRadians)) + rotationRate);
  }

  public void calibrateJaguars() {
    if (m_stick.getXButton()) setAll(1);
    else if (m_stick.getYButton()) setAll(-1);
    else setAll(0);
  }

  /* To check PWM ports:
  * setAll(0.15);
  * Unplug each motor controller, whichever stops = that PWM id
  */
  public void setAll(double speed) { 
    leftSpark.set(speed);
    rightSpark.set(speed);
    frontLeftSpark.set(speed);
    frontRightSpark.set(speed);
    rearLeftSpark.set(speed);
    rearRightSpark.set(speed);
  }

  public void driveFWDREV(double speed) {
    leftSpark.set(-speed);
    rightSpark.set(speed);
    frontLeftSpark.set(-speed * 0.5);
    frontRightSpark.set(speed * 0.5);
    rearLeftSpark.set(-speed * 0.5);
    rearRightSpark.set(speed * 0.5);
  }
}
