package androidx.media3.exoplayer.source;

import A4.H;
import Bb.m;
import H.a;
import J.g;
import M.c;
import android.os.Handler;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MediaSourceEventListener {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EventDispatcher {
        private final CopyOnWriteArrayList<ListenerAndHandler> listenerAndHandlers;
        public final MediaSource.MediaPeriodId mediaPeriodId;
        public final int windowIndex;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class ListenerAndHandler {
            public Handler handler;
            public MediaSourceEventListener listener;

            public ListenerAndHandler(Handler handler2, MediaSourceEventListener mediaSourceEventListener) {
                this.handler = handler2;
                this.listener = mediaSourceEventListener;
            }
        }

        public EventDispatcher() {
            this(new CopyOnWriteArrayList(), 0, (MediaSource.MediaPeriodId) null);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$downstreamFormatChanged$5(MediaLoadData mediaLoadData, MediaSourceEventListener mediaSourceEventListener) {
            mediaSourceEventListener.onDownstreamFormatChanged(this.windowIndex, this.mediaPeriodId, mediaLoadData);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$loadCanceled$2(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, MediaSourceEventListener mediaSourceEventListener) {
            mediaSourceEventListener.onLoadCanceled(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$loadCompleted$1(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, MediaSourceEventListener mediaSourceEventListener) {
            mediaSourceEventListener.onLoadCompleted(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$loadError$3(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z, MediaSourceEventListener mediaSourceEventListener) {
            mediaSourceEventListener.onLoadError(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData, iOException, z);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$loadStarted$0(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i2, MediaSourceEventListener mediaSourceEventListener) {
            mediaSourceEventListener.onLoadStarted(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData, i2);
        }

        public void addEventListener(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
            Assertions.checkNotNull(handler);
            Assertions.checkNotNull(mediaSourceEventListener);
            this.listenerAndHandlers.add(new ListenerAndHandler(handler, mediaSourceEventListener));
        }

        public void dispatchEvent(Consumer<MediaSourceEventListener> consumer) {
            Iterator<ListenerAndHandler> it = this.listenerAndHandlers.iterator();
            while (it.hasNext()) {
                ListenerAndHandler next = it.next();
                Util.postOrRun(next.handler, new a(26, consumer, next.listener));
            }
        }

        public void downstreamFormatChanged(int i2, Format format, int i7, Object obj, long j2) {
            downstreamFormatChanged(new MediaLoadData(1, i2, format, i7, obj, Util.usToMs(j2), -9223372036854775807L));
        }

        public void loadCanceled(LoadEventInfo loadEventInfo, int i2, int i7, Format format, int i8, Object obj, long j2, long j3) {
            loadCanceled(loadEventInfo, new MediaLoadData(i2, i7, format, i8, obj, Util.usToMs(j2), Util.usToMs(j3)));
        }

        public void loadCompleted(LoadEventInfo loadEventInfo, int i2, int i7, Format format, int i8, Object obj, long j2, long j3) {
            loadCompleted(loadEventInfo, new MediaLoadData(i2, i7, format, i8, obj, Util.usToMs(j2), Util.usToMs(j3)));
        }

        public void loadError(LoadEventInfo loadEventInfo, int i2, int i7, Format format, int i8, Object obj, long j2, long j3, IOException iOException, boolean z) {
            loadError(loadEventInfo, new MediaLoadData(i2, i7, format, i8, obj, Util.usToMs(j2), Util.usToMs(j3)), iOException, z);
        }

        public void loadStarted(LoadEventInfo loadEventInfo, int i2, int i7, Format format, int i8, Object obj, long j2, long j3, int i10) {
            loadStarted(loadEventInfo, new MediaLoadData(i2, i7, format, i8, obj, Util.usToMs(j2), Util.usToMs(j3)), i10);
        }

        public void removeEventListener(MediaSourceEventListener mediaSourceEventListener) {
            Iterator<ListenerAndHandler> it = this.listenerAndHandlers.iterator();
            while (it.hasNext()) {
                ListenerAndHandler next = it.next();
                if (next.listener == mediaSourceEventListener) {
                    this.listenerAndHandlers.remove(next);
                }
            }
        }

        public EventDispatcher withParameters(int i2, MediaSource.MediaPeriodId mediaPeriodId2) {
            return new EventDispatcher(this.listenerAndHandlers, i2, mediaPeriodId2);
        }

        private EventDispatcher(CopyOnWriteArrayList<ListenerAndHandler> copyOnWriteArrayList, int i2, MediaSource.MediaPeriodId mediaPeriodId2) {
            this.listenerAndHandlers = copyOnWriteArrayList;
            this.windowIndex = i2;
            this.mediaPeriodId = mediaPeriodId2;
        }

        public void downstreamFormatChanged(MediaLoadData mediaLoadData) {
            dispatchEvent(new H(28, (Object) this, (Object) mediaLoadData));
        }

        public void loadCanceled(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            dispatchEvent(new c(this, loadEventInfo, mediaLoadData, 1));
        }

        public void loadCompleted(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            dispatchEvent(new c(this, loadEventInfo, mediaLoadData, 0));
        }

        public void loadError(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
            dispatchEvent(new g(1, (Object) this, (Object) loadEventInfo, (Object) mediaLoadData, (Object) iOException, z));
        }

        public void loadStarted(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i2) {
            dispatchEvent(new m((Object) this, (Object) loadEventInfo, (Object) mediaLoadData, i2, 5));
        }
    }

    void onDownstreamFormatChanged(int i2, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData);

    void onLoadCanceled(int i2, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData);

    void onLoadCompleted(int i2, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData);

    void onLoadError(int i2, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z);

    void onLoadStarted(int i2, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i7);
}
