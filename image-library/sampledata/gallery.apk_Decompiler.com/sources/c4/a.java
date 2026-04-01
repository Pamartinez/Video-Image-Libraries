package C4;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseBlurPinchAnimationManager;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements PropertyAnimator.PropertyAnimationListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumsBaseBlurPinchAnimationManager e;

    public /* synthetic */ a(AlbumsBaseBlurPinchAnimationManager albumsBaseBlurPinchAnimationManager, int i2) {
        this.d = i2;
        this.e = albumsBaseBlurPinchAnimationManager;
    }

    public final void onAnimationEnd(View view) {
        int i2 = this.d;
        AlbumsBaseBlurPinchAnimationManager albumsBaseBlurPinchAnimationManager = this.e;
        switch (i2) {
            case 0:
                albumsBaseBlurPinchAnimationManager.lambda$prepareVirtualAlbumViewAnimation$7(view);
                return;
            case 1:
                albumsBaseBlurPinchAnimationManager.lambda$prepareFolderEmptyViewIconAnimation$3(view);
                return;
            default:
                albumsBaseBlurPinchAnimationManager.lambda$prepareAlbumEmptyViewAnimation$2(view);
                return;
        }
    }
}
