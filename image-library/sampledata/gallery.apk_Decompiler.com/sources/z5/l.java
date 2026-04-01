package z5;

import android.graphics.Bitmap;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchMapHeaderView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.StoryPicturesBasePresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.plugins.filebrowser.LogViewFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements MapContainer.OnSnapshotReadyListener, MediaData.OnDataReadListener, Toolbar.OnMenuItemClickListener {
    public final /* synthetic */ Object d;

    public /* synthetic */ l(Object obj) {
        this.d = obj;
    }

    public void onDataReadCompleted(MediaItem mediaItem) {
        ((StoryPicturesBasePresenter) this.d).onHeaderItemLoadingCompleted(mediaItem);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return ((LogViewFragment) this.d).onMenuSelected(menuItem);
    }

    public void onSnapshotReady(Bitmap bitmap) {
        ((SearchMapHeaderView) this.d).snapshotReady(bitmap);
    }
}
