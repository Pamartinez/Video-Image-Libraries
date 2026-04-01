package com.samsung.android.gallery.support.library.v12.remster;

import android.content.Context;
import android.net.Uri;
import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;
import com.samsung.android.gallery.support.library.utils.Reflector;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemRemasterManagerReflection extends VslMesDetectorCompat {
    private Class<?> mClazz;
    private Object mPhotoRemasterManager;

    public SemRemasterManagerReflection(String str) {
        try {
            Class<?> clazz = VslMesDetectorCompat.VslMesDetectorClassLoader.getInstance(str, "com.samsung.android.media.photoremaster.SemPhotoRemasterManager").getClazz();
            this.mClazz = clazz;
            if (clazz != null) {
                this.mPhotoRemasterManager = clazz.newInstance();
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void setLastModifiedDateTime(long j2) {
        Class<?> cls = this.mClazz;
        if (cls != null) {
            Reflector.invoke(Reflector.getMethod(cls, "setParameter", Integer.TYPE, Long.TYPE), this.mPhotoRemasterManager, 1004, Long.valueOf(j2));
        }
    }

    private void setPathInput(String str) {
        Class<?> cls = this.mClazz;
        if (cls != null) {
            Reflector.invoke(Reflector.getMethod(cls, "setParameter", Integer.TYPE, String.class), this.mPhotoRemasterManager, 1002, str);
        }
    }

    private void setUriInput(Uri uri) {
        Class<?> cls = this.mClazz;
        if (cls != null) {
            Reflector.invoke(Reflector.getMethod(cls, "setParameter", Integer.TYPE, Object.class), this.mPhotoRemasterManager, 1001, uri);
        }
    }

    public void deInit(boolean z) {
        Reflector.invoke(this.mClazz, this.mPhotoRemasterManager, "deinit");
    }

    public String getApiVersion() {
        return getParameter(1000);
    }

    public long getEnumEnhanceType() {
        return getLongParameter(2201, Integer.MIN_VALUE);
    }

    public String getFullPathRevitalized() {
        return getParameter(1003);
    }

    public String getParameter(int i2) {
        return (String) Reflector.invoke(this.mClazz, this.mPhotoRemasterManager, "getParameter", Integer.valueOf(i2));
    }

    public void init(Context context) {
        Reflector.invoke(this.mClazz, this.mPhotoRemasterManager, "init", context);
    }

    public boolean processImage(Uri uri, String str, long j2, int i2, long j3) {
        setUriInput(uri);
        setPathInput(str);
        setLastModifiedDateTime(j2);
        return ((Boolean) Reflector.invoke(Reflector.getMethod(this.mClazz, "processImage", Integer.TYPE, List.class), this.mPhotoRemasterManager, Integer.valueOf(i2), VslMesDetectorCompat.decodeEnhances(j3))).booleanValue();
    }

    public void stop() {
        Reflector.invoke(this.mClazz, this.mPhotoRemasterManager, "stop");
    }

    public boolean support() {
        if (this.mPhotoRemasterManager != null) {
            return true;
        }
        return false;
    }

    public String tag() {
        return "SemRemasterManagerReflection";
    }
}
