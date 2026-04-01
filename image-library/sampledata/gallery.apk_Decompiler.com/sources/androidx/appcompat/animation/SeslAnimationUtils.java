package androidx.appcompat.animation;

import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslAnimationUtils {
    public static final Interpolator ELASTIC_40 = new SeslElasticInterpolator(1.0f, 0.8f);
    public static final Interpolator ELASTIC_50 = new SeslElasticInterpolator(1.0f, 0.7f);
    public static final Interpolator SINE_IN_OUT_70 = new PathInterpolator(0.33f, 0.0f, 0.3f, 1.0f);
    public static final Interpolator SINE_IN_OUT_80 = new PathInterpolator(0.33f, 0.0f, 0.2f, 1.0f);
    public static final Interpolator SINE_IN_OUT_90 = new PathInterpolator(0.33f, 0.0f, 0.1f, 1.0f);
    public static final Interpolator SINE_OUT_33 = new PathInterpolator(0.17f, 0.17f, 0.67f, 1.0f);
    public static final Interpolator SINE_OUT_70 = new PathInterpolator(0.17f, 0.17f, 0.3f, 1.0f);
    public static final Interpolator SINE_OUT_80 = new PathInterpolator(0.17f, 0.17f, 0.2f, 1.0f);
}
