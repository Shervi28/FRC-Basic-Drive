// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class Arcade extends CommandBase {
  private DoubleSupplier xInput;
  private DoubleSupplier yInput;

  private Drive driveSub;
  /** Creates a new Arcade. */
  public Arcade(DoubleSupplier xInput, DoubleSupplier yInput, Drive driveSub) {
    this.xInput = xInput;
    this.yInput = yInput;
    this.driveSub = driveSub;
    
    addRequirements(driveSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSub.arcadeDrive(xInput.getAsDouble(), yInput.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
