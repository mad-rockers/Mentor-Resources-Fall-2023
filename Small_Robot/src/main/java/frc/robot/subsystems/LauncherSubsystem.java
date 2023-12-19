package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LauncherSubsystem extends SubsystemBase {

  //   private MecanumDrive m_robotDrive;
  private PWMSparkMax leftMotor;
  private PWMSparkMax rightMotor;

  public LauncherSubsystem() {
    leftMotor = new PWMSparkMax(5);
    rightMotor = new PWMSparkMax(6);

    rightMotor.setInverted(true);

    /* commented out because we don't want to spool up the launcher on initialization */
    // leftMotor.set(0.25);
    // rightMotor.set(0.25);
  }

  public CommandBase spoolUpLauncherCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* at time of writing, the launcher broke; so, for now, we DO NOT want to let the operator spool up the launcher */
          //   leftMotor.set(0.25);
          //   rightMotor.set(0.25);
        });
  }

  public void stop() {
    leftMotor.stopMotor();
    rightMotor.stopMotor();
  }
}
