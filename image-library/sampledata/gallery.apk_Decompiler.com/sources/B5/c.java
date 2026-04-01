package B5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.work.OperationKt;
import androidx.work.Tracer;
import bd.a;
import com.samsung.android.gallery.app.activity.external.launcher.LocalAlbumViewLauncher;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.DownloadType;
import com.samsung.android.gallery.app.controller.viewer.DirectorsViewEditCmd;
import com.samsung.android.gallery.app.controller.viewer.DownloadForViewerCmd;
import com.samsung.android.gallery.app.controller.viewer.DownloadNdeOriginalCmd;
import com.samsung.android.gallery.app.controller.viewer.MultiDownloadForViewerCmd;
import com.samsung.android.gallery.app.controller.viewer.SaveCaptureCmd;
import com.samsung.android.gallery.app.provider.SharedItemUploader;
import com.samsung.android.gallery.app.receiver.BackupAndRestoreReceiver;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.dragdrop.AlbumsDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.dragdrop.DexDragAndDrop;
import com.samsung.android.gallery.app.ui.list.picker.pictures.PicturesPickerAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureSelectPresenterV2;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.app.ui.list.search.pictures.relationship.RelationshipPickerLauncher;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.PdcRecommendDelegate;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.selection.HideSceneSelectionViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular.IrregularView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ILargeImage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.LargeImageLoader;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsListHandler;
import com.samsung.android.gallery.app.ui.viewer2.menu.FavoriteMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.album.ShortcutHelper;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureFileHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaDataStoriesV7;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.search.engine.ExtraResults;
import com.samsung.android.gallery.module.search.engine.IntelligentExpandedSearchEngine;
import com.samsung.android.gallery.module.search.engine.PersonalDataCore;
import com.samsung.android.gallery.module.service.download.DownloadSyncMgr;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.o3dp.lib3dphotography.MeshCompressOption;
import com.samsung.o3dp.lib3dphotography.O3DPHeif3DListener;
import com.samsung.o3dp.lib3dphotography.O3DPJpeg3DListener;
import com.samsung.o3dp.lib3dphotography.O3DPhotoPipeline;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2265h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Object f2266i;

    public /* synthetic */ c(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
        this.f2265h = obj4;
        this.f2266i = obj5;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((RelationshipPickerLauncher) this.e).lambda$launch$1((Supplier) this.f, (ISearchPicturesView) this.g, (Map) this.f2265h, (Object[]) this.f2266i);
                return;
            case 1:
                ((ShortcutHelper) this.e).lambda$setTaskEdgeShortcut$3((MediaItem) this.f, (ShortcutHelper.UseType) this.g, (Activity) this.f2265h, (Bitmap) this.f2266i);
                return;
            case 2:
                ((LocalAlbumViewLauncher) this.e).lambda$loadLocalAlbumItem$0((Uri) this.f, (MediaItem[]) this.g, (AtomicInteger) this.f2265h, (CountDownLatch) this.f2266i);
                return;
            case 3:
                ((BaseCommand) this.e).lambda$executeAfterDownload$4((EventContext) this.f, (MediaItem) this.g, (Consumer) this.f2265h, (DownloadType) this.f2266i);
                return;
            case 4:
                ((DetailsListHandler) this.e).lambda$updateDetailsItem$7((TimeTickLog) this.f, (MediaItem) this.g, (DetailsLoadResult) this.f2265h, (DetailsLoadResult) this.f2266i);
                return;
            case 5:
                ((ObjectCaptureFileHandler) this.e).lambda$getClipData$1((SecureFile) this.f, (Bitmap) this.g, (SecureFile) this.f2265h, (MediaItem) this.f2266i);
                return;
            case 6:
                ((FavoriteMenuItem) this.e).lambda$onMenuSelectInternal$2((Activity) this.f, (Blackboard) this.g, (MediaItem) this.f2265h, (String) this.f2266i);
                return;
            case 7:
                ((ViewerMenuItem) this.e).lambda$executeAfterDownload$26((DownloadType) this.f, (EventContext) this.g, (MediaItem) this.f2265h, (Consumer) this.f2266i);
                return;
            case 8:
                ((AlbumsDragAndDropDelegate) this.e).lambda$prepareStartDragOnBG$13((View) this.f, (MediaItem[]) this.g, (MediaItem[]) this.f2265h, (ListViewHolder) this.f2266i);
                return;
            case 9:
                ((DexDragAndDrop) this.e).lambda$handleDropFromExternal$0((IBaseListView) this.f, (ArrayList) this.g, (MediaItem) this.f2265h, (DragEvent) this.f2266i);
                return;
            case 10:
                ((DirectorsViewEditCmd) this.e).lambda$executeAfterDownload$4((DownloadSyncMgr) this.f, (MediaItem) this.g, (EventContext) this.f2265h, (Consumer) this.f2266i);
                return;
            case 11:
                ((DownloadForViewerCmd) this.e).lambda$onDataCompleted$1((MediaItem) this.f, (Consumer) this.g, (DownloadType) this.f2265h, (DownloadSyncMgr) this.f2266i);
                return;
            case 12:
                ((DownloadNdeOriginalCmd) this.e).lambda$onDataCompleted$1((MediaItem) this.f, (Consumer) this.g, (DownloadType) this.f2265h, (DownloadSyncMgr) this.f2266i);
                return;
            case 13:
                ((MultiDownloadForViewerCmd) this.e).lambda$onDataCompleted$1((MediaItem[]) this.f, (Consumer) this.g, (DownloadType) this.f2265h, (DownloadSyncMgr) this.f2266i);
                return;
            case 14:
                ((SaveCaptureCmd) this.e).lambda$executeCapture$0((MediaItem) this.f, (ImageView) this.g, (Rect) this.f2265h, (Rect) this.f2266i);
                return;
            case 15:
                ((IntelligentExpandedSearchEngine) this.e).lambda$getSearchExpandedResult$5((Cursor[]) this.f, (SearchFilter) this.g, (ExtraResults) this.f2265h, (AtomicReference) this.f2266i);
                return;
            case 16:
                ((PersonalDataCore) this.e).lambda$loadCandidatePeopleBy$2((Context) this.f, (String) this.g, (LinkedHashMap) this.f2265h, (String) this.f2266i);
                return;
            case 17:
                ((SharedItemUploader) this.e).lambda$run$3((String) this.f, (String) this.g, (Bundle) this.f2265h, (Context) this.f2266i);
                return;
            case 18:
                ((BackupAndRestoreReceiver) this.e).lambda$onReceive$0((Context) this.f, (Intent) this.g, (String) this.f2265h, (String) this.f2266i);
                return;
            case 19:
                ((PicturesPickerAdapter) this.e).lambda$restoreClipboard$0((TimeTickLog) this.f, (Runnable) this.g, (LinkedHashSet) this.f2265h, (LinkedHashSet) this.f2266i);
                return;
            case 20:
                ((PdcRecommendDelegate) this.e).lambda$apply$8((ArrayList) this.f, (String) this.g, (Blackboard) this.f2265h, (ArrayList) this.f2266i);
                return;
            case 21:
                ((CollageAdapter) this.e).lambda$onThumbnailLoadCompleted$3((ListViewHolder) this.f, (MediaItem) this.g, (Bitmap) this.f2265h, (ThumbKind) this.f2266i);
                return;
            case 22:
                ((MediaDataStoriesV7) this.e).lambda$swap$1((Cursor[]) this.f, (Cursor[]) this.g, (ArrayList) this.f2265h, (ArrayList) this.f2266i);
                return;
            case 23:
                ((O3DPhotoPipeline) this.e).lambda$recordJpeg3d$2((Bitmap) this.f, (MeshCompressOption) this.g, (String) this.f2265h, (O3DPJpeg3DListener) this.f2266i);
                return;
            case 24:
                ((O3DPhotoPipeline) this.e).lambda$recordHeif3d$3((Bitmap) this.f, (MeshCompressOption) this.g, (String) this.f2265h, (O3DPHeif3DListener) this.f2266i);
                return;
            case 25:
                ((HideSceneSelectionViewAdapter) this.e).lambda$restoreClipboard$3((a) this.f, (TimeTickLog) this.g, (LinkedHashSet) this.f2265h, (LinkedHashSet) this.f2266i);
                return;
            case 26:
                OperationKt.launchOperation$lambda$2$lambda$1((Tracer) this.e, (String) this.f, (Ae.a) this.g, (MutableLiveData) this.f2265h, (CallbackToFutureAdapter.Completer) this.f2266i);
                return;
            case 27:
                ((CreatureSelectPresenterV2) this.e).lambda$createAutoAlbum$2((String) this.f, (ArrayList) this.g, (ArrayList) this.f2265h, (ArrayList) this.f2266i);
                return;
            case 28:
                ((IrregularView) this.e).lambda$onThumbnailLoadCompleted$3((Bitmap) this.f, (MediaItem) this.g, (ImageView) this.f2265h, (RectF) this.f2266i);
                return;
            default:
                ((LargeImageLoader) this.e).lambda$bindOriginImage$1((Bitmap) this.f, (MediaItem) this.g, (ILargeImage) this.f2265h, (Filter) this.f2266i);
                return;
        }
    }
}
