package com.samsung.android.gallery.module.map.transition;

import com.samsung.android.gallery.module.map.clustering.ICluster;
import com.samsung.android.gallery.module.map.transition.abstraction.MarkerWithPosition;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3039a;
    public final /* synthetic */ ICluster b;

    public /* synthetic */ b(ICluster iCluster, int i2) {
        this.f3039a = i2;
        this.b = iCluster;
    }

    public final boolean test(Object obj) {
        int i2 = this.f3039a;
        ICluster iCluster = this.b;
        MarkerWithPosition markerWithPosition = (MarkerWithPosition) obj;
        switch (i2) {
            case 0:
                return iCluster.equals(markerWithPosition.getCluster());
            default:
                return iCluster.equals(markerWithPosition.getCluster());
        }
    }
}
