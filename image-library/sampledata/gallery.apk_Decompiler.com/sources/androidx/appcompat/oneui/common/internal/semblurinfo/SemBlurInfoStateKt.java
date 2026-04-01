package androidx.appcompat.oneui.common.internal.semblurinfo;

import androidx.core.view.SemBlurCompat;
import com.samsung.android.sdk.scs.base.StatusCodes;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0016\u0010\u0000\u001a\u00020\u00018\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\u00020\u00018\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003¨\u0006\u0006"}, d2 = {"FIGMA_BLUR_COMPONENT_DARK_THICK", "Landroidx/core/view/SemBlurCompat$CurveParameter;", "getFIGMA_BLUR_COMPONENT_DARK_THICK", "()Landroidx/core/view/SemBlurCompat$CurveParameter;", "FIGMA_BLUR_COMPONENT_LIGHT_THICK", "getFIGMA_BLUR_COMPONENT_LIGHT_THICK", "appcompat_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SemBlurInfoStateKt {
    private static final SemBlurCompat.CurveParameter FIGMA_BLUR_COMPONENT_DARK_THICK = new SemBlurCompat.CurveParameter(StatusCodes.INPUT_MISSING, 0.5f, -15.0f, 0.0f, 255.0f, 33.8f, 153.7f);
    private static final SemBlurCompat.CurveParameter FIGMA_BLUR_COMPONENT_LIGHT_THICK = new SemBlurCompat.CurveParameter(StatusCodes.INPUT_MISSING, 0.4f, 15.0f, 15.0f, 235.0f, 176.7f, 253.2f);

    public static final SemBlurCompat.CurveParameter getFIGMA_BLUR_COMPONENT_DARK_THICK() {
        return FIGMA_BLUR_COMPONENT_DARK_THICK;
    }

    public static final SemBlurCompat.CurveParameter getFIGMA_BLUR_COMPONENT_LIGHT_THICK() {
        return FIGMA_BLUR_COMPONENT_LIGHT_THICK;
    }
}
