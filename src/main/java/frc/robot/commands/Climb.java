/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class Climb extends CommandBase {
  //subsystem
  private final Climber climber;

  //feeding inputs
  BooleanSupplier button;
  int state = 0;

  public Climb(Climber c, BooleanSupplier b) {
    // Use addRequirements() here to declare subsystem dependencies.
    climber = c;
    button = b;
    addRequirements(climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //pressing the button will cause state to cycle between 0, 1, and 2
    if(button.getAsBoolean()){
      state = (state + 1) % 4;
    }
    
    //the pistons will be extended in different combinations depending on the value of state
    switch(state){
      case 0:
        climber.stageOne(false);
        climber.stageTwo(false);
        break;
      case 2:
        climber.stageOne(true);
        climber.stageTwo(true);
        break;
      default:
        climber.stageOne(true);
        climber.stageTwo(false);
        break;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
