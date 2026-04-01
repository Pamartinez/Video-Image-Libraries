package com.samsung.android.sum.core.service;

import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Pair;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.controller.MediaController;
import com.samsung.android.sum.core.graph.MFDescriptorGraph;
import com.samsung.android.sum.core.message.Request;
import com.samsung.android.sum.core.message.Response;
import com.samsung.android.sum.core.message.ResponseHolder;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RemoteService extends ServiceStub implements ServiceController, MediaController.OnEventListener {
    /* access modifiers changed from: private */
    public static final String TAG = Def.tagOf((Class<?>) RemoteService.class);
    protected AtomicReference<Timer> exitTimer = new AtomicReference<>((Object) null);
    protected final Map<Integer, Messenger> replyListeners = new HashMap();
    protected Messenger requestMessenger = new Messenger(new IncommingHandler(this));

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class IncommingHandler extends Handler {
        private final WeakReference<RemoteService> weakRefService;

        public IncommingHandler(RemoteService remoteService) {
            super(Looper.getMainLooper());
            this.weakRefService = new WeakReference<>(remoteService);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            String access$000 = RemoteService.TAG;
            SLog.d(access$000, "handleMessage: msg=" + message + " on " + Thread.currentThread().getId());
            message.getData().setClassLoader(MFDescriptorGraph.class.getClassLoader());
            Request of2 = Request.of(message);
            int intValue = ((Integer) of2.get("id", 0)).intValue();
            if (this.weakRefService.get() != null) {
                this.weakRefService.get().request(intValue, of2);
            }
        }
    }

    public IBinder onBind(Intent intent) {
        SLog.i(TAG, "onBind");
        Timer timer = this.exitTimer.get();
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        this.exitTimer.set((Object) null);
        onIntentReceived(intent);
        return null;
    }

    public void onRebind(Intent intent) {
        SLog.i(TAG, "onRebind");
        Timer timer = this.exitTimer.get();
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        this.exitTimer.set((Object) null);
        onIntentReceived(intent);
        super.onRebind(intent);
    }

    public boolean onUnbind(Intent intent) {
        String str = TAG;
        SLog.i(str, "onUnbind: " + intent);
        if (this.isDaemon && this.mediaFilterControllers.isEmpty()) {
            Pair pair = new Pair(30, TimeUnit.MINUTES);
            SLog.i(str, "all clients are disconnected, run exit-timer[" + pair.first + " " + pair.second + " to stop service");
            Timer timer = new Timer("Self-stop ".concat(getClass().getSimpleName()));
            this.exitTimer.set(timer);
            timer.schedule(new TimerTask() {
                public void run() {
                    RemoteService.this.stopSelf();
                }
            }, ((TimeUnit) pair.second).toMillis((long) ((Integer) pair.first).intValue()));
        }
        return super.onUnbind(intent);
    }

    public ResponseHolder request(int i2, Request request) {
        ResponseHolder request2 = super.request(i2, request);
        if (!request2.contains()) {
            int code = request.getCode();
            if (code == 705) {
                int createMediaFilterController = createMediaFilterController();
                this.replyListeners.put(Integer.valueOf(createMediaFilterController), request.getResponseReceiver());
                Response of2 = Response.of((com.samsung.android.sum.core.message.Message) request);
                of2.put("id", Integer.valueOf(createMediaFilterController));
                request2.put(of2);
            } else if (code == 706) {
                releaseMediaFilterController(i2);
                Response of3 = Response.of((com.samsung.android.sum.core.message.Message) request);
                Messenger remove = this.replyListeners.remove(Integer.valueOf(i2));
                if (remove != null) {
                    of3.setResponseReceiver(remove);
                }
                request2.put(of3);
                return request2;
            }
        }
        return request2;
    }
}
