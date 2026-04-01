package com.samsung.android.app.sdk.deepsky.objectcapture.ui.reflect;

import java.lang.reflect.Method;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewTreeObserverOnComputeInternalInsetsListenerReflection extends AbstractProxyReflection {
    private static final String ORIGINAL_CLASS_NAME = "android.view.ViewTreeObserver$OnComputeInternalInsetsListener";

    public ViewTreeObserverOnComputeInternalInsetsListenerReflection() {
        super(ORIGINAL_CLASS_NAME);
    }

    public Object invokeInternal(Object obj, Method method, Object[] objArr) {
        if (!"onComputeInternalInsets".equals(method.getName())) {
            return null;
        }
        onComputeInternalInsets(objArr[0]);
        return null;
    }

    public void onComputeInternalInsets(Object obj) {
    }
}
