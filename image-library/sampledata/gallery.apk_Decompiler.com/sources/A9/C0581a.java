package a9;

import android.os.Bundle;
import com.samsung.android.gallery.module.debugger.PerformanceTracker;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: a9.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0581a implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ PerformanceTracker e;

    public /* synthetic */ C0581a(PerformanceTracker performanceTracker, int i2) {
        this.d = i2;
        this.e = performanceTracker;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        PerformanceTracker performanceTracker = this.e;
        switch (i2) {
            case 0:
                performanceTracker.onThumbnailLoadStart(obj, bundle);
                return;
            case 1:
                performanceTracker.onThumbnailLoadDone(obj, bundle);
                return;
            case 2:
                performanceTracker.onQuickViewDone(obj, bundle);
                return;
            case 3:
                performanceTracker.onMediaDataLoadDone(obj, bundle);
                return;
            default:
                performanceTracker.onMediaDataFullSwapDone(obj, bundle);
                return;
        }
    }
}
