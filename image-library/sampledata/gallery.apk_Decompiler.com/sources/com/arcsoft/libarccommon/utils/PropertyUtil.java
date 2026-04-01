package com.arcsoft.libarccommon.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PropertyUtil {
    private static final String TAG = "ArcSoft_PropertyUtil";

    public static String getJavaProperty(String str, String str2) {
        try {
            Runtime runtime = Runtime.getRuntime();
            String readLine = new BufferedReader(new InputStreamReader(runtime.exec("getprop " + str).getInputStream())).readLine();
            if (readLine == null || readLine.length() <= 0) {
                return str2;
            }
            return readLine;
        } catch (Exception e) {
            String str3 = TAG;
            ArcCommonLog.e(str3, "exception = " + e.getMessage());
        } catch (Throwable unused) {
            return null;
        }
    }
}
