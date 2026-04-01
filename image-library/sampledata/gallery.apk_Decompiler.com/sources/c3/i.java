package C3;

import com.samsung.android.gallery.app.activity.GalleryApplication;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingPicturesFragment;
import com.samsung.android.gallery.database.dal.local.recovery.RecoverFiles;
import com.samsung.android.gallery.database.dal.local.table.AlbumBnRHelper;
import com.samsung.android.gallery.database.dal.mp.impl.CategoriesImpl;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.badge.BadgeHelper;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureFileHandler;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.debugger.BugReporter;
import com.samsung.android.gallery.module.debugger.ViewerPerformanceTracker;
import com.samsung.android.gallery.module.media.GalleryMediaSession;
import com.samsung.android.gallery.module.receiver.GlobalActionReceiver;
import com.samsung.android.gallery.module.search.languagepack.NeuralTranslator;
import com.samsung.android.gallery.module.search.root.IntelligentSearch;
import com.samsung.android.gallery.module.story.ondemand.OnDemandSuggestionDataManager;
import com.samsung.android.gallery.module.suggested.SuggestedHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.module.voc.FindHiddenFiles;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataManager;
import com.samsung.android.livetranslation.text.LiveTranslation;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;

    public /* synthetic */ i(int i2) {
        this.d = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                KnoxUtil.getInstance().reload(Locale.getDefault());
                return;
            case 1:
                GalleryApplication.initAppWoContext();
                return;
            case 2:
                BugReporter.saveLastDump();
                return;
            case 3:
                BadgeHelper.lambda$updateMenuTabBadgeIfNecessary$0();
                return;
            case 4:
                GalleryMediaSession.lambda$releaseMediaSession$2();
                return;
            case 5:
                AlbumBnRHelper.getInstance().keepRestoreFile();
                return;
            case 6:
                LabsDevManageFragment.lambda$addCategoryStatus$42();
                return;
            case 7:
                GalleryPreference.getInstance().removeState(PreferenceName.SHOW_ALBUM_INLINE_CUE);
                return;
            case 8:
                SamsungAccountManager.getInstance().reload();
                return;
            case 9:
                Blackboard.postBroadcastEventGlobal(EventMessage.obtain(101, 1, 0, (Object) null));
                return;
            case 10:
                SharingPicturesFragment.lambda$onCreate$2();
                return;
            case 11:
                SamsungAccountManager.getInstance().notifyChange();
                return;
            case 12:
                ClipDataManager.getInstance().runDeferredUpdate();
                return;
            case 13:
                CloudStateCompat.load(true);
                return;
            case 14:
                GlobalActionReceiver.lambda$processSharingServiceStopped$14();
                return;
            case 15:
                Blackboard.publishGlobal(CommandKey.DATA_REQUEST("data://badge/stories"), (Object) null);
                return;
            case 16:
                return;
            case 17:
                OnDemandSuggestionDataManager.getInstance().update();
                return;
            case 18:
                ViewerPerformanceTracker.lambda$dump$0();
                return;
            case 19:
                TranslationManager.getInstance().loadTranslationMap(AppResources.getAppContext());
                return;
            case 20:
                BlackboardUtils.forceRefreshPicturesDataGlobal();
                return;
            case 21:
                LiveTranslation.mOnEngineListener.onError("Failed due to timeout");
                return;
            case 22:
                ObjectCaptureFileHandler.clearTempFiles(Blackboard.getContext());
                return;
            case 23:
                SuggestedHelper.getInstance().updatePreferenceForTab();
                return;
            case 24:
                RecoverFiles.lambda$execute$0();
                return;
            case 25:
                CategoriesImpl.lambda$getShotModeCursor$10();
                return;
            case 26:
                NeuralTranslator.getInstance().refresh();
                return;
            case 27:
                IntelligentSearch.getInstance().requestUpdateConfigStatus();
                return;
            case 28:
                FindHiddenFiles.lambda$execute$0();
                return;
            default:
                ClipDataManager.getInstance().runDeferredUpdate();
                return;
        }
    }

    private final void a() {
    }
}
