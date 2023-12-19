package frc.robot.subsystems;

public class TargetData {
  public final double tx;
  public final double ty;
  public final double ta;
  public final boolean tv;
  public final double tid;

  public TargetData(double tx, double ty, double ta, double tid, boolean tv) {
    this.tx = tx;
    this.ty = ty;
    this.ta = ta;
    this.tid = tid;
    this.tv = tv;
  }
}
