package z5;

import android.graphics.Bitmap;
import android.view.MenuItem;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeader2View;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import k2.q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements q, ThumbnailLoadedListener {
    public final /* synthetic */ SearchCreatureHeader2View d;

    public /* synthetic */ d(SearchCreatureHeader2View searchCreatureHeader2View) {
        this.d = searchCreatureHeader2View;
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        this.d.bindMainImage(bitmap, uniqueKey, thumbKind);
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return this.d.lambda$initContactRecommendBottomNavi$13(menuItem);
    }
}
