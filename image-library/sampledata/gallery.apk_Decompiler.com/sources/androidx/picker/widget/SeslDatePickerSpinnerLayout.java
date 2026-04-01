package androidx.picker.widget;

import N2.j;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.picker.R$id;
import androidx.picker.R$integer;
import androidx.picker.R$layout;
import androidx.picker.R$string;
import androidx.picker.widget.SeslDatePicker;
import androidx.picker.widget.SeslNumberPicker;
import androidx.reflect.lunarcalendar.SeslSolarLunarTablesReflector;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import dalvik.system.PathClassLoader;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SeslDatePickerSpinnerLayout extends LinearLayout {
    /* access modifiers changed from: private */
    public Context mContext;
    protected Calendar mCurrentDate;
    protected Locale mCurrentLocale;
    protected SeslDatePicker mDatePicker;
    protected final View mDayPaddingView;
    protected final SeslNumberPicker mDaySpinner;
    protected final EditText mDaySpinnerInput;
    private TextView.OnEditorActionListener mEditorActionListener;
    private boolean mIsEditTextMode;
    protected boolean mIsLeapMonth;
    protected boolean mIsLunar;
    protected int mLunarCurrentDay;
    protected int mLunarCurrentMonth;
    protected int mLunarCurrentYear;
    protected int mLunarTempDay;
    protected int mLunarTempMonth;
    protected int mLunarTempYear;
    protected Calendar mMaxDate;
    protected Calendar mMinDate;
    private SeslNumberPicker.OnEditTextModeChangedListener mModeChangeListener;
    protected final SeslNumberPicker mMonthSpinner;
    protected final EditText mMonthSpinnerInput;
    protected String[] mMonths;
    protected int mNumberOfMonths;
    private OnSpinnerDateChangedListener mOnSpinnerDateChangedListener;
    PathClassLoader mPathClassLoader;
    /* access modifiers changed from: private */
    public EditText[] mPickerTexts;
    protected String[] mShortMonths;
    /* access modifiers changed from: private */
    public Object mSolarLunarTables;
    protected final LinearLayout mSpinners;
    protected Calendar mTempDate;
    /* access modifiers changed from: private */
    public Toast mToast;
    /* access modifiers changed from: private */
    public String mToastText;
    protected final View mYearPaddingView;
    protected final SeslNumberPicker mYearSpinner;
    protected final EditText mYearSpinnerInput;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnSpinnerDateChangedListener {
        void onDateChanged(SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout, int i2, int i7, int i8);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SeslKeyListener implements View.OnKeyListener {
        public SeslKeyListener() {
        }

        public boolean onKey(View view, int i2, KeyEvent keyEvent) {
            SeslDatePickerSpinnerLayout.this.seslLog(keyEvent.toString());
            if (keyEvent.getAction() != 1) {
                return false;
            }
            if (i2 != 23) {
                if (i2 != 61) {
                    if (i2 != 66 && i2 != 160) {
                        return false;
                    }
                    if (SeslDatePickerSpinnerLayout.this.isEditTextMode()) {
                        EditText editText = (EditText) view;
                        if ((editText.getImeOptions() & 5) == 5) {
                            View findNextFocus = FocusFinder.getInstance().findNextFocus(SeslDatePickerSpinnerLayout.this.mDatePicker, view, 2);
                            if (findNextFocus == null) {
                                return true;
                            }
                            findNextFocus.requestFocus();
                        } else if ((editText.getImeOptions() & 6) == 6) {
                            SeslDatePickerSpinnerLayout.this.updateInputState();
                            SeslDatePickerSpinnerLayout.this.setEditTextMode(false);
                        }
                    }
                }
                return true;
            }
            int i7 = SeslDatePickerSpinnerLayout.this.getResources().getConfiguration().keyboard;
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SeslTextWatcher implements TextWatcher {
        private int mChangedLen;
        private int mCheck;
        private int mId;
        private boolean mIsMonth;
        private int mMaxLen;
        private int mNext;
        private String mPrevText;

        private void changeFocus() {
            AccessibilityManager accessibilityManager = (AccessibilityManager) SeslDatePickerSpinnerLayout.this.mContext.getSystemService("accessibility");
            if (accessibilityManager == null || !accessibilityManager.isTouchExplorationEnabled()) {
                SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout = SeslDatePickerSpinnerLayout.this;
                seslDatePickerSpinnerLayout.seslLog("[" + this.mId + "] changeFocus() mNext : " + this.mNext + ", mCheck : " + this.mCheck);
                if (this.mNext >= 0) {
                    if (!SeslDatePickerSpinnerLayout.this.mPickerTexts[this.mCheck].isFocused()) {
                        SeslDatePickerSpinnerLayout.this.mPickerTexts[this.mNext].requestFocus();
                    }
                    if (SeslDatePickerSpinnerLayout.this.mPickerTexts[this.mId].isFocused()) {
                        SeslDatePickerSpinnerLayout.this.mPickerTexts[this.mId].clearFocus();
                    }
                }
            }
        }

        private boolean isFarsiLanguage() {
            return "fa".equals(SeslDatePickerSpinnerLayout.this.mCurrentLocale.getLanguage());
        }

        private boolean isMeaLanguage() {
            String language = SeslDatePickerSpinnerLayout.this.mCurrentLocale.getLanguage();
            if ("ar".equals(language) || "fa".equals(language) || "ur".equals(language)) {
                return true;
            }
            return false;
        }

        private boolean isMonthStr(String str) {
            int i2 = 0;
            while (true) {
                SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout = SeslDatePickerSpinnerLayout.this;
                if (i2 >= seslDatePickerSpinnerLayout.mNumberOfMonths) {
                    return false;
                }
                if (str.equals(seslDatePickerSpinnerLayout.mShortMonths[i2])) {
                    return true;
                }
                i2++;
            }
        }

        private boolean isNumericStr(String str) {
            if (TextUtils.isEmpty(str) || !Character.isDigit(str.charAt(0))) {
                return false;
            }
            return true;
        }

        private boolean isSwaLanguage() {
            String language = SeslDatePickerSpinnerLayout.this.mCurrentLocale.getLanguage();
            if ("hi".equals(language) || "ta".equals(language) || "ml".equals(language) || "te".equals(language) || "or".equals(language) || "ne".equals(language) || "as".equals(language) || "bn".equals(language) || "gu".equals(language) || "si".equals(language) || "pa".equals(language) || "kn".equals(language) || "mr".equals(language)) {
                return true;
            }
            return false;
        }

        private void showInvalidValueEnteredToast(String str, int i2) {
            SeslDatePickerSpinnerLayout.this.mPickerTexts[this.mId].setText(str);
            if (i2 != 0) {
                SeslDatePickerSpinnerLayout.this.mPickerTexts[this.mId].setSelection(i2);
            }
            if (SeslDatePickerSpinnerLayout.this.mToast == null) {
                SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout = SeslDatePickerSpinnerLayout.this;
                Toast unused = seslDatePickerSpinnerLayout.mToast = Toast.makeText(seslDatePickerSpinnerLayout.mContext, SeslDatePickerSpinnerLayout.this.mToastText, 0);
                View inflate = LayoutInflater.from(SeslDatePickerSpinnerLayout.this.mContext).inflate(R$layout.sesl_custom_toast_layout, (ViewGroup) null);
                ((TextView) inflate.findViewById(R$id.message)).setText(SeslDatePickerSpinnerLayout.this.mToastText);
                SeslDatePickerSpinnerLayout.this.mToast.setView(inflate);
            }
            SeslDatePickerSpinnerLayout.this.mToast.show();
        }

        public void afterTextChanged(Editable editable) {
            SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout = SeslDatePickerSpinnerLayout.this;
            seslDatePickerSpinnerLayout.seslLog("[" + this.mId + "] afterTextChanged: " + editable.toString());
        }

        public void beforeTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
            SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout = SeslDatePickerSpinnerLayout.this;
            StringBuilder sb2 = new StringBuilder("[");
            sb2.append(this.mId);
            sb2.append("] beforeTextChanged: ");
            sb2.append(charSequence);
            sb2.append(ArcCommonLog.TAG_COMMA);
            j.x(sb2, i2, ArcCommonLog.TAG_COMMA, i7, ArcCommonLog.TAG_COMMA);
            sb2.append(i8);
            seslDatePickerSpinnerLayout.seslLog(sb2.toString());
            this.mPrevText = charSequence.toString();
            this.mChangedLen = i8;
        }

        public void onTextChanged(CharSequence charSequence, int i2, int i7, int i8) {
            int i10;
            int i11;
            SeslDatePickerSpinnerLayout.this.seslLog("[" + this.mId + "] onTextChanged: " + this.mPrevText + " -> " + charSequence);
            int length = charSequence.length();
            String charSequence2 = charSequence.toString();
            String str = (String) SeslDatePickerSpinnerLayout.this.mPickerTexts[this.mId].getTag();
            if (str != null && ("onClick".equals(str) || "onLongClick".equals(str))) {
                SeslDatePickerSpinnerLayout.this.seslLog("[" + this.mId + "] TAG exists: " + str);
            } else if (SeslDatePickerSpinnerLayout.this.mPickerTexts[this.mId].isFocused()) {
                String str2 = "";
                if (this.mIsMonth) {
                    if (SeslDatePickerSpinnerLayout.this.usingNumericMonths() && this.mChangedLen == 1) {
                        SeslDatePickerSpinnerLayout.this.seslLog(C0086a.l(new StringBuilder("["), this.mId, "] Samsung Keypad Num Month"));
                        int minValue = SeslDatePickerSpinnerLayout.this.mMonthSpinner.getMinValue();
                        int parseInt = Integer.parseInt(charSequence2);
                        if (length == this.mMaxLen) {
                            if (parseInt >= minValue) {
                                changeFocus();
                            } else if (Character.getNumericValue(charSequence2.charAt(0)) < 2) {
                                showInvalidValueEnteredToast(Character.toString(charSequence2.charAt(0)), 1);
                            } else {
                                showInvalidValueEnteredToast(str2, 0);
                            }
                        } else if (length <= 0) {
                        } else {
                            if (minValue >= 10 && "0".equals(charSequence2)) {
                                showInvalidValueEnteredToast(str2, 0);
                            } else if (!"1".equals(charSequence2) && !"0".equals(charSequence2)) {
                                if (parseInt < minValue) {
                                    showInvalidValueEnteredToast(str2, 0);
                                } else {
                                    changeFocus();
                                }
                            }
                        }
                    } else if (isNumericStr(this.mPrevText)) {
                    } else {
                        if (length >= this.mMaxLen) {
                            if (!isMeaLanguage()) {
                                changeFocus();
                            } else if (TextUtils.isEmpty(this.mPrevText) && isMonthStr(charSequence2)) {
                                changeFocus();
                            }
                        } else if ((isSwaLanguage() || isFarsiLanguage()) && length > 0 && !isNumericStr(charSequence2)) {
                            changeFocus();
                        }
                    }
                } else if (this.mChangedLen != 1) {
                } else {
                    if (this.mMaxLen < 3) {
                        int minValue2 = SeslDatePickerSpinnerLayout.this.mDaySpinner.getMinValue();
                        int parseInt2 = Integer.parseInt(charSequence2);
                        if (this.mPrevText.length() >= length || length != this.mMaxLen) {
                            if ((minValue2 < 10 || parseInt2 != 0) && ((minValue2 < 20 || !(parseInt2 == 0 || parseInt2 == 1)) && (minValue2 < 30 || !(parseInt2 == 0 || parseInt2 == 1 || parseInt2 == 2)))) {
                                if (parseInt2 > 3) {
                                    if (parseInt2 < minValue2) {
                                        showInvalidValueEnteredToast(str2, 0);
                                        return;
                                    }
                                    changeFocus();
                                }
                                if (SeslDatePickerSpinnerLayout.this.usingNumericMonths()) {
                                    i11 = SeslDatePickerSpinnerLayout.this.mMonthSpinner.getValue() - 1;
                                } else {
                                    i11 = SeslDatePickerSpinnerLayout.this.mMonthSpinner.getValue();
                                }
                                if (SeslDatePickerSpinnerLayout.this.mIsLunar || i11 != 1 || parseInt2 != 3) {
                                    return;
                                }
                                if (parseInt2 < minValue2) {
                                    showInvalidValueEnteredToast(str2, 0);
                                } else {
                                    changeFocus();
                                }
                            } else {
                                showInvalidValueEnteredToast(str2, 0);
                            }
                        } else if (parseInt2 >= minValue2) {
                            changeFocus();
                        } else if (Character.getNumericValue(charSequence2.charAt(0)) < 4) {
                            showInvalidValueEnteredToast(Character.toString(charSequence2.charAt(0)), 1);
                        } else {
                            showInvalidValueEnteredToast(str2, 0);
                        }
                    } else {
                        int minValue3 = SeslDatePickerSpinnerLayout.this.mYearSpinner.getMinValue();
                        int maxValue = SeslDatePickerSpinnerLayout.this.mYearSpinner.getMaxValue();
                        int parseInt3 = Integer.parseInt(charSequence2);
                        if (this.mPrevText.length() >= length || length != this.mMaxLen) {
                            int i12 = length - 1;
                            int pow = (int) (1000.0d / Math.pow(10.0d, (double) i12));
                            if (length != 1) {
                                str2 = charSequence2.substring(0, i12);
                            }
                            if (parseInt3 < minValue3 / pow || parseInt3 > maxValue / pow) {
                                showInvalidValueEnteredToast(str2, i12);
                            }
                        } else if (parseInt3 < minValue3 || parseInt3 > maxValue) {
                            showInvalidValueEnteredToast(charSequence2.substring(0, 3), 3);
                        } else {
                            if (SeslDatePickerSpinnerLayout.this.usingNumericMonths()) {
                                i10 = SeslDatePickerSpinnerLayout.this.mMonthSpinner.getValue() - 1;
                            } else {
                                i10 = SeslDatePickerSpinnerLayout.this.mMonthSpinner.getValue();
                            }
                            SeslDatePickerSpinnerLayout.this.mTempDate.clear();
                            SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout = SeslDatePickerSpinnerLayout.this;
                            seslDatePickerSpinnerLayout.mTempDate.set(parseInt3, i10, seslDatePickerSpinnerLayout.mDaySpinner.getValue());
                            Calendar instance = Calendar.getInstance();
                            instance.clear();
                            instance.set(SeslDatePickerSpinnerLayout.this.mMinDate.get(1), SeslDatePickerSpinnerLayout.this.mMinDate.get(2), SeslDatePickerSpinnerLayout.this.mMinDate.get(5));
                            if (!SeslDatePickerSpinnerLayout.this.mTempDate.before(instance)) {
                                SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout2 = SeslDatePickerSpinnerLayout.this;
                                if (!seslDatePickerSpinnerLayout2.mTempDate.after(seslDatePickerSpinnerLayout2.mMaxDate)) {
                                    changeFocus();
                                    return;
                                }
                            }
                            showInvalidValueEnteredToast(charSequence2.substring(0, 3), 3);
                        }
                    }
                }
            }
        }

        private SeslTextWatcher(int i2, int i7, boolean z) {
            this.mChangedLen = 0;
            this.mMaxLen = i2;
            this.mId = i7;
            this.mIsMonth = z;
            int i8 = i7 - 1;
            this.mCheck = i8;
            if (i8 < 0) {
                this.mCheck = 2;
            }
            this.mNext = i7 + 1 > 2 ? -1 : i7 + 1;
        }
    }

    public SeslDatePickerSpinnerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16843612);
    }

    private Calendar getCalendarForLocale(Calendar calendar, Locale locale) {
        if (calendar == null) {
            return Calendar.getInstance(locale);
        }
        long timeInMillis = calendar.getTimeInMillis();
        Calendar instance = Calendar.getInstance(locale);
        instance.setTimeInMillis(timeInMillis);
        return instance;
    }

    /* access modifiers changed from: private */
    public int getLunarMaxDayOfMonth(int i2, int i7, boolean z) {
        Object obj = this.mSolarLunarTables;
        if (obj == null) {
            return 0;
        }
        return SeslSolarLunarTablesReflector.getDayLengthOf(this.mPathClassLoader, obj, i2, i7, z);
    }

    private boolean isNewDate(int i2, int i7, int i8) {
        if (this.mCurrentDate.get(1) == i2 && this.mCurrentDate.get(2) == i7 && this.mCurrentDate.get(5) == i8) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void notifyDateChanged() {
        OnSpinnerDateChangedListener onSpinnerDateChangedListener = this.mOnSpinnerDateChangedListener;
        if (onSpinnerDateChangedListener != null) {
            onSpinnerDateChangedListener.onDateChanged(this, getYear(), getMonth(), getDayOfMonth());
        }
    }

    private void reorderSpinners() {
        this.mSpinners.removeAllViews();
        char[] dateFormatOrder = DateFormat.getDateFormatOrder(this.mContext);
        int length = dateFormatOrder.length;
        for (int i2 = 0; i2 < length; i2++) {
            char c5 = dateFormatOrder[i2];
            if (c5 == 'M') {
                this.mSpinners.addView(this.mMonthSpinner);
                setImeOptions(this.mMonthSpinner, length, i2);
            } else if (c5 == 'd') {
                this.mSpinners.addView(this.mDaySpinner);
                setImeOptions(this.mDaySpinner, length, i2);
            } else if (c5 == 'y') {
                this.mSpinners.addView(this.mYearSpinner);
                setImeOptions(this.mYearSpinner, length, i2);
            } else {
                throw new IllegalArgumentException(Arrays.toString(dateFormatOrder));
            }
        }
        if (dateFormatOrder[0] == 'y') {
            this.mSpinners.addView(this.mYearPaddingView, 0);
            this.mSpinners.addView(this.mDayPaddingView);
        } else {
            this.mSpinners.addView(this.mDayPaddingView, 0);
            this.mSpinners.addView(this.mYearPaddingView);
        }
        char c6 = dateFormatOrder[0];
        char c8 = dateFormatOrder[1];
        if (c6 == 'M') {
            setTextWatcher(0);
        } else if (c6 == 'd') {
            setTextWatcher(1);
        } else if (c6 == 'y') {
            if (c8 == 'd') {
                setTextWatcher(3);
            } else {
                setTextWatcher(2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void setDate(int i2, int i7, int i8) {
        this.mCurrentDate.set(i2, i7, i8);
        if (this.mIsLunar) {
            this.mLunarCurrentYear = i2;
            this.mLunarCurrentMonth = i7;
            this.mLunarCurrentDay = i8;
        }
        if (this.mCurrentDate.before(this.mMinDate)) {
            this.mCurrentDate.setTimeInMillis(this.mMinDate.getTimeInMillis());
        } else if (this.mCurrentDate.after(this.mMaxDate)) {
            this.mCurrentDate.setTimeInMillis(this.mMaxDate.getTimeInMillis());
        }
    }

    private void setImeOptions(SeslNumberPicker seslNumberPicker, int i2, int i7) {
        int i8;
        if (i7 < i2 - 1) {
            i8 = 33554437;
        } else {
            i8 = 33554438;
        }
        ((TextView) seslNumberPicker.findViewById(R$id.numberpicker_input)).setImeOptions(i8);
    }

    private void setSpinnersTextSize() {
        Resources resources = this.mContext.getResources();
        int integer = resources.getInteger(R$integer.sesl_date_picker_spinner_number_text_size);
        int integer2 = resources.getInteger(R$integer.sesl_date_picker_spinner_number_text_size_with_unit);
        float f = (float) integer;
        this.mDaySpinner.setTextSize(f);
        this.mYearSpinner.setTextSize(f);
        String language = this.mCurrentLocale.getLanguage();
        if ("my".equals(language) || "ml".equals(language) || "ar".equals(language) || "fa".equals(language)) {
            integer = resources.getInteger(R$integer.sesl_date_picker_spinner_long_month_text_size);
        } else if ("ga".equals(language)) {
            integer = resources.getInteger(R$integer.sesl_date_picker_spinner_long_month_text_size) - 1;
        } else if ("hu".equals(language)) {
            integer -= 4;
        }
        if (usingNumericMonths()) {
            this.mMonthSpinner.setTextSize(f);
        } else {
            this.mMonthSpinner.setTextSize((float) integer);
        }
        if ("ko".equals(language) || "zh".equals(language) || "ja".equals(language)) {
            float f5 = (float) integer2;
            this.mDaySpinner.setTextSize(f5);
            this.mMonthSpinner.setTextSize(f5);
            this.mYearSpinner.setTextSize(f5);
            this.mDaySpinner.setDateUnit(997);
            this.mMonthSpinner.setDateUnit(998);
            this.mYearSpinner.setDateUnit(999);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0084  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setTextWatcher(int r13) {
        /*
            r12 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "setTextWatcher() usingNumericMonths  : "
            r0.<init>(r2)
            boolean r2 = r12.usingNumericMonths()
            r0.append(r2)
            java.lang.String r2 = "format  : "
            r0.append(r2)
            r0.append(r13)
            java.lang.String r0 = r0.toString()
            r12.seslLog(r0)
            r6 = 3
            r0 = 0
            r2 = 2
            r7 = 1
            if (r13 == 0) goto L_0x0039
            if (r13 == r7) goto L_0x0036
            if (r13 == r2) goto L_0x0032
            if (r13 == r6) goto L_0x002e
            r0 = -1
            r3 = r0
            r8 = r3
            r9 = r8
            goto L_0x003c
        L_0x002e:
            r3 = r0
            r8 = r2
        L_0x0030:
            r9 = r7
            goto L_0x003c
        L_0x0032:
            r3 = r0
            r9 = r2
        L_0x0034:
            r8 = r7
            goto L_0x003c
        L_0x0036:
            r9 = r0
            r3 = r2
            goto L_0x0034
        L_0x0039:
            r8 = r0
            r3 = r2
            goto L_0x0030
        L_0x003c:
            android.widget.EditText[] r0 = r12.mPickerTexts
            androidx.picker.widget.SeslNumberPicker r2 = r12.mYearSpinner
            android.widget.EditText r2 = r2.getEditText()
            r0[r3] = r2
            android.widget.EditText[] r0 = r12.mPickerTexts
            androidx.picker.widget.SeslNumberPicker r2 = r12.mMonthSpinner
            android.widget.EditText r2 = r2.getEditText()
            r0[r8] = r2
            android.widget.EditText[] r0 = r12.mPickerTexts
            androidx.picker.widget.SeslNumberPicker r2 = r12.mDaySpinner
            android.widget.EditText r2 = r2.getEditText()
            r0[r9] = r2
            android.widget.EditText[] r0 = r12.mPickerTexts
            r10 = r0[r3]
            androidx.picker.widget.SeslDatePickerSpinnerLayout$SeslTextWatcher r0 = new androidx.picker.widget.SeslDatePickerSpinnerLayout$SeslTextWatcher
            r4 = 0
            r5 = 0
            r2 = 4
            r1 = r12
            r0.<init>(r2, r3, r4)
            r11 = r3
            r10.addTextChangedListener(r0)
            boolean r0 = r12.usingNumericMonths()
            if (r0 == 0) goto L_0x0084
            android.widget.EditText[] r0 = r12.mPickerTexts
            r10 = r0[r8]
            androidx.picker.widget.SeslDatePickerSpinnerLayout$SeslTextWatcher r0 = new androidx.picker.widget.SeslDatePickerSpinnerLayout$SeslTextWatcher
            r4 = 1
            r5 = 0
            r2 = 2
            r1 = r12
            r3 = r8
            r0.<init>(r2, r3, r4)
            r10.addTextChangedListener(r0)
            r10 = r3
            goto L_0x0096
        L_0x0084:
            r3 = r8
            android.widget.EditText[] r0 = r12.mPickerTexts
            r8 = r0[r3]
            androidx.picker.widget.SeslDatePickerSpinnerLayout$SeslTextWatcher r0 = new androidx.picker.widget.SeslDatePickerSpinnerLayout$SeslTextWatcher
            r4 = 1
            r5 = 0
            r2 = 3
            r1 = r12
            r0.<init>(r2, r3, r4)
            r10 = r3
            r8.addTextChangedListener(r0)
        L_0x0096:
            android.widget.EditText[] r0 = r12.mPickerTexts
            r8 = r0[r9]
            androidx.picker.widget.SeslDatePickerSpinnerLayout$SeslTextWatcher r0 = new androidx.picker.widget.SeslDatePickerSpinnerLayout$SeslTextWatcher
            r4 = 0
            r5 = 0
            r2 = 2
            r1 = r12
            r3 = r9
            r0.<init>(r2, r3, r4)
            r8.addTextChangedListener(r0)
            if (r13 != r6) goto L_0x00af
            boolean r0 = r12.usingNumericMonths()
            if (r0 == 0) goto L_0x00ba
        L_0x00af:
            android.widget.EditText[] r0 = r12.mPickerTexts
            int r2 = r0.length
            int r2 = r2 - r7
            r0 = r0[r2]
            android.widget.TextView$OnEditorActionListener r2 = r12.mEditorActionListener
            r0.setOnEditorActionListener(r2)
        L_0x00ba:
            android.widget.EditText[] r0 = r12.mPickerTexts
            r0 = r0[r11]
            androidx.picker.widget.SeslDatePickerSpinnerLayout$SeslKeyListener r2 = new androidx.picker.widget.SeslDatePickerSpinnerLayout$SeslKeyListener
            r2.<init>()
            r0.setOnKeyListener(r2)
            android.widget.EditText[] r0 = r12.mPickerTexts
            r0 = r0[r10]
            androidx.picker.widget.SeslDatePickerSpinnerLayout$SeslKeyListener r2 = new androidx.picker.widget.SeslDatePickerSpinnerLayout$SeslKeyListener
            r2.<init>()
            r0.setOnKeyListener(r2)
            android.widget.EditText[] r0 = r12.mPickerTexts
            r0 = r0[r3]
            androidx.picker.widget.SeslDatePickerSpinnerLayout$SeslKeyListener r2 = new androidx.picker.widget.SeslDatePickerSpinnerLayout$SeslKeyListener
            r2.<init>()
            r0.setOnKeyListener(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.picker.widget.SeslDatePickerSpinnerLayout.setTextWatcher(int):void");
    }

    /* access modifiers changed from: private */
    public void updateModeState(boolean z) {
        if (this.mIsEditTextMode != z && !z) {
            if (this.mDaySpinner.isEditTextMode()) {
                this.mDaySpinner.setEditTextMode(false);
            }
            if (this.mMonthSpinner.isEditTextMode()) {
                this.mMonthSpinner.setEditTextMode(false);
            }
            if (this.mYearSpinner.isEditTextMode()) {
                this.mYearSpinner.setEditTextMode(false);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateSpinners(boolean z, boolean z3, boolean z7, boolean z9) {
        EditText[] editTextArr;
        int i2;
        int i7;
        int actualMaximum;
        int i8;
        int i10;
        if (z3) {
            this.mYearSpinner.setMinValue(this.mMinDate.get(1));
            this.mYearSpinner.setMaxValue(this.mMaxDate.get(1));
            this.mYearSpinner.setWrapSelectorWheel(false);
        }
        if (z7) {
            if (this.mMaxDate.get(1) - this.mMinDate.get(1) == 0) {
                i8 = this.mMinDate.get(2);
                i10 = this.mMaxDate.get(2);
            } else {
                int i11 = this.mCurrentDate.get(1);
                if (this.mIsLunar) {
                    i11 = this.mLunarCurrentYear;
                }
                if (i11 == this.mMinDate.get(1)) {
                    i8 = this.mMinDate.get(2);
                } else if (i11 == this.mMaxDate.get(1)) {
                    i10 = this.mMaxDate.get(2);
                    i8 = 0;
                } else {
                    i8 = 0;
                }
                i10 = 11;
            }
            if (usingNumericMonths()) {
                i8++;
                i10++;
            }
            this.mMonthSpinner.setDisplayedValues((String[]) null);
            this.mMonthSpinner.setMinValue(i8);
            this.mMonthSpinner.setMaxValue(i10);
            if (!usingNumericMonths()) {
                this.mMonthSpinner.setDisplayedValues((String[]) Arrays.copyOfRange(this.mShortMonths, this.mMonthSpinner.getMinValue(), this.mMonthSpinner.getMaxValue() + 1));
            }
        }
        if (z9) {
            int i12 = this.mMaxDate.get(1) - this.mMinDate.get(1);
            int i13 = this.mMaxDate.get(2) - this.mMinDate.get(2);
            if (i12 == 0 && i13 == 0) {
                i2 = this.mMinDate.get(5);
                i7 = this.mMaxDate.get(5);
            } else {
                int i14 = this.mCurrentDate.get(1);
                int i15 = this.mCurrentDate.get(2);
                if (this.mIsLunar) {
                    i14 = this.mLunarCurrentYear;
                    i15 = this.mLunarCurrentMonth;
                }
                if (i14 == this.mMinDate.get(1) && i15 == this.mMinDate.get(2)) {
                    int i16 = this.mMinDate.get(5);
                    int actualMaximum2 = this.mCurrentDate.getActualMaximum(5);
                    if (this.mIsLunar) {
                        i7 = getLunarMaxDayOfMonth(i14, i15, this.mIsLeapMonth);
                        i2 = i16;
                    } else {
                        i2 = i16;
                        i7 = actualMaximum2;
                    }
                } else {
                    if (i14 == this.mMaxDate.get(1) && i15 == this.mMaxDate.get(2)) {
                        actualMaximum = this.mMaxDate.get(5);
                        if (this.mIsLunar) {
                            i7 = Math.min(actualMaximum, getLunarMaxDayOfMonth(i14, i15, this.mIsLeapMonth));
                        }
                        i2 = 1;
                        i7 = actualMaximum;
                    } else {
                        actualMaximum = this.mCurrentDate.getActualMaximum(5);
                        if (this.mIsLunar) {
                            i7 = getLunarMaxDayOfMonth(i14, i15, this.mIsLeapMonth);
                        }
                        i2 = 1;
                        i7 = actualMaximum;
                    }
                    i2 = 1;
                }
            }
            this.mDaySpinner.setMinValue(i2);
            this.mDaySpinner.setMaxValue(i7);
        }
        if (z) {
            this.mYearSpinner.setValue(this.mCurrentDate.get(1));
            int i17 = this.mCurrentDate.get(2);
            if (this.mIsLunar) {
                i17 = this.mLunarCurrentMonth;
            }
            if (usingNumericMonths()) {
                this.mMonthSpinner.setValue(i17 + 1);
            } else {
                this.mMonthSpinner.setValue(i17);
            }
            int i18 = this.mCurrentDate.get(5);
            if (this.mIsLunar) {
                i18 = this.mLunarCurrentDay;
            }
            this.mDaySpinner.setValue(i18);
            if (usingNumericMonths()) {
                this.mMonthSpinnerInput.setRawInputType(2);
            }
            if (this.mIsEditTextMode && (editTextArr = this.mPickerTexts) != null) {
                for (EditText editText : editTextArr) {
                    if (editText.hasFocus()) {
                        editText.setSelection(0, 0);
                        editText.selectAll();
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean usingNumericMonths() {
        return Character.isDigit(this.mShortMonths[0].charAt(0));
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    public int getDayOfMonth() {
        if (this.mIsLunar) {
            return this.mLunarCurrentDay;
        }
        return this.mCurrentDate.get(5);
    }

    public int getMonth() {
        if (this.mIsLunar) {
            return this.mLunarCurrentMonth;
        }
        return this.mCurrentDate.get(2);
    }

    public int getYear() {
        if (this.mIsLunar) {
            return this.mLunarCurrentYear;
        }
        return this.mCurrentDate.get(1);
    }

    public void init(int i2, int i7, int i8) {
        setDate(i2, i7, i8);
        updateSpinners(true, true, true, true);
    }

    public boolean isEditTextMode() {
        return this.mIsEditTextMode;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setCurrentLocale(configuration.locale);
        setSpinnersTextSize();
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.getText().add(DateUtils.formatDateTime(this.mContext, this.mCurrentDate.getTimeInMillis(), 20));
    }

    public void requestLayout() {
        super.requestLayout();
        SeslNumberPicker seslNumberPicker = this.mDaySpinner;
        if (seslNumberPicker != null) {
            seslNumberPicker.requestLayout();
        }
        SeslNumberPicker seslNumberPicker2 = this.mYearSpinner;
        if (seslNumberPicker2 != null) {
            seslNumberPicker2.requestLayout();
        }
        SeslNumberPicker seslNumberPicker3 = this.mMonthSpinner;
        if (seslNumberPicker3 != null) {
            seslNumberPicker3.requestLayout();
        }
    }

    public void setCurrentLocale(Locale locale) {
        this.mTempDate = getCalendarForLocale(this.mTempDate, locale);
        this.mMinDate = getCalendarForLocale(this.mMinDate, locale);
        this.mMaxDate = getCalendarForLocale(this.mMaxDate, locale);
        this.mCurrentDate = getCalendarForLocale(this.mCurrentDate, locale);
        this.mNumberOfMonths = this.mTempDate.getActualMaximum(2) + 1;
        this.mShortMonths = new DateFormatSymbols().getShortMonths();
        this.mMonths = new DateFormatSymbols().getMonths();
        int i2 = 0;
        int i7 = 0;
        while (true) {
            String[] strArr = this.mShortMonths;
            if (i7 >= strArr.length) {
                break;
            }
            strArr[i7] = strArr[i7].toUpperCase();
            i7++;
        }
        if (usingNumericMonths()) {
            this.mShortMonths = new String[this.mNumberOfMonths];
            while (i2 < this.mNumberOfMonths) {
                int i8 = i2 + 1;
                this.mShortMonths[i2] = String.format("%d", new Object[]{Integer.valueOf(i8)});
                i2 = i8;
            }
        }
    }

    public void setEditTextMode(boolean z) {
        if (this.mIsEditTextMode != z) {
            this.mIsEditTextMode = z;
            InputMethodManager inputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
            this.mDaySpinner.setEditTextMode(z);
            this.mMonthSpinner.setEditTextMode(z);
            this.mYearSpinner.setEditTextMode(z);
            if (inputMethodManager == null) {
                return;
            }
            if (!this.mIsEditTextMode) {
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else {
                inputMethodManager.showSoftInput(this.mDaySpinner, 0);
            }
        }
    }

    public void setEnabled(boolean z) {
        this.mDaySpinner.setEnabled(z);
        this.mMonthSpinner.setEnabled(z);
        this.mYearSpinner.setEnabled(z);
    }

    public void setMaxDate(long j2) {
        this.mMaxDate.setTimeInMillis(j2);
        if (this.mCurrentDate.after(this.mMaxDate)) {
            this.mCurrentDate.setTimeInMillis(this.mMaxDate.getTimeInMillis());
        }
        updateSpinners(true, true, true, true);
    }

    public void setMinDate(long j2) {
        this.mMinDate.setTimeInMillis(j2);
        if (this.mCurrentDate.before(this.mMinDate)) {
            this.mCurrentDate.setTimeInMillis(this.mMinDate.getTimeInMillis());
        }
        updateSpinners(true, true, true, true);
    }

    public void setOnEditTextModeChangedListener(SeslDatePicker seslDatePicker, SeslDatePicker.OnEditTextModeChangedListener onEditTextModeChangedListener) {
        if (this.mDatePicker == null) {
            this.mDatePicker = seslDatePicker;
        }
    }

    public void setOnSpinnerDateChangedListener(SeslDatePicker seslDatePicker, OnSpinnerDateChangedListener onSpinnerDateChangedListener) {
        if (this.mDatePicker == null) {
            this.mDatePicker = seslDatePicker;
        }
        this.mOnSpinnerDateChangedListener = onSpinnerDateChangedListener;
    }

    public void updateDate(int i2, int i7, int i8) {
        if (isNewDate(i2, i7, i8)) {
            setDate(i2, i7, i8);
            updateSpinners(true, true, true, true);
        }
    }

    public void updateInputState() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        if (inputMethodManager.isActive(this.mYearSpinnerInput)) {
            inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            this.mYearSpinnerInput.clearFocus();
        } else if (inputMethodManager.isActive(this.mMonthSpinnerInput)) {
            inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            this.mMonthSpinnerInput.clearFocus();
        } else if (inputMethodManager.isActive(this.mDaySpinnerInput)) {
            inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            this.mDaySpinnerInput.clearFocus();
        }
    }

    public SeslDatePickerSpinnerLayout(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public SeslDatePickerSpinnerLayout(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        Typeface typeface;
        this.mIsLunar = false;
        this.mIsLeapMonth = false;
        this.mPathClassLoader = null;
        this.mModeChangeListener = new SeslNumberPicker.OnEditTextModeChangedListener() {
            public void onEditTextModeChanged(SeslNumberPicker seslNumberPicker, boolean z) {
                SeslDatePickerSpinnerLayout.this.setEditTextMode(z);
                SeslDatePickerSpinnerLayout.this.updateModeState(z);
            }
        };
        this.mPickerTexts = new EditText[3];
        this.mEditorActionListener = new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
                if (i2 == 6) {
                    SeslDatePickerSpinnerLayout.this.updateInputState();
                    SeslDatePickerSpinnerLayout.this.setEditTextMode(false);
                }
                return false;
            }
        };
        this.mContext = context;
        LayoutInflater.from(context).inflate(R$layout.sesl_date_picker_spinner, this, true);
        Locale locale = Locale.getDefault();
        this.mCurrentLocale = locale;
        setCurrentLocale(locale);
        AnonymousClass2 r11 = new SeslNumberPicker.OnValueChangeListener() {
            /* JADX WARNING: Removed duplicated region for block: B:42:0x00dc  */
            /* JADX WARNING: Removed duplicated region for block: B:50:0x0122  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onValueChange(androidx.picker.widget.SeslNumberPicker r9, int r10, int r11) {
                /*
                    r8 = this;
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r0 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    java.util.Calendar r1 = r0.mTempDate
                    java.util.Calendar r0 = r0.mCurrentDate
                    long r2 = r0.getTimeInMillis()
                    r1.setTimeInMillis(r2)
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r0 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    boolean r1 = r0.mIsLunar
                    if (r1 == 0) goto L_0x001f
                    int r1 = r0.mLunarCurrentYear
                    r0.mLunarTempYear = r1
                    int r1 = r0.mLunarCurrentMonth
                    r0.mLunarTempMonth = r1
                    int r1 = r0.mLunarCurrentDay
                    r0.mLunarTempDay = r1
                L_0x001f:
                    androidx.picker.widget.SeslNumberPicker r0 = r0.mDaySpinner
                    boolean r0 = r9.equals(r0)
                    r1 = 2
                    r2 = 5
                    r3 = 0
                    r4 = 1
                    if (r0 == 0) goto L_0x007d
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r9 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    java.util.Calendar r9 = r9.mTempDate
                    int r9 = r9.getActualMaximum(r2)
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r0 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    boolean r5 = r0.mIsLunar
                    if (r5 == 0) goto L_0x004f
                    java.util.Calendar r9 = r0.mTempDate
                    int r9 = r9.get(r4)
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r5 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    java.util.Calendar r5 = r5.mTempDate
                    int r5 = r5.get(r1)
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r6 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    boolean r6 = r6.mIsLeapMonth
                    int r9 = r0.getLunarMaxDayOfMonth(r9, r5, r6)
                L_0x004f:
                    if (r10 != r9) goto L_0x0053
                    if (r11 == r4) goto L_0x0057
                L_0x0053:
                    if (r10 != r4) goto L_0x0067
                    if (r11 != r9) goto L_0x0067
                L_0x0057:
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r9 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    java.util.Calendar r9 = r9.mTempDate
                    r9.set(r2, r11)
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r9 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    boolean r10 = r9.mIsLunar
                    if (r10 == 0) goto L_0x007a
                    r9.mLunarTempDay = r11
                    goto L_0x007a
                L_0x0067:
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r9 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    java.util.Calendar r9 = r9.mTempDate
                    int r11 = r11 - r10
                    r9.add(r2, r11)
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r9 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    boolean r10 = r9.mIsLunar
                    if (r10 == 0) goto L_0x007a
                    int r10 = r9.mLunarTempDay
                    int r10 = r10 + r11
                    r9.mLunarTempDay = r10
                L_0x007a:
                    r9 = r3
                L_0x007b:
                    r10 = r9
                    goto L_0x00d6
                L_0x007d:
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r0 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    androidx.picker.widget.SeslNumberPicker r0 = r0.mMonthSpinner
                    boolean r0 = r9.equals(r0)
                    if (r0 == 0) goto L_0x00b7
                    r9 = 11
                    if (r10 != r9) goto L_0x008d
                    if (r11 == 0) goto L_0x0091
                L_0x008d:
                    if (r10 != 0) goto L_0x00a1
                    if (r11 != r9) goto L_0x00a1
                L_0x0091:
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r9 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    java.util.Calendar r9 = r9.mTempDate
                    r9.set(r1, r11)
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r9 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    boolean r10 = r9.mIsLunar
                    if (r10 == 0) goto L_0x00b4
                    r9.mLunarTempMonth = r11
                    goto L_0x00b4
                L_0x00a1:
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r9 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    java.util.Calendar r9 = r9.mTempDate
                    int r11 = r11 - r10
                    r9.add(r1, r11)
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r9 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    boolean r10 = r9.mIsLunar
                    if (r10 == 0) goto L_0x00b4
                    int r10 = r9.mLunarTempMonth
                    int r10 = r10 + r11
                    r9.mLunarTempMonth = r10
                L_0x00b4:
                    r9 = r3
                    r10 = r4
                    goto L_0x00d6
                L_0x00b7:
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r0 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    androidx.picker.widget.SeslNumberPicker r0 = r0.mYearSpinner
                    boolean r9 = r9.equals(r0)
                    if (r9 == 0) goto L_0x013a
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r9 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    java.util.Calendar r9 = r9.mTempDate
                    int r11 = r11 - r10
                    r9.add(r4, r11)
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r9 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    boolean r10 = r9.mIsLunar
                    if (r10 == 0) goto L_0x00d4
                    int r10 = r9.mLunarTempYear
                    int r10 = r10 + r11
                    r9.mLunarTempYear = r10
                L_0x00d4:
                    r9 = r4
                    goto L_0x007b
                L_0x00d6:
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r11 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    boolean r0 = r11.mIsLunar
                    if (r0 == 0) goto L_0x0104
                    int r0 = r11.mLunarTempYear
                    int r5 = r11.mLunarTempMonth
                    boolean r6 = r11.mIsLeapMonth
                    int r11 = r11.getLunarMaxDayOfMonth(r0, r5, r6)
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r0 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    int r5 = r0.mLunarTempDay
                    if (r5 <= r11) goto L_0x00ee
                    r0.mLunarTempDay = r11
                L_0x00ee:
                    boolean r11 = r0.mIsLeapMonth
                    if (r11 == 0) goto L_0x0104
                    dalvik.system.PathClassLoader r11 = r0.mPathClassLoader
                    java.lang.Object r5 = r0.mSolarLunarTables
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r6 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    int r7 = r6.mLunarTempYear
                    int r6 = r6.mLunarTempMonth
                    boolean r11 = androidx.reflect.lunarcalendar.SeslSolarLunarTablesReflector.isLeapMonth(r11, r5, r7, r6)
                    r0.mIsLeapMonth = r11
                L_0x0104:
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r11 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    java.util.Calendar r11 = r11.mTempDate
                    int r11 = r11.get(r4)
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r0 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    java.util.Calendar r0 = r0.mTempDate
                    int r0 = r0.get(r1)
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r1 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    java.util.Calendar r1 = r1.mTempDate
                    int r1 = r1.get(r2)
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r2 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    boolean r4 = r2.mIsLunar
                    if (r4 == 0) goto L_0x0128
                    int r11 = r2.mLunarTempYear
                    int r0 = r2.mLunarTempMonth
                    int r1 = r2.mLunarTempDay
                L_0x0128:
                    r2.setDate(r11, r0, r1)
                    if (r9 != 0) goto L_0x012f
                    if (r10 == 0) goto L_0x0134
                L_0x012f:
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r11 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    r11.updateSpinners(r3, r3, r9, r10)
                L_0x0134:
                    androidx.picker.widget.SeslDatePickerSpinnerLayout r8 = androidx.picker.widget.SeslDatePickerSpinnerLayout.this
                    r8.notifyDateChanged()
                    return
                L_0x013a:
                    java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
                    r8.<init>()
                    throw r8
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.picker.widget.SeslDatePickerSpinnerLayout.AnonymousClass2.onValueChange(androidx.picker.widget.SeslNumberPicker, int, int):void");
            }
        };
        this.mSpinners = (LinearLayout) findViewById(R$id.sesl_date_picker_pickers);
        this.mDayPaddingView = findViewById(R$id.sesl_date_picker_spinner_day_padding);
        this.mYearPaddingView = findViewById(R$id.sesl_date_picker_spinner_year_padding);
        SeslNumberPicker seslNumberPicker = (SeslNumberPicker) findViewById(R$id.sesl_date_picker_spinner_day);
        this.mDaySpinner = seslNumberPicker;
        int i8 = R$id.numberpicker_input;
        this.mDaySpinnerInput = (EditText) seslNumberPicker.findViewById(i8);
        seslNumberPicker.setFormatter(SeslNumberPicker.getTwoDigitFormatter());
        seslNumberPicker.setOnValueChangedListener(r11);
        seslNumberPicker.setOnEditTextModeChangedListener(this.mModeChangeListener);
        seslNumberPicker.setMaxInputLength(2);
        seslNumberPicker.setYearDateTimeInputMode();
        SeslNumberPicker seslNumberPicker2 = (SeslNumberPicker) findViewById(R$id.sesl_date_picker_spinner_month);
        this.mMonthSpinner = seslNumberPicker2;
        EditText editText = (EditText) seslNumberPicker2.findViewById(i8);
        this.mMonthSpinnerInput = editText;
        if (usingNumericMonths()) {
            seslNumberPicker2.setMinValue(1);
            seslNumberPicker2.setMaxValue(12);
            seslNumberPicker2.setYearDateTimeInputMode();
            seslNumberPicker2.setMaxInputLength(2);
        } else {
            seslNumberPicker2.setMinValue(0);
            seslNumberPicker2.setMaxValue(this.mNumberOfMonths - 1);
            seslNumberPicker2.setFormatter((SeslNumberPicker.Formatter) null);
            seslNumberPicker2.setDisplayedValues(this.mShortMonths);
            editText.setInputType(1);
            seslNumberPicker2.setMonthInputMode();
            seslNumberPicker2.setCustomTalkbackFormatter(new SeslNumberPicker.CustomTalkbackFormatter() {
                public String format(int i2) {
                    return SeslDatePickerSpinnerLayout.this.mMonths[i2];
                }
            });
        }
        seslNumberPicker2.setOnValueChangedListener(r11);
        seslNumberPicker2.setOnEditTextModeChangedListener(this.mModeChangeListener);
        SeslNumberPicker seslNumberPicker3 = (SeslNumberPicker) findViewById(R$id.sesl_date_picker_spinner_year);
        this.mYearSpinner = seslNumberPicker3;
        this.mYearSpinnerInput = (EditText) seslNumberPicker3.findViewById(i8);
        seslNumberPicker3.setOnValueChangedListener(r11);
        seslNumberPicker3.setOnEditTextModeChangedListener(this.mModeChangeListener);
        seslNumberPicker3.setMaxInputLength(4);
        seslNumberPicker3.setYearDateTimeInputMode();
        if (Build.VERSION.SDK_INT >= 34) {
            typeface = Typeface.create(Typeface.create("sec", 0), 600, false);
        } else {
            typeface = Typeface.create("sec-roboto-light", 1);
        }
        seslNumberPicker.setTextTypeface(typeface);
        seslNumberPicker2.setTextTypeface(typeface);
        seslNumberPicker3.setTextTypeface(typeface);
        this.mToastText = context.getResources().getString(R$string.sesl_number_picker_invalid_value_entered);
        setSpinnersTextSize();
        seslNumberPicker.setPickerContentDescription(context.getResources().getString(R$string.sesl_date_picker_day));
        seslNumberPicker2.setPickerContentDescription(context.getResources().getString(R$string.sesl_date_picker_month));
        seslNumberPicker3.setPickerContentDescription(context.getResources().getString(R$string.sesl_date_picker_year));
        this.mCurrentDate.setTimeInMillis(System.currentTimeMillis());
        init(this.mCurrentDate.get(1), this.mCurrentDate.get(2), this.mCurrentDate.get(5));
        reorderSpinners();
    }

    /* access modifiers changed from: private */
    public void seslLog(String str) {
    }
}
