package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.core.content.res.TypedArrayUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CheckBoxPreference extends TwoStatePreference {
    private final Listener mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class Listener implements CompoundButton.OnCheckedChangeListener {
        public Listener() {
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (!CheckBoxPreference.this.callChangeListener(Boolean.valueOf(z))) {
                compoundButton.setChecked(!z);
            } else {
                CheckBoxPreference.this.setChecked(z);
            }
        }
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    private void syncCheckboxView(View view) {
        boolean z = view instanceof CompoundButton;
        if (z) {
            ((CompoundButton) view).setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.mChecked);
        }
        if (z) {
            ((CompoundButton) view).setOnCheckedChangeListener(this.mListener);
        }
    }

    private void syncViewIfAccessibilityEnabled(View view) {
        if (((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
            syncCheckboxView(view.findViewById(16908289));
            if (!isTalkBackIsRunning()) {
                syncSummaryView(view.findViewById(16908304));
            }
        }
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        syncCheckboxView(preferenceViewHolder.findViewById(16908289));
        syncSummaryView(preferenceViewHolder);
    }

    public void performClick(View view) {
        super.performClick(view);
        syncViewIfAccessibilityEnabled(view);
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        this.mListener = new Listener();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CheckBoxPreference, i2, i7);
        setSummaryOn(TypedArrayUtils.getString(obtainStyledAttributes, R$styleable.CheckBoxPreference_summaryOn, R$styleable.CheckBoxPreference_android_summaryOn));
        setSummaryOff(TypedArrayUtils.getString(obtainStyledAttributes, R$styleable.CheckBoxPreference_summaryOff, R$styleable.CheckBoxPreference_android_summaryOff));
        setDisableDependentsState(TypedArrayUtils.getBoolean(obtainStyledAttributes, R$styleable.CheckBoxPreference_disableDependentsState, R$styleable.CheckBoxPreference_android_disableDependentsState, false));
        obtainStyledAttributes.recycle();
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.getAttr(context, R$attr.checkBoxPreferenceStyle, 16842895));
    }
}
