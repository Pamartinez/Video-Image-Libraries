package com.samsung.android.gallery.widget.popover;

import Ob.a;
import android.view.View;
import o6.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PopoverInfo {
    private int mActionId;
    private int mAnchorType;
    private View mAnchorView;
    private AnchorUpdateListener mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface AnchorUpdateListener {
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyAnchorChanged$0(View view) {
        AnchorUpdateListener anchorUpdateListener = this.mListener;
        if (anchorUpdateListener != null) {
            ((p) anchorUpdateListener).a(view);
        }
    }

    public void clear() {
        this.mListener = null;
        this.mAnchorView = null;
    }

    public int getActionId() {
        return this.mActionId;
    }

    public int getAnchorType() {
        return this.mAnchorType;
    }

    public View getAnchorView() {
        return this.mAnchorView;
    }

    public void notifyAnchorChanged(View view, boolean z) {
        notifyAnchorChanged(view, z, 0);
    }

    public void notifyAnchorSelfChanged() {
        notifyAnchorChanged(this.mAnchorView, true, 30);
    }

    public PopoverInfo setActionId(int i2) {
        this.mActionId = i2;
        return this;
    }

    public PopoverInfo setAnchorType(int i2) {
        this.mAnchorType = i2;
        return this;
    }

    public PopoverInfo setAnchorView(View view) {
        this.mAnchorView = view;
        return this;
    }

    public PopoverInfo setOnAnchorUpdateListener(AnchorUpdateListener anchorUpdateListener) {
        this.mListener = anchorUpdateListener;
        return this;
    }

    private void notifyAnchorChanged(View view, boolean z, int i2) {
        AnchorUpdateListener anchorUpdateListener;
        this.mAnchorView = view;
        if (view != null && (anchorUpdateListener = this.mListener) != null) {
            if (z) {
                view.postDelayed(new a(0, this, view), (long) i2);
            } else {
                ((p) anchorUpdateListener).a(view);
            }
        }
    }
}
