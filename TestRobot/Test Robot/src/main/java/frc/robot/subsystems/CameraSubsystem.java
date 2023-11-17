package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CameraSubsystem extends SubsystemBase {
    
    private final NetworkTableEntry yAngleFromCameraPerspective = 
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty");
    private double heightOfCamera;
    private double heightOfTarget;
    private double angleBetweenLevelPlaneAndCamera;
    private double angleBetweenCameraAndTarget;
    private double distanceFromTarget;

    public CameraSubsystem() {
        heightOfCamera = 14.5;
        heightOfTarget = 25;
        angleBetweenLevelPlaneAndCamera = 0;
        /* angleBetweenCameraAndTarget is not pre-set */
    }

    public void calculateDistanceFromTarget() {
        /*
         * yAngleFromCameraPerspective is intentionally made negative when 
         * the cables to the camera come out towrads the floor.
         */
        angleBetweenCameraAndTarget = -yAngleFromCameraPerspective.getDouble(0.0);
    
        distanceFromTarget = (heightOfTarget - heightOfCamera) / Math.tan(Math.toRadians(angleBetweenLevelPlaneAndCamera + angleBetweenCameraAndTarget));
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Height of Camera:", heightOfCamera);
        SmartDashboard.putNumber("Height of Target:", heightOfTarget);
        SmartDashboard.putNumber("Angle of Camera from Level Plane:", angleBetweenLevelPlaneAndCamera);
        SmartDashboard.putNumber("Angle of Target:", angleBetweenCameraAndTarget);
        SmartDashboard.putNumber("Distance:", distanceFromTarget);
    }
}
