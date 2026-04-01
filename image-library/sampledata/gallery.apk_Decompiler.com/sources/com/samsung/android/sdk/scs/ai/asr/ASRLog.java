package com.samsung.android.sdk.scs.ai.asr;

import android.os.Build;
import com.adobe.internal.xmp.XMPConst;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sdk.scs.base.utils.Log;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ASRLog extends Log {
    public static final boolean isDevelop = (!"user".equals(Build.TYPE));

    private static String createMsg(Object[] objArr) {
        if (objArr == null) {
            return "null";
        }
        int length = objArr.length - 1;
        if (length == -1) {
            return XMPConst.ARRAY_ITEM_NAME;
        }
        int i2 = 0;
        if (length == 0) {
            return String.valueOf(objArr[0]);
        }
        StringBuilder sb2 = new StringBuilder("[");
        while (true) {
            sb2.append(objArr[i2]);
            if (i2 == length) {
                sb2.append(']');
                return sb2.toString();
            }
            sb2.append(ArcCommonLog.TAG_COMMA);
            i2++;
        }
    }

    public static void d(String str, Object... objArr) {
        Log.d(str, createMsg(objArr));
    }

    public static void e(String str, Object... objArr) {
        Log.e(str, createMsg(objArr));
    }

    public static void i(String str, Object... objArr) {
        Log.i(str, createMsg(objArr));
    }

    public static void secure(String str, Object... objArr) {
        if (isDevelop) {
            Log.d(C0212a.A(str, "@Dbg"), createMsg(objArr));
        }
    }

    public static void w(String str, Object... objArr) {
        Log.w(str, createMsg(objArr));
    }
}
