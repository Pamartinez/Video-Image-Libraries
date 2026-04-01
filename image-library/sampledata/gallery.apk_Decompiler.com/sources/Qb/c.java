package Qb;

import S8.C0577a;
import Sd.A;
import Sd.C;
import Sd.e;
import Sd.y;
import android.graphics.ColorFilter;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Pair;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.ppp.PppChecker;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCreateFamilySpace;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCreateStory;
import com.samsung.android.gallery.app.controller.sharing.request.RequestDownloadForEditInSharing;
import com.samsung.android.gallery.app.controller.sharing.request.RequestDownloadForPlayInSharing;
import com.samsung.android.gallery.app.controller.sharing.request.RequestEmptyFromTrash;
import com.samsung.android.gallery.app.controller.sharing.request.RequestSpaceListSort;
import com.samsung.android.gallery.app.ui.list.dragdrop.AlbumsDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.dragdrop.SplitDragAndDropDelegate;
import com.samsung.android.gallery.app.ui.list.stories.category.StoriesCategory2HeaderAdapter;
import com.samsung.android.gallery.app.ui.list.stories.category.TopColorEffectHandler;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.EventListener;
import com.samsung.android.gallery.app.ui.list.timeline.TimelineViewAdapter;
import com.samsung.android.gallery.app.ui.list.timeline.quicksearch.QuickSearchView;
import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterLayoutHandler;
import com.samsung.android.gallery.app.ui.viewer2.remaster.RemasterViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.remaster.focusview.RemasterFocusViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.remaster.ondemand.RemasterProcessingViewHandler;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.cloud.a;
import com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadListener;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.library.v0.media.SemVideoTranscoderCompat;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.previewable.PreviewHdrVideo;
import com.samsung.android.sdk.mobileservice.social.share.result.ContentDownloadResult;
import java.util.Set;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ c(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((PreviewHdrVideo) obj2).lambda$loadVideoInfo$2((MediaHelper.VideoInfo) obj);
                return;
            case 1:
                ((PppChecker) obj2).onCompleted(obj);
                return;
            case 2:
                ((ImageViewHolder) obj).setColorFilter((ColorFilter) obj2);
                return;
            case 3:
                ((TimelineViewAdapter) obj2).lambda$onDataChanged$1((MediaData) obj);
                return;
            case 4:
                ((Set) obj2).remove((String) obj);
                return;
            case 5:
                ((SemVideoTranscoderCompat) obj2).onProgressChanged(((Integer) obj).intValue());
                return;
            case 6:
                ((QuickSearchView) obj2).lambda$bindView$0((View) obj);
                return;
            case 7:
                ((RemasterLayoutHandler) obj2).lambda$addPhotoViewPositionChangedListener$3((PointF) obj);
                return;
            case 8:
                ((RemasterViewerHolder) obj2).lambda$onInitialized$0((Float) obj);
                return;
            case 9:
                ((CloudDownloadListener) obj2).onProgress((long) ((Integer) obj).intValue(), 100);
                return;
            case 10:
                ((Bundle) obj2).putAll((Bundle) obj);
                return;
            case 11:
                ((C0577a) obj2).accept(new A((Bundle) obj));
                return;
            case 12:
                ((C0577a) obj2).accept(new C((Bundle) obj));
                return;
            case 13:
                ((C0577a) obj2).accept(new y((Bundle) obj));
                return;
            case 14:
                ((C0577a) obj2).accept(new e((Bundle) obj));
                return;
            case 15:
                ((a) obj2).accept(new e((Bundle) obj));
                return;
            case 16:
                ((a) obj2).accept(new y((Bundle) obj));
                return;
            case 17:
                ((RequestCreateFamilySpace) obj2).lambda$request$0((Blackboard) obj);
                return;
            case 18:
                ((RequestCreateStory) obj2).lambda$new$0((MediaItem) obj);
                return;
            case 19:
                ((RequestDownloadForEditInSharing) obj2).lambda$requestDownloadContents$0((ContentDownloadResult) obj);
                return;
            case 20:
                ((RequestDownloadForPlayInSharing) obj2).lambda$requestDownloadContents$0((ContentDownloadResult) obj);
                return;
            case 21:
                ((RequestEmptyFromTrash) obj2).lambda$request$1((Pair) obj);
                return;
            case 22:
                ((RequestSpaceListSort) obj2).lambda$request$0((String) obj);
                return;
            case 23:
                ((AlbumsDragAndDropDelegate) obj2).lambda$handleDrop$3((Boolean) obj);
                return;
            case 24:
                ((FragmentActivity) obj).requestDragAndDropPermissions((DragEvent) obj2);
                return;
            case 25:
                ((SplitDragAndDropDelegate) obj2).lambda$handleSplitViewAnimations$3((GalleryListView) obj);
                return;
            case 26:
                ((RemasterFocusViewHandler) obj2).lambda$new$4((RectF) obj);
                return;
            case 27:
                ((StoriesCategory2HeaderAdapter) obj2).lambda$handleDensityChange$1((EventListener) obj);
                return;
            case 28:
                ((TopColorEffectHandler) obj2).lambda$destroy$1((RecyclerView) obj);
                return;
            default:
                ((RemasterProcessingViewHandler) obj2).lambda$showLoadingIcon$6((ImageView) obj);
                return;
        }
    }
}
