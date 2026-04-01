package androidx.media3.exoplayer;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Log;
import com.samsung.scsp.framework.core.network.HeaderSetup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class WifiLockManager {
    private boolean enabled;
    private boolean stayAwake;
    private final HandlerWrapper wifiLockHandler;
    private final WifiLockManagerInternal wifiLockManagerInternal;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WifiLockManagerInternal {
        private final Context applicationContext;
        private WifiManager.WifiLock wifiLock;

        public WifiLockManagerInternal(Context context) {
            this.applicationContext = context;
        }

        public void updateWifiLock(boolean z, boolean z3) {
            if (z && this.wifiLock == null) {
                WifiManager wifiManager = (WifiManager) this.applicationContext.getApplicationContext().getSystemService(HeaderSetup.Key.WIFI);
                if (wifiManager == null) {
                    Log.w("WifiLockManager", "WifiManager is null, therefore not creating the WifiLock.");
                    return;
                }
                WifiManager.WifiLock createWifiLock = wifiManager.createWifiLock(3, "ExoPlayer:WifiLockManager");
                this.wifiLock = createWifiLock;
                createWifiLock.setReferenceCounted(false);
            }
            WifiManager.WifiLock wifiLock2 = this.wifiLock;
            if (wifiLock2 != null) {
                if (!z || !z3) {
                    wifiLock2.release();
                } else {
                    wifiLock2.acquire();
                }
            }
        }
    }

    public WifiLockManager(Context context, Looper looper, Clock clock) {
        this.wifiLockManagerInternal = new WifiLockManagerInternal(context.getApplicationContext());
        this.wifiLockHandler = clock.createHandler(looper, (Handler.Callback) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setEnabled$0(boolean z, boolean z3) {
        this.wifiLockManagerInternal.updateWifiLock(z, z3);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setStayAwake$1(boolean z) {
        this.wifiLockManagerInternal.updateWifiLock(true, z);
    }

    public void setEnabled(boolean z) {
        if (this.enabled != z) {
            this.enabled = z;
            this.wifiLockHandler.post(new B(this, z, this.stayAwake, 1));
        }
    }

    public void setStayAwake(boolean z) {
        if (this.stayAwake != z) {
            this.stayAwake = z;
            if (this.enabled) {
                this.wifiLockHandler.post(new A(this, z, 1));
            }
        }
    }
}
