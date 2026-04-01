package androidx.media3.exoplayer.drm;

import F2.C0010c0;
import F2.G;
import F2.O0;
import F2.U;
import F2.y0;
import K.c;
import android.media.ResourceBusyException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DefaultDrmSession;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.drm.ExoMediaDrm;
import androidx.media3.exoplayer.upstream.DefaultLoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DefaultDrmSessionManager implements DrmSessionManager {
    private final MediaDrmCallback callback;
    private ExoMediaDrm exoMediaDrm;
    private final ExoMediaDrm.Provider exoMediaDrmProvider;
    /* access modifiers changed from: private */
    public final Set<DefaultDrmSession> keepaliveSessions;
    private final HashMap<String, String> keyRequestParameters;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    volatile MediaDrmHandler mediaDrmHandler;
    private int mode;
    private final boolean multiSession;
    /* access modifiers changed from: private */
    public DefaultDrmSession noMultiSessionDrmSession;
    private byte[] offlineLicenseKeySetId;
    /* access modifiers changed from: private */
    public DefaultDrmSession placeholderDrmSession;
    private final boolean playClearSamplesWithoutKeys;
    /* access modifiers changed from: private */
    public Handler playbackHandler;
    /* access modifiers changed from: private */
    public Looper playbackLooper;
    private PlayerId playerId;
    /* access modifiers changed from: private */
    public final Set<PreacquiredSessionReference> preacquiredSessionReferences;
    /* access modifiers changed from: private */
    public int prepareCallsCount;
    /* access modifiers changed from: private */
    public final ProvisioningManagerImpl provisioningManagerImpl;
    private final ReferenceCountListenerImpl referenceCountListener;
    /* access modifiers changed from: private */
    public final long sessionKeepaliveMs;
    /* access modifiers changed from: private */
    public final List<DefaultDrmSession> sessions;
    private final int[] useDrmSessionsForClearContentTrackTypes;
    private final UUID uuid;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private ExoMediaDrm.Provider exoMediaDrmProvider = FrameworkMediaDrm.DEFAULT_PROVIDER;
        private final HashMap<String, String> keyRequestParameters = new HashMap<>();
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy();
        private boolean multiSession;
        private boolean playClearSamplesWithoutKeys = true;
        private long sessionKeepaliveMs = 300000;
        private int[] useDrmSessionsForClearContentTrackTypes = new int[0];
        private UUID uuid = C.WIDEVINE_UUID;

        public DefaultDrmSessionManager build(MediaDrmCallback mediaDrmCallback) {
            return new DefaultDrmSessionManager(this.uuid, this.exoMediaDrmProvider, mediaDrmCallback, this.keyRequestParameters, this.multiSession, this.useDrmSessionsForClearContentTrackTypes, this.playClearSamplesWithoutKeys, this.loadErrorHandlingPolicy, this.sessionKeepaliveMs);
        }

        public Builder setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy2) {
            this.loadErrorHandlingPolicy = (LoadErrorHandlingPolicy) Assertions.checkNotNull(loadErrorHandlingPolicy2);
            return this;
        }

        public Builder setMultiSession(boolean z) {
            this.multiSession = z;
            return this;
        }

        public Builder setPlayClearSamplesWithoutKeys(boolean z) {
            this.playClearSamplesWithoutKeys = z;
            return this;
        }

        public Builder setUseDrmSessionsForClearContent(int... iArr) {
            for (int i2 : iArr) {
                boolean z = true;
                if (!(i2 == 2 || i2 == 1)) {
                    z = false;
                }
                Assertions.checkArgument(z);
            }
            this.useDrmSessionsForClearContentTrackTypes = (int[]) iArr.clone();
            return this;
        }

        public Builder setUuidAndExoMediaDrmProvider(UUID uuid2, ExoMediaDrm.Provider provider) {
            this.uuid = (UUID) Assertions.checkNotNull(uuid2);
            this.exoMediaDrmProvider = (ExoMediaDrm.Provider) Assertions.checkNotNull(provider);
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class MediaDrmEventListener implements ExoMediaDrm.OnEventListener {
        private MediaDrmEventListener() {
        }

        public void onEvent(ExoMediaDrm exoMediaDrm, byte[] bArr, int i2, int i7, byte[] bArr2) {
            ((MediaDrmHandler) Assertions.checkNotNull(DefaultDrmSessionManager.this.mediaDrmHandler)).obtainMessage(i2, bArr).sendToTarget();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class MediaDrmHandler extends Handler {
        public MediaDrmHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            byte[] bArr = (byte[]) message.obj;
            if (bArr != null) {
                for (DefaultDrmSession defaultDrmSession : DefaultDrmSessionManager.this.sessions) {
                    if (defaultDrmSession.hasSessionId(bArr)) {
                        defaultDrmSession.onMediaDrmEvent(message.what);
                        return;
                    }
                }
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class MissingSchemeDataException extends Exception {
        private MissingSchemeDataException(UUID uuid) {
            super("Media does not support uuid: " + uuid);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PreacquiredSessionReference implements DrmSessionManager.DrmSessionReference {
        private final DrmSessionEventListener.EventDispatcher eventDispatcher;
        private boolean isReleased;
        private DrmSession session;

        public PreacquiredSessionReference(DrmSessionEventListener.EventDispatcher eventDispatcher2) {
            this.eventDispatcher = eventDispatcher2;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$acquire$0(Format format) {
            if (DefaultDrmSessionManager.this.prepareCallsCount != 0 && !this.isReleased) {
                DefaultDrmSessionManager defaultDrmSessionManager = DefaultDrmSessionManager.this;
                this.session = defaultDrmSessionManager.acquireSession((Looper) Assertions.checkNotNull(defaultDrmSessionManager.playbackLooper), this.eventDispatcher, format, false);
                DefaultDrmSessionManager.this.preacquiredSessionReferences.add(this);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$release$1() {
            if (!this.isReleased) {
                DrmSession drmSession = this.session;
                if (drmSession != null) {
                    drmSession.release(this.eventDispatcher);
                }
                DefaultDrmSessionManager.this.preacquiredSessionReferences.remove(this);
                this.isReleased = true;
            }
        }

        public void acquire(Format format) {
            ((Handler) Assertions.checkNotNull(DefaultDrmSessionManager.this.playbackHandler)).post(new c(this, format));
        }

        public void release() {
            Util.postOrRun((Handler) Assertions.checkNotNull(DefaultDrmSessionManager.this.playbackHandler), new d(0, this));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ProvisioningManagerImpl implements DefaultDrmSession.ProvisioningManager {
        private DefaultDrmSession provisioningSession;
        private final Set<DefaultDrmSession> sessionsAwaitingProvisioning = new HashSet();

        public ProvisioningManagerImpl() {
        }

        public void onProvisionCompleted() {
            this.provisioningSession = null;
            U y = U.y(this.sessionsAwaitingProvisioning);
            this.sessionsAwaitingProvisioning.clear();
            G A10 = y.listIterator(0);
            while (A10.hasNext()) {
                ((DefaultDrmSession) A10.next()).onProvisionCompleted();
            }
        }

        public void onProvisionError(Exception exc, boolean z) {
            this.provisioningSession = null;
            U y = U.y(this.sessionsAwaitingProvisioning);
            this.sessionsAwaitingProvisioning.clear();
            G A10 = y.listIterator(0);
            while (A10.hasNext()) {
                ((DefaultDrmSession) A10.next()).onProvisionError(exc, z);
            }
        }

        public void onSessionFullyReleased(DefaultDrmSession defaultDrmSession) {
            this.sessionsAwaitingProvisioning.remove(defaultDrmSession);
            if (this.provisioningSession == defaultDrmSession) {
                this.provisioningSession = null;
                if (!this.sessionsAwaitingProvisioning.isEmpty()) {
                    DefaultDrmSession next = this.sessionsAwaitingProvisioning.iterator().next();
                    this.provisioningSession = next;
                    next.provision();
                }
            }
        }

        public void provisionRequired(DefaultDrmSession defaultDrmSession) {
            this.sessionsAwaitingProvisioning.add(defaultDrmSession);
            if (this.provisioningSession == null) {
                this.provisioningSession = defaultDrmSession;
                defaultDrmSession.provision();
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ReferenceCountListenerImpl implements DefaultDrmSession.ReferenceCountListener {
        private ReferenceCountListenerImpl() {
        }

        public void onReferenceCountDecremented(DefaultDrmSession defaultDrmSession, int i2) {
            if (i2 == 1 && DefaultDrmSessionManager.this.prepareCallsCount > 0 && DefaultDrmSessionManager.this.sessionKeepaliveMs != -9223372036854775807L) {
                DefaultDrmSessionManager.this.keepaliveSessions.add(defaultDrmSession);
                ((Handler) Assertions.checkNotNull(DefaultDrmSessionManager.this.playbackHandler)).postAtTime(new d(1, defaultDrmSession), defaultDrmSession, DefaultDrmSessionManager.this.sessionKeepaliveMs + SystemClock.uptimeMillis());
            } else if (i2 == 0) {
                DefaultDrmSessionManager.this.sessions.remove(defaultDrmSession);
                if (DefaultDrmSessionManager.this.placeholderDrmSession == defaultDrmSession) {
                    DefaultDrmSession unused = DefaultDrmSessionManager.this.placeholderDrmSession = null;
                }
                if (DefaultDrmSessionManager.this.noMultiSessionDrmSession == defaultDrmSession) {
                    DefaultDrmSession unused2 = DefaultDrmSessionManager.this.noMultiSessionDrmSession = null;
                }
                DefaultDrmSessionManager.this.provisioningManagerImpl.onSessionFullyReleased(defaultDrmSession);
                if (DefaultDrmSessionManager.this.sessionKeepaliveMs != -9223372036854775807L) {
                    ((Handler) Assertions.checkNotNull(DefaultDrmSessionManager.this.playbackHandler)).removeCallbacksAndMessages(defaultDrmSession);
                    DefaultDrmSessionManager.this.keepaliveSessions.remove(defaultDrmSession);
                }
            }
            DefaultDrmSessionManager.this.maybeReleaseMediaDrm();
        }

        public void onReferenceCountIncremented(DefaultDrmSession defaultDrmSession, int i2) {
            if (DefaultDrmSessionManager.this.sessionKeepaliveMs != -9223372036854775807L) {
                DefaultDrmSessionManager.this.keepaliveSessions.remove(defaultDrmSession);
                ((Handler) Assertions.checkNotNull(DefaultDrmSessionManager.this.playbackHandler)).removeCallbacksAndMessages(defaultDrmSession);
            }
        }
    }

    private static boolean acquisitionFailedIndicatingResourceShortage(DrmSession drmSession) {
        if (drmSession.getState() != 1) {
            return false;
        }
        Throwable cause = ((DrmSession.DrmSessionException) Assertions.checkNotNull(drmSession.getError())).getCause();
        if ((cause instanceof ResourceBusyException) || DrmUtil.isFailureToConstructResourceBusyException(cause)) {
            return true;
        }
        return false;
    }

    private boolean canAcquireSession(DrmInitData drmInitData) {
        if (this.offlineLicenseKeySetId != null) {
            return true;
        }
        if (getSchemeDatas(drmInitData, this.uuid, true).isEmpty()) {
            if (drmInitData.schemeDataCount != 1 || !drmInitData.get(0).matches(C.COMMON_PSSH_UUID)) {
                return false;
            }
            Log.w("DefaultDrmSessionMgr", "DrmInitData only contains common PSSH SchemeData. Assuming support for: " + this.uuid);
        }
        String str = drmInitData.schemeType;
        if (str == null || "cenc".equals(str) || "cbcs".equals(str)) {
            return true;
        }
        if ("cbc1".equals(str) || "cens".equals(str)) {
            return false;
        }
        return true;
    }

    private DefaultDrmSession createAndAcquireSession(List<DrmInitData.SchemeData> list, boolean z, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        Assertions.checkNotNull(this.exoMediaDrm);
        List<DrmInitData.SchemeData> list2 = list;
        DefaultDrmSession defaultDrmSession = new DefaultDrmSession(this.uuid, this.exoMediaDrm, this.provisioningManagerImpl, this.referenceCountListener, list2, this.mode, this.playClearSamplesWithoutKeys | z, z, this.offlineLicenseKeySetId, this.keyRequestParameters, this.callback, (Looper) Assertions.checkNotNull(this.playbackLooper), this.loadErrorHandlingPolicy, (PlayerId) Assertions.checkNotNull(this.playerId));
        defaultDrmSession.acquire(eventDispatcher);
        if (this.sessionKeepaliveMs != -9223372036854775807L) {
            defaultDrmSession.acquire((DrmSessionEventListener.EventDispatcher) null);
        }
        return defaultDrmSession;
    }

    private DefaultDrmSession createAndAcquireSessionWithRetry(List<DrmInitData.SchemeData> list, boolean z, DrmSessionEventListener.EventDispatcher eventDispatcher, boolean z3) {
        DefaultDrmSession createAndAcquireSession = createAndAcquireSession(list, z, eventDispatcher);
        if (acquisitionFailedIndicatingResourceShortage(createAndAcquireSession) && !this.keepaliveSessions.isEmpty()) {
            releaseAllKeepaliveSessions();
            undoAcquisition(createAndAcquireSession, eventDispatcher);
            createAndAcquireSession = createAndAcquireSession(list, z, eventDispatcher);
        }
        if (!acquisitionFailedIndicatingResourceShortage(createAndAcquireSession) || !z3 || this.preacquiredSessionReferences.isEmpty()) {
            return createAndAcquireSession;
        }
        releaseAllPreacquiredSessions();
        if (!this.keepaliveSessions.isEmpty()) {
            releaseAllKeepaliveSessions();
        }
        undoAcquisition(createAndAcquireSession, eventDispatcher);
        return createAndAcquireSession(list, z, eventDispatcher);
    }

    private static List<DrmInitData.SchemeData> getSchemeDatas(DrmInitData drmInitData, UUID uuid2, boolean z) {
        ArrayList arrayList = new ArrayList(drmInitData.schemeDataCount);
        for (int i2 = 0; i2 < drmInitData.schemeDataCount; i2++) {
            DrmInitData.SchemeData schemeData = drmInitData.get(i2);
            if ((schemeData.matches(uuid2) || (C.CLEARKEY_UUID.equals(uuid2) && schemeData.matches(C.COMMON_PSSH_UUID))) && (schemeData.data != null || z)) {
                arrayList.add(schemeData);
            }
        }
        return arrayList;
    }

    private synchronized void initPlaybackLooper(Looper looper) {
        boolean z;
        try {
            Looper looper2 = this.playbackLooper;
            if (looper2 == null) {
                this.playbackLooper = looper;
                this.playbackHandler = new Handler(looper);
            } else {
                if (looper2 == looper) {
                    z = true;
                } else {
                    z = false;
                }
                Assertions.checkState(z);
                Assertions.checkNotNull(this.playbackHandler);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private DrmSession maybeAcquirePlaceholderSession(int i2, boolean z) {
        ExoMediaDrm exoMediaDrm2 = (ExoMediaDrm) Assertions.checkNotNull(this.exoMediaDrm);
        if ((exoMediaDrm2.getCryptoType() == 2 && FrameworkCryptoConfig.WORKAROUND_DEVICE_NEEDS_KEYS_TO_CONFIGURE_CODEC) || Util.linearSearch(this.useDrmSessionsForClearContentTrackTypes, i2) == -1 || exoMediaDrm2.getCryptoType() == 1) {
            return null;
        }
        DefaultDrmSession defaultDrmSession = this.placeholderDrmSession;
        if (defaultDrmSession == null) {
            G g = U.e;
            DefaultDrmSession createAndAcquireSessionWithRetry = createAndAcquireSessionWithRetry(y0.f278h, true, (DrmSessionEventListener.EventDispatcher) null, z);
            this.sessions.add(createAndAcquireSessionWithRetry);
            this.placeholderDrmSession = createAndAcquireSessionWithRetry;
        } else {
            defaultDrmSession.acquire((DrmSessionEventListener.EventDispatcher) null);
        }
        return this.placeholderDrmSession;
    }

    private void maybeCreateMediaDrmHandler(Looper looper) {
        if (this.mediaDrmHandler == null) {
            this.mediaDrmHandler = new MediaDrmHandler(looper);
        }
    }

    /* access modifiers changed from: private */
    public void maybeReleaseMediaDrm() {
        if (this.exoMediaDrm != null && this.prepareCallsCount == 0 && this.sessions.isEmpty() && this.preacquiredSessionReferences.isEmpty()) {
            ((ExoMediaDrm) Assertions.checkNotNull(this.exoMediaDrm)).release();
            this.exoMediaDrm = null;
        }
    }

    private void releaseAllKeepaliveSessions() {
        O0 v = C0010c0.y(this.keepaliveSessions).iterator();
        while (v.hasNext()) {
            ((DrmSession) v.next()).release((DrmSessionEventListener.EventDispatcher) null);
        }
    }

    private void releaseAllPreacquiredSessions() {
        O0 v = C0010c0.y(this.preacquiredSessionReferences).iterator();
        while (v.hasNext()) {
            ((PreacquiredSessionReference) v.next()).release();
        }
    }

    private void undoAcquisition(DrmSession drmSession, DrmSessionEventListener.EventDispatcher eventDispatcher) {
        drmSession.release(eventDispatcher);
        if (this.sessionKeepaliveMs != -9223372036854775807L) {
            drmSession.release((DrmSessionEventListener.EventDispatcher) null);
        }
    }

    private void verifyPlaybackThread(boolean z) {
        if (z && this.playbackLooper == null) {
            Log.w("DefaultDrmSessionMgr", "DefaultDrmSessionManager accessed before setPlayer(), possibly on the wrong thread.", new IllegalStateException());
        } else if (Thread.currentThread() != ((Looper) Assertions.checkNotNull(this.playbackLooper)).getThread()) {
            Log.w("DefaultDrmSessionMgr", "DefaultDrmSessionManager accessed on the wrong thread.\nCurrent thread: " + Thread.currentThread().getName() + "\nExpected thread: " + this.playbackLooper.getThread().getName(), new IllegalStateException());
        }
    }

    public DrmSession acquireSession(DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
        boolean z = false;
        verifyPlaybackThread(false);
        if (this.prepareCallsCount > 0) {
            z = true;
        }
        Assertions.checkState(z);
        Assertions.checkStateNotNull(this.playbackLooper);
        return acquireSession(this.playbackLooper, eventDispatcher, format, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x002b A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getCryptoType(androidx.media3.common.Format r4) {
        /*
            r3 = this;
            r0 = 0
            r3.verifyPlaybackThread(r0)
            androidx.media3.exoplayer.drm.ExoMediaDrm r1 = r3.exoMediaDrm
            java.lang.Object r1 = androidx.media3.common.util.Assertions.checkNotNull(r1)
            androidx.media3.exoplayer.drm.ExoMediaDrm r1 = (androidx.media3.exoplayer.drm.ExoMediaDrm) r1
            int r1 = r1.getCryptoType()
            androidx.media3.common.DrmInitData r2 = r4.drmInitData
            if (r2 != 0) goto L_0x0025
            java.lang.String r4 = r4.sampleMimeType
            int r4 = androidx.media3.common.MimeTypes.getTrackType(r4)
            int[] r3 = r3.useDrmSessionsForClearContentTrackTypes
            int r3 = androidx.media3.common.util.Util.linearSearch(r3, r4)
            r4 = -1
            if (r3 == r4) goto L_0x0024
            goto L_0x002b
        L_0x0024:
            return r0
        L_0x0025:
            boolean r3 = r3.canAcquireSession(r2)
            if (r3 == 0) goto L_0x002c
        L_0x002b:
            return r1
        L_0x002c:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.drm.DefaultDrmSessionManager.getCryptoType(androidx.media3.common.Format):int");
    }

    public DrmSessionManager.DrmSessionReference preacquireSession(DrmSessionEventListener.EventDispatcher eventDispatcher, Format format) {
        boolean z;
        if (this.prepareCallsCount > 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        Assertions.checkStateNotNull(this.playbackLooper);
        PreacquiredSessionReference preacquiredSessionReference = new PreacquiredSessionReference(eventDispatcher);
        preacquiredSessionReference.acquire(format);
        return preacquiredSessionReference;
    }

    public final void prepare() {
        verifyPlaybackThread(true);
        int i2 = this.prepareCallsCount;
        this.prepareCallsCount = i2 + 1;
        if (i2 == 0) {
            if (this.exoMediaDrm == null) {
                ExoMediaDrm.Provider provider = this.exoMediaDrmProvider;
                UUID uuid2 = this.uuid;
                ((c) provider).getClass();
                ExoMediaDrm a7 = FrameworkMediaDrm.lambda$static$0(uuid2);
                this.exoMediaDrm = a7;
                a7.setOnEventListener(new MediaDrmEventListener());
            } else if (this.sessionKeepaliveMs != -9223372036854775807L) {
                for (int i7 = 0; i7 < this.sessions.size(); i7++) {
                    this.sessions.get(i7).acquire((DrmSessionEventListener.EventDispatcher) null);
                }
            }
        }
    }

    public final void release() {
        verifyPlaybackThread(true);
        int i2 = this.prepareCallsCount - 1;
        this.prepareCallsCount = i2;
        if (i2 == 0) {
            if (this.sessionKeepaliveMs != -9223372036854775807L) {
                ArrayList arrayList = new ArrayList(this.sessions);
                for (int i7 = 0; i7 < arrayList.size(); i7++) {
                    ((DefaultDrmSession) arrayList.get(i7)).release((DrmSessionEventListener.EventDispatcher) null);
                }
            }
            releaseAllPreacquiredSessions();
            maybeReleaseMediaDrm();
        }
    }

    public void setMode(int i2, byte[] bArr) {
        Assertions.checkState(this.sessions.isEmpty());
        if (i2 == 1 || i2 == 3) {
            Assertions.checkNotNull(bArr);
        }
        this.mode = i2;
        this.offlineLicenseKeySetId = bArr;
    }

    public void setPlayer(Looper looper, PlayerId playerId2) {
        initPlaybackLooper(looper);
        this.playerId = playerId2;
    }

    private DefaultDrmSessionManager(UUID uuid2, ExoMediaDrm.Provider provider, MediaDrmCallback mediaDrmCallback, HashMap<String, String> hashMap, boolean z, int[] iArr, boolean z3, LoadErrorHandlingPolicy loadErrorHandlingPolicy2, long j2) {
        Assertions.checkNotNull(uuid2);
        Assertions.checkArgument(!C.COMMON_PSSH_UUID.equals(uuid2), "Use C.CLEARKEY_UUID instead");
        this.uuid = uuid2;
        this.exoMediaDrmProvider = provider;
        this.callback = mediaDrmCallback;
        this.keyRequestParameters = hashMap;
        this.multiSession = z;
        this.useDrmSessionsForClearContentTrackTypes = iArr;
        this.playClearSamplesWithoutKeys = z3;
        this.loadErrorHandlingPolicy = loadErrorHandlingPolicy2;
        this.provisioningManagerImpl = new ProvisioningManagerImpl();
        this.referenceCountListener = new ReferenceCountListenerImpl();
        this.mode = 0;
        this.sessions = new ArrayList();
        this.preacquiredSessionReferences = Collections.newSetFromMap(new IdentityHashMap());
        this.keepaliveSessions = Collections.newSetFromMap(new IdentityHashMap());
        this.sessionKeepaliveMs = j2;
    }

    /* access modifiers changed from: private */
    public DrmSession acquireSession(Looper looper, DrmSessionEventListener.EventDispatcher eventDispatcher, Format format, boolean z) {
        List<DrmInitData.SchemeData> list;
        maybeCreateMediaDrmHandler(looper);
        DrmInitData drmInitData = format.drmInitData;
        if (drmInitData == null) {
            return maybeAcquirePlaceholderSession(MimeTypes.getTrackType(format.sampleMimeType), z);
        }
        DefaultDrmSession defaultDrmSession = null;
        if (this.offlineLicenseKeySetId == null) {
            list = getSchemeDatas((DrmInitData) Assertions.checkNotNull(drmInitData), this.uuid, false);
            if (list.isEmpty()) {
                MissingSchemeDataException missingSchemeDataException = new MissingSchemeDataException(this.uuid);
                Log.e("DefaultDrmSessionMgr", "DRM error", missingSchemeDataException);
                if (eventDispatcher != null) {
                    eventDispatcher.drmSessionManagerError(missingSchemeDataException);
                }
                return new ErrorStateDrmSession(new DrmSession.DrmSessionException(missingSchemeDataException, 6003));
            }
        } else {
            list = null;
        }
        if (this.multiSession) {
            Iterator<DefaultDrmSession> it = this.sessions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DefaultDrmSession next = it.next();
                if (Objects.equals(next.schemeDatas, list)) {
                    defaultDrmSession = next;
                    break;
                }
            }
        } else {
            defaultDrmSession = this.noMultiSessionDrmSession;
        }
        if (defaultDrmSession == null) {
            DefaultDrmSession createAndAcquireSessionWithRetry = createAndAcquireSessionWithRetry(list, false, eventDispatcher, z);
            if (!this.multiSession) {
                this.noMultiSessionDrmSession = createAndAcquireSessionWithRetry;
            }
            this.sessions.add(createAndAcquireSessionWithRetry);
            return createAndAcquireSessionWithRetry;
        }
        defaultDrmSession.acquire(eventDispatcher);
        return defaultDrmSession;
    }
}
