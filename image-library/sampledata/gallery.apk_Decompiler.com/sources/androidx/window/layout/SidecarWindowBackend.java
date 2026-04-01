package androidx.window.layout;

import android.app.Activity;
import android.content.Context;
import androidx.core.util.Consumer;
import androidx.window.core.Version;
import androidx.window.embedding.c;
import androidx.window.layout.ExtensionInterfaceCompat;
import com.samsung.android.sdk.mobileservice.profile.Profile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u0000 #2\u00020\u0001:\u0003#$%B\u0013\b\u0007\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0003¢\u0006\u0004\b\f\u0010\rJ-\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0015\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u0005R&\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b8\u0006X\u0004¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u0012\u0004\b!\u0010\"\u001a\u0004\b\u001f\u0010 ¨\u0006&"}, d2 = {"Landroidx/window/layout/SidecarWindowBackend;", "Landroidx/window/layout/WindowBackend;", "Landroidx/window/layout/ExtensionInterfaceCompat;", "windowExtension", "<init>", "(Landroidx/window/layout/ExtensionInterfaceCompat;)V", "Landroid/app/Activity;", "activity", "", "isActivityRegistered", "(Landroid/app/Activity;)Z", "Lme/x;", "callbackRemovedForActivity", "(Landroid/app/Activity;)V", "Ljava/util/concurrent/Executor;", "executor", "Landroidx/core/util/Consumer;", "Landroidx/window/layout/WindowLayoutInfo;", "callback", "registerLayoutChangeCallback", "(Landroid/app/Activity;Ljava/util/concurrent/Executor;Landroidx/core/util/Consumer;)V", "unregisterLayoutChangeCallback", "(Landroidx/core/util/Consumer;)V", "Landroidx/window/layout/ExtensionInterfaceCompat;", "getWindowExtension", "()Landroidx/window/layout/ExtensionInterfaceCompat;", "setWindowExtension", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Landroidx/window/layout/SidecarWindowBackend$WindowLayoutChangeCallbackWrapper;", "windowLayoutChangeCallbacks", "Ljava/util/concurrent/CopyOnWriteArrayList;", "getWindowLayoutChangeCallbacks", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "getWindowLayoutChangeCallbacks$annotations", "()V", "Companion", "ExtensionListenerImpl", "WindowLayoutChangeCallbackWrapper", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SidecarWindowBackend implements WindowBackend {
    public static final Companion Companion = new Companion((e) null);
    public static final boolean DEBUG = false;
    private static final String TAG = "WindowServer";
    /* access modifiers changed from: private */
    public static volatile SidecarWindowBackend globalInstance;
    /* access modifiers changed from: private */
    public static final ReentrantLock globalLock = new ReentrantLock();
    private ExtensionInterfaceCompat windowExtension;
    private final CopyOnWriteArrayList<WindowLayoutChangeCallbackWrapper> windowLayoutChangeCallbacks = new CopyOnWriteArrayList<>();

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\u0012\u0010\u0003R\u0014\u0010\u0013\u001a\u00020\u000e8\u0006XT¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002XT¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Landroidx/window/layout/SidecarWindowBackend$Companion;", "", "<init>", "()V", "Landroid/content/Context;", "context", "Landroidx/window/layout/SidecarWindowBackend;", "getInstance", "(Landroid/content/Context;)Landroidx/window/layout/SidecarWindowBackend;", "Landroidx/window/layout/ExtensionInterfaceCompat;", "initAndVerifyExtension", "(Landroid/content/Context;)Landroidx/window/layout/ExtensionInterfaceCompat;", "Landroidx/window/core/Version;", "sidecarVersion", "", "isSidecarVersionSupported", "(Landroidx/window/core/Version;)Z", "Lme/x;", "resetInstance", "DEBUG", "Z", "", "TAG", "Ljava/lang/String;", "globalInstance", "Landroidx/window/layout/SidecarWindowBackend;", "Ljava/util/concurrent/locks/ReentrantLock;", "globalLock", "Ljava/util/concurrent/locks/ReentrantLock;", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final SidecarWindowBackend getInstance(Context context) {
            j.e(context, "context");
            if (SidecarWindowBackend.globalInstance == null) {
                ReentrantLock access$getGlobalLock$cp = SidecarWindowBackend.globalLock;
                access$getGlobalLock$cp.lock();
                try {
                    if (SidecarWindowBackend.globalInstance == null) {
                        SidecarWindowBackend.globalInstance = new SidecarWindowBackend(SidecarWindowBackend.Companion.initAndVerifyExtension(context));
                    }
                } finally {
                    access$getGlobalLock$cp.unlock();
                }
            }
            SidecarWindowBackend access$getGlobalInstance$cp = SidecarWindowBackend.globalInstance;
            j.b(access$getGlobalInstance$cp);
            return access$getGlobalInstance$cp;
        }

        public final ExtensionInterfaceCompat initAndVerifyExtension(Context context) {
            j.e(context, "context");
            try {
                if (isSidecarVersionSupported(SidecarCompat.Companion.getSidecarVersion())) {
                    SidecarCompat sidecarCompat = new SidecarCompat(context);
                    if (!sidecarCompat.validateExtensionInterface()) {
                        return null;
                    }
                    return sidecarCompat;
                }
            } catch (Throwable unused) {
            }
            return null;
        }

        public final boolean isSidecarVersionSupported(Version version) {
            if (version != null && version.compareTo(Version.Companion.getVERSION_0_1()) >= 0) {
                return true;
            }
            return false;
        }

        public final void resetInstance() {
            SidecarWindowBackend.globalInstance = null;
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/window/layout/SidecarWindowBackend$ExtensionListenerImpl;", "Landroidx/window/layout/ExtensionInterfaceCompat$ExtensionCallbackInterface;", "<init>", "(Landroidx/window/layout/SidecarWindowBackend;)V", "Landroid/app/Activity;", "activity", "Landroidx/window/layout/WindowLayoutInfo;", "newLayout", "Lme/x;", "onWindowLayoutChanged", "(Landroid/app/Activity;Landroidx/window/layout/WindowLayoutInfo;)V", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class ExtensionListenerImpl implements ExtensionInterfaceCompat.ExtensionCallbackInterface {
        final /* synthetic */ SidecarWindowBackend this$0;

        public ExtensionListenerImpl(SidecarWindowBackend sidecarWindowBackend) {
            j.e(sidecarWindowBackend, "this$0");
            this.this$0 = sidecarWindowBackend;
        }

        public void onWindowLayoutChanged(Activity activity, WindowLayoutInfo windowLayoutInfo) {
            j.e(activity, "activity");
            j.e(windowLayoutInfo, "newLayout");
            Iterator<WindowLayoutChangeCallbackWrapper> it = this.this$0.getWindowLayoutChangeCallbacks().iterator();
            while (it.hasNext()) {
                WindowLayoutChangeCallbackWrapper next = it.next();
                if (j.a(next.getActivity(), activity)) {
                    next.accept(windowLayoutInfo);
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0007¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0012R\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u000e¨\u0006\u001b"}, d2 = {"Landroidx/window/layout/SidecarWindowBackend$WindowLayoutChangeCallbackWrapper;", "", "Landroid/app/Activity;", "activity", "Ljava/util/concurrent/Executor;", "executor", "Landroidx/core/util/Consumer;", "Landroidx/window/layout/WindowLayoutInfo;", "callback", "<init>", "(Landroid/app/Activity;Ljava/util/concurrent/Executor;Landroidx/core/util/Consumer;)V", "newLayoutInfo", "Lme/x;", "accept", "(Landroidx/window/layout/WindowLayoutInfo;)V", "Landroid/app/Activity;", "getActivity", "()Landroid/app/Activity;", "Ljava/util/concurrent/Executor;", "Landroidx/core/util/Consumer;", "getCallback", "()Landroidx/core/util/Consumer;", "lastInfo", "Landroidx/window/layout/WindowLayoutInfo;", "getLastInfo", "()Landroidx/window/layout/WindowLayoutInfo;", "setLastInfo", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WindowLayoutChangeCallbackWrapper {
        private final Activity activity;
        private final Consumer<WindowLayoutInfo> callback;
        private final Executor executor;
        private WindowLayoutInfo lastInfo;

        public WindowLayoutChangeCallbackWrapper(Activity activity2, Executor executor2, Consumer<WindowLayoutInfo> consumer) {
            j.e(activity2, "activity");
            j.e(executor2, "executor");
            j.e(consumer, Profile.PhoneNumberData.TYPE_CALLBACK);
            this.activity = activity2;
            this.executor = executor2;
            this.callback = consumer;
        }

        /* access modifiers changed from: private */
        /* renamed from: accept$lambda-0  reason: not valid java name */
        public static final void m32accept$lambda0(WindowLayoutChangeCallbackWrapper windowLayoutChangeCallbackWrapper, WindowLayoutInfo windowLayoutInfo) {
            j.e(windowLayoutChangeCallbackWrapper, "this$0");
            j.e(windowLayoutInfo, "$newLayoutInfo");
            windowLayoutChangeCallbackWrapper.callback.accept(windowLayoutInfo);
        }

        public final void accept(WindowLayoutInfo windowLayoutInfo) {
            j.e(windowLayoutInfo, "newLayoutInfo");
            this.lastInfo = windowLayoutInfo;
            this.executor.execute(new c(1, this, windowLayoutInfo));
        }

        public final Activity getActivity() {
            return this.activity;
        }

        public final Consumer<WindowLayoutInfo> getCallback() {
            return this.callback;
        }

        public final WindowLayoutInfo getLastInfo() {
            return this.lastInfo;
        }

        public final void setLastInfo(WindowLayoutInfo windowLayoutInfo) {
            this.lastInfo = windowLayoutInfo;
        }
    }

    public SidecarWindowBackend(ExtensionInterfaceCompat extensionInterfaceCompat) {
        this.windowExtension = extensionInterfaceCompat;
        ExtensionInterfaceCompat extensionInterfaceCompat2 = this.windowExtension;
        if (extensionInterfaceCompat2 != null) {
            extensionInterfaceCompat2.setExtensionCallback(new ExtensionListenerImpl(this));
        }
    }

    private final void callbackRemovedForActivity(Activity activity) {
        CopyOnWriteArrayList<WindowLayoutChangeCallbackWrapper> copyOnWriteArrayList = this.windowLayoutChangeCallbacks;
        if (copyOnWriteArrayList == null || !copyOnWriteArrayList.isEmpty()) {
            for (WindowLayoutChangeCallbackWrapper activity2 : copyOnWriteArrayList) {
                if (j.a(activity2.getActivity(), activity)) {
                    return;
                }
            }
        }
        ExtensionInterfaceCompat extensionInterfaceCompat = this.windowExtension;
        if (extensionInterfaceCompat != null) {
            extensionInterfaceCompat.onWindowLayoutChangeListenerRemoved(activity);
        }
    }

    private final boolean isActivityRegistered(Activity activity) {
        CopyOnWriteArrayList<WindowLayoutChangeCallbackWrapper> copyOnWriteArrayList = this.windowLayoutChangeCallbacks;
        if (copyOnWriteArrayList != null && copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        for (WindowLayoutChangeCallbackWrapper activity2 : copyOnWriteArrayList) {
            if (j.a(activity2.getActivity(), activity)) {
                return true;
            }
        }
        return false;
    }

    public final ExtensionInterfaceCompat getWindowExtension() {
        return this.windowExtension;
    }

    public final CopyOnWriteArrayList<WindowLayoutChangeCallbackWrapper> getWindowLayoutChangeCallbacks() {
        return this.windowLayoutChangeCallbacks;
    }

    public void registerLayoutChangeCallback(Activity activity, Executor executor, Consumer<WindowLayoutInfo> consumer) {
        WindowLayoutInfo windowLayoutInfo;
        T t;
        j.e(activity, "activity");
        j.e(executor, "executor");
        j.e(consumer, Profile.PhoneNumberData.TYPE_CALLBACK);
        ReentrantLock reentrantLock = globalLock;
        reentrantLock.lock();
        try {
            ExtensionInterfaceCompat windowExtension2 = getWindowExtension();
            if (windowExtension2 == null) {
                consumer.accept(new WindowLayoutInfo(C1202t.d));
                return;
            }
            boolean isActivityRegistered = isActivityRegistered(activity);
            WindowLayoutChangeCallbackWrapper windowLayoutChangeCallbackWrapper = new WindowLayoutChangeCallbackWrapper(activity, executor, consumer);
            getWindowLayoutChangeCallbacks().add(windowLayoutChangeCallbackWrapper);
            if (!isActivityRegistered) {
                windowExtension2.onWindowLayoutChangeListenerAdded(activity);
            } else {
                Iterator<T> it = getWindowLayoutChangeCallbacks().iterator();
                while (true) {
                    windowLayoutInfo = null;
                    if (!it.hasNext()) {
                        t = null;
                        break;
                    }
                    t = it.next();
                    if (activity.equals(((WindowLayoutChangeCallbackWrapper) t).getActivity())) {
                        break;
                    }
                }
                WindowLayoutChangeCallbackWrapper windowLayoutChangeCallbackWrapper2 = (WindowLayoutChangeCallbackWrapper) t;
                if (windowLayoutChangeCallbackWrapper2 != null) {
                    windowLayoutInfo = windowLayoutChangeCallbackWrapper2.getLastInfo();
                }
                if (windowLayoutInfo != null) {
                    windowLayoutChangeCallbackWrapper.accept(windowLayoutInfo);
                }
            }
            reentrantLock.unlock();
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void setWindowExtension(ExtensionInterfaceCompat extensionInterfaceCompat) {
        this.windowExtension = extensionInterfaceCompat;
    }

    public void unregisterLayoutChangeCallback(Consumer<WindowLayoutInfo> consumer) {
        j.e(consumer, Profile.PhoneNumberData.TYPE_CALLBACK);
        synchronized (globalLock) {
            try {
                if (getWindowExtension() != null) {
                    ArrayList<WindowLayoutChangeCallbackWrapper> arrayList = new ArrayList<>();
                    Iterator<WindowLayoutChangeCallbackWrapper> it = getWindowLayoutChangeCallbacks().iterator();
                    while (it.hasNext()) {
                        WindowLayoutChangeCallbackWrapper next = it.next();
                        if (next.getCallback() == consumer) {
                            arrayList.add(next);
                        }
                    }
                    getWindowLayoutChangeCallbacks().removeAll(arrayList);
                    for (WindowLayoutChangeCallbackWrapper activity : arrayList) {
                        callbackRemovedForActivity(activity.getActivity());
                    }
                }
            } finally {
            }
        }
    }

    public static /* synthetic */ void getWindowLayoutChangeCallbacks$annotations() {
    }
}
