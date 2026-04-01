package com.samsung.android.sum.core.service;

import Tc.a;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.ConditionVariable;
import android.os.IBinder;
import bc.C0584a;
import c4.C0438h;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.controller.MediaController;
import com.samsung.android.sum.core.functional.ExceptionHandler;
import com.samsung.android.sum.core.message.Event;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.message.Request;
import com.samsung.android.sum.core.message.Response;
import com.samsung.android.sum.core.message.ResponseHolder;
import com.samsung.android.sum.core.service.LocalService;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LocalServiceProxy implements ServiceProxy, MediaController.OnEventListener {
    /* access modifiers changed from: private */
    public static final String TAG = Def.tagOf((Class<?>) LocalServiceProxy.class);
    private ServiceConnection connection;
    private final Context context;
    private WeakReference<MediaController.OnEventListener> eventListener;
    private ExceptionHandler exceptionHandler;
    /* access modifiers changed from: private */
    public LocalService localService;
    /* access modifiers changed from: private */
    public int mediaFilterControllerId;
    /* access modifiers changed from: private */
    public final ConditionVariable mfControllerSync = new ConditionVariable();
    private final BlockingQueue<Request> requestChannel = new LinkedBlockingQueue();
    private Future<Void> requestJob;
    private ExecutorService requestThreadPool = Executors.newCachedThreadPool();
    private final List<ResponseHolder> responseList = new CopyOnWriteArrayList();

    public LocalServiceProxy(Context context2, Class<?> cls, Map<Integer, Object> map) {
        this.context = context2;
        this.connection = new ServiceConnection() {
            public void onBindingDied(ComponentName componentName) {
                super.onBindingDied(componentName);
            }

            public void onNullBinding(ComponentName componentName) {
                super.onNullBinding(componentName);
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                LocalService unused = LocalServiceProxy.this.localService = ((LocalService.LocalBinder) iBinder).getService();
                LocalServiceProxy.this.localService.setEventListener(this);
                LocalServiceProxy localServiceProxy = LocalServiceProxy.this;
                int unused2 = localServiceProxy.mediaFilterControllerId = localServiceProxy.localService.createMediaFilterController();
                LocalServiceProxy.this.mfControllerSync.open();
            }

            public void onServiceDisconnected(ComponentName componentName) {
                SLog.e(LocalServiceProxy.TAG, "onServiceDisconnected E");
                LocalServiceProxy.this.onError(Response.of(-4, new IllegalStateException("service disconnected abnormally")));
                SLog.e(LocalServiceProxy.TAG, "onServiceDisconnected X");
            }
        };
        this.requestJob = this.requestThreadPool.submit(new C0584a(24, this));
        Intent intent = new Intent(context2, cls);
        if (map.containsKey(0)) {
            intent.setAction(ServiceProxy.ACTION_START_FOREGROUND);
        }
        if (map.containsKey(1)) {
            context2.startService(intent);
        }
        boolean bindService = context2.bindService(intent, this.connection, 1);
        String str = TAG;
        SLog.d(str, "success to bind: " + bindService);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        this.mfControllerSync.block();
        while (true) {
            try {
                Request take = this.requestChannel.take();
                String str = TAG;
                SLog.d(str, "take request: " + take);
                Response response = this.localService.request(this.mediaFilterControllerId, take).get();
                if (response.getResponseListener() != null) {
                    response.getResponseListener().accept(response);
                }
            } catch (InterruptedException unused) {
                SLog.w(TAG, "request canceled or release");
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onError$5(Response response, Exception exc, ResponseHolder responseHolder) {
        String str = TAG;
        SLog.e(str, "send response(" + response.getCode() + ") for request(" + responseHolder.getCode() + ")");
        StringBuilder sb2 = new StringBuilder("\tmessage: ");
        sb2.append((String) response.get("message", ""));
        SLog.e(str, sb2.toString());
        if (responseHolder.get() != null) {
            responseHolder.get().setException(exc);
        } else {
            responseHolder.put(Response.of(-4, exc));
        }
        responseHolder.signal();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onWarn$4(Response response, ResponseHolder responseHolder) {
        String str = TAG;
        SLog.w(str, "send response(" + response.getCode() + ") for request(" + responseHolder.getCode() + ")");
        StringBuilder sb2 = new StringBuilder("\tmessage: ");
        sb2.append((String) response.get("message", ""));
        SLog.w(str, sb2.toString());
        responseHolder.put(response);
        responseHolder.signal();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$release$3(ResponseHolder responseHolder) {
        String str = TAG;
        SLog.i(str, "send canceled response for " + responseHolder.getCode() + " to finish up releasing");
        responseHolder.put(Response.of((int) Message.WARN_CANCELED));
        responseHolder.signal();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$request$1(ResponseHolder responseHolder, Message message) {
        responseHolder.put((Response) message);
        responseHolder.signal();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Response lambda$request$2(Request request, ResponseHolder responseHolder) {
        ExceptionHandler exceptionHandler2;
        try {
            if (!request.isOneWay()) {
                String str = TAG;
                SLog.d(str, "wait response...E: " + request.getCode());
                responseHolder.await();
                SLog.d(str, "wait response...X: " + request.getCode());
            }
        } catch (Exception e) {
            if (responseHolder.get() != null) {
                responseHolder.get().setException(e);
            } else {
                e.printStackTrace();
            }
        }
        this.responseList.remove(responseHolder);
        Response reset = responseHolder.reset();
        if (reset.getException() == null || ((exceptionHandler2 = this.exceptionHandler) != null && exceptionHandler2.accept(reset.getException()))) {
            if (request.getOnReplyListener() != null) {
                request.getOnReplyListener().accept(reset.get());
            }
            return reset;
        }
        throw reset.getException();
    }

    /* access modifiers changed from: private */
    public void onError(Response response) {
        Exception exception = response.getException();
        ExceptionHandler exceptionHandler2 = this.exceptionHandler;
        if (exceptionHandler2 != null) {
            exceptionHandler2.accept(exception);
        } else {
            this.responseList.forEach(new b(response, exception, 0));
        }
    }

    private void onWarn(Response response) {
        String str = TAG;
        SLog.d(str, "onWarn: " + response);
        this.responseList.forEach(new c(response, 0));
    }

    public IBinder getBinder() {
        return this.localService.binder;
    }

    public ExceptionHandler getExceptionHandler() {
        return this.exceptionHandler;
    }

    public void onEvent(Event event) {
        String str = TAG;
        SLog.d(str, "onEvent: " + event);
        Response of2 = Response.of((Message) event);
        if (of2.isError()) {
            onError(of2);
        } else if (of2.isWarn()) {
            onWarn(of2);
        } else {
            MediaController.OnEventListener onEventListener = this.eventListener.get();
            if (onEventListener != null) {
                onEventListener.onEvent(event);
            }
        }
    }

    public void release() {
        String str = TAG;
        SLog.d(str, "release E");
        LocalService localService2 = this.localService;
        if (localService2 != null) {
            localService2.releaseMediaFilterController(this.mediaFilterControllerId);
        }
        if (this.connection != null) {
            SLog.d(str, "try to unbind");
            try {
                this.context.unbindService(this.connection);
            } catch (NoSuchElementException e) {
                String str2 = TAG;
                SLog.w(str2, "broken connection: " + e.getMessage());
            }
            this.connection = null;
        }
        this.responseList.forEach(new C0438h(14));
        Future<Void> future = this.requestJob;
        if (future != null) {
            future.cancel(true);
            this.requestJob = null;
        }
        ExecutorService executorService = this.requestThreadPool;
        if (executorService != null) {
            executorService.shutdown();
            this.requestThreadPool = null;
        }
        SLog.d(TAG, "release X");
    }

    public Future<Response> request(Request request) {
        ResponseHolder responseHolder = new ResponseHolder(request.getCode());
        this.responseList.add(responseHolder);
        try {
            if (request.isOneWay()) {
                responseHolder.put(Response.of(0));
            } else {
                String str = TAG;
                SLog.d(str, "add response-listener for " + request.getCode());
                request.then(new a(responseHolder, 0));
            }
            this.requestChannel.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.requestThreadPool.submit(new a((Object) this, (Object) request, (Object) responseHolder, 7));
    }

    public void setEventListener(MediaController.OnEventListener onEventListener) {
        this.eventListener = new WeakReference<>(onEventListener);
    }

    public void setExceptionHandler(ExceptionHandler exceptionHandler2) {
        this.exceptionHandler = exceptionHandler2;
    }
}
