package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SeekBar;
import androidx.appcompat.R$attr;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslSeekBar extends SeslAbsSeekBar {
    private int mOldValue;
    private OnSeekBarChangeListener mOnSeekBarChangeListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnSeekBarChangeListener {
        void onProgressChanged(SeslSeekBar seslSeekBar, int i2, boolean z);

        void onStartTrackingTouch(SeslSeekBar seslSeekBar);

        void onStopTrackingTouch(SeslSeekBar seslSeekBar);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnSeekBarHoverListener {
    }

    public SeslSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.seekBarStyle);
    }

    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    public void onHoverChanged(int i2, int i7, int i8) {
        super.onHoverChanged(i2, i7, i8);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (canUserSetProgress()) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS);
        }
    }

    public void onProgressRefresh(float f, boolean z, int i2) {
        super.onProgressRefresh(f, z, i2);
        if (!this.mIsSeamless) {
            OnSeekBarChangeListener onSeekBarChangeListener = this.mOnSeekBarChangeListener;
            if (onSeekBarChangeListener != null) {
                onSeekBarChangeListener.onProgressChanged(this, i2, z);
                return;
            }
            return;
        }
        int round = Math.round(((float) i2) / 1000.0f);
        if (this.mOldValue != round) {
            this.mOldValue = round;
            OnSeekBarChangeListener onSeekBarChangeListener2 = this.mOnSeekBarChangeListener;
            if (onSeekBarChangeListener2 != null) {
                onSeekBarChangeListener2.onProgressChanged(this, round, z);
            }
        }
    }

    public void onStartTrackingHover(int i2, int i7, int i8) {
        super.onStartTrackingHover(i2, i7, i8);
    }

    public void onStartTrackingTouch() {
        super.onStartTrackingTouch();
        OnSeekBarChangeListener onSeekBarChangeListener = this.mOnSeekBarChangeListener;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onStartTrackingTouch(this);
        }
    }

    public void onStopTrackingHover() {
        super.onStopTrackingHover();
    }

    public void onStopTrackingTouch() {
        super.onStopTrackingTouch();
        OnSeekBarChangeListener onSeekBarChangeListener = this.mOnSeekBarChangeListener;
        if (onSeekBarChangeListener != null) {
            onSeekBarChangeListener.onStopTrackingTouch(this);
        }
    }

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mOnSeekBarChangeListener = onSeekBarChangeListener;
    }

    public SeslSeekBar(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public SeslSeekBar(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
    }

    public void setOnSeekBarHoverListener(OnSeekBarHoverListener onSeekBarHoverListener) {
    }
}
