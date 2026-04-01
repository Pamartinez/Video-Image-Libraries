package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MaskingMediaPeriod;
import androidx.media3.exoplayer.source.MaskingMediaSource;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.ShuffleOrder;
import androidx.media3.exoplayer.upstream.Allocator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class MediaSourceList {
    private final HashMap<MediaSourceHolder, MediaSourceAndListener> childSources;
    private final Set<MediaSourceHolder> enabledMediaSourceHolders;
    /* access modifiers changed from: private */
    public final HandlerWrapper eventHandler;
    /* access modifiers changed from: private */
    public final AnalyticsCollector eventListener;
    private boolean isPrepared;
    private final IdentityHashMap<MediaPeriod, MediaSourceHolder> mediaSourceByMediaPeriod = new IdentityHashMap<>();
    private final Map<Object, MediaSourceHolder> mediaSourceByUid = new HashMap();
    private final List<MediaSourceHolder> mediaSourceHolders = new ArrayList();
    private final MediaSourceListInfoRefreshListener mediaSourceListInfoListener;
    private TransferListener mediaTransferListener;
    private final PlayerId playerId;
    private ShuffleOrder shuffleOrder = new ShuffleOrder.DefaultShuffleOrder(0);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class ForwardingEventListener implements MediaSourceEventListener, DrmSessionEventListener {
        private final MediaSourceHolder id;

        public ForwardingEventListener(MediaSourceHolder mediaSourceHolder) {
            this.id = mediaSourceHolder;
        }

        private Pair<Integer, MediaSource.MediaPeriodId> getEventParameters(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
            MediaSource.MediaPeriodId mediaPeriodId2 = null;
            if (mediaPeriodId != null) {
                MediaSource.MediaPeriodId access$100 = MediaSourceList.getMediaPeriodIdForChildMediaPeriodId(this.id, mediaPeriodId);
                if (access$100 == null) {
                    return null;
                }
                mediaPeriodId2 = access$100;
            }
            return Pair.create(Integer.valueOf(MediaSourceList.getWindowIndexForChildWindowIndex(this.id, i2)), mediaPeriodId2);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDownstreamFormatChanged$5(Pair pair, MediaLoadData mediaLoadData) {
            MediaSourceList.this.eventListener.onDownstreamFormatChanged(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, mediaLoadData);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDrmKeysLoaded$7(Pair pair) {
            MediaSourceList.this.eventListener.onDrmKeysLoaded(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDrmKeysRemoved$10(Pair pair) {
            MediaSourceList.this.eventListener.onDrmKeysRemoved(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDrmKeysRestored$9(Pair pair) {
            MediaSourceList.this.eventListener.onDrmKeysRestored(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDrmSessionAcquired$6(Pair pair, int i2) {
            MediaSourceList.this.eventListener.onDrmSessionAcquired(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, i2);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDrmSessionManagerError$8(Pair pair, Exception exc) {
            MediaSourceList.this.eventListener.onDrmSessionManagerError(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, exc);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDrmSessionReleased$11(Pair pair) {
            MediaSourceList.this.eventListener.onDrmSessionReleased(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onLoadCanceled$2(Pair pair, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            MediaSourceList.this.eventListener.onLoadCanceled(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, loadEventInfo, mediaLoadData);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onLoadCompleted$1(Pair pair, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            MediaSourceList.this.eventListener.onLoadCompleted(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, loadEventInfo, mediaLoadData);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onLoadError$3(Pair pair, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
            MediaSourceList.this.eventListener.onLoadError(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, loadEventInfo, mediaLoadData, iOException, z);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onLoadStarted$0(Pair pair, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i2) {
            MediaSourceList.this.eventListener.onLoadStarted(((Integer) pair.first).intValue(), (MediaSource.MediaPeriodId) pair.second, loadEventInfo, mediaLoadData, i2);
        }

        public void onDownstreamFormatChanged(int i2, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i2, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new r(this, eventParameters, mediaLoadData, 2));
            }
        }

        public void onDrmKeysLoaded(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i2, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new u(this, eventParameters, 0));
            }
        }

        public void onDrmKeysRemoved(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i2, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new u(this, eventParameters, 2));
            }
        }

        public void onDrmKeysRestored(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i2, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new u(this, eventParameters, 3));
            }
        }

        public void onDrmSessionAcquired(int i2, MediaSource.MediaPeriodId mediaPeriodId, int i7) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i2, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new v(this, eventParameters, i7));
            }
        }

        public void onDrmSessionManagerError(int i2, MediaSource.MediaPeriodId mediaPeriodId, Exception exc) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i2, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new r(this, eventParameters, exc, 1));
            }
        }

        public void onDrmSessionReleased(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i2, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new u(this, eventParameters, 1));
            }
        }

        public void onLoadCanceled(int i2, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i2, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new t(this, eventParameters, loadEventInfo, mediaLoadData, 0));
            }
        }

        public void onLoadCompleted(int i2, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i2, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new t(this, eventParameters, loadEventInfo, mediaLoadData, 1));
            }
        }

        public void onLoadError(int i2, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i2, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new x(this, eventParameters, loadEventInfo, mediaLoadData, iOException, z));
            }
        }

        public void onLoadStarted(int i2, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i7) {
            Pair<Integer, MediaSource.MediaPeriodId> eventParameters = getEventParameters(i2, mediaPeriodId);
            if (eventParameters != null) {
                MediaSourceList.this.eventHandler.post(new w(this, eventParameters, loadEventInfo, mediaLoadData, i7));
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MediaSourceAndListener {
        public final MediaSource.MediaSourceCaller caller;
        public final ForwardingEventListener eventListener;
        public final MediaSource mediaSource;

        public MediaSourceAndListener(MediaSource mediaSource2, MediaSource.MediaSourceCaller mediaSourceCaller, ForwardingEventListener forwardingEventListener) {
            this.mediaSource = mediaSource2;
            this.caller = mediaSourceCaller;
            this.eventListener = forwardingEventListener;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MediaSourceHolder implements MediaSourceInfoHolder {
        public final List<MediaSource.MediaPeriodId> activeMediaPeriodIds = new ArrayList();
        public int firstWindowIndexInChild;
        public boolean isRemoved;
        public final MaskingMediaSource mediaSource;
        public final Object uid = new Object();

        public MediaSourceHolder(MediaSource mediaSource2, boolean z) {
            this.mediaSource = new MaskingMediaSource(mediaSource2, z);
        }

        public Timeline getTimeline() {
            return this.mediaSource.getTimeline();
        }

        public Object getUid() {
            return this.uid;
        }

        public void reset(int i2) {
            this.firstWindowIndexInChild = i2;
            this.isRemoved = false;
            this.activeMediaPeriodIds.clear();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface MediaSourceListInfoRefreshListener {
        void onPlaylistUpdateRequested();
    }

    public MediaSourceList(MediaSourceListInfoRefreshListener mediaSourceListInfoRefreshListener, AnalyticsCollector analyticsCollector, HandlerWrapper handlerWrapper, PlayerId playerId2) {
        this.playerId = playerId2;
        this.mediaSourceListInfoListener = mediaSourceListInfoRefreshListener;
        this.eventListener = analyticsCollector;
        this.eventHandler = handlerWrapper;
        this.childSources = new HashMap<>();
        this.enabledMediaSourceHolders = new HashSet();
    }

    private void correctOffsets(int i2, int i7) {
        while (i2 < this.mediaSourceHolders.size()) {
            this.mediaSourceHolders.get(i2).firstWindowIndexInChild += i7;
            i2++;
        }
    }

    private void disableChildSource(MediaSourceHolder mediaSourceHolder) {
        MediaSourceAndListener mediaSourceAndListener = this.childSources.get(mediaSourceHolder);
        if (mediaSourceAndListener != null) {
            mediaSourceAndListener.mediaSource.disable(mediaSourceAndListener.caller);
        }
    }

    private void disableUnusedMediaSources() {
        Iterator<MediaSourceHolder> it = this.enabledMediaSourceHolders.iterator();
        while (it.hasNext()) {
            MediaSourceHolder next = it.next();
            if (next.activeMediaPeriodIds.isEmpty()) {
                disableChildSource(next);
                it.remove();
            }
        }
    }

    private void enableMediaSource(MediaSourceHolder mediaSourceHolder) {
        this.enabledMediaSourceHolders.add(mediaSourceHolder);
        MediaSourceAndListener mediaSourceAndListener = this.childSources.get(mediaSourceHolder);
        if (mediaSourceAndListener != null) {
            mediaSourceAndListener.mediaSource.enable(mediaSourceAndListener.caller);
        }
    }

    private static Object getChildPeriodUid(Object obj) {
        return AbstractConcatenatedTimeline.getChildPeriodUidFromConcatenatedUid(obj);
    }

    /* access modifiers changed from: private */
    public static MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSourceHolder mediaSourceHolder, MediaSource.MediaPeriodId mediaPeriodId) {
        for (int i2 = 0; i2 < mediaSourceHolder.activeMediaPeriodIds.size(); i2++) {
            if (mediaSourceHolder.activeMediaPeriodIds.get(i2).windowSequenceNumber == mediaPeriodId.windowSequenceNumber) {
                return mediaPeriodId.copyWithPeriodUid(getPeriodUid(mediaSourceHolder, mediaPeriodId.periodUid));
            }
        }
        return null;
    }

    private static Object getMediaSourceHolderUid(Object obj) {
        return AbstractConcatenatedTimeline.getChildTimelineUidFromConcatenatedUid(obj);
    }

    private static Object getPeriodUid(MediaSourceHolder mediaSourceHolder, Object obj) {
        return AbstractConcatenatedTimeline.getConcatenatedUid(mediaSourceHolder.uid, obj);
    }

    /* access modifiers changed from: private */
    public static int getWindowIndexForChildWindowIndex(MediaSourceHolder mediaSourceHolder, int i2) {
        return i2 + mediaSourceHolder.firstWindowIndexInChild;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareChildSource$0(MediaSource mediaSource, Timeline timeline) {
        this.mediaSourceListInfoListener.onPlaylistUpdateRequested();
    }

    private void maybeReleaseChildSource(MediaSourceHolder mediaSourceHolder) {
        if (mediaSourceHolder.isRemoved && mediaSourceHolder.activeMediaPeriodIds.isEmpty()) {
            MediaSourceAndListener mediaSourceAndListener = (MediaSourceAndListener) Assertions.checkNotNull(this.childSources.remove(mediaSourceHolder));
            mediaSourceAndListener.mediaSource.releaseSource(mediaSourceAndListener.caller);
            mediaSourceAndListener.mediaSource.removeEventListener(mediaSourceAndListener.eventListener);
            mediaSourceAndListener.mediaSource.removeDrmEventListener(mediaSourceAndListener.eventListener);
            this.enabledMediaSourceHolders.remove(mediaSourceHolder);
        }
    }

    private void prepareChildSource(MediaSourceHolder mediaSourceHolder) {
        MaskingMediaSource maskingMediaSource = mediaSourceHolder.mediaSource;
        s sVar = new s(this);
        ForwardingEventListener forwardingEventListener = new ForwardingEventListener(mediaSourceHolder);
        this.childSources.put(mediaSourceHolder, new MediaSourceAndListener(maskingMediaSource, sVar, forwardingEventListener));
        maskingMediaSource.addEventListener(Util.createHandlerForCurrentOrMainLooper(), forwardingEventListener);
        maskingMediaSource.addDrmEventListener(Util.createHandlerForCurrentOrMainLooper(), forwardingEventListener);
        maskingMediaSource.prepareSource(sVar, this.mediaTransferListener, this.playerId);
    }

    private void removeMediaSourcesInternal(int i2, int i7) {
        for (int i8 = i7 - 1; i8 >= i2; i8--) {
            MediaSourceHolder remove = this.mediaSourceHolders.remove(i8);
            this.mediaSourceByUid.remove(remove.uid);
            correctOffsets(i8, -remove.mediaSource.getTimeline().getWindowCount());
            remove.isRemoved = true;
            if (this.isPrepared) {
                maybeReleaseChildSource(remove);
            }
        }
    }

    public Timeline addMediaSources(int i2, List<MediaSourceHolder> list, ShuffleOrder shuffleOrder2) {
        if (!list.isEmpty()) {
            this.shuffleOrder = shuffleOrder2;
            for (int i7 = i2; i7 < list.size() + i2; i7++) {
                MediaSourceHolder mediaSourceHolder = list.get(i7 - i2);
                if (i7 > 0) {
                    MediaSourceHolder mediaSourceHolder2 = this.mediaSourceHolders.get(i7 - 1);
                    Timeline timeline = mediaSourceHolder2.mediaSource.getTimeline();
                    mediaSourceHolder.reset(timeline.getWindowCount() + mediaSourceHolder2.firstWindowIndexInChild);
                } else {
                    mediaSourceHolder.reset(0);
                }
                correctOffsets(i7, mediaSourceHolder.mediaSource.getTimeline().getWindowCount());
                this.mediaSourceHolders.add(i7, mediaSourceHolder);
                this.mediaSourceByUid.put(mediaSourceHolder.uid, mediaSourceHolder);
                if (this.isPrepared) {
                    prepareChildSource(mediaSourceHolder);
                    if (this.mediaSourceByMediaPeriod.isEmpty()) {
                        this.enabledMediaSourceHolders.add(mediaSourceHolder);
                    } else {
                        disableChildSource(mediaSourceHolder);
                    }
                }
            }
        }
        return createTimeline();
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j2) {
        Object mediaSourceHolderUid = getMediaSourceHolderUid(mediaPeriodId.periodUid);
        MediaSource.MediaPeriodId copyWithPeriodUid = mediaPeriodId.copyWithPeriodUid(getChildPeriodUid(mediaPeriodId.periodUid));
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) Assertions.checkNotNull(this.mediaSourceByUid.get(mediaSourceHolderUid));
        enableMediaSource(mediaSourceHolder);
        mediaSourceHolder.activeMediaPeriodIds.add(copyWithPeriodUid);
        MaskingMediaPeriod createPeriod = mediaSourceHolder.mediaSource.createPeriod(copyWithPeriodUid, allocator, j2);
        this.mediaSourceByMediaPeriod.put(createPeriod, mediaSourceHolder);
        disableUnusedMediaSources();
        return createPeriod;
    }

    public Timeline createTimeline() {
        if (this.mediaSourceHolders.isEmpty()) {
            return Timeline.EMPTY;
        }
        int i2 = 0;
        for (int i7 = 0; i7 < this.mediaSourceHolders.size(); i7++) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(i7);
            mediaSourceHolder.firstWindowIndexInChild = i2;
            i2 += mediaSourceHolder.mediaSource.getTimeline().getWindowCount();
        }
        return new PlaylistTimeline(this.mediaSourceHolders, this.shuffleOrder);
    }

    public ShuffleOrder getShuffleOrder() {
        return this.shuffleOrder;
    }

    public int getSize() {
        return this.mediaSourceHolders.size();
    }

    public boolean isPrepared() {
        return this.isPrepared;
    }

    public void prepare(TransferListener transferListener) {
        Assertions.checkState(!this.isPrepared);
        this.mediaTransferListener = transferListener;
        for (int i2 = 0; i2 < this.mediaSourceHolders.size(); i2++) {
            MediaSourceHolder mediaSourceHolder = this.mediaSourceHolders.get(i2);
            prepareChildSource(mediaSourceHolder);
            this.enabledMediaSourceHolders.add(mediaSourceHolder);
        }
        this.isPrepared = true;
    }

    public void release() {
        for (MediaSourceAndListener next : this.childSources.values()) {
            try {
                next.mediaSource.releaseSource(next.caller);
            } catch (RuntimeException e) {
                Log.e("MediaSourceList", "Failed to release child source.", e);
            }
            next.mediaSource.removeEventListener(next.eventListener);
            next.mediaSource.removeDrmEventListener(next.eventListener);
        }
        this.childSources.clear();
        this.enabledMediaSourceHolders.clear();
        this.isPrepared = false;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        MediaSourceHolder mediaSourceHolder = (MediaSourceHolder) Assertions.checkNotNull(this.mediaSourceByMediaPeriod.remove(mediaPeriod));
        mediaSourceHolder.mediaSource.releasePeriod(mediaPeriod);
        mediaSourceHolder.activeMediaPeriodIds.remove(((MaskingMediaPeriod) mediaPeriod).id);
        if (!this.mediaSourceByMediaPeriod.isEmpty()) {
            disableUnusedMediaSources();
        }
        maybeReleaseChildSource(mediaSourceHolder);
    }

    public Timeline removeMediaSourceRange(int i2, int i7, ShuffleOrder shuffleOrder2) {
        boolean z;
        if (i2 < 0 || i2 > i7 || i7 > getSize()) {
            z = false;
        } else {
            z = true;
        }
        Assertions.checkArgument(z);
        this.shuffleOrder = shuffleOrder2;
        removeMediaSourcesInternal(i2, i7);
        return createTimeline();
    }

    public Timeline setMediaSources(List<MediaSourceHolder> list, ShuffleOrder shuffleOrder2) {
        removeMediaSourcesInternal(0, this.mediaSourceHolders.size());
        return addMediaSources(this.mediaSourceHolders.size(), list, shuffleOrder2);
    }

    public Timeline setShuffleOrder(ShuffleOrder shuffleOrder2) {
        int size = getSize();
        if (shuffleOrder2.getLength() != size) {
            shuffleOrder2 = shuffleOrder2.cloneAndClear().cloneAndInsert(0, size);
        }
        this.shuffleOrder = shuffleOrder2;
        return createTimeline();
    }

    public Timeline updateMediaSourcesWithMediaItems(int i2, int i7, List<MediaItem> list) {
        boolean z;
        boolean z3 = false;
        if (i2 < 0 || i2 > i7 || i7 > getSize()) {
            z = false;
        } else {
            z = true;
        }
        Assertions.checkArgument(z);
        if (list.size() == i7 - i2) {
            z3 = true;
        }
        Assertions.checkArgument(z3);
        for (int i8 = i2; i8 < i7; i8++) {
            this.mediaSourceHolders.get(i8).mediaSource.updateMediaItem(list.get(i8 - i2));
        }
        return createTimeline();
    }
}
