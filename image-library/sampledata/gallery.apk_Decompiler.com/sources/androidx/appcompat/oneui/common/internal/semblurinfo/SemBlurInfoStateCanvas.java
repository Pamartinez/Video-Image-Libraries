package androidx.appcompat.oneui.common.internal.semblurinfo;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.appcompat.oneui.common.internal.resource.ThemeResourceColor;
import androidx.core.oneui.common.internal.semblurinfo.SemBlurInfoState;
import androidx.core.view.SemBlurCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0012\b\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0015HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u001c\u001a\u00020\u000f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aHÖ\u0003¢\u0006\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010\u0019R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010 \u001a\u0004\b!\u0010\"R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010#\u001a\u0004\b$\u0010%R\u0019\u0010\t\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\t\u0010&\u001a\u0004\b'\u0010(R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010)\u001a\u0004\b*\u0010+¨\u0006,"}, d2 = {"Landroidx/appcompat/oneui/common/internal/semblurinfo/SemBlurInfoStateCanvas;", "Landroidx/core/oneui/common/internal/semblurinfo/SemBlurInfoState;", "", "blurMode", "Landroidx/appcompat/oneui/common/internal/semblurinfo/ColorCurvePreset;", "colorCurvePreset", "Landroidx/appcompat/oneui/common/internal/resource/ThemeResourceColor;", "blurBackgroundColor", "Landroid/graphics/drawable/Drawable;", "nonBlurBackground", "useTypeCanvasBlur", "<init>", "(ILandroidx/appcompat/oneui/common/internal/semblurinfo/ColorCurvePreset;Landroidx/appcompat/oneui/common/internal/resource/ThemeResourceColor;Landroid/graphics/drawable/Drawable;Ljava/lang/Integer;)V", "Landroid/view/View;", "view", "", "applyBlurInfo", "(Landroid/view/View;)Z", "Lme/x;", "clearBlurInfo", "(Landroid/view/View;)V", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "I", "getBlurMode", "Landroidx/appcompat/oneui/common/internal/semblurinfo/ColorCurvePreset;", "getColorCurvePreset", "()Landroidx/appcompat/oneui/common/internal/semblurinfo/ColorCurvePreset;", "Landroidx/appcompat/oneui/common/internal/resource/ThemeResourceColor;", "getBlurBackgroundColor", "()Landroidx/appcompat/oneui/common/internal/resource/ThemeResourceColor;", "Landroid/graphics/drawable/Drawable;", "getNonBlurBackground", "()Landroid/graphics/drawable/Drawable;", "Ljava/lang/Integer;", "getUseTypeCanvasBlur", "()Ljava/lang/Integer;", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SemBlurInfoStateCanvas extends SemBlurInfoState {
    private final ThemeResourceColor blurBackgroundColor;
    private final int blurMode;
    private final ColorCurvePreset colorCurvePreset;
    private final Drawable nonBlurBackground;
    private final Integer useTypeCanvasBlur;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SemBlurInfoStateCanvas(int i2, ColorCurvePreset colorCurvePreset2, ThemeResourceColor themeResourceColor, Drawable drawable, Integer num) {
        super(i2);
        j.e(colorCurvePreset2, "colorCurvePreset");
        this.blurMode = i2;
        this.colorCurvePreset = colorCurvePreset2;
        this.blurBackgroundColor = themeResourceColor;
        this.nonBlurBackground = drawable;
        this.useTypeCanvasBlur = num;
    }

    public boolean applyBlurInfo(View view) {
        j.e(view, "view");
        Context context = view.getContext();
        int blurMode2 = getBlurMode();
        ColorCurvePreset colorCurvePreset2 = this.colorCurvePreset;
        j.d(context, "context");
        View view2 = view;
        if (!SemBlurCompat.setBlurEffectPreset(view2, blurMode2, (SemBlurCompat.CurveParameter) colorCurvePreset2.getResource(context), (Integer) null, (Float) null, this.useTypeCanvasBlur)) {
            return false;
        }
        view2.setClipToOutline(true);
        ThemeResourceColor themeResourceColor = this.blurBackgroundColor;
        if (themeResourceColor != null) {
            view2.setBackgroundTintList(ColorStateList.valueOf(themeResourceColor.getResource(context).intValue()));
        }
        return true;
    }

    public void clearBlurInfo(View view) {
        j.e(view, "view");
        SemBlurCompat.setBlurInfoClear(view);
        view.setBackgroundTintList((ColorStateList) null);
        Drawable drawable = this.nonBlurBackground;
        if (drawable != null) {
            view.setBackground(drawable);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SemBlurInfoStateCanvas)) {
            return false;
        }
        SemBlurInfoStateCanvas semBlurInfoStateCanvas = (SemBlurInfoStateCanvas) obj;
        if (this.blurMode == semBlurInfoStateCanvas.blurMode && j.a(this.colorCurvePreset, semBlurInfoStateCanvas.colorCurvePreset) && j.a(this.blurBackgroundColor, semBlurInfoStateCanvas.blurBackgroundColor) && j.a(this.nonBlurBackground, semBlurInfoStateCanvas.nonBlurBackground) && j.a(this.useTypeCanvasBlur, semBlurInfoStateCanvas.useTypeCanvasBlur)) {
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
        int hashCode = (this.colorCurvePreset.hashCode() + (Integer.hashCode(this.blurMode) * 31)) * 31;
        ThemeResourceColor themeResourceColor = this.blurBackgroundColor;
        int i8 = 0;
        if (themeResourceColor == null) {
            i2 = 0;
        } else {
            i2 = themeResourceColor.hashCode();
        }
        int i10 = (hashCode + i2) * 31;
        Drawable drawable = this.nonBlurBackground;
        if (drawable == null) {
            i7 = 0;
        } else {
            i7 = drawable.hashCode();
        }
        int i11 = (i10 + i7) * 31;
        Integer num = this.useTypeCanvasBlur;
        if (num != null) {
            i8 = num.hashCode();
        }
        return i11 + i8;
    }

    public String toString() {
        return "SemBlurInfoStateCanvas(blurMode=" + this.blurMode + ", colorCurvePreset=" + this.colorCurvePreset + ", blurBackgroundColor=" + this.blurBackgroundColor + ", nonBlurBackground=" + this.nonBlurBackground + ", useTypeCanvasBlur=" + this.useTypeCanvasBlur + ')';
    }
}
