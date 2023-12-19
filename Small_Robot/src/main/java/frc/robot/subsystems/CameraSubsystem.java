package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import frc.robot.LimelightHelpers.LimelightResults;
import frc.robot.LimelightHelpers.LimelightTarget_Fiducial;

public class CameraSubsystem extends SubsystemBase {

  private final String limelightName;
  private TargetData targetData;
  private double heightOfCamera;
  private double heightOfTarget;
  private double angleBetweenLevelPlaneAndCamera;
  private double angleBetweenCameraAndTarget;
  private double distanceFromTarget;

  public CameraSubsystem(String limelightName) {
    this.limelightName = limelightName;

    heightOfCamera = 8;
    heightOfTarget = 16;
    angleBetweenLevelPlaneAndCamera = 10;
  }

  public LimelightResults getLatestResults() {
    return LimelightHelpers.getLatestResults(limelightName);
  }

  public TargetData getTargetData() {
    LimelightResults results = getLatestResults();

    // Check if fiducial targets are available
    if (results.targetingResults.targets_Fiducials.length > 0) {
      LimelightTarget_Fiducial fiducial =
          results.targetingResults.targets_Fiducials[0]; // Assuming the first target

      double tx = fiducial.tx;
      double ty = fiducial.ty;
      double ta = fiducial.ta;
      double tid = fiducial.fiducialID;
      boolean tv = fiducial.fiducialID != 0; // Assuming ID 0 means no valid target

      targetData = new TargetData(tx, ty, ta, tid, tv);
      return targetData;
    }

    targetData = new TargetData(0, 0, 0, 0, false);
    return targetData; // Return default values if no target is found
  }

  public boolean getValidTarget() {
    return targetData.tv;
  }

  public void calculateDistanceFromTarget() {
    angleBetweenCameraAndTarget = targetData.ty;

    distanceFromTarget =
        (heightOfTarget - heightOfCamera)
            / Math.tan(
                Math.toRadians(angleBetweenLevelPlaneAndCamera + angleBetweenCameraAndTarget));
  }

  public double getDistance() {
    return distanceFromTarget;
  }

  @Override
  public void periodic() {
    /* Calculate distance from target */
    calculateDistanceFromTarget();
    /* Get Target Data */
    TargetData data = getTargetData();

    /* Update Dashboard */
    SmartDashboard.putNumber("Height of Camera:", heightOfCamera);
    SmartDashboard.putNumber("Height of Target:", heightOfTarget);
    SmartDashboard.putNumber(
        "AngleBetween Level Plane and Camera:", angleBetweenLevelPlaneAndCamera);
    SmartDashboard.putNumber("Distance from Target:", distanceFromTarget);

    if (data.tv) {
      SmartDashboard.putBoolean("Valid Target Found:", data.tv);
      SmartDashboard.putNumber("Limelight X Value:", data.tx);
      SmartDashboard.putNumber("Limelight Y Value:", data.ty);
      SmartDashboard.putNumber("Limelight Area Value:", data.ta);
    } else {
      SmartDashboard.putBoolean("Valid Target Found:", data.tv);
      SmartDashboard.putNumber("Limelight X Value:", 0);
      SmartDashboard.putNumber("Limelight Y Value:", 0);
      SmartDashboard.putNumber("Limelight Area Value:", 0);
    }
  }
}
