// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Jaguar;
import frc.robot.Constants;

public class Drive extends SubsystemBase {
  /* Seated person facing forward:
    3  4
  1  ^^  2
    5  6
  */
  private final Jaguar leftJaguar = new Jaguar(Constants.PWM.LEFT_JAGUAR_CHANNEL);
  private final Jaguar rightJaguar = new Jaguar(Constants.PWM.RIGHT_JAGUAR_CHANNEL);
  private final Jaguar frontLeftJaguar = new Jaguar(Constants.PWM.FRONT_LEFT_JAGUAR_CHANNEL);
  private final Jaguar frontRightJaguar = new Jaguar(Constants.PWM.FRONT_RIGHT_JAGUAR_CHANNEL);
  private final Jaguar rearLeftJaguar = new Jaguar(Constants.PWM.REAR_LEFT_JAGUAR_CHANNEL);
  private final Jaguar rearRightJaguar = new Jaguar(Constants.PWM.REAR_RIGHT_JAGUAR_CHANNEL);

  /** Creates a new Drive. */
  public Drive() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  /* Driving forward:
  * Set l and r to forward
  * Set fl to forward
  * Set fr to backward (drive against fl)
  * Set rl to backward
  * Set rr to forward (drive against rl)
  */
  /* What does that look like?
  * l.set(setpoint)
  * r.set(setpoint)
  * fl.set(setpoint)
  * fr.set(-setpoint)
  * rl.set(-setpoint)
  * rr.set(setpoint)
  * That should drive forward/left/right/backward
  * TODO: TEST (during lunch?)
  */
}
