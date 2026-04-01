package com.samsung.o3dp.lib3dphotography.animation.transition;

import androidx.core.view.animation.PathInterpolatorCompat;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PathTransition extends Transition {
    private static final String DEFAULT_VALUE = "SINE_IN_OUT_33";
    public static String SINE_IN_OUT_33 = "SINE_IN_OUT_33";
    public static String SINE_IN_OUT_60 = "SINE_IN_OUT_60";
    public static String SINE_IN_OUT_70 = "SINE_IN_OUT_70";
    public static String SINE_IN_OUT_80 = "SINE_IN_OUT_80";
    public static String SINE_IN_OUT_90 = "SINE_IN_OUT_90";
    private static final HashMap<String, Float[]> VALUE_MAP = new HashMap<String, Float[]>() {
        {
            String str = PathTransition.SINE_IN_OUT_33;
            Float valueOf = Float.valueOf(0.33f);
            Float valueOf2 = Float.valueOf(0.0f);
            Float valueOf3 = Float.valueOf(0.67f);
            Float valueOf4 = Float.valueOf(1.0f);
            put(str, new Float[]{valueOf, valueOf2, valueOf3, valueOf4});
            put(PathTransition.SINE_IN_OUT_60, new Float[]{valueOf, valueOf2, Float.valueOf(0.4f), valueOf4});
            put(PathTransition.SINE_IN_OUT_70, new Float[]{valueOf, valueOf2, Float.valueOf(0.3f), valueOf4});
            put(PathTransition.SINE_IN_OUT_80, new Float[]{valueOf, valueOf2, Float.valueOf(0.2f), valueOf4});
            put(PathTransition.SINE_IN_OUT_90, new Float[]{valueOf, valueOf2, Float.valueOf(0.1f), valueOf4});
        }
    };
    private Float[] mValue;

    public PathTransition() {
        this.mValue = VALUE_MAP.get(DEFAULT_VALUE);
    }

    public void setValue(Object obj) {
        super.setValue(obj);
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            if (jSONArray.length() == 2) {
                try {
                    JSONArray jSONArray2 = jSONArray.getJSONArray(0);
                    JSONArray jSONArray3 = jSONArray.getJSONArray(1);
                    if (jSONArray2.length() == 2 && jSONArray3.length() == 2) {
                        this.mValue[0] = Float.valueOf((float) jSONArray2.getDouble(0));
                        this.mValue[1] = Float.valueOf((float) jSONArray2.getDouble(1));
                        this.mValue[2] = Float.valueOf((float) jSONArray3.getDouble(0));
                        this.mValue[3] = Float.valueOf((float) jSONArray3.getDouble(1));
                        return;
                    }
                    throw new RuntimeException("PathTransition data should be [[x1,y1],[x2,y2]] values");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new RuntimeException("PathTransition data should be [[x1,y1],[x2,y2]] values");
            }
        } else if (obj instanceof String) {
            HashMap<String, Float[]> hashMap = VALUE_MAP;
            this.mValue = hashMap.getOrDefault((String) obj, hashMap.get(DEFAULT_VALUE));
        }
    }

    public float transitionTime(float f) {
        return PathInterpolatorCompat.create(this.mValue[0].floatValue(), this.mValue[1].floatValue(), this.mValue[2].floatValue(), this.mValue[3].floatValue()).getInterpolation(f);
    }

    public PathTransition(String str) {
        HashMap<String, Float[]> hashMap = VALUE_MAP;
        this.mValue = hashMap.getOrDefault(str, hashMap.get(DEFAULT_VALUE));
    }
}
