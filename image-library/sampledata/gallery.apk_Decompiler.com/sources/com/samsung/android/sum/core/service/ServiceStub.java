package com.samsung.android.sum.core.service;

import A4.I;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.controller.MediaController;
import com.samsung.android.sum.core.controller.MediaFilterController;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.n;
import com.samsung.android.sum.core.graph.Graph;
import com.samsung.android.sum.core.graph.MFDescriptorGraph;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.message.Request;
import com.samsung.android.sum.core.message.Response;
import com.samsung.android.sum.core.message.ResponseHolder;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ServiceStub extends Service implements ServiceController, MediaController.OnEventListener {
    private static final String TAG = Def.tagOf((Class<?>) ServiceStub.class);
    protected final AtomicInteger controllerId = new AtomicInteger(0);
    protected boolean isDaemon = false;
    protected Map<Integer, MediaFilterController> mediaFilterControllers = new ConcurrentHashMap();

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$request$0(Request request, Integer num) {
        if (num.intValue() == request.getCode()) {
            return true;
        }
        return false;
    }

    public abstract Graph<MediaFilter> createGraph(MFDescriptorGraph mFDescriptorGraph);

    public int createMediaFilterController() {
        int incrementAndGet = this.controllerId.incrementAndGet();
        MediaFilterController mediaFilterController = new MediaFilterController(incrementAndGet);
        this.mediaFilterControllers.put(Integer.valueOf(incrementAndGet), mediaFilterController);
        mediaFilterController.setOnEventListener(this);
        return incrementAndGet;
    }

    public IBinder onBind(Intent intent) {
        onIntentReceived(intent);
        return null;
    }

    public void onDestroy() {
        this.isDaemon = false;
        super.onDestroy();
    }

    public void onIntentReceived(Intent intent) {
        String str = (String) Optional.ofNullable(intent).map(new n(11)).orElse("n/a");
        String str2 = TAG;
        SLog.d(str2, "intent: action=" + str);
        if (ServiceProxy.ACTION_START_FOREGROUND.equals(str)) {
            startForegroundService((Intent) intent.getParcelableExtra("activity-intent"));
        } else if (ServiceProxy.ACTION_STOP_FOREGROUND.equals(str)) {
            stopForegroundServiceStub();
        }
    }

    public void onRebind(Intent intent) {
        onIntentReceived(intent);
        super.onRebind(intent);
    }

    public int onStartCommand(Intent intent, int i2, int i7) {
        SLog.i(TAG, "onStartCommand");
        this.isDaemon = true;
        onIntentReceived(intent);
        return super.onStartCommand(intent, i2, i7);
    }

    public void releaseMediaFilterController(int i2) {
        MediaFilterController remove = this.mediaFilterControllers.remove(Integer.valueOf(i2));
        if (remove != null) {
            remove.release();
        }
    }

    public ResponseHolder request(int i2, Request request) {
        boolean z;
        ResponseHolder responseHolder = new ResponseHolder(request);
        MediaFilterController mediaFilterController = this.mediaFilterControllers.get(Integer.valueOf(i2));
        if (mediaFilterController != null || !Stream.of(new Integer[]{700, Integer.valueOf(Message.RELEASE_GRAPH)}).anyMatch(new I(23, request))) {
            int code = request.getCode();
            if (code == 700) {
                MFDescriptorGraph mFDescriptorGraph = (MFDescriptorGraph) request.get("graph");
                if (mFDescriptorGraph != null) {
                    z = true;
                } else {
                    z = false;
                }
                Def.check(z, "no descriptorGraph", new Object[0]);
                try {
                    mediaFilterController.setMediaFilterGraph(createGraph(mFDescriptorGraph));
                    responseHolder.put(Response.of((Message) request));
                    return responseHolder;
                } catch (NullPointerException e) {
                    SLog.w(TAG, "failed to create graph on " + e);
                    responseHolder.put(Response.of((int) Message.WARN_CANCELED));
                    return responseHolder;
                }
            } else if (code != 704) {
                return responseHolder;
            } else {
                mediaFilterController.release();
                responseHolder.put(Response.of((Message) request));
                return responseHolder;
            }
        } else {
            SLog.d(TAG, "no mediaFilterController given, maybe canceled");
            responseHolder.put(Response.of((int) Message.WARN_CANCELED));
            return responseHolder;
        }
    }

    public abstract void startForegroundServiceStub(Intent intent);

    public abstract void stopForegroundServiceStub();
}
