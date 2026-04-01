package com.samsung.android.gallery.settings.widget;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import androidx.preference.CheckBoxPreference;
import androidx.preference.PreferenceViewHolder;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.settings.R$dimen;
import com.samsung.android.gallery.settings.R$layout;
import com.samsung.android.gallery.settings.R$string;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RadioPreference extends CheckBoxPreference {
    public RadioPreference(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init(context);
    }

    private int getLayoutId() {
        return R$layout.radio_preference_layout;
    }

    private void init(Context context) {
        setLayoutResource(getLayoutId());
        seslSetDividerStartOffset(context.getResources().getDimensionPixelOffset(R$dimen.check_box_preference_divider_padding_start));
    }

    private void setContentDescription(PreferenceViewHolder preferenceViewHolder) {
        int i2;
        View view = preferenceViewHolder.itemView;
        View findViewById = preferenceViewHolder.findViewById(16908289);
        String str = (String) getTitle();
        if ((findViewById instanceof RadioButton) && !TextUtils.isEmpty(str)) {
            StringBuilder sb2 = new StringBuilder();
            Resources resources = view.getResources();
            if (((RadioButton) findViewById).isChecked()) {
                i2 = R$string.speak_checked;
            } else {
                i2 = R$string.speak_not_checked;
            }
            sb2.append(resources.getString(i2));
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(str);
            sb2.append(ArcCommonLog.TAG_COMMA);
            sb2.append(view.getResources().getString(R$string.speak_radio_button));
            view.setContentDescription(sb2.toString());
        }
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        setContentDescription(preferenceViewHolder);
    }

    public RadioPreference(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        init(context);
    }

    public RadioPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }
}
