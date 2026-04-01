package n4;

import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerFakeViewManager;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabLayoutManager;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabViewAdapter;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultContainer;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultFragment;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder.DragItem;
import com.samsung.android.gallery.app.ui.list.stories.pictures.cover.StoryCover;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;

/* renamed from: n4.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0489a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;

    public /* synthetic */ C0489a(int i2, int i7) {
        this.d = i7;
        this.e = i2;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        int i7 = this.e;
        switch (i2) {
            case 0:
                ViewMarginUtils.setStartMargin(((FragmentActivity) obj).findViewById(R.id.bottom_bar_layout_container), i7);
                return;
            case 1:
                ((DragItem) obj).updateDragPosition(i7);
                return;
            case 2:
                ((Delegate) obj).handleOrientationChange(i7);
                return;
            case 3:
                ((Delegate) obj).handleDensityChange(i7);
                return;
            case 4:
                ((Delegate) obj).handleResolutionChange(i7);
                return;
            case 5:
                ((BaseListPresenter) obj).onDrawerSizeChanged(i7);
                return;
            case 6:
                ((BaseListPresenter) obj).onDrawerSizeChanged(i7);
                return;
            case 7:
                ((DrawerFakeViewManager) obj).setState(i7);
                return;
            case 8:
                ((DrawerTabViewAdapter) obj).setState(i7);
                return;
            case 9:
                ((DrawerTabLayoutManager) obj).setState(i7);
                return;
            case 10:
                ((RecyclerView.LayoutManager) obj).scrollToPosition(i7);
                return;
            case 11:
                ((QueryParams) obj).addAlbumId(i7).addGroupType(GroupType.SINGLE_TAKEN).onlyTrashed();
                return;
            case 12:
                ((QueryParams) obj).addAlbumId(i7).addGroupType(GroupType.SINGLE_TAKEN);
                return;
            case 13:
                ((ViewGroup) obj).setBackgroundColor(i7);
                return;
            case 14:
                ((GalleryAppBarLayout) obj).setBackgroundColor(i7);
                return;
            case 15:
                ((SearchHeaderView) obj).setItemCount(i7);
                return;
            case 16:
                ((SearchHeaderView) obj).handleOrientationChange(i7);
                return;
            case 17:
                ((SearchHeaderView) obj).handleOrientationChange(i7);
                return;
            case 18:
                ((SearchClusterResultContainer) obj).handleOrientationChange(i7);
                return;
            case 19:
                SearchClusterResultFragment.lambda$onBottomSearchToolbarChanged$4(i7, (RecyclerView) obj);
                return;
            default:
                ((StoryCover) obj).handleDensityChange(i7);
                return;
        }
    }
}
