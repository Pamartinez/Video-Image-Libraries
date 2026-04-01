package com.samsung.android.app.sdk.deepsky.objectcapture.ui.reflect;

import android.view.ViewTreeObserver;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewTreeObserverReflection extends AbstractBaseReflection {
    private Object mListener;

    public void addOnComputeInternalInsetsListener(ViewTreeObserver viewTreeObserver, Object obj) {
        this.mListener = obj;
        invokeNormalMethod(viewTreeObserver, "addOnComputeInternalInsetsListener", new Class[]{loadClassIfNeeded("android.view.ViewTreeObserver$OnComputeInternalInsetsListener")}, obj);
    }

    public String getBaseClassName() {
        return "android.view.ViewTreeObserver";
    }

    public void removeOnComputeInternalInsetsListener(ViewTreeObserver viewTreeObserver, Object obj) {
        invokeNormalMethod(viewTreeObserver, "removeOnComputeInternalInsetsListener", new Class[]{loadClassIfNeeded("android.view.ViewTreeObserver$OnComputeInternalInsetsListener")}, this.mListener);
    }
}
