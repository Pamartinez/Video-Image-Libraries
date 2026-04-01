package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import android.graphics.Region;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.reflect.ReflectionContainer;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.reflect.ViewTreeObserverInternalInsetsInfoReflection;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.reflect.ViewTreeObserverOnComputeInternalInsetsListenerReflection;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"com/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup$insetsComputer$1", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/reflect/ViewTreeObserverOnComputeInternalInsetsListenerReflection;", "", "info", "Lme/x;", "onComputeInternalInsets", "(Ljava/lang/Object;)V", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ObjectCapturePopup$insetsComputer$1 extends ViewTreeObserverOnComputeInternalInsetsListenerReflection {
    final /* synthetic */ ObjectCapturePopup this$0;

    public ObjectCapturePopup$insetsComputer$1(ObjectCapturePopup objectCapturePopup) {
        this.this$0 = objectCapturePopup;
    }

    public void onComputeInternalInsets(Object obj) {
        j.e(obj, "info");
        ViewTreeObserverInternalInsetsInfoReflection viewTreeObserverInternalInsetsInfo = ReflectionContainer.getViewTreeObserverInternalInsetsInfo();
        viewTreeObserverInternalInsetsInfo.contentInsetsSetEmpty(obj);
        viewTreeObserverInternalInsetsInfo.visibleInsetsSetEmpty(obj);
        Region touchableRegion = viewTreeObserverInternalInsetsInfo.getTouchableRegion(obj);
        if (touchableRegion != null) {
            touchableRegion.set(this.this$0.touchableRegion);
        }
        viewTreeObserverInternalInsetsInfo.setTouchableInsets(obj, viewTreeObserverInternalInsetsInfo.TOUCHABLE_INSETS_REGION);
    }
}
