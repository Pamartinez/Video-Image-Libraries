package M4;

import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesViewAdapter;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.listview.selection.SelectionManager;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumPicturesViewAdapter e;

    public /* synthetic */ q(AlbumPicturesViewAdapter albumPicturesViewAdapter, int i2) {
        this.d = i2;
        this.e = albumPicturesViewAdapter;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        AlbumPicturesViewAdapter albumPicturesViewAdapter = this.e;
        switch (i2) {
            case 0:
                albumPicturesViewAdapter.lambda$unSelectAll$1((Blackboard) obj);
                return;
            default:
                albumPicturesViewAdapter.lambda$unSelectAll$2((SelectionManager) obj);
                return;
        }
    }
}
