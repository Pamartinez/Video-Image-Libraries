package a8;

import android.os.Handler;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ImageViewHolder e;

    public /* synthetic */ a(ImageViewHolder imageViewHolder, int i2) {
        this.d = i2;
        this.e = imageViewHolder;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        ImageViewHolder imageViewHolder = this.e;
        switch (i2) {
            case 0:
                imageViewHolder.lambda$bindView$1(obj);
                return;
            case 1:
                imageViewHolder.lambda$bindView$2(obj);
                return;
            default:
                imageViewHolder.lambda$onTouch$3((Handler) obj);
                return;
        }
    }
}
