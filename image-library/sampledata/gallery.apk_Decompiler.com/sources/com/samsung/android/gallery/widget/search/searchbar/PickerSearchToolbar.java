package com.samsung.android.gallery.widget.search.searchbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.R$drawable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PickerSearchToolbar extends SearchToolbar {
    public PickerSearchToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void updateBackground(boolean z) {
        if (!Features.isEnabled(Features.IS_THEME_INSTALLED)) {
            if (z) {
                setBackgroundResource(R$drawable.picker_search_bar_background);
            } else {
                setBackground((Drawable) null);
            }
        }
    }
}
