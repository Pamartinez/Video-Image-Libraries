package com.samsung.android.gallery.widget.capture;

import S7.d;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.clip.ClipView;
import com.samsung.android.gallery.widget.clip.objectcapture.ObjectCaptureView;
import com.samsung.android.gallery.widget.clip.textextraction.TextExtractionView;
import java.util.Optional;
import o6.B;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClipViewDelegate {
    private final StringCompat TAG = new StringCompat(getClass().getSimpleName());
    private ICaptureActionMode mActionMode;
    private final int[] mLocation = new int[2];
    private ObjectCaptureView mObjectCaptureView;
    private final IClipRootView mRootView;
    private TextExtractionView mTextExtractionView;

    public ClipViewDelegate(IClipRootView iClipRootView) {
        this.mRootView = iClipRootView;
    }

    private void bindObjectCaptureView(View view, boolean z, boolean z3) {
        ObjectCaptureView objectCaptureView = (ObjectCaptureView) view;
        this.mObjectCaptureView = objectCaptureView;
        if (objectCaptureView != null) {
            bindViewInternal(objectCaptureView, z, z3);
        }
    }

    private void bindTextExtractionView(View view, boolean z, boolean z3) {
        TextExtractionView textExtractionView = (TextExtractionView) view;
        this.mTextExtractionView = textExtractionView;
        if (textExtractionView != null) {
            bindViewInternal(textExtractionView, z, z3);
        }
    }

    private void bindViewInternal(ClipView clipView, boolean z, boolean z3) {
        ClipView clipView2 = clipView;
        clipView2.post(new d(this, clipView2, z3, z, 2));
    }

    private void handleTouchOnCaptureActionMode(MotionEvent motionEvent) {
        ICaptureActionMode iCaptureActionMode = this.mActionMode;
        if (iCaptureActionMode != null) {
            iCaptureActionMode.onTouchEvent(motionEvent);
        }
    }

    private boolean hasObjectCaptureData() {
        ObjectCaptureView objectCaptureView = this.mObjectCaptureView;
        if (objectCaptureView == null || !objectCaptureView.hasData()) {
            return false;
        }
        return true;
    }

    private boolean hasTextExtractionData() {
        TextExtractionView textExtractionView = this.mTextExtractionView;
        if (textExtractionView == null || !textExtractionView.hasData()) {
            return false;
        }
        return true;
    }

    private boolean isSelectedNow() {
        TextExtractionView textExtractionView = this.mTextExtractionView;
        if (textExtractionView == null || !textExtractionView.isSelectedDirectCheck()) {
            return false;
        }
        return true;
    }

    private boolean isTextExtractionFullState() {
        TextExtractionView textExtractionView = this.mTextExtractionView;
        if (textExtractionView == null || !textExtractionView.isFullState()) {
            return false;
        }
        return true;
    }

    private boolean isTextExtractionFullStateExceptFilter() {
        TextExtractionView textExtractionView = this.mTextExtractionView;
        if (textExtractionView == null || !textExtractionView.isFullStateExceptFilter()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindViewInternal$0(ClipView clipView, boolean z, boolean z3) {
        this.mRootView.getLocation(this.mLocation);
        clipView.setVideoMode(z);
        clipView.setDefaultRect(this.mRootView.getDisplayMinRect());
        if (z3) {
            clipView.onRefresh(this.mRootView.getDisplayRect(), this.mLocation, this.mRootView.getTopMarginFromSupplier(), this.mRootView.getBottomMarginFromSupplier(), this.mRootView.isAlreadyUp(), true);
            return;
        }
        clipView.onReady(this.mRootView.getDisplayRect(), this.mLocation, this.mRootView.getTopMarginFromSupplier(), this.mRootView.getBottomMarginFromSupplier(), this.mRootView.isAlreadyUp());
    }

    private void refreshViewInternal(ClipView clipView, int i2, int i7, RectF rectF, RectF rectF2) {
        if (clipView != null) {
            clipView.setDefaultRect(rectF);
            clipView.onRefresh(rectF2, this.mLocation, i2, i7, true, false);
        }
    }

    public void bindCaptureView(View view, boolean z, boolean z3, boolean z7) {
        if (z) {
            bindTextExtractionView(view, z3, z7);
        } else {
            bindObjectCaptureView(view, z3, z7);
        }
    }

    public void clear() {
        this.mActionMode = null;
        this.mTextExtractionView = null;
        this.mObjectCaptureView = null;
    }

    public boolean hasData() {
        if (hasTextExtractionData() || hasObjectCaptureData()) {
            return true;
        }
        return false;
    }

    public boolean isMovable() {
        if (isTextExtractionFullStateExceptFilter() || isSelectedNow()) {
            return false;
        }
        return true;
    }

    public boolean isToggleConsumable() {
        boolean z;
        boolean z3;
        boolean z7;
        TextExtractionView textExtractionView = this.mTextExtractionView;
        if (textExtractionView == null || !textExtractionView.isToggleConsumable()) {
            z = false;
        } else {
            z = true;
        }
        ObjectCaptureView objectCaptureView = this.mObjectCaptureView;
        if (objectCaptureView == null || !objectCaptureView.isObjectSelected()) {
            z3 = false;
        } else {
            z3 = true;
        }
        ICaptureActionMode iCaptureActionMode = this.mActionMode;
        if (iCaptureActionMode == null || !iCaptureActionMode.isEnabled()) {
            z7 = false;
        } else {
            z7 = true;
        }
        if (z) {
            Optional.ofNullable(this.mTextExtractionView).ifPresent(new B(12));
        }
        if (z3) {
            Optional.ofNullable(this.mObjectCaptureView).ifPresent(new B(13));
        }
        if (z || z3 || z7) {
            return true;
        }
        return false;
    }

    public void onDraw() {
        this.mRootView.getLocation(this.mLocation);
        if (hasTextExtractionData()) {
            this.mTextExtractionView.setDisplayRect(this.mRootView.getDisplayRect(), this.mLocation, this.mRootView.getTopMarginFromSupplier(), this.mRootView.getBottomMarginFromSupplier());
            this.mTextExtractionView.invalidate();
        }
        if (hasObjectCaptureData()) {
            this.mObjectCaptureView.setDisplayRect(this.mRootView.getDisplayRect(), this.mLocation, this.mRootView.getTopMarginFromSupplier(), this.mRootView.getBottomMarginFromSupplier());
            this.mObjectCaptureView.invalidate();
        }
    }

    public boolean onLongPress(float f, float f5) {
        if (hasTextExtractionData() && (this.mTextExtractionView.onClick(f, f5, true) || isSelectedNow() || isTextExtractionFullState())) {
            return true;
        }
        if (hasObjectCaptureData()) {
            return this.mObjectCaptureView.onLongClick(f, f5);
        }
        return false;
    }

    public boolean onSinglePress(float f, float f5) {
        if (!isTextExtractionFullState()) {
            return false;
        }
        this.mRootView.invalidate();
        if (this.mTextExtractionView.onClick(f, f5, false) || isSelectedNow()) {
            return true;
        }
        return false;
    }

    public boolean onTouch(MotionEvent motionEvent) {
        boolean z;
        boolean z3;
        boolean z7 = false;
        if (!hasTextExtractionData() || !this.mTextExtractionView.onTouch(motionEvent)) {
            z = false;
        } else {
            z = true;
        }
        if (!hasObjectCaptureData() || !this.mObjectCaptureView.onTouch(motionEvent)) {
            z3 = false;
        } else {
            z3 = true;
        }
        boolean z9 = z | z3;
        handleTouchOnCaptureActionMode(motionEvent);
        int action = motionEvent.getAction();
        if (!(action == 1 || action == 6 || action == 262 || action == 3 || action == 5 || action == 261)) {
            z7 = true;
        }
        return z9 & z7;
    }

    public void refreshView() {
        int bottomMarginFromSupplier = this.mRootView.getBottomMarginFromSupplier();
        int topMarginFromSupplier = this.mRootView.getTopMarginFromSupplier();
        RectF displayMinRect = this.mRootView.getDisplayMinRect();
        RectF displayRect = this.mRootView.getDisplayRect();
        this.mRootView.getLocation(this.mLocation);
        refreshViewInternal(this.mTextExtractionView, topMarginFromSupplier, bottomMarginFromSupplier, displayMinRect, displayRect);
        refreshViewInternal(this.mObjectCaptureView, topMarginFromSupplier, bottomMarginFromSupplier, displayMinRect, displayRect);
    }

    public void setCaptureActionMode(ICaptureActionMode iCaptureActionMode) {
        this.mActionMode = iCaptureActionMode;
    }

    public void setLogTag(Object obj) {
        this.TAG.setTag(obj);
    }
}
