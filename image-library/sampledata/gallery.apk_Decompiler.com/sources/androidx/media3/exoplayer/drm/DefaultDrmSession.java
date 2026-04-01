package androidx.media3.exoplayer.drm;

import Ad.j;
import K.a;
import android.media.NotProvisionedException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.CopyOnWriteMultiset;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.ExoMediaDrm;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DefaultDrmSession implements DrmSession {
    /* access modifiers changed from: private */
    public final MediaDrmCallback callback;
    private CryptoConfig cryptoConfig;
    private ExoMediaDrm.KeyRequest currentKeyRequest;
    private ExoMediaDrm.ProvisionRequest currentProvisionRequest;
    private final CopyOnWriteMultiset<DrmSessionEventListener.EventDispatcher> eventDispatchers;
    private final boolean isPlaceholderSession;
    private final HashMap<String, String> keyRequestParameters;
    private DrmSession.DrmSessionException lastException;
    /* access modifiers changed from: private */
    public final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final ExoMediaDrm mediaDrm;
    private final int mode;
    private byte[] offlineLicenseKeySetId;
    private final boolean playClearSamplesWithoutKeys;
    private final Looper playbackLooper;
    private final PlayerId playerId;
    private final ProvisioningManager provisioningManager;
    private int referenceCount;
    private final ReferenceCountListener referenceCountListener;
    private RequestHandler requestHandler;
    private HandlerThread requestHandlerThread;
    /* access modifiers changed from: private */
    public final ResponseHandler responseHandler;
    public final List<DrmInitData.SchemeData> schemeDatas;
    private byte[] sessionId;
    private int state;
    /* access modifiers changed from: private */
    public final UUID uuid;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ProvisioningManager {
        void onProvisionCompleted();

        void onProvisionError(Exception exc, boolean z);

        void provisionRequired(DefaultDrmSession defaultDrmSession);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ReferenceCountListener {
        void onReferenceCountDecremented(DefaultDrmSession defaultDrmSession, int i2);

        void onReferenceCountIncremented(DefaultDrmSession defaultDrmSession, int i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class RequestHandler extends Handler {
        private boolean isReleased;

        public RequestHandler(Looper looper) {
            super(looper);
        }

        private boolean maybeRetryRequest(Message message, MediaDrmCallbackException mediaDrmCallbackException) {
            IOException iOException;
            Message message2 = message;
            MediaDrmCallbackException mediaDrmCallbackException2 = mediaDrmCallbackException;
            RequestTask requestTask = (RequestTask) message2.obj;
            if (!requestTask.allowRetry) {
                return false;
            }
            int i2 = requestTask.errorCount + 1;
            requestTask.errorCount = i2;
            if (i2 > DefaultDrmSession.this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(3)) {
                return false;
            }
            LoadEventInfo loadEventInfo = new LoadEventInfo(requestTask.taskId, mediaDrmCallbackException2.dataSpec, mediaDrmCallbackException2.uriAfterRedirects, mediaDrmCallbackException2.responseHeaders, SystemClock.elapsedRealtime(), SystemClock.elapsedRealtime() - requestTask.startTimeMs, mediaDrmCallbackException2.bytesLoaded);
            MediaLoadData mediaLoadData = new MediaLoadData(3);
            if (mediaDrmCallbackException2.getCause() instanceof IOException) {
                iOException = (IOException) mediaDrmCallbackException2.getCause();
            } else {
                iOException = new UnexpectedDrmSessionException(mediaDrmCallbackException2.getCause());
            }
            long retryDelayMsFor = DefaultDrmSession.this.loadErrorHandlingPolicy.getRetryDelayMsFor(new LoadErrorHandlingPolicy.LoadErrorInfo(loadEventInfo, mediaLoadData, iOException, requestTask.errorCount));
            if (retryDelayMsFor == -9223372036854775807L) {
                return false;
            }
            synchronized (this) {
                try {
                    if (this.isReleased) {
                        return false;
                    }
                    sendMessageDelayed(Message.obtain(message2), retryDelayMsFor);
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: androidx.media3.exoplayer.drm.MediaDrmCallbackException} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: androidx.media3.exoplayer.drm.MediaDrmCallbackException} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: androidx.media3.exoplayer.drm.MediaDrmCallbackException} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: byte[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: byte[]} */
        /* JADX WARNING: type inference failed for: r1v2, types: [java.lang.Throwable, java.lang.Exception] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r6.obj
                androidx.media3.exoplayer.drm.DefaultDrmSession$RequestTask r0 = (androidx.media3.exoplayer.drm.DefaultDrmSession.RequestTask) r0
                int r1 = r6.what     // Catch:{ MediaDrmCallbackException -> 0x0023, Exception -> 0x0021 }
                r2 = 1
                if (r1 == r2) goto L_0x002b
                r2 = 2
                if (r1 != r2) goto L_0x0025
                androidx.media3.exoplayer.drm.DefaultDrmSession r1 = androidx.media3.exoplayer.drm.DefaultDrmSession.this     // Catch:{ MediaDrmCallbackException -> 0x0023, Exception -> 0x0021 }
                androidx.media3.exoplayer.drm.MediaDrmCallback r1 = r1.callback     // Catch:{ MediaDrmCallbackException -> 0x0023, Exception -> 0x0021 }
                androidx.media3.exoplayer.drm.DefaultDrmSession r2 = androidx.media3.exoplayer.drm.DefaultDrmSession.this     // Catch:{ MediaDrmCallbackException -> 0x0023, Exception -> 0x0021 }
                java.util.UUID r2 = r2.uuid     // Catch:{ MediaDrmCallbackException -> 0x0023, Exception -> 0x0021 }
                java.lang.Object r3 = r0.request     // Catch:{ MediaDrmCallbackException -> 0x0023, Exception -> 0x0021 }
                androidx.media3.exoplayer.drm.ExoMediaDrm$KeyRequest r3 = (androidx.media3.exoplayer.drm.ExoMediaDrm.KeyRequest) r3     // Catch:{ MediaDrmCallbackException -> 0x0023, Exception -> 0x0021 }
                byte[] r1 = r1.executeKeyRequest(r2, r3)     // Catch:{ MediaDrmCallbackException -> 0x0023, Exception -> 0x0021 }
                goto L_0x004f
            L_0x0021:
                r1 = move-exception
                goto L_0x0040
            L_0x0023:
                r1 = move-exception
                goto L_0x0048
            L_0x0025:
                java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ MediaDrmCallbackException -> 0x0023, Exception -> 0x0021 }
                r1.<init>()     // Catch:{ MediaDrmCallbackException -> 0x0023, Exception -> 0x0021 }
                throw r1     // Catch:{ MediaDrmCallbackException -> 0x0023, Exception -> 0x0021 }
            L_0x002b:
                androidx.media3.exoplayer.drm.DefaultDrmSession r1 = androidx.media3.exoplayer.drm.DefaultDrmSession.this     // Catch:{ MediaDrmCallbackException -> 0x0023, Exception -> 0x0021 }
                androidx.media3.exoplayer.drm.MediaDrmCallback r1 = r1.callback     // Catch:{ MediaDrmCallbackException -> 0x0023, Exception -> 0x0021 }
                androidx.media3.exoplayer.drm.DefaultDrmSession r2 = androidx.media3.exoplayer.drm.DefaultDrmSession.this     // Catch:{ MediaDrmCallbackException -> 0x0023, Exception -> 0x0021 }
                java.util.UUID r2 = r2.uuid     // Catch:{ MediaDrmCallbackException -> 0x0023, Exception -> 0x0021 }
                java.lang.Object r3 = r0.request     // Catch:{ MediaDrmCallbackException -> 0x0023, Exception -> 0x0021 }
                androidx.media3.exoplayer.drm.ExoMediaDrm$ProvisionRequest r3 = (androidx.media3.exoplayer.drm.ExoMediaDrm.ProvisionRequest) r3     // Catch:{ MediaDrmCallbackException -> 0x0023, Exception -> 0x0021 }
                byte[] r1 = r1.executeProvisionRequest(r2, r3)     // Catch:{ MediaDrmCallbackException -> 0x0023, Exception -> 0x0021 }
                goto L_0x004f
            L_0x0040:
                java.lang.String r2 = "DefaultDrmSession"
                java.lang.String r3 = "Key/provisioning request produced an unexpected exception. Not retrying."
                androidx.media3.common.util.Log.w(r2, r3, r1)
                goto L_0x004f
            L_0x0048:
                boolean r2 = r5.maybeRetryRequest(r6, r1)
                if (r2 == 0) goto L_0x004f
                goto L_0x0078
            L_0x004f:
                androidx.media3.exoplayer.drm.DefaultDrmSession r2 = androidx.media3.exoplayer.drm.DefaultDrmSession.this
                androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy r2 = r2.loadErrorHandlingPolicy
                long r3 = r0.taskId
                r2.onLoadTaskConcluded(r3)
                monitor-enter(r5)
                boolean r2 = r5.isReleased     // Catch:{ all -> 0x0075 }
                if (r2 != 0) goto L_0x0077
                androidx.media3.exoplayer.drm.DefaultDrmSession r2 = androidx.media3.exoplayer.drm.DefaultDrmSession.this     // Catch:{ all -> 0x0075 }
                androidx.media3.exoplayer.drm.DefaultDrmSession$ResponseHandler r2 = r2.responseHandler     // Catch:{ all -> 0x0075 }
                int r6 = r6.what     // Catch:{ all -> 0x0075 }
                java.lang.Object r0 = r0.request     // Catch:{ all -> 0x0075 }
                android.util.Pair r0 = android.util.Pair.create(r0, r1)     // Catch:{ all -> 0x0075 }
                android.os.Message r6 = r2.obtainMessage(r6, r0)     // Catch:{ all -> 0x0075 }
                r6.sendToTarget()     // Catch:{ all -> 0x0075 }
                goto L_0x0077
            L_0x0075:
                r6 = move-exception
                goto L_0x0079
            L_0x0077:
                monitor-exit(r5)     // Catch:{ all -> 0x0075 }
            L_0x0078:
                return
            L_0x0079:
                monitor-exit(r5)     // Catch:{ all -> 0x0075 }
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.drm.DefaultDrmSession.RequestHandler.handleMessage(android.os.Message):void");
        }

        public void post(int i2, Object obj, boolean z) {
            obtainMessage(i2, new RequestTask(LoadEventInfo.getNewId(), z, SystemClock.elapsedRealtime(), obj)).sendToTarget();
        }

        public synchronized void release() {
            removeCallbacksAndMessages((Object) null);
            this.isReleased = true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class RequestTask {
        public final boolean allowRetry;
        public int errorCount;
        public final Object request;
        public final long startTimeMs;
        public final long taskId;

        public RequestTask(long j2, boolean z, long j3, Object obj) {
            this.taskId = j2;
            this.allowRetry = z;
            this.startTimeMs = j3;
            this.request = obj;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ResponseHandler extends Handler {
        public ResponseHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            Pair pair = (Pair) message.obj;
            Object obj = pair.first;
            Object obj2 = pair.second;
            int i2 = message.what;
            if (i2 == 1) {
                DefaultDrmSession.this.onProvisionResponse(obj, obj2);
            } else if (i2 == 2) {
                DefaultDrmSession.this.onKeyResponse(obj, obj2);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class UnexpectedDrmSessionException extends IOException {
        public UnexpectedDrmSessionException(Throwable th) {
            super(th);
        }
    }

    public DefaultDrmSession(UUID uuid2, ExoMediaDrm exoMediaDrm, ProvisioningManager provisioningManager2, ReferenceCountListener referenceCountListener2, List<DrmInitData.SchemeData> list, int i2, boolean z, boolean z3, byte[] bArr, HashMap<String, String> hashMap, MediaDrmCallback mediaDrmCallback, Looper looper, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, PlayerId playerId2) {
        if (i2 == 1 || i2 == 3) {
            Assertions.checkNotNull(bArr);
        }
        this.uuid = uuid2;
        this.provisioningManager = provisioningManager2;
        this.referenceCountListener = referenceCountListener2;
        this.mediaDrm = exoMediaDrm;
        this.mode = i2;
        this.playClearSamplesWithoutKeys = z;
        this.isPlaceholderSession = z3;
        if (bArr != null) {
            this.offlineLicenseKeySetId = bArr;
            this.schemeDatas = null;
        } else {
            this.schemeDatas = Collections.unmodifiableList((List) Assertions.checkNotNull(list));
        }
        this.keyRequestParameters = hashMap;
        this.callback = mediaDrmCallback;
        this.eventDispatchers = new CopyOnWriteMultiset<>();
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.playerId = playerId2;
        this.state = 2;
        this.playbackLooper = looper;
        this.responseHandler = new ResponseHandler(looper);
    }

    private void dispatchEvent(Consumer<DrmSessionEventListener.EventDispatcher> consumer) {
        for (DrmSessionEventListener.EventDispatcher accept : this.eventDispatchers.elementSet()) {
            consumer.accept(accept);
        }
    }

    private void doLicense(boolean z) {
        if (!this.isPlaceholderSession) {
            byte[] bArr = (byte[]) Util.castNonNull(this.sessionId);
            int i2 = this.mode;
            if (i2 == 0 || i2 == 1) {
                if (this.offlineLicenseKeySetId == null) {
                    postKeyRequest(bArr, 1, z);
                } else if (this.state == 4 || restoreKeys()) {
                    long licenseDurationRemainingSec = getLicenseDurationRemainingSec();
                    if (this.mode == 0 && licenseDurationRemainingSec <= 60) {
                        Log.d("DefaultDrmSession", "Offline license has expired or will expire soon. Remaining seconds: " + licenseDurationRemainingSec);
                        postKeyRequest(bArr, 2, z);
                    } else if (licenseDurationRemainingSec <= 0) {
                        onError(new KeysExpiredException(), 2);
                    } else {
                        this.state = 4;
                        dispatchEvent(new a(1));
                    }
                }
            } else if (i2 != 2) {
                if (i2 == 3) {
                    Assertions.checkNotNull(this.offlineLicenseKeySetId);
                    Assertions.checkNotNull(this.sessionId);
                    postKeyRequest(this.offlineLicenseKeySetId, 3, z);
                }
            } else if (this.offlineLicenseKeySetId == null || restoreKeys()) {
                postKeyRequest(bArr, 2, z);
            }
        }
    }

    private long getLicenseDurationRemainingSec() {
        if (!C.WIDEVINE_UUID.equals(this.uuid)) {
            return Long.MAX_VALUE;
        }
        Pair pair = (Pair) Assertions.checkNotNull(WidevineUtil.getLicenseDurationRemainingSec(this));
        return Math.min(((Long) pair.first).longValue(), ((Long) pair.second).longValue());
    }

    private boolean isOpen() {
        int i2 = this.state;
        if (i2 == 3 || i2 == 4) {
            return true;
        }
        return false;
    }

    private void onError(Throwable th, int i2) {
        this.lastException = new DrmSession.DrmSessionException(th, DrmUtil.getErrorCodeForMediaDrmException(th, i2));
        Log.e("DefaultDrmSession", "DRM session error", th);
        if (th instanceof Exception) {
            dispatchEvent(new b((Exception) th));
        } else if (!(th instanceof Error)) {
            throw new IllegalStateException("Unexpected Throwable subclass", th);
        } else if (!DrmUtil.isFailureToConstructResourceBusyException(th) && !DrmUtil.isFailureToConstructNotProvisionedException(th)) {
            throw ((Error) th);
        }
        if (this.state != 4) {
            this.state = 1;
        }
    }

    /* access modifiers changed from: private */
    public void onKeyResponse(Object obj, Object obj2) {
        if (obj == this.currentKeyRequest && isOpen()) {
            this.currentKeyRequest = null;
            if ((obj2 instanceof Exception) || (obj2 instanceof NoSuchMethodError)) {
                onKeysError((Throwable) obj2, false);
                return;
            }
            try {
                byte[] bArr = (byte[]) obj2;
                if (this.mode == 3) {
                    this.mediaDrm.provideKeyResponse((byte[]) Util.castNonNull(this.offlineLicenseKeySetId), bArr);
                    dispatchEvent(new j(29));
                    return;
                }
                byte[] provideKeyResponse = this.mediaDrm.provideKeyResponse(this.sessionId, bArr);
                int i2 = this.mode;
                if (!((i2 != 2 && (i2 != 0 || this.offlineLicenseKeySetId == null)) || provideKeyResponse == null || provideKeyResponse.length == 0)) {
                    this.offlineLicenseKeySetId = provideKeyResponse;
                }
                this.state = 4;
                dispatchEvent(new a(0));
            } catch (Exception | NoSuchMethodError e) {
                onKeysError(e, true);
            }
        }
    }

    private void onKeysError(Throwable th, boolean z) {
        int i2;
        if ((th instanceof NotProvisionedException) || DrmUtil.isFailureToConstructNotProvisionedException(th)) {
            this.provisioningManager.provisionRequired(this);
            return;
        }
        if (z) {
            i2 = 1;
        } else {
            i2 = 2;
        }
        onError(th, i2);
    }

    private void onKeysRequired() {
        if (this.mode == 0 && this.state == 4) {
            Util.castNonNull(this.sessionId);
            doLicense(false);
        }
    }

    /* access modifiers changed from: private */
    public void onProvisionResponse(Object obj, Object obj2) {
        if (obj != this.currentProvisionRequest) {
            return;
        }
        if (this.state == 2 || isOpen()) {
            this.currentProvisionRequest = null;
            if (obj2 instanceof Exception) {
                this.provisioningManager.onProvisionError((Exception) obj2, false);
                return;
            }
            try {
                this.mediaDrm.provideProvisionResponse((byte[]) obj2);
                this.provisioningManager.onProvisionCompleted();
            } catch (Exception e) {
                this.provisioningManager.onProvisionError(e, true);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v9, types: [androidx.media3.common.util.Consumer, java.lang.Object] */
    private boolean openInternal() {
        if (isOpen()) {
            return true;
        }
        try {
            byte[] openSession = this.mediaDrm.openSession();
            this.sessionId = openSession;
            this.mediaDrm.setPlayerIdForSession(openSession, this.playerId);
            this.cryptoConfig = this.mediaDrm.createCryptoConfig(this.sessionId);
            this.state = 3;
            dispatchEvent(new Object());
            Assertions.checkNotNull(this.sessionId);
            return true;
        } catch (NotProvisionedException unused) {
            this.provisioningManager.provisionRequired(this);
            return false;
        } catch (Exception | NoSuchMethodError e) {
            if (DrmUtil.isFailureToConstructNotProvisionedException(e)) {
                this.provisioningManager.provisionRequired(this);
                return false;
            }
            onError(e, 1);
            return false;
        }
    }

    private void postKeyRequest(byte[] bArr, int i2, boolean z) {
        try {
            this.currentKeyRequest = this.mediaDrm.getKeyRequest(bArr, this.schemeDatas, i2, this.keyRequestParameters);
            ((RequestHandler) Util.castNonNull(this.requestHandler)).post(2, Assertions.checkNotNull(this.currentKeyRequest), z);
        } catch (Exception | NoSuchMethodError e) {
            onKeysError(e, true);
        }
    }

    private boolean restoreKeys() {
        try {
            this.mediaDrm.restoreKeys(this.sessionId, this.offlineLicenseKeySetId);
            return true;
        } catch (Exception | NoSuchMethodError e) {
            onError(e, 1);
            return false;
        }
    }

    private void verifyPlaybackThread() {
        if (Thread.currentThread() != this.playbackLooper.getThread()) {
            Log.w("DefaultDrmSession", "DefaultDrmSession accessed on the wrong thread.\nCurrent thread: " + Thread.currentThread().getName() + "\nExpected thread: " + this.playbackLooper.getThread().getName(), new IllegalStateException());
        }
    }

    public void acquire(DrmSessionEventListener.EventDispatcher eventDispatcher) {
        verifyPlaybackThread();
        boolean z = false;
        if (this.referenceCount < 0) {
            Log.e("DefaultDrmSession", "Session reference count less than zero: " + this.referenceCount);
            this.referenceCount = 0;
        }
        if (eventDispatcher != null) {
            this.eventDispatchers.add(eventDispatcher);
        }
        int i2 = this.referenceCount + 1;
        this.referenceCount = i2;
        if (i2 == 1) {
            if (this.state == 2) {
                z = true;
            }
            Assertions.checkState(z);
            HandlerThread handlerThread = new HandlerThread("ExoPlayer:DrmRequestHandler");
            this.requestHandlerThread = handlerThread;
            handlerThread.start();
            this.requestHandler = new RequestHandler(this.requestHandlerThread.getLooper());
            if (openInternal()) {
                doLicense(true);
            }
        } else if (eventDispatcher != null && isOpen() && this.eventDispatchers.count(eventDispatcher) == 1) {
            eventDispatcher.drmSessionAcquired(this.state);
        }
        this.referenceCountListener.onReferenceCountIncremented(this, this.referenceCount);
    }

    public final DrmSession.DrmSessionException getError() {
        verifyPlaybackThread();
        if (this.state == 1) {
            return this.lastException;
        }
        return null;
    }

    public final int getState() {
        verifyPlaybackThread();
        return this.state;
    }

    public boolean hasSessionId(byte[] bArr) {
        verifyPlaybackThread();
        return Arrays.equals(this.sessionId, bArr);
    }

    public void onMediaDrmEvent(int i2) {
        if (i2 == 2) {
            onKeysRequired();
        }
    }

    public void onProvisionCompleted() {
        if (openInternal()) {
            doLicense(true);
        }
    }

    public void onProvisionError(Exception exc, boolean z) {
        int i2;
        if (z) {
            i2 = 1;
        } else {
            i2 = 3;
        }
        onError(exc, i2);
    }

    public boolean playClearSamplesWithoutKeys() {
        verifyPlaybackThread();
        return this.playClearSamplesWithoutKeys;
    }

    public void provision() {
        this.currentProvisionRequest = this.mediaDrm.getProvisionRequest();
        ((RequestHandler) Util.castNonNull(this.requestHandler)).post(1, Assertions.checkNotNull(this.currentProvisionRequest), true);
    }

    public Map<String, String> queryKeyStatus() {
        verifyPlaybackThread();
        byte[] bArr = this.sessionId;
        if (bArr == null) {
            return null;
        }
        return this.mediaDrm.queryKeyStatus(bArr);
    }

    public void release(DrmSessionEventListener.EventDispatcher eventDispatcher) {
        verifyPlaybackThread();
        int i2 = this.referenceCount;
        if (i2 <= 0) {
            Log.e("DefaultDrmSession", "release() called on a session that's already fully released.");
            return;
        }
        int i7 = i2 - 1;
        this.referenceCount = i7;
        if (i7 == 0) {
            this.state = 0;
            ((ResponseHandler) Util.castNonNull(this.responseHandler)).removeCallbacksAndMessages((Object) null);
            ((RequestHandler) Util.castNonNull(this.requestHandler)).release();
            this.requestHandler = null;
            ((HandlerThread) Util.castNonNull(this.requestHandlerThread)).quit();
            this.requestHandlerThread = null;
            this.cryptoConfig = null;
            this.lastException = null;
            this.currentKeyRequest = null;
            this.currentProvisionRequest = null;
            byte[] bArr = this.sessionId;
            if (bArr != null) {
                this.mediaDrm.closeSession(bArr);
                this.sessionId = null;
            }
        }
        if (eventDispatcher != null) {
            this.eventDispatchers.remove(eventDispatcher);
            if (this.eventDispatchers.count(eventDispatcher) == 0) {
                eventDispatcher.drmSessionReleased();
            }
        }
        this.referenceCountListener.onReferenceCountDecremented(this, this.referenceCount);
    }
}
