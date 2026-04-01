package A6;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.hardware.usb.UsbDevice;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.activity.UsbAttachActivity;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.SaveObjectCaptureToAlbumCmd;
import com.samsung.android.gallery.app.controller.internals.RemoveHighlightCmd;
import com.samsung.android.gallery.app.controller.internals.SaveAsPdfCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestStreamingVideo;
import com.samsung.android.gallery.app.controller.story.AddToStoryCmd;
import com.samsung.android.gallery.app.controller.story.CreateStoryCmd;
import com.samsung.android.gallery.app.receiver.BackupAndRestoreReceiver;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.dragdrop.AlbumsDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.dragdrop.MultiControlDragAndDrop;
import com.samsung.android.gallery.app.ui.list.dragdrop.TwoHandedDragAndDrop;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.app.ui.list.search.pictures.relationship.RelationshipPickerLauncher;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchSelectedFiltersDelegate;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingHeaderViewDelegateV2;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatTransitoryItemOnDemandViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.ProcessingViewManager;
import com.samsung.android.gallery.app.ui.list.stories.pictures.cover.StoryCover;
import com.samsung.android.gallery.app.ui.list.stories.pictures.legacy.StoryPicturesLegacyFragment;
import com.samsung.android.gallery.app.ui.list.suggestions.SuggestionsImageViewHolder;
import com.samsung.android.gallery.app.ui.list.suggestions.SuggestionsItemAdapter;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.GroupLoader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.groupshot.AbsGroupItemLoader;
import com.samsung.android.gallery.app.ui.viewer2.grouppanel.list.GroupPanelListAdapter;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.ImageRegionDecoder;
import com.samsung.android.gallery.module.search.engine.ExtraResults;
import com.samsung.android.gallery.module.search.engine.IntelligentExpandedSearchEngine;
import com.samsung.android.gallery.module.search.engine.IntelligentSearchEngine;
import com.samsung.android.gallery.module.search.engine.PersonalDataCore;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.widget.listview.pinch.v3.BitmapCache;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.livemotion.LiveMotionAdapter;
import com.samsung.android.gallery.widget.photoview.ImageProcessor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2264h;

    public /* synthetic */ a(RecyclerView.Adapter adapter, Object obj, Object obj2, Bitmap bitmap, int i2) {
        this.d = i2;
        this.e = adapter;
        this.g = obj;
        this.f2264h = obj2;
        this.f = bitmap;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((StoryCover) this.e).lambda$onThumbnailLoadCompleted$0((Bitmap) this.f, (UniqueKey) this.g, (ThumbKind) this.f2264h);
                return;
            case 1:
                ((RelationshipPickerLauncher) this.e).lambda$launch$3((ISearchPicturesView) this.f, (List) this.g, (Object[]) this.f2264h);
                return;
            case 2:
                ((RelationshipPickerLauncher) this.e).lambda$showRelationShipPicker$5((String) this.f, (LinkedHashMap) this.g, (ISearchPicturesView) this.f2264h);
                return;
            case 3:
                ((UsbAttachActivity) this.e).lambda$onCreate$0((AtomicBoolean) this.f, (UsbDevice) this.g, (CountDownLatch) this.f2264h);
                return;
            case 4:
                ((StoryPicturesLegacyFragment) this.e).lambda$onThumbnailLoadCompleted$0((Bitmap) this.f, (UniqueKey) this.g, (ThumbKind) this.f2264h);
                return;
            case 5:
                ((AbsGroupItemLoader) this.e).lambda$invalidateSubItems$1((MediaItem) this.f, (MediaItem) this.g, (GroupLoader.SubItemLoadListener) this.f2264h);
                return;
            case 6:
                ((SaveObjectCaptureToAlbumCmd) this.e).lambda$saveFile$2((String) this.f, (String) this.g, (String) this.f2264h);
                return;
            case 7:
                ((SearchSelectedFiltersDelegate) this.e).lambda$postThumbnailTypeMainFilter$1((Bitmap) this.f, (String) this.g, (MediaItem) this.f2264h);
                return;
            case 8:
                ((BitmapCache) this.e).lambda$onThumbLoaded$1((String) this.g, (Bitmap) this.f, (ThumbnailInterface) this.f2264h);
                return;
            case 9:
                ((LiveMotionAdapter) this.e).lambda$onThumbnailLoadCompleted$2((Bitmap) this.f, (MediaItem) this.g, (ListViewHolder) this.f2264h);
                return;
            case 10:
                ((SuggestionsItemAdapter) this.e).lambda$onBindViewHolder$0((SuggestionsImageViewHolder) this.g, (MediaItem) this.f2264h, (Bitmap) this.f);
                return;
            case 11:
                ((ObjectCaptureHelper) this.e).lambda$onSaveGif$4((MediaItem) this.f, (String) this.g, (Context) this.f2264h);
                return;
            case 12:
                ((ImageProcessor) this.e).lambda$initTileTask$1((View) this.f, (Integer) this.g, (ImageRegionDecoder) this.f2264h);
                return;
            case 13:
                ((RemoveHighlightCmd) this.e).lambda$onExecute$0((MediaItem[]) this.f, (Object[]) this.g, (EventContext) this.f2264h);
                return;
            case 14:
                ((SaveAsPdfCmd) this.e).lambda$showConfirmDialogAndSave$2((EventContext) this.f, (String) this.g, (MediaItem[]) this.f2264h);
                return;
            case 15:
                ((SharingHeaderViewDelegateV2) this.e).lambda$bindImageToView$3((Bitmap) this.f, (UniqueKey) this.g, (ThumbKind) this.f2264h);
                return;
            case 16:
                ((GroupPanelListAdapter) this.e).lambda$loadOrDecode$1((MediaItem) this.g, (ImageViewHolder) this.f2264h, (Bitmap) this.f);
                return;
            case 17:
                ((RequestStreamingVideo) this.e).lambda$requestStreamingVideo$1((Integer) this.f, (Uri) this.g, (FileItemInterface) this.f2264h);
                return;
            case 18:
                ((AlbumsDragAndDropDelegate) this.e).lambda$startDragInner$0((View) this.f, (MediaItem[]) this.g, (ListViewHolder) this.f2264h);
                return;
            case 19:
                MultiControlDragAndDrop.lambda$handleDropFromExternal$0((ArrayList) this.e, (ArrayList) this.f, (IBaseListView) this.g, (MediaItem) this.f2264h);
                return;
            case 20:
                ((TwoHandedDragAndDrop) this.e).lambda$startFileOperation$1((IBaseListView) this.f, (ClipData) this.g, (MediaItem) this.f2264h);
                return;
            case 21:
                new AddToStoryCmd().execute((EventContext) this.e, (MediaItem) this.f, (MediaItem[]) this.g, (Long[]) this.f2264h);
                return;
            case 22:
                ((CreateStoryCmd) this.e).lambda$createStory$0((Context) this.f, (ArrayList) this.g, (String) this.f2264h);
                return;
            case 23:
                ((IntelligentExpandedSearchEngine) this.e).lambda$getSearchExpandedResult$6((SearchFilter) this.f, (QueryParams) this.g, (AtomicReference) this.f2264h);
                return;
            case 24:
                ((IntelligentSearchEngine) this.e).lambda$getIdsNExtraResult$6((Cursor) this.f, (ArrayList) this.g, (ExtraResults) this.f2264h);
                return;
            case 25:
                ((PersonalDataCore) this.e).lambda$getRecommendMap$12((Bundle) this.f, (String) this.g, (LinkedHashMap) this.f2264h);
                return;
            case 26:
                ((StoriesCatTransitoryItemOnDemandViewHolder) this.e).lambda$bindSearchToolbarAsync$1((ViewGroup) this.f, (View) this.g, (View) this.f2264h);
                return;
            case 27:
                ((BackupAndRestoreReceiver) this.e).lambda$onReceive$1((Context) this.f, (Intent) this.g, (String) this.f2264h);
                return;
            case 28:
                ((ProcessingViewManager) this.e).lambda$onSuccess$6((Bitmap) this.f, (MediaItem) this.g, (ImageView) this.f2264h);
                return;
            default:
                ((ProcessingViewManager) this.e).lambda$show$0((String) this.f, (ArrayList) this.g, (String) this.f2264h);
                return;
        }
    }

    public /* synthetic */ a(BitmapCache bitmapCache, String str, Bitmap bitmap, ThumbnailInterface thumbnailInterface) {
        this.d = 8;
        this.e = bitmapCache;
        this.g = str;
        this.f = bitmap;
        this.f2264h = thumbnailInterface;
    }

    public /* synthetic */ a(Object obj, Object obj2, Object obj3, Object obj4, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
        this.f2264h = obj4;
    }
}
