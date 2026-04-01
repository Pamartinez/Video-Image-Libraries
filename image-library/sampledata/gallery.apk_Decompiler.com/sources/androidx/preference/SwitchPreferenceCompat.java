package androidx.preference;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.view.ViewCompat;
import androidx.reflect.view.SeslHapticFeedbackConstantsReflector;
import androidx.reflect.view.SeslViewReflector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SwitchPreferenceCompat extends TwoStatePreference {
    private static final boolean SUPPORT_TOUCH_FEEDBACK = true;
    private final DummyClickListener mClickListener;
    private int mIsLargeLayout;
    private final Listener mListener;
    private CharSequence mSwitchOff;
    private CharSequence mSwitchOn;
    private int mWidth;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class DummyClickListener implements View.OnClickListener {
        private DummyClickListener() {
        }

        public void onClick(View view) {
            SwitchPreferenceCompat.this.callClickListener();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class Listener implements CompoundButton.OnCheckedChangeListener {
        public Listener() {
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (!SwitchPreferenceCompat.this.callChangeListener(Boolean.valueOf(z))) {
                compoundButton.setChecked(!z);
            } else {
                SwitchPreferenceCompat.this.setChecked(z);
            }
        }
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        this.mListener = new Listener();
        this.mWidth = 0;
        this.mClickListener = new DummyClickListener();
        this.mIsLargeLayout = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SwitchPreferenceCompat, i2, i7);
        setSummaryOn(TypedArrayUtils.getString(obtainStyledAttributes, R$styleable.SwitchPreferenceCompat_summaryOn, R$styleable.SwitchPreferenceCompat_android_summaryOn));
        setSummaryOff(TypedArrayUtils.getString(obtainStyledAttributes, R$styleable.SwitchPreferenceCompat_summaryOff, R$styleable.SwitchPreferenceCompat_android_summaryOff));
        setSwitchTextOn(TypedArrayUtils.getString(obtainStyledAttributes, R$styleable.SwitchPreferenceCompat_switchTextOn, R$styleable.SwitchPreferenceCompat_android_switchTextOn));
        setSwitchTextOff(TypedArrayUtils.getString(obtainStyledAttributes, R$styleable.SwitchPreferenceCompat_switchTextOff, R$styleable.SwitchPreferenceCompat_android_switchTextOff));
        setDisableDependentsState(TypedArrayUtils.getBoolean(obtainStyledAttributes, R$styleable.SwitchPreferenceCompat_disableDependentsState, R$styleable.SwitchPreferenceCompat_android_disableDependentsState, false));
        obtainStyledAttributes.recycle();
    }

    private boolean canHapticFeedback(boolean z, View view, SwitchCompat switchCompat) {
        if (!SUPPORT_TOUCH_FEEDBACK || z == switchCompat.isChecked() || !view.hasWindowFocus() || !SeslViewReflector.isVisibleToUser(view) || view.isTemporarilyDetached()) {
            return false;
        }
        return true;
    }

    private void syncSwitchView(View view) {
        boolean z = view instanceof SwitchCompat;
        if (z) {
            ((SwitchCompat) view).setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.mChecked);
        }
        if (z) {
            SwitchCompat switchCompat = (SwitchCompat) view;
            switchCompat.setTextOn(this.mSwitchOn);
            switchCompat.setTextOff(this.mSwitchOff);
            switchCompat.setOnCheckedChangeListener(this.mListener);
            if (switchCompat.isClickable()) {
                switchCompat.setOnClickListener(this.mClickListener);
            }
            if (isTalkBackIsRunning() && !(this instanceof SeslSwitchPreferenceScreen)) {
                ViewCompat.setBackground(switchCompat, (Drawable) null);
                switchCompat.setClickable(false);
            }
        }
    }

    private void syncViewIfAccessibilityEnabled(View view) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        if (accessibilityManager == null || accessibilityManager.isEnabled()) {
            if (this.mIsLargeLayout != 1) {
                syncSwitchView(view.findViewById(16908352));
            }
            if (!isTalkBackIsRunning()) {
                syncSummaryView(view.findViewById(16908304));
            }
        }
    }

    private void updateLayout(View view) {
        int i2;
        int dimensionPixelSize;
        int paddingEnd;
        View findViewById = view.findViewById(R$id.widget_frame);
        View findViewById2 = view.findViewById(16908312);
        View findViewById3 = view.findViewById(R$id.switch_widget);
        View findViewById4 = view.findViewById(16908352);
        Configuration configuration = getContext().getResources().getConfiguration();
        int i7 = configuration.screenWidthDp;
        if ((i7 > 320 || configuration.fontScale < 1.1f) && (i7 >= 411 || configuration.fontScale < 1.3f)) {
            i2 = 2;
        } else {
            i2 = 1;
        }
        if (i2 == 1) {
            this.mIsLargeLayout = i2;
            TextView textView = (TextView) view.findViewById(16908310);
            float measureText = textView.getPaint().measureText(textView.getText().toString());
            TextView textView2 = (TextView) view.findViewById(16908304);
            float measureText2 = textView2.getPaint().measureText(textView2.getText().toString());
            if (textView2.getVisibility() == 8) {
                measureText2 = 0.0f;
            }
            if (this instanceof SeslSwitchPreferenceScreen) {
                dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R$dimen.sesl_preference_screen_item_switch_size);
                paddingEnd = findViewById2.getPaddingEnd();
            } else {
                dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R$dimen.sesl_preference_item_switch_size);
                paddingEnd = findViewById2.getPaddingEnd();
            }
            float paddingEnd2 = (float) (((this.mWidth - view.getPaddingEnd()) - view.getPaddingStart()) - (paddingEnd + dimensionPixelSize));
            if (measureText >= paddingEnd2 || measureText2 >= paddingEnd2) {
                findViewById.setVisibility(0);
                findViewById2.setVisibility(8);
                textView.requestLayout();
                SwitchCompat switchCompat = (SwitchCompat) findViewById3;
                if (!switchCompat.canHapticFeedback(this.mChecked) && canHapticFeedback(this.mChecked, view, switchCompat)) {
                    switchCompat.performHapticFeedback(SeslHapticFeedbackConstantsReflector.semGetVibrationIndex(27));
                }
                syncSwitchView(findViewById3);
                SwitchCompat switchCompat2 = (SwitchCompat) findViewById4;
                switchCompat2.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
                switchCompat2.setCheckedWithoutAnimation(this.mChecked);
                return;
            }
            findViewById2.setVisibility(0);
            findViewById.setVisibility(8);
            textView.requestLayout();
            SwitchCompat switchCompat3 = (SwitchCompat) findViewById4;
            if (!switchCompat3.canHapticFeedback(this.mChecked) && canHapticFeedback(this.mChecked, view, switchCompat3)) {
                switchCompat3.performHapticFeedback(SeslHapticFeedbackConstantsReflector.semGetVibrationIndex(27));
            }
            syncSwitchView(findViewById4);
            SwitchCompat switchCompat4 = (SwitchCompat) findViewById3;
            switchCompat4.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
            switchCompat4.setCheckedWithoutAnimation(this.mChecked);
            return;
        }
        if (this.mIsLargeLayout != i2) {
            this.mIsLargeLayout = i2;
            findViewById2.setVisibility(0);
            findViewById.setVisibility(8);
            ((TextView) view.findViewById(16908310)).requestLayout();
        }
        syncSwitchView(findViewById4);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder, int i2) {
        this.mWidth = i2;
        onBindViewHolder(preferenceViewHolder);
        updateLayout(preferenceViewHolder.itemView);
    }

    public void performClick(View view) {
        super.performClick(view);
        syncViewIfAccessibilityEnabled(view);
    }

    public void setSwitchTextOff(CharSequence charSequence) {
        this.mSwitchOff = charSequence;
        notifyChanged();
    }

    public void setSwitchTextOn(CharSequence charSequence) {
        this.mSwitchOn = charSequence;
        notifyChanged();
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        if (this.mIsLargeLayout != 1) {
            syncSwitchView(preferenceViewHolder.findViewById(16908352));
        }
        syncSummaryView(preferenceViewHolder);
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.switchPreferenceCompatStyle);
    }

    public SwitchPreferenceCompat(Context context) {
        this(context, (AttributeSet) null);
    }
}
