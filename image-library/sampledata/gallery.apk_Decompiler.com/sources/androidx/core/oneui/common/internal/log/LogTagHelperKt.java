package androidx.core.oneui.common.internal.log;

import android.os.Build;
import android.util.Log;
import i.C0212a;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001b\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001b\u0010\u0007\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0007\u0010\u0005\u001a\u001b\u0010\b\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\b\u0010\u0005\"\u001a\u0010\n\u001a\u00020\t8\u0000X\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/core/oneui/common/internal/log/LogTag;", "", "msg", "Lme/x;", "debug", "(Landroidx/core/oneui/common/internal/log/LogTag;Ljava/lang/String;)V", "info", "warn", "error", "", "IS_DEBUG_DEVICE", "Z", "getIS_DEBUG_DEVICE", "()Z", "core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LogTagHelperKt {
    private static final boolean IS_DEBUG_DEVICE;

    static {
        boolean z;
        String str = Build.TYPE;
        j.d(str, "TYPE");
        String lowerCase = str.toLowerCase(Locale.ROOT);
        j.d(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        if (lowerCase.equals("eng") || lowerCase.equals("userdebug")) {
            z = true;
        } else {
            z = false;
        }
        IS_DEBUG_DEVICE = z;
    }

    public static final void debug(LogTag logTag, String str) {
        String str2;
        j.e(logTag, "<this>");
        j.e(str, "msg");
        if (logTag.isDebugVersion()) {
            str2 = logTag.getVersion();
        } else {
            str2 = "";
        }
        if (IS_DEBUG_DEVICE) {
            StringBuilder s = C0212a.s(str2);
            s.append(logTag.getPrefix());
            s.append('.');
            s.append(logTag.getLogTag());
            Log.d(s.toString(), str);
        }
    }

    public static final void error(LogTag logTag, String str) {
        String str2;
        j.e(logTag, "<this>");
        j.e(str, "msg");
        if (logTag.isDebugVersion()) {
            str2 = logTag.getVersion();
        } else {
            str2 = "";
        }
        StringBuilder s = C0212a.s(str2);
        s.append(logTag.getPrefix());
        s.append('.');
        s.append(logTag.getLogTag());
        Log.e(s.toString(), str);
    }

    public static final void info(LogTag logTag, String str) {
        String str2;
        j.e(logTag, "<this>");
        j.e(str, "msg");
        if (logTag.isDebugVersion()) {
            str2 = logTag.getVersion();
        } else {
            str2 = "";
        }
        StringBuilder s = C0212a.s(str2);
        s.append(logTag.getPrefix());
        s.append('.');
        s.append(logTag.getLogTag());
        Log.i(s.toString(), str);
    }

    public static final void warn(LogTag logTag, String str) {
        String str2;
        j.e(logTag, "<this>");
        j.e(str, "msg");
        if (logTag.isDebugVersion()) {
            str2 = logTag.getVersion();
        } else {
            str2 = "";
        }
        StringBuilder s = C0212a.s(str2);
        s.append(logTag.getPrefix());
        s.append('.');
        s.append(logTag.getLogTag());
        Log.w(s.toString(), str);
    }
}
