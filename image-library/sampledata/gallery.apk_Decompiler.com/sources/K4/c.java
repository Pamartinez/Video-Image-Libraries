package K4;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderView;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MxAlbumsHeaderView e;
    public final /* synthetic */ MediaItem f;

    public /* synthetic */ c(MxAlbumsHeaderView mxAlbumsHeaderView, MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = mxAlbumsHeaderView;
        this.f = mediaItem;
    }

    public final void onClick(View view) {
        switch (this.d) {
            case 0:
                this.e.lambda$updateInvitation$3(this.f, view);
                return;
            default:
                this.e.lambda$updateInvitation$4(this.f, view);
                return;
        }
    }
}
