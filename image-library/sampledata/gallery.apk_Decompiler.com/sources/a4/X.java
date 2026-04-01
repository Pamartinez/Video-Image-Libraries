package A4;

import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.StoriesDrawerSlideAnimationManager;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class X implements MediaItemLoader.OnMediaItemLoadingListener, PropertyAnimator.PropertyAnimationListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ X(StoriesDrawerSlideAnimationManager storiesDrawerSlideAnimationManager, int i2, TextUtils.TruncateAt truncateAt, int i7) {
        this.f = storiesDrawerSlideAnimationManager;
        this.d = i2;
        this.g = truncateAt;
        this.e = i7;
    }

    public void onAnimationEnd(View view) {
        int i2 = this.e;
        ((StoriesDrawerSlideAnimationManager) this.f).lambda$prepareOnDemandNoItemDescriptionAnim$5(this.d, (TextUtils.TruncateAt) this.g, i2, view);
    }

    public /* synthetic */ X(BaseListViewAdapter baseListViewAdapter, int i2, int i7, ListViewHolder listViewHolder) {
        this.f = listViewHolder;
        this.g = baseListViewAdapter;
        this.d = i2;
        this.e = i7;
    }
}
