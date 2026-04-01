package androidx.appcompat.oneui.common.internal.semblurinfo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.appcompat.oneui.common.internal.resource.ThemeResourceColor;
import androidx.core.oneui.common.internal.semblurinfo.SemBlurInfoState;
import androidx.core.view.SemBlurCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0012\b\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0017\u001a\u00020\u0016HÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001d\u001a\u00020\u00102\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bHÖ\u0003¢\u0006\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u001f\u001a\u0004\b \u0010\u001aR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010$\u001a\u0004\b%\u0010&R\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u0010'\u001a\u0004\b(\u0010)R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010*\u001a\u0004\b+\u0010,¨\u0006-"}, d2 = {"Landroidx/appcompat/oneui/common/internal/semblurinfo/SemBlurInfoStateWindow;", "Landroidx/core/oneui/common/internal/semblurinfo/SemBlurInfoState;", "", "blurMode", "", "cornerRadius", "Landroidx/appcompat/oneui/common/internal/semblurinfo/ColorCurvePreset;", "colorCurvePreset", "Landroidx/appcompat/oneui/common/internal/resource/ThemeResourceColor;", "blurBackgroundColor", "Landroid/graphics/drawable/Drawable;", "nonBlurBackground", "<init>", "(ILjava/lang/Float;Landroidx/appcompat/oneui/common/internal/semblurinfo/ColorCurvePreset;Landroidx/appcompat/oneui/common/internal/resource/ThemeResourceColor;Landroid/graphics/drawable/Drawable;)V", "Landroid/view/View;", "view", "", "applyBlurInfo", "(Landroid/view/View;)Z", "Lme/x;", "clearBlurInfo", "(Landroid/view/View;)V", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "I", "getBlurMode", "Ljava/lang/Float;", "getCornerRadius", "()Ljava/lang/Float;", "Landroidx/appcompat/oneui/common/internal/semblurinfo/ColorCurvePreset;", "getColorCurvePreset", "()Landroidx/appcompat/oneui/common/internal/semblurinfo/ColorCurvePreset;", "Landroidx/appcompat/oneui/common/internal/resource/ThemeResourceColor;", "getBlurBackgroundColor", "()Landroidx/appcompat/oneui/common/internal/resource/ThemeResourceColor;", "Landroid/graphics/drawable/Drawable;", "getNonBlurBackground", "()Landroid/graphics/drawable/Drawable;", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SemBlurInfoStateWindow extends SemBlurInfoState {
    private final ThemeResourceColor blurBackgroundColor;
    private final int blurMode;
    private final ColorCurvePreset colorCurvePreset;
    private final Float cornerRadius;
    private final Drawable nonBlurBackground;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SemBlurInfoStateWindow(int i2, Float f, ColorCurvePreset colorCurvePreset2, ThemeResourceColor themeResourceColor, Drawable drawable) {
        super(i2);
        j.e(colorCurvePreset2, "colorCurvePreset");
        this.blurMode = i2;
        this.cornerRadius = f;
        this.colorCurvePreset = colorCurvePreset2;
        this.blurBackgroundColor = themeResourceColor;
        this.nonBlurBackground = drawable;
    }

    public boolean applyBlurInfo(View view) {
        Integer num;
        j.e(view, "view");
        Context context = view.getContext();
        int blurMode2 = getBlurMode();
        ColorCurvePreset colorCurvePreset2 = this.colorCurvePreset;
        j.d(context, "context");
        SemBlurCompat.CurveParameter curveParameter = (SemBlurCompat.CurveParameter) colorCurvePreset2.getResource(context);
        ThemeResourceColor themeResourceColor = this.blurBackgroundColor;
        if (themeResourceColor != null) {
            num = themeResourceColor.getResource(context);
        } else {
            num = null;
        }
        View view2 = view;
        boolean blurEffectPreset$default = SemBlurCompat.setBlurEffectPreset$default(view2, blurMode2, curveParameter, num, this.cornerRadius, (Integer) null, 32, (Object) null);
        view2.setClipToOutline(true);
        return blurEffectPreset$default;
    }

    public void clearBlurInfo(View view) {
        j.e(view, "view");
        SemBlurCompat.setBlurInfoClear(view);
        Drawable drawable = this.nonBlurBackground;
        if (drawable != null) {
            view.setBackground(drawable);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SemBlurInfoStateWindow)) {
            return false;
        }
        SemBlurInfoStateWindow semBlurInfoStateWindow = (SemBlurInfoStateWindow) obj;
        if (this.blurMode == semBlurInfoStateWindow.blurMode && j.a(this.cornerRadius, semBlurInfoStateWindow.cornerRadius) && j.a(this.colorCurvePreset, semBlurInfoStateWindow.colorCurvePreset) && j.a(this.blurBackgroundColor, semBlurInfoStateWindow.blurBackgroundColor) && j.a(this.nonBlurBackground, semBlurInfoStateWindow.nonBlurBackground)) {
            return true;
        }
        return false;
    }

    public int getBlurMode() {
        return this.blurMode;
    }

    public int hashCode() {
        int i2;
        int i7;
        int hashCode = Integer.hashCode(this.blurMode) * 31;
        Float f = this.cornerRadius;
        int i8 = 0;
        if (f == null) {
            i2 = 0;
        } else {
            i2 = f.hashCode();
        }
        int hashCode2 = (this.colorCurvePreset.hashCode() + ((hashCode + i2) * 31)) * 31;
        ThemeResourceColor themeResourceColor = this.blurBackgroundColor;
        if (themeResourceColor == null) {
            i7 = 0;
        } else {
            i7 = themeResourceColor.hashCode();
        }
        int i10 = (hashCode2 + i7) * 31;
        Drawable drawable = this.nonBlurBackground;
        if (drawable != null) {
            i8 = drawable.hashCode();
        }
        return i10 + i8;
    }

    public String toString() {
        return "SemBlurInfoStateWindow(blurMode=" + this.blurMode + ", cornerRadius=" + this.cornerRadius + ", colorCurvePreset=" + this.colorCurvePreset + ", blurBackgroundColor=" + this.blurBackgroundColor + ", nonBlurBackground=" + this.nonBlurBackground + ')';
    }
}
