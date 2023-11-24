package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CameraSubsystem extends SubsystemBase {
    
    private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    private NetworkTableEntry tv = table.getEntry("tv");
    private NetworkTableEntry tx = table.getEntry("tx");
    private NetworkTableEntry ty = table.getEntry("ty");
    private NetworkTableEntry ta = table.getEntry("ta");
    private double heightOfCamera;
    private double heightOfTarget;
    private double angleBetweenLevelPlaneAndCamera;
    private double angleBetweenCameraAndTarget;
    private double distanceFromTarget;

    public CameraSubsystem() {
        heightOfCamera = 11.5;
        heightOfTarget = 16;
        angleBetweenLevelPlaneAndCamera = 0;
    }

    public void calculateDistanceFromTarget() {
        angleBetweenCameraAndTarget = ty.getDouble(0.0);
    
        distanceFromTarget = (heightOfTarget - heightOfCamera) / Math.tan(Math.toRadians(angleBetweenLevelPlaneAndCamera + angleBetweenCameraAndTarget));
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Height of Camera:", heightOfCamera);
        SmartDashboard.putNumber("Height of Target:", heightOfTarget);
        SmartDashboard.putNumber("Angle of Camera from Level Plane:", angleBetweenLevelPlaneAndCamera);
        if (tv.getDouble(0.0) == 1) { /* Then limelight sees valid targets */
            SmartDashboard.putString("Limelight Valid Targets:", "TRUE");
        } else {
            SmartDashboard.putString("Limelight Valid Targets:", "FALSE");
        }
        SmartDashboard.putNumber("Limelight X Value:", tx.getDouble(0.0));
        SmartDashboard.putNumber("Limelight Y Value:", ty.getDouble(0.0));
        SmartDashboard.putNumber("Limelight Area Value:", ta.getDouble(0.0));
        SmartDashboard.putNumber("Distance:", distanceFromTarget);
    }
}
