package com.samsung.android.sum.core;

import B8.b;
import C3.C0392b;
import O3.o;
import android.content.Context;
import android.os.Build;
import android.os.SemSystemProperties;
import com.samsung.android.sdk.mobileservice.social.share.ShareApi;
import com.samsung.android.sdk.scs.ai.visual.c2pa.a;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DebugUtils {
    public static final SystemDebugLevel SYSTEM_DEBUG_LEVEL = SystemDebugLevel.valueOf(SemSystemProperties.getInt("ro.boot.debug_level", 0));
    public static final boolean TRACE_BUFFER = SemSystemProperties.getBoolean("secmm.sum.trace-buffer", false);
    public static final boolean TRACE_BUFFER_CHANNEL = SemSystemProperties.getBoolean("secmm.sum.trace-buffer-channel", false);
    public static final boolean TRACE_MESSAGE = SemSystemProperties.getBoolean("secmm.sum.trace-message", true);
    public static final boolean TRACE_MESSAGE_CHANNEL = SemSystemProperties.getBoolean("secmm.sum.trace-message-channel", false);
    private static final Map<String, String> appDebugLevelMap = new HashMap<String, String>() {
        {
            put("com.sec.android.app.camera", "secmm.sum.debug-level-camera");
            put("com.samsung.android.motionphoto.app", "secmm.sum.debug-level-mps");
        }
    };

    /* renamed from: com.samsung.android.sum.core.DebugUtils$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sum$core$DebugUtils$SystemDebugLevel;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.android.sum.core.DebugUtils$SystemDebugLevel[] r0 = com.samsung.android.sum.core.DebugUtils.SystemDebugLevel.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sum$core$DebugUtils$SystemDebugLevel = r0
                com.samsung.android.sum.core.DebugUtils$SystemDebugLevel r1 = com.samsung.android.sum.core.DebugUtils.SystemDebugLevel.LOW     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$DebugUtils$SystemDebugLevel     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sum.core.DebugUtils$SystemDebugLevel r1 = com.samsung.android.sum.core.DebugUtils.SystemDebugLevel.HIGH     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$DebugUtils$SystemDebugLevel     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.sum.core.DebugUtils$SystemDebugLevel r1 = com.samsung.android.sum.core.DebugUtils.SystemDebugLevel.MID     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$DebugUtils$SystemDebugLevel     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.sum.core.DebugUtils$SystemDebugLevel r1 = com.samsung.android.sum.core.DebugUtils.SystemDebugLevel.NONE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.DebugUtils.AnonymousClass2.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SystemDebugLevel {
        NONE(0),
        LOW(20300),
        MID(18765),
        HIGH(18760);
        
        private final int levelCode;

        private SystemDebugLevel(int i2) {
            this.levelCode = i2;
        }

        public static String nameOf(SystemDebugLevel systemDebugLevel) {
            int i2 = AnonymousClass2.$SwitchMap$com$samsung$android$sum$core$DebugUtils$SystemDebugLevel[systemDebugLevel.ordinal()];
            if (i2 == 1) {
                return ShareApi.HQVGA_240_SIZE_IMAGE;
            }
            if (i2 == 2) {
                return "high";
            }
            if (i2 != 3) {
                return "none";
            }
            return "mid";
        }

        public int getLevelCode() {
            return this.levelCode;
        }

        public static SystemDebugLevel valueOf(int i2) {
            return (SystemDebugLevel) Arrays.stream(values()).filter(new b(i2, 17)).findFirst().orElse(NONE);
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Object, java.util.function.Function] */
    public static SumDebugLevel getAppDebugLevel(Context context) {
        return (SumDebugLevel) Optional.ofNullable(appDebugLevelMap.get(context.getPackageName())).map(new Object()).map(new a(2)).orElse((Object) null);
    }

    public static boolean isDebugModeEnabled() {
        if (!Build.TYPE.equals("user") || !SemSystemProperties.getBoolean("ro.product_ship", false)) {
            return true;
        }
        return false;
    }

    public static boolean isShipMode() {
        return SemSystemProperties.getBoolean("ro.product_ship", false);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SumDebugLevel {
        VERBOSE(2, "verbose"),
        DEBUG(3, "debug"),
        INFO(4, "info"),
        WARN(5, "warn"),
        ERROR(6, "error"),
        ASSERT(7, "assert");
        
        private static final String tag = "secmm.sum.debug-level";
        private final int levelCode;
        private final String levelString;

        private SumDebugLevel(int i2, String str) {
            this.levelCode = i2;
            this.levelString = str;
        }

        public static String nameOf(int i2) {
            return (String) Arrays.stream(values()).filter(new b(i2, 16)).findFirst().map(new a(3)).orElse("debug");
        }

        public static SumDebugLevel of(String str) {
            if (str.isEmpty()) {
                return null;
            }
            return (SumDebugLevel) Arrays.stream(values()).filter(new C0392b(str, 19)).findFirst().orElse((Object) null);
        }

        public int getLevelCode() {
            return this.levelCode;
        }

        public String getLevelName() {
            return this.levelString;
        }

        public static SumDebugLevel of(int i2) {
            return of((String) Optional.ofNullable(SemSystemProperties.get(tag, nameOf(i2))).map(new o(4)).orElse("info"));
        }
    }
}
