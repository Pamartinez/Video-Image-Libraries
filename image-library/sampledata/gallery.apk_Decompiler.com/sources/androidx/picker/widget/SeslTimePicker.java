package androidx.picker.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.core.math.MathUtils;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslTimePicker extends FrameLayout {
    private TimePickerDelegate mDelegate;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class AbsTimePickerDelegate implements TimePickerDelegate {
        protected Context mContext;
        Locale mCurrentLocale;
        SeslTimePicker mDelegator;
        OnTimeChangedListener mOnTimeChangedListener;

        public AbsTimePickerDelegate(SeslTimePicker seslTimePicker, Context context) {
            this.mDelegator = seslTimePicker;
            this.mContext = context;
            setCurrentLocale(Locale.getDefault());
        }

        public void setCurrentLocale(Locale locale) {
            if (!locale.equals(this.mCurrentLocale)) {
                this.mCurrentLocale = locale;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnEditTextModeChangedListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnTimeChangedListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface TimePickerDelegate {
        boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent);

        int getBaseline();

        int getDefaultHeight();

        int getDefaultWidth();

        int getHour();

        int getMinute();

        boolean isEnabled();

        void onConfigurationChanged(Configuration configuration);

        void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent);

        void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo);

        void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent);

        void onRestoreInstanceState(Parcelable parcelable);

        Parcelable onSaveInstanceState(Parcelable parcelable);

        void requestLayout();

        void set5MinuteInterval(boolean z);

        void setCurrentLocale(Locale locale);

        void setCustomTimePickerIdleColor(int i2);

        void setCustomTimePickerScrollColor(int i2);

        void setEditTextMode(boolean z);

        void setEnabled(boolean z);

        void setHour(int i2);

        void setIs24Hour(boolean z);

        void setMinute(int i2);

        void setOnEditTextModeChangedListener(OnEditTextModeChangedListener onEditTextModeChangedListener);

        void setOnTimeChangedListener(OnTimeChangedListener onTimeChangedListener);

        void showMarginLeft(boolean z);
    }

    public SeslTimePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16843933);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return this.mDelegate.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    public int getBaseline() {
        return this.mDelegate.getBaseline();
    }

    public int getHour() {
        return this.mDelegate.getHour();
    }

    public int getMinute() {
        return this.mDelegate.getMinute();
    }

    public boolean isEnabled() {
        return this.mDelegate.isEnabled();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mDelegate.onConfigurationChanged(configuration);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        this.mDelegate.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        this.mDelegate.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
    }

    public void onMeasure(int i2, int i7) {
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i7);
        if (mode == Integer.MIN_VALUE) {
            i2 = View.MeasureSpec.makeMeasureSpec(this.mDelegate.getDefaultWidth(), 1073741824);
        }
        if (mode2 == Integer.MIN_VALUE) {
            i7 = View.MeasureSpec.makeMeasureSpec(this.mDelegate.getDefaultHeight(), 1073741824);
        }
        super.onMeasure(i2, i7);
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        this.mDelegate.onPopulateAccessibilityEvent(accessibilityEvent);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        View.BaseSavedState baseSavedState = (View.BaseSavedState) parcelable;
        super.onRestoreInstanceState(baseSavedState.getSuperState());
        this.mDelegate.onRestoreInstanceState(baseSavedState);
    }

    public Parcelable onSaveInstanceState() {
        return this.mDelegate.onSaveInstanceState(super.onSaveInstanceState());
    }

    public void requestLayout() {
        super.requestLayout();
        TimePickerDelegate timePickerDelegate = this.mDelegate;
        if (timePickerDelegate != null) {
            timePickerDelegate.requestLayout();
        }
    }

    public void set5MinuteInterval(boolean z) {
        this.mDelegate.set5MinuteInterval(z);
    }

    public void setCustomTimePickerIdleColor(int i2) {
        this.mDelegate.setCustomTimePickerIdleColor(i2);
    }

    public void setCustomTimePickerScrollColor(int i2) {
        this.mDelegate.setCustomTimePickerScrollColor(i2);
    }

    public void setEditTextMode(boolean z) {
        this.mDelegate.setEditTextMode(z);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mDelegate.setEnabled(z);
    }

    public void setHour(int i2) {
        this.mDelegate.setHour(MathUtils.clamp(i2, 0, 23));
    }

    public void setIs24HourView(Boolean bool) {
        if (bool != null) {
            this.mDelegate.setIs24Hour(bool.booleanValue());
        }
    }

    public void setLocale(Locale locale) {
        this.mDelegate.setCurrentLocale(locale);
    }

    public void setMinute(int i2) {
        this.mDelegate.setMinute(MathUtils.clamp(i2, 0, 59));
    }

    public void setOnEditTextModeChangedListener(OnEditTextModeChangedListener onEditTextModeChangedListener) {
        this.mDelegate.setOnEditTextModeChangedListener(onEditTextModeChangedListener);
    }

    public void setOnTimeChangedListener(OnTimeChangedListener onTimeChangedListener) {
        this.mDelegate.setOnTimeChangedListener(onTimeChangedListener);
    }

    public void showMarginLeft(Boolean bool) {
        this.mDelegate.showMarginLeft(bool.booleanValue());
    }

    public SeslTimePicker(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public SeslTimePicker(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        this.mDelegate = new SeslTimePickerSpinnerDelegate(this, context, attributeSet, i2, i7);
    }
}
