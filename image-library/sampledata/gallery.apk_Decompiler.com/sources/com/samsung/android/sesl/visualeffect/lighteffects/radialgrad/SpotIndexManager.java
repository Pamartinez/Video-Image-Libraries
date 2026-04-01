package com.samsung.android.sesl.visualeffect.lighteffects.radialgrad;

import c0.C0086a;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.i;
import ne.C1195m;
import ne.C1196n;
import ne.z;
import oe.C1217f;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\t\"\u0004\b\u0000\u0010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0004\b\u000b\u0010\fJ3\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\r\"\u0004\b\u0000\u0010\b2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\r¢\u0006\u0004\b\u000f\u0010\u0010R \u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\r8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R \u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\r8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0012R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\t8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/SpotIndexManager;", "", "<init>", "()V", "", "renderIndex", "renderToUserIndex", "(I)I", "T", "", "userOrderData", "convertToRenderOrder", "(Ljava/util/List;)Ljava/util/List;", "", "userOrderMap", "convertToRenderOrderMap", "(Ljava/util/Map;)Ljava/util/Map;", "USER_TO_RENDER_INDEX", "Ljava/util/Map;", "RENDER_TO_USER_INDEX", "RENDER_ORDER", "Ljava/util/List;", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SpotIndexManager {
    public static final SpotIndexManager INSTANCE = new SpotIndexManager();
    private static final List<Integer> RENDER_ORDER = C1195m.q0(0, 2, 3, 1);
    private static final Map<Integer, Integer> RENDER_TO_USER_INDEX = z.b0(new i(0, 0), new i(1, 2), new i(2, 3), new i(3, 1));
    private static final Map<Integer, Integer> USER_TO_RENDER_INDEX = z.b0(new i(0, 0), new i(1, 3), new i(2, 1), new i(3, 2));

    private SpotIndexManager() {
    }

    public final <T> List<T> convertToRenderOrder(List<? extends T> list) {
        j.e(list, "userOrderData");
        if (list.size() == 4) {
            Iterable<Number> iterable = RENDER_ORDER;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (Number intValue : iterable) {
                arrayList.add(list.get(intValue.intValue()));
            }
            return arrayList;
        }
        throw new IllegalArgumentException("Data list must have exactly 4 elements");
    }

    public final <T> Map<Integer, T> convertToRenderOrderMap(Map<Integer, ? extends T> map) {
        j.e(map, "userOrderMap");
        C1217f fVar = new C1217f();
        for (int i2 = 0; i2 < 4; i2++) {
            Object obj = map.get(Integer.valueOf(INSTANCE.renderToUserIndex(i2)));
            if (obj != null) {
                fVar.put(Integer.valueOf(i2), obj);
            }
        }
        return fVar.b();
    }

    public final int renderToUserIndex(int i2) {
        if (i2 < 0 || i2 >= 4) {
            throw new IllegalArgumentException(C0086a.i(i2, "Render index must be between 0 and 3, but was ").toString());
        }
        Integer num = RENDER_TO_USER_INDEX.get(Integer.valueOf(i2));
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }
}
