package com.samsung.android.sum.core;

import N2.j;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.dynamicview.a;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sum.core.filter.MediaFilter;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Def {
    public static final int INVALID = -1;
    public static final String JUNIT_TEST_EXECUTION_MODE = "secmm.sum.junit-test";
    public static final long MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS = 3000;
    public static final long SURFACE_CHANNEL_TIMEOUT_MILLIS = 5000;
    private static final String TAG = tagOf((Class<?>) Def.class);
    public static final long serialVersionUID = 1;

    public static void check(boolean z) {
        check(z, "", new Object[0]);
    }

    public static String contentToString(String... strArr) {
        return (String) Arrays.stream(strArr).filter(new a(4)).collect(Collectors.joining(ArcCommonLog.TAG_COMMA));
    }

    public static String contentToStringln(String str, String... strArr) {
        return (String) Arrays.stream(strArr).filter(new a(5)).map(new C9.a(str, 3)).collect(Collectors.joining("\n"));
    }

    public static String fmtstr(String str, Object... objArr) {
        return String.format(str, objArr);
    }

    public static String fmtstrln(String str, Object... objArr) {
        return String.format(str + "\n", objArr);
    }

    public static String getCoreVersion() {
        return BuildConfig.SUM_CORE_VERSION;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x001f A[SYNTHETIC, Splitter:B:17:0x001f] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x002a A[SYNTHETIC, Splitter:B:24:0x002a] */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long getFileSize(java.io.FileDescriptor r4) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0028, all -> 0x001c }
            r1.<init>(r4)     // Catch:{ IOException -> 0x0028, all -> 0x001c }
            java.nio.channels.FileChannel r4 = r1.getChannel()     // Catch:{ IOException -> 0x001a, all -> 0x0017 }
            long r2 = r4.size()     // Catch:{ IOException -> 0x001a, all -> 0x0017 }
            r1.close()     // Catch:{ IOException -> 0x0012 }
            return r2
        L_0x0012:
            r4 = move-exception
            r4.printStackTrace()
            return r2
        L_0x0017:
            r4 = move-exception
            r0 = r1
            goto L_0x001d
        L_0x001a:
            r0 = r1
            goto L_0x0028
        L_0x001c:
            r4 = move-exception
        L_0x001d:
            if (r0 == 0) goto L_0x0027
            r0.close()     // Catch:{ IOException -> 0x0023 }
            goto L_0x0027
        L_0x0023:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0027:
            throw r4
        L_0x0028:
            if (r0 == 0) goto L_0x0032
            r0.close()     // Catch:{ IOException -> 0x002e }
            goto L_0x0032
        L_0x002e:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0032:
            r0 = -1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.Def.getFileSize(java.io.FileDescriptor):long");
    }

    public static boolean isAndroid() {
        try {
            Class.forName("android.os.Build");
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isJUnitTest() {
        try {
            Class.forName("org.junit.Test");
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isRangeIn(int i2, int i7, int i8) {
        if (i2 < i7 || i2 > i8) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$contentToString$1(String str) {
        return !str.isEmpty();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$contentToStringln$2(String str) {
        return !str.isEmpty();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$taglnOf$0(Object obj) {
        return tagOf(obj) + "\n";
    }

    public static String makeExceptionTag(Exception exc, MediaFilter mediaFilter) {
        return "@[" + mediaFilter.getDescriptor().getFilterId() + "]@" + exc.getMessage();
    }

    public static void require(boolean z) {
        require(z, "", new Object[0]);
    }

    public static String tagOf(Class<?> cls) {
        return "Sum@".concat(cls.getSimpleName());
    }

    public static String taglnOf(Object obj) {
        return (String) Optional.ofNullable(obj).map(new com.samsung.android.sdk.scs.ai.visual.c2pa.a(4)).orElse("");
    }

    public static void check(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalStateException(fmtstr(str, objArr));
        }
    }

    public static void require(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(fmtstr(str, objArr));
        }
    }

    public static String tagOf(Object obj) {
        String str;
        if (obj instanceof String) {
            str = (String) obj;
        } else if (obj instanceof Class) {
            str = ((Class) obj).getSimpleName();
        } else {
            str = obj.getClass().getSimpleName();
        }
        StringBuilder k = j.k("[[", str, Log.TAG_SEPARATOR);
        k.append(obj.hashCode());
        k.append("]]");
        return k.toString();
    }
}
