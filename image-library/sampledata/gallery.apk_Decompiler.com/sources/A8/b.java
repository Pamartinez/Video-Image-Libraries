package a8;

import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ImageViewHolder e;

    public /* synthetic */ b(ImageViewHolder imageViewHolder, int i2) {
        this.d = i2;
        this.e = imageViewHolder;
    }

    public final void run() {
        int i2 = this.d;
        ImageViewHolder imageViewHolder = this.e;
        switch (i2) {
            case 0:
                imageViewHolder.lambda$setContentTypeVisibility$6();
                return;
            case 1:
                imageViewHolder.lambda$new$0();
                return;
            default:
                imageViewHolder.lambda$updateContentDurationView$9();
                return;
        }
    }
}
