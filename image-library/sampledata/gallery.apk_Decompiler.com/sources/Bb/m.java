package Bb;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import androidx.media3.common.Player;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.DefaultAnalyticsCollector;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import com.samsung.android.gallery.app.ui.list.search.editcreature.TagCreatureAdapter;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader.PreviewLoader;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import com.samsung.android.sdk.mobileservice.social.share.ShareApi;
import com.samsung.android.sdk.mobileservice.social.share.result.ItemListResult;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements ThumbnailLoadedListener, ShareApi.ShareBaseResultCallback, ListenerSet.Event, Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2784h;

    public /* synthetic */ m(Object obj, int i2, Object obj2, Object obj3, int i7) {
        this.d = i7;
        this.f = obj;
        this.e = i2;
        this.g = obj2;
        this.f2784h = obj3;
    }

    public void accept(Object obj) {
        ((MediaSourceEventListener.EventDispatcher) this.f).lambda$loadStarted$0((LoadEventInfo) this.g, (MediaLoadData) this.f2784h, this.e, (MediaSourceEventListener) obj);
    }

    public void invoke(Object obj) {
        switch (this.d) {
            case 3:
                DefaultAnalyticsCollector.lambda$onPositionDiscontinuity$46((AnalyticsListener.EventTime) this.f, this.e, (Player.PositionInfo) this.g, (Player.PositionInfo) this.f2784h, (AnalyticsListener) obj);
                return;
            default:
                DefaultAnalyticsCollector.lambda$onLoadStarted$26((AnalyticsListener.EventTime) this.f, (LoadEventInfo) this.g, (MediaLoadData) this.f2784h, this.e, (AnalyticsListener) obj);
                return;
        }
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        switch (this.d) {
            case 0:
                ((FilmStripViewHolder) this.f).lambda$setDefaultImageWithFilter$7((BiConsumer) this.g, (ImageView) this.f2784h, this.e, bitmap, uniqueKey, thumbKind);
                return;
            case 1:
                PreviewLoader previewLoader = (PreviewLoader) this.f;
                AtomicBoolean atomicBoolean = (AtomicBoolean) this.g;
                MediaItem mediaItem = (MediaItem) this.f2784h;
                previewLoader.lambda$loadThumbnail$3(this.e, atomicBoolean, mediaItem, bitmap, uniqueKey, thumbKind);
                return;
            default:
                ((TagCreatureAdapter) this.f).lambda$loadBitmap$1((CreatureNameData) this.g, (View) this.f2784h, this.e, bitmap, uniqueKey, thumbKind);
                return;
        }
    }

    public void onResult(Object obj) {
        MdeSharingHelper.lambda$requestItemDeletion$9((ArrayList) this.f, (AtomicInteger) this.f2784h, this.e, (BiConsumer) this.g, (ItemListResult) obj);
    }

    public /* synthetic */ m(Object obj, Object obj2, Object obj3, int i2, int i7) {
        this.d = i7;
        this.f = obj;
        this.g = obj2;
        this.f2784h = obj3;
        this.e = i2;
    }

    public /* synthetic */ m(ArrayList arrayList, AtomicInteger atomicInteger, int i2, BiConsumer biConsumer) {
        this.d = 2;
        this.f = arrayList;
        this.f2784h = atomicInteger;
        this.e = i2;
        this.g = biConsumer;
    }
}
