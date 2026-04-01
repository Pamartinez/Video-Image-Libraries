package H;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.metrics.NetworkEvent;
import android.media.metrics.PlaybackErrorEvent;
import android.media.metrics.PlaybackMetrics;
import android.media.metrics.PlaybackStateEvent;
import android.media.metrics.TrackChangeEvent;
import android.view.View;
import androidx.media3.effect.BaseGlShaderProgram;
import androidx.media3.effect.DefaultVideoFrameProcessor;
import androidx.media3.effect.MultipleInputVideoGraph;
import androidx.media3.exoplayer.analytics.MediaMetricsListener;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.FileOpCmd;
import com.samsung.android.gallery.app.controller.album.UpdateOrderCmd;
import com.samsung.android.gallery.app.controller.creature.PersonLinkCmd;
import com.samsung.android.gallery.app.controller.creature.UnmergeCreatureCmd;
import com.samsung.android.gallery.app.ui.list.albums.mx.all.MxAllAlbumsFragment;
import com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderView;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.QuickSharePrivacyTip;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsInvitationViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.recap.delegate.MediaPlayerViewDelegate;
import com.samsung.android.gallery.app.ui.list.stories.recap.lastpage.RecapPageDataLoader;
import com.samsung.android.gallery.app.ui.list.stories.spotify.SpotifySlideshowFragment;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.MediaViewPlayerHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.ShareVideoDownloadHandler;
import com.samsung.android.gallery.app.ui.viewer2.crop.CropViewFragment;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.livemotion.PeopleDataHelper;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ a(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((BaseGlShaderProgram) this.e).lambda$queueInputFrame$1((Exception) this.f);
                return;
            case 1:
                ((DefaultVideoFrameProcessor) this.e).lambda$registerInputStream$1((InterruptedException) this.f);
                return;
            case 2:
                ((MultipleInputVideoGraph) this.e).lambda$handleVideoFrameProcessingException$2((Exception) this.f);
                return;
            case 3:
                ((FileOpCmd) this.e).lambda$startDragToAlbumDialogCmd$13((String) this.f);
                return;
            case 4:
                ((UpdateOrderCmd) this.e).lambda$onExecute$0((EventContext) this.f);
                return;
            case 5:
                ((MediaPlayerViewDelegate) this.e).lambda$showPreview$1((Bitmap) this.f);
                return;
            case 6:
                ((MediaViewPlayerHandler) this.e).lambda$syncPrevAsMediaView$22((Bitmap) this.f);
                return;
            case 7:
                ((MediaViewPlayerHandler) this.e).lambda$disableKeepScreenOn$30((Activity) this.f);
                return;
            case 8:
                ((MediaViewPlayerHandler) this.e).lambda$runPreview$20((MediaItem) this.f);
                return;
            case 9:
                ((ShareVideoDownloadHandler) this.e).lambda$verify$0((MediaItem) this.f);
                return;
            case 10:
                ((Consumer) this.e).accept((CreatureNameData) this.f);
                return;
            case 11:
                ((PersonLinkCmd) this.e).lambda$onExecute$0((CreatureNameData) this.f);
                return;
            case 12:
                ((UnmergeCreatureCmd) this.e).lambda$undoMerge$6((String) this.f);
                return;
            case 13:
                ((UnmergeCreatureCmd) this.e).lambda$undoMerge$7((EventContext) this.f);
                return;
            case 14:
                ((RecapPageDataLoader) this.e).lambda$loadData$2((Consumer) this.f);
                return;
            case 15:
                ((CropViewFragment) this.e).lambda$onAnimationFrameUpdated$1((Bitmap) this.f);
                return;
            case 16:
                ((MediaMetricsListener) this.e).lambda$reportTrackChangeEvent$3((TrackChangeEvent) this.f);
                return;
            case 17:
                ((MediaMetricsListener) this.e).lambda$maybeReportNetworkChange$1((NetworkEvent) this.f);
                return;
            case 18:
                ((MediaMetricsListener) this.e).lambda$maybeReportPlaybackError$0((PlaybackErrorEvent) this.f);
                return;
            case 19:
                ((MediaMetricsListener) this.e).lambda$finishCurrentSession$4((PlaybackMetrics) this.f);
                return;
            case 20:
                ((MediaMetricsListener) this.e).lambda$maybeReportPlaybackStateChange$2((PlaybackStateEvent) this.f);
                return;
            case 21:
                ((MxAllAlbumsFragment) this.e).lambda$refreshAllAlbum$0((String) this.f);
                return;
            case 22:
                ((MxAlbumsHeaderView) this.e).lambda$setThumbnailOnHostIcon$6((Bitmap) this.f);
                return;
            case 23:
                ((SpotifySlideshowFragment) this.e).lambda$applyBlur$2((Bitmap) this.f);
                return;
            case 24:
                ((SharingsInvitationViewHolder) this.e).lambda$bindImage$2((Bitmap) this.f);
                return;
            case 25:
                ((PeopleDataHelper) this.e).lambda$prepare$0((MediaItem) this.f);
                return;
            case 26:
                ((androidx.media3.common.util.Consumer) this.e).accept((MediaSourceEventListener) this.f);
                return;
            case 27:
                ((AlbumPicturesFragment) this.e).lambda$updateAlbumSyncMenu$43((MediaItem) this.f);
                return;
            case 28:
                ((AlbumPicturesPresenter) this.e).lambda$turnOffMergedAlbum$21((MediaItem) this.f);
                return;
            default:
                ((QuickSharePrivacyTip) this.e).lambda$show$0((View) this.f);
                return;
        }
    }
}
