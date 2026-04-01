package y7;

import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeader2View;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Object f;

    public /* synthetic */ e(Object obj, boolean z, int i2) {
        this.d = i2;
        this.f = obj;
        this.e = z;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((TextExtractionHandler) this.f).lambda$detectDone$6(this.e);
                return;
            default:
                ((SearchCreatureHeader2View) this.f).lambda$setToolbarAsFloating$14(this.e);
                return;
        }
    }
}
