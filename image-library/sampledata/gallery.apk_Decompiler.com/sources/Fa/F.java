package Fa;

import J8.a;
import android.view.View;
import android.widget.CheckBox;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsViewAdapter;
import com.samsung.android.gallery.module.album.EssentialAlbum;
import com.samsung.android.gallery.module.bgm.bgmdata.BgmDataListener;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.settings.helper.Troubleshooting;
import com.samsung.android.gallery.settings.ui.LabsFragment;
import com.samsung.android.gallery.settings.ui.SearchSettingFragment;
import com.samsung.android.gallery.settings.ui.SettingFragment;
import com.samsung.android.gallery.settings.ui.SettingPresenter;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.animations.CheckboxAnimation;
import com.samsung.android.gallery.widget.dialog.OverlayTipCompat;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.scroller.Scroller;
import com.samsung.android.gallery.widget.tip.PopupTipCompat;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class F implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ F(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((Preference) obj).setEnabled(PreferenceFeatures.isEnabled(PreferenceFeatures.PhotoStrip41));
                return;
            case 1:
                LabsFragment.lambda$initPreferenceForUtilities$13((Object[]) obj);
                return;
            case 2:
                ((PreferenceFeatures) obj).recycle();
                return;
            case 3:
                ((PocFeatures) obj).recycle();
                return;
            case 4:
                SearchSettingFragment.lambda$updateMainLayoutBackgroundColor$3((View) obj);
                return;
            case 5:
                SettingFragment.lambda$onResume$1((Preference) obj);
                return;
            case 6:
                SettingPresenter.lambda$updateAboutPageBadge$2((Preference) obj);
                return;
            case 7:
                ((Troubleshooting.TroubleResolver) obj).test();
                return;
            case 8:
                new CheckboxAnimation().show((CheckBox) obj);
                return;
            case 9:
                ((Scroller) obj).resetDefaultMaxScroll();
                return;
            case 10:
                ((GalleryListAdapter) obj).unSelectAll();
                return;
            case 11:
                ((GalleryListAdapter) obj).notifySelectedItemChanged();
                return;
            case 12:
                ((Scroller) obj).resetScrollbarHeight();
                return;
            case 13:
                ((GalleryListAdapter) obj).checkPreviewCandidate(600);
                return;
            case 14:
                ((Scroller) obj).hidePopup();
                return;
            case 15:
                ((RecyclerView.LayoutManager) obj).setItemPrefetchEnabled(true);
                return;
            case 16:
                ((RecyclerView.LayoutManager) obj).setItemPrefetchEnabled(false);
                return;
            case 17:
                ((a) ((BgmDataListener) obj)).f2828a.lambda$new$1();
                return;
            case 18:
                ((GalleryListAdapter) obj).notifyAdvancedMouseFocusedItemChanged();
                return;
            case 19:
                EssentialAlbum.getInstance().remove(((Integer) obj).intValue());
                return;
            case 20:
                EssentialAlbum.getInstance().add(((Integer) obj).intValue());
                return;
            case 21:
                ((OverlayTipCompat) obj).dismiss();
                return;
            case 22:
                ((PopupTipCompat) obj).dismiss();
                return;
            case 23:
                ((OverlayTipCompat) obj).refresh();
                return;
            case 24:
                ((PopupTipCompat) obj).refresh();
                return;
            case 25:
                AnalyticsLogger.getInstance().postLog(((EventContext) obj).getScreenId(), AnalyticsEventId.EVENT_DETAIL_VIEW_APV_FRAME_DROP.toString());
                return;
            case 26:
                ((IMediaPlayerView) obj).release3dEffect();
                return;
            case 27:
                ((MxAlbumsViewAdapter) obj).refreshHeaderView(false);
                return;
            case 28:
                ((MxAlbumsViewAdapter) obj).onFamilyAlbumCreated();
                return;
            default:
                ((MxAlbumsViewAdapter) obj).refreshHeaderView(true);
                return;
        }
    }
}
