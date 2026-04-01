package androidx.media3.common.util;

import Da.f;
import O.a;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyDisplayInfo;
import android.telephony.TelephonyManager;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class NetworkTypeObserver {
    private static NetworkTypeObserver staticInstance;
    /* access modifiers changed from: private */
    public final Executor backgroundExecutor;
    private boolean isInitialized;
    private final CopyOnWriteArrayList<ListenerHolder> listeners = new CopyOnWriteArrayList<>();
    private final Object lock = new Object();
    private int networkType = 0;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Api31 {

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class DisplayInfoCallback extends TelephonyCallback implements TelephonyCallback.DisplayInfoListener {
            private final NetworkTypeObserver instance;

            public DisplayInfoCallback(NetworkTypeObserver networkTypeObserver) {
                this.instance = networkTypeObserver;
            }

            public void onDisplayInfoChanged(TelephonyDisplayInfo telephonyDisplayInfo) {
                boolean z;
                int overrideNetworkType = telephonyDisplayInfo.getOverrideNetworkType();
                int i2 = 5;
                if (overrideNetworkType == 3 || overrideNetworkType == 4 || overrideNetworkType == 5) {
                    z = true;
                } else {
                    z = false;
                }
                NetworkTypeObserver networkTypeObserver = this.instance;
                if (z) {
                    i2 = 10;
                }
                networkTypeObserver.updateNetworkType(i2);
            }
        }

        public static void disambiguate4gAnd5gNsa(Context context, NetworkTypeObserver networkTypeObserver) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) Assertions.checkNotNull((TelephonyManager) context.getSystemService("phone"));
                DisplayInfoCallback displayInfoCallback = new DisplayInfoCallback(networkTypeObserver);
                telephonyManager.registerTelephonyCallback(networkTypeObserver.backgroundExecutor, displayInfoCallback);
                telephonyManager.unregisterTelephonyCallback(displayInfoCallback);
            } catch (RuntimeException unused) {
                networkTypeObserver.updateNetworkType(5);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class ListenerHolder {
        private final Executor executor;
        private final WeakReference<Listener> listener;

        public ListenerHolder(Listener listener2, Executor executor2) {
            this.listener = new WeakReference<>(listener2);
            this.executor = executor2;
        }

        /* access modifiers changed from: private */
        public void lambda$callOnNetworkTypeChanged$0() {
            Listener listener2 = this.listener.get();
            if (listener2 != null) {
                ((a) listener2).f552a.onNetworkTypeChanged(NetworkTypeObserver.this.getNetworkType());
            }
        }

        public void callOnNetworkTypeChanged() {
            this.executor.execute(new a(this));
        }

        public boolean canBeRemoved() {
            if (this.listener.get() == null) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class Receiver extends BroadcastReceiver {
        private Receiver() {
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onReceive$0(Context context) {
            NetworkTypeObserver.this.handleConnectivityActionBroadcast(context);
        }

        public void onReceive(Context context, Intent intent) {
            NetworkTypeObserver.this.backgroundExecutor.execute(new b(this, context));
        }
    }

    private NetworkTypeObserver(Context context) {
        Executor executor = BackgroundExecutor.get();
        this.backgroundExecutor = executor;
        executor.execute(new f(9, this, context));
    }

    public static synchronized NetworkTypeObserver getInstance(Context context) {
        NetworkTypeObserver networkTypeObserver;
        synchronized (NetworkTypeObserver.class) {
            try {
                if (staticInstance == null) {
                    staticInstance = new NetworkTypeObserver(context);
                }
                networkTypeObserver = staticInstance;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return networkTypeObserver;
    }

    private static int getMobileNetworkType(NetworkInfo networkInfo) {
        switch (networkInfo.getSubtype()) {
            case 1:
            case 2:
                return 3;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 14:
            case 15:
            case 17:
                return 4;
            case 13:
                return 5;
            case 18:
                return 2;
            case 20:
                return 9;
            default:
                return 6;
        }
    }

    private static int getNetworkTypeFromConnectivityManager(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        int i2 = 0;
        if (connectivityManager == null) {
            return 0;
        }
        try {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            i2 = 1;
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                int type = activeNetworkInfo.getType();
                if (type != 0) {
                    if (type == 1) {
                        return 2;
                    }
                    if (!(type == 4 || type == 5)) {
                        if (type == 6) {
                            return 5;
                        }
                        if (type != 9) {
                            return 8;
                        }
                        return 7;
                    }
                }
                return getMobileNetworkType(activeNetworkInfo);
            }
        } catch (SecurityException unused) {
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public void handleConnectivityActionBroadcast(Context context) {
        int networkTypeFromConnectivityManager = getNetworkTypeFromConnectivityManager(context);
        if (Build.VERSION.SDK_INT < 31 || networkTypeFromConnectivityManager != 5) {
            updateNetworkType(networkTypeFromConnectivityManager);
        } else {
            Api31.disambiguate4gAnd5gNsa(context, this);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: init */
    public void lambda$new$0(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(new Receiver(), intentFilter);
    }

    private void removeClearedReferences() {
        Iterator<ListenerHolder> it = this.listeners.iterator();
        while (it.hasNext()) {
            ListenerHolder next = it.next();
            if (next.canBeRemoved()) {
                this.listeners.remove(next);
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        if (r2.hasNext() == false) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0024, code lost:
        r2.next().callOnNetworkTypeChanged();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateNetworkType(int r3) {
        /*
            r2 = this;
            r2.removeClearedReferences()
            java.lang.Object r0 = r2.lock
            monitor-enter(r0)
            boolean r1 = r2.isInitialized     // Catch:{ all -> 0x0010 }
            if (r1 == 0) goto L_0x0012
            int r1 = r2.networkType     // Catch:{ all -> 0x0010 }
            if (r1 != r3) goto L_0x0012
            monitor-exit(r0)     // Catch:{ all -> 0x0010 }
            return
        L_0x0010:
            r2 = move-exception
            goto L_0x002f
        L_0x0012:
            r1 = 1
            r2.isInitialized = r1     // Catch:{ all -> 0x0010 }
            r2.networkType = r3     // Catch:{ all -> 0x0010 }
            java.util.concurrent.CopyOnWriteArrayList<androidx.media3.common.util.NetworkTypeObserver$ListenerHolder> r2 = r2.listeners     // Catch:{ all -> 0x0010 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0010 }
            monitor-exit(r0)     // Catch:{ all -> 0x0010 }
        L_0x001e:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002e
            java.lang.Object r3 = r2.next()
            androidx.media3.common.util.NetworkTypeObserver$ListenerHolder r3 = (androidx.media3.common.util.NetworkTypeObserver.ListenerHolder) r3
            r3.callOnNetworkTypeChanged()
            goto L_0x001e
        L_0x002e:
            return
        L_0x002f:
            monitor-exit(r0)     // Catch:{ all -> 0x0010 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.common.util.NetworkTypeObserver.updateNetworkType(int):void");
    }

    public int getNetworkType() {
        int i2;
        synchronized (this.lock) {
            i2 = this.networkType;
        }
        return i2;
    }

    public void register(Listener listener, Executor executor) {
        boolean z;
        removeClearedReferences();
        ListenerHolder listenerHolder = new ListenerHolder(listener, executor);
        synchronized (this.lock) {
            this.listeners.add(listenerHolder);
            z = this.isInitialized;
        }
        if (z) {
            listenerHolder.callOnNetworkTypeChanged();
        }
    }
}
