package com.samsung.android.gallery.app.ui.list.pictures.adapter;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ Cluster e;

    public /* synthetic */ a(Cluster cluster, int i2) {
        this.d = i2;
        this.e = cluster;
    }

    public final Object get() {
        int i2 = this.d;
        Cluster cluster = this.e;
        switch (i2) {
            case 0:
                return ((RealRatioCluster) cluster).lambda$new$0();
            default:
                return ((RealRatioNoDividerCluster) cluster).lambda$new$0();
        }
    }
}
