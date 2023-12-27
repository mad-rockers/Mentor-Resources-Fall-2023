package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
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

    rightMotor.setInverted(true);

    launcherSpeed = 0.80;

    /* commented out because we don't want to spool up the launcher on initialization */
    // leftMotor.set(launcherSpeed);
    // rightMotor.set(launcherSpeed);
  }

  public void spoolUpLauncher() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    // return runOnce(
    //     () -> {
    //       /* at time of writing, the launcher broke; so, for now, we DO NOT want to let the
    // operator spool up the launcher */
    //       //   leftMotor.set(launcherSpeed);
    //       //   rightMotor.set(launcherSpeed);
    //     });
    Timer timer = new Timer();
    timer.reset();
    stopLauncher();
    double currentSpeed = 0.0;

    while (currentSpeed < launcherSpeed) {
      timer.start();
      while (timer.get() < 0.1) {
        // wait
      }
      timer.stop();
      timer.reset();
      currentSpeed += 0.1;
      leftMotor.set(currentSpeed);
      rightMotor.set(currentSpeed);
    }
  }

  public void stopLauncher() {
    leftMotor.stopMotor();
    rightMotor.stopMotor();
  }

  public void increaseLauncherSpeed() {
    if (launcherSpeed < 1.0) {
      launcherSpeed += 0.1;
      // leftMotor.set(launcherSpeed);
      // rightMotor.set(launcherSpeed);
    }
  }

  public void decreaseLauncherSpeed() {
    if (launcherSpeed > 0.1) {
      launcherSpeed -= 0.1;
      // leftMotor.set(launcherSpeed);
      // rightMotor.set(launcherSpeed);
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
