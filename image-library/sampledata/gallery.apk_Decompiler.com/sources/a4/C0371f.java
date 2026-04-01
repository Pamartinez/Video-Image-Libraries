package A4;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.WindowInsets;
import androidx.preference.PreferenceCategory;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.app.controller.album.UpdateOrderCmd;
import com.samsung.android.gallery.app.controller.externals.FetchContentsForKnoxCmd;
import com.samsung.android.gallery.app.controller.viewer.DirectorsViewEditCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListDelegate;
import com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderPresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.dragdrop.ListDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.picker.albums.AlbumsPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.pictures.PicturesPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.search.CollectionPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.search.SearchClusterResultPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.search.SearchPickerFragment;
import com.samsung.android.gallery.app.ui.list.picker.search.SearchPicturesPickerFragment;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsFragment;
import com.samsung.android.gallery.app.ui.list.stories.header.StoriesPinView61;
import com.samsung.android.gallery.app.ui.map.picker.google.GoogleMapPickerContainer;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.PreviewLoader;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.c2pa.C2paScsImpl;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.details.DetailsRelatedDataLoader;
import com.samsung.android.gallery.module.details.EditDetailsUpdater;
import com.samsung.android.gallery.module.dynamicview.PlaybackOption;
import com.samsung.android.gallery.module.dynamicview.SpeedRun;
import com.samsung.android.gallery.module.lottie.recap.binder.RecapImageSelector;
import com.samsung.android.gallery.module.myquery.SearchMyQuery;
import com.samsung.android.gallery.settings.ui.SharingServiceFragment;
import com.samsung.android.gallery.settings.widget.MainSwitchPreference;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.PreferenceAnalytics;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.widget.abstraction.VideoBackupInfo;
import com.samsung.android.gallery.widget.listview.pinch.v3.PinchItemBuilder;
import com.samsung.android.gallery.widget.listview.pinch.v3.RelativeRange;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.ai.language.Configuration;
import com.samsung.android.sdk.scs.ai.language.SmartReplyer;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import com.samsung.android.visual.ai.sdkcommon.o;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* renamed from: A4.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0371f implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ C0371f(Object obj, MediaItem mediaItem, Object obj2, int i2) {
        this.d = i2;
        this.f = obj;
        this.e = mediaItem;
        this.g = obj2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                BaseListDelegate.lambda$bindImage$2((MediaItem) this.e, (Bitmap[]) this.f, (ListViewHolder) this.g, (Boolean) obj);
                return;
            case 1:
                ((PreviewLoader) this.f).lambda$handleVideoBackupInfo$1((MediaItem) this.e, (VideoBackupInfo) this.g, (Bitmap) obj);
                return;
            case 2:
                ((SharingServiceFragment) this.e).lambda$loadPreference$3((Context) this.f, (PreferenceCategory) this.g, (MainSwitchPreference) obj);
                return;
            case 3:
                ((FileOpCmd) this.e).lambda$onPreExecute$1((EventContext) this.f, (Object[]) this.g, (MediaItem[]) obj);
                return;
            case 4:
                UpdateOrderCmd.lambda$getHighlightedMediaItem$4((HashMap) this.e, (ArrayList) this.f, (ArrayList) this.g, (MediaItem) obj);
                return;
            case 5:
                ((PinchItemBuilder) this.e).lambda$createDividerItems$3((RelativeRange) this.f, (ArrayList) this.g, (Integer) obj);
                return;
            case 6:
                ((MxAlbumsHeaderPresenter) this.f).lambda$showNoticeProfileSharingDialog$1((String) this.g, (MediaItem) this.e, (Context) obj);
                return;
            case 7:
                ((C2paScsImpl) this.e).lambda$extract$0((String) this.f, (Consumer) this.g, (o) obj);
                return;
            case 8:
                ((SharingsFragment) this.f).lambda$showNoticeProfileSharingDialog$3((String) this.g, (MediaItem) this.e, (Context) obj);
                return;
            case 9:
                ((AlbumPicturesPresenter) this.f).lambda$turnOnMergedAlbum$17((MediaItem) this.e, (ArrayList) this.g, (MediaItem) obj);
                return;
            case 10:
                ((FetchContentsForKnoxCmd) this.e).lambda$onPreExecute$1((EventContext) this.f, (Object[]) this.g, (MediaItem[]) obj);
                return;
            case 11:
                ((AtomicBoolean) this.e).set(SearchMyQuery.getInstance().updateMyQuery(((Integer) ((Map.Entry) obj).getKey()).intValue(), (String) this.f, ((EventContext) this.g).getTotalCount()));
                return;
            case 12:
                ((ListDragAndDropDelegate) this.e).lambda$startDrag$1((MediaItem[]) this.f, (ListViewHolder) this.g, (View) obj);
                return;
            case 13:
                ((DirectorsViewEditCmd) this.e).lambda$handleSingleEdit$1((EventContext) this.f, (Consumer) this.g, obj);
                return;
            case 14:
                ((AlbumsPickerFragment) this.e).lambda$onApplyWindowInsets$1((WindowInsets) this.f, (View) this.g, (WindowInsets) obj);
                return;
            case 15:
                ((PicturesPickerFragment) this.e).lambda$onApplyWindowInsets$0((WindowInsets) this.f, (View) this.g, (WindowInsets) obj);
                return;
            case 16:
                ((GoogleMapPickerContainer) this.e).lambda$requestCurrentLocationUpdate$2((Runnable) this.f, (Context) this.g, (Boolean) obj);
                return;
            case 17:
                ((CollectionPickerFragment) this.e).lambda$onApplyWindowInsets$0((WindowInsets) this.f, (View) this.g, (WindowInsets) obj);
                return;
            case 18:
                ((SearchClusterResultPickerFragment) this.e).lambda$onApplyWindowInsets$0((WindowInsets) this.f, (View) this.g, (WindowInsets) obj);
                return;
            case 19:
                ((SearchPickerFragment) this.e).lambda$onApplyWindowInsets$0((WindowInsets) this.f, (View) this.g, (WindowInsets) obj);
                return;
            case 20:
                ((SearchPicturesPickerFragment) this.e).lambda$onApplyWindowInsets$1((WindowInsets) this.f, (View) this.g, (WindowInsets) obj);
                return;
            case 21:
                ((StoriesPinView61) this.f).lambda$onItemLongClicked$2((ListViewHolder) this.g, (MediaItem) this.e, (Consumer) obj);
                return;
            case 22:
                DetailsRelatedDataLoader.lambda$queryRelatedCreatureFiles$0((String) this.f, (ArrayList) this.g, (MediaItem) this.e, (QueryParams) obj);
                return;
            case 23:
                ((EditDetailsUpdater) this.f).lambda$onUpdateLocation$7((MediaItem) this.e, (double[]) this.g, (MediaItem) obj);
                return;
            case 24:
                ((SpeedRun) this.e).lambda$getViewerPlayback$1((AtomicInteger) this.f, (ArrayList) this.g, (PlaybackOption) obj);
                return;
            case 25:
                ((RecapImageSelector) this.e).lambda$selectImages$0((AtomicBoolean) this.f, (AtomicBoolean) this.g, (Map.Entry) obj);
                return;
            case 26:
                BucketUtils.lambda$getRetailDirs$6((ArrayList) this.e, (ArrayList) this.f, (ArrayList) this.g, (StorageInfo) obj);
                return;
            case 27:
                PreferenceAnalytics.lambda$migrate$1((ArrayList) this.e, (HashMap) this.f, (Map) this.g, (PreferenceAnalytics) obj);
                return;
            case 28:
                ((Configuration) this.e).lambda$getModelInfo$0((AppInfo) this.f, (Map) this.g, (LlmServiceObserver2) obj);
                return;
            default:
                ((SmartReplyer) this.e).lambda$identifyLanguage$2((AppInfo) this.f, (String) this.g, (LlmServiceObserver2) obj);
                return;
        }
    }

    public /* synthetic */ C0371f(Object obj, Object obj2, MediaItem mediaItem, int i2) {
        this.d = i2;
        this.f = obj;
        this.g = obj2;
        this.e = mediaItem;
    }

    public /* synthetic */ C0371f(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }
}
