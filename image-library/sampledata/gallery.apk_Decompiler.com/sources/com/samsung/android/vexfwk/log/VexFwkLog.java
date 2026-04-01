package com.samsung.android.vexfwk.log;

import android.util.Log;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/log/VexFwkLog;", "", "<init>", "()V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkLog {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0003¢\u0006\u0004\b\u0006\u0010\u0007J!\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\n\u0010\u000bJ!\u0010\f\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\f\u0010\u000bJ!\u0010\r\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\r\u0010\u000bJ!\u0010\u000e\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u000e\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u000f\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/vexfwk/log/VexFwkLog$Companion;", "", "<init>", "()V", "", "msg", "makeUpMessage", "(Ljava/lang/String;)Ljava/lang/String;", "tag", "Lme/x;", "v", "(Ljava/lang/String;Ljava/lang/String;)V", "d", "i", "w", "e", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final String makeUpMessage(String str) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            if (stackTrace.length <= 5) {
                return str;
            }
            String fileName = stackTrace[4].getFileName();
            int lineNumber = stackTrace[4].getLineNumber();
            String methodName = stackTrace[4].getMethodName();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(fileName);
            sb2.append(NumericEnum.SEP);
            sb2.append(lineNumber);
            sb2.append(" ");
            sb2.append(methodName);
            return C0212a.p(sb2, ": ", str);
        }

        public final void d(String str, String str2) {
            j.e(str2, "msg");
            Log.d(str, makeUpMessage(str2));
        }

        public final void e(String str, String str2) {
            j.e(str, "tag");
            j.e(str2, "msg");
            Log.e(str, makeUpMessage(str2));
        }

        public final void i(String str, String str2) {
            j.e(str2, "msg");
            Log.i(str, makeUpMessage(str2));
        }

        public final void v(String str, String str2) {
            j.e(str2, "msg");
            Log.v(str, makeUpMessage(str2));
        }

        public final void w(String str, String str2) {
            j.e(str2, "msg");
            Log.w(str, makeUpMessage(str2));
        }

        private Companion() {
        }
    }

    public static final void d(String str, String str2) {
        Companion.d(str, str2);
    }

    public static final void e(String str, String str2) {
        Companion.e(str, str2);
    }

    public static final void i(String str, String str2) {
        Companion.i(str, str2);
    }

    private static final String makeUpMessage(String str) {
        return Companion.makeUpMessage(str);
    }

    public static final void v(String str, String str2) {
        Companion.v(str, str2);
    }

    public static final void w(String str, String str2) {
        Companion.w(str, str2);
    }
}
