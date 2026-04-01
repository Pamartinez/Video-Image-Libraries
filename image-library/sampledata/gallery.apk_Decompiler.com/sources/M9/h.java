package M9;

import android.os.Bundle;
import com.samsung.android.gallery.module.publisher.MapDataPublisher;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MapDataPublisher e;

    public /* synthetic */ h(MapDataPublisher mapDataPublisher, int i2) {
        this.d = i2;
        this.e = mapDataPublisher;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        MapDataPublisher mapDataPublisher = this.e;
        switch (i2) {
            case 0:
                mapDataPublisher.publishMapData(obj, bundle);
                return;
            case 1:
                mapDataPublisher.publishFilteredMapData(obj, bundle);
                return;
            default:
                mapDataPublisher.publishMapClusterData2(obj, bundle);
                return;
        }
    }
}
