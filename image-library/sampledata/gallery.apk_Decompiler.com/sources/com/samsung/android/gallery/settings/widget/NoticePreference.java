package com.samsung.android.gallery.settings.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.Preference;
import com.samsung.android.gallery.resources.R$color;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NoticePreference extends Preference {
    public NoticePreference(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        seslSetSummaryColor(context.getColor(R$color.red));
    }

    public NoticePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        seslSetSummaryColor(context.getColor(R$color.red));
    }
}
