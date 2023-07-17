package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.GlobalVars;

public class Drive extends SubsystemBase {
  DifferentialDrive m_drive;
  public Drive() {
    WPI_TalonFX frontLeft = new WPI_TalonFX(Constants.Ids.KFrontLeftID);
    WPI_TalonFX frontRight = new WPI_TalonFX(Constants.Ids.KFrontRightID);
    WPI_TalonFX backLeft = new WPI_TalonFX(Constants.Ids.KBackLeftID);
    WPI_TalonFX backRight = new WPI_TalonFX(Constants.Ids.KBackRightID);

    configMotor(frontLeft, true);
    configMotor(frontRight, false);
    configMotor(backLeft, true);
    configMotor(backRight, false);

    backLeft.follow(frontLeft);
    backRight.follow(frontRight);

    // Formats Motors To Differentail Drive
    m_drive = new DifferentialDrive(frontLeft, frontRight);
  }

  public void arcadeDrive(double x, double y){
    double xInit = x;
    double yInit = y;

    if(Math.abs(xInit) < Constants.driveConst.KDeadZone) xInit = 0;
    if(Math.abs(yInit) < Constants.driveConst.KDeadZone) yInit = 0;

    xInit*=GlobalVars.driveVar.KSpeedPercent;
    yInit*=GlobalVars.driveVar.KSpeedPercent;
    // Arcade Drive Feeds Values to Motors
    m_drive.arcadeDrive(xInit, yInit);
  }
  public void configMotor(WPI_TalonFX motor, boolean isInvert){
    motor.configFactoryDefault();
    motor.setNeutralMode(NeutralMode.Brake);

    if(isInvert) motor.setInverted(true);
  }

  @Override
  public void periodic() {
    
  }
}

