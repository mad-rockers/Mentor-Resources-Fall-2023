package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
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

  private double desiredTarget;

  public CameraSubsystem(String limelightName) {
    this.limelightName = limelightName;
    LimelightHelpers.setLEDMode_ForceOff(limelightName);

    targetData = new TargetData(0, 0, 0, 0, false, null);

    heightOfCamera = 8;
    heightOfTarget = 16;
    angleBetweenLevelPlaneAndCamera = 10;

    desiredTarget = 1.0;
  }

  public void incrementDesiredTarget() {
    if (desiredTarget < 3.0) {
      desiredTarget += 1.0;
    } else {
      desiredTarget = 1.0;
    }
  }

  public void decrementDesiredTarget() {
    if (desiredTarget > 1.0) {
      desiredTarget -= 1.0;
    } else {
      desiredTarget = 3.0;
    }
  }

  public double getDesiredTarget() {
    return desiredTarget;
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
      Pose2d pose2d = fiducial.getRobotPose_TargetSpace2D();

      targetData = new TargetData(tx, ty, ta, tid, tv, pose2d);
      return targetData;
    }

    targetData = new TargetData(0, 0, 0, 0, false, null);
    return targetData; // Return default values if no target is found
  }

  public boolean getValidTarget() {
    return targetData.tv;
  }

  public double getTargetID() {
    return targetData.tid;
  }

  public void calculateDistanceFromTarget() {
    if (targetData == null) {
      distanceFromTarget = 0.0;
      return;
    }
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
    // /* Calculate distance from target */
    // calculateDistanceFromTarget();
    // /* Get Target Data */
    // TargetData data = getTargetData();

    // /* Update Dashboard */
    // SmartDashboard.putNumber("Height of Camera:", heightOfCamera);
    // SmartDashboard.putNumber("Height of Target:", heightOfTarget);
    // SmartDashboard.putNumber(
    //     "AngleBetween Level Plane and Camera:", angleBetweenLevelPlaneAndCamera);
    // SmartDashboard.putNumber("Distance from Target:", distanceFromTarget);
    // SmartDashboard.putNumber("Desired Target:", desiredTarget);

    // if (data.tv) {
    //   SmartDashboard.putBoolean("Valid Target Found:", data.tv);
    //   SmartDashboard.putNumber("Limelight X Value:", data.tx);
    //   SmartDashboard.putNumber("Limelight Y Value:", data.ty);
    //   SmartDashboard.putNumber("Limelight Area Value:", data.ta);
    //   SmartDashboard.putNumber("Target ID:", data.tid);
    //   SmartDashboard.putNumber("2d Pose X:", data.pose2d.getX());
    //   SmartDashboard.putNumber("2d Pose Y:", data.pose2d.getY());
    //   SmartDashboard.putNumber("2d Pose Rotation:", data.pose2d.getRotation().getDegrees());
    // } else {
    //   SmartDashboard.putBoolean("Valid Target Found:", data.tv);
    //   SmartDashboard.putNumber("Limelight X Value:", 0);
    //   SmartDashboard.putNumber("Limelight Y Value:", 0);
    //   SmartDashboard.putNumber("Limelight Area Value:", 0);
    //   SmartDashboard.putNumber("Target ID:", 0);
    //   SmartDashboard.putNumber("2d Pose X:", 0);
    //   SmartDashboard.putNumber("2d Pose Y:", 0);
    //   SmartDashboard.putNumber("2d Pose Rotation:", 0);
    // }
  }
}
