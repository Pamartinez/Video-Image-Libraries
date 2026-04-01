package com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup;

import android.view.View;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCapturePopup;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000b\u0010\nR\u001c\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/FloatingOnAttachStateChangeListener;", "Landroid/view/View$OnAttachStateChangeListener;", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup;", "popup", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup;)V", "Landroid/view/View;", "v", "Lme/x;", "onViewAttachedToWindow", "(Landroid/view/View;)V", "onViewDetachedFromWindow", "Ljava/lang/ref/WeakReference;", "objectCapturePopup", "Ljava/lang/ref/WeakReference;", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FloatingOnAttachStateChangeListener implements View.OnAttachStateChangeListener {
    private final WeakReference<ObjectCapturePopup> objectCapturePopup;

    public FloatingOnAttachStateChangeListener(ObjectCapturePopup objectCapturePopup2) {
        this.objectCapturePopup = new WeakReference<>(objectCapturePopup2);
    }

    public void onViewAttachedToWindow(View view) {
        j.e(view, "v");
    }

    public void onViewDetachedFromWindow(View view) {
        j.e(view, "v");
        if (this.objectCapturePopup.get() != null) {
            ObjectCapturePopup objectCapturePopup2 = this.objectCapturePopup.get();
            j.b(objectCapturePopup2);
            objectCapturePopup2.onDetachFromWindow();
        }
        view.removeOnAttachStateChangeListener(this);
    }
}
