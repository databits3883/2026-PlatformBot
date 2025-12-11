// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.RobotContainer;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class RunActuatorTime extends Command {
  int direction = 0;
  int totalSeconds = 0;
  long timeWhenStart = 0;
  public RunActuatorTime (int directionInput) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.actuatorMotor);
    direction = directionInput;
    totalSeconds = Constants.ACTUATOR_RUN_TIME;
  }
  /** Creates a new runSpinner. */

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //System.out.println("RunSpinnner:init:about to start motor");
    RobotContainer.actuatorMotor.startMotor(direction);
    timeWhenStart = System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (interrupted)
    {
      //System.out.println("RunSpinnner:end:interrupted was called!");
      //We do not need to do anything special if this gets interrupted early
    }
    System.out.println("RunSpinnnerTime:end:about to stop motor");
    RobotContainer.actuatorMotor.stopMotor();
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    long currentTime = System.currentTimeMillis();
    long elapsedTime = currentTime - timeWhenStart;
    //System.out.println("RunSpinnerTime:isFinished:about to stop motor timeWhenStart = 3"+timeWhenStart);
   //System.out.println("RunSpinnerTime:isFinished:about to stop motor currentTime = 3"+currentTime);
    //System.out.println("RunSpinnerTime:isFinished:about to stop motor elapsedTime = 3"+elapsedTime);
    if (elapsedTime > (1000 * totalSeconds))
    return true;
    else
    return false;
  }


  
}
