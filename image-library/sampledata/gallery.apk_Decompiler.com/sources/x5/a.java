package x5;

import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterCategoryItemAdapter;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ ClusterCategoryItemAdapter e;
    public final /* synthetic */ String f;
    public final /* synthetic */ MediaItem g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f2742h;

    public /* synthetic */ a(ClusterCategoryItemAdapter clusterCategoryItemAdapter, MediaItem mediaItem, int i2, String str) {
        this.e = clusterCategoryItemAdapter;
        this.g = mediaItem;
        this.f2742h = i2;
        this.f = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$getDividerMediaItem$3(this.g, this.f2742h, this.f);
                return;
            default:
                this.e.lambda$getDividerMediaItem$2(this.f, this.g, this.f2742h);
                return;
        }
    }

    public /* synthetic */ a(ClusterCategoryItemAdapter clusterCategoryItemAdapter, String str, MediaItem mediaItem, int i2) {
        this.e = clusterCategoryItemAdapter;
        this.f = str;
        this.g = mediaItem;
        this.f2742h = i2;
    }
}
