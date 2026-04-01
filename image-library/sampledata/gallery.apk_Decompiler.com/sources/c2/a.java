package C2;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;
import androidx.appcompat.widget.AppCompatTextView;
import com.sec.android.gallery3d.R;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends AppCompatTextView {
    public static int a(Context context, TypedArray typedArray, int... iArr) {
        int i2 = -1;
        for (int i7 = 0; i7 < iArr.length && i2 < 0; i7++) {
            int i8 = iArr[i7];
            TypedValue typedValue = new TypedValue();
            if (!typedArray.getValue(i8, typedValue) || typedValue.type != 2) {
                i2 = typedArray.getDimensionPixelSize(i8, -1);
            } else {
                TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{typedValue.data});
                int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
                obtainStyledAttributes.recycle();
                i2 = dimensionPixelSize;
            }
        }
        return i2;
    }

    public final void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        if (k.K(context, R.attr.textAppearanceLineHeightEnabled, true)) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(i2, Q1.a.f615C);
            int a7 = a(getContext(), obtainStyledAttributes, 1, 2);
            obtainStyledAttributes.recycle();
            if (a7 >= 0) {
                setLineHeight(a7);
            }
        }
    }
}
