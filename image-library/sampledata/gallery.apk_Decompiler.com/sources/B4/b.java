package B4;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsFooterView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumsFooterView e;

    public /* synthetic */ b(AlbumsFooterView albumsFooterView, int i2) {
        this.d = i2;
        this.e = albumsFooterView;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        AlbumsFooterView albumsFooterView = this.e;
        switch (i2) {
            case 0:
                albumsFooterView.onClickCreateGroup(view);
                return;
            default:
                albumsFooterView.onClickHideAlbum(view);
                return;
        }
    }
}
