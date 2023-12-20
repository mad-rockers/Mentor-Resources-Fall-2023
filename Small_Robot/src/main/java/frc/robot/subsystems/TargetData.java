package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;

public class TargetData {
  public final double tx;
  public final double ty;
  public final double ta;
  public final boolean tv;
  public final double tid;
  public final Pose2d pose2d;

  public TargetData(double tx, double ty, double ta, double tid, boolean tv, Pose2d pose2d) {
    this.tx = tx;
    this.ty = ty;
    this.ta = ta;
    this.tid = tid;
    this.tv = tv;
    this.pose2d = pose2d;
  }
}
