// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.MecanumDriveSubsystem;

/** An example command that uses an example subsystem. */
public class GoToSpecificTarget1Command extends CommandBase {
  private final CameraSubsystem m_CameraSubsystem;
  private final MecanumDriveSubsystem m_DriveSubsystem;
  private final Timer timer;

  double forwardSpeed;
  double rotateSpeed;
  double distanceLimit;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public GoToSpecificTarget1Command(
      CameraSubsystem cameraSubsystem, MecanumDriveSubsystem mecanumDriveSubsystem) {
    m_CameraSubsystem = cameraSubsystem;
    m_DriveSubsystem = mecanumDriveSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_CameraSubsystem, m_DriveSubsystem);
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    forwardSpeed = 0.15;
    rotateSpeed = 0.15;
    distanceLimit = 30;
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Add timer
    if (m_CameraSubsystem.getValidTarget() && m_CameraSubsystem.getTargetID() == 1.0) {
      if (m_CameraSubsystem.getDistance() > distanceLimit) {
        m_DriveSubsystem.driveForwards(forwardSpeed);
      } else {
        m_DriveSubsystem.stop();
      }
    } else {
      m_DriveSubsystem.rotate(rotateSpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_DriveSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (timer.get() > 10.0) {
      return true;
    }
    return false;
  }
}
