package com.samsung.android.sum.core.controller;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.message.Event;
import com.samsung.android.sum.core.message.Request;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MediaController<T> extends AutoCloseable {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnEventListener {
        void onEvent(Event event);
    }

    void close() {
        release();
    }

    void quitNow();

    void quitSafely();

    @Deprecated
    void release();

    T request(Request request);

    T run(MediaBuffer mediaBuffer) {
        return run((List<MediaBuffer>) new ArrayList<MediaBuffer>(mediaBuffer) {
            final /* synthetic */ MediaBuffer val$mediabuffer;

            {
                this.val$mediabuffer = r2;
                add(r2);
            }
        }, (List<MediaBuffer>) new ArrayList());
    }

    T run(List<MediaBuffer> list, List<MediaBuffer> list2);

    void setOnEventListener(OnEventListener onEventListener);

    T run(MediaBuffer mediaBuffer, MediaBuffer mediaBuffer2) {
        return run((List<MediaBuffer>) new ArrayList<MediaBuffer>(mediaBuffer) {
            final /* synthetic */ MediaBuffer val$inBuffer;

            {
                this.val$inBuffer = r2;
                add(r2);
            }
        }, (List<MediaBuffer>) new ArrayList<MediaBuffer>(mediaBuffer2) {
            final /* synthetic */ MediaBuffer val$outBuffer;

            {
                this.val$outBuffer = r2;
                add(r2);
            }
        });
    }

    T run(List<MediaBuffer> list) {
        return run(list, (List<MediaBuffer>) new ArrayList());
    }
}
