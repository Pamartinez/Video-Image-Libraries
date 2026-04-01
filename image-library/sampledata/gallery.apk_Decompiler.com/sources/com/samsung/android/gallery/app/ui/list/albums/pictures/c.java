package com.samsung.android.gallery.app.ui.list.albums.pictures;

import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.WidthAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements WidthAnimator.WidthAnimationCallback, PropertyAnimator.PropertyAnimationListener {
    public final /* synthetic */ int d;

    public /* synthetic */ c(int i2) {
        this.d = i2;
    }

    public boolean isWidthAnimationNeeded(View view, int i2) {
        return AlbumsPaneSlideAnimationManager.lambda$addWidthAnimation$1(view, i2);
    }

    public void onAnimationEnd(View view) {
        switch (this.d) {
            case 1:
                AlbumsPaneSlideAnimationManager.lambda$addWidthAnimation$2(view);
                return;
            default:
                ((TextView) view).getLayoutParams().height = -2;
                return;
        }
    }
}
