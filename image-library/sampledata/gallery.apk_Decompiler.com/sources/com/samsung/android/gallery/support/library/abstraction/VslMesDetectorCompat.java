package com.samsung.android.gallery.support.library.abstraction;

import N2.j;
import O3.o;
import android.content.Context;
import android.net.Uri;
import c0.C0086a;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.utils.Reflector;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import dalvik.system.DexClassLoader;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VslMesDetectorCompat {
    protected final String TAG = tag();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class VslMesDetectorClassLoader {
        private static volatile VslMesDetectorClassLoader sInstance;
        private final Class<?> mClazz;

        private VslMesDetectorClassLoader(String str, String str2) {
            this.mClazz = Reflector.getClass(str2, new DexClassLoader("/system/framework/saiv.jar", str, (String) null, getClass().getClassLoader()));
        }

        public static VslMesDetectorClassLoader getInstance(String str, String str2) {
            if (sInstance == null) {
                synchronized (VslMesDetectorClassLoader.class) {
                    try {
                        if (sInstance == null) {
                            sInstance = new VslMesDetectorClassLoader(str, str2);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            return sInstance;
        }

        public Class<?> getClazz() {
            return this.mClazz;
        }
    }

    public static List<Integer> decodeEnhances(long j2) {
        return decodeEnhances(j2, false);
    }

    private static boolean isPreprocessor(long j2) {
        if (((byte) ((int) (j2 & 255))) == 15) {
            return true;
        }
        return false;
    }

    public boolean detectImage(Uri uri, String str, long j2, String str2) {
        return false;
    }

    public String getApiVersion() {
        return null;
    }

    public long getEnumEnhanceType() {
        return -1;
    }

    public String getFocusRoi(String str, String str2) {
        return "";
    }

    public String getFullPathRevitalized() {
        return "";
    }

    public final long getLongParameter(int i2, int i7) {
        try {
            String parameter = getParameter(i2);
            if (parameter != null) {
                return Long.parseLong(parameter);
            }
            return (long) i7;
        } catch (Exception e) {
            j.D(e, C0086a.o(i2, "getIntParameter failed {", "} e="), this.TAG);
            return (long) i7;
        }
    }

    public String getParameter(int i2) {
        return null;
    }

    public int getProcessMode() {
        return 2;
    }

    public String getTagAnalyzedFull() {
        return "";
    }

    public boolean processImage(Uri uri, String str, long j2, int i2, long j3) {
        return false;
    }

    public boolean supportUpscaleType(int i2, int i7) {
        int i8;
        if (getApiVersion() != null) {
            i8 = 20;
        } else {
            i8 = Encode.BitRate.VIDEO_QCIF_BITRATE;
        }
        if (i2 * i7 >= 1000000 || Math.min(i2, i7) < i8) {
            return false;
        }
        return true;
    }

    public String tag() {
        return "VslMesDetectorCompat";
    }

    public boolean tryInit(Context context) {
        return false;
    }

    public static List<Integer> decodeEnhances(long j2, boolean z) {
        Byte[] bArr = new Byte[8];
        if (z && SdkConfig.atLeast(SdkConfig.SEM.T_MR5) && isPreprocessor(j2)) {
            j2 >>= 8;
        }
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[i2] = Byte.valueOf((byte) ((int) (255 & j2)));
            j2 >>= 8;
        }
        return (List) Stream.of(bArr).map(new o(6)).collect(Collectors.toList());
    }

    public void stop() {
    }

    public void deInit(boolean z) {
    }

    public void init(Context context) {
    }

    public void setProgressUpdateListener(Consumer<Double> consumer) {
    }

    public void setServicePurpose(String str) {
    }
}
