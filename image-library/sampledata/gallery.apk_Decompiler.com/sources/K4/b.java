package K4;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MxAlbumsHeaderView e;

    public /* synthetic */ b(MxAlbumsHeaderView mxAlbumsHeaderView, int i2) {
        this.d = i2;
        this.e = mxAlbumsHeaderView;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        MxAlbumsHeaderView mxAlbumsHeaderView = this.e;
        switch (i2) {
            case 0:
                mxAlbumsHeaderView.lambda$updateCancelButton$2(view);
                return;
            default:
                mxAlbumsHeaderView.lambda$updateDoneButton$1(view);
                return;
        }
    }
}
