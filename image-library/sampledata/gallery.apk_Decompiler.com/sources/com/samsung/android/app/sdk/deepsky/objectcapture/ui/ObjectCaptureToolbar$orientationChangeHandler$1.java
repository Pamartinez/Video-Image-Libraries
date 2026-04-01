package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import android.graphics.Rect;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001JW\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00108\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0012¨\u0006\u0014"}, d2 = {"com/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCaptureToolbar$orientationChangeHandler$1", "Landroid/view/View$OnLayoutChangeListener;", "Landroid/view/View;", "view", "", "newLeft", "newRight", "newTop", "newBottom", "oldLeft", "oldRight", "oldTop", "oldBottom", "Lme/x;", "onLayoutChange", "(Landroid/view/View;IIIIIIII)V", "Landroid/graphics/Rect;", "mNewRect", "Landroid/graphics/Rect;", "mOldRect", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ObjectCaptureToolbar$orientationChangeHandler$1 implements View.OnLayoutChangeListener {
    private final Rect mNewRect = new Rect();
    private final Rect mOldRect = new Rect();
    final /* synthetic */ ObjectCaptureToolbar this$0;

    public ObjectCaptureToolbar$orientationChangeHandler$1(ObjectCaptureToolbar objectCaptureToolbar) {
        this.this$0 = objectCaptureToolbar;
    }

    public void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        j.e(view, "view");
        int i15 = this.this$0.context.getResources().getConfiguration().orientation;
        if (this.this$0.orientation != i15) {
            this.this$0.popup.setMovingStarted(false);
        }
        this.this$0.orientation = i15;
        this.mNewRect.set(i2, i7, i8, i10);
        this.mOldRect.set(i11, i12, i13, i14);
        if (!this.this$0.popup.isDismissed() && !j.a(this.mNewRect, this.mOldRect)) {
            this.this$0.popup.setWidthChanged(true);
            this.this$0.updateLayout();
        }
    }
}
