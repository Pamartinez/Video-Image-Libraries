package androidx.core.view;

import android.content.Context;
import android.provider.Settings;
import android.view.View;
import androidx.core.oneui.OneUI;
import androidx.reflect.feature.SeslFloatingFeatureReflector;
import androidx.reflect.provider.SeslSettingsReflector$SeslSystemReflector;
import androidx.reflect.view.SeslSemBlurInfoReflector;
import androidx.reflect.view.SeslViewReflector;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001eB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JE\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0004\b\u000e\u0010\u000fJK\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00102\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u001b\u0010\u001aJ+\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\u001c\u0010\u001d¨\u0006\u001f"}, d2 = {"Landroidx/core/view/SemBlurCompat;", "", "<init>", "()V", "Landroid/view/View;", "view", "", "blurMode", "color", "blurRadius", "", "cornerRadius", "useTypeCanvasBlur", "", "setBlurEffect", "(Landroid/view/View;IIIFLjava/lang/Integer;)Z", "Landroidx/core/view/SemBlurCompat$CurveParameter;", "curveParameter", "setBlurEffectPreset", "(Landroid/view/View;ILandroidx/core/view/SemBlurCompat$CurveParameter;Ljava/lang/Integer;Ljava/lang/Float;Ljava/lang/Integer;)Z", "Lme/x;", "setBlurInfoClear", "(Landroid/view/View;)V", "Landroid/content/Context;", "context", "isThemeApplied", "(Landroid/content/Context;)Z", "isReduceTransparencySettingsEnabled", "isNotBlurSupport", "(Landroid/content/Context;ILjava/lang/Integer;)Z", "CurveParameter", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SemBlurCompat {
    public static final SemBlurCompat INSTANCE = new SemBlurCompat();

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\b\u0017\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f¨\u0006\u0015"}, d2 = {"Landroidx/core/view/SemBlurCompat$CurveParameter;", "", "blurRadius", "", "saturation", "", "curveLevel", "curveMinX", "curveMaxX", "curveMinY", "curveMaxY", "(IFFFFFF)V", "getBlurRadius", "()I", "getCurveLevel", "()F", "getCurveMaxX", "getCurveMaxY", "getCurveMinX", "getCurveMinY", "getSaturation", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CurveParameter {
        private final int blurRadius;
        private final float curveLevel;
        private final float curveMaxX;
        private final float curveMaxY;
        private final float curveMinX;
        private final float curveMinY;
        private final float saturation;

        public CurveParameter(int i2, float f, float f5, float f8, float f10, float f11, float f12) {
            this.blurRadius = i2;
            this.saturation = f;
            this.curveLevel = f5;
            this.curveMinX = f8;
            this.curveMaxX = f10;
            this.curveMinY = f11;
            this.curveMaxY = f12;
        }

        public final int getBlurRadius() {
            return this.blurRadius;
        }

        public final float getCurveLevel() {
            return this.curveLevel;
        }

        public final float getCurveMaxX() {
            return this.curveMaxX;
        }

        public final float getCurveMaxY() {
            return this.curveMaxY;
        }

        public final float getCurveMinX() {
            return this.curveMinX;
        }

        public final float getCurveMinY() {
            return this.curveMinY;
        }

        public final float getSaturation() {
            return this.saturation;
        }
    }

    private SemBlurCompat() {
    }

    private final boolean isNotBlurSupport(Context context, int i2, Integer num) {
        String string = SeslFloatingFeatureReflector.getString("SEC_FLOATING_FEATURE_GRAPHICS_SUPPORT_3D_SURFACE_TRANSITION_FLAG", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE);
        boolean isGreaterOrEqual = OneUI.isGreaterOrEqual(OneUI.Version.ONEUI_8_5);
        if (isThemeApplied(context) || isReduceTransparencySettingsEnabled(context)) {
            return true;
        }
        if (i2 != 2) {
            return !Boolean.parseBoolean(string);
        }
        if (num == null || num.intValue() != 1) {
            return false;
        }
        if (!Boolean.parseBoolean(string) || !isGreaterOrEqual) {
            return true;
        }
        return false;
    }

    private final boolean isReduceTransparencySettingsEnabled(Context context) {
        String field_SEM_ACCESSIBILITY_REDUCE_TRANSPARENCY = SeslSettingsReflector$SeslSystemReflector.getField_SEM_ACCESSIBILITY_REDUCE_TRANSPARENCY();
        if (j.a(field_SEM_ACCESSIBILITY_REDUCE_TRANSPARENCY, "not_supported") || Settings.System.getInt(context.getContentResolver(), field_SEM_ACCESSIBILITY_REDUCE_TRANSPARENCY, 0) != 1) {
            return false;
        }
        return true;
    }

    private final boolean isThemeApplied(Context context) {
        if (Settings.System.getString(context.getContentResolver(), "current_sec_active_themepackage") != null) {
            return true;
        }
        return false;
    }

    public static final boolean setBlurEffect(View view, int i2, int i7, int i8, float f) {
        j.e(view, "view");
        return setBlurEffect$default(view, i2, i7, i8, f, (Integer) null, 32, (Object) null);
    }

    public static /* synthetic */ boolean setBlurEffect$default(View view, int i2, int i7, int i8, float f, Integer num, int i10, Object obj) {
        if ((i10 & 32) != 0) {
            num = 0;
        }
        return setBlurEffect(view, i2, i7, i8, f, num);
    }

    public static final boolean setBlurEffectPreset(View view, int i2, CurveParameter curveParameter, Integer num, Float f, Integer num2) {
        Object semCreateBlurBuilder;
        j.e(view, "view");
        j.e(curveParameter, "curveParameter");
        Context context = view.getContext();
        SemBlurCompat semBlurCompat = INSTANCE;
        j.d(context, "context");
        if (semBlurCompat.isNotBlurSupport(context, i2, num2) || (semCreateBlurBuilder = SeslSemBlurInfoReflector.semCreateBlurBuilder(i2)) == null) {
            return false;
        }
        SeslSemBlurInfoReflector.semSetBuilderBlurRadius(semCreateBlurBuilder, curveParameter.getBlurRadius());
        SeslSemBlurInfoReflector.semSetColorCurve(semCreateBlurBuilder, curveParameter.getSaturation(), curveParameter.getCurveLevel(), curveParameter.getCurveMinX(), curveParameter.getCurveMaxX(), curveParameter.getCurveMinY(), curveParameter.getCurveMaxY());
        if (num != null) {
            SeslSemBlurInfoReflector.semSetBuilderBlurBackgroundColor(semCreateBlurBuilder, num.intValue());
        }
        if (f != null) {
            SeslSemBlurInfoReflector.semSetBuilderBlurBackgroundCornerRadius(semCreateBlurBuilder, f.floatValue());
        }
        SeslSemBlurInfoReflector.semBuildSetBlurInfo(semCreateBlurBuilder, view);
        return true;
    }

    public static /* synthetic */ boolean setBlurEffectPreset$default(View view, int i2, CurveParameter curveParameter, Integer num, Float f, Integer num2, int i7, Object obj) {
        if ((i7 & 8) != 0) {
            num = null;
        }
        if ((i7 & 16) != 0) {
            f = null;
        }
        if ((i7 & 32) != 0) {
            num2 = 0;
        }
        return setBlurEffectPreset(view, i2, curveParameter, num, f, num2);
    }

    public static final void setBlurInfoClear(View view) {
        j.e(view, "view");
        SeslViewReflector.semSetBlurInfo(view, (Object) null);
    }

    public static final boolean setBlurEffect(View view, int i2, int i7, int i8, float f, Integer num) {
        Object semCreateBlurBuilder;
        j.e(view, "view");
        Context context = view.getContext();
        SemBlurCompat semBlurCompat = INSTANCE;
        j.d(context, "context");
        if (semBlurCompat.isNotBlurSupport(context, i2, num) || (semCreateBlurBuilder = SeslSemBlurInfoReflector.semCreateBlurBuilder(i2)) == null) {
            return false;
        }
        SeslSemBlurInfoReflector.semSetBuilderBlurRadius(semCreateBlurBuilder, i8);
        SeslSemBlurInfoReflector.semSetBuilderBlurBackgroundColor(semCreateBlurBuilder, i7);
        SeslSemBlurInfoReflector.semSetBuilderBlurBackgroundCornerRadius(semCreateBlurBuilder, f);
        SeslSemBlurInfoReflector.semBuildSetBlurInfo(semCreateBlurBuilder, view);
        return true;
    }
}
