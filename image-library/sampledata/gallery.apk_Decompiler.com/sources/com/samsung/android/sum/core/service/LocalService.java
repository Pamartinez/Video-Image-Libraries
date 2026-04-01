package com.samsung.android.sum.core.service;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcelable;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.controller.MediaController;
import com.samsung.android.sum.core.filter.n;
import com.samsung.android.sum.core.message.Event;
import java.util.Arrays;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LocalService extends ServiceStub {
    private static final String TAG = Def.tagOf((Class<?>) LocalService.class);
    protected Binder binder = new LocalBinder();
    protected MediaController.OnEventListener eventListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public LocalService getService() {
            return LocalService.this;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaBuffer lambda$onEvent$0(Parcelable parcelable) {
        return (MediaBuffer) parcelable;
    }

    public IBinder onBind(Intent intent) {
        super.onBind(intent);
        return this.binder;
    }

    public void onEvent(Event event) {
        Parcelable[] parcelableArray;
        if (event.getCode() == 110 && (parcelableArray = event.toAndroidMessage().getData().getParcelableArray("buffer-list")) != null) {
            event.put("buffer-list", Arrays.stream(parcelableArray).map(new n(9)).collect(Collectors.toList()));
        }
    }

    public void setEventListener(MediaController.OnEventListener onEventListener) {
        this.eventListener = onEventListener;
    }

    public void startForegroundServiceStub(Intent intent) {
        throw new UnsupportedOperationException("Local Service do not this, if required, override it");
    }

    public void stopForegroundServiceStub() {
        throw new UnsupportedOperationException("Local Service do not this, if required, override it");
    }
}
