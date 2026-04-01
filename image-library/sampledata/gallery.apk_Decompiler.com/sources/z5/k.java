package z5;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeaderView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SearchCreatureHeaderView e;
    public final /* synthetic */ Bitmap f;

    public /* synthetic */ k(SearchCreatureHeaderView searchCreatureHeaderView, Bitmap bitmap, int i2) {
        this.d = i2;
        this.e = searchCreatureHeaderView;
        this.f = bitmap;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$initContactRecommendThumbnail$2(this.f);
                return;
            default:
                this.e.lambda$bindMainImage$15(this.f);
                return;
        }
    }
}
