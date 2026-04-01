package C4;

import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.WidthAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements PropertyAnimator.PropertyAnimationListener, WidthAnimator.WidthAnimationCallback {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumsBasePinchAnimationManager e;

    public /* synthetic */ g(AlbumsBasePinchAnimationManager albumsBasePinchAnimationManager, int i2) {
        this.d = i2;
        this.e = albumsBasePinchAnimationManager;
    }

    public boolean isWidthAnimationNeeded(View view, int i2) {
        return this.e.isWidthAnimationNeeded((TextView) view, i2);
    }

    public void onAnimationEnd(View view) {
        int i2 = this.d;
        AlbumsBasePinchAnimationManager albumsBasePinchAnimationManager = this.e;
        switch (i2) {
            case 0:
                albumsBasePinchAnimationManager.lambda$prepareFolderChildAnimation$9(view);
                return;
            case 1:
                albumsBasePinchAnimationManager.lambda$prepareFolderChildAnimation$8(view);
                return;
            case 2:
                albumsBasePinchAnimationManager.lambda$prepareFolderBackgroundAnimation$10(view);
                return;
            case 3:
                albumsBasePinchAnimationManager.resetTranslate(view);
                return;
            default:
                albumsBasePinchAnimationManager.lambda$prepareThumbnailAnimation$3(view);
                return;
        }
    }
}
