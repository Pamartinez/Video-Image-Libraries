package com.samsung.android.gallery.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;
import bc.C0584a;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryTextView extends TextView {
    private int mHoverType;
    private final Runnable mSetHoverTypeRunnable = new C0584a(29, this);

    public GalleryTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private boolean hasCallbacks(Runnable runnable) {
        Handler handler = getHandler();
        if (handler == null || !handler.hasCallbacks(runnable)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        try {
            if (isAttachedToWindow()) {
                super.semSetHoverPopupType(this.mHoverType);
                this.mHoverType = 0;
            }
        } catch (Exception e) {
            Log.e((CharSequence) "GalleryTextView", "setHoverPopupType failed {" + Integer.toHexString(hashCode()) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mHoverType + "}", (Throwable) e);
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mHoverType > 0 && !hasCallbacks(this.mSetHoverTypeRunnable)) {
            postDelayed(this.mSetHoverTypeRunnable, 1000);
        }
    }

    public void onDetachedFromWindow() {
        removeCallbacks(this.mSetHoverTypeRunnable);
        super.onDetachedFromWindow();
    }

    public void semSetHoverPopupType(int i2) {
        this.mHoverType = i2;
    }
}
