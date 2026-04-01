package com.samsung.android.sdk.mobileservice.util;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.FormatFlagsConversionMismatchException;
import java.util.Iterator;
import java.util.MissingFormatArgumentException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum SdkLog {
    INSTANCE;
    
    private static final boolean ENG = false;
    private static final int LINE_LENGTH = 512;
    private static final String prefix = "SEMS-13.0.31_";
    private static final String version = "13.0.31_";

    static {
        ENG = "eng".equals(Build.TYPE);
    }

    private ArrayList<String> breakUpMsg(String str, int i2) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        int length = str.length();
        if (length > i2) {
            int i7 = 0;
            while (true) {
                int i8 = i7 + 1;
                int i10 = i2 * i8;
                arrayList.add(str.substring(i2 * i7, i10));
                if (length <= (i7 + 2) * i2) {
                    arrayList.add(str.substring(i10, length));
                    return arrayList;
                }
                i7 = i8;
            }
        } else {
            arrayList.add(str);
            return arrayList;
        }
    }

    public static void d(String str, String str2) {
        Iterator<String> it = INSTANCE.breakUpMsg(str2, 512).iterator();
        while (it.hasNext()) {
            Log.d(prefix + str, it.next());
        }
    }

    public static void e(String str, String str2) {
        Iterator<String> it = INSTANCE.breakUpMsg(str2, 512).iterator();
        while (it.hasNext()) {
            Log.e(prefix + str, it.next());
        }
    }

    public static String getReference(Object obj) {
        try {
            return obj.toString().substring(obj.toString().lastIndexOf(com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR));
        } catch (Exception unused) {
            return null;
        }
    }

    public static void i(String str, String str2) {
        Iterator<String> it = INSTANCE.breakUpMsg(str2, 512).iterator();
        while (it.hasNext()) {
            Log.i(prefix + str, it.next());
        }
    }

    public static void s(String str, String str2, String... strArr) {
        if (strArr == null) {
            strArr = new String[1];
        }
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (strArr[i2] == null) {
                strArr[i2] = "null";
            }
            if (!ENG) {
                char[] cArr = new char[strArr[i2].length()];
                Arrays.fill(cArr, '*');
                strArr[i2] = new String(cArr);
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            try {
                str2 = String.format(str2, strArr);
            } catch (FormatFlagsConversionMismatchException | MissingFormatArgumentException unused) {
            }
        }
        Iterator<String> it = INSTANCE.breakUpMsg(str2, 512).iterator();
        while (it.hasNext()) {
            Log.d(prefix + str, it.next());
        }
    }

    public static void v(String str, String str2) {
        Iterator<String> it = INSTANCE.breakUpMsg(str2, 512).iterator();
        while (it.hasNext()) {
            Log.v(prefix + str, it.next());
        }
    }

    public static void s(String str, String str2) {
        if (ENG) {
            Iterator<String> it = INSTANCE.breakUpMsg(str2, 512).iterator();
            while (it.hasNext()) {
                Log.d(prefix + str, it.next());
            }
        }
    }

    public static void s(Exception exc) {
        if (ENG) {
            exc.printStackTrace();
            return;
        }
        i("SEMS_SDK", "fatal exception! Trace not allowed.\n" + exc.getMessage());
    }
}
