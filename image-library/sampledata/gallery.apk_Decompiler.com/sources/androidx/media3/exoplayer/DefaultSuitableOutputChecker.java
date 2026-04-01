package androidx.media3.exoplayer;

import F2.G;
import F2.U;
import F2.y0;
import I.b;
import android.content.Context;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.MediaRoute2Info;
import android.media.MediaRouter2;
import android.media.RouteDiscoveryPreference;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.SuitableOutputChecker;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class DefaultSuitableOutputChecker implements SuitableOutputChecker {
    private final SuitableOutputChecker impl;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ImplApi23 implements SuitableOutputChecker {
        private AudioDeviceCallback audioDeviceCallback;
        private AudioManager audioManager;
        /* access modifiers changed from: private */
        public BackgroundThreadStateHandler<Boolean> isSuitableForPlaybackState;

        private ImplApi23() {
        }

        /* access modifiers changed from: private */
        public boolean hasSupportedAudioOutput() {
            for (AudioDeviceInfo audioDeviceInfo : ((AudioManager) Assertions.checkStateNotNull(this.audioManager)).getDevices(2)) {
                if (audioDeviceInfo.getType() == 8 || audioDeviceInfo.getType() == 5 || audioDeviceInfo.getType() == 6 || audioDeviceInfo.getType() == 11 || audioDeviceInfo.getType() == 4 || audioDeviceInfo.getType() == 3) {
                    return true;
                }
                int i2 = Build.VERSION.SDK_INT;
                if (audioDeviceInfo.getType() == 22 || audioDeviceInfo.getType() == 23) {
                    return true;
                }
                if (i2 >= 31 && (audioDeviceInfo.getType() == 26 || audioDeviceInfo.getType() == 27)) {
                    return true;
                }
                if (i2 >= 33 && audioDeviceInfo.getType() == 30) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$disable$2() {
            AudioManager audioManager2 = this.audioManager;
            if (audioManager2 != null) {
                audioManager2.unregisterAudioDeviceCallback((AudioDeviceCallback) Assertions.checkNotNull(this.audioDeviceCallback));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$enable$1(Context context) {
            AudioManager audioManager2;
            Assertions.checkNotNull(this.isSuitableForPlaybackState);
            if (Util.isWear(context) && (audioManager2 = (AudioManager) context.getSystemService("audio")) != null) {
                this.audioManager = audioManager2;
                AnonymousClass1 r0 = new AudioDeviceCallback() {
                    public void onAudioDevicesAdded(AudioDeviceInfo[] audioDeviceInfoArr) {
                        ImplApi23.this.isSuitableForPlaybackState.setStateInBackground(Boolean.valueOf(ImplApi23.this.hasSupportedAudioOutput()));
                    }

                    public void onAudioDevicesRemoved(AudioDeviceInfo[] audioDeviceInfoArr) {
                        ImplApi23.this.isSuitableForPlaybackState.setStateInBackground(Boolean.valueOf(ImplApi23.this.hasSupportedAudioOutput()));
                    }
                };
                this.audioDeviceCallback = r0;
                audioManager2.registerAudioDeviceCallback(r0, new Handler((Looper) Assertions.checkNotNull(Looper.myLooper())));
                this.isSuitableForPlaybackState.setStateInBackground(Boolean.valueOf(hasSupportedAudioOutput()));
            }
        }

        public void disable() {
            ((BackgroundThreadStateHandler) Assertions.checkNotNull(this.isSuitableForPlaybackState)).runInBackground(new C0079b(1, this));
        }

        public void enable(SuitableOutputChecker.Callback callback, Context context, Looper looper, Looper looper2, Clock clock) {
            Looper looper3 = looper2;
            BackgroundThreadStateHandler<Boolean> backgroundThreadStateHandler = new BackgroundThreadStateHandler<>(Boolean.TRUE, looper3, looper, clock, new e(callback, 0));
            this.isSuitableForPlaybackState = backgroundThreadStateHandler;
            backgroundThreadStateHandler.runInBackground(new f(0, this, context));
        }

        public boolean isSelectedOutputSuitableForPlayback() {
            BackgroundThreadStateHandler<Boolean> backgroundThreadStateHandler = this.isSuitableForPlaybackState;
            if (backgroundThreadStateHandler == null) {
                return true;
            }
            return backgroundThreadStateHandler.get().booleanValue();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ImplApi35 implements SuitableOutputChecker {
        private static final RouteDiscoveryPreference EMPTY_DISCOVERY_PREFERENCE = new RouteDiscoveryPreference.Builder(y0.f278h, false).build();
        private MediaRouter2.ControllerCallback controllerCallback;
        /* access modifiers changed from: private */
        public BackgroundThreadStateHandler<Boolean> isSuitableForPlaybackState;
        private MediaRouter2.RouteCallback routeCallback;
        /* access modifiers changed from: private */
        public MediaRouter2 router;

        static {
            G g = U.e;
        }

        private ImplApi35() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:7:0x0011 A[RETURN] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static boolean isRouteSuitableForMediaPlayback(android.media.MediaRoute2Info r1, int r2, boolean r3) {
            /*
                int r1 = r1.getSuitabilityStatus()
                r0 = 1
                if (r1 != r0) goto L_0x000f
                if (r2 == r0) goto L_0x000c
                r1 = 2
                if (r2 != r1) goto L_0x0012
            L_0x000c:
                if (r3 == 0) goto L_0x0012
                goto L_0x0011
            L_0x000f:
                if (r1 != 0) goto L_0x0012
            L_0x0011:
                return r0
            L_0x0012:
                r1 = 0
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.DefaultSuitableOutputChecker.ImplApi35.isRouteSuitableForMediaPlayback(android.media.MediaRoute2Info, int, boolean):boolean");
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$disable$2() {
            ((MediaRouter2) Assertions.checkNotNull(this.router)).unregisterControllerCallback((MediaRouter2.ControllerCallback) Assertions.checkNotNull(this.controllerCallback));
            this.controllerCallback = null;
            this.router.unregisterRouteCallback((MediaRouter2.RouteCallback) Assertions.checkNotNull(this.routeCallback));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$enable$1(Context context) {
            Assertions.checkNotNull(this.isSuitableForPlaybackState);
            this.router = MediaRouter2.getInstance(context);
            this.routeCallback = new MediaRouter2.RouteCallback() {
            };
            BackgroundThreadStateHandler<Boolean> backgroundThreadStateHandler = this.isSuitableForPlaybackState;
            Objects.requireNonNull(backgroundThreadStateHandler);
            b bVar = new b(0, backgroundThreadStateHandler);
            this.router.registerRouteCallback(bVar, this.routeCallback, EMPTY_DISCOVERY_PREFERENCE);
            AnonymousClass2 r42 = new MediaRouter2.ControllerCallback() {
                public void onControllerUpdated(MediaRouter2.RoutingController routingController) {
                    ImplApi35.this.isSuitableForPlaybackState.setStateInBackground(Boolean.valueOf(ImplApi35.isSelectedOutputSuitableForPlayback(ImplApi35.this.router)));
                }
            };
            this.controllerCallback = r42;
            this.router.registerControllerCallback(bVar, r42);
            this.isSuitableForPlaybackState.setStateInBackground(Boolean.valueOf(isSelectedOutputSuitableForPlayback(this.router)));
        }

        public void disable() {
            ((BackgroundThreadStateHandler) Assertions.checkStateNotNull(this.isSuitableForPlaybackState)).runInBackground(new C0079b(2, this));
        }

        public void enable(SuitableOutputChecker.Callback callback, Context context, Looper looper, Looper looper2, Clock clock) {
            Looper looper3 = looper2;
            BackgroundThreadStateHandler<Boolean> backgroundThreadStateHandler = new BackgroundThreadStateHandler<>(Boolean.TRUE, looper3, looper, clock, new e(callback, 1));
            this.isSuitableForPlaybackState = backgroundThreadStateHandler;
            backgroundThreadStateHandler.runInBackground(new f(1, this, context));
        }

        public boolean isSelectedOutputSuitableForPlayback() {
            BackgroundThreadStateHandler<Boolean> backgroundThreadStateHandler = this.isSuitableForPlaybackState;
            if (backgroundThreadStateHandler == null) {
                return true;
            }
            return backgroundThreadStateHandler.get().booleanValue();
        }

        /* access modifiers changed from: private */
        public static boolean isSelectedOutputSuitableForPlayback(MediaRouter2 mediaRouter2) {
            int b = ((MediaRouter2) Assertions.checkNotNull(mediaRouter2)).getSystemController().getRoutingSessionInfo().getTransferReason();
            boolean v = mediaRouter2.getSystemController().wasTransferInitiatedBySelf();
            for (MediaRoute2Info isRouteSuitableForMediaPlayback : mediaRouter2.getSystemController().getSelectedRoutes()) {
                if (isRouteSuitableForMediaPlayback(isRouteSuitableForMediaPlayback, b, v)) {
                    return true;
                }
            }
            return false;
        }
    }

    public DefaultSuitableOutputChecker() {
        if (Build.VERSION.SDK_INT >= 35) {
            this.impl = new ImplApi35();
        } else {
            this.impl = new ImplApi23();
        }
    }

    public void disable() {
        SuitableOutputChecker suitableOutputChecker = this.impl;
        if (suitableOutputChecker != null) {
            suitableOutputChecker.disable();
        }
    }

    public void enable(SuitableOutputChecker.Callback callback, Context context, Looper looper, Looper looper2, Clock clock) {
        SuitableOutputChecker suitableOutputChecker = this.impl;
        if (suitableOutputChecker != null) {
            suitableOutputChecker.enable(callback, context, looper, looper2, clock);
        }
    }

    public boolean isSelectedOutputSuitableForPlayback() {
        SuitableOutputChecker suitableOutputChecker = this.impl;
        if (suitableOutputChecker == null || suitableOutputChecker.isSelectedOutputSuitableForPlayback()) {
            return true;
        }
        return false;
    }
}
