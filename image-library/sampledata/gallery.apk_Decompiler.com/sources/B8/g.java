package B8;

import android.graphics.Bitmap;
import android.view.View;
import androidx.preference.PreferenceCategory;
import androidx.work.impl.Processor;
import androidx.work.impl.model.WorkGenerationalId;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.AddContentsToMergedCreatureCmd;
import com.samsung.android.gallery.app.controller.story.CreateStoryWithPickCmd;
import com.samsung.android.gallery.app.controller.story.StoriesPinCmd;
import com.samsung.android.gallery.app.service.HighlightEncodeService;
import com.samsung.android.gallery.app.service.RemasterService;
import com.samsung.android.gallery.app.service.StoryBaseService;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseBlurPinchAnimationManager;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryFragment;
import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNamePresenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.FaceClusterMergeDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeader2View;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.StoriesOnDemandDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.SharedTransitionHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.theme.ThemeUpdater;
import com.samsung.android.gallery.module.album.ShortcutHelper;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.remaster.RemasterHelper;
import com.samsung.android.gallery.module.viewer.DualPhotoManager;
import com.samsung.android.gallery.settings.ui.SharingServiceFragment;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerViewImp;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ g(Object obj, Object obj2, boolean z, int i2) {
        this.d = i2;
        this.f = obj;
        this.g = obj2;
        this.e = z;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((ShortcutHelper) this.f).lambda$handleResultCanceled$9((Blackboard) this.g, this.e);
                return;
            case 1:
                AlbumsBaseBlurPinchAnimationManager.lambda$prepareFolderChildAnimation$1((ListViewHolder) this.f, this.e, (View) this.g);
                return;
            case 2:
                ((SharingServiceFragment) this.f).lambda$loadPreference$1((PreferenceCategory) this.g, this.e);
                return;
            case 3:
                ((AddContentsToMergedCreatureCmd) this.f).lambda$onExecute$1((String[]) this.g, this.e);
                return;
            case 4:
                ((TextExtractionHelper) this.f).lambda$extract$1((Bitmap) this.g, this.e);
                return;
            case 5:
                ((CreateStoryWithPickCmd) this.f).lambda$onExecute$0((EventContext) this.g, this.e);
                return;
            case 6:
                ((StoriesPinCmd) this.f).lambda$pinStory$1((MediaItem[]) this.g, this.e);
                return;
            case 7:
                ((StoriesOnDemandDelegate) this.f).lambda$loadAndLaunchStory$8((ArrayList) this.g, this.e);
                return;
            case 8:
                ((HighlightEncodeService) this.f).lambda$onCompleted$7((Blackboard) this.g, this.e);
                return;
            case 9:
                ((RemasterService) this.f).lambda$onCompleted$0(this.e, (RemasterHelper.Result) this.g);
                return;
            case 10:
                ((StoryBaseService) this.f).lambda$onProgressCompleted$3(this.e, (String) this.g);
                return;
            case 11:
                ((SimpleThreadPool) this.f).lambda$attachWatchdog$0(this.e, (Runnable) this.g);
                return;
            case 12:
                ((SharedTransitionHandler) this.f).lambda$prepareEnter$3(this.e, (int[]) this.g);
                return;
            case 13:
                ((MediaPlayerViewImp) this.f).lambda$setAudioEraserOff$24(this.e, (String) this.g);
                return;
            case 14:
                ((Processor) this.f).lambda$runOnExecuted$2((WorkGenerationalId) this.g, this.e);
                return;
            case 15:
                ((CreatureCategoryFragment) this.f).lambda$updateTop5$3(this.e, (String) this.g);
                return;
            case 16:
                ((EditCreatureNamePresenter) this.f).lambda$publishDataAsSelectedItem$13(this.e, (CreatureNameData) this.g);
                return;
            case 17:
                ThemeUpdater.lambda$updateBgmToDb$1((MediaItem) this.f, this.e, (String) this.g);
                return;
            case 18:
                ((DualPhotoManager) this.f).lambda$changeCover$1((BiConsumer) this.g, this.e);
                return;
            case 19:
                ((FaceClusterMergeDelegate) this.f).lambda$executeLastMerge$12((Bitmap) this.g, this.e);
                return;
            default:
                ((SearchCreatureHeader2View) this.f).lambda$loadContactIcon$2(this.e, (AtomicReference) this.g);
                return;
        }
    }

    public /* synthetic */ g(Object obj, boolean z, Object obj2, int i2) {
        this.d = i2;
        this.f = obj;
        this.e = z;
        this.g = obj2;
    }
}
