package E9;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.app.ui.list.albums.drag.AlbumsDragFragment;
import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsPresenter;
import com.samsung.android.gallery.app.ui.list.albums.mx.MxAlbumsViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.SuggestedActionView;
import com.samsung.android.gallery.app.ui.list.search.util.ActionSuggester;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionVideoController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.InstantSlowMoSaveClipHandler;
import com.samsung.android.gallery.module.bgm.bgmdata.BgmCache;
import com.samsung.android.gallery.module.mde.executor.AddContents;
import com.samsung.android.gallery.module.media.MediaMotionPhotoCaptureWorker;
import com.samsung.android.gallery.settings.ui.AboutFragment;
import com.samsung.android.gallery.settings.ui.BaseFragment;
import com.samsung.android.gallery.settings.ui.DetailEnhancerFragment;
import com.samsung.android.gallery.settings.ui.EditSuggestionsFragment;
import com.samsung.android.gallery.settings.ui.LabsUserTrialFragment;
import com.samsung.android.gallery.settings.ui.SharingNotificationFragment;
import com.samsung.android.gallery.settings.widget.HelpPreference;
import com.samsung.android.gallery.settings.widget.MainSwitchPreference;
import com.samsung.android.gallery.settings.widget.SearchPreference;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayback;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;
import com.samsung.android.gallery.widget.listview.handler.AdvancedMouseSelectionHandler;
import com.samsung.android.gallery.widget.listview.handler.DisHandler;
import com.samsung.android.gallery.widget.listview.handler.DisHandlerComposite;
import com.samsung.android.gallery.widget.listview.pinch.v3.PinchItem;
import com.samsung.android.gallery.widget.listview.pinch.v3.PinchItemBuilder;
import com.samsung.android.sdk.mobileservice.social.share.result.SharedItemListResult;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((AddContents) obj2).lambda$getAddContentsResultCallback$0((SharedItemListResult) obj);
                return;
            case 1:
                ((AlbumsDragFragment) obj2).lambda$onDestroy$1((GalleryListView) obj);
                return;
            case 2:
                ((MotionVideoController) obj2).downloadCompleted(obj);
                return;
            case 3:
                ((MediaMotionPhotoCaptureWorker) obj2).lambda$prepareInternal$1((MediaPlayback) obj);
                return;
            case 4:
                ((AboutFragment) obj2).lambda$onCreateView$2((FragmentActivity) obj);
                return;
            case 5:
                ((BaseFragment) obj2).lambda$onResume$0((ActionBar) obj);
                return;
            case 6:
                DetailEnhancerFragment.lambda$updateRadioPrefs$1((PreferenceScreen) obj2, (Preference) obj);
                return;
            case 7:
                ((EditSuggestionsFragment) obj2).lambda$initPreference$1((HelpPreference) obj);
                return;
            case 8:
                ((GalleryPreference) obj).restore(((File) obj2).getPath());
                return;
            case 9:
                ((Preference) obj).setSummary((CharSequence) "1 representative image every " + ((Integer) obj2) + " minutes");
                return;
            case 10:
                ((LabsUserTrialFragment) obj2).lambda$addCategorySearch$8((String) obj);
                return;
            case 11:
                ((SwitchPreferenceCompat) obj2).setChecked(((Boolean) obj).booleanValue());
                return;
            case 12:
                ((Preference) obj2).setSummary((CharSequence) (String) obj);
                return;
            case 13:
                ((SharingNotificationFragment) obj2).lambda$loadMainSwitchPreference$2((MainSwitchPreference) obj);
                return;
            case 14:
                ((GalleryRecyclerView) obj2).lambda$updateIndexTipPosition$6((RecyclerView.LayoutManager) obj);
                return;
            case 15:
                ((BgmCache) obj2).lambda$updateCache$0((String) obj);
                return;
            case 16:
                ((ConcurrentHashMap) obj2).remove((String) obj);
                return;
            case 17:
                ((AdvancedMouseSelectionHandler) obj2).lambda$resetListViewHolderTint$4((Integer) obj);
                return;
            case 18:
                ((GalleryListAdapter) obj2).selectClusterDivider(((Integer) obj).intValue());
                return;
            case 19:
                ((DisHandlerComposite) obj2).lambda$onBindViewHolder$0((DisHandler) obj);
                return;
            case 20:
                ((AtomicReference) obj2).set((String) obj);
                return;
            case 21:
                ((FileOpCmd) obj2).lambda$startChoiceAlbumCmd$2((ArrayList) obj);
                return;
            case 22:
                ((SwitchCompat) obj2).setEnabled(true);
                return;
            case 23:
                ((InstantSlowMoSaveClipHandler) obj2).lambda$getMediaCaptureFrame$7((Bitmap) obj);
                return;
            case 24:
                ((HelpPreference) obj2).lambda$updateLinkDescription$0((TextView) obj);
                return;
            case 25:
                ((SearchPreference) obj2).lambda$onBindViewHolder$0((View) obj);
                return;
            case 26:
                ((MxAlbumsPresenter) obj2).lambda$onViewResume$0((MxAlbumsViewAdapter) obj);
                return;
            case 27:
                ((MxAlbumsViewAdapter) obj2).lambda$onPrepareDelete$2((Integer) obj);
                return;
            case 28:
                ((SuggestedActionView) obj2).lambda$onSuggestedActionClicked$1((ActionSuggester) obj);
                return;
            default:
                ((PinchItemBuilder) obj2).lambda$updateHeaderViewRect$6((PinchItem) obj);
                return;
        }
    }
}
