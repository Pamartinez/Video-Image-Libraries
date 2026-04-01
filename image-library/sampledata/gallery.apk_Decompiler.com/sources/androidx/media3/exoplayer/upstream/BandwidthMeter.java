package androidx.media3.exoplayer.upstream;

import android.os.Handler;
import androidx.media3.common.util.Assertions;
import androidx.media3.datasource.TransferListener;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface BandwidthMeter {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface EventListener {

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class EventDispatcher {
            private final CopyOnWriteArrayList<HandlerAndListener> listeners = new CopyOnWriteArrayList<>();

            /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
            public static final class HandlerAndListener {
                /* access modifiers changed from: private */
                public final Handler handler;
                /* access modifiers changed from: private */
                public final EventListener listener;
                /* access modifiers changed from: private */
                public boolean released;

                public HandlerAndListener(Handler handler2, EventListener eventListener) {
                    this.handler = handler2;
                    this.listener = eventListener;
                }

                public void release() {
                    this.released = true;
                }
            }

            public void addListener(Handler handler, EventListener eventListener) {
                Assertions.checkNotNull(handler);
                Assertions.checkNotNull(eventListener);
                removeListener(eventListener);
                this.listeners.add(new HandlerAndListener(handler, eventListener));
            }

            public void bandwidthSample(int i2, long j2, long j3) {
                long j8;
                long j10;
                int i7;
                Iterator<HandlerAndListener> it = this.listeners.iterator();
                while (it.hasNext()) {
                    HandlerAndListener next = it.next();
                    if (!next.released) {
                        i7 = i2;
                        j10 = j2;
                        j8 = j3;
                        next.handler.post(new a(next, i7, j10, j8));
                    } else {
                        i7 = i2;
                        j10 = j2;
                        j8 = j3;
                    }
                    i2 = i7;
                    j2 = j10;
                    j3 = j8;
                }
            }

            public void removeListener(EventListener eventListener) {
                Iterator<HandlerAndListener> it = this.listeners.iterator();
                while (it.hasNext()) {
                    HandlerAndListener next = it.next();
                    if (next.listener == eventListener) {
                        next.release();
                        this.listeners.remove(next);
                    }
                }
            }
        }

        void onBandwidthSample(int i2, long j2, long j3);
    }

    void addEventListener(Handler handler, EventListener eventListener);

    TransferListener getTransferListener();

    void removeEventListener(EventListener eventListener);
}
