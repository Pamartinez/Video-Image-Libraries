package com.samsung.android.gallery.settings.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SeslSwitchBar;
import androidx.preference.PreferenceViewHolder;
import com.samsung.android.gallery.settings.R$id;
import com.samsung.android.gallery.settings.R$layout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MainSwitchPreference extends BaseSwitchPreference implements CompoundButton.OnCheckedChangeListener {
    public MainSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getLayoutId() {
        return R$layout.main_switch_preference_layout;
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        SeslSwitchBar seslSwitchBar = (SeslSwitchBar) preferenceViewHolder.itemView.findViewById(R$id.switch_bar);
        if (seslSwitchBar != null && seslSwitchBar.getSwitch() != null) {
            seslSwitchBar.getSwitch().setOnCheckedChangeListener(this);
            seslSwitchBar.setChecked(isChecked());
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (!callChangeListener(Boolean.valueOf(z))) {
            compoundButton.setChecked(!z);
            return;
        }
        setChecked(z);
        compoundButton.setChecked(z);
    }

    public boolean supportCustomLayout() {
        return true;
    }

    public MainSwitchPreference(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public void onClick() {
    }
}
