package B8;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.media3.common.TrackGroup;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.people.RelationshipPickerCmd;
import com.samsung.android.gallery.app.ui.list.hover.HoverHandler;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureSelectPresenterV2;
import com.samsung.android.gallery.app.ui.list.stories.highlight.ExportHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular.IrregularView;
import com.samsung.android.gallery.module.album.ShortcutHelper;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.story.transitory.StoriesViewPagerAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements ThumbnailLoadedListener, FutureListener, DefaultTrackSelector.TrackInfo.Factory, HoverHandler.DataLoadCallback, MediaPlayerCompat.OnPlayBackCompleteListener, MediaScannerListener, DataCollectionDelegate.OnDataCompletionListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2772h;

    public /* synthetic */ f(Object obj, Object obj2, MediaItem mediaItem, Object obj3, int i2) {
        this.d = i2;
        this.f = obj;
        this.g = obj2;
        this.e = mediaItem;
        this.f2772h = obj3;
    }

    public void a(ArrayList arrayList) {
        ((HoverHandler) this.f).lambda$loadHoverPreviewPopup$0((Context) this.e, (ListViewHolder) this.g, (ViewGroup) this.f2772h, arrayList);
    }

    public List create(int i2, TrackGroup trackGroup, int[] iArr) {
        return DefaultTrackSelector.lambda$selectVideoTrack$1((DefaultTrackSelector.Parameters) this.f, (String) this.e, (int[]) this.g, (Point) this.f2772h, i2, trackGroup, iArr);
    }

    public void onCompleted() {
        ((ExportHandler) this.f).lambda$moveToStorage$11((TimeTickLog) this.e, (String) this.g, (Consumer) this.f2772h);
    }

    public void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        ((CreatureSelectPresenterV2) this.f).lambda$createAutoAlbum$3((String) this.e, (ArrayList) this.g, (ArrayList) this.f2772h, eventContext, arrayList);
    }

    public void onFutureDone(Future future) {
        ((RelationshipPickerCmd) this.f).lambda$completed$2((MediaItem) this.e, (CreatureNameData) this.g, (AtomicLong) this.f2772h, future);
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        switch (this.d) {
            case 0:
                ((ShortcutHelper) this.f).lambda$setTaskEdgeShortcut$4((MediaItem) this.e, (ShortcutHelper.UseType) this.g, (Activity) this.f2772h, bitmap, uniqueKey, thumbKind);
                return;
            case 4:
                ((StoriesViewPagerAdapter) this.f).lambda$loadThumbnailWithKindCheck$0((ListViewHolder) this.g, (MediaItem) this.e, (ThumbKind) this.f2772h, bitmap, uniqueKey, thumbKind);
                return;
            default:
                ((IrregularView) this.f).lambda$bindImage$2((ImageView) this.g, (MediaItem) this.e, (RectF) this.f2772h, bitmap, uniqueKey, thumbKind);
                return;
        }
    }

    public void onPlaybackComplete(MediaPlayerCompat mediaPlayerCompat) {
        ((DynamicViewPlayInfo) this.f).lambda$initPlayBack$0((AtomicInteger) this.e, (MediaPlayerCompat) this.g, (DynamicViewPlayInfo.CallBack) this.f2772h, mediaPlayerCompat);
    }

    public /* synthetic */ f(Object obj, Object obj2, Object obj3, Object obj4, int i2) {
        this.d = i2;
        this.f = obj;
        this.e = obj2;
        this.g = obj3;
        this.f2772h = obj4;
    }
}
