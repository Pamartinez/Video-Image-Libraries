package g5;

import com.samsung.android.gallery.app.ui.list.reorder.ReorderItemController;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ ReorderItemController d;
    public final /* synthetic */ float e;
    public final /* synthetic */ float f;
    public final /* synthetic */ float g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ float f2638h;

    public /* synthetic */ b(ReorderItemController reorderItemController, float f5, float f8, float f10, float f11) {
        this.d = reorderItemController;
        this.e = f5;
        this.f = f8;
        this.g = f10;
        this.f2638h = f11;
    }

    public final void run() {
        this.d.lambda$startAlbumDrag$0(this.e, this.f, this.g, this.f2638h);
    }
}
