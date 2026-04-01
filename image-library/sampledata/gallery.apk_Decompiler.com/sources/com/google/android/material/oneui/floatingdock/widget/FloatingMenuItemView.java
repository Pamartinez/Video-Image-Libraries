package com.google.android.material.oneui.floatingdock.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.util.SeslMisc;
import androidx.appcompat.widget.AppCompatImageView;
import com.sec.android.gallery3d.R;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/google/android/material/oneui/floatingdock/widget/FloatingMenuItemView;", "Landroidx/appcompat/widget/AppCompatImageView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "getAccessibilityClassName", "", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FloatingMenuItemView extends AppCompatImageView {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FloatingMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int i2;
        j.e(context, "context");
        if (SeslMisc.isLightTheme(context)) {
            i2 = R.drawable.sesl_floating_pane_menu_item_background_ripple;
        } else {
            i2 = R.drawable.sesl_floating_pane_menu_item_background_ripple_dark;
        }
        setBackground(context.getDrawable(i2));
    }

    public CharSequence getAccessibilityClassName() {
        return "android.widget.Button";
    }
}
