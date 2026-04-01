package U5;

import J5.c;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.WindowInsets;
import android.widget.TextView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCaptureToolbar;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ToolbarMenuItem;
import com.samsung.android.gallery.app.controller.viewer.SaveDualPhotoCmd;
import com.samsung.android.gallery.app.controller.viewer.StartDirectorsViewDualEditCmd;
import com.samsung.android.gallery.app.receiver.BackupAndRestoreReceiver;
import com.samsung.android.gallery.app.service.MediaCaptureService;
import com.samsung.android.gallery.app.service.StoryBaseService;
import com.samsung.android.gallery.app.service.VideoConversionService;
import com.samsung.android.gallery.app.ui.list.picker.search.suggestion.SuggestionContainerPickerFragment;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.EventListener;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent;
import com.samsung.android.gallery.app.ui.list.stories.category.helper.StoryLauncher;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.OnDemandFloatingItemAdapter;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.StoriesOnDemandDelegate;
import com.samsung.android.gallery.app.ui.list.stories.header.StoriesPinAdapter;
import com.samsung.android.gallery.app.ui.list.stories.header.StoriesPinModel;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageItemPicker;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate.PeopleDataDelegate;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dynamicview.ClipInfo;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.module.dynamicview.Highlight;
import com.samsung.android.gallery.module.lottie.recap.binder.RecapImageAssetDelegateForRecorder;
import com.samsung.android.gallery.module.map.abstraction.MapPickerContainer;
import com.samsung.android.gallery.module.map.manager.AddressCompat;
import com.samsung.android.gallery.module.search.engine.PersonalDataCore;
import com.samsung.android.gallery.module.story.StoryUriBuilder;
import com.samsung.android.gallery.module.story.ondemand.OnDemandResultData;
import com.samsung.android.gallery.module.utils.GalleryPreferenceEntry;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.SqliteCaseBuilder;
import com.samsung.android.gallery.widget.search.searchbar.autocomplete.FilterAutoCompleteView;
import com.samsung.android.gallery.widget.simpleslideshow.SimpleSlideShowAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.Function;
import org.json.JSONArray;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ b(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((EventListener) obj).onHandleInternalEvent((InternalEvent) this.e, (Object[]) this.f);
                return;
            case 1:
                ((SaveDualPhotoCmd) this.e).lambda$onExecute$0((String) this.f, (Boolean) obj);
                return;
            case 2:
                ((StartDirectorsViewDualEditCmd) this.e).lambda$onExecute$0((MediaItem[]) this.f, (Boolean) obj);
                return;
            case 3:
                ((ArrayList) this.e).add((Long) ((HashMap) this.f).get((Long) obj));
                return;
            case 4:
                PersonalDataCore.lambda$composePdcInfoForKeywordSearch$9((JSONArray) this.e, (JSONArray) this.f, (String) obj);
                return;
            case 5:
                ((LinkedHashMap) this.e).put((String) this.f, (List) obj);
                return;
            case 6:
                ((FilterAutoCompleteView) this.e).lambda$initView$0((Activity) this.f, (RecyclerView) obj);
                return;
            case 7:
                ((Context) this.e).grantUriPermission((String) obj, (Uri) this.f, 1);
                return;
            case 8:
                BackupAndRestoreReceiver.lambda$restoreGalleryPreferenceEntry$3((GalleryPreferenceEntry) this.e, (String) this.f, (Blackboard) obj);
                return;
            case 9:
                ((PeopleDataDelegate) this.e).lambda$onPageSelected$0((MediaItem) this.f, (ArrayList) obj);
                return;
            case 10:
                ((SimpleSlideShowAdapter) this.e).lambda$preparePeopleData$2((MediaItem) this.f, (ArrayList) obj);
                return;
            case 11:
                ((StoryLauncher) this.e).lambda$launchRecapViewer$1((StoryUriBuilder) this.f, (String) obj);
                return;
            case 12:
                ((OnDemandFloatingItemAdapter) this.e).lambda$onCreateViewHolder$0((Integer) this.f, (OnDemandFloatingItemAdapter.FloatingItemClickListener) obj);
                return;
            case 13:
                ((LinkedHashMap) this.e).put((String) obj, (List) ((Map) this.f).remove((String) obj));
                return;
            case 14:
                ((StoriesOnDemandDelegate) this.e).lambda$startOnDemand$1((String) this.f, (OnDemandResultData) obj);
                return;
            case 15:
                ((StoriesOnDemandDelegate) this.e).lambda$handleConfirmRelation$4((c) this.f, (Map) obj);
                return;
            case 16:
                ((StringJoiner) this.e).add(String.valueOf(((Function) this.f).apply((FileItemInterface) obj)));
                return;
            case 17:
                ((StoriesPinAdapter) this.e).lambda$bindItemInfo$0((MediaItem) this.f, (TextView) obj);
                return;
            case 18:
                ((StoriesPinModel) this.e).lambda$notifyDataRangeChanged$2((List) this.f, (MediaItem) obj);
                return;
            case 19:
                ((ArrayList) this.e).add((MediaItem) this.f);
                return;
            case 20:
                ((MediaCaptureService) this.e).lambda$notifyResultForShare$13((FileItemInterface) this.f, (Blackboard) obj);
                return;
            case 21:
                ((StoryBaseService) this.e).lambda$onPrepareDataService$2((DynamicViewPlayInfo) this.f, (DynamicViewPlayInfo) obj);
                return;
            case 22:
                ((VideoConversionService) this.e).lambda$notifyResult$8((FileItemInterface) this.f, (Blackboard) obj);
                return;
            case 23:
                ((SuggestionContainerPickerFragment) this.e).lambda$onApplyWindowInsets$0((View) this.f, (WindowInsets) obj);
                return;
            case 24:
                ObjectCaptureToolbar.doShow$lambda$0((ArrayList) this.e, (MenuBuilder) this.f, (ToolbarMenuItem) obj);
                return;
            case 25:
                ((ArrayList) this.e).add(((CollageItemPicker.Content) obj).item);
                return;
            case 26:
                ScreenShotFilterType.lambda$projection$5((SqliteCaseBuilder) this.e, (String) this.f, (ScreenShotFilterType) obj);
                return;
            case 27:
                ((Highlight) this.e).lambda$getPlayList$0((ArrayList) this.f, (ClipInfo) obj);
                return;
            case 28:
                ((RecapImageAssetDelegateForRecorder) this.e).lambda$freeUnusedBitmap$4((String) this.f, (String) obj);
                return;
            default:
                ((MapPickerContainer) this.e).lambda$startTitleTask$0((Context) this.f, (AddressCompat) obj);
                return;
        }
    }
}
