// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.RobotContainer;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class RunSpinner extends Command {
  /** Creates a new runSpinner. */
  public RunSpinner() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.spinnerMotor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //System.out.println("RunSpinnner:init:about to start motor");
    RobotContainer.spinnerMotor.startMotor();
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
    System.out.println("RunSpinnner:end:about to stop motor");
    RobotContainer.spinnerMotor.stopMotor();
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
