package com.samsung.android.sum.core.controller;

import Qa.a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.controller.MediaController;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.functional.ExceptionHandler;
import com.samsung.android.sum.core.graph.MFDescriptorGraph;
import com.samsung.android.sum.core.message.Event;
import com.samsung.android.sum.core.message.Request;
import com.samsung.android.sum.core.message.Response;
import com.samsung.android.sum.core.service.ServiceProxy;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SumClient implements MediaController<Future<Response>>, MediaController.OnEventListener {
    private static final String TAG = Def.tagOf((Class<?>) SumClient.class);
    private MediaController.OnEventListener eventListener;
    private final MFDescriptorGraph graph;
    private volatile ServiceProxy serviceProxy;

    /* JADX WARNING: type inference failed for: r0v3, types: [com.samsung.android.sum.core.functional.ExceptionHandler, java.lang.Object] */
    public SumClient(ServiceProxy serviceProxy2, MFDescriptorGraph mFDescriptorGraph) {
        this.serviceProxy = serviceProxy2;
        this.graph = mFDescriptorGraph;
        if (!mFDescriptorGraph.getOption().contains(7)) {
            serviceProxy2.setExceptionHandler(new Object());
        }
        serviceProxy2.setEventListener(this);
        serviceProxy2.request(Request.of(700, "graph", mFDescriptorGraph).asOneWay());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$new$0(Exception exc) {
        String str = TAG;
        SLog.d(str, "ignore exception: " + ((String) Optional.ofNullable(exc).map(new g(2)).orElse("n/a")));
        return true;
    }

    public ExceptionHandler getExceptionHandler() {
        return this.serviceProxy.getExceptionHandler();
    }

    public MFDescriptorGraph getGraph() {
        return this.graph;
    }

    public void onEvent(Event event) {
        String str = TAG;
        SLog.d(str, "onEvent:  " + event);
        if (event.isError()) {
            SLog.d(str, "error occur, do force-release: " + event.getException());
        } else if (event.getCode() == 301) {
            SLog.d(str, "create graph on reconnect service");
            this.serviceProxy.request(Request.of(700, "graph", this.graph).asOneWay());
        }
        MediaController.OnEventListener onEventListener = this.eventListener;
        if (onEventListener != null) {
            onEventListener.onEvent(event);
        }
    }

    public void quitNow() {
        release();
    }

    public void quitSafely() {
        release();
    }

    public void release() {
        SLog.d(TAG, "release");
        if (this.serviceProxy != null) {
            synchronized (this.serviceProxy) {
                try {
                    if (this.serviceProxy != null) {
                        this.serviceProxy.release();
                        this.serviceProxy = null;
                        this.eventListener = null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
        this.serviceProxy.setExceptionHandler(exceptionHandler);
    }

    public void setOnEventListener(MediaController.OnEventListener onEventListener) {
        this.eventListener = onEventListener;
    }

    public Future<Response> request(Request request) {
        return (Future) Optional.ofNullable(this.serviceProxy).map(new d(2, request)).orElseGet(new i(1));
    }

    public Future<Response> run(List<MediaBuffer> list, MediaFormat mediaFormat) {
        MediaBuffer[] mediaBufferArr = new MediaBuffer[list.size()];
        Arrays.fill(mediaBufferArr, MediaBuffer.allocateHardware(mediaFormat));
        return run((List) list, Arrays.asList(mediaBufferArr));
    }

    public Future<Response> run(List<MediaBuffer> list, List<MediaBuffer> list2) {
        return (Future) Optional.ofNullable(this.serviceProxy).map(new a(6, (Object) list, (Object) list2)).orElseGet(new i(0));
    }
}
