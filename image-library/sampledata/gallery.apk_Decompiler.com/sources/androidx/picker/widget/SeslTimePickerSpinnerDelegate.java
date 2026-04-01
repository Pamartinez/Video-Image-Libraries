package androidx.picker.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.icu.text.DateFormatSymbols;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.picker.R$color;
import androidx.picker.R$dimen;
import androidx.picker.R$id;
import androidx.picker.R$layout;
import androidx.picker.R$string;
import androidx.picker.R$styleable;
import androidx.picker.util.SeslPickerBasicUtils;
import androidx.picker.widget.SeslNumberPicker;
import androidx.picker.widget.SeslTimePicker;
import androidx.reflect.icu.SeslLocaleDataReflector;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.Calendar;
import java.util.Locale;
import q4.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SeslTimePickerSpinnerDelegate extends SeslTimePicker.AbsTimePickerDelegate {
    /* access modifiers changed from: private */
    public static final char[] DIGIT_CHARACTERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 1632, 1633, 1634, 1635, 1636, 1637, 1638, 1639, 1640, 1641, 1776, 1777, 1778, 1779, 1780, 1781, 1782, 1783, 1784, 1785, 2406, 2407, 2408, 2409, 2410, 2411, 2412, 2413, 2414, 2415, 2534, 2535, 2536, 2537, 2538, 2539, 2540, 2541, 2542, 2543, 3302, 3303, 3304, 3305, 3306, 3307, 3308, 3309, 3310, 3311, 4160, 4161, 4162, 4163, 4164, 4165, 4166, 4167, 4168, 4169};
    private final View mAmPmMarginInside;
    /* access modifiers changed from: private */
    public final SeslNumberPicker mAmPmSpinner;
    private final EditText mAmPmSpinnerInput;
    private final String[] mAmPmStrings;
    private final View mDTPaddingLeft;
    private final View mDTPaddingRight;
    private final int mDefaultHeight;
    private final int mDefaultWidth;
    private final TextView mDivider;
    private TextView.OnEditorActionListener mEditorActionListener = new TextView.OnEditorActionListener() {
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            if (i2 == 6) {
                if (!SeslTimePickerSpinnerDelegate.this.mSkipToChangeInterval && !SeslTimePickerSpinnerDelegate.this.mMinuteSpinner.isChangedDefaultInterval() && SeslTimePickerSpinnerDelegate.this.mMinuteSpinner.getValue() % 5 != 0) {
                    SeslTimePickerSpinnerDelegate.this.mMinuteSpinner.applyWheelCustomInterval(false);
                }
                SeslTimePickerSpinnerDelegate.this.updateInputState();
                SeslTimePickerSpinnerDelegate.this.setEditTextMode(false);
            }
            return false;
        }
    };
    /* access modifiers changed from: private */
    public char mHourFormat;
    private final SeslNumberPicker mHourSpinner;
    /* access modifiers changed from: private */
    public final EditText mHourSpinnerInput;
    private boolean mHourWithTwoDigit;
    private boolean mIs24HourView;
    /* access modifiers changed from: private */
    public boolean mIsAm;
    /* access modifiers changed from: private */
    public boolean mIsAmPmAutoFlipped = false;
    private boolean mIsDateTimeMode;
    /* access modifiers changed from: private */
    public boolean mIsEditTextMode;
    private boolean mIsEnabled = true;
    /* access modifiers changed from: private */
    public boolean mIsInvalidMinute = false;
    private boolean mIsMarginLeftShown;
    private int mLayoutMode;
    private int mMinuteInterval = 1;
    /* access modifiers changed from: private */
    public final SeslNumberPicker mMinuteSpinner;
    /* access modifiers changed from: private */
    public final EditText mMinuteSpinnerInput;
    private SeslNumberPicker.OnEditTextModeChangedListener mModeChangeListener = new SeslNumberPicker.OnEditTextModeChangedListener() {
        public void onEditTextModeChanged(SeslNumberPicker seslNumberPicker, boolean z) {
            SeslTimePickerSpinnerDelegate.this.setEditTextMode(z);
            SeslTimePickerSpinnerDelegate.this.updateModeState(z);
        }
    };
    private final View mPaddingLeft;
    private final View mPaddingRight;
    /* access modifiers changed from: private */
    public EditText[] mPickerTexts = new EditText[3];
    /* access modifiers changed from: private */
    public boolean mSkipToChangeInterval = false;
    private Calendar mTempCalendar;
    private final LinearLayout mTimeLayout;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SeslKeyListener implements View.OnKeyListener {
        public SeslKeyListener() {
        }

        public boolean onKey(View view, int i2, KeyEvent keyEvent) {
            if (keyEvent.getAction() != 1) {
                return false;
            }
            if (i2 != 23) {
                if (i2 != 61) {
                    if (i2 != 66 && i2 != 160) {
                        return false;
                    }
                    if (SeslTimePickerSpinnerDelegate.this.isEditTextMode()) {
                        EditText editText = (EditText) view;
                        if ((editText.getImeOptions() & 5) == 5) {
                            View findNextFocus = FocusFinder.getInstance().findNextFocus(SeslTimePickerSpinnerDelegate.this.mDelegator, view, 2);
                            if (findNextFocus == null) {
                                return true;
                            }
                            findNextFocus.requestFocus();
                        } else if ((editText.getImeOptions() & 6) == 6) {
                            SeslTimePickerSpinnerDelegate.this.updateInputState();
                            SeslTimePickerSpinnerDelegate.this.setEditTextMode(false);
                        }
                    }
                }
                return true;
            } else if (SeslTimePickerSpinnerDelegate.this.mDelegator.getResources().getConfiguration().keyboard != 3) {
                return true;
            } else {
                return false;
            }
        }
    }

    public SeslTimePickerSpinnerDelegate(SeslTimePicker seslTimePicker, Context context, AttributeSet attributeSet, int i2, int i7) {
        super(seslTimePicker, context);
        boolean z;
        boolean z3;
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, R$styleable.TimePicker, i2, i7);
        this.mIsDateTimeMode = obtainStyledAttributes.getBoolean(R$styleable.TimePicker_dateTimeMode, false);
        this.mLayoutMode = obtainStyledAttributes.getInt(R$styleable.TimePicker_timeLayoutMode, 0);
        obtainStyledAttributes.recycle();
        LayoutInflater from = LayoutInflater.from(this.mContext);
        if (isDateTimeMode()) {
            int i8 = this.mLayoutMode;
            if (i8 == 1) {
                from.inflate(R$layout.sesl_spinning_datepicker_time_picker_spinner_phone, this.mDelegator, true);
            } else if (i8 == 2) {
                from.inflate(R$layout.sesl_spinning_datepicker_time_picker_spinner_multipane, this.mDelegator, true);
            } else {
                from.inflate(R$layout.sesl_spinning_datepicker_time_picker_spinner, this.mDelegator, true);
            }
        } else {
            from.inflate(R$layout.sesl_time_picker_spinner, this.mDelegator, true);
        }
        SeslNumberPicker seslNumberPicker = (SeslNumberPicker) seslTimePicker.findViewById(R$id.sesl_timepicker_hour);
        this.mHourSpinner = seslNumberPicker;
        seslNumberPicker.setPickerContentDescription(context.getResources().getString(R$string.sesl_time_picker_hour));
        seslNumberPicker.setOnEditTextModeChangedListener(this.mModeChangeListener);
        seslNumberPicker.setOnValueChangedListener(new SeslNumberPicker.OnValueChangeListener() {
            public void onValueChange(SeslNumberPicker seslNumberPicker, int i2, int i7) {
                int i8;
                boolean z;
                if (!SeslTimePickerSpinnerDelegate.this.is24Hour() && !SeslTimePickerSpinnerDelegate.this.mIsEditTextMode) {
                    if (SeslTimePickerSpinnerDelegate.this.mHourFormat == 'K') {
                        i8 = 0;
                    } else {
                        i8 = 12;
                    }
                    if ((i2 == 11 && i7 == i8) || (i2 == i8 && i7 == 11)) {
                        SeslTimePickerSpinnerDelegate seslTimePickerSpinnerDelegate = SeslTimePickerSpinnerDelegate.this;
                        if (seslTimePickerSpinnerDelegate.mAmPmSpinner.getValue() != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        boolean unused = seslTimePickerSpinnerDelegate.mIsAm = z;
                        SeslTimePickerSpinnerDelegate.this.mAmPmSpinner.setEnabled(false);
                        SeslTimePickerSpinnerDelegate.this.mAmPmSpinner.performClick(false);
                        boolean unused2 = SeslTimePickerSpinnerDelegate.this.mIsAmPmAutoFlipped = true;
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                boolean unused = SeslTimePickerSpinnerDelegate.this.mIsAmPmAutoFlipped = false;
                                if (SeslTimePickerSpinnerDelegate.this.mAmPmSpinner != null) {
                                    SeslTimePickerSpinnerDelegate.this.mAmPmSpinner.setEnabled(true);
                                }
                            }
                        }, 500);
                    }
                }
                SeslTimePickerSpinnerDelegate.this.onTimeChanged();
            }
        });
        int i10 = R$id.numberpicker_input;
        EditText editText = (EditText) seslNumberPicker.findViewById(i10);
        this.mHourSpinnerInput = editText;
        seslNumberPicker.setYearDateTimeInputMode();
        editText.setImeOptions(33554437);
        seslNumberPicker.setMaxInputLength(2);
        editText.setOnEditorActionListener(this.mEditorActionListener);
        TextView textView = (TextView) this.mDelegator.findViewById(R$id.sesl_timepicker_divider);
        this.mDivider = textView;
        if (textView != null) {
            setDividerText();
        }
        Resources resources = this.mDelegator.getResources();
        int i11 = resources.getConfiguration().smallestScreenWidthDp;
        if (i11 >= 600) {
            this.mDefaultWidth = resources.getDimensionPixelSize(R$dimen.sesl_time_picker_dialog_min_width);
        } else {
            this.mDefaultWidth = (int) (TypedValue.applyDimension(1, (float) i11, resources.getDisplayMetrics()) + 0.5f);
        }
        this.mDefaultHeight = resources.getDimensionPixelSize(R$dimen.sesl_time_picker_spinner_height);
        SeslNumberPicker seslNumberPicker2 = (SeslNumberPicker) this.mDelegator.findViewById(R$id.sesl_timepicker_minute);
        this.mMinuteSpinner = seslNumberPicker2;
        seslNumberPicker2.setYearDateTimeInputMode();
        seslNumberPicker2.setMinValue(0);
        seslNumberPicker2.setMaxValue(59);
        seslNumberPicker2.setOnLongPressUpdateInterval(100);
        seslNumberPicker2.setSkipValuesOnLongPressEnabled(true);
        seslNumberPicker2.setFormatter(SeslNumberPicker.getTwoDigitFormatter());
        seslNumberPicker2.setPickerContentDescription(context.getResources().getString(R$string.sesl_time_picker_minute));
        seslNumberPicker2.setOnEditTextModeChangedListener(this.mModeChangeListener);
        seslNumberPicker2.setOnValueChangedListener(new SeslNumberPicker.OnValueChangeListener() {
            public void onValueChange(SeslNumberPicker seslNumberPicker, int i2, int i7) {
                SeslTimePickerSpinnerDelegate.this.onTimeChanged();
            }
        });
        EditText editText2 = (EditText) seslNumberPicker2.findViewById(i10);
        this.mMinuteSpinnerInput = editText2;
        editText2.setImeOptions(33554438);
        seslNumberPicker2.setMaxInputLength(2);
        editText2.setOnEditorActionListener(this.mEditorActionListener);
        String[] amPmStrings = getAmPmStrings(context);
        this.mAmPmStrings = amPmStrings;
        View findViewById = this.mDelegator.findViewById(R$id.sesl_timepicker_ampm);
        this.mDTPaddingRight = this.mDelegator.findViewById(R$id.sesl_datetimepicker_padding_right);
        this.mDTPaddingLeft = this.mDelegator.findViewById(R$id.sesl_datetimepicker_padding_left);
        this.mIsMarginLeftShown = false;
        this.mPaddingLeft = this.mDelegator.findViewById(R$id.sesl_timepicker_padding_left);
        this.mPaddingRight = this.mDelegator.findViewById(R$id.sesl_timepicker_padding_right);
        this.mTimeLayout = (LinearLayout) this.mDelegator.findViewById(R$id.sesl_timepicker_hour_minute_layout);
        this.mAmPmMarginInside = this.mDelegator.findViewById(R$id.sesl_timepicker_ampm_picker_margin);
        SeslNumberPicker seslNumberPicker3 = (SeslNumberPicker) findViewById;
        this.mAmPmSpinner = seslNumberPicker3;
        seslNumberPicker3.setAmPm();
        seslNumberPicker3.setMinValue(0);
        seslNumberPicker3.setMaxValue(1);
        seslNumberPicker3.setDisplayedValues(amPmStrings);
        seslNumberPicker3.setOnValueChangedListener(new SeslNumberPicker.OnValueChangeListener() {
            public void onValueChange(SeslNumberPicker seslNumberPicker, int i2, int i7) {
                boolean z = true;
                if (!SeslTimePickerSpinnerDelegate.this.mAmPmSpinner.isEnabled()) {
                    SeslTimePickerSpinnerDelegate.this.mAmPmSpinner.setEnabled(true);
                }
                if (SeslTimePickerSpinnerDelegate.this.mIsAmPmAutoFlipped) {
                    boolean unused = SeslTimePickerSpinnerDelegate.this.mIsAmPmAutoFlipped = false;
                } else if (SeslTimePickerSpinnerDelegate.this.mIsAm && i7 == 0) {
                } else {
                    if (SeslTimePickerSpinnerDelegate.this.mIsAm || i7 != 1) {
                        SeslTimePickerSpinnerDelegate seslTimePickerSpinnerDelegate = SeslTimePickerSpinnerDelegate.this;
                        if (i7 != 0) {
                            z = false;
                        }
                        boolean unused2 = seslTimePickerSpinnerDelegate.mIsAm = z;
                        SeslTimePickerSpinnerDelegate.this.updateAmPmControl();
                        SeslTimePickerSpinnerDelegate.this.onTimeChanged();
                        SeslTimePickerSpinnerDelegate.this.validCheck();
                    }
                }
            }
        });
        EditText editText3 = (EditText) seslNumberPicker3.findViewById(i10);
        this.mAmPmSpinnerInput = editText3;
        editText3.setInputType(0);
        editText3.setCursorVisible(false);
        editText3.setFocusable(false);
        editText3.setFocusableInTouchMode(false);
        byte directionality = Character.getDirectionality(amPmStrings[0].charAt(0));
        if (directionality == 1 || directionality == 2) {
            z = true;
        } else {
            z = false;
        }
        Locale locale = this.mCurrentLocale;
        byte directionality2 = Character.getDirectionality(locale.getDisplayName(locale).charAt(0));
        if (directionality2 == 1 || directionality2 == 2) {
            z3 = true;
        } else {
            z3 = false;
        }
        boolean isAmPmAtStart = isAmPmAtStart();
        if ((isAmPmAtStart && z3 == z) || (!isAmPmAtStart && z3 != z)) {
            changeAmPmView();
        }
        getHourFormatData();
        updateHourControl();
        updateAmPmControl();
        Calendar calendar = this.mTempCalendar;
        if (calendar != null) {
            setHour(calendar.get(11));
            setMinute(this.mTempCalendar.get(12));
        }
        if (!isEnabled()) {
            setEnabled(false);
        }
        if (this.mDelegator.getImportantForAccessibility() == 0) {
            this.mDelegator.setImportantForAccessibility(1);
        }
        setTextWatcher();
        if (isDateTimeMode()) {
            float dimensionPixelSize = (((float) resources.getDimensionPixelSize(R$dimen.sesl_spinning_date_picker_date_spinner_text_size)) * 160.0f) / ((float) resources.getDisplayMetrics().densityDpi);
            setNumberPickerTextSize(0, dimensionPixelSize);
            setNumberPickerTextSize(1, dimensionPixelSize);
            setNumberPickerTextSize(3, dimensionPixelSize);
            setNumberPickerTextSize(2, dimensionPixelSize);
        }
    }

    private static int appendQuotedText(SpannableStringBuilder spannableStringBuilder, int i2) {
        int length = spannableStringBuilder.length();
        int i7 = i2 + 1;
        if (i7 >= length || spannableStringBuilder.charAt(i7) != '\'') {
            spannableStringBuilder.delete(i2, i7);
            int i8 = length - 1;
            int i10 = 0;
            while (i2 < i8) {
                if (spannableStringBuilder.charAt(i2) == '\'') {
                    int i11 = i2 + 1;
                    if (i11 >= i8 || spannableStringBuilder.charAt(i11) != '\'') {
                        spannableStringBuilder.delete(i2, i11);
                        return i10;
                    }
                    spannableStringBuilder.delete(i2, i11);
                    i8--;
                    i10++;
                    i2 = i11;
                } else {
                    i2++;
                    i10++;
                }
            }
            return i10;
        }
        spannableStringBuilder.delete(i2, i7);
        return 1;
    }

    private void changeAmPmView() {
        ViewGroup viewGroup = (ViewGroup) this.mDelegator.findViewById(R$id.sesl_timepicker_layout);
        viewGroup.removeView(this.mAmPmSpinner);
        viewGroup.removeView(this.mAmPmMarginInside);
        int i2 = 1;
        if (isDateTimeMode()) {
            i2 = 1 + viewGroup.indexOfChild(this.mDTPaddingLeft);
        }
        viewGroup.addView(this.mAmPmMarginInside, i2);
        viewGroup.addView(this.mAmPmSpinner, i2);
    }

    public static String[] getAmPmStrings(Context context) {
        String str;
        String str2;
        String[] strArr = new String[2];
        if (Build.VERSION.SDK_INT >= 31) {
            DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(GregorianCalendar.class, context.getResources().getConfiguration().locale);
            String[] amPmStrings = dateFormatSymbols.getAmPmStrings();
            String[] ampmNarrowStrings = SeslLocaleDataReflector.getAmpmNarrowStrings(dateFormatSymbols);
            if (isMeaLanguage()) {
                strArr[0] = amPmStrings[0];
                strArr[1] = amPmStrings[1];
                return strArr;
            }
            if (amPmStrings[0].length() > 4) {
                str = ampmNarrowStrings[0];
            } else {
                str = amPmStrings[0];
            }
            strArr[0] = str;
            if (amPmStrings[1].length() > 4) {
                str2 = ampmNarrowStrings[1];
            } else {
                str2 = amPmStrings[1];
            }
            strArr[1] = str2;
            return strArr;
        }
        Object obj = SeslLocaleDataReflector.get(context.getResources().getConfiguration().locale);
        if (obj != null) {
            String[] field_amPm = SeslLocaleDataReflector.getField_amPm(obj);
            String field_narrowAm = SeslLocaleDataReflector.getField_narrowAm(obj);
            String field_narrowPm = SeslLocaleDataReflector.getField_narrowPm(obj);
            String str3 = field_amPm[0];
            String str4 = field_amPm[1];
            if (isMeaLanguage()) {
                strArr[0] = str3;
                strArr[1] = str4;
                return strArr;
            }
            if (str3.length() <= 4) {
                field_narrowAm = str3;
            }
            strArr[0] = field_narrowAm;
            if (str4.length() <= 4) {
                field_narrowPm = str4;
            }
            strArr[1] = field_narrowPm;
            return strArr;
        }
        Log.e("SeslTimePickerSpinner", "LocaleData failed. Use DateFormatSymbols for ampm");
        return new java.text.DateFormatSymbols().getAmPmStrings();
    }

    private void getHourFormatData() {
        String str;
        Locale locale = this.mCurrentLocale;
        if (this.mIs24HourView) {
            str = "Hm";
        } else {
            str = "hm";
        }
        String bestDateTimePattern = DateFormat.getBestDateTimePattern(locale, str);
        int length = bestDateTimePattern.length();
        this.mHourWithTwoDigit = false;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = bestDateTimePattern.charAt(i2);
            if (charAt == 'H' || charAt == 'h' || charAt == 'K' || charAt == 'k') {
                this.mHourFormat = charAt;
                int i7 = i2 + 1;
                if (i7 < length && charAt == bestDateTimePattern.charAt(i7)) {
                    this.mHourWithTwoDigit = true;
                    return;
                }
                return;
            }
        }
    }

    private static String getHourMinSeparatorFromPattern(String str) {
        boolean z = false;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt != ' ') {
                if (charAt != '\'') {
                    if (charAt == 'H' || charAt == 'K' || charAt == 'h' || charAt == 'k') {
                        z = true;
                    } else if (z) {
                        return Character.toString(str.charAt(i2));
                    }
                } else if (z) {
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str.substring(i2));
                    return spannableStringBuilder.subSequence(0, appendQuotedText(spannableStringBuilder, 0)).toString();
                }
            }
        }
        return NumericEnum.SEP;
    }

    private int getMinuteInterval() {
        return this.mMinuteInterval;
    }

    private boolean isAmPmAtStart() {
        return DateFormat.getBestDateTimePattern(this.mCurrentLocale, "hm").startsWith("a");
    }

    private static boolean isCharacterNumberLanguage() {
        String language = Locale.getDefault().getLanguage();
        if ("lo".equals(language) || "ar".equals(language) || "fa".equals(language) || "ur".equals(language) || "my".equals(language)) {
            return true;
        }
        return false;
    }

    private boolean isDateTimeMode() {
        return this.mIsDateTimeMode;
    }

    private static boolean isMeaLanguage() {
        String language = Locale.getDefault().getLanguage();
        if ("lo".equals(language) || "ar".equals(language) || "fa".equals(language) || "ur".equals(language)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void onTimeChanged() {
        SeslTimePicker.OnTimeChangedListener onTimeChangedListener = this.mOnTimeChangedListener;
        if (onTimeChangedListener != null) {
            ((j) onTimeChangedListener).d.onTimeChanged(this.mDelegator, getHour(), getMinute());
        }
    }

    private void setCurrentHour(int i2, boolean z) {
        if (i2 != getHour()) {
            if (!is24Hour()) {
                if (i2 >= 12) {
                    this.mIsAm = false;
                    if (i2 > 12) {
                        i2 -= 12;
                    }
                } else {
                    this.mIsAm = true;
                    if (i2 == 0) {
                        i2 = 12;
                    }
                }
                updateAmPmControl();
            }
            this.mHourSpinner.setValue(i2);
            if (z) {
                onTimeChanged();
            }
        }
    }

    private void setDividerText() {
        String str;
        Typeface typeface;
        if (this.mIs24HourView) {
            str = "Hm";
        } else {
            str = "hm";
        }
        this.mDivider.setText(getHourMinSeparatorFromPattern(DateFormat.getBestDateTimePattern(this.mCurrentLocale, str)));
        Typeface defaultFromStyle = Typeface.defaultFromStyle(0);
        Typeface create = Typeface.create("sec-roboto-condensed-light", 0);
        if (Build.VERSION.SDK_INT >= 34) {
            typeface = Typeface.create(Typeface.create("sec", 0), 400, false);
        } else {
            typeface = Typeface.create("sec-roboto-light", 0);
        }
        if (!defaultFromStyle.equals(typeface)) {
            create = typeface;
        } else if (create.equals(typeface)) {
            create = Typeface.create("sans-serif-thin", 0);
        }
        this.mDivider.setTypeface(create);
        Typeface openThemeTypeface = SeslPickerBasicUtils.getOpenThemeTypeface(this.mContext);
        if (openThemeTypeface != null) {
            this.mDivider.setTypeface(openThemeTypeface);
        }
    }

    private void setMinuteInterval(int i2) {
        this.mMinuteInterval = i2;
    }

    private void setTextWatcher() {
        this.mPickerTexts[0] = this.mHourSpinner.getEditText();
        this.mPickerTexts[1] = this.mMinuteSpinner.getEditText();
        this.mPickerTexts[0].addTextChangedListener(new SeslTextWatcher(2, 0));
        this.mPickerTexts[1].addTextChangedListener(new SeslTextWatcher(2, 1));
        this.mPickerTexts[0].setOnKeyListener(new SeslKeyListener());
        this.mPickerTexts[1].setOnKeyListener(new SeslKeyListener());
    }

    /* access modifiers changed from: private */
    public void updateAmPmControl() {
        if (is24Hour()) {
            this.mAmPmMarginInside.setVisibility(8);
            this.mAmPmSpinner.setVisibility(8);
            if (!isDateTimeMode()) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1, 560.0f);
                this.mPaddingLeft.setLayoutParams(layoutParams);
                this.mPaddingRight.setLayoutParams(layoutParams);
                this.mDivider.setLayoutParams(layoutParams);
                this.mTimeLayout.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 3080.0f));
                return;
            }
            this.mPaddingRight.setVisibility(0);
            if (this.mIsMarginLeftShown) {
                this.mPaddingLeft.setVisibility(0);
            }
            this.mDTPaddingRight.setVisibility(8);
            this.mDTPaddingLeft.setVisibility(8);
            return;
        }
        this.mAmPmSpinner.setValue(this.mIsAm ^ true ? 1 : 0);
        this.mAmPmSpinner.setVisibility(0);
        this.mAmPmMarginInside.setVisibility(0);
        if (!isDateTimeMode()) {
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -1, 270.0f);
            this.mPaddingLeft.setLayoutParams(layoutParams2);
            this.mPaddingRight.setLayoutParams(layoutParams2);
            this.mDivider.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 180.0f));
            this.mTimeLayout.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 2700.0f));
            return;
        }
        this.mPaddingLeft.setVisibility(8);
        this.mPaddingRight.setVisibility(8);
        this.mDTPaddingRight.setVisibility(0);
        if (this.mIsMarginLeftShown) {
            this.mDTPaddingLeft.setVisibility(0);
        }
    }

    private void updateHourControl() {
        SeslNumberPicker.Formatter formatter;
        if (is24Hour()) {
            if (this.mHourFormat == 'k') {
                this.mHourSpinner.setMinValue(1);
                this.mHourSpinner.setMaxValue(24);
            } else {
                this.mHourSpinner.setMinValue(0);
                this.mHourSpinner.setMaxValue(23);
            }
        } else if (this.mHourFormat == 'K') {
            this.mHourSpinner.setMinValue(0);
            this.mHourSpinner.setMaxValue(11);
        } else {
            this.mHourSpinner.setMinValue(1);
            this.mHourSpinner.setMaxValue(12);
        }
        SeslNumberPicker seslNumberPicker = this.mHourSpinner;
        if (this.mHourWithTwoDigit) {
            formatter = SeslNumberPicker.getTwoDigitFormatter();
        } else {
            formatter = null;
        }
        seslNumberPicker.setFormatter(formatter);
    }

    /* access modifiers changed from: private */
    public void updateInputState() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        if (inputMethodManager.isActive(this.mHourSpinnerInput)) {
            inputMethodManager.hideSoftInputFromWindow(this.mDelegator.getWindowToken(), 0);
            EditText editText = this.mHourSpinnerInput;
            if (editText != null) {
                editText.clearFocus();
            }
        } else if (inputMethodManager.isActive(this.mMinuteSpinnerInput)) {
            inputMethodManager.hideSoftInputFromWindow(this.mDelegator.getWindowToken(), 0);
            EditText editText2 = this.mMinuteSpinnerInput;
            if (editText2 != null) {
                editText2.clearFocus();
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateModeState(boolean z) {
        if (this.mIsEditTextMode != z && !z) {
            if (this.mHourSpinner.isEditTextMode()) {
                this.mHourSpinner.setEditTextMode(false);
            }
            if (this.mMinuteSpinner.isEditTextMode()) {
                this.mMinuteSpinner.setEditTextMode(false);
            }
        }
    }

    /* access modifiers changed from: private */
    public void validCheck() {
        if (this.mIsEditTextMode) {
            EditText editText = this.mHourSpinnerInput;
            if (editText != null && editText.hasFocus()) {
                if (!TextUtils.isEmpty(this.mHourSpinnerInput.getText())) {
                    int parseInt = Integer.parseInt(String.valueOf(this.mHourSpinnerInput.getText()));
                    if (!is24Hour()) {
                        boolean z = this.mIsAm;
                        if (!z && parseInt != 12) {
                            parseInt += 12;
                        } else if (z && parseInt == 12) {
                            parseInt = 0;
                        }
                    }
                    setHour(parseInt);
                    this.mHourSpinnerInput.selectAll();
                } else {
                    return;
                }
            }
            EditText editText2 = this.mMinuteSpinnerInput;
            if (editText2 != null && editText2.hasFocus() && !TextUtils.isEmpty(this.mMinuteSpinnerInput.getText())) {
                setMinute(Integer.parseInt(String.valueOf(this.mMinuteSpinnerInput.getText())));
                this.mMinuteSpinnerInput.selectAll();
            }
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    public int getBaseline() {
        return this.mHourSpinner.getBaseline();
    }

    public int getDefaultHeight() {
        return this.mDefaultHeight;
    }

    public int getDefaultWidth() {
        return this.mDefaultWidth;
    }

    public int getHour() {
        int value = this.mHourSpinner.getValue();
        if (is24Hour()) {
            return value;
        }
        if (this.mIsAm) {
            return value % 12;
        }
        return (value % 12) + 12;
    }

    public int getMinute() {
        return this.mMinuteSpinner.getValue();
    }

    public boolean is24Hour() {
        return this.mIs24HourView;
    }

    public boolean isEditTextMode() {
        return this.mIsEditTextMode;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public void onConfigurationChanged(Configuration configuration) {
        setCurrentLocale(configuration.locale);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        accessibilityEvent.setClassName(TimePicker.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        accessibilityNodeInfo.setClassName(TimePicker.class.getName());
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        int i2;
        if (this.mIs24HourView) {
            i2 = 129;
        } else {
            i2 = 65;
        }
        this.mTempCalendar.set(11, getHour());
        this.mTempCalendar.set(12, getMinute());
        accessibilityEvent.getText().add(DateUtils.formatDateTime(this.mContext, this.mTempCalendar.getTimeInMillis(), i2));
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        setHour(savedState.getHour());
        setMinute(savedState.getMinute());
    }

    public Parcelable onSaveInstanceState(Parcelable parcelable) {
        return new SavedState(parcelable, getHour(), getMinute());
    }

    public void requestLayout() {
        SeslNumberPicker seslNumberPicker = this.mAmPmSpinner;
        if (seslNumberPicker != null) {
            seslNumberPicker.requestLayout();
        }
        SeslNumberPicker seslNumberPicker2 = this.mHourSpinner;
        if (seslNumberPicker2 != null) {
            seslNumberPicker2.requestLayout();
        }
        SeslNumberPicker seslNumberPicker3 = this.mMinuteSpinner;
        if (seslNumberPicker3 != null) {
            seslNumberPicker3.requestLayout();
        }
    }

    public void set5MinuteInterval(boolean z) {
        int i2;
        if (z) {
            if (getMinute() >= 58) {
                int hour = getHour();
                if (hour == 23) {
                    i2 = 0;
                } else {
                    i2 = hour + 1;
                }
                setCurrentHour(i2, false);
            }
            this.mMinuteSpinner.setCustomIntervalValue(5);
            this.mMinuteSpinner.applyWheelCustomInterval(true);
            setMinuteInterval(5);
            return;
        }
        this.mMinuteSpinner.setCustomIntervalValue(5);
    }

    public void setCurrentLocale(Locale locale) {
        super.setCurrentLocale(locale);
        this.mTempCalendar = Calendar.getInstance(locale);
    }

    public void setCustomTimePickerIdleColor(int i2) {
        this.mHourSpinner.setCustomNumberPickerIdleColor(i2);
        this.mMinuteSpinner.setCustomNumberPickerIdleColor(i2);
        this.mAmPmSpinner.setCustomNumberPickerIdleColor(i2);
        this.mDivider.setTextColor(i2);
        this.mDelegator.invalidate();
    }

    public void setCustomTimePickerScrollColor(int i2) {
        this.mHourSpinner.setCustomNumberPickerScrollColor(i2);
        this.mMinuteSpinner.setCustomNumberPickerScrollColor(i2);
        this.mAmPmSpinner.setCustomNumberPickerScrollColor(i2);
        this.mDivider.setTextColor(this.mContext.getResources().getColor(R$color.sesl_number_picker_text_color_appwidget));
        this.mDelegator.invalidate();
    }

    public void setEditTextMode(boolean z) {
        EditText editText;
        if (this.mIsEditTextMode != z) {
            this.mIsEditTextMode = z;
            InputMethodManager inputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
            this.mHourSpinner.setEditTextMode(z);
            this.mMinuteSpinner.setEditTextMode(z);
            if (inputMethodManager == null) {
                return;
            }
            if (!this.mIsEditTextMode) {
                inputMethodManager.hideSoftInputFromWindow(this.mDelegator.getWindowToken(), 0);
                return;
            }
            if (this.mMinuteSpinnerInput.hasFocus()) {
                editText = this.mMinuteSpinnerInput;
            } else {
                editText = this.mHourSpinnerInput;
            }
            if (!inputMethodManager.showSoftInput(editText, 0)) {
                this.mDelegator.postDelayed(new Runnable() {
                    public void run() {
                        EditText editText;
                        InputMethodManager inputMethodManager = (InputMethodManager) SeslTimePickerSpinnerDelegate.this.mContext.getSystemService("input_method");
                        if (SeslTimePickerSpinnerDelegate.this.mIsEditTextMode && inputMethodManager != null) {
                            boolean hasFocus = SeslTimePickerSpinnerDelegate.this.mMinuteSpinnerInput.hasFocus();
                            SeslTimePickerSpinnerDelegate seslTimePickerSpinnerDelegate = SeslTimePickerSpinnerDelegate.this;
                            if (hasFocus) {
                                editText = seslTimePickerSpinnerDelegate.mMinuteSpinnerInput;
                            } else {
                                editText = seslTimePickerSpinnerDelegate.mHourSpinnerInput;
                            }
                            inputMethodManager.showSoftInput(editText, 0);
                        }
                    }
                }, 20);
            }
        }
    }

    public void setEnabled(boolean z) {
        this.mMinuteSpinner.setEnabled(z);
        TextView textView = this.mDivider;
        if (textView != null) {
            textView.setEnabled(z);
        }
        this.mHourSpinner.setEnabled(z);
        this.mAmPmSpinner.setEnabled(z);
        this.mIsEnabled = z;
    }

    public void setHour(int i2) {
        setCurrentHour(i2, true);
    }

    public void setIs24Hour(boolean z) {
        if (this.mIs24HourView != z) {
            int hour = getHour();
            this.mIs24HourView = z;
            getHourFormatData();
            updateHourControl();
            setCurrentHour(hour, false);
            updateAmPmControl();
        }
    }

    public void setMinute(int i2) {
        int minuteInterval = getMinuteInterval();
        if (minuteInterval != 1) {
            if (this.mIsEditTextMode) {
                this.mMinuteSpinner.setValue(i2);
            } else {
                if (i2 % minuteInterval == 0) {
                    this.mMinuteSpinner.applyWheelCustomInterval(true);
                } else {
                    this.mMinuteSpinner.applyWheelCustomInterval(false);
                }
                this.mMinuteSpinner.setValue(i2);
            }
        } else if (i2 != getMinute()) {
            this.mMinuteSpinner.setValue(i2);
        } else if (isCharacterNumberLanguage()) {
            this.mMinuteSpinner.setValue(i2);
            return;
        } else {
            return;
        }
        onTimeChanged();
    }

    public void setNumberPickerTextSize(int i2, float f) {
        if (i2 == 0) {
            this.mHourSpinner.setTextSize(f);
        } else if (i2 == 1) {
            this.mMinuteSpinner.setTextSize(f);
        } else if (i2 == 2) {
            this.mAmPmSpinner.setTextSize(f);
        } else if (i2 != 3) {
            this.mMinuteSpinner.setTextSize(f);
        } else {
            this.mDivider.setTextSize(1, f);
        }
    }

    public void setOnTimeChangedListener(SeslTimePicker.OnTimeChangedListener onTimeChangedListener) {
        this.mOnTimeChangedListener = onTimeChangedListener;
    }

    public void showMarginLeft(boolean z) {
        this.mIsMarginLeftShown = z;
        if (isDateTimeMode()) {
            updateAmPmControl();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        private final int mHour;
        private final int mMinute;

        public int getHour() {
            return this.mHour;
        }

        public int getMinute() {
            return this.mMinute;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.mHour);
            parcel.writeInt(this.mMinute);
        }

        private SavedState(Parcelable parcelable, int i2, int i7) {
            super(parcelable);
            this.mHour = i2;
            this.mMinute = i7;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.mHour = parcel.readInt();
            this.mMinute = parcel.readInt();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SeslTextWatcher implements TextWatcher {
        private int changedLen = 0;
        private int mId;
        private int mMaxLen;
        private int mNext;
        private String prevText;

        public SeslTextWatcher(int i2, int i7) {
            int i8;
            this.mMaxLen = i2;
            this.mId = i7;
            if (i7 + 1 >= 2) {
                i8 = -1;
            } else {
                i8 = i7 + 1;
            }
            this.mNext = i8;
        }

        private void changeFocus() {
            if (((AccessibilityManager) SeslTimePickerSpinnerDelegate.this.mContext.getSystemService("accessibility")).isTouchExplorationEnabled()) {
                int i2 = this.mId;
                if (i2 == 0) {
                    SeslTimePickerSpinnerDelegate seslTimePickerSpinnerDelegate = SeslTimePickerSpinnerDelegate.this;
                    seslTimePickerSpinnerDelegate.setHour(Integer.parseInt(String.valueOf(seslTimePickerSpinnerDelegate.mPickerTexts[this.mId].getText())));
                    SeslTimePickerSpinnerDelegate.this.mPickerTexts[this.mId].selectAll();
                } else if (i2 == 1) {
                    SeslTimePickerSpinnerDelegate seslTimePickerSpinnerDelegate2 = SeslTimePickerSpinnerDelegate.this;
                    seslTimePickerSpinnerDelegate2.setMinute(Integer.parseInt(String.valueOf(seslTimePickerSpinnerDelegate2.mPickerTexts[this.mId].getText())));
                    SeslTimePickerSpinnerDelegate.this.mPickerTexts[this.mId].selectAll();
                }
            } else if (this.mNext >= 0) {
                SeslTimePickerSpinnerDelegate.this.mPickerTexts[this.mNext].requestFocus();
                if (SeslTimePickerSpinnerDelegate.this.mPickerTexts[this.mId].isFocused()) {
                    SeslTimePickerSpinnerDelegate.this.mPickerTexts[this.mId].clearFocus();
                }
            } else if (this.mId == 1) {
                SeslTimePickerSpinnerDelegate seslTimePickerSpinnerDelegate3 = SeslTimePickerSpinnerDelegate.this;
                seslTimePickerSpinnerDelegate3.setMinute(Integer.parseInt(String.valueOf(seslTimePickerSpinnerDelegate3.mPickerTexts[this.mId].getText())));
                SeslTimePickerSpinnerDelegate.this.mPickerTexts[this.mId].selectAll();
            }
        }

        private int convertDigitCharacterToNumber(String str) {
            int i2 = 0;
            for (char ch : SeslTimePickerSpinnerDelegate.DIGIT_CHARACTERS) {
                if (str.equals(Character.toString(ch))) {
                    return i2 % 10;
                }
                i2++;
            }
            return -1;
        }

        public void beforeTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
            this.prevText = charSequence.toString();
            this.changedLen = i8;
        }

        public void onTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
            String str = (String) SeslTimePickerSpinnerDelegate.this.mPickerTexts[this.mId].getTag();
            if (str == null || (!str.equals("onClick") && !str.equals("onLongClick"))) {
                int i10 = this.mId;
                if (i10 != 0) {
                    if (i10 != 1) {
                        if (this.prevText.length() < charSequence.length() && charSequence.length() == this.mMaxLen && SeslTimePickerSpinnerDelegate.this.mPickerTexts[this.mId].isFocused()) {
                            changeFocus();
                        }
                    } else if (this.changedLen != 1) {
                    } else {
                        if (charSequence.length() == this.mMaxLen) {
                            if (SeslTimePickerSpinnerDelegate.this.mPickerTexts[this.mId].isFocused()) {
                                changeFocus();
                            }
                        } else if (charSequence.length() > 0) {
                            int convertDigitCharacterToNumber = convertDigitCharacterToNumber(charSequence.toString());
                            if (convertDigitCharacterToNumber < 6 || convertDigitCharacterToNumber > 9) {
                                if (!SeslTimePickerSpinnerDelegate.this.mIsInvalidMinute || !(convertDigitCharacterToNumber == 5 || convertDigitCharacterToNumber == 0)) {
                                    boolean unused = SeslTimePickerSpinnerDelegate.this.mIsInvalidMinute = false;
                                    boolean unused2 = SeslTimePickerSpinnerDelegate.this.mSkipToChangeInterval = false;
                                    return;
                                }
                                boolean unused3 = SeslTimePickerSpinnerDelegate.this.mIsInvalidMinute = false;
                                boolean unused4 = SeslTimePickerSpinnerDelegate.this.mSkipToChangeInterval = true;
                            } else if (SeslTimePickerSpinnerDelegate.this.mPickerTexts[this.mId].isFocused()) {
                                boolean unused5 = SeslTimePickerSpinnerDelegate.this.mIsInvalidMinute = true;
                                changeFocus();
                            }
                        }
                    }
                } else if (this.changedLen != 1) {
                } else {
                    if (charSequence.length() == this.mMaxLen) {
                        if (SeslTimePickerSpinnerDelegate.this.mPickerTexts[this.mId].isFocused()) {
                            changeFocus();
                        }
                    } else if (charSequence.length() > 0) {
                        int convertDigitCharacterToNumber2 = convertDigitCharacterToNumber(charSequence.toString());
                        if ((convertDigitCharacterToNumber2 > 2 || (convertDigitCharacterToNumber2 > 1 && !SeslTimePickerSpinnerDelegate.this.is24Hour())) && SeslTimePickerSpinnerDelegate.this.mPickerTexts[this.mId].isFocused()) {
                            changeFocus();
                        }
                    }
                }
            } else {
                SeslTimePickerSpinnerDelegate.this.mPickerTexts[this.mId].setTag("");
            }
        }

        public void afterTextChanged(Editable editable) {
        }
    }

    public void setOnEditTextModeChangedListener(SeslTimePicker.OnEditTextModeChangedListener onEditTextModeChangedListener) {
    }
}
