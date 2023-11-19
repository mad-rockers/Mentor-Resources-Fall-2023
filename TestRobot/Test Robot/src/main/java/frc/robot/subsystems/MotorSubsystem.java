package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorSubsystem extends SubsystemBase {
    /* 
     * This PWMSparkMax cannot be declared anywhere else, as the Scheduler
     * will throw an exception. This is by design -> this prevents two separate
     * areas of the code from trying to set different parameters simultaneously
     * for components of the subsystem. 
     */
    private final PWMSparkMax m_onlyDrive = new PWMSparkMax(0);
    private double yValue;
    private Timer timer = new Timer();

    public void driveWithDeadZone(double yValue) {
        this.yValue = yValue;

        if (yValue <= 0.05 && yValue >= -0.05) {
        m_onlyDrive.stopMotor();
        } else {
        m_onlyDrive.set(yValue);
        }
    }

    public void driveForwardForFiveSeconds() {
        timer.reset();

        timer.start();

        while (timer.get() < 5.0) {
            m_onlyDrive.set(0.50);
        }

        timer.stop();
        m_onlyDrive.stopMotor();
    }

    public CommandBase driveForwardForFiveSecondsCommand() {
        // Inline construction of command goes here.
        // Subsystem::RunOnce implicitly requires `this` subsystem.
        return runOnce(
            () -> {
              driveForwardForFiveSeconds();
            });
    }

    public void driveBackwardForFiveSeconds() {
        timer.reset();

        timer.start();

        while (timer.get() < 5.0) {
            m_onlyDrive.set(-0.50);
        }

        timer.stop();
        m_onlyDrive.stopMotor();
    }

    public CommandBase driveBackwardForFiveSecondsCommand() {
        return runOnce(
            () -> {
                driveBackwardForFiveSeconds();
            });
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Controller Y Value:", yValue);
    }
}
