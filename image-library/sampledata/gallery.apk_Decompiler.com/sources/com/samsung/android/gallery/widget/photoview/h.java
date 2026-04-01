package com.samsung.android.gallery.widget.photoview;

import java.util.Map;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3215a;
    public final /* synthetic */ int b;

    public /* synthetic */ h(int i2, int i7) {
        this.f3215a = i7;
        this.b = i2;
    }

    public final boolean test(Object obj) {
        int i2 = this.f3215a;
        int i7 = this.b;
        switch (i2) {
            case 0:
                return AliveZoomScheduler.lambda$cleanUpTask$0(i7, (AliveZoomLoadTileTask) obj);
            default:
                return DebugDelegate.lambda$drawDebugInfo$0(i7, (Map.Entry) obj);
        }
    }
}
