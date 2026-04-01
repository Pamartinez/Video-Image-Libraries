package z5;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchScreenShotHeaderAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchScreenShotHeaderViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements View.OnClickListener {
    public final /* synthetic */ SearchScreenShotHeaderAdapter d;
    public final /* synthetic */ int e;
    public final /* synthetic */ SearchScreenShotHeaderViewHolder f;
    public final /* synthetic */ MediaItem g;

    public /* synthetic */ m(int i2, SearchScreenShotHeaderAdapter searchScreenShotHeaderAdapter, SearchScreenShotHeaderViewHolder searchScreenShotHeaderViewHolder, MediaItem mediaItem) {
        this.d = searchScreenShotHeaderAdapter;
        this.e = i2;
        this.f = searchScreenShotHeaderViewHolder;
        this.g = mediaItem;
    }

    public final void onClick(View view) {
        this.d.lambda$onBindViewHolder$0(this.e, this.f, this.g, view);
    }
}
