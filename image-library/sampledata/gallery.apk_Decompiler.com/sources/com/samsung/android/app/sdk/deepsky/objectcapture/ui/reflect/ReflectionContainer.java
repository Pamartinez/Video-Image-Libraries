package com.samsung.android.app.sdk.deepsky.objectcapture.ui.reflect;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ReflectionContainer {
    private static ViewTreeObserverInternalInsetsInfoReflection sViewTreeObserverInternalInsetsInfoReflection;
    private static ViewTreeObserverReflection sViewTreeObserverReflection;

    public static ViewTreeObserverReflection getViewTreeObserver() {
        if (sViewTreeObserverReflection == null) {
            sViewTreeObserverReflection = new ViewTreeObserverReflection();
        }
        return sViewTreeObserverReflection;
    }

    public static ViewTreeObserverInternalInsetsInfoReflection getViewTreeObserverInternalInsetsInfo() {
        if (sViewTreeObserverInternalInsetsInfoReflection == null) {
            sViewTreeObserverInternalInsetsInfoReflection = new ViewTreeObserverInternalInsetsInfoReflection();
        }
        return sViewTreeObserverInternalInsetsInfoReflection;
    }
}
