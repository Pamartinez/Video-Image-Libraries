package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import androidx.appcompat.R$color;
import androidx.appcompat.R$dimen;
import androidx.appcompat.util.SeslMisc;
import androidx.core.content.res.ResourcesCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslDropDownItemTextView extends SeslCheckedTextView {
    private static final String TAG = "SeslDropDownItemTextView";

    public SeslDropDownItemTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public void setChecked(boolean z) {
        Context context;
        int i2;
        int i7;
        super.setChecked(z);
        if (Build.VERSION.SDK_INT >= 34) {
            Typeface create = Typeface.create("sec", 0);
            if (z) {
                i7 = 600;
            } else {
                i7 = 400;
            }
            setTypeface(Typeface.create(create, i7, false));
        } else {
            setTypeface(Typeface.create("sec-roboto-light", z ? 1 : 0));
        }
        if (z && (context = getContext()) != null && getCurrentTextColor() == -65281) {
            String str = TAG;
            Log.w(str, "text color reload!");
            if (SeslMisc.isLightTheme(context)) {
                i2 = R$color.sesl_spinner_dropdown_text_color_light;
            } else {
                i2 = R$color.sesl_spinner_dropdown_text_color_dark;
            }
            ColorStateList colorStateList = ResourcesCompat.getColorStateList(context.getResources(), i2, context.getTheme());
            if (colorStateList != null) {
                setTextColor(colorStateList);
            } else {
                Log.w(str, "Didn't set SeslDropDownItemTextView text color!!");
            }
        }
    }

    public SeslDropDownItemTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Resources resources = context.getResources();
        setMaxWidth(resources.getDisplayMetrics().widthPixels - (resources.getDimensionPixelSize(R$dimen.sesl_menu_popup_offset_horizontal) * 2));
    }
}
