package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MecanumDriveSubsystem extends SubsystemBase {

    private MecanumDrive m_robotDrive;

    public MecanumDriveSubsystem() {
        PWMSparkMax frontLeft = new PWMSparkMax(3);
        PWMSparkMax rearLeft = new PWMSparkMax(0);
        PWMSparkMax frontRight = new PWMSparkMax(2);
        PWMSparkMax rearRight = new PWMSparkMax(1);

        frontRight.setInverted(true);
        rearRight.setInverted(true);

        m_robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
    }

    public void driveCartesian(double forwardAndBack, double leftAndRight, double spinAround) {
        m_robotDrive.driveCartesian(-forwardAndBack, leftAndRight, spinAround);
    }
}
