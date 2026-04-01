package A4;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.SeslSwitchPreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumsPaneFragment;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.setting.SharingPicturesSettingFragment;
import com.samsung.android.gallery.app.ui.list.stories.header.BasePinView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.header.StoryHeaderBasic;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.bgm.updater.BgmUpdateListener;
import com.samsung.android.gallery.module.bgm.updater.DownloadListener;
import com.samsung.android.gallery.settings.ui.SharingServiceFragment;
import com.samsung.android.gallery.widget.animations.textexpand.TextExpandAnimListView;
import com.samsung.android.gallery.widget.animations.textexpand.TextExpandAnimView;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.search.searchbar.BottomSearchToolbarInnerContainer;
import com.samsung.android.gallery.widget.videoview.mediaplayer.AudioManagerDelegate;
import com.samsung.android.gallery.widget.videoview.mediaplayer.ScaleDelegate;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.filter.MediaFilter;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class L implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;

    public /* synthetic */ L(boolean z, int i2) {
        this.d = i2;
        this.e = z;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        boolean z = this.e;
        switch (i2) {
            case 0:
                ((BaseListViewAdapter) obj).stopPreview(z);
                return;
            case 1:
                ((AlbumsViewAdapter) obj).setFooterEnabled(z);
                return;
            case 2:
                ((StoryHeaderBasic) obj).setEnabled(z);
                return;
            case 3:
                ((MenuItem) obj).setVisible(z);
                return;
            case 4:
                ((BasePinView) obj).onSelectModeChanged(z);
                return;
            case 5:
                ((BgmUpdateListener) obj).onDownloadedAll(z);
                return;
            case 6:
                ((Preference) obj).setVisible(z);
                return;
            case 7:
                ((Preference) obj).setEnabled(z);
                return;
            case 8:
                ((PreferenceScreen) obj).setEnabled(z);
                return;
            case 9:
                ((SwitchPreferenceCompat) obj).setChecked(z);
                return;
            case 10:
                SharingServiceFragment.lambda$loadPreference$0(z, (SeslSwitchPreferenceScreen) obj);
                return;
            case 11:
                ((GalleryListAdapter) obj).exitSelect(z);
                return;
            case 12:
                ((MxAlbumsViewAdapter) obj).enableHeaderView(z);
                return;
            case 13:
                ((DownloadListener) obj).onDownloadedAll(z);
                return;
            case 14:
                AlbumPicturesFragment.lambda$updateAddItemButtonEnable$5(z, (View) obj);
                return;
            case 15:
                ((AlbumsPaneFragment) obj).setNestedScroll(z);
                return;
            case 16:
                ((AlbumPicturesPresenter) obj).updateAlbumSyncMenu(z);
                return;
            case 17:
                SharingPicturesSettingFragment.lambda$updateLinkSwitchState$12(z, (Preference) obj);
                return;
            case 18:
                ((ImageViewHolder) obj).setFocusedFilterOnAdvancedMouseEvent(z, false);
                return;
            case 19:
                ((GalleryListView) obj).seslSetPenSelectionEnabled(!z);
                return;
            case 20:
                ((MvpBaseFragment) obj).setDefaultExitTransitioning(z);
                return;
            case 21:
                ((BottomSearchToolbarInnerContainer) ((ViewGroup) obj)).setGradientBackground(z);
                return;
            case 22:
                ((Consumer) obj).accept(Boolean.valueOf(z));
                return;
            case 23:
                ((MediaFilter.Option) ((MFDescriptor) obj)).setAllowPartialConnection(z);
                return;
            case 24:
                ((MediaFilter.Option) ((MFDescriptor) obj)).setUseExternalBufferComposer(z);
                return;
            case 25:
                ((IBaseFragment) obj).setDefaultExitTransitioning(z);
                return;
            case 26:
                TextExpandAnimListView.lambda$setImportantForAccessibility$4(z, (TextExpandAnimView) obj);
                return;
            case 27:
                ((ScaleDelegate) obj).setCenterCrop(z);
                return;
            case 28:
                ((ScaleDelegate) obj).setDefaultPosition(z);
                return;
            default:
                ((AudioManagerDelegate) obj).setAudioFocusEnabled(!z);
                return;
        }
    }
}
