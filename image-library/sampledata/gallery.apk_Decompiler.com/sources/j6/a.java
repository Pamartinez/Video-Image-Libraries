package J6;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.slideshow.SimpleExportHandler;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerViewImp;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ int f;
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2378h;

    public /* synthetic */ a(MediaPlayerViewImp mediaPlayerViewImp, int i2, int i7, boolean z) {
        this.d = 3;
        this.f2378h = mediaPlayerViewImp;
        this.f = i2;
        this.g = i7;
        this.e = z;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((SimpleExportHandler) this.f2378h).lambda$startSimpleExport$1(this.e, this.f, this.g);
                return;
            case 1:
                ((NestedScrollView) this.f2378h).lambda$seslSetFadingEdgeEnabled$0(this.e, this.f, this.g);
                return;
            case 2:
                ((RecyclerView) this.f2378h).lambda$seslSetFadingEdgeEnabled$0(this.e, this.f, this.g);
                return;
            default:
                ((MediaPlayerViewImp) this.f2378h).lambda$setSlowMo$44(this.f, this.g, this.e);
                return;
        }
    }

    public /* synthetic */ a(Object obj, boolean z, int i2, int i7, int i8) {
        this.d = i8;
        this.f2378h = obj;
        this.e = z;
        this.f = i2;
        this.g = i7;
    }
}
