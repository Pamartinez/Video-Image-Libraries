package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\u0006¨\u0006\b"}, d2 = {"com/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureView$attachStateChangeListener$1", "Landroid/view/View$OnAttachStateChangeListener;", "Landroid/view/View;", "v", "Lme/x;", "onViewAttachedToWindow", "(Landroid/view/View;)V", "onViewDetachedFromWindow", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ObjectCaptureView$attachStateChangeListener$1 implements View.OnAttachStateChangeListener {
    final /* synthetic */ ObjectCaptureView this$0;

    public ObjectCaptureView$attachStateChangeListener$1(ObjectCaptureView objectCaptureView) {
        this.this$0 = objectCaptureView;
    }

    public void onViewAttachedToWindow(View view) {
        j.e(view, "v");
        if (!this.this$0.getMultiTapMode()) {
            this.this$0.startAnimation();
        }
    }

    public void onViewDetachedFromWindow(View view) {
        j.e(view, "v");
        view.removeOnAttachStateChangeListener(this);
    }
}
