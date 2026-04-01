package A4;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.work.WorkManager;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.pdc.PdcSearchDelegate;
import com.samsung.android.gallery.app.ui.list.stories.pictures.header.StoryHeaderBasic;
import com.samsung.android.gallery.module.clipboard.Clipboard;
import com.samsung.android.gallery.plugins.mergeplayer.PlayerInstance;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripProgressBarDelegate;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.selection.AdvancedMouseFocusManager;
import com.samsung.android.gallery.widget.smartalbum.SmartAlbumHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.sec.android.gallery3d.R;
import java.util.UUID;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

/* renamed from: A4.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0366a implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ C0366a(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((View) obj).requestLayout();
                return;
            case 1:
                ((GalleryListView) obj).refreshScrollPosition();
                return;
            case 2:
                ((TextView) obj).setTextSize(0, ((TextView) obj).getResources().getDimension(R.dimen.noitem_main_text_size));
                return;
            case 3:
                ((TextView) obj).setTextSize(0, ((TextView) obj).getResources().getDimension(R.dimen.noitem_description_text_size));
                return;
            case 4:
                ((View) obj).bringToFront();
                return;
            case 5:
                BaseListFragment.lambda$clearAdvancedMouseFocus$10((AdvancedMouseFocusManager) obj);
                return;
            case 6:
                ((GalleryToolbar) obj).setContentInset();
                return;
            case 7:
                ((BaseListViewAdapter) obj).getDragSelectTouchListener().reset();
                return;
            case 8:
                ((GalleryToolbar) obj).handleDensityChanged();
                return;
            case 9:
                ((BaseListPresenter) obj).moveToSearch();
                return;
            case 10:
                ((BaseListPresenter) obj).enterSelectionMode(0);
                return;
            case 11:
                Clipboard.getInstance((Blackboard) obj).clear();
                return;
            case 12:
                ((GalleryListView) obj).setIsAllowAdvancedMouseEvent((BooleanSupplier) null);
                return;
            case 13:
                ((GalleryListView) obj).cancelDragAndDrop();
                return;
            case 14:
                ((BaseListViewAdapter) obj).checkPreviewCandidate();
                return;
            case 15:
                ((GalleryListView) obj).resetTouch();
                return;
            case 16:
                Blackboard.getApplicationInstance().post("recapProgress", (Float) obj);
                return;
            case 17:
                WorkManager.getInstance(AppResources.getAppContext()).cancelWorkById((UUID) obj);
                return;
            case 18:
                ((PlayerInstance) obj).open();
                return;
            case 19:
                ((PlayerInstance) obj).open();
                return;
            case 20:
                ((SmartAlbumHolder) obj).restore();
                return;
            case 21:
                ((GalleryAppBarLayout) obj).setTitle((CharSequence) null);
                return;
            case 22:
                ((MenuItem) obj).setTitle(R.string.confirm);
                return;
            case 23:
                ((PdcSearchDelegate) obj).handleNoResult();
                return;
            case 24:
                ((StoryHeaderBasic) obj).layoutAnimationDone();
                return;
            case 25:
                ((StoryHeaderBasic) obj).clear();
                return;
            case 26:
                ((StoryHeaderBasic) obj).resume();
                return;
            case 27:
                ((StoryHeaderBasic) obj).pause();
                return;
            case 28:
                ViewMarginUtils.setPadding((View) ((IMediaPlayerView) obj), 0, 0, 0, 0);
                return;
            default:
                ((FilmStripProgressBarDelegate) obj).onFocusOut();
                return;
        }
    }
}
