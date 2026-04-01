package com.samsung.android.gallery.support.library.v11.remaster;

import android.content.Context;
import android.net.Uri;
import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;
import com.samsung.android.gallery.support.library.utils.Reflector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemVslMesDetectorCompat121 extends VslMesDetectorCompat {
    private Class<?> mClassName;
    private Object mVslMesDetector;

    public SemVslMesDetectorCompat121(String str) {
        try {
            Class<?> clazz = VslMesDetectorCompat.VslMesDetectorClassLoader.getInstance(str, "com.samsung.android.app.aimediaenhancer.VslMesDetector").getClazz();
            this.mClassName = clazz;
            if (clazz != null) {
                this.mVslMesDetector = clazz.newInstance();
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setContext(Context context) {
        Reflector.invoke(this.mClassName, this.mVslMesDetector, "setContext", context);
    }

    public void deInit(boolean z) {
        Reflector.invoke(this.mClassName, this.mVslMesDetector, "deinit");
    }

    public String getApiVersion() {
        return (String) Reflector.invoke(this.mClassName, this.mVslMesDetector, "getStringParam", 1000);
    }

    public long getEnumEnhanceType() {
        return (long) ((Integer) Reflector.invoke(this.mClassName, this.mVslMesDetector, "getEnumEnhanceType")).intValue();
    }

    public String getFullPathRevitalized() {
        return (String) Reflector.invoke(this.mClassName, this.mVslMesDetector, "getFullPathRevitalized");
    }

    public void init(Context context) {
        setContext(context);
        Reflector.invoke(this.mClassName, this.mVslMesDetector, "init");
    }

    public boolean processImage(Uri uri, String str, long j2, int i2, long j3) {
        Reflector.invoke(this.mClassName, this.mVslMesDetector, "setInputImageUri", uri);
        Reflector.invoke(this.mClassName, this.mVslMesDetector, "setInputImagePath", str);
        Reflector.invoke(this.mClassName, this.mVslMesDetector, "setLastModifiedDatetime", Long.valueOf(j2));
        Object invoke = Reflector.invoke(this.mClassName, this.mVslMesDetector, "processImage", Integer.valueOf(i2), Integer.valueOf(Math.toIntExact(j3)));
        if (invoke == null || !((Boolean) invoke).booleanValue()) {
            return false;
        }
        return true;
    }

    public void stop() {
        Reflector.invoke(this.mClassName, this.mVslMesDetector, "stop");
    }

    public String tag() {
        return "SemVslMesDetectorCompat121";
    }
}
