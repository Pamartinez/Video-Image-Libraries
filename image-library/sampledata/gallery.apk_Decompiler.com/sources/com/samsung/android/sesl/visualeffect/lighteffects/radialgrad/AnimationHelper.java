package com.samsung.android.sesl.visualeffect.lighteffects.radialgrad;

import android.graphics.PointF;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Je\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u00112\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u000b2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00070\u000b¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/AnimationHelper;", "", "<init>", "()V", "", "", "colors", "", "fps", "", "wiggleInterval", "", "Landroid/graphics/PointF;", "currentSpotPositions", "currentSpotScales", "Ljava/util/ArrayList;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/SpotConfig;", "Lkotlin/collections/ArrayList;", "buildSpotConfigs", "(Ljava/util/List;FJLjava/util/Map;Ljava/util/Map;)Ljava/util/ArrayList;", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AnimationHelper {
    public static final AnimationHelper INSTANCE = new AnimationHelper();

    private AnimationHelper() {
    }

    public final ArrayList<SpotConfig> buildSpotConfigs(List<Integer> list, float f, long j2, Map<Integer, ? extends PointF> map, Map<Integer, Float> map2) {
        WiggleAnimationConfig wiggleAnimationConfig;
        List<Integer> list2 = list;
        Map<Integer, ? extends PointF> map3 = map;
        Map<Integer, Float> map4 = map2;
        j.e(list2, "colors");
        j.e(map3, "currentSpotPositions");
        j.e(map4, "currentSpotScales");
        ArrayList<SpotConfig> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < 4; i2++) {
            int intValue = list2.get(i2).intValue();
            PointF pointF = (PointF) map3.get(Integer.valueOf(i2));
            float f5 = 0.0f;
            if (pointF == null) {
                pointF = new PointF(0.0f, 0.0f);
            }
            PointF pointF2 = pointF;
            Float f8 = map4.get(Integer.valueOf(i2));
            if (f8 != null) {
                f5 = f8.floatValue();
            }
            float f10 = f5;
            if (i2 == 0 || i2 == 3) {
                wiggleAnimationConfig = new WiggleAnimationConfig(j2, Constants.INSTANCE.getCUBIC_BEZIER_INTERPOLATOR(), Float.valueOf(0.35f), Float.valueOf(0.5f), (Integer) null, f, 16, (e) null);
            } else {
                wiggleAnimationConfig = null;
            }
            arrayList.add(new SpotConfig(false, intValue, pointF2, f10, wiggleAnimationConfig, 1, (e) null));
        }
        return arrayList;
    }
}
