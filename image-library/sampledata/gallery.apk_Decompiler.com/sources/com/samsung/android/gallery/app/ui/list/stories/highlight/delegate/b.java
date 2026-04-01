package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.GifPlayDelegate;
import com.samsung.android.gallery.module.media.GifAnimation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements GifAnimation.AnimationFrameUpdateListener {
    public final /* synthetic */ GifPlayDelegate d;
    public final /* synthetic */ GifPlayDelegate.PlayerHolder e;

    public /* synthetic */ b(GifPlayDelegate gifPlayDelegate, GifPlayDelegate.PlayerHolder playerHolder) {
        this.d = gifPlayDelegate;
        this.e = playerHolder;
    }

    public final void onAnimationFrameUpdated(Bitmap bitmap) {
        this.d.lambda$playInternal$8(this.e, bitmap);
    }
}
