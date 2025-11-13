// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SpinnerMotor extends SubsystemBase {
  SparkMax motor = new SparkMax(Constants.SPINNER_MOTOR_ID, MotorType.kBrushed);
  /** Creates a new spinner. */
  public SpinnerMotor() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void startMotor(){
    //System.out.println("SpinnerMotor:about to start motor");
    motor.set(1);

  }
  public void stopMotor(){
    //System.out.println("SpinnerMotor:about to stop motor");
    motor.set(0);
    
  }
}
