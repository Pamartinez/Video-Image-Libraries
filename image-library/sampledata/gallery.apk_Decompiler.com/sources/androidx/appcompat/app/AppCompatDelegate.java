package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.LocaleManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.widget.Toolbar;
import androidx.collection.ArraySet;
import androidx.core.app.AppLocalesStorageHelper;
import androidx.core.os.LocaleListCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AppCompatDelegate {
    private static final ArraySet<WeakReference<AppCompatDelegate>> sActivityDelegates = new ArraySet<>();
    private static final Object sActivityDelegatesLock = new Object();
    private static final Object sAppLocalesStorageSyncLock = new Object();
    private static int sDefaultNightMode = -100;
    private static Boolean sIsAutoStoreLocalesOptedIn = null;
    private static boolean sIsFrameworkSyncChecked = false;
    private static LocaleListCompat sRequestedAppLocales = null;
    static SerialExecutor sSerialExecutorForLocalesStorage = new SerialExecutor(new ThreadPerTaskExecutor());
    private static LocaleListCompat sStoredAppLocales = null;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api24Impl {
        public static LocaleList localeListForLanguageTags(String str) {
            return LocaleList.forLanguageTags(str);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api33Impl {
        public static LocaleList localeManagerGetApplicationLocales(Object obj) {
            return ((LocaleManager) obj).getApplicationLocales();
        }

        public static void localeManagerSetApplicationLocales(Object obj, LocaleList localeList) {
            ((LocaleManager) obj).setApplicationLocales(localeList);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SerialExecutor implements Executor {
        Runnable mActive;
        final Executor mExecutor;
        private final Object mLock = new Object();
        final Queue<Runnable> mTasks = new ArrayDeque();

        public SerialExecutor(Executor executor) {
            this.mExecutor = executor;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$execute$0(Runnable runnable) {
            try {
                runnable.run();
            } finally {
                scheduleNext();
            }
        }

        public void execute(Runnable runnable) {
            synchronized (this.mLock) {
                try {
                    this.mTasks.add(new c(this, runnable));
                    if (this.mActive == null) {
                        scheduleNext();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public void scheduleNext() {
            synchronized (this.mLock) {
                try {
                    Runnable poll = this.mTasks.poll();
                    this.mActive = poll;
                    if (poll != null) {
                        this.mExecutor.execute(poll);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ThreadPerTaskExecutor implements Executor {
        public void execute(Runnable runnable) {
            new Thread(runnable).start();
        }
    }

    public static void addActiveDelegate(AppCompatDelegate appCompatDelegate) {
        synchronized (sActivityDelegatesLock) {
            removeDelegateFromActives(appCompatDelegate);
            sActivityDelegates.add(new WeakReference(appCompatDelegate));
        }
    }

    public static AppCompatDelegate create(Activity activity, AppCompatCallback appCompatCallback) {
        return new AppCompatDelegateImpl(activity, appCompatCallback);
    }

    public static LocaleListCompat getApplicationLocales() {
        if (Build.VERSION.SDK_INT >= 33) {
            Object localeManagerForApplication = getLocaleManagerForApplication();
            if (localeManagerForApplication != null) {
                return LocaleListCompat.wrap(Api33Impl.localeManagerGetApplicationLocales(localeManagerForApplication));
            }
        } else {
            LocaleListCompat localeListCompat = sRequestedAppLocales;
            if (localeListCompat != null) {
                return localeListCompat;
            }
        }
        return LocaleListCompat.getEmptyLocaleList();
    }

    public static int getDefaultNightMode() {
        return sDefaultNightMode;
    }

    public static Object getLocaleManagerForApplication() {
        Context contextForDelegate;
        Iterator<WeakReference<AppCompatDelegate>> it = sActivityDelegates.iterator();
        while (it.hasNext()) {
            AppCompatDelegate appCompatDelegate = (AppCompatDelegate) it.next().get();
            if (appCompatDelegate != null && (contextForDelegate = appCompatDelegate.getContextForDelegate()) != null) {
                return contextForDelegate.getSystemService("locale");
            }
        }
        return null;
    }

    public static LocaleListCompat getRequestedAppLocales() {
        return sRequestedAppLocales;
    }

    public static boolean isAutoStorageOptedIn(Context context) {
        if (sIsAutoStoreLocalesOptedIn == null) {
            try {
                Bundle bundle = AppLocalesMetadataHolderService.getServiceInfo(context).metaData;
                if (bundle != null) {
                    sIsAutoStoreLocalesOptedIn = Boolean.valueOf(bundle.getBoolean("autoStoreLocales"));
                }
            } catch (PackageManager.NameNotFoundException unused) {
                Log.d("AppCompatDelegate", "Checking for metadata for AppLocalesMetadataHolderService : Service not found");
                sIsAutoStoreLocalesOptedIn = Boolean.FALSE;
            }
        }
        return sIsAutoStoreLocalesOptedIn.booleanValue();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$syncRequestedAndStoredLocales$1(Context context) {
        syncLocalesToFramework(context);
        sIsFrameworkSyncChecked = true;
    }

    public static void removeActivityDelegate(AppCompatDelegate appCompatDelegate) {
        synchronized (sActivityDelegatesLock) {
            removeDelegateFromActives(appCompatDelegate);
        }
    }

    private static void removeDelegateFromActives(AppCompatDelegate appCompatDelegate) {
        synchronized (sActivityDelegatesLock) {
            try {
                Iterator<WeakReference<AppCompatDelegate>> it = sActivityDelegates.iterator();
                while (it.hasNext()) {
                    AppCompatDelegate appCompatDelegate2 = (AppCompatDelegate) it.next().get();
                    if (appCompatDelegate2 == appCompatDelegate || appCompatDelegate2 == null) {
                        it.remove();
                    }
                }
            } finally {
            }
        }
    }

    public static void syncLocalesToFramework(Context context) {
        if (Build.VERSION.SDK_INT >= 33) {
            ComponentName componentName = new ComponentName(context, "androidx.appcompat.app.AppLocalesMetadataHolderService");
            if (context.getPackageManager().getComponentEnabledSetting(componentName) != 1) {
                if (getApplicationLocales().isEmpty()) {
                    String readLocales = AppLocalesStorageHelper.readLocales(context);
                    Object systemService = context.getSystemService("locale");
                    if (systemService != null) {
                        Api33Impl.localeManagerSetApplicationLocales(systemService, Api24Impl.localeListForLanguageTags(readLocales));
                    }
                }
                context.getPackageManager().setComponentEnabledSetting(componentName, 1, 1);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0058, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void syncRequestedAndStoredLocales(android.content.Context r3) {
        /*
            boolean r0 = isAutoStorageOptedIn(r3)
            if (r0 != 0) goto L_0x0007
            goto L_0x001c
        L_0x0007:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 33
            if (r0 < r1) goto L_0x001d
            boolean r0 = sIsFrameworkSyncChecked
            if (r0 != 0) goto L_0x001c
            androidx.appcompat.app.AppCompatDelegate$SerialExecutor r0 = sSerialExecutorForLocalesStorage
            C3.l r1 = new C3.l
            r2 = 2
            r1.<init>(r3, r2)
            r0.execute(r1)
        L_0x001c:
            return
        L_0x001d:
            java.lang.Object r0 = sAppLocalesStorageSyncLock
            monitor-enter(r0)
            androidx.core.os.LocaleListCompat r1 = sRequestedAppLocales     // Catch:{ all -> 0x0033 }
            if (r1 != 0) goto L_0x0044
            androidx.core.os.LocaleListCompat r1 = sStoredAppLocales     // Catch:{ all -> 0x0033 }
            if (r1 != 0) goto L_0x0035
            java.lang.String r3 = androidx.core.app.AppLocalesStorageHelper.readLocales(r3)     // Catch:{ all -> 0x0033 }
            androidx.core.os.LocaleListCompat r3 = androidx.core.os.LocaleListCompat.forLanguageTags(r3)     // Catch:{ all -> 0x0033 }
            sStoredAppLocales = r3     // Catch:{ all -> 0x0033 }
            goto L_0x0035
        L_0x0033:
            r3 = move-exception
            goto L_0x0059
        L_0x0035:
            androidx.core.os.LocaleListCompat r3 = sStoredAppLocales     // Catch:{ all -> 0x0033 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0033 }
            if (r3 == 0) goto L_0x003f
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            return
        L_0x003f:
            androidx.core.os.LocaleListCompat r3 = sStoredAppLocales     // Catch:{ all -> 0x0033 }
            sRequestedAppLocales = r3     // Catch:{ all -> 0x0033 }
            goto L_0x0057
        L_0x0044:
            androidx.core.os.LocaleListCompat r2 = sStoredAppLocales     // Catch:{ all -> 0x0033 }
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0033 }
            if (r1 != 0) goto L_0x0057
            androidx.core.os.LocaleListCompat r1 = sRequestedAppLocales     // Catch:{ all -> 0x0033 }
            sStoredAppLocales = r1     // Catch:{ all -> 0x0033 }
            java.lang.String r1 = r1.toLanguageTags()     // Catch:{ all -> 0x0033 }
            androidx.core.app.AppLocalesStorageHelper.persistLocales(r3, r1)     // Catch:{ all -> 0x0033 }
        L_0x0057:
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            return
        L_0x0059:
            monitor-exit(r0)     // Catch:{ all -> 0x0033 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.AppCompatDelegate.syncRequestedAndStoredLocales(android.content.Context):void");
    }

    public abstract void addContentView(View view, ViewGroup.LayoutParams layoutParams);

    public Context attachBaseContext2(Context context) {
        attachBaseContext(context);
        return context;
    }

    public abstract <T extends View> T findViewById(int i2);

    public abstract Context getContextForDelegate();

    public abstract int getLocalNightMode();

    public abstract MenuInflater getMenuInflater();

    public abstract ActionBar getSupportActionBar();

    public abstract void installViewFactory();

    public abstract void invalidateOptionsMenu();

    public abstract void onConfigurationChanged(Configuration configuration);

    public abstract void onCreate(Bundle bundle);

    public abstract void onDestroy();

    public abstract void onPostCreate(Bundle bundle);

    public abstract void onPostResume();

    public abstract void onSaveInstanceState(Bundle bundle);

    public abstract void onStart();

    public abstract void onStop();

    public abstract boolean requestWindowFeature(int i2);

    public abstract void setContentView(int i2);

    public abstract void setContentView(View view);

    public abstract void setContentView(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void setLocalNightMode(int i2);

    public abstract void setSupportActionBar(Toolbar toolbar);

    public abstract void setTheme(int i2);

    public abstract void setTitle(CharSequence charSequence);

    public static AppCompatDelegate create(Dialog dialog, AppCompatCallback appCompatCallback) {
        return new AppCompatDelegateImpl(dialog, appCompatCallback);
    }

    @Deprecated
    public void attachBaseContext(Context context) {
    }

    public void setOnBackInvokedDispatcher(OnBackInvokedDispatcher onBackInvokedDispatcher) {
    }
}
