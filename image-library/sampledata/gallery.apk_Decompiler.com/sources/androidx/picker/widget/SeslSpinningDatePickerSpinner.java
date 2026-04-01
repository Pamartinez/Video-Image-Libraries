package androidx.picker.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.icu.text.SimpleDateFormat;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeProvider;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.reflect.view.SeslViewReflector;
import java.util.Calendar;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SeslSpinningDatePickerSpinner extends LinearLayout {
    private static final DateFormatter mDateFormatter = new DateFormatter();
    private DatePickerDelegate mDelegate;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class AbsDatePickerDelegate implements DatePickerDelegate {
        Context mContext;
        SeslSpinningDatePickerSpinner mDelegator;

        public AbsDatePickerDelegate(SeslSpinningDatePickerSpinner seslSpinningDatePickerSpinner, Context context) {
            this.mDelegator = seslSpinningDatePickerSpinner;
            this.mContext = context;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CustomEditText extends EditText {
        private int mAdjustEditTextPosition;

        public CustomEditText(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public void onDraw(Canvas canvas) {
            canvas.translate(0.0f, (float) this.mAdjustEditTextPosition);
            super.onDraw(canvas);
        }

        public void setEditTextPosition(int i2) {
            this.mAdjustEditTextPosition = i2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DatePickerDelegate {
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

        void performLongClick();

        void scrollBy(int i2, int i7);

        void setEnabled(boolean z);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Formatter {
        String format(Calendar calendar);
    }

    public SeslSpinningDatePickerSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public static Formatter getDateFormatter() {
        return mDateFormatter;
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
        return this.mDelegate.getAccessibilityNodeProvider();
    }

    public int[] getEnableStateSet() {
        return LinearLayout.ENABLED_STATE_SET;
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
        this.mDelegate.onDraw(canvas);
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

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mDelegate.setEnabled(z);
    }

    public void setMeasuredDimensionWrapper(int i2, int i7) {
        setMeasuredDimension(i2, i7);
    }

    public void superOnMeasure(int i2, int i7) {
        super.onMeasure(i2, i7);
    }

    public SeslSpinningDatePickerSpinner(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public boolean isVisibleToUserWrapper(Rect rect) {
        return SeslViewReflector.isVisibleToUser(this, rect);
    }

    public SeslSpinningDatePickerSpinner(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        this.mDelegate = new SeslSpinningDatePickerSpinnerDelegate(this, context, attributeSet, i2, i7);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DateFormatter implements Formatter {
        final Object[] mArgs = new Object[1];
        Locale mCurrentLocale;
        SimpleDateFormat mFmt;

        public DateFormatter() {
            init(Locale.getDefault());
        }

        private SimpleDateFormat createFormatter(Locale locale) {
            if (isSimplifiedChinese(locale)) {
                return new SimpleDateFormat("EEEEE, MMM dd", locale);
            }
            if (isRTL(locale)) {
                return new SimpleDateFormat("EEEEE, MMM dd", locale);
            }
            return new SimpleDateFormat("EEE, MMM dd", locale);
        }

        private void init(Locale locale) {
            this.mFmt = createFormatter(locale);
            this.mCurrentLocale = locale;
        }

        private boolean isRTL(Locale locale) {
            byte directionality = Character.getDirectionality(locale.getDisplayName(locale).charAt(0));
            if (directionality == 1 || directionality == 2) {
                return true;
            }
            return false;
        }

        private boolean isSimplifiedChinese(Locale locale) {
            String language = locale.getLanguage();
            Locale locale2 = Locale.SIMPLIFIED_CHINESE;
            if (!language.equals(locale2.getLanguage()) || !locale.getCountry().equals(locale2.getCountry())) {
                return false;
            }
            return true;
        }

        public String format(Calendar calendar) {
            Locale locale = Locale.getDefault();
            if (!this.mCurrentLocale.equals(locale)) {
                init(locale);
            }
            this.mArgs[0] = calendar;
            return this.mFmt.format(calendar.getTime());
        }

        public String formatForAccessibility(Calendar calendar, Context context) {
            return DateUtils.formatDateTime(context, calendar.getTimeInMillis(), 26);
        }

        public String format(Calendar calendar, Context context) {
            this.mArgs[0] = calendar;
            return DateUtils.formatDateTime(context, calendar.getTimeInMillis(), 524314);
        }
    }
}
