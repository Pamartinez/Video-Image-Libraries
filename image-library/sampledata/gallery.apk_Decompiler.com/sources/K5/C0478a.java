package k5;

import com.samsung.android.gallery.app.ui.list.search.category.document.SearchDocumentItemAdapter;
import com.samsung.android.gallery.module.data.MediaItem;

/* renamed from: k5.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0478a implements Runnable {
    public final /* synthetic */ int d = 0;
    public final /* synthetic */ SearchDocumentItemAdapter e;
    public final /* synthetic */ String f;
    public final /* synthetic */ MediaItem g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f2653h;

    public /* synthetic */ C0478a(SearchDocumentItemAdapter searchDocumentItemAdapter, MediaItem mediaItem, int i2, String str) {
        this.e = searchDocumentItemAdapter;
        this.g = mediaItem;
        this.f2653h = i2;
        this.f = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$getDividerMediaItem$3(this.g, this.f2653h, this.f);
                return;
            default:
                this.e.lambda$getDividerMediaItem$2(this.f, this.g, this.f2653h);
                return;
        }
    }

    public /* synthetic */ C0478a(SearchDocumentItemAdapter searchDocumentItemAdapter, String str, MediaItem mediaItem, int i2) {
        this.e = searchDocumentItemAdapter;
        this.f = str;
        this.g = mediaItem;
        this.f2653h = i2;
    }
}
