package com.samsung.android.gallery.app.controller.sharing;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.mde.abstraction.ConnectListener;
import com.samsung.android.gallery.support.utils.Log;
import java.lang.ref.WeakReference;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingConnectionHelper {
    private static volatile SharingConnectionHelper sInstance;
    /* access modifiers changed from: private */
    public final LinkedBlockingQueue<RequestData> mRequestDataQueue = new LinkedBlockingQueue<>(10);
    /* access modifiers changed from: private */
    public boolean mWhileConnecting;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RequestData {
        WeakReference<EventContext> mHandlerRef;
        private Object[] mParams;

        public RequestData(EventContext eventContext, Object[] objArr) {
            this.mHandlerRef = new WeakReference<>(eventContext);
            this.mParams = objArr;
        }

        public EventContext getEventContext() {
            WeakReference<EventContext> weakReference = this.mHandlerRef;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        public Object[] getParams() {
            return this.mParams;
        }
    }

    private SharingConnectionHelper() {
    }

    public static SharingConnectionHelper getInstance() {
        if (sInstance == null) {
            synchronized (SharingConnectionHelper.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new SharingConnectionHelper();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    /* access modifiers changed from: private */
    public synchronized void processRequestOperation() {
        while (!this.mRequestDataQueue.isEmpty()) {
            try {
                RequestData poll = this.mRequestDataQueue.poll();
                if (!(poll == null || poll.getEventContext() == null)) {
                    new RequestSharedAlbumCmd().execute(poll.getEventContext(), poll.getParams());
                }
            } finally {
                while (true) {
                }
            }
        }
        this.mWhileConnecting = false;
    }

    public void connectSession(EventContext eventContext, Object... objArr) {
        if (this.mRequestDataQueue.remainingCapacity() <= 0) {
            Log.she("SharingConnectionHelper", "fail to add request data into QUEUE");
            return;
        }
        this.mRequestDataQueue.add(new RequestData(eventContext, objArr));
        if (!this.mWhileConnecting) {
            this.mWhileConnecting = true;
            Log.sh("SharingConnectionHelper", "Sync command added and connectSessionAsync called.");
            MdeSharingService.getInstance().connectSessionAsync(2, new ConnectListener() {
                public void onFailure(int i2) {
                    SharingConnectionHelper.this.mWhileConnecting = false;
                    SharingConnectionHelper.this.mRequestDataQueue.clear();
                }

                public void onSuccess() {
                    SharingConnectionHelper.this.processRequestOperation();
                }
            });
        }
    }
}
