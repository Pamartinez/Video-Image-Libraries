package Ba;

import Tf.w;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Pair;
import android.view.View;
import androidx.preference.Preference;
import com.samsung.android.gallery.app.activity.VersionUpdateHandler;
import com.samsung.android.gallery.app.controller.externals.DocumentScanCmd;
import com.samsung.android.gallery.app.controller.internals.FixDateTimeCmd;
import com.samsung.android.gallery.app.controller.internals.RemoveLiveEffectCmd;
import com.samsung.android.gallery.app.controller.internals.ShowSnackBarForViewerCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestDeleteContent;
import com.samsung.android.gallery.app.controller.sharing.request.RequestDeleteFromTrash;
import com.samsung.android.gallery.app.controller.sharing.request.RequestDownloadCover;
import com.samsung.android.gallery.app.controller.sharing.request.RequestFamilyQuota;
import com.samsung.android.gallery.app.controller.sharing.request.RequestMyQuota;
import com.samsung.android.gallery.app.controller.sharing.request.RequestRestoreFromTrash;
import com.samsung.android.gallery.app.controller.sharing.request.RequestSync;
import com.samsung.android.gallery.app.controller.viewer.SaveCaptureCmd;
import com.samsung.android.gallery.app.receiver.SharedAlbumNotificationReceiver;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.PdcRecommendDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.SubscriberListenerInfo;
import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterImageLoader;
import com.samsung.android.gallery.app.ui.viewer2.selection.SelectionViewFragment;
import com.samsung.android.gallery.bixby.bixby.abstraction.GalleryActionHandler;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.dataset.chapter.LayoutInfo;
import com.samsung.android.gallery.module.dataset.tables.ChapterIndexer;
import com.samsung.android.gallery.module.lottie.recap.binder.RecapTextDelegateImpl;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapTextLayer;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoActivity;
import com.samsung.android.gallery.plugins.motionphoto.VideoCtrlView;
import com.samsung.android.gallery.plugins.panorama.PanoramaActivity;
import com.samsung.android.gallery.settings.ui.EditSuggestionsFragment;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.widget.photoview.ImageProcessor;
import com.samsung.android.gallery.widget.search.searchbar.NewSearchToolbar;
import com.samsung.android.gallery.widget.search.searchbar.selectedfilter.SearchSelectedFiltersEditableAdapter;
import com.samsung.android.motionphoto.utils.v2.video.VideoTransfer;
import com.samsung.android.sum.core.filter.MediaMuxerFilter;
import com.samsung.android.sum.core.types.MediaType;
import com.sec.android.gallery3d.R;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2777a;
    public final /* synthetic */ Object b;

    public /* synthetic */ h(int i2, Object obj) {
        this.f2777a = i2;
        this.b = obj;
    }

    public final void accept(Object obj, Object obj2) {
        int i2 = this.f2777a;
        Object obj3 = this.b;
        switch (i2) {
            case 0:
                ((MotionPhotoActivity) obj3).lambda$bindVideoCtrl$5((VideoCtrlView.SeekState) obj, (Integer) obj2);
                return;
            case 1:
                BlackboardUtils.postEventDataChanged((Blackboard) obj2, (EventMessage) obj3);
                return;
            case 2:
                VersionUpdateHandler.lambda$updateVersion15$2((HashMap) obj3, (String) obj, (String) obj2);
                return;
            case 3:
                ((PanoramaActivity) obj3).lambda$onCreate$2((View) obj, (Boolean) obj2);
                return;
            case 4:
                ((EditSuggestionsFragment) obj3).lambda$initPreference$3((Preference) obj, (SettingPreference) obj2);
                return;
            case 5:
                new ShowSnackBarForViewerCmd().execute(new DocumentScanCmd.DocumentScanSaveTask.EventContextImpl((Activity) obj3), (Uri) obj2, ((Activity) obj3).getString(R.string.scan_saved_to, new Object[]{FileUtils.getDirectoryFromPath(BucketUtils.translatePath((String) obj, false), false)}), Boolean.FALSE);
                return;
            case 6:
                ((ImageProcessor) obj3).lambda$recycleTileMap$6((Integer) obj, (List) obj2);
                return;
            case 7:
                ((FixDateTimeCmd) obj3).lambda$initResultList$15((Integer) obj, (Integer) obj2);
                return;
            case 8:
                ((RemoveLiveEffectCmd) obj3).lambda$executeInternal$2((Integer) obj, (Integer) obj2);
                return;
            case 9:
                ((RemasterImageLoader) obj3).lambda$clearSubscriber$1((String) obj, (SubscriberListenerInfo) obj2);
                return;
            case 10:
                ((RequestDeleteContent) obj3).lambda$getResultsCallback$0((List) obj, (Integer) obj2);
                return;
            case 11:
                ((RequestDeleteFromTrash) obj3).lambda$request$1((String) obj, (List) obj2);
                return;
            case 12:
                ((RequestDownloadCover) obj3).lambda$requestDownloadCover$0((Integer) obj, (String) obj2);
                return;
            case 13:
                ((RequestFamilyQuota) obj3).lambda$requestQuota$0((Long) obj, (Long) obj2);
                return;
            case 14:
                ((RequestMyQuota) obj3).lambda$requestQuota$0((Long) obj, (Long) obj2);
                return;
            case 15:
                ((RequestRestoreFromTrash) obj3).lambda$request$1((String) obj, (List) obj2);
                return;
            case 16:
                ((RequestSync) obj3).handleRequestCallback(((Integer) obj).intValue(), (String) obj2);
                return;
            case 17:
                ((SelectionViewFragment) obj3).onSelected(((Integer) obj).intValue(), ((Boolean) obj2).booleanValue());
                return;
            case 18:
                ((NewSearchToolbar) obj3).lambda$initInstantSearchHandler$3((String) obj, (String) obj2);
                return;
            case 19:
                ((SaveCaptureCmd) obj3).lambda$copyAndScan$10((String) obj, (Uri) obj2);
                return;
            case 20:
                ((SearchSelectedFiltersEditableAdapter) obj3).handleInstantSearch((String) obj, (String) obj2);
                return;
            case 21:
                SharedAlbumNotificationReceiver.lambda$updateNewBadgeInMenuTab$4((boolean[]) obj3, (String) obj, (Blackboard) obj2);
                return;
            case 22:
                ((ChapterIndexer) obj3).onClusterMessage(((Integer) obj).intValue(), (LayoutInfo) obj2);
                return;
            case 23:
                PdcRecommendDelegate.lambda$loadAndBind$3((LinkedHashMap) obj3, (String) obj, (List) obj2);
                return;
            case 24:
                ((GalleryActionHandler) obj3).execute((String) obj, (Blackboard) obj2);
                return;
            case 25:
                FileItemInterface.lambda$cloneTags$0((Map) obj3, (String) obj, obj2);
                return;
            case 26:
                ((RecapTextDelegateImpl) obj3).lambda$preload$0((String) obj, (RecapTextLayer) obj2);
                return;
            case 27:
                VideoTransfer._init_$lambda$2((w) obj3, obj, obj2);
                return;
            case 28:
                ((MediaMuxerFilter) obj3).lambda$onMessageReceived$2((MediaType) obj, (Pair) obj2);
                return;
            default:
                PrivateDatabase.lambda$saveUserTags$0((SQLiteDatabase) obj3, (String) obj, obj2);
                return;
        }
    }
}
