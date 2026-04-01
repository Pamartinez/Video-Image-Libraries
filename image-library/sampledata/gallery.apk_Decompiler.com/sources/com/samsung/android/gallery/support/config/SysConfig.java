package com.samsung.android.gallery.support.config;

import N2.j;
import android.util.ArrayMap;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SysConfig {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ConfigParams {
        static final ConfigParams sInstance = new ConfigParams();
        final Object LOCK = new Object();
        final ArrayMap<String, String> map = new ArrayMap<>();

        public ConfigParams() {
            load();
        }

        public static ConfigParams getInstance() {
            return sInstance;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ String lambda$save$0(Map.Entry entry) {
            return ((String) entry.getKey()) + "=" + ((String) entry.getValue());
        }

        private void save() {
            String str;
            FileOutputStream fileOutputStream;
            synchronized (this.LOCK) {
                str = (String) this.map.entrySet().stream().map(new a(0)).collect(Collectors.joining("\n"));
            }
            long currentTimeMillis = System.currentTimeMillis();
            File file = new File("/data/sec/gallery/config/system.cfg");
            File parentFile = file.getParentFile();
            if (parentFile != null && (parentFile.exists() || parentFile.mkdirs())) {
                try {
                    fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(str.getBytes());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e) {
                    j.D(e, new StringBuilder("save failed. e="), "SysConfig");
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            Log.v("SysConfig", "save +" + (System.currentTimeMillis() - currentTimeMillis) + " " + this.map);
            return;
            throw th;
        }

        public String get(String str, String str2) {
            String str3;
            synchronized (this.LOCK) {
                str3 = this.map.get(str);
            }
            if (str3 == null) {
                return str2;
            }
            return str3;
        }

        public void load() {
            FileInputStream fileInputStream;
            long currentTimeMillis = System.currentTimeMillis();
            File file = new File("/data/sec/gallery/config/system.cfg");
            if (file.exists()) {
                ArrayMap arrayMap = new ArrayMap();
                try {
                    fileInputStream = new FileInputStream(file);
                    byte[] bArr = new byte[fileInputStream.available()];
                    if (fileInputStream.read(bArr) > 0) {
                        Stream.of(new String(bArr).split("\n")).map(new a(1)).forEach(new b(arrayMap));
                    }
                    fileInputStream.close();
                } catch (Exception unused) {
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
                synchronized (this.LOCK) {
                    this.map.clear();
                    this.map.putAll(arrayMap);
                }
            }
            Log.v("SysConfig", "load +" + (System.currentTimeMillis() - currentTimeMillis) + " " + this.map);
            return;
            throw th;
        }

        public <T> void set(String str, T t) {
            String put;
            String valueOf = String.valueOf(t);
            synchronized (this.LOCK) {
                put = this.map.put(str, valueOf);
            }
            if (!valueOf.equals(put)) {
                save();
            }
        }
    }

    public static String computeIfAbsent(String str, Supplier<String> supplier) {
        String str2 = ConfigParams.getInstance().get(str, (String) null);
        if (str2 != null) {
            return str2;
        }
        String str3 = supplier.get();
        ConfigParams.getInstance().set(str, str3);
        return str3;
    }
}
