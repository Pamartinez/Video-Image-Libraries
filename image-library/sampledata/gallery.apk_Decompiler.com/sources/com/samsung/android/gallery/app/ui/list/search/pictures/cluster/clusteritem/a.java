package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResultFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ClusterResultFactory.SearchResultClusterConstructor {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2541a;

    public /* synthetic */ a(int i2) {
        this.f2541a = i2;
    }

    public final ClusterResult create(View view, EventContext eventContext) {
        switch (this.f2541a) {
            case 0:
                return new TopResultsClusterResult(view, eventContext);
            case 1:
                return new CarouselClusterResult(view, eventContext);
            case 2:
                return new AlbumsClusterResult(view, eventContext);
            case 3:
                return new LocationsClusterResult(view, eventContext);
            case 4:
                return new PeoplesClusterResult(view, eventContext);
            default:
                return new OCRsClusterResult(view, eventContext);
        }
    }
}
