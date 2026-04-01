package com.samsung.android.gallery.app.ui.list.albums.pictures;

import android.view.View;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements PropertyAnimator.PropertyAnimationListener {
    public final /* synthetic */ AlbumsPaneSlideAnimationManager d;

    public /* synthetic */ b(AlbumsPaneSlideAnimationManager albumsPaneSlideAnimationManager) {
        this.d = albumsPaneSlideAnimationManager;
    }

    public final void onAnimationEnd(View view) {
        this.d.resetTranslate(view);
    }
}
