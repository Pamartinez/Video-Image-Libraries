package R6;

import Sd.g;
import Sd.h;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.story.AddContentsToStoryCmd;
import com.samsung.android.gallery.app.controller.story.AddToStoryCmd;
import com.samsung.android.gallery.app.controller.story.RemoveFromStoryCmd;
import com.samsung.android.gallery.app.controller.story.RenameStoryCmd;
import com.samsung.android.gallery.app.controller.story.SaveManageContentsCmd;
import com.samsung.android.gallery.app.controller.story.SaveOnDemandStoryCmd;
import com.samsung.android.gallery.app.controller.story.SaveRecapVideoCmd;
import com.samsung.android.gallery.app.controller.story.SaveStoryLiveEffectCmd;
import com.samsung.android.gallery.app.controller.viewer.EraseObjectCmd;
import com.samsung.android.gallery.app.controller.viewer.SaveDualPhotoCmd;
import com.samsung.android.gallery.app.provider.SharedItemUploader;
import com.samsung.android.gallery.app.remote.v2.PresentationViewPagerHolder;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.dragdrop.AlbumsDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.dragdrop.MSYourPhoneDragAndDrop;
import com.samsung.android.gallery.app.ui.list.dragdrop.popup.ListPopupMenuDelegate;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatTransitoryItemOnDemandViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.PdcRecommendDelegate;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.StoriesOnDemandDelegate;
import com.samsung.android.gallery.app.ui.list.stories.header.StoriesPinAdapter;
import com.samsung.android.gallery.app.ui.list.stories.header.StoriesPinModel;
import com.samsung.android.gallery.app.ui.list.timeline.TimelineFragment;
import com.samsung.android.gallery.app.ui.viewer2.remaster.focusview.RemasterFocusViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.selection.SelectionViewFragment;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureEraseInfo;
import com.samsung.android.gallery.module.cloud.scpm.GotoLink;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.StoriesPinCache;
import com.samsung.android.gallery.module.data.StoriesPinData;
import com.samsung.android.gallery.module.remote.v2.RemoteDisplayState;
import com.samsung.android.gallery.module.retailmode.RetailModeInstaller;
import com.samsung.android.gallery.module.search.autocomplete.ScsAutoCompleteDataLoader;
import com.samsung.android.gallery.module.search.engine.ExtraResults;
import com.samsung.android.gallery.module.search.engine.IntelligentSearchEngine;
import com.samsung.android.gallery.module.search.engine.PersonalDataCore;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ScpmMessage;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ c(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((TimelineFragment) this.e).lambda$startScrollAndShrink$2(this.f, (Bundle) this.g);
                return;
            case 1:
                ((GotoLink) this.e).lambda$update$1((ScpmMessage) this.f, (String) this.g);
                return;
            case 2:
                ((RemoteDisplayState) this.e).lambda$new$2((Map) this.f, (String) this.g);
                return;
            case 3:
                g gVar = (g) this.e;
                Consumer consumer = (Consumer) this.g;
                gVar.getClass();
                Bundle extras = ((Intent) this.f).getExtras();
                ((h) gVar.f3702c).getClass();
                try {
                    consumer.accept(extras);
                    return;
                } catch (Throwable unused) {
                    return;
                }
            case 4:
                ((AlbumsDragAndDropDelegate) this.e).lambda$handleDrop$2((DragEvent) this.f, (AtomicBoolean) this.g);
                return;
            case 5:
                MSYourPhoneDragAndDrop.lambda$handleDropFromExternal$0((IBaseListView) this.e, (ArrayList) this.f, (MediaItem) this.g);
                return;
            case 6:
                ((RemasterFocusViewHandler) this.e).lambda$onLoadedFocusData$1((ArrayList) this.f, (Bitmap) this.g);
                return;
            case 7:
                ((RetailModeInstaller) this.e).lambda$setup$0((Context) this.f, (Intent) this.g);
                return;
            case 8:
                AddContentsToStoryCmd.lambda$addToStory$2((MediaItem) this.e, (EventContext) this.f, (MediaItem[]) this.g);
                return;
            case 9:
                ((AddToStoryCmd) this.e).lambda$onExecute$0((MediaItem) this.f, (Object[]) this.g);
                return;
            case 10:
                ((RemoveFromStoryCmd) this.e).lambda$onExecute$1((MediaItem) this.f, (MediaItem[]) this.g);
                return;
            case 11:
                ((RenameStoryCmd) this.e).lambda$renameStory$0((String) this.f, (EventContext) this.g);
                return;
            case 12:
                ((SaveManageContentsCmd) this.e).lambda$onExecute$0((Object[]) this.f, (EventContext) this.g);
                return;
            case 13:
                ((SaveOnDemandStoryCmd) this.e).lambda$onSaveClick$1((MediaItem) this.f, (CharSequence) this.g);
                return;
            case 14:
                ((SaveRecapVideoCmd) this.e).lambda$saveRecap$1((MediaItem) this.f, (String) this.g);
                return;
            case 15:
                ((SaveStoryLiveEffectCmd) this.e).lambda$saveLiveEffect$1((MediaItem) this.f, (String) this.g);
                return;
            case 16:
                ((ListPopupMenuDelegate) this.e).lambda$show$3((Context) this.f, (Consumer) this.g);
                return;
            case 17:
                ((SelectionViewFragment) this.e).lambda$onApplyWindowInsets$2((Rect) this.f, (WindowInsets) this.g);
                return;
            case 18:
                ((ScsAutoCompleteDataLoader) this.e).lambda$loadInBackground$1((SearchFilter) this.f, (BiConsumer) this.g);
                return;
            case 19:
                ((EraseObjectCmd) this.e).lambda$erase$0((String) this.f, (ObjectCaptureEraseInfo.EraseType) this.g);
                return;
            case 20:
                ((SaveDualPhotoCmd) this.e).lambda$onExecute$1((MediaItem) this.f, (String) this.g);
                return;
            case 21:
                ((IntelligentSearchEngine) this.e).lambda$getIdsNExtraResult$7((SearchFilter) this.f, (ExtraResults) this.g);
                return;
            case 22:
                ((SharedItemUploader) this.e).lambda$run$0((String) this.f, (String) this.g);
                return;
            case 23:
                ((StoriesCatTransitoryItemOnDemandViewHolder) this.e).lambda$bindSearchToolbarAsync$2((ViewGroup) this.f, (View) this.g);
                return;
            case 24:
                PersonalDataCore.getInstance((Blackboard) this.e).sendRelationShipFeedback(AppResources.getAppContext(), (String) this.f, (ArrayList) this.g);
                return;
            case 25:
                ((PdcRecommendDelegate) this.e).lambda$loadAndBind$4((LinkedHashMap) this.f, (LinkedHashMap) this.g);
                return;
            case 26:
                ((StoriesOnDemandDelegate) this.e).lambda$onLoadedCandidatePeopleBy$5((Supplier) this.f, (Map) this.g);
                return;
            case 27:
                ((PresentationViewPagerHolder) this.e).bindImage((Bitmap) this.f, (MediaItem) this.g);
                return;
            case 28:
                ((StoriesPinAdapter) this.e).lambda$bindThumbnail$1((ListViewHolder) this.f, (Bitmap) this.g);
                return;
            default:
                ((StoriesPinModel) this.e).lambda$loadCachedData$5((StoriesPinData) this.f, (StoriesPinCache) this.g);
                return;
        }
    }
}
