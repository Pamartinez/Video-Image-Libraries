package com.samsung.android.gallery.support.utils;

import android.content.Context;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SystemEnvironment {
    private static final ConcurrentHashMap<String, Observer> sListeners = new ConcurrentHashMap<>();
    private static Locale sLocale = Locale.getDefault();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface EnvironmentChangeListener {
        void onEnvironmentChange(Context context);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Observer {
        final int flag;
        final EnvironmentChangeListener listener;

        public Observer(EnvironmentChangeListener environmentChangeListener, int i2) {
            this.listener = environmentChangeListener;
            this.flag = i2;
        }
    }

    public static void addObserver(String str, EnvironmentChangeListener environmentChangeListener, int i2) {
        sListeners.put(str, new Observer(environmentChangeListener, i2));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$notifyObservers$0(int i2, Observer observer) {
        if ((i2 & observer.flag) != 0) {
            return true;
        }
        return false;
    }

    public static void notifyObservers(Context context, int i2) {
        try {
            sListeners.values().stream().filter(new S(i2)).forEach(new P(1, context));
        } catch (Exception e) {
            Log.e("SystemEnvironment", "notifyObservers failed {" + i2 + ',' + sListeners.size() + "} e=" + e.getMessage());
        }
    }

    public static void onFileSystemChange(Context context) {
        notifyObservers(context, 1);
    }

    public static void onLocaleChange(Context context) {
        if (setLocale(Locale.getDefault())) {
            notifyObservers(context, 2);
        }
    }

    private static synchronized boolean setLocale(Locale locale) {
        synchronized (SystemEnvironment.class) {
            if (sLocale == locale) {
                return false;
            }
            sLocale = locale;
            return true;
        }
    }

    public static boolean updateIfChanged(Context context) {
        if (FileUtils.initialize(context)) {
            onFileSystemChange(context);
            return true;
        }
        onLocaleChange(context);
        return false;
    }
}
