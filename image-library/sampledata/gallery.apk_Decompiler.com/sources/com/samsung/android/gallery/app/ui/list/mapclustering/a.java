package com.samsung.android.gallery.app.ui.list.mapclustering;

import com.samsung.android.gallery.app.ui.list.mapclustering.ClusteringMapFragmentV2;
import com.samsung.android.gallery.module.dataset.MediaData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaData.SimpleDataChangeListener e;

    public /* synthetic */ a(MediaData.SimpleDataChangeListener simpleDataChangeListener, int i2) {
        this.d = i2;
        this.e = simpleDataChangeListener;
    }

    public final void run() {
        int i2 = this.d;
        MediaData.SimpleDataChangeListener simpleDataChangeListener = this.e;
        switch (i2) {
            case 0:
                ((ClusteringMapFragmentV2.AnonymousClass2) simpleDataChangeListener).lambda$onDataChanged$0();
                return;
            default:
                ((ClusteringMapFragmentV2.AnonymousClass3) simpleDataChangeListener).lambda$onDataChanged$0();
                return;
        }
    }
}
