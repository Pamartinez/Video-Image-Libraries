package com.samsung.android.gallery.module.map.transition;

import com.samsung.android.gallery.module.map.clustering.ICluster;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import com.samsung.android.gallery.module.map.transition.abstraction.MarkerWithPosition;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3038a;
    public final /* synthetic */ BaseMarkerManager b;

    public /* synthetic */ a(BaseMarkerManager baseMarkerManager, int i2) {
        this.f3038a = i2;
        this.b = baseMarkerManager;
    }

    public final boolean test(Object obj) {
        int i2 = this.f3038a;
        BaseMarkerManager baseMarkerManager = this.b;
        switch (i2) {
            case 0:
                return baseMarkerManager.lambda$getMarkerIncludeFocusedId$7((ICluster) obj);
            case 1:
                return baseMarkerManager.lambda$getMarkerIncludeFocusedId$6((MapItem) obj);
            case 2:
                return baseMarkerManager.lambda$onMarkerClicked$3((MarkerWithPosition) obj);
            case 3:
                return baseMarkerManager.lambda$updateMarkerCollection$1((MarkerWithPosition) obj);
            default:
                return baseMarkerManager.lambda$updateMarkerCollection$0((MapItem) obj);
        }
    }
}
