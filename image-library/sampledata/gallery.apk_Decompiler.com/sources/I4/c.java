package I4;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsPinchAnimationManager;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements PropertyAnimator.PropertyAnimationListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MxAlbumsPinchAnimationManager e;

    public /* synthetic */ c(MxAlbumsPinchAnimationManager mxAlbumsPinchAnimationManager, int i2) {
        this.d = i2;
        this.e = mxAlbumsPinchAnimationManager;
    }

    public final void onAnimationEnd(View view) {
        int i2 = this.d;
        MxAlbumsPinchAnimationManager mxAlbumsPinchAnimationManager = this.e;
        switch (i2) {
            case 0:
                mxAlbumsPinchAnimationManager.resetTranslate(view);
                return;
            case 1:
                mxAlbumsPinchAnimationManager.resetTranslate(view);
                return;
            default:
                mxAlbumsPinchAnimationManager.resetTranslate(view);
                return;
        }
    }
}
