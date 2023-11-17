package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorSubsystem extends SubsystemBase {
    /* 
     * This PWMSparkMax cannot be declared anywhere else, as the Scheduler
     * will throw an exception. This is by design -> this prevents two separate
     * areas of the code from trying to set different parameters simultaneously
     * for components of the subsystem. 
     */
    private final PWMSparkMax m_onlyDrive = new PWMSparkMax(0);

    public void driveWithDeadZone(double yValue) {
        SmartDashboard.putNumber("Xbox Right Y Value:", yValue);

        if (yValue <= 0.05 && yValue >= -0.05) {
        m_onlyDrive.stopMotor();
        } else {
        m_onlyDrive.set(yValue);
        }
    }
}
