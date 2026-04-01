package androidx.picker.widget;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.OverScroller;
import android.widget.Scroller;
import androidx.appcompat.R$attr;
import androidx.appcompat.util.SeslMisc;
import androidx.core.content.res.ResourcesCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.picker.R$color;
import androidx.picker.R$dimen;
import androidx.picker.R$id;
import androidx.picker.R$layout;
import androidx.picker.R$string;
import androidx.picker.R$styleable;
import androidx.picker.util.SeslAnimationListener;
import androidx.picker.util.SeslPickerBasicUtils;
import androidx.picker.widget.SeslSpinningDatePickerSpinner;
import androidx.reflect.content.res.SeslCompatibilityInfoReflector;
import androidx.reflect.content.res.SeslConfigurationReflector;
import androidx.reflect.graphics.SeslPaintReflector;
import androidx.reflect.media.SeslAudioManagerReflector;
import androidx.reflect.view.SeslHapticFeedbackConstantsReflector;
import androidx.reflect.view.SeslViewReflector;
import c0.C0086a;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import dalvik.system.PathClassLoader;
import i.C0212a;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SeslSpinningDatePickerSpinnerDelegate extends SeslSpinningDatePickerSpinner.AbsDatePickerDelegate {
    private final PathInterpolator ALPHA_PATH_INTERPOLATOR;
    private final PathInterpolator SIZE_PATH_INTERPOLATOR;
    /* access modifiers changed from: private */
    public AccessibilityManager mAccessibilityManager;
    private AccessibilityNodeProviderImpl mAccessibilityNodeProvider;
    private float mActivatedAlpha;
    /* access modifiers changed from: private */
    public final Scroller mAdjustScroller;
    /* access modifiers changed from: private */
    public float mAlpha;
    private AudioManager mAudioManager;
    /* access modifiers changed from: private */
    public int mBottomSelectionDividerBottom;
    private ChangeCurrentByOneFromLongPressCommand mChangeCurrentByOneFromLongPressCommand;
    private int mChangeValueBy = 1;
    private ValueAnimator mColorInAnimator;
    private ValueAnimator mColorOutAnimator;
    private ValueAnimator.AnimatorUpdateListener mColorUpdateListener;
    private final boolean mComputeMaxWidth;
    private int mCurrentScrollOffset;
    /* access modifiers changed from: private */
    public final Scroller mCustomScroller;
    private boolean mCustomTypefaceSet = false;
    /* access modifiers changed from: private */
    public boolean mDecrementVirtualButtonPressed;
    private final Typeface mDefaultTypeface;
    private ValueAnimator mFadeInAnimator;
    private ValueAnimator mFadeOutAnimator;
    /* access modifiers changed from: private */
    public Scroller mFlingScroller;
    private SeslSpinningDatePickerSpinner.Formatter mFormatter;
    /* access modifiers changed from: private */
    public OverScroller mGravityScroller;
    private HapticPreDrawListener mHapticPreDrawListener;
    private Typeface mHcfFocusedTypefaceBold;
    private final int mHcfUnfocusedTextSizeDiff;
    private final float mHeightRatio;
    private FloatValueHolder mHolder;
    private float mIdleAlpha;
    private boolean mIgnoreMoveEvents;
    /* access modifiers changed from: private */
    public boolean mIncrementVirtualButtonPressed;
    private float mInitialAlpha;
    private int mInitialScrollOffset = Integer.MIN_VALUE;
    private final EditText mInputText;
    private boolean mIsHcfEnabled;
    private boolean mIsLongClicked = false;
    private boolean mIsLongPressed = false;
    /* access modifiers changed from: private */
    public boolean mIsLunar;
    /* access modifiers changed from: private */
    public boolean mIsStartingAnimation = false;
    private boolean mIsValueChanged = false;
    private long mLastDownEventTime;
    private float mLastDownEventY;
    private float mLastDownOrMoveEventY;
    /* access modifiers changed from: private */
    public int mLastFocusedChildVirtualViewId;
    /* access modifiers changed from: private */
    public int mLastHoveredChildVirtualViewId;
    private final Typeface mLegacyTypeface;
    /* access modifiers changed from: private */
    public final Scroller mLinearScroller;
    private String[] mLongMonths;
    private int mLongPressCount;
    /* access modifiers changed from: private */
    public long mLongPressUpdateInterval = 300;
    private boolean mLongPressed_FIRST_SCROLL;
    private boolean mLongPressed_SECOND_SCROLL;
    private boolean mLongPressed_THIRD_SCROLL;
    private final int mMaxHeight;
    /* access modifiers changed from: private */
    public Calendar mMaxValue;
    private int mMaxWidth;
    private int mMaximumFlingVelocity;
    private final int mMinHeight;
    /* access modifiers changed from: private */
    public Calendar mMinValue;
    private final int mMinWidth;
    private int mMinimumFlingVelocity;
    private int mModifiedTxtHeight;
    private PathClassLoader mPathClassLoader = null;
    private boolean mPerformClickOnTap;
    /* access modifiers changed from: private */
    public String mPickerContentDescription;
    private int mPickerSoundIndex;
    private Typeface mPickerSubTypeface;
    private Typeface mPickerTypeface;
    private int mPickerVibrateIndex;
    private final PressedStateHelper mPressedStateHelper;
    /* access modifiers changed from: private */
    public int mPreviousScrollerY;
    /* access modifiers changed from: private */
    public float mPreviousSpringY;
    /* access modifiers changed from: private */
    public boolean mReservedStartAnimation = false;
    private int mScrollState = 0;
    /* access modifiers changed from: private */
    public final int mSelectionDividerHeight;
    /* access modifiers changed from: private */
    public int mSelectorElementHeight;
    private final HashMap<Calendar, String> mSelectorIndexToStringCache = new HashMap<>();
    private final Calendar[] mSelectorIndices = new Calendar[5];
    private int mSelectorTextGapHeight;
    private Paint mSelectorWheelPaint;
    private String[] mShortMonths;
    private boolean mSkipNumbers;
    private Object mSolarLunarConverter = null;
    private SpringAnimation mSpringAnimation;
    private DynamicAnimation.OnAnimationEndListener mSpringAnimationEndListener;
    private DynamicAnimation.OnAnimationUpdateListener mSpringAnimationUpdateListener;
    /* access modifiers changed from: private */
    public boolean mSpringFlingRunning;
    /* access modifiers changed from: private */
    public int mTextColor;
    private final int mTextColorIdle;
    private final int mTextColorScrolling;
    private int mTextSize;
    /* access modifiers changed from: private */
    public int mTopSelectionDividerTop;
    private int mTouchSlop;
    private ValueAnimator.AnimatorUpdateListener mUpdateListener;
    /* access modifiers changed from: private */
    public Calendar mValue;
    private int mValueChangeOffset;
    private VelocityTracker mVelocityTracker;
    private final Drawable mVirtualButtonFocusedDrawable;
    /* access modifiers changed from: private */
    public boolean mWrapSelectorWheel;
    private boolean mWrapSelectorWheelPreferred = true;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class AccessibilityNodeProviderImpl extends AccessibilityNodeProvider {
        private int mAccessibilityFocusedView = Integer.MIN_VALUE;
        private final int[] mTempArray = new int[2];
        private final Rect mTempRect = new Rect();

        public AccessibilityNodeProviderImpl() {
        }

        private AccessibilityNodeInfo createAccessibilityNodeInfoForDatePickerWidget(int i2, int i7, int i8, int i10) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
            obtain.setClassName(SeslSpinningDatePickerSpinner.class.getName());
            obtain.setPackageName(SeslSpinningDatePickerSpinnerDelegate.this.mContext.getPackageName());
            obtain.setSource(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator);
            if (hasVirtualDecrementButton()) {
                obtain.addChild(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator, 1);
            }
            obtain.addChild(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator, 2);
            if (hasVirtualIncrementButton()) {
                obtain.addChild(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator, 3);
            }
            obtain.setParent((View) SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.getParentForAccessibility());
            obtain.setEnabled(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.isEnabled());
            obtain.setScrollable(true);
            float field_applicationScale = SeslCompatibilityInfoReflector.getField_applicationScale(SeslSpinningDatePickerSpinnerDelegate.this.mContext.getResources());
            Rect rect = this.mTempRect;
            rect.set(i2, i7, i8, i10);
            scaleRect(rect, field_applicationScale);
            obtain.setBoundsInParent(rect);
            obtain.setVisibleToUser(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.isVisibleToUserWrapper());
            int[] iArr = this.mTempArray;
            SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.getLocationOnScreen(iArr);
            rect.offset(iArr[0], iArr[1]);
            scaleRect(rect, field_applicationScale);
            obtain.setBoundsInScreen(rect);
            if (this.mAccessibilityFocusedView != -1) {
                obtain.addAction(64);
            } else {
                obtain.addAction(128);
            }
            if (SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.isEnabled()) {
                if (SeslSpinningDatePickerSpinnerDelegate.this.getWrapSelectorWheel() || SeslSpinningDatePickerSpinnerDelegate.this.getValue().compareTo(SeslSpinningDatePickerSpinnerDelegate.this.getMaxValue()) < 0) {
                    obtain.addAction(4096);
                }
                if (SeslSpinningDatePickerSpinnerDelegate.this.getWrapSelectorWheel() || SeslSpinningDatePickerSpinnerDelegate.this.getValue().compareTo(SeslSpinningDatePickerSpinnerDelegate.this.getMinValue()) > 0) {
                    obtain.addAction(SerializeOptions.SORT);
                }
            }
            return obtain;
        }

        private AccessibilityNodeInfo createAccessibilityNodeInfoForVirtualButton(int i2, String str, int i7, int i8, int i10, int i11) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
            obtain.setClassName(Button.class.getName());
            obtain.setPackageName(SeslSpinningDatePickerSpinnerDelegate.this.mContext.getPackageName());
            obtain.setSource(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator, i2);
            obtain.setParent(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator);
            obtain.setText(str);
            obtain.setClickable(true);
            obtain.setLongClickable(true);
            obtain.setEnabled(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.isEnabled());
            Rect rect = this.mTempRect;
            rect.set(i7, i8, i10, i11);
            obtain.setVisibleToUser(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.isVisibleToUserWrapper(rect));
            obtain.setBoundsInParent(rect);
            int[] iArr = this.mTempArray;
            SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.getLocationOnScreen(iArr);
            rect.offset(iArr[0], iArr[1]);
            obtain.setBoundsInScreen(rect);
            if (this.mAccessibilityFocusedView != i2) {
                obtain.addAction(64);
            } else {
                obtain.addAction(128);
            }
            if (SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.isEnabled()) {
                obtain.addAction(16);
            }
            return obtain;
        }

        private AccessibilityNodeInfo createAccessibiltyNodeInfoForCenter(int i2, int i7, int i8, int i10) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
            obtain.setPackageName(SeslSpinningDatePickerSpinnerDelegate.this.mContext.getPackageName());
            obtain.setSource(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator, 2);
            obtain.setParent(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator);
            obtain.setText(getVirtualCurrentButtonText() + SeslSpinningDatePickerSpinnerDelegate.this.mContext.getString(R$string.sesl_date_picker_switch_to_calendar_description));
            obtain.setClickable(true);
            obtain.setEnabled(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.isEnabled());
            if (this.mAccessibilityFocusedView != 2) {
                obtain.setAccessibilityFocused(false);
                obtain.addAction(64);
            } else {
                obtain.setAccessibilityFocused(true);
                obtain.addAction(128);
            }
            Rect rect = this.mTempRect;
            rect.set(i2, i7, i8, i10);
            obtain.setVisibleToUser(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.isVisibleToUserWrapper(rect));
            obtain.setBoundsInParent(rect);
            int[] iArr = this.mTempArray;
            SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.getLocationOnScreen(iArr);
            rect.offset(iArr[0], iArr[1]);
            obtain.setBoundsInScreen(rect);
            return obtain;
        }

        private void findAccessibilityNodeInfosByTextInChild(String str, int i2, List<AccessibilityNodeInfo> list) {
            if (i2 == 1) {
                String virtualDecrementButtonText = getVirtualDecrementButtonText();
                if (!TextUtils.isEmpty(virtualDecrementButtonText) && virtualDecrementButtonText.toLowerCase().contains(str)) {
                    list.add(createAccessibilityNodeInfo(1));
                }
            } else if (i2 == 2) {
                String virtualCurrentButtonText = getVirtualCurrentButtonText();
                if (!TextUtils.isEmpty(virtualCurrentButtonText) && virtualCurrentButtonText.toLowerCase().contains(str)) {
                    list.add(createAccessibilityNodeInfo(2));
                }
            } else if (i2 == 3) {
                String virtualIncrementButtonText = getVirtualIncrementButtonText();
                if (!TextUtils.isEmpty(virtualIncrementButtonText) && virtualIncrementButtonText.toLowerCase().contains(str)) {
                    list.add(createAccessibilityNodeInfo(3));
                }
            }
        }

        /* access modifiers changed from: private */
        public String getVirtualCurrentButtonText() {
            Calendar calendar = (Calendar) SeslSpinningDatePickerSpinnerDelegate.this.mValue.clone();
            if (SeslSpinningDatePickerSpinnerDelegate.this.mWrapSelectorWheel) {
                calendar = SeslSpinningDatePickerSpinnerDelegate.this.getWrappedSelectorIndex(calendar);
            }
            if (calendar.compareTo(SeslSpinningDatePickerSpinnerDelegate.this.mMaxValue) > 0) {
                return null;
            }
            if (SeslSpinningDatePickerSpinnerDelegate.this.mIsLunar) {
                return SeslSpinningDatePickerSpinnerDelegate.this.formatDateForLunarForAccessibility(calendar);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(SeslSpinningDatePickerSpinnerDelegate.this.formatDateForAccessibility(calendar));
            sb2.append(ArcCommonLog.TAG_COMMA);
            return C0212a.p(sb2, SeslSpinningDatePickerSpinnerDelegate.this.mPickerContentDescription, ArcCommonLog.TAG_COMMA);
        }

        private String getVirtualDecrementButtonText() {
            Calendar calendar = (Calendar) SeslSpinningDatePickerSpinnerDelegate.this.mValue.clone();
            calendar.add(5, -1);
            if (SeslSpinningDatePickerSpinnerDelegate.this.mWrapSelectorWheel) {
                calendar = SeslSpinningDatePickerSpinnerDelegate.this.getWrappedSelectorIndex(calendar);
            }
            if (calendar.compareTo(SeslSpinningDatePickerSpinnerDelegate.this.mMinValue) < 0) {
                return null;
            }
            if (SeslSpinningDatePickerSpinnerDelegate.this.mIsLunar) {
                return SeslSpinningDatePickerSpinnerDelegate.this.formatDateForLunarForAccessibility(calendar);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(SeslSpinningDatePickerSpinnerDelegate.this.formatDateForAccessibility(calendar));
            sb2.append(ArcCommonLog.TAG_COMMA);
            return C0212a.p(sb2, SeslSpinningDatePickerSpinnerDelegate.this.mPickerContentDescription, ArcCommonLog.TAG_COMMA);
        }

        private String getVirtualIncrementButtonText() {
            Calendar calendar = (Calendar) SeslSpinningDatePickerSpinnerDelegate.this.mValue.clone();
            calendar.add(5, 1);
            if (SeslSpinningDatePickerSpinnerDelegate.this.mWrapSelectorWheel) {
                calendar = SeslSpinningDatePickerSpinnerDelegate.this.getWrappedSelectorIndex(calendar);
            }
            if (calendar.compareTo(SeslSpinningDatePickerSpinnerDelegate.this.mMaxValue) > 0) {
                return null;
            }
            if (SeslSpinningDatePickerSpinnerDelegate.this.mIsLunar) {
                return SeslSpinningDatePickerSpinnerDelegate.this.formatDateForLunarForAccessibility(calendar);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(SeslSpinningDatePickerSpinnerDelegate.this.formatDateForAccessibility(calendar));
            sb2.append(ArcCommonLog.TAG_COMMA);
            return C0212a.p(sb2, SeslSpinningDatePickerSpinnerDelegate.this.mPickerContentDescription, ArcCommonLog.TAG_COMMA);
        }

        private boolean hasVirtualDecrementButton() {
            if (SeslSpinningDatePickerSpinnerDelegate.this.getWrapSelectorWheel() || SeslSpinningDatePickerSpinnerDelegate.this.getValue().compareTo(SeslSpinningDatePickerSpinnerDelegate.this.getMinValue()) > 0) {
                return true;
            }
            return false;
        }

        private boolean hasVirtualIncrementButton() {
            if (SeslSpinningDatePickerSpinnerDelegate.this.getWrapSelectorWheel() || SeslSpinningDatePickerSpinnerDelegate.this.getValue().compareTo(SeslSpinningDatePickerSpinnerDelegate.this.getMaxValue()) < 0) {
                return true;
            }
            return false;
        }

        private void scaleRect(Rect rect, float f) {
            if (f != 1.0f) {
                rect.left = (int) ((((float) rect.left) * f) + 0.5f);
                rect.top = (int) ((((float) rect.top) * f) + 0.5f);
                rect.right = (int) ((((float) rect.right) * f) + 0.5f);
                rect.bottom = (int) ((((float) rect.bottom) * f) + 0.5f);
            }
        }

        private void sendAccessibilityEventForCenter(int i2) {
            if (SeslSpinningDatePickerSpinnerDelegate.this.mAccessibilityManager.isEnabled()) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
                obtain.setPackageName(SeslSpinningDatePickerSpinnerDelegate.this.mContext.getPackageName());
                obtain.getText().add(getVirtualCurrentButtonText() + SeslSpinningDatePickerSpinnerDelegate.this.mContext.getString(R$string.sesl_date_picker_switch_to_calendar_description));
                obtain.setEnabled(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.isEnabled());
                obtain.setSource(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator, 2);
                SeslSpinningDatePickerSpinner seslSpinningDatePickerSpinner = SeslSpinningDatePickerSpinnerDelegate.this.mDelegator;
                seslSpinningDatePickerSpinner.requestSendAccessibilityEvent(seslSpinningDatePickerSpinner, obtain);
            }
        }

        private void sendAccessibilityEventForVirtualButton(int i2, int i7, String str) {
            if (SeslSpinningDatePickerSpinnerDelegate.this.mAccessibilityManager.isEnabled()) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain(i7);
                obtain.setClassName(Button.class.getName());
                obtain.setPackageName(SeslSpinningDatePickerSpinnerDelegate.this.mContext.getPackageName());
                obtain.getText().add(str);
                obtain.setEnabled(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.isEnabled());
                obtain.setSource(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator, i2);
                SeslSpinningDatePickerSpinner seslSpinningDatePickerSpinner = SeslSpinningDatePickerSpinnerDelegate.this.mDelegator;
                seslSpinningDatePickerSpinner.requestSendAccessibilityEvent(seslSpinningDatePickerSpinner, obtain);
            }
        }

        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i2) {
            int left = SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.getLeft();
            int right = SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.getRight();
            int top = SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.getTop();
            int bottom = SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.getBottom();
            int scrollX = SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.getScrollX();
            int scrollY = SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.getScrollY();
            if (!(SeslSpinningDatePickerSpinnerDelegate.this.mLastFocusedChildVirtualViewId == -1 && SeslSpinningDatePickerSpinnerDelegate.this.mLastHoveredChildVirtualViewId == Integer.MIN_VALUE)) {
                if (i2 == -1) {
                    return createAccessibilityNodeInfoForDatePickerWidget(scrollX, scrollY, (right - left) + scrollX, (bottom - top) + scrollY);
                }
                if (i2 == 1) {
                    return createAccessibilityNodeInfoForVirtualButton(1, getVirtualDecrementButtonText(), scrollX, scrollY, (right - left) + scrollX, SeslSpinningDatePickerSpinnerDelegate.this.mTopSelectionDividerTop + SeslSpinningDatePickerSpinnerDelegate.this.mSelectionDividerHeight);
                } else if (i2 == 2) {
                    return createAccessibiltyNodeInfoForCenter(scrollX, SeslSpinningDatePickerSpinnerDelegate.this.mTopSelectionDividerTop + SeslSpinningDatePickerSpinnerDelegate.this.mSelectionDividerHeight, (right - left) + scrollX, SeslSpinningDatePickerSpinnerDelegate.this.mBottomSelectionDividerBottom - SeslSpinningDatePickerSpinnerDelegate.this.mSelectionDividerHeight);
                } else if (i2 == 3) {
                    int i7 = (bottom - top) + scrollY;
                    return createAccessibilityNodeInfoForVirtualButton(3, getVirtualIncrementButtonText(), scrollX, SeslSpinningDatePickerSpinnerDelegate.this.mBottomSelectionDividerBottom - SeslSpinningDatePickerSpinnerDelegate.this.mSelectionDividerHeight, (right - left) + scrollX, i7);
                }
            }
            AccessibilityNodeInfo createAccessibilityNodeInfo = super.createAccessibilityNodeInfo(i2);
            if (createAccessibilityNodeInfo == null) {
                return AccessibilityNodeInfo.obtain();
            }
            return createAccessibilityNodeInfo;
        }

        public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i2) {
            if (TextUtils.isEmpty(str)) {
                return Collections.EMPTY_LIST;
            }
            String lowerCase = str.toLowerCase();
            ArrayList arrayList = new ArrayList();
            if (i2 == -1) {
                findAccessibilityNodeInfosByTextInChild(lowerCase, 1, arrayList);
                findAccessibilityNodeInfosByTextInChild(lowerCase, 2, arrayList);
                findAccessibilityNodeInfosByTextInChild(lowerCase, 3, arrayList);
                return arrayList;
            } else if (i2 != 1 && i2 != 2 && i2 != 3) {
                return super.findAccessibilityNodeInfosByText(str, i2);
            } else {
                findAccessibilityNodeInfosByTextInChild(lowerCase, i2, arrayList);
                return arrayList;
            }
        }

        public boolean performAction(int i2, int i7, Bundle bundle) {
            if (SeslSpinningDatePickerSpinnerDelegate.this.mIsStartingAnimation) {
                return false;
            }
            int right = SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.getRight();
            int bottom = SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.getBottom();
            if (i2 != -1) {
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 == 3) {
                            if (i7 != 16) {
                                if (i7 != 64) {
                                    if (i7 != 128 || this.mAccessibilityFocusedView != i2) {
                                        return false;
                                    }
                                    this.mAccessibilityFocusedView = Integer.MIN_VALUE;
                                    sendAccessibilityEventForVirtualView(i2, 65536);
                                    SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate = SeslSpinningDatePickerSpinnerDelegate.this;
                                    seslSpinningDatePickerSpinnerDelegate.mDelegator.invalidate(0, seslSpinningDatePickerSpinnerDelegate.mBottomSelectionDividerBottom, right, bottom);
                                    return true;
                                } else if (this.mAccessibilityFocusedView == i2) {
                                    return false;
                                } else {
                                    this.mAccessibilityFocusedView = i2;
                                    sendAccessibilityEventForVirtualView(i2, 32768);
                                    SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate2 = SeslSpinningDatePickerSpinnerDelegate.this;
                                    seslSpinningDatePickerSpinnerDelegate2.mDelegator.invalidate(0, seslSpinningDatePickerSpinnerDelegate2.mBottomSelectionDividerBottom, right, bottom);
                                    return true;
                                }
                            } else if (!SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.isEnabled()) {
                                return false;
                            } else {
                                SeslSpinningDatePickerSpinnerDelegate.this.startFadeAnimation(false);
                                SeslSpinningDatePickerSpinnerDelegate.this.changeValueByOne(true);
                                sendAccessibilityEventForVirtualView(i2, 1);
                                SeslSpinningDatePickerSpinnerDelegate.this.startFadeAnimation(true);
                                return true;
                            }
                        }
                    } else if (i7 != 16) {
                        if (i7 != 64) {
                            if (i7 != 128 || this.mAccessibilityFocusedView != i2) {
                                return false;
                            }
                            this.mAccessibilityFocusedView = Integer.MIN_VALUE;
                            sendAccessibilityEventForVirtualView(i2, 65536);
                            SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate3 = SeslSpinningDatePickerSpinnerDelegate.this;
                            seslSpinningDatePickerSpinnerDelegate3.mDelegator.invalidate(0, seslSpinningDatePickerSpinnerDelegate3.mTopSelectionDividerTop, right, SeslSpinningDatePickerSpinnerDelegate.this.mBottomSelectionDividerBottom);
                            return true;
                        } else if (this.mAccessibilityFocusedView == i2) {
                            return false;
                        } else {
                            this.mAccessibilityFocusedView = i2;
                            sendAccessibilityEventForVirtualView(i2, 32768);
                            SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate4 = SeslSpinningDatePickerSpinnerDelegate.this;
                            seslSpinningDatePickerSpinnerDelegate4.mDelegator.invalidate(0, seslSpinningDatePickerSpinnerDelegate4.mTopSelectionDividerTop, right, SeslSpinningDatePickerSpinnerDelegate.this.mBottomSelectionDividerBottom);
                            return true;
                        }
                    } else if (!SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.isEnabled()) {
                        return false;
                    } else {
                        SeslSpinningDatePickerSpinnerDelegate.this.performClick();
                        return true;
                    }
                } else if (i7 != 16) {
                    if (i7 != 64) {
                        if (i7 != 128 || this.mAccessibilityFocusedView != i2) {
                            return false;
                        }
                        this.mAccessibilityFocusedView = Integer.MIN_VALUE;
                        sendAccessibilityEventForVirtualView(i2, 65536);
                        SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate5 = SeslSpinningDatePickerSpinnerDelegate.this;
                        seslSpinningDatePickerSpinnerDelegate5.mDelegator.invalidate(0, 0, right, seslSpinningDatePickerSpinnerDelegate5.mTopSelectionDividerTop);
                        return true;
                    } else if (this.mAccessibilityFocusedView == i2) {
                        return false;
                    } else {
                        this.mAccessibilityFocusedView = i2;
                        sendAccessibilityEventForVirtualView(i2, 32768);
                        SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate6 = SeslSpinningDatePickerSpinnerDelegate.this;
                        seslSpinningDatePickerSpinnerDelegate6.mDelegator.invalidate(0, 0, right, seslSpinningDatePickerSpinnerDelegate6.mTopSelectionDividerTop);
                        return true;
                    }
                } else if (!SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.isEnabled()) {
                    return false;
                } else {
                    SeslSpinningDatePickerSpinnerDelegate.this.startFadeAnimation(false);
                    SeslSpinningDatePickerSpinnerDelegate.this.changeValueByOne(false);
                    sendAccessibilityEventForVirtualView(i2, 1);
                    SeslSpinningDatePickerSpinnerDelegate.this.startFadeAnimation(true);
                    return true;
                }
            } else if (i7 != 64) {
                if (i7 != 128) {
                    if (i7 != 4096) {
                        if (i7 == 8192) {
                            if (!SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.isEnabled() || (!SeslSpinningDatePickerSpinnerDelegate.this.getWrapSelectorWheel() && SeslSpinningDatePickerSpinnerDelegate.this.getValue().compareTo(SeslSpinningDatePickerSpinnerDelegate.this.getMinValue()) <= 0)) {
                                return false;
                            }
                            SeslSpinningDatePickerSpinnerDelegate.this.startFadeAnimation(false);
                            SeslSpinningDatePickerSpinnerDelegate.this.changeValueByOne(false);
                            SeslSpinningDatePickerSpinnerDelegate.this.startFadeAnimation(true);
                            return true;
                        }
                    } else if (!SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.isEnabled() || (!SeslSpinningDatePickerSpinnerDelegate.this.getWrapSelectorWheel() && SeslSpinningDatePickerSpinnerDelegate.this.getValue().compareTo(SeslSpinningDatePickerSpinnerDelegate.this.getMaxValue()) >= 0)) {
                        return false;
                    } else {
                        SeslSpinningDatePickerSpinnerDelegate.this.startFadeAnimation(false);
                        SeslSpinningDatePickerSpinnerDelegate.this.changeValueByOne(true);
                        SeslSpinningDatePickerSpinnerDelegate.this.startFadeAnimation(true);
                        return true;
                    }
                } else if (this.mAccessibilityFocusedView != i2) {
                    return false;
                } else {
                    this.mAccessibilityFocusedView = Integer.MIN_VALUE;
                    SeslViewReflector.clearAccessibilityFocus(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator);
                    return true;
                }
            } else if (this.mAccessibilityFocusedView == i2) {
                return false;
            } else {
                this.mAccessibilityFocusedView = i2;
                SeslViewReflector.requestAccessibilityFocus(SeslSpinningDatePickerSpinnerDelegate.this.mDelegator);
                return true;
            }
            return super.performAction(i2, i7, bundle);
        }

        public void sendAccessibilityEventForVirtualView(int i2, int i7) {
            if (i2 != 1) {
                if (i2 == 2) {
                    sendAccessibilityEventForCenter(i7);
                } else if (i2 == 3 && hasVirtualIncrementButton()) {
                    sendAccessibilityEventForVirtualButton(i2, i7, getVirtualIncrementButtonText());
                }
            } else if (hasVirtualDecrementButton()) {
                sendAccessibilityEventForVirtualButton(i2, i7, getVirtualDecrementButtonText());
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ChangeCurrentByOneFromLongPressCommand implements Runnable {
        private boolean mIncrement;

        public ChangeCurrentByOneFromLongPressCommand() {
        }

        /* access modifiers changed from: private */
        public void setStep(boolean z) {
            this.mIncrement = z;
        }

        public void run() {
            SeslSpinningDatePickerSpinnerDelegate.this.changeValueByOne(this.mIncrement);
            SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate = SeslSpinningDatePickerSpinnerDelegate.this;
            seslSpinningDatePickerSpinnerDelegate.mDelegator.postDelayed(this, seslSpinningDatePickerSpinnerDelegate.mLongPressUpdateInterval);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class HapticPreDrawListener implements ViewTreeObserver.OnPreDrawListener {
        boolean mSkipHapticCalls;

        private HapticPreDrawListener() {
            this.mSkipHapticCalls = false;
        }

        public boolean onPreDraw() {
            this.mSkipHapticCalls = false;
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PressedStateHelper implements Runnable {
        private int mManagedButton;
        private int mMode;

        public PressedStateHelper() {
        }

        public void buttonPressDelayed(int i2) {
            cancel();
            this.mMode = 1;
            this.mManagedButton = i2;
            SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.postDelayed(this, (long) ViewConfiguration.getTapTimeout());
        }

        public void buttonTapped(int i2) {
            cancel();
            this.mMode = 2;
            this.mManagedButton = i2;
            SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.post(this);
        }

        public void cancel() {
            int right = SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.getRight();
            int bottom = SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.getBottom();
            this.mMode = 0;
            this.mManagedButton = 0;
            SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.removeCallbacks(this);
            if (SeslSpinningDatePickerSpinnerDelegate.this.mIncrementVirtualButtonPressed) {
                boolean unused = SeslSpinningDatePickerSpinnerDelegate.this.mIncrementVirtualButtonPressed = false;
                SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate = SeslSpinningDatePickerSpinnerDelegate.this;
                seslSpinningDatePickerSpinnerDelegate.mDelegator.invalidate(0, seslSpinningDatePickerSpinnerDelegate.mBottomSelectionDividerBottom, right, bottom);
            }
            if (SeslSpinningDatePickerSpinnerDelegate.this.mDecrementVirtualButtonPressed) {
                boolean unused2 = SeslSpinningDatePickerSpinnerDelegate.this.mDecrementVirtualButtonPressed = false;
                SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate2 = SeslSpinningDatePickerSpinnerDelegate.this;
                seslSpinningDatePickerSpinnerDelegate2.mDelegator.invalidate(0, 0, right, seslSpinningDatePickerSpinnerDelegate2.mTopSelectionDividerTop);
            }
        }

        public void run() {
            int right = SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.getRight();
            int bottom = SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.getBottom();
            int i2 = this.mMode;
            if (i2 == 1) {
                int i7 = this.mManagedButton;
                if (i7 == 1) {
                    boolean unused = SeslSpinningDatePickerSpinnerDelegate.this.mIncrementVirtualButtonPressed = true;
                    SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate = SeslSpinningDatePickerSpinnerDelegate.this;
                    seslSpinningDatePickerSpinnerDelegate.mDelegator.invalidate(0, seslSpinningDatePickerSpinnerDelegate.mBottomSelectionDividerBottom, right, bottom);
                } else if (i7 == 2) {
                    boolean unused2 = SeslSpinningDatePickerSpinnerDelegate.this.mDecrementVirtualButtonPressed = true;
                    SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate2 = SeslSpinningDatePickerSpinnerDelegate.this;
                    seslSpinningDatePickerSpinnerDelegate2.mDelegator.invalidate(0, 0, right, seslSpinningDatePickerSpinnerDelegate2.mTopSelectionDividerTop);
                }
            } else if (i2 == 2) {
                int i8 = this.mManagedButton;
                if (i8 == 1) {
                    if (!SeslSpinningDatePickerSpinnerDelegate.this.mIncrementVirtualButtonPressed) {
                        SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.postDelayed(this, (long) ViewConfiguration.getPressedStateDuration());
                    }
                    SeslSpinningDatePickerSpinnerDelegate.access$2080(SeslSpinningDatePickerSpinnerDelegate.this, 1);
                    SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate3 = SeslSpinningDatePickerSpinnerDelegate.this;
                    seslSpinningDatePickerSpinnerDelegate3.mDelegator.invalidate(0, seslSpinningDatePickerSpinnerDelegate3.mBottomSelectionDividerBottom, right, bottom);
                } else if (i8 == 2) {
                    if (!SeslSpinningDatePickerSpinnerDelegate.this.mDecrementVirtualButtonPressed) {
                        SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.postDelayed(this, (long) ViewConfiguration.getPressedStateDuration());
                    }
                    SeslSpinningDatePickerSpinnerDelegate.access$2280(SeslSpinningDatePickerSpinnerDelegate.this, 1);
                    SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate4 = SeslSpinningDatePickerSpinnerDelegate.this;
                    seslSpinningDatePickerSpinnerDelegate4.mDelegator.invalidate(0, 0, right, seslSpinningDatePickerSpinnerDelegate4.mTopSelectionDividerTop);
                }
            }
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SeslSpinningDatePickerSpinnerDelegate(SeslSpinningDatePickerSpinner seslSpinningDatePickerSpinner, Context context, AttributeSet attributeSet, int i2, int i7) {
        super(seslSpinningDatePickerSpinner, context);
        boolean z;
        int i8;
        int i10;
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        int i11 = i2;
        int i12 = i7;
        PathInterpolator pathInterpolator = new PathInterpolator(0.5f, 0.0f, 0.4f, 1.0f);
        this.SIZE_PATH_INTERPOLATOR = pathInterpolator;
        PathInterpolator pathInterpolator2 = new PathInterpolator(0.17f, 0.17f, 0.83f, 0.83f);
        this.ALPHA_PATH_INTERPOLATOR = pathInterpolator2;
        this.mActivatedAlpha = 0.4f;
        this.mIdleAlpha = 0.1f;
        this.mAlpha = 0.1f;
        this.mInitialAlpha = 1.0f;
        this.mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = SeslSpinningDatePickerSpinnerDelegate.this.mAlpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.invalidate();
            }
        };
        this.mColorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int unused = SeslSpinningDatePickerSpinnerDelegate.this.mTextColor = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.invalidate();
            }
        };
        this.mSpringAnimationUpdateListener = new DynamicAnimation.OnAnimationUpdateListener() {
            public void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f, float f5) {
                float access$1500 = f - SeslSpinningDatePickerSpinnerDelegate.this.mPreviousSpringY;
                if (SeslSpinningDatePickerSpinnerDelegate.this.mSpringFlingRunning || Math.round(access$1500) != 0) {
                    if (Math.round(access$1500) == 0) {
                        boolean unused = SeslSpinningDatePickerSpinnerDelegate.this.mSpringFlingRunning = false;
                    }
                    SeslSpinningDatePickerSpinnerDelegate.this.scrollBy(0, Math.round(access$1500));
                    float unused2 = SeslSpinningDatePickerSpinnerDelegate.this.mPreviousSpringY = f;
                    SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.invalidate();
                    return;
                }
                dynamicAnimation.cancel();
                boolean unused3 = SeslSpinningDatePickerSpinnerDelegate.this.ensureScrollWheelAdjusted();
            }
        };
        this.mSpringAnimationEndListener = new DynamicAnimation.OnAnimationEndListener() {
            public void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f5) {
                boolean unused = SeslSpinningDatePickerSpinnerDelegate.this.mSpringFlingRunning = false;
                SeslSpinningDatePickerSpinnerDelegate.this.mGravityScroller.forceFinished(true);
                SeslSpinningDatePickerSpinnerDelegate.this.startFadeAnimation(true);
            }
        };
        Resources resources = this.mContext.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.sesl_number_picker_spinner_height);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R$dimen.sesl_number_picker_spinner_width);
        this.mHeightRatio = ((float) resources.getDimensionPixelSize(R$dimen.sesl_number_picker_spinner_edit_text_height)) / ((float) dimensionPixelSize);
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet2, R$styleable.NumberPicker, i11, i12);
        int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R$styleable.NumberPicker_internalMinHeight, -1);
        this.mMinHeight = dimensionPixelSize3;
        int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(R$styleable.NumberPicker_internalMaxHeight, dimensionPixelSize);
        this.mMaxHeight = dimensionPixelSize4;
        int dimensionPixelSize5 = obtainStyledAttributes.getDimensionPixelSize(R$styleable.NumberPicker_internalMinWidth, dimensionPixelSize2);
        this.mMinWidth = dimensionPixelSize5;
        this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.NumberPicker_internalMaxWidth, -1);
        obtainStyledAttributes.recycle();
        this.mValue = getCalendarForLocale(this.mValue, Locale.getDefault());
        this.mMinValue = getCalendarForLocale(this.mMinValue, Locale.getDefault());
        this.mMaxValue = getCalendarForLocale(this.mMaxValue, Locale.getDefault());
        TypedArray obtainStyledAttributes2 = context2.obtainStyledAttributes(attributeSet2, R$styleable.DatePicker, i11, i12);
        try {
            this.mMinValue.set(obtainStyledAttributes2.getInt(R$styleable.DatePicker_android_startYear, 1902), 0, 1);
            this.mMaxValue.set(obtainStyledAttributes2.getInt(R$styleable.DatePicker_android_endYear, 2100), 11, 31);
            if (dimensionPixelSize3 != -1 && dimensionPixelSize4 != -1 && dimensionPixelSize3 > dimensionPixelSize4) {
                throw new IllegalArgumentException("minHeight > maxHeight");
            } else if (dimensionPixelSize5 == -1 || (i10 = this.mMaxWidth) == -1 || dimensionPixelSize5 <= i10) {
                this.mSelectionDividerHeight = (int) TypedValue.applyDimension(1, 2.0f, resources.getDisplayMetrics());
                if (this.mMaxWidth == -1) {
                    z = true;
                } else {
                    z = false;
                }
                this.mComputeMaxWidth = z;
                TypedValue typedValue = new TypedValue();
                context2.getTheme().resolveAttribute(R$attr.colorPrimaryDark, typedValue, true);
                int i13 = typedValue.resourceId;
                if (i13 != 0) {
                    i8 = ResourcesCompat.getColor(resources, i13, (Resources.Theme) null);
                } else {
                    i8 = typedValue.data;
                }
                this.mVirtualButtonFocusedDrawable = new ColorDrawable((i8 & 16777215) | 855638016);
                if (!SeslMisc.isLightTheme(this.mContext)) {
                    this.mIdleAlpha = 0.2f;
                    this.mAlpha = 0.2f;
                }
                this.mPressedStateHelper = new PressedStateHelper();
                this.mDelegator.setWillNotDraw(false);
                ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R$layout.sesl_spinning_date_picker_spinner, this.mDelegator, true);
                EditText editText = (EditText) this.mDelegator.findViewById(R$id.datepicker_input);
                this.mInputText = editText;
                editText.setIncludeFontPadding(false);
                Typeface defaultFromStyle = Typeface.defaultFromStyle(1);
                this.mDefaultTypeface = defaultFromStyle;
                Typeface create = Typeface.create("sec-roboto-condensed-light", 1);
                this.mLegacyTypeface = create;
                if (Build.VERSION.SDK_INT >= 34) {
                    this.mPickerTypeface = Typeface.create(Typeface.create("sec", 0), 600, false);
                } else {
                    this.mPickerTypeface = Typeface.create("sec-roboto-light", 1);
                }
                if (defaultFromStyle.equals(this.mPickerTypeface)) {
                    if (!create.equals(this.mPickerTypeface)) {
                        this.mPickerTypeface = create;
                    } else {
                        this.mPickerTypeface = Typeface.create("sans-serif-thin", 1);
                    }
                }
                this.mPickerSubTypeface = Typeface.create(this.mPickerTypeface, 0);
                if (!SeslConfigurationReflector.isDexEnabled(resources.getConfiguration())) {
                    Typeface openThemeTypeface = SeslPickerBasicUtils.getOpenThemeTypeface(this.mContext);
                    if (openThemeTypeface != null) {
                        this.mPickerTypeface = openThemeTypeface;
                        this.mPickerSubTypeface = Typeface.create(openThemeTypeface, 0);
                    }
                } else {
                    this.mIdleAlpha = 0.2f;
                    this.mAlpha = 0.2f;
                }
                if (isCharacterNumberLanguage()) {
                    this.mPickerTypeface = defaultFromStyle;
                    this.mPickerSubTypeface = Typeface.create(defaultFromStyle, 0);
                }
                this.mIsHcfEnabled = isHighContrastFontEnabled();
                this.mHcfFocusedTypefaceBold = Typeface.create(this.mPickerTypeface, 1);
                this.mHcfUnfocusedTextSizeDiff = (int) TypedValue.applyDimension(1, 2.0f, this.mContext.getResources().getDisplayMetrics());
                setInputTextTypeface();
                int colorForState = editText.getTextColors().getColorForState(this.mDelegator.getEnableStateSet(), -1);
                this.mTextColorIdle = colorForState;
                int color = ResourcesCompat.getColor(resources, R$color.sesl_number_picker_text_color_scroll, context2.getTheme());
                this.mTextColorScrolling = color;
                this.mTextColor = colorForState;
                ViewConfiguration viewConfiguration = ViewConfiguration.get(context2);
                this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
                this.mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity() * 2;
                this.mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity() / 4;
                this.mTextSize = (int) editText.getTextSize();
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setTextAlign(Paint.Align.CENTER);
                paint.setTextSize((float) this.mTextSize);
                paint.setTypeface(this.mPickerTypeface);
                paint.setColor(this.mTextColor);
                this.mSelectorWheelPaint = paint;
                this.mInitialAlpha = ((float) paint.getAlpha()) / 255.0f;
                this.mCustomScroller = new Scroller(this.mContext, pathInterpolator, true);
                Scroller scroller = new Scroller(this.mContext, (Interpolator) null, true);
                this.mLinearScroller = scroller;
                this.mFlingScroller = scroller;
                this.mAdjustScroller = new Scroller(this.mContext, new PathInterpolator(0.4f, 0.0f, 0.3f, 1.0f));
                this.mGravityScroller = new OverScroller(this.mContext, new DecelerateInterpolator());
                this.mHolder = new FloatValueHolder();
                SpringAnimation springAnimation = new SpringAnimation(this.mHolder);
                this.mSpringAnimation = springAnimation;
                springAnimation.setSpring(new SpringForce());
                this.mSpringAnimation.setMinimumVisibleChange(1.0f);
                this.mSpringAnimation.addUpdateListener(this.mSpringAnimationUpdateListener);
                this.mSpringAnimation.addEndListener(this.mSpringAnimationEndListener);
                this.mSpringAnimation.getSpring().setStiffness(7.0f);
                this.mSpringAnimation.getSpring().setDampingRatio(0.99f);
                setFormatter(SeslSpinningDatePickerSpinner.getDateFormatter());
                this.mDelegator.setVerticalScrollBarEnabled(false);
                if (this.mDelegator.getImportantForAccessibility() == 0) {
                    this.mDelegator.setImportantForAccessibility(1);
                }
                this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
                this.mHapticPreDrawListener = new HapticPreDrawListener();
                this.mPickerVibrateIndex = SeslHapticFeedbackConstantsReflector.semGetVibrationIndex(32);
                this.mPickerSoundIndex = SeslAudioManagerReflector.getField_SOUND_TIME_PICKER_SCROLL();
                this.mDelegator.setFocusableInTouchMode(false);
                this.mDelegator.setDescendantFocusability(131072);
                this.mDelegator.setDefaultFocusHighlightEnabled(false);
                this.mPickerContentDescription = "";
                SeslViewReflector.semSetDirectPenInputEnabled(editText, false);
                this.mAccessibilityManager = (AccessibilityManager) this.mContext.getSystemService("accessibility");
                ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{this.mActivatedAlpha, this.mIdleAlpha});
                this.mFadeOutAnimator = ofFloat;
                ofFloat.setInterpolator(pathInterpolator2);
                this.mFadeOutAnimator.setDuration(200);
                this.mFadeOutAnimator.setStartDelay(100);
                this.mFadeOutAnimator.addUpdateListener(this.mUpdateListener);
                ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{this.mIdleAlpha, this.mActivatedAlpha});
                this.mFadeInAnimator = ofFloat2;
                ofFloat2.setInterpolator(pathInterpolator2);
                this.mFadeInAnimator.setDuration(200);
                this.mFadeInAnimator.addUpdateListener(this.mUpdateListener);
                ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(colorForState), Integer.valueOf(color)});
                this.mColorInAnimator = ofObject;
                ofObject.setInterpolator(pathInterpolator2);
                this.mColorInAnimator.setDuration(200);
                this.mColorInAnimator.addUpdateListener(this.mColorUpdateListener);
                ValueAnimator ofObject2 = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(color), Integer.valueOf(colorForState)});
                this.mColorOutAnimator = ofObject2;
                ofObject2.setInterpolator(pathInterpolator2);
                this.mColorOutAnimator.setDuration(200);
                this.mColorOutAnimator.setStartDelay(100);
                this.mColorOutAnimator.addUpdateListener(this.mColorUpdateListener);
                this.mShortMonths = new DateFormatSymbols().getShortMonths();
                this.mLongMonths = new DateFormatSymbols().getMonths();
            } else {
                throw new IllegalArgumentException("minWidth > maxWidth");
            }
        } finally {
            obtainStyledAttributes2.recycle();
        }
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [boolean, byte] */
    public static /* synthetic */ boolean access$2080(SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate, int i2) {
        ? r22 = (byte) (i2 ^ seslSpinningDatePickerSpinnerDelegate.mIncrementVirtualButtonPressed);
        seslSpinningDatePickerSpinnerDelegate.mIncrementVirtualButtonPressed = r22;
        return r22;
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [boolean, byte] */
    public static /* synthetic */ boolean access$2280(SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate, int i2) {
        ? r22 = (byte) (i2 ^ seslSpinningDatePickerSpinnerDelegate.mDecrementVirtualButtonPressed);
        seslSpinningDatePickerSpinnerDelegate.mDecrementVirtualButtonPressed = r22;
        return r22;
    }

    /* access modifiers changed from: private */
    public void changeValueByOne(boolean z) {
        int i2;
        if (!moveToFinalScrollerPosition(this.mFlingScroller)) {
            moveToFinalScrollerPosition(this.mAdjustScroller);
        }
        this.mPreviousScrollerY = 0;
        this.mChangeValueBy = 1;
        if (this.mLongPressed_FIRST_SCROLL) {
            this.mLongPressed_FIRST_SCROLL = false;
            this.mLongPressed_SECOND_SCROLL = true;
        } else if (this.mLongPressed_SECOND_SCROLL) {
            this.mLongPressed_SECOND_SCROLL = false;
            this.mLongPressed_THIRD_SCROLL = true;
            if (getValue().get(5) % 10 == 0) {
                this.mChangeValueBy = 10;
            } else if (z) {
                this.mChangeValueBy = 10 - (getValue().get(5) % 10);
            } else {
                this.mChangeValueBy = getValue().get(5) % 10;
            }
        } else if (this.mLongPressed_THIRD_SCROLL) {
            this.mChangeValueBy = 10;
        }
        boolean z3 = this.mIsLongPressed;
        if (z3 && this.mSkipNumbers) {
            this.mLongPressUpdateInterval = 600;
            i2 = 200;
        } else if (z3) {
            this.mChangeValueBy = 1;
            this.mLongPressUpdateInterval = 300;
            i2 = 100;
        } else {
            i2 = 500;
        }
        int i7 = i2;
        int i8 = this.mChangeValueBy;
        this.mLongPressCount = i8 - 1;
        if (z) {
            this.mFlingScroller.startScroll(0, 0, 0, (-this.mSelectorElementHeight) * i8, i7);
        } else {
            this.mFlingScroller.startScroll(0, 0, 0, this.mSelectorElementHeight * i8, i7);
        }
        this.mDelegator.invalidate();
    }

    private void clearCalendar(Calendar calendar, Calendar calendar2) {
        calendar.set(1, calendar2.get(1));
        calendar.set(2, calendar2.get(2));
        calendar.set(5, calendar2.get(5));
    }

    private void decrementSelectorIndices(Calendar[] calendarArr) {
        System.arraycopy(calendarArr, 0, calendarArr, 1, calendarArr.length - 1);
        Calendar calendar = (Calendar) calendarArr[1].clone();
        calendar.add(5, -1);
        if (this.mWrapSelectorWheel && calendar.compareTo(this.mMinValue) < 0) {
            clearCalendar(calendar, this.mMaxValue);
        }
        calendarArr[0] = calendar;
        ensureCachedScrollSelectorValue(calendar);
    }

    private void ensureCachedScrollSelectorValue(Calendar calendar) {
        String str;
        HashMap<Calendar, String> hashMap = this.mSelectorIndexToStringCache;
        if (hashMap.get(calendar) == null) {
            if (calendar.compareTo(this.mMinValue) < 0 || calendar.compareTo(this.mMaxValue) > 0) {
                str = "";
            } else if (this.mIsLunar) {
                str = formatDateForLunar(calendar);
            } else {
                str = formatDate(calendar);
            }
            hashMap.put(calendar, str);
        }
    }

    /* access modifiers changed from: private */
    public boolean ensureScrollWheelAdjusted() {
        return ensureScrollWheelAdjusted(0);
    }

    private void fling(int i2) {
        int min;
        if (!this.mWrapSelectorWheel && i2 > 0 && getValue().equals(getMinValue())) {
            startFadeAnimation(true);
        } else if (this.mWrapSelectorWheel || i2 >= 0 || !getValue().equals(getMaxValue())) {
            this.mPreviousScrollerY = 0;
            Math.abs(i2);
            this.mPreviousSpringY = (float) this.mCurrentScrollOffset;
            this.mSpringAnimation.setStartVelocity((float) i2);
            this.mGravityScroller.forceFinished(true);
            int i7 = i2;
            this.mGravityScroller.fling(0, this.mCurrentScrollOffset, 0, i7, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
            int round = Math.round(((float) (this.mGravityScroller.getFinalY() + this.mCurrentScrollOffset)) / ((float) this.mSelectorElementHeight));
            int i8 = this.mSelectorElementHeight;
            int i10 = this.mInitialScrollOffset;
            int i11 = (round * i8) + i10;
            if (i7 > 0) {
                min = Math.max(i11, i8 + i10);
            } else {
                min = Math.min(i11, (-i8) + i10);
            }
            float f = (float) min;
            this.mSpringAnimation.setStartValue((float) this.mCurrentScrollOffset);
            this.mSpringFlingRunning = true;
            this.mSpringAnimation.animateToFinalPosition(f);
            this.mDelegator.invalidate();
        } else {
            startFadeAnimation(true);
        }
    }

    private String formatDate(Calendar calendar) {
        SeslSpinningDatePickerSpinner.Formatter formatter = this.mFormatter;
        if (formatter == null) {
            return formatDateWithLocale(calendar);
        }
        if (formatter instanceof SeslSpinningDatePickerSpinner.DateFormatter) {
            return ((SeslSpinningDatePickerSpinner.DateFormatter) formatter).format(calendar, this.mContext);
        }
        return formatter.format(calendar);
    }

    /* access modifiers changed from: private */
    public String formatDateForAccessibility(Calendar calendar) {
        SeslSpinningDatePickerSpinner.Formatter formatter = this.mFormatter;
        if (formatter == null) {
            return formatDateWithLocale(calendar);
        }
        if (formatter instanceof SeslSpinningDatePickerSpinner.DateFormatter) {
            return ((SeslSpinningDatePickerSpinner.DateFormatter) formatter).formatForAccessibility(calendar, this.mContext);
        }
        return formatter.format(calendar);
    }

    private String formatDateForLunar(Calendar calendar) {
        String str;
        int i2;
        Calendar calendar2 = (Calendar) calendar.clone();
        SeslSpinningDatePicker$LunarDate seslSpinningDatePicker$LunarDate = new SeslSpinningDatePicker$LunarDate();
        SeslSpinningDatePickerSpinner.Formatter formatter = this.mFormatter;
        if (formatter == null) {
            str = formatDateWithLocale(calendar2);
        } else if (formatter instanceof SeslSpinningDatePickerSpinner.DateFormatter) {
            str = ((SeslSpinningDatePickerSpinner.DateFormatter) formatter).format(calendar2, this.mContext);
        } else {
            str = formatter.format(calendar2);
        }
        String dayWithLocale = getDayWithLocale(seslSpinningDatePicker$LunarDate.day);
        String formatDayWithLocale = formatDayWithLocale(calendar2);
        String monthWithLocale = getMonthWithLocale(seslSpinningDatePicker$LunarDate.month);
        String formatMonthWithLocale = formatMonthWithLocale(calendar2);
        StringBuilder sb2 = new StringBuilder(str);
        if (Locale.getDefault().getLanguage() == "vi") {
            i2 = sb2.lastIndexOf(" " + formatDayWithLocale) + 1;
        } else {
            i2 = sb2.lastIndexOf(formatDayWithLocale);
        }
        if (i2 != -1) {
            sb2.replace(i2, formatDayWithLocale.length() + i2, dayWithLocale);
        }
        int lastIndexOf = sb2.lastIndexOf(formatMonthWithLocale);
        if (lastIndexOf != -1) {
            sb2.replace(lastIndexOf, formatMonthWithLocale.length() + lastIndexOf, monthWithLocale);
        }
        return sb2.toString();
    }

    /* access modifiers changed from: private */
    public String formatDateForLunarForAccessibility(Calendar calendar) {
        String str;
        Calendar calendar2 = (Calendar) calendar.clone();
        SeslSpinningDatePicker$LunarDate seslSpinningDatePicker$LunarDate = new SeslSpinningDatePicker$LunarDate();
        SeslSpinningDatePickerSpinner.Formatter formatter = this.mFormatter;
        if (formatter == null) {
            str = formatDateWithLocaleForAccessibility(calendar2);
        } else if (formatter instanceof SeslSpinningDatePickerSpinner.DateFormatter) {
            str = ((SeslSpinningDatePickerSpinner.DateFormatter) formatter).formatForAccessibility(calendar2, this.mContext);
        } else {
            str = formatter.format(calendar2);
        }
        String dayWithLocale = getDayWithLocale(seslSpinningDatePicker$LunarDate.day);
        String formatDayWithLocale = formatDayWithLocale(calendar2);
        String monthWithLocaleForAccessibility = getMonthWithLocaleForAccessibility(seslSpinningDatePicker$LunarDate.month);
        String formatMonthWithLocaleForAccessibility = formatMonthWithLocaleForAccessibility(calendar2);
        StringBuilder sb2 = new StringBuilder(str);
        int lastIndexOf = sb2.lastIndexOf(formatDayWithLocale);
        if (lastIndexOf != -1) {
            sb2.replace(lastIndexOf, formatDayWithLocale.length() + lastIndexOf, dayWithLocale);
        }
        int lastIndexOf2 = sb2.lastIndexOf(formatMonthWithLocaleForAccessibility);
        if (lastIndexOf2 != -1) {
            sb2.replace(lastIndexOf2, formatMonthWithLocaleForAccessibility.length() + lastIndexOf2, monthWithLocaleForAccessibility);
        }
        return sb2.toString();
    }

    private static String formatDateWithLocale(Calendar calendar) {
        return new SimpleDateFormat("EEE, MMM d", Locale.getDefault()).format(calendar.getTime());
    }

    private static String formatDateWithLocaleForAccessibility(Calendar calendar) {
        return new SimpleDateFormat("EEEE, MMMM d", Locale.getDefault()).format(calendar.getTime());
    }

    private static String formatDayWithLocale(Calendar calendar) {
        return new SimpleDateFormat("d", Locale.getDefault()).format(calendar.getTime());
    }

    private static String formatMonthWithLocale(Calendar calendar) {
        return new SimpleDateFormat("MMM", Locale.getDefault()).format(calendar.getTime());
    }

    private static String formatMonthWithLocaleForAccessibility(Calendar calendar) {
        return new SimpleDateFormat("MMMM", Locale.getDefault()).format(calendar.getTime());
    }

    private static String formatNumberWithLocale(int i2) {
        return String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(i2)});
    }

    private Calendar getCalendarForLocale(Calendar calendar, Locale locale) {
        Calendar instance = Calendar.getInstance(locale);
        if (calendar != null) {
            instance.setTimeInMillis(calendar.getTimeInMillis());
        }
        instance.set(11, 12);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        return instance;
    }

    private static String getDayWithLocale(int i2) {
        return String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(i2)});
    }

    private String getMonthWithLocale(int i2) {
        return this.mShortMonths[i2];
    }

    private String getMonthWithLocaleForAccessibility(int i2) {
        return this.mLongMonths[i2];
    }

    /* access modifiers changed from: private */
    public Calendar getWrappedSelectorIndex(Calendar calendar) {
        if (calendar.compareTo(this.mMaxValue) > 0) {
            Calendar calendar2 = (Calendar) this.mMinValue.clone();
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            calendar2.add(5, ((int) timeUnit.toDays(calendar.getTimeInMillis() - this.mMinValue.getTimeInMillis())) % (((int) timeUnit.toDays(this.mMaxValue.getTimeInMillis() - this.mMinValue.getTimeInMillis())) + 1));
            return calendar2;
        } else if (calendar.compareTo(this.mMinValue) >= 0) {
            return calendar;
        } else {
            Calendar calendar3 = (Calendar) this.mMaxValue.clone();
            TimeUnit timeUnit2 = TimeUnit.MILLISECONDS;
            calendar3.add(5, -(((int) timeUnit2.toDays(this.mMaxValue.getTimeInMillis() - calendar.getTimeInMillis())) % (((int) timeUnit2.toDays(this.mMaxValue.getTimeInMillis() - this.mMinValue.getTimeInMillis())) + 1)));
            return calendar3;
        }
    }

    private void incrementSelectorIndices(Calendar[] calendarArr) {
        System.arraycopy(calendarArr, 1, calendarArr, 0, calendarArr.length - 1);
        Calendar calendar = (Calendar) calendarArr[calendarArr.length - 2].clone();
        calendar.add(5, 1);
        if (this.mWrapSelectorWheel && calendar.compareTo(this.mMaxValue) > 0) {
            clearCalendar(calendar, this.mMinValue);
        }
        calendarArr[calendarArr.length - 1] = calendar;
        ensureCachedScrollSelectorValue(calendar);
    }

    private void initializeSelectorWheel() {
        if (this.mIsStartingAnimation) {
            if (!moveToFinalScrollerPosition(this.mFlingScroller)) {
                moveToFinalScrollerPosition(this.mAdjustScroller);
            }
            stopScrollAnimation();
        }
        if (!this.mIsStartingAnimation) {
            initializeSelectorWheelIndices();
        }
        int bottom = (int) ((((float) ((this.mDelegator.getBottom() - this.mDelegator.getTop()) - (this.mTextSize * 3))) / 3.0f) + 0.5f);
        this.mSelectorTextGapHeight = bottom;
        int i2 = this.mTextSize + bottom;
        this.mSelectorElementHeight = i2;
        int i7 = this.mModifiedTxtHeight;
        if (i7 > i2) {
            i7 = this.mDelegator.getHeight() / 3;
        }
        this.mValueChangeOffset = i7;
        int top = ((this.mModifiedTxtHeight / 2) + this.mInputText.getTop()) - this.mSelectorElementHeight;
        this.mInitialScrollOffset = top;
        this.mCurrentScrollOffset = top;
        ((SeslSpinningDatePickerSpinner.CustomEditText) this.mInputText).setEditTextPosition(((int) (((this.mSelectorWheelPaint.descent() - this.mSelectorWheelPaint.ascent()) / 2.0f) - this.mSelectorWheelPaint.descent())) - (this.mInputText.getBaseline() - (this.mModifiedTxtHeight / 2)));
        if (this.mReservedStartAnimation) {
            startAnimation(0, (SeslAnimationListener) null);
            this.mReservedStartAnimation = false;
        }
    }

    private void initializeSelectorWheelIndices() {
        this.mSelectorIndexToStringCache.clear();
        Calendar[] calendarArr = this.mSelectorIndices;
        Calendar value = getValue();
        for (int i2 = 0; i2 < this.mSelectorIndices.length; i2++) {
            Calendar calendar = (Calendar) value.clone();
            calendar.add(5, i2 - 2);
            if (this.mWrapSelectorWheel) {
                calendar = getWrappedSelectorIndex(calendar);
            }
            calendarArr[i2] = calendar;
            ensureCachedScrollSelectorValue(calendar);
        }
    }

    private boolean isCharacterNumberLanguage() {
        String language = Locale.getDefault().getLanguage();
        if ("ar".equals(language) || "fa".equals(language) || "my".equals(language)) {
            return true;
        }
        return false;
    }

    private boolean isHighContrastFontEnabled() {
        return SeslViewReflector.isHighContrastTextEnabled(this.mInputText);
    }

    private int makeMeasureSpec(int i2, int i7) {
        if (i7 != -1) {
            int size = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i2);
            if (mode == Integer.MIN_VALUE) {
                return View.MeasureSpec.makeMeasureSpec(Math.min(size, i7), 1073741824);
            }
            if (mode == 0) {
                return View.MeasureSpec.makeMeasureSpec(i7, 1073741824);
            }
            if (mode != 1073741824) {
                throw new IllegalArgumentException(C0086a.i(mode, "Unknown measure mode: "));
            }
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public boolean moveToFinalScrollerPosition(Scroller scroller) {
        int i2;
        scroller.forceFinished(true);
        int finalY = scroller.getFinalY() - scroller.getCurrY();
        int i7 = this.mSelectorElementHeight;
        if (i7 == 0 || (i2 = this.mInitialScrollOffset - (this.mCurrentScrollOffset + finalY)) == 0) {
            return false;
        }
        int i8 = i2 % i7;
        int abs = Math.abs(i8);
        int i10 = this.mSelectorElementHeight;
        if (abs > i10 / 2) {
            if (i8 > 0) {
                i8 -= i10;
            } else {
                i8 += i10;
            }
        }
        scrollBy(0, finalY + i8);
        return true;
    }

    private void notifyChange(Calendar calendar) {
        if (this.mAccessibilityManager.isEnabled() && !this.mIsStartingAnimation) {
            Calendar wrappedSelectorIndex = getWrappedSelectorIndex(this.mValue);
            if (wrappedSelectorIndex.compareTo(this.mMaxValue) <= 0) {
                if (this.mIsLunar) {
                    formatDateForLunarForAccessibility(wrappedSelectorIndex);
                } else {
                    formatDateForAccessibility(wrappedSelectorIndex);
                }
            }
            this.mDelegator.sendAccessibilityEvent(4);
        }
    }

    private void onScrollStateChange(int i2) {
        if (this.mScrollState != i2) {
            this.mScrollState = i2;
        }
    }

    private void onScrollerFinished(Scroller scroller) {
        if (scroller == this.mFlingScroller) {
            onScrollStateChange(0);
        }
    }

    private void playSoundAndHapticFeedback() {
        this.mAudioManager.playSoundEffect(this.mPickerSoundIndex);
        if (!this.mHapticPreDrawListener.mSkipHapticCalls) {
            this.mDelegator.performHapticFeedback(this.mPickerVibrateIndex);
            this.mHapticPreDrawListener.mSkipHapticCalls = true;
        }
    }

    private void postChangeCurrentByOneFromLongPress(boolean z, long j2) {
        ChangeCurrentByOneFromLongPressCommand changeCurrentByOneFromLongPressCommand = this.mChangeCurrentByOneFromLongPressCommand;
        if (changeCurrentByOneFromLongPressCommand == null) {
            this.mChangeCurrentByOneFromLongPressCommand = new ChangeCurrentByOneFromLongPressCommand();
        } else {
            this.mDelegator.removeCallbacks(changeCurrentByOneFromLongPressCommand);
        }
        this.mIsLongPressed = true;
        this.mLongPressed_FIRST_SCROLL = true;
        this.mChangeCurrentByOneFromLongPressCommand.setStep(z);
        this.mDelegator.postDelayed(this.mChangeCurrentByOneFromLongPressCommand, j2);
    }

    private void removeAllCallbacks() {
        if (this.mIsLongPressed) {
            this.mIsLongPressed = false;
            this.mCurrentScrollOffset = this.mInitialScrollOffset;
        }
        this.mLongPressed_FIRST_SCROLL = false;
        this.mLongPressed_SECOND_SCROLL = false;
        this.mLongPressed_THIRD_SCROLL = false;
        this.mChangeValueBy = 1;
        this.mLongPressUpdateInterval = 300;
        ChangeCurrentByOneFromLongPressCommand changeCurrentByOneFromLongPressCommand = this.mChangeCurrentByOneFromLongPressCommand;
        if (changeCurrentByOneFromLongPressCommand != null) {
            this.mDelegator.removeCallbacks(changeCurrentByOneFromLongPressCommand);
        }
        this.mPressedStateHelper.cancel();
    }

    private void removeChangeCurrentByOneFromLongPress() {
        if (this.mIsLongPressed) {
            this.mIsLongPressed = false;
            this.mCurrentScrollOffset = this.mInitialScrollOffset;
        }
        this.mLongPressed_FIRST_SCROLL = false;
        this.mLongPressed_SECOND_SCROLL = false;
        this.mLongPressed_THIRD_SCROLL = false;
        this.mChangeValueBy = 1;
        this.mLongPressUpdateInterval = 300;
        ChangeCurrentByOneFromLongPressCommand changeCurrentByOneFromLongPressCommand = this.mChangeCurrentByOneFromLongPressCommand;
        if (changeCurrentByOneFromLongPressCommand != null) {
            this.mDelegator.removeCallbacks(changeCurrentByOneFromLongPressCommand);
        }
    }

    private int resolveSizeAndStateRespectingMinSize(int i2, int i7, int i8) {
        if (i2 != -1) {
            return View.resolveSizeAndState(Math.max(i2, i7), i8, 0);
        }
        return i7;
    }

    private void setInputTextTypeface() {
        if (this.mIsHcfEnabled) {
            this.mInputText.setTypeface(this.mHcfFocusedTypefaceBold);
        } else {
            this.mInputText.setTypeface(this.mPickerTypeface);
        }
    }

    private void setValueInternal(Calendar calendar, boolean z) {
        Calendar calendar2;
        if (this.mWrapSelectorWheel) {
            calendar2 = getWrappedSelectorIndex(calendar);
        } else {
            int compareTo = calendar.compareTo(this.mMinValue);
            Object obj = calendar;
            if (compareTo < 0) {
                obj = this.mMinValue.clone();
            }
            Calendar calendar3 = (Calendar) obj;
            int compareTo2 = calendar3.compareTo(this.mMaxValue);
            Object obj2 = calendar3;
            if (compareTo2 > 0) {
                obj2 = this.mMaxValue.clone();
            }
            calendar2 = (Calendar) obj2;
        }
        Calendar calendar4 = (Calendar) this.mValue.clone();
        clearCalendar(this.mValue, calendar2);
        if (z) {
            notifyChange(calendar4);
        }
        initializeSelectorWheelIndices();
        this.mDelegator.invalidate();
    }

    /* access modifiers changed from: private */
    public void startFadeAnimation(boolean z) {
        int i2 = 0;
        if (z) {
            this.mFadeOutAnimator.setStartDelay((long) (this.mFlingScroller.getDuration() + 100));
            ValueAnimator valueAnimator = this.mColorOutAnimator;
            if (!this.mFlingScroller.isFinished()) {
                i2 = this.mFlingScroller.getDuration();
            }
            valueAnimator.setStartDelay((long) (i2 + 100));
            this.mColorOutAnimator.start();
            this.mFadeOutAnimator.start();
            return;
        }
        this.mFadeInAnimator.setFloatValues(new float[]{this.mAlpha, this.mActivatedAlpha});
        this.mColorInAnimator.setIntValues(new int[]{this.mTextColor, this.mTextColorScrolling});
        this.mColorOutAnimator.cancel();
        this.mFadeOutAnimator.cancel();
        this.mColorInAnimator.start();
        this.mFadeInAnimator.start();
    }

    private void stopFlingAnimation() {
        this.mFlingScroller.abortAnimation();
        this.mAdjustScroller.abortAnimation();
        this.mGravityScroller.abortAnimation();
        this.mSpringAnimation.cancel();
        this.mSpringFlingRunning = false;
    }

    private void stopScrollAnimation() {
        this.mFlingScroller.abortAnimation();
        this.mAdjustScroller.abortAnimation();
        this.mGravityScroller.abortAnimation();
        this.mSpringAnimation.cancel();
        this.mSpringFlingRunning = false;
        if (!this.mIsStartingAnimation && !moveToFinalScrollerPosition(this.mFlingScroller)) {
            moveToFinalScrollerPosition(this.mAdjustScroller);
        }
        ensureScrollWheelAdjusted();
    }

    private void tryComputeMaxWidth() {
        if (this.mComputeMaxWidth) {
            float f = 0.0f;
            float f5 = 0.0f;
            for (int i2 = 0; i2 <= 9; i2++) {
                float measureText = this.mSelectorWheelPaint.measureText(formatNumberWithLocale(i2));
                if (measureText > f5) {
                    f5 = measureText;
                }
            }
            float f8 = (float) ((int) (((float) 2) * f5));
            float f10 = 0.0f;
            for (String measureText2 : new android.icu.text.DateFormatSymbols(Locale.getDefault()).getShortWeekdays()) {
                float measureText3 = this.mSelectorWheelPaint.measureText(measureText2);
                if (measureText3 > f10) {
                    f10 = measureText3;
                }
            }
            for (String measureText4 : new android.icu.text.DateFormatSymbols(Locale.getDefault()).getShortMonths()) {
                float measureText5 = this.mSelectorWheelPaint.measureText(measureText4);
                if (measureText5 > f) {
                    f = measureText5;
                }
            }
            int paddingRight = this.mInputText.getPaddingRight() + this.mInputText.getPaddingLeft() + ((int) (f8 + f10 + f + (this.mSelectorWheelPaint.measureText(" ") * 2.0f) + this.mSelectorWheelPaint.measureText(GlobalPostProcInternalPPInterface.SPLIT_REGEX)));
            if (isHighContrastFontEnabled()) {
                paddingRight += ((int) Math.ceil((double) (SeslPaintReflector.getHCTStrokeWidth(this.mSelectorWheelPaint) / 2.0f))) * 13;
            }
            if (this.mMaxWidth != paddingRight) {
                int i7 = this.mMinWidth;
                if (paddingRight > i7) {
                    this.mMaxWidth = paddingRight;
                } else {
                    this.mMaxWidth = i7;
                }
                this.mDelegator.invalidate();
            }
        }
    }

    private void updateHoveredVirtualView(int i2) {
        int i7 = this.mLastHoveredChildVirtualViewId;
        if (i7 != i2) {
            this.mLastHoveredChildVirtualViewId = i2;
            AccessibilityNodeProviderImpl accessibilityNodeProviderImpl = (AccessibilityNodeProviderImpl) getAccessibilityNodeProvider();
            accessibilityNodeProviderImpl.sendAccessibilityEventForVirtualView(i2, 128);
            accessibilityNodeProviderImpl.sendAccessibilityEventForVirtualView(i7, 256);
        }
    }

    public void computeScroll() {
        if (!this.mSpringFlingRunning) {
            Scroller scroller = this.mFlingScroller;
            if (scroller.isFinished()) {
                scroller = this.mAdjustScroller;
                if (scroller.isFinished()) {
                    return;
                }
            }
            scroller.computeScrollOffset();
            int currY = scroller.getCurrY();
            if (this.mPreviousScrollerY == 0) {
                this.mPreviousScrollerY = scroller.getStartY();
            }
            scrollBy(0, currY - this.mPreviousScrollerY);
            this.mPreviousScrollerY = currY;
            if (scroller.isFinished()) {
                onScrollerFinished(scroller);
            } else {
                this.mDelegator.invalidate();
            }
        }
    }

    public int computeVerticalScrollExtent() {
        return this.mDelegator.getHeight();
    }

    public int computeVerticalScrollOffset() {
        return this.mCurrentScrollOffset;
    }

    public int computeVerticalScrollRange() {
        return (((int) TimeUnit.MILLISECONDS.toDays(this.mMaxValue.getTimeInMillis() - this.mMinValue.getTimeInMillis())) + 1) * this.mSelectorElementHeight;
    }

    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        int i2;
        if (!this.mAccessibilityManager.isEnabled()) {
            return false;
        }
        int y = (int) motionEvent.getY();
        if (y <= this.mTopSelectionDividerTop) {
            i2 = 1;
        } else if (this.mBottomSelectionDividerBottom <= y) {
            i2 = 3;
        } else {
            i2 = 2;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7 || actionMasked == 9) {
            updateHoveredVirtualView(i2);
            return true;
        } else if (actionMasked != 10 || this.mLastHoveredChildVirtualViewId == Integer.MIN_VALUE) {
            return false;
        } else {
            updateHoveredVirtualView(Integer.MIN_VALUE);
            return true;
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int action = keyEvent.getAction();
        int keyCode = keyEvent.getKeyCode();
        if (!(keyCode == 66 || keyCode == 160)) {
            switch (keyCode) {
                case 19:
                case 20:
                    if (action == 0) {
                        if (keyCode == 20) {
                            int i2 = this.mLastFocusedChildVirtualViewId;
                            if (i2 == 1) {
                                this.mLastFocusedChildVirtualViewId = 2;
                                this.mDelegator.invalidate();
                                return true;
                            } else if (i2 == 2) {
                                if (!this.mWrapSelectorWheel && getValue().equals(getMaxValue())) {
                                    return false;
                                }
                                this.mLastFocusedChildVirtualViewId = 3;
                                this.mDelegator.invalidate();
                                return true;
                            }
                        } else if (keyCode == 19) {
                            int i7 = this.mLastFocusedChildVirtualViewId;
                            if (i7 != 2) {
                                if (i7 == 3) {
                                    this.mLastFocusedChildVirtualViewId = 2;
                                    this.mDelegator.invalidate();
                                    return true;
                                }
                            } else if (!this.mWrapSelectorWheel && getValue().equals(getMinValue())) {
                                return false;
                            } else {
                                this.mLastFocusedChildVirtualViewId = 1;
                                this.mDelegator.invalidate();
                                return true;
                            }
                        }
                    } else if (action == 1 && this.mAccessibilityManager.isEnabled()) {
                        AccessibilityNodeProviderImpl accessibilityNodeProviderImpl = (AccessibilityNodeProviderImpl) getAccessibilityNodeProvider();
                        if (accessibilityNodeProviderImpl != null) {
                            accessibilityNodeProviderImpl.performAction(this.mLastFocusedChildVirtualViewId, 64, (Bundle) null);
                        }
                        return true;
                    }
                    break;
                case 21:
                case 22:
                    if (action == 0) {
                        if (keyCode == 21) {
                            View focusSearch = this.mDelegator.focusSearch(17);
                            if (focusSearch != null) {
                                focusSearch.requestFocus(17);
                            }
                            return true;
                        } else if (keyCode == 22) {
                            View focusSearch2 = this.mDelegator.focusSearch(66);
                            if (focusSearch2 != null) {
                                focusSearch2.requestFocus(66);
                            }
                            return true;
                        }
                    }
                    break;
                case 23:
                    break;
            }
        }
        if (action == 0) {
            if (this.mLastFocusedChildVirtualViewId == 2) {
                performClick();
                removeAllCallbacks();
            } else if (this.mFlingScroller.isFinished()) {
                int i8 = this.mLastFocusedChildVirtualViewId;
                if (i8 == 1) {
                    startFadeAnimation(false);
                    changeValueByOne(false);
                    Calendar calendar = (Calendar) getMinValue().clone();
                    calendar.add(5, 1);
                    if (!this.mWrapSelectorWheel && getValue().equals(calendar)) {
                        this.mLastFocusedChildVirtualViewId = 2;
                    }
                    startFadeAnimation(true);
                } else if (i8 == 3) {
                    startFadeAnimation(false);
                    changeValueByOne(true);
                    Calendar calendar2 = (Calendar) getMaxValue().clone();
                    calendar2.add(5, -1);
                    if (!this.mWrapSelectorWheel && getValue().equals(calendar2)) {
                        this.mLastFocusedChildVirtualViewId = 2;
                    }
                    startFadeAnimation(true);
                }
            }
        }
        return false;
    }

    public boolean dispatchKeyEventPreIme(KeyEvent keyEvent) {
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 1 && actionMasked != 3) {
            return false;
        }
        removeAllCallbacks();
        return false;
    }

    public void dispatchTrackballEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            removeAllCallbacks();
        }
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        if (this.mAccessibilityNodeProvider == null) {
            this.mAccessibilityNodeProvider = new AccessibilityNodeProviderImpl();
        }
        return this.mAccessibilityNodeProvider;
    }

    public Calendar getMaxValue() {
        return this.mMaxValue;
    }

    public Calendar getMinValue() {
        return this.mMinValue;
    }

    public Calendar getValue() {
        return this.mValue;
    }

    public boolean getWrapSelectorWheel() {
        return this.mWrapSelectorWheel;
    }

    public void onAttachedToWindow() {
        this.mDelegator.getViewTreeObserver().addOnPreDrawListener(this.mHapticPreDrawListener);
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.mCustomTypefaceSet) {
            if (isCharacterNumberLanguage()) {
                this.mInputText.setIncludeFontPadding(true);
                Typeface typeface = this.mDefaultTypeface;
                this.mPickerTypeface = typeface;
                this.mPickerSubTypeface = Typeface.create(typeface, 0);
                this.mHcfFocusedTypefaceBold = Typeface.create(this.mPickerTypeface, 1);
                setInputTextTypeface();
                return;
            }
            this.mInputText.setIncludeFontPadding(false);
            setInputTextTypeface();
            tryComputeMaxWidth();
        }
    }

    public void onDetachedFromWindow() {
        this.mGravityScroller.abortAnimation();
        this.mSpringAnimation.cancel();
        this.mSpringFlingRunning = false;
        removeAllCallbacks();
        this.mDelegator.getViewTreeObserver().removeOnPreDrawListener(this.mHapticPreDrawListener);
    }

    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        int right = this.mDelegator.getRight();
        int left = this.mDelegator.getLeft();
        int bottom = this.mDelegator.getBottom();
        float f = 2.0f;
        float f5 = ((float) (right - left)) / 2.0f;
        float f8 = (float) (this.mCurrentScrollOffset - this.mSelectorElementHeight);
        Drawable drawable = this.mVirtualButtonFocusedDrawable;
        if (drawable != null && this.mScrollState == 0) {
            int i2 = this.mLastFocusedChildVirtualViewId;
            if (i2 == 1) {
                drawable.setState(this.mDelegator.getDrawableState());
                this.mVirtualButtonFocusedDrawable.setBounds(0, 0, right, this.mTopSelectionDividerTop);
                this.mVirtualButtonFocusedDrawable.draw(canvas2);
            } else if (i2 == 2) {
                drawable.setState(this.mDelegator.getDrawableState());
                this.mVirtualButtonFocusedDrawable.setBounds(0, this.mTopSelectionDividerTop, right, this.mBottomSelectionDividerBottom);
                this.mVirtualButtonFocusedDrawable.draw(canvas2);
            } else if (i2 == 3) {
                drawable.setState(this.mDelegator.getDrawableState());
                this.mVirtualButtonFocusedDrawable.setBounds(0, this.mBottomSelectionDividerBottom, right, bottom);
                this.mVirtualButtonFocusedDrawable.draw(canvas2);
            }
        }
        Calendar[] calendarArr = this.mSelectorIndices;
        int length = calendarArr.length;
        int i7 = 0;
        while (i7 < length) {
            String str = this.mSelectorIndexToStringCache.get(calendarArr[i7]);
            float f10 = this.mAlpha;
            float f11 = this.mIdleAlpha;
            if (f10 < f11) {
                f10 = f11;
            }
            int descent = (int) ((((this.mSelectorWheelPaint.descent() - this.mSelectorWheelPaint.ascent()) / f) + f8) - this.mSelectorWheelPaint.descent());
            int i8 = this.mTopSelectionDividerTop;
            int i10 = this.mInitialScrollOffset;
            float f12 = f;
            if (f8 >= ((float) (i8 - i10))) {
                int i11 = this.mBottomSelectionDividerBottom;
                if (f8 <= ((float) (i10 + i11))) {
                    if (f8 <= ((float) (i8 + i11)) / f12) {
                        canvas2.save();
                        canvas2.clipRect(0, this.mTopSelectionDividerTop, right, this.mBottomSelectionDividerBottom);
                        this.mSelectorWheelPaint.setColor(this.mTextColor);
                        this.mSelectorWheelPaint.setTypeface(this.mPickerTypeface);
                        float f13 = (float) descent;
                        canvas2.drawText(str, f5, f13, this.mSelectorWheelPaint);
                        canvas2.restore();
                        canvas2.save();
                        canvas2.clipRect(0, 0, right, this.mTopSelectionDividerTop);
                        this.mSelectorWheelPaint.setTypeface(this.mPickerSubTypeface);
                        this.mSelectorWheelPaint.setAlpha((int) (f10 * 255.0f * this.mInitialAlpha));
                        canvas2.drawText(str, f5, f13, this.mSelectorWheelPaint);
                        canvas2.restore();
                    } else {
                        canvas2.save();
                        canvas2.clipRect(0, this.mTopSelectionDividerTop, right, this.mBottomSelectionDividerBottom);
                        this.mSelectorWheelPaint.setTypeface(this.mPickerTypeface);
                        this.mSelectorWheelPaint.setColor(this.mTextColor);
                        float f14 = (float) descent;
                        canvas2.drawText(str, f5, f14, this.mSelectorWheelPaint);
                        canvas2.restore();
                        canvas2.save();
                        canvas2.clipRect(0, this.mBottomSelectionDividerBottom, right, bottom);
                        this.mSelectorWheelPaint.setAlpha((int) (f10 * 255.0f * this.mInitialAlpha));
                        this.mSelectorWheelPaint.setTypeface(this.mPickerSubTypeface);
                        canvas2.drawText(str, f5, f14, this.mSelectorWheelPaint);
                        canvas2.restore();
                    }
                    f8 += (float) this.mSelectorElementHeight;
                    i7++;
                    f = f12;
                }
            }
            canvas2.save();
            this.mSelectorWheelPaint.setAlpha((int) (f10 * 255.0f * this.mInitialAlpha));
            this.mSelectorWheelPaint.setTypeface(this.mPickerSubTypeface);
            canvas2.drawText(str, f5, (float) descent, this.mSelectorWheelPaint);
            canvas2.restore();
            f8 += (float) this.mSelectorElementHeight;
            i7++;
            f = f12;
        }
    }

    public void onFocusChanged(boolean z, int i2, Rect rect) {
        AccessibilityNodeProviderImpl accessibilityNodeProviderImpl;
        AccessibilityNodeProviderImpl accessibilityNodeProviderImpl2;
        if (!z) {
            if (this.mAccessibilityManager.isEnabled() && (accessibilityNodeProviderImpl2 = (AccessibilityNodeProviderImpl) getAccessibilityNodeProvider()) != null) {
                accessibilityNodeProviderImpl2.performAction(this.mLastFocusedChildVirtualViewId, 128, (Bundle) null);
            }
            this.mLastFocusedChildVirtualViewId = -1;
            this.mLastHoveredChildVirtualViewId = Integer.MIN_VALUE;
        } else {
            InputMethodManager inputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(this.mDelegator.getWindowToken(), 0);
            }
            this.mLastFocusedChildVirtualViewId = 1;
            if (!this.mWrapSelectorWheel && getValue().equals(getMinValue())) {
                this.mLastFocusedChildVirtualViewId = 2;
            }
            if (this.mAccessibilityManager.isEnabled() && (accessibilityNodeProviderImpl = (AccessibilityNodeProviderImpl) getAccessibilityNodeProvider()) != null) {
                accessibilityNodeProviderImpl.performAction(this.mLastFocusedChildVirtualViewId, 64, (Bundle) null);
            }
        }
        this.mDelegator.invalidate();
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.mDelegator.isEnabled() && !this.mIsStartingAnimation && (motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != 0.0f) {
                startFadeAnimation(false);
                if (axisValue < 0.0f) {
                    z = true;
                }
                changeValueByOne(z);
                startFadeAnimation(true);
                return true;
            }
        }
        return false;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        accessibilityEvent.setClassName(SeslSpinningDatePickerSpinner.class.getName());
        accessibilityEvent.setScrollable(true);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        accessibilityEvent.setScrollY(((int) timeUnit.toDays(this.mValue.getTimeInMillis() - this.mMinValue.getTimeInMillis())) * this.mSelectorElementHeight);
        accessibilityEvent.setMaxScrollY(((int) timeUnit.toDays(this.mMaxValue.getTimeInMillis() - this.mMinValue.getTimeInMillis())) * this.mSelectorElementHeight);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.mDelegator.isEnabled() || this.mIsStartingAnimation || motionEvent.getActionMasked() != 0) {
            return false;
        }
        removeAllCallbacks();
        float y = motionEvent.getY();
        this.mLastDownEventY = y;
        this.mLastDownOrMoveEventY = y;
        this.mLastDownEventTime = motionEvent.getEventTime();
        this.mIgnoreMoveEvents = false;
        this.mPerformClickOnTap = false;
        this.mIsValueChanged = false;
        float f = this.mLastDownEventY;
        if (f < ((float) this.mTopSelectionDividerTop)) {
            startFadeAnimation(false);
            if (this.mScrollState == 0) {
                this.mPressedStateHelper.buttonPressDelayed(2);
            }
        } else if (f > ((float) this.mBottomSelectionDividerBottom)) {
            startFadeAnimation(false);
            if (this.mScrollState == 0) {
                this.mPressedStateHelper.buttonPressDelayed(1);
            }
        }
        this.mDelegator.getParent().requestDisallowInterceptTouchEvent(true);
        if (!this.mFlingScroller.isFinished()) {
            this.mFlingScroller.forceFinished(true);
            this.mAdjustScroller.forceFinished(true);
            if (this.mScrollState == 2) {
                this.mFlingScroller.abortAnimation();
                this.mAdjustScroller.abortAnimation();
            }
            onScrollStateChange(0);
        } else if (this.mSpringAnimation.isRunning()) {
            this.mGravityScroller.forceFinished(true);
            this.mAdjustScroller.forceFinished(true);
            this.mSpringAnimation.cancel();
            this.mSpringFlingRunning = false;
            if (this.mScrollState == 2) {
                this.mGravityScroller.abortAnimation();
                this.mAdjustScroller.abortAnimation();
            }
            onScrollStateChange(0);
        } else if (!this.mAdjustScroller.isFinished()) {
            this.mFlingScroller.forceFinished(true);
            this.mAdjustScroller.forceFinished(true);
        } else {
            float f5 = this.mLastDownEventY;
            if (f5 < ((float) this.mTopSelectionDividerTop)) {
                postChangeCurrentByOneFromLongPress(false, (long) ViewConfiguration.getLongPressTimeout());
            } else if (f5 > ((float) this.mBottomSelectionDividerBottom)) {
                postChangeCurrentByOneFromLongPress(true, (long) ViewConfiguration.getLongPressTimeout());
            } else {
                this.mPerformClickOnTap = true;
            }
        }
        return true;
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        int measuredWidth = this.mDelegator.getMeasuredWidth();
        int measuredHeight = this.mDelegator.getMeasuredHeight();
        int measuredWidth2 = this.mInputText.getMeasuredWidth();
        int max = Math.max(this.mInputText.getMeasuredHeight(), (int) Math.floor((double) (((float) measuredHeight) * this.mHeightRatio)));
        this.mModifiedTxtHeight = max;
        int i11 = (measuredWidth - measuredWidth2) / 2;
        int i12 = (measuredHeight - max) / 2;
        int i13 = max + i12;
        this.mInputText.layout(i11, i12, measuredWidth2 + i11, i13);
        if (z) {
            initializeSelectorWheel();
            if (this.mModifiedTxtHeight > this.mSelectorElementHeight) {
                int i14 = this.mValueChangeOffset;
                this.mTopSelectionDividerTop = i14;
                this.mBottomSelectionDividerBottom = i14 * 2;
                return;
            }
            this.mTopSelectionDividerTop = i12;
            this.mBottomSelectionDividerBottom = i13;
        }
    }

    public void onMeasure(int i2, int i7) {
        this.mDelegator.superOnMeasure(makeMeasureSpec(i2, this.mMaxWidth), makeMeasureSpec(i7, this.mMaxHeight));
        this.mDelegator.setMeasuredDimensionWrapper(resolveSizeAndStateRespectingMinSize(this.mMinWidth, this.mDelegator.getMeasuredWidth(), i2), resolveSizeAndStateRespectingMinSize(this.mMinHeight, this.mDelegator.getMeasuredHeight(), i7));
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        accessibilityEvent.getText().add(((AccessibilityNodeProviderImpl) getAccessibilityNodeProvider()).getVirtualCurrentButtonText());
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mDelegator.isEnabled() || this.mIsStartingAnimation) {
            return false;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1) {
            removeChangeCurrentByOneFromLongPress();
            this.mPressedStateHelper.cancel();
            VelocityTracker velocityTracker = this.mVelocityTracker;
            velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumFlingVelocity);
            int yVelocity = (int) velocityTracker.getYVelocity();
            int y = (int) motionEvent.getY();
            int abs = (int) Math.abs(((float) y) - this.mLastDownEventY);
            if (Math.abs(yVelocity) <= this.mMinimumFlingVelocity) {
                long eventTime = motionEvent.getEventTime() - this.mLastDownEventTime;
                if (abs > this.mTouchSlop || eventTime >= ((long) ViewConfiguration.getLongPressTimeout())) {
                    if (this.mIsLongClicked) {
                        this.mIsLongClicked = false;
                    }
                    ensureScrollWheelAdjusted(abs);
                    startFadeAnimation(true);
                } else if (this.mPerformClickOnTap) {
                    this.mPerformClickOnTap = false;
                    performClick();
                } else {
                    if (y > this.mBottomSelectionDividerBottom) {
                        changeValueByOne(true);
                        this.mPressedStateHelper.buttonTapped(1);
                    } else if (y < this.mTopSelectionDividerTop) {
                        changeValueByOne(false);
                        this.mPressedStateHelper.buttonTapped(2);
                    } else {
                        ensureScrollWheelAdjusted(abs);
                    }
                    startFadeAnimation(true);
                }
                this.mIsValueChanged = false;
                onScrollStateChange(0);
            } else if (abs > this.mTouchSlop || !this.mPerformClickOnTap) {
                fling(yVelocity);
                onScrollStateChange(2);
            } else {
                this.mPerformClickOnTap = false;
                performClick();
                onScrollStateChange(0);
            }
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        } else if (actionMasked != 2) {
            if (actionMasked == 3) {
                ensureScrollWheelAdjusted();
                startFadeAnimation(true);
                onScrollStateChange(0);
            }
        } else if (!this.mIgnoreMoveEvents) {
            float y8 = motionEvent.getY();
            if (this.mScrollState == 1) {
                scrollBy(0, (int) (y8 - this.mLastDownOrMoveEventY));
                this.mDelegator.invalidate();
            } else if (((int) Math.abs(y8 - this.mLastDownEventY)) > this.mTouchSlop) {
                removeAllCallbacks();
                startFadeAnimation(false);
                onScrollStateChange(1);
            }
            this.mLastDownOrMoveEventY = y8;
        }
        return true;
    }

    public void onWindowFocusChanged(boolean z) {
        if (!this.mIsStartingAnimation) {
            if (!this.mFlingScroller.isFinished()) {
                this.mFlingScroller.forceFinished(true);
            }
            if (!this.mAdjustScroller.isFinished()) {
                this.mAdjustScroller.forceFinished(true);
            }
            if (!this.mGravityScroller.isFinished()) {
                this.mGravityScroller.forceFinished(true);
            }
            if (this.mSpringAnimation.isRunning()) {
                this.mSpringAnimation.cancel();
                this.mSpringFlingRunning = false;
            }
            ensureScrollWheelAdjusted();
        }
        this.mIsHcfEnabled = isHighContrastFontEnabled();
        this.mSelectorWheelPaint.setTextSize((float) this.mTextSize);
        this.mSelectorWheelPaint.setTypeface(this.mPickerTypeface);
        setInputTextTypeface();
    }

    public void performClick() {
        stopScrollAnimation();
    }

    public void performLongClick() {
        this.mIgnoreMoveEvents = true;
        this.mIsLongClicked = true;
    }

    public void scrollBy(int i2, int i7) {
        Calendar[] calendarArr = this.mSelectorIndices;
        if (i7 != 0 && this.mSelectorElementHeight > 0) {
            if (!this.mWrapSelectorWheel && this.mCurrentScrollOffset + i7 > this.mInitialScrollOffset && calendarArr[2].compareTo(this.mMinValue) <= 0) {
                stopFlingAnimation();
                i7 = this.mInitialScrollOffset - this.mCurrentScrollOffset;
            }
            if (!this.mWrapSelectorWheel && this.mCurrentScrollOffset + i7 < this.mInitialScrollOffset && calendarArr[2].compareTo(this.mMaxValue) >= 0) {
                stopFlingAnimation();
                i7 = this.mInitialScrollOffset - this.mCurrentScrollOffset;
            }
            this.mCurrentScrollOffset += i7;
            while (true) {
                int i8 = this.mCurrentScrollOffset;
                if (i8 - this.mInitialScrollOffset < this.mValueChangeOffset) {
                    break;
                }
                this.mCurrentScrollOffset = i8 - this.mSelectorElementHeight;
                decrementSelectorIndices(calendarArr);
                if (!this.mIsStartingAnimation) {
                    setValueInternal(calendarArr[2], true);
                    this.mIsValueChanged = true;
                    int i10 = this.mLongPressCount;
                    if (i10 > 0) {
                        this.mLongPressCount = i10 - 1;
                    } else {
                        playSoundAndHapticFeedback();
                    }
                }
                if (!this.mWrapSelectorWheel && calendarArr[2].compareTo(this.mMinValue) <= 0) {
                    this.mCurrentScrollOffset = this.mInitialScrollOffset;
                }
            }
            while (true) {
                int i11 = this.mCurrentScrollOffset;
                if (i11 - this.mInitialScrollOffset <= (-this.mValueChangeOffset)) {
                    this.mCurrentScrollOffset = i11 + this.mSelectorElementHeight;
                    incrementSelectorIndices(calendarArr);
                    if (!this.mIsStartingAnimation) {
                        setValueInternal(calendarArr[2], true);
                        this.mIsValueChanged = true;
                        int i12 = this.mLongPressCount;
                        if (i12 > 0) {
                            this.mLongPressCount = i12 - 1;
                        } else {
                            playSoundAndHapticFeedback();
                        }
                    }
                    if (!this.mWrapSelectorWheel && calendarArr[2].compareTo(this.mMaxValue) >= 0) {
                        this.mCurrentScrollOffset = this.mInitialScrollOffset;
                    }
                } else {
                    return;
                }
            }
        }
    }

    public void setEnabled(boolean z) {
        if (!z && this.mScrollState != 0) {
            stopScrollAnimation();
            onScrollStateChange(0);
        }
    }

    public void setFormatter(SeslSpinningDatePickerSpinner.Formatter formatter) {
        if (formatter != this.mFormatter) {
            this.mFormatter = formatter;
            initializeSelectorWheelIndices();
        }
    }

    public void startAnimation(final int i2, SeslAnimationListener seslAnimationListener) {
        this.mAlpha = this.mActivatedAlpha;
        this.mDelegator.post(new Runnable() {
            public void run() {
                if (SeslSpinningDatePickerSpinnerDelegate.this.mSelectorElementHeight == 0) {
                    boolean unused = SeslSpinningDatePickerSpinnerDelegate.this.mReservedStartAnimation = true;
                    return;
                }
                boolean unused2 = SeslSpinningDatePickerSpinnerDelegate.this.mIsStartingAnimation = true;
                SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate = SeslSpinningDatePickerSpinnerDelegate.this;
                Scroller unused3 = seslSpinningDatePickerSpinnerDelegate.mFlingScroller = seslSpinningDatePickerSpinnerDelegate.mCustomScroller;
                final int access$100 = (int) (((double) SeslSpinningDatePickerSpinnerDelegate.this.mSelectorElementHeight) * 5.4d);
                SeslSpinningDatePickerSpinnerDelegate.this.scrollBy(0, SeslSpinningDatePickerSpinnerDelegate.this.mSelectorElementHeight * 5);
                SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.invalidate();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate = SeslSpinningDatePickerSpinnerDelegate.this;
                                if (!seslSpinningDatePickerSpinnerDelegate.moveToFinalScrollerPosition(seslSpinningDatePickerSpinnerDelegate.mFlingScroller)) {
                                    SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate2 = SeslSpinningDatePickerSpinnerDelegate.this;
                                    boolean unused = seslSpinningDatePickerSpinnerDelegate2.moveToFinalScrollerPosition(seslSpinningDatePickerSpinnerDelegate2.mAdjustScroller);
                                }
                                int unused2 = SeslSpinningDatePickerSpinnerDelegate.this.mPreviousScrollerY = 0;
                                SeslSpinningDatePickerSpinnerDelegate.this.mFlingScroller.startScroll(0, 0, 0, -access$100, 557);
                                SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.invalidate();
                                new Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate = SeslSpinningDatePickerSpinnerDelegate.this;
                                        boolean unused = seslSpinningDatePickerSpinnerDelegate.moveToFinalScrollerPosition(seslSpinningDatePickerSpinnerDelegate.mFlingScroller);
                                        SeslSpinningDatePickerSpinnerDelegate.this.mFlingScroller.abortAnimation();
                                        SeslSpinningDatePickerSpinnerDelegate.this.mAdjustScroller.abortAnimation();
                                        boolean unused2 = SeslSpinningDatePickerSpinnerDelegate.this.ensureScrollWheelAdjusted();
                                        SeslSpinningDatePickerSpinnerDelegate seslSpinningDatePickerSpinnerDelegate2 = SeslSpinningDatePickerSpinnerDelegate.this;
                                        Scroller unused3 = seslSpinningDatePickerSpinnerDelegate2.mFlingScroller = seslSpinningDatePickerSpinnerDelegate2.mLinearScroller;
                                        boolean unused4 = SeslSpinningDatePickerSpinnerDelegate.this.mIsStartingAnimation = false;
                                        SeslSpinningDatePickerSpinnerDelegate.this.mDelegator.invalidate();
                                        SeslSpinningDatePickerSpinnerDelegate.this.startFadeAnimation(true);
                                        SeslAnimationListener unused5 = SeslSpinningDatePickerSpinnerDelegate.this.getClass();
                                    }
                                }, 857);
                            }
                        }, 100);
                    }
                }, (long) i2);
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        if (r0 > 0) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        if (r0 > 0) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean ensureScrollWheelAdjusted(int r10) {
        /*
            r9 = this;
            int r0 = r9.mInitialScrollOffset
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = 0
            if (r0 != r1) goto L_0x0008
            return r2
        L_0x0008:
            int r1 = r9.mCurrentScrollOffset
            int r0 = r0 - r1
            if (r0 == 0) goto L_0x0043
            r9.mPreviousScrollerY = r2
            boolean r1 = r9.mIsValueChanged
            if (r1 != 0) goto L_0x0023
            if (r10 == 0) goto L_0x0023
            int r10 = java.lang.Math.abs(r10)
            int r1 = r9.mSelectorElementHeight
            if (r10 >= r1) goto L_0x0023
            if (r0 <= 0) goto L_0x0020
        L_0x001f:
            int r1 = -r1
        L_0x0020:
            int r0 = r0 + r1
        L_0x0021:
            r7 = r0
            goto L_0x0030
        L_0x0023:
            int r10 = java.lang.Math.abs(r0)
            int r1 = r9.mSelectorElementHeight
            int r3 = r1 / 2
            if (r10 <= r3) goto L_0x0021
            if (r0 <= 0) goto L_0x0020
            goto L_0x001f
        L_0x0030:
            android.widget.Scroller r3 = r9.mAdjustScroller
            r6 = 0
            r8 = 300(0x12c, float:4.2E-43)
            r4 = 0
            r5 = 0
            r3.startScroll(r4, r5, r6, r7, r8)
            androidx.picker.widget.SeslSpinningDatePickerSpinner r10 = r9.mDelegator
            r10.invalidate()
            r9.mIsValueChanged = r2
            r9 = 1
            return r9
        L_0x0043:
            r9.mIsValueChanged = r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.picker.widget.SeslSpinningDatePickerSpinnerDelegate.ensureScrollWheelAdjusted(int):boolean");
    }

    public void onWindowVisibilityChanged(int i2) {
    }
}
