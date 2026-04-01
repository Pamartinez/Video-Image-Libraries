package com.samsung.android.gallery.module.publisher;

import android.database.Cursor;
import android.os.Bundle;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntry;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class W implements Runnable {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ Cursor[] e;
    public final /* synthetic */ long f;
    public final /* synthetic */ Bundle g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ SearchDataPublisher f3059h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Object f3060i;

    public /* synthetic */ W(SearchDataPublisher searchDataPublisher, QueryParams queryParams, Cursor[] cursorArr, long j2, Bundle bundle) {
        this.f3059h = searchDataPublisher;
        this.f3060i = queryParams;
        this.e = cursorArr;
        this.f = j2;
        this.g = bundle;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                long j2 = this.f;
                Bundle bundle = this.g;
                this.f3059h.lambda$publishAllScreenShotFiles$21((QueryParams) this.f3060i, this.e, j2, bundle);
                return;
            default:
                long j3 = this.f;
                Bundle bundle2 = this.g;
                ((SearchDataPublisherV2) this.f3059h).lambda$publishCarouselClusterData$9(this.e, (ClusterResultsEntry) this.f3060i, j3, bundle2);
                return;
        }
    }

    public /* synthetic */ W(SearchDataPublisherV2 searchDataPublisherV2, Cursor[] cursorArr, ClusterResultsEntry clusterResultsEntry, long j2, Bundle bundle) {
        this.f3059h = searchDataPublisherV2;
        this.e = cursorArr;
        this.f3060i = clusterResultsEntry;
        this.f = j2;
        this.g = bundle;
    }
}
