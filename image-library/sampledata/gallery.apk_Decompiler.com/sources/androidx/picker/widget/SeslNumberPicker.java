package androidx.picker.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.icu.text.DecimalFormatSymbols;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.reflect.view.SeslViewReflector;
import androidx.reflect.view.accessibility.SeslAccessibilityManagerReflector;
import androidx.reflect.widget.SeslTextViewReflector;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslNumberPicker extends LinearLayout {
    private static final TwoDigitFormatter sTwoDigitFormatter = new TwoDigitFormatter();
    private NumberPickerDelegate mDelegate;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class AbsNumberPickerDelegate implements NumberPickerDelegate {
        protected Context mContext;
        SeslNumberPicker mDelegator;

        public AbsNumberPickerDelegate(SeslNumberPicker seslNumberPicker, Context context) {
            this.mDelegator = seslNumberPicker;
            this.mContext = context;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CustomEditText extends EditText {
        private int mAdjustEditTextPosition;
        private String mPickerContentDescription = "";

        public CustomEditText(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            SeslTextViewReflector.setLocalePreferredLineHeightForMinimumUsed(this, false);
        }

        private CharSequence getTextForAccessibility() {
            Editable text = getText();
            if (this.mPickerContentDescription.equals("")) {
                return text;
            }
            if (!TextUtils.isEmpty(text)) {
                return text.toString() + ArcCommonLog.TAG_COMMA + this.mPickerContentDescription;
            }
            return ArcCommonLog.TAG_COMMA + this.mPickerContentDescription;
        }

        public void onDraw(Canvas canvas) {
            canvas.translate(0.0f, (float) this.mAdjustEditTextPosition);
            super.onDraw(canvas);
        }

        public void onEditorAction(int i2) {
            super.onEditorAction(i2);
            if (i2 == 6) {
                clearFocus();
            }
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            if (SeslAccessibilityManagerReflector.isScreenReaderEnabled((AccessibilityManager) getContext().getSystemService("accessibility"), true)) {
                accessibilityNodeInfo.setText(getText());
                AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setTooltipText(this.mPickerContentDescription);
                return;
            }
            accessibilityNodeInfo.setText(getTextForAccessibility());
        }

        public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            int size = accessibilityEvent.getText().size();
            super.onPopulateAccessibilityEvent(accessibilityEvent);
            int size2 = accessibilityEvent.getText().size();
            if (size2 > size) {
                accessibilityEvent.getText().remove(size2 - 1);
            }
            Editable text = getText();
            if (!TextUtils.isEmpty(text)) {
                accessibilityEvent.getText().add(text);
            }
            accessibilityEvent.setContentDescription(this.mPickerContentDescription);
        }

        public void setEditTextPosition(int i2) {
            this.mAdjustEditTextPosition = i2;
        }

        public void setPickerContentDescription(String str) {
            this.mPickerContentDescription = str;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface CustomTalkbackFormatter {
        String format(int i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Formatter {
        String format(int i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface NumberPickerDelegate {
        void applyWheelCustomInterval(boolean z);

        void computeScroll();

        int computeVerticalScrollExtent();

        int computeVerticalScrollOffset();

        int computeVerticalScrollRange();

        boolean dispatchHoverEvent(MotionEvent motionEvent);

        boolean dispatchKeyEvent(KeyEvent keyEvent);

        boolean dispatchKeyEventPreIme(KeyEvent keyEvent);

        boolean dispatchTouchEvent(MotionEvent motionEvent);

        void dispatchTrackballEvent(MotionEvent motionEvent);

        AccessibilityNodeProvider getAccessibilityNodeProvider();

        String[] getDisplayedValues();

        EditText getEditText();

        int getMaxValue();

        int getMinValue();

        int getPaintFlags();

        int getValue();

        boolean getWrapSelectorWheel();

        boolean isChangedDefaultInterval();

        boolean isEditTextMode();

        boolean isEditTextModeNotAmPm();

        void onAttachedToWindow();

        void onConfigurationChanged(Configuration configuration);

        void onDetachedFromWindow();

        void onDraw(Canvas canvas);

        void onFocusChanged(boolean z, int i2, Rect rect);

        boolean onGenericMotionEvent(MotionEvent motionEvent);

        void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent);

        boolean onInterceptTouchEvent(MotionEvent motionEvent);

        void onLayout(boolean z, int i2, int i7, int i8, int i10);

        void onMeasure(int i2, int i7);

        void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent);

        boolean onTouchEvent(MotionEvent motionEvent);

        void onWindowFocusChanged(boolean z);

        void onWindowVisibilityChanged(int i2);

        void performClick();

        void performClick(boolean z);

        void performLongClick();

        void scrollBy(int i2, int i7);

        void setAmPm();

        void setCustomIntervalValue(int i2);

        void setCustomNumberPickerIdleColor(int i2);

        void setCustomNumberPickerScrollColor(int i2);

        void setCustomTalkbackFormatter(CustomTalkbackFormatter customTalkbackFormatter);

        void setDateUnit(int i2);

        void setDisplayedValues(String[] strArr);

        void setEditTextMode(boolean z);

        void setEditTextModeEnabled(boolean z);

        void setEnabled(boolean z);

        void setErrorToastMessage(String str);

        void setFormatter(Formatter formatter);

        void setMaxInputLength(int i2);

        void setMaxValue(int i2);

        void setMinValue(int i2);

        void setMonthInputMode();

        void setOnEditTextModeChangedListener(OnEditTextModeChangedListener onEditTextModeChangedListener);

        void setOnScrollListener(OnScrollListener onScrollListener);

        void setOnValueChangedListener(OnValueChangeListener onValueChangeListener);

        void setPaintFlags(int i2);

        void setPickerContentDescription(String str);

        void setSubTextSize(float f);

        void setSubTextTypeface(Typeface typeface);

        void setTextSize(float f);

        void setTextTypeface(Typeface typeface);

        void setValue(int i2);

        void setWrapSelectorWheel(boolean z);

        void setYearDateTimeInputMode();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnEditTextModeChangedListener {
        void onEditTextModeChanged(SeslNumberPicker seslNumberPicker, boolean z);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnScrollListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnValueChangeListener {
        void onValueChange(SeslNumberPicker seslNumberPicker, int i2, int i7);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TwoDigitFormatter implements Formatter {
        final Object[] mArgs = new Object[1];
        final StringBuilder mBuilder = new StringBuilder();
        java.util.Formatter mFmt;
        char mZeroDigit;

        public TwoDigitFormatter() {
            init(Locale.getDefault());
        }

        private java.util.Formatter createFormatter(Locale locale) {
            return new java.util.Formatter(this.mBuilder, locale);
        }

        private static char getZeroDigit(Locale locale) {
            return DecimalFormatSymbols.getInstance(locale).getZeroDigit();
        }

        private void init(Locale locale) {
            this.mFmt = createFormatter(locale);
            this.mZeroDigit = getZeroDigit(locale);
        }

        public String format(int i2) {
            Locale locale = Locale.getDefault();
            if (this.mZeroDigit != getZeroDigit(locale)) {
                init(locale);
            }
            this.mArgs[0] = Integer.valueOf(i2);
            synchronized (this.mBuilder) {
                StringBuilder sb2 = this.mBuilder;
                sb2.delete(0, sb2.length());
                this.mFmt.format("%02d", this.mArgs);
            }
            return this.mFmt.toString();
        }
    }

    public SeslNumberPicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public static Formatter getTwoDigitFormatter() {
        return sTwoDigitFormatter;
    }

    public void applyWheelCustomInterval(boolean z) {
        this.mDelegate.applyWheelCustomInterval(z);
    }

    public void computeScroll() {
        this.mDelegate.computeScroll();
    }

    public int computeVerticalScrollExtent() {
        return this.mDelegate.computeVerticalScrollExtent();
    }

    public int computeVerticalScrollOffset() {
        return this.mDelegate.computeVerticalScrollOffset();
    }

    public int computeVerticalScrollRange() {
        return this.mDelegate.computeVerticalScrollRange();
    }

    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (this.mDelegate.isEditTextModeNotAmPm()) {
            return super.dispatchHoverEvent(motionEvent);
        }
        return this.mDelegate.dispatchHoverEvent(motionEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.mDelegate.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchKeyEventPreIme(KeyEvent keyEvent) {
        if (this.mDelegate.dispatchKeyEventPreIme(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEventPreIme(keyEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        this.mDelegate.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        this.mDelegate.dispatchTrackballEvent(motionEvent);
        return super.dispatchTrackballEvent(motionEvent);
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        if (this.mDelegate.isEditTextModeNotAmPm()) {
            return super.getAccessibilityNodeProvider();
        }
        return this.mDelegate.getAccessibilityNodeProvider();
    }

    public String[] getDisplayedValues() {
        return this.mDelegate.getDisplayedValues();
    }

    public EditText getEditText() {
        return this.mDelegate.getEditText();
    }

    public int[] getEnableStateSet() {
        return LinearLayout.ENABLED_STATE_SET;
    }

    public int getMaxValue() {
        return this.mDelegate.getMaxValue();
    }

    public int getMinValue() {
        return this.mDelegate.getMinValue();
    }

    public int getPaintFlags() {
        return this.mDelegate.getPaintFlags();
    }

    public int getValue() {
        return this.mDelegate.getValue();
    }

    public boolean getWrapSelectorWheel() {
        return this.mDelegate.getWrapSelectorWheel();
    }

    public boolean isChangedDefaultInterval() {
        return this.mDelegate.isChangedDefaultInterval();
    }

    public boolean isEditTextMode() {
        return this.mDelegate.isEditTextMode();
    }

    public boolean isVisibleToUserWrapper() {
        return SeslViewReflector.isVisibleToUser(this);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mDelegate.onAttachedToWindow();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mDelegate.onConfigurationChanged(configuration);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mDelegate.onDetachedFromWindow();
    }

    public void onDraw(Canvas canvas) {
        if (this.mDelegate.isEditTextModeNotAmPm()) {
            super.onDraw(canvas);
        } else {
            this.mDelegate.onDraw(canvas);
        }
    }

    public void onFocusChanged(boolean z, int i2, Rect rect) {
        this.mDelegate.onFocusChanged(z, i2, rect);
        super.onFocusChanged(z, i2, rect);
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (this.mDelegate.onGenericMotionEvent(motionEvent)) {
            return true;
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        this.mDelegate.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mDelegate.onInterceptTouchEvent(motionEvent);
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        this.mDelegate.onLayout(z, i2, i7, i8, i10);
    }

    public void onMeasure(int i2, int i7) {
        this.mDelegate.onMeasure(i2, i7);
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        this.mDelegate.onPopulateAccessibilityEvent(accessibilityEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mDelegate.onTouchEvent(motionEvent);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.mDelegate.onWindowFocusChanged(z);
    }

    public void onWindowVisibilityChanged(int i2) {
        this.mDelegate.onWindowVisibilityChanged(i2);
        super.onWindowVisibilityChanged(i2);
    }

    public boolean performClick() {
        if (this.mDelegate.isEditTextModeNotAmPm()) {
            return super.performClick();
        }
        if (super.performClick()) {
            return true;
        }
        this.mDelegate.performClick();
        return true;
    }

    public boolean performLongClick() {
        if (super.performLongClick()) {
            return true;
        }
        this.mDelegate.performLongClick();
        return true;
    }

    public void scrollBy(int i2, int i7) {
        this.mDelegate.scrollBy(i2, i7);
    }

    public void setAmPm() {
        this.mDelegate.setAmPm();
    }

    public void setCustomIntervalValue(int i2) {
        this.mDelegate.setCustomIntervalValue(i2);
    }

    public void setCustomNumberPickerIdleColor(int i2) {
        this.mDelegate.setCustomNumberPickerIdleColor(i2);
    }

    public void setCustomNumberPickerScrollColor(int i2) {
        this.mDelegate.setCustomNumberPickerScrollColor(i2);
    }

    public void setCustomTalkbackFormatter(CustomTalkbackFormatter customTalkbackFormatter) {
        this.mDelegate.setCustomTalkbackFormatter(customTalkbackFormatter);
    }

    public void setDateUnit(int i2) {
        this.mDelegate.setDateUnit(i2);
    }

    public void setDisplayedValues(String[] strArr) {
        this.mDelegate.setDisplayedValues(strArr);
    }

    public void setEditTextMode(boolean z) {
        this.mDelegate.setEditTextMode(z);
    }

    public void setEditTextModeEnabled(boolean z) {
        this.mDelegate.setEditTextModeEnabled(z);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mDelegate.setEnabled(z);
    }

    public void setErrorToastMessage(String str) {
        this.mDelegate.setErrorToastMessage(str);
    }

    public void setFormatter(Formatter formatter) {
        this.mDelegate.setFormatter(formatter);
    }

    public void setMaxInputLength(int i2) {
        this.mDelegate.setMaxInputLength(i2);
    }

    public void setMaxValue(int i2) {
        this.mDelegate.setMaxValue(i2);
    }

    public void setMeasuredDimensionWrapper(int i2, int i7) {
        setMeasuredDimension(i2, i7);
    }

    public void setMinValue(int i2) {
        this.mDelegate.setMinValue(i2);
    }

    public void setMonthInputMode() {
        this.mDelegate.setMonthInputMode();
    }

    public void setOnEditTextModeChangedListener(OnEditTextModeChangedListener onEditTextModeChangedListener) {
        this.mDelegate.setOnEditTextModeChangedListener(onEditTextModeChangedListener);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mDelegate.setOnScrollListener(onScrollListener);
    }

    public void setOnValueChangedListener(OnValueChangeListener onValueChangeListener) {
        this.mDelegate.setOnValueChangedListener(onValueChangeListener);
    }

    public void setPaintFlags(int i2) {
        this.mDelegate.setPaintFlags(i2);
    }

    public void setPickerContentDescription(String str) {
        this.mDelegate.setPickerContentDescription(str);
    }

    public void setSubTextSize(float f) {
        this.mDelegate.setSubTextSize(f);
    }

    public void setSubTextTypeface(Typeface typeface) {
        this.mDelegate.setSubTextTypeface(typeface);
    }

    public void setTextSize(float f) {
        this.mDelegate.setTextSize(f);
    }

    public void setTextTypeface(Typeface typeface) {
        this.mDelegate.setTextTypeface(typeface);
    }

    public void setValue(int i2) {
        this.mDelegate.setValue(i2);
    }

    public void setWrapSelectorWheel(boolean z) {
        this.mDelegate.setWrapSelectorWheel(z);
    }

    public void setYearDateTimeInputMode() {
        this.mDelegate.setYearDateTimeInputMode();
    }

    public void superOnMeasure(int i2, int i7) {
        super.onMeasure(i2, i7);
    }

    public SeslNumberPicker(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public boolean isVisibleToUserWrapper(Rect rect) {
        return SeslViewReflector.isVisibleToUser(this, rect);
    }

    public SeslNumberPicker(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        this.mDelegate = new SeslNumberPickerSpinnerDelegate(this, context, attributeSet, i2, i7);
    }

    public void performClick(boolean z) {
        this.mDelegate.performClick(z);
    }

    public void setOnLongPressUpdateInterval(long j2) {
    }

    public void setSkipValuesOnLongPressEnabled(boolean z) {
    }
}
