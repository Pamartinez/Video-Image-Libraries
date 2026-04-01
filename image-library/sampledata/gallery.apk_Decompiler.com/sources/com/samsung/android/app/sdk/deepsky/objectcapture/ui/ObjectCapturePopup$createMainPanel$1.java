package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import android.content.Context;
import android.util.Size;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0014¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"com/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup$createMainPanel$1", "Landroid/widget/LinearLayout;", "", "widthMeasureSpecRaw", "heightMeasureSpec", "Lme/x;", "onMeasure", "(II)V", "Landroid/view/MotionEvent;", "ev", "", "onInterceptTouchEvent", "(Landroid/view/MotionEvent;)Z", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ObjectCapturePopup$createMainPanel$1 extends LinearLayout {
    final /* synthetic */ ObjectCapturePopup this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ObjectCapturePopup$createMainPanel$1(ObjectCapturePopup objectCapturePopup, Context context) {
        super(context);
        this.this$0 = objectCapturePopup;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        j.e(motionEvent, "ev");
        return this.this$0.animationHelper.isOverflowAnimating();
    }

    public void onMeasure(int i2, int i7) {
        if (this.this$0.animationHelper.isOverflowAnimating()) {
            Size access$getMainPanelSize$p = this.this$0.mainPanelSize;
            j.b(access$getMainPanelSize$p);
            i2 = View.MeasureSpec.makeMeasureSpec(access$getMainPanelSize$p.getWidth(), 1073741824);
        }
        super.onMeasure(i2, i7);
    }
}
