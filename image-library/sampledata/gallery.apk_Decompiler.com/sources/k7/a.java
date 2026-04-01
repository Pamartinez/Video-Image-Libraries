package K7;

import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.samsung.android.gallery.app.ui.container.tablet.ListContainerFragment;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.delegate.VuDelegateComposite;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.local.table.LocalTable;
import com.samsung.android.gallery.module.remotegallery.RemoteServer;
import com.samsung.android.gallery.plugins.filebrowser.FileListFragment;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;

    public /* synthetic */ a(int i2, int i7, ArrayList arrayList) {
        this.d = 0;
        this.e = i2;
        this.f = i7;
        this.g = arrayList;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                VuDelegateComposite.lambda$onMediaDataChangedInternal$3(this.e, this.f, (ArrayList) this.g, (AbsVuDelegate) obj);
                return;
            case 1:
                ((RemoteServer) this.g).lambda$getFileList$2(this.e, this.f, (QueryParams) obj);
                return;
            case 2:
                ListContainerFragment.lambda$updateToolbarNavigation$9(this.e, (View.OnClickListener) this.g, this.f, (GalleryToolbar) obj);
                return;
            case 3:
                ((LocalTable) obj).upgradeTablesOnTransaction((SQLiteDatabase) this.g, this.e, this.f);
                return;
            default:
                ((FileListFragment) this.g).lambda$computePositionMap$5(this.e, this.f, (LinearLayoutManager) obj);
                return;
        }
    }

    public /* synthetic */ a(int i2, View.OnClickListener onClickListener, int i7) {
        this.d = 2;
        this.e = i2;
        this.g = onClickListener;
        this.f = i7;
    }

    public /* synthetic */ a(Object obj, int i2, int i7, int i8) {
        this.d = i8;
        this.g = obj;
        this.e = i2;
        this.f = i7;
    }
}
