package Z8;

import com.samsung.android.gallery.module.dataset.tables.DefaultTable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ DefaultTable d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ long f;

    public /* synthetic */ a(DefaultTable defaultTable, boolean z, long j2) {
        this.d = defaultTable;
        this.e = z;
        this.f = j2;
    }

    public final void run() {
        this.d.lambda$readDataCursor$1(this.e, this.f);
    }
}
