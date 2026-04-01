package n5;

import android.view.Menu;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItem;
import com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.people.PeopleSelectPresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.filter.StoryFilterApplier;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.database.dal.local.CacheProviderHelper;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.function.Function;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2672a;

    public /* synthetic */ e(int i2) {
        this.f2672a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f2672a) {
            case 0:
                return IdentityCreatureUtil.createWithUnifiedId(Long.parseLong(((String) obj).trim()), IdentityCreatureUtil.Category.PEOPLE);
            case 1:
                return PeopleSelectPresenter.lambda$createAutoAlbum$5((Long) obj);
            case 2:
                return PeopleSelectPresenter.lambda$editAutoAlbum$3((Long) obj);
            case 3:
                return ((Menu) obj).findItem(R.id.action_set_as_relation);
            case 4:
                return Integer.valueOf(((FileItemInterface) obj).getAlbumID());
            case 5:
                return ((MediaItem) obj).getTitle();
            case 6:
                return Boolean.valueOf("play".equals(obj));
            case 7:
                return ((Delegate) obj).getClass().getSimpleName();
            case 8:
                return Integer.valueOf(((String) obj).hashCode());
            case 9:
                return CacheProviderHelper.CacheReader.lambda$recoverCursor$0((byte[]) obj);
            case 10:
                return ((IdleWorkerJob) obj).toString();
            case 11:
                return (IdleWorkerJob) ((Supplier) obj).get();
            case 12:
                return ((DrawerTabViewAdapter) obj).getDefaultItemData();
            case 13:
                return ((DrawerTabViewAdapter) obj).getFocusedKey();
            case 14:
                return Integer.valueOf(((GalleryToolbar) obj).getPaddingTop());
            case 15:
                return ((DrawerTabItem) obj).getTitle();
            case 16:
                return StoryFilterApplier.lambda$createLutMap$0((String) obj);
            case 17:
                return Integer.valueOf(((ViewerMenuItem) obj).getMenuId());
            case 18:
                return Integer.valueOf(Integer.parseInt((String) obj));
            case 19:
                return ((MediaItem) ((MediaItem) obj)).getSubCategory();
            case 20:
                return Long.valueOf(((CreatureNameData) obj).getUid());
            case 21:
                return ((CreatureNameData) obj).getName();
            case 22:
                return ((MediaItem) obj).getTitle();
            case 23:
                return ((PageItem) obj).name();
            case 24:
                return Long.valueOf(((MediaItem) ((MediaItem) obj)).getMediaId());
            case 25:
                return Long.valueOf(((MediaItem) ((MediaItem) obj)).getFileId());
            case 26:
                return Long.valueOf(((FileItemInterface) obj).getFileId());
            case 27:
                return Long.valueOf((long) ((FileItemInterface) obj).getAlbumID());
            case 28:
                return Long.valueOf(((FileItemInterface) obj).getMediaId());
            default:
                return C0212a.l("\n", (String) obj);
        }
    }
}
