package androidx.media3.exoplayer.source;

import M.a;
import android.os.Handler;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CompositeMediaSource<T> extends BaseMediaSource {
    private final HashMap<T, MediaSourceAndListener<T>> childSources = new HashMap<>();
    private Handler eventHandler;
    private TransferListener mediaTransferListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class ForwardingEventListener implements MediaSourceEventListener, DrmSessionEventListener {
        private DrmSessionEventListener.EventDispatcher drmEventDispatcher;
        private final T id;
        private MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;

        public ForwardingEventListener(T t) {
            this.mediaSourceEventDispatcher = CompositeMediaSource.this.createEventDispatcher((MediaSource.MediaPeriodId) null);
            this.drmEventDispatcher = CompositeMediaSource.this.createDrmEventDispatcher((MediaSource.MediaPeriodId) null);
            this.id = t;
        }

        private boolean maybeUpdateEventDispatcher(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
            MediaSource.MediaPeriodId mediaPeriodId2;
            if (mediaPeriodId != null) {
                mediaPeriodId2 = CompositeMediaSource.this.getMediaPeriodIdForChildMediaPeriodId(this.id, mediaPeriodId);
                if (mediaPeriodId2 == null) {
                    return false;
                }
            } else {
                mediaPeriodId2 = null;
            }
            int windowIndexForChildWindowIndex = CompositeMediaSource.this.getWindowIndexForChildWindowIndex(this.id, i2);
            MediaSourceEventListener.EventDispatcher eventDispatcher = this.mediaSourceEventDispatcher;
            if (eventDispatcher.windowIndex != windowIndexForChildWindowIndex || !Objects.equals(eventDispatcher.mediaPeriodId, mediaPeriodId2)) {
                this.mediaSourceEventDispatcher = CompositeMediaSource.this.createEventDispatcher(windowIndexForChildWindowIndex, mediaPeriodId2);
            }
            DrmSessionEventListener.EventDispatcher eventDispatcher2 = this.drmEventDispatcher;
            if (eventDispatcher2.windowIndex == windowIndexForChildWindowIndex && Objects.equals(eventDispatcher2.mediaPeriodId, mediaPeriodId2)) {
                return true;
            }
            this.drmEventDispatcher = CompositeMediaSource.this.createDrmEventDispatcher(windowIndexForChildWindowIndex, mediaPeriodId2);
            return true;
        }

        private MediaLoadData maybeUpdateMediaLoadData(MediaLoadData mediaLoadData, MediaSource.MediaPeriodId mediaPeriodId) {
            MediaLoadData mediaLoadData2 = mediaLoadData;
            MediaSource.MediaPeriodId mediaPeriodId2 = mediaPeriodId;
            long mediaTimeForChildMediaTime = CompositeMediaSource.this.getMediaTimeForChildMediaTime(this.id, mediaLoadData2.mediaStartTimeMs, mediaPeriodId2);
            long mediaTimeForChildMediaTime2 = CompositeMediaSource.this.getMediaTimeForChildMediaTime(this.id, mediaLoadData2.mediaEndTimeMs, mediaPeriodId2);
            if (mediaTimeForChildMediaTime == mediaLoadData2.mediaStartTimeMs && mediaTimeForChildMediaTime2 == mediaLoadData2.mediaEndTimeMs) {
                return mediaLoadData2;
            }
            return new MediaLoadData(mediaLoadData2.dataType, mediaLoadData2.trackType, mediaLoadData2.trackFormat, mediaLoadData2.trackSelectionReason, mediaLoadData2.trackSelectionData, mediaTimeForChildMediaTime, mediaTimeForChildMediaTime2);
        }

        public void onDownstreamFormatChanged(int i2, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
            if (maybeUpdateEventDispatcher(i2, mediaPeriodId)) {
                this.mediaSourceEventDispatcher.downstreamFormatChanged(maybeUpdateMediaLoadData(mediaLoadData, mediaPeriodId));
            }
        }

        public void onDrmKeysLoaded(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
            if (maybeUpdateEventDispatcher(i2, mediaPeriodId)) {
                this.drmEventDispatcher.drmKeysLoaded();
            }
        }

        public void onDrmKeysRemoved(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
            if (maybeUpdateEventDispatcher(i2, mediaPeriodId)) {
                this.drmEventDispatcher.drmKeysRemoved();
            }
        }

        public void onDrmKeysRestored(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
            if (maybeUpdateEventDispatcher(i2, mediaPeriodId)) {
                this.drmEventDispatcher.drmKeysRestored();
            }
        }

        public void onDrmSessionAcquired(int i2, MediaSource.MediaPeriodId mediaPeriodId, int i7) {
            if (maybeUpdateEventDispatcher(i2, mediaPeriodId)) {
                this.drmEventDispatcher.drmSessionAcquired(i7);
            }
        }

        public void onDrmSessionManagerError(int i2, MediaSource.MediaPeriodId mediaPeriodId, Exception exc) {
            if (maybeUpdateEventDispatcher(i2, mediaPeriodId)) {
                this.drmEventDispatcher.drmSessionManagerError(exc);
            }
        }

        public void onDrmSessionReleased(int i2, MediaSource.MediaPeriodId mediaPeriodId) {
            if (maybeUpdateEventDispatcher(i2, mediaPeriodId)) {
                this.drmEventDispatcher.drmSessionReleased();
            }
        }

        public void onLoadCanceled(int i2, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            if (maybeUpdateEventDispatcher(i2, mediaPeriodId)) {
                this.mediaSourceEventDispatcher.loadCanceled(loadEventInfo, maybeUpdateMediaLoadData(mediaLoadData, mediaPeriodId));
            }
        }

        public void onLoadCompleted(int i2, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            if (maybeUpdateEventDispatcher(i2, mediaPeriodId)) {
                this.mediaSourceEventDispatcher.loadCompleted(loadEventInfo, maybeUpdateMediaLoadData(mediaLoadData, mediaPeriodId));
            }
        }

        public void onLoadError(int i2, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
            if (maybeUpdateEventDispatcher(i2, mediaPeriodId)) {
                this.mediaSourceEventDispatcher.loadError(loadEventInfo, maybeUpdateMediaLoadData(mediaLoadData, mediaPeriodId), iOException, z);
            }
        }

        public void onLoadStarted(int i2, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i7) {
            if (maybeUpdateEventDispatcher(i2, mediaPeriodId)) {
                this.mediaSourceEventDispatcher.loadStarted(loadEventInfo, maybeUpdateMediaLoadData(mediaLoadData, mediaPeriodId), i7);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MediaSourceAndListener<T> {
        public final MediaSource.MediaSourceCaller caller;
        public final CompositeMediaSource<T>.ForwardingEventListener eventListener;
        public final MediaSource mediaSource;

        public MediaSourceAndListener(MediaSource mediaSource2, MediaSource.MediaSourceCaller mediaSourceCaller, CompositeMediaSource<T>.ForwardingEventListener forwardingEventListener) {
            this.mediaSource = mediaSource2;
            this.caller = mediaSourceCaller;
            this.eventListener = forwardingEventListener;
        }
    }

    public void disableInternal() {
        for (MediaSourceAndListener next : this.childSources.values()) {
            next.mediaSource.disable(next.caller);
        }
    }

    public void enableInternal() {
        for (MediaSourceAndListener next : this.childSources.values()) {
            next.mediaSource.enable(next.caller);
        }
    }

    public abstract MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(T t, MediaSource.MediaPeriodId mediaPeriodId);

    public void maybeThrowSourceInfoRefreshError() {
        for (MediaSourceAndListener<T> mediaSourceAndListener : this.childSources.values()) {
            mediaSourceAndListener.mediaSource.maybeThrowSourceInfoRefreshError();
        }
    }

    /* renamed from: onChildSourceInfoRefreshed */
    public abstract void lambda$prepareChildSource$0(T t, MediaSource mediaSource, Timeline timeline);

    public final void prepareChildSource(T t, MediaSource mediaSource) {
        Assertions.checkArgument(!this.childSources.containsKey(t));
        a aVar = new a(this, t);
        ForwardingEventListener forwardingEventListener = new ForwardingEventListener(t);
        this.childSources.put(t, new MediaSourceAndListener(mediaSource, aVar, forwardingEventListener));
        mediaSource.addEventListener((Handler) Assertions.checkNotNull(this.eventHandler), forwardingEventListener);
        mediaSource.addDrmEventListener((Handler) Assertions.checkNotNull(this.eventHandler), forwardingEventListener);
        mediaSource.prepareSource(aVar, this.mediaTransferListener, getPlayerId());
        if (!isEnabled()) {
            mediaSource.disable(aVar);
        }
    }

    public void prepareSourceInternal(TransferListener transferListener) {
        this.mediaTransferListener = transferListener;
        this.eventHandler = Util.createHandlerForCurrentLooper();
    }

    public void releaseSourceInternal() {
        for (MediaSourceAndListener next : this.childSources.values()) {
            next.mediaSource.releaseSource(next.caller);
            next.mediaSource.removeEventListener(next.eventListener);
            next.mediaSource.removeDrmEventListener(next.eventListener);
        }
        this.childSources.clear();
    }

    public int getWindowIndexForChildWindowIndex(T t, int i2) {
        return i2;
    }

    public long getMediaTimeForChildMediaTime(T t, long j2, MediaSource.MediaPeriodId mediaPeriodId) {
        return j2;
    }
}
