package z5;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeader2View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchCreatureHeader2View e;
    public final /* synthetic */ Bitmap f;

    public /* synthetic */ e(SearchCreatureHeader2View searchCreatureHeader2View, Bitmap bitmap, int i2) {
        this.d = i2;
        this.e = searchCreatureHeader2View;
        this.f = bitmap;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$initContactRecommendThumbnail$6(this.f);
                return;
            default:
                this.e.lambda$bindMainImage$11(this.f);
                return;
        }
    }
}
