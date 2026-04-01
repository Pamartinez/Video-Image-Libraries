package com.samsung.android.sdk.scs.ai.image;

import android.graphics.Rect;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Boundary {
    private Rect mRect;
    private BoundaryType mType = BoundaryType.UNKNOWN;

    private Boundary() {
    }

    public static Boundary create() {
        return new Boundary();
    }

    public Rect getRect() {
        return this.mRect;
    }

    public BoundaryType getType() {
        return this.mType;
    }

    public Boundary setRect(Rect rect) {
        this.mRect = rect;
        return this;
    }

    public Boundary setType(BoundaryType boundaryType) {
        this.mType = boundaryType;
        return this;
    }
}
