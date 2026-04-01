package A4;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailRequestHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e0 implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ThumbnailRequestHolder e;
    public final /* synthetic */ Bitmap f;

    public /* synthetic */ e0(ThumbnailRequestHolder thumbnailRequestHolder, Bitmap bitmap, int i2) {
        this.d = i2;
        this.e = thumbnailRequestHolder;
        this.f = bitmap;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$bindImageIfAvailable$0(this.f);
                return;
            default:
                this.e.getViewHolder().bindImage(this.f);
                return;
        }
    }
}
