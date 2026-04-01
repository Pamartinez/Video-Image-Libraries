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
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
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
import android.widget.NumberPicker;
import android.widget.OverScroller;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.util.SeslMisc;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
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
import androidx.picker.widget.SeslNumberPicker;
import androidx.reflect.content.res.SeslCompatibilityInfoReflector;
import androidx.reflect.content.res.SeslConfigurationReflector;
import androidx.reflect.graphics.SeslPaintReflector;
import androidx.reflect.media.SeslAudioManagerReflector;
import androidx.reflect.media.SeslSemSoundAssistantManagerReflector;
import androidx.reflect.view.SeslHapticFeedbackConstantsReflector;
import androidx.reflect.view.SeslViewReflector;
import androidx.reflect.widget.SeslHoverPopupWindowReflector;
import c0.C0086a;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import i.C0212a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SeslNumberPickerSpinnerDelegate extends SeslNumberPicker.AbsNumberPickerDelegate {
    /* access modifiers changed from: private */
    public static final char[] DIGIT_CHARACTERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 1632, 1633, 1634, 1635, 1636, 1637, 1638, 1639, 1640, 1641, 1776, 1777, 1778, 1779, 1780, 1781, 1782, 1783, 1784, 1785, 2406, 2407, 2408, 2409, 2410, 2411, 2412, 2413, 2414, 2415, 2534, 2535, 2536, 2537, 2538, 2539, 2540, 2541, 2542, 2543, 3302, 3303, 3304, 3305, 3306, 3307, 3308, 3309, 3310, 3311, 4160, 4161, 4162, 4163, 4164, 4165, 4166, 4167, 4168, 4169};
    protected final PathInterpolator ALPHA_PATH_INTERPOLATOR;
    protected final PathInterpolator SIZE_PATH_INTERPOLATOR;
    /* access modifiers changed from: private */
    public AccessibilityManager mAccessibilityManager;
    protected AccessibilityNodeProviderImpl mAccessibilityNodeProvider;
    protected float mActivatedAlpha;
    protected final Scroller mAdjustScroller;
    protected float mAlpha;
    protected AudioManager mAudioManager;
    protected BeginSoftInputOnLongPressCommand mBeginSoftInputOnLongPressCommand;
    protected int mBottomSelectionDividerBottom;
    protected ValueAnimator mColorInAnimator;
    protected ValueAnimator mColorOutAnimator;
    private ValueAnimator.AnimatorUpdateListener mColorUpdateListener;
    protected final boolean mComputeMaxWidth;
    protected float mCurVelocity;
    protected int mCurrentScrollOffset;
    protected final Scroller mCustomScroller;
    /* access modifiers changed from: private */
    public SeslNumberPicker.CustomTalkbackFormatter mCustomTalkbackFormatter;
    protected int mCustomTextColorScrolling;
    protected boolean mCustomTypefaceSet = false;
    protected boolean mCustomWheelIntervalMode = false;
    protected boolean mDecrementVirtualButtonPressed;
    protected final Typeface mDefaultTypeface;
    protected String[] mDisplayedValues;
    protected ValueAnimator mFadeInAnimator;
    protected ValueAnimator mFadeOutAnimator;
    protected Scroller mFlingScroller;
    protected SeslNumberPicker.Formatter mFormatter;
    protected OverScroller mGravityScroller;
    protected HapticPreDrawListener mHapticPreDrawListener;
    protected Typeface mHcfFocusedTypefaceBold;
    protected final float mHeightRatio;
    protected FloatValueHolder mHolder;
    protected float mIdleAlpha;
    protected boolean mIgnoreMoveEvents;
    protected boolean mIgnoreUpEvent;
    protected boolean mIncrementVirtualButtonPressed;
    protected float mInitialAlpha;
    protected int mInitialScrollOffset = Integer.MIN_VALUE;
    protected final EditText mInputText;
    protected boolean mIsAmPm;
    protected boolean mIsBoldTextEnabled;
    protected boolean mIsCustomScrollColorForPicker = false;
    protected boolean mIsEditTextMode;
    protected boolean mIsEditTextModeEnabled = true;
    protected boolean mIsHcfEnabled;
    protected boolean mIsLongClicked = false;
    protected boolean mIsLongPressed = false;
    protected boolean mIsPressedBackKey = false;
    protected boolean mIsStartingAnimation = false;
    protected boolean mIsValueChanged = false;
    protected long mLastDownEventTime;
    protected float mLastDownEventY;
    protected float mLastDownOrMoveEventY;
    protected int mLastFocusedChildVirtualViewId;
    protected int mLastHoveredChildVirtualViewId;
    protected final Typeface mLegacyTypeface;
    protected final Scroller mLinearScroller;
    protected final int mMaxHeight;
    protected int mMaxValue;
    protected int mMaxWidth;
    protected int mMaximumFlingVelocity;
    protected final int mMinHeight;
    protected int mMinValue;
    protected final int mMinWidth;
    protected int mMinimumFlingVelocity;
    protected int mModifiedTxtHeight;
    protected SeslNumberPicker.OnEditTextModeChangedListener mOnEditTextModeChangedListener;
    protected SeslNumberPicker.OnValueChangeListener mOnValueChangeListener;
    protected boolean mPerformClickOnTap;
    protected String mPickerContentDescription;
    protected int mPickerSoundFastIndex;
    protected int mPickerSoundIndex;
    protected int mPickerSoundSlowIndex;
    protected Typeface mPickerSubTypeface;
    protected Typeface mPickerTypeface;
    protected int mPickerVibrateIndex;
    protected final PressedStateHelper mPressedStateHelper;
    protected int mPreviousScrollerY;
    protected float mPreviousSpringY;
    protected boolean mReservedStartAnimation = false;
    protected int mScrollState = 0;
    protected final int mSelectionDividerHeight;
    protected int mSelectorElementHeight;
    protected final SparseArray<String> mSelectorIndexToStringCache = new SparseArray<>();
    protected final int[] mSelectorIndices = new int[5];
    protected int mSelectorTextGapHeight;
    protected Paint mSelectorWheelPaint;
    protected int mShortFlickThreshold;
    protected SpringAnimation mSpringAnimation;
    private DynamicAnimation.OnAnimationEndListener mSpringAnimationEndListener;
    private DynamicAnimation.OnAnimationUpdateListener mSpringAnimationUpdateListener;
    protected boolean mSpringFlingRunning;
    protected SwitchIntervalOnLongPressCommand mSwitchIntervalOnLongPressCommand;
    protected int mTextColor;
    protected int mTextColorIdle;
    protected int mTextColorScrolling;
    protected int mTextSize;
    /* access modifiers changed from: private */
    public Toast mToast;
    private String mToastText;
    protected int mTopSelectionDividerTop;
    protected int mTouchSlop;
    protected String mUnitValue;
    private ValueAnimator.AnimatorUpdateListener mUpdateListener;
    protected int mValue;
    protected int mValueChangeOffset;
    protected VelocityTracker mVelocityTracker;
    protected final Drawable mVirtualButtonFocusedDrawable;
    protected int mWheelInterval = 1;
    protected boolean mWrapSelectorWheel;
    protected boolean mWrapSelectorWheelPreferred = true;
    protected int selectedPickerColor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class AccessibilityNodeProviderImpl extends AccessibilityNodeProvider {
        private int mAccessibilityFocusedView = Integer.MIN_VALUE;
        private final int[] mTempArray = new int[2];
        private final Rect mTempRect = new Rect();

        public AccessibilityNodeProviderImpl() {
        }

        private AccessibilityNodeInfo createAccessibilityNodeInfoForNumberPicker(int i2, int i7, int i8, int i10) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
            obtain.setClassName(NumberPicker.class.getName());
            obtain.setPackageName(SeslNumberPickerSpinnerDelegate.this.mContext.getPackageName());
            obtain.setSource(SeslNumberPickerSpinnerDelegate.this.mDelegator);
            if (hasVirtualDecrementButton()) {
                obtain.addChild(SeslNumberPickerSpinnerDelegate.this.mDelegator, 1);
            }
            obtain.addChild(SeslNumberPickerSpinnerDelegate.this.mDelegator, 2);
            if (hasVirtualIncrementButton()) {
                obtain.addChild(SeslNumberPickerSpinnerDelegate.this.mDelegator, 3);
            }
            obtain.setParent((View) SeslNumberPickerSpinnerDelegate.this.mDelegator.getParentForAccessibility());
            obtain.setEnabled(SeslNumberPickerSpinnerDelegate.this.mDelegator.isEnabled());
            obtain.setScrollable(true);
            float field_applicationScale = SeslCompatibilityInfoReflector.getField_applicationScale(SeslNumberPickerSpinnerDelegate.this.mContext.getResources());
            Rect rect = this.mTempRect;
            rect.set(i2, i7, i8, i10);
            scaleRect(rect, field_applicationScale);
            obtain.setBoundsInParent(rect);
            obtain.setVisibleToUser(SeslNumberPickerSpinnerDelegate.this.mDelegator.isVisibleToUserWrapper());
            int[] iArr = this.mTempArray;
            SeslNumberPickerSpinnerDelegate.this.mDelegator.getLocationOnScreen(iArr);
            rect.offset(iArr[0], iArr[1]);
            scaleRect(rect, field_applicationScale);
            obtain.setBoundsInScreen(rect);
            if (this.mAccessibilityFocusedView != -1) {
                obtain.addAction(64);
            } else {
                obtain.addAction(128);
            }
            if (SeslNumberPickerSpinnerDelegate.this.mDelegator.isEnabled()) {
                if (SeslNumberPickerSpinnerDelegate.this.getWrapSelectorWheel() || SeslNumberPickerSpinnerDelegate.this.getValue() < SeslNumberPickerSpinnerDelegate.this.getMaxValue()) {
                    obtain.addAction(4096);
                }
                if (SeslNumberPickerSpinnerDelegate.this.getWrapSelectorWheel() || SeslNumberPickerSpinnerDelegate.this.getValue() > SeslNumberPickerSpinnerDelegate.this.getMinValue()) {
                    obtain.addAction(SerializeOptions.SORT);
                }
            }
            return obtain;
        }

        private AccessibilityNodeInfo createAccessibilityNodeInfoForVirtualButton(int i2, String str, int i7, int i8, int i10, int i11) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
            obtain.setClassName(Button.class.getName());
            obtain.setPackageName(SeslNumberPickerSpinnerDelegate.this.mContext.getPackageName());
            obtain.setSource(SeslNumberPickerSpinnerDelegate.this.mDelegator, i2);
            obtain.setParent(SeslNumberPickerSpinnerDelegate.this.mDelegator);
            obtain.setText(str);
            AccessibilityNodeInfoCompat.wrap(obtain).setTooltipText(SeslNumberPickerSpinnerDelegate.this.mPickerContentDescription);
            obtain.setClickable(true);
            obtain.setLongClickable(true);
            obtain.setEnabled(SeslNumberPickerSpinnerDelegate.this.mDelegator.isEnabled());
            Rect rect = this.mTempRect;
            rect.set(i7, i8, i10, i11);
            obtain.setVisibleToUser(SeslNumberPickerSpinnerDelegate.this.mDelegator.isVisibleToUserWrapper(rect));
            obtain.setBoundsInParent(rect);
            int[] iArr = this.mTempArray;
            SeslNumberPickerSpinnerDelegate.this.mDelegator.getLocationOnScreen(iArr);
            rect.offset(iArr[0], iArr[1]);
            obtain.setBoundsInScreen(rect);
            if (this.mAccessibilityFocusedView != i2) {
                obtain.addAction(64);
            } else {
                obtain.addAction(128);
            }
            if (SeslNumberPickerSpinnerDelegate.this.mDelegator.isEnabled()) {
                obtain.addAction(16);
            }
            return obtain;
        }

        private AccessibilityNodeInfo createAccessibiltyNodeInfoForInputText(int i2, int i7, int i8, int i10) {
            AccessibilityNodeInfo createAccessibilityNodeInfo = SeslNumberPickerSpinnerDelegate.this.mInputText.createAccessibilityNodeInfo();
            createAccessibilityNodeInfo.setSource(SeslNumberPickerSpinnerDelegate.this.mDelegator, 2);
            if (this.mAccessibilityFocusedView != 2) {
                createAccessibilityNodeInfo.setAccessibilityFocused(false);
                createAccessibilityNodeInfo.addAction(64);
            } else {
                createAccessibilityNodeInfo.setAccessibilityFocused(true);
                createAccessibilityNodeInfo.addAction(128);
            }
            SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate = SeslNumberPickerSpinnerDelegate.this;
            if (!seslNumberPickerSpinnerDelegate.mIsEditTextModeEnabled) {
                createAccessibilityNodeInfo.setClassName(TextView.class.getName());
                createAccessibilityNodeInfo.setText(getVirtualCurrentButtonText(false));
                AccessibilityNodeInfoCompat.wrap(createAccessibilityNodeInfo).setTooltipText(SeslNumberPickerSpinnerDelegate.this.mPickerContentDescription);
                createAccessibilityNodeInfo.setSelected(true);
                createAccessibilityNodeInfo.setAccessibilityFocused(false);
            } else if (seslNumberPickerSpinnerDelegate.mCustomTalkbackFormatter != null) {
                createAccessibilityNodeInfo.setText(getVirtualCurrentButtonText(false));
            }
            Rect rect = this.mTempRect;
            rect.set(i2, i7, i8, i10);
            createAccessibilityNodeInfo.setVisibleToUser(SeslNumberPickerSpinnerDelegate.this.mDelegator.isVisibleToUserWrapper(rect));
            createAccessibilityNodeInfo.setBoundsInParent(rect);
            int[] iArr = this.mTempArray;
            SeslNumberPickerSpinnerDelegate.this.mDelegator.getLocationOnScreen(iArr);
            rect.offset(iArr[0], iArr[1]);
            createAccessibilityNodeInfo.setBoundsInScreen(rect);
            return createAccessibilityNodeInfo;
        }

        private void findAccessibilityNodeInfosByTextInChild(String str, int i2, List<AccessibilityNodeInfo> list) {
            if (i2 == 1) {
                String virtualDecrementButtonText = getVirtualDecrementButtonText();
                if (!TextUtils.isEmpty(virtualDecrementButtonText) && virtualDecrementButtonText.toLowerCase().contains(str)) {
                    list.add(createAccessibilityNodeInfo(1));
                }
            } else if (i2 == 2) {
                Editable text = SeslNumberPickerSpinnerDelegate.this.mInputText.getText();
                if (!TextUtils.isEmpty(text) && text.toString().toLowerCase().contains(str)) {
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
        public String getVirtualCurrentButtonText(boolean z) {
            String str;
            SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate = SeslNumberPickerSpinnerDelegate.this;
            int i2 = seslNumberPickerSpinnerDelegate.mValue;
            if (seslNumberPickerSpinnerDelegate.mWrapSelectorWheel) {
                i2 = seslNumberPickerSpinnerDelegate.getWrappedSelectorIndex(i2);
            }
            SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate2 = SeslNumberPickerSpinnerDelegate.this;
            if (i2 > seslNumberPickerSpinnerDelegate2.mMaxValue) {
                str = null;
            } else if (seslNumberPickerSpinnerDelegate2.mCustomTalkbackFormatter != null) {
                str = SeslNumberPickerSpinnerDelegate.this.mCustomTalkbackFormatter.format(i2);
            } else {
                SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate3 = SeslNumberPickerSpinnerDelegate.this;
                String[] strArr = seslNumberPickerSpinnerDelegate3.mDisplayedValues;
                if (strArr == null) {
                    str = seslNumberPickerSpinnerDelegate3.formatNumber(i2);
                } else {
                    str = strArr[i2 - seslNumberPickerSpinnerDelegate3.mMinValue];
                }
            }
            if (str == null || !z) {
                return str;
            }
            return C0212a.p(C0212a.t(str, ArcCommonLog.TAG_COMMA), SeslNumberPickerSpinnerDelegate.this.mPickerContentDescription, ArcCommonLog.TAG_COMMA);
        }

        private String getVirtualDecrementButtonText() {
            SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate = SeslNumberPickerSpinnerDelegate.this;
            int i2 = seslNumberPickerSpinnerDelegate.mWheelInterval;
            if (i2 == 1 || !seslNumberPickerSpinnerDelegate.mCustomWheelIntervalMode) {
                i2 = 1;
            }
            int i7 = seslNumberPickerSpinnerDelegate.mValue - i2;
            if (seslNumberPickerSpinnerDelegate.mWrapSelectorWheel) {
                i7 = seslNumberPickerSpinnerDelegate.getWrappedSelectorIndex(i7);
            }
            SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate2 = SeslNumberPickerSpinnerDelegate.this;
            if (i7 < seslNumberPickerSpinnerDelegate2.mMinValue) {
                return null;
            }
            if (seslNumberPickerSpinnerDelegate2.mCustomTalkbackFormatter != null) {
                return SeslNumberPickerSpinnerDelegate.this.mCustomTalkbackFormatter.format(i7);
            }
            SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate3 = SeslNumberPickerSpinnerDelegate.this;
            String[] strArr = seslNumberPickerSpinnerDelegate3.mDisplayedValues;
            if (strArr == null) {
                return seslNumberPickerSpinnerDelegate3.formatNumber(i7);
            }
            return strArr[i7 - seslNumberPickerSpinnerDelegate3.mMinValue];
        }

        private String getVirtualIncrementButtonText() {
            SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate = SeslNumberPickerSpinnerDelegate.this;
            int i2 = seslNumberPickerSpinnerDelegate.mWheelInterval;
            if (i2 == 1 || !seslNumberPickerSpinnerDelegate.mCustomWheelIntervalMode) {
                i2 = 1;
            }
            int i7 = seslNumberPickerSpinnerDelegate.mValue + i2;
            if (seslNumberPickerSpinnerDelegate.mWrapSelectorWheel) {
                i7 = seslNumberPickerSpinnerDelegate.getWrappedSelectorIndex(i7);
            }
            SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate2 = SeslNumberPickerSpinnerDelegate.this;
            if (i7 > seslNumberPickerSpinnerDelegate2.mMaxValue) {
                return null;
            }
            if (seslNumberPickerSpinnerDelegate2.mCustomTalkbackFormatter != null) {
                return SeslNumberPickerSpinnerDelegate.this.mCustomTalkbackFormatter.format(i7);
            }
            SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate3 = SeslNumberPickerSpinnerDelegate.this;
            String[] strArr = seslNumberPickerSpinnerDelegate3.mDisplayedValues;
            if (strArr == null) {
                return seslNumberPickerSpinnerDelegate3.formatNumber(i7);
            }
            return strArr[i7 - seslNumberPickerSpinnerDelegate3.mMinValue];
        }

        private boolean hasVirtualDecrementButton() {
            if (SeslNumberPickerSpinnerDelegate.this.getWrapSelectorWheel() || SeslNumberPickerSpinnerDelegate.this.getValue() > SeslNumberPickerSpinnerDelegate.this.getMinValue()) {
                return true;
            }
            return false;
        }

        private boolean hasVirtualIncrementButton() {
            if (SeslNumberPickerSpinnerDelegate.this.getWrapSelectorWheel() || SeslNumberPickerSpinnerDelegate.this.getValue() < SeslNumberPickerSpinnerDelegate.this.getMaxValue()) {
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

        private void sendAccessibilityEventForVirtualButton(int i2, int i7, String str) {
            if (SeslNumberPickerSpinnerDelegate.this.mAccessibilityManager.isEnabled()) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain(i7);
                obtain.setClassName(Button.class.getName());
                obtain.setPackageName(SeslNumberPickerSpinnerDelegate.this.mContext.getPackageName());
                obtain.getText().add(str);
                obtain.setEnabled(SeslNumberPickerSpinnerDelegate.this.mDelegator.isEnabled());
                obtain.setSource(SeslNumberPickerSpinnerDelegate.this.mDelegator, i2);
                SeslNumberPicker seslNumberPicker = SeslNumberPickerSpinnerDelegate.this.mDelegator;
                seslNumberPicker.requestSendAccessibilityEvent(seslNumberPicker, obtain);
            }
        }

        private void sendAccessibilityEventForVirtualText(int i2) {
            if (SeslNumberPickerSpinnerDelegate.this.mAccessibilityManager.isEnabled()) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
                SeslNumberPickerSpinnerDelegate.this.mInputText.onInitializeAccessibilityEvent(obtain);
                SeslNumberPickerSpinnerDelegate.this.mInputText.onPopulateAccessibilityEvent(obtain);
                obtain.setSource(SeslNumberPickerSpinnerDelegate.this.mDelegator, 2);
                SeslNumberPicker seslNumberPicker = SeslNumberPickerSpinnerDelegate.this.mDelegator;
                seslNumberPicker.requestSendAccessibilityEvent(seslNumberPicker, obtain);
            }
        }

        public AccessibilityNodeInfo createAccessibilityNodeInfo(int i2) {
            int left = SeslNumberPickerSpinnerDelegate.this.mDelegator.getLeft();
            int right = SeslNumberPickerSpinnerDelegate.this.mDelegator.getRight();
            int top = SeslNumberPickerSpinnerDelegate.this.mDelegator.getTop();
            int bottom = SeslNumberPickerSpinnerDelegate.this.mDelegator.getBottom();
            int scrollX = SeslNumberPickerSpinnerDelegate.this.mDelegator.getScrollX();
            int scrollY = SeslNumberPickerSpinnerDelegate.this.mDelegator.getScrollY();
            SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate = SeslNumberPickerSpinnerDelegate.this;
            if (!(seslNumberPickerSpinnerDelegate.mLastFocusedChildVirtualViewId == -1 && seslNumberPickerSpinnerDelegate.mLastHoveredChildVirtualViewId == Integer.MIN_VALUE)) {
                if (i2 == -1) {
                    return createAccessibilityNodeInfoForNumberPicker(scrollX, scrollY, (right - left) + scrollX, (bottom - top) + scrollY);
                }
                if (i2 == 1) {
                    SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate2 = SeslNumberPickerSpinnerDelegate.this;
                    return createAccessibilityNodeInfoForVirtualButton(1, getVirtualDecrementButtonText(), scrollX, scrollY, (right - left) + scrollX, seslNumberPickerSpinnerDelegate2.mTopSelectionDividerTop + seslNumberPickerSpinnerDelegate2.mSelectionDividerHeight);
                } else if (i2 == 2) {
                    int i7 = seslNumberPickerSpinnerDelegate.mTopSelectionDividerTop;
                    int i8 = seslNumberPickerSpinnerDelegate.mSelectionDividerHeight;
                    return createAccessibiltyNodeInfoForInputText(scrollX, i7 + i8, (right - left) + scrollX, seslNumberPickerSpinnerDelegate.mBottomSelectionDividerBottom - i8);
                } else if (i2 == 3) {
                    String virtualIncrementButtonText = getVirtualIncrementButtonText();
                    SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate3 = SeslNumberPickerSpinnerDelegate.this;
                    int i10 = (bottom - top) + scrollY;
                    return createAccessibilityNodeInfoForVirtualButton(3, virtualIncrementButtonText, scrollX, seslNumberPickerSpinnerDelegate3.mBottomSelectionDividerBottom - seslNumberPickerSpinnerDelegate3.mSelectionDividerHeight, (right - left) + scrollX, i10);
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
            SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate = SeslNumberPickerSpinnerDelegate.this;
            if (seslNumberPickerSpinnerDelegate.mIsStartingAnimation) {
                return false;
            }
            int right = seslNumberPickerSpinnerDelegate.mDelegator.getRight();
            int bottom = SeslNumberPickerSpinnerDelegate.this.mDelegator.getBottom();
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
                                    SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate2 = SeslNumberPickerSpinnerDelegate.this;
                                    seslNumberPickerSpinnerDelegate2.mDelegator.invalidate(0, seslNumberPickerSpinnerDelegate2.mBottomSelectionDividerBottom, right, bottom);
                                    return true;
                                } else if (this.mAccessibilityFocusedView == i2) {
                                    return false;
                                } else {
                                    this.mAccessibilityFocusedView = i2;
                                    sendAccessibilityEventForVirtualView(i2, 32768);
                                    SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate3 = SeslNumberPickerSpinnerDelegate.this;
                                    seslNumberPickerSpinnerDelegate3.mDelegator.invalidate(0, seslNumberPickerSpinnerDelegate3.mBottomSelectionDividerBottom, right, bottom);
                                    return true;
                                }
                            } else if (!SeslNumberPickerSpinnerDelegate.this.mDelegator.isEnabled()) {
                                return false;
                            } else {
                                SeslNumberPickerSpinnerDelegate.this.startFadeAnimation(false);
                                SeslNumberPickerSpinnerDelegate.this.changeValueByOne(true);
                                sendAccessibilityEventForVirtualView(i2, 1);
                                SeslNumberPickerSpinnerDelegate.this.startFadeAnimation(true);
                                return true;
                            }
                        }
                    } else if (i7 != 1) {
                        if (i7 != 2) {
                            if (i7 != 16) {
                                if (i7 != 32) {
                                    if (i7 != 64) {
                                        if (i7 != 128) {
                                            return SeslNumberPickerSpinnerDelegate.this.mInputText.performAccessibilityAction(i7, bundle);
                                        }
                                        if (this.mAccessibilityFocusedView != i2) {
                                            return false;
                                        }
                                        this.mAccessibilityFocusedView = Integer.MIN_VALUE;
                                        sendAccessibilityEventForVirtualView(i2, 65536);
                                        SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate4 = SeslNumberPickerSpinnerDelegate.this;
                                        seslNumberPickerSpinnerDelegate4.mDelegator.invalidate(0, seslNumberPickerSpinnerDelegate4.mTopSelectionDividerTop, right, seslNumberPickerSpinnerDelegate4.mBottomSelectionDividerBottom);
                                        return true;
                                    } else if (this.mAccessibilityFocusedView == i2) {
                                        return false;
                                    } else {
                                        this.mAccessibilityFocusedView = i2;
                                        sendAccessibilityEventForVirtualView(i2, 32768);
                                        SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate5 = SeslNumberPickerSpinnerDelegate.this;
                                        seslNumberPickerSpinnerDelegate5.mDelegator.invalidate(0, seslNumberPickerSpinnerDelegate5.mTopSelectionDividerTop, right, seslNumberPickerSpinnerDelegate5.mBottomSelectionDividerBottom);
                                        return true;
                                    }
                                } else if (!SeslNumberPickerSpinnerDelegate.this.mDelegator.isEnabled()) {
                                    return false;
                                } else {
                                    SeslNumberPickerSpinnerDelegate.this.performLongClick();
                                    return true;
                                }
                            } else if (!SeslNumberPickerSpinnerDelegate.this.mDelegator.isEnabled()) {
                                return false;
                            } else {
                                SeslNumberPickerSpinnerDelegate.this.performClick();
                                return true;
                            }
                        } else if (!SeslNumberPickerSpinnerDelegate.this.mDelegator.isEnabled() || !SeslNumberPickerSpinnerDelegate.this.mInputText.isFocused()) {
                            return false;
                        } else {
                            SeslNumberPickerSpinnerDelegate.this.mInputText.clearFocus();
                            return true;
                        }
                    } else if (!SeslNumberPickerSpinnerDelegate.this.mDelegator.isEnabled() || SeslNumberPickerSpinnerDelegate.this.mInputText.isFocused()) {
                        return false;
                    } else {
                        return SeslNumberPickerSpinnerDelegate.this.mInputText.requestFocus();
                    }
                } else if (i7 != 16) {
                    if (i7 != 64) {
                        if (i7 != 128 || this.mAccessibilityFocusedView != i2) {
                            return false;
                        }
                        this.mAccessibilityFocusedView = Integer.MIN_VALUE;
                        sendAccessibilityEventForVirtualView(i2, 65536);
                        SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate6 = SeslNumberPickerSpinnerDelegate.this;
                        seslNumberPickerSpinnerDelegate6.mDelegator.invalidate(0, 0, right, seslNumberPickerSpinnerDelegate6.mTopSelectionDividerTop);
                        return true;
                    } else if (this.mAccessibilityFocusedView == i2) {
                        return false;
                    } else {
                        this.mAccessibilityFocusedView = i2;
                        sendAccessibilityEventForVirtualView(i2, 32768);
                        SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate7 = SeslNumberPickerSpinnerDelegate.this;
                        seslNumberPickerSpinnerDelegate7.mDelegator.invalidate(0, 0, right, seslNumberPickerSpinnerDelegate7.mTopSelectionDividerTop);
                        return true;
                    }
                } else if (!SeslNumberPickerSpinnerDelegate.this.mDelegator.isEnabled()) {
                    return false;
                } else {
                    SeslNumberPickerSpinnerDelegate.this.startFadeAnimation(false);
                    SeslNumberPickerSpinnerDelegate.this.changeValueByOne(false);
                    sendAccessibilityEventForVirtualView(i2, 1);
                    SeslNumberPickerSpinnerDelegate.this.startFadeAnimation(true);
                    return true;
                }
            } else if (i7 != 64) {
                if (i7 != 128) {
                    if (i7 != 4096) {
                        if (i7 == 8192) {
                            if (!SeslNumberPickerSpinnerDelegate.this.mDelegator.isEnabled() || (!SeslNumberPickerSpinnerDelegate.this.getWrapSelectorWheel() && SeslNumberPickerSpinnerDelegate.this.getValue() <= SeslNumberPickerSpinnerDelegate.this.getMinValue())) {
                                return false;
                            }
                            SeslNumberPickerSpinnerDelegate.this.startFadeAnimation(false);
                            SeslNumberPickerSpinnerDelegate.this.changeValueByOne(false);
                            SeslNumberPickerSpinnerDelegate.this.startFadeAnimation(true);
                            return true;
                        }
                    } else if (!SeslNumberPickerSpinnerDelegate.this.mDelegator.isEnabled() || (!SeslNumberPickerSpinnerDelegate.this.getWrapSelectorWheel() && SeslNumberPickerSpinnerDelegate.this.getValue() >= SeslNumberPickerSpinnerDelegate.this.getMaxValue())) {
                        return false;
                    } else {
                        SeslNumberPickerSpinnerDelegate.this.startFadeAnimation(false);
                        SeslNumberPickerSpinnerDelegate.this.changeValueByOne(true);
                        SeslNumberPickerSpinnerDelegate.this.startFadeAnimation(true);
                        return true;
                    }
                } else if (this.mAccessibilityFocusedView != i2) {
                    return false;
                } else {
                    this.mAccessibilityFocusedView = Integer.MIN_VALUE;
                    SeslViewReflector.clearAccessibilityFocus(SeslNumberPickerSpinnerDelegate.this.mDelegator);
                    return true;
                }
            } else if (this.mAccessibilityFocusedView == i2) {
                return false;
            } else {
                this.mAccessibilityFocusedView = i2;
                SeslViewReflector.requestAccessibilityFocus(SeslNumberPickerSpinnerDelegate.this.mDelegator);
                return true;
            }
            return super.performAction(i2, i7, bundle);
        }

        public void sendAccessibilityEventForVirtualView(int i2, int i7) {
            if (i2 != 1) {
                if (i2 == 2) {
                    sendAccessibilityEventForVirtualText(i7);
                } else if (i2 == 3 && hasVirtualIncrementButton()) {
                    sendAccessibilityEventForVirtualButton(i2, i7, getVirtualIncrementButtonText());
                }
            } else if (hasVirtualDecrementButton()) {
                sendAccessibilityEventForVirtualButton(i2, i7, getVirtualDecrementButtonText());
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class BeginSoftInputOnLongPressCommand implements Runnable {
        public BeginSoftInputOnLongPressCommand() {
        }

        public void run() {
            SeslNumberPickerSpinnerDelegate.this.performLongClick();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class HapticPreDrawListener implements ViewTreeObserver.OnPreDrawListener {
        public boolean mSkipHapticCalls;

        private HapticPreDrawListener() {
            this.mSkipHapticCalls = false;
        }

        public boolean onPreDraw() {
            this.mSkipHapticCalls = false;
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class InputTextFilter extends NumberKeyListener {
        public InputTextFilter() {
        }

        public CharSequence filter(CharSequence charSequence, int i2, int i7, Spanned spanned, int i8, int i10) {
            if (SeslNumberPickerSpinnerDelegate.this.mDisplayedValues == null) {
                CharSequence filter = super.filter(charSequence, i2, i7, spanned, i8, i10);
                if (filter == null) {
                    filter = charSequence.subSequence(i2, i7);
                }
                String str = String.valueOf(spanned.subSequence(0, i8)) + filter + spanned.subSequence(i10, spanned.length());
                if ("".equals(str)) {
                    return str;
                }
                if (SeslNumberPickerSpinnerDelegate.this.getSelectedPos(str) <= SeslNumberPickerSpinnerDelegate.this.mMaxValue) {
                    int length = str.length();
                    SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate = SeslNumberPickerSpinnerDelegate.this;
                    if (length <= seslNumberPickerSpinnerDelegate.formatNumber(seslNumberPickerSpinnerDelegate.mMaxValue).length()) {
                        return filter;
                    }
                }
                SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate2 = SeslNumberPickerSpinnerDelegate.this;
                if (seslNumberPickerSpinnerDelegate2.mIsEditTextMode) {
                    if (seslNumberPickerSpinnerDelegate2.mToast == null) {
                        SeslNumberPickerSpinnerDelegate.this.initToastObject();
                    }
                    SeslNumberPickerSpinnerDelegate.this.mToast.show();
                }
                return "";
            }
            String valueOf = String.valueOf(charSequence.subSequence(i2, i7));
            String lowerCase = String.valueOf(String.valueOf(spanned.subSequence(0, i8)) + valueOf + spanned.subSequence(i10, spanned.length())).toLowerCase();
            boolean access$1300 = SeslNumberPickerSpinnerDelegate.this.needCompareEqualMonthLanguage();
            for (String lowerCase2 : SeslNumberPickerSpinnerDelegate.this.mDisplayedValues) {
                String lowerCase3 = lowerCase2.toLowerCase();
                if ((access$1300 && lowerCase3.equals(lowerCase)) || lowerCase3.startsWith(lowerCase)) {
                    return valueOf;
                }
            }
            if (SeslNumberPickerSpinnerDelegate.this.mIsEditTextMode && !TextUtils.isEmpty(lowerCase)) {
                if (SeslNumberPickerSpinnerDelegate.this.mToast == null) {
                    SeslNumberPickerSpinnerDelegate.this.initToastObject();
                }
                SeslNumberPickerSpinnerDelegate.this.mToast.show();
            }
            return "";
        }

        public char[] getAcceptedChars() {
            return SeslNumberPickerSpinnerDelegate.DIGIT_CHARACTERS;
        }

        public int getInputType() {
            return 1;
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
            SeslNumberPickerSpinnerDelegate.this.mDelegator.postDelayed(this, (long) ViewConfiguration.getTapTimeout());
        }

        public void buttonTapped(int i2) {
            cancel();
            this.mMode = 2;
            this.mManagedButton = i2;
            SeslNumberPickerSpinnerDelegate.this.mDelegator.post(this);
        }

        public void cancel() {
            int right = SeslNumberPickerSpinnerDelegate.this.mDelegator.getRight();
            int bottom = SeslNumberPickerSpinnerDelegate.this.mDelegator.getBottom();
            this.mMode = 0;
            this.mManagedButton = 0;
            SeslNumberPickerSpinnerDelegate.this.mDelegator.removeCallbacks(this);
            SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate = SeslNumberPickerSpinnerDelegate.this;
            if (seslNumberPickerSpinnerDelegate.mIncrementVirtualButtonPressed) {
                seslNumberPickerSpinnerDelegate.mIncrementVirtualButtonPressed = false;
                seslNumberPickerSpinnerDelegate.mDelegator.invalidate(0, seslNumberPickerSpinnerDelegate.mBottomSelectionDividerBottom, right, bottom);
            }
            SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate2 = SeslNumberPickerSpinnerDelegate.this;
            if (seslNumberPickerSpinnerDelegate2.mDecrementVirtualButtonPressed) {
                seslNumberPickerSpinnerDelegate2.mDecrementVirtualButtonPressed = false;
                seslNumberPickerSpinnerDelegate2.mDelegator.invalidate(0, 0, right, seslNumberPickerSpinnerDelegate2.mTopSelectionDividerTop);
            }
        }

        public void run() {
            int right = SeslNumberPickerSpinnerDelegate.this.mDelegator.getRight();
            int bottom = SeslNumberPickerSpinnerDelegate.this.mDelegator.getBottom();
            int i2 = this.mMode;
            if (i2 == 1) {
                int i7 = this.mManagedButton;
                if (i7 == 1) {
                    SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate = SeslNumberPickerSpinnerDelegate.this;
                    seslNumberPickerSpinnerDelegate.mIncrementVirtualButtonPressed = true;
                    seslNumberPickerSpinnerDelegate.mDelegator.invalidate(0, seslNumberPickerSpinnerDelegate.mBottomSelectionDividerBottom, right, bottom);
                } else if (i7 == 2) {
                    SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate2 = SeslNumberPickerSpinnerDelegate.this;
                    seslNumberPickerSpinnerDelegate2.mDecrementVirtualButtonPressed = true;
                    seslNumberPickerSpinnerDelegate2.mDelegator.invalidate(0, 0, right, seslNumberPickerSpinnerDelegate2.mTopSelectionDividerTop);
                }
            } else if (i2 == 2) {
                int i8 = this.mManagedButton;
                if (i8 == 1) {
                    SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate3 = SeslNumberPickerSpinnerDelegate.this;
                    if (!seslNumberPickerSpinnerDelegate3.mIncrementVirtualButtonPressed) {
                        seslNumberPickerSpinnerDelegate3.mDelegator.postDelayed(this, (long) ViewConfiguration.getPressedStateDuration());
                    }
                    SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate4 = SeslNumberPickerSpinnerDelegate.this;
                    seslNumberPickerSpinnerDelegate4.mIncrementVirtualButtonPressed = !seslNumberPickerSpinnerDelegate4.mIncrementVirtualButtonPressed;
                    seslNumberPickerSpinnerDelegate4.mDelegator.invalidate(0, seslNumberPickerSpinnerDelegate4.mBottomSelectionDividerBottom, right, bottom);
                } else if (i8 == 2) {
                    SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate5 = SeslNumberPickerSpinnerDelegate.this;
                    if (!seslNumberPickerSpinnerDelegate5.mDecrementVirtualButtonPressed) {
                        seslNumberPickerSpinnerDelegate5.mDelegator.postDelayed(this, (long) ViewConfiguration.getPressedStateDuration());
                    }
                    SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate6 = SeslNumberPickerSpinnerDelegate.this;
                    seslNumberPickerSpinnerDelegate6.mDecrementVirtualButtonPressed = !seslNumberPickerSpinnerDelegate6.mDecrementVirtualButtonPressed;
                    seslNumberPickerSpinnerDelegate6.mDelegator.invalidate(0, 0, right, seslNumberPickerSpinnerDelegate6.mTopSelectionDividerTop);
                }
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SwitchIntervalOnLongPressCommand implements Runnable {
        public SwitchIntervalOnLongPressCommand() {
        }

        public void run() {
            SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate = SeslNumberPickerSpinnerDelegate.this;
            seslNumberPickerSpinnerDelegate.mIgnoreMoveEvents = true;
            seslNumberPickerSpinnerDelegate.mIgnoreUpEvent = true;
            seslNumberPickerSpinnerDelegate.applyWheelCustomInterval(true ^ seslNumberPickerSpinnerDelegate.mCustomWheelIntervalMode);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SeslNumberPickerSpinnerDelegate(SeslNumberPicker seslNumberPicker, Context context, AttributeSet attributeSet, int i2, int i7) {
        super(seslNumberPicker, context);
        boolean z;
        int i8;
        PathInterpolator pathInterpolator = new PathInterpolator(0.5f, 0.0f, 0.4f, 1.0f);
        this.SIZE_PATH_INTERPOLATOR = pathInterpolator;
        PathInterpolator pathInterpolator2 = new PathInterpolator(0.17f, 0.17f, 0.83f, 0.83f);
        this.ALPHA_PATH_INTERPOLATOR = pathInterpolator2;
        this.mActivatedAlpha = 0.4f;
        this.mIdleAlpha = 0.2f;
        this.mInitialAlpha = 1.0f;
        this.mAlpha = 0.2f;
        this.mShortFlickThreshold = 1700;
        this.mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SeslNumberPickerSpinnerDelegate.this.mAlpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                SeslNumberPickerSpinnerDelegate.this.mDelegator.invalidate();
            }
        };
        this.mColorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SeslNumberPickerSpinnerDelegate.this.mTextColor = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                SeslNumberPickerSpinnerDelegate.this.mDelegator.invalidate();
            }
        };
        this.mSpringAnimationUpdateListener = new DynamicAnimation.OnAnimationUpdateListener() {
            public void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f, float f5) {
                SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate = SeslNumberPickerSpinnerDelegate.this;
                if (f5 <= 0.0f) {
                    f5 = -f5;
                }
                seslNumberPickerSpinnerDelegate.mCurVelocity = f5;
                float f8 = f - seslNumberPickerSpinnerDelegate.mPreviousSpringY;
                if (seslNumberPickerSpinnerDelegate.mSpringFlingRunning || Math.round(f8) != 0) {
                    if (Math.round(f8) == 0) {
                        SeslNumberPickerSpinnerDelegate.this.mSpringFlingRunning = false;
                    }
                    SeslNumberPickerSpinnerDelegate.this.scrollBy(0, Math.round(f8));
                    SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate2 = SeslNumberPickerSpinnerDelegate.this;
                    seslNumberPickerSpinnerDelegate2.mPreviousSpringY = f;
                    seslNumberPickerSpinnerDelegate2.mDelegator.invalidate();
                    return;
                }
                dynamicAnimation.cancel();
                if (!SeslNumberPickerSpinnerDelegate.this.ensureScrollWheelAdjusted()) {
                    boolean unused = SeslNumberPickerSpinnerDelegate.this.updateInputTextView();
                }
            }
        };
        this.mSpringAnimationEndListener = new DynamicAnimation.OnAnimationEndListener() {
            public void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f5) {
                SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate = SeslNumberPickerSpinnerDelegate.this;
                seslNumberPickerSpinnerDelegate.mSpringFlingRunning = false;
                seslNumberPickerSpinnerDelegate.mGravityScroller.forceFinished(true);
                SeslNumberPickerSpinnerDelegate.this.startFadeAnimation(true);
            }
        };
        Resources resources = this.mContext.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.sesl_number_picker_spinner_height);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R$dimen.sesl_number_picker_spinner_width);
        this.mHeightRatio = ((float) resources.getDimensionPixelSize(R$dimen.sesl_number_picker_spinner_edit_text_height)) / ((float) dimensionPixelSize);
        Context context2 = context;
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R$styleable.NumberPicker, i2, i7);
        int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R$styleable.NumberPicker_internalMinHeight, -1);
        this.mMinHeight = dimensionPixelSize3;
        int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(R$styleable.NumberPicker_internalMaxHeight, dimensionPixelSize);
        this.mMaxHeight = dimensionPixelSize4;
        int dimensionPixelSize5 = obtainStyledAttributes.getDimensionPixelSize(R$styleable.NumberPicker_internalMinWidth, dimensionPixelSize2);
        this.mMinWidth = dimensionPixelSize5;
        this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.NumberPicker_internalMaxWidth, -1);
        obtainStyledAttributes.recycle();
        if (dimensionPixelSize3 != -1 && dimensionPixelSize4 != -1 && dimensionPixelSize3 > dimensionPixelSize4) {
            throw new IllegalArgumentException("minHeight > maxHeight");
        } else if (dimensionPixelSize5 == -1 || (i8 = this.mMaxWidth) == -1 || dimensionPixelSize5 <= i8) {
            this.mSelectionDividerHeight = (int) TypedValue.applyDimension(1, 2.0f, resources.getDisplayMetrics());
            if (this.mMaxWidth == -1) {
                z = true;
            } else {
                z = false;
            }
            this.mComputeMaxWidth = z;
            if (!SeslMisc.isLightTheme(this.mContext)) {
                this.mIdleAlpha = 0.2f;
                this.mAlpha = 0.2f;
            }
            this.mPressedStateHelper = new PressedStateHelper();
            this.mDelegator.setWillNotDraw(false);
            ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R$layout.sesl_number_picker_spinner, this.mDelegator, true);
            EditText editText = (EditText) this.mDelegator.findViewById(R$id.numberpicker_input);
            this.mInputText = editText;
            editText.setLongClickable(false);
            editText.setIncludeFontPadding(false);
            editText.setAccessibilityDelegate(new View.AccessibilityDelegate() {
                public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
                    if (i2 == 16) {
                        SeslNumberPickerSpinnerDelegate.this.mInputText.selectAll();
                        SeslNumberPickerSpinnerDelegate.this.showSoftInput();
                    }
                    return super.performAccessibilityAction(view, i2, bundle);
                }
            });
            Typeface defaultFromStyle = Typeface.defaultFromStyle(1);
            this.mDefaultTypeface = defaultFromStyle;
            Typeface create = Typeface.create("sec-roboto-condensed-light", 1);
            this.mLegacyTypeface = create;
            int i10 = Build.VERSION.SDK_INT;
            if (i10 >= 34) {
                this.mPickerTypeface = Typeface.create(Typeface.create("sec", 0), 600, false);
            } else {
                Typeface create2 = Typeface.create("sec-roboto-light", 1);
                this.mPickerTypeface = create2;
                if (defaultFromStyle.equals(create2)) {
                    if (!create.equals(this.mPickerTypeface)) {
                        this.mPickerTypeface = create;
                    } else {
                        this.mPickerTypeface = Typeface.create("sans-serif-thin", 1);
                    }
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
                editText.setIncludeFontPadding(true);
                this.mPickerTypeface = defaultFromStyle;
                this.mPickerSubTypeface = Typeface.create(defaultFromStyle, 0);
            }
            this.mIsHcfEnabled = isHighContrastFontEnabled();
            this.mHcfFocusedTypefaceBold = Typeface.create(this.mPickerTypeface, 1);
            setInputTextTypeface();
            initPickerTextColor(this.mContext);
            this.mVirtualButtonFocusedDrawable = new ColorDrawable(this.selectedPickerColor);
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View view, boolean z) {
                    if (z) {
                        SeslNumberPickerSpinnerDelegate.this.setEditTextMode(true);
                        SeslNumberPickerSpinnerDelegate.this.mInputText.selectAll();
                        return;
                    }
                    SeslNumberPickerSpinnerDelegate.this.mInputText.setSelection(0, 0);
                    SeslNumberPickerSpinnerDelegate.this.validateInputTextView(view);
                }
            });
            editText.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (!(view instanceof EditText) || motionEvent.getActionMasked() != 0) {
                        return false;
                    }
                    ((EditText) view).selectAll();
                    SeslNumberPickerSpinnerDelegate.this.showSoftInput();
                    return true;
                }
            });
            editText.setFilters(new InputFilter[]{new InputTextFilter()});
            editText.setRawInputType(2);
            editText.setImeOptions(33554438);
            if (i10 >= 33) {
                editText.setAutoHandwritingEnabled(false);
            } else {
                editText.setPrivateImeOptions("disableDirectWriting=true;");
            }
            editText.setCursorVisible(false);
            SeslViewReflector.semSetHoverPopupType(editText, SeslHoverPopupWindowReflector.getField_TYPE_NONE());
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
            if (updateBoldTextEnabledInSettings()) {
                this.mSelectorWheelPaint.setFakeBoldText(true);
            }
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
            setFormatter(SeslNumberPicker.getTwoDigitFormatter());
            updateInputTextView();
            this.mDelegator.setVerticalScrollBarEnabled(false);
            if (this.mDelegator.getImportantForAccessibility() == 0) {
                this.mDelegator.setImportantForAccessibility(1);
            }
            this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
            this.mHapticPreDrawListener = new HapticPreDrawListener();
            this.mPickerVibrateIndex = SeslHapticFeedbackConstantsReflector.semGetVibrationIndex(32);
            this.mPickerSoundIndex = SeslAudioManagerReflector.getField_SOUND_TIME_PICKER_SCROLL();
            this.mPickerSoundFastIndex = SeslAudioManagerReflector.getField_SOUND_TIME_PICKER_SCROLL_FAST();
            this.mPickerSoundSlowIndex = SeslAudioManagerReflector.getField_SOUND_TIME_PICKER_SCROLL_SLOW();
            SeslSemSoundAssistantManagerReflector.setFastAudioOpenMode(this.mContext, true);
            this.mDelegator.setFocusableInTouchMode(false);
            this.mDelegator.setDescendantFocusability(131072);
            this.mDelegator.setDefaultFocusHighlightEnabled(false);
            this.mPickerContentDescription = "";
            this.mToastText = resources.getString(R$string.sesl_number_picker_invalid_value_entered);
            this.mUnitValue = "";
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
            ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(this.mTextColorIdle), Integer.valueOf(this.mTextColorScrolling)});
            this.mColorInAnimator = ofObject;
            ofObject.setInterpolator(pathInterpolator2);
            this.mColorInAnimator.setDuration(200);
            this.mColorInAnimator.addUpdateListener(this.mColorUpdateListener);
            ValueAnimator ofObject2 = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(this.mTextColorScrolling), Integer.valueOf(this.mTextColorIdle)});
            this.mColorOutAnimator = ofObject2;
            ofObject2.setInterpolator(pathInterpolator2);
            this.mColorOutAnimator.setDuration(200);
            this.mColorOutAnimator.setStartDelay(100);
            this.mColorOutAnimator.addUpdateListener(this.mColorUpdateListener);
        } else {
            throw new IllegalArgumentException("minWidth > maxWidth");
        }
    }

    /* access modifiers changed from: private */
    public void changeValueByOne(boolean z) {
        this.mInputText.setVisibility(4);
        if (!moveToFinalScrollerPosition(this.mFlingScroller)) {
            moveToFinalScrollerPosition(this.mAdjustScroller);
        }
        this.mPreviousScrollerY = 0;
        if (z) {
            this.mFlingScroller.startScroll(0, 0, 0, -this.mSelectorElementHeight, 500);
        } else {
            this.mFlingScroller.startScroll(0, 0, 0, this.mSelectorElementHeight, 500);
        }
        this.mDelegator.invalidate();
    }

    private void decrementSelectorIndices(int[] iArr) {
        System.arraycopy(iArr, 0, iArr, 1, iArr.length - 1);
        int i2 = iArr[1] - 1;
        if (this.mWrapSelectorWheel && i2 < this.mMinValue) {
            i2 = this.mMaxValue;
        }
        iArr[0] = i2;
        ensureCachedScrollSelectorValue(i2);
    }

    private void ensureCachedScrollSelectorValue(int i2) {
        String str;
        SparseArray<String> sparseArray = this.mSelectorIndexToStringCache;
        if (sparseArray.get(i2) == null) {
            int i7 = this.mMinValue;
            if (i2 < i7 || i2 > this.mMaxValue) {
                str = "";
            } else {
                String[] strArr = this.mDisplayedValues;
                if (strArr != null) {
                    str = strArr[i2 - i7];
                } else {
                    str = formatNumber(i2);
                }
            }
            sparseArray.put(i2, str);
        }
    }

    /* access modifiers changed from: private */
    public boolean ensureScrollWheelAdjusted() {
        return ensureScrollWheelAdjusted(0);
    }

    private void ensureValueAdjusted(int i2) {
        int i7 = this.mValue;
        int i8 = i7 % i2;
        if (i8 != 0) {
            int i10 = i7 - i8;
            if (i8 > i2 / 2) {
                i10 += i2;
            }
            setValueInternal(i10, true);
        }
    }

    private void fling(int i2) {
        int min;
        if (!this.mWrapSelectorWheel && i2 > 0 && getValue() == getMinValue()) {
            startFadeAnimation(true);
        } else if (this.mWrapSelectorWheel || i2 >= 0 || getValue() != getMaxValue()) {
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

    /* access modifiers changed from: private */
    public String formatNumber(int i2) {
        SeslNumberPicker.Formatter formatter = this.mFormatter;
        if (formatter != null) {
            return formatter.format(i2);
        }
        return formatNumberWithLocale(i2);
    }

    private static String formatNumberWithLocale(int i2) {
        return String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(i2)});
    }

    /* access modifiers changed from: private */
    public int getSelectedPos(String str) {
        if (this.mDisplayedValues == null) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                return this.mMinValue;
            }
        } else {
            for (int i2 = 0; i2 < this.mDisplayedValues.length; i2++) {
                str = str.toLowerCase();
                if (this.mDisplayedValues[i2].toLowerCase().startsWith(str)) {
                    return this.mMinValue + i2;
                }
            }
            return Integer.parseInt(str);
        }
    }

    /* access modifiers changed from: private */
    public int getWrappedSelectorIndex(int i2) {
        int i7 = this.mMaxValue;
        if (i2 > i7) {
            int i8 = this.mMinValue;
            return ((i2 - i8) % ((i7 - i8) + 1)) + i8;
        }
        int i10 = this.mMinValue;
        if (i2 < i10) {
            return i7 - ((i7 - i2) % ((i7 - i10) + 1));
        }
        return i2;
    }

    private void hideSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
        if (inputMethodManager != null && inputMethodManager.isActive(this.mInputText)) {
            inputMethodManager.hideSoftInputFromWindow(this.mDelegator.getWindowToken(), 0);
            this.mInputText.setVisibility(4);
        }
    }

    private void incrementSelectorIndices(int[] iArr) {
        System.arraycopy(iArr, 1, iArr, 0, iArr.length - 1);
        int i2 = iArr[iArr.length - 2] + 1;
        if (this.mWrapSelectorWheel && i2 > this.mMaxValue) {
            i2 = this.mMinValue;
        }
        iArr[iArr.length - 1] = i2;
        ensureCachedScrollSelectorValue(i2);
    }

    private void initPickerTextColor(Context context) {
        if (this.mIsCustomScrollColorForPicker) {
            this.mTextColorScrolling = this.mCustomTextColorScrolling;
            Resources resources = context.getResources();
            int i2 = R$color.sesl_number_picker_text_color_appwidget;
            this.mTextColorIdle = ResourcesCompat.getColor(resources, i2, context.getTheme());
            this.selectedPickerColor = ResourcesCompat.getColor(context.getResources(), R$color.sesl_number_picker_text_highlight_color_appwidget, context.getTheme());
            int i7 = this.mTextColorIdle;
            this.mTextColor = i7;
            this.mSelectorWheelPaint.setColor(i7);
            this.mInputText.setHighlightColor(this.selectedPickerColor);
            this.mInputText.setTextColor(this.mContext.getResources().getColor(i2));
            return;
        }
        this.mTextColorScrolling = ResourcesCompat.getColor(context.getResources(), R$color.sesl_number_picker_text_color_scroll, context.getTheme());
        this.mTextColorIdle = this.mInputText.getTextColors().getColorForState(this.mDelegator.getEnableStateSet(), -1);
        int color = ResourcesCompat.getColor(context.getResources(), R$color.sesl_number_picker_text_highlight_color, context.getTheme());
        this.selectedPickerColor = color;
        this.mTextColor = this.mTextColorIdle;
        this.mInputText.setHighlightColor(color);
    }

    /* access modifiers changed from: private */
    public void initToastObject() {
        this.mToast = Toast.makeText(this.mContext, this.mToastText, 0);
        View inflate = LayoutInflater.from(this.mContext).inflate(R$layout.sesl_custom_toast_layout, (ViewGroup) null);
        ((TextView) inflate.findViewById(R$id.message)).setText(this.mToastText);
        this.mToast.setView(inflate);
    }

    private void initializeSelectorWheel() {
        if (this.mIsStartingAnimation) {
            if (!moveToFinalScrollerPosition(this.mFlingScroller)) {
                moveToFinalScrollerPosition(this.mAdjustScroller);
            }
            stopScrollAnimation();
        } else {
            initializeSelectorWheelIndices();
        }
        int bottom = (int) ((((float) ((this.mDelegator.getBottom() - this.mDelegator.getTop()) - (this.mTextSize * 3))) / ((float) 3)) + 0.5f);
        this.mSelectorTextGapHeight = bottom;
        int i2 = this.mTextSize + bottom;
        this.mSelectorElementHeight = i2;
        int i7 = this.mModifiedTxtHeight;
        if (i7 > i2 || this.mIsAmPm) {
            i7 = this.mDelegator.getHeight() / 3;
        }
        this.mValueChangeOffset = i7;
        int top = ((this.mModifiedTxtHeight / 2) + this.mInputText.getTop()) - this.mSelectorElementHeight;
        this.mInitialScrollOffset = top;
        this.mCurrentScrollOffset = top;
        ((SeslNumberPicker.CustomEditText) this.mInputText).setEditTextPosition(((int) (((this.mSelectorWheelPaint.descent() - this.mSelectorWheelPaint.ascent()) / 2.0f) - this.mSelectorWheelPaint.descent())) - (this.mInputText.getBaseline() - (this.mModifiedTxtHeight / 2)));
        if (this.mReservedStartAnimation) {
            startAnimation(0, (SeslAnimationListener) null);
            this.mReservedStartAnimation = false;
        }
    }

    private void initializeSelectorWheelIndices() {
        int i2;
        int i7;
        this.mSelectorIndexToStringCache.clear();
        int[] iArr = this.mSelectorIndices;
        if (this.mIsStartingAnimation) {
            i2 = iArr[2];
        } else {
            i2 = getValue();
        }
        for (int i8 = 0; i8 < this.mSelectorIndices.length; i8++) {
            int i10 = i8 - 2;
            if (this.mCustomWheelIntervalMode) {
                i7 = this.mWheelInterval;
            } else {
                i7 = 1;
            }
            int i11 = (i10 * i7) + i2;
            if (this.mWrapSelectorWheel) {
                i11 = getWrappedSelectorIndex(i11);
            }
            iArr[i8] = i11;
            ensureCachedScrollSelectorValue(i11);
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

    /* access modifiers changed from: private */
    public boolean needCompareEqualMonthLanguage() {
        if (!"vi".equals(Locale.getDefault().getLanguage()) || !"inputType=month_edittext".equals(this.mInputText.getPrivateImeOptions())) {
            return false;
        }
        return true;
    }

    private void notifyChange(int i2, int i7) {
        if (this.mAccessibilityManager.isEnabled() && !this.mIsStartingAnimation) {
            int wrappedSelectorIndex = getWrappedSelectorIndex(this.mValue);
            if (wrappedSelectorIndex <= this.mMaxValue) {
                String[] strArr = this.mDisplayedValues;
                if (strArr == null) {
                    formatNumber(wrappedSelectorIndex);
                } else {
                    String str = strArr[wrappedSelectorIndex - this.mMinValue];
                }
            }
            this.mDelegator.sendAccessibilityEvent(4);
            AccessibilityNodeProviderImpl accessibilityNodeProviderImpl = (AccessibilityNodeProviderImpl) getAccessibilityNodeProvider();
            if (accessibilityNodeProviderImpl != null && (!this.mIsEditTextModeEnabled || (!this.mWrapSelectorWheel && (getValue() == getMaxValue() || getValue() == getMinValue())))) {
                accessibilityNodeProviderImpl.performAction(2, 64, (Bundle) null);
            }
        }
        SeslNumberPicker.OnValueChangeListener onValueChangeListener = this.mOnValueChangeListener;
        if (onValueChangeListener != null) {
            onValueChangeListener.onValueChange(this.mDelegator, i2, this.mValue);
        }
    }

    private void onScrollStateChange(int i2) {
        if (this.mScrollState != i2) {
            this.mScrollState = i2;
        }
    }

    private void onScrollerFinished(Scroller scroller) {
        if (scroller == this.mFlingScroller) {
            if (!ensureScrollWheelAdjusted()) {
                updateInputTextView();
            }
            onScrollStateChange(0);
        } else if (this.mScrollState != 1) {
            updateInputTextView();
        }
    }

    private void playSoundAndHapticFeedback() {
        int i2;
        AudioManager audioManager = this.mAudioManager;
        if (this.mCurVelocity > 1000.0f) {
            i2 = this.mPickerSoundFastIndex;
        } else {
            i2 = this.mPickerSoundSlowIndex;
        }
        audioManager.playSoundEffect(i2);
        if (!this.mHapticPreDrawListener.mSkipHapticCalls) {
            this.mDelegator.performHapticFeedback(50056);
            this.mHapticPreDrawListener.mSkipHapticCalls = true;
        }
    }

    private void postBeginSoftInputOnLongPressCommand() {
        BeginSoftInputOnLongPressCommand beginSoftInputOnLongPressCommand = this.mBeginSoftInputOnLongPressCommand;
        if (beginSoftInputOnLongPressCommand == null) {
            this.mBeginSoftInputOnLongPressCommand = new BeginSoftInputOnLongPressCommand();
        } else {
            this.mDelegator.removeCallbacks(beginSoftInputOnLongPressCommand);
        }
        this.mDelegator.postDelayed(this.mBeginSoftInputOnLongPressCommand, (long) ViewConfiguration.getLongPressTimeout());
    }

    private void postSwitchIntervalOnLongPress() {
        SwitchIntervalOnLongPressCommand switchIntervalOnLongPressCommand = this.mSwitchIntervalOnLongPressCommand;
        if (switchIntervalOnLongPressCommand == null) {
            this.mSwitchIntervalOnLongPressCommand = new SwitchIntervalOnLongPressCommand();
        } else {
            this.mDelegator.removeCallbacks(switchIntervalOnLongPressCommand);
        }
        this.mDelegator.postDelayed(this.mSwitchIntervalOnLongPressCommand, (long) ViewConfiguration.getLongPressTimeout());
    }

    private void removeAllCallbacks() {
        SwitchIntervalOnLongPressCommand switchIntervalOnLongPressCommand = this.mSwitchIntervalOnLongPressCommand;
        if (switchIntervalOnLongPressCommand != null) {
            this.mDelegator.removeCallbacks(switchIntervalOnLongPressCommand);
        }
        BeginSoftInputOnLongPressCommand beginSoftInputOnLongPressCommand = this.mBeginSoftInputOnLongPressCommand;
        if (beginSoftInputOnLongPressCommand != null) {
            this.mDelegator.removeCallbacks(beginSoftInputOnLongPressCommand);
        }
        this.mPressedStateHelper.cancel();
    }

    private void removeBeginSoftInputCommand() {
        BeginSoftInputOnLongPressCommand beginSoftInputOnLongPressCommand = this.mBeginSoftInputOnLongPressCommand;
        if (beginSoftInputOnLongPressCommand != null) {
            this.mDelegator.removeCallbacks(beginSoftInputOnLongPressCommand);
        }
    }

    private void removeSwitchIntervalOnLongPress() {
        SwitchIntervalOnLongPressCommand switchIntervalOnLongPressCommand = this.mSwitchIntervalOnLongPressCommand;
        if (switchIntervalOnLongPressCommand != null) {
            this.mDelegator.removeCallbacks(switchIntervalOnLongPressCommand);
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

    private void setValueInternal(int i2, boolean z) {
        int i7;
        if (this.mValue != i2) {
            if (this.mWrapSelectorWheel) {
                i7 = getWrappedSelectorIndex(i2);
            } else {
                i7 = Math.min(Math.max(i2, this.mMinValue), this.mMaxValue);
            }
            int i8 = this.mValue;
            this.mValue = i7;
            updateInputTextView();
            if (z) {
                notifyChange(i8, i7);
            }
            initializeSelectorWheelIndices();
            this.mDelegator.invalidate();
            if (this.mAccessibilityManager.isEnabled() && this.mDelegator.getParent() != null) {
                ViewParent parent = this.mDelegator.getParent();
                SeslNumberPicker seslNumberPicker = this.mDelegator;
                parent.notifySubtreeAccessibilityStateChanged(seslNumberPicker, seslNumberPicker, 1);
            }
        } else if (isCharacterNumberLanguage()) {
            updateInputTextView();
            this.mDelegator.invalidate();
        }
    }

    /* access modifiers changed from: private */
    public void showSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
        if (inputMethodManager != null) {
            this.mInputText.setVisibility(0);
            this.mInputText.requestFocus();
            inputMethodManager.viewClicked(this.mInputText);
            inputMethodManager.showSoftInput(this.mInputText, 0);
        }
    }

    private void showSoftInputForWindowFocused() {
        this.mDelegator.postDelayed(new Runnable() {
            public void run() {
                InputMethodManager inputMethodManager = (InputMethodManager) SeslNumberPickerSpinnerDelegate.this.mContext.getSystemService("input_method");
                if (inputMethodManager != null) {
                    SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate = SeslNumberPickerSpinnerDelegate.this;
                    if (seslNumberPickerSpinnerDelegate.mIsEditTextMode && seslNumberPickerSpinnerDelegate.mInputText.isFocused() && !inputMethodManager.showSoftInput(SeslNumberPickerSpinnerDelegate.this.mInputText, 0)) {
                        SeslNumberPickerSpinnerDelegate.this.mDelegator.postDelayed(new Runnable() {
                            public void run() {
                                InputMethodManager inputMethodManager = (InputMethodManager) SeslNumberPickerSpinnerDelegate.this.mContext.getSystemService("input_method");
                                if (inputMethodManager != null) {
                                    SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate = SeslNumberPickerSpinnerDelegate.this;
                                    if (seslNumberPickerSpinnerDelegate.mIsEditTextMode && seslNumberPickerSpinnerDelegate.mInputText.isFocused()) {
                                        inputMethodManager.showSoftInput(SeslNumberPickerSpinnerDelegate.this.mInputText, 0);
                                    }
                                }
                            }
                        }, 20);
                    }
                }
            }
        }, 20);
    }

    /* access modifiers changed from: private */
    public void startFadeAnimation(boolean z) {
        int i2;
        int i7 = 0;
        if (z) {
            ValueAnimator valueAnimator = this.mFadeOutAnimator;
            if (this.mFlingScroller.isFinished()) {
                i2 = 0;
            } else {
                i2 = this.mFlingScroller.getDuration();
            }
            valueAnimator.setStartDelay((long) (i2 + 100));
            ValueAnimator valueAnimator2 = this.mColorOutAnimator;
            if (!this.mFlingScroller.isFinished()) {
                i7 = this.mFlingScroller.getDuration();
            }
            valueAnimator2.setStartDelay((long) (i7 + 100));
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
        this.mIsStartingAnimation = false;
        if (!moveToFinalScrollerPosition(this.mFlingScroller)) {
            moveToFinalScrollerPosition(this.mAdjustScroller);
        }
        ensureScrollWheelAdjusted();
    }

    private void tryComputeMaxWidth() {
        int i2;
        if (this.mComputeMaxWidth) {
            String[] strArr = this.mDisplayedValues;
            int i7 = 0;
            if (strArr == null) {
                float f = 0.0f;
                for (int i8 = 0; i8 <= 9; i8++) {
                    float measureText = this.mSelectorWheelPaint.measureText(formatNumberWithLocale(i8));
                    if (measureText > f) {
                        f = measureText;
                    }
                }
                for (int i10 = this.mMaxValue; i10 > 0; i10 /= 10) {
                    i7++;
                }
                i2 = (int) (((float) i7) * f);
            } else {
                int length = strArr.length;
                int i11 = 0;
                int i12 = 0;
                while (i7 < length) {
                    float measureText2 = this.mSelectorWheelPaint.measureText(this.mDisplayedValues[i7]);
                    if (measureText2 > ((float) i11)) {
                        i11 = (int) measureText2;
                        i12 = this.mDisplayedValues[i7].length();
                    }
                    i7++;
                }
                i2 = i11;
                i7 = i12;
            }
            int paddingRight = this.mInputText.getPaddingRight() + this.mInputText.getPaddingLeft() + i2;
            if (isHighContrastFontEnabled()) {
                paddingRight += (i7 + 2) * ((int) Math.ceil((double) (SeslPaintReflector.getHCTStrokeWidth(this.mSelectorWheelPaint) / 2.0f)));
            }
            if (this.mMaxWidth != paddingRight) {
                int i13 = this.mMinWidth;
                if (paddingRight > i13) {
                    this.mMaxWidth = paddingRight;
                } else {
                    this.mMaxWidth = i13;
                }
                this.mDelegator.invalidate();
            }
        }
    }

    private boolean updateBoldTextEnabledInSettings() {
        boolean z = false;
        if (Settings.Global.getInt(this.mContext.getContentResolver(), "bold_text", 0) != 0) {
            z = true;
        }
        this.mIsBoldTextEnabled = z;
        return z;
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

    /* access modifiers changed from: private */
    public boolean updateInputTextView() {
        String str;
        String[] strArr = this.mDisplayedValues;
        if (strArr == null) {
            str = formatNumber(this.mValue);
        } else {
            str = strArr[this.mValue - this.mMinValue];
        }
        if (TextUtils.isEmpty(str) || str.equals(this.mInputText.getText().toString())) {
            return false;
        }
        this.mInputText.setText(str);
        Selection.setSelection(this.mInputText.getText(), this.mInputText.getText().length());
        return true;
    }

    private void updateWrapSelectorWheel() {
        boolean z;
        if (this.mMaxValue - this.mMinValue < this.mSelectorIndices.length || !this.mWrapSelectorWheelPreferred) {
            z = false;
        } else {
            z = true;
        }
        if (this.mWrapSelectorWheel != z) {
            this.mWrapSelectorWheel = z;
            initializeSelectorWheelIndices();
            this.mDelegator.invalidate();
        }
    }

    /* access modifiers changed from: private */
    public void validateInputTextView(View view) {
        String valueOf = String.valueOf(((TextView) view).getText());
        int selectedPos = getSelectedPos(valueOf);
        boolean z = false;
        if (TextUtils.isEmpty(valueOf) || this.mValue == selectedPos) {
            int i2 = this.mWheelInterval;
            if (i2 == 1 || !this.mCustomWheelIntervalMode || !this.mIsPressedBackKey) {
                updateInputTextView();
                return;
            }
            if (selectedPos % i2 == 0) {
                z = true;
            }
            applyWheelCustomInterval(z);
            return;
        }
        int i7 = this.mWheelInterval;
        if (i7 != 1 && this.mCustomWheelIntervalMode) {
            if (selectedPos % i7 == 0) {
                z = true;
            }
            applyWheelCustomInterval(z);
        }
        setValueInternal(selectedPos, true);
    }

    public void applyWheelCustomInterval(boolean z) {
        int i2 = this.mWheelInterval;
        if (i2 != 1) {
            this.mCustomWheelIntervalMode = z;
            if (z) {
                ensureValueAdjusted(i2);
            }
            initializeSelectorWheelIndices();
            this.mDelegator.invalidate();
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
        return ((this.mMaxValue - this.mMinValue) + 1) * this.mSelectorElementHeight;
    }

    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        if (!this.mAccessibilityManager.isEnabled()) {
            return false;
        }
        int y = (int) motionEvent.getY();
        int i2 = 2;
        if (!this.mIsEditTextMode) {
            if (y <= this.mTopSelectionDividerTop) {
                i2 = 1;
            } else if (this.mBottomSelectionDividerBottom <= y) {
                i2 = 3;
            }
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
                    if (this.mIsEditTextMode) {
                        return false;
                    }
                    if (action == 0) {
                        if (keyCode == 20) {
                            int i2 = this.mLastFocusedChildVirtualViewId;
                            if (i2 == 1) {
                                this.mLastFocusedChildVirtualViewId = 2;
                                this.mDelegator.invalidate();
                                return true;
                            } else if (i2 == 2) {
                                if (!this.mWrapSelectorWheel && getValue() == getMaxValue()) {
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
                            } else if (!this.mWrapSelectorWheel && getValue() == getMinValue()) {
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
        if (!this.mIsEditTextMode && action == 1) {
            if (this.mLastFocusedChildVirtualViewId == 2) {
                if (!this.mIsEditTextModeEnabled) {
                    return false;
                }
                this.mInputText.setVisibility(0);
                this.mInputText.requestFocus();
                showSoftInput();
                removeAllCallbacks();
                return true;
            } else if (this.mFlingScroller.isFinished()) {
                int i8 = this.mLastFocusedChildVirtualViewId;
                if (i8 == 1) {
                    startFadeAnimation(false);
                    changeValueByOne(false);
                    if (!this.mWrapSelectorWheel && getValue() == getMinValue() + 1) {
                        this.mLastFocusedChildVirtualViewId = 2;
                    }
                    startFadeAnimation(true);
                } else if (i8 == 3) {
                    startFadeAnimation(false);
                    changeValueByOne(true);
                    if (!this.mWrapSelectorWheel && getValue() == getMaxValue() - 1) {
                        this.mLastFocusedChildVirtualViewId = 2;
                    }
                    startFadeAnimation(true);
                }
            }
        }
        return false;
    }

    public boolean dispatchKeyEventPreIme(KeyEvent keyEvent) {
        if (!this.mIsEditTextModeEnabled) {
            return false;
        }
        if ((this.mInputText.hasFocus() || (!this.mIsEditTextModeEnabled && this.mDelegator.hasFocus())) && keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1) {
            this.mIsPressedBackKey = true;
            hideSoftInput();
            setEditTextMode(false);
            return true;
        }
        this.mIsPressedBackKey = false;
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

    public String[] getDisplayedValues() {
        return this.mDisplayedValues;
    }

    public EditText getEditText() {
        return this.mInputText;
    }

    public int getMaxValue() {
        return this.mMaxValue;
    }

    public int getMinValue() {
        return this.mMinValue;
    }

    public int getPaintFlags() {
        return this.mSelectorWheelPaint.getFlags();
    }

    public int getValue() {
        return this.mValue;
    }

    public boolean getWrapSelectorWheel() {
        return this.mWrapSelectorWheel;
    }

    public boolean isChangedDefaultInterval() {
        if (this.mWheelInterval == 1 || this.mCustomWheelIntervalMode) {
            return false;
        }
        return true;
    }

    public boolean isEditTextMode() {
        return this.mIsEditTextMode;
    }

    public boolean isEditTextModeNotAmPm() {
        if (!this.mIsEditTextMode || this.mIsAmPm) {
            return false;
        }
        return true;
    }

    public void onAttachedToWindow() {
        this.mDelegator.getViewTreeObserver().addOnPreDrawListener(this.mHapticPreDrawListener);
    }

    public void onConfigurationChanged(Configuration configuration) {
        boolean z = this.mIsBoldTextEnabled;
        updateBoldTextEnabledInSettings();
        boolean z3 = this.mIsBoldTextEnabled;
        if (z != z3) {
            this.mSelectorWheelPaint.setFakeBoldText(z3);
        }
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
        float f = ((float) (right - left)) / 2.0f;
        float f5 = (float) (this.mCurrentScrollOffset - this.mSelectorElementHeight);
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
        int[] iArr = this.mSelectorIndices;
        for (int i7 : iArr) {
            String str = this.mSelectorIndexToStringCache.get(i7);
            if (str != null && !str.isEmpty() && !this.mUnitValue.isEmpty()) {
                StringBuilder s = C0212a.s(str);
                s.append(this.mUnitValue);
                str = s.toString();
            }
            float f8 = this.mAlpha;
            float f10 = this.mIdleAlpha;
            if (f8 < f10) {
                f8 = f10;
            }
            int descent = (int) ((((this.mSelectorWheelPaint.descent() - this.mSelectorWheelPaint.ascent()) / 2.0f) + f5) - this.mSelectorWheelPaint.descent());
            int i8 = this.mTopSelectionDividerTop;
            int i10 = this.mInitialScrollOffset;
            if (f5 >= ((float) (i8 - i10))) {
                int i11 = this.mBottomSelectionDividerBottom;
                if (f5 <= ((float) (i10 + i11))) {
                    if (f5 <= ((float) (i8 + i11)) / 2.0f) {
                        canvas2.save();
                        canvas2.clipRect(0, this.mTopSelectionDividerTop, right, this.mBottomSelectionDividerBottom);
                        this.mSelectorWheelPaint.setColor(this.mTextColor);
                        this.mSelectorWheelPaint.setTypeface(this.mPickerTypeface);
                        float f11 = (float) descent;
                        canvas2.drawText(str, f, f11, this.mSelectorWheelPaint);
                        canvas2.restore();
                        canvas2.save();
                        canvas2.clipRect(0, 0, right, this.mTopSelectionDividerTop);
                        this.mSelectorWheelPaint.setTypeface(this.mPickerSubTypeface);
                        this.mSelectorWheelPaint.setAlpha((int) (f8 * 255.0f * this.mInitialAlpha));
                        canvas2.drawText(str, f, f11, this.mSelectorWheelPaint);
                        canvas2.restore();
                    } else {
                        canvas2.save();
                        canvas2.clipRect(0, this.mTopSelectionDividerTop, right, this.mBottomSelectionDividerBottom);
                        this.mSelectorWheelPaint.setTypeface(this.mPickerTypeface);
                        this.mSelectorWheelPaint.setColor(this.mTextColor);
                        float f12 = (float) descent;
                        canvas2.drawText(str, f, f12, this.mSelectorWheelPaint);
                        canvas2.restore();
                        canvas2.save();
                        canvas2.clipRect(0, this.mBottomSelectionDividerBottom, right, bottom);
                        this.mSelectorWheelPaint.setAlpha((int) (f8 * 255.0f * this.mInitialAlpha));
                        this.mSelectorWheelPaint.setTypeface(this.mPickerSubTypeface);
                        canvas2.drawText(str, f, f12, this.mSelectorWheelPaint);
                        canvas2.restore();
                    }
                    f5 += (float) this.mSelectorElementHeight;
                }
            }
            canvas2.save();
            this.mSelectorWheelPaint.setAlpha((int) (f8 * 255.0f * this.mInitialAlpha));
            this.mSelectorWheelPaint.setTypeface(this.mPickerSubTypeface);
            canvas2.drawText(str, f, (float) descent, this.mSelectorWheelPaint);
            canvas2.restore();
            f5 += (float) this.mSelectorElementHeight;
        }
    }

    public void onFocusChanged(boolean z, int i2, Rect rect) {
        AccessibilityNodeProviderImpl accessibilityNodeProviderImpl;
        AccessibilityNodeProviderImpl accessibilityNodeProviderImpl2;
        if (!z) {
            if (this.mAccessibilityManager.isEnabled() && (accessibilityNodeProviderImpl2 = (AccessibilityNodeProviderImpl) getAccessibilityNodeProvider()) != null) {
                if (this.mIsEditTextMode) {
                    this.mLastFocusedChildVirtualViewId = 2;
                }
                accessibilityNodeProviderImpl2.performAction(this.mLastFocusedChildVirtualViewId, 128, (Bundle) null);
            }
            this.mLastFocusedChildVirtualViewId = -1;
            this.mLastHoveredChildVirtualViewId = Integer.MIN_VALUE;
        } else {
            if (this.mIsEditTextMode) {
                this.mLastFocusedChildVirtualViewId = -1;
                if (this.mInputText.getVisibility() == 0) {
                    this.mInputText.requestFocus();
                }
            } else {
                this.mLastFocusedChildVirtualViewId = 1;
                if (!this.mWrapSelectorWheel && getValue() == getMinValue()) {
                    this.mLastFocusedChildVirtualViewId = 2;
                }
            }
            if (this.mAccessibilityManager.isEnabled() && (accessibilityNodeProviderImpl = (AccessibilityNodeProviderImpl) getAccessibilityNodeProvider()) != null) {
                if (this.mIsEditTextMode) {
                    this.mLastFocusedChildVirtualViewId = 2;
                }
                accessibilityNodeProviderImpl.performAction(this.mLastFocusedChildVirtualViewId, 64, (Bundle) null);
            }
        }
        this.mDelegator.invalidate();
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.mDelegator.isEnabled() && !this.mIsEditTextMode && !this.mIsStartingAnimation && (motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8) {
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
        accessibilityEvent.setClassName(NumberPicker.class.getName());
        accessibilityEvent.setScrollable(true);
        accessibilityEvent.setScrollY((this.mMinValue + this.mValue) * this.mSelectorElementHeight);
        accessibilityEvent.setMaxScrollY((this.mMaxValue - this.mMinValue) * this.mSelectorElementHeight);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.mDelegator.isEnabled() || this.mIsEditTextMode || this.mIsStartingAnimation || motionEvent.getActionMasked() != 0) {
            return false;
        }
        removeAllCallbacks();
        this.mInputText.setVisibility(4);
        float y = motionEvent.getY();
        this.mLastDownEventY = y;
        this.mLastDownOrMoveEventY = y;
        this.mLastDownEventTime = motionEvent.getEventTime();
        this.mIgnoreMoveEvents = false;
        this.mIgnoreUpEvent = false;
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
                if (this.mWheelInterval != 1) {
                    postSwitchIntervalOnLongPress();
                }
            } else if (f5 <= ((float) this.mBottomSelectionDividerBottom)) {
                this.mPerformClickOnTap = true;
                if (this.mWheelInterval != 1) {
                    postSwitchIntervalOnLongPress();
                } else {
                    postBeginSoftInputOnLongPressCommand();
                }
            } else if (this.mWheelInterval != 1) {
                postSwitchIntervalOnLongPress();
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
        accessibilityEvent.getText().add(((AccessibilityNodeProviderImpl) getAccessibilityNodeProvider()).getVirtualCurrentButtonText(true));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mDelegator.isEnabled() || this.mIsEditTextMode || this.mIsStartingAnimation) {
            return false;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1) {
            removeBeginSoftInputCommand();
            removeSwitchIntervalOnLongPress();
            if (!this.mIgnoreUpEvent) {
                this.mPressedStateHelper.cancel();
                VelocityTracker velocityTracker = this.mVelocityTracker;
                velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumFlingVelocity);
                int yVelocity = (int) velocityTracker.getYVelocity();
                int y = (int) motionEvent.getY();
                int abs = (int) Math.abs(((float) y) - this.mLastDownEventY);
                if (!this.mIsEditTextModeEnabled && this.mIgnoreMoveEvents) {
                    ensureScrollWheelAdjusted();
                    startFadeAnimation(true);
                    onScrollStateChange(0);
                } else if (Math.abs(yVelocity) <= this.mMinimumFlingVelocity || Math.abs(yVelocity) <= this.mShortFlickThreshold) {
                    if (abs > this.mTouchSlop) {
                        if (this.mIsLongClicked) {
                            showSoftInput();
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
            }
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
        InputMethodManager inputMethodManager;
        if (z && this.mIsEditTextMode && this.mInputText.isFocused()) {
            showSoftInputForWindowFocused();
        } else if (z && this.mIsEditTextMode && !this.mInputText.isFocused() && (inputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method")) != null && inputMethodManager.isActive(this.mInputText)) {
            inputMethodManager.hideSoftInputFromWindow(this.mDelegator.getWindowToken(), 0);
        }
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
        if (this.mIsEditTextModeEnabled) {
            showSoftInput();
        }
    }

    public void performLongClick() {
        this.mIgnoreMoveEvents = true;
        if (this.mIsEditTextModeEnabled) {
            this.mIsLongClicked = true;
        }
    }

    public void scrollBy(int i2, int i7) {
        int i8;
        int i10;
        int i11;
        int i12;
        int[] iArr = this.mSelectorIndices;
        if (i7 != 0 && this.mSelectorElementHeight > 0) {
            if (!this.mWrapSelectorWheel && (i11 = this.mCurrentScrollOffset) + i7 > (i12 = this.mInitialScrollOffset) && iArr[2] <= this.mMinValue) {
                i7 = i12 - i11;
                stopFlingAnimation();
                if (this.mIsAmPm && this.mLastDownOrMoveEventY > ((float) this.mDelegator.getBottom())) {
                    this.mIgnoreMoveEvents = true;
                    return;
                }
            }
            if (!this.mWrapSelectorWheel && (i8 = this.mCurrentScrollOffset) + i7 < (i10 = this.mInitialScrollOffset) && iArr[2] >= this.mMaxValue) {
                i7 = i10 - i8;
                stopFlingAnimation();
                if (this.mIsAmPm && this.mLastDownOrMoveEventY < ((float) this.mDelegator.getTop())) {
                    this.mIgnoreMoveEvents = true;
                    return;
                }
            }
            this.mCurrentScrollOffset += i7;
            while (true) {
                int i13 = this.mCurrentScrollOffset;
                if (i13 - this.mInitialScrollOffset < this.mValueChangeOffset) {
                    break;
                }
                this.mCurrentScrollOffset = i13 - this.mSelectorElementHeight;
                decrementSelectorIndices(iArr);
                playSoundAndHapticFeedback();
                if (!this.mIsStartingAnimation) {
                    setValueInternal(iArr[2], true);
                    this.mIsValueChanged = true;
                } else if (this.mWheelInterval != 1 && this.mCustomWheelIntervalMode) {
                    initializeSelectorWheelIndices();
                }
                if (!this.mWrapSelectorWheel && iArr[2] <= this.mMinValue) {
                    this.mCurrentScrollOffset = this.mInitialScrollOffset;
                }
            }
            while (true) {
                int i14 = this.mCurrentScrollOffset;
                if (i14 - this.mInitialScrollOffset <= (-this.mValueChangeOffset)) {
                    this.mCurrentScrollOffset = i14 + this.mSelectorElementHeight;
                    incrementSelectorIndices(iArr);
                    playSoundAndHapticFeedback();
                    if (!this.mIsStartingAnimation) {
                        setValueInternal(iArr[2], true);
                        this.mIsValueChanged = true;
                    } else if (this.mWheelInterval != 1 && this.mCustomWheelIntervalMode) {
                        initializeSelectorWheelIndices();
                    }
                    if (!this.mWrapSelectorWheel && iArr[2] >= this.mMaxValue) {
                        this.mCurrentScrollOffset = this.mInitialScrollOffset;
                    }
                } else {
                    return;
                }
            }
        }
    }

    public void setAmPm() {
        this.mIsAmPm = true;
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R$dimen.sesl_time_picker_spinner_am_pm_text_size);
        this.mTextSize = dimensionPixelSize;
        this.mSelectorWheelPaint.setTextSize((float) dimensionPixelSize);
        this.mInputText.setTextSize(0, (float) this.mTextSize);
        setEditTextModeEnabled(false);
    }

    public void setCustomIntervalValue(int i2) {
        this.mWheelInterval = i2;
    }

    public void setCustomNumberPickerIdleColor(int i2) {
        this.mInputText.setTextColor(i2);
        initPickerTextColor(this.mContext);
        this.mSelectorWheelPaint.setColor(this.mTextColor);
        this.mColorInAnimator.setIntValues(new int[]{this.mTextColorIdle, this.mTextColorScrolling});
        this.mColorOutAnimator.setIntValues(new int[]{this.mTextColorScrolling, this.mTextColorIdle});
        this.mDelegator.invalidate();
    }

    public void setCustomNumberPickerScrollColor(int i2) {
        this.mIsCustomScrollColorForPicker = true;
        this.mCustomTextColorScrolling = i2;
        initPickerTextColor(this.mContext);
        this.mColorInAnimator.setIntValues(new int[]{this.mTextColorIdle, this.mTextColorScrolling});
        this.mColorOutAnimator.setIntValues(new int[]{this.mTextColorScrolling, this.mTextColorIdle});
        this.mDelegator.invalidate();
    }

    public void setCustomTalkbackFormatter(SeslNumberPicker.CustomTalkbackFormatter customTalkbackFormatter) {
        this.mCustomTalkbackFormatter = customTalkbackFormatter;
    }

    public void setDateUnit(int i2) {
        if (i2 != -1) {
            switch (i2) {
                case 997:
                    this.mUnitValue = this.mContext.getResources().getString(R$string.sesl_date_picker_day);
                    return;
                case 998:
                    this.mUnitValue = this.mContext.getResources().getString(R$string.sesl_date_picker_month);
                    return;
                case 999:
                    this.mUnitValue = this.mContext.getResources().getString(R$string.sesl_date_picker_year);
                    return;
                default:
                    return;
            }
        } else {
            this.mUnitValue = "";
        }
    }

    public void setDisplayedValues(String[] strArr) {
        if (this.mDisplayedValues != strArr) {
            this.mDisplayedValues = strArr;
            if (strArr != null) {
                this.mInputText.setRawInputType(524289);
            } else {
                this.mInputText.setRawInputType(2);
            }
            updateInputTextView();
            initializeSelectorWheelIndices();
            tryComputeMaxWidth();
        }
    }

    public void setEditTextMode(boolean z) {
        AccessibilityNodeProviderImpl accessibilityNodeProviderImpl;
        if (this.mIsEditTextModeEnabled && this.mIsEditTextMode != z) {
            this.mIsEditTextMode = z;
            if (z) {
                tryComputeMaxWidth();
                removeAllCallbacks();
                if (!this.mIsStartingAnimation) {
                    this.mCurrentScrollOffset = this.mInitialScrollOffset;
                    this.mFlingScroller.abortAnimation();
                    this.mGravityScroller.abortAnimation();
                    this.mSpringFlingRunning = false;
                    this.mSpringAnimation.cancel();
                    onScrollStateChange(0);
                }
                this.mDelegator.setDescendantFocusability(262144);
                updateInputTextView();
                this.mInputText.setVisibility(0);
                if (this.mAccessibilityManager.isEnabled() && (accessibilityNodeProviderImpl = (AccessibilityNodeProviderImpl) getAccessibilityNodeProvider()) != null) {
                    accessibilityNodeProviderImpl.performAction(2, 128, (Bundle) null);
                }
            } else {
                int i2 = this.mWheelInterval;
                if (!(i2 == 1 || !this.mCustomWheelIntervalMode || this.mValue % i2 == 0)) {
                    applyWheelCustomInterval(false);
                }
                if (this.mFadeOutAnimator.isRunning()) {
                    this.mFadeOutAnimator.cancel();
                }
                if (this.mFadeInAnimator.isRunning()) {
                    this.mFadeInAnimator.cancel();
                }
                if (this.mColorInAnimator.isRunning()) {
                    this.mColorInAnimator.cancel();
                }
                if (this.mColorOutAnimator.isRunning()) {
                    this.mColorOutAnimator.cancel();
                }
                this.mTextColor = this.mTextColorIdle;
                this.mAlpha = this.mIdleAlpha;
                this.mInputText.setVisibility(4);
                this.mDelegator.setDescendantFocusability(131072);
            }
            this.mDelegator.invalidate();
            SeslNumberPicker.OnEditTextModeChangedListener onEditTextModeChangedListener = this.mOnEditTextModeChangedListener;
            if (onEditTextModeChangedListener != null) {
                onEditTextModeChangedListener.onEditTextModeChanged(this.mDelegator, this.mIsEditTextMode);
            }
        }
    }

    public void setEditTextModeEnabled(boolean z) {
        if (this.mIsEditTextModeEnabled != z && !z) {
            if (this.mIsEditTextMode) {
                setEditTextMode(false);
            }
            this.mInputText.setAccessibilityDelegate((View.AccessibilityDelegate) null);
            this.mIsEditTextModeEnabled = z;
        }
    }

    public void setEnabled(boolean z) {
        this.mInputText.setEnabled(z);
        if (!z && this.mScrollState != 0) {
            stopScrollAnimation();
            onScrollStateChange(0);
        }
    }

    public void setErrorToastMessage(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mToastText = str;
        }
    }

    public void setFormatter(SeslNumberPicker.Formatter formatter) {
        if (formatter != this.mFormatter) {
            this.mFormatter = formatter;
            initializeSelectorWheelIndices();
            updateInputTextView();
        }
    }

    public void setMaxInputLength(int i2) {
        InputFilter inputFilter = this.mInputText.getFilters()[0];
        InputFilter.LengthFilter lengthFilter = new InputFilter.LengthFilter(i2);
        this.mInputText.setFilters(new InputFilter[]{inputFilter, lengthFilter});
    }

    public void setMaxValue(int i2) {
        if (this.mMaxValue != i2) {
            if (i2 >= 0) {
                boolean z = this.mWrapSelectorWheel;
                int i7 = this.mWheelInterval;
                if (i7 == 1 || ((z ? 1 : 0) + i2) % i7 == 0) {
                    this.mMaxValue = i2;
                    if (i2 < this.mValue) {
                        this.mValue = i2;
                    }
                    updateWrapSelectorWheel();
                    initializeSelectorWheelIndices();
                    updateInputTextView();
                    tryComputeMaxWidth();
                    this.mDelegator.invalidate();
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("maxValue must be >= 0");
        }
    }

    public void setMinValue(int i2) {
        if (this.mMinValue != i2) {
            if (i2 >= 0) {
                int i7 = this.mWheelInterval;
                if (i7 == 1 || i2 % i7 == 0) {
                    this.mMinValue = i2;
                    if (i2 > this.mValue) {
                        this.mValue = i2;
                    }
                    updateWrapSelectorWheel();
                    initializeSelectorWheelIndices();
                    updateInputTextView();
                    tryComputeMaxWidth();
                    this.mDelegator.invalidate();
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("minValue must be >= 0");
        }
    }

    public void setMonthInputMode() {
        this.mInputText.setImeOptions(33554432);
        this.mInputText.setPrivateImeOptions("inputType=month_edittext");
        this.mInputText.setText("");
    }

    public void setOnEditTextModeChangedListener(SeslNumberPicker.OnEditTextModeChangedListener onEditTextModeChangedListener) {
        this.mOnEditTextModeChangedListener = onEditTextModeChangedListener;
    }

    public void setOnValueChangedListener(SeslNumberPicker.OnValueChangeListener onValueChangeListener) {
        this.mOnValueChangeListener = onValueChangeListener;
    }

    public void setPaintFlags(int i2) {
        if (this.mSelectorWheelPaint.getFlags() != i2) {
            this.mSelectorWheelPaint.setFlags(i2);
            this.mInputText.setPaintFlags(i2);
            tryComputeMaxWidth();
        }
    }

    public void setPickerContentDescription(String str) {
        this.mPickerContentDescription = str;
        ((SeslNumberPicker.CustomEditText) this.mInputText).setPickerContentDescription(str);
    }

    public void setSubTextTypeface(Typeface typeface) {
        this.mCustomTypefaceSet = true;
        this.mPickerSubTypeface = typeface;
        this.mSelectorWheelPaint.setTypeface(this.mPickerTypeface);
        this.mHcfFocusedTypefaceBold = Typeface.create(this.mPickerTypeface, 1);
        setInputTextTypeface();
        tryComputeMaxWidth();
    }

    public void setTextSize(float f) {
        int applyDimension = (int) TypedValue.applyDimension(1, f, this.mContext.getResources().getDisplayMetrics());
        this.mTextSize = applyDimension;
        this.mSelectorWheelPaint.setTextSize((float) applyDimension);
        this.mInputText.setTextSize(0, (float) this.mTextSize);
        tryComputeMaxWidth();
    }

    public void setTextTypeface(Typeface typeface) {
        this.mCustomTypefaceSet = true;
        this.mPickerTypeface = typeface;
        this.mPickerSubTypeface = Typeface.create(typeface, 0);
        this.mSelectorWheelPaint.setTypeface(this.mPickerTypeface);
        this.mHcfFocusedTypefaceBold = Typeface.create(this.mPickerTypeface, 1);
        setInputTextTypeface();
        tryComputeMaxWidth();
    }

    public void setValue(int i2) {
        if (!this.mFlingScroller.isFinished() || this.mSpringAnimation.isRunning()) {
            stopScrollAnimation();
        }
        setValueInternal(i2, false);
    }

    public void setWrapSelectorWheel(boolean z) {
        this.mWrapSelectorWheelPreferred = z;
        updateWrapSelectorWheel();
    }

    public void setYearDateTimeInputMode() {
        this.mInputText.setImeOptions(33554432);
        this.mInputText.setPrivateImeOptions("inputType=YearDateTime_edittext");
        this.mInputText.setText("");
    }

    public void startAnimation(final int i2, SeslAnimationListener seslAnimationListener) {
        if (!this.mIsEditTextMode) {
            if (this.mIsAmPm || this.mWrapSelectorWheel || getValue() - getMinValue() != 0) {
                if (this.mFadeOutAnimator.isStarted()) {
                    this.mFadeOutAnimator.cancel();
                }
                if (this.mFadeInAnimator.isStarted()) {
                    this.mFadeInAnimator.cancel();
                }
                if (this.mColorInAnimator.isStarted()) {
                    this.mColorInAnimator.cancel();
                }
                if (this.mColorOutAnimator.isStarted()) {
                    this.mColorOutAnimator.cancel();
                }
                this.mDelegator.post(new Runnable() {
                    public void run() {
                        final int i2;
                        int i7;
                        float f;
                        int i8;
                        SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate = SeslNumberPickerSpinnerDelegate.this;
                        if (seslNumberPickerSpinnerDelegate.mSelectorElementHeight == 0) {
                            seslNumberPickerSpinnerDelegate.mReservedStartAnimation = true;
                            return;
                        }
                        seslNumberPickerSpinnerDelegate.mIsStartingAnimation = true;
                        seslNumberPickerSpinnerDelegate.mFlingScroller = seslNumberPickerSpinnerDelegate.mCustomScroller;
                        if (seslNumberPickerSpinnerDelegate.getValue() != SeslNumberPickerSpinnerDelegate.this.getMinValue()) {
                            i2 = SeslNumberPickerSpinnerDelegate.this.mSelectorElementHeight;
                        } else {
                            i2 = -SeslNumberPickerSpinnerDelegate.this.mSelectorElementHeight;
                        }
                        int value = SeslNumberPickerSpinnerDelegate.this.getValue() - SeslNumberPickerSpinnerDelegate.this.getMinValue();
                        SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate2 = SeslNumberPickerSpinnerDelegate.this;
                        boolean z = seslNumberPickerSpinnerDelegate2.mWrapSelectorWheel;
                        if (z || value >= 5) {
                            i7 = 5;
                        } else {
                            i7 = value;
                        }
                        if (z || value >= 5) {
                            f = 5.4f;
                        } else {
                            f = ((float) value) + 0.4f;
                        }
                        boolean z3 = seslNumberPickerSpinnerDelegate2.mIsAmPm;
                        if (z3) {
                            i8 = i2;
                        } else {
                            i8 = seslNumberPickerSpinnerDelegate2.mSelectorElementHeight * i7;
                        }
                        if (!z3) {
                            i2 = (int) (((float) seslNumberPickerSpinnerDelegate2.mSelectorElementHeight) * f);
                        }
                        seslNumberPickerSpinnerDelegate2.scrollBy(0, i8);
                        SeslNumberPickerSpinnerDelegate.this.mDelegator.invalidate();
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                new Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        int i2;
                                        SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate = SeslNumberPickerSpinnerDelegate.this;
                                        if (!seslNumberPickerSpinnerDelegate.moveToFinalScrollerPosition(seslNumberPickerSpinnerDelegate.mFlingScroller)) {
                                            SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate2 = SeslNumberPickerSpinnerDelegate.this;
                                            boolean unused = seslNumberPickerSpinnerDelegate2.moveToFinalScrollerPosition(seslNumberPickerSpinnerDelegate2.mAdjustScroller);
                                        }
                                        SeslNumberPickerSpinnerDelegate.this.startFadeAnimation(false);
                                        AnonymousClass1 r0 = AnonymousClass1.this;
                                        SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate3 = SeslNumberPickerSpinnerDelegate.this;
                                        seslNumberPickerSpinnerDelegate3.mPreviousScrollerY = 0;
                                        Scroller scroller = seslNumberPickerSpinnerDelegate3.mFlingScroller;
                                        int i7 = -i2;
                                        if (seslNumberPickerSpinnerDelegate3.mIsAmPm) {
                                            i2 = 857;
                                        } else {
                                            i2 = 557;
                                        }
                                        scroller.startScroll(0, 0, 0, i7, i2);
                                        SeslNumberPickerSpinnerDelegate.this.mDelegator.invalidate();
                                        new Handler().postDelayed(new Runnable() {
                                            public void run() {
                                                SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate = SeslNumberPickerSpinnerDelegate.this;
                                                boolean unused = seslNumberPickerSpinnerDelegate.moveToFinalScrollerPosition(seslNumberPickerSpinnerDelegate.mFlingScroller);
                                                SeslNumberPickerSpinnerDelegate.this.mFlingScroller.abortAnimation();
                                                SeslNumberPickerSpinnerDelegate.this.mAdjustScroller.abortAnimation();
                                                boolean unused2 = SeslNumberPickerSpinnerDelegate.this.ensureScrollWheelAdjusted();
                                                SeslNumberPickerSpinnerDelegate seslNumberPickerSpinnerDelegate2 = SeslNumberPickerSpinnerDelegate.this;
                                                seslNumberPickerSpinnerDelegate2.mFlingScroller = seslNumberPickerSpinnerDelegate2.mLinearScroller;
                                                seslNumberPickerSpinnerDelegate2.mIsStartingAnimation = false;
                                                seslNumberPickerSpinnerDelegate2.mDelegator.invalidate();
                                                SeslNumberPickerSpinnerDelegate.this.startFadeAnimation(true);
                                                SeslNumberPickerSpinnerDelegate.this.getClass();
                                            }
                                        }, 857);
                                    }
                                }, 100);
                            }
                        }, (long) i2);
                    }
                });
            }
        }
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
            androidx.picker.widget.SeslNumberPicker r10 = r9.mDelegator
            r10.invalidate()
            r9.mIsValueChanged = r2
            r9 = 1
            return r9
        L_0x0043:
            r9.mIsValueChanged = r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.picker.widget.SeslNumberPickerSpinnerDelegate.ensureScrollWheelAdjusted(int):boolean");
    }

    public void performClick(boolean z) {
        if (this.mIsAmPm) {
            z = this.mValue != this.mMaxValue;
        }
        changeValueByOne(z);
    }

    public void onWindowVisibilityChanged(int i2) {
    }

    public void setOnScrollListener(SeslNumberPicker.OnScrollListener onScrollListener) {
    }

    public void setSubTextSize(float f) {
    }
}
