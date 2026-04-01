package com.samsung.android.gallery.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.samsung.android.gallery.support.utils.FontUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UtlTextView extends TextView {
    public UtlTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        FontUtils.setUpToLargeAttr(this, attributeSet);
    }
}
