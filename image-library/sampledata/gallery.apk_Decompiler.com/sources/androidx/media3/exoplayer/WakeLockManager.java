package androidx.media3.exoplayer;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class WakeLockManager {
    private boolean enabled;
    private boolean stayAwake;
    private final HandlerWrapper wakeLockHandler;
    private final WakeLockManagerInternal wakeLockManagerInternal;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WakeLockManagerInternal {
        private final Context applicationContext;
        private PowerManager.WakeLock wakeLock;

        public WakeLockManagerInternal(Context context) {
            this.applicationContext = context;
        }

        public void updateWakeLock(boolean z, boolean z3) {
            if (z && this.wakeLock == null) {
                PowerManager powerManager = (PowerManager) this.applicationContext.getSystemService("power");
                if (powerManager == null) {
                    Log.w("WakeLockManager", "PowerManager is null, therefore not creating the WakeLock.");
                    return;
                }
                PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(1, "ExoPlayer:WakeLockManager");
                this.wakeLock = newWakeLock;
                newWakeLock.setReferenceCounted(false);
            }
            PowerManager.WakeLock wakeLock2 = this.wakeLock;
            if (wakeLock2 != null) {
                if (!z || !z3) {
                    wakeLock2.release();
                } else {
                    wakeLock2.acquire();
                }
            }
        }
    }

    public WakeLockManager(Context context, Looper looper, Clock clock) {
        this.wakeLockManagerInternal = new WakeLockManagerInternal(context.getApplicationContext());
        this.wakeLockHandler = clock.createHandler(looper, (Handler.Callback) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setEnabled$0(boolean z, boolean z3) {
        this.wakeLockManagerInternal.updateWakeLock(z, z3);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setStayAwake$1(boolean z) {
        this.wakeLockManagerInternal.updateWakeLock(true, z);
    }

    public void setEnabled(boolean z) {
        if (this.enabled != z) {
            this.enabled = z;
            this.wakeLockHandler.post(new B(this, z, this.stayAwake, 0));
        }
    }

    public void setStayAwake(boolean z) {
        if (this.stayAwake != z) {
            this.stayAwake = z;
            if (this.enabled) {
                this.wakeLockHandler.post(new A(this, z, 0));
            }
        }
    }
}
