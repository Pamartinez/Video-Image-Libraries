package androidx.core.os;

import android.os.Build;
import android.os.ext.SdkExtensions;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0012B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\f8\u0006X\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\f8\u0006X\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\f8\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\f8\u0006X\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u000e¨\u0006\u0013"}, d2 = {"Landroidx/core/os/BuildCompat;", "", "<init>", "()V", "", "codename", "buildCodename", "", "isAtLeastPreReleaseCodename", "(Ljava/lang/String;Ljava/lang/String;)Z", "isAtLeastV", "()Z", "", "R_EXTENSION_INT", "I", "S_EXTENSION_INT", "T_EXTENSION_INT", "AD_SERVICES_EXTENSION_INT", "Api30Impl", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BuildCompat {
    public static final int AD_SERVICES_EXTENSION_INT;
    public static final BuildCompat INSTANCE = new BuildCompat();
    public static final int R_EXTENSION_INT;
    public static final int S_EXTENSION_INT;
    public static final int T_EXTENSION_INT;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"Landroidx/core/os/BuildCompat$Api30Impl;", "", "()V", "getExtensionVersion", "", "extension", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Api30Impl {
        public static final Api30Impl INSTANCE = new Api30Impl();

        private Api30Impl() {
        }

        public final int getExtensionVersion(int i2) {
            return SdkExtensions.getExtensionVersion(i2);
        }
    }

    static {
        Api30Impl api30Impl = Api30Impl.INSTANCE;
        R_EXTENSION_INT = api30Impl.getExtensionVersion(30);
        S_EXTENSION_INT = api30Impl.getExtensionVersion(31);
        T_EXTENSION_INT = api30Impl.getExtensionVersion(33);
        AD_SERVICES_EXTENSION_INT = api30Impl.getExtensionVersion(1000000);
    }

    private BuildCompat() {
    }

    public static final boolean isAtLeastPreReleaseCodename(String str, String str2) {
        j.e(str, "codename");
        j.e(str2, "buildCodename");
        if ("REL".equals(str2)) {
            return false;
        }
        Integer isAtLeastPreReleaseCodename$codenameToInt = isAtLeastPreReleaseCodename$codenameToInt(str2);
        Integer isAtLeastPreReleaseCodename$codenameToInt2 = isAtLeastPreReleaseCodename$codenameToInt(str);
        if (isAtLeastPreReleaseCodename$codenameToInt == null || isAtLeastPreReleaseCodename$codenameToInt2 == null) {
            if (isAtLeastPreReleaseCodename$codenameToInt == null && isAtLeastPreReleaseCodename$codenameToInt2 == null) {
                Locale locale = Locale.ROOT;
                String upperCase = str2.toUpperCase(locale);
                j.d(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                String upperCase2 = str.toUpperCase(locale);
                j.d(upperCase2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                if (upperCase.compareTo(upperCase2) >= 0) {
                    return true;
                }
                return false;
            } else if (isAtLeastPreReleaseCodename$codenameToInt != null) {
                return true;
            } else {
                return false;
            }
        } else if (isAtLeastPreReleaseCodename$codenameToInt.intValue() >= isAtLeastPreReleaseCodename$codenameToInt2.intValue()) {
            return true;
        } else {
            return false;
        }
    }

    private static final Integer isAtLeastPreReleaseCodename$codenameToInt(String str) {
        String upperCase = str.toUpperCase(Locale.ROOT);
        j.d(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        if (upperCase.equals("BAKLAVA")) {
            return 0;
        }
        return null;
    }

    public static final boolean isAtLeastV() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 35) {
            return true;
        }
        if (i2 < 34) {
            return false;
        }
        String str = Build.VERSION.CODENAME;
        j.d(str, "CODENAME");
        if (isAtLeastPreReleaseCodename("VanillaIceCream", str)) {
            return true;
        }
        return false;
    }
}
