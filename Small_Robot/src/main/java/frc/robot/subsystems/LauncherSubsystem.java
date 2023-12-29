package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LauncherSubsystem extends SubsystemBase {

  //   private MecanumDrive m_robotDrive;
  private PWMSparkMax leftMotor;
  private PWMSparkMax rightMotor;
  private double launcherSpeed;

  public LauncherSubsystem() {
    leftMotor = new PWMSparkMax(4);
    rightMotor = new PWMSparkMax(5);

    leftMotor.setInverted(true);

    launcherSpeed = 0.20;

    /* commented out because we don't want to spool up the launcher on initialization */
    // leftMotor.set(launcherSpeed);
    // rightMotor.set(launcherSpeed);
  }

  // public CommandBase spoolUpLauncherCommand() {
  //   // Inline construction of command goes here.
  //   // Subsystem::RunOnce implicitly requires `this` subsystem.
  //   return runOnce(
  //       () -> {
  //         /* at time of writing, the launcher broke; so, for now, we DO NOT want to let the
  // operator spool up the launcher */
  //         //   leftMotor.set(launcherSpeed);
  //         //   rightMotor.set(launcherSpeed);
  //       });
  // }

  public void spoolUpLauncher() {
    leftMotor.set(launcherSpeed);
    rightMotor.set(launcherSpeed);
  }

  public void increaseLauncherSpeed() {
    if (launcherSpeed < 1.0) {
      launcherSpeed += 0.1;
    }
  }

  public void decreaseLauncherSpeed() {
    if (launcherSpeed > 0.1) {
      launcherSpeed -= 0.1;
    }
  }

  public void stop() {
    leftMotor.stopMotor();
    rightMotor.stopMotor();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Launcher Speed:", launcherSpeed);
  }
}
