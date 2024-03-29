// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.GoToSpecificTargetCommand;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.MecanumDriveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final CameraSubsystem m_cameraSubsystem = new CameraSubsystem("limelight");
  private final MecanumDriveSubsystem m_mecanumDriveSubsystem = new MecanumDriveSubsystem();
  private final LauncherSubsystem m_launcherSubsystem = new LauncherSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    m_cameraSubsystem.setDefaultCommand(
        Commands.run(() -> m_cameraSubsystem.calculateDistanceFromTarget(), m_cameraSubsystem));

    m_mecanumDriveSubsystem.setDefaultCommand(
        Commands.run(
            () ->
                m_mecanumDriveSubsystem.driveCartesian(
                    m_driverController.getLeftY(),
                    m_driverController.getLeftX(),
                    m_driverController.getRightX()),
            m_mecanumDriveSubsystem));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_driverController.a().onTrue(getAutonomousCommand()))
    //     .onTrue(new GoToTargetCommand(m_cameraSubsystem, m_mecanumDriveSubsystem));

    m_driverController
        .y()
        // .onTrue(new GoToTargetCommand(m_cameraSubsystem, m_mecanumDriveSubsystem));
        .onTrue(new GoToSpecificTargetCommand(m_cameraSubsystem, m_mecanumDriveSubsystem));

    m_driverController
        .rightBumper()
        .onTrue(
            Commands.runOnce(() -> m_cameraSubsystem.incrementDesiredTarget(), m_cameraSubsystem));
    m_driverController
        .leftBumper()
        .onTrue(
            Commands.runOnce(() -> m_cameraSubsystem.decrementDesiredTarget(), m_cameraSubsystem));

    m_driverController
        .povUp()
        .onTrue(
            Commands.runOnce(
                () -> m_launcherSubsystem.increaseLauncherSpeed(), m_launcherSubsystem));
    m_driverController
        .povDown()
        .onTrue(
            Commands.runOnce(
                () -> m_launcherSubsystem.decreaseLauncherSpeed(), m_launcherSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController
        .a()
        .onTrue(Commands.runOnce(() -> m_launcherSubsystem.spoolUpLauncher(), m_launcherSubsystem));

    m_driverController
        .b()
        .onTrue(Commands.runOnce(() -> m_launcherSubsystem.stop(), m_launcherSubsystem));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
