package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LauncherSubsystem extends SubsystemBase {

  private PWMSparkMax leftMotor;
  private PWMSparkMax rightMotor;
  private double launcherSpeed;
  private Timer timer;

  public LauncherSubsystem() {
    leftMotor = new PWMSparkMax(4);
    rightMotor = new PWMSparkMax(5);

    rightMotor.setInverted(true);

    timer = new Timer();

    /* 
     * This initial launcher speed is lower than what we think we'll need, 
     * but we'll be able to use the controller to increase or decrease it 
     * as necessary.
     */
    launcherSpeed = 0.50;
  }

  public void spoolUpLauncher() {
    /* 
     * Ensure that the launcher is stopped before assuming anything about speed.
     */
    stopLauncher();
    double currentSpeed = 0.0;

    timer.reset();

    while (currentSpeed < launcherSpeed) {
      timer.start();
      while (timer.get() < 0.2) {
        // wait
      }
      currentSpeed += 0.1;
      leftMotor.set(currentSpeed);
      rightMotor.set(currentSpeed);

      timer.stop();
      timer.reset();
    }
  }

  public void stopLauncher() {
    leftMotor.stopMotor();
    rightMotor.stopMotor();
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
