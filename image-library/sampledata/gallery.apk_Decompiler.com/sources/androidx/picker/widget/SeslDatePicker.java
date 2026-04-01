package androidx.picker.widget;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;
import androidx.appcompat.R$id;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.ColorUtils;
import androidx.picker.R$color;
import androidx.picker.R$dimen;
import androidx.picker.R$layout;
import androidx.picker.R$string;
import androidx.picker.R$styleable;
import androidx.picker.widget.SeslDatePickerSpinnerLayout;
import androidx.picker.widget.SeslSimpleMonthView;
import androidx.reflect.feature.SeslCscFeatureReflector;
import androidx.reflect.feature.SeslFloatingFeatureReflector;
import androidx.reflect.lunarcalendar.SeslSolarLunarConverterReflector;
import androidx.reflect.lunarcalendar.SeslSolarLunarTablesReflector;
import androidx.reflect.os.SeslSystemPropertiesReflector;
import androidx.reflect.view.SeslViewReflector;
import androidx.reflect.widget.SeslHoverPopupWindowReflector;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sdk.cover.ScoverState;
import dalvik.system.PathClassLoader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslDatePicker extends LinearLayout implements SeslSimpleMonthView.OnDayClickListener, View.OnClickListener, View.OnLongClickListener, SeslSimpleMonthView.OnDeactivatedDayClickListener {
    private static final PathInterpolator CALENDAR_HEADER_SPINNER_INTERPOLATOR = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);
    private static PackageManager mPackageManager;
    private final ViewAnimator mAnimator;
    private final ObjectAnimator mAntiClockwiseAnim;
    protected int mBackgroundBorderlessResId;
    private final View.OnFocusChangeListener mBtnFocusChangeListener;
    private final int mBtnTintColor;
    private final RelativeLayout mCalendarHeader;
    private final View.OnClickListener mCalendarHeaderClickListener;
    private final RelativeLayout mCalendarHeaderLayout;
    protected int mCalendarHeaderLayoutHeight;
    private final View mCalendarHeaderSpinner;
    /* access modifiers changed from: private */
    public final TextView mCalendarHeaderText;
    private final int mCalendarHeaderTextColor;
    private final LinearLayout mCalendarHeaderTextSpinnerLayout;
    /* access modifiers changed from: private */
    public final CalendarPagerAdapter mCalendarPagerAdapter;
    protected int mCalendarViewMargin;
    /* access modifiers changed from: private */
    public final ViewPager mCalendarViewPager;
    protected int mCalendarViewPagerHeight;
    protected int mCalendarViewPagerWidth;
    private ChangeCurrentByOneFromLongPressCommand mChangeCurrentByOneFromLongPressCommand;
    private final ObjectAnimator mClockwiseAnim;
    private FrameLayout mContentFrame;
    /* access modifiers changed from: private */
    public final Context mContext;
    protected Calendar mCurrentDate;
    private Locale mCurrentLocale;
    protected int mCurrentPosition;
    protected int mCurrentViewType;
    private RelativeLayout mCustomButtonLayout;
    private View mCustomButtonView;
    protected int mDatePickerHeight;
    private final LinearLayout mDatePickerLayout;
    /* access modifiers changed from: private */
    public SimpleDateFormat mDayFormatter;
    private final LinearLayout mDayOfTheWeekLayout;
    protected int mDayOfTheWeekLayoutHeight;
    protected int mDayOfTheWeekLayoutWidth;
    private final DayOfTheWeekView mDayOfTheWeekView;
    protected int mDayOfWeekStart;
    private int mDialogPaddingVertical;
    private Window mDialogWindow;
    protected Calendar mEndDate;
    private final View mFirstBlankSpace;
    protected int mFirstBlankSpaceHeight;
    protected int mFirstDayOfWeek;
    /* access modifiers changed from: private */
    public final Handler mHandler;
    private boolean mIsCalendarViewDisabled;
    private boolean mIsCalledFromDeactivatedDayClick;
    /* access modifiers changed from: private */
    public boolean mIsConfigurationChanged;
    protected boolean mIsCustomButtonSeparate;
    private boolean mIsEnabled;
    private boolean mIsFarsiLanguage;
    private boolean mIsFirstMeasure;
    protected boolean mIsFromSetLunar;
    private final boolean mIsInDialog;
    protected int mIsLeapEndMonth;
    protected boolean mIsLeapMonth;
    protected int mIsLeapStartMonth;
    protected boolean mIsLunar;
    protected boolean mIsLunarSupported;
    /* access modifiers changed from: private */
    public boolean mIsRTL;
    private boolean mIsSimplifiedChinese;
    private boolean mIsSpinnerViewDisabled;
    /* access modifiers changed from: private */
    public boolean mIsWeekRangeSet;
    protected boolean mLunarChanged;
    protected int mLunarCurrentDay;
    protected int mLunarCurrentMonth;
    protected int mLunarCurrentYear;
    protected int mLunarEndDay;
    protected int mLunarEndMonth;
    protected int mLunarEndYear;
    protected int mLunarStartDay;
    protected int mLunarStartMonth;
    protected int mLunarStartYear;
    protected Calendar mMaxDate;
    private int mMeasureSpecHeight;
    protected Calendar mMinDate;
    protected int mMode;
    private final View.OnKeyListener mMonthBtnKeyListener;
    private final View.OnTouchListener mMonthBtnTouchListener;
    protected String mMonthViewColor;
    /* access modifiers changed from: private */
    public final ImageButton mNextButton;
    protected int mNumDays;
    protected int mOldCalendarViewPagerWidth;
    protected int mOldSelectedDay;
    private OnDateChangedListener mOnDateChangedListener;
    protected int mPadding;
    PathClassLoader mPathClassLoader;
    protected int mPositionCount;
    /* access modifiers changed from: private */
    public final ImageButton mPrevButton;
    private final View mSecondBlankSpace;
    protected int mSecondBlankSpaceHeight;
    private Object mSolarLunarConverter;
    private Object mSolarLunarTables;
    private final SeslDatePickerSpinnerLayout mSpinnerLayout;
    protected Calendar mStartDate;
    protected final Calendar mTempDate;
    protected final Calendar mTempMinMaxDate;
    protected int[] mTotalMonthCountWithLeap;
    protected int mWeekStart;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class CalendarPagerAdapter extends PagerAdapter {
        final SparseArray<SeslSimpleMonthView> views = new SparseArray<>();

        public CalendarPagerAdapter() {
        }

        public void destroyItem(View view, int i2, Object obj) {
            SeslDatePicker seslDatePicker = SeslDatePicker.this;
            seslDatePicker.debugLog("destroyItem : " + i2);
            ((ViewPager) view).removeView((View) obj);
            this.views.remove(i2);
        }

        public void finishUpdate(View view) {
            SeslDatePicker.this.debugLog("finishUpdate");
        }

        public int getCount() {
            int maxYear = SeslDatePicker.this.getMaxYear() - SeslDatePicker.this.getMinYear();
            SeslDatePicker seslDatePicker = SeslDatePicker.this;
            seslDatePicker.mPositionCount = (maxYear * 12) + (seslDatePicker.getMaxMonth() - SeslDatePicker.this.getMinMonth()) + 1;
            SeslDatePicker seslDatePicker2 = SeslDatePicker.this;
            if (seslDatePicker2.mIsLunar) {
                seslDatePicker2.mPositionCount = seslDatePicker2.getTotalMonthCountWithLeap(seslDatePicker2.getMaxYear());
            }
            return SeslDatePicker.this.mPositionCount;
        }

        public int getItemPosition(Object obj) {
            return -2;
        }

        public Object instantiateItem(View view, int i2) {
            boolean z;
            int i7;
            int i8 = i2;
            SeslSimpleMonthView seslSimpleMonthView = new SeslSimpleMonthView(SeslDatePicker.this.mContext);
            SeslDatePicker.this.debugLog("instantiateItem : " + i8);
            seslSimpleMonthView.setClickable(true);
            seslSimpleMonthView.setOnDayClickListener(SeslDatePicker.this);
            seslSimpleMonthView.setOnDeactivatedDayClickListener(SeslDatePicker.this);
            seslSimpleMonthView.setTextColor(SeslDatePicker.this.mMonthViewColor);
            int minMonth = SeslDatePicker.this.getMinMonth() + i8;
            int minYear = SeslDatePicker.this.getMinYear() + (minMonth / 12);
            int i10 = minMonth % 12;
            SeslDatePicker seslDatePicker = SeslDatePicker.this;
            if (seslDatePicker.mIsLunar) {
                LunarDate access$2300 = seslDatePicker.getLunarDateByPosition(i8);
                minYear = access$2300.year;
                int i11 = access$2300.month;
                z = access$2300.isLeapMonth;
                i10 = i11;
            } else {
                z = false;
            }
            int i12 = -1;
            if (SeslDatePicker.this.mCurrentDate.get(1) == minYear && SeslDatePicker.this.mCurrentDate.get(2) == i10) {
                i7 = SeslDatePicker.this.mCurrentDate.get(5);
            } else {
                i7 = -1;
            }
            SeslDatePicker seslDatePicker2 = SeslDatePicker.this;
            boolean z3 = seslDatePicker2.mIsLunar;
            if (!z3) {
                i12 = i7;
            } else if (seslDatePicker2.mLunarCurrentYear == minYear && seslDatePicker2.mLunarCurrentMonth == i10) {
                i12 = seslDatePicker2.mLunarCurrentDay;
            }
            if (seslDatePicker2.mIsLunarSupported) {
                seslSimpleMonthView.setLunar(z3, z, seslDatePicker2.mPathClassLoader);
            }
            int i13 = SeslDatePicker.this.mStartDate.get(1);
            int i14 = SeslDatePicker.this.mStartDate.get(2);
            int i15 = SeslDatePicker.this.mStartDate.get(5);
            int i16 = SeslDatePicker.this.mEndDate.get(1);
            int i17 = SeslDatePicker.this.mEndDate.get(2);
            int i18 = SeslDatePicker.this.mEndDate.get(5);
            SeslDatePicker seslDatePicker3 = SeslDatePicker.this;
            if (seslDatePicker3.mIsLunar) {
                i13 = seslDatePicker3.mLunarStartYear;
                i14 = seslDatePicker3.mLunarStartMonth;
                i15 = seslDatePicker3.mLunarStartDay;
                i16 = seslDatePicker3.mLunarEndYear;
                i17 = seslDatePicker3.mLunarEndMonth;
                i18 = seslDatePicker3.mLunarEndDay;
            }
            int i19 = i17;
            int i20 = i16;
            int i21 = i13;
            int i22 = i15;
            int i23 = i14;
            int firstDayOfWeek = seslDatePicker3.getFirstDayOfWeek();
            SeslDatePicker seslDatePicker4 = SeslDatePicker.this;
            int i24 = minYear;
            int i25 = firstDayOfWeek;
            int i26 = i24;
            seslSimpleMonthView.setMonthParams(i12, i10, i26, i25, 1, 31, seslDatePicker4.mMinDate, seslDatePicker4.mMaxDate, i21, i23, i22, seslDatePicker4.mIsLeapStartMonth, i20, i19, i18, seslDatePicker4.mIsLeapEndMonth, seslDatePicker4.mMode);
            if (i8 == 0) {
                seslSimpleMonthView.setFirstMonth();
            }
            if (i8 == SeslDatePicker.this.mPositionCount - 1) {
                seslSimpleMonthView.setLastMonth();
            }
            SeslDatePicker seslDatePicker5 = SeslDatePicker.this;
            if (seslDatePicker5.mIsLunar) {
                if (i8 != 0 && seslDatePicker5.getLunarDateByPosition(i8 - 1).isLeapMonth) {
                    seslSimpleMonthView.setPrevMonthLeap();
                }
                SeslDatePicker seslDatePicker6 = SeslDatePicker.this;
                if (i8 != seslDatePicker6.mPositionCount - 1 && seslDatePicker6.getLunarDateByPosition(i8 + 1).isLeapMonth) {
                    seslSimpleMonthView.setNextMonthLeap();
                }
            }
            DateValidator unused = SeslDatePicker.this.getClass();
            SeslDatePicker.this.mNumDays = seslSimpleMonthView.getNumDays();
            SeslDatePicker.this.mWeekStart = seslSimpleMonthView.getWeekStart();
            ((ViewPager) view).addView(seslSimpleMonthView, 0);
            this.views.put(i8, seslSimpleMonthView);
            return seslSimpleMonthView;
        }

        public boolean isViewFromObject(View view, Object obj) {
            if (view == null || !view.equals(obj)) {
                return false;
            }
            return true;
        }

        public Parcelable saveState() {
            return null;
        }

        public void startUpdate(View view) {
            SeslDatePicker.this.debugLog("startUpdate");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ChangeCurrentByOneFromLongPressCommand implements Runnable {
        private boolean mIncrement;

        private ChangeCurrentByOneFromLongPressCommand() {
        }

        /* access modifiers changed from: private */
        public void setStep(boolean z) {
            this.mIncrement = z;
        }

        public void run() {
            if (this.mIncrement) {
                SeslDatePicker.this.mCalendarViewPager.setCurrentItem(SeslDatePicker.this.mCurrentPosition + 1);
            } else {
                SeslDatePicker.this.mCalendarViewPager.setCurrentItem(SeslDatePicker.this.mCurrentPosition - 1);
            }
            SeslDatePicker.this.postDelayed(this, 300);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DateValidator {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class DayOfTheWeekView extends View {
        private final int[] mDayColorSet = new int[7];
        private final Calendar mDayLabelCalendar = Calendar.getInstance();
        private final String mDefaultWeekdayFeatureString = "XXXXXXR";
        private final Paint mMonthDayLabelPaint;
        private final int mNormalDayTextColor;
        private final int mSaturdayTextColor;
        private final int mSundayTextColor;
        private final String mWeekdayFeatureString;

        public DayOfTheWeekView(Context context, TypedArray typedArray) {
            super(context);
            Resources resources = context.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.sesl_date_picker_month_day_label_text_size);
            int color = typedArray.getColor(R$styleable.DatePicker_dayTextColor, resources.getColor(R$color.sesl_date_picker_normal_text_color_light));
            this.mNormalDayTextColor = color;
            this.mSundayTextColor = typedArray.getColor(R$styleable.DatePicker_sundayTextColor, resources.getColor(R$color.sesl_date_picker_sunday_text_color_light));
            this.mSaturdayTextColor = ResourcesCompat.getColor(resources, R$color.sesl_date_picker_saturday_week_text_color_light, (Resources.Theme) null);
            String str = SeslDatePicker.this.mMonthViewColor;
            if (str != null) {
                this.mWeekdayFeatureString = str;
            } else {
                this.mWeekdayFeatureString = SeslCscFeatureReflector.getString("CscFeature_Calendar_SetColorOfDays", "XXXXXXR");
            }
            Paint paint = new Paint();
            this.mMonthDayLabelPaint = paint;
            paint.setAntiAlias(true);
            paint.setColor(color);
            paint.setTextSize((float) dimensionPixelSize);
            if (Build.VERSION.SDK_INT >= 33) {
                paint.setTypeface(Typeface.create(Typeface.create("sec", 0), 400, false));
            } else {
                paint.setTypeface(Typeface.create("sec-roboto-light", 0));
            }
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setStyle(Paint.Style.FILL);
            paint.setFakeBoldText(false);
        }

        public void onDraw(Canvas canvas) {
            int i2;
            int i7;
            int i8;
            super.onDraw(canvas);
            SeslDatePicker seslDatePicker = SeslDatePicker.this;
            int i10 = seslDatePicker.mNumDays;
            if (i10 != 0) {
                int i11 = (seslDatePicker.mDayOfTheWeekLayoutHeight * 2) / 3;
                int i12 = seslDatePicker.mDayOfTheWeekLayoutWidth / (i10 * 2);
                int i13 = 0;
                for (int i14 = 0; i14 < SeslDatePicker.this.mNumDays; i14++) {
                    char charAt = this.mWeekdayFeatureString.charAt(i14);
                    int i15 = (i14 + 2) % SeslDatePicker.this.mNumDays;
                    if (charAt == 'B') {
                        i8 = ColorUtils.setAlphaComponent(this.mSaturdayTextColor, ScoverState.TYPE_NFC_SMART_COVER);
                    } else if (charAt != 'R') {
                        i8 = ColorUtils.setAlphaComponent(this.mNormalDayTextColor, 204);
                    } else {
                        i8 = ColorUtils.setAlphaComponent(this.mSundayTextColor, ScoverState.TYPE_NFC_SMART_COVER);
                    }
                    this.mDayColorSet[i15] = i8;
                }
                while (true) {
                    SeslDatePicker seslDatePicker2 = SeslDatePicker.this;
                    int i16 = seslDatePicker2.mNumDays;
                    if (i13 < i16) {
                        int i17 = (seslDatePicker2.mWeekStart + i13) % i16;
                        this.mDayLabelCalendar.set(7, i17);
                        String upperCase = SeslDatePicker.this.mDayFormatter.format(this.mDayLabelCalendar.getTime()).toUpperCase();
                        if (SeslDatePicker.this.mIsRTL) {
                            SeslDatePicker seslDatePicker3 = SeslDatePicker.this;
                            i2 = ((((seslDatePicker3.mNumDays - 1) - i13) * 2) + 1) * i12;
                            i7 = seslDatePicker3.mPadding;
                        } else {
                            i7 = ((i13 * 2) + 1) * i12;
                            i2 = SeslDatePicker.this.mPadding;
                        }
                        this.mMonthDayLabelPaint.setColor(this.mDayColorSet[i17]);
                        canvas.drawText(upperCase, (float) (i2 + i7), (float) i11, this.mMonthDayLabelPaint);
                        i13++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LunarDate {
        public int day = 1;
        boolean isLeapMonth = false;
        public int month = 1;
        public int year = 1900;

        public void set(int i2, int i7, int i8, boolean z) {
            this.year = i2;
            this.month = i7;
            this.day = i8;
            this.isLeapMonth = z;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnDateChangedListener {
        void onDateChanged(SeslDatePicker seslDatePicker, int i2, int i7, int i8);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnEditTextModeChangedListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnViewTypeChangedListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ValidationCallback {
    }

    public SeslDatePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16843612);
    }

    private void addCustomButtonInHeader() {
        if (this.mCustomButtonView != null) {
            removeCustomViewFromParent();
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mCalendarHeader.getLayoutParams();
            layoutParams.addRule(16, this.mCustomButtonView.getId());
            layoutParams.leftMargin = this.mContext.getResources().getDimensionPixelOffset(R$dimen.sesl_date_picker_lunar_calendar_header_margin);
            ((RelativeLayout.LayoutParams) this.mPrevButton.getLayoutParams()).leftMargin = 0;
            ((RelativeLayout.LayoutParams) this.mNextButton.getLayoutParams()).rightMargin = 0;
            this.mCalendarHeaderLayout.addView(this.mCustomButtonView);
        }
    }

    private void addCustomButtonSeparateLayout() {
        if (this.mCustomButtonView != null) {
            RelativeLayout relativeLayout = this.mCustomButtonLayout;
            if (relativeLayout == null) {
                this.mCustomButtonLayout = new RelativeLayout(this.mContext);
                this.mCustomButtonLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, this.mCalendarHeaderLayoutHeight));
            } else {
                ((LinearLayout.LayoutParams) relativeLayout.getLayoutParams()).height = this.mCalendarHeaderLayoutHeight;
            }
            removeCustomViewFromParent();
            this.mCustomButtonLayout.addView(this.mCustomButtonView);
            this.mDatePickerLayout.addView(this.mCustomButtonLayout, 0);
            this.mDatePickerHeight += this.mCalendarHeaderLayoutHeight;
        }
    }

    private void calculateContentHeight() {
        Window window;
        if (getLayoutParams().height == -2 || getMeasuredHeight() <= this.mDatePickerHeight) {
            if (this.mContentFrame == null && (window = this.mDialogWindow) != null) {
                this.mContentFrame = (FrameLayout) window.findViewById(R$id.customPanel);
            }
            int i2 = this.mMeasureSpecHeight;
            FrameLayout frameLayout = this.mContentFrame;
            if (frameLayout != null) {
                i2 = frameLayout.getMeasuredHeight();
                if (this.mDialogWindow != null) {
                    i2 -= this.mDialogPaddingVertical;
                }
            }
            if (!isEditTextMode()) {
                updateViewType(i2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void clearCalendar(Calendar calendar, int i2, int i7, int i8) {
        calendar.clear();
        calendar.set(1, i2);
        calendar.set(2, i7);
        calendar.set(5, i8);
    }

    private Calendar convertLunarToSolar(Calendar calendar, int i2, int i7, int i8) {
        Calendar calendar2 = (Calendar) calendar.clone();
        SeslSolarLunarConverterReflector.convertLunarToSolar(this.mPathClassLoader, this.mSolarLunarConverter, i2, i7, i8, this.mIsLeapMonth);
        calendar2.set(SeslSolarLunarConverterReflector.getYear(this.mPathClassLoader, this.mSolarLunarConverter), SeslSolarLunarConverterReflector.getMonth(this.mPathClassLoader, this.mSolarLunarConverter), SeslSolarLunarConverterReflector.getDay(this.mPathClassLoader, this.mSolarLunarConverter));
        return calendar2;
    }

    private Calendar convertSolarToLunar(Calendar calendar, LunarDate lunarDate) {
        Calendar calendar2 = (Calendar) calendar.clone();
        SeslSolarLunarConverterReflector.convertSolarToLunar(this.mPathClassLoader, this.mSolarLunarConverter, calendar.get(1), calendar.get(2), calendar.get(5));
        calendar2.set(SeslSolarLunarConverterReflector.getYear(this.mPathClassLoader, this.mSolarLunarConverter), SeslSolarLunarConverterReflector.getMonth(this.mPathClassLoader, this.mSolarLunarConverter), SeslSolarLunarConverterReflector.getDay(this.mPathClassLoader, this.mSolarLunarConverter));
        if (lunarDate != null) {
            lunarDate.day = SeslSolarLunarConverterReflector.getDay(this.mPathClassLoader, this.mSolarLunarConverter);
            lunarDate.month = SeslSolarLunarConverterReflector.getMonth(this.mPathClassLoader, this.mSolarLunarConverter);
            lunarDate.year = SeslSolarLunarConverterReflector.getYear(this.mPathClassLoader, this.mSolarLunarConverter);
            lunarDate.isLeapMonth = SeslSolarLunarConverterReflector.isLeapMonth(this.mPathClassLoader, this.mSolarLunarConverter);
        }
        return calendar2;
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

    private static String getCalendarPackageName() {
        String string = SeslFloatingFeatureReflector.getString("SEC_FLOATING_FEATURE_CALENDAR_CONFIG_PACKAGE_NAME", "com.android.calendar");
        if ("com.android.calendar".equals(string)) {
            return string;
        }
        try {
            mPackageManager.getPackageInfo(string, 0);
            return string;
        } catch (PackageManager.NameNotFoundException unused) {
            return "com.android.calendar";
        }
    }

    /* access modifiers changed from: private */
    public int getDayOffset() {
        int i2;
        SeslSimpleMonthView seslSimpleMonthView = this.mCalendarPagerAdapter.views.get(this.mCurrentPosition);
        if (seslSimpleMonthView == null) {
            i2 = 1;
        } else {
            i2 = seslSimpleMonthView.getDayOfWeekStart();
        }
        this.mDayOfWeekStart = i2;
        int i7 = (((this.mCurrentDate.get(5) % 7) + this.mDayOfWeekStart) - 1) % 7;
        if (i7 == 0) {
            return 7;
        }
        return i7;
    }

    private String getFormattedCurrentDate() {
        return DateUtils.formatDateTime(this.mContext, this.mCurrentDate.getTimeInMillis(), 20);
    }

    private int getIndexOfleapMonthOfYear(int i2) {
        Object obj = this.mSolarLunarTables;
        if (obj == null) {
            return 127;
        }
        int field_START_OF_LUNAR_YEAR = SeslSolarLunarTablesReflector.getField_START_OF_LUNAR_YEAR(this.mPathClassLoader, obj);
        int field_WIDTH_PER_YEAR = SeslSolarLunarTablesReflector.getField_WIDTH_PER_YEAR(this.mPathClassLoader, this.mSolarLunarTables);
        return SeslSolarLunarTablesReflector.getLunar(this.mPathClassLoader, this.mSolarLunarTables, ((i2 - field_START_OF_LUNAR_YEAR) * field_WIDTH_PER_YEAR) + SeslSolarLunarTablesReflector.getField_INDEX_OF_LEAP_MONTH(this.mPathClassLoader, this.mSolarLunarTables));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: int} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.picker.widget.SeslDatePicker.LunarDate getLunarDateByPosition(int r8) {
        /*
            r7 = this;
            androidx.picker.widget.SeslDatePicker$LunarDate r0 = new androidx.picker.widget.SeslDatePicker$LunarDate
            r0.<init>()
            int r1 = r7.getMinYear()
            int r2 = r7.getMinYear()
        L_0x000d:
            int r3 = r7.getMaxYear()
            r4 = 1
            r5 = 0
            if (r2 > r3) goto L_0x004c
            int r3 = r7.getTotalMonthCountWithLeap(r2)
            if (r8 >= r3) goto L_0x0049
            int r1 = r7.getMinYear()
            if (r2 != r1) goto L_0x0027
            int r1 = r7.getMinMonth()
            int r1 = -r1
            goto L_0x002d
        L_0x0027:
            int r1 = r2 + -1
            int r1 = r7.getTotalMonthCountWithLeap(r1)
        L_0x002d:
            int r8 = r8 - r1
            int r7 = r7.getIndexOfleapMonthOfYear(r2)
            r1 = 13
            r3 = 12
            if (r7 <= r3) goto L_0x0039
            goto L_0x003a
        L_0x0039:
            r3 = r1
        L_0x003a:
            if (r8 >= r7) goto L_0x003e
            r6 = r8
            goto L_0x0040
        L_0x003e:
            int r6 = r8 + -1
        L_0x0040:
            if (r3 != r1) goto L_0x0045
            if (r7 != r8) goto L_0x0045
            r5 = r4
        L_0x0045:
            r1 = r2
            r7 = r5
            r5 = r6
            goto L_0x004d
        L_0x0049:
            int r2 = r2 + 1
            goto L_0x000d
        L_0x004c:
            r7 = r5
        L_0x004d:
            r0.set(r1, r5, r4, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.picker.widget.SeslDatePicker.getLunarDateByPosition(int):androidx.picker.widget.SeslDatePicker$LunarDate");
    }

    /* access modifiers changed from: private */
    public String getMonthAndYearString(Calendar calendar) {
        if (this.mIsFarsiLanguage) {
            return new SimpleDateFormat("LLLL y", this.mCurrentLocale).format(calendar.getTime());
        }
        int i2 = this.mContext.getResources().getConfiguration().screenWidthDp;
        StringBuilder sb2 = new StringBuilder(50);
        Formatter formatter = new Formatter(sb2, this.mCurrentLocale);
        sb2.setLength(0);
        long timeInMillis = calendar.getTimeInMillis();
        if (i2 <= 250) {
            return DateUtils.formatDateRange(getContext(), formatter, timeInMillis, timeInMillis, 65572, Time.getCurrentTimezone()).toString().toUpperCase();
        }
        return DateUtils.formatDateRange(getContext(), formatter, timeInMillis, timeInMillis, 36, Time.getCurrentTimezone()).toString();
    }

    private String getMonthViewColorStringForSpecific() {
        String simOperator;
        try {
            if ("wifi-only".equalsIgnoreCase(SeslSystemPropertiesReflector.getStringProperties("ro.carrier"))) {
                String stringProperties = SeslSystemPropertiesReflector.getStringProperties("persist.sys.selected_country_iso");
                if (TextUtils.isEmpty(stringProperties)) {
                    stringProperties = SeslSystemPropertiesReflector.getStringProperties("ro.csc.countryiso_code");
                }
                if ("AE".equals(stringProperties)) {
                    return "XXXXXBR";
                }
            } else if ("XSG".equals(SeslSystemPropertiesReflector.getSalesCode()) && (simOperator = ((TelephonyManager) this.mContext.getSystemService("phone")).getSimOperator()) != null && simOperator.length() > 3 && Integer.parseInt(simOperator.substring(0, 3)) == 424) {
                return "XXXXXBR";
            }
            return null;
        } catch (NoClassDefFoundError e) {
            Log.e("SeslDatePicker", "msg : " + e.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: private */
    public int getTotalMonthCountWithLeap(int i2) {
        if (this.mTotalMonthCountWithLeap == null || i2 < getMinYear()) {
            return 0;
        }
        return this.mTotalMonthCountWithLeap[i2 - getMinYear()];
    }

    private boolean isFarsiLanguage() {
        return "fa".equals(this.mCurrentLocale.getLanguage());
    }

    private boolean isRTL() {
        if ("ur".equals(this.mCurrentLocale.getLanguage())) {
            return false;
        }
        Locale locale = this.mCurrentLocale;
        byte directionality = Character.getDirectionality(locale.getDisplayName(locale).charAt(0));
        if (directionality == 1 || directionality == 2) {
            return true;
        }
        return false;
    }

    private boolean isSimplifiedChinese() {
        String language = this.mCurrentLocale.getLanguage();
        Locale locale = Locale.SIMPLIFIED_CHINESE;
        if (!language.equals(locale.getLanguage()) || !this.mCurrentLocale.getCountry().equals(locale.getCountry())) {
            return false;
        }
        return true;
    }

    private boolean isSystemAnimationsRemoved() {
        if (Settings.Global.getFloat(this.mContext.getContentResolver(), "animator_duration_scale", 1.0f) == 0.0f) {
            return true;
        }
        return false;
    }

    private int makeMeasureSpec(int i2, int i7) {
        int i8;
        if (i7 == -1) {
            return i2;
        }
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            int i10 = getResources().getConfiguration().smallestScreenWidthDp;
            if (i10 >= 600) {
                i8 = getResources().getDimensionPixelSize(R$dimen.sesl_date_picker_dialog_min_width);
            } else {
                i8 = (int) (TypedValue.applyDimension(1, (float) i10, getResources().getDisplayMetrics()) + 0.5f);
            }
        } else {
            i8 = View.MeasureSpec.getSize(i2);
        }
        if (mode == Integer.MIN_VALUE) {
            int i11 = this.mCalendarViewMargin;
            this.mCalendarViewPagerWidth = i8 - (i11 * 2);
            this.mDayOfTheWeekLayoutWidth = i8 - (i11 * 2);
            return View.MeasureSpec.makeMeasureSpec(i8, 1073741824);
        } else if (mode == 0) {
            return View.MeasureSpec.makeMeasureSpec(i7, 1073741824);
        } else {
            if (mode == 1073741824) {
                int i12 = this.mCalendarViewMargin;
                this.mCalendarViewPagerWidth = i8 - (i12 * 2);
                this.mDayOfTheWeekLayoutWidth = i8 - (i12 * 2);
                return i2;
            }
            throw new IllegalArgumentException(C0086a.i(mode, "Unknown measure mode: "));
        }
    }

    private void manageCalendarHeaderLayoutClick(boolean z) {
        if (z) {
            this.mCalendarHeaderTextSpinnerLayout.setOnClickListener((View.OnClickListener) null);
            this.mCalendarHeaderTextSpinnerLayout.setClickable(false);
            setCalendarHeaderPadding(false);
            this.mCalendarHeaderSpinner.setVisibility(8);
        } else if (!this.mCalendarHeaderTextSpinnerLayout.hasOnClickListeners()) {
            this.mCalendarHeaderTextSpinnerLayout.setOnClickListener(this.mCalendarHeaderClickListener);
            this.mCalendarHeaderTextSpinnerLayout.setClickable(true);
            setCalendarHeaderPadding(true);
            this.mCalendarHeaderSpinner.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void onDateChanged() {
        if (this.mOnDateChangedListener != null) {
            int i2 = this.mCurrentDate.get(1);
            int i7 = this.mCurrentDate.get(2);
            int i8 = this.mCurrentDate.get(5);
            if (this.mIsLunar) {
                i2 = this.mLunarCurrentYear;
                i7 = this.mLunarCurrentMonth;
                i8 = this.mLunarCurrentDay;
            }
            this.mOnDateChangedListener.onDateChanged(this, i2, i7, i8);
        }
    }

    private void postChangeCurrentByOneFromLongPress(boolean z, long j2) {
        ChangeCurrentByOneFromLongPressCommand changeCurrentByOneFromLongPressCommand = this.mChangeCurrentByOneFromLongPressCommand;
        if (changeCurrentByOneFromLongPressCommand == null) {
            this.mChangeCurrentByOneFromLongPressCommand = new ChangeCurrentByOneFromLongPressCommand();
        } else {
            removeCallbacks(changeCurrentByOneFromLongPressCommand);
        }
        this.mChangeCurrentByOneFromLongPressCommand.setStep(z);
        postDelayed(this.mChangeCurrentByOneFromLongPressCommand, j2);
    }

    private void removeCustomButtonInHeader() {
        Resources resources = this.mContext.getResources();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mCalendarHeader.getLayoutParams();
        layoutParams.removeRule(16);
        layoutParams.leftMargin = 0;
        int i2 = R$dimen.sesl_date_picker_calendar_view_margin;
        ((RelativeLayout.LayoutParams) this.mPrevButton.getLayoutParams()).leftMargin = resources.getDimensionPixelOffset(i2);
        ((RelativeLayout.LayoutParams) this.mNextButton.getLayoutParams()).rightMargin = resources.getDimensionPixelOffset(i2);
        removeCustomViewFromParent();
    }

    private void removeCustomButtonSeparateLayout() {
        removeCustomViewFromParent();
        this.mDatePickerLayout.removeView(this.mCustomButtonLayout);
        this.mDatePickerHeight -= this.mCalendarHeaderLayoutHeight;
    }

    private void removeCustomViewFromParent() {
        View view = this.mCustomButtonView;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.mCustomButtonView);
            }
        }
    }

    private static Activity scanForActivity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return scanForActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    private void setCalendarHeaderPadding(boolean z) {
        if (z) {
            this.mCalendarHeaderTextSpinnerLayout.setPadding(this.mContext.getResources().getDimensionPixelSize(R$dimen.sesl_date_picker_calendar_header_layout_padding_left), getPaddingTop(), this.mContext.getResources().getDimensionPixelSize(R$dimen.sesl_date_picker_calendar_header_layout_padding_right), getPaddingBottom());
        } else {
            this.mCalendarHeaderTextSpinnerLayout.setPadding(0, getPaddingTop(), 0, getPaddingBottom());
        }
    }

    /* access modifiers changed from: private */
    public void setNextButtonProperties(float f, boolean z) {
        this.mNextButton.setImageAlpha((int) (f * 255.0f));
        if (z) {
            this.mNextButton.setBackgroundResource(this.mBackgroundBorderlessResId);
            this.mNextButton.setEnabled(true);
            this.mNextButton.setFocusable(true);
            return;
        }
        this.mNextButton.setBackground((Drawable) null);
        this.mNextButton.setEnabled(false);
        this.mNextButton.setFocusable(false);
    }

    /* access modifiers changed from: private */
    public void setPrevButtonProperties(float f, boolean z) {
        this.mPrevButton.setImageAlpha((int) (f * 255.0f));
        if (z) {
            this.mPrevButton.setBackgroundResource(this.mBackgroundBorderlessResId);
            this.mPrevButton.setEnabled(true);
            this.mPrevButton.setFocusable(true);
            return;
        }
        this.mPrevButton.setBackground((Drawable) null);
        this.mPrevButton.setEnabled(false);
        this.mPrevButton.setFocusable(false);
    }

    private void setTotalMonthCountWithLeap() {
        int i2;
        if (this.mSolarLunarTables != null && this.mPathClassLoader != null) {
            this.mTotalMonthCountWithLeap = new int[((getMaxYear() - getMinYear()) + 1)];
            int i7 = 0;
            for (int minYear = getMinYear(); minYear <= getMaxYear(); minYear++) {
                int i8 = 12;
                if (minYear == getMinYear()) {
                    int minMonth = getMinMonth() + 1;
                    int indexOfleapMonthOfYear = getIndexOfleapMonthOfYear(minYear);
                    if (indexOfleapMonthOfYear > 12 || indexOfleapMonthOfYear < minMonth) {
                        i2 = 13 - minMonth;
                    } else {
                        i2 = 14 - minMonth;
                    }
                } else if (minYear == getMaxYear()) {
                    int maxMonth = getMaxMonth();
                    int i10 = maxMonth + 1;
                    int indexOfleapMonthOfYear2 = getIndexOfleapMonthOfYear(minYear);
                    if (indexOfleapMonthOfYear2 > 12 || i10 < indexOfleapMonthOfYear2) {
                        i2 = i10;
                    } else {
                        i2 = maxMonth + 2;
                    }
                } else {
                    if (getIndexOfleapMonthOfYear(minYear) <= 12) {
                        i8 = 13;
                    }
                    i2 = i8;
                }
                i7 += i2;
                this.mTotalMonthCountWithLeap[minYear - getMinYear()] = i7;
            }
        }
    }

    /* access modifiers changed from: private */
    public void startCalendarHeaderSpinnerAnimation() {
        if (this.mCurrentViewType == 0) {
            if (this.mAntiClockwiseAnim.isRunning()) {
                this.mAntiClockwiseAnim.cancel();
            }
            this.mClockwiseAnim.start();
            return;
        }
        if (this.mClockwiseAnim.isRunning()) {
            this.mClockwiseAnim.cancel();
        }
        this.mAntiClockwiseAnim.start();
    }

    /* access modifiers changed from: private */
    public void updateSimpleMonthView(boolean z) {
        int i2;
        int i7;
        int i8 = this.mCurrentDate.get(2);
        int i10 = this.mCurrentDate.get(1);
        if (this.mIsLunar) {
            i10 = this.mLunarCurrentYear;
            i8 = this.mLunarCurrentMonth;
        }
        if (this.mLunarChanged) {
            i8 = this.mTempDate.get(2);
            i10 = this.mTempDate.get(1);
        }
        int minMonth = (i8 - getMinMonth()) + ((i10 - getMinYear()) * 12);
        if (this.mIsLunar) {
            if (i8 < getIndexOfleapMonthOfYear(i10)) {
                i2 = i8;
            } else {
                i2 = i8 + 1;
            }
            if (i10 == getMinYear()) {
                i7 = -getMinMonth();
            } else {
                i7 = getTotalMonthCountWithLeap(i10 - 1);
            }
            minMonth = i7 + i2;
            int i11 = this.mMode;
            if (((i11 == 1 || i11 == 3) && i8 == this.mLunarStartMonth && this.mIsLeapStartMonth == 1) || (((i11 == 2 || i11 == 3) && i8 == this.mLunarEndMonth && this.mIsLeapEndMonth == 1) || (i11 == 0 && this.mIsLeapMonth))) {
                minMonth++;
            }
        }
        this.mCurrentPosition = minMonth;
        if (isSystemAnimationsRemoved()) {
            this.mCalendarViewPager.setCurrentItem(minMonth, false);
        } else {
            this.mCalendarViewPager.setCurrentItem(minMonth, z);
        }
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1000;
        obtainMessage.obj = Boolean.TRUE;
        this.mHandler.sendMessage(obtainMessage);
        Message obtainMessage2 = this.mHandler.obtainMessage();
        obtainMessage2.what = 1001;
        this.mHandler.sendMessage(obtainMessage2);
    }

    /* access modifiers changed from: private */
    public void updateStartEndDateRange(int i2, int i7, int i8, int i10) {
        clearCalendar(this.mStartDate, i7, i8, (i10 - i2) + 1);
        int i11 = 7 - i2;
        clearCalendar(this.mEndDate, i7, i8, i10 + i11);
        if (this.mIsLunar) {
            Calendar convertLunarToSolar = convertLunarToSolar(getCalendarForLocale((Calendar) null, this.mCurrentLocale), i7, i8, i10);
            Calendar calendar = (Calendar) convertLunarToSolar.clone();
            calendar.add(6, (-i2) + 1);
            LunarDate lunarDate = new LunarDate();
            convertSolarToLunar(calendar, lunarDate);
            this.mLunarStartYear = lunarDate.year;
            this.mLunarStartMonth = lunarDate.month;
            this.mLunarStartDay = lunarDate.day;
            this.mIsLeapStartMonth = 0;
            convertLunarToSolar.add(6, i11);
            convertSolarToLunar(convertLunarToSolar, lunarDate);
            this.mLunarEndYear = lunarDate.year;
            this.mLunarEndMonth = lunarDate.month;
            this.mLunarEndDay = lunarDate.day;
            this.mIsLeapEndMonth = 0;
        }
    }

    private void updateViewType(int i2) {
        Activity scanForActivity = scanForActivity(this.mContext);
        boolean z = this.mIsCalendarViewDisabled;
        if (z || this.mIsSpinnerViewDisabled) {
            setCurrentViewType(z ? 1 : 0);
            manageCalendarHeaderLayoutClick(true);
            this.mAnimator.setMeasureAllChildren(false);
        } else if (scanForActivity == null || !scanForActivity.isInMultiWindowMode()) {
            manageCalendarHeaderLayoutClick(false);
        } else if (i2 < this.mDatePickerHeight) {
            setCurrentViewType(1);
            manageCalendarHeaderLayoutClick(true);
        } else {
            manageCalendarHeaderLayoutClick(false);
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.getText().add(getFormattedCurrentDate());
        return true;
    }

    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    public int getCurrentViewType() {
        return this.mCurrentViewType;
    }

    public int getDateMode() {
        return this.mMode;
    }

    public int getDayOfMonth() {
        if (this.mIsLunar) {
            return this.mLunarCurrentDay;
        }
        return this.mCurrentDate.get(5);
    }

    public Calendar getEndDate() {
        return this.mEndDate;
    }

    public int getFirstDayOfWeek() {
        int i2 = this.mFirstDayOfWeek;
        if (i2 != 0) {
            return i2;
        }
        return this.mCurrentDate.getFirstDayOfWeek();
    }

    public int[] getLunarEndDate() {
        return new int[]{this.mLunarEndYear, this.mLunarEndMonth, this.mLunarEndDay, this.mIsLeapEndMonth};
    }

    public int[] getLunarStartDate() {
        return new int[]{this.mLunarStartYear, this.mLunarStartMonth, this.mLunarStartDay, this.mIsLeapStartMonth};
    }

    public long getMaxDate() {
        return this.mMaxDate.getTimeInMillis();
    }

    public int getMaxDay() {
        return this.mMaxDate.get(5);
    }

    public int getMaxMonth() {
        return this.mMaxDate.get(2);
    }

    public int getMaxYear() {
        return this.mMaxDate.get(1);
    }

    public long getMinDate() {
        return this.mMinDate.getTimeInMillis();
    }

    public int getMinDay() {
        return this.mMinDate.get(5);
    }

    public int getMinMonth() {
        return this.mMinDate.get(2);
    }

    public int getMinYear() {
        return this.mMinDate.get(1);
    }

    public int getMonth() {
        if (this.mIsLunar) {
            return this.mLunarCurrentMonth;
        }
        return this.mCurrentDate.get(2);
    }

    public Calendar getStartDate() {
        return this.mStartDate;
    }

    public int getYear() {
        if (this.mIsLunar) {
            return this.mLunarCurrentYear;
        }
        return this.mCurrentDate.get(1);
    }

    public void init(int i2, int i7, int i8, OnDateChangedListener onDateChangedListener) {
        this.mCurrentDate.set(1, i2);
        this.mCurrentDate.set(2, i7);
        this.mCurrentDate.set(5, i8);
        if (this.mIsLunar) {
            this.mLunarCurrentYear = i2;
            this.mLunarCurrentMonth = i7;
            this.mLunarCurrentDay = i8;
        }
        if (this.mCurrentDate.before(this.mMinDate)) {
            this.mCurrentDate = getCalendarForLocale(this.mMinDate, this.mCurrentLocale);
        }
        if (this.mCurrentDate.after(this.mMaxDate)) {
            this.mCurrentDate = getCalendarForLocale(this.mMaxDate, this.mCurrentLocale);
        }
        this.mOnDateChangedListener = onDateChangedListener;
        updateSimpleMonthView(true);
        onDateChanged();
        this.mSpinnerLayout.setMinDate(this.mMinDate.getTimeInMillis());
        this.mSpinnerLayout.setMaxDate(this.mMaxDate.getTimeInMillis());
        if (this.mCurrentViewType == 0) {
            this.mSpinnerLayout.setVisibility(4);
            this.mSpinnerLayout.setEnabled(false);
        }
        clearCalendar(this.mStartDate, i2, i7, i8);
        clearCalendar(this.mEndDate, i2, i7, i8);
        if (this.mIsLunar) {
            this.mLunarStartYear = i2;
            this.mLunarStartMonth = i7;
            this.mLunarStartDay = i8;
            this.mLunarEndYear = i2;
            this.mLunarEndMonth = i7;
            this.mLunarEndDay = i8;
        }
    }

    public boolean isEditTextMode() {
        if (this.mCurrentViewType == 0 || !this.mSpinnerLayout.isEditTextMode()) {
            return false;
        }
        return true;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == androidx.picker.R$id.sesl_date_picker_calendar_header_prev_button) {
            if (this.mIsRTL) {
                if (this.mCurrentPosition != this.mPositionCount - 1) {
                    if (isSystemAnimationsRemoved()) {
                        this.mCalendarViewPager.setCurrentItem(this.mCurrentPosition + 1, false);
                    } else {
                        this.mCalendarViewPager.setCurrentItem(this.mCurrentPosition + 1);
                    }
                }
            } else if (this.mCurrentPosition != 0) {
                if (isSystemAnimationsRemoved()) {
                    this.mCalendarViewPager.setCurrentItem(this.mCurrentPosition - 1, false);
                } else {
                    this.mCalendarViewPager.setCurrentItem(this.mCurrentPosition - 1);
                }
            }
        } else if (id != androidx.picker.R$id.sesl_date_picker_calendar_header_next_button) {
        } else {
            if (this.mIsRTL) {
                if (this.mCurrentPosition != 0) {
                    if (isSystemAnimationsRemoved()) {
                        this.mCalendarViewPager.setCurrentItem(this.mCurrentPosition - 1, false);
                    } else {
                        this.mCalendarViewPager.setCurrentItem(this.mCurrentPosition - 1);
                    }
                }
            } else if (this.mCurrentPosition != this.mPositionCount - 1) {
                if (isSystemAnimationsRemoved()) {
                    this.mCalendarViewPager.setCurrentItem(this.mCurrentPosition + 1, false);
                } else {
                    this.mCalendarViewPager.setCurrentItem(this.mCurrentPosition + 1);
                }
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mIsRTL = isRTL();
        this.mIsFarsiLanguage = isFarsiLanguage();
        Locale locale = configuration.getLocales().get(0);
        if (!this.mCurrentLocale.equals(locale)) {
            this.mCurrentLocale = locale;
            boolean isSimplifiedChinese = isSimplifiedChinese();
            this.mIsSimplifiedChinese = isSimplifiedChinese;
            if (isSimplifiedChinese) {
                this.mDayFormatter = new SimpleDateFormat("EEEEE", locale);
            } else {
                this.mDayFormatter = new SimpleDateFormat("EEE", locale);
            }
        }
        Resources resources = this.mContext.getResources();
        this.mDatePickerLayout.setGravity(1);
        this.mIsFirstMeasure = true;
        this.mCalendarHeaderLayoutHeight = resources.getDimensionPixelOffset(R$dimen.sesl_date_picker_calendar_header_height);
        this.mCalendarViewPagerHeight = resources.getDimensionPixelOffset(R$dimen.sesl_date_picker_calendar_view_height);
        this.mDayOfTheWeekLayoutHeight = resources.getDimensionPixelOffset(R$dimen.sesl_date_picker_calendar_day_height);
        this.mFirstBlankSpaceHeight = resources.getDimensionPixelOffset(R$dimen.sesl_date_picker_gap_between_header_and_weekend);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R$dimen.sesl_date_picker_gap_between_weekend_and_calender);
        this.mSecondBlankSpaceHeight = dimensionPixelOffset;
        this.mDatePickerHeight = this.mCalendarHeaderLayoutHeight + this.mFirstBlankSpaceHeight + this.mDayOfTheWeekLayoutHeight + dimensionPixelOffset + this.mCalendarViewPagerHeight;
        if (this.mIsRTL) {
            this.mIsConfigurationChanged = true;
        }
    }

    public void onDayClick(SeslSimpleMonthView seslSimpleMonthView, int i2, int i7, int i8) {
        boolean z;
        int i10;
        int i11;
        int i12 = i2;
        int i13 = i7;
        int i14 = i8;
        debugLog(C0086a.i(i14, "onDayClick day : "));
        if (!this.mIsCalledFromDeactivatedDayClick) {
            this.mDayOfWeekStart = seslSimpleMonthView.getDayOfWeekStart();
        }
        int i15 = this.mCurrentDate.get(1);
        int i16 = this.mCurrentDate.get(2);
        if (this.mIsLunar) {
            i15 = this.mLunarCurrentYear;
            i16 = this.mLunarCurrentMonth;
        }
        onDayOfMonthSelected(i12, i13, i14);
        if (this.mCurrentPosition != ((i12 - getMinYear()) * 12) + (i13 - getMinMonth())) {
            z = true;
        } else {
            z = false;
        }
        if (!(i12 == i15 && i13 == i16 && i14 == this.mOldSelectedDay && !this.mIsLunar && !z)) {
            this.mOldSelectedDay = i14;
            this.mCalendarPagerAdapter.notifyDataSetChanged();
        }
        if (getMinMonth() == i13 && getMinYear() == i12) {
            i10 = getMinDay();
        } else {
            i10 = 1;
        }
        if (getMaxMonth() == i13 && getMaxYear() == i12) {
            i11 = getMaxDay();
        } else {
            i11 = 31;
        }
        if (this.mIsLunarSupported) {
            seslSimpleMonthView.setLunar(this.mIsLunar, this.mIsLeapMonth, this.mPathClassLoader);
        } else {
            SeslSimpleMonthView seslSimpleMonthView2 = seslSimpleMonthView;
        }
        int i17 = this.mStartDate.get(1);
        int i18 = this.mStartDate.get(2);
        int i19 = this.mStartDate.get(5);
        int i20 = this.mEndDate.get(1);
        int i21 = this.mEndDate.get(2);
        int i22 = this.mEndDate.get(5);
        if (this.mIsLunar) {
            i17 = this.mLunarStartYear;
            i18 = this.mLunarStartMonth;
            i19 = this.mLunarStartDay;
            i20 = this.mLunarEndYear;
            i21 = this.mLunarEndMonth;
            i22 = this.mLunarEndDay;
        }
        int i23 = i19;
        int i24 = i22;
        int i25 = i18;
        int firstDayOfWeek = getFirstDayOfWeek();
        int i26 = i17;
        Calendar calendar = this.mMinDate;
        Calendar calendar2 = this.mMaxDate;
        int i27 = this.mIsLeapStartMonth;
        seslSimpleMonthView.setMonthParams(i14, i13, i12, firstDayOfWeek, i10, i11, calendar, calendar2, i26, i25, i23, i27, i20, i21, i24, this.mIsLeapEndMonth, this.mMode);
        seslSimpleMonthView.invalidate();
        this.mIsCalledFromDeactivatedDayClick = false;
    }

    public void onDayOfMonthSelected(int i2, int i7, int i8) {
        this.mCurrentDate.set(1, i2);
        this.mCurrentDate.set(2, i7);
        this.mCurrentDate.set(5, i8);
        if (this.mIsLunar) {
            this.mLunarCurrentYear = i2;
            this.mLunarCurrentMonth = i7;
            this.mLunarCurrentDay = i8;
        }
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 1000;
        this.mHandler.sendMessage(obtainMessage);
        int i10 = this.mMode;
        if (i10 == 1) {
            if (this.mStartDate.compareTo(this.mEndDate) == 0 || this.mCurrentDate.compareTo(this.mEndDate) >= 0) {
                clearCalendar(this.mEndDate, i2, i7, i8);
            }
            clearCalendar(this.mStartDate, i2, i7, i8);
            if (this.mIsLunar) {
                if (this.mStartDate.compareTo(this.mEndDate) == 0 || this.mCurrentDate.compareTo(this.mEndDate) >= 0) {
                    this.mLunarEndYear = i2;
                    this.mLunarEndMonth = i7;
                    this.mLunarEndDay = i8;
                    this.mIsLeapEndMonth = this.mIsLeapMonth ? 1 : 0;
                }
                this.mLunarStartYear = i2;
                this.mLunarStartMonth = i7;
                this.mLunarStartDay = i8;
                this.mIsLeapStartMonth = this.mIsLeapMonth ? 1 : 0;
            }
        } else if (i10 == 2) {
            if (this.mCurrentDate.compareTo(this.mStartDate) < 0) {
                clearCalendar(this.mStartDate, i2, i7, i8);
            }
            clearCalendar(this.mEndDate, i2, i7, i8);
            if (this.mIsLunar) {
                if (this.mCurrentDate.compareTo(this.mStartDate) < 0) {
                    this.mLunarStartYear = i2;
                    this.mLunarStartMonth = i7;
                    this.mLunarStartDay = i8;
                    this.mIsLeapStartMonth = this.mIsLeapMonth ? 1 : 0;
                }
                this.mLunarEndYear = i2;
                this.mLunarEndMonth = i7;
                this.mLunarEndDay = i8;
                this.mIsLeapEndMonth = this.mIsLeapMonth ? 1 : 0;
            }
        } else if (i10 != 3) {
            clearCalendar(this.mStartDate, i2, i7, i8);
            clearCalendar(this.mEndDate, i2, i7, i8);
            if (this.mIsLunar) {
                this.mLunarStartYear = i2;
                this.mLunarStartMonth = i7;
                this.mLunarStartDay = i8;
                boolean z = this.mIsLeapMonth;
                this.mIsLeapStartMonth = z ? 1 : 0;
                this.mLunarEndYear = i2;
                this.mLunarEndMonth = i7;
                this.mLunarEndDay = i8;
                this.mIsLeapEndMonth = z;
            }
        } else {
            this.mIsWeekRangeSet = true;
            int i11 = 7;
            int i12 = (((i8 % 7) + this.mDayOfWeekStart) - 1) % 7;
            if (i12 != 0) {
                i11 = i12;
            }
            updateStartEndDateRange(i11, i2, i7, i8);
        }
        if (this.mMode != 0) {
            onValidationChanged(!this.mStartDate.after(this.mEndDate));
        }
        onDateChanged();
    }

    public void onDeactivatedDayClick(SeslSimpleMonthView seslSimpleMonthView, int i2, int i7, int i8, boolean z, boolean z3) {
        int i10;
        int i11;
        int i12;
        int i13 = 1;
        this.mIsCalledFromDeactivatedDayClick = true;
        if (this.mIsLunar) {
            int i14 = this.mCurrentPosition;
            if (z3) {
                i11 = i14 - 1;
            } else {
                i11 = i14 + 1;
            }
            LunarDate lunarDateByPosition = getLunarDateByPosition(i11);
            int i15 = lunarDateByPosition.year;
            int i16 = lunarDateByPosition.month;
            this.mIsLeapMonth = lunarDateByPosition.isLeapMonth;
            int i17 = this.mCurrentPosition;
            if (z3) {
                i12 = i17 - 1;
            } else {
                i12 = i17 + 1;
            }
            this.mCurrentPosition = i12;
            this.mCalendarViewPager.setCurrentItem(i12);
            SeslSimpleMonthView seslSimpleMonthView2 = this.mCalendarPagerAdapter.views.get(this.mCurrentPosition);
            if (seslSimpleMonthView2 != null) {
                i13 = seslSimpleMonthView2.getDayOfWeekStart();
            }
            this.mDayOfWeekStart = i13;
            onDayClick(seslSimpleMonthView, i15, i16, i8);
            return;
        }
        SeslSimpleMonthView seslSimpleMonthView3 = this.mCalendarPagerAdapter.views.get((i7 - getMinMonth()) + ((i2 - getMinYear()) * 12));
        if (seslSimpleMonthView3 == null) {
            i10 = 1;
        } else {
            i10 = seslSimpleMonthView3.getDayOfWeekStart();
        }
        this.mDayOfWeekStart = i10;
        onDayClick(seslSimpleMonthView, i2, i7, i8);
        updateSimpleMonthView(true);
    }

    public void onDetachedFromWindow() {
        removeAllCallbacks();
        super.onDetachedFromWindow();
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        super.onLayout(z, i2, i7, i8, i10);
        calculateContentHeight();
    }

    public boolean onLongClick(View view) {
        int id = view.getId();
        if (id == androidx.picker.R$id.sesl_date_picker_calendar_header_prev_button && this.mCurrentPosition != 0) {
            postChangeCurrentByOneFromLongPress(false, (long) ViewConfiguration.getLongPressTimeout());
        } else if (id == androidx.picker.R$id.sesl_date_picker_calendar_header_next_button && this.mCurrentPosition != this.mPositionCount - 1) {
            postChangeCurrentByOneFromLongPress(true, (long) ViewConfiguration.getLongPressTimeout());
        }
        return false;
    }

    public void onMeasure(int i2, int i7) {
        this.mMeasureSpecHeight = View.MeasureSpec.getSize(i7);
        int makeMeasureSpec = makeMeasureSpec(i2, this.mCalendarViewPagerWidth);
        if (this.mIsFirstMeasure || this.mOldCalendarViewPagerWidth != this.mCalendarViewPagerWidth) {
            this.mIsFirstMeasure = false;
            this.mOldCalendarViewPagerWidth = this.mCalendarViewPagerWidth;
            RelativeLayout relativeLayout = this.mCustomButtonLayout;
            if (relativeLayout != null) {
                relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, this.mCalendarHeaderLayoutHeight));
            }
            this.mCalendarHeaderLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            this.mDayOfTheWeekLayout.setLayoutParams(new LinearLayout.LayoutParams(this.mDayOfTheWeekLayoutWidth, this.mDayOfTheWeekLayoutHeight));
            this.mDayOfTheWeekView.setLayoutParams(new LinearLayout.LayoutParams(this.mDayOfTheWeekLayoutWidth, this.mDayOfTheWeekLayoutHeight));
            this.mCalendarViewPager.setLayoutParams(new LinearLayout.LayoutParams(this.mCalendarViewPagerWidth, this.mCalendarViewPagerHeight));
            if (this.mIsRTL && this.mIsConfigurationChanged) {
                this.mCalendarViewPager.seslSetConfigurationChanged(true);
            }
            this.mFirstBlankSpace.setLayoutParams(new LinearLayout.LayoutParams(-1, this.mFirstBlankSpaceHeight));
            this.mSecondBlankSpace.setLayoutParams(new LinearLayout.LayoutParams(-1, this.mSecondBlankSpaceHeight));
            super.onMeasure(makeMeasureSpec, i7);
            return;
        }
        super.onMeasure(makeMeasureSpec, i7);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(((View.BaseSavedState) parcelable).getSuperState());
        SavedState savedState = (SavedState) parcelable;
        this.mCurrentDate.set(savedState.getSelectedYear(), savedState.getSelectedMonth(), savedState.getSelectedDay());
        if (this.mIsLunar) {
            this.mLunarCurrentYear = savedState.getSelectedYear();
            this.mLunarCurrentMonth = savedState.getSelectedMonth();
            this.mLunarCurrentDay = savedState.getSelectedDay();
        }
        this.mMinDate.setTimeInMillis(savedState.getMinDate());
        this.mMaxDate.setTimeInMillis(savedState.getMaxDate());
    }

    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        int i2 = this.mCurrentDate.get(1);
        int i7 = this.mCurrentDate.get(2);
        int i8 = this.mCurrentDate.get(5);
        if (this.mIsLunar) {
            i2 = this.mLunarCurrentYear;
            i7 = this.mLunarCurrentMonth;
            i8 = this.mLunarCurrentDay;
        }
        int i10 = i8;
        return new SavedState(onSaveInstanceState, i2, i7, i10, this.mMinDate.getTimeInMillis(), this.mMaxDate.getTimeInMillis());
    }

    public void onSizeChanged(int i2, int i7, int i8, int i10) {
        super.onSizeChanged(i2, i7, i8, i10);
    }

    public void removeAllCallbacks() {
        ChangeCurrentByOneFromLongPressCommand changeCurrentByOneFromLongPressCommand = this.mChangeCurrentByOneFromLongPressCommand;
        if (changeCurrentByOneFromLongPressCommand != null) {
            removeCallbacks(changeCurrentByOneFromLongPressCommand);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    SeslDatePicker.this.mCalendarViewPager.setCurrentItem(SeslDatePicker.this.mCurrentPosition, false);
                }
            }, 200);
        }
    }

    public void requestLayout() {
        super.requestLayout();
        SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout = this.mSpinnerLayout;
        if (seslDatePickerSpinnerLayout != null && seslDatePickerSpinnerLayout.getVisibility() == 0) {
            this.mSpinnerLayout.requestLayout();
        }
    }

    public void setCurrentViewType(int i2) {
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        if (i2 != 0) {
            if (i2 == 1) {
                if (this.mCurrentViewType != i2) {
                    this.mCalendarHeaderSpinner.setRotation(-180.0f);
                    int i13 = this.mMode;
                    if (i13 != 1) {
                        if (i13 != 2) {
                            if (this.mIsLunar) {
                                i10 = this.mLunarCurrentYear;
                                i8 = this.mLunarCurrentMonth;
                                i7 = this.mLunarCurrentDay;
                                this.mSpinnerLayout.updateDate(i10, i8, i7);
                                this.mAnimator.setDisplayedChild(1);
                                this.mSpinnerLayout.setEnabled(true);
                                this.mCurrentViewType = i2;
                                Message obtainMessage = this.mHandler.obtainMessage();
                                obtainMessage.what = 1000;
                                this.mHandler.sendMessage(obtainMessage);
                            } else {
                                i10 = this.mCurrentDate.get(1);
                                i11 = this.mCurrentDate.get(2);
                                i12 = this.mCurrentDate.get(5);
                            }
                        } else if (this.mIsLunar) {
                            i10 = this.mLunarEndYear;
                            i8 = this.mLunarEndMonth;
                            i7 = this.mLunarEndDay;
                            this.mSpinnerLayout.updateDate(i10, i8, i7);
                            this.mAnimator.setDisplayedChild(1);
                            this.mSpinnerLayout.setEnabled(true);
                            this.mCurrentViewType = i2;
                            Message obtainMessage2 = this.mHandler.obtainMessage();
                            obtainMessage2.what = 1000;
                            this.mHandler.sendMessage(obtainMessage2);
                        } else {
                            i10 = this.mEndDate.get(1);
                            i11 = this.mEndDate.get(2);
                            i12 = this.mEndDate.get(5);
                        }
                    } else if (this.mIsLunar) {
                        i10 = this.mLunarStartYear;
                        i8 = this.mLunarStartMonth;
                        i7 = this.mLunarStartDay;
                        this.mSpinnerLayout.updateDate(i10, i8, i7);
                        this.mAnimator.setDisplayedChild(1);
                        this.mSpinnerLayout.setEnabled(true);
                        this.mCurrentViewType = i2;
                        Message obtainMessage22 = this.mHandler.obtainMessage();
                        obtainMessage22.what = 1000;
                        this.mHandler.sendMessage(obtainMessage22);
                    } else {
                        i10 = this.mStartDate.get(1);
                        i11 = this.mStartDate.get(2);
                        i12 = this.mStartDate.get(5);
                    }
                    int i14 = i11;
                    i7 = i12;
                    i8 = i14;
                    this.mSpinnerLayout.updateDate(i10, i8, i7);
                    this.mAnimator.setDisplayedChild(1);
                    this.mSpinnerLayout.setEnabled(true);
                    this.mCurrentViewType = i2;
                    Message obtainMessage222 = this.mHandler.obtainMessage();
                    obtainMessage222.what = 1000;
                    this.mHandler.sendMessage(obtainMessage222);
                }
            } else {
                return;
            }
        } else if (this.mCurrentViewType != i2) {
            this.mSpinnerLayout.updateInputState();
            this.mSpinnerLayout.setEditTextMode(false);
            this.mAnimator.setDisplayedChild(0);
            this.mSpinnerLayout.setVisibility(4);
            this.mSpinnerLayout.setEnabled(false);
            this.mCurrentViewType = i2;
            Message obtainMessage3 = this.mHandler.obtainMessage();
            obtainMessage3.what = 1000;
            this.mHandler.sendMessage(obtainMessage3);
            this.mCalendarPagerAdapter.notifyDataSetChanged();
        }
        Message obtainMessage4 = this.mHandler.obtainMessage();
        obtainMessage4.what = 1001;
        this.mHandler.sendMessage(obtainMessage4);
    }

    public void setDateMode(int i2) {
        int i7;
        int i8;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25 = i2;
        this.mMode = i25;
        this.mIsWeekRangeSet = false;
        if (i25 == 1) {
            if (this.mIsLunar) {
                i21 = this.mLunarStartYear;
                i20 = this.mLunarStartMonth;
                i19 = this.mLunarStartDay;
            } else {
                i21 = this.mStartDate.get(1);
                i20 = this.mStartDate.get(2);
                i19 = this.mStartDate.get(5);
            }
            this.mSpinnerLayout.updateDate(i21, i20, i19);
        } else if (i25 == 2) {
            if (this.mIsLunar) {
                i24 = this.mLunarEndYear;
                i23 = this.mLunarEndMonth;
                i22 = this.mLunarEndDay;
            } else {
                i24 = this.mEndDate.get(1);
                i23 = this.mEndDate.get(2);
                i22 = this.mEndDate.get(5);
            }
            this.mSpinnerLayout.updateDate(i24, i23, i22);
        }
        if (this.mCurrentViewType == 1) {
            this.mSpinnerLayout.setVisibility(0);
            this.mSpinnerLayout.setEnabled(true);
        }
        SeslSimpleMonthView seslSimpleMonthView = this.mCalendarPagerAdapter.views.get(this.mCurrentPosition);
        if (seslSimpleMonthView != null) {
            if (this.mIsLunar) {
                i7 = this.mLunarCurrentYear;
                i8 = this.mLunarCurrentMonth;
                i10 = this.mLunarCurrentDay;
            } else {
                i7 = this.mCurrentDate.get(1);
                i8 = this.mCurrentDate.get(2);
                i10 = this.mCurrentDate.get(5);
            }
            int i26 = i10;
            int i27 = i8;
            int i28 = i26;
            int i29 = i7;
            if (getMinMonth() == i27 && getMinYear() == i29) {
                i11 = getMinDay();
            } else {
                i11 = 1;
            }
            if (getMaxMonth() == i27 && getMaxYear() == i29) {
                i12 = getMaxDay();
            } else {
                i12 = 31;
            }
            int i30 = i12;
            if (this.mIsLunar) {
                i13 = this.mLunarStartYear;
                int i31 = this.mLunarStartMonth;
                int i32 = this.mLunarStartDay;
                i14 = this.mLunarEndYear;
                i17 = i31;
                i18 = i32;
                i16 = this.mLunarEndMonth;
                i15 = this.mLunarEndDay;
            } else {
                i13 = this.mStartDate.get(1);
                int i33 = this.mStartDate.get(2);
                int i34 = this.mStartDate.get(5);
                i14 = this.mEndDate.get(1);
                int i35 = this.mEndDate.get(2);
                i15 = this.mEndDate.get(5);
                i16 = i35;
                i17 = i33;
                i18 = i34;
            }
            seslSimpleMonthView.setMonthParams(i28, i27, i29, getFirstDayOfWeek(), i11, i30, this.mMinDate, this.mMaxDate, i13, i17, i18, this.mIsLeapStartMonth, i14, i16, i15, this.mIsLeapEndMonth, this.mMode);
            seslSimpleMonthView.invalidate();
        }
        if (this.mIsLunar) {
            updateSimpleMonthView(false);
        }
        this.mCalendarPagerAdapter.notifyDataSetChanged();
    }

    public void setDialogPaddingVertical(int i2) {
        this.mDialogPaddingVertical = i2;
    }

    public void setDialogWindow(Window window) {
        if (window != null) {
            this.mDialogWindow = window;
        }
    }

    public void setEditTextMode(boolean z) {
        if (this.mCurrentViewType != 0) {
            this.mSpinnerLayout.setEditTextMode(z);
        }
    }

    public void setEnabled(boolean z) {
        if (isEnabled() != z) {
            super.setEnabled(z);
            this.mIsEnabled = z;
        }
    }

    public void setFirstDayOfWeek(int i2) {
        if (i2 < 1 || i2 > 7) {
            throw new IllegalArgumentException("firstDayOfWeek must be between 1 and 7");
        }
        this.mFirstDayOfWeek = i2;
    }

    public void setMaxDate(long j2) {
        this.mTempMinMaxDate.setTimeInMillis(j2);
        if (this.mTempMinMaxDate.get(1) != this.mMaxDate.get(1) || this.mTempMinMaxDate.get(6) == this.mMaxDate.get(6)) {
            if (this.mIsLunar) {
                setTotalMonthCountWithLeap();
            }
            if (this.mCurrentDate.after(this.mTempMinMaxDate)) {
                this.mCurrentDate.setTimeInMillis(j2);
                onDateChanged();
            }
            this.mMaxDate.setTimeInMillis(j2);
            this.mSpinnerLayout.setMaxDate(this.mMaxDate.getTimeInMillis());
            this.mCalendarPagerAdapter.notifyDataSetChanged();
            updateSimpleMonthView(false);
        }
    }

    public void setMinDate(long j2) {
        this.mTempMinMaxDate.setTimeInMillis(j2);
        if (this.mTempMinMaxDate.get(1) != this.mMinDate.get(1) || this.mTempMinMaxDate.get(6) == this.mMinDate.get(6)) {
            if (this.mIsLunar) {
                setTotalMonthCountWithLeap();
            }
            if (this.mCurrentDate.before(this.mTempMinMaxDate)) {
                this.mCurrentDate.setTimeInMillis(j2);
                onDateChanged();
            }
            this.mMinDate.setTimeInMillis(j2);
            this.mSpinnerLayout.setMinDate(this.mMinDate.getTimeInMillis());
            this.mCalendarPagerAdapter.notifyDataSetChanged();
            updateSimpleMonthView(false);
        }
    }

    public void setOnEditTextModeChangedListener(OnEditTextModeChangedListener onEditTextModeChangedListener) {
        this.mSpinnerLayout.setOnEditTextModeChangedListener(this, onEditTextModeChangedListener);
    }

    public void setSeparateLunarButton(boolean z) {
        if (this.mIsCustomButtonSeparate != z) {
            if (z) {
                removeCustomButtonInHeader();
                addCustomButtonSeparateLayout();
            } else {
                removeCustomButtonSeparateLayout();
                addCustomButtonInHeader();
            }
            this.mIsCustomButtonSeparate = z;
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
        private final long mMaxDate;
        private final long mMinDate;
        private final int mSelectedDay;
        private final int mSelectedMonth;
        private final int mSelectedYear;

        public long getMaxDate() {
            return this.mMaxDate;
        }

        public long getMinDate() {
            return this.mMinDate;
        }

        public int getSelectedDay() {
            return this.mSelectedDay;
        }

        public int getSelectedMonth() {
            return this.mSelectedMonth;
        }

        public int getSelectedYear() {
            return this.mSelectedYear;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.mSelectedYear);
            parcel.writeInt(this.mSelectedMonth);
            parcel.writeInt(this.mSelectedDay);
            parcel.writeLong(this.mMinDate);
            parcel.writeLong(this.mMaxDate);
        }

        private SavedState(Parcelable parcelable, int i2, int i7, int i8, long j2, long j3) {
            super(parcelable);
            this.mSelectedYear = i2;
            this.mSelectedMonth = i7;
            this.mSelectedDay = i8;
            this.mMinDate = j2;
            this.mMaxDate = j3;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.mSelectedYear = parcel.readInt();
            this.mSelectedMonth = parcel.readInt();
            this.mSelectedDay = parcel.readInt();
            this.mMinDate = parcel.readLong();
            this.mMaxDate = parcel.readLong();
        }
    }

    public SeslDatePicker(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    /* JADX INFO: finally extract failed */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SeslDatePicker(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        int i8 = i2;
        int i10 = i7;
        this.mIsConfigurationChanged = false;
        this.mIsFirstMeasure = true;
        this.mIsEnabled = true;
        this.mIsCalendarViewDisabled = false;
        this.mIsSpinnerViewDisabled = false;
        this.mCurrentViewType = -1;
        this.mOldSelectedDay = -1;
        this.mPadding = 0;
        this.mBackgroundBorderlessResId = -1;
        this.mMode = 0;
        this.mFirstDayOfWeek = 0;
        this.mMonthViewColor = null;
        this.mIsLunarSupported = false;
        this.mIsLunar = false;
        this.mIsLeapMonth = false;
        this.mIsFromSetLunar = false;
        this.mLunarChanged = false;
        this.mIsCustomButtonSeparate = false;
        this.mPathClassLoader = null;
        AnonymousClass1 r82 = new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    SeslDatePicker.this.removeAllCallbacks();
                }
            }
        };
        this.mBtnFocusChangeListener = r82;
        this.mHandler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                int i2;
                super.handleMessage(message);
                int i7 = message.what;
                if (i7 != 1000) {
                    if (i7 == 1001) {
                        SeslDatePicker seslDatePicker = SeslDatePicker.this;
                        if (seslDatePicker.mCurrentViewType == 1) {
                            seslDatePicker.setPrevButtonProperties(0.0f, false);
                            SeslDatePicker.this.setNextButtonProperties(0.0f, false);
                            SeslDatePicker.this.mPrevButton.setImportantForAccessibility(2);
                            SeslDatePicker.this.mNextButton.setImportantForAccessibility(2);
                            SeslDatePicker.this.mCalendarHeaderText.sendAccessibilityEvent(8);
                            return;
                        }
                        int field_TYPE_NONE = SeslHoverPopupWindowReflector.getField_TYPE_NONE();
                        if (field_TYPE_NONE != -1) {
                            SeslViewReflector.semSetHoverPopupType(SeslDatePicker.this.mPrevButton, field_TYPE_NONE);
                            SeslViewReflector.semSetHoverPopupType(SeslDatePicker.this.mNextButton, field_TYPE_NONE);
                        }
                        TooltipCompat.setTooltipText(SeslDatePicker.this.mPrevButton, SeslDatePicker.this.getResources().getString(R$string.sesl_date_picker_decrement_month));
                        TooltipCompat.setTooltipText(SeslDatePicker.this.mNextButton, SeslDatePicker.this.getResources().getString(R$string.sesl_date_picker_increment_month));
                        SeslDatePicker.this.mPrevButton.setImportantForAccessibility(1);
                        SeslDatePicker.this.mNextButton.setImportantForAccessibility(1);
                        SeslDatePicker seslDatePicker2 = SeslDatePicker.this;
                        int i8 = seslDatePicker2.mCurrentPosition;
                        if (i8 <= 0 || i8 >= seslDatePicker2.mPositionCount - 1) {
                            int i10 = seslDatePicker2.mPositionCount;
                            if (i10 == 1) {
                                seslDatePicker2.setPrevButtonProperties(0.4f, false);
                                SeslDatePicker.this.setNextButtonProperties(0.4f, false);
                                SeslDatePicker.this.removeAllCallbacks();
                            } else if (i8 == 0) {
                                seslDatePicker2.setPrevButtonProperties(0.4f, false);
                                SeslDatePicker.this.setNextButtonProperties(1.0f, true);
                                SeslDatePicker.this.removeAllCallbacks();
                            } else if (i8 == i10 - 1) {
                                seslDatePicker2.setPrevButtonProperties(1.0f, true);
                                SeslDatePicker.this.setNextButtonProperties(0.4f, false);
                                SeslDatePicker.this.removeAllCallbacks();
                            }
                        } else {
                            seslDatePicker2.setPrevButtonProperties(1.0f, true);
                            SeslDatePicker.this.setNextButtonProperties(1.0f, true);
                        }
                    }
                } else if (SeslDatePicker.this.mTempDate.get(1) <= SeslDatePicker.this.getMaxYear() && SeslDatePicker.this.mTempDate.get(1) >= SeslDatePicker.this.getMinYear()) {
                    SeslDatePicker seslDatePicker3 = SeslDatePicker.this;
                    String access$000 = seslDatePicker3.getMonthAndYearString(seslDatePicker3.mTempDate);
                    SeslDatePicker.this.mCalendarHeaderText.setText(access$000);
                    SeslDatePicker seslDatePicker4 = SeslDatePicker.this;
                    String access$0002 = seslDatePicker4.getMonthAndYearString(seslDatePicker4.mTempDate);
                    SeslDatePicker seslDatePicker5 = SeslDatePicker.this;
                    if (!access$0002.equals(seslDatePicker5.getMonthAndYearString(seslDatePicker5.mCurrentDate))) {
                        SeslDatePicker.this.mCalendarViewPager.announceForAccessibility(SeslDatePicker.this.mCalendarHeaderText.getText());
                    }
                    Context access$300 = SeslDatePicker.this.mContext;
                    if (SeslDatePicker.this.mCurrentViewType == 0) {
                        i2 = R$string.sesl_date_picker_switch_to_month_day_year_view_description;
                    } else {
                        i2 = R$string.sesl_date_picker_switch_to_calendar_description;
                    }
                    String string = access$300.getString(i2);
                    DateValidator unused = SeslDatePicker.this.getClass();
                    TextView access$100 = SeslDatePicker.this.mCalendarHeaderText;
                    access$100.setContentDescription(access$000 + ArcCommonLog.TAG_COMMA + string);
                }
            }
        };
        AnonymousClass3 r9 = new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 1 && motionEvent.getAction() != 3) {
                    return false;
                }
                SeslDatePicker.this.removeAllCallbacks();
                return false;
            }
        };
        this.mMonthBtnTouchListener = r9;
        AnonymousClass4 r10 = new View.OnKeyListener() {
            public boolean onKey(View view, int i2, KeyEvent keyEvent) {
                if (SeslDatePicker.this.mIsRTL) {
                    boolean unused = SeslDatePicker.this.mIsConfigurationChanged = false;
                }
                if (keyEvent.getAction() == 1 || keyEvent.getAction() == 3) {
                    SeslDatePicker.this.removeAllCallbacks();
                }
                return false;
            }
        };
        this.mMonthBtnKeyListener = r10;
        AnonymousClass5 r11 = new View.OnClickListener() {
            public void onClick(View view) {
                SeslDatePicker seslDatePicker = SeslDatePicker.this;
                seslDatePicker.setCurrentViewType((seslDatePicker.mCurrentViewType + 1) % 2);
                SeslDatePicker.this.startCalendarHeaderSpinnerAnimation();
            }
        };
        this.mCalendarHeaderClickListener = r11;
        this.mContext = context2;
        this.mCurrentLocale = Locale.getDefault();
        this.mIsRTL = isRTL();
        this.mIsFarsiLanguage = isFarsiLanguage();
        boolean isSimplifiedChinese = isSimplifiedChinese();
        this.mIsSimplifiedChinese = isSimplifiedChinese;
        if (isSimplifiedChinese) {
            this.mDayFormatter = new SimpleDateFormat("EEEEE", this.mCurrentLocale);
        } else {
            this.mDayFormatter = new SimpleDateFormat("EEE", this.mCurrentLocale);
        }
        this.mMinDate = getCalendarForLocale(this.mMinDate, this.mCurrentLocale);
        Calendar calendarForLocale = getCalendarForLocale(this.mMaxDate, this.mCurrentLocale);
        this.mMaxDate = calendarForLocale;
        this.mTempMinMaxDate = getCalendarForLocale(calendarForLocale, this.mCurrentLocale);
        Calendar calendarForLocale2 = getCalendarForLocale(this.mCurrentDate, this.mCurrentLocale);
        this.mCurrentDate = calendarForLocale2;
        this.mTempDate = getCalendarForLocale(calendarForLocale2, this.mCurrentLocale);
        Resources resources = getResources();
        int[] iArr = R$styleable.DatePicker;
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet2, iArr, i8, i10);
        AnonymousClass1 r16 = r82;
        this.mMinDate.set(obtainStyledAttributes.getInt(R$styleable.DatePicker_android_startYear, 1902), 0, 1);
        this.mMaxDate.set(obtainStyledAttributes.getInt(R$styleable.DatePicker_android_endYear, 2100), 11, 31);
        ((LayoutInflater) context2.getSystemService("layout_inflater")).inflate(R$layout.sesl_date_picker, this, true);
        int i11 = obtainStyledAttributes.getInt(R$styleable.DatePicker_android_firstDayOfWeek, 0);
        if (i11 != 0) {
            setFirstDayOfWeek(i11);
        }
        obtainStyledAttributes.recycle();
        this.mMonthViewColor = getMonthViewColorStringForSpecific();
        TypedArray obtainStyledAttributes2 = context2.obtainStyledAttributes(attributeSet2, iArr, i8, i10);
        try {
            DayOfTheWeekView dayOfTheWeekView = new DayOfTheWeekView(context2, obtainStyledAttributes2);
            this.mDayOfTheWeekView = dayOfTheWeekView;
            int color = obtainStyledAttributes2.getColor(R$styleable.DatePicker_headerTextColor, resources.getColor(R$color.sesl_date_picker_header_text_color_light));
            this.mCalendarHeaderTextColor = color;
            int color2 = obtainStyledAttributes2.getColor(R$styleable.DatePicker_buttonTintColor, resources.getColor(R$color.sesl_date_picker_button_tint_color_light));
            this.mBtnTintColor = color2;
            obtainStyledAttributes2.recycle();
            CalendarPagerAdapter calendarPagerAdapter = new CalendarPagerAdapter();
            this.mCalendarPagerAdapter = calendarPagerAdapter;
            ViewPager viewPager = (ViewPager) findViewById(androidx.picker.R$id.sesl_date_picker_calendar);
            this.mCalendarViewPager = viewPager;
            viewPager.setAdapter(calendarPagerAdapter);
            viewPager.setOnPageChangeListener(new CalendarPageChangeListener());
            viewPager.seslSetSupportedMouseWheelEvent(true);
            viewPager.canSupportLayoutDirectionForDatePicker(true);
            this.mPadding = resources.getDimensionPixelOffset(R$dimen.sesl_date_picker_calendar_view_padding);
            this.mCalendarHeader = (RelativeLayout) findViewById(androidx.picker.R$id.sesl_date_picker_calendar_header);
            LinearLayout linearLayout = (LinearLayout) findViewById(androidx.picker.R$id.sesl_date_picker_calendar_header_text_spinner_layout);
            this.mCalendarHeaderTextSpinnerLayout = linearLayout;
            View findViewById = findViewById(androidx.picker.R$id.sesl_date_picker_calendar_header_spinner);
            this.mCalendarHeaderSpinner = findViewById;
            int i12 = androidx.picker.R$id.sesl_date_picker_calendar_header_text;
            TextView textView = (TextView) findViewById(i12);
            this.mCalendarHeaderText = textView;
            textView.setTextColor(color);
            this.mStartDate = getCalendarForLocale(this.mCurrentDate, this.mCurrentLocale);
            this.mEndDate = getCalendarForLocale(this.mCurrentDate, this.mCurrentLocale);
            this.mAnimator = (ViewAnimator) findViewById(androidx.picker.R$id.sesl_date_picker_view_animator);
            SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout = (SeslDatePickerSpinnerLayout) findViewById(androidx.picker.R$id.sesl_date_picker_spinner_view);
            this.mSpinnerLayout = seslDatePickerSpinnerLayout;
            seslDatePickerSpinnerLayout.setOnSpinnerDateChangedListener(this, new SeslDatePickerSpinnerLayout.OnSpinnerDateChangedListener() {
                /* JADX WARNING: Code restructure failed: missing block: B:21:0x00a2, code lost:
                    if (r5.mCurrentDate.compareTo(r5.mEndDate) > 0) goto L_0x00a4;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:28:0x00cc, code lost:
                    if (r5.mCurrentDate.compareTo(r5.mEndDate) > 0) goto L_0x00ce;
                 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onDateChanged(androidx.picker.widget.SeslDatePickerSpinnerLayout r5, int r6, int r7, int r8) {
                    /*
                        r4 = this;
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        java.util.Calendar r5 = r5.mCurrentDate
                        r0 = 1
                        r5.set(r0, r6)
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        java.util.Calendar r5 = r5.mCurrentDate
                        r1 = 2
                        r5.set(r1, r7)
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        java.util.Calendar r5 = r5.mCurrentDate
                        r2 = 5
                        r5.set(r2, r8)
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        boolean r2 = r5.mIsLunar
                        if (r2 == 0) goto L_0x0024
                        r5.mLunarCurrentYear = r6
                        r5.mLunarCurrentMonth = r7
                        r5.mLunarCurrentDay = r8
                    L_0x0024:
                        int r2 = r5.mMode
                        r3 = 0
                        if (r2 == r0) goto L_0x008e
                        if (r2 == r1) goto L_0x0051
                        java.util.Calendar r1 = r5.mStartDate
                        r5.clearCalendar(r1, r6, r7, r8)
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        java.util.Calendar r1 = r5.mEndDate
                        r5.clearCalendar(r1, r6, r7, r8)
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        boolean r1 = r5.mIsLunar
                        if (r1 == 0) goto L_0x00e2
                        r5.mLunarStartYear = r6
                        r5.mLunarStartMonth = r7
                        r5.mLunarStartDay = r8
                        r5.mIsLeapStartMonth = r3
                        r5.mLunarEndYear = r6
                        r5.mLunarEndMonth = r7
                        r5.mLunarEndDay = r8
                        r5.mIsLeapEndMonth = r3
                        r5.mIsLeapMonth = r3
                        goto L_0x00e2
                    L_0x0051:
                        java.util.Calendar r1 = r5.mCurrentDate
                        java.util.Calendar r5 = r5.mStartDate
                        int r5 = r1.compareTo(r5)
                        if (r5 >= 0) goto L_0x0062
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        java.util.Calendar r1 = r5.mStartDate
                        r5.clearCalendar(r1, r6, r7, r8)
                    L_0x0062:
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        java.util.Calendar r1 = r5.mEndDate
                        r5.clearCalendar(r1, r6, r7, r8)
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        boolean r1 = r5.mIsLunar
                        if (r1 == 0) goto L_0x00e2
                        java.util.Calendar r1 = r5.mCurrentDate
                        java.util.Calendar r5 = r5.mStartDate
                        int r5 = r1.compareTo(r5)
                        if (r5 >= 0) goto L_0x0083
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        r5.mLunarStartYear = r6
                        r5.mLunarStartMonth = r7
                        r5.mLunarStartDay = r8
                        r5.mIsLeapStartMonth = r3
                    L_0x0083:
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        r5.mLunarEndYear = r6
                        r5.mLunarEndMonth = r7
                        r5.mLunarEndDay = r8
                        r5.mIsLeapEndMonth = r3
                        goto L_0x00e2
                    L_0x008e:
                        java.util.Calendar r1 = r5.mStartDate
                        java.util.Calendar r5 = r5.mEndDate
                        int r5 = r1.compareTo(r5)
                        if (r5 == 0) goto L_0x00a4
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        java.util.Calendar r1 = r5.mCurrentDate
                        java.util.Calendar r5 = r5.mEndDate
                        int r5 = r1.compareTo(r5)
                        if (r5 <= 0) goto L_0x00ab
                    L_0x00a4:
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        java.util.Calendar r1 = r5.mEndDate
                        r5.clearCalendar(r1, r6, r7, r8)
                    L_0x00ab:
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        java.util.Calendar r1 = r5.mStartDate
                        r5.clearCalendar(r1, r6, r7, r8)
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        boolean r1 = r5.mIsLunar
                        if (r1 == 0) goto L_0x00e2
                        java.util.Calendar r1 = r5.mStartDate
                        java.util.Calendar r5 = r5.mEndDate
                        int r5 = r1.compareTo(r5)
                        if (r5 == 0) goto L_0x00ce
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        java.util.Calendar r1 = r5.mCurrentDate
                        java.util.Calendar r5 = r5.mEndDate
                        int r5 = r1.compareTo(r5)
                        if (r5 <= 0) goto L_0x00d8
                    L_0x00ce:
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        r5.mLunarEndYear = r6
                        r5.mLunarEndMonth = r7
                        r5.mLunarEndDay = r8
                        r5.mIsLeapEndMonth = r3
                    L_0x00d8:
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        r5.mLunarStartYear = r6
                        r5.mLunarStartMonth = r7
                        r5.mLunarStartDay = r8
                        r5.mIsLeapStartMonth = r3
                    L_0x00e2:
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        java.util.Calendar r1 = r5.mStartDate
                        java.util.Calendar r2 = r5.mEndDate
                        boolean r1 = r1.after(r2)
                        r0 = r0 ^ r1
                        r5.onValidationChanged(r0)
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        r5.updateSimpleMonthView(r3)
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        int r0 = r5.mMode
                        r1 = 3
                        if (r0 != r1) goto L_0x010b
                        boolean r5 = r5.mIsWeekRangeSet
                        if (r5 == 0) goto L_0x010b
                        androidx.picker.widget.SeslDatePicker r5 = androidx.picker.widget.SeslDatePicker.this
                        int r0 = r5.getDayOffset()
                        r5.updateStartEndDateRange(r0, r6, r7, r8)
                    L_0x010b:
                        androidx.picker.widget.SeslDatePicker r4 = androidx.picker.widget.SeslDatePicker.this
                        r4.onDateChanged()
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.picker.widget.SeslDatePicker.AnonymousClass6.onDateChanged(androidx.picker.widget.SeslDatePickerSpinnerLayout, int, int, int):void");
                }
            });
            this.mCurrentViewType = 0;
            linearLayout.setOnClickListener(r11);
            linearLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View view, boolean z) {
                    if (z) {
                        SeslDatePicker seslDatePicker = SeslDatePicker.this;
                        if (seslDatePicker.mCurrentViewType == 1) {
                            seslDatePicker.setEditTextMode(false);
                        }
                    }
                }
            });
            this.mDayOfTheWeekLayoutHeight = resources.getDimensionPixelOffset(R$dimen.sesl_date_picker_calendar_day_height);
            int i13 = R$dimen.sesl_date_picker_calendar_view_width;
            this.mCalendarViewPagerWidth = resources.getDimensionPixelOffset(i13);
            this.mCalendarViewMargin = resources.getDimensionPixelOffset(R$dimen.sesl_date_picker_calendar_view_margin);
            this.mDayOfTheWeekLayoutWidth = resources.getDimensionPixelOffset(i13);
            LinearLayout linearLayout2 = (LinearLayout) findViewById(androidx.picker.R$id.sesl_date_picker_day_of_the_week);
            this.mDayOfTheWeekLayout = linearLayout2;
            linearLayout2.addView(dayOfTheWeekView);
            this.mDatePickerLayout = (LinearLayout) findViewById(androidx.picker.R$id.sesl_date_picker_layout);
            this.mCalendarHeaderLayout = (RelativeLayout) findViewById(androidx.picker.R$id.sesl_date_picker_calendar_header_layout);
            if (this.mIsRTL) {
                ImageButton imageButton = (ImageButton) findViewById(androidx.picker.R$id.sesl_date_picker_calendar_header_next_button);
                this.mPrevButton = imageButton;
                ImageButton imageButton2 = (ImageButton) findViewById(androidx.picker.R$id.sesl_date_picker_calendar_header_prev_button);
                this.mNextButton = imageButton2;
                imageButton.setContentDescription(context2.getString(R$string.sesl_date_picker_decrement_month));
                imageButton2.setContentDescription(context2.getString(R$string.sesl_date_picker_increment_month));
            } else {
                this.mPrevButton = (ImageButton) findViewById(androidx.picker.R$id.sesl_date_picker_calendar_header_prev_button);
                this.mNextButton = (ImageButton) findViewById(androidx.picker.R$id.sesl_date_picker_calendar_header_next_button);
            }
            this.mPrevButton.setOnClickListener(this);
            this.mNextButton.setOnClickListener(this);
            this.mPrevButton.setOnLongClickListener(this);
            this.mNextButton.setOnLongClickListener(this);
            this.mPrevButton.setOnTouchListener(r9);
            this.mNextButton.setOnTouchListener(r9);
            this.mPrevButton.setOnKeyListener(r10);
            this.mNextButton.setOnKeyListener(r10);
            AnonymousClass1 r92 = r16;
            this.mPrevButton.setOnFocusChangeListener(r92);
            this.mNextButton.setOnFocusChangeListener(r92);
            this.mPrevButton.setColorFilter(color2);
            this.mNextButton.setColorFilter(color2);
            TypedValue typedValue = new TypedValue();
            context2.getTheme().resolveAttribute(16843868, typedValue, true);
            this.mBackgroundBorderlessResId = typedValue.resourceId;
            this.mCalendarHeaderLayoutHeight = resources.getDimensionPixelOffset(R$dimen.sesl_date_picker_calendar_header_height);
            this.mCalendarViewPagerHeight = resources.getDimensionPixelOffset(R$dimen.sesl_date_picker_calendar_view_height);
            this.mOldCalendarViewPagerWidth = this.mCalendarViewPagerWidth;
            linearLayout.setFocusable(true);
            this.mPrevButton.setNextFocusRightId(i12);
            this.mNextButton.setNextFocusLeftId(i12);
            linearLayout.setNextFocusRightId(androidx.picker.R$id.sesl_date_picker_calendar_header_next_button);
            linearLayout.setNextFocusLeftId(androidx.picker.R$id.sesl_date_picker_calendar_header_prev_button);
            this.mFirstBlankSpace = findViewById(androidx.picker.R$id.sesl_date_picker_between_header_and_weekend);
            this.mFirstBlankSpaceHeight = resources.getDimensionPixelOffset(R$dimen.sesl_date_picker_gap_between_header_and_weekend);
            this.mSecondBlankSpace = findViewById(androidx.picker.R$id.sesl_date_picker_between_weekend_and_calender);
            int dimensionPixelOffset = resources.getDimensionPixelOffset(R$dimen.sesl_date_picker_gap_between_weekend_and_calender);
            this.mSecondBlankSpaceHeight = dimensionPixelOffset;
            this.mDatePickerHeight = this.mCalendarHeaderLayoutHeight + this.mFirstBlankSpaceHeight + this.mDayOfTheWeekLayoutHeight + dimensionPixelOffset + this.mCalendarViewPagerHeight;
            updateSimpleMonthView(true);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(findViewById, "rotation", new float[]{-180.0f, 0.0f});
            this.mClockwiseAnim = ofFloat;
            ofFloat.setDuration(350);
            PathInterpolator pathInterpolator = CALENDAR_HEADER_SPINNER_INTERPOLATOR;
            ofFloat.setInterpolator(pathInterpolator);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(findViewById, "rotation", new float[]{0.0f, -180.0f});
            this.mAntiClockwiseAnim = ofFloat2;
            ofFloat2.setDuration(350);
            ofFloat2.setInterpolator(pathInterpolator);
            TypedValue typedValue2 = new TypedValue();
            context2.getTheme().resolveAttribute(16842839, typedValue2, true);
            boolean z = typedValue2.data != 0;
            this.mIsInDialog = z;
            Activity scanForActivity = scanForActivity(context2);
            if (scanForActivity != null && !z) {
                this.mContentFrame = (FrameLayout) scanForActivity.getWindow().getDecorView().findViewById(16908290);
            } else if (scanForActivity == null) {
                Log.e("SeslDatePicker", "Cannot get window of this context. context:" + context2);
            }
        } catch (Throwable th) {
            obtainStyledAttributes2.recycle();
            throw th;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class CalendarPageChangeListener implements ViewPager.OnPageChangeListener {
        private CalendarPageChangeListener() {
        }

        public void onPageSelected(int i2) {
            boolean z = false;
            if (SeslDatePicker.this.mIsRTL) {
                boolean unused = SeslDatePicker.this.mIsConfigurationChanged = false;
            }
            SeslDatePicker seslDatePicker = SeslDatePicker.this;
            if (seslDatePicker.mIsFromSetLunar) {
                seslDatePicker.mIsFromSetLunar = false;
                return;
            }
            seslDatePicker.mCurrentPosition = i2;
            int minMonth = seslDatePicker.getMinMonth() + i2;
            int minYear = SeslDatePicker.this.getMinYear() + (minMonth / 12);
            int i7 = minMonth % 12;
            int i8 = SeslDatePicker.this.mCurrentDate.get(5);
            SeslDatePicker seslDatePicker2 = SeslDatePicker.this;
            if (seslDatePicker2.mIsLunar) {
                LunarDate access$2300 = seslDatePicker2.getLunarDateByPosition(i2);
                minYear = access$2300.year;
                int i10 = access$2300.month;
                SeslDatePicker seslDatePicker3 = SeslDatePicker.this;
                int i11 = seslDatePicker3.mLunarCurrentDay;
                seslDatePicker3.mIsLeapMonth = access$2300.isLeapMonth;
                i7 = i10;
                i8 = i11;
            }
            if (minYear != SeslDatePicker.this.mTempDate.get(1)) {
                z = true;
            }
            SeslDatePicker.this.mTempDate.set(1, minYear);
            SeslDatePicker.this.mTempDate.set(2, i7);
            SeslDatePicker.this.mTempDate.set(5, 1);
            if (i8 > SeslDatePicker.this.mTempDate.getActualMaximum(5)) {
                i8 = SeslDatePicker.this.mTempDate.getActualMaximum(5);
            }
            SeslDatePicker.this.mTempDate.set(5, i8);
            Message obtainMessage = SeslDatePicker.this.mHandler.obtainMessage();
            obtainMessage.what = 1000;
            obtainMessage.obj = Boolean.valueOf(z);
            SeslDatePicker.this.mHandler.sendMessage(obtainMessage);
            Message obtainMessage2 = SeslDatePicker.this.mHandler.obtainMessage();
            obtainMessage2.what = 1001;
            SeslDatePicker.this.mHandler.sendMessage(obtainMessage2);
            SparseArray<SeslSimpleMonthView> sparseArray = SeslDatePicker.this.mCalendarPagerAdapter.views;
            if (sparseArray.get(i2) != null) {
                sparseArray.get(i2).clearAccessibilityFocus();
                sparseArray.get(i2).setImportantForAccessibility(1);
            }
            if (i2 != 0) {
                int i12 = i2 - 1;
                if (sparseArray.get(i12) != null) {
                    sparseArray.get(i12).clearAccessibilityFocus();
                    sparseArray.get(i12).setImportantForAccessibility(2);
                }
            }
            if (i2 != SeslDatePicker.this.mPositionCount - 1) {
                int i13 = i2 + 1;
                if (sparseArray.get(i13) != null) {
                    sparseArray.get(i13).clearAccessibilityFocus();
                    sparseArray.get(i13).setImportantForAccessibility(2);
                }
            }
        }

        public void onPageScrollStateChanged(int i2) {
        }

        public void onPageScrolled(int i2, float f, int i7) {
        }
    }

    /* access modifiers changed from: private */
    public void debugLog(String str) {
    }

    public void onValidationChanged(boolean z) {
    }

    public void setDateValidator(DateValidator dateValidator) {
    }

    public void setOnViewTypeChangedListener(OnViewTypeChangedListener onViewTypeChangedListener) {
    }

    public void setValidationCallback(ValidationCallback validationCallback) {
    }
}
