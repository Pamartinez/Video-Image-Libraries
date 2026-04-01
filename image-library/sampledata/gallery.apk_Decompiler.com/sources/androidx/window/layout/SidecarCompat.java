package androidx.window.layout;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.window.core.SpecificationComputer;
import androidx.window.core.Version;
import androidx.window.layout.ExtensionInterfaceCompat;
import androidx.window.sidecar.SidecarDeviceState;
import androidx.window.sidecar.SidecarInterface;
import androidx.window.sidecar.SidecarProvider;
import androidx.window.sidecar.SidecarWindowLayoutInfo;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 +2\u00020\u0001:\u0005+,-./B\u001d\b\u0007\u0012\n\b\u0001\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u0006\u0010\nJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u0010\u0010\u000fJ\u0017\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0018\u0010\u000fJ\u001d\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u001d\u0010\u000fJ\u000f\u0010\u001f\u001a\u00020\u001eH\u0017¢\u0006\u0004\b\u001f\u0010 R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010!\u001a\u0004\b\"\u0010#R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010$R \u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u000b0%8\u0002X\u0004¢\u0006\u0006\n\u0004\b&\u0010'R \u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020(0%8\u0002X\u0004¢\u0006\u0006\n\u0004\b)\u0010'R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010*¨\u00060"}, d2 = {"Landroidx/window/layout/SidecarCompat;", "Landroidx/window/layout/ExtensionInterfaceCompat;", "Landroidx/window/sidecar/SidecarInterface;", "sidecar", "Landroidx/window/layout/SidecarAdapter;", "sidecarAdapter", "<init>", "(Landroidx/window/sidecar/SidecarInterface;Landroidx/window/layout/SidecarAdapter;)V", "Landroid/content/Context;", "context", "(Landroid/content/Context;)V", "Landroid/app/Activity;", "activity", "Lme/x;", "registerConfigurationChangeListener", "(Landroid/app/Activity;)V", "unregisterComponentCallback", "Landroidx/window/layout/ExtensionInterfaceCompat$ExtensionCallbackInterface;", "extensionCallback", "setExtensionCallback", "(Landroidx/window/layout/ExtensionInterfaceCompat$ExtensionCallbackInterface;)V", "Landroidx/window/layout/WindowLayoutInfo;", "getWindowLayoutInfo", "(Landroid/app/Activity;)Landroidx/window/layout/WindowLayoutInfo;", "onWindowLayoutChangeListenerAdded", "Landroid/os/IBinder;", "windowToken", "register", "(Landroid/os/IBinder;Landroid/app/Activity;)V", "onWindowLayoutChangeListenerRemoved", "", "validateExtensionInterface", "()Z", "Landroidx/window/sidecar/SidecarInterface;", "getSidecar", "()Landroidx/window/sidecar/SidecarInterface;", "Landroidx/window/layout/SidecarAdapter;", "", "windowListenerRegisteredContexts", "Ljava/util/Map;", "Landroid/content/ComponentCallbacks;", "componentCallbackMap", "Landroidx/window/layout/ExtensionInterfaceCompat$ExtensionCallbackInterface;", "Companion", "DistinctElementCallback", "DistinctSidecarElementCallback", "FirstAttachAdapter", "TranslatingCallback", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SidecarCompat implements ExtensionInterfaceCompat {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "SidecarCompat";
    private final Map<Activity, ComponentCallbacks> componentCallbackMap;
    /* access modifiers changed from: private */
    public ExtensionInterfaceCompat.ExtensionCallbackInterface extensionCallback;
    private final SidecarInterface sidecar;
    /* access modifiers changed from: private */
    public final SidecarAdapter sidecarAdapter;
    /* access modifiers changed from: private */
    public final Map<IBinder, Activity> windowListenerRegisteredContexts;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0002\b\rJ\u0017\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0012R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Landroidx/window/layout/SidecarCompat$Companion;", "", "()V", "TAG", "", "sidecarVersion", "Landroidx/window/core/Version;", "getSidecarVersion", "()Landroidx/window/core/Version;", "getActivityWindowToken", "Landroid/os/IBinder;", "activity", "Landroid/app/Activity;", "getActivityWindowToken$window_release", "getSidecarCompat", "Landroidx/window/sidecar/SidecarInterface;", "context", "Landroid/content/Context;", "getSidecarCompat$window_release", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final IBinder getActivityWindowToken$window_release(Activity activity) {
            Window window;
            WindowManager.LayoutParams attributes;
            if (activity == null || (window = activity.getWindow()) == null || (attributes = window.getAttributes()) == null) {
                return null;
            }
            return attributes.token;
        }

        public final SidecarInterface getSidecarCompat$window_release(Context context) {
            j.e(context, "context");
            return SidecarProvider.getSidecarImpl(context.getApplicationContext());
        }

        public final Version getSidecarVersion() {
            try {
                String apiVersion = SidecarProvider.getApiVersion();
                if (!TextUtils.isEmpty(apiVersion)) {
                    return Version.Companion.parse(apiVersion);
                }
                return null;
            } catch (NoClassDefFoundError | UnsupportedOperationException unused) {
                return null;
            }
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u001f\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR \u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00070\u00108\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/window/layout/SidecarCompat$DistinctElementCallback;", "Landroidx/window/layout/ExtensionInterfaceCompat$ExtensionCallbackInterface;", "callbackInterface", "<init>", "(Landroidx/window/layout/ExtensionInterfaceCompat$ExtensionCallbackInterface;)V", "Landroid/app/Activity;", "activity", "Landroidx/window/layout/WindowLayoutInfo;", "newLayout", "Lme/x;", "onWindowLayoutChanged", "(Landroid/app/Activity;Landroidx/window/layout/WindowLayoutInfo;)V", "Landroidx/window/layout/ExtensionInterfaceCompat$ExtensionCallbackInterface;", "Ljava/util/concurrent/locks/ReentrantLock;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "Ljava/util/WeakHashMap;", "activityWindowLayoutInfo", "Ljava/util/WeakHashMap;", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DistinctElementCallback implements ExtensionInterfaceCompat.ExtensionCallbackInterface {
        private final WeakHashMap<Activity, WindowLayoutInfo> activityWindowLayoutInfo = new WeakHashMap<>();
        private final ExtensionInterfaceCompat.ExtensionCallbackInterface callbackInterface;
        private final ReentrantLock lock = new ReentrantLock();

        public DistinctElementCallback(ExtensionInterfaceCompat.ExtensionCallbackInterface extensionCallbackInterface) {
            j.e(extensionCallbackInterface, "callbackInterface");
            this.callbackInterface = extensionCallbackInterface;
        }

        public void onWindowLayoutChanged(Activity activity, WindowLayoutInfo windowLayoutInfo) {
            j.e(activity, "activity");
            j.e(windowLayoutInfo, "newLayout");
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                if (!windowLayoutInfo.equals(this.activityWindowLayoutInfo.get(activity))) {
                    WindowLayoutInfo put = this.activityWindowLayoutInfo.put(activity, windowLayoutInfo);
                    reentrantLock.unlock();
                    this.callbackInterface.onWindowLayoutChanged(activity, windowLayoutInfo);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u0010\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0012R\u0014\u0010\u0004\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R \u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u000e0\u00198\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Landroidx/window/layout/SidecarCompat$DistinctSidecarElementCallback;", "Landroidx/window/sidecar/SidecarInterface$SidecarCallback;", "Landroidx/window/layout/SidecarAdapter;", "sidecarAdapter", "callbackInterface", "<init>", "(Landroidx/window/layout/SidecarAdapter;Landroidx/window/sidecar/SidecarInterface$SidecarCallback;)V", "Landroidx/window/sidecar/SidecarDeviceState;", "newDeviceState", "Lme/x;", "onDeviceStateChanged", "(Landroidx/window/sidecar/SidecarDeviceState;)V", "Landroid/os/IBinder;", "token", "Landroidx/window/sidecar/SidecarWindowLayoutInfo;", "newLayout", "onWindowLayoutChanged", "(Landroid/os/IBinder;Landroidx/window/sidecar/SidecarWindowLayoutInfo;)V", "Landroidx/window/layout/SidecarAdapter;", "Landroidx/window/sidecar/SidecarInterface$SidecarCallback;", "Ljava/util/concurrent/locks/ReentrantLock;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "lastDeviceState", "Landroidx/window/sidecar/SidecarDeviceState;", "Ljava/util/WeakHashMap;", "mActivityWindowLayoutInfo", "Ljava/util/WeakHashMap;", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DistinctSidecarElementCallback implements SidecarInterface.SidecarCallback {
        private final SidecarInterface.SidecarCallback callbackInterface;
        private SidecarDeviceState lastDeviceState;
        private final ReentrantLock lock = new ReentrantLock();
        private final WeakHashMap<IBinder, SidecarWindowLayoutInfo> mActivityWindowLayoutInfo = new WeakHashMap<>();
        private final SidecarAdapter sidecarAdapter;

        public DistinctSidecarElementCallback(SidecarAdapter sidecarAdapter2, SidecarInterface.SidecarCallback sidecarCallback) {
            j.e(sidecarAdapter2, "sidecarAdapter");
            j.e(sidecarCallback, "callbackInterface");
            this.sidecarAdapter = sidecarAdapter2;
            this.callbackInterface = sidecarCallback;
        }

        public void onDeviceStateChanged(SidecarDeviceState sidecarDeviceState) {
            j.e(sidecarDeviceState, "newDeviceState");
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                if (!this.sidecarAdapter.isEqualSidecarDeviceState(this.lastDeviceState, sidecarDeviceState)) {
                    this.lastDeviceState = sidecarDeviceState;
                    this.callbackInterface.onDeviceStateChanged(sidecarDeviceState);
                    reentrantLock.unlock();
                }
            } finally {
                reentrantLock.unlock();
            }
        }

        public void onWindowLayoutChanged(IBinder iBinder, SidecarWindowLayoutInfo sidecarWindowLayoutInfo) {
            j.e(iBinder, "token");
            j.e(sidecarWindowLayoutInfo, "newLayout");
            synchronized (this.lock) {
                if (!this.sidecarAdapter.isEqualSidecarWindowLayoutInfo(this.mActivityWindowLayoutInfo.get(iBinder), sidecarWindowLayoutInfo)) {
                    SidecarWindowLayoutInfo put = this.mActivityWindowLayoutInfo.put(iBinder, sidecarWindowLayoutInfo);
                    this.callbackInterface.onWindowLayoutChanged(iBinder, sidecarWindowLayoutInfo);
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\r\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\r\u0010\fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000eR\"\u0010\u0011\u001a\u0010\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u00040\u00040\u000f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/window/layout/SidecarCompat$FirstAttachAdapter;", "Landroid/view/View$OnAttachStateChangeListener;", "Landroidx/window/layout/SidecarCompat;", "sidecarCompat", "Landroid/app/Activity;", "activity", "<init>", "(Landroidx/window/layout/SidecarCompat;Landroid/app/Activity;)V", "Landroid/view/View;", "view", "Lme/x;", "onViewAttachedToWindow", "(Landroid/view/View;)V", "onViewDetachedFromWindow", "Landroidx/window/layout/SidecarCompat;", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "activityWeakReference", "Ljava/lang/ref/WeakReference;", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FirstAttachAdapter implements View.OnAttachStateChangeListener {
        private final WeakReference<Activity> activityWeakReference;
        private final SidecarCompat sidecarCompat;

        public FirstAttachAdapter(SidecarCompat sidecarCompat2, Activity activity) {
            j.e(sidecarCompat2, "sidecarCompat");
            j.e(activity, "activity");
            this.sidecarCompat = sidecarCompat2;
            this.activityWeakReference = new WeakReference<>(activity);
        }

        public void onViewAttachedToWindow(View view) {
            j.e(view, "view");
            view.removeOnAttachStateChangeListener(this);
            Activity activity = this.activityWeakReference.get();
            IBinder activityWindowToken$window_release = SidecarCompat.Companion.getActivityWindowToken$window_release(activity);
            if (activity != null && activityWindowToken$window_release != null) {
                this.sidecarCompat.register(activityWindowToken$window_release, activity);
            }
        }

        public void onViewDetachedFromWindow(View view) {
            j.e(view, "view");
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0017¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\r\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0017¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/window/layout/SidecarCompat$TranslatingCallback;", "Landroidx/window/sidecar/SidecarInterface$SidecarCallback;", "<init>", "(Landroidx/window/layout/SidecarCompat;)V", "Landroidx/window/sidecar/SidecarDeviceState;", "newDeviceState", "Lme/x;", "onDeviceStateChanged", "(Landroidx/window/sidecar/SidecarDeviceState;)V", "Landroid/os/IBinder;", "windowToken", "Landroidx/window/sidecar/SidecarWindowLayoutInfo;", "newLayout", "onWindowLayoutChanged", "(Landroid/os/IBinder;Landroidx/window/sidecar/SidecarWindowLayoutInfo;)V", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class TranslatingCallback implements SidecarInterface.SidecarCallback {
        final /* synthetic */ SidecarCompat this$0;

        public TranslatingCallback(SidecarCompat sidecarCompat) {
            j.e(sidecarCompat, "this$0");
            this.this$0 = sidecarCompat;
        }

        public void onDeviceStateChanged(SidecarDeviceState sidecarDeviceState) {
            SidecarInterface sidecar;
            j.e(sidecarDeviceState, "newDeviceState");
            SidecarCompat sidecarCompat = this.this$0;
            for (Activity activity : this.this$0.windowListenerRegisteredContexts.values()) {
                IBinder activityWindowToken$window_release = SidecarCompat.Companion.getActivityWindowToken$window_release(activity);
                SidecarWindowLayoutInfo sidecarWindowLayoutInfo = null;
                if (!(activityWindowToken$window_release == null || (sidecar = sidecarCompat.getSidecar()) == null)) {
                    sidecarWindowLayoutInfo = sidecar.getWindowLayoutInfo(activityWindowToken$window_release);
                }
                ExtensionInterfaceCompat.ExtensionCallbackInterface access$getExtensionCallback$p = sidecarCompat.extensionCallback;
                if (access$getExtensionCallback$p != null) {
                    access$getExtensionCallback$p.onWindowLayoutChanged(activity, sidecarCompat.sidecarAdapter.translate(sidecarWindowLayoutInfo, sidecarDeviceState));
                }
            }
        }

        public void onWindowLayoutChanged(IBinder iBinder, SidecarWindowLayoutInfo sidecarWindowLayoutInfo) {
            SidecarDeviceState sidecarDeviceState;
            j.e(iBinder, "windowToken");
            j.e(sidecarWindowLayoutInfo, "newLayout");
            Activity activity = (Activity) this.this$0.windowListenerRegisteredContexts.get(iBinder);
            if (activity == null) {
                Log.w(SidecarCompat.TAG, "Unable to resolve activity from window token. Missing a call to #onWindowLayoutChangeListenerAdded()?");
                return;
            }
            SidecarAdapter access$getSidecarAdapter$p = this.this$0.sidecarAdapter;
            SidecarInterface sidecar = this.this$0.getSidecar();
            if (sidecar == null) {
                sidecarDeviceState = null;
            } else {
                sidecarDeviceState = sidecar.getDeviceState();
            }
            if (sidecarDeviceState == null) {
                sidecarDeviceState = new SidecarDeviceState();
            }
            WindowLayoutInfo translate = access$getSidecarAdapter$p.translate(sidecarWindowLayoutInfo, sidecarDeviceState);
            ExtensionInterfaceCompat.ExtensionCallbackInterface access$getExtensionCallback$p = this.this$0.extensionCallback;
            if (access$getExtensionCallback$p != null) {
                access$getExtensionCallback$p.onWindowLayoutChanged(activity, translate);
            }
        }
    }

    public SidecarCompat(SidecarInterface sidecarInterface, SidecarAdapter sidecarAdapter2) {
        j.e(sidecarAdapter2, "sidecarAdapter");
        this.sidecar = sidecarInterface;
        this.sidecarAdapter = sidecarAdapter2;
        this.windowListenerRegisteredContexts = new LinkedHashMap();
        this.componentCallbackMap = new LinkedHashMap();
    }

    private final void registerConfigurationChangeListener(Activity activity) {
        if (this.componentCallbackMap.get(activity) == null) {
            SidecarCompat$registerConfigurationChangeListener$configChangeObserver$1 sidecarCompat$registerConfigurationChangeListener$configChangeObserver$1 = new SidecarCompat$registerConfigurationChangeListener$configChangeObserver$1(this, activity);
            this.componentCallbackMap.put(activity, sidecarCompat$registerConfigurationChangeListener$configChangeObserver$1);
            activity.registerComponentCallbacks(sidecarCompat$registerConfigurationChangeListener$configChangeObserver$1);
        }
    }

    private final void unregisterComponentCallback(Activity activity) {
        activity.unregisterComponentCallbacks(this.componentCallbackMap.get(activity));
        this.componentCallbackMap.remove(activity);
    }

    public final SidecarInterface getSidecar() {
        return this.sidecar;
    }

    public final WindowLayoutInfo getWindowLayoutInfo(Activity activity) {
        SidecarWindowLayoutInfo sidecarWindowLayoutInfo;
        j.e(activity, "activity");
        IBinder activityWindowToken$window_release = Companion.getActivityWindowToken$window_release(activity);
        if (activityWindowToken$window_release == null) {
            return new WindowLayoutInfo(C1202t.d);
        }
        SidecarInterface sidecarInterface = this.sidecar;
        SidecarDeviceState sidecarDeviceState = null;
        if (sidecarInterface == null) {
            sidecarWindowLayoutInfo = null;
        } else {
            sidecarWindowLayoutInfo = sidecarInterface.getWindowLayoutInfo(activityWindowToken$window_release);
        }
        SidecarAdapter sidecarAdapter2 = this.sidecarAdapter;
        SidecarInterface sidecarInterface2 = this.sidecar;
        if (sidecarInterface2 != null) {
            sidecarDeviceState = sidecarInterface2.getDeviceState();
        }
        if (sidecarDeviceState == null) {
            sidecarDeviceState = new SidecarDeviceState();
        }
        return sidecarAdapter2.translate(sidecarWindowLayoutInfo, sidecarDeviceState);
    }

    public void onWindowLayoutChangeListenerAdded(Activity activity) {
        j.e(activity, "activity");
        IBinder activityWindowToken$window_release = Companion.getActivityWindowToken$window_release(activity);
        if (activityWindowToken$window_release != null) {
            register(activityWindowToken$window_release, activity);
            return;
        }
        activity.getWindow().getDecorView().addOnAttachStateChangeListener(new FirstAttachAdapter(this, activity));
    }

    public void onWindowLayoutChangeListenerRemoved(Activity activity) {
        boolean z;
        SidecarInterface sidecarInterface;
        j.e(activity, "activity");
        IBinder activityWindowToken$window_release = Companion.getActivityWindowToken$window_release(activity);
        if (activityWindowToken$window_release != null) {
            SidecarInterface sidecarInterface2 = this.sidecar;
            if (sidecarInterface2 != null) {
                sidecarInterface2.onWindowLayoutChangeListenerRemoved(activityWindowToken$window_release);
            }
            unregisterComponentCallback(activity);
            if (this.windowListenerRegisteredContexts.size() == 1) {
                z = true;
            } else {
                z = false;
            }
            this.windowListenerRegisteredContexts.remove(activityWindowToken$window_release);
            if (z && (sidecarInterface = this.sidecar) != null) {
                sidecarInterface.onDeviceStateListenersChanged(true);
            }
        }
    }

    public final void register(IBinder iBinder, Activity activity) {
        SidecarInterface sidecarInterface;
        j.e(iBinder, "windowToken");
        j.e(activity, "activity");
        this.windowListenerRegisteredContexts.put(iBinder, activity);
        SidecarInterface sidecarInterface2 = this.sidecar;
        if (sidecarInterface2 != null) {
            sidecarInterface2.onWindowLayoutChangeListenerAdded(iBinder);
        }
        if (this.windowListenerRegisteredContexts.size() == 1 && (sidecarInterface = this.sidecar) != null) {
            sidecarInterface.onDeviceStateListenersChanged(false);
        }
        ExtensionInterfaceCompat.ExtensionCallbackInterface extensionCallbackInterface = this.extensionCallback;
        if (extensionCallbackInterface != null) {
            extensionCallbackInterface.onWindowLayoutChanged(activity, getWindowLayoutInfo(activity));
        }
        registerConfigurationChangeListener(activity);
    }

    public void setExtensionCallback(ExtensionInterfaceCompat.ExtensionCallbackInterface extensionCallbackInterface) {
        j.e(extensionCallbackInterface, "extensionCallback");
        this.extensionCallback = new DistinctElementCallback(extensionCallbackInterface);
        SidecarInterface sidecarInterface = this.sidecar;
        if (sidecarInterface != null) {
            sidecarInterface.setSidecarCallback(new DistinctSidecarElementCallback(this.sidecarAdapter, new TranslatingCallback(this)));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:98:?, code lost:
        return true;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:73:0x0106 */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x001e A[Catch:{ NoSuchFieldError -> 0x00b7, all -> 0x018a }] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002a A[Catch:{ NoSuchFieldError -> 0x00b7, all -> 0x018a }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0056 A[Catch:{ NoSuchFieldError -> 0x00b7, all -> 0x018a }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0058 A[Catch:{ NoSuchFieldError -> 0x00b7, all -> 0x018a }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0064 A[Catch:{ NoSuchFieldError -> 0x00b7, all -> 0x018a }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x007d A[Catch:{ NoSuchFieldError -> 0x00b7, all -> 0x018a }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x007f A[Catch:{ NoSuchFieldError -> 0x00b7, all -> 0x018a }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0089 A[Catch:{ NoSuchFieldError -> 0x00b7, all -> 0x018a }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00a2 A[Catch:{ NoSuchFieldError -> 0x00b7, all -> 0x018a }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00a4 A[Catch:{ NoSuchFieldError -> 0x00b7, all -> 0x018a }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00ae A[Catch:{ NoSuchFieldError -> 0x00b7, all -> 0x018a }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0131 A[Catch:{ NoSuchFieldError -> 0x00b7, all -> 0x018a }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0142 A[Catch:{ NoSuchFieldError -> 0x00b7, all -> 0x018a }] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x015a A[Catch:{ NoSuchFieldError -> 0x00b7, all -> 0x018a }] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0166 A[Catch:{ NoSuchFieldError -> 0x00b7, all -> 0x018a }] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0172 A[Catch:{ NoSuchFieldError -> 0x00b7, all -> 0x018a }] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x017e A[Catch:{ NoSuchFieldError -> 0x00b7, all -> 0x018a }] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001c A[Catch:{ NoSuchFieldError -> 0x00b7, all -> 0x018a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean validateExtensionInterface() {
        /*
            r7 = this;
            androidx.window.sidecar.SidecarInterface r0 = r7.sidecar     // Catch:{ all -> 0x018a }
            r1 = 0
            if (r0 != 0) goto L_0x0007
        L_0x0005:
            r0 = r1
            goto L_0x001a
        L_0x0007:
            java.lang.Class r0 = r0.getClass()     // Catch:{ all -> 0x018a }
            if (r0 != 0) goto L_0x000e
            goto L_0x0005
        L_0x000e:
            java.lang.String r2 = "setSidecarCallback"
            java.lang.Class<androidx.window.sidecar.SidecarInterface$SidecarCallback> r3 = androidx.window.sidecar.SidecarInterface.SidecarCallback.class
            java.lang.Class[] r3 = new java.lang.Class[]{r3}     // Catch:{ all -> 0x018a }
            java.lang.reflect.Method r0 = r0.getMethod(r2, r3)     // Catch:{ all -> 0x018a }
        L_0x001a:
            if (r0 != 0) goto L_0x001e
            r0 = r1
            goto L_0x0022
        L_0x001e:
            java.lang.Class r0 = r0.getReturnType()     // Catch:{ all -> 0x018a }
        L_0x0022:
            java.lang.Class r2 = java.lang.Void.TYPE     // Catch:{ all -> 0x018a }
            boolean r3 = kotlin.jvm.internal.j.a(r0, r2)     // Catch:{ all -> 0x018a }
            if (r3 == 0) goto L_0x017e
            androidx.window.sidecar.SidecarInterface r0 = r7.sidecar     // Catch:{ all -> 0x018a }
            if (r0 != 0) goto L_0x002f
            goto L_0x0032
        L_0x002f:
            r0.getDeviceState()     // Catch:{ all -> 0x018a }
        L_0x0032:
            androidx.window.sidecar.SidecarInterface r0 = r7.sidecar     // Catch:{ all -> 0x018a }
            r3 = 1
            if (r0 != 0) goto L_0x0038
            goto L_0x003b
        L_0x0038:
            r0.onDeviceStateListenersChanged(r3)     // Catch:{ all -> 0x018a }
        L_0x003b:
            androidx.window.sidecar.SidecarInterface r0 = r7.sidecar     // Catch:{ all -> 0x018a }
            java.lang.Class<android.os.IBinder> r4 = android.os.IBinder.class
            if (r0 != 0) goto L_0x0043
        L_0x0041:
            r0 = r1
            goto L_0x0054
        L_0x0043:
            java.lang.Class r0 = r0.getClass()     // Catch:{ all -> 0x018a }
            if (r0 != 0) goto L_0x004a
            goto L_0x0041
        L_0x004a:
            java.lang.String r5 = "getWindowLayoutInfo"
            java.lang.Class[] r6 = new java.lang.Class[]{r4}     // Catch:{ all -> 0x018a }
            java.lang.reflect.Method r0 = r0.getMethod(r5, r6)     // Catch:{ all -> 0x018a }
        L_0x0054:
            if (r0 != 0) goto L_0x0058
            r0 = r1
            goto L_0x005c
        L_0x0058:
            java.lang.Class r0 = r0.getReturnType()     // Catch:{ all -> 0x018a }
        L_0x005c:
            java.lang.Class<androidx.window.sidecar.SidecarWindowLayoutInfo> r5 = androidx.window.sidecar.SidecarWindowLayoutInfo.class
            boolean r5 = kotlin.jvm.internal.j.a(r0, r5)     // Catch:{ all -> 0x018a }
            if (r5 == 0) goto L_0x0172
            androidx.window.sidecar.SidecarInterface r0 = r7.sidecar     // Catch:{ all -> 0x018a }
            if (r0 != 0) goto L_0x006a
        L_0x0068:
            r0 = r1
            goto L_0x007b
        L_0x006a:
            java.lang.Class r0 = r0.getClass()     // Catch:{ all -> 0x018a }
            if (r0 != 0) goto L_0x0071
            goto L_0x0068
        L_0x0071:
            java.lang.String r5 = "onWindowLayoutChangeListenerAdded"
            java.lang.Class[] r6 = new java.lang.Class[]{r4}     // Catch:{ all -> 0x018a }
            java.lang.reflect.Method r0 = r0.getMethod(r5, r6)     // Catch:{ all -> 0x018a }
        L_0x007b:
            if (r0 != 0) goto L_0x007f
            r0 = r1
            goto L_0x0083
        L_0x007f:
            java.lang.Class r0 = r0.getReturnType()     // Catch:{ all -> 0x018a }
        L_0x0083:
            boolean r5 = kotlin.jvm.internal.j.a(r0, r2)     // Catch:{ all -> 0x018a }
            if (r5 == 0) goto L_0x0166
            androidx.window.sidecar.SidecarInterface r7 = r7.sidecar     // Catch:{ all -> 0x018a }
            if (r7 != 0) goto L_0x008f
        L_0x008d:
            r7 = r1
            goto L_0x00a0
        L_0x008f:
            java.lang.Class r7 = r7.getClass()     // Catch:{ all -> 0x018a }
            if (r7 != 0) goto L_0x0096
            goto L_0x008d
        L_0x0096:
            java.lang.String r0 = "onWindowLayoutChangeListenerRemoved"
            java.lang.Class[] r4 = new java.lang.Class[]{r4}     // Catch:{ all -> 0x018a }
            java.lang.reflect.Method r7 = r7.getMethod(r0, r4)     // Catch:{ all -> 0x018a }
        L_0x00a0:
            if (r7 != 0) goto L_0x00a4
            r7 = r1
            goto L_0x00a8
        L_0x00a4:
            java.lang.Class r7 = r7.getReturnType()     // Catch:{ all -> 0x018a }
        L_0x00a8:
            boolean r0 = kotlin.jvm.internal.j.a(r7, r2)     // Catch:{ all -> 0x018a }
            if (r0 == 0) goto L_0x015a
            androidx.window.sidecar.SidecarDeviceState r7 = new androidx.window.sidecar.SidecarDeviceState     // Catch:{ all -> 0x018a }
            r7.<init>()     // Catch:{ all -> 0x018a }
            r0 = 3
            r7.posture = r0     // Catch:{ NoSuchFieldError -> 0x00b7 }
            goto L_0x00e6
        L_0x00b7:
            java.lang.Class<androidx.window.sidecar.SidecarDeviceState> r2 = androidx.window.sidecar.SidecarDeviceState.class
            java.lang.String r4 = "setPosture"
            java.lang.Class r5 = java.lang.Integer.TYPE     // Catch:{ all -> 0x018a }
            java.lang.Class[] r5 = new java.lang.Class[]{r5}     // Catch:{ all -> 0x018a }
            java.lang.reflect.Method r2 = r2.getMethod(r4, r5)     // Catch:{ all -> 0x018a }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x018a }
            java.lang.Object[] r4 = new java.lang.Object[]{r4}     // Catch:{ all -> 0x018a }
            r2.invoke(r7, r4)     // Catch:{ all -> 0x018a }
            java.lang.Class<androidx.window.sidecar.SidecarDeviceState> r2 = androidx.window.sidecar.SidecarDeviceState.class
            java.lang.String r4 = "getPosture"
            java.lang.reflect.Method r2 = r2.getMethod(r4, r1)     // Catch:{ all -> 0x018a }
            java.lang.Object r7 = r2.invoke(r7, r1)     // Catch:{ all -> 0x018a }
            if (r7 == 0) goto L_0x0152
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ all -> 0x018a }
            int r7 = r7.intValue()     // Catch:{ all -> 0x018a }
            if (r7 != r0) goto L_0x014a
        L_0x00e6:
            androidx.window.sidecar.SidecarDisplayFeature r7 = new androidx.window.sidecar.SidecarDisplayFeature     // Catch:{ all -> 0x018a }
            r7.<init>()     // Catch:{ all -> 0x018a }
            android.graphics.Rect r0 = r7.getRect()     // Catch:{ all -> 0x018a }
            java.lang.String r2 = "displayFeature.rect"
            kotlin.jvm.internal.j.d(r0, r2)     // Catch:{ all -> 0x018a }
            r7.setRect(r0)     // Catch:{ all -> 0x018a }
            r7.getType()     // Catch:{ all -> 0x018a }
            r7.setType(r3)     // Catch:{ all -> 0x018a }
            androidx.window.sidecar.SidecarWindowLayoutInfo r0 = new androidx.window.sidecar.SidecarWindowLayoutInfo     // Catch:{ all -> 0x018a }
            r0.<init>()     // Catch:{ all -> 0x018a }
            java.util.List r7 = r0.displayFeatures     // Catch:{ NoSuchFieldError -> 0x0106 }
            goto L_0x018b
        L_0x0106:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x018a }
            r2.<init>()     // Catch:{ all -> 0x018a }
            r2.add(r7)     // Catch:{ all -> 0x018a }
            java.lang.Class<androidx.window.sidecar.SidecarWindowLayoutInfo> r7 = androidx.window.sidecar.SidecarWindowLayoutInfo.class
            java.lang.String r4 = "setDisplayFeatures"
            java.lang.Class<java.util.List> r5 = java.util.List.class
            java.lang.Class[] r5 = new java.lang.Class[]{r5}     // Catch:{ all -> 0x018a }
            java.lang.reflect.Method r7 = r7.getMethod(r4, r5)     // Catch:{ all -> 0x018a }
            java.lang.Object[] r4 = new java.lang.Object[]{r2}     // Catch:{ all -> 0x018a }
            r7.invoke(r0, r4)     // Catch:{ all -> 0x018a }
            java.lang.Class<androidx.window.sidecar.SidecarWindowLayoutInfo> r7 = androidx.window.sidecar.SidecarWindowLayoutInfo.class
            java.lang.String r4 = "getDisplayFeatures"
            java.lang.reflect.Method r7 = r7.getMethod(r4, r1)     // Catch:{ all -> 0x018a }
            java.lang.Object r7 = r7.invoke(r0, r1)     // Catch:{ all -> 0x018a }
            if (r7 == 0) goto L_0x0142
            java.util.List r7 = (java.util.List) r7     // Catch:{ all -> 0x018a }
            boolean r7 = r2.equals(r7)     // Catch:{ all -> 0x018a }
            if (r7 == 0) goto L_0x013a
            goto L_0x018b
        L_0x013a:
            java.lang.Exception r7 = new java.lang.Exception     // Catch:{ all -> 0x018a }
            java.lang.String r0 = "Invalid display feature getter/setter"
            r7.<init>(r0)     // Catch:{ all -> 0x018a }
            throw r7     // Catch:{ all -> 0x018a }
        L_0x0142:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException     // Catch:{ all -> 0x018a }
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.collections.List<androidx.window.sidecar.SidecarDisplayFeature>"
            r7.<init>(r0)     // Catch:{ all -> 0x018a }
            throw r7     // Catch:{ all -> 0x018a }
        L_0x014a:
            java.lang.Exception r7 = new java.lang.Exception     // Catch:{ all -> 0x018a }
            java.lang.String r0 = "Invalid device posture getter/setter"
            r7.<init>(r0)     // Catch:{ all -> 0x018a }
            throw r7     // Catch:{ all -> 0x018a }
        L_0x0152:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException     // Catch:{ all -> 0x018a }
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.Int"
            r7.<init>(r0)     // Catch:{ all -> 0x018a }
            throw r7     // Catch:{ all -> 0x018a }
        L_0x015a:
            java.lang.NoSuchMethodException r0 = new java.lang.NoSuchMethodException     // Catch:{ all -> 0x018a }
            java.lang.String r1 = "Illegal return type for 'onWindowLayoutChangeListenerRemoved': "
            java.lang.String r7 = kotlin.jvm.internal.j.j(r7, r1)     // Catch:{ all -> 0x018a }
            r0.<init>(r7)     // Catch:{ all -> 0x018a }
            throw r0     // Catch:{ all -> 0x018a }
        L_0x0166:
            java.lang.NoSuchMethodException r7 = new java.lang.NoSuchMethodException     // Catch:{ all -> 0x018a }
            java.lang.String r1 = "Illegal return type for 'onWindowLayoutChangeListenerAdded': "
            java.lang.String r0 = kotlin.jvm.internal.j.j(r0, r1)     // Catch:{ all -> 0x018a }
            r7.<init>(r0)     // Catch:{ all -> 0x018a }
            throw r7     // Catch:{ all -> 0x018a }
        L_0x0172:
            java.lang.NoSuchMethodException r7 = new java.lang.NoSuchMethodException     // Catch:{ all -> 0x018a }
            java.lang.String r1 = "Illegal return type for 'getWindowLayoutInfo': "
            java.lang.String r0 = kotlin.jvm.internal.j.j(r0, r1)     // Catch:{ all -> 0x018a }
            r7.<init>(r0)     // Catch:{ all -> 0x018a }
            throw r7     // Catch:{ all -> 0x018a }
        L_0x017e:
            java.lang.NoSuchMethodException r7 = new java.lang.NoSuchMethodException     // Catch:{ all -> 0x018a }
            java.lang.String r1 = "Illegal return type for 'setSidecarCallback': "
            java.lang.String r0 = kotlin.jvm.internal.j.j(r0, r1)     // Catch:{ all -> 0x018a }
            r7.<init>(r0)     // Catch:{ all -> 0x018a }
            throw r7     // Catch:{ all -> 0x018a }
        L_0x018a:
            r3 = 0
        L_0x018b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.SidecarCompat.validateExtensionInterface():boolean");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SidecarCompat(Context context) {
        this(Companion.getSidecarCompat$window_release(context), new SidecarAdapter((SpecificationComputer.VerificationMode) null, 1, (e) null));
        j.e(context, "context");
    }
}
