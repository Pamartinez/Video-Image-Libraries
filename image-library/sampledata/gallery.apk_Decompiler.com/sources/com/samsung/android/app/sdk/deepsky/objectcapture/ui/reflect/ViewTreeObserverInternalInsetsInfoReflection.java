package com.samsung.android.app.sdk.deepsky.objectcapture.ui.reflect;

import android.graphics.Rect;
import android.graphics.Region;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewTreeObserverInternalInsetsInfoReflection extends AbstractBaseReflection {
    public int TOUCHABLE_INSETS_REGION;

    public void contentInsetsSetEmpty(Object obj) {
        Rect rect = (Rect) getNormalValue(obj, "contentInsets");
        if (rect != null) {
            rect.setEmpty();
        }
    }

    public String getBaseClassName() {
        return "android.view.ViewTreeObserver$InternalInsetsInfo";
    }

    public Region getTouchableRegion(Object obj) {
        Object normalValue = getNormalValue(obj, "touchableRegion");
        if (normalValue == null) {
            return null;
        }
        return (Region) normalValue;
    }

    public void loadStaticFields() {
        this.TOUCHABLE_INSETS_REGION = getIntStaticValue("TOUCHABLE_INSETS_REGION");
    }

    public void setTouchableInsets(Object obj, int i2) {
        invokeNormalMethod(obj, "setTouchableInsets", new Class[]{Integer.TYPE}, Integer.valueOf(i2));
    }

    public void setTouchableRegion(Object obj, int i2) {
        setNormalValue(obj, "touchableRegion", Integer.valueOf(i2));
    }

    public void visibleInsetsSetEmpty(Object obj) {
        Rect rect = (Rect) getNormalValue(obj, "visibleInsets");
        if (rect != null) {
            rect.setEmpty();
        }
    }
}
