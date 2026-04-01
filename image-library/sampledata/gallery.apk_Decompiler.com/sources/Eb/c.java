package Eb;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.media.GifAnimation;
import com.samsung.android.gallery.widget.hoverview.HoverPreviewViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements GifAnimation.AnimationFrameUpdateListener, GifAnimation.AnimationFrameStartListener {
    public final /* synthetic */ HoverPreviewViewHolder d;

    public /* synthetic */ c(HoverPreviewViewHolder hoverPreviewViewHolder) {
        this.d = hoverPreviewViewHolder;
    }

    public void onAnimationFrameStarted() {
        this.d.onAnimationFrameStarted();
    }

    public void onAnimationFrameUpdated(Bitmap bitmap) {
        this.d.onAnimationFrameUpdated(bitmap);
    }
}
