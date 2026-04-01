package androidx.appcompat.widget;

import N2.j;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import androidx.appcompat.animation.SeslAnimationUtils;
import androidx.appcompat.graphics.drawable.SeslRecoilDrawable;
import androidx.appcompat.util.SeslMisc;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import h.C0198a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslSwitchBar extends LinearLayout implements CompoundButton.OnCheckedChangeListener {
    private static final Long BACKGROUND_COLOR_CHANGE_DURATION = 350L;
    private static final int SWITCH_OFF_STRING_RESOURCE_ID = R$string.sesl_switchbar_off_text;
    private static final int SWITCH_ON_STRING_RESOURCE_ID = R$string.sesl_switchbar_on_text;
    private LinearLayout mBackground;
    private int mBackgroundActivatedColor;
    private int mBackgroundColor;
    private ValueAnimator mBackgroundColorInAnimator;
    private ValueAnimator mBackgroundColorOutAnimator;
    private SwitchBarDelegate mDelegate;
    private String mLabel;
    private int mOffTextColor;
    private int mOffTextId;
    private int mOnTextColor;
    private int mOnTextId;
    private SeslProgressBar mProgressBar;
    private String mSessionDesc;
    /* access modifiers changed from: private */
    public SeslToggleSwitch mSwitch;
    private final List<OnSwitchChangeListener> mSwitchChangeListeners;
    private TextView mTextView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnSwitchChangeListener {
        void onSwitchChanged(SwitchCompat switchCompat, boolean z);
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
        boolean checked;
        boolean visible;

        public String toString() {
            StringBuilder sb2 = new StringBuilder("SeslSwitchBar.SavedState{");
            sb2.append(Integer.toHexString(System.identityHashCode(this)));
            sb2.append(" checked=");
            sb2.append(this.checked);
            sb2.append(" visible=");
            return j.h(sb2, this.visible, "}");
        }

        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeValue(Boolean.valueOf(this.checked));
            parcel.writeValue(Boolean.valueOf(this.visible));
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.checked = ((Boolean) parcel.readValue((ClassLoader) null)).booleanValue();
            this.visible = ((Boolean) parcel.readValue((ClassLoader) null)).booleanValue();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SwitchBarDelegate extends AccessibilityDelegateCompat {
        private String mSessionName = "";
        private SeslToggleSwitch mSwitch;
        private TextView mText;

        public SwitchBarDelegate(View view) {
            this.mText = (TextView) view.findViewById(R$id.sesl_switchbar_text);
            this.mSwitch = (SeslToggleSwitch) view.findViewById(R$id.sesl_switchbar_switch);
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            this.mSwitch.setContentDescription(this.mText.getText());
            if (!TextUtils.isEmpty(this.mSessionName)) {
                accessibilityNodeInfoCompat.setText(this.mSessionName);
            }
        }

        public void setSessionName(String str) {
            this.mSessionName = str;
        }
    }

    public SeslSwitchBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.seslSwitchBarStyle);
    }

    private String getActivityTitle() {
        for (Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                CharSequence title = ((Activity) context).getTitle();
                if (title != null) {
                    return title.toString();
                }
                return "";
            }
        }
        return "";
    }

    private void initBackgroundColorAnimator() {
        ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(this.mBackgroundColor), Integer.valueOf(this.mBackgroundActivatedColor)});
        this.mBackgroundColorInAnimator = ofObject;
        Long l = BACKGROUND_COLOR_CHANGE_DURATION;
        ofObject.setDuration(l.longValue());
        ValueAnimator valueAnimator = this.mBackgroundColorInAnimator;
        Interpolator interpolator = SeslAnimationUtils.SINE_OUT_33;
        valueAnimator.setInterpolator(interpolator);
        this.mBackgroundColorInAnimator.addUpdateListener(new C0198a(this, 0));
        ValueAnimator ofObject2 = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(this.mBackgroundActivatedColor), Integer.valueOf(this.mBackgroundColor)});
        this.mBackgroundColorOutAnimator = ofObject2;
        ofObject2.setDuration(l.longValue());
        this.mBackgroundColorOutAnimator.setInterpolator(interpolator);
        this.mBackgroundColorOutAnimator.addUpdateListener(new C0198a(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initBackgroundColorAnimator$0(ValueAnimator valueAnimator) {
        setSwitchBarBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initBackgroundColorAnimator$1(ValueAnimator valueAnimator) {
        setSwitchBarBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    private void propagateChecked(boolean z) {
        int size = this.mSwitchChangeListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mSwitchChangeListeners.get(i2).onSwitchChanged(this.mSwitch, z);
        }
    }

    private void setSwitchBarBackgroundColor(int i2) {
        LinearLayout linearLayout = this.mBackground;
        if (linearLayout != null) {
            Drawable mutate = DrawableCompat.wrap(linearLayout.getBackground().mutate()).mutate();
            if (mutate instanceof SeslRecoilDrawable) {
                SeslRecoilDrawable seslRecoilDrawable = (SeslRecoilDrawable) mutate;
                if (seslRecoilDrawable.getNumberOfLayers() > 0) {
                    Drawable drawable = seslRecoilDrawable.getDrawable(0);
                    if (drawable instanceof GradientDrawable) {
                        ((GradientDrawable) drawable).setColor(i2);
                        return;
                    }
                    return;
                }
                return;
            }
            DrawableCompat.setTintList(mutate, ColorStateList.valueOf(i2));
        }
    }

    public void addOnSwitchChangeListener(OnSwitchChangeListener onSwitchChangeListener) {
        if (!this.mSwitchChangeListeners.contains(onSwitchChangeListener)) {
            this.mSwitchChangeListeners.add(onSwitchChangeListener);
            return;
        }
        throw new IllegalStateException("Cannot add twice the same OnSwitchChangeListener");
    }

    public CharSequence getAccessibilityClassName() {
        return SeslSwitchBar.class.getName();
    }

    public final SeslToggleSwitch getSwitch() {
        return this.mSwitch;
    }

    public boolean isChecked() {
        return this.mSwitch.isChecked();
    }

    public boolean isShowing() {
        if (getVisibility() == 0) {
            return true;
        }
        return false;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        propagateChecked(z);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ValueAnimator valueAnimator = this.mBackgroundColorInAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeAllUpdateListeners();
        }
        ValueAnimator valueAnimator2 = this.mBackgroundColorOutAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.removeAllUpdateListeners();
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SeslSwitchBar seslSwitchBar;
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mSwitch.setCheckedInternal(savedState.checked);
        int i2 = 0;
        setTextViewLabelAndBackground(savedState.checked, false);
        if (!savedState.visible) {
            i2 = 8;
        }
        setVisibility(i2);
        SeslToggleSwitch seslToggleSwitch = this.mSwitch;
        if (savedState.visible) {
            seslSwitchBar = this;
        } else {
            seslSwitchBar = null;
        }
        seslToggleSwitch.setOnCheckedChangeListener(seslSwitchBar);
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.checked = this.mSwitch.isChecked();
        savedState.visible = isShowing();
        return savedState;
    }

    public boolean performClick() {
        return this.mSwitch.performClick();
    }

    public void setChecked(boolean z) {
        setTextViewLabelAndBackground(z, false);
        this.mSwitch.setChecked(z);
    }

    public void setCheckedInternal(boolean z) {
        setTextViewLabelAndBackground(z, false);
        this.mSwitch.setCheckedInternal(z);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.mTextView.setEnabled(z);
        this.mSwitch.setEnabled(z);
        this.mBackground.setEnabled(z);
        setTextViewLabelAndBackground(isChecked(), false);
    }

    public void setProgressBarVisible(boolean z) {
        int i2;
        try {
            SeslProgressBar seslProgressBar = this.mProgressBar;
            if (z) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            seslProgressBar.setVisibility(i2);
        } catch (IndexOutOfBoundsException e) {
            Log.i("SetProgressBarVisible", "Invalid argument" + e);
        }
    }

    public void setSessionDescription(String str) {
        this.mSessionDesc = str;
        this.mDelegate.setSessionName(str);
    }

    public void setSwitchBarText(int i2, int i7) {
        this.mOnTextId = i2;
        this.mOffTextId = i7;
        setTextViewLabelAndBackground(isChecked(), false);
    }

    public void setTextViewLabel(boolean z) {
        int i2;
        Resources resources = getResources();
        if (z) {
            i2 = this.mOnTextId;
        } else {
            i2 = this.mOffTextId;
        }
        String string = resources.getString(i2);
        this.mLabel = string;
        this.mTextView.setText(string);
    }

    public void setTextViewLabelAndBackground(boolean z, boolean z3) {
        int i2;
        int i7;
        int i8;
        Resources resources = getResources();
        if (z) {
            i2 = this.mOnTextId;
        } else {
            i2 = this.mOffTextId;
        }
        this.mLabel = resources.getString(i2);
        if (z3) {
            if (this.mBackgroundColorInAnimator == null || this.mBackgroundColorOutAnimator == null) {
                initBackgroundColorAnimator();
            }
            if (z) {
                if (this.mBackgroundColorOutAnimator.isRunning()) {
                    this.mBackgroundColorOutAnimator.cancel();
                }
                this.mBackgroundColorInAnimator.start();
            } else {
                if (this.mBackgroundColorInAnimator.isRunning()) {
                    this.mBackgroundColorInAnimator.cancel();
                }
                this.mBackgroundColorOutAnimator.start();
            }
        } else {
            if (z) {
                i8 = this.mBackgroundActivatedColor;
            } else {
                i8 = this.mBackgroundColor;
            }
            setSwitchBarBackgroundColor(i8);
        }
        TextView textView = this.mTextView;
        if (z) {
            i7 = this.mOnTextColor;
        } else {
            i7 = this.mOffTextColor;
        }
        textView.setTextColor(i7);
        if (isEnabled()) {
            this.mTextView.setAlpha(1.0f);
        } else if (!SeslMisc.isLightTheme(getContext()) || !z) {
            this.mTextView.setAlpha(0.4f);
        } else {
            this.mTextView.setAlpha(0.55f);
        }
        String str = this.mLabel;
        if (str == null || !str.contentEquals(this.mTextView.getText())) {
            this.mTextView.setText(this.mLabel);
        }
    }

    public SeslSwitchBar(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public SeslSwitchBar(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        this.mSwitchChangeListeners = new ArrayList();
        this.mSessionDesc = null;
        LayoutInflater.from(context).inflate(R$layout.sesl_switchbar, this);
        Resources resources = getResources();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SeslSwitchBar, i2, i7);
        this.mBackgroundColor = obtainStyledAttributes.getColor(R$styleable.SeslSwitchBar_seslSwitchBarBackgroundColor, resources.getColor(R$color.sesl_switchbar_off_background_color_light));
        this.mBackgroundActivatedColor = obtainStyledAttributes.getColor(R$styleable.SeslSwitchBar_seslSwitchBarBackgroundActivatedColor, resources.getColor(R$color.sesl_switchbar_on_background_color_light));
        int i8 = R$styleable.SeslSwitchBar_seslSwitchBarTextActivatedColor;
        int i10 = R$color.sesl_switchbar_on_text_color_light;
        this.mOnTextColor = obtainStyledAttributes.getColor(i8, resources.getColor(i10));
        this.mOffTextColor = obtainStyledAttributes.getColor(R$styleable.SeslSwitchBar_seslSwitchBarTextColor, resources.getColor(i10));
        obtainStyledAttributes.recycle();
        this.mProgressBar = (SeslProgressBar) findViewById(R$id.sesl_switchbar_progress);
        LinearLayout linearLayout = (LinearLayout) findViewById(R$id.sesl_switchbar_container);
        this.mBackground = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SeslSwitchBar.this.mSwitch != null && SeslSwitchBar.this.mSwitch.isEnabled()) {
                    SeslSwitchBar.this.mSwitch.setChecked(!SeslSwitchBar.this.mSwitch.isChecked());
                }
            }
        });
        initBackgroundColorAnimator();
        this.mOnTextId = SWITCH_ON_STRING_RESOURCE_ID;
        this.mOffTextId = SWITCH_OFF_STRING_RESOURCE_ID;
        TextView textView = (TextView) findViewById(R$id.sesl_switchbar_text);
        this.mTextView = textView;
        ((ViewGroup.MarginLayoutParams) textView.getLayoutParams()).setMarginStart((int) resources.getDimension(R$dimen.sesl_switchbar_margin_start));
        SeslToggleSwitch seslToggleSwitch = (SeslToggleSwitch) findViewById(R$id.sesl_switchbar_switch);
        this.mSwitch = seslToggleSwitch;
        seslToggleSwitch.setSaveEnabled(false);
        this.mSwitch.setFocusable(false);
        this.mSwitch.setClickable(false);
        this.mSwitch.setOnCheckedChangeListener(this);
        setSwitchBarText(this.mOnTextId, this.mOffTextId);
        addOnSwitchChangeListener(new OnSwitchChangeListener() {
            public void onSwitchChanged(SwitchCompat switchCompat, boolean z) {
                SeslSwitchBar.this.setTextViewLabelAndBackground(z, true);
            }
        });
        ((ViewGroup.MarginLayoutParams) this.mSwitch.getLayoutParams()).setMarginEnd((int) resources.getDimension(R$dimen.sesl_switchbar_margin_end));
        SwitchBarDelegate switchBarDelegate = new SwitchBarDelegate(this);
        this.mDelegate = switchBarDelegate;
        ViewCompat.setAccessibilityDelegate(this.mBackground, switchBarDelegate);
        setSessionDescription(getActivityTitle());
    }
}
