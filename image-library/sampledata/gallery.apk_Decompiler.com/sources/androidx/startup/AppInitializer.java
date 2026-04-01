package androidx.startup;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.tracing.Trace;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class AppInitializer {
    private static volatile AppInitializer sInstance;
    private static final Object sLock = new Object();
    final Context mContext;
    final Set<Class<? extends Initializer<?>>> mDiscovered = new HashSet();
    final Map<Class<?>, Object> mInitialized = new HashMap();

    public AppInitializer(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static AppInitializer getInstance(Context context) {
        if (sInstance == null) {
            synchronized (sLock) {
                try {
                    if (sInstance == null) {
                        sInstance = new AppInitializer(context);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public void discoverAndInitialize() {
        try {
            Trace.beginSection("Startup");
            discoverAndInitialize(this.mContext.getPackageManager().getProviderInfo(new ComponentName(this.mContext.getPackageName(), InitializationProvider.class.getName()), 128).metaData);
            Trace.endSection();
        } catch (PackageManager.NameNotFoundException e) {
            throw new StartupException((Throwable) e);
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    public <T> T doInitialize(Class<? extends Initializer<?>> cls) {
        T t;
        synchronized (sLock) {
            try {
                t = this.mInitialized.get(cls);
                if (t == null) {
                    t = doInitialize(cls, new HashSet());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return t;
    }

    public <T> T initializeComponent(Class<? extends Initializer<T>> cls) {
        return doInitialize(cls);
    }

    public boolean isEagerlyInitialized(Class<? extends Initializer<?>> cls) {
        return this.mDiscovered.contains(cls);
    }

    private <T> T doInitialize(Class<? extends Initializer<?>> cls, Set<Class<?>> set) {
        T t;
        if (Trace.isEnabled()) {
            try {
                Trace.beginSection(cls.getSimpleName());
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        if (!set.contains(cls)) {
            if (!this.mInitialized.containsKey(cls)) {
                set.add(cls);
                Initializer initializer = (Initializer) cls.getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
                List<Class<? extends Initializer<?>>> dependencies = initializer.dependencies();
                if (!dependencies.isEmpty()) {
                    for (Class next : dependencies) {
                        if (!this.mInitialized.containsKey(next)) {
                            doInitialize(next, set);
                        }
                    }
                }
                t = initializer.create(this.mContext);
                set.remove(cls);
                this.mInitialized.put(cls, t);
            } else {
                t = this.mInitialized.get(cls);
            }
            Trace.endSection();
            return t;
        }
        String name = cls.getName();
        throw new IllegalStateException("Cannot initialize " + name + ". Cycle detected.");
    }

    public void discoverAndInitialize(Bundle bundle) {
        String string = this.mContext.getString(R$string.androidx_startup);
        if (bundle != null) {
            try {
                HashSet hashSet = new HashSet();
                for (String next : bundle.keySet()) {
                    if (string.equals(bundle.getString(next, (String) null))) {
                        Class<?> cls = Class.forName(next);
                        if (Initializer.class.isAssignableFrom(cls)) {
                            this.mDiscovered.add(cls);
                        }
                    }
                }
                for (Class<? extends Initializer<?>> doInitialize : this.mDiscovered) {
                    doInitialize(doInitialize, hashSet);
                }
            } catch (ClassNotFoundException e) {
                throw new StartupException((Throwable) e);
            }
        }
    }
}
