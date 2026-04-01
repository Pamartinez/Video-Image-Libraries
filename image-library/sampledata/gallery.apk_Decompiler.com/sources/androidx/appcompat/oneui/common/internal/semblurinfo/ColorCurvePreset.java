package androidx.appcompat.oneui.common.internal.semblurinfo;

import androidx.appcompat.oneui.common.internal.resource.ThemeResourceImpl;
import androidx.core.view.SemBlurCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\rHÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0012\u001a\u0004\b\u0015\u0010\u0014¨\u0006\u0016"}, d2 = {"Landroidx/appcompat/oneui/common/internal/semblurinfo/ColorCurvePreset;", "Landroidx/appcompat/oneui/common/internal/resource/ThemeResourceImpl;", "Landroidx/core/view/SemBlurCompat$CurveParameter;", "colorCurvePresetLight", "colorCurvePresetDark", "<init>", "(Landroidx/core/view/SemBlurCompat$CurveParameter;Landroidx/core/view/SemBlurCompat$CurveParameter;)V", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroidx/core/view/SemBlurCompat$CurveParameter;", "getColorCurvePresetLight", "()Landroidx/core/view/SemBlurCompat$CurveParameter;", "getColorCurvePresetDark", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ColorCurvePreset extends ThemeResourceImpl<SemBlurCompat.CurveParameter> {
    private final SemBlurCompat.CurveParameter colorCurvePresetDark;
    private final SemBlurCompat.CurveParameter colorCurvePresetLight;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ColorCurvePreset(SemBlurCompat.CurveParameter curveParameter, SemBlurCompat.CurveParameter curveParameter2) {
        super(curveParameter, curveParameter2);
        j.e(curveParameter, "colorCurvePresetLight");
        j.e(curveParameter2, "colorCurvePresetDark");
        this.colorCurvePresetLight = curveParameter;
        this.colorCurvePresetDark = curveParameter2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ColorCurvePreset)) {
            return false;
        }
        ColorCurvePreset colorCurvePreset = (ColorCurvePreset) obj;
        if (j.a(this.colorCurvePresetLight, colorCurvePreset.colorCurvePresetLight) && j.a(this.colorCurvePresetDark, colorCurvePreset.colorCurvePresetDark)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.colorCurvePresetDark.hashCode() + (this.colorCurvePresetLight.hashCode() * 31);
    }

    public String toString() {
        return "ColorCurvePreset(colorCurvePresetLight=" + this.colorCurvePresetLight + ", colorCurvePresetDark=" + this.colorCurvePresetDark + ')';
    }
}
