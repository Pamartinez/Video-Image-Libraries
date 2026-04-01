package B6;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.samsung.android.gallery.widget.utils.DebugSmartCropRectInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ImageView e;
    public final /* synthetic */ Bitmap f;

    public /* synthetic */ c(ImageView imageView, Bitmap bitmap, int i2) {
        this.d = i2;
        this.e = imageView;
        this.f = bitmap;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.setImageBitmap(this.f);
                return;
            case 1:
                DebugSmartCropRectInfo.lambda$reloadViewHolderThumbnail$5(this.e, this.f);
                return;
            default:
                this.e.setImageBitmap(this.f);
                return;
        }
    }
}
