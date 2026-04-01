package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntry;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class Z implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchDataPublisherV2 e;
    public final /* synthetic */ Cursor[] f;
    public final /* synthetic */ ClusterResultsEntry g;

    public /* synthetic */ Z(SearchDataPublisherV2 searchDataPublisherV2, Cursor[] cursorArr, ClusterResultsEntry clusterResultsEntry, int i2) {
        this.d = i2;
        this.e = searchDataPublisherV2;
        this.f = cursorArr;
        this.g = clusterResultsEntry;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$publishCarouselClusterData$2(this.f, this.g);
                return;
            case 1:
                this.e.lambda$publishCarouselClusterData$3(this.f, this.g);
                return;
            case 2:
                this.e.lambda$publishCarouselClusterData$4(this.f, this.g);
                return;
            case 3:
                this.e.lambda$publishCarouselClusterData$5(this.f, this.g);
                return;
            case 4:
                this.e.lambda$publishCarouselClusterData$6(this.f, this.g);
                return;
            default:
                this.e.lambda$publishCarouselClusterData$7(this.f, this.g);
                return;
        }
    }
}
