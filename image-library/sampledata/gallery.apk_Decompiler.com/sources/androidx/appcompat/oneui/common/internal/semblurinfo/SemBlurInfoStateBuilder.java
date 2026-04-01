package androidx.appcompat.oneui.common.internal.semblurinfo;

import android.graphics.drawable.Drawable;
import androidx.appcompat.oneui.common.internal.resource.ThemeResourceColor;
import androidx.core.oneui.common.internal.semblurinfo.SemBlurInfoState;
import c0.C0086a;
import com.samsung.android.sdk.bixby2.action.ActionHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u000bJ\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u000eR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0004\n\u0002\u0010\u000f¨\u0006\u0014"}, d2 = {"Landroidx/appcompat/oneui/common/internal/semblurinfo/SemBlurInfoStateBuilder;", "", "blurMode", "", "colorCurvePreset", "Landroidx/appcompat/oneui/common/internal/semblurinfo/ColorCurvePreset;", "blurBackgroundColor", "Landroidx/appcompat/oneui/common/internal/resource/ThemeResourceColor;", "useTypeCanvasBlur", "(ILandroidx/appcompat/oneui/common/internal/semblurinfo/ColorCurvePreset;Landroidx/appcompat/oneui/common/internal/resource/ThemeResourceColor;Ljava/lang/Integer;)V", "cornerRadius", "", "Ljava/lang/Float;", "nonBlurBackground", "Landroid/graphics/drawable/Drawable;", "Ljava/lang/Integer;", "build", "Landroidx/core/oneui/common/internal/semblurinfo/SemBlurInfoState;", "radius", "background", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SemBlurInfoStateBuilder {
    private final ThemeResourceColor blurBackgroundColor;
    private final int blurMode;
    private final ColorCurvePreset colorCurvePreset;
    private Float cornerRadius;
    private Drawable nonBlurBackground;
    private final Integer useTypeCanvasBlur;

    public SemBlurInfoStateBuilder(int i2, ColorCurvePreset colorCurvePreset2, ThemeResourceColor themeResourceColor, Integer num) {
        j.e(colorCurvePreset2, "colorCurvePreset");
        this.blurMode = i2;
        this.colorCurvePreset = colorCurvePreset2;
        this.blurBackgroundColor = themeResourceColor;
        this.useTypeCanvasBlur = num;
    }

    public final SemBlurInfoState build() {
        int i2 = this.blurMode;
        if (i2 == 0) {
            Float f = this.cornerRadius;
            ColorCurvePreset colorCurvePreset2 = this.colorCurvePreset;
            ThemeResourceColor themeResourceColor = this.blurBackgroundColor;
            j.b(themeResourceColor);
            return new SemBlurInfoStateWindow(i2, f, colorCurvePreset2, themeResourceColor, this.nonBlurBackground);
        } else if (i2 == 2) {
            ColorCurvePreset colorCurvePreset3 = this.colorCurvePreset;
            ThemeResourceColor themeResourceColor2 = this.blurBackgroundColor;
            j.b(themeResourceColor2);
            return new SemBlurInfoStateCanvas(i2, colorCurvePreset3, themeResourceColor2, this.nonBlurBackground, this.useTypeCanvasBlur);
        } else {
            throw new IllegalStateException(C0086a.l(new StringBuilder("blurMode("), this.blurMode, ") is not supported. support mode: BLUR_MODE_CANVAS, BLUR_MODE_WINDOW"));
        }
    }

    public final SemBlurInfoStateBuilder cornerRadius(float f) {
        this.cornerRadius = Float.valueOf(f);
        return this;
    }

    public final SemBlurInfoStateBuilder nonBlurBackground(Drawable drawable) {
        j.e(drawable, ActionHandler.ACTION_BACKGROUND);
        this.nonBlurBackground = drawable;
        return this;
    }
}
