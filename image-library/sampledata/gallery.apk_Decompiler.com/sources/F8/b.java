package F8;

import com.samsung.android.gallery.module.bgm.updater.BgmUpdateListener;
import com.samsung.android.gallery.module.bgm.updater.DownloadListener;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ String f;
    public final /* synthetic */ ArrayList g;

    public /* synthetic */ b(boolean z, String str, ArrayList arrayList, int i2) {
        this.d = i2;
        this.e = z;
        this.f = str;
        this.g = arrayList;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((BgmUpdateListener) obj).onDownloaded(this.e, this.f, this.g);
                return;
            default:
                ((DownloadListener) obj).onDownloaded(this.e, this.f, this.g);
                return;
        }
    }
}
