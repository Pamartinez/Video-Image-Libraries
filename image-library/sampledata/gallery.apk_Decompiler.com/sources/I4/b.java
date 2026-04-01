package I4;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.HierarchicalViewAdapter;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.details.items.DetailsItem;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.SlideshowViewerHolder;
import com.samsung.android.gallery.module.bgm.provider.IProviderCallback;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.widget.livemotion.abstraction.ViewPagerCallback;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransform;
import com.samsung.android.gallery.widget.pinch.IPinchAdapter;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ b(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((MxAlbumsViewAdapter) obj).updateSharingBadge();
                return;
            case 1:
                ((IPinchAdapter) obj).updateExtraViewHolderMargin();
                return;
            case 2:
                ((IPinchAdapter) obj).notifyItemChanged(0);
                return;
            case 3:
                ((MxAlbumsViewAdapter) obj).notifyItemRangeChanged("header");
                return;
            case 4:
                ((HierarchicalViewAdapter) obj).notifyDataSetChanged();
                return;
            case 5:
                ((Consumer) obj).accept(Boolean.TRUE);
                return;
            case 6:
                ((Blackboard) obj).postEvent(EventMessage.obtain(3013));
                return;
            case 7:
                ((Blackboard) obj).postEvent(EventMessage.obtain(3012));
                return;
            case 8:
                ((FragmentActivity) obj).setResult(0);
                return;
            case 9:
                ((IProviderCallback) obj).onFailed(-1);
                return;
            case 10:
                ((IProviderCallback) obj).onDisconnected();
                return;
            case 11:
                ((IProviderCallback) obj).onConnected();
                return;
            case 12:
                ((IPinchAdapter) obj).stopPreview(false);
                return;
            case 13:
                ((SlideshowViewerHolder) obj).invoke(ViewerAction.TRANSITION_PREPARE, new Object[0]);
                return;
            case 14:
                ((DetailsItem) obj).updateLayout();
                return;
            case 15:
                ((DetailsItem) obj).onRecycled();
                return;
            case 16:
                ((DetailsItem) obj).onResume();
                return;
            case 17:
                ((DetailsItem) obj).onPause();
                return;
            case 18:
                ((Consumer) obj).accept(-1);
                return;
            case 19:
                ((PageTransform) obj).onCancel();
                return;
            case 20:
                ((ViewPagerCallback) obj).onZoomState(false);
                return;
            case 21:
                ((ViewPagerCallback) obj).onSlideShowDone();
                return;
            case 22:
                ((ViewPagerCallback) obj).onZoomState(false);
                return;
            case 23:
                ((RecyclerView) obj).setNestedScrollingEnabled(false);
                return;
            case 24:
                ((ViewPagerCallback) obj).onViewPagerIdleState();
                return;
            case 25:
                ((GalleryAppBarLayout) obj).setExpanded(true);
                return;
            case 26:
                ((Blackboard) obj).erase("data://albums/current");
                return;
            case 27:
                ((GalleryToolbar) obj).setAlpha(0.0f);
                return;
            case 28:
                ((GalleryAppBarLayout) obj).setTitle((CharSequence) null);
                return;
            default:
                ((GalleryAppBarLayout) obj).setSubtitle((CharSequence) null);
                return;
        }
    }
}
