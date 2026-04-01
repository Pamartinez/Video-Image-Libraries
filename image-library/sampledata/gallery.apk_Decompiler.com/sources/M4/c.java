package M4;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumPicturesFragment e;

    public /* synthetic */ c(AlbumPicturesFragment albumPicturesFragment, int i2) {
        this.d = i2;
        this.e = albumPicturesFragment;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        AlbumPicturesFragment albumPicturesFragment = this.e;
        switch (i2) {
            case 0:
                albumPicturesFragment.lambda$onEmptyViewVisibilityChanged$3(view);
                return;
            default:
                albumPicturesFragment.lambda$inflateHeaderView$33(view);
                return;
        }
    }
}
