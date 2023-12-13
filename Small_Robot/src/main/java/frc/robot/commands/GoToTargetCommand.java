// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.MecanumDriveSubsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class GoToTargetCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final CameraSubsystem m_CameraSubsystem;
  private final MecanumDriveSubsystem m_DriveSubsystem;

  double forwardSpeed;
  double rotateSpeed;
  double distanceLimit;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public GoToTargetCommand(CameraSubsystem cameraSubsystem, MecanumDriveSubsystem mecanumDriveSubsystem) 
  {
    m_CameraSubsystem = cameraSubsystem;
    m_DriveSubsystem = mecanumDriveSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_CameraSubsystem, m_DriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    forwardSpeed = 0.15;
    rotateSpeed = 0.15;
    distanceLimit = 30;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    //Add timer
    if (!m_CameraSubsystem.getValidTarget())
    {
        m_DriveSubsystem.rotate(rotateSpeed);
    }
    else if (m_CameraSubsystem.getValidTarget())
    {
        if (m_CameraSubsystem.getDistance() > distanceLimit)
        {
            m_DriveSubsystem.driveForwards(forwardSpeed);
        }
        else
        {

        }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
