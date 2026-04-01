package com.samsung.android.sum.core.service;

import J5.c;
import Tc.a;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import bc.C0584a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.controller.MediaController;
import com.samsung.android.sum.core.descriptor.b;
import com.samsung.android.sum.core.filter.n;
import com.samsung.android.sum.core.functional.ExceptionHandler;
import com.samsung.android.sum.core.message.Event;
import com.samsung.android.sum.core.message.Request;
import com.samsung.android.sum.core.message.Response;
import com.samsung.android.sum.core.message.ResponseHolder;
import com.samsung.android.sum.core.types.ServiceStatus;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoteServiceProxy implements ServiceProxy {
    /* access modifiers changed from: private */
    public static final String TAG = Def.tagOf((Class<?>) RemoteServiceProxy.class);
    private ServiceConnection connection;
    private final Context context;
    private WeakReference<MediaController.OnEventListener> eventListener;
    private ExceptionHandler exceptionHandler;
    private int mediaFilterControllerId;
    private final ConditionVariable mfControllerSync;
    private final BlockingQueue<Request> requestChannel;
    private Future<Void> requestJob;
    /* access modifiers changed from: private */
    public Messenger requestMessenger;
    private ExecutorService requestThreadPool;
    private HandlerThread responseHandlerThread;
    private final List<ResponseHolder> responseList;
    /* access modifiers changed from: private */
    public Messenger responseMessenger;
    /* access modifiers changed from: private */
    public ServiceStatus status;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class IncomingHandler extends Handler {
        private final Consumer<Response> responseConsumer;

        public IncomingHandler(Consumer<Response> consumer, Looper looper) {
            super(looper);
            this.responseConsumer = consumer;
        }

        public void handleMessage(Message message) {
            String access$000 = RemoteServiceProxy.TAG;
            SLog.d(access$000, "receive message: " + message);
            this.responseConsumer.accept(Response.of(message));
        }
    }

    public RemoteServiceProxy(Context context2, Class<?> cls, Map<Integer, Object> map) {
        this(context2, (String) Optional.ofNullable(cls.getPackage()).map(new n(10)).orElse("n/a"), cls.getName(), map);
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
    public static /* synthetic */ void lambda$release$6(ResponseHolder responseHolder) {
        String str = TAG;
        SLog.i(str, "send canceled response for " + responseHolder.getCode() + " to finish up releasing");
        responseHolder.put(Response.of((int) com.samsung.android.sum.core.message.Message.WARN_CANCELED));
        responseHolder.signal();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$requestThenAwait$2(ResponseHolder responseHolder, com.samsung.android.sum.core.message.Message message) {
        responseHolder.put((Response) message);
        responseHolder.signal();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Response lambda$requestThenAwait$3(Request request, ResponseHolder responseHolder) {
        ExceptionHandler exceptionHandler2;
        try {
            String str = TAG;
            SLog.d(str, "wait response...E: " + request.getCode());
            responseHolder.await();
            SLog.d(str, "wait response...X: " + request.getCode());
        } catch (Exception e) {
            if (responseHolder.get() != null) {
                responseHolder.get().setException(e);
            } else {
                String str2 = TAG;
                SLog.w(str2, "fail to get response: " + e);
            }
        }
        this.responseList.remove(responseHolder);
        Response reset = responseHolder.reset();
        if (reset.getException() == null || ((exceptionHandler2 = this.exceptionHandler) != null && exceptionHandler2.accept(reset.getException()))) {
            if (request.getOnReplyListener() != null) {
                request.getOnReplyListener().accept(reset.get());
            }
            String str3 = TAG;
            SLog.d(str3, "request X: " + reset);
            return reset;
        }
        throw reset.getException();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startRequestThread$0() {
        this.mfControllerSync.block();
        while (true) {
            try {
                Request take = this.requestChannel.take();
                String str = TAG;
                SLog.d(str, "take request: " + take.getCode() + "[id=" + this.mediaFilterControllerId + "]");
                take.put("id", Integer.valueOf(this.mediaFilterControllerId));
                Message androidMessage = take.toAndroidMessage();
                if (!take.isOneWay()) {
                    take.setResponseReceiver(this.responseMessenger);
                }
                take.setReceiver(this.requestMessenger).post();
                SLog.d(str, "send message to remote: " + androidMessage);
            } catch (InterruptedException unused) {
                SLog.w(TAG, "request canceled or release");
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void onError(Response response) {
        Exception exception = response.getException();
        this.responseList.forEach(new b(response, exception, 1));
        ExceptionHandler exceptionHandler2 = this.exceptionHandler;
        if (exceptionHandler2 != null) {
            exceptionHandler2.accept(exception);
            return;
        }
        throw new RuntimeException(exception);
    }

    /* access modifiers changed from: private */
    public void onReceiveResponse(Response response) {
        String str = TAG;
        SLog.d(str, "onReceiveResponse: " + response);
        if (response.getResponseListener() != null) {
            SLog.d(str, "invoke response listener");
            response.getResponseListener().accept(response);
        }
        boolean booleanValue = ((Boolean) response.get(com.samsung.android.sum.core.message.Message.KEY_EVENT_NOTI, Boolean.FALSE)).booleanValue();
        if (response.getResponseListener() == null || booleanValue) {
            int code = response.getCode();
            if (code == 705) {
                this.mediaFilterControllerId = ((Integer) Optional.ofNullable((Integer) response.get("id")).orElse(-1)).intValue();
                this.mfControllerSync.open();
            } else if (code != 706) {
                if (!booleanValue) {
                    if (response.isError()) {
                        onError(response);
                    } else if (response.isWarn()) {
                        onWarn(response);
                    }
                }
                MediaController.OnEventListener onEventListener = this.eventListener.get();
                if (onEventListener != null) {
                    onEventListener.onEvent(Event.of(response));
                }
            } else {
                this.mfControllerSync.open();
            }
        }
    }

    /* access modifiers changed from: private */
    public void onServiceDisconnected() {
        SLog.d(TAG, "onServiceDisconnected");
        this.status = ServiceStatus.DISCONNECTED;
        this.mfControllerSync.close();
        this.requestJob.cancel(true);
        this.requestJob = null;
    }

    /* access modifiers changed from: private */
    public void onServiceReconnected() {
        SLog.i(TAG, "onServiceReconnected");
        startRequestThread();
        if (this.eventListener.get() != null) {
            this.eventListener.get().onEvent(Event.of(301));
        }
    }

    private void onWarn(Response response) {
        String str = TAG;
        SLog.d(str, "onWarn: " + response);
        this.responseList.forEach(new c(response, 1));
    }

    private Future<Response> requestOneWay(Request request) {
        this.requestChannel.put(request);
        Response of2 = Response.of(0);
        String str = TAG;
        SLog.d(str, "request X: " + of2);
        return CompletableFuture.supplyAsync(new c(23, of2));
    }

    private Future<Response> requestThenAwait(Request request) {
        ResponseHolder responseHolder = new ResponseHolder(request.getCode());
        this.responseList.add(responseHolder);
        request.then(new a(responseHolder, 1));
        this.requestChannel.put(request);
        return this.requestThreadPool.submit(new a((Object) this, (Object) request, (Object) responseHolder, 8));
    }

    private void startRequestThread() {
        this.requestJob = this.requestThreadPool.submit(new C0584a(25, this));
    }

    public IBinder getBinder() {
        return this.requestMessenger.getBinder();
    }

    public ExceptionHandler getExceptionHandler() {
        return this.exceptionHandler;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x007e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = TAG;
        com.samsung.android.sum.core.SLog.w(r1, "broken connection: " + r0.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00ce, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0148, code lost:
        if (r12.connection != null) goto L_0x014a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x014a, code lost:
        com.samsung.android.sum.core.SLog.i(TAG, "try to unbind");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        r12.context.unbindService(r12.connection);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0159, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        r3 = TAG;
        com.samsung.android.sum.core.SLog.w(r3, "broken connection: " + r2.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x016f, code lost:
        r12.connection = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0171, code lost:
        r12.responseList.forEach(new c4.C0438h(15));
        r0 = r12.requestJob;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x017f, code lost:
        if (r0 != null) goto L_0x0181;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0181, code lost:
        r0.cancel(true);
        r12.requestJob = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0186, code lost:
        r0 = r12.requestThreadPool;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0188, code lost:
        if (r0 != null) goto L_0x018a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x018a, code lost:
        r0.shutdownNow();
        r12.requestThreadPool = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x018f, code lost:
        r0 = r12.responseHandlerThread;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0191, code lost:
        if (r0 != null) goto L_0x0193;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0193, code lost:
        r0.quitSafely();
        r12.responseHandlerThread = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0198, code lost:
        r12.requestMessenger = null;
        r12.responseMessenger = null;
        r12.status = com.samsung.android.sum.core.types.ServiceStatus.NONE;
        com.samsung.android.sum.core.SLog.d(TAG, "release X");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01a7, code lost:
        throw r1;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:9:0x0073, B:29:0x00d2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void release() {
        /*
            r12 = this;
            java.lang.String r0 = "broken connection: "
            java.lang.String r1 = "broken connection: "
            java.lang.String r2 = "can't send message: "
            java.lang.String r3 = "broken connection: "
            java.lang.String r4 = "wait to release...X: "
            java.lang.String r5 = "release E: "
            monitor-enter(r12)
            r6 = 1
            r7 = 0
            java.lang.String r8 = TAG     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            r9.<init>(r5)     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            android.os.Messenger r5 = r12.requestMessenger     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            r9.append(r5)     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            java.lang.String r5 = r9.toString()     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            com.samsung.android.sum.core.SLog.d((java.lang.String) r8, (java.lang.String) r5)     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            android.os.ConditionVariable r5 = r12.mfControllerSync     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            r5.close()     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            r5 = 706(0x2c2, float:9.9E-43)
            com.samsung.android.sum.core.message.Request r5 = com.samsung.android.sum.core.message.Request.of((int) r5)     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            android.os.Messenger r9 = r12.responseMessenger     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            com.samsung.android.sum.core.message.Message r5 = r5.setResponseReceiver(r9)     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            java.lang.String r9 = "id"
            int r10 = r12.mediaFilterControllerId     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            com.samsung.android.sum.core.message.Message r5 = r5.put(r9, r10)     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            android.os.Message r5 = r5.toAndroidMessage()     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            android.os.Messenger r9 = r12.requestMessenger     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            r9.send(r5)     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            java.lang.String r5 = "wait to release...E"
            com.samsung.android.sum.core.SLog.d((java.lang.String) r8, (java.lang.String) r5)     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            android.os.ConditionVariable r5 = r12.mfControllerSync     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            r10 = 3
            long r9 = r9.toMillis(r10)     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            boolean r5 = r5.block(r9)     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            r9.<init>(r4)     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            r9.append(r5)     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            java.lang.String r4 = r9.toString()     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            com.samsung.android.sum.core.SLog.d((java.lang.String) r8, (java.lang.String) r4)     // Catch:{ RemoteException | NullPointerException -> 0x00d1 }
            android.content.ServiceConnection r0 = r12.connection     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x0096
            java.lang.String r0 = "try to unbind"
            com.samsung.android.sum.core.SLog.i((java.lang.String) r8, (java.lang.String) r0)     // Catch:{ all -> 0x007b }
            android.content.Context r0 = r12.context     // Catch:{ NoSuchElementException -> 0x007e }
            android.content.ServiceConnection r1 = r12.connection     // Catch:{ NoSuchElementException -> 0x007e }
            r0.unbindService(r1)     // Catch:{ NoSuchElementException -> 0x007e }
            goto L_0x0094
        L_0x007b:
            r0 = move-exception
            goto L_0x01a8
        L_0x007e:
            r0 = move-exception
            java.lang.String r1 = TAG     // Catch:{ all -> 0x007b }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x007b }
            r2.<init>(r3)     // Catch:{ all -> 0x007b }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x007b }
            r2.append(r0)     // Catch:{ all -> 0x007b }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x007b }
            com.samsung.android.sum.core.SLog.w((java.lang.String) r1, (java.lang.String) r0)     // Catch:{ all -> 0x007b }
        L_0x0094:
            r12.connection = r7     // Catch:{ all -> 0x007b }
        L_0x0096:
            java.util.List<com.samsung.android.sum.core.message.ResponseHolder> r0 = r12.responseList     // Catch:{ all -> 0x007b }
            c4.h r1 = new c4.h     // Catch:{ all -> 0x007b }
            r2 = 15
            r1.<init>(r2)     // Catch:{ all -> 0x007b }
            r0.forEach(r1)     // Catch:{ all -> 0x007b }
            java.util.concurrent.Future<java.lang.Void> r0 = r12.requestJob     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x00ab
            r0.cancel(r6)     // Catch:{ all -> 0x007b }
            r12.requestJob = r7     // Catch:{ all -> 0x007b }
        L_0x00ab:
            java.util.concurrent.ExecutorService r0 = r12.requestThreadPool     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x00b4
            r0.shutdownNow()     // Catch:{ all -> 0x007b }
            r12.requestThreadPool = r7     // Catch:{ all -> 0x007b }
        L_0x00b4:
            android.os.HandlerThread r0 = r12.responseHandlerThread     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x00bd
            r0.quitSafely()     // Catch:{ all -> 0x007b }
            r12.responseHandlerThread = r7     // Catch:{ all -> 0x007b }
        L_0x00bd:
            r12.requestMessenger = r7     // Catch:{ all -> 0x007b }
            r12.responseMessenger = r7     // Catch:{ all -> 0x007b }
            com.samsung.android.sum.core.types.ServiceStatus r0 = com.samsung.android.sum.core.types.ServiceStatus.NONE     // Catch:{ all -> 0x007b }
            r12.status = r0     // Catch:{ all -> 0x007b }
            java.lang.String r0 = TAG     // Catch:{ all -> 0x007b }
            goto L_0x0141
        L_0x00c9:
            com.samsung.android.sum.core.SLog.d((java.lang.String) r0, (java.lang.String) r1)     // Catch:{ all -> 0x007b }
            goto L_0x0144
        L_0x00ce:
            r1 = move-exception
            goto L_0x0146
        L_0x00d1:
            r3 = move-exception
            java.lang.String r4 = TAG     // Catch:{ all -> 0x00ce }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ce }
            r5.<init>(r2)     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = r3.getMessage()     // Catch:{ all -> 0x00ce }
            r5.append(r2)     // Catch:{ all -> 0x00ce }
            java.lang.String r2 = r5.toString()     // Catch:{ all -> 0x00ce }
            com.samsung.android.sum.core.SLog.w((java.lang.String) r4, (java.lang.String) r2)     // Catch:{ all -> 0x00ce }
            android.content.ServiceConnection r0 = r12.connection     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x0110
            java.lang.String r0 = "try to unbind"
            com.samsung.android.sum.core.SLog.i((java.lang.String) r4, (java.lang.String) r0)     // Catch:{ all -> 0x007b }
            android.content.Context r0 = r12.context     // Catch:{ NoSuchElementException -> 0x00f8 }
            android.content.ServiceConnection r2 = r12.connection     // Catch:{ NoSuchElementException -> 0x00f8 }
            r0.unbindService(r2)     // Catch:{ NoSuchElementException -> 0x00f8 }
            goto L_0x010e
        L_0x00f8:
            r0 = move-exception
            java.lang.String r2 = TAG     // Catch:{ all -> 0x007b }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x007b }
            r3.<init>(r1)     // Catch:{ all -> 0x007b }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x007b }
            r3.append(r0)     // Catch:{ all -> 0x007b }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x007b }
            com.samsung.android.sum.core.SLog.w((java.lang.String) r2, (java.lang.String) r0)     // Catch:{ all -> 0x007b }
        L_0x010e:
            r12.connection = r7     // Catch:{ all -> 0x007b }
        L_0x0110:
            java.util.List<com.samsung.android.sum.core.message.ResponseHolder> r0 = r12.responseList     // Catch:{ all -> 0x007b }
            c4.h r1 = new c4.h     // Catch:{ all -> 0x007b }
            r2 = 15
            r1.<init>(r2)     // Catch:{ all -> 0x007b }
            r0.forEach(r1)     // Catch:{ all -> 0x007b }
            java.util.concurrent.Future<java.lang.Void> r0 = r12.requestJob     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x0125
            r0.cancel(r6)     // Catch:{ all -> 0x007b }
            r12.requestJob = r7     // Catch:{ all -> 0x007b }
        L_0x0125:
            java.util.concurrent.ExecutorService r0 = r12.requestThreadPool     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x012e
            r0.shutdownNow()     // Catch:{ all -> 0x007b }
            r12.requestThreadPool = r7     // Catch:{ all -> 0x007b }
        L_0x012e:
            android.os.HandlerThread r0 = r12.responseHandlerThread     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x0137
            r0.quitSafely()     // Catch:{ all -> 0x007b }
            r12.responseHandlerThread = r7     // Catch:{ all -> 0x007b }
        L_0x0137:
            r12.requestMessenger = r7     // Catch:{ all -> 0x007b }
            r12.responseMessenger = r7     // Catch:{ all -> 0x007b }
            com.samsung.android.sum.core.types.ServiceStatus r0 = com.samsung.android.sum.core.types.ServiceStatus.NONE     // Catch:{ all -> 0x007b }
            r12.status = r0     // Catch:{ all -> 0x007b }
            java.lang.String r0 = TAG     // Catch:{ all -> 0x007b }
        L_0x0141:
            java.lang.String r1 = "release X"
            goto L_0x00c9
        L_0x0144:
            monitor-exit(r12)
            return
        L_0x0146:
            android.content.ServiceConnection r2 = r12.connection     // Catch:{ all -> 0x007b }
            if (r2 == 0) goto L_0x0171
            java.lang.String r2 = TAG     // Catch:{ all -> 0x007b }
            java.lang.String r3 = "try to unbind"
            com.samsung.android.sum.core.SLog.i((java.lang.String) r2, (java.lang.String) r3)     // Catch:{ all -> 0x007b }
            android.content.Context r2 = r12.context     // Catch:{ NoSuchElementException -> 0x0159 }
            android.content.ServiceConnection r3 = r12.connection     // Catch:{ NoSuchElementException -> 0x0159 }
            r2.unbindService(r3)     // Catch:{ NoSuchElementException -> 0x0159 }
            goto L_0x016f
        L_0x0159:
            r2 = move-exception
            java.lang.String r3 = TAG     // Catch:{ all -> 0x007b }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x007b }
            r4.<init>(r0)     // Catch:{ all -> 0x007b }
            java.lang.String r0 = r2.getMessage()     // Catch:{ all -> 0x007b }
            r4.append(r0)     // Catch:{ all -> 0x007b }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x007b }
            com.samsung.android.sum.core.SLog.w((java.lang.String) r3, (java.lang.String) r0)     // Catch:{ all -> 0x007b }
        L_0x016f:
            r12.connection = r7     // Catch:{ all -> 0x007b }
        L_0x0171:
            java.util.List<com.samsung.android.sum.core.message.ResponseHolder> r0 = r12.responseList     // Catch:{ all -> 0x007b }
            c4.h r2 = new c4.h     // Catch:{ all -> 0x007b }
            r3 = 15
            r2.<init>(r3)     // Catch:{ all -> 0x007b }
            r0.forEach(r2)     // Catch:{ all -> 0x007b }
            java.util.concurrent.Future<java.lang.Void> r0 = r12.requestJob     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x0186
            r0.cancel(r6)     // Catch:{ all -> 0x007b }
            r12.requestJob = r7     // Catch:{ all -> 0x007b }
        L_0x0186:
            java.util.concurrent.ExecutorService r0 = r12.requestThreadPool     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x018f
            r0.shutdownNow()     // Catch:{ all -> 0x007b }
            r12.requestThreadPool = r7     // Catch:{ all -> 0x007b }
        L_0x018f:
            android.os.HandlerThread r0 = r12.responseHandlerThread     // Catch:{ all -> 0x007b }
            if (r0 == 0) goto L_0x0198
            r0.quitSafely()     // Catch:{ all -> 0x007b }
            r12.responseHandlerThread = r7     // Catch:{ all -> 0x007b }
        L_0x0198:
            r12.requestMessenger = r7     // Catch:{ all -> 0x007b }
            r12.responseMessenger = r7     // Catch:{ all -> 0x007b }
            com.samsung.android.sum.core.types.ServiceStatus r0 = com.samsung.android.sum.core.types.ServiceStatus.NONE     // Catch:{ all -> 0x007b }
            r12.status = r0     // Catch:{ all -> 0x007b }
            java.lang.String r0 = TAG     // Catch:{ all -> 0x007b }
            java.lang.String r2 = "release X"
            com.samsung.android.sum.core.SLog.d((java.lang.String) r0, (java.lang.String) r2)     // Catch:{ all -> 0x007b }
            throw r1     // Catch:{ all -> 0x007b }
        L_0x01a8:
            monitor-exit(r12)     // Catch:{ all -> 0x007b }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.service.RemoteServiceProxy.release():void");
    }

    public Future<Response> request(Request request) {
        ServiceStatus serviceStatus = this.status;
        if (serviceStatus == ServiceStatus.DISCONNECTED || serviceStatus == ServiceStatus.DEAD) {
            String str = TAG;
            SLog.w(str, "service already died. ignore this. (code=" + request.getCode() + ")");
            return CompletableFuture.completedFuture(Response.of(-4));
        }
        String str2 = TAG;
        SLog.d(str2, "request: " + request);
        try {
            if (request.isOneWay()) {
                return requestOneWay(request);
            }
            return requestThenAwait(request);
        } catch (Exception e) {
            String str3 = TAG;
            SLog.w(str3, "fail to send request: " + e);
            return CompletableFuture.completedFuture(Response.of(-4));
        }
    }

    public void setEventListener(MediaController.OnEventListener onEventListener) {
        this.eventListener = new WeakReference<>(onEventListener);
    }

    public void setExceptionHandler(ExceptionHandler exceptionHandler2) {
        this.exceptionHandler = exceptionHandler2;
    }

    public RemoteServiceProxy(Context context2, String str, String str2, Map<Integer, Object> map) {
        this.requestThreadPool = Executors.newFixedThreadPool(6);
        this.mfControllerSync = new ConditionVariable();
        this.responseList = new CopyOnWriteArrayList();
        this.requestChannel = new LinkedBlockingQueue();
        this.context = context2;
        String str3 = TAG;
        HandlerThread handlerThread = new HandlerThread(str3);
        this.responseHandlerThread = handlerThread;
        handlerThread.start();
        this.responseMessenger = new Messenger(new IncomingHandler(new b(11, this), this.responseHandlerThread.getLooper()));
        this.status = ServiceStatus.LOADED;
        this.connection = new ServiceConnection() {
            public void onBindingDied(ComponentName componentName) {
                SLog.e(RemoteServiceProxy.TAG, "onServiceBindingDied");
                ServiceStatus unused = RemoteServiceProxy.this.status = ServiceStatus.DEAD;
                super.onBindingDied(componentName);
            }

            public void onNullBinding(ComponentName componentName) {
                SLog.e(RemoteServiceProxy.TAG, "onServiceNullBinding");
                ServiceStatus unused = RemoteServiceProxy.this.status = ServiceStatus.DEAD;
                super.onNullBinding(componentName);
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                SLog.i(RemoteServiceProxy.TAG, "onServiceConnected E");
                Messenger unused = RemoteServiceProxy.this.requestMessenger = new Messenger(iBinder);
                Request.of((int) com.samsung.android.sum.core.message.Message.CREATE_MEDIAFILTER_CONTROLLER).setReceiver(RemoteServiceProxy.this.requestMessenger).setResponseReceiver(RemoteServiceProxy.this.responseMessenger).post();
                ServiceStatus access$300 = RemoteServiceProxy.this.status;
                ServiceStatus unused2 = RemoteServiceProxy.this.status = ServiceStatus.CONNECTED;
                if (access$300 == ServiceStatus.DISCONNECTED) {
                    RemoteServiceProxy.this.onServiceReconnected();
                }
                SLog.i(RemoteServiceProxy.TAG, "onServiceConnected X");
            }

            public void onServiceDisconnected(ComponentName componentName) {
                SLog.e(RemoteServiceProxy.TAG, "onServiceDisconnected E");
                RemoteServiceProxy.this.onError(Response.of(-4, new IllegalStateException("service disconnected abnormally")));
                RemoteServiceProxy.this.onServiceDisconnected();
                SLog.e(RemoteServiceProxy.TAG, "onServiceDisconnected X");
            }
        };
        startRequestThread();
        Intent intent = new Intent();
        intent.setClassName(str, str2);
        if (map.containsKey(0)) {
            intent.setAction(ServiceProxy.ACTION_START_FOREGROUND);
            Intent intent2 = new Intent();
            intent2.setClass(context2, (Class) map.get(0));
            intent.putExtra("activity-intent", intent2);
        }
        if (map.containsKey(1)) {
            context2.startService(intent);
        }
        boolean bindService = context2.bindService(intent, this.connection, 1);
        SLog.d(str3, "bind to service: " + bindService);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Response lambda$requestOneWay$1(Response response) {
        return response;
    }
}
