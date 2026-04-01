package com.samsung.android.app.sdk.deepsky.objectcapture.ui.popup;

import android.content.Context;
import android.util.Size;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCapturePopup;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0014¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/popup/OverflowPanel;", "Landroid/widget/ListView;", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup;", "popup", "<init>", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup;)V", "", "widthMeasureSpec", "heightMeasureSpecRaw", "Lme/x;", "onMeasure", "(II)V", "Landroid/view/MotionEvent;", "ev", "", "dispatchTouchEvent", "(Landroid/view/MotionEvent;)Z", "awakenScrollBars", "()Z", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/ui/ObjectCapturePopup;", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class OverflowPanel extends ListView {
    private final ObjectCapturePopup popup;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OverflowPanel(Context context, ObjectCapturePopup objectCapturePopup) {
        super(context);
        j.e(objectCapturePopup, "popup");
        this.popup = objectCapturePopup;
        setScrollBarDefaultDelayBeforeFade(ViewConfiguration.getScrollDefaultDelay() * 3);
    }

    public boolean awakenScrollBars() {
        return super.awakenScrollBars();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        j.e(motionEvent, "ev");
        if (canScrollVertically(1) || canScrollVertically(-1)) {
            this.popup.setScrolling(true);
        }
        if (this.popup.isOverflowAnimating()) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void onMeasure(int i2, int i7) {
        Size overflowPanelSize = this.popup.getOverflowPanelSize();
        j.b(overflowPanelSize);
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(overflowPanelSize.getHeight() - this.popup.getOverflowButtonSize().getHeight(), 1073741824));
    }
}
