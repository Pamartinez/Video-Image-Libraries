package com.samsung.android.gallery.module.map.transition;

import com.samsung.android.gallery.module.map.clustering.ICluster;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3040a;
    public final /* synthetic */ BaseMarkerManager b;

    public /* synthetic */ c(BaseMarkerManager baseMarkerManager, int i2) {
        this.f3040a = i2;
        this.b = baseMarkerManager;
    }

    public final Object apply(Object obj) {
        int i2 = this.f3040a;
        BaseMarkerManager baseMarkerManager = this.b;
        ICluster iCluster = (ICluster) obj;
        switch (i2) {
            case 0:
                return baseMarkerManager.lambda$getMarkerIncludeFocusedId$9(iCluster);
            default:
                return baseMarkerManager.lambda$getMarkerIncludeFocusedId$13(iCluster);
        }
    }
}
