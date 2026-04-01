package A4;

import android.content.ComponentName;
import android.view.Menu;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListKeyHandler;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseFragment;
import com.samsung.android.gallery.app.ui.list.stories.pictures.legacy.StoryTripManager;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.tables.IMediaDataTable;
import com.samsung.android.gallery.module.dynamicview.ClipInfo;
import com.samsung.android.gallery.module.map.manager.AddressCompat;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.selection.AdvancedMouseFocusManager;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.sec.android.gallery3d.R;
import java.util.function.Function;

/* renamed from: A4.t  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0384t implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2258a;

    public /* synthetic */ C0384t(int i2) {
        this.f2258a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f2258a) {
            case 0:
                return ((FragmentActivity) obj).findViewById(R.id.bottom_bar);
            case 1:
                return Boolean.valueOf(((GalleryListView) obj).isSimilarModeAnimating());
            case 2:
                return Boolean.valueOf(((GalleryListView) obj).isOngoingPinchAnimation());
            case 3:
                return Boolean.valueOf(((BaseListPresenter) obj).onDeleteKey());
            case 4:
                return Boolean.valueOf(((BaseListPresenter) obj).onEscapeKey());
            case 5:
                return ((GalleryListView) obj).smoothScrollBy(0, -((GalleryListView) obj).getHeight());
            case 6:
                return ((GalleryListView) obj).smoothScrollBy(0, ((GalleryListView) obj).getHeight());
            case 7:
                return ((GalleryListView) obj).scrollToPositionWithAnimation(0);
            case 8:
                return ((GalleryListView) obj).scrollToPositionWithAnimation(Math.max(((GalleryListView) obj).getItemCount() - 1, 0));
            case 9:
                return Boolean.valueOf(((BaseListPresenter) obj).onCopyKey());
            case 10:
                return Boolean.valueOf(((BaseListPresenter) obj).onCreateKey());
            case 11:
                return BaseListKeyHandler.lambda$onGenericMotionEvent$5((Blackboard) obj);
            case 12:
                return Integer.valueOf(((MenuDataBinding) obj).getId());
            case 13:
                return ((AdvancedMouseFocusManager) obj).getSelectedList();
            case 14:
                return new SecureFile(FileUtils.getParentDirectory((String) obj)).getName();
            case 15:
                return Boolean.valueOf(((GalleryAppBarLayout) obj).seslIsCollapsed());
            case 16:
                return ((FragmentActivity) obj).getIntent();
            case 17:
                return ((Menu) obj).findItem(R.id.action_set_as_relation);
            case 18:
                return ((FilterResultsEntity) obj).getRawLabels();
            case 19:
                return Long.valueOf(IdentityCreatureUtil.getUnifiedIdentityId(((MediaItem) obj).getSubCategory()));
            case 20:
                return ((MediaItem) ((MediaItem) obj)).getPath();
            case 21:
                return ((MediaItem) ((MediaItem) obj)).getPath();
            case 22:
                return Integer.valueOf(((MediaData) obj).getCount());
            case 23:
                return AlbumsBaseFragment.lambda$isMoveMode$1((Blackboard) obj);
            case 24:
                return StoryTripManager.lambda$isStoryTrip$0((IMediaDataTable) obj);
            case 25:
                return ((MediaItem) ((MediaItem) obj)).getCropRect();
            case 26:
                return Float.valueOf(((ClipInfo) obj).getScore());
            case 27:
                return AddressCompat.lambda$toFormattedAddressText$2((String) obj);
            case 28:
                return RectUtils.parseOf((String) obj);
            default:
                return ((ComponentName) obj).getClassName();
        }
    }
}
