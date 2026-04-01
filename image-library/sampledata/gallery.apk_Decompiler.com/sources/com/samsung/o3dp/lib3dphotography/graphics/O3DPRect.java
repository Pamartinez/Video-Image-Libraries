package com.samsung.o3dp.lib3dphotography.graphics;

import android.graphics.Rect;
import com.samsung.o3dp.lib3dphotography.O3DPObjType;
import com.samsung.o3dp.lib3dphotography.utils.StringUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class O3DPRect {
    protected Rect mBound;
    protected O3DPObjType mObjectType;
    private boolean mToBeDeleted = false;

    public O3DPRect(O3DPObjType o3DPObjType) {
        this.mObjectType = o3DPObjType;
    }

    public abstract <T extends O3DPRect> T copyInstance();

    public Rect getBound() {
        return this.mBound;
    }

    public O3DPObjType getObjectType() {
        return this.mObjectType;
    }

    public boolean intersect(Rect rect) {
        return this.mBound.intersect(rect);
    }

    public boolean isToBeDeleted() {
        return this.mToBeDeleted;
    }

    public void offset(int i2, int i7) {
        this.mBound.offset(i2, i7);
    }

    public void scaleBound(float f) {
        Rect rect = this.mBound;
        rect.left = Math.round(((float) rect.left) * f);
        Rect rect2 = this.mBound;
        rect2.top = Math.round(((float) rect2.top) * f);
        Rect rect3 = this.mBound;
        rect3.right = Math.round(((float) rect3.right) * f);
        Rect rect4 = this.mBound;
        rect4.bottom = Math.round(((float) rect4.bottom) * f);
    }

    public void setBound(Rect rect) {
        this.mBound = rect;
    }

    public void setToBeDeleted(boolean z) {
        this.mToBeDeleted = z;
    }

    public int size() {
        return this.mBound.height() * this.mBound.width();
    }

    public String toString() {
        return StringUtil.format("bound " + this.mBound, new Object[0]);
    }
}
