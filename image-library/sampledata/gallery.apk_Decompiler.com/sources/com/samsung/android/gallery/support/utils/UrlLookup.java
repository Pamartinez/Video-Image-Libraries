package com.samsung.android.gallery.support.utils;

import A.a;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class UrlLookup {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BackupFileHolder {
        static final String[] KEYS = {"stub", "app-filing", "amap.tnc", "privacy-policy"};
        protected final String TAG;
        final HashMap<String, String> map;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class LazyBackupFileHolder {
            static final BackupFileHolder sInstance = new BackupFileHolder(0);
        }

        public /* synthetic */ BackupFileHolder(int i2) {
            this();
        }

        public static BackupFileHolder getInstance() {
            return LazyBackupFileHolder.sInstance;
        }

        private boolean isFileAvailable(String str) {
            File file = new File(str);
            if (!file.exists() || file.length() <= 0) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ HashMap lambda$load$0() {
            return loadFile("/data/sec/gallery/url.asset.backup");
        }

        public String get(String str) {
            return this.map.get(str);
        }

        public HashMap<String, String> load() {
            long currentTimeMillis = System.currentTimeMillis();
            HashMap<String, String> hashMap = (HashMap) Optional.ofNullable(loadFile("/data/sec/gallery/url.backup")).orElseGet(new W(this));
            if (hashMap == null) {
                return new HashMap<>();
            }
            savePreference(hashMap);
            String str = this.TAG;
            Log.d(str, "load" + Logger.vt(Integer.valueOf(hashMap.size()), Long.valueOf(currentTimeMillis)) + "");
            return hashMap;
        }

        public final HashMap<String, String> loadFile(String str) {
            try {
                if (isFileAvailable(str)) {
                    return (HashMap) GsonTool.fromJsonFile(HashMap.class, str);
                }
                return null;
            } catch (Exception e) {
                a.s(e, new StringBuilder("loadFile failed. e="), this.TAG);
                return null;
            }
        }

        public final void saveAsJsonFile(HashMap<String, String> hashMap, String str) {
            if (!FileUtils.makeParentIfAbsent(str) || !GsonTool.toJsonFile(hashMap, str)) {
                Log.e(this.TAG, "saveAsJsonFile failed");
                return;
            }
            String str2 = this.TAG;
            Log.d(str2, "saveAsJsonFile " + FileUtils.info(str));
        }

        public final void savePreference(HashMap<String, String> hashMap) {
            if (hashMap.size() > 0) {
                for (String str : KEYS) {
                    String str2 = hashMap.get(str);
                    if (str2 != null) {
                        GalleryPreference.getInstance().saveState(str, str2);
                    }
                }
            }
        }

        private BackupFileHolder() {
            this.TAG = "UrlLookup#".concat(getClass().getSimpleName());
            this.map = load();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DataHolder {
        private static final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        private static final HashMap<String, byte[]> values = new HashMap<String, byte[]>() {
            {
                put("tianyi", new byte[]{114, 77, -110, -15, 114, 76, -51, -18, 63, 71, -100, -77, 126, 87, -101, 30});
                put("baidu", new byte[]{114, 77, -110, -15, 115, 67, -106, -69, 100, 12, -111, -70, 101, 70, -106, -84, 122, 12, -116, -66, 124, 81, -118, -79, 118, 60});
                put("baidu.netdisk", new byte[]{17, -71, 93, 90, 7, -71, 90, 76, 24, -25, 28, 16, 29, -14, 82, 92, 7, -76, 92, 81, 93, -68, 65, 80, 6, -87, 86, 77, 76, -82, 80, 2, 0, -68, 94, 76, 6, -77, 84, 25, 1, -78, 70, 75, 28, -32, 100, 103, 7, -78, 65, 111, 43, -115, 87, 91, 3, -120, 89, 75, 62, -103, 22, 13, 53, -80, 88, 111, 10, -123, 5, 124, 55, -24, 122, 112, 58, -81, 81, 106, 24, -18, 89, 94, 58, -123, 69, 79, 33, -104, 64, 26, 65, -101, 96, 80, 52, -74, 87, 86, 48, -113, 3, 87, 75, -74, 121, 117, 43, -8, 1, 125, 10, -124, 67, 110, 52, -116, 81, 81, 68, -66, 90, 71, 3, -103, 5, 26, 65, -97, 126, 112, 24, 22});
                put("tencent", new byte[]{16, -78, 94, 17, 2, -84, 29, 78, 16, -79, 92, 74, 23, 112});
                put("tencent.cloud", new byte[]{4, -72, 90, 70, 6, -77, 81, 86, 11, -65, 74, 5, 92, -75, 92, 82, 22, -126, 71, 94, 17, -14, 85, 86, 31, -72, 12});
                put("meitu", new byte[]{16, -78, 94, 17, 30, -87, 29, 82, 7, -91, 75, 17, 30, -87, 75, 71, 102});
                put("meitu.action", new byte[]{-68, 91, 79, -1, -78, 81, 75, -91, -86, 26, 75, -65, -85, 81, 76, -91, -15, 85, 65, -91, -74, 91, 76, 51});
            }
        };

        public static String get(String str) {
            return map.computeIfAbsent(str, new C0670h(7));
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ String lambda$get$0(String str) {
            return (String) Optional.ofNullable(values.get(str)).map(new X(0)).orElse("");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ProviderHolder extends BackupFileHolder {
        static final ProviderHolder sInstance = new ProviderHolder();

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static final class KeyHolder {
            static final String[] keys = {"stub", "app-filing", "amap.tnc", "privacy-policy", "sa-api", "tianyi", "baidu", "baidu.netdisk", "tencent", "tencent.cloud", "meitu", "meitu.action", "baidu.v2", "autonavi.tc", "autonavi.pp"};
        }

        public ProviderHolder() {
            super(0);
        }

        public static ProviderHolder getInstance() {
            return sInstance;
        }

        public HashMap<String, String> load() {
            long currentTimeMillis = System.currentTimeMillis();
            HashMap<String, String> hashMap = new HashMap<>();
            try {
                Bundle call = AppResources.getAppContext().getContentResolver().call(Uri.parse("content://com.samsung.android.gallery.plugins.MapProvider"), "data://load/china-url", (String) null, (Bundle) null);
                if (call != null) {
                    for (String str : KeyHolder.keys) {
                        String string = call.getString(str, (String) null);
                        if (string != null) {
                            hashMap.put(str, string);
                        }
                    }
                }
                if (hashMap.size() > 0) {
                    savePreference(hashMap);
                    saveAsJsonFile(hashMap, "/data/sec/gallery/url.backup");
                }
                Log.d(this.TAG, "load" + Logger.vt(Integer.valueOf(hashMap.size()), Long.valueOf(currentTimeMillis)) + "");
                return hashMap;
            } catch (Exception e) {
                a.s(e, new StringBuilder("load failed. e="), this.TAG);
                return hashMap;
            }
        }
    }

    public static String getData(String str) {
        return DataHolder.get(str);
    }

    public static String getUrl(String str) {
        String loadString = GalleryPreference.getInstance().loadString(str, (String) null);
        if (!TextUtils.isEmpty(loadString)) {
            return loadString;
        }
        String str2 = BackupFileHolder.getInstance().get(str);
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        return ProviderHolder.getInstance().get(str);
    }
}
