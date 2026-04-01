package androidx.appcompat.oneui.common.internal.policy;

import android.content.Context;
import androidx.appcompat.oneui.common.internal.resource.ThemeResourceColor;
import androidx.appcompat.oneui.common.internal.semblurinfo.ColorCurvePreset;
import androidx.appcompat.oneui.common.internal.semblurinfo.SemBlurInfoStateBuilder;
import androidx.appcompat.oneui.common.internal.semblurinfo.SemBlurInfoStateKt;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Landroidx/appcompat/oneui/common/internal/policy/BlurInfoState;", "", "()V", "generateFloatingComponentBlurInfoStateBuilder", "Landroidx/appcompat/oneui/common/internal/semblurinfo/SemBlurInfoStateBuilder;", "context", "Landroid/content/Context;", "blurMode", "", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BlurInfoState {
    public static final BlurInfoState INSTANCE = new BlurInfoState();

    private BlurInfoState() {
    }

    public final SemBlurInfoStateBuilder generateFloatingComponentBlurInfoStateBuilder(Context context, int i2) {
        j.e(context, "context");
        return new SemBlurInfoStateBuilder(i2, new ColorCurvePreset(SemBlurInfoStateKt.getFIGMA_BLUR_COMPONENT_LIGHT_THICK(), SemBlurInfoStateKt.getFIGMA_BLUR_COMPONENT_DARK_THICK()), new ThemeResourceColor(0, 0, 2, (e) null), 1);
    }
}
