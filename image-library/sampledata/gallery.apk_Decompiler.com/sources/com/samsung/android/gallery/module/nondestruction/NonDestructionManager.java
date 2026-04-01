package com.samsung.android.gallery.module.nondestruction;

import K9.a;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MathUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.scsp.media.file.FileApiContract;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NonDestructionManager extends ContentObserver {
    public static boolean SUPPORT = PreferenceFeatures.OneUi40.SUPPORT_NONDESTRUCTIVE_SLOW_MO;
    private final Object LOCK = new Object();
    private final Object LOCK_INIT = new Object();
    private final String TAG = "NonDestManager";
    private final Uri URI = Uri.parse("content://secmedia/nondestruction");
    private HashMap<String, String> mDataMap = new HashMap<>();
    private volatile boolean mInitRequired = true;
    private final Runnable mLoadAndNotify = new a(this, 0);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        static final NonDestructionManager instance = new NonDestructionManager();
    }

    public NonDestructionManager() {
        super((Handler) null);
        if (SUPPORT) {
            registerObserver();
            SimpleThreadPool.getInstance().execute(new a(this, 1));
        }
    }

    private HashMap<String, String> get() {
        HashMap<String, String> hashMap;
        synchronized (this.LOCK) {
            hashMap = this.mDataMap;
        }
        return hashMap;
    }

    public static NonDestructionManager getInstance() {
        return LazyHolder.instance;
    }

    private HashMap<String, String> initIfAbsent() {
        if (this.mInitRequired) {
            synchronized (this.LOCK_INIT) {
                try {
                    if (this.mInitRequired) {
                        this.mInitRequired = false;
                        HashMap<String, String> loadAndGet = loadAndGet();
                        return loadAndGet;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return get();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        notifyIfChanged(get(), initIfAbsent());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1() {
        notifyIfChanged(get(), loadAndGet());
    }

    private HashMap<String, String> load() {
        Cursor query;
        Throwable th;
        long currentTimeMillis = System.currentTimeMillis();
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            query = AppResources.getAppContext().getContentResolver().query(this.URI, (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    int columnIndex = query.getColumnIndex("hash");
                    int columnIndex2 = query.getColumnIndex(FileApiContract.Parameter.PATH);
                    do {
                        String string = query.getString(columnIndex);
                        String string2 = query.getString(columnIndex2);
                        if (!(string == null || string2 == null)) {
                            hashMap.put(string, string2);
                        }
                    } while (query.moveToNext());
                }
            }
            Log.d("NonDestManager", "load" + Logger.vt(Integer.valueOf(hashMap.size()), Long.valueOf(currentTimeMillis)));
            if (query == null) {
                return hashMap;
            }
            query.close();
            return hashMap;
        } catch (Exception e) {
            Exception exc = e;
            Log.e("NonDestManager", "load {" + hashMap.size() + "} failed. e=" + exc.getMessage());
            return hashMap;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    private HashMap<String, String> loadAndGet() {
        HashMap<String, String> load = load();
        synchronized (this.LOCK) {
            this.mDataMap = load;
        }
        return load;
    }

    private void notifyIfChanged(HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        if (MathUtils.compare(hashMap, hashMap2) != 0) {
            Blackboard.getApplicationInstance().post("global://event/non_destruction/dataChanged", (Object) null);
        } else {
            Log.d("NonDestManager", "notifyIfChanged skip", Integer.valueOf(hashMap.size()), Integer.valueOf(hashMap2.size()));
        }
    }

    private void registerObserver() {
        try {
            System.currentTimeMillis();
            AppResources.getAppContext().getContentResolver().registerContentObserver(this.URI, false, this);
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("registerObserver failed. e="), "NonDestManager");
        }
    }

    public String computePath(String str) {
        if (!SUPPORT || str == null) {
            return null;
        }
        return initIfAbsent().get(str);
    }

    public String getPath(String str) {
        String str2;
        if (!SUPPORT || str == null) {
            return null;
        }
        synchronized (this.LOCK) {
            str2 = this.mDataMap.get(str);
        }
        return str2;
    }

    public boolean hasData(String str) {
        boolean containsKey;
        if (!SUPPORT || str == null) {
            return false;
        }
        synchronized (this.LOCK) {
            containsKey = this.mDataMap.containsKey(str);
        }
        return containsKey;
    }

    public void onChange(boolean z) {
        onChange(z, (Uri) null);
    }

    public void onChange(boolean z, Uri uri) {
        Log.d("NonDestManager", "onChange", Boolean.valueOf(z), uri);
        ThreadUtil.removeCallbackOnBgThread(this.mLoadAndNotify);
        ThreadUtil.postOnBgThreadDelayed(this.mLoadAndNotify, 30);
    }
}
