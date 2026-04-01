package com.samsung.android.gallery.widget.animator;

import android.view.animation.Interpolator;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PathInterpolator {
    private static final HashMap<Type, Float[]> VALUE_MAP = new HashMap<Type, Float[]>() {
        {
            Type type = Type.TYPE_SINE_IN_OUT_33;
            Float valueOf = Float.valueOf(0.33f);
            Float valueOf2 = Float.valueOf(0.0f);
            Float valueOf3 = Float.valueOf(0.67f);
            Float valueOf4 = Float.valueOf(1.0f);
            put(type, new Float[]{valueOf, valueOf2, valueOf3, valueOf4});
            Type type2 = Type.TYPE_SINE_IN_OUT_60;
            Float valueOf5 = Float.valueOf(0.4f);
            put(type2, new Float[]{valueOf, valueOf2, valueOf5, valueOf4});
            put(Type.TYPE_SINE_IN_OUT_70, new Float[]{valueOf, valueOf2, Float.valueOf(0.3f), valueOf4});
            Type type3 = Type.TYPE_SINE_IN_OUT_80;
            Float valueOf6 = Float.valueOf(0.2f);
            put(type3, new Float[]{valueOf, valueOf2, valueOf6, valueOf4});
            put(Type.TYPE_SINE_IN_OUT_90, new Float[]{valueOf, valueOf2, Float.valueOf(0.1f), valueOf4});
            if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
                put(Type.TYPE_STORY_END_ANIMATION, new Float[]{valueOf5, valueOf2, valueOf6, valueOf4});
                put(Type.TYPE_RELATED_NUMBER_FADE_IN, new Float[]{valueOf2, Float.valueOf(0.85f), Float.valueOf(0.13f), Float.valueOf(2.0f)});
            }
            put(Type.TYPE_SUGGEST_KEYWORD_FADE_IN, new Float[]{Float.valueOf(0.32f), Float.valueOf(0.94f), Float.valueOf(0.6f), valueOf4});
        }
    };

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        TYPE_SINE_IN_OUT_33,
        TYPE_SINE_IN_OUT_60,
        TYPE_SINE_IN_OUT_70,
        TYPE_SINE_IN_OUT_80,
        TYPE_SINE_IN_OUT_90,
        TYPE_STORY_END_ANIMATION,
        TYPE_RELATED_NUMBER_FADE_IN,
        TYPE_SUGGEST_KEYWORD_FADE_IN
    }

    public static Interpolator create(Type type) {
        Float[] fArr = VALUE_MAP.get(type);
        if (fArr != null) {
            return PathInterpolatorCompat.create(fArr[0].floatValue(), fArr[1].floatValue(), fArr[2].floatValue(), fArr[3].floatValue());
        }
        Log.e("PathInterpolator", "create failed. invalid type " + type);
        return null;
    }

    public static Interpolator create(float f, float f5, float f8, float f10) {
        return PathInterpolatorCompat.create(f, f5, f8, f10);
    }
}
