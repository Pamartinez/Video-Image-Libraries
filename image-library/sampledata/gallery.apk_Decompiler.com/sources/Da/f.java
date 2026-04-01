package Da;

import A9.b;
import E2.h;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.WindowInsets;
import android.webkit.WebView;
import android.widget.ImageView;
import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.common.util.NetworkTypeObserver;
import androidx.preference.Preference;
import com.samsung.android.gallery.app.controller.abstraction.ChangeCoverCmd;
import com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder.StoryCoverViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder.StoryPicturesLegacyHeaderViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.recap.RecapPresenter;
import com.samsung.android.gallery.app.ui.list.stories.recap.SharedTransitionHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto.MotionPhotoViewModeHandler;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.analyticsquery.SasCount;
import com.samsung.android.gallery.module.bgm.BgmUriService;
import com.samsung.android.gallery.module.bgm.bgmdata.BgmData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mde.executor.ServiceExecutor;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode;
import com.samsung.android.gallery.plugins.portrait.LiveEffectActivity;
import com.samsung.android.gallery.settings.ui.BasePreferenceFragment;
import com.samsung.android.gallery.settings.ui.EditSuggestionsFragment;
import com.samsung.android.gallery.settings.ui.LabsConfigFragment;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;
import com.samsung.android.gallery.settings.ui.LabsDeveloperFragment;
import com.samsung.android.gallery.settings.ui.LabsFragment;
import com.samsung.android.gallery.settings.ui.TroubleshootingFragment;
import com.samsung.android.gallery.settings.widget.HelpPreference;
import com.samsung.android.gallery.settings.widget.SummaryPreference;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.dialog.ProgressDialogCompat;
import com.samsung.android.gallery.widget.hoverview.HoverPreviewViewHolder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ f(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((LiveEffectActivity) this.e).lambda$new$12((WindowInsets) this.f);
                return;
            case 1:
                ((LiveEffectActivity) this.e).lambda$renderLiveEffectByItemType$4((View) this.f);
                return;
            case 2:
                ((Consumer) this.e).accept((ArrayList) this.f);
                return;
            case 3:
                ((StoryCoverViewHolder) this.e).lambda$bindSlideItem$1((ImageView) this.f);
                return;
            case 4:
                ((StoryPicturesLegacyHeaderViewHolder) this.e).lambda$updateDuration$2((MediaItem) this.f);
                return;
            case 5:
                ((StoryPicturesLegacyHeaderViewHolder) this.e).lambda$updateDuration$1((String) this.f);
                return;
            case 6:
                ((ServiceExecutor) this.e).lambda$showErrorToast$0((String) this.f);
                return;
            case 7:
                ((HoverPreviewViewHolder) this.e).lambda$updateCurrentBitmap$1((Bitmap) this.f);
                return;
            case 8:
                ((BackgroundThreadStateHandler) this.e).lambda$updateStateAsync$1((h) this.f);
                return;
            case 9:
                ((NetworkTypeObserver) this.e).lambda$new$0((Context) this.f);
                return;
            case 10:
                MotionPhotoViewModeHandler.lambda$onViewModeSelected$4((MediaItem) this.e, (MotionPhotoViewMode) this.f);
                return;
            case 11:
                ((BgmUriService) this.e).lambda$onUpdated$7((ArrayList) this.f);
                return;
            case 12:
                ((BgmUriService) this.e).lambda$requestDownload$1((String) this.f);
                return;
            case 13:
                ((MetadataManager) this.e).lambda$preload$0((FileItemInterface) this.f);
                return;
            case 14:
                ((BasePreferenceFragment) this.e).lambda$showHighlightIfGuided$15((String) this.f);
                return;
            case 15:
                ((EditSuggestionsFragment) this.e).lambda$initPreference$0((HelpPreference) this.f);
                return;
            case 16:
                ((LabsConfigFragment) this.e).lambda$clearPreference$4((List) this.f);
                return;
            case 17:
                ((LabsDevManageFragment) this.e).lambda$loadYearCacheInfo$66((String) this.f);
                return;
            case 18:
                ((LabsDevManageFragment) this.e).lambda$loadCacheInfo$61((Context) this.f);
                return;
            case 19:
                ((LabsDevManageFragment) this.e).lambda$loadCacheInfo$60((HashMap) this.f);
                return;
            case 20:
                ((LabsDeveloperFragment) this.e).lambda$deleteDarkCache$19((ProgressDialogCompat) this.f);
                return;
            case 21:
                ThreadUtil.postOnUiThread(new b((SummaryPreference) this.e, (String) this.f, "SaS: " + new SasCount().summaryOfSaS(), 17));
                return;
            case 22:
                ((WebView) this.e).loadData((String) this.f, "text/html", "UTF-8");
                return;
            case 23:
                ((LabsFragment) this.e).lambda$initPreferenceForUtilities$15((Preference) this.f);
                return;
            case 24:
                ((LabsFragment) this.e).lambda$onPrivateAlbumRestoreClicked$30((ArrayList) this.f);
                return;
            case 25:
                ((TroubleshootingFragment) this.e).lambda$executeDiagnose$0((ArrayList) this.f);
                return;
            case 26:
                ((ChangeCoverCmd) this.e).lambda$changeCover$0((MediaItem) this.f);
                return;
            case 27:
                ((RecapPresenter) this.e).lambda$updateNewBadgeState$3((MediaItem) this.f);
                return;
            case 28:
                ((SharedTransitionHandler) this.e).lambda$startTransition$1((SharedTransition.TransitionListener) this.f);
                return;
            default:
                ((BgmData) this.e).lambda$swap$0((ConcurrentHashMap) this.f);
                return;
        }
    }
}
