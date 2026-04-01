package A4;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.ui.container.tablet.ListContainerFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.timeline.TimelineFragment;
import com.samsung.android.gallery.app.ui.list.timeline.TimelineViewAdapter;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.ImageLoader;
import com.samsung.android.gallery.module.cloud.CloudLog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.remotegallery.RemoteClient;
import com.samsung.android.gallery.module.thumbnail.VideoThumbnailAsyncLoader;
import com.samsung.android.gallery.plugins.motionphoto.VideoCtrlView;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.observable.ObservableList;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.handler.AdvancedMouseSelectionHandlerV2;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.previewable.PreviewHdrVideo;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.media.SemAsyncVideoFrameDecoder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class M implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2239h;

    public /* synthetic */ M(Object obj, int i2, int i7, Object obj2, int i8) {
        this.d = i8;
        this.g = obj;
        this.e = i2;
        this.f = i7;
        this.f2239h = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((BaseListViewAdapter) this.g).lambda$bindThumbnail$0((ListViewHolder) this.f2239h, this.e, this.f);
                return;
            case 1:
                ((VideoCtrlView) this.g).lambda$load$5(this.e, this.f, (ObservableList) this.f2239h);
                return;
            case 2:
                int i2 = this.f;
                ((ImageLoader) this.g).lambda$updateFullSizeBitmap$3(this.e, (MediaItem) this.f2239h, i2);
                return;
            case 3:
                ((AdvancedMouseSelectionHandlerV2) this.g).lambda$updateShiftKeySelection$0(this.e, this.f, (GalleryListAdapter) this.f2239h);
                return;
            case 4:
                CloudLog.lambda$sendLog$1((Context) this.g, (String) this.f2239h, this.e, this.f);
                return;
            case 5:
                ((PreviewHdrVideo) this.g).lambda$onError$6((MediaPlayerCompat) this.f2239h, this.e, this.f);
                return;
            case 6:
                ((TimelineFragment) this.g).lambda$startScrollAndShrink$3(this.e, this.f, this.f2239h);
                return;
            case 7:
                ((TimelineViewAdapter) this.g).lambda$onUpdateViewHolder$0(this.e, this.f, (ListViewHolder) this.f2239h);
                return;
            case 8:
                ((RemoteClient) this.g).lambda$query$6(this.e, this.f, (ArrayList) this.f2239h);
                return;
            case 9:
                View.OnClickListener onClickListener = (View.OnClickListener) this.f2239h;
                int i7 = this.f;
                ListContainerFragment.lambda$updateToolbarNavigation$8((GalleryToolbar) this.g, this.e, onClickListener, i7);
                return;
            default:
                ((VideoThumbnailAsyncLoader) this.g).lambda$onError$2(this.e, this.f, (SemAsyncVideoFrameDecoder) this.f2239h);
                return;
        }
    }

    public /* synthetic */ M(Object obj, int i2, Object obj2, int i7, int i8) {
        this.d = i8;
        this.g = obj;
        this.e = i2;
        this.f2239h = obj2;
        this.f = i7;
    }

    public /* synthetic */ M(Object obj, Object obj2, int i2, int i7, int i8) {
        this.d = i8;
        this.g = obj;
        this.f2239h = obj2;
        this.e = i2;
        this.f = i7;
    }
}
