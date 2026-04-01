package com.samsung.o3dp.lib3dphotography;

import com.samsung.o3dp.lib3dphotography.ObjectDetector;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4212a;
    public final /* synthetic */ Serializable b;

    public /* synthetic */ l(Serializable serializable, int i2) {
        this.f4212a = i2;
        this.b = serializable;
    }

    public final boolean test(Object obj) {
        int i2 = this.f4212a;
        Serializable serializable = this.b;
        switch (i2) {
            case 0:
                return ObjectDetector.lambda$searchWithLabels$1((ArrayList) serializable, (ObjectDetector.ObjectInfo) obj);
            default:
                return ((ObjectDetector.ObjectInfo) obj).categoryName.equals(((ObjectDetector.Category) serializable).categoryName);
        }
    }
}
