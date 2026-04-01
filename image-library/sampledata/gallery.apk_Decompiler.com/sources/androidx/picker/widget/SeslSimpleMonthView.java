package androidx.picker.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.R$attr;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import androidx.picker.R$color;
import androidx.picker.R$dimen;
import androidx.picker.R$integer;
import androidx.picker.R$string;
import androidx.picker.R$styleable;
import androidx.reflect.feature.SeslCscFeatureReflector;
import androidx.reflect.lunarcalendar.SeslFeatureReflector;
import androidx.reflect.lunarcalendar.SeslLunarDateUtilsReflector;
import androidx.reflect.lunarcalendar.SeslSolarLunarConverterReflector;
import androidx.reflect.view.SeslViewReflector;
import c0.C0086a;
import dalvik.system.PathClassLoader;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SeslSimpleMonthView extends View {
    protected Paint mAbnormalSelectedDayPaint;
    protected final int mAbnormalStartEndDateBackgroundAlpha;
    protected final Calendar mCalendar;
    protected int mCalendarWidth;
    protected final Context mContext;
    protected final int[] mDayColorSet;
    protected int mDayNumberDisabledAlpha;
    protected Paint mDayNumberPaint;
    protected Paint mDayNumberSelectedPaint;
    protected int mDayOfWeekStart;
    protected int mDaySelectedCircleSize;
    protected int mDaySelectedCircleStroke;
    protected int mEnabledDayEnd;
    protected int mEnabledDayStart;
    protected int mEndDay;
    protected int mEndMonth;
    protected int mEndYear;
    protected Paint mHcfEnabledDayNumberPaint;
    protected boolean mIsFirstMonth;
    protected boolean mIsHcfEnabled;
    protected boolean mIsLastMonth;
    protected int mIsLeapEndMonth;
    protected boolean mIsLeapMonth;
    protected int mIsLeapStartMonth;
    protected boolean mIsLunar;
    protected boolean mIsNextMonthLeap;
    protected boolean mIsPrevMonthLeap;
    protected boolean mIsRTL;
    protected int mLastAccessibilityFocusedView;
    protected boolean mLockAccessibilityDelegate;
    protected boolean mLostAccessibilityFocus;
    protected Calendar mMaxDate;
    protected Calendar mMinDate;
    protected int mMiniDayNumberTextSize;
    protected int mMode;
    protected int mMonth;
    protected int mNormalTextColor;
    protected int mNumCells;
    protected final int mNumDays;
    protected OnDayClickListener mOnDayClickListener;
    protected OnDeactivatedDayClickListener mOnDeactivatedDayClickListener;
    protected int mPadding;
    protected PathClassLoader mPathClassLoader;
    protected final int mPrevNextMonthDayNumberAlpha;
    protected final int mSaturdayTextColor;
    protected int mSelectedDay;
    protected final int mSelectedDayColor;
    protected int mSelectedDayNumberTextColor;
    protected Object mSolarLunarConverter;
    protected int mStartDay;
    protected int mStartMonth;
    protected int mStartYear;
    protected final int mSundayTextColor;
    protected Calendar mTempDate;
    protected Calendar mTodayDate;
    protected Paint mTodayDateMarkPaint;
    protected final MonthViewTouchHelper mTouchHelper;
    protected int mWeekHeight;
    protected int mWeekStart;
    protected int mYear;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class MonthViewTouchHelper extends ExploreByTouchHelper {
        private final Calendar mTempCalendar = Calendar.getInstance();
        private final Rect mTempRect = new Rect();

        public MonthViewTouchHelper(View view) {
            super(view);
        }

        private int getDayPositionInWeek(int i2) {
            int dayOfWeekStart = ((SeslSimpleMonthView.this.getDayOfWeekStart() - 1) + i2) % 7;
            if (dayOfWeekStart == 0) {
                return 7;
            }
            return dayOfWeekStart;
        }

        private void getItemBounds(int i2, Rect rect) {
            SeslSimpleMonthView seslSimpleMonthView = SeslSimpleMonthView.this;
            int i7 = seslSimpleMonthView.mPadding;
            SeslSimpleMonthView seslSimpleMonthView2 = SeslSimpleMonthView.this;
            int i8 = seslSimpleMonthView2.mWeekHeight;
            int i10 = seslSimpleMonthView2.mCalendarWidth / 7;
            int access$100 = (i2 - 1) + seslSimpleMonthView2.findDayOffset();
            int i11 = access$100 / 7;
            int i12 = access$100 % 7;
            int i13 = (i11 * i8) + ((int) (seslSimpleMonthView.mContext.getResources().getDisplayMetrics().density * -1.0f));
            SeslSimpleMonthView seslSimpleMonthView3 = SeslSimpleMonthView.this;
            if (seslSimpleMonthView3.mMode == 3) {
                rect.set(0, i13, seslSimpleMonthView3.mCalendarWidth, i8 + i13);
                return;
            }
            int i14 = (i12 * i10) + i7;
            rect.set(i14, i13, i10 + i14, i8 + i13);
        }

        private CharSequence getItemDescription(int i2) {
            Calendar calendar = this.mTempCalendar;
            SeslSimpleMonthView seslSimpleMonthView = SeslSimpleMonthView.this;
            calendar.set(seslSimpleMonthView.mYear, seslSimpleMonthView.mMonth, i2);
            String formatDateTime = DateUtils.formatDateTime(SeslSimpleMonthView.this.mContext, this.mTempCalendar.getTimeInMillis(), 22);
            SeslSimpleMonthView seslSimpleMonthView2 = SeslSimpleMonthView.this;
            if (!seslSimpleMonthView2.mIsLunar || seslSimpleMonthView2.mPathClassLoader == null) {
                return formatDateTime;
            }
            int i7 = seslSimpleMonthView2.mYear;
            int i8 = seslSimpleMonthView2.mMonth;
            boolean z = seslSimpleMonthView2.mIsLeapMonth;
            if (i2 <= 0) {
                i8 -= z ^ true ? 1 : 0;
                z = seslSimpleMonthView2.mIsPrevMonthLeap;
                if (i8 < 0) {
                    i7--;
                    i8 = 11;
                }
                i2 += seslSimpleMonthView2.getDaysInMonthLunar(i8, i7, z);
            } else {
                int i10 = seslSimpleMonthView2.mNumCells;
                if (i2 > i10) {
                    z = seslSimpleMonthView2.mIsNextMonthLeap;
                    int i11 = i8 + (z ^ true ? 1 : 0);
                    if (i11 > 11) {
                        i7++;
                        i11 = 0;
                    }
                    i2 -= i10;
                }
            }
            int i12 = i2;
            int i13 = i7;
            int i14 = i8;
            boolean z3 = z;
            SeslSimpleMonthView seslSimpleMonthView3 = SeslSimpleMonthView.this;
            SeslSolarLunarConverterReflector.convertLunarToSolar(seslSimpleMonthView3.mPathClassLoader, seslSimpleMonthView3.mSolarLunarConverter, i13, i14, i12, z3);
            SeslSimpleMonthView seslSimpleMonthView4 = SeslSimpleMonthView.this;
            int year = SeslSolarLunarConverterReflector.getYear(seslSimpleMonthView4.mPathClassLoader, seslSimpleMonthView4.mSolarLunarConverter);
            SeslSimpleMonthView seslSimpleMonthView5 = SeslSimpleMonthView.this;
            int month = SeslSolarLunarConverterReflector.getMonth(seslSimpleMonthView5.mPathClassLoader, seslSimpleMonthView5.mSolarLunarConverter);
            SeslSimpleMonthView seslSimpleMonthView6 = SeslSimpleMonthView.this;
            int day = SeslSolarLunarConverterReflector.getDay(seslSimpleMonthView6.mPathClassLoader, seslSimpleMonthView6.mSolarLunarConverter);
            Calendar instance = Calendar.getInstance();
            instance.set(year, month, day);
            SeslSimpleMonthView seslSimpleMonthView7 = SeslSimpleMonthView.this;
            return SeslLunarDateUtilsReflector.buildLunarDateString(seslSimpleMonthView7.mPathClassLoader, instance, seslSimpleMonthView7.getContext());
        }

        private String getItemDescriptionWeek(int i2, int i7) {
            return String.format(SeslSimpleMonthView.this.getResources().getString(R$string.sesl_date_picker_week_select_content_description), new Object[]{getItemDescription(i2), getItemDescription(i7)});
        }

        public void clearFocusedVirtualView() {
            int focusedVirtualView = getFocusedVirtualView();
            if (focusedVirtualView != Integer.MIN_VALUE) {
                getAccessibilityNodeProvider(SeslSimpleMonthView.this).performAction(focusedVirtualView, 128, (Bundle) null);
            }
        }

        public int getVirtualViewAt(float f, float f5) {
            int access$000 = SeslSimpleMonthView.this.getDayFromLocation(f, f5);
            SeslSimpleMonthView seslSimpleMonthView = SeslSimpleMonthView.this;
            if (seslSimpleMonthView.mIsFirstMonth && access$000 < seslSimpleMonthView.mEnabledDayStart) {
                return Integer.MIN_VALUE;
            }
            if (seslSimpleMonthView.mIsLastMonth && access$000 > seslSimpleMonthView.mEnabledDayEnd) {
                return Integer.MIN_VALUE;
            }
            seslSimpleMonthView.getClass();
            int access$100 = access$000 + SeslSimpleMonthView.this.findDayOffset();
            if (SeslSimpleMonthView.this.mMode != 3) {
                return access$100;
            }
            int i2 = access$100 + 6;
            return i2 - (i2 % 7);
        }

        public void getVisibleVirtualViews(List<Integer> list) {
            int access$100 = SeslSimpleMonthView.this.findDayOffset();
            for (int i2 = 1; i2 <= 42; i2++) {
                int i7 = i2 - access$100;
                SeslSimpleMonthView seslSimpleMonthView = SeslSimpleMonthView.this;
                if ((seslSimpleMonthView.mMode != 3 || i2 % 7 == 0) && ((!seslSimpleMonthView.mIsFirstMonth || i7 >= seslSimpleMonthView.mEnabledDayStart) && (!seslSimpleMonthView.mIsLastMonth || i7 <= seslSimpleMonthView.mEnabledDayEnd))) {
                    seslSimpleMonthView.getClass();
                    list.add(Integer.valueOf(i2));
                }
            }
        }

        public boolean onPerformActionForVirtualView(int i2, int i7, Bundle bundle) {
            if (i7 != 16) {
                return false;
            }
            int access$100 = i2 - SeslSimpleMonthView.this.findDayOffset();
            SeslSimpleMonthView seslSimpleMonthView = SeslSimpleMonthView.this;
            if ((seslSimpleMonthView.mIsFirstMonth && access$100 < seslSimpleMonthView.mEnabledDayStart) || (seslSimpleMonthView.mIsLastMonth && access$100 > seslSimpleMonthView.mEnabledDayEnd)) {
                return true;
            }
            if (access$100 > 0) {
                int i8 = seslSimpleMonthView.mNumCells;
                if (access$100 <= i8) {
                    seslSimpleMonthView.onDayClick(seslSimpleMonthView.mYear, seslSimpleMonthView.mMonth, access$100);
                } else if (seslSimpleMonthView.mIsLunar) {
                    int i10 = seslSimpleMonthView.mMonth + 1;
                    if (i10 > 11) {
                        seslSimpleMonthView.onDeactivatedDayClick(seslSimpleMonthView.mYear + 1, 0, access$100 - i8, false);
                    } else {
                        seslSimpleMonthView.onDeactivatedDayClick(seslSimpleMonthView.mYear, i10, access$100 - i8, false);
                    }
                } else {
                    Calendar instance = Calendar.getInstance();
                    instance.clear();
                    SeslSimpleMonthView seslSimpleMonthView2 = SeslSimpleMonthView.this;
                    instance.set(seslSimpleMonthView2.mYear, seslSimpleMonthView2.mMonth, seslSimpleMonthView2.mNumCells);
                    instance.add(5, access$100 - SeslSimpleMonthView.this.mNumCells);
                    SeslSimpleMonthView.this.onDeactivatedDayClick(instance.get(1), instance.get(2), instance.get(5), false);
                }
            } else if (seslSimpleMonthView.mIsLunar) {
                int i11 = seslSimpleMonthView.mMonth;
                boolean z = seslSimpleMonthView.mIsLeapMonth;
                int i12 = i11 - (z ^ true ? 1 : 0);
                if (i12 < 0) {
                    int access$200 = seslSimpleMonthView.getDaysInMonthLunar(11, seslSimpleMonthView.mYear - 1, z);
                    SeslSimpleMonthView seslSimpleMonthView3 = SeslSimpleMonthView.this;
                    seslSimpleMonthView3.onDeactivatedDayClick(seslSimpleMonthView3.mYear - 1, i12, access$200 + access$100, true);
                } else {
                    int access$2002 = seslSimpleMonthView.getDaysInMonthLunar(i12, seslSimpleMonthView.mYear, z);
                    SeslSimpleMonthView seslSimpleMonthView4 = SeslSimpleMonthView.this;
                    seslSimpleMonthView4.onDeactivatedDayClick(seslSimpleMonthView4.mYear, i12, access$2002 + access$100, true);
                }
            } else {
                Calendar instance2 = Calendar.getInstance();
                instance2.clear();
                SeslSimpleMonthView seslSimpleMonthView5 = SeslSimpleMonthView.this;
                instance2.set(seslSimpleMonthView5.mYear, seslSimpleMonthView5.mMonth, 1);
                instance2.add(5, access$100 - 1);
                SeslSimpleMonthView.this.onDeactivatedDayClick(instance2.get(1), instance2.get(2), instance2.get(5), true);
            }
            return true;
        }

        public void onPopulateEventForVirtualView(int i2, AccessibilityEvent accessibilityEvent) {
            int access$100 = i2 - SeslSimpleMonthView.this.findDayOffset();
            if (accessibilityEvent.getEventType() == 32768) {
                SeslSimpleMonthView seslSimpleMonthView = SeslSimpleMonthView.this;
                seslSimpleMonthView.mLastAccessibilityFocusedView = access$100;
                seslSimpleMonthView.mLostAccessibilityFocus = false;
            }
            if (accessibilityEvent.getEventType() == 65536) {
                SeslSimpleMonthView seslSimpleMonthView2 = SeslSimpleMonthView.this;
                seslSimpleMonthView2.mLastAccessibilityFocusedView = -1;
                seslSimpleMonthView2.mLostAccessibilityFocus = true;
            }
            if (SeslSimpleMonthView.this.mMode == 3) {
                int dayPositionInWeek = getDayPositionInWeek(access$100);
                accessibilityEvent.setContentDescription(getItemDescriptionWeek((access$100 - dayPositionInWeek) + 1, (7 - dayPositionInWeek) + access$100));
                return;
            }
            accessibilityEvent.setContentDescription(getItemDescription(access$100));
        }

        public void onPopulateNodeForVirtualView(int i2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int access$100 = i2 - SeslSimpleMonthView.this.findDayOffset();
            getItemBounds(access$100, this.mTempRect);
            if (SeslSimpleMonthView.this.mMode == 3) {
                int dayPositionInWeek = getDayPositionInWeek(access$100);
                accessibilityNodeInfoCompat.setContentDescription(getItemDescriptionWeek((access$100 - dayPositionInWeek) + 1, (7 - dayPositionInWeek) + access$100));
            } else {
                accessibilityNodeInfoCompat.setContentDescription(getItemDescription(access$100));
            }
            accessibilityNodeInfoCompat.setBoundsInParent(this.mTempRect);
            accessibilityNodeInfoCompat.addAction(16);
            int i7 = SeslSimpleMonthView.this.mSelectedDay;
            if (i7 != -1 && access$100 == i7) {
                accessibilityNodeInfoCompat.addAction(4);
                accessibilityNodeInfoCompat.setClickable(true);
                accessibilityNodeInfoCompat.setCheckable(true);
                accessibilityNodeInfoCompat.setChecked(true);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnDayClickListener {
        void onDayClick(SeslSimpleMonthView seslSimpleMonthView, int i2, int i7, int i8);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnDeactivatedDayClickListener {
        void onDeactivatedDayClick(SeslSimpleMonthView seslSimpleMonthView, int i2, int i7, int i8, boolean z, boolean z3);
    }

    public SeslSimpleMonthView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:142:0x0308  */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x03e9  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x040a  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x046c  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x0474  */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x0488  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x0585  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x05c0  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x05c8  */
    /* JADX WARNING: Removed duplicated region for block: B:268:0x02fb A[EDGE_INSN: B:268:0x02fb->B:140:0x02fb ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:280:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00cb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void drawDays(android.graphics.Canvas r38) {
        /*
            r37 = this;
            r0 = r37
            r1 = r38
            int r2 = r0.mWeekHeight
            r7 = 2
            int r2 = r2 * r7
            r8 = 3
            int r2 = r2 / r8
            int r3 = r0.mCalendarWidth
            int r9 = r3 / 14
            int r10 = r0.findDayOffset()
            int r3 = r0.mMiniDayNumberTextSize
            float r3 = (float) r3
            r4 = 1076677837(0x402ccccd, float:2.7)
            float r11 = r3 / r4
            int r12 = r0.mStartYear
            int r3 = r0.mStartMonth
            float r3 = (float) r3
            int r13 = r0.mStartDay
            int r14 = r0.mEndYear
            int r4 = r0.mEndMonth
            float r4 = (float) r4
            int r15 = r0.mEndDay
            boolean r5 = r0.mIsLunar
            r8 = 1
            r17 = 1056964608(0x3f000000, float:0.5)
            if (r5 == 0) goto L_0x0035
            int r6 = r0.mIsLeapStartMonth
            if (r6 != r8) goto L_0x0035
            float r3 = r3 + r17
        L_0x0035:
            r18 = r3
            if (r5 == 0) goto L_0x003f
            int r3 = r0.mIsLeapEndMonth
            if (r3 != r8) goto L_0x003f
            float r4 = r4 + r17
        L_0x003f:
            r19 = r4
            int r3 = r0.mYear
            int r4 = r0.mMonth
            float r4 = (float) r4
            if (r5 == 0) goto L_0x004e
            boolean r5 = r0.mIsLeapMonth
            if (r5 == 0) goto L_0x004e
            float r4 = r4 + r17
        L_0x004e:
            r17 = r4
            int r4 = r12 * 10000
            r5 = 1120403456(0x42c80000, float:100.0)
            float r6 = r18 * r5
            int r6 = (int) r6
            int r4 = r4 + r6
            int r6 = r14 * 10000
            r20 = r5
            float r5 = r19 * r20
            int r5 = (int) r5
            int r6 = r6 + r5
            int r5 = r3 * 10000
            r21 = r8
            float r8 = r17 * r20
            int r8 = (int) r8
            int r5 = r5 + r8
            int r8 = r0.mMode
            r20 = 0
            if (r8 == 0) goto L_0x0079
            int r8 = r4 + r13
            r22 = r7
            int r7 = r6 + r15
            if (r8 <= r7) goto L_0x007b
            r7 = r21
            goto L_0x007d
        L_0x0079:
            r22 = r7
        L_0x007b:
            r7 = r20
        L_0x007d:
            if (r7 != 0) goto L_0x00b3
            if (r12 != r14) goto L_0x008e
            int r23 = (r18 > r19 ? 1 : (r18 == r19 ? 0 : -1))
            if (r23 != 0) goto L_0x008e
            if (r3 != r12) goto L_0x008e
            int r23 = (r17 > r18 ? 1 : (r17 == r18 ? 0 : -1))
            if (r23 != 0) goto L_0x008e
            r5 = r13
            r4 = r15
            goto L_0x00b5
        L_0x008e:
            if (r4 >= r5) goto L_0x009f
            if (r5 >= r6) goto L_0x009f
            if (r3 != r14) goto L_0x0098
            int r4 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r4 == 0) goto L_0x009f
        L_0x0098:
            int r4 = r0.mNumCells
            int r4 = r4 + 1
        L_0x009c:
            r5 = r20
            goto L_0x00b5
        L_0x009f:
            if (r3 != r12) goto L_0x00ab
            int r4 = (r17 > r18 ? 1 : (r17 == r18 ? 0 : -1))
            if (r4 != 0) goto L_0x00ab
            int r4 = r0.mNumCells
            int r4 = r4 + 1
            r5 = r13
            goto L_0x00b5
        L_0x00ab:
            if (r3 != r14) goto L_0x00b3
            int r4 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r4 != 0) goto L_0x00b3
            r4 = r15
            goto L_0x009c
        L_0x00b3:
            r4 = -1
            r5 = -1
        L_0x00b5:
            boolean r6 = r0.isHighContrastFontEnabled()
            r0.mIsHcfEnabled = r6
            r23 = r10
            r24 = r20
            r6 = r21
        L_0x00c1:
            int r8 = r0.mNumCells
            r26 = r7
            java.lang.String r7 = "%d"
            r27 = r11
            if (r6 > r8) goto L_0x02fb
            boolean r8 = r0.mIsRTL
            if (r8 == 0) goto L_0x00dc
            int r8 = 6 - r23
            int r8 = r8 * 2
            int r8 = r8 + 1
            int r8 = r8 * r9
            r28 = 7
            int r11 = r0.mPadding
        L_0x00da:
            int r8 = r8 + r11
            goto L_0x00e6
        L_0x00dc:
            r28 = 7
            int r8 = r23 * 2
            int r8 = r8 + 1
            int r8 = r8 * r9
            int r11 = r0.mPadding
            goto L_0x00da
        L_0x00e6:
            int r11 = r0.mWeekStart
            int r11 = r23 + r11
            int r11 = r11 % 7
            r29 = r11
            android.graphics.Paint r11 = r0.mDayNumberPaint
            r30 = r10
            int[] r10 = r0.mDayColorSet
            r10 = r10[r29]
            r11.setColor(r10)
            int r10 = r0.mEnabledDayStart
            if (r6 < r10) goto L_0x0101
            int r10 = r0.mEnabledDayEnd
            if (r6 <= r10) goto L_0x0108
        L_0x0101:
            android.graphics.Paint r10 = r0.mDayNumberPaint
            int r11 = r0.mDayNumberDisabledAlpha
            r10.setAlpha(r11)
        L_0x0108:
            int r10 = r0.mYear
            int r11 = r0.mMonth
            boolean r10 = r0.isTodayDate(r10, r11, r6)
            if (r10 == 0) goto L_0x0129
            float r10 = (float) r8
            float r11 = (float) r2
            float r11 = r11 - r27
            r29 = r7
            int r7 = r0.mDaySelectedCircleSize
            r31 = r7
            int r7 = r0.mDaySelectedCircleStroke
            int r7 = r31 - r7
            float r7 = (float) r7
            r31 = r9
            android.graphics.Paint r9 = r0.mTodayDateMarkPaint
            r1.drawCircle(r10, r11, r7, r9)
            goto L_0x012d
        L_0x0129:
            r29 = r7
            r31 = r9
        L_0x012d:
            android.graphics.Paint r7 = r0.mDayNumberPaint
            boolean r9 = r0.mIsHcfEnabled
            if (r9 == 0) goto L_0x0148
            int r9 = r7.getAlpha()
            int r10 = r0.mDayNumberDisabledAlpha
            if (r9 == r10) goto L_0x0148
            android.graphics.Paint r7 = r0.mHcfEnabledDayNumberPaint
            android.graphics.Paint r9 = r0.mDayNumberPaint
            int r9 = r9.getColor()
            r7.setColor(r9)
            android.graphics.Paint r7 = r0.mHcfEnabledDayNumberPaint
        L_0x0148:
            if (r26 == 0) goto L_0x01c4
            if (r12 != r3) goto L_0x015b
            int r9 = (r18 > r17 ? 1 : (r18 == r17 ? 0 : -1))
            if (r9 != 0) goto L_0x015b
            if (r13 != r6) goto L_0x015b
            int r9 = r0.mMode
            r10 = r22
            if (r9 == r10) goto L_0x0170
            r10 = 3
            if (r9 == r10) goto L_0x0170
        L_0x015b:
            if (r14 != r3) goto L_0x016d
            int r9 = (r19 > r17 ? 1 : (r19 == r17 ? 0 : -1))
            if (r9 != 0) goto L_0x016d
            if (r15 != r6) goto L_0x016d
            int r9 = r0.mMode
            r10 = r21
            if (r9 == r10) goto L_0x0170
            r10 = 3
            if (r9 != r10) goto L_0x016d
            goto L_0x0170
        L_0x016d:
            r32 = r4
            goto L_0x0183
        L_0x0170:
            float r9 = (float) r8
            float r10 = (float) r2
            float r10 = r10 - r27
            int r11 = r0.mDaySelectedCircleSize
            float r11 = (float) r11
            r32 = r4
            android.graphics.Paint r4 = r0.mDayNumberSelectedPaint
            r1.drawCircle(r9, r10, r11, r4)
            int r4 = r0.mSelectedDayNumberTextColor
            r7.setColor(r4)
        L_0x0183:
            if (r14 != r3) goto L_0x0193
            int r4 = (r19 > r17 ? 1 : (r19 == r17 ? 0 : -1))
            if (r4 != 0) goto L_0x0193
            if (r15 != r6) goto L_0x0193
            int r4 = r0.mMode
            r10 = 2
            if (r4 == r10) goto L_0x01b7
            r10 = 3
            if (r4 == r10) goto L_0x01b7
        L_0x0193:
            if (r12 != r3) goto L_0x01a4
            int r4 = (r18 > r17 ? 1 : (r18 == r17 ? 0 : -1))
            if (r4 != 0) goto L_0x01a4
            if (r13 != r6) goto L_0x01a4
            int r4 = r0.mMode
            r10 = 1
            if (r4 == r10) goto L_0x01b7
            r10 = 3
            if (r4 != r10) goto L_0x01a4
            goto L_0x01b7
        L_0x01a4:
            r9 = r2
            r11 = r5
            r33 = r12
            r25 = r13
            r35 = r14
            r13 = r31
            r10 = r32
            r31 = -1
            r32 = r3
            r12 = r6
            goto L_0x02b3
        L_0x01b7:
            float r4 = (float) r8
            float r9 = (float) r2
            float r9 = r9 - r27
            int r10 = r0.mDaySelectedCircleSize
            float r10 = (float) r10
            android.graphics.Paint r11 = r0.mAbnormalSelectedDayPaint
            r1.drawCircle(r4, r9, r10, r11)
            goto L_0x01a4
        L_0x01c4:
            r32 = r4
            if (r5 >= r6) goto L_0x020c
            r4 = r32
            if (r6 >= r4) goto L_0x0203
            int r9 = r8 - r31
            float r9 = (float) r9
            float r10 = (float) r2
            float r10 = r10 - r27
            int r11 = r0.mDaySelectedCircleSize
            float r1 = (float) r11
            float r10 = r10 - r1
            int r1 = r31 * 2
            float r1 = (float) r1
            float r1 = r1 + r9
            r22 = 2
            int r11 = r11 * 2
            float r11 = (float) r11
            float r11 = r11 + r10
            r32 = r6
            android.graphics.Paint r6 = r0.mDayNumberSelectedPaint
            r33 = r9
            r9 = r2
            r2 = r33
            r33 = r11
            r11 = r5
            r5 = r33
            r33 = r12
            r12 = r32
            r32 = r3
            r3 = r10
            r10 = r4
            r4 = r1
            r1 = r38
            r1.drawRect(r2, r3, r4, r5, r6)
            int r2 = r0.mSelectedDayNumberTextColor
            r7.setColor(r2)
        L_0x0201:
            r2 = -1
            goto L_0x0215
        L_0x0203:
            r9 = r2
            r32 = r3
            r10 = r4
            r11 = r5
            r33 = r12
        L_0x020a:
            r12 = r6
            goto L_0x0201
        L_0x020c:
            r9 = r2
            r11 = r5
            r33 = r12
            r10 = r32
            r32 = r3
            goto L_0x020a
        L_0x0215:
            if (r11 == r2) goto L_0x0236
            if (r11 != r10) goto L_0x0236
            if (r12 != r11) goto L_0x0236
            float r3 = (float) r8
            float r4 = (float) r9
            float r4 = r4 - r27
            int r5 = r0.mDaySelectedCircleSize
            float r5 = (float) r5
            android.graphics.Paint r6 = r0.mDayNumberSelectedPaint
            r1.drawCircle(r3, r4, r5, r6)
            int r3 = r0.mSelectedDayNumberTextColor
            r7.setColor(r3)
            r25 = r13
            r35 = r14
            r13 = r31
            r31 = r2
            goto L_0x02b3
        L_0x0236:
            if (r10 != r12) goto L_0x0277
            float r3 = (float) r9
            float r3 = r3 - r27
            boolean r4 = r0.mIsRTL
            if (r4 == 0) goto L_0x0241
            float r4 = (float) r8
            goto L_0x0244
        L_0x0241:
            int r4 = r8 - r31
            float r4 = (float) r4
        L_0x0244:
            int r5 = r0.mDaySelectedCircleSize
            float r6 = (float) r5
            float r6 = r3 - r6
            r25 = r13
            r13 = r31
            float r2 = (float) r13
            float r2 = r2 + r4
            r22 = 2
            int r5 = r5 * 2
            float r5 = (float) r5
            float r5 = r5 + r6
            r34 = r3
            r3 = r6
            android.graphics.Paint r6 = r0.mDayNumberSelectedPaint
            r31 = r4
            r4 = r2
            r2 = r31
            r35 = r14
            r14 = r34
            r31 = -1
            r1.drawRect(r2, r3, r4, r5, r6)
            float r2 = (float) r8
            int r3 = r0.mDaySelectedCircleSize
            float r3 = (float) r3
            android.graphics.Paint r4 = r0.mDayNumberSelectedPaint
            r1.drawCircle(r2, r14, r3, r4)
            int r2 = r0.mSelectedDayNumberTextColor
            r7.setColor(r2)
            goto L_0x02b3
        L_0x0277:
            r25 = r13
            r35 = r14
            r13 = r31
            r31 = r2
            if (r11 != r12) goto L_0x02b3
            float r2 = (float) r9
            float r14 = r2 - r27
            boolean r2 = r0.mIsRTL
            if (r2 == 0) goto L_0x028c
            int r2 = r8 - r13
            float r2 = (float) r2
            goto L_0x028d
        L_0x028c:
            float r2 = (float) r8
        L_0x028d:
            int r3 = r0.mDaySelectedCircleSize
            float r4 = (float) r3
            float r4 = r14 - r4
            float r5 = (float) r13
            float r5 = r5 + r2
            r22 = 2
            int r3 = r3 * 2
            float r3 = (float) r3
            float r3 = r3 + r4
            android.graphics.Paint r6 = r0.mDayNumberSelectedPaint
            r36 = r5
            r5 = r3
            r3 = r4
            r4 = r36
            r1.drawRect(r2, r3, r4, r5, r6)
            float r2 = (float) r8
            int r3 = r0.mDaySelectedCircleSize
            float r3 = (float) r3
            android.graphics.Paint r4 = r0.mDayNumberSelectedPaint
            r1.drawCircle(r2, r14, r3, r4)
            int r2 = r0.mSelectedDayNumberTextColor
            r7.setColor(r2)
        L_0x02b3:
            int r2 = r0.mMode
            if (r2 != 0) goto L_0x02be
            if (r12 != r10) goto L_0x02be
            int r2 = r0.mSelectedDayNumberTextColor
            r7.setColor(r2)
        L_0x02be:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r12)
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            r14 = r29
            java.lang.String r2 = java.lang.String.format(r14, r2)
            float r3 = (float) r8
            float r4 = (float) r9
            r1.drawText(r2, r3, r4, r7)
            int r2 = r23 + 1
            r3 = r28
            if (r2 != r3) goto L_0x02df
            int r2 = r0.mWeekHeight
            int r2 = r2 + r9
            int r24 = r24 + 1
            r23 = r20
            goto L_0x02e2
        L_0x02df:
            r23 = r2
            r2 = r9
        L_0x02e2:
            int r6 = r12 + 1
            r4 = r10
            r5 = r11
            r9 = r13
            r13 = r25
            r7 = r26
            r11 = r27
            r10 = r30
            r3 = r32
            r12 = r33
            r14 = r35
            r21 = 1
            r22 = 2
            goto L_0x00c1
        L_0x02fb:
            r11 = r5
            r14 = r7
            r13 = r9
            r30 = r10
            r9 = r2
            r10 = r4
            boolean r2 = r0.mIsLastMonth
            r7 = 11
            if (r2 != 0) goto L_0x0480
            int r2 = r0.mMonth
            int r3 = r2 + 1
            int r4 = r0.mYear
            if (r3 <= r7) goto L_0x0316
            int r3 = r4 + 1
            r5 = r3
            r3 = r20
            goto L_0x0317
        L_0x0316:
            r5 = r4
        L_0x0317:
            boolean r6 = r0.mIsLunar
            if (r6 == 0) goto L_0x032d
            boolean r3 = r0.mIsNextMonthLeap
            r21 = 1
            r3 = r3 ^ 1
            int r3 = r3 + r2
            if (r3 <= r7) goto L_0x032a
            int r4 = r4 + 1
            r12 = r4
            r8 = r20
            goto L_0x032f
        L_0x032a:
            r8 = r3
            r12 = r4
            goto L_0x032f
        L_0x032d:
            r8 = r3
            r12 = r5
        L_0x032f:
            r2 = r24
            r15 = 1
        L_0x0332:
            r3 = 6
            if (r2 == r3) goto L_0x0480
            boolean r3 = r0.mIsRTL
            if (r3 == 0) goto L_0x0348
            int r3 = 6 - r23
            r22 = 2
            int r3 = r3 * 2
            r21 = 1
            int r3 = r3 + 1
            int r3 = r3 * r13
            int r4 = r0.mPadding
        L_0x0346:
            int r3 = r3 + r4
            goto L_0x0352
        L_0x0348:
            r21 = 1
            int r3 = r23 * 2
            int r3 = r3 + 1
            int r3 = r3 * r13
            int r4 = r0.mPadding
            goto L_0x0346
        L_0x0352:
            int r4 = r0.mWeekStart
            int r4 = r23 + r4
            r28 = 7
            int r4 = r4 % 7
            android.graphics.Paint r5 = r0.mDayNumberPaint
            int[] r6 = r0.mDayColorSet
            r4 = r6[r4]
            r5.setColor(r4)
            android.graphics.Paint r4 = r0.mDayNumberPaint
            int r5 = r0.mPrevNextMonthDayNumberAlpha
            r4.setAlpha(r5)
            int r4 = r0.mMode
            if (r4 == 0) goto L_0x03c1
            int r4 = r0.mNumCells
            r21 = 1
            int r4 = r4 + 1
            if (r10 != r4) goto L_0x03c1
            int r4 = r0.mEndDay
            if (r15 < r4) goto L_0x0380
            boolean r4 = r0.isNextMonthEndMonth()
            if (r4 != 0) goto L_0x0386
        L_0x0380:
            r24 = r2
            r7 = r3
            r18 = r11
            goto L_0x03c7
        L_0x0386:
            int r4 = r0.mEndDay
            if (r15 != r4) goto L_0x03c1
            float r4 = (float) r9
            float r4 = r4 - r27
            boolean r5 = r0.mIsRTL
            if (r5 == 0) goto L_0x0393
            float r5 = (float) r3
            goto L_0x0396
        L_0x0393:
            int r5 = r3 - r13
            float r5 = (float) r5
        L_0x0396:
            int r6 = r0.mDaySelectedCircleSize
            float r7 = (float) r6
            float r7 = r4 - r7
            float r1 = (float) r13
            float r1 = r1 + r5
            r22 = 2
            int r6 = r6 * 2
            float r6 = (float) r6
            float r6 = r6 + r7
            r24 = r2
            r2 = r5
            r5 = r6
            android.graphics.Paint r6 = r0.mDayNumberSelectedPaint
            r18 = r7
            r7 = r3
            r3 = r18
            r18 = r11
            r11 = r4
            r4 = r1
            r1 = r38
            r1.drawRect(r2, r3, r4, r5, r6)
            float r2 = (float) r7
            int r3 = r0.mDaySelectedCircleSize
            float r3 = (float) r3
            android.graphics.Paint r4 = r0.mDayNumberSelectedPaint
            r1.drawCircle(r2, r11, r3, r4)
            goto L_0x03e5
        L_0x03c1:
            r24 = r2
            r7 = r3
            r18 = r11
            goto L_0x03e5
        L_0x03c7:
            int r3 = r7 - r13
            float r2 = (float) r3
            float r3 = (float) r9
            float r3 = r3 - r27
            int r4 = r0.mDaySelectedCircleSize
            float r5 = (float) r4
            float r3 = r3 - r5
            int r5 = r13 * 2
            float r5 = (float) r5
            float r5 = r5 + r2
            r22 = 2
            int r4 = r4 * 2
            float r4 = (float) r4
            float r4 = r4 + r3
            android.graphics.Paint r6 = r0.mDayNumberSelectedPaint
            r36 = r5
            r5 = r4
            r4 = r36
            r1.drawRect(r2, r3, r4, r5, r6)
        L_0x03e5:
            boolean r2 = r0.mIsLunar
            if (r2 != 0) goto L_0x0404
            java.util.Calendar r2 = r0.mTempDate
            r2.clear()
            java.util.Calendar r2 = r0.mTempDate
            r2.set(r12, r8, r15)
            java.util.Calendar r2 = r0.mTempDate
            java.util.Calendar r3 = r0.mMaxDate
            boolean r2 = r2.after(r3)
            if (r2 == 0) goto L_0x0404
            android.graphics.Paint r2 = r0.mDayNumberPaint
            int r3 = r0.mDayNumberDisabledAlpha
            r2.setAlpha(r3)
        L_0x0404:
            boolean r2 = r0.isTodayDate(r12, r8, r15)
            if (r2 == 0) goto L_0x0420
            android.graphics.Paint r2 = r0.mTodayDateMarkPaint
            int r3 = r0.mPrevNextMonthDayNumberAlpha
            r2.setAlpha(r3)
            float r2 = (float) r7
            float r3 = (float) r9
            float r3 = r3 - r27
            int r4 = r0.mDaySelectedCircleSize
            int r5 = r0.mDaySelectedCircleStroke
            int r4 = r4 - r5
            float r4 = (float) r4
            android.graphics.Paint r5 = r0.mTodayDateMarkPaint
            r1.drawCircle(r2, r3, r4, r5)
        L_0x0420:
            android.graphics.Paint r2 = r0.mDayNumberPaint
            boolean r3 = r0.mIsHcfEnabled
            if (r3 == 0) goto L_0x043b
            int r3 = r2.getAlpha()
            int r4 = r0.mDayNumberDisabledAlpha
            if (r3 == r4) goto L_0x043b
            android.graphics.Paint r2 = r0.mHcfEnabledDayNumberPaint
            android.graphics.Paint r3 = r0.mDayNumberPaint
            int r3 = r3.getColor()
            r2.setColor(r3)
            android.graphics.Paint r2 = r0.mHcfEnabledDayNumberPaint
        L_0x043b:
            int r3 = r0.mMode
            if (r3 == 0) goto L_0x0456
            int r3 = r0.mNumCells
            r21 = 1
            int r3 = r3 + 1
            if (r10 != r3) goto L_0x0456
            int r3 = r0.mEndDay
            if (r15 <= r3) goto L_0x0451
            boolean r3 = r0.isNextMonthEndMonth()
            if (r3 != 0) goto L_0x0456
        L_0x0451:
            int r3 = r0.mSelectedDayNumberTextColor
            r2.setColor(r3)
        L_0x0456:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r15)
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r3 = java.lang.String.format(r14, r3)
            float r4 = (float) r7
            float r5 = (float) r9
            r1.drawText(r3, r4, r5, r2)
            int r2 = r23 + 1
            r3 = 7
            if (r2 != r3) goto L_0x0474
            int r2 = r0.mWeekHeight
            int r9 = r9 + r2
            int r2 = r24 + 1
            r23 = r20
            goto L_0x0478
        L_0x0474:
            r23 = r2
            r2 = r24
        L_0x0478:
            int r15 = r15 + 1
            r11 = r18
            r7 = 11
            goto L_0x0332
        L_0x0480:
            r18 = r11
            if (r30 <= 0) goto L_0x062a
            boolean r2 = r0.mIsFirstMonth
            if (r2 != 0) goto L_0x062a
            java.util.Calendar r2 = java.util.Calendar.getInstance()
            r2.clear()
            int r3 = r0.mYear
            int r4 = r0.mMonth
            r10 = 1
            r2.set(r3, r4, r10)
            r7 = r30
            int r3 = -r7
            r8 = 5
            r2.add(r8, r3)
            int r3 = r0.mMonth
            int r3 = r3 - r10
            int r4 = r0.mYear
            if (r3 >= 0) goto L_0x04a9
            int r4 = r4 + -1
            r3 = 11
        L_0x04a9:
            int r2 = r2.get(r8)
            boolean r5 = r0.mIsLunar
            if (r5 == 0) goto L_0x04cf
            int r2 = r0.mYear
            int r3 = r0.mMonth
            boolean r4 = r0.mIsLeapMonth
            r21 = 1
            r4 = r4 ^ 1
            int r3 = r3 - r4
            if (r3 >= 0) goto L_0x04c2
            int r2 = r2 + -1
            r3 = 11
        L_0x04c2:
            r4 = r2
            boolean r2 = r0.mIsPrevMonthLeap
            int r2 = r0.getDaysInMonthLunar(r3, r4, r2)
            int r2 = r2 - r7
            int r2 = r2 + 1
        L_0x04cc:
            r9 = r3
            r10 = r4
            goto L_0x04d2
        L_0x04cf:
            r21 = 1
            goto L_0x04cc
        L_0x04d2:
            r11 = r2
            r12 = r20
        L_0x04d5:
            if (r12 >= r7) goto L_0x062a
            boolean r2 = r0.mIsRTL
            if (r2 == 0) goto L_0x04e9
            int r2 = 6 - r12
            r22 = 2
            int r2 = r2 * 2
            int r2 = r2 + 1
            int r2 = r2 * r13
            int r3 = r0.mPadding
        L_0x04e6:
            int r2 = r2 + r3
            r15 = r2
            goto L_0x04f1
        L_0x04e9:
            int r2 = r12 * 2
            int r2 = r2 + 1
            int r2 = r2 * r13
            int r3 = r0.mPadding
            goto L_0x04e6
        L_0x04f1:
            int r2 = r0.mWeekHeight
            r22 = 2
            int r2 = r2 * 2
            r16 = 3
            int r2 = r2 / 3
            int r3 = r0.mWeekStart
            int r3 = r3 + r12
            r28 = 7
            int r3 = r3 % 7
            android.graphics.Paint r4 = r0.mDayNumberPaint
            int[] r5 = r0.mDayColorSet
            r3 = r5[r3]
            r4.setColor(r3)
            android.graphics.Paint r3 = r0.mDayNumberPaint
            int r4 = r0.mPrevNextMonthDayNumberAlpha
            r3.setAlpha(r4)
            int r3 = r0.mMode
            if (r3 == 0) goto L_0x055f
            if (r18 != 0) goto L_0x055f
            int r3 = r0.mStartDay
            if (r11 > r3) goto L_0x0522
            boolean r3 = r0.isPrevMonthStartMonth()
            if (r3 != 0) goto L_0x0526
        L_0x0522:
            r8 = r2
            r30 = r7
            goto L_0x0563
        L_0x0526:
            int r3 = r0.mStartDay
            if (r11 != r3) goto L_0x055f
            float r3 = (float) r2
            float r3 = r3 - r27
            boolean r4 = r0.mIsRTL
            if (r4 == 0) goto L_0x0535
            int r4 = r15 - r13
            float r4 = (float) r4
            goto L_0x0536
        L_0x0535:
            float r4 = (float) r15
        L_0x0536:
            int r5 = r0.mDaySelectedCircleSize
            float r6 = (float) r5
            float r6 = r3 - r6
            float r8 = (float) r13
            float r8 = r8 + r4
            r22 = 2
            int r5 = r5 * 2
            float r5 = (float) r5
            float r5 = r5 + r6
            r19 = r3
            r3 = r6
            android.graphics.Paint r6 = r0.mDayNumberSelectedPaint
            r30 = r8
            r8 = r2
            r2 = r4
            r4 = r30
            r30 = r7
            r7 = r19
            r1.drawRect(r2, r3, r4, r5, r6)
            float r2 = (float) r15
            int r3 = r0.mDaySelectedCircleSize
            float r3 = (float) r3
            android.graphics.Paint r4 = r0.mDayNumberSelectedPaint
            r1.drawCircle(r2, r7, r3, r4)
            goto L_0x0581
        L_0x055f:
            r8 = r2
            r30 = r7
            goto L_0x0581
        L_0x0563:
            int r2 = r15 - r13
            float r2 = (float) r2
            float r3 = (float) r8
            float r3 = r3 - r27
            int r4 = r0.mDaySelectedCircleSize
            float r5 = (float) r4
            float r3 = r3 - r5
            int r5 = r13 * 2
            float r5 = (float) r5
            float r5 = r5 + r2
            r22 = 2
            int r4 = r4 * 2
            float r4 = (float) r4
            float r4 = r4 + r3
            android.graphics.Paint r6 = r0.mDayNumberSelectedPaint
            r36 = r5
            r5 = r4
            r4 = r36
            r1.drawRect(r2, r3, r4, r5, r6)
        L_0x0581:
            boolean r2 = r0.mIsLunar
            if (r2 != 0) goto L_0x05c0
            java.util.Calendar r2 = r0.mTempDate
            r2.clear()
            java.util.Calendar r2 = r0.mTempDate
            r2.set(r10, r9, r11)
            java.util.Calendar r2 = java.util.Calendar.getInstance()
            r2.clear()
            java.util.Calendar r3 = r0.mMinDate
            r4 = 1
            int r3 = r3.get(r4)
            java.util.Calendar r5 = r0.mMinDate
            r6 = 2
            int r5 = r5.get(r6)
            java.util.Calendar r7 = r0.mMinDate
            r4 = 5
            int r7 = r7.get(r4)
            r2.set(r3, r5, r7)
            java.util.Calendar r2 = r0.mTempDate
            java.util.Calendar r3 = r0.mMinDate
            boolean r2 = r2.before(r3)
            if (r2 == 0) goto L_0x05c2
            android.graphics.Paint r2 = r0.mDayNumberPaint
            int r3 = r0.mDayNumberDisabledAlpha
            r2.setAlpha(r3)
            goto L_0x05c2
        L_0x05c0:
            r4 = 5
            r6 = 2
        L_0x05c2:
            boolean r2 = r0.isTodayDate(r10, r9, r11)
            if (r2 == 0) goto L_0x05de
            android.graphics.Paint r2 = r0.mTodayDateMarkPaint
            int r3 = r0.mPrevNextMonthDayNumberAlpha
            r2.setAlpha(r3)
            float r2 = (float) r15
            float r3 = (float) r8
            float r3 = r3 - r27
            int r5 = r0.mDaySelectedCircleSize
            int r7 = r0.mDaySelectedCircleStroke
            int r5 = r5 - r7
            float r5 = (float) r5
            android.graphics.Paint r7 = r0.mTodayDateMarkPaint
            r1.drawCircle(r2, r3, r5, r7)
        L_0x05de:
            android.graphics.Paint r2 = r0.mDayNumberPaint
            boolean r3 = r0.mIsHcfEnabled
            if (r3 == 0) goto L_0x05f9
            int r3 = r2.getAlpha()
            int r5 = r0.mDayNumberDisabledAlpha
            if (r3 == r5) goto L_0x05f9
            android.graphics.Paint r2 = r0.mHcfEnabledDayNumberPaint
            android.graphics.Paint r3 = r0.mDayNumberPaint
            int r3 = r3.getColor()
            r2.setColor(r3)
            android.graphics.Paint r2 = r0.mHcfEnabledDayNumberPaint
        L_0x05f9:
            int r3 = r0.mMode
            if (r3 == 0) goto L_0x060e
            if (r18 != 0) goto L_0x060e
            int r3 = r0.mStartDay
            if (r11 >= r3) goto L_0x0609
            boolean r3 = r0.isPrevMonthStartMonth()
            if (r3 != 0) goto L_0x060e
        L_0x0609:
            int r3 = r0.mSelectedDayNumberTextColor
            r2.setColor(r3)
        L_0x060e:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r11)
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r3 = java.lang.String.format(r14, r3)
            float r5 = (float) r15
            float r7 = (float) r8
            r1.drawText(r3, r5, r7, r2)
            int r11 = r11 + 1
            int r12 = r12 + 1
            r8 = r4
            r7 = r30
            r21 = 1
            goto L_0x04d5
        L_0x062a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.picker.widget.SeslSimpleMonthView.drawDays(android.graphics.Canvas):void");
    }

    /* access modifiers changed from: private */
    public int findDayOffset() {
        int i2 = this.mDayOfWeekStart;
        int i7 = this.mWeekStart;
        if (i2 < i7) {
            i2 += 7;
        }
        return i2 - i7;
    }

    /* access modifiers changed from: private */
    public int getDayFromLocation(float f, float f5) {
        int i2 = this.mPadding;
        if (this.mIsRTL) {
            f = ((float) this.mCalendarWidth) - f;
        }
        float f8 = (float) i2;
        if (f < f8) {
            return -1;
        }
        int i7 = this.mCalendarWidth;
        if (f > ((float) (i2 + i7))) {
            return -1;
        }
        return ((((int) f5) / this.mWeekHeight) * 7) + (((int) (((f - f8) * 7.0f) / ((float) i7))) - findDayOffset()) + 1;
    }

    private static int getDaysInMonth(int i2, int i7) {
        switch (i2) {
            case 0:
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
                return 31;
            case 1:
                if (i7 % 4 != 0) {
                    return 28;
                }
                if (i7 % 100 != 0 || i7 % 400 == 0) {
                    return 29;
                }
                return 28;
            case 3:
            case 5:
            case 8:
            case 10:
                return 30;
            default:
                throw new IllegalArgumentException("Invalid Month");
        }
    }

    /* access modifiers changed from: private */
    public int getDaysInMonthLunar(int i2, int i7, boolean z) {
        int daysInMonth = getDaysInMonth(i2, i7);
        Object obj = this.mSolarLunarConverter;
        if (obj != null) {
            return SeslSolarLunarConverterReflector.getDayLengthOf(this.mPathClassLoader, obj, i7, i2, z);
        }
        Log.e("SeslSimpleMonthView", "getDaysInMonthLunar, mSolarLunarConverter is null");
        return daysInMonth;
    }

    private void initView() {
        Paint paint = new Paint();
        this.mDayNumberSelectedPaint = paint;
        paint.setAntiAlias(true);
        this.mDayNumberSelectedPaint.setColor(this.mSelectedDayColor);
        Paint paint2 = this.mDayNumberSelectedPaint;
        Paint.Align align = Paint.Align.CENTER;
        paint2.setTextAlign(align);
        this.mDayNumberSelectedPaint.setStrokeWidth((float) this.mDaySelectedCircleStroke);
        this.mDayNumberSelectedPaint.setFakeBoldText(true);
        Paint paint3 = this.mDayNumberSelectedPaint;
        Paint.Style style = Paint.Style.FILL;
        paint3.setStyle(style);
        Paint paint4 = new Paint(this.mDayNumberSelectedPaint);
        this.mAbnormalSelectedDayPaint = paint4;
        paint4.setColor(this.mNormalTextColor);
        this.mAbnormalSelectedDayPaint.setAlpha(this.mAbnormalStartEndDateBackgroundAlpha);
        Paint paint5 = new Paint();
        this.mDayNumberPaint = paint5;
        paint5.setAntiAlias(true);
        this.mDayNumberPaint.setTextSize((float) this.mMiniDayNumberTextSize);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 34) {
            this.mDayNumberPaint.setTypeface(Typeface.create(Typeface.create("sec", 0), 400, false));
        } else {
            this.mDayNumberPaint.setTypeface(Typeface.create("sec-roboto-light", 0));
        }
        this.mDayNumberPaint.setTextAlign(align);
        this.mDayNumberPaint.setStyle(style);
        this.mDayNumberPaint.setFakeBoldText(false);
        Paint paint6 = new Paint(this.mDayNumberPaint);
        this.mHcfEnabledDayNumberPaint = paint6;
        if (i2 >= 34) {
            this.mHcfEnabledDayNumberPaint.setTypeface(Typeface.create(Typeface.create("sec", 0), 600, false));
        } else {
            paint6.setTypeface(Typeface.create("sec-roboto-light", 1));
        }
        Paint paint7 = new Paint(this.mDayNumberPaint);
        this.mTodayDateMarkPaint = paint7;
        paint7.setColor(this.mNormalTextColor);
        this.mTodayDateMarkPaint.setAntiAlias(true);
        this.mTodayDateMarkPaint.setStrokeWidth((float) this.mDaySelectedCircleStroke);
        this.mTodayDateMarkPaint.setStyle(Paint.Style.STROKE);
    }

    private boolean isHighContrastFontEnabled() {
        return SeslViewReflector.isHighContrastTextEnabled(this);
    }

    private boolean isNextMonthEndMonth() {
        if (this.mIsLunar) {
            float f = (float) this.mMonth;
            float f5 = (float) this.mEndMonth;
            if (this.mIsLeapMonth) {
                f += 0.5f;
            }
            if (this.mIsLeapEndMonth == 1) {
                f5 += 0.5f;
            }
            float f8 = f5 - f;
            int i2 = this.mYear;
            int i7 = this.mEndYear;
            if (i2 != i7 || (f8 >= 1.0f && (f8 != 1.0f || this.mIsNextMonthLeap))) {
                if (i2 == i7 - 1) {
                    float f10 = f8 + 12.0f;
                    if (f10 < 1.0f || (f10 == 1.0f && !this.mIsNextMonthLeap)) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        }
        int i8 = this.mYear;
        int i10 = this.mEndYear;
        if ((i8 == i10 && this.mMonth == this.mEndMonth - 1) || (i8 == i10 - 1 && this.mMonth == 11 && this.mEndMonth == 0)) {
            return true;
        }
        return false;
    }

    private boolean isPrevMonthStartMonth() {
        if (this.mIsLunar) {
            float f = (float) this.mMonth;
            float f5 = (float) this.mStartMonth;
            if (this.mIsLeapMonth) {
                f += 0.5f;
            }
            if (this.mIsLeapStartMonth == 1) {
                f5 += 0.5f;
            }
            float f8 = f - f5;
            int i2 = this.mYear;
            int i7 = this.mStartYear;
            if (i2 != i7 || (f8 >= 1.0f && (f8 != 1.0f || this.mIsPrevMonthLeap))) {
                if (i2 == i7 + 1) {
                    float f10 = f8 + 12.0f;
                    if (f10 < 1.0f || (f10 == 1.0f && !this.mIsPrevMonthLeap)) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        }
        int i8 = this.mYear;
        int i10 = this.mStartYear;
        if ((i8 == i10 && this.mMonth == this.mStartMonth + 1) || (i8 == i10 + 1 && this.mMonth == 0 && this.mStartMonth == 11)) {
            return true;
        }
        return false;
    }

    private boolean isRTL() {
        Locale locale = Locale.getDefault();
        if ("ur".equals(locale.getLanguage())) {
            return false;
        }
        byte directionality = Character.getDirectionality(locale.getDisplayName(locale).charAt(0));
        if (directionality == 1 || directionality == 2) {
            return true;
        }
        return false;
    }

    private boolean isTodayDate(int i2, int i7, int i8) {
        PathClassLoader pathClassLoader;
        Object obj;
        int i10 = this.mTodayDate.get(1);
        int i11 = this.mTodayDate.get(2);
        int i12 = this.mTodayDate.get(5);
        if (!(!this.mIsLunar || (pathClassLoader = this.mPathClassLoader) == null || (obj = this.mSolarLunarConverter) == null)) {
            SeslSolarLunarConverterReflector.convertSolarToLunar(pathClassLoader, obj, i10, i11, i12);
            i10 = SeslSolarLunarConverterReflector.getYear(this.mPathClassLoader, this.mSolarLunarConverter);
            i11 = SeslSolarLunarConverterReflector.getMonth(this.mPathClassLoader, this.mSolarLunarConverter);
            i12 = SeslSolarLunarConverterReflector.getDay(this.mPathClassLoader, this.mSolarLunarConverter);
        }
        if (i10 == i2 && i11 == i7 && i12 == i8) {
            return true;
        }
        return false;
    }

    private static boolean isValidDayOfWeek(int i2) {
        if (i2 < 1 || i2 > 7) {
            return false;
        }
        return true;
    }

    private static boolean isValidMonth(int i2) {
        if (i2 < 0 || i2 > 11) {
            return false;
        }
        return true;
    }

    private int makeMeasureSpec(int i2, int i7) {
        if (i7 == -1) {
            return i2;
        }
        int size = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            int min = Math.min(size, i7);
            this.mCalendarWidth = min;
            return View.MeasureSpec.makeMeasureSpec(min, 1073741824);
        } else if (mode == 0) {
            return View.MeasureSpec.makeMeasureSpec(i7, 1073741824);
        } else {
            if (mode == 1073741824) {
                this.mCalendarWidth = size;
                return i2;
            }
            throw new IllegalArgumentException(C0086a.i(mode, "Unknown measure mode: "));
        }
    }

    /* access modifiers changed from: private */
    public void onDayClick(int i2, int i7, int i8) {
        if (this.mOnDayClickListener != null) {
            playSoundEffect(0);
            this.mOnDayClickListener.onDayClick(this, i2, i7, i8);
        }
        this.mTouchHelper.sendEventForVirtualView(i8 + findDayOffset(), 1);
    }

    /* access modifiers changed from: private */
    public void onDeactivatedDayClick(int i2, int i7, int i8, boolean z) {
        int i10;
        SeslSimpleMonthView seslSimpleMonthView;
        if (!this.mIsLunar) {
            this.mTempDate.clear();
            this.mTempDate.set(i2, i7, i8);
            if (z) {
                Calendar instance = Calendar.getInstance();
                instance.clear();
                instance.set(this.mMinDate.get(1), this.mMinDate.get(2), this.mMinDate.get(5));
                if (this.mTempDate.before(instance)) {
                    return;
                }
            } else if (this.mTempDate.after(this.mMaxDate)) {
                return;
            }
        }
        if (this.mOnDeactivatedDayClickListener != null) {
            playSoundEffect(0);
            seslSimpleMonthView = this;
            i10 = i8;
            this.mOnDeactivatedDayClickListener.onDeactivatedDayClick(seslSimpleMonthView, i2, i7, i10, this.mIsLeapMonth, z);
        } else {
            seslSimpleMonthView = this;
            i10 = i8;
        }
        seslSimpleMonthView.mTouchHelper.sendEventForVirtualView(i10, 1);
    }

    public void clearAccessibilityFocus() {
        this.mTouchHelper.clearFocusedVirtualView();
    }

    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (this.mTouchHelper.dispatchHoverEvent(motionEvent) || super.dispatchHoverEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public int getDayOfWeekStart() {
        return this.mDayOfWeekStart - (this.mWeekStart - 1);
    }

    public int getNumDays() {
        return 7;
    }

    public int getWeekStart() {
        return this.mWeekStart;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mIsRTL = isRTL();
        this.mTouchHelper.invalidateRoot();
        Resources resources = this.mContext.getResources();
        this.mWeekHeight = resources.getDimensionPixelOffset(R$dimen.sesl_date_picker_calendar_week_height);
        this.mDaySelectedCircleSize = resources.getDimensionPixelSize(R$dimen.sesl_date_picker_selected_day_circle_radius);
        this.mMiniDayNumberTextSize = resources.getDimensionPixelSize(R$dimen.sesl_date_picker_day_number_text_size);
        initView();
    }

    public void onDraw(Canvas canvas) {
        drawDays(canvas);
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        int i11;
        int i12;
        boolean z3 = this.mLostAccessibilityFocus;
        if (!z3 && this.mLastAccessibilityFocusedView == -1 && (i12 = this.mSelectedDay) != -1) {
            this.mTouchHelper.sendEventForVirtualView(i12 + findDayOffset(), 32768);
        } else if (!z3 && (i11 = this.mLastAccessibilityFocusedView) != -1) {
            this.mTouchHelper.sendEventForVirtualView(i11 + findDayOffset(), 32768);
        }
        if (z) {
            this.mTouchHelper.invalidateRoot();
        }
        super.onLayout(z, i2, i7, i8, i10);
    }

    public void onMeasure(int i2, int i7) {
        super.onMeasure(makeMeasureSpec(i2, this.mCalendarWidth), i7);
    }

    public void onSizeChanged(int i2, int i7, int i8, int i10) {
        this.mTouchHelper.invalidateRoot();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            int dayFromLocation = getDayFromLocation(motionEvent.getX(), motionEvent.getY());
            if ((this.mIsFirstMonth && dayFromLocation < this.mEnabledDayStart) || (this.mIsLastMonth && dayFromLocation > this.mEnabledDayEnd)) {
                return true;
            }
            int i2 = 11;
            if (dayFromLocation > 0) {
                int i7 = this.mNumCells;
                if (dayFromLocation <= i7) {
                    onDayClick(this.mYear, this.mMonth, dayFromLocation);
                } else if (this.mIsLunar) {
                    int i8 = this.mYear;
                    int i10 = this.mMonth + (this.mIsNextMonthLeap ^ true ? 1 : 0);
                    if (i10 > 11) {
                        i8++;
                        i10 = 0;
                    }
                    onDeactivatedDayClick(i8, i10, dayFromLocation - i7, false);
                } else {
                    Calendar instance = Calendar.getInstance();
                    instance.clear();
                    instance.set(this.mYear, this.mMonth, this.mNumCells);
                    instance.add(5, dayFromLocation - this.mNumCells);
                    onDeactivatedDayClick(instance.get(1), instance.get(2), instance.get(5), false);
                }
            } else if (this.mIsLunar) {
                int i11 = this.mYear;
                int i12 = this.mMonth - (this.mIsLeapMonth ^ true ? 1 : 0);
                if (i12 < 0) {
                    i11--;
                } else {
                    i2 = i12;
                }
                onDeactivatedDayClick(i11, i2, getDaysInMonthLunar(i2, i11, this.mIsPrevMonthLeap) + dayFromLocation, true);
            } else {
                Calendar instance2 = Calendar.getInstance();
                instance2.clear();
                instance2.set(this.mYear, this.mMonth, 1);
                instance2.add(5, dayFromLocation - 1);
                onDeactivatedDayClick(instance2.get(1), instance2.get(2), instance2.get(5), true);
            }
        }
        return true;
    }

    public void setAccessibilityDelegate(View.AccessibilityDelegate accessibilityDelegate) {
        if (!this.mLockAccessibilityDelegate) {
            super.setAccessibilityDelegate(accessibilityDelegate);
        }
    }

    public void setFirstMonth() {
        this.mIsFirstMonth = true;
    }

    public void setLastMonth() {
        this.mIsLastMonth = true;
    }

    public void setLunar(boolean z, boolean z3, PathClassLoader pathClassLoader) {
        this.mIsLunar = z;
        this.mIsLeapMonth = z3;
        if (z && this.mSolarLunarConverter == null) {
            this.mPathClassLoader = pathClassLoader;
            this.mSolarLunarConverter = SeslFeatureReflector.getSolarLunarConverter(pathClassLoader);
        }
    }

    public void setMonthParams(int i2, int i7, int i8, int i10, int i11, int i12, Calendar calendar, Calendar calendar2, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21) {
        Object obj;
        Calendar calendar3 = calendar;
        Calendar calendar4 = calendar2;
        this.mMode = i21;
        if (this.mWeekHeight < 10) {
            this.mWeekHeight = 10;
        }
        this.mSelectedDay = i2;
        if (isValidMonth(i7)) {
            this.mMonth = i7;
        }
        this.mYear = i8;
        this.mCalendar.clear();
        this.mCalendar.set(2, this.mMonth);
        this.mCalendar.set(1, this.mYear);
        this.mCalendar.set(5, 1);
        this.mMinDate = calendar3;
        this.mMaxDate = calendar4;
        if (!this.mIsLunar || (obj = this.mSolarLunarConverter) == null) {
            this.mDayOfWeekStart = this.mCalendar.get(7);
            this.mNumCells = getDaysInMonth(this.mMonth, this.mYear);
        } else {
            SeslSolarLunarConverterReflector.convertLunarToSolar(this.mPathClassLoader, obj, this.mYear, this.mMonth, 1, this.mIsLeapMonth);
            this.mDayOfWeekStart = SeslSolarLunarConverterReflector.getWeekday(this.mPathClassLoader, this.mSolarLunarConverter, SeslSolarLunarConverterReflector.getYear(this.mPathClassLoader, this.mSolarLunarConverter), SeslSolarLunarConverterReflector.getMonth(this.mPathClassLoader, this.mSolarLunarConverter), SeslSolarLunarConverterReflector.getDay(this.mPathClassLoader, this.mSolarLunarConverter)) + 1;
            this.mNumCells = getDaysInMonthLunar(this.mMonth, this.mYear, this.mIsLeapMonth);
        }
        if (isValidDayOfWeek(i10)) {
            this.mWeekStart = i10;
        } else {
            this.mWeekStart = this.mCalendar.getFirstDayOfWeek();
        }
        int i22 = (this.mMonth == calendar3.get(2) && this.mYear == calendar3.get(1)) ? calendar3.get(5) : i11;
        int i23 = (this.mMonth == calendar4.get(2) && this.mYear == calendar4.get(1)) ? calendar4.get(5) : i12;
        if (i22 > 0 && i23 < 32) {
            this.mEnabledDayStart = i22;
        }
        if (i23 > 0 && i23 < 32 && i23 >= i22) {
            this.mEnabledDayEnd = i23;
        }
        this.mTouchHelper.invalidateRoot();
        this.mStartYear = i13;
        this.mStartMonth = i14;
        this.mStartDay = i15;
        this.mIsLeapStartMonth = i16;
        this.mEndYear = i17;
        this.mEndMonth = i18;
        this.mEndDay = i19;
        this.mIsLeapEndMonth = i20;
    }

    public void setNextMonthLeap() {
        this.mIsNextMonthLeap = true;
    }

    public void setOnDayClickListener(OnDayClickListener onDayClickListener) {
        this.mOnDayClickListener = onDayClickListener;
    }

    public void setOnDeactivatedDayClickListener(OnDeactivatedDayClickListener onDeactivatedDayClickListener) {
        this.mOnDeactivatedDayClickListener = onDeactivatedDayClickListener;
    }

    public void setPrevMonthLeap() {
        this.mIsPrevMonthLeap = true;
    }

    public void setTextColor(String str) {
        if (str == null) {
            str = SeslCscFeatureReflector.getString("CscFeature_Calendar_SetColorOfDays", "XXXXXXR");
        }
        for (int i2 = 0; i2 < 7; i2++) {
            char charAt = str.charAt(i2);
            int i7 = (i2 + 2) % 7;
            if (charAt == 'R') {
                this.mDayColorSet[i7] = this.mSundayTextColor;
            } else if (charAt == 'B') {
                this.mDayColorSet[i7] = this.mSaturdayTextColor;
            } else {
                this.mDayColorSet[i7] = this.mNormalTextColor;
            }
        }
    }

    public SeslSimpleMonthView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16843612);
    }

    /* JADX INFO: finally extract failed */
    public SeslSimpleMonthView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet);
        this.mDayColorSet = new int[7];
        this.mMode = 0;
        this.mDayOfWeekStart = 0;
        this.mPadding = 0;
        this.mSelectedDay = -1;
        this.mWeekStart = 1;
        this.mNumDays = 7;
        this.mNumCells = 7;
        this.mEnabledDayStart = 1;
        this.mEnabledDayEnd = 31;
        this.mIsHcfEnabled = false;
        this.mCalendar = Calendar.getInstance();
        this.mMinDate = Calendar.getInstance();
        this.mMaxDate = Calendar.getInstance();
        this.mTempDate = Calendar.getInstance();
        this.mTodayDate = Calendar.getInstance();
        this.mIsLunar = false;
        this.mIsLeapMonth = false;
        this.mPathClassLoader = null;
        this.mIsFirstMonth = false;
        this.mIsLastMonth = false;
        this.mIsPrevMonthLeap = false;
        this.mIsNextMonthLeap = false;
        this.mLastAccessibilityFocusedView = -1;
        this.mLostAccessibilityFocus = false;
        this.mContext = context;
        this.mIsRTL = isRTL();
        Resources resources = context.getResources();
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.colorPrimary, typedValue, true);
        int i7 = typedValue.resourceId;
        if (i7 != 0) {
            this.mSelectedDayColor = resources.getColor(i7);
        } else {
            this.mSelectedDayColor = typedValue.data;
        }
        this.mSundayTextColor = resources.getColor(R$color.sesl_date_picker_sunday_number_text_color_light);
        this.mSaturdayTextColor = resources.getColor(R$color.sesl_date_picker_saturday_text_color_light);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.DatePicker, i2, 0);
        try {
            this.mNormalTextColor = obtainStyledAttributes.getColor(R$styleable.DatePicker_dayNumberTextColor, resources.getColor(R$color.sesl_date_picker_normal_day_number_text_color_light));
            this.mSelectedDayNumberTextColor = obtainStyledAttributes.getColor(R$styleable.DatePicker_selectedDayNumberTextColor, resources.getColor(R$color.sesl_date_picker_selected_day_number_text_color_light));
            this.mDayNumberDisabledAlpha = obtainStyledAttributes.getInteger(R$styleable.DatePicker_dayNumberDisabledAlpha, resources.getInteger(R$integer.sesl_day_number_disabled_alpha_light));
            obtainStyledAttributes.recycle();
            this.mWeekHeight = resources.getDimensionPixelOffset(R$dimen.sesl_date_picker_calendar_week_height);
            this.mDaySelectedCircleSize = resources.getDimensionPixelSize(R$dimen.sesl_date_picker_selected_day_circle_radius);
            this.mDaySelectedCircleStroke = resources.getDimensionPixelSize(R$dimen.sesl_date_picker_selected_day_circle_stroke);
            this.mMiniDayNumberTextSize = resources.getDimensionPixelSize(R$dimen.sesl_date_picker_day_number_text_size);
            this.mCalendarWidth = resources.getDimensionPixelOffset(R$dimen.sesl_date_picker_calendar_view_width);
            this.mPadding = resources.getDimensionPixelOffset(R$dimen.sesl_date_picker_calendar_view_padding);
            MonthViewTouchHelper monthViewTouchHelper = new MonthViewTouchHelper(this);
            this.mTouchHelper = monthViewTouchHelper;
            ViewCompat.setAccessibilityDelegate(this, monthViewTouchHelper);
            setImportantForAccessibility(1);
            this.mLockAccessibilityDelegate = true;
            if (Settings.System.getString(context.getContentResolver(), "current_sec_active_themepackage") != null) {
                this.mDayNumberDisabledAlpha = resources.getInteger(R$integer.sesl_day_number_theme_disabled_alpha);
            }
            this.mPrevNextMonthDayNumberAlpha = resources.getInteger(R$integer.sesl_day_number_theme_disabled_alpha);
            this.mAbnormalStartEndDateBackgroundAlpha = resources.getInteger(R$integer.sesl_date_picker_abnormal_start_end_date_background_alpha);
            initView();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }
}
