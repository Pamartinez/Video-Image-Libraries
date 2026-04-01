package com.samsung.android.gallery.module.map.clustering;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MercatorProjection implements IProjection {
    private final double mWorldWidth;

    public MercatorProjection() {
        this.mWorldWidth = 1.0d;
    }

    public double[] toLatLng(Point point) {
        double d = point.f3036x;
        double d2 = this.mWorldWidth;
        return new double[]{90.0d - Math.toDegrees(Math.atan(Math.exp(((-(0.5d - (point.y / d2))) * 2.0d) * 3.141592653589793d)) * 2.0d), ((d / d2) - 0.5d) * 360.0d};
    }

    public Point toPoint(double[] dArr) {
        double sin = Math.sin(Math.toRadians(dArr[0]));
        double d = this.mWorldWidth;
        return new Point(((dArr[1] / 360.0d) + 0.5d) * d, (((Math.log((sin + 1.0d) / (1.0d - sin)) * 0.5d) / -6.283185307179586d) + 0.5d) * d);
    }

    public MercatorProjection(double d, double d2) {
        this.mWorldWidth = Math.pow(2.0d, Math.min(d, d2)) * 256.0d;
    }
}
