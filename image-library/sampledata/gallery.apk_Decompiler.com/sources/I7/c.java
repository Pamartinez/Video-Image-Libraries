package I7;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.viewer2.crop.ICropView;
import com.samsung.android.gallery.module.media.GifAnimation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements GifAnimation.AnimationFrameUpdateListener, GifAnimation.AnimationFrameStartListener {
    public final /* synthetic */ ICropView d;

    public /* synthetic */ c(ICropView iCropView) {
        this.d = iCropView;
    }

    public void onAnimationFrameStarted() {
        this.d.onAnimationFrameStarted();
    }

    public void onAnimationFrameUpdated(Bitmap bitmap) {
        this.d.onAnimationFrameUpdated(bitmap);
    }
}
