package androidx.room;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import android.util.Log;
import androidx.room.InvalidationTracker;
import g0.C0189b;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0018\u001a\n \u0017*\u0004\u0018\u00010\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\"\u0010\u001b\u001a\u00020\u001a8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010\"\u001a\u00020!8\u0006@\u0006X.¢\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R$\u0010)\u001a\u0004\u0018\u00010(8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0017\u00100\u001a\u00020/8\u0006¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R\u0017\u00105\u001a\u0002048\u0006¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108R\u0017\u0010:\u001a\u0002098\u0006¢\u0006\f\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=R\u0017\u0010?\u001a\u00020>8\u0006¢\u0006\f\n\u0004\b?\u0010@\u001a\u0004\bA\u0010BR\u0017\u0010C\u001a\u00020>8\u0006¢\u0006\f\n\u0004\bC\u0010@\u001a\u0004\bD\u0010B¨\u0006E"}, d2 = {"Landroidx/room/MultiInstanceInvalidationClient;", "", "Landroid/content/Context;", "context", "", "name", "Landroid/content/Intent;", "serviceIntent", "Landroidx/room/InvalidationTracker;", "invalidationTracker", "Ljava/util/concurrent/Executor;", "executor", "<init>", "(Landroid/content/Context;Ljava/lang/String;Landroid/content/Intent;Landroidx/room/InvalidationTracker;Ljava/util/concurrent/Executor;)V", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "Landroidx/room/InvalidationTracker;", "getInvalidationTracker", "()Landroidx/room/InvalidationTracker;", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "kotlin.jvm.PlatformType", "appContext", "Landroid/content/Context;", "", "clientId", "I", "getClientId", "()I", "setClientId", "(I)V", "Landroidx/room/InvalidationTracker$Observer;", "observer", "Landroidx/room/InvalidationTracker$Observer;", "getObserver", "()Landroidx/room/InvalidationTracker$Observer;", "setObserver", "(Landroidx/room/InvalidationTracker$Observer;)V", "Landroidx/room/IMultiInstanceInvalidationService;", "service", "Landroidx/room/IMultiInstanceInvalidationService;", "getService", "()Landroidx/room/IMultiInstanceInvalidationService;", "setService", "(Landroidx/room/IMultiInstanceInvalidationService;)V", "Landroidx/room/IMultiInstanceInvalidationCallback;", "callback", "Landroidx/room/IMultiInstanceInvalidationCallback;", "getCallback", "()Landroidx/room/IMultiInstanceInvalidationCallback;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "stopped", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getStopped", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "Landroid/content/ServiceConnection;", "serviceConnection", "Landroid/content/ServiceConnection;", "getServiceConnection", "()Landroid/content/ServiceConnection;", "Ljava/lang/Runnable;", "setUpRunnable", "Ljava/lang/Runnable;", "getSetUpRunnable", "()Ljava/lang/Runnable;", "removeObserverRunnable", "getRemoveObserverRunnable", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MultiInstanceInvalidationClient {
    private final Context appContext;
    private final IMultiInstanceInvalidationCallback callback = new MultiInstanceInvalidationClient$callback$1(this);
    private int clientId;
    private final Executor executor;
    private final InvalidationTracker invalidationTracker;
    private final String name;
    public InvalidationTracker.Observer observer;
    private final Runnable removeObserverRunnable;
    private IMultiInstanceInvalidationService service;
    private final ServiceConnection serviceConnection;
    private final Runnable setUpRunnable;
    private final AtomicBoolean stopped = new AtomicBoolean(false);

    public MultiInstanceInvalidationClient(Context context, String str, Intent intent, InvalidationTracker invalidationTracker2, Executor executor2) {
        j.e(context, "context");
        j.e(str, "name");
        j.e(intent, "serviceIntent");
        j.e(invalidationTracker2, "invalidationTracker");
        j.e(executor2, "executor");
        this.name = str;
        this.invalidationTracker = invalidationTracker2;
        this.executor = executor2;
        Context applicationContext = context.getApplicationContext();
        this.appContext = applicationContext;
        MultiInstanceInvalidationClient$serviceConnection$1 multiInstanceInvalidationClient$serviceConnection$1 = new MultiInstanceInvalidationClient$serviceConnection$1(this);
        this.serviceConnection = multiInstanceInvalidationClient$serviceConnection$1;
        this.setUpRunnable = new C0189b(this, 0);
        this.removeObserverRunnable = new C0189b(this, 1);
        setObserver(new InvalidationTracker.Observer(this, (String[]) invalidationTracker2.getTableIdLookup$room_runtime_release().keySet().toArray(new String[0])) {
            final /* synthetic */ MultiInstanceInvalidationClient this$0;

            {
                this.this$0 = r1;
            }

            public boolean isRemote$room_runtime_release() {
                return true;
            }

            public void onInvalidated(Set<String> set) {
                j.e(set, "tables");
                if (!this.this$0.getStopped().get()) {
                    try {
                        IMultiInstanceInvalidationService service = this.this$0.getService();
                        if (service != null) {
                            service.broadcastInvalidation(this.this$0.getClientId(), (String[]) set.toArray(new String[0]));
                        }
                    } catch (RemoteException e) {
                        Log.w("ROOM", "Cannot broadcast invalidation", e);
                    }
                }
            }
        });
        applicationContext.bindService(intent, multiInstanceInvalidationClient$serviceConnection$1, 1);
    }

    /* access modifiers changed from: private */
    public static final void removeObserverRunnable$lambda$2(MultiInstanceInvalidationClient multiInstanceInvalidationClient) {
        j.e(multiInstanceInvalidationClient, "this$0");
        multiInstanceInvalidationClient.invalidationTracker.removeObserver(multiInstanceInvalidationClient.getObserver());
    }

    /* access modifiers changed from: private */
    public static final void setUpRunnable$lambda$1(MultiInstanceInvalidationClient multiInstanceInvalidationClient) {
        j.e(multiInstanceInvalidationClient, "this$0");
        try {
            IMultiInstanceInvalidationService iMultiInstanceInvalidationService = multiInstanceInvalidationClient.service;
            if (iMultiInstanceInvalidationService != null) {
                multiInstanceInvalidationClient.clientId = iMultiInstanceInvalidationService.registerCallback(multiInstanceInvalidationClient.callback, multiInstanceInvalidationClient.name);
                multiInstanceInvalidationClient.invalidationTracker.addObserver(multiInstanceInvalidationClient.getObserver());
            }
        } catch (RemoteException e) {
            Log.w("ROOM", "Cannot register multi-instance invalidation callback", e);
        }
    }

    public final int getClientId() {
        return this.clientId;
    }

    public final Executor getExecutor() {
        return this.executor;
    }

    public final InvalidationTracker getInvalidationTracker() {
        return this.invalidationTracker;
    }

    public final InvalidationTracker.Observer getObserver() {
        InvalidationTracker.Observer observer2 = this.observer;
        if (observer2 != null) {
            return observer2;
        }
        j.k("observer");
        throw null;
    }

    public final Runnable getRemoveObserverRunnable() {
        return this.removeObserverRunnable;
    }

    public final IMultiInstanceInvalidationService getService() {
        return this.service;
    }

    public final Runnable getSetUpRunnable() {
        return this.setUpRunnable;
    }

    public final AtomicBoolean getStopped() {
        return this.stopped;
    }

    public final void setObserver(InvalidationTracker.Observer observer2) {
        j.e(observer2, "<set-?>");
        this.observer = observer2;
    }

    public final void setService(IMultiInstanceInvalidationService iMultiInstanceInvalidationService) {
        this.service = iMultiInstanceInvalidationService;
    }
}
