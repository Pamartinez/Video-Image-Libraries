package v2;

import android.content.res.ColorStateList;
import android.graphics.Color;
import androidx.core.graphics.ColorUtils;
import com.samsung.android.sdk.cover.ScoverState;

/* renamed from: v2.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0299a {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f1980a = {16842919};
    public static final int[] b = {16842908};

    /* renamed from: c  reason: collision with root package name */
    public static final int[] f1981c = {16842913, 16842919};
    public static final int[] d = {16842913};

    public static int a(ColorStateList colorStateList, int[] iArr) {
        int i2;
        if (colorStateList != null) {
            i2 = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        } else {
            i2 = 0;
        }
        return ColorUtils.setAlphaComponent(i2, Math.min(Color.alpha(i2) * 2, ScoverState.TYPE_NFC_SMART_COVER));
    }

    public static ColorStateList b(ColorStateList colorStateList) {
        if (colorStateList != null) {
            return colorStateList;
        }
        return ColorStateList.valueOf(0);
    }
}
