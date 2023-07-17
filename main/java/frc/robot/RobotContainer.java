// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.Arcade;
import frc.robot.subsystems.Drive;

public class RobotContainer {
  private Drive driveRobot;
  private CommandXboxController driveControl;

  public RobotContainer() {
    driveControl = new CommandXboxController(0);
    driveRobot.setDefaultCommand(new Arcade(() -> driveControl.getRightX(), () -> driveControl.getLeftY(), driveRobot));


    configureBindings();
  }

  private void configureBindings() {
    driveControl.povUp().onTrue(new InstantCommand(() -> {
      if(GlobalVars.driveVar.KSpeedPercent != 1) GlobalVars.driveVar.KSpeedPercent += 0.1;
    }));

    driveControl.povDown().onTrue(new InstantCommand(() -> {
      if(GlobalVars.driveVar.KSpeedPercent != 0) GlobalVars.driveVar.KSpeedPercent -= 0.1;
    }));

  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
