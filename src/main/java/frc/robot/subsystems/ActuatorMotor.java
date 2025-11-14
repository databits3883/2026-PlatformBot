// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ActuatorMotor extends SubsystemBase {
  SparkMax motor = new SparkMax(Constants.ACTUATOR_MOTOR_ID, MotorType.kBrushed);
  /** Creates a new spinner. */
  public ActuatorMotor() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void startMotor(int direction){
    motor.set(direction);

  }
  public void stopMotor(){
    motor.set(0);    
  }
}
