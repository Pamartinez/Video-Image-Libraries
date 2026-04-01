package com.samsung.android.gallery.support.utils;

import N2.j;
import android.accounts.Account;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewStub;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.scsp.framework.core.identity.IdentityApiContract;
import com.samsung.scsp.framework.core.network.HeaderSetup;
import i.C0212a;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Logger {
    private static final String[] COLOR_MODE = {"DEF", "WCG", "HDR", "HDR10", "A8"};
    public static final boolean QUERY;
    public static final boolean THUMBNAIL = false;
    private static final boolean sAllowDebug;
    private static final boolean sAllowPrivateLog;
    static final byte[][] sEncV2Key = {new byte[]{17, 34, -1, -33}, new byte[]{-33, 52, 34, -47}, new byte[]{115, -35, 51, 63}, new byte[]{-54, -52, -86, -124}, new byte[]{21, 33, -33, -33}, new byte[]{81, 18, -1, -3}, new byte[]{52, 51, 63, 115}, new byte[]{95, -14, 31, -22}};
    static final byte[][] sEncV3Key = {new byte[]{0}, new byte[]{1}, new byte[]{2}, new byte[]{3}, new byte[]{-19, 70, 76, 9, -57, -123, 46, 21, 16, 86, -35, 122, 98, 6, 27, 121, 68, 123, -103, 101, -13, 48, 12, 96, -72, -76, 77, -126, -66, -112, 36, -81, -83, 85, 60, 100, 121, 26, -13, -20, -115, 116, -108, 88, 17, 43, 56, -119, 125, 1, Byte.MIN_VALUE, -120, 13, 15, 110, -58, 92, -42, -6, -47, -15, 10, 83, -44, 50, 27, 10, -34, 113, -71, -5, -1, -3, 36, -118, -113, -3, -10, -28, -17, 112, 47, 2, -46, -23, -110, -61, -29, -102, -85, -39, 72, -35, -16, -26, -20, -56, 33, Byte.MAX_VALUE, -21, -99, -2, 80, -18, -65, 124, -18, 33, -16, 2, -55, -19, 105, 36, 124, 21, -104, -125, -105, -24, 124, 83, -103, 105, -118, -28, -76, -9, 11, 40, -55, 113, 60, 78, -43, -6, 11, 23, -14, 67, -69, 66, -62, 124, -66, -28, -61, -49, 110, 123, 39, -96, -80, -65, -48, 15, -24, -70, 42, 80, 93, -40, -42, 65, -58, -31, -44, -53, -41, -91, 96, -109, -54, -27, -114, -44, -35, -4, 3, 123, -123, 64, -69, -91, -107, -127, 57, 69, -112, 5, -38, -126, -81, -16, -22, -33, 43, 32, 77, -72, 36, -99, -108, 46, 51, 38, 99, 43, -18, -35, 36, 111, -36, -67, 16, 111, -16, -1, Byte.MIN_VALUE, 65, 70, -49, 62, -114, 26, 108, -29, 11, -5, -54, -110, 54, 69, -114, 67, 49, -58, -115, -11, 31, -123, 113, 29, -82, -92, -8, 68, -76, -42, Byte.MAX_VALUE, 46, 8, -71, -4, 26, 32}, new byte[]{-54, 51, -60, -113, -18, 106, -84, -47, -118, -124, -88, 31, -96, 21, 91, 96, -29, 119, -55, 84, 34, -93, -44, -97, 78, -116, 44, 48, 8, -34, 14, 71, -68, -75, -18, -46, 70, -110, 5, 62, -20, 18, 126, -92, -65, -53, 104, 8, 81, 17, -87, -15, -76, -80, 22, 93, 21, 65, 93, 80, -126, 114, -52, 20, -57, 15, -106, -78, -104, 114, -27, -3, 30, 121, 93, 73, 21, 79, -29, 45, -4, 21, 50, 125, -127, 119, -50, 123, 36, -21, -43, -117, 47, -52, 119, -73, 126, 44, -9, 33, 121, 45, 43, 28, -106, 102, -24, -126, -98, -58, 48, 34, -13, -99, -3, 66, 59, -28, 54, -99, 88, -122, 38, -55, -110, 51, -76, -116, 91, 91, -72, 7, -125, Byte.MIN_VALUE, -42, Byte.MIN_VALUE, -2, -44, 86, -89, -11, -68, -38, -13, -84, 76, -123, 85, -113, 61, 39, 11, -45, -99, 73, 19, 50, 96, -44, -98, -62, -20, 14, -24, 69, 62, -37, 77, -91, -125, -35, 84, -20, 111, -111, -112, -102, -39, -51, 12, -85, -28, -75, -118, -111, 6, -104, -117, 98, 120, -72, 37, 79, 68, 22, -44, -119, 11, -71, -32, 79, -105, 101, -90, -91, 6, 4, 55, 89, 82, -116, 2, 112, -71, -31, 24, -92, -12, 109, -103, -57, 40, -47, 108, -5, -18, -27, 21, -123, -20, 96, -32, 6, -55, 34, -29, -86, 60, 47, -125, -24, -103, 76, 23, -69, 119, -45, -15, -89, -17, -45, -84, 35, 113, -49, -68}, new byte[]{-117, 44, -36, 41, 104, 41, -34, -36, 82, -70, -124, 8, -121, -17, -116, -61, 115, -87, -1, -8, 87, -81, 25, 113, -25, 38, -51, 47, -30, -91, 121, -83, -41, -124, -92, 122, -54, -18, 81, 12, 100, -27, -5, -73, 103, 35, -83, -12, 49, 10, -107, -78, 10, -66, 112, 66, -78, -105, -66, 24, -29, 11, 46, 120, 94, -57, 113, 95, 121, 70, -90, -102, -74, -87, 24, 14, 47, -17, -61, -2, -49, 15, 4, -63, 69, 100, -106, 107, 94, -92, 99, -94, 4, -76, -118, -76, -86, 25, -4, -27, 45, -34, -92, 4, -104, -11, -118, -28, -102, -45, -117, -97, -15, 69, 76, 41, -7, 83, -85, -87, -127, 112, -21, -85, -12, -4, -67, 18, 10, 117, -86, -42, -103, 18, -73, 26, 45, 14, 106, -30, -40, 8, 35, -119, 64, -87, -11, 19, 5, 94, -115, -90, -42, -81, -90, 50, -68, 56, 1, -124, 23, 45, Byte.MAX_VALUE, -120, 92, -52, 14, 7, 93, -16, 108, 16, 48, 43, 73, 62, -118, 22, -126, 111, -51, -86, -48, 124, 90, 64, -54, -123, -108, 13, -62, 99, -113, -27, 58, -11, 88, -80, 121, -16, 56, -44, 97, 56, 28, -28, -119, 24, 117, 114, 100, 28, 107, 106, -85, 19, 16, 47, -40, -121, 111, -1, -21, -1, -4, -110, 50, -111, -27, 14, -106, -76, 66, 73, -108, -70, 107, -13, -58, 8, 61, 18, -35, 18, 10, -76, Byte.MAX_VALUE, -43, -1, -109, 17, -92, -48, -96, -40, 48}, new byte[]{-77, -20, 107, 105, 105, 42, -56, 48, 63, 111, -127, 58, -25, 43, -68, -113, 14, -26, 97, 19, -44, -43, 91, 50, -34, -59, -113, -23, 93, -13, 121, 80, -101, -23, 97, 10, -53, 78, 112, -94, -35, -45, 102, -64, -14, -38, -33, 102, 98, -39, -11, 99, 18, -65, 19, 123, -17, 117, 105, -70, -82, 124, 25, 51, 78, -88, 16, -92, 79, 36, -12, -93, -31, 95, 40, -82, -79, 31, 42, 94, -5, -15, -5, 3, -33, -10, 83, -105, -117, 33, -126, -72, -24, 123, 76, -110, 4, 99, -77, -3, 77, -28, 18, 45, 58, -20, 22, 40, 43, -85, 82, -13, -26, -83, 0, -18, 6, -103, 75, 3, -27, 98, 92, 66, -114, -43, -113, 104, -45, -39, -69, -98, 75, -55, -13, 62, -120, -12, 6, 91, -13, 113, -24, 25, 19, 30, 5, -30, 117, -22, 87, -80, 118, -103, -104, 35, 27, 42, 7, -60, -64, -73, Byte.MIN_VALUE, -49, -7, 92, -112, -116, -110, -38, 68, 40, -50, -60, -35, -74, 1, 60, -77, 36, -32, -17, 52, 50, -96, 126, Byte.MAX_VALUE, -35, -42, -106, -21, -105, 29, Byte.MAX_VALUE, 46, -47, -117, -1, -38, -49, 54, 87, -33, -75, -36, 95, -12, 88, -14, -1, 29, -41, -5, -106, 67, -59, -4, -2, 48, 31, 112, -92, 30, -21, -68, 102, 100, -31, -7, 80, 49, 36, 58, 120, -45, 105, -64, -40, -100, -101, 55, -86, 102, 74, -42, -18, 77, -90, -31, 10, -33, -30, -42, -23, 106, 31}};

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DecimalFormatHolder {
        static final DecimalFormat FORMATTER = new DecimalFormat("#,###");
    }

    static {
        boolean z;
        boolean z3 = DeviceConfig.DEBUG_BINARY;
        sAllowDebug = z3;
        sAllowPrivateLog = z3;
        if (!isAllowDebug() || !Log.isLoggable(Contract.QUERY, 3)) {
            z = false;
        } else {
            z = true;
        }
        QUERY = z;
    }

    public static String decrypt(byte[] bArr) {
        int length = bArr.length - 1;
        byte[] bArr2 = sEncV2Key[bArr[length] % 10];
        byte[] bArr3 = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            bArr3[i2] = (byte) (bArr[i2] ^ bArr2[i2 % bArr2.length]);
        }
        return new String(bArr3);
    }

    public static void dumpLog(PrintWriter printWriter, String str) {
        if (printWriter != null) {
            printWriter.println(str);
        } else {
            Log.e("DUMP", str);
        }
    }

    public static String encode(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return "null";
        }
        byte[] xorWithKey = xorWithKey(str.getBytes(), sEncV3Key[i2]);
        return " #G$E" + i2 + Base64.getEncoder().encodeToString(xorWithKey) + " ";
    }

    public static String format(long j2) {
        return DecimalFormatHolder.FORMATTER.format(j2);
    }

    public static String getAnonymousName(Object obj) {
        if (obj == null) {
            return "null";
        }
        String obj2 = obj.toString();
        int lastIndexOf = obj2.lastIndexOf(".");
        if (lastIndexOf > 0) {
            return obj2.substring(lastIndexOf + 1);
        }
        return obj2;
    }

    public static String getCursorInfo(Cursor cursor) {
        if (cursor == null) {
            return "n";
        }
        try {
            if (cursor.isClosed()) {
                return "c";
            }
            return "" + cursor.getCount();
        } catch (Exception unused) {
            return "e";
        }
    }

    private static String getDrawableBounds(Drawable drawable) {
        Rect rect;
        if (drawable != null) {
            rect = drawable.getBounds();
        } else {
            rect = null;
        }
        if (rect == null) {
            return "DrawableRect(null)";
        }
        StringBuilder sb2 = new StringBuilder("DrawableRect(");
        sb2.append(rect.left);
        sb2.append(',');
        sb2.append(rect.top);
        sb2.append('-');
        sb2.append(rect.right);
        sb2.append(',');
        return j.e(sb2, rect.bottom, ')');
    }

    public static String getEncodedString(Object obj) {
        return obj == null ? "" : getEncodedString(toString(obj));
    }

    public static String getResourceName(View view) {
        String str;
        int id = view.getId();
        if (id == -1) {
            return "null";
        }
        try {
            Resources resources = view.getResources();
            if (resources != null) {
                StringBuilder sb2 = new StringBuilder();
                int i2 = -16777216 & id;
                if (i2 == 2130706432) {
                    str = IdentityApiContract.Parameter.APP;
                } else if (i2 == 16777216) {
                    str = "android";
                } else {
                    str = "";
                }
                sb2.append(str);
                sb2.append(':');
                sb2.append(resources.getResourceTypeName(id));
                sb2.append('/');
                sb2.append(resources.getResourceEntryName(id));
                return sb2.toString();
            }
        } catch (Exception unused) {
        }
        return "";
    }

    public static String getSimpleName(Object obj) {
        if (obj == null) {
            return "null";
        }
        return obj.getClass().getSimpleName() + com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR + Integer.toHexString(obj.hashCode());
    }

    private static String getValueString(String str, Bundle bundle) {
        try {
            Object obj = bundle.get(str);
            if (obj == null) {
                return "null";
            }
            if (obj instanceof String[]) {
                return StringCompat.toString((T[]) (String[]) obj);
            }
            if (obj instanceof int[]) {
                return StringCompat.toString((int[]) obj);
            }
            if (obj instanceof long[]) {
                return StringCompat.toString((long[]) obj);
            }
            return obj.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean isAllowDebug() {
        return sAllowDebug;
    }

    public static boolean isAllowPrivateLog() {
        return sAllowPrivateLog;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$toString$0(StringBuilder sb2, Bundle bundle, String str) {
        sb2.append(str);
        sb2.append('=');
        sb2.append(getValueString(str, bundle));
        sb2.append(',');
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$toString$1(Bundle bundle) {
        return " " + bundle.getString("locationKey");
    }

    private static void loadConfigBasics(Configuration configuration, ArrayList<String> arrayList) {
        LocaleList locales = configuration.getLocales();
        if (!locales.isEmpty()) {
            arrayList.add(locales.toString());
        }
        arrayList.add(HeaderSetup.Key.MCC + configuration.mcc);
        arrayList.add(HeaderSetup.Key.MNC + configuration.mnc);
    }

    private static void loadConfigColorMode(Configuration configuration, ArrayList<String> arrayList) {
        int i2 = configuration.colorMode & 12;
        if (i2 == 4) {
            arrayList.add("nohdr");
        } else if (i2 == 8) {
            arrayList.add("hdr");
        }
        int i7 = configuration.colorMode & 3;
        if (i7 == 1) {
            arrayList.add("nowcg");
        } else if (i7 == 2) {
            arrayList.add("wcg");
        }
    }

    private static void loadConfigDensityDpi(Configuration configuration, ArrayList<String> arrayList) {
        int i2 = configuration.densityDpi;
        if (i2 == 120) {
            arrayList.add("ldpi");
        } else if (i2 == 160) {
            arrayList.add("mdpi");
        } else if (i2 == 213) {
            arrayList.add("tvdpi");
        } else if (i2 == 240) {
            arrayList.add("hdpi");
        } else if (i2 == 320) {
            arrayList.add("xhdpi");
        } else if (i2 == 480) {
            arrayList.add("xxhdpi");
        } else if (i2 != 640) {
            arrayList.add(configuration.densityDpi + "dpi");
        } else {
            arrayList.add("xxxhdpi");
        }
    }

    private static void loadConfigInput(Configuration configuration, ArrayList<String> arrayList) {
        int i2 = configuration.touchscreen;
        if (i2 == 1) {
            arrayList.add("notouch");
        } else if (i2 == 3) {
            arrayList.add("finger");
        }
        int i7 = configuration.keyboardHidden;
        if (i7 == 1) {
            arrayList.add("keyexp");
        } else if (i7 == 2) {
            arrayList.add("keyhid");
        }
        int i8 = configuration.keyboard;
        if (i8 == 1) {
            arrayList.add("nokeys");
        } else if (i8 == 2) {
            arrayList.add("qwerty");
        } else if (i8 == 3) {
            arrayList.add("12key");
        }
    }

    private static void loadConfigNavigation(Configuration configuration, ArrayList<String> arrayList) {
        int i2 = configuration.navigationHidden;
        if (i2 == 1) {
            arrayList.add("navexp");
        } else if (i2 == 2) {
            arrayList.add("navhid");
        }
        int i7 = configuration.navigation;
        if (i7 == 1) {
            arrayList.add("nonav");
        } else if (i7 == 2) {
            arrayList.add("dpad");
        } else if (i7 == 3) {
            arrayList.add("trackball");
        } else if (i7 == 4) {
            arrayList.add("wheel");
        }
    }

    private static void loadConfigOrientation(Configuration configuration, ArrayList<String> arrayList) {
        int i2 = configuration.orientation;
        if (i2 == 0) {
            arrayList.add("undef");
        } else if (i2 == 1) {
            arrayList.add("port");
        } else if (i2 == 2) {
            arrayList.add("land");
        } else if (i2 == 3) {
            arrayList.add("square");
        }
    }

    private static void loadConfigUiMode(Configuration configuration, ArrayList<String> arrayList) {
        switch (configuration.uiMode & 15) {
            case 2:
                arrayList.add("desk");
                break;
            case 3:
                arrayList.add("car");
                break;
            case 4:
                arrayList.add("television");
                break;
            case 5:
                arrayList.add("appliance");
                break;
            case 6:
                arrayList.add("watch");
                break;
            case 7:
                arrayList.add("vrheadset");
                break;
        }
        int i2 = configuration.uiMode & 48;
        if (i2 == 16) {
            arrayList.add("light");
        } else if (i2 == 32) {
            arrayList.add("night");
        }
    }

    private static void loadScreenLayout(Configuration configuration, ArrayList<String> arrayList) {
        int i2 = configuration.screenLayout & 192;
        if (i2 == 64) {
            arrayList.add("ldltr");
        } else if (i2 == 128) {
            arrayList.add("ldrtl");
        }
        arrayList.add("sw" + configuration.smallestScreenWidthDp + "dp");
        arrayList.add("w" + configuration.screenWidthDp + "dp");
        arrayList.add("h" + configuration.screenHeightDp + "dp");
        int i7 = configuration.screenLayout & 15;
        if (i7 == 1) {
            arrayList.add("small");
        } else if (i7 == 2) {
            arrayList.add("normal");
        } else if (i7 == 3) {
            arrayList.add("large");
        } else if (i7 == 4) {
            arrayList.add("xlarge");
        }
        int i8 = configuration.screenLayout & 48;
        if (i8 == 16) {
            arrayList.add("nolong");
        } else if (i8 == 32) {
            arrayList.add("long");
        }
        int i10 = configuration.screenLayout & 768;
        if (i10 == 256) {
            arrayList.add("noround");
        } else if (i10 == 512) {
            arrayList.add("round");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005a, code lost:
        if (r1 == null) goto L_0x005d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String readProcessOutput(java.lang.String... r4) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            int r2 = r4.length     // Catch:{ IOException -> 0x0019 }
            r3 = 1
            if (r2 != r3) goto L_0x001b
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x0019 }
            r3 = 0
            r4 = r4[r3]     // Catch:{ IOException -> 0x0019 }
            java.lang.Process r4 = r2.exec(r4)     // Catch:{ IOException -> 0x0019 }
        L_0x0015:
            r1 = r4
            goto L_0x0024
        L_0x0017:
            r4 = move-exception
            goto L_0x0062
        L_0x0019:
            r4 = move-exception
            goto L_0x0053
        L_0x001b:
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x0019 }
            java.lang.Process r4 = r2.exec(r4)     // Catch:{ IOException -> 0x0019 }
            goto L_0x0015
        L_0x0024:
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0019 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0019 }
            java.io.InputStream r3 = r1.getInputStream()     // Catch:{ IOException -> 0x0019 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0019 }
            r4.<init>(r2)     // Catch:{ IOException -> 0x0019 }
        L_0x0032:
            java.lang.String r2 = r4.readLine()     // Catch:{ all -> 0x0041 }
            if (r2 == 0) goto L_0x0043
            r0.append(r2)     // Catch:{ all -> 0x0041 }
            java.lang.String r2 = "\n"
            r0.append(r2)     // Catch:{ all -> 0x0041 }
            goto L_0x0032
        L_0x0041:
            r2 = move-exception
            goto L_0x004a
        L_0x0043:
            r4.close()     // Catch:{ IOException -> 0x0019 }
        L_0x0046:
            r1.destroy()
            goto L_0x005d
        L_0x004a:
            r4.close()     // Catch:{ all -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r4 = move-exception
            r2.addSuppressed(r4)     // Catch:{ IOException -> 0x0019 }
        L_0x0052:
            throw r2     // Catch:{ IOException -> 0x0019 }
        L_0x0053:
            java.lang.String r2 = "Logger"
            java.lang.String r3 = "readProcessOutput failed"
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r2, (java.lang.String) r3, (java.lang.Throwable) r4)     // Catch:{ all -> 0x0017 }
            if (r1 == 0) goto L_0x005d
            goto L_0x0046
        L_0x005d:
            java.lang.String r4 = r0.toString()
            return r4
        L_0x0062:
            if (r1 == 0) goto L_0x0067
            r1.destroy()
        L_0x0067:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.utils.Logger.readProcessOutput(java.lang.String[]):java.lang.String");
    }

    public static String toColorMode(int i2) {
        String[] strArr = COLOR_MODE;
        if (i2 < strArr.length) {
            return strArr[i2];
        }
        return C0086a.i(i2, "ColorMode#");
    }

    public static String toDebugString(Bitmap bitmap, boolean z) {
        String str;
        if (bitmap.isRecycled()) {
            return ",recycled";
        }
        try {
            StringBuilder sb2 = new StringBuilder();
            String str2 = "";
            if (Build.VERSION.SDK_INT < 34) {
                str = str2;
            } else if (bitmap.hasGainmap()) {
                str = ",G";
            } else {
                str = ",g";
            }
            sb2.append(str);
            if (z) {
                str2 = GlobalPostProcInternalPPInterface.SPLIT_REGEX + bitmap.getConfig() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + bitmap.getColorSpace();
            }
            sb2.append(str2);
            return sb2.toString();
        } catch (IllegalStateException unused) {
            return ",recycled";
        }
    }

    public static String toHexString(int i2, int i7) {
        String hexString = Integer.toHexString(i2);
        int length = hexString.length() - i7;
        if (length > 0) {
            return hexString.substring(length);
        }
        return hexString;
    }

    public static String toMegaBytes(long j2) {
        return C0212a.o(new StringBuilder(), Math.round((((double) j2) / 1048576.0d) * 100.0d) / 100, "MB");
    }

    public static String toSimpleString(Throwable th) {
        if (th == null) {
            return "null";
        }
        return th.getClass().getName() + ' ' + th.getMessage();
    }

    public static String toString(Bundle bundle) {
        return toString(bundle, true);
    }

    private static String toStringInner(View view) {
        String str;
        String str2;
        if (view == null) {
            return "";
        }
        try {
            int visibility = view.getVisibility();
            if (view.getId() != -1) {
                str = view.getResources().getResourceEntryName(view.getId());
            } else {
                str = "NO_ID";
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(view.getClass().getSimpleName());
            String hexString = Integer.toHexString(System.identityHashCode(view));
            if (visibility == 0) {
                str2 = "V";
            } else if (visibility == 8) {
                str2 = "G";
            } else {
                str2 = "IV";
            }
            sb2.append(v(hexString, str, str2, Integer.valueOf(view.getWidth()), Integer.valueOf(view.getHeight())));
            return sb2.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String toVisibilityValue(int i2) {
        if (i2 == 0) {
            return "V";
        }
        if (i2 == 4) {
            return "I";
        }
        if (i2 == 8) {
            return "G";
        }
        return String.valueOf(i2);
    }

    private static String v(int i2, Object... objArr) {
        if (i2 <= 0) {
            return "";
        }
        switch (i2) {
            case 1:
                return " {" + toString(objArr[0]) + '}';
            case 2:
                StringBuilder sb2 = new StringBuilder(" {");
                C0086a.w(objArr[0], sb2, ',');
                sb2.append(toString(objArr[1]));
                sb2.append('}');
                return sb2.toString();
            case 3:
                StringBuilder sb3 = new StringBuilder(" {");
                C0086a.w(objArr[0], sb3, ',');
                C0086a.w(objArr[1], sb3, ',');
                sb3.append(toString(objArr[2]));
                sb3.append('}');
                return sb3.toString();
            case 4:
                StringBuilder sb4 = new StringBuilder(" {");
                C0086a.w(objArr[0], sb4, ',');
                C0086a.w(objArr[1], sb4, ',');
                C0086a.w(objArr[2], sb4, ',');
                sb4.append(toString(objArr[3]));
                sb4.append('}');
                return sb4.toString();
            case 5:
                StringBuilder sb5 = new StringBuilder(" {");
                C0086a.w(objArr[0], sb5, ',');
                C0086a.w(objArr[1], sb5, ',');
                C0086a.w(objArr[2], sb5, ',');
                C0086a.w(objArr[3], sb5, ',');
                sb5.append(toString(objArr[4]));
                sb5.append('}');
                return sb5.toString();
            case 6:
                StringBuilder sb6 = new StringBuilder(" {");
                C0086a.w(objArr[0], sb6, ',');
                C0086a.w(objArr[1], sb6, ',');
                C0086a.w(objArr[2], sb6, ',');
                C0086a.w(objArr[3], sb6, ',');
                C0086a.w(objArr[4], sb6, ',');
                sb6.append(toString(objArr[5]));
                sb6.append('}');
                return sb6.toString();
            case 7:
                StringBuilder sb7 = new StringBuilder(" {");
                C0086a.w(objArr[0], sb7, ',');
                C0086a.w(objArr[1], sb7, ',');
                C0086a.w(objArr[2], sb7, ',');
                C0086a.w(objArr[3], sb7, ',');
                C0086a.w(objArr[4], sb7, ',');
                C0086a.w(objArr[5], sb7, ',');
                sb7.append(toString(objArr[6]));
                sb7.append('}');
                return sb7.toString();
            default:
                StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX, " {", "}");
                for (int i7 = 0; i7 < i2; i7++) {
                    stringJoiner.add(toString(objArr[i7]));
                }
                return stringJoiner.toString();
        }
    }

    public static String vt(Object... objArr) {
        if (objArr.length <= 0) {
            return "";
        }
        Long l = objArr[objArr.length - 1];
        if (l instanceof Long) {
            return v(objArr.length - 1, objArr) + " +" + (System.currentTimeMillis() - l.longValue());
        } else if (!(l instanceof TimeTickLog)) {
            return v(objArr.length, objArr);
        } else {
            return v(objArr.length - 1, objArr) + " +" + ((TimeTickLog) l).summary();
        }
    }

    public static void writeStringToFile(String str, String str2, boolean z) {
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;
        PrintWriter printWriter;
        try {
            fileWriter = new FileWriter(str, z);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(str2);
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
            return;
            throw th;
            throw th;
            throw th;
        } catch (Exception e) {
            Log.e((CharSequence) "Logger", "writeStringToFile failed (" + str + ")", (Throwable) e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
    }

    public static void writeStringsToFile(String str, ArrayList<String> arrayList) {
        FileWriter fileWriter;
        PrintWriter printWriter;
        int i2;
        BufferedReader bufferedReader;
        ArrayList arrayList2 = new ArrayList();
        try {
            if (new File(str).exists()) {
                FileReader fileReader = new FileReader(str);
                try {
                    bufferedReader = new BufferedReader(fileReader);
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        } else if (!readLine.isEmpty()) {
                            arrayList2.add(readLine);
                        }
                    }
                    bufferedReader.close();
                    fileReader.close();
                } catch (Throwable th) {
                    fileReader.close();
                    throw th;
                }
            }
        } catch (Exception e) {
            Log.e((CharSequence) "Logger", "writeStringsToFile failed", (Throwable) e);
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        arrayList2.addAll(arrayList);
        try {
            fileWriter = new FileWriter(str);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            try {
                printWriter = new PrintWriter(bufferedWriter);
                if (arrayList2.size() > 1000) {
                    i2 = arrayList2.size() - 1000;
                } else {
                    i2 = 0;
                }
                while (i2 < arrayList2.size()) {
                    printWriter.println((String) arrayList2.get(i2));
                    i2++;
                }
                printWriter.close();
                bufferedWriter.close();
                fileWriter.close();
                return;
            } catch (Throwable th3) {
                bufferedWriter.close();
                throw th3;
            }
        } catch (Exception e7) {
            Log.e((CharSequence) "Logger", "writeStringsToFile failed", (Throwable) e7);
            return;
        } catch (Throwable th4) {
            th.addSuppressed(th4);
        }
        throw th;
        throw th;
        throw th;
    }

    private static byte[] xorWithKey(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr3[i2] = (byte) (bArr[i2] ^ bArr2[i2 % bArr2.length]);
        }
        return bArr3;
    }

    public static String getEncodedString(String str) {
        if (str == null) {
            return "";
        }
        return encode(str, RandomNumber.nextInt(4) + 4);
    }

    public static String toSimpleString(View view) {
        if (view == null) {
            return "View{null}";
        }
        int visibility = view.getVisibility();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getSimpleName((Object) view));
        sb2.append("{");
        sb2.append(visibility == 0 ? 'V' : visibility == 4 ? 'I' : 'G');
        sb2.append(view.isEnabled() ? 'E' : 'e');
        sb2.append(view.isFocusable() ? 'F' : 'f');
        sb2.append(view.isClickable() ? 'C' : 'c');
        sb2.append(view.isLongClickable() ? 'L' : 'l');
        sb2.append(view instanceof ViewStub ? 'S' : 's');
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(getResourceName(view));
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(getEncodedString(view.getTransitionName()));
        sb2.append(", Rect(");
        sb2.append(view.getLeft());
        sb2.append(',');
        sb2.append(view.getTop());
        sb2.append('-');
        sb2.append(view.getRight());
        sb2.append(',');
        sb2.append(view.getBottom());
        sb2.append("), ");
        return C0212a.p(sb2, view instanceof ImageView ? getDrawableBounds(((ImageView) view).getDrawable()) : "null", "}");
    }

    public static String toString(Account account) {
        if (account == null) {
            return "Account{null}";
        }
        return C0212a.p(new StringBuilder("Account{type="), account.type, "}");
    }

    public static String getSimpleName(String str) {
        try {
            return str.substring(str.lastIndexOf(46) + 1);
        } catch (Exception unused) {
            return str;
        }
    }

    public static String toString(Bundle bundle, boolean z) {
        if (bundle == null) {
            return "Bundle{null}";
        }
        Set<String> keySet = bundle.keySet();
        if (keySet.isEmpty()) {
            return "Bundle{empty}";
        }
        StringBuilder sb2 = new StringBuilder();
        keySet.forEach(new C0679q(3, sb2, bundle));
        sb2.deleteCharAt(sb2.length() - 1);
        if (z) {
            return getEncodedString("Bundle{" + sb2 + "}");
        }
        return "Bundle{" + sb2 + "}";
    }

    public static String vt(long j2) {
        return " +" + (System.currentTimeMillis() - j2);
    }

    public static String vt(TimeTickLog timeTickLog) {
        return " +" + timeTickLog.summary();
    }

    public static String toString(Drawable drawable) {
        if (drawable == null) {
            return "drawable{null}";
        }
        return drawable.getClass().getSimpleName() + "{" + drawable.getIntrinsicWidth() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + drawable.getIntrinsicHeight() + "}";
    }

    public static String toString(View view) {
        StringBuilder sb2;
        if (view == null) {
            return "";
        }
        ViewParent parent = view.getParent();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(toStringInner(view));
        if (parent instanceof View) {
            sb2 = new StringBuilder(", P=");
            sb2.append(toStringInner((View) parent));
        } else {
            sb2 = new StringBuilder(", P=");
            sb2.append(parent);
        }
        sb3.append(sb2.toString());
        return sb3.toString();
    }

    public static String toSimpleString(Bitmap bitmap) {
        if (bitmap == null) {
            return "Bitmap{null}";
        }
        return getSimpleName((Object) bitmap) + "{" + bitmap.getWidth() + "x" + bitmap.getHeight() + toDebugString(bitmap, false) + "}";
    }

    public static String toString(Bitmap bitmap) {
        if (bitmap == null) {
            return "Bitmap{null}";
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getSimpleName((Object) bitmap));
        sb2.append("{");
        sb2.append(bitmap.getWidth());
        sb2.append("x");
        sb2.append(bitmap.getHeight());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(bitmap.getAllocationByteCount());
        sb2.append(toDebugString(bitmap, true));
        return C0212a.p(sb2, bitmap.isMutable() ? ",M" : ",m", "}");
    }

    public static String toString(Fragment fragment) {
        return getEncodedString(getSimpleName((Object) fragment) + ((String) Optional.ofNullable(fragment.getArguments()).map(new C0670h(18)).orElse("")));
    }

    public static String toString(Intent intent) {
        if (intent == null) {
            return "Intent{null}";
        }
        return intent + " " + toString(intent.getExtras());
    }

    public static String toString(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return toString(uri != null ? uri.toString() : null, strArr, str, strArr2, str2);
    }

    public static String toString(String str, String[] strArr, String str2, String[] strArr2, String str3) {
        StringBuilder sb2 = new StringBuilder();
        if (str == null) {
            str = "nullUri";
        }
        sb2.append(str);
        sb2.append(" [");
        C0086a.z(sb2, Arrays.toString(strArr), "] [", str2, "] [");
        return C0212a.q(sb2, Arrays.toString(strArr2), "] [", str3, "]");
    }

    public static String toString(Cursor cursor) {
        return "CURSOR#[" + getCursorInfo(cursor) + ']';
    }

    public static String toString(Cursor[] cursorArr) {
        StringBuilder sb2 = new StringBuilder("CURSOR#" + cursorArr.length);
        if (cursorArr.length > 0) {
            sb2.append('[');
            sb2.append(getCursorInfo(cursorArr[0]));
            for (int i2 = 1; i2 < cursorArr.length; i2++) {
                sb2.append(',');
                sb2.append(getCursorInfo(cursorArr[i2]));
            }
            sb2.append(']');
        }
        return sb2.toString();
    }

    public static String toString(Object obj) {
        if (obj instanceof Bitmap) {
            return toString((Bitmap) obj);
        }
        if (obj instanceof Drawable) {
            return toString((Drawable) obj);
        }
        if (obj instanceof View) {
            return toString((View) obj);
        }
        if (obj instanceof Cursor) {
            return toString((Cursor) obj);
        }
        if (obj instanceof Intent) {
            return toString((Intent) obj);
        }
        if (obj instanceof Bundle) {
            return toString((Bundle) obj);
        }
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() ? "T" : "F" : obj == null ? "null" : obj.toString();
    }

    public static String toString(Configuration configuration) {
        ArrayList arrayList = new ArrayList();
        loadConfigBasics(configuration, arrayList);
        loadScreenLayout(configuration, arrayList);
        loadConfigColorMode(configuration, arrayList);
        loadConfigOrientation(configuration, arrayList);
        loadConfigUiMode(configuration, arrayList);
        loadConfigDensityDpi(configuration, arrayList);
        loadConfigInput(configuration, arrayList);
        loadConfigNavigation(configuration, arrayList);
        return TextUtils.join("-", arrayList);
    }

    public static String v(Object... objArr) {
        return v(objArr.length, objArr);
    }
}
