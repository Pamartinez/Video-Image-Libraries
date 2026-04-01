package com.samsung.android.gallery.support.config;

import android.os.Build;
import java.util.Optional;
import n5.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SdkConfig {
    public static final int SDK_VER = Build.VERSION.SDK_INT;
    public static final int SEM_SYSTEM_VER = getSystemSemVersion();
    public static final int SEM_VER = getSemVersion();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FirstApiLevel {
        public static final boolean LESS_THAN_P;
        public static final boolean LESS_THAN_T;
        public static final boolean LESS_THAN_U;
        public static final int SDK_INT;

        static {
            boolean z;
            boolean z3;
            boolean z7 = false;
            int systemPropertyInt = getSystemPropertyInt("ro.product.first_api_level", 0);
            SDK_INT = systemPropertyInt;
            if (systemPropertyInt < 28) {
                z = true;
            } else {
                z = false;
            }
            LESS_THAN_P = z;
            if (systemPropertyInt < 33) {
                z3 = true;
            } else {
                z3 = false;
            }
            LESS_THAN_T = z3;
            if (systemPropertyInt < 34) {
                z7 = true;
            }
            LESS_THAN_U = z7;
        }

        public static String getAppInitVersion() {
            int i2 = SDK_INT;
            if (i2 >= 35) {
                return "15.6.00.0#1560000000#0";
            }
            if (i2 == 34) {
                return "15.0.00.0#1500000000#0";
            }
            if (i2 == 33) {
                return "14.0.00.0#1400000000#0";
            }
            if (i2 == 31) {
                return "13.0.00.0#1300000000#0";
            }
            if (i2 == 30) {
                return "12.0.00.0#1200000000#0";
            }
            if (i2 == 29) {
                return "11.0.00.0#1100000000#0";
            }
            if (i2 == 28) {
                return "10.0.00.0#1000000000#0";
            }
            return "-1";
        }

        public static int getSystemPropertyInt(String str, int i2) {
            try {
                return ((Integer) Optional.ofNullable((String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class}).invoke((Object) null, new Object[]{str})).map(new e(18)).orElse(Integer.valueOf(i2))).intValue();
            } catch (Error | Exception unused) {
                return i2;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum GED {
        L(21),
        M(23),
        N(24),
        N_MR1(25),
        O(26),
        O_MR1(26),
        P(28),
        Q(29),
        R(30),
        S(31),
        S_V2(32),
        T(33),
        U(34),
        V(35),
        B(36),
        MAX(Integer.MAX_VALUE);
        
        final boolean enabled;
        final int version;

        private GED(int i2) {
            boolean z;
            this.version = i2;
            if (SdkConfig.SDK_VER >= i2) {
                z = true;
            } else {
                z = false;
            }
            this.enabled = z;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SEM {
        N(80000, "0"),
        N_MR1(80100, "0"),
        N_MR5(80500, "0"),
        O(90000, "0"),
        O_MR5(90500, "0"),
        P(100000, "1.0"),
        P_MR5(100500, "1.5"),
        Q(110000, "2.0"),
        Q_MR1(110100, "2.1"),
        Q_MR5(110500, "2.5"),
        R(120000, "3.0"),
        R_MR1(120100, "3.1"),
        R_MR5(120500, "3.1.1"),
        S(130000, "4.0"),
        S_MR1(130100, "4.1"),
        S_MR5(130500, "4.1.1"),
        T(140000, "5.0"),
        T_MR1(140100, "5.1"),
        T_MR5(140500, "5.1.1"),
        U(150000, "6.0"),
        U_MR1(150100, "6.1"),
        U_MR5(150500, "6.1.1"),
        V(160000, "7.0"),
        B(170000, "8.0"),
        B_MR5(170500, "8.5"),
        MAX(Integer.MAX_VALUE, "0");
        
        final boolean enabled;
        final boolean enabledSystem;
        public final String oneUi;
        public final int version;

        private SEM(int i2, String str) {
            boolean z;
            this.version = i2;
            boolean z3 = false;
            if (SdkConfig.SEM_VER >= i2) {
                z = true;
            } else {
                z = false;
            }
            this.enabled = z;
            this.enabledSystem = SdkConfig.SEM_SYSTEM_VER >= i2 ? true : z3;
            this.oneUi = str;
        }
    }

    public static boolean atLeast(GED ged) {
        return ged.enabled;
    }

    public static boolean atLeastSystem(SEM sem) {
        return sem.enabledSystem;
    }

    public static int getSemVersion() {
        try {
            return Build.VERSION.SEM_PLATFORM_INT;
        } catch (Error | Exception unused) {
            return 0;
        }
    }

    public static int getSystemSemVersion() {
        try {
            return Build.VERSION.SEM_PLATFORM_INT;
        } catch (Error | Exception unused) {
            return 0;
        }
    }

    public static boolean lessThan(GED ged) {
        return SDK_VER < ged.version;
    }

    public static boolean match(GED ged) {
        return SDK_VER == ged.version;
    }

    public static boolean moreThan(GED ged) {
        return SDK_VER > ged.version;
    }

    public static boolean range(GED ged, GED ged2) {
        return ged.enabled && SDK_VER < ged2.version;
    }

    public static boolean atLeast(SEM sem) {
        return sem.enabled;
    }

    public static boolean lessThan(SEM sem) {
        return SEM_VER < sem.version;
    }

    public static boolean match(SEM sem) {
        return SEM_VER == sem.version;
    }

    public static boolean moreThan(SEM sem) {
        return SEM_VER > sem.version;
    }

    public static boolean range(SEM sem, SEM sem2) {
        return sem.enabled && SEM_VER < sem2.version;
    }
}
