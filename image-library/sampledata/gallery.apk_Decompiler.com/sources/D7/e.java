package D7;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.GifImageLoader;
import com.samsung.android.gallery.module.media.GifAnimation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements GifAnimation.AnimationFrameStartListener, GifAnimation.AnimationFrameUpdateListener {
    public final /* synthetic */ GifImageLoader d;

    public /* synthetic */ e(GifImageLoader gifImageLoader) {
        this.d = gifImageLoader;
    }

    public void onAnimationFrameStarted() {
        this.d.onAnimationFrameStart();
    }

    public void onAnimationFrameUpdated(Bitmap bitmap) {
        this.d.onAnimationFrameUpdated(bitmap);
    }
}
