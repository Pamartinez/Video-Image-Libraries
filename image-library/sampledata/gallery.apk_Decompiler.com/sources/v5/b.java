package v5;

import android.graphics.Bitmap;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.search.pictures.OnHeaderClickListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements OnHeaderClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ISearchPicturesView f2716a;

    public /* synthetic */ b(ISearchPicturesView iSearchPicturesView) {
        this.f2716a = iSearchPicturesView;
    }

    public final void onHeaderClicked(View view, int i2, MediaItem mediaItem, Bitmap bitmap) {
        this.f2716a.onHeaderItemClicked(view, i2, mediaItem, bitmap);
    }
}
