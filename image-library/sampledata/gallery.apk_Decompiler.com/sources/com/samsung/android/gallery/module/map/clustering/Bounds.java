package com.samsung.android.gallery.module.map.clustering;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Bounds {
    final double maxX;
    final double maxY;
    final double midX;
    final double midY;
    final double minX;
    final double minY;

    public Bounds(double d, double d2, double d3, double d5) {
        this.minX = d;
        this.minY = d3;
        this.maxX = d2;
        this.maxY = d5;
        this.midX = (d + d2) / 2.0d;
        this.midY = (d3 + d5) / 2.0d;
    }

    private boolean intersects(double d, double d2, double d3, double d5) {
        return d < this.maxX && this.minX < d2 && d3 < this.maxY && this.minY < d5;
    }

    public boolean contains(double d, double d2) {
        return this.minX <= d && d <= this.maxX && this.minY <= d2 && d2 <= this.maxY;
    }

    public boolean contains(Point point) {
        return contains(point.f3036x, point.y);
    }

    public boolean intersects(Bounds bounds) {
        return intersects(bounds.minX, bounds.maxX, bounds.minY, bounds.maxY);
    }

    public boolean contains(Bounds bounds) {
        return bounds.minX >= this.minX && bounds.maxX <= this.maxX && bounds.minY >= this.minY && bounds.maxY <= this.maxY;
    }
}
