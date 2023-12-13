package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MecanumDriveSubsystem extends SubsystemBase {

    private MecanumDrive m_robotDrive;

    public MecanumDriveSubsystem() {
        PWMSparkMax frontLeft = new PWMSparkMax(0);
        PWMSparkMax rearLeft = new PWMSparkMax(3);
        PWMSparkMax frontRight = new PWMSparkMax(1);
        PWMSparkMax rearRight = new PWMSparkMax(2);

        frontRight.setInverted(true);
        rearRight.setInverted(true);

        m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
    }

    public void driveCartesian(double forwardAndBack, double leftAndRight, double spinAround) {
        m_robotDrive.driveCartesian(-forwardAndBack, leftAndRight, spinAround);
    }

    public void driveForwards(double xSpeed)
    {
        m_robotDrive.driveCartesian(xSpeed, 0, 0);
    }

    public void rotate(double zSpeed)
    {
        m_robotDrive.driveCartesian(0, 0, zSpeed);
    }

    public void stop()
    {
        m_robotDrive.driveCartesian(0, 0, 0);
    }
}
