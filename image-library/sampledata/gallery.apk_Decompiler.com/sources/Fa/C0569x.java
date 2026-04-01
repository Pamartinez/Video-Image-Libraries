package Fa;

import com.samsung.android.gallery.app.ui.list.reorder.ReorderDragManager;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.film.FilmStripDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import com.samsung.android.gallery.widget.videoview.mediaplayer.CaptureDelegate;
import java.util.function.Consumer;

/* renamed from: Fa.x  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0569x implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2816h;

    public /* synthetic */ C0569x(LabsDevManageFragment labsDevManageFragment, boolean z, int i2, String str) {
        this.d = 0;
        this.g = labsDevManageFragment;
        this.e = z;
        this.f = i2;
        this.f2816h = str;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((LabsDevManageFragment) this.g).lambda$loadCacheInfo$64(this.e, this.f, (String) this.f2816h);
                return;
            case 1:
                int i2 = this.f;
                ((ReorderDragManager) this.g).lambda$createFolder$5(this.e, (MediaItem[]) this.f2816h, i2);
                return;
            case 2:
                ((CaptureDelegate) this.g).lambda$captureFrame$0((Consumer) this.f2816h, this.f, this.e);
                return;
            default:
                int i7 = this.f;
                ((FilmStripDelegate) this.g).lambda$confirmFilmStrip$5(this.e, (FilmStripViewHolder) this.f2816h, i7);
                return;
        }
    }

    public /* synthetic */ C0569x(Object obj, boolean z, Object obj2, int i2, int i7) {
        this.d = i7;
        this.g = obj;
        this.e = z;
        this.f2816h = obj2;
        this.f = i2;
    }

    public /* synthetic */ C0569x(Consumer consumer, int i2, boolean z, CaptureDelegate captureDelegate) {
        this.d = 2;
        this.g = captureDelegate;
        this.f2816h = consumer;
        this.f = i2;
        this.e = z;
    }
}
