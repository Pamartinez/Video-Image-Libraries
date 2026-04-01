package com.samsung.android.gallery.widget.search.searchbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomSearchToolbarInnerContainer extends LinearLayout {
    public BottomSearchToolbarInnerContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void clearFocus() {
        if (getHeight() > 0) {
            super.clearFocus();
        }
    }

    public void setGradientBackground(boolean z) {
        if (!z) {
            setBackground((Drawable) null);
        }
    }
}
