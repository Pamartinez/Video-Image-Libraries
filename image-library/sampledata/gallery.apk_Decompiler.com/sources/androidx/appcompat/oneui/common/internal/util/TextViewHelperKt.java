package androidx.appcompat.oneui.common.internal.util;

import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a%\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroid/widget/TextView;", "", "baseSizeDp", "Landroidx/appcompat/oneui/common/internal/util/MaxFontScaleRatio;", "maxFontScale", "Lme/x;", "checkMaxFontScale", "(Landroid/widget/TextView;ILandroidx/appcompat/oneui/common/internal/util/MaxFontScaleRatio;)V", "appcompat_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TextViewHelperKt {
    public static final void checkMaxFontScale(TextView textView, int i2, MaxFontScaleRatio maxFontScaleRatio) {
        j.e(textView, "<this>");
        j.e(maxFontScaleRatio, "maxFontScale");
        float f = textView.getResources().getConfiguration().fontScale;
        float ratio = maxFontScaleRatio.getRatio();
        if (f > ratio) {
            f = ratio;
        }
        textView.setTextSize(0, ((float) textView.getResources().getDimensionPixelSize(i2)) * f);
    }
}
